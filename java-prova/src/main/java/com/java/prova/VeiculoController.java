package com.java.prova;

import com.java.prova.dto.AluguelDTO;
import com.java.prova.model.Veiculo;
import com.java.prova.service.AluguelService;
import com.java.prova.service.CondutorService;
import com.java.prova.service.VeiculoService;
import org.hibernate.secure.spi.IntegrationException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Welerson Luiz Pawlak
 */

@RestController
@RequestMapping("/v1/aluguel")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private CondutorService condutorService;

    @Autowired
    private CoreService coreService;

    @GetMapping(value = "/")
    public String home() {
        return "Veículos Elétricos v1";
    }

    @GetMapping(value = "/{idVeiculo}")
    public ResponseEntity<Veiculo> consultarVeiculo(@PathVariable Integer idVeiculo){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        Veiculo veiculo = veiculoService.findOne(idVeiculo);
        return ResponseEntity.ok().headers(responseHeaders).body(veiculo);
    }

    @GetMapping(value = "/movimentacao/{idVeiculo}")
    public String consultarMovimentacaoVeiculo(@PathVariable("idVeiculo") int idVeiculo) throws SQLException {
        return coreService.consultarMovimentacao(idVeiculo);
    }

    @PostMapping
    public ResponseEntity<AluguelDTO> iniciarAlugel(@RequestBody AluguelDTO aluguelDTO) throws SQLException {
        if (Objects.isNull(aluguelDTO)){
            throw new IntegrationException("Payload Nulo!");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(aluguelDTO.getHoraOperacao());
        cal.add(Calendar.HOUR_OF_DAY, 3);
        aluguelDTO.setHoraOperacao(cal.getTime());

        if (dataIsInvalid(new DateTime(aluguelDTO.getHoraOperacao()))){
            throw new IntegrationException("Data e hora inválida para alugar.");
        }

        AluguelDTO resposta = coreService.iniciarAluguel(aluguelDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok().headers(responseHeaders).body(resposta);
    }

    @PutMapping
    public ResponseEntity<AluguelDTO> finalizarAluguel(@RequestBody AluguelDTO aluguelDTO) throws SQLException, ParseException {
        if (Objects.isNull(aluguelDTO)){
            throw new IntegrationException("Payload Nulo!");
        }

        // ToDo Identificar erro de conversao causando 3 horas a mais
        Calendar cal = Calendar.getInstance();
        cal.setTime(aluguelDTO.getHoraOperacao());
        cal.add(Calendar.HOUR_OF_DAY, 3);
        aluguelDTO.setHoraOperacao(cal.getTime());

        if (dataIsInvalid(new DateTime(aluguelDTO.getHoraOperacao()))){
            throw new IntegrationException("Data e hora inválida para alugar.");
        }

        AluguelDTO resposta = coreService.finalizarAluguel(aluguelDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok().headers(responseHeaders).body(resposta);
    }

    public boolean dataIsInvalid(DateTime data){
        return data.getHourOfDay() < 6 || data.getHourOfDay() > 22 || (data.getHourOfDay() == 22 && data.getMinuteOfHour() > 0);
    }

}

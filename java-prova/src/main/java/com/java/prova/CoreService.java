package com.java.prova;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.java.prova.dto.AluguelDTO;
import com.java.prova.model.Aluguel;
import com.java.prova.service.AluguelService;
import org.hibernate.secure.spi.IntegrationException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author Welerson Luiz Pawlak
 */

@Component
public class CoreService {

    public CoreService() throws SQLException {
    }

    @Autowired
    private AluguelService aluguelService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

    public String consultarMovimentacao(Integer idVeiculo) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT SUM(PRECO), COUNT(PRECO) FROM ALUGUEL WHERE ID_VEICULO = ? AND DATA_FIM_ALUGUEL IS NOT NULL");
        ps.setInt(1, idVeiculo);

        ResultSet resultSet = ps.executeQuery();

        resultSet.first();

        JsonObject response = new JsonObject();
        response.addProperty("numViagens", resultSet.getString(2));
        response.addProperty("ganhoTotal", resultSet.getString(1));

        return new GsonBuilder().setPrettyPrinting().create().toJson(response);
    }

    public AluguelDTO iniciarAluguel(AluguelDTO aluguelDTO) throws IntegrationException, SQLException {
        if (isAlugado(aluguelDTO.getIdVeiculo()))
            throw new IntegrationException("Veículo ainda em utilização");

        return insertAluguel(aluguelDTO);
    }

    public AluguelDTO finalizarAluguel(AluguelDTO aluguelDTO) throws SQLException, ParseException {

        if (!isAlugado(aluguelDTO.getIdVeiculo()))
            throw new IntegrationException("O veículo não tem nenhuma viagem iniciada.");

        Double preco = calcularPreco(aluguelDTO);

        if (preco == null){
            throw new IntegrationException("Erro ao calcular Preco, verifique as datas");
        }

        updateAluguel(aluguelDTO, preco);
        updateVeiculoTable(aluguelDTO);
        updateCondutorTable(aluguelDTO, preco);

        Aluguel aluguel = new Aluguel();
        aluguel.setIdVeiculo(aluguelDTO.getIdVeiculo());
        aluguel.setEmailCondutor(aluguelDTO.getEmailCondutor());
        aluguel.setDataFimAluguel(format.format(aluguelDTO.getHoraOperacao()));

        return aluguelDTO;
    }

    private boolean isAlugado(Integer idVeiculo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALUGUEL WHERE ID_VEICULO = ? AND DATA_FIM_ALUGUEL IS NULL");
        ps.setInt(1, idVeiculo);

        return ps.executeQuery().first();
    }

    private AluguelDTO insertAluguel(AluguelDTO aluguelDTO){
        Aluguel aluguel = new Aluguel();

        aluguel.setIdAluguel(new Random().nextInt(1000000000));
        aluguel.setIdVeiculo(aluguelDTO.getIdVeiculo());
        aluguel.setEmailCondutor(aluguelDTO.getEmailCondutor());
        aluguel.setDataInicioAluguel(format.format(aluguelDTO.getHoraOperacao()));

        aluguelService.save(aluguel);

        return aluguelDTO;
    }

    private void updateVeiculoTable(AluguelDTO aluguelDTO) throws SQLException {
        String update = "UPDATE VEICULO SET ULTIMA_CORRIDA_VEICULO = ? WHERE ID_VEICULO = ?";

        PreparedStatement ps  = connection.prepareStatement(update);
        ps.setString(1, format.format(aluguelDTO.getHoraOperacao()));
        ps.setInt(2, aluguelDTO.getIdVeiculo());

        ps.execute();
    }

    private boolean updateAluguel(AluguelDTO aluguelDTO, Double preco) throws SQLException {
        String update = "UPDATE ALUGUEL SET DATA_FIM_ALUGUEL = ?, PRECO = ? WHERE ID_VEICULO = ? AND DATA_FIM_ALUGUEL IS NULL";

        PreparedStatement ps  = connection.prepareStatement(update);
        ps.setString(1, format.format(aluguelDTO.getHoraOperacao()));
        ps.setDouble(2, preco);
        ps.setInt(3, aluguelDTO.getIdVeiculo());

        ps.execute();
        return true;
    }

    private boolean updateCondutorTable(AluguelDTO aluguelDTO, Double preco) throws SQLException {
        String select = "SELECT CPF_CONDUTOR, SALDO_CONDUTOR FROM CONDUTOR WHERE EMAIL_CONDUTOR = ?";

        PreparedStatement ps  = connection.prepareStatement(select);
        ps.setString(1, aluguelDTO.getEmailCondutor());
        ResultSet resultSet = ps.executeQuery();

        if (!resultSet.next()){
            return false;
        }

        int idCondutor = resultSet.getInt(1);
        Double saldoCondutor = resultSet.getDouble(2);

        saldoCondutor -= preco;

        String update = "UPDATE CONDUTOR SET SALDO_CONDUTOR = ? WHERE CPF_CONDUTOR = ?";

        ps = connection.prepareStatement(update);
        ps.setDouble(1, saldoCondutor);
        ps.setInt(1, idCondutor);

        ps.execute();
        return true;
    }

    private Double calcularPreco(AluguelDTO aluguelDTO) throws SQLException, ParseException {
        Double total = 0.0;

        PreparedStatement ps = connection.prepareStatement("SELECT DATA_INICIO_ALUGUEL FROM ALUGUEL WHERE ID_VEICULO = ? AND DATA_FIM_ALUGUEL IS NULL");
        ps.setString(1, aluguelDTO.getIdVeiculo().toString());
        ResultSet resultSet = ps.executeQuery();

        resultSet.next();

        DateTime dataInicio = new DateTime(format.parse(resultSet.getString(1)));
        DateTime dataFim = new DateTime(aluguelDTO.getHoraOperacao());
        int minutosUtilizados = dataFim.getMinuteOfDay() - dataInicio.getMinuteOfDay();

        if (minutosUtilizados < 0){
            return null;
        }

        ps = connection.prepareStatement("SELECT TIPO_VEICULO FROM VEICULO WHERE ID_VEICULO = ?");
        ps.setString(1, aluguelDTO.getIdVeiculo().toString());
        resultSet = ps.executeQuery();

        if (!resultSet.next()){
            return null;
        }

        switch (resultSet.getString("TIPO_VEICULO")){
            case "PATINETE":
                if (dataInicio.getDayOfWeek() == 0 || dataInicio.getDayOfWeek() == 7){
                    total = 0.72*minutosUtilizados;
                } else {
                    total = 0.48*minutosUtilizados;
                }
                break;
            case "BICICLETA":
                if (dataInicio.getDayOfWeek() == 0 || dataInicio.getDayOfWeek() == 7){
                    total = 1.00*minutosUtilizados;
                } else {
                    total = 0.63*minutosUtilizados;
                }
                break;
            default: break;
        }

        return Math.floor(total * 100) / 100;
    }

}

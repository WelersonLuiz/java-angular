package com.java.prova.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Welerson Luiz Pawlak
 */

@Entity
@Table(name = "VEICULO")
public class Veiculo {

    @Column(name = "ID_VEICULO")
    @Id
    private Integer idVeiculo;

    @Column
    private String tipoVeiculo;

    @Column
    private Integer anoVeiculo;

    @Column
    private String marcaVeiculo;

    @Column
    private String modeloVeiculo;

    @Column
    private String corVeiculo;

    @Column
    private String velocidadeMaxVeiculo;

    @Column
    private String quilometragemVeiculo;

    @Column
    private Date ultimaCorridaVeiculo;

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(Integer anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public void setCorVeiculo(String corVeiculo) {
        this.corVeiculo = corVeiculo;
    }

    public String getVelocidadeMaxVeiculo() {
        return velocidadeMaxVeiculo;
    }

    public void setVelocidadeMaxVeiculo(String velocidadeMaxVeiculo) {
        this.velocidadeMaxVeiculo = velocidadeMaxVeiculo;
    }

    public String getQuilometragemVeiculo() {
        return quilometragemVeiculo;
    }

    public void setQuilometragemVeiculo(String quilometragemVeiculo) {
        this.quilometragemVeiculo = quilometragemVeiculo;
    }

    public Date getUltimaCorridaVeiculo() {
        return ultimaCorridaVeiculo;
    }

    public void setUltimaCorridaVeiculo(Date ultimaCorridaVeiculo) {
        this.ultimaCorridaVeiculo = ultimaCorridaVeiculo;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
            "idVeiculo=" + idVeiculo +
            ", tipoVeiculo='" + tipoVeiculo + '\'' +
            ", anoVeiculo=" + anoVeiculo +
            ", marcaVeiculo='" + marcaVeiculo + '\'' +
            ", modeloVeiculo='" + modeloVeiculo + '\'' +
            ", corVeiculo='" + corVeiculo + '\'' +
            ", velocidadeMaxVeiculo='" + velocidadeMaxVeiculo + '\'' +
            ", quilometragemVeiculo='" + quilometragemVeiculo + '\'' +
            ", ultimaCorridaVeiculo=" + ultimaCorridaVeiculo +
            '}';
    }
}

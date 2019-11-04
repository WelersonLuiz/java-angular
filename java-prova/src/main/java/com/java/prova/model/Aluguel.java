package com.java.prova.model;

import com.google.gson.GsonBuilder;

import javax.persistence.*;

/**
 * @author Welerson Luiz Pawlak
 */

@Entity
@Table(name = "ALUGUEL")
public class Aluguel {

    @Column(name = "ID_ALUGUEL")
    @Id
    private Integer idAluguel;

    @Column
    private Integer idVeiculo;

    @Column
    private String emailCondutor;

    @Column
    private String dataInicioAluguel;

    @Column
    private String dataFimAluguel;

    @Column
    private Float preco;

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getEmailCondutor() {
        return emailCondutor;
    }

    public void setEmailCondutor(String emailCondutor) {
        this.emailCondutor = emailCondutor;
    }

    public String getDataInicioAluguel() {
        return dataInicioAluguel;
    }

    public void setDataInicioAluguel(String dataInicioAluguel) {
        this.dataInicioAluguel = dataInicioAluguel;
    }

    public String getDataFimAluguel() {
        return dataFimAluguel;
    }

    public void setDataFimAluguel(String dataFimAluguel) {
        this.dataFimAluguel = dataFimAluguel;
    }

    public Integer getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(Integer idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}

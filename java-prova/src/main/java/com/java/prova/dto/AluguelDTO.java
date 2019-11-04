package com.java.prova.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * @author Welerson Luiz Pawlak
 */
public class AluguelDTO {

    private Integer idVeiculo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date horaOperacao;
    private String emailCondutor;

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Date getHoraOperacao() {
        return horaOperacao;
    }

    public void setHoraOperacao(Date horaOperacao) {
        this.horaOperacao = horaOperacao;
    }

    public String getEmailCondutor() {
        return emailCondutor;
    }

    public void setEmailCondutor(String emailCondutor) {
        this.emailCondutor = emailCondutor;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}

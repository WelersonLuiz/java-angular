package com.java.prova.model;

import javax.persistence.*;

/**
 * @author Welerson Luiz Pawlak
 */

@Entity
@Table(name = "CONDUTOR")
public class Condutor {

    @Column(name = "CPF_CONDUTOR")
    @Id
    private Integer cpfCondutor;

    @Column
    private String nomeCondutor;

    @Column
    private String emailCondutor;

    @Column
    private Double saldoCondutor;

    public Integer getCpfCondutor() {
        return cpfCondutor;
    }

    public void setCpfCondutor(Integer cpfCondutor) {
        this.cpfCondutor = cpfCondutor;
    }

    public String getNomeCondutor() {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
    }

    public String getEmailCondutor() {
        return emailCondutor;
    }

    public void setEmailCondutor(String emailCondutor) {
        this.emailCondutor = emailCondutor;
    }

    public Double getSaldoCondutor() {
        return saldoCondutor;
    }

    public void setSaldoCondutor(Double saldoCondutor) {
        this.saldoCondutor = saldoCondutor;
    }

    @Override
    public String toString() {
        return "CondutorEntity{" +
            "cpfCondutor=" + cpfCondutor +
            ", nomeCondutor='" + nomeCondutor + '\'' +
            ", emailCondutor='" + emailCondutor + '\'' +
            ", saldoCondutor=" + saldoCondutor +
            '}';
    }
}

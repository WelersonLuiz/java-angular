package com.java.prova.service;

import com.java.prova.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Welerson Luiz Pawlak
 */

public interface VeiculoService extends JpaRepository<Veiculo, Integer> {}

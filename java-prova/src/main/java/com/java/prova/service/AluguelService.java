package com.java.prova.service;

import com.java.prova.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Welerson Luiz Pawlak
 */

public interface AluguelService extends JpaRepository<Aluguel, Integer> {}

package com.java.prova.service;

import com.java.prova.model.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Welerson Luiz Pawlak
 */

public interface CondutorService extends JpaRepository<Condutor, Integer> {}

package com.example.finaltestbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByData(LocalDate data);

    List<Evento> findBySedeId(Long sedeId);
    
}

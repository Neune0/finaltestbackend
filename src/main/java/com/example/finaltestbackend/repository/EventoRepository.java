package com.example.finaltestbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.finaltestbackend.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByData(LocalDate data);

    List<Evento> findBySedeId(Long sedeId);
    
    // Add these methods for price filtering
    List<Evento> findByPrezzoLessThanEqual(Double maxPrezzo);
    
    List<Evento> findByDataAndPrezzoLessThanEqual(LocalDate data, Double maxPrezzo);
    
    List<Evento> findBySedeIdAndPrezzoLessThanEqual(Long sedeId, Double maxPrezzo);
    
    List<Evento> findByDataAndSedeIdAndPrezzoLessThanEqual(LocalDate data, Long sedeId, Double maxPrezzo);

    @Query("SELECT COALESCE(SUM(p.numeroBiglietti), 0) FROM Prenotazione p WHERE p.evento.id = :eventoId")
    Integer countBookedTickets(@Param("eventoId") Long eventoId);
}

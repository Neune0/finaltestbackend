package com.example.finaltestbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Prenotazione;
import com.example.finaltestbackend.model.Utente;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUtente(Utente utente);

    @Query("SELECT SUM(p.numeroBiglietti) FROM Prenotazione p WHERE p.evento = ?1")
    int countBigliettiByEvento(Evento evento);
    
}

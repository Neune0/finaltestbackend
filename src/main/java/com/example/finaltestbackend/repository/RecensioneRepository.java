package com.example.finaltestbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Recensione;
import com.example.finaltestbackend.model.Utente;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    boolean existsByUtenteAndEvento(Utente utente, Evento evento);

    List<Recensione> findByUtenteOrderById(Utente utente);

    List<Recensione> findByUtente(Utente utente);
}

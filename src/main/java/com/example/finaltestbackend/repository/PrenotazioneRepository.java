package com.example.finaltestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    
}

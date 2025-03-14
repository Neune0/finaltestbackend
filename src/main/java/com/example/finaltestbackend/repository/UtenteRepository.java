package com.example.finaltestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByEmail(String email);
    
}

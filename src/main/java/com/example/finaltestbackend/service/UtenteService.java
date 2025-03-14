package com.example.finaltestbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.finaltestbackend.model.Role;
import com.example.finaltestbackend.model.Utente;
import com.example.finaltestbackend.repository.UtenteRepository;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente register(Utente utente) {
        if (utente.getRuolo() == null) {
            utente.setRuolo(Role.ROLE_USER);
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }
}
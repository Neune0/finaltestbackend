package com.example.finaltestbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finaltestbackend.model.Recensione;
import com.example.finaltestbackend.repository.RecensioneRepository;
import com.example.finaltestbackend.model.Utente;
import com.example.finaltestbackend.model.Evento;
@Service
public class RecensioneService {
    private final RecensioneRepository recensioneRepository;
    private final UtenteService utenteService;
    private final EventoService eventoService;
    
    public RecensioneService(RecensioneRepository recensioneRepository, UtenteService utenteService, EventoService eventoService) {
        this.recensioneRepository = recensioneRepository;
        this.utenteService = utenteService;
        this.eventoService = eventoService;
    }

    public Recensione findById(Long id) {
        return recensioneRepository.findById(id).orElse(null);
    }

    public boolean existsByUtenteAndEvento(Long utenteId, Long eventoId) {
        Utente utente = utenteService.findById(utenteId);
        Evento evento = eventoService.findById(eventoId);
        return recensioneRepository.existsByUtenteAndEvento(utente, evento);
    }

    public void addRecensione(Recensione recensione) {
        recensioneRepository.save(recensione);
    }

    public List<Recensione> findByUtente(Long utenteId){
        Utente utente = utenteService.findById(utenteId);
        return recensioneRepository.findByUtente(utente);
    }

    public void deleteById(Long id) {
        recensioneRepository.deleteById(id);
    }
    
}

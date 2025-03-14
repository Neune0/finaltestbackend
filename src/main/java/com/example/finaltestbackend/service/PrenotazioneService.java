package com.example.finaltestbackend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Prenotazione;
import com.example.finaltestbackend.model.Utente;
import com.example.finaltestbackend.repository.ArtistaRepository;
import com.example.finaltestbackend.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

    private final ArtistaRepository artistaRepository;
    private final PrenotazioneRepository prenotazioneRepository;


    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, ArtistaRepository artistaRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.artistaRepository = artistaRepository;
    }
    
    public Prenotazione createPrenotazione(Utente utente, Evento evento,int numeroBiglietti){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazione.setNumeroBiglietti(numeroBiglietti);
        prenotazione.setDataPrenotazione(LocalDate.now());
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> findByUtente(Utente utente){
        return prenotazioneRepository.findByUtente(utente);
    }

    public int countBigliettiByEvento(Evento evento){
        return prenotazioneRepository.countBigliettiByEvento(evento);
    }

    public void deletePrenotazione(Long Prenotazione){
        prenotazioneRepository.deleteById(Prenotazione);
    }

    public boolean isPrenotazioneOwnedByUtente(Long prenotazioneId, Utente utente) {
        return prenotazioneRepository.findById(prenotazioneId)
                .map(p -> p.getUtente().getId().equals(utente.getId()))
                .orElse(false);
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public List<Prenotazione> findByEventoId(Long eventoId) {
        return prenotazioneRepository.findByEventoId(eventoId);
    }
}

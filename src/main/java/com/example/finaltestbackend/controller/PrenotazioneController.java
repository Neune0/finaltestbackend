package com.example.finaltestbackend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Prenotazione;
import com.example.finaltestbackend.model.Utente;
import com.example.finaltestbackend.service.EventoService;
import com.example.finaltestbackend.service.PrenotazioneService;
import com.example.finaltestbackend.service.UtenteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
    
    private final PrenotazioneService prenotazioneService;
    private final EventoService eventoService;
    private final UtenteService utenteService;
    
    @PostMapping("/create")
    public String createPrenotazione(
            @RequestParam Long eventoId,
            @RequestParam Integer numeroBiglietti,
            Authentication authentication) {
        
        // Get current user
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);
        
        // Get event
        Evento evento = eventoService.findById(eventoId);
        
        // Validate number of tickets
        if (numeroBiglietti <= 0) {
            return "redirect:/eventi/" + eventoId;
        }
        
        // Check if enough seats are available
        int bigliettiPrenotati = prenotazioneService.countBigliettiByEvento(evento);
        int postiDisponibili = evento.getSede().getCapienza() - bigliettiPrenotati;
        
        if (numeroBiglietti > postiDisponibili) {
            return "redirect:/eventi/" + eventoId;
        }
        
        // Create reservation
        prenotazioneService.createPrenotazione(utente, evento, numeroBiglietti);
        
        return "redirect:/eventi/" + eventoId;
    }
    
    @GetMapping("/history")
    public String getPrenotazioniHistory(Authentication authentication, Model model) {
        // Get current user
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);
        
        if (utente == null) {
            return "redirect:/login";
        }
        
        // Get reservations
        List<Prenotazione> prenotazioni = prenotazioneService.findByUtente(utente);
        model.addAttribute("prenotazioni", prenotazioni);
        
        return "prenotazioni";
    }
}
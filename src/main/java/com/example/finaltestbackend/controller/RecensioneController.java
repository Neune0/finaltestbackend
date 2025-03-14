package com.example.finaltestbackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finaltestbackend.service.EventoService;
import com.example.finaltestbackend.service.RecensioneService;
import com.example.finaltestbackend.service.UtenteService;

import lombok.RequiredArgsConstructor;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Recensione;
import com.example.finaltestbackend.model.Utente;

@Controller
@RequestMapping("/recensioni")
@RequiredArgsConstructor
public class RecensioneController {
    private final RecensioneService recensioneService;
    private final EventoService eventoService;
    private final UtenteService utenteService;

    @PostMapping("/create")
    public String createRecensione(
        @ModelAttribute Recensione recensione,
            @RequestParam Long eventoId,
            Authentication authentication){
        // prendo l'utente loggato
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);
        Long utenteId = utente.getId();

        // prendo l'evento associato alla recensione
        Evento evento = eventoService.findById(eventoId);

        // controllo se esiste gia una recensione associata all'evento
        boolean recensioneEsistente = recensioneService.existsByUtenteAndEvento(utenteId, eventoId);
        if(recensioneEsistente){
            return "redirect:/eventi/" + eventoId;
        }

        // setto l'utente e l'evento alla recensione
        recensione.setUtente(utente);
        recensione.setEvento(evento);
        recensioneService.addRecensione(recensione);

        return  "redirect:/eventi/" + eventoId;
    }
    
}

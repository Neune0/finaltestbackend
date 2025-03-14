package com.example.finaltestbackend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            Authentication authentication) {
        // prendo l'utente loggato
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);
        Long utenteId = utente.getId();

        // prendo l'evento associato alla recensione
        Evento evento = eventoService.findById(eventoId);

        // controllo se esiste gia una recensione associata all'evento
        boolean recensioneEsistente = recensioneService.existsByUtenteAndEvento(utenteId, eventoId);
        if (recensioneEsistente) {
            return "redirect:/eventi/" + eventoId;
        }

        // setto l'utente e l'evento alla recensione
        recensione.setUtente(utente);
        recensione.setEvento(evento);
        recensioneService.addRecensione(recensione);

        return "redirect:/eventi/" + eventoId;
    }

    @GetMapping("/history")
    public String getRecensioneHistory(Authentication authentication, Model model) {
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);

        List<Recensione> recensioni = recensioneService.findByUtente(utente.getId());
        model.addAttribute("recensioni", recensioni);
        return "recensioni";
    }

    @DeleteMapping("/{id}")
    public String deleteRecensione(
            @PathVariable Long id,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        // Get current user
        String email = authentication.getName();
        Utente utente = utenteService.findByEmail(email);

        if (utente == null) {
            redirectAttributes.addFlashAttribute("error", "Utente non trovato");
            return "redirect:/recensioni/history";
        }

        // Check if review exists and belongs to current user
        Recensione recensione = recensioneService.findById(id);
        if (recensione == null || !recensione.getUtente().getId().equals(utente.getId())) {
            redirectAttributes.addFlashAttribute("error", "Recensione non trovata o non sei autorizzato a eliminarla");
            return "redirect:/recensioni/history";
        }

        // Delete the review
        recensioneService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Recensione eliminata con successo");

        return "redirect:/recensioni/history";
    }
}

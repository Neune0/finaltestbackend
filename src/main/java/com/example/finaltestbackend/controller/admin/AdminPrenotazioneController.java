package com.example.finaltestbackend.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finaltestbackend.model.Prenotazione;
import com.example.finaltestbackend.service.EventoService;
import com.example.finaltestbackend.service.PrenotazioneService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/prenotazioni")
@RequiredArgsConstructor
public class AdminPrenotazioneController {
    
    private final PrenotazioneService prenotazioneService;
    private final EventoService eventoService;
    
    @GetMapping
    public String listAll(Model model) {
        List<Prenotazione> prenotazioni = prenotazioneService.findAll();
        model.addAttribute("prenotazioni", prenotazioni);
        return "admin/prenotazioni/list";
    }
    
    @GetMapping("/evento/{eventoId}")
    public String listByEvento(@PathVariable Long eventoId, Model model) {
        model.addAttribute("evento", eventoService.findById(eventoId));
        model.addAttribute("prenotazioni", prenotazioneService.findByEventoId(eventoId));
        return "admin/prenotazioni/by-evento";
    }
}
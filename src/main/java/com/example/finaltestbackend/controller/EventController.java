package com.example.finaltestbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.repository.EventoRepository;
import com.example.finaltestbackend.service.EventoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/eventi/{id}")
@RequiredArgsConstructor
public class EventController {

    private final EventoRepository eventoRepository;
    private final EventoService eventoService;  // Add this service

    @GetMapping
    public String getEvent(@PathVariable Long id, Model model) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento == null) {
            return "redirect:/login";
        }
        
        // Add ticket availability information
        int bookedTickets = eventoService.getBookedTickets(id);
        int availableTickets = evento.getSede().getCapienza() - bookedTickets;
        int occupancyPercentage = eventoService.getAvailabilityPercentage(evento);
        
        model.addAttribute("evento", evento);
        model.addAttribute("bookedTickets", bookedTickets);
        model.addAttribute("availableTickets", availableTickets);
        model.addAttribute("occupancyPercentage", occupancyPercentage);
        
        return "event";
    }
    
}

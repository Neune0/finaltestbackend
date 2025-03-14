package com.example.finaltestbackend.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Sede;

import com.example.finaltestbackend.service.EventoService;
import com.example.finaltestbackend.service.SedeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final EventoService eventoService;
    private final SedeService sedeService;

    @GetMapping
    public String home(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Long sedeId,
            @RequestParam(required = false) Double maxPrezzo,
            Model model) {

        // Get all venues for the dropdown
        List<Sede> sedi = sedeService.findAll();
        model.addAttribute("sedi", sedi);

        // Get events with all filters
        List<Evento> eventi = eventoService.findWithFilters(data, sedeId, maxPrezzo);
        model.addAttribute("eventi", eventi);
        
        // Calculate availability for each event
        Map<Long, Integer> bookedTicketsMap = new HashMap<>();
        Map<Long, Integer> availableTicketsMap = new HashMap<>();
        Map<Long, Integer> occupancyPercentageMap = new HashMap<>();
        
        for (Evento evento : eventi) {
            int bookedTickets = eventoService.getBookedTickets(evento.getId());
            int availableTickets = evento.getSede().getCapienza() - bookedTickets;
            int occupancyPercentage = eventoService.getAvailabilityPercentage(evento);
            
            bookedTicketsMap.put(evento.getId(), bookedTickets);
            availableTicketsMap.put(evento.getId(), availableTickets);
            occupancyPercentageMap.put(evento.getId(), occupancyPercentage);
        }
        
        model.addAttribute("bookedTicketsMap", bookedTicketsMap);
        model.addAttribute("availableTicketsMap", availableTicketsMap);
        model.addAttribute("occupancyPercentageMap", occupancyPercentageMap);

        return "home";
    }
}

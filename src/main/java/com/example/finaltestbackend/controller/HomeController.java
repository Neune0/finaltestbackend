package com.example.finaltestbackend.controller;

import java.time.LocalDate;
import java.util.List;

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
            Model model) {
        
        // Get all venues for the dropdown
        List<Sede> sedi = sedeService.findAll();
        model.addAttribute("sedi", sedi);
        
        // Apply filters if provided
        List<Evento> eventi;
        
        if (data != null && sedeId != null) {
            // Filter by both date and venue
            eventi = eventoService.findByDataAndSedeId(data, sedeId);
        } else if (data != null) {
            // Filter by date only
            eventi = eventoService.findByData(data);
        } else if (sedeId != null) {
            // Filter by venue only
            eventi = eventoService.findBySedeId(sedeId);
        } else {
            // No filters, get all events
            eventi = eventoService.findAll();
        }
        
        model.addAttribute("eventi", eventi);
        
        return "home";
    }
}

package com.example.finaltestbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.repository.EventoRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/eventi/{id}")
@RequiredArgsConstructor
public class EventController {

    private final EventoRepository eventoRepository;

    @GetMapping
    public String getEvent(@PathVariable Long id,Model model) {
        Evento evento =  eventoRepository.findById(id).orElse(null);
        if (evento == null) {
            return "redirect:/login";
        }
        model.addAttribute("evento",evento);
        return "event";
    }
    
}

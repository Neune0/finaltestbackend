package com.example.finaltestbackend.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.model.Sede;
import com.example.finaltestbackend.service.EventoService;
import com.example.finaltestbackend.service.SedeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/eventi")
@RequiredArgsConstructor
public class AdminEventController {
    
    private final EventoService eventoService;
    private final SedeService sedeService;
    
    @GetMapping
    public String listEvents(Model model) {
        List<Evento> eventi = eventoService.findAll();
        model.addAttribute("eventi", eventi);
        return "admin/eventi/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("sedi", sedeService.findAll());
        return "admin/eventi/form";
    }
    
    @PostMapping
    public String createEvent(@ModelAttribute Evento evento, RedirectAttributes redirectAttributes) {
        eventoService.save(evento);
        redirectAttributes.addFlashAttribute("success", "Evento creato con successo!");
        return "redirect:/admin/eventi";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Evento evento = eventoService.findById(id);
        if (evento == null) {
            return "redirect:/admin/eventi";
        }
        
        model.addAttribute("evento", evento);
        model.addAttribute("sedi", sedeService.findAll());
        return "admin/eventi/form";
    }
    
    @PutMapping("/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Evento evento, 
                             RedirectAttributes redirectAttributes) {
        evento.setId(id);
        eventoService.save(evento);
        redirectAttributes.addFlashAttribute("success", "Evento aggiornato con successo!");
        return "redirect:/admin/eventi";
    }
    
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        eventoService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Evento eliminato con successo!");
        return "redirect:/admin/eventi";
    }
}
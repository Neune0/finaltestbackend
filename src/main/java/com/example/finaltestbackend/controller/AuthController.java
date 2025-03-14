package com.example.finaltestbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.finaltestbackend.model.Utente;
import com.example.finaltestbackend.service.UtenteService;

/**
 * Controller per la gestione della registrazione e del login degli
 * utenti.
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UtenteService utenteService;

    /**
     * Mostra il modulo di registrazione.
     *
     * @param model Model per passare dati alla vista
     * @return Nome del template Thymeleaf per la registrazione
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente());
        return "register";
    }

    /**
     * Gestisce la registrazione di un nuovo utente.
     *
     * @param user Oggetto User ottenuto dal form di registrazione
     * @return Redirect alla pagina di login
     */
    @PostMapping("/register")
    public String register(@ModelAttribute Utente utente) {
        utenteService.register(utente);
        return "redirect:/auth/login";
    }

    /**
     * Mostra il modulo di login.
     *
     * @return Nome del template Thymeleaf per il login
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
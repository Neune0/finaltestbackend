package com.example.finaltestbackend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.repository.EventoRepository;

@Service
public class EventoService{
    private final EventoRepository eventoRepository;
    

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public List<Evento> findAll(){
        return eventoRepository.findAll();
    }

    public List<Evento> findByDataAndSedeId(LocalDate data, Long sedeId) {
        // cerco per data
        List<Evento> eventi = eventoRepository.findByData(data);
        // filtro per sede
        eventi.removeIf(e -> e.getSede().getId() != sedeId);
        return eventi;
    }

    public List<Evento> findByData(LocalDate data) {
        return eventoRepository.findByData(data);
        
    }

    public List<Evento> findBySedeId(Long sedeId) {
        return eventoRepository.findBySedeId(sedeId);
        
    }
}
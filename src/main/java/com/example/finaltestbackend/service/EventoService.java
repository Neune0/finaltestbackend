package com.example.finaltestbackend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finaltestbackend.model.Evento;
import com.example.finaltestbackend.repository.EventoRepository;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public List<Evento> findWithFilters(LocalDate data, Long sedeId, Double maxPrezzo) {
        if (data != null && sedeId != null && maxPrezzo != null) {
            return eventoRepository.findByDataAndSedeIdAndPrezzoLessThanEqual(data, sedeId, maxPrezzo);
        } else if (data != null && sedeId != null) {
            return eventoRepository.findByData(data);
        } else if (data != null && maxPrezzo != null) {
            return eventoRepository.findByDataAndPrezzoLessThanEqual(data, maxPrezzo);
        } else if (sedeId != null && maxPrezzo != null) {
            return eventoRepository.findBySedeIdAndPrezzoLessThanEqual(sedeId, maxPrezzo);
        } else if (data != null) {
            return eventoRepository.findByData(data);
        } else if (sedeId != null) {
            return eventoRepository.findBySedeId(sedeId);
        } else if (maxPrezzo != null) {
            return eventoRepository.findByPrezzoLessThanEqual(maxPrezzo);
        } else {
            return eventoRepository.findAll();
        }
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

    // Add this method to your EventoService class
    public int getBookedTickets(Long eventoId) {
        Integer count = eventoRepository.countBookedTickets(eventoId);
        return count != null ? count : 0;
    }

    // Also add this helper method for calculating availability percentage
    public int getAvailabilityPercentage(Evento evento) {
        int bookedTickets = getBookedTickets(evento.getId());
        int capacity = evento.getSede().getCapienza();
        return capacity > 0 ? (bookedTickets * 100) / capacity : 0;
    }

    // Add these methods to your EventoService
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }
}
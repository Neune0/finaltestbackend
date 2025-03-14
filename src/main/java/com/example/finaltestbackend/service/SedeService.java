package com.example.finaltestbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.finaltestbackend.model.Sede;
import com.example.finaltestbackend.repository.SedeRepository;

@Service
public class SedeService {
    private final SedeRepository sedeRepository;

    public SedeService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }
    

    public Sede findById(Long id) {
        return sedeRepository.findById(id).orElse(null);
    }

    public List<Sede> findAll(){
        return sedeRepository.findAll();
    }
}

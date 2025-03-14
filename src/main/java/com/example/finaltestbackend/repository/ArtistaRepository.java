package com.example.finaltestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    
}

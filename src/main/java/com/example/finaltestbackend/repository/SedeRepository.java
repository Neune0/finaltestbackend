package com.example.finaltestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltestbackend.model.Sede;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    
}

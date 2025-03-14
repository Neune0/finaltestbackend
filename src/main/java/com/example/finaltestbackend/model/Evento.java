package com.example.finaltestbackend.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;

    private String descrizione;

    private LocalDate data;

    private LocalTime orario;

    private double prezzo;

    // ogni evento si svolge in una sola sede
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    // un evento puo avere piu prenotazioni
    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;

    // un evento puo avere piu recensioni
    @OneToMany(mappedBy = "evento")
    private List<Recensione> recensioni;

    // un evento puo avere piu artisti
    @ManyToMany
    @JoinTable(
        name = "evento_artista",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artisti;
    
}

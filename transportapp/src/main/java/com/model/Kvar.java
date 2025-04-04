package com.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kvar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opis;

    private LocalDateTime vrijemeKvara;

    @ManyToOne
    @JoinColumn(name="vozilo_id")
    private Vozilo vozilo;

    public Long getId() {
        return this.id;
    }
    public String getOpis() {
        return this.opis;
    }
    public LocalDateTime getVrijemeKvara() {
        return this.vrijemeKvara;
    }
    public Vozilo getVozilo() {
        return this.vozilo;
    }
}

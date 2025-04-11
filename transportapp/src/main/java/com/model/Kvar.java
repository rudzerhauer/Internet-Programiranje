package com.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public void setVrijemeKvara(LocalDateTime vrijemeKvara) {
        this.vrijemeKvara = vrijemeKvara;
    }
    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }
}

package com.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity


public class Iznajmljivanje {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="vrijeme_preuzimanja")
    private LocalDateTime vrijemePreuzimanja;
    @Column(name="vrijeme_vracanja")
    private LocalDateTime vrijemeVracanja;
    private Double trajanje;


    @Embedded
    private Lokacija lokacijaPreuzimanja;  //Mapiranje to lattitude_preuzimanje, longitude_preuizmanje
    @Embedded
    private Lokacija lokacijaVracanja; //Mapiranje to lattitude_vracanje, longitude_vracanje

    @ManyToOne
    @JoinColumn(name="id_vozila")
    private Vozilo vozilo;

    @ManyToOne
    @JoinColumn(name="id_korisnik")
     @JsonBackReference  // Mark this as the "back" side of the relationship, and it will not be serialized
    private Klijent klijent;
    @Column(name="licni_dokument")
    private String licniDokument;
    @Column(name="vozacka_dozvola")
    private String vozackaDozvola;

    private boolean aktivno;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getVrijemePreuzimanja() {
        return vrijemePreuzimanja;
    }

    public LocalDateTime getVrijemeVracanja() {
        return vrijemeVracanja;
    }

    public Double getTrajanje() {
        return trajanje;
    }

    public Lokacija getLokacijaPreuzimanja() {
        return lokacijaPreuzimanja;
    }

    public Lokacija getLokacijaVracanja() {
        return lokacijaVracanja;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public String getLicniDokument() {
        return licniDokument;
    }

    public String getVozackaDozvola() {
        return vozackaDozvola;
    }

    public boolean isAktivno() {
        return aktivno;
    }
    
}

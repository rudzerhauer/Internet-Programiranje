package com.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Proizvodjac {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String drzava;
    private String adresa;
    private String telefon;
    private String fax;
    private String email;
   // Proizvodjac.java
    @OneToMany(mappedBy = "proizvodjac")
    private List<Vozilo> vozila;


    public Long getId() {
        return this.id;
    }
    public String getNaziv() {
        return this.naziv;
    }
    public String getDrzava() {
        return this.drzava;
    }
    public String getAdresa() {
        return this.adresa;
    }
    public String getTelefon() {
        return this.telefon;
    }
    public String getFax() {
        return this.fax;
    }
    public String getEmail() {
        return this.email;
    }
    public List getVozila() {
        return this.vozila;
    }


}

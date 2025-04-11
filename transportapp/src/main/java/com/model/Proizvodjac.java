package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
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
    @SuppressWarnings("rawtypes")
    public List getVozila() {
        return this.vozila;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}

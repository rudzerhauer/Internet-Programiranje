package com.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Klijent {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String ime;
    private String prezime;
    private String brLicneKarte;
    private String email;
    private String brTelefona;
    private String slikaAvatara;
    @OneToMany(mappedBy="klijent", cascade=CascadeType.ALL)
    private List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
    

    public Integer getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    public String getBrLicneKarte() {
        return brLicneKarte;
    }

    public String getEmail() {
        return email;
    }
    public String getBrTelefona() {
        return brTelefona;
    }

    public String getSlikaAvatara() {
        return slikaAvatara;
    }
    }
    



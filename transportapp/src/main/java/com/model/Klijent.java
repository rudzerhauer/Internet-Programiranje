package com.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity


public class Klijent extends Korisnik {
    
    

  
    @Column(name="br_licne_karte")
    private String brLicneKarte;
    
    
    @OneToMany(mappedBy="klijent", cascade=CascadeType.ALL)
     @JsonManagedReference  // Mark this as the "managed" side of the relationship
     private List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
    

    

    

   

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    public String getBrLicneKarte() {
        return brLicneKarte;
    }

    public void setBrLicneKarte(String brLicneKarte2) {
        this.brLicneKarte = brLicneKarte2;
    }

    
    

  
    }
    



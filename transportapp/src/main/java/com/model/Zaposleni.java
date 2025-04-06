package com.model;

import jakarta.persistence.Entity;

@Entity
public class Zaposleni extends Korisnik {
    
    
    private String uloga;


    

    

    

    

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

}

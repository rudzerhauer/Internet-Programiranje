package com.model;

import jakarta.persistence.Entity;

@Entity
public class Zaposleni extends Korisnik {
    
    
    private RadnoMjesto uloga;


    

    

    

    

    public RadnoMjesto getUloga() {
        return uloga;
    }

    public void setUloga(RadnoMjesto uloga) {
        this.uloga = uloga;
    }

}

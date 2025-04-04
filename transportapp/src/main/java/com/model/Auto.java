package com.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Auto extends Vozilo {
     LocalDate datumNabavke;
     String model;
     String opis;



    public Auto(Integer idVozila, double cijenaNabavke, Proizvodjac proizvodjac, LocalDate datumNabavke, String model, String opis) {
        super(idVozila, cijenaNabavke, proizvodjac);
        this.datumNabavke = datumNabavke;
        this.model = model;
        this.opis = opis;

    }
    public Auto() {
        super();
    }

    public LocalDate getDatumNabavke() {
        return this.datumNabavke;
    }
    public String getModel() {
        return this.model;
    }
    public String getOpis() {
        return this.opis;
    }
    
}

package com.model;

import java.sql.Time;

import jakarta.persistence.Entity;

@Entity
public class EBike extends Vozilo {
    Time autonomija;

    public EBike(Integer idVozila, double cijenaNabavke, Proizvodjac proizvodjac, Time autonomija) {
        super(idVozila, cijenaNabavke, proizvodjac);
        this.autonomija = autonomija;

    }
    public EBike() {
        super();
    }

    public Time getAutonomija() {
        return this.autonomija;
    }
    
}

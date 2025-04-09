package com.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="elektricni_bicikl")
public class EBike extends Vozilo {
    Integer autonomija;

    public EBike(Integer idVozila, double cijenaNabavke, Proizvodjac proizvodjac, Integer autonomija) {
        super(idVozila, cijenaNabavke, proizvodjac);
        this.autonomija = autonomija;

    }
    public EBike() {
        super();
    }

    public Integer getAutonomija() {
        return this.autonomija;
    }
    public void setAutonomija(Integer autonomija) {
        this.autonomija = autonomija;
    }
    
}

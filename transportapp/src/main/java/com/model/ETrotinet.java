package com.model;

import jakarta.persistence.Entity;

@Entity
public class ETrotinet extends Vozilo {
    private double maksBrzina;

    public ETrotinet(Integer idVozila, double cijenaNabavke, Proizvodjac proizvodjac, double maksBrzina) {
        super(idVozila, cijenaNabavke, proizvodjac);
        this.maksBrzina = maksBrzina;


    }
    public ETrotinet() {
        super();
    }
    public double getMaksBrzina() {
        return this.maksBrzina;
    }

    public void setMaksBrzina(double maksBrzina) {
        this.maksBrzina = maksBrzina;
    }


    
}

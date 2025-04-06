package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="elektricni_trotinet")
public class ETrotinet extends Vozilo {
    @Column(name="maksimalna_brzina")
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

package com.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idVozila")
public abstract class Vozilo {
    public Vozilo() {}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idVozila;
    private double cijenaNabavke;
    // Vozilo.java
    @ManyToOne
    @JoinColumn(name = "proizvodjac_id")
    @JsonBackReference
    private Proizvodjac proizvodjac;
    private String slikaPutanja;

    private boolean pokvareno;
    @OneToMany(mappedBy="vozilo", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Kvar> kvarovi = new ArrayList<>();
    @OneToMany(mappedBy="vozilo", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();

    public Vozilo(Integer idVozila, double cijenaNabavke, Proizvodjac proizvodjac) {
        this.idVozila = idVozila;
        this.cijenaNabavke = cijenaNabavke;
        this.proizvodjac = proizvodjac;
    }
   



    public Integer getIdVozila() {
        return this.idVozila;
    }
    public double getCijenaNabavke() {
        return this.cijenaNabavke;
    }
    public Proizvodjac getProizvodjac() {
        return this.proizvodjac;
    }
    public boolean isPokvareno() {
        return this.pokvareno;
    }
    @SuppressWarnings("rawtypes")
    public List getKvarovi() {
        return this.kvarovi;
    }
    public void setIdVozila(Integer idVozila) {
        this.idVozila = idVozila;
    }
    public void setCijenaNabavke(double cijenaNabavke) {
        this.cijenaNabavke = cijenaNabavke;
    }
    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }
    public void setKvarovi(List<Kvar> kvarovi) {
        this.kvarovi = kvarovi;
    }

    public String getSlikaPutanja() {
        return slikaPutanja;
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    
}

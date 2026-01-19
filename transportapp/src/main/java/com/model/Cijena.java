package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cijena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleType; // "Auto", "EBike", "ETrotinet"
    private double cijenaPoSatu; // Price per hour in KM

    // Constructors
    public Cijena() {}

    public Cijena(String vehicleType, double cijenaPoSatu) {
        this.vehicleType = vehicleType;
        this.cijenaPoSatu = cijenaPoSatu;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCijenaPoSatu() {
        return cijenaPoSatu;
    }

    public void setCijenaPoSatu(double cijenaPoSatu) {
        this.cijenaPoSatu = cijenaPoSatu;
    }
}
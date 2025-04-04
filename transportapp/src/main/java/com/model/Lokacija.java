package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Lokacija {

    @Column(name = "latitude_preuzimanje", insertable = false, updatable = false)
    private double latitudePreuzimanje;

    @Column(name = "longitude_preuzimanje", insertable = false, updatable = false)
    private double longitudePreuzimanje;

    @Column(name = "latitude_vracanje", insertable = false, updatable = false)
    private double latitudeVracanje;

    @Column(name = "longitude_vracanje", insertable = false, updatable = false)
    private double longitudeVracanje;

    // Getters and setters

    public double getLatitudePreuzimanje() {
        return latitudePreuzimanje;
    }

    public void setLatitudePreuzimanje(double latitudePreuzimanje) {
        this.latitudePreuzimanje = latitudePreuzimanje;
    }

    public double getLongitudePreuzimanje() {
        return longitudePreuzimanje;
    }

    public void setLongitudePreuzimanje(double longitudePreuzimanje) {
        this.longitudePreuzimanje = longitudePreuzimanje;
    }

    public double getLatitudeVracanje() {
        return latitudeVracanje;
    }

    public void setLatitudeVracanje(double latitudeVracanje) {
        this.latitudeVracanje = latitudeVracanje;
    }

    public double getLongitudeVracanje() {
        return longitudeVracanje;
    }

    public void setLongitudeVracanje(double longitudeVracanje) {
        this.longitudeVracanje = longitudeVracanje;
    }
}

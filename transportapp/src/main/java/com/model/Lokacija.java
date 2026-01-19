package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class Lokacija {

    @Column(name = "latitude_preuzimanja", insertable = false, updatable = false)
    private Double latitudePreuzimanje;

    @Column(name = "longitude_preuzimanja", insertable = false, updatable = false)
    private Double longitudePreuzimanje;

    @Column(name = "latitude_vracanja", insertable = false, updatable = false)
    private Double latitudeVracanje;

    @Column(name = "longitude_vracanja", insertable = false, updatable = false)
    private Double longitudeVracanje;

    // Getters and setters

    public Double getLatitudePreuzimanje() {
        return latitudePreuzimanje;
    }

    public void setLatitudePreuzimanje(double latitudePreuzimanje) {
        this.latitudePreuzimanje = latitudePreuzimanje;
    }

    public Double getLongitudePreuzimanje() {
        return longitudePreuzimanje;
    }

    public void setLongitudePreuzimanje(double longitudePreuzimanje) {
        this.longitudePreuzimanje = longitudePreuzimanje;
    }

    public Double getLatitudeVracanje() {
        return latitudeVracanje;
    }
    @Transient
public Double getLatitude() {
    return latitudePreuzimanje;
}

@Transient
public Double getLongitude() {
    return longitudePreuzimanje;
}

    public void setLatitudeVracanje(double latitudeVracanje) {
        this.latitudeVracanje = latitudeVracanje;
    }

    public Double getLongitudeVracanje() {
        return longitudeVracanje;
    }

    public void setLongitudeVracanje(double longitudeVracanje) {
        this.longitudeVracanje = longitudeVracanje;
    }
}

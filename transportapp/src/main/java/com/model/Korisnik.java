package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Korisnik {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_korisnik")
    private int id_korisnik;

    private String username;
    private String password;
    private String ime;
    private String prezime;

    @Column(name="br_telefona")
    private String brTelefona;
    @Column(name="slika_avatara")
    private String slikaAvatara;
    private String email;

    public int getId() {
        return id_korisnik;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrTelefona() {
        return brTelefona;
    }

    public String getSlikaAvatara() {
        return slikaAvatara;
    }

    public String getEmail() {
        return email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
    }
    public void setSlikaAvatara(String avatarSlika) {
        this.slikaAvatara = avatarSlika;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

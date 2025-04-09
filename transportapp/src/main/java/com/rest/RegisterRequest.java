package com.rest;

import jakarta.validation.constraints.NotNull;

public class RegisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String ime;
    @NotNull
    private String prezime;
    @NotNull
    private String brTelefona;
    private String slikaAvatara;
    @NotNull
    private String email;
    private String userType;
    private String brLicneKarte; 
    private String uloga; 

    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public String getBrTelefona() { return brTelefona; }
    public void setBrTelefona(String brTelefona) { this.brTelefona = brTelefona; }
    public String getSlikaAvatara() { return slikaAvatara; }
    public void setSlikaAvatara(String slikaAvatara) { this.slikaAvatara = slikaAvatara; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBrLicneKarte() { return brLicneKarte; }
    public void setBrLicneKarte(String brLicneKarte) { this.brLicneKarte = brLicneKarte; }
    public String getUloga() { return uloga; }
    public void setUloga(String uloga) { this.uloga = uloga; }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

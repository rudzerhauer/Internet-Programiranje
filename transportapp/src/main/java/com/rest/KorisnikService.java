package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Klijent;
import com.model.Korisnik;
import com.model.Zaposleni;
import com.repositorys.KorisnikRepository;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;
    

    // Existing methods
    public List<Korisnik> getAllKorisnici() {
        return this.korisnikRepository.findAll();
    }

    public Korisnik getKorisnikById(int id) {
        return this.korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik sa id nije pronadjen"));
    }

    public Korisnik createKorisnik(Korisnik korisnik) {
        return this.korisnikRepository.save(korisnik);
    }

    public Korisnik updateKorisnik(int id, Korisnik korisnik) {
        Korisnik existing = getKorisnikById(id);
        existing.setIme(korisnik.getIme());
        existing.setPrezime(korisnik.getPrezime());
        existing.setUsername(korisnik.getUsername());
        existing.setPassword(korisnik.getPassword());
        existing.setBrTelefona(korisnik.getBrTelefona());
        existing.setSlikaAvatara(korisnik.getSlikaAvatara());
        existing.setEmail(korisnik.getEmail());

        if (existing instanceof Zaposleni existingZaposleni && korisnik instanceof Zaposleni updatedZaposleni) {
            existingZaposleni.setUloga(updatedZaposleni.getUloga());
        } else if (existing instanceof Klijent existingKlijent && korisnik instanceof Klijent updatedKlijent) {
            existingKlijent.setBrLicneKarte(updatedKlijent.getBrLicneKarte());
            existingKlijent.setBlokiran(updatedKlijent.isBlokiran());
        }
        return korisnikRepository.save(existing);
    }

    public void deleteKorisnik(int id) {
        korisnikRepository.deleteById(id);
    }

    // New methods for User Management Page
    public List<Klijent> getAllKlijenti() {
        return korisnikRepository.findAllKlijenti();
    }

    public List<Zaposleni> getAllZaposleni() {
        return korisnikRepository.findAllZaposleni();
    }

    public Klijent blockKlijent(int id) {
        Klijent klijent = (Klijent) korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klijent nije pronadjen"));
        klijent.setBlokiran(true);
        return korisnikRepository.save(klijent);
    }

    public Klijent unblockKlijent(int id) {
        Klijent klijent = (Klijent) korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klijent nije pronadjen"));
        klijent.setBlokiran(false);
        return korisnikRepository.save(klijent);
    }

    public Zaposleni createZaposleni(Zaposleni zaposleni) {
        return korisnikRepository.save(zaposleni);
    }

    public Zaposleni updateZaposleni(int id, Zaposleni updatedZaposleni) {
        Zaposleni existing = (Zaposleni) korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zaposleni nije pronadjen"));
        existing.setUsername(updatedZaposleni.getUsername());
        existing.setPassword(updatedZaposleni.getPassword());
        existing.setIme(updatedZaposleni.getIme());
        existing.setPrezime(updatedZaposleni.getPrezime());
        existing.setBrTelefona(updatedZaposleni.getBrTelefona());
        existing.setSlikaAvatara(updatedZaposleni.getSlikaAvatara());
        existing.setEmail(updatedZaposleni.getEmail());
        existing.setUloga(updatedZaposleni.getUloga());
        return korisnikRepository.save(existing);
    }

    public void deleteZaposleni(int id) {
        Zaposleni existing = (Zaposleni) korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zaposleni nije pronadjen"));
        korisnikRepository.delete(existing);
    }
}
package com.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.model.Klijent;
import com.model.Korisnik;
import com.model.Zaposleni;
import com.rest.KorisnikService;

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {
    @Autowired
    KorisnikService korisnikService;

    // Existing endpoints
    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<Korisnik> getAllKorisnici() {
        return this.korisnikService.getAllKorisnici();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Korisnik getKorisnikById(@PathVariable int id) {
        return this.korisnikService.getKorisnikById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Korisnik createKorisnik(@RequestBody Korisnik korisnik) {
        return this.korisnikService.createKorisnik(korisnik);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Korisnik updateKorisnik(@PathVariable int id, @RequestBody Korisnik korisnik) {
        return korisnikService.updateKorisnik(id, korisnik);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable int id) {
        korisnikService.deleteKorisnik(id);
        return ResponseEntity.noContent().build();
    }

    // New endpoints for User Management Page
    @GetMapping("/klijenti")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<Klijent> getAllKlijenti() {
        return korisnikService.getAllKlijenti();
    }

    @GetMapping("/zaposleni")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<Zaposleni> getAllZaposleni() {
        return korisnikService.getAllZaposleni();
    }

    @PostMapping("/klijenti/{id}/block")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Klijent blockKlijent(@PathVariable int id) {
        return korisnikService.blockKlijent(id);
    }

    @PostMapping("/klijenti/{id}/unblock")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Klijent unblockKlijent(@PathVariable int id) {
        return korisnikService.unblockKlijent(id);
    }

    @PostMapping("/zaposleni")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Zaposleni createZaposleni(@RequestBody Zaposleni zaposleni) {
        return korisnikService.createZaposleni(zaposleni);
    }

    @PutMapping("/zaposleni/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Zaposleni updateZaposleni(@PathVariable int id, @RequestBody Zaposleni zaposleni) {
        return korisnikService.updateZaposleni(id, zaposleni);
    }

    @DeleteMapping("/zaposleni/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteZaposleni(@PathVariable int id) {
        korisnikService.deleteZaposleni(id);
        return ResponseEntity.noContent().build();
    }
}
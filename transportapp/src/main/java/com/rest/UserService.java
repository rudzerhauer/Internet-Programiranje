package com.rest;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Klijent;
import com.model.Korisnik;
import com.model.Zaposleni;
import com.repositorys.KorisnikRepository;
@Service
public class UserService implements UserDetailsService {
    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Korisnik registerUser(RegisterRequest request) {
        if(korisnikRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("korisnicko ime vec postoji");
        }
        Korisnik korisnik;
        if(request.getBrLicneKarte() != null) {
            Klijent klijent = new Klijent();
            klijent.setBrLicneKarte(request.getBrLicneKarte());
            korisnik = klijent;
        } else if (request.getUloga() != null) {
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setUloga(request.getUloga());
            korisnik = zaposleni;
        } else {
            throw new RuntimeException("Korisnik nije specifikovan");
        }

        korisnik.setUsername(request.getUsername());
        korisnik.setPassword(passwordEncoder.encode(request.getPassword()));
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setBrTelefona(request.getBrTelefona());
        korisnik.setSlikaAvatara(request.getSlikaAvatara());
        korisnik.setEmail(request.getEmail());

        return korisnikRepository.save(korisnik);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(korisnik.getUsername(), korisnik.getPassword(), new ArrayList<>());

    }

}

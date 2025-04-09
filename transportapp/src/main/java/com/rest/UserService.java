package com.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Klijent;
import com.model.Korisnik;
import com.model.Zaposleni;
import com.repositorys.KlijentRepository;
import com.repositorys.KorisnikRepository;
import com.repositorys.ZaposleniRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private KlijentRepository klijentRepository;
    @Autowired
    private ZaposleniRepository zaposleniRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request) {
        if ("KLIJENT".equalsIgnoreCase(request.getUserType())) {
            if (request.getBrLicneKarte() == null || request.getBrLicneKarte().isEmpty()) {
                throw new IllegalArgumentException("ID card number is required for clients");
            }
            Klijent klijent = new Klijent();
            klijent.setUsername(request.getUsername());
            klijent.setPassword(passwordEncoder.encode(request.getPassword()));
            klijent.setIme(request.getIme());
            klijent.setPrezime(request.getPrezime());
            klijent.setBrTelefona(request.getBrTelefona());
            klijent.setEmail(request.getEmail());
            klijent.setBrLicneKarte(request.getBrLicneKarte());
            klijentRepository.save(klijent); // Saves to both korisnik and klijent tables
        } else if ("ZAPOSLENI".equalsIgnoreCase(request.getUserType())) {
            if (request.getUloga() == null || request.getUloga().isEmpty()) {
                throw new IllegalArgumentException("Role is required for employees");
            }
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setUsername(request.getUsername());
            zaposleni.setPassword(passwordEncoder.encode(request.getPassword()));
            zaposleni.setIme(request.getIme());
            zaposleni.setPrezime(request.getPrezime());
            zaposleni.setBrTelefona(request.getBrTelefona());
            zaposleni.setEmail(request.getEmail());
            zaposleni.setUloga(request.getUloga());
            zaposleniRepository.save(zaposleni); // Saves to both korisnik and zaposleni tables
        } else {
            throw new IllegalArgumentException("Invalid user type. Use 'KLIJENT' or 'ZAPOSLENI'");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Check if the user is a Zaposleni or Klijent
        zaposleniRepository.findById(korisnik.getId()).ifPresent(zaposleni -> 
            authorities.add(new SimpleGrantedAuthority("ROLE_" + zaposleni.getUloga()))
        );
        klijentRepository.findById(korisnik.getId()).ifPresent(klijent -> 
            authorities.add(new SimpleGrantedAuthority("ROLE_KLIJENT"))
        );

        return new org.springframework.security.core.userdetails.User(
            korisnik.getUsername(), korisnik.getPassword(), authorities);
    }
}
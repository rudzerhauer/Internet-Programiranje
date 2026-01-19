package com.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Klijent;
import com.model.Korisnik;
import com.model.Zaposleni;

import jakarta.transaction.Transactional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    @Query("SELECT k FROM Klijent k")
    @Transactional
    List<Klijent> findAllKlijenti();

    @Query("SELECT z FROM Zaposleni z")
    @Transactional
    List<Zaposleni> findAllZaposleni();
    Optional<Korisnik> findByUsername(String username);
}
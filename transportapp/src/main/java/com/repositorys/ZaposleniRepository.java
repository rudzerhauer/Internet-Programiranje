package com.repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Zaposleni;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {
    // No need for findByKorisnik
    // Optional: Add a method to find by username if needed
    Optional<Zaposleni> findByUsername(String username);
}


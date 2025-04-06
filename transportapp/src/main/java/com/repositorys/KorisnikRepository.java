package com.repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Korisnik;

public interface  KorisnikRepository extends JpaRepository<Korisnik,Integer> {
    Optional <Korisnik> findByUsername(String username);
}


package com.repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Klijent;

public interface  KlijentRepository extends JpaRepository<Klijent,Integer> {
    Optional <Klijent> findByUsername(String username);
}


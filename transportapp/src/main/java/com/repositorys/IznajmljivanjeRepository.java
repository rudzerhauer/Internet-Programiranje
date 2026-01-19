package com.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Iznajmljivanje;

public interface IznajmljivanjeRepository extends JpaRepository<Iznajmljivanje, Integer> {
   
        public List<Iznajmljivanje> findByAktivnoTrue();
}
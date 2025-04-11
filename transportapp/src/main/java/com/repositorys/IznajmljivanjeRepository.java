package com.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Iznajmljivanje;

public interface IznajmljivanjeRepository extends JpaRepository<Iznajmljivanje, Integer> {
}
package com.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Proizvodjac;

@Repository
public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Long> {
}
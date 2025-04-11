package com.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Kvar;


public interface KvarRepository extends JpaRepository<Kvar, Long> {
    
}

package com.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Vozilo;

public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {
    
}

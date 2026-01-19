package com.repositorys;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Iznajmljivanje;
import com.model.Vozilo;

public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {
    @Query("SELECT i FROM Iznajmljivanje i " +
           "JOIN FETCH i.vozilo v " +
           "JOIN FETCH i.lokacijaPreuzimanja lp " +
           "JOIN FETCH i.lokacijaVracanja lv " +
           "WHERE i.aktivno = true")
    List<Iznajmljivanje> findActiveIznajmljivanja();
}

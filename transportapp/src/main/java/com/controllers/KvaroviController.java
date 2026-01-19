package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Kvar;
import com.model.Vozilo;
import com.repositorys.KvarRepository;
import com.repositorys.VoziloRepository;

@RestController
public class KvaroviController {

    @Autowired
    KvarRepository kvarRepository;
    @Autowired
    VoziloRepository voziloRepository;

    @GetMapping("/api/vozila/kvar")
    public List<Kvar> getAll() {
        return kvarRepository.findAll();
    }
    
    @PostMapping("/api/vozila//kvar/{voziloID}")
    public ResponseEntity<?> dodajKvar(@PathVariable Integer voziloId, @RequestBody Kvar kvar) {
        Vozilo vozilo = voziloRepository.findById(voziloId).orElseThrow();
        kvar.setVozilo(vozilo);
        kvarRepository.save(kvar);
        return ResponseEntity.ok(kvar);
    }
}
package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Vozilo;
import com.repositorys.VoziloRepository;

@RestController
public class VoziloController {

    @Autowired
    VoziloRepository voziloRepository;


    @GetMapping("/api")
    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }

    
}

package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Kvar;
import com.repositorys.KvarRepository;

@RestController
public class KvaroviController {

    @Autowired
    KvarRepository kvarRepository;


    @GetMapping("/api/kvar")
    public List<Kvar> getAll() {
        return kvarRepository.findAll();
    }

    
}

package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Iznajmljivanje;
import com.repositorys.IznajmljivanjeRepository;
import com.rest.IznajmljivanjeService;

@RestController
@RequestMapping("/api/iznajmljivanja")
public class IznajmljivanjeController {

    @Autowired
    private IznajmljivanjeService iznajmljivanjeService;
    @Autowired
    private IznajmljivanjeRepository iznajmljivanjeRepository;

    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Iznajmljivanje>> getAllIznajmljivanja() {
        System.out.println("Received request for /api/iznajmljivanja");
        List<Iznajmljivanje> iznajmljivanja = iznajmljivanjeService.findAllIznajmljivanja();
        System.out.println("Returning " + iznajmljivanja.size() + " iznajmljivanja");
        return ResponseEntity.ok(iznajmljivanja);
    }
    @GetMapping("/active")
    public List<Iznajmljivanje> getActiveIznajmljivanja() {
        return iznajmljivanjeRepository.findByAktivnoTrue();
    }
}
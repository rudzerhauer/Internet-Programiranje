package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Proizvodjac;
import com.rest.ProizvodjacService;

@RestController
@RequestMapping("/api/proizvodjaci")
public class ProizvodjacController {
    @Autowired
    private ProizvodjacService proizvodjacService;


    @GetMapping 
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<Proizvodjac> getAllProizvodjac() {
        return proizvodjacService.getAllProizvodjac();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Proizvodjac getProizvodjacById(@PathVariable Long id) {
        return proizvodjacService.getProizvodjacById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Proizvodjac createProizvodjac(@RequestBody Proizvodjac proizvodjac) {
        return proizvodjacService.createProizvodjac(proizvodjac);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Proizvodjac updateProizvodjac(@PathVariable Long id, @RequestBody Proizvodjac proizvodjac) {
        return proizvodjacService.updateProizvodjac(id, proizvodjac);
    }    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteProizvodjac(@PathVariable Long id) {
        proizvodjacService.deleteProizvodjac(id);
        return ResponseEntity.noContent().build();
    }
}

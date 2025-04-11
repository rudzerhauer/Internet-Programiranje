package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Proizvodjac;
import com.repositorys.ProizvodjacRepository;

@Service
public class ProizvodjacService {
    @Autowired
    ProizvodjacRepository proizvodjacRepository;



    public  List<Proizvodjac> getAllProizvodjac() {
       return proizvodjacRepository.findAll();
    }
    public Proizvodjac getProizvodjacById(Long id) {
        return proizvodjacRepository.findById(id).orElseThrow(() -> new RuntimeException("Proizvodjac nije pronadjen jbg"));
    }
    public Proizvodjac createProizvodjac(Proizvodjac proizvodjac) {
        return proizvodjacRepository.save(proizvodjac);
    }

    public Proizvodjac updateProizvodjac(Long id, Proizvodjac proizvodjac) {
        Proizvodjac existing = getProizvodjacById(id);
        existing.setNaziv(proizvodjac.getNaziv());
        existing.setDrzava(proizvodjac.getDrzava());
        existing.setAdresa(proizvodjac.getAdresa());
        existing.setTelefon(proizvodjac.getTelefon());
        existing.setFax(proizvodjac.getFax());
        existing.setEmail(proizvodjac.getEmail());
        return proizvodjacRepository.save(existing);
    }

    public void deleteProizvodjac(Long id) {
        Proizvodjac existing = getProizvodjacById(id);
        if(!existing.getVozila().isEmpty()) {
            throw new RuntimeException("Proizvodjac se ne moze izbrisati, jer je povezan sa vozilom");
        }
        proizvodjacRepository.delete(existing);
    }
}

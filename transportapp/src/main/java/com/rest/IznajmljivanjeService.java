package com.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Iznajmljivanje;
import com.repositorys.IznajmljivanjeRepository;

import jakarta.transaction.Transactional;
@Service
public class IznajmljivanjeService {
    @Autowired
    private IznajmljivanjeRepository iznajmljivanjeRepository;

    @Transactional
    public List<Iznajmljivanje> findAllIznajmljivanja() {
        return iznajmljivanjeRepository.findAll();
    }
    public List<Iznajmljivanje> findByAktivno() {
         List<Iznajmljivanje> lista = iznajmljivanjeRepository.findAll()
    .stream()
    .filter(Iznajmljivanje::isAktivno)
    .collect(Collectors.toList());
       return lista;
    }
}

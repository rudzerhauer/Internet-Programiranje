package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.Vozilo;
import com.repositorys.VoziloRepository;
import com.rest.VoziloService;


@RestController
@RequestMapping("/api/vozila")
public class VoziloController {
    @Autowired
     VoziloService  voziloService;

    @Autowired
    VoziloRepository voziloRepository;


    @GetMapping("/types")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<String> getVoziloTypes() {
        return voziloService.getVoziloTypes();
    }
    @GetMapping("/by-type/{type}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<Vozilo> getVozilaByType(@PathVariable String type) {
        return voziloService.getVozilaByType(type);
    }
    @PostMapping("/{type}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Vozilo createVozilo(@PathVariable String type, @RequestBody Vozilo vozilo) {
        return voziloService.createVozilo(type, vozilo);
}
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> deleteVozilo(@PathVariable Integer id) {
        voziloService.deleteVozilo(id);
        return ResponseEntity.ok("Vozilo deleted");
    }
    @PostMapping("/upload-csv")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
        List<Vozilo> vozila = voziloService.uploadVozilaFromCsv(file);
        return ResponseEntity.ok(vozila);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error uploading CSV:" + e.getMessage());
    }
    


}
}

package com.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.model.Iznajmljivanje;
import com.model.Kvar;
import com.model.Vozilo;
import com.repositorys.KvarRepository;
import com.repositorys.VoziloRepository;
import com.rest.VoziloService;


@RestController
@RequestMapping("/api/vozila")
public class VoziloController {
    @Autowired
     VoziloService  voziloService;

    @Autowired
    VoziloRepository voziloRepository;
    @Autowired
    KvarRepository kvarRepository;
    


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
    public ResponseEntity<Vozilo> createVozilo(@PathVariable String type, @RequestBody Map<String, Object> voziloData) {
        try {
            Vozilo vozilo = voziloService.createVoziloFromMap(type, voziloData);
            Vozilo createdVozilo = voziloService.createVozilo(type, vozilo);
            return ResponseEntity.ok(createdVozilo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
}
@GetMapping("/{id}")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public Optional<Vozilo> getVoziloById(@PathVariable Integer id) {
  return voziloService.getVoziloById(id);
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
    @GetMapping("/{id}/kvarovi")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<Kvar>> getKvaroviByVoziloId(@PathVariable Integer id) {
        Vozilo vozilo = voziloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vozilo not found"));
        return ResponseEntity.ok(vozilo.getKvarovi());
    }
    @PostMapping("/{id}/kvarovi")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Kvar> addKvar(@PathVariable Integer id, @RequestBody Map<String, Object> kvarData) {
        Vozilo vozilo = voziloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vozilo not found"));

        Kvar kvar = new Kvar();
        kvar.setOpis((String) kvarData.get("opis"));
        String vrijemeKvaraStr = (String) kvarData.get("vrijemeKvara");
        if (vrijemeKvaraStr != null) {
            LocalDateTime vrijemeKvara = LocalDateTime.parse(vrijemeKvaraStr);
            kvar.setVrijemeKvara(vrijemeKvara);
        }
        kvar.setVozilo(vozilo);
        Kvar savedKvar = kvarRepository.save(kvar);
        return ResponseEntity.ok(savedKvar);
    }
    @DeleteMapping("/kvarovi/{kvarId}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteKvar(@PathVariable Long kvarId) {
        if (!kvarRepository.existsById(kvarId)) {
            return ResponseEntity.notFound().build();
        }
        kvarRepository.deleteById(kvarId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/iznajmljivanja")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<Iznajmljivanje>> getIznajmljivanjaByVoziloId(@PathVariable Integer id) {
        Vozilo vozilo = voziloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vozilo not found"));
        return ResponseEntity.ok(vozilo.getIznajmljivanja());
    }

}

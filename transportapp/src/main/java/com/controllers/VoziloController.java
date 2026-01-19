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

import com.model.Cijena;
import com.model.Iznajmljivanje;
import com.model.Kvar;
import com.model.Vozilo;
import com.repositorys.CijenaRepository;
import com.repositorys.KvarRepository;
import com.repositorys.VoziloRepository;
import com.rest.VoziloService;

@RestController
@RequestMapping("/api/vozila")
public class VoziloController {
    @Autowired
    VoziloService voziloService;

    @Autowired
    VoziloRepository voziloRepository;

    @Autowired
    KvarRepository kvarRepository;
    @Autowired
    CijenaRepository cijenaRepository;

    @GetMapping("/cijene")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Cijena>> getCijene() {
        return ResponseEntity.ok(cijenaRepository.findAll());
    }
   

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
    public ResponseEntity<Vozilo> createVozilo(@PathVariable String type, @RequestBody Vozilo vozilo) {
        System.out.println("Received request to create vozilo of type: " + type);
        System.out.println("Request body: " + vozilo);
        System.out.println("ProizvodjacId from request: " + vozilo.getProizvodjacId());
        Vozilo createdVozilo = voziloService.createVozilo(type, vozilo);
        return ResponseEntity.ok(createdVozilo);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    public ResponseEntity<List<Vozilo>> getAllVozila() {
        try {
            List<Vozilo> vozila = voziloRepository.findAll();
            System.out.println("Returning " + vozila.size() + " vozila");
            return ResponseEntity.ok(vozila);
        } catch (Exception e) {
            System.err.println("Error fetching vozila: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    public ResponseEntity<Optional<Vozilo>> getVoziloById(@PathVariable String id) {
        try {
            Integer parsedId = Integer.parseInt(id);
            Optional<Vozilo> vozilo = voziloService.getVoziloById(parsedId);
            if (vozilo.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(vozilo);
        } catch (NumberFormatException e) {
            System.err.println("Invalid vehicle ID: " + id);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> deleteVozilo(@PathVariable String id) {
        try {
            Integer parsedId = Integer.parseInt(id);
            voziloService.deleteVozilo(parsedId);
            return ResponseEntity.ok("Vozilo deleted");
        } catch (NumberFormatException e) {
            System.err.println("Invalid vehicle ID: " + id);
            return ResponseEntity.badRequest().body("Invalid vehicle ID");
        }
    }

    @PostMapping("/upload-csv")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<Vozilo> vozila = voziloService.uploadVozilaFromCsv(file);
            return ResponseEntity.ok(vozila);
        } catch (Exception e) {
            System.err.println("Error uploading CSV: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error uploading CSV: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/kvarovi")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    public ResponseEntity<List<Kvar>> getKvaroviByVoziloId(@PathVariable String id) {
        try {
            Integer parsedId = Integer.parseInt(id);
            Vozilo vozilo = voziloRepository.findById(parsedId)
                    .orElseThrow(() -> new IllegalArgumentException("Vozilo not found"));
            return ResponseEntity.ok(vozilo.getKvarovi());
        } catch (NumberFormatException e) {
            System.err.println("Invalid vehicle ID: " + id);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/kvar")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    public ResponseEntity<Kvar> addKvar(@PathVariable String id, @RequestBody Map<String, Object> kvarData) {
        try {
            Integer parsedId = Integer.parseInt(id);
            Vozilo vozilo = voziloRepository.findById(parsedId)
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
        } catch (NumberFormatException e) {
            System.err.println("Invalid vehicle ID: " + id);
            return ResponseEntity.badRequest().body(null);
        }
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
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    public ResponseEntity<List<Iznajmljivanje>> getIznajmljivanjaByVoziloId(@PathVariable String id) {
        try {
            Integer parsedId = Integer.parseInt(id);
            Vozilo vozilo = voziloRepository.findById(parsedId)
                    .orElseThrow(() -> new IllegalArgumentException("Vozilo not found"));
            return ResponseEntity.ok(vozilo.getIznajmljivanja());
        } catch (NumberFormatException e) {
            System.err.println("Invalid vehicle ID: " + id);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
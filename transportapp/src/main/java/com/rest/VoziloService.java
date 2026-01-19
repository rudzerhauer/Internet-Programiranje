package com.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.Auto;
import com.model.EBike;
import com.model.ETrotinet;
import com.model.Proizvodjac;
import com.model.Vozilo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.repositorys.ProizvodjacRepository;
import com.repositorys.VoziloRepository;

@Service
public class VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;
    @Autowired
    private ProizvodjacRepository proizvodjacRepository;

  public List<Vozilo> getVozilaByType(String type) {
    List<Vozilo> allVozila = voziloRepository.findAll();
    List<Vozilo> filtered = new ArrayList<>();
    for (Vozilo vozilo : allVozila) {
      if (type.equals("Auto") && vozilo instanceof Auto) {
        filtered.add(vozilo);
      } else if (type.equals("EBike") && vozilo instanceof EBike) {
        filtered.add(vozilo);
      } else if (type.equals("ETrotinet") && vozilo instanceof ETrotinet) {
        filtered.add(vozilo);
      }
    }

    // Initialize proizvodjac for each vozilo in the filtered list
    for (Vozilo vozilo : filtered) {
      if (vozilo.getProizvodjac() != null) {
        Hibernate.initialize(vozilo.getProizvodjac());
      } else {
        System.out.println("Proizvodjac is null for vozilo: " + vozilo.getIdVozila());
      }
    }

    return filtered;
  }

  public Vozilo createVozilo(String type, Vozilo vozilo) {
    // Log the incoming vozilo object
    System.out.println("Creating vozilo with proizvodjacId: " + vozilo.getProizvodjacId());

    // Handle proizvodjacId from the request
    Long proizvodjacId = vozilo.getProizvodjacId();
    if (proizvodjacId == null) {
      throw new IllegalArgumentException("ProizvodjacId is required");
    }

    // Fetch the Proizvodjac
    Proizvodjac proizvodjac = proizvodjacRepository.findById(proizvodjacId)
        .orElseThrow(() -> new IllegalArgumentException("Proizvodjac not found with ID: " + proizvodjacId));
    System.out.println("Fetched proizvodjac: " + proizvodjac.getNaziv());

    // Set the proizvodjac on the vozilo
    vozilo.setProizvodjac(proizvodjac);
    System.out.println("Set proizvodjac on vozilo: " + vozilo.getProizvodjac().getNaziv());

    // Save the vozilo
    Vozilo savedVozilo = voziloRepository.save(vozilo);
    System.out.println("Saved vozilo with ID: " + savedVozilo.getIdVozila());

    // Initialize proizvodjac before returning
    if (savedVozilo.getProizvodjac() != null) {
      Hibernate.initialize(savedVozilo.getProizvodjac());
      System.out.println("Initialized proizvodjac: " + savedVozilo.getProizvodjac().getNaziv());
    } else {
      System.out.println("Proizvodjac is null after saving vozilo: " + savedVozilo.getIdVozila());
    }

    return savedVozilo;
  }



    public void deleteVozilo(Integer id) {
        voziloRepository.deleteById(id);
    }
    public List<String> getVoziloTypes() {
        List<String> types = new ArrayList<>();
        List<Vozilo> allVozila = voziloRepository.findAll();
        for (Vozilo vozilo : allVozila) {
            if (vozilo instanceof Auto && !types.contains("Auto")) {
                types.add("Auto");
            } else if (vozilo instanceof EBike && !types.contains("EBike")) {
                types.add("EBike");
            } else if (vozilo instanceof ETrotinet && !types.contains("ETrotinet")) {
                types.add("ETrotinet");
            }
        }
        return types;
    }
    public Vozilo createVoziloFromMap(String type, Map<String, Object> voziloData) {   //trebam ovo dodati u servis
        Vozilo vozilo;
        switch (type) {
            case "Auto":
                vozilo = new Auto();
                ((Auto) vozilo).setModel((String) voziloData.get("model"));
                ((Auto) vozilo).setOpis((String) voziloData.get("opis"));
                String datumString = (String) voziloData.get("datumNabavke");
                LocalDate datumNabavke = LocalDate.parse(datumString);  
                ((Auto) vozilo).setDatumNabavke(datumNabavke); 
                break;
            case "EBike":
                vozilo = new EBike();
                ((EBike) vozilo).setAutonomija(((Number) voziloData.get("autonomija")).intValue());
                break;
            case "ETrotinet":
                vozilo = new ETrotinet();
                ((ETrotinet) vozilo).setMaksBrzina(((Number) voziloData.get("maksBrzina")).intValue());
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }

        // Set common fields
        vozilo.setProizvodjac((Proizvodjac) voziloData.get("proizvodjac"));
        vozilo.setCijenaNabavke(((Number) voziloData.get("cijenaNabavke")).doubleValue());

        return vozilo;
    }
    

    public List<Vozilo> uploadVozilaFromCsv(MultipartFile file) throws Exception {
        List<Vozilo> vozila = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())))) {
            csvReader.readNext(); // Skip header
            String[] data;
            int broj = 1;
            while ((data = csvReader.readNext()) != null) {
                broj++;
                if (data.length < 6) {
                    throw new IllegalArgumentException("Invalid CSV format at line " + broj + ": Expected at least 6 columns, found " + data.length + ". Line: '" + String.join(",", data) + "'");
                }
                Vozilo vozilo;
                String type = data[0].trim();
                try {
                    if (type.equals("Auto")) {
                        if (data[1].trim().isEmpty() || data[2].trim().isEmpty() || data[3].trim().isEmpty() || data[4].trim().isEmpty() || data[5].trim().isEmpty()) {
                            throw new IllegalArgumentException("Missing required fields for Auto at line " + broj + ": datumNabavke, model, opis, cijenaNabavke, and proizvodjacId are required");
                        }
                        Auto auto = new Auto();
                        auto.setDatumNabavke(LocalDate.parse(data[1].trim()));
                        auto.setModel(data[2].trim());
                        auto.setOpis(data[3].trim());
                        auto.setCijenaNabavke(Double.parseDouble(data[4].trim()));
                        Long proizvodjacId = Long.parseLong(data[5].trim());
                        Proizvodjac proizvodjac = proizvodjacRepository.findById(proizvodjacId)
                            .orElseThrow(() -> new IllegalArgumentException("Proizvodjac with ID " + proizvodjacId + " not found at line "));
                        auto.setProizvodjac(proizvodjac);
                        vozilo = auto;
                    } else if (type.equals("EBike")) {
                        if (data[1].trim().isEmpty() || data[3].trim().isEmpty()) {
                            throw new IllegalArgumentException("Missing required fields for EBike at line " + broj + ": autonomija and cijenaNabavke are required");
                        }
                        EBike eBike = new EBike();
                        eBike.setAutonomija(Integer.parseInt(data[1].trim()));
                        eBike.setCijenaNabavke(Double.parseDouble(data[3].trim()));
                        vozilo = eBike;
                    } else if (type.equals("ETrotinet")) {
                        if (data[1].trim().isEmpty() || data[3].trim().isEmpty()) {
                            throw new IllegalArgumentException("Missing required fields for ETrotinet at line " + broj + ": maksBrzina and cijenaNabavke are required");
                        }
                        ETrotinet eTrotinet = new ETrotinet();
                        eTrotinet.setMaksBrzina(Double.parseDouble(data[1].trim()));
                        eTrotinet.setCijenaNabavke(Double.parseDouble(data[3].trim()));
                        vozilo = eTrotinet;
                    } else {
                        throw new IllegalArgumentException("Unknown vehicle type at line " + broj + ": " + type);
                    }
                    vozila.add(vozilo);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format at line " + broj + ": " + e.getMessage());
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format at line " + broj + ": " + e.getMessage());
                }
            }
        } catch (CsvValidationException e) {
            throw new IllegalArgumentException("Invalid CSV structure: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error reading CSV file: " + e.getMessage());
        }
        return voziloRepository.saveAll(vozila);
    }

    public Optional<Vozilo> getVoziloById(Integer id) {
        return voziloRepository.findById(id);
    }

    
}

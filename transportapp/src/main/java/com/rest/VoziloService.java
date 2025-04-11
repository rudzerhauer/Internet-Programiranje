package com.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.Auto;
import com.model.EBike;
import com.model.ETrotinet;
import com.model.Proizvodjac;
import com.model.Vozilo;
import com.repositorys.VoziloRepository;

@Service
public class VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

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
        return filtered;
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

    public Vozilo createVozilo(String type, Vozilo vozilo) {
        if (type.equals("Auto")) {
            Auto auto = new Auto();
            auto.setCijenaNabavke(vozilo.getCijenaNabavke());
            auto.setProizvodjac(vozilo.getProizvodjac());
            auto.setDatumNabavke(((Auto) vozilo).getDatumNabavke());
            auto.setModel(((Auto) vozilo).getModel());
            auto.setOpis(((Auto) vozilo).getOpis());
            return voziloRepository.save(auto);
        } else if (type.equals("EBike")) {
            EBike eBike = new EBike();
            eBike.setCijenaNabavke(vozilo.getCijenaNabavke());
            eBike.setProizvodjac(vozilo.getProizvodjac());
            eBike.setAutonomija(((EBike) vozilo).getAutonomija());
            return voziloRepository.save(eBike);
        } else if (type.equals("ETrotinet")) {
            ETrotinet eTrotinet = new ETrotinet();
            eTrotinet.setCijenaNabavke(vozilo.getCijenaNabavke());
            eTrotinet.setProizvodjac(vozilo.getProizvodjac());
            eTrotinet.setMaksBrzina(((ETrotinet) vozilo).getMaksBrzina());
            return voziloRepository.save(eTrotinet);
        }
        throw new IllegalArgumentException("Invalid vehicle type");
    }

    public void deleteVozilo(Integer id) {
        voziloRepository.deleteById(id);
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }
                String[] data = line.split(",");
                if (data.length < 6) {
                    throw new IllegalArgumentException("Invalid CSV format");
                }
                Vozilo vozilo;
                String type = data[0].trim();
                if (type.equals("Auto")) {
                    Auto auto = new Auto();
                    auto.setDatumNabavke(LocalDate.parse(data[1].trim()));
                    auto.setModel(data[2].trim());
                    auto.setOpis(data[3].trim());
                    auto.setCijenaNabavke(Double.parseDouble(data[4].trim()));
                    // Note: Proizvodjac needs to be fetched or set based on an ID
                    vozilo = auto;
                } else if (type.equals("EBike")) {
                    EBike eBike = new EBike();
                    eBike.setAutonomija(Integer.valueOf(data[1].trim()));
                    eBike.setCijenaNabavke(Double.parseDouble(data[2].trim()));
                    vozilo = eBike;
                } else if (type.equals("ETrotinet")) {
                    ETrotinet eTrotinet = new ETrotinet();
                    eTrotinet.setMaksBrzina(Double.parseDouble(data[1].trim()));
                    eTrotinet.setCijenaNabavke(Double.parseDouble(data[2].trim()));
                    vozilo = eTrotinet;
                } else {
                    throw new IllegalArgumentException("Unknown vehicle type: " + type);
                }
                vozila.add(vozilo);
            }
        }
        return voziloRepository.saveAll(vozila);
    }

    public Optional<Vozilo> getVoziloById(Integer id) {
        return voziloRepository.findById(id);
    }
}

package com.andreea.librarium.service;


import com.andreea.librarium.model.RezervariCarti;
import com.andreea.librarium.repositories.RezervareCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervareCarteService {
    @Autowired
    private RezervareCarteRepository rezervareCarteRepository;
    @Autowired
    public RezervareCarteService(RezervareCarteRepository rezervareCarteRepository) {
        this.rezervareCarteRepository = rezervareCarteRepository;
    }

    public List<RezervariCarti> getRezervariByUserId(Integer idUtilizator) {
        return rezervareCarteRepository.findByIdUtilizator_Id(idUtilizator);
    }
    public boolean anuleazaRezervare(Integer idRezervare) {
        try {
            rezervareCarteRepository.deleteById(idRezervare);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public  void saveRezervareCarte(RezervariCarti rezervariCarti){
        rezervareCarteRepository.save(rezervariCarti);
    }
}

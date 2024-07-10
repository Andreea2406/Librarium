package com.andreea.librarium.service;

import com.andreea.librarium.model.RezervariSali;
import com.andreea.librarium.model.SaliBiblioteca;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RezervareSaliRepository;
import com.andreea.librarium.repositories.SaliRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RezervareSaliService {

    private final RezervareSaliRepository rezervareSaliRepository;
    private final SaliService saliService;
    private final UtilizatoriRepository utilizatoriRepository;

    @Autowired
    public RezervareSaliService(RezervareSaliRepository rezervareSaliRepository, SaliService saliService, UtilizatoriRepository utilizatoriRepository) {
        this.rezervareSaliRepository = rezervareSaliRepository;
        this.saliService = saliService;
        this.utilizatoriRepository = utilizatoriRepository;
    }
    public boolean anuleazaRezervareSala(Integer idRezervareSala) {
        try {
            rezervareSaliRepository.deleteById(idRezervareSala);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public RezervariSali adaugaRezervare(Integer salaId, Integer utilizatorId, Instant dataOraInceput, Instant dataOraFinal) {
        SaliBiblioteca sala = saliService.getSalaById(salaId);
        if (sala == null) {
            throw new RuntimeException("Sala nu a fost găsită.");
        }
        Utilizatori utilizator = utilizatoriRepository.findById(utilizatorId);
        if (utilizator == null) {
            throw new RuntimeException("Utilizatorul nu a fost găsit.");
        }


        RezervariSali rezervare = new RezervariSali();
        rezervare.setIdSala(sala);
        rezervare.setIdUtilizator(utilizator);
        rezervare.setDataOraInceput(dataOraInceput);
        rezervare.setDataOraFinal(dataOraFinal);
        rezervare.setStareRezervare(true);

        return rezervareSaliRepository.save(rezervare);
    }
    public List<RezervariSali> getRezervariByUserId(Integer utilizatorId) {
        return rezervareSaliRepository.findByIdUtilizator_Id(utilizatorId);
    }
}

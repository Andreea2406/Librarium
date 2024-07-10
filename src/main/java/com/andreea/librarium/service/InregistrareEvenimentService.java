package com.andreea.librarium.service;

import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.model.InregistrareEveniment;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.EvenimenteRepository;
import com.andreea.librarium.repositories.InregistrareEvenimentRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InregistrareEvenimentService {

    @Autowired
    private InregistrareEvenimentRepository inregistrareEvenimentRepository;

    @Autowired
    private EvenimenteRepository evenimenteRepository;

    @Autowired
    private UtilizatoriRepository utilizatoriRepository;
    public List<InregistrareEveniment> getRegistrationsForCurrentUser(Integer userId) {
        return inregistrareEvenimentRepository.findByUserId(userId);
    }

    public boolean inscrieUtilizatorLaEveniment(Integer idEveniment, Integer idUtilizator) {
        try {
            Evenimente eveniment = evenimenteRepository.findById(idEveniment).orElse(null);
            Utilizatori utilizator = utilizatoriRepository.findById(idUtilizator);

            if (eveniment == null || utilizator == null) {
                return false;
            }

            InregistrareEveniment inregistrareEveniment = new InregistrareEveniment();
            inregistrareEveniment.setIdEveniment(eveniment);
            inregistrareEveniment.setIdUtilizator(utilizator);
            inregistrareEveniment.setDataInregistrarii(LocalDate.now());

            inregistrareEvenimentRepository.save(inregistrareEveniment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean anuleazaInscriere(Integer idInregistrare) {
        try {
            inregistrareEvenimentRepository.deleteById(idInregistrare);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

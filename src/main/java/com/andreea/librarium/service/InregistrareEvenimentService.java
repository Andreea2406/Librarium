package com.andreea.librarium.service;

import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.model.InregistrareEveniment;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.EvenimenteRepository;
import com.andreea.librarium.repositories.InregistrareEvenimentRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InregistrareEvenimentService {

    @Autowired
    private InregistrareEvenimentRepository inregistrareEvenimentRepository;

    @Autowired
    private EvenimenteRepository evenimenteRepository;

    @Autowired
    private UtilizatoriRepository utilizatoriRepository;

    public boolean inscrieUtilizatorLaEveniment(Integer idEveniment, Integer idUtilizator) {
        try {
            Evenimente eveniment = evenimenteRepository.findById(idEveniment).orElse(null);
            Utilizatori utilizator = utilizatoriRepository.findById(idUtilizator);

            if (eveniment == null || utilizator == null) {
                return false; // Evenimentul sau utilizatorul nu există
            }

            InregistrareEveniment inregistrareEveniment = new InregistrareEveniment();
            inregistrareEveniment.setIdEveniment(eveniment);
            inregistrareEveniment.setIdUtilizator(utilizator);
            inregistrareEveniment.setDataInregistrarii(LocalDate.now());

            inregistrareEvenimentRepository.save(inregistrareEveniment);
            return true; // Înregistrare efectuată cu succes
        } catch (Exception e) {
            e.printStackTrace();
            return false; // A apărut o eroare la înregistrare
        }
    }
}

package com.andreea.librarium.service;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.CartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartiService {


    private final CartiRepository cartiRepository;

    @Autowired
    public CartiService(CartiRepository cartiRepository) {
        this.cartiRepository = cartiRepository;
    }

    public Carti saveCarte(Carti carte) {
        // Aici puteți adăuga logica pentru validare, prelucrare etc., apoi salvați în repository
        return cartiRepository.save(carte);
    }
    public List<Carti> getAllBooks() {
        return cartiRepository.findAll();
    }

    public Carti getBookById(Integer id){
//        Carti carti=cartiRepository.findById(id);
        return cartiRepository.findById(id).orElse(null);

    }
//    public Carti obtineTitluCarte(Integer carteId) {
//        Carti carti = cartiRepository.findById(carteId);
//
//        // Verificăm dacă cartea există și returnăm titlul sau un mesaj corespunzător
//        return carteOptional.map(Carti::getTitlu).orElse("Cartea nu a fost găsită pentru ID-ul specificat");
//    }
    public Carti updateCarte(Carti carti){
        return cartiRepository.save(carti);
    }

    public Carti deleteCarteById(Integer id) {
        // Fetch the entity by ID
        Carti carti =cartiRepository.findById(id).orElse(null);

        // Check if the entity exists
        if (carti != null) {
            // Delete the entity
            cartiRepository.delete(carti);

            // Return the deleted entity or relevant information
            return carti;
        } else {
            // If the entity does not exist, you might handle it accordingly
            return null;
        }
    }

}

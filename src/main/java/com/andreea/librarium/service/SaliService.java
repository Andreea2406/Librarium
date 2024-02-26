package com.andreea.librarium.service;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.SaliBiblioteca;
import com.andreea.librarium.repositories.SaliRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaliService {

    private final SaliRepository saliRepository;

    public SaliService(SaliRepository saliRepository) {
        this.saliRepository = saliRepository;
    }

    public List<SaliBiblioteca> getAll(){
        return saliRepository.findAll();

    }
    public SaliBiblioteca saveSala(SaliBiblioteca saliBiblioteca){
        return saliRepository.save(saliBiblioteca);
    }
    public SaliBiblioteca getSalaById(Integer id){
//        Carti carti=cartiRepository.findById(id);
        return saliRepository.findById(id).orElse(null);

    }
    public SaliBiblioteca updateSala(SaliBiblioteca saliBiblioteca){
        return saliRepository.save(saliBiblioteca);
    }
    public SaliBiblioteca deleteSalaById(Integer id) {
        // Fetch the entity by ID
        SaliBiblioteca saliBiblioteca=saliRepository.findById(id).orElse(null);

        // Check if the entity exists
        if (saliBiblioteca != null) {
            // Delete the entity
            saliRepository.delete(saliBiblioteca);

            // Return the deleted entity or relevant information
            return saliBiblioteca;
        } else {
            // If the entity does not exist, you might handle it accordingly
            return null;
        }
    }
}

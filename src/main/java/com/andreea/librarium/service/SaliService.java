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
        SaliBiblioteca saliBiblioteca=saliRepository.findById(id).orElse(null);

        if (saliBiblioteca != null) {
            saliRepository.delete(saliBiblioteca);

            return saliBiblioteca;
        } else {
            return null;
        }
    }
}

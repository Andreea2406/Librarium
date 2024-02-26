package com.andreea.librarium.service;

import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.repositories.EvenimenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenimenteService {

    private final EvenimenteRepository evenimenteRepository;

    public EvenimenteService(EvenimenteRepository evenimenteRepository) {
        this.evenimenteRepository = evenimenteRepository;
    }

    public Evenimente saveEveniment(Evenimente evenimente){
        return evenimenteRepository.save(evenimente);
    }

    public List<Evenimente> getAllEvenimente(){
        return evenimenteRepository.findAll();

    }
    public Evenimente getEvenimentById(Integer id){
        return evenimenteRepository.findById(id).orElse(null);
    }
    public Evenimente updateEveniment(Evenimente evenimente){
        return evenimenteRepository.save(evenimente);
    }
    public Evenimente deleteEvenimentById(Integer id){
        Evenimente evenimente=evenimenteRepository.findById(id).orElse(null);
        if(evenimente!=null){
            evenimenteRepository.delete(evenimente);
            return evenimente;
        }
        else {
            return null;
        }
    }
}
package com.andreea.librarium.service;

import com.andreea.librarium.model.Imprumuturi;
import com.andreea.librarium.repositories.ImprumuturiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImprumuturiService {

    @Autowired
    private ImprumuturiRepository imprumuturiRepository; // Inject your repository
    @Autowired
    public ImprumuturiService(ImprumuturiRepository imprumuturiRepository) {
        this.imprumuturiRepository = imprumuturiRepository;
    }

    public List<Imprumuturi> getImprumuturiByUserId(Integer idUtilizator) {
        return imprumuturiRepository.findByIdUtilizator_Id(idUtilizator);
    }
    public void saveImprumut(Imprumuturi imprumut) {
        imprumuturiRepository.save(imprumut);
    }
    public List<Imprumuturi> getAllImprumuturi(){
        return imprumuturiRepository.findAll();

    }
}

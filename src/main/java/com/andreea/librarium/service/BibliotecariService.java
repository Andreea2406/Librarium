package com.andreea.librarium.service;


import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.BibliotecariRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BibliotecariService {
    private BibliotecariService bibliotecariService;
    private BibliotecariRepository bibliotecariRepository;
    private UtilizatoriRepository utilizatoriRepository;
    @Autowired
    public BibliotecariService(BibliotecariRepository bibliotecariRepository) {
        this.bibliotecariRepository = bibliotecariRepository;
    }

    public List<Utilizatori> getAllBibliotecari() {
        return bibliotecariRepository.findAllBibliotecari();
    }


}

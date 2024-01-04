package com.andreea.librarium.service;

import com.andreea.librarium.model.Rol;
import com.andreea.librarium.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> findByNumeRol(String numeRol) {
        return rolRepository.findByNumeRol(numeRol);
    }
    public Optional<Rol> findById(Integer roleId) {
        return rolRepository.findById(roleId);
    }
    // Other methods related to roles can be added here

}

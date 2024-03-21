package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartiRepository extends JpaRepository<Carti, Integer> {

    Optional<Carti>findByTitlu(String titlu);

    Carti findById(Long id);


}

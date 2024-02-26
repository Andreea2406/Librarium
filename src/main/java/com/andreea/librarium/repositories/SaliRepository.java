package com.andreea.librarium.repositories;


import com.andreea.librarium.model.SaliBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaliRepository extends JpaRepository<SaliBiblioteca,Integer> {
}

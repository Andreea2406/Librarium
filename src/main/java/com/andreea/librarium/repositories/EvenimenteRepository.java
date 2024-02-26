package com.andreea.librarium.repositories;


import com.andreea.librarium.model.Evenimente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenimenteRepository extends JpaRepository<Evenimente,Integer> {
}

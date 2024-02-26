package com.andreea.librarium.repositories;

import com.andreea.librarium.model.RezervariCarti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervareCarteRepository extends JpaRepository<RezervariCarti, Integer> {
    List<RezervariCarti> findByIdUtilizator_Id(Integer idUtilizator);

}

package com.andreea.librarium.repositories;

import com.andreea.librarium.model.RezervariSali;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezervareSaliRepository extends JpaRepository<RezervariSali,Integer> {
    List<RezervariSali> findByIdUtilizator_Id(Integer utilizatorId);

}

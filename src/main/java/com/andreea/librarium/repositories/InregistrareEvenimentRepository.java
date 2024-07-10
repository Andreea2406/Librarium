package com.andreea.librarium.repositories;

import com.andreea.librarium.model.InregistrareEveniment;
import com.andreea.librarium.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InregistrareEvenimentRepository extends JpaRepository<InregistrareEveniment, Integer> {
    @Query("SELECT ie FROM InregistrareEveniment ie WHERE ie.idUtilizator.id = :userId")
    List<InregistrareEveniment> findByUserId(@Param("userId") Integer userId);
}

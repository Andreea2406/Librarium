package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Conversatie;
import com.andreea.librarium.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesajRepository extends JpaRepository<Mesaj, Long> {
    List<Mesaj> findByConversatieId(Integer conversatieId);





}
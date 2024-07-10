package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Conversatie;
import com.andreea.librarium.model.ParticipantConversatie;
import com.andreea.librarium.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ParticipantConversatieRepository  extends JpaRepository<ParticipantConversatie, Integer> {


    boolean existsByUtilizatoriAndConversatie(Utilizatori utilizatori, Conversatie conversatie);
boolean existsByUtilizatoriIdAndConversatieId(Integer utilizatoriId, Integer conversatieId);
    Optional<ParticipantConversatie> findByUtilizatoriAndConversatie(Utilizatori utilizatori, Conversatie conversatie);

    List<ParticipantConversatie> findByUtilizatoriId(Integer utilizatoriId);
    List<ParticipantConversatie> findByConversatieId(Integer conversatieId);




}

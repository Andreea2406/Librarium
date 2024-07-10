package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Conversatie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversatieRepository extends JpaRepository<Conversatie, Integer> {
    Optional<Conversatie> findByTitlu(String titlu);
    @Override
    List<Conversatie> findAll();
    @Query("SELECT c FROM Conversatie c JOIN c.participanti p JOIN p.utilizatori u JOIN u.roluriUtilizatoris ru JOIN ru.idRol r WHERE r.numeRol = 'bibliotecar' AND u.id = :userId")
    List<Conversatie> findAllByBibliotecarId(@Param("userId") Long userId);


}

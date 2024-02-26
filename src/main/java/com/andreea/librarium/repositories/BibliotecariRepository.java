package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliotecariRepository extends JpaRepository<Utilizatori, Integer> {
    @Query("SELECT utilizator FROM Utilizatori utilizator JOIN utilizator.role role WHERE role.numeRol = 'bibliotecar'")
//    List<Utilizatori> findUserByRole(int role);
    List<Utilizatori> findAllBibliotecari();



}

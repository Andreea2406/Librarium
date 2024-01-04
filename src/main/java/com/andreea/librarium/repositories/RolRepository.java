package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface RolRepository extends JpaRepository<Rol,Integer> {
    @Query("SELECT r FROM Rol r WHERE r.numeRol=?1")
    Optional<Rol> findByNumeRol(String numeRol);
}

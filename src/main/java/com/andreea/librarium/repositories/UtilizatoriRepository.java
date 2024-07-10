package com.andreea.librarium.repositories;
import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.Utilizatori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilizatoriRepository extends JpaRepository<Utilizatori,Long> {
      Optional<Utilizatori>findByEmail(String email);
      Optional<Utilizatori> findByEmailAndParola(String email, String parola);
      Utilizatori findById(Integer id);
      @Query("SELECT DISTINCT u FROM Utilizatori u JOIN u.role r WHERE r.id = :rolId")

      List<Utilizatori> findAllByRol(@Param("rolId") Long rolId);
      @Query("SELECT u FROM Utilizatori u WHERE u.id = :id")
      Optional<Utilizatori> findByIdAsInteger(@Param("id") Integer id);

      List<Utilizatori> findByNumeContainingIgnoreCase(String nume);



      @Query("SELECT u FROM Utilizatori u JOIN u.roluriUtilizatoris ru JOIN ru.idRol r WHERE r.numeRol = :rolName")
      List<Utilizatori> findAllByRole_Name(@Param("rolName") String rolName);



}

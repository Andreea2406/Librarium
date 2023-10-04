package com.andreea.librarium.repositories;
import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//public interface CustomerRepository extends JpaRepository<Customer, String>
@Repository

public interface UtilizatoriRepository extends JpaRepository<Utilizatori,Long> {
      Optional<Utilizatori>findByEmail(String email);

      Optional<Utilizatori> findByEmailAndParola(String email, String parola);
     // Optional<Rol> findByName(String name);



}

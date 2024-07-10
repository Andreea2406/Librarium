package com.andreea.librarium.repositories;

import com.andreea.librarium.model.Carti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CartiRepository extends JpaRepository<Carti, Integer>, CartiRepositoryCustom  {

    Optional<Carti>findByTitlu(String titlu);
    List<Carti> findTop9ByOrderByIdAsc();

    Carti findById(Long id);
    List<Carti> findByCategorieIn(Set<String> categorie);
    List<Carti> findByTitluContainingIgnoreCaseOrAutorContainingIgnoreCase(String titluQuery, String autorQuery);
    List<Carti> findByTitluContainingIgnoreCase(String title);


}

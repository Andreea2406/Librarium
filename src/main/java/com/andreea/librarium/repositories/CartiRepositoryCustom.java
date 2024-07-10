package com.andreea.librarium.repositories;
import com.andreea.librarium.model.Carti;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartiRepositoryCustom {
    List<Carti> findByCriteria(String query, String categorie, String autor, String editura, String limba);

}

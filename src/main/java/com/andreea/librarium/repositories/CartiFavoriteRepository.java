package com.andreea.librarium.repositories;

import com.andreea.librarium.model.CartiFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartiFavoriteRepository extends JpaRepository<CartiFavorite, Integer> {
    boolean existsByUtilizatorIdAndCarteId(Integer utilizatorId, Integer carteId);

    @Query("SELECT cf FROM CartiFavorite cf JOIN FETCH cf.carte WHERE cf.utilizatorId = :utilizatorId")
    List<CartiFavorite> findFavoriteCartiIdsByUserId(@Param("utilizatorId") Integer utilizatorId);
    @Query("SELECT cf FROM CartiFavorite cf JOIN FETCH cf.carte WHERE cf.utilizatorId = :utilizatorId")
    List<CartiFavorite> findWithCartiByUserId(@Param("utilizatorId") Integer utilizatorId);


    @Query("SELECT cf FROM CartiFavorite cf WHERE cf.utilizatorId = :utilizatorId")
    List<CartiFavorite> findByUtilizatorId(@Param("utilizatorId") Integer utilizatorId);
    void deleteByUtilizatorIdAndCarteId(Integer utilizatorId, Integer carteId);
    void deleteByUtilizatorId(Integer utilizatorId);

}

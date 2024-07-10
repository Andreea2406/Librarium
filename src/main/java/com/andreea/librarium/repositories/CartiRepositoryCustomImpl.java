package com.andreea.librarium.repositories;
import com.andreea.librarium.model.Carti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository
public class CartiRepositoryCustomImpl implements CartiRepositoryCustom{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Carti> findByCriteria(String query, String categorie, String autor, String editura, String limba) {
        StringBuilder sql = new StringBuilder("SELECT c FROM Carti c WHERE 1=1");

        if (query != null && !query.isEmpty()) {
            sql.append(" AND c.titlu LIKE :query");
        }
        if (categorie != null && !categorie.isEmpty()) {
            sql.append(" AND c.categorie = :categorie");
        }
        if (autor != null && !autor.isEmpty()) {
            sql.append(" AND c.autor = :autor");
        }
        if (editura != null && !editura.isEmpty()) {
            sql.append(" AND c.editura = :editura");
        }
        if (limba != null && !limba.isEmpty()) {
            sql.append(" AND c.limba = :limba");
        }

        Query jpaQuery = entityManager.createQuery(sql.toString(), Carti.class);

        if (query != null && !query.isEmpty()) {
            jpaQuery.setParameter("query", "%" + query + "%");
        }
        if (categorie != null && !categorie.isEmpty()) {
            jpaQuery.setParameter("categorie", categorie);
        }
        if (autor != null && !autor.isEmpty()) {
            jpaQuery.setParameter("autor", autor);
        }
        if (editura != null && !editura.isEmpty()) {
            jpaQuery.setParameter("editura", editura);
        }
        if (limba != null && !limba.isEmpty()) {
            jpaQuery.setParameter("limba", limba);
        }

        return jpaQuery.getResultList();



    }
}

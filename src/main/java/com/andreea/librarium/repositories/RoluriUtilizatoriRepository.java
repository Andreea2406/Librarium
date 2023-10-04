package com.andreea.librarium.repositories;

import com.andreea.librarium.model.RoluriUtilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoluriUtilizatoriRepository  extends JpaRepository <RoluriUtilizatori,Long> {
}

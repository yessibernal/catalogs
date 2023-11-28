package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingColorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingColorRepository extends JpaRepository<DesingColorEntity,Long> {
    @Query("SELECT d FROM DesingColorEntity d WHERE d.status = 1")
    List<DesingColorEntity> findDesingColors (Pageable pageable);

    @Query("SELECT d FROM DesingColorEntity d WHERE d.id = :id AND d.status = 1")
    DesingColorEntity findDesingColorsStatusById (Long id);
}

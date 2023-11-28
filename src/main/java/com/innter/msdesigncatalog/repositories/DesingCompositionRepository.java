package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingCompositionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingCompositionRepository extends JpaRepository<DesingCompositionEntity, Long> {

    @Query("SELECT c FROM DesingCompositionEntity c WHERE c.status = 1")
    List<DesingCompositionEntity> findDesingComposition (Pageable pageable);

    @Query("SELECT c FROM DesingCompositionEntity c WHERE c.id = :id AND c.status = 1")
    DesingCompositionEntity findDesingCompositionsStatusById (Long id);
}

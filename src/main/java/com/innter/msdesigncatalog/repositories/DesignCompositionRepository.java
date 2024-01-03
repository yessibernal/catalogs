package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignCompositionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignCompositionRepository extends JpaRepository<DesignCompositionEntity, Long> {

    @Query("SELECT c FROM DesignCompositionEntity c WHERE c.status = 1")
    List<DesignCompositionEntity> findDesignComposition (Pageable pageable);

    @Query("SELECT c FROM DesignCompositionEntity c WHERE c.id = :id AND c.status = 1")
    DesignCompositionEntity findDesignCompositionsStatusById (Long id);
}

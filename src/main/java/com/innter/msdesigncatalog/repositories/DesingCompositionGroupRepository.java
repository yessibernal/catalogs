package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingCompositionGroupRepository extends JpaRepository<DesingCompositionGroupEntity,Long> {

    @Query("SELECT c FROM DesingCompositionGroupEntity c WHERE c.status = 1")
    List<DesingCompositionGroupEntity> findDesingCompositionGroup(Pageable pageable);

    @Query("SELECT c FROM DesingCompositionGroupEntity c WHERE c.id = :id AND c.status = 1")
    DesingCompositionGroupEntity findDesingCompositionGroupStatusById (Long id);
}

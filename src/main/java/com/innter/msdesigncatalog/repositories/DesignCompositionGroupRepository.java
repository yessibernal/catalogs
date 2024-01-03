package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignCompositionGroupRepository extends JpaRepository<DesignCompositionGroupEntity,Long> {

    @Query("SELECT c FROM DesignCompositionGroupEntity c WHERE c.status = 1")
    List<DesignCompositionGroupEntity> findDesignCompositionGroup(Pageable pageable);

    @Query("SELECT c FROM DesignCompositionGroupEntity c WHERE c.id = :id AND c.status = 1")
    DesignCompositionGroupEntity findDesignCompositionGroupStatusById (Long id);
}

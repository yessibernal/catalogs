package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import com.innter.msdesigncatalog.entities.DesignGarmentGroupEntity;
import com.innter.msdesigncatalog.entities.DesignPriceCompositionGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignPriceCompositionGroupRepository extends JpaRepository <DesignPriceCompositionGroupEntity, Long> {
    @Query("SELECT d FROM DesignPriceCompositionGroupEntity d WHERE d.status = 1")
    List<DesignPriceCompositionGroupEntity> findDesignPriceCompositionGroupEntity(Pageable pageable);

    @Query("SELECT d FROM DesignPriceCompositionGroupEntity d WHERE d.id = :id AND d.status = 1")
    DesignPriceCompositionGroupEntity findDesignPriceCompositionGroupEntityStatusById (Long id);

    @Query("SELECT g FROM DesignGarmentGroupEntity g WHERE g.id = :id AND g.status = 1")
    DesignGarmentGroupEntity findGarmentGroup (Long id);

    @Query("SELECT c FROM DesignCompositionGroupEntity c WHERE c.id = :id AND c.status = 1")
    DesignCompositionGroupEntity findCompositionGroup (Long id);



}

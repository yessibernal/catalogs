package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import com.innter.msdesigncatalog.entities.DesingPriceCompositionGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingPriceCompositionGroupRepository extends JpaRepository <DesingPriceCompositionGroupEntity, Long> {
    @Query("SELECT d FROM DesingPriceCompositionGroupEntity d WHERE d.status = 1")
    List<DesingPriceCompositionGroupEntity> findDesingPriceCompositionGroupEntity(Pageable pageable);

    @Query("SELECT d FROM DesingPriceCompositionGroupEntity d WHERE d.id = :id AND d.status = 1")
    DesingPriceCompositionGroupEntity findDesingPriceCompositionGroupEntityStatusById (Long id);

    @Query("SELECT g FROM DesingGarmentGroupEntity g WHERE g.id = :id AND g.status = 1")
    DesingGarmentGroupEntity findGarmentGroup (Long id);

    @Query("SELECT c FROM DesingCompositionGroupEntity c WHERE c.id = :id AND c.status = 1")
    DesingCompositionGroupEntity findCompositionGroup (Long id);



}

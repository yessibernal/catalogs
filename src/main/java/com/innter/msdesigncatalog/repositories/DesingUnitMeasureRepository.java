package com.innter.msdesigncatalog.repositories;
import com.innter.msdesigncatalog.entities.DesingUnitMeasureEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingUnitMeasureRepository extends JpaRepository<DesingUnitMeasureEntity,Long> {

    @Query("SELECT d FROM DesingUnitMeasureEntity d WHERE d.id = :id AND d.status = 1")
    DesingUnitMeasureEntity findDesingUnitMeasureStatusById(Long id);

    @Query("SELECT d FROM DesingUnitMeasureEntity d WHERE d.status = 1")
    List<DesingUnitMeasureEntity> findDesingUnitsMeasure (Pageable pageable);

}

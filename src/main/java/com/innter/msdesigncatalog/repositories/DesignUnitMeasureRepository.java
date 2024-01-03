package com.innter.msdesigncatalog.repositories;
import com.innter.msdesigncatalog.entities.DesignUnitMeasureEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignUnitMeasureRepository extends JpaRepository<DesignUnitMeasureEntity,Long> {

    @Query("SELECT d FROM DesignUnitMeasureEntity d WHERE d.id = :id AND d.status = 1")
    DesignUnitMeasureEntity findDesignUnitMeasureStatusById(Long id);

    @Query("SELECT d FROM DesignUnitMeasureEntity d WHERE d.status = 1")
    List<DesignUnitMeasureEntity> findDesignUnitsMeasure (Pageable pageable);

}

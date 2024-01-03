package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignPrintEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignPrintRepository extends JpaRepository<DesignPrintEntity, Long> {
    @Query("SELECT d FROM DesignPrintEntity d WHERE d.status = 1")
    List<DesignPrintEntity> findDesingPrints(Pageable pageable);

    @Query("SELECT d FROM DesignPrintEntity d WHERE d.id = :id AND d.status = 1")
    DesignPrintEntity findDesingPrintsStatusById(Long id);
}

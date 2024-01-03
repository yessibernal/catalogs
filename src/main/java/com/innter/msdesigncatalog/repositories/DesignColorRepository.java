package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignColorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignColorRepository extends JpaRepository<DesignColorEntity,Long> {
    @Query("SELECT d FROM DesignColorEntity d WHERE d.status = 1")
    List<DesignColorEntity> findDesignColors (Pageable pageable);

    @Query("SELECT d FROM DesignColorEntity d WHERE d.id = :id AND d.status = 1")
    DesignColorEntity findDesignColorsStatusById (Long id);
}

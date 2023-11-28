package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingMaterialTypeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingMaterialTypeRepository extends JpaRepository<DesingMaterialTypeEntity, Long> {

    @Query("SELECT m FROM DesingMaterialTypeEntity m WHERE m.status = 1")
    List<DesingMaterialTypeEntity> findDesingMaterialType(Pageable pageable);

    @Query("SELECT m FROM DesingMaterialTypeEntity m WHERE m.id = :id AND m.status = 1")
    DesingMaterialTypeEntity findDesingMaterialTypeStatusById (Long id);
}

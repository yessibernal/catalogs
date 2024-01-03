package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignMaterialTypeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMaterialTypeRepository extends JpaRepository<DesignMaterialTypeEntity, Long> {

    @Query("SELECT m FROM DesignMaterialTypeEntity m WHERE m.status = 1")
    List<DesignMaterialTypeEntity> findDesignMaterialType(Pageable pageable);

    @Query("SELECT m FROM DesignMaterialTypeEntity m WHERE m.id = :id AND m.status = 1")
    DesignMaterialTypeEntity findDesignMaterialTypeStatusById (Long id);
}

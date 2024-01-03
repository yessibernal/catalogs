package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignCareInstructionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignCareInstructionRepository extends JpaRepository <DesignCareInstructionEntity, Long> {

    @Query("SELECT i FROM DesignCareInstructionEntity i WHERE i.status = 1")
    List<DesignCareInstructionEntity> findDesignCareInstruction (Pageable pageable);

    @Query("SELECT i FROM DesignCareInstructionEntity i WHERE i.id = :id AND i.status = 1")
    DesignCareInstructionEntity findDesignCareInstructionStatusById (Long id);
}

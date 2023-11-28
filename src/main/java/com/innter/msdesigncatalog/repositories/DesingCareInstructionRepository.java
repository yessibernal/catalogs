package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingCareInstructionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingCareInstructionRepository extends JpaRepository <DesingCareInstructionEntity, Long> {

    @Query("SELECT i FROM DesingCareInstructionEntity i WHERE i.status = 1")
    List<DesingCareInstructionEntity> findDesingCareInstruction (Pageable pageable);

    @Query("SELECT i FROM DesingCareInstructionEntity i WHERE i.id = :id AND i.status = 1")
    DesingCareInstructionEntity findDesingCareInstructionStatusById (Long id);
}

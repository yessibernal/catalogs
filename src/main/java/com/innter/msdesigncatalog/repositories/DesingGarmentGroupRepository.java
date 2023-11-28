package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingGarmentGroupRepository extends JpaRepository<DesingGarmentGroupEntity, Long> {

    @Query("SELECT g FROM DesingGarmentGroupEntity g WHERE g.status = 1")
    List<DesingGarmentGroupEntity> findDesingGarmentGroup (Pageable pageable);

    @Query("SELECT g FROM DesingGarmentGroupEntity g WHERE g.id = :id AND g.status = 1")
    DesingGarmentGroupEntity findDesingGarmentGroupStatusById (Long id);
}

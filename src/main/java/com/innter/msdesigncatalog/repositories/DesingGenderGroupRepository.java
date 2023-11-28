package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingGendersGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DesingGenderGroupRepository extends JpaRepository<DesingGendersGroupEntity, Long> {

    @Query("SELECT g FROM DesingGendersGroupEntity g WHERE g.status = 1")
    List<DesingGendersGroupEntity> findDesingGendersGroup (Pageable pageable);

    @Query("SELECT g FROM DesingGendersGroupEntity g WHERE g.id = :id AND g.status = 1")
    DesingGendersGroupEntity findDesingGendersGroupStatusById (Long id);
}

package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignGendersGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DesignGenderGroupRepository extends JpaRepository<DesignGendersGroupEntity, Long> {

    @Query("SELECT g FROM DesignGendersGroupEntity g WHERE g.status = 1")
    List<DesignGendersGroupEntity> findDesignGendersGroup (Pageable pageable);

    @Query("SELECT g FROM DesignGendersGroupEntity g WHERE g.id = :id AND g.status = 1")
    DesignGendersGroupEntity findDesignGendersGroupStatusById (Long id);
}

package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignMarkerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMarkerRepository extends JpaRepository<DesignMarkerEntity, Long> {

    @Query("SELECT m FROM DesignMarkerEntity m WHERE m.status = 1")
    List<DesignMarkerEntity> findDesignMarker(Pageable pageable);

    @Query("SELECT m FROM DesignMarkerEntity m WHERE m.id = :id AND m.status = 1")
    DesignMarkerEntity findDesignMarkerStatusById (Long id);
}

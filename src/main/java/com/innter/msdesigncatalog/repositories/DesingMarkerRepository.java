package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingMarkerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingMarkerRepository extends JpaRepository<DesingMarkerEntity, Long> {

    @Query("SELECT m FROM DesingMarkerEntity m WHERE m.status = 1")
    List<DesingMarkerEntity> findDesingMarker(Pageable pageable);

    @Query("SELECT m FROM DesingMarkerEntity m WHERE m.id = :id AND m.status = 1")
    DesingMarkerEntity findDesingMarkerStatusById (Long id);
}

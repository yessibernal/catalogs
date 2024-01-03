package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesignTracePieceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface DesignTracePieceRepository extends JpaRepository <DesignTracePieceEntity, Long> {

    @Query("SELECT d FROM DesignTracePieceEntity d WHERE d.id = :id AND d.status = 1")
    DesignTracePieceEntity findDesignTracePiecesStatusById (Long id);

    @Query("SELECT d FROM DesignTracePieceEntity d WHERE d.status = 1")
    List<DesignTracePieceEntity> findDesignTracePiecesStatus (Pageable pageable);
}

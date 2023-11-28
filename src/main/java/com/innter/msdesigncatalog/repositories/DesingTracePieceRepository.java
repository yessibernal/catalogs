package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.DesingTracePieceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface DesingTracePieceRepository extends JpaRepository <DesingTracePieceEntity, Long> {

    @Query("SELECT d FROM DesingTracePieceEntity d WHERE d.id = :id AND d.status = 1")
    DesingTracePieceEntity findDesingTracePiecesStatusById (Long id);

    @Query("SELECT d FROM DesingTracePieceEntity d WHERE d.status = 1")
    List<DesingTracePieceEntity> findDesingTracePiecesStatus (Pageable pageable);
}

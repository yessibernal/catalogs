package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesignTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesignTracePieceResponse;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesignTracePieceService {

    DesignTracePieceResponse saveDesignTracePiece (DesignTracePieceRequest designTracePieceRequest);

    List<DesignTracePieceResponse> getDesignTracePieces (Pageable pageable);

    DesignTracePieceResponse editedDesignTracePiece (DesignTracePieceRequest designTracePieceRequest, Long id);

    DesignTracePieceResponse editedDesignTracePieceByStatus (DesignRequestStatus designRequestStatus, Long id);
}

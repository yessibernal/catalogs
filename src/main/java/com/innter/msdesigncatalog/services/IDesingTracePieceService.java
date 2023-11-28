package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesingTracePieceResponse;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesingTracePieceService {

    DesingTracePieceResponse saveDesingTracePiece (DesingTracePieceRequest desingTracePieceRequest);

    List<DesingTracePieceResponse> getDesingTracePieces (Pageable pageable);

    DesingTracePieceResponse editedDesingTracePiece (DesingTracePieceRequest desingTracePieceRequest, Long id);

    DesingTracePieceResponse editedDesingTracePieceByStatus (DesingRequestStatus desingRequestStatus, Long id);
}

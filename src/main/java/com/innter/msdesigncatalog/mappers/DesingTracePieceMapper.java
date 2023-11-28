package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesingTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesingTracePieceEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingTracePieceMapper implements  IDesingTracePieceMapper{
    @Override
    public DesingTracePieceResponse desingTracePieceToDesingTracePieceResponse(DesingTracePieceEntity desingTracePiece) {
        DesingTracePieceResponse desingTracePieceResponse = new DesingTracePieceResponse();
        desingTracePieceResponse.setId(desingTracePiece.getId());
        desingTracePieceResponse.setName(desingTracePiece.getName());
        return desingTracePieceResponse;
    }

    @Override
    public DesingTracePieceEntity desingTracePieceRequestToDesingTracePiece(DesingTracePieceRequest desingTracePieceRequest) {
        DesingTracePieceEntity desingTracePiece = new DesingTracePieceEntity();
        desingTracePiece.setName(desingTracePieceRequest.getName());
        return desingTracePiece;
    }
}

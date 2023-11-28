package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesingTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesingTracePieceEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingTracePieceMapper {

    DesingTracePieceResponse desingTracePieceToDesingTracePieceResponse (DesingTracePieceEntity desingTracePiece);

    DesingTracePieceEntity desingTracePieceRequestToDesingTracePiece (DesingTracePieceRequest desingTracePieceRequest);
}

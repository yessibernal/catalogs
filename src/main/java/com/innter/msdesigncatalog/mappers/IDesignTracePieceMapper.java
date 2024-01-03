package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesignTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesignTracePieceEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignTracePieceMapper {

    DesignTracePieceResponse designTracePieceToDesignTracePieceResponse (DesignTracePieceEntity designTracePiece);

    DesignTracePieceEntity designTracePieceRequestToDesignTracePiece (DesignTracePieceRequest designTracePieceRequest);
}

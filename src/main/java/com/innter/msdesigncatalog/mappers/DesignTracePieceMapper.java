package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesignTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesignTracePieceEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignTracePieceMapper implements  IDesignTracePieceMapper {
    @Override
    public DesignTracePieceResponse designTracePieceToDesignTracePieceResponse(DesignTracePieceEntity designTracePiece) {
        DesignTracePieceResponse designTracePieceResponse = new DesignTracePieceResponse();
        designTracePieceResponse.setId(designTracePiece.getId());
        designTracePieceResponse.setName(designTracePiece.getName());
        return designTracePieceResponse;
    }

    @Override
    public DesignTracePieceEntity designTracePieceRequestToDesignTracePiece(DesignTracePieceRequest designTracePieceRequest) {
        DesignTracePieceEntity designTracePiece = new DesignTracePieceEntity();
        designTracePiece.setName(designTracePieceRequest.getName());
        return designTracePiece;
    }
}

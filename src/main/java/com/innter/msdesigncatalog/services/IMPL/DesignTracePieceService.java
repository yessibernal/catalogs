package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesignTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesignTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesignTracePieceEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignTracePieceMapper;
import com.innter.msdesigncatalog.repositories.DesignTracePieceRepository;
import com.innter.msdesigncatalog.services.IDesignTracePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignTracePieceService implements IDesignTracePieceService {

    @Autowired
    private DesignTracePieceRepository designTracePieceRepository;

    @Autowired
    private DesignTracePieceMapper designTracePieceMapper;


    @Override
    public DesignTracePieceResponse saveDesignTracePiece(DesignTracePieceRequest designTracePieceRequest) {
        try {
            DesignTracePieceEntity designTracePiece = designTracePieceMapper.designTracePieceRequestToDesignTracePiece(designTracePieceRequest);
            designTracePieceRepository.save(designTracePiece);
            return designTracePieceMapper.designTracePieceToDesignTracePieceResponse(designTracePiece);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La pieza de trazo no se pudo crear.");
        }
    }

    @Override
    public List<DesignTracePieceResponse> getDesignTracePieces(Pageable pageable) {
        try {
            List<DesignTracePieceEntity> designTracePieceEntities = designTracePieceRepository.findDesignTracePiecesStatus(pageable);;
            List<DesignTracePieceResponse> designTracePieceResponses = new ArrayList<>();
            designTracePieceEntities.stream().forEach(designTracePiece -> {
                designTracePieceResponses.add(designTracePieceMapper.designTracePieceToDesignTracePieceResponse(designTracePiece));
            });
            return designTracePieceResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las piezas de trazo no se pudieron encontrar.");
        }
    }

    @Override
    public DesignTracePieceResponse editedDesignTracePiece(DesignTracePieceRequest designTracePieceRequest, Long id) {
        DesignTracePieceEntity designTracePiece = findDesignTracePieceById(designTracePieceRepository.findById(id));
        if (designTracePiece.getStatus() == true){
            designTracePiece.setName(designTracePieceRequest.getName());
            designTracePieceRepository.save(designTracePiece);
            return designTracePieceMapper.designTracePieceToDesignTracePieceResponse(designTracePiece);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La pieza de trazo no tiene un estado activo");
    }

    @Override
    public DesignTracePieceResponse editedDesignTracePieceByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignTracePieceEntity designTracePiece = designTracePieceRepository.findDesignTracePiecesStatusById(id);
            designTracePiece.setStatus(designRequestStatus.getStatus());
            designTracePieceRepository.save(designTracePiece);
            return designTracePieceMapper.designTracePieceToDesignTracePieceResponse(designTracePiece);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La pieza de trazo no fue encontrada.");
        }
    }

    private DesignTracePieceEntity findDesignTracePieceById (Optional<DesignTracePieceEntity> optionalDesignTracePiece){
        return optionalDesignTracePiece.orElseThrow(()-> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND,"La pieza de trazo no fue encontrada."));
    }
}

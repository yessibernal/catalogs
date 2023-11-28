package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingTracePieceRequest;
import com.innter.msdesigncatalog.dtos.response.DesingTracePieceResponse;
import com.innter.msdesigncatalog.entities.DesingTracePieceEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingTracePieceMapper;
import com.innter.msdesigncatalog.repositories.DesingTracePieceRepository;
import com.innter.msdesigncatalog.services.IDesingTracePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingTracePieceService implements IDesingTracePieceService {

    @Autowired
    private DesingTracePieceRepository desingTracePieceRepository;

    @Autowired
    private DesingTracePieceMapper desingTracePieceMapper;


    @Override
    public DesingTracePieceResponse saveDesingTracePiece(DesingTracePieceRequest desingTracePieceRequest) {
        try {
            DesingTracePieceEntity desingTracePiece = desingTracePieceMapper.desingTracePieceRequestToDesingTracePiece(desingTracePieceRequest);
            desingTracePieceRepository.save(desingTracePiece);
            return desingTracePieceMapper.desingTracePieceToDesingTracePieceResponse(desingTracePiece);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La pieza de trazo no se pudo crear.");
        }
    }

    @Override
    public List<DesingTracePieceResponse> getDesingTracePieces(Pageable pageable) {
        try {
            List<DesingTracePieceEntity> desingTracePieceEntities = desingTracePieceRepository.findDesingTracePiecesStatus(pageable);;
            List<DesingTracePieceResponse> desingTracePieceResponses = new ArrayList<>();
            desingTracePieceEntities.stream().forEach(desingTracePiece -> {
                desingTracePieceResponses.add(desingTracePieceMapper.desingTracePieceToDesingTracePieceResponse(desingTracePiece));
            });
            return desingTracePieceResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las piezas de trazo no se pudieron encontrar.");
        }
    }

    @Override
    public DesingTracePieceResponse editedDesingTracePiece(DesingTracePieceRequest desingTracePieceRequest, Long id) {
        DesingTracePieceEntity desingTracePiece = findDesingTracePieceById(desingTracePieceRepository.findById(id));
        desingTracePiece.setName(desingTracePieceRequest.getName());
        desingTracePieceRepository.save(desingTracePiece);
        return desingTracePieceMapper.desingTracePieceToDesingTracePieceResponse(desingTracePiece);
    }

    @Override
    public DesingTracePieceResponse editedDesingTracePieceByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingTracePieceEntity desingTracePiece = desingTracePieceRepository.findDesingTracePiecesStatusById(id);
            desingTracePiece.setStatus(desingRequestStatus.getStatus());
            desingTracePieceRepository.save(desingTracePiece);
            return desingTracePieceMapper.desingTracePieceToDesingTracePieceResponse(desingTracePiece);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La pieza de trazo no fue encontrada.");
        }
    }

    private DesingTracePieceEntity findDesingTracePieceById (Optional<DesingTracePieceEntity> optionalDesingTracePiece){
        return optionalDesingTracePiece.orElseThrow(()-> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND,"La pieza de trazo no fue encontrada."));
    }
}

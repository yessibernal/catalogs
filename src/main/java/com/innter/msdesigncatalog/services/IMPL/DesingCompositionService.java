package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesingCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingCompositionEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingCompositionMapper;
import com.innter.msdesigncatalog.repositories.DesingCompositionRepository;
import com.innter.msdesigncatalog.services.IDesingCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingCompositionService implements IDesingCompositionService {
    @Autowired
    private DesingCompositionRepository desingCompositionRepository;
    @Autowired
    private DesingCompositionMapper desingCompositionMapper;

    @Override
    public DesingCompositionResponse saveDesingComposition(DesingCompositionRequest desingCompositionRequest) {
        try {
            DesingCompositionEntity desingCompositionEntity = desingCompositionMapper.DesingCompositionRequestToDesingComposition(desingCompositionRequest);
            desingCompositionRepository.save(desingCompositionEntity);
            return desingCompositionMapper.desingCompositionToDesingCompositionResponse(desingCompositionEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La composición no se pudo crear.");
        }
    }

    @Override
    public List<DesingCompositionResponse> getDesingCompositions(Pageable pageable) {
        try {
            List<DesingCompositionEntity>  desingCompositionEntities = desingCompositionRepository.findDesingComposition(pageable);
            List<DesingCompositionResponse> desingCompositionResponses = new ArrayList<>();
            desingCompositionEntities.stream().forEach(desingComposition -> {
                desingCompositionResponses.add(desingCompositionMapper.desingCompositionToDesingCompositionResponse(desingComposition));
            });
            return desingCompositionResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las composiciones no se pudieron encontrar.");
        }
    }

    @Override
    public DesingCompositionResponse editedDesingComposition(DesingCompositionRequest desingCompositionRequest, Long id) {
        DesingCompositionEntity desingCompositionEntity = findDesingCompositionById(desingCompositionRepository.findById(id));
        desingCompositionEntity.setName(desingCompositionRequest.getName());
        desingCompositionRepository.save(desingCompositionEntity);
        return desingCompositionMapper.desingCompositionToDesingCompositionResponse(desingCompositionEntity);
    }

    @Override
    public DesingCompositionResponse editedDesingCompositionByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingCompositionEntity desingCompositionEntity = desingCompositionRepository.findDesingCompositionsStatusById(id);
            desingCompositionEntity.setStatus(desingRequestStatus.getStatus());
            desingCompositionRepository.save(desingCompositionEntity);
            return desingCompositionMapper.desingCompositionToDesingCompositionResponse(desingCompositionEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La composición no fue encontrado.");
        }
    }

    private DesingCompositionEntity findDesingCompositionById(Optional<DesingCompositionEntity> optionalDesingComposition){
        return optionalDesingComposition.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La composición no fue encontrado."));
    }
}

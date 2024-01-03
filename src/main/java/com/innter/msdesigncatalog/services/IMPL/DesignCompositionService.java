package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesignCompositionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignCompositionMapper;
import com.innter.msdesigncatalog.repositories.DesignCompositionRepository;
import com.innter.msdesigncatalog.services.IDesignCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignCompositionService implements IDesignCompositionService {
    @Autowired
    private DesignCompositionRepository designCompositionRepository;
    @Autowired
    private DesignCompositionMapper designCompositionMapper;

    @Override
    public DesignCompositionResponse saveDesignComposition(DesignCompositionRequest designCompositionRequest) {
        try {
            DesignCompositionEntity designCompositionEntity = designCompositionMapper.DesignCompositionRequestToDesignComposition(designCompositionRequest);
            designCompositionRepository.save(designCompositionEntity);
            return designCompositionMapper.designCompositionToDesignCompositionResponse(designCompositionEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La composición no se pudo crear.");
        }
    }

    @Override
    public List<DesignCompositionResponse> getDesignCompositions(Pageable pageable) {
        try {
            List<DesignCompositionEntity>  designCompositionEntities = designCompositionRepository.findDesignComposition(pageable);
            List<DesignCompositionResponse> designCompositionResponses = new ArrayList<>();
            designCompositionEntities.stream().forEach(designComposition -> {
                designCompositionResponses.add(designCompositionMapper.designCompositionToDesignCompositionResponse(designComposition));
            });
            return designCompositionResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las composiciones no se pudieron encontrar.");
        }
    }

    @Override
    public DesignCompositionResponse editedDesignComposition(DesignCompositionRequest designCompositionRequest, Long id) {
        DesignCompositionEntity designCompositionEntity = findDesignCompositionById(designCompositionRepository.findById(id));
        if (designCompositionEntity.getStatus() == true){
            designCompositionEntity.setName(designCompositionRequest.getName());
            designCompositionRepository.save(designCompositionEntity);
            return designCompositionMapper.designCompositionToDesignCompositionResponse(designCompositionEntity);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La composición no tiene un estado activo");
    }

    @Override
    public DesignCompositionResponse editedDesignCompositionByStatus(DesignRequestStatus desingRequestStatus, Long id) {
        try {
            DesignCompositionEntity designCompositionEntity = designCompositionRepository.findDesignCompositionsStatusById(id);
            designCompositionEntity.setStatus(desingRequestStatus.getStatus());
            designCompositionRepository.save(designCompositionEntity);
            return designCompositionMapper.designCompositionToDesignCompositionResponse(designCompositionEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La composición no fue encontrado.");
        }
    }

    private DesignCompositionEntity findDesignCompositionById(Optional<DesignCompositionEntity> optionalDesignComposition){
        return optionalDesignComposition.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La composición no fue encontrado."));
    }
}

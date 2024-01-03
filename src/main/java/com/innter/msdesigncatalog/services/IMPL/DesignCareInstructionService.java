package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesignCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCareInstructionResponse;
import com.innter.msdesigncatalog.entities.DesignCareInstructionEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignCareInstructionMapper;
import com.innter.msdesigncatalog.repositories.DesignCareInstructionRepository;
import com.innter.msdesigncatalog.services.IDesignCareInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignCareInstructionService implements IDesignCareInstructionService {

    @Autowired
    private DesignCareInstructionRepository designCareInstructionRepository;
    @Autowired
    private DesignCareInstructionMapper designCareInstructionMapper;

    @Override
    public DesignCareInstructionResponse saveDesignCareInstruction(DesignCareInstructionRequest newDesingCareInstruction) {
       try {
           DesignCareInstructionEntity desingCareInstruction = designCareInstructionMapper.designCareInstructionRequestToDesignCareInstruction(newDesingCareInstruction);
           designCareInstructionRepository.save(desingCareInstruction);
           return designCareInstructionMapper.designCareInstructionToDesignCareInstructionResponse(desingCareInstruction);
       }catch (Exception e){
           throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La instrucción del cuidado no crear.");
       }
    }

    @Override
    public List<DesignCareInstructionResponse> getDesignCareInstruction(Pageable pageable) {
        try {
            List<DesignCareInstructionEntity> desingCareInstructionEntities = designCareInstructionRepository.findDesignCareInstruction(pageable);
            List<DesignCareInstructionResponse> desingCareInstructionResponses = new ArrayList<>();
            desingCareInstructionEntities.stream().forEach(desingCareInstruction -> {
                desingCareInstructionResponses.add(designCareInstructionMapper.designCareInstructionToDesignCareInstructionResponse(desingCareInstruction));
            });
            return desingCareInstructionResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las instrucciones de cuidado no se pudieron encontrar..");
        }
    }

    @Override
    public DesignCareInstructionResponse editedDesignCareInstruction(DesignCareInstructionRequest newDesingCareInstruction, Long id) {
        DesignCareInstructionEntity designCareInstruction = findDesignCareInstructionById(designCareInstructionRepository.findById(id));
        if (designCareInstruction.getStatus() == true){
            designCareInstruction.setName(newDesingCareInstruction.getName());
            designCareInstruction.setIcon(newDesingCareInstruction.getIcon());
            designCareInstructionRepository.save(designCareInstruction);
            return designCareInstructionMapper.designCareInstructionToDesignCareInstructionResponse(designCareInstruction);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La instrucción del cuidado no tiene un estado activo");
    }

    @Override
    public DesignCareInstructionResponse editedDesignCareInstructionByStatus(DesignRequestStatus desingRequestStatus, Long id) {
        try {
            DesignCareInstructionEntity designCareInstruction = designCareInstructionRepository.findDesignCareInstructionStatusById(id);
            designCareInstruction.setStatus(desingRequestStatus.getStatus());
            designCareInstructionRepository.save(designCareInstruction);
            return designCareInstructionMapper.designCareInstructionToDesignCareInstructionResponse(designCareInstruction);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La instrucción del cuidado no fue encontrada.");
        }
    }


    private DesignCareInstructionEntity findDesignCareInstructionById(Optional<DesignCareInstructionEntity> optionalDesignCareInstruction){
        return optionalDesignCareInstruction.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La instrucción del cuidado no fue encontrada."));
    }
}

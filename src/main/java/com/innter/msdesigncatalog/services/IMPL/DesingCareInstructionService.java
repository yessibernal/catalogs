package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesingCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCareInstructionResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingCareInstructionEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingCareInstructionMapper;
import com.innter.msdesigncatalog.repositories.DesingCareInstructionRepository;
import com.innter.msdesigncatalog.services.IDesingCareInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingCareInstructionService implements IDesingCareInstructionService {

    @Autowired
    private DesingCareInstructionRepository desingCareInstructionRepository;
    @Autowired
    private DesingCareInstructionMapper desingCareInstructionMapper;

    @Override
    public DesingCareInstructionResponse saveDesingCareInstruction(DesingCareInstructionRequest newDesingCareInstruction) {
       try {
           DesingCareInstructionEntity desingCareInstruction = desingCareInstructionMapper.desingCareInstructionRequestToDesingCareInstruction(newDesingCareInstruction);
           desingCareInstructionRepository.save(desingCareInstruction);
           return desingCareInstructionMapper.desingCareInstructionToDesingCareInstructionResponse(desingCareInstruction);
       }catch (Exception e){
           throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La instrucción del cuidado no crear.");
       }
    }

    @Override
    public List<DesingCareInstructionResponse> getDesingCareInstruction(Pageable pageable) {
        try {
            List<DesingCareInstructionEntity> desingCareInstructionEntities = desingCareInstructionRepository.findDesingCareInstruction(pageable);
            List<DesingCareInstructionResponse> desingCareInstructionResponses = new ArrayList<>();
            desingCareInstructionEntities.stream().forEach(desingCareInstruction -> {
                desingCareInstructionResponses.add(desingCareInstructionMapper.desingCareInstructionToDesingCareInstructionResponse(desingCareInstruction));
            });
            return desingCareInstructionResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las instrucciones de cuidado no se pudieron encontrar..");
        }
    }

    @Override
    public DesingCareInstructionResponse editedDesingCareInstruction(DesingCareInstructionRequest newDesingCareInstruction, Long id) {
        DesingCareInstructionEntity desingCareInstruction = findDesingCareInstructionById(desingCareInstructionRepository.findById(id));
        desingCareInstruction.setName(newDesingCareInstruction.getName());
        desingCareInstruction.setIcon(newDesingCareInstruction.getIcon());
        desingCareInstructionRepository.save(desingCareInstruction);
        return desingCareInstructionMapper.desingCareInstructionToDesingCareInstructionResponse(desingCareInstruction);
    }

    @Override
    public DesingCareInstructionResponse editedDesingCareInstructionByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingCareInstructionEntity desingCareInstruction = desingCareInstructionRepository.findDesingCareInstructionStatusById(id);
            desingCareInstruction.setStatus(desingRequestStatus.getStatus());
            desingCareInstructionRepository.save(desingCareInstruction);
            return desingCareInstructionMapper.desingCareInstructionToDesingCareInstructionResponse(desingCareInstruction);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El color no fue encontrada.");
        }
    }

    private DesingCareInstructionEntity findDesingCareInstructionById(Optional<DesingCareInstructionEntity> optionalDesingCareInstruction){
        return optionalDesingCareInstruction.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La instrucción del cuidado no fue encontrada."));
    }
}

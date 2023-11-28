package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCareInstructionResponse;
import com.innter.msdesigncatalog.entities.DesingCareInstructionEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingCareInstructionMapper implements IDesingCareInstructionMapper{
    @Override
    public DesingCareInstructionResponse desingCareInstructionToDesingCareInstructionResponse (DesingCareInstructionEntity desingCareInstruction) {
        DesingCareInstructionResponse desingCareInstructionResponse = new DesingCareInstructionResponse();
        desingCareInstructionResponse.setId(desingCareInstruction.getId());
        desingCareInstructionResponse.setName(desingCareInstruction.getName());
        desingCareInstructionResponse.setIcon(desingCareInstruction.getIcon());
        return desingCareInstructionResponse;
    }

    @Override
    public DesingCareInstructionEntity desingCareInstructionRequestToDesingCareInstruction (DesingCareInstructionRequest desingCareInstructionRequest) {
        DesingCareInstructionEntity desingCareInstruction = new DesingCareInstructionEntity();
        desingCareInstruction.setName(desingCareInstructionRequest.getName());
        desingCareInstruction.setIcon(desingCareInstructionRequest.getIcon());
        return desingCareInstruction;
    }
}

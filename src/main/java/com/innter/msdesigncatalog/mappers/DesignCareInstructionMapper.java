package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCareInstructionResponse;
import com.innter.msdesigncatalog.entities.DesignCareInstructionEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignCareInstructionMapper implements IDesignCareInstructionMapper{
    @Override
    public DesignCareInstructionResponse designCareInstructionToDesignCareInstructionResponse(DesignCareInstructionEntity designCareInstruction) {
        DesignCareInstructionResponse designCareInstructionResponse = new DesignCareInstructionResponse();
        designCareInstructionResponse.setId(designCareInstruction.getId());
        designCareInstructionResponse.setName(designCareInstruction.getName());
        designCareInstructionResponse.setIcon(designCareInstruction.getIcon());
        return designCareInstructionResponse;
    }

    @Override
    public DesignCareInstructionEntity designCareInstructionRequestToDesignCareInstruction(DesignCareInstructionRequest designCareInstructionRequest) {
        DesignCareInstructionEntity designCareInstruction = new DesignCareInstructionEntity();
        designCareInstruction.setName(designCareInstructionRequest.getName());
        designCareInstruction.setIcon(designCareInstructionRequest.getIcon());
        return designCareInstruction;
    }
}

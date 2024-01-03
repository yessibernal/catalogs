package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCareInstructionResponse;
import com.innter.msdesigncatalog.entities.DesignCareInstructionEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignCareInstructionMapper {

    DesignCareInstructionResponse designCareInstructionToDesignCareInstructionResponse (DesignCareInstructionEntity designCareInstruction);

    DesignCareInstructionEntity designCareInstructionRequestToDesignCareInstruction (DesignCareInstructionRequest designCareInstructionRequest);
}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCareInstructionResponse;
import com.innter.msdesigncatalog.entities.DesingCareInstructionEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingCareInstructionMapper {

    DesingCareInstructionResponse desingCareInstructionToDesingCareInstructionResponse (DesingCareInstructionEntity desingCareInstruction);

    DesingCareInstructionEntity desingCareInstructionRequestToDesingCareInstruction (DesingCareInstructionRequest desingCareInstructionRequest);
}

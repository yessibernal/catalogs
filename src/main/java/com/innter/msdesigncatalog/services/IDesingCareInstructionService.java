package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCareInstructionResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingCareInstructionService {

    DesingCareInstructionResponse saveDesingCareInstruction (DesingCareInstructionRequest newDesingCareInstruction);

    List<DesingCareInstructionResponse> getDesingCareInstruction (Pageable pageable);

    DesingCareInstructionResponse editedDesingCareInstruction (DesingCareInstructionRequest newDesingCareInstruction, Long id);

    DesingCareInstructionResponse editedDesingCareInstructionByStatus (DesingRequestStatus desingRequestStatus, Long id);
}

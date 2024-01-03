package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCareInstructionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignCareInstructionService {

    DesignCareInstructionResponse saveDesignCareInstruction (DesignCareInstructionRequest newDesingCareInstruction);

    List<DesignCareInstructionResponse> getDesignCareInstruction (Pageable pageable);

    DesignCareInstructionResponse editedDesignCareInstruction (DesignCareInstructionRequest newDesingCareInstruction, Long id);

    DesignCareInstructionResponse editedDesignCareInstructionByStatus (DesignRequestStatus desingRequestStatus, Long id);
}

package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignCompositionService {

    DesignCompositionResponse saveDesignComposition (DesignCompositionRequest designCompositionRequest);

    List<DesignCompositionResponse> getDesignCompositions (Pageable pageable);

    DesignCompositionResponse editedDesignComposition (DesignCompositionRequest designCompositionRequest, Long id);

    DesignCompositionResponse editedDesignCompositionByStatus (DesignRequestStatus designRequestStatus, Long id);
}

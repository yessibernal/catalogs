package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingCompositionService {

    DesingCompositionResponse saveDesingComposition (DesingCompositionRequest desingCompositionRequest);

    List<DesingCompositionResponse> getDesingCompositions (Pageable pageable);

    DesingCompositionResponse editedDesingComposition (DesingCompositionRequest desingCompositionRequest, Long id);

    DesingCompositionResponse editedDesingCompositionByStatus (DesingRequestStatus desingRequestStatus, Long id);
}

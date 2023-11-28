package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMarkerResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingMarkerService {

    DesingMarkerResponse saveDesingMarker (DesingMarkerRequest desingMarkerRequest);

    List<DesingMarkerResponse> getDesingMarkers (Pageable pageable);

    DesingMarkerResponse editedDesingMarker (DesingMarkerRequest desingMarkerRequest, Long id);

    DesingMarkerResponse editedDesingMarkerByStatus (DesingRequestStatus desingRequestStatus, Long id);
}

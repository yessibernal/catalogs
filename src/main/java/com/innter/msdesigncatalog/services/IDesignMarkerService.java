package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignMarkerRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignMarkerResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignMarkerService {

    DesignMarkerResponse saveDesignMarker (DesignMarkerRequest designMarkerRequest);

    List<DesignMarkerResponse> getDesignMarkers (Pageable pageable);

    DesignMarkerResponse editedDesignMarker (DesignMarkerRequest designMarkerRequest, Long id);

    DesignMarkerResponse editedDesignMarkerByStatus (DesignRequestStatus designRequestStatus, Long id);
}

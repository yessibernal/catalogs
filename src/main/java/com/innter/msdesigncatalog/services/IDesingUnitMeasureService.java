package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesingUnitMeasureResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingUnitMeasureService {

    DesingUnitMeasureResponse getDesingUnitMeasure (Long id);

    DesingUnitMeasureResponse saveDesingUnitMeasure (DesingUnitMeasureRequest newDesingUnitMeasure);

    DesingUnitMeasureResponse editedDesingUnitMeasure(DesingUnitMeasureRequest newDesingUnitMeasure,Long id);

    DesingUnitMeasureResponse editedDesingUnitMeasureByStatus (DesingRequestStatus desingRequestStatus, Long id);

    List<DesingUnitMeasureResponse> getDesingUnitsMeasure (Pageable pageable);
}

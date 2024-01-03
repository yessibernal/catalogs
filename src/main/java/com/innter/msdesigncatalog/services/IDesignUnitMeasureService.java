package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesignUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesignUnitMeasureResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignUnitMeasureService {

    DesignUnitMeasureResponse getDesignUnitMeasure (Long id);

    DesignUnitMeasureResponse saveDesignUnitMeasure (DesignUnitMeasureRequest newDesignUnitMeasure);

    DesignUnitMeasureResponse editedDesignUnitMeasure(DesignUnitMeasureRequest newDesignUnitMeasure,Long id);

    DesignUnitMeasureResponse editedDesignUnitMeasureByStatus (DesignRequestStatus designRequestStatus, Long id);

    List<DesignUnitMeasureResponse> getDesignUnitsMeasure (Pageable pageable);
}

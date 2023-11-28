package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMaterialTypeResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingMaterialTypeService {

    DesingMaterialTypeResponse saveDesingMaterialTypeResponse (DesingMaterialTypeRequest desingMaterialTypeRequest);

    List<DesingMaterialTypeResponse> getDesingMaterialsType (Pageable pageable);

    DesingMaterialTypeResponse editedDesingMaterialType (DesingMaterialTypeRequest desingMaterialTypeRequest, Long id);

    DesingMaterialTypeResponse editedDesingMaterialTypeByStatus (DesingRequestStatus desingRequestStatus, Long id);
}

package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignMaterialTypeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignMaterialTypeService {

    DesignMaterialTypeResponse saveDesignMaterialTypeResponse (DesignMaterialTypeRequest designMaterialTypeRequest);

    List<DesignMaterialTypeResponse> getDesignMaterialsType (Pageable pageable);

    DesignMaterialTypeResponse editedDesignMaterialType (DesignMaterialTypeRequest designMaterialTypeRequest, Long id);

    DesignMaterialTypeResponse editedDesignMaterialTypeByStatus (DesignRequestStatus designRequestStatus, Long id);
}

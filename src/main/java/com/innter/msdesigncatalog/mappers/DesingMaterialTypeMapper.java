package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMaterialTypeResponse;
import com.innter.msdesigncatalog.entities.DesingMaterialTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingMaterialTypeMapper implements IDesingMaterialTypeMapper{
    @Override
    public DesingMaterialTypeResponse desingMaterialTypeToDesingMaterialTypeResponse(DesingMaterialTypeEntity desingMaterialType) {
        DesingMaterialTypeResponse desingMaterialTypeResponse = new DesingMaterialTypeResponse();
        desingMaterialTypeResponse.setId(desingMaterialType.getId());
        desingMaterialTypeResponse.setName(desingMaterialType.getName());
        desingMaterialTypeResponse.setCode(desingMaterialType.getCode());
        desingMaterialTypeResponse.setType(desingMaterialType.getType());
        desingMaterialTypeResponse.setClassification(desingMaterialType.getClassification());
        return desingMaterialTypeResponse;
    }

    @Override
    public DesingMaterialTypeEntity desingMaterialTypeRequestToDesingMaterialType(DesingMaterialTypeRequest desingMaterialTypeRequest) {
        DesingMaterialTypeEntity  desingMaterialType = new DesingMaterialTypeEntity();
        desingMaterialType.setName(desingMaterialTypeRequest.getName());
        desingMaterialType.setCode(desingMaterialTypeRequest.getCode());
        desingMaterialType.setType(desingMaterialTypeRequest.getType());
        desingMaterialType.setClassification(desingMaterialTypeRequest.getClassification());
        return desingMaterialType;
    }
}

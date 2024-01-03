package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesignMaterialTypeResponse;
import com.innter.msdesigncatalog.entities.DesignMaterialTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignMaterialTypeMapper implements IDesignMaterialTypeMapper {
    @Override
    public DesignMaterialTypeResponse designMaterialTypeToDesignMaterialTypeResponse(DesignMaterialTypeEntity designMaterialType) {
        DesignMaterialTypeResponse designMaterialTypeResponse = new DesignMaterialTypeResponse();
        designMaterialTypeResponse.setId(designMaterialType.getId());
        designMaterialTypeResponse.setName(designMaterialType.getName());
        designMaterialTypeResponse.setCode(designMaterialType.getCode());
        designMaterialTypeResponse.setType(designMaterialType.getType());
        designMaterialTypeResponse.setClassification(designMaterialType.getClassification());
        return designMaterialTypeResponse;
    }

    @Override
    public DesignMaterialTypeEntity designMaterialTypeRequestToDesignMaterialType(DesignMaterialTypeRequest designMaterialTypeRequest) {
        DesignMaterialTypeEntity  designMaterialType = new DesignMaterialTypeEntity();
        designMaterialType.setName(designMaterialTypeRequest.getName());
        designMaterialType.setCode(designMaterialTypeRequest.getCode());
        designMaterialType.setType(designMaterialTypeRequest.getType());
        designMaterialType.setClassification(designMaterialTypeRequest.getClassification());
        return designMaterialType;
    }
}

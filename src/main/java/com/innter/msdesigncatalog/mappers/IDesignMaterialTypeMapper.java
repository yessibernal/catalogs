package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesignMaterialTypeResponse;
import com.innter.msdesigncatalog.entities.DesignMaterialTypeEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignMaterialTypeMapper {

    DesignMaterialTypeResponse designMaterialTypeToDesignMaterialTypeResponse (DesignMaterialTypeEntity designMaterialType);

    DesignMaterialTypeEntity designMaterialTypeRequestToDesignMaterialType (DesignMaterialTypeRequest designMaterialTypeRequest);
}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMaterialTypeResponse;
import com.innter.msdesigncatalog.entities.DesingMaterialTypeEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingMaterialTypeMapper {

    DesingMaterialTypeResponse desingMaterialTypeToDesingMaterialTypeResponse (DesingMaterialTypeEntity desingMaterialType);

    DesingMaterialTypeEntity desingMaterialTypeRequestToDesingMaterialType (DesingMaterialTypeRequest desingMaterialTypeRequest);
}

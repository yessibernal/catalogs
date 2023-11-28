package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesingUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesingUnitMeasureEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingUnitMeasureMapper {

    DesingUnitMeasureResponse desingUnitMeasureToDesingUnitMeasureResponse (DesingUnitMeasureEntity desingUnitMeasure);

    DesingUnitMeasureEntity desingUnitMeasureRequestToDesingUnitMeasure (DesingUnitMeasureRequest desingUnitMeasureRequest);
}

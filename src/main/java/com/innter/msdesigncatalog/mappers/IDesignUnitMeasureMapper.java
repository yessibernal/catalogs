package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesignUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesignUnitMeasureEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignUnitMeasureMapper {

    DesignUnitMeasureResponse designUnitMeasureToDesignUnitMeasureResponse (DesignUnitMeasureEntity designUnitMeasure);

    DesignUnitMeasureEntity designUnitMeasureRequestToDesignUnitMeasure (DesignUnitMeasureRequest designUnitMeasureRequest);
}

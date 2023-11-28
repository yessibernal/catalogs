package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesingUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesingUnitMeasureEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingUnitMeasureMapper implements IDesingUnitMeasureMapper{
    @Override
    public DesingUnitMeasureResponse desingUnitMeasureToDesingUnitMeasureResponse(DesingUnitMeasureEntity desingUnitMeasure) {
        DesingUnitMeasureResponse desingUnitMeasureResponse = new DesingUnitMeasureResponse();
        desingUnitMeasureResponse.setId(desingUnitMeasure.getId());
        desingUnitMeasureResponse.setName(desingUnitMeasure.getName());
        desingUnitMeasureResponse.setAbbreviation(desingUnitMeasure.getAbbreviation());
        return desingUnitMeasureResponse;
    }

    @Override
    public DesingUnitMeasureEntity desingUnitMeasureRequestToDesingUnitMeasure(DesingUnitMeasureRequest desingUnitMeasure) {
        DesingUnitMeasureEntity desingUnitMeasureEntity = new DesingUnitMeasureEntity();
        desingUnitMeasureEntity.setName(desingUnitMeasure.getName());
        desingUnitMeasureEntity.setAbbreviation(desingUnitMeasure.getAbbreviation());
        return desingUnitMeasureEntity;
    }
}

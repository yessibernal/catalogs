package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesignUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesignUnitMeasureEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignUnitMeasureMapper implements IDesignUnitMeasureMapper{
    @Override
    public DesignUnitMeasureResponse designUnitMeasureToDesignUnitMeasureResponse(DesignUnitMeasureEntity designUnitMeasure) {
        DesignUnitMeasureResponse designUnitMeasureResponse = new DesignUnitMeasureResponse();
        designUnitMeasureResponse.setId(designUnitMeasure.getId());
        designUnitMeasureResponse.setName(designUnitMeasure.getName());
        designUnitMeasureResponse.setAbbreviation(designUnitMeasure.getAbbreviation());
        return designUnitMeasureResponse;
    }

    @Override
    public DesignUnitMeasureEntity designUnitMeasureRequestToDesignUnitMeasure(DesignUnitMeasureRequest designUnitMeasure) {
        DesignUnitMeasureEntity designUnitMeasureEntity = new DesignUnitMeasureEntity();
        designUnitMeasureEntity.setName(designUnitMeasure.getName());
        designUnitMeasureEntity.setAbbreviation(designUnitMeasure.getAbbreviation());
        return designUnitMeasureEntity;
    }
}

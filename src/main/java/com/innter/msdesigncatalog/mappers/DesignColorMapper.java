package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignColorRequest;
import com.innter.msdesigncatalog.dtos.response.DesignColorResponse;
import com.innter.msdesigncatalog.entities.DesignColorEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignColorMapper implements IDesignColorMapper{
    @Override
    public DesignColorResponse designColorToDesignColorResponse(DesignColorEntity designColor) {
        DesignColorResponse designColorResponse = new DesignColorResponse();
        designColorResponse.setId(designColor.getId());
        designColorResponse.setName(designColor.getName());
        designColorResponse.setColor(designColor.getColor());
        designColorResponse.setProvider(designColor.getProvider());
        return designColorResponse;
    }

    @Override
    public DesignColorEntity designColorRequestToDesignColor(DesignColorRequest designColorRequest) {
        DesignColorEntity designColor = new DesignColorEntity();
        designColor.setName(designColorRequest.getName());
        designColor.setColor(designColorRequest.getColor());
        designColor.setProvider(designColorRequest.getProvider());
        return designColor;
    }
}

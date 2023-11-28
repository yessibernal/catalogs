package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingColorRequest;
import com.innter.msdesigncatalog.dtos.response.DesingColorResponse;
import com.innter.msdesigncatalog.entities.DesingColorEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingColorMapper implements IDesingColorMapper{
    @Override
    public DesingColorResponse desingColorToDesingColorResponse(DesingColorEntity desingColor) {
        DesingColorResponse desingColorResponse = new DesingColorResponse();
        desingColorResponse.setId(desingColor.getId());
        desingColorResponse.setName(desingColor.getName());
        desingColorResponse.setColor(desingColor.getColor());
        desingColorResponse.setProvider(desingColor.getProvider());
        return desingColorResponse;
    }

    @Override
    public DesingColorEntity desingColorRequestToDesingColor(DesingColorRequest desingColorRequest) {
        DesingColorEntity desingColor = new DesingColorEntity();
        desingColor.setName(desingColorRequest.getName());
        desingColor.setColor(desingColorRequest.getColor());
        desingColor.setProvider(desingColorRequest.getProvider());
        return desingColor;
    }
}

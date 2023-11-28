package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionResponse;
import com.innter.msdesigncatalog.entities.DesingCompositionEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingCompositionMapper implements IDesingCompositionMapper{
    @Override
    public DesingCompositionResponse desingCompositionToDesingCompositionResponse(DesingCompositionEntity desingCompositionEntity) {
        DesingCompositionResponse desingCompositionResponse = new DesingCompositionResponse();
        desingCompositionResponse.setId(desingCompositionEntity.getId());
        desingCompositionResponse.setName(desingCompositionEntity.getName());
        return desingCompositionResponse;
    }

    @Override
    public DesingCompositionEntity DesingCompositionRequestToDesingComposition(DesingCompositionRequest desingCompositionRequest) {
        DesingCompositionEntity desingCompositionEntity = new DesingCompositionEntity();
        desingCompositionEntity.setName(desingCompositionRequest.getName());
        return desingCompositionEntity;
    }
}

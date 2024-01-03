package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignCompositionMapper implements IDesignCompositionMapper {
    @Override
    public DesignCompositionResponse designCompositionToDesignCompositionResponse(DesignCompositionEntity designCompositionEntity) {
        DesignCompositionResponse designCompositionResponse = new DesignCompositionResponse();
        designCompositionResponse.setId(designCompositionEntity.getId());
        designCompositionResponse.setName(designCompositionEntity.getName());
        return designCompositionResponse;
    }

    @Override
    public DesignCompositionEntity DesignCompositionRequestToDesignComposition(DesignCompositionRequest designCompositionRequest) {
        DesignCompositionEntity designCompositionEntity = new DesignCompositionEntity();
        designCompositionEntity.setName(designCompositionRequest.getName());
        return designCompositionEntity;
    }
}

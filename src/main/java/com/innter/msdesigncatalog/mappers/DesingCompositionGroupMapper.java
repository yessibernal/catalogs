package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingCompositionGroupMapper implements IDesingCompositionGroupMapper{
    @Override
    public DesingCompositionGroupResponse desingCompositionGroupToDesingCompositionGroupResponse(DesingCompositionGroupEntity desingCompositionGroupEntity) {
        DesingCompositionGroupResponse desingCompositionGroupResponse = new DesingCompositionGroupResponse();
        desingCompositionGroupResponse.setId(desingCompositionGroupEntity.getId());
        desingCompositionGroupResponse.setName(desingCompositionGroupEntity.getName());
        return desingCompositionGroupResponse;
    }

    @Override
    public DesingCompositionGroupEntity desingCompositionGroupRequestToDesingCompositionGroup(DesingCompositionGroupRequest desingCompositionGroupRequest) {
        DesingCompositionGroupEntity desingCompositionGroupEntity = new DesingCompositionGroupEntity();
        desingCompositionGroupEntity.setName(desingCompositionGroupRequest.getName());
        return desingCompositionGroupEntity;
    }
}

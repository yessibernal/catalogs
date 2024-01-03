package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignCompositionGroupMapper implements IDesignCompositionGroupMapper{
    @Override
    public DesignCompositionGroupResponse designCompositionGroupToDesignCompositionGroupResponse(DesignCompositionGroupEntity designCompositionGroupEntity) {
        DesignCompositionGroupResponse designCompositionGroupResponse = new DesignCompositionGroupResponse();
        designCompositionGroupResponse.setId(designCompositionGroupEntity.getId());
        designCompositionGroupResponse.setName(designCompositionGroupEntity.getName());
        return designCompositionGroupResponse;
    }

    @Override
    public DesignCompositionGroupEntity designCompositionGroupRequestToDesignCompositionGroup(DesignCompositionGroupRequest designCompositionGroupRequest) {
        DesignCompositionGroupEntity designCompositionGroupEntity = new DesignCompositionGroupEntity();
        designCompositionGroupEntity.setName(designCompositionGroupRequest.getName());
        return designCompositionGroupEntity;
    }
}

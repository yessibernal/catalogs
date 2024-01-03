package com.innter.msdesigncatalog.mappers;


import com.innter.msdesigncatalog.dtos.request.DesignGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignGarmentGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGarmentGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignGarmentGroupMapper implements IDesignGarmentGroupMapper{
    @Override
    public DesignGarmentGroupResponse designGarmentGroupToDesignGarmentGroupResponse(DesignGarmentGroupEntity designGarmentGroup) {
        DesignGarmentGroupResponse designGarmentGroupResponse = new DesignGarmentGroupResponse();
        designGarmentGroupResponse.setId(designGarmentGroup.getId());
        designGarmentGroupResponse.setName(designGarmentGroup.getName());
        designGarmentGroupResponse.setCode(designGarmentGroup.getCode());
        designGarmentGroupResponse.setGarmentLocation(designGarmentGroup.getGarmentLocation());
        return designGarmentGroupResponse;
    }

    @Override
    public DesignGarmentGroupEntity designGarmentGroupRequestToDesignGarmentGroup(DesignGarmentGroupRequest designGarmentGroupRequest) {
        DesignGarmentGroupEntity designGarmentGroup = new DesignGarmentGroupEntity();
        designGarmentGroup.setName(designGarmentGroupRequest.getName());
        designGarmentGroup.setCode(designGarmentGroupRequest.getCode());
        designGarmentGroup.setGarmentLocation(designGarmentGroupRequest.getGarmentLocation());
        return designGarmentGroup;
    }
}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGarmentGroupResponse;
import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingGarmentGroupMapper implements IDesingGarmentGroupMapper{
    @Override
    public DesingGarmentGroupResponse desingGarmentGroupToDesingGarmentGroupResponse(DesingGarmentGroupEntity desingGarmentGroup) {
        DesingGarmentGroupResponse desingGarmentGroupResponse = new DesingGarmentGroupResponse();
        desingGarmentGroupResponse.setId(desingGarmentGroup.getId());
        desingGarmentGroupResponse.setName(desingGarmentGroup.getName());
        desingGarmentGroupResponse.setCode(desingGarmentGroup.getCode());
        desingGarmentGroupResponse.setGarmentLocation(desingGarmentGroup.getGarmentLocation());
        return desingGarmentGroupResponse;
    }

    @Override
    public DesingGarmentGroupEntity desingGarmentGroupRequestToDesingGarmentGroup(DesingGarmentGroupRequest desingGarmentGroupRequest) {
        DesingGarmentGroupEntity desingGarmentGroup = new DesingGarmentGroupEntity();
        desingGarmentGroup.setName(desingGarmentGroupRequest.getName());
        desingGarmentGroup.setCode(desingGarmentGroupRequest.getCode());
        desingGarmentGroup.setGarmentLocation(desingGarmentGroupRequest.getGarmentLocation());
        return desingGarmentGroup;
    }
}

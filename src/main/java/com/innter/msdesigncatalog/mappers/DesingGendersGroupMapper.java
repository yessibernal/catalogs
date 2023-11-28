package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGenderGroupResponse;
import com.innter.msdesigncatalog.entities.DesingGendersGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingGendersGroupMapper implements IDesingGenderGroupMapper{
    @Override
    public DesingGenderGroupResponse desingGenderGroupToDesingGenderGroupResponse(DesingGendersGroupEntity desingGendersGroup) {
        DesingGenderGroupResponse desingGenderGroupResponse = new DesingGenderGroupResponse();
        desingGenderGroupResponse.setId(desingGendersGroup.getId());
        desingGenderGroupResponse.setName(desingGendersGroup.getName());
        return desingGenderGroupResponse;
    }

    @Override
    public DesingGendersGroupEntity desingGenderGroupRequestToDesingGenderGroup(DesingGenderGroupRequest desingGenderGroupRequest) {
        DesingGendersGroupEntity gendersGroupEntity = new DesingGendersGroupEntity();
        gendersGroupEntity.setName(desingGenderGroupRequest.getName());
        return gendersGroupEntity;
    }
}

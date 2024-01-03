package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignGenderGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGendersGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignGendersGroupMapper implements IDesignGenderGroupMapper{
    @Override
    public DesignGenderGroupResponse designGenderGroupToDesignGenderGroupResponse(DesignGendersGroupEntity designGendersGroup) {
        DesignGenderGroupResponse designGenderGroupResponse = new DesignGenderGroupResponse();
        designGenderGroupResponse.setId(designGendersGroup.getId());
        designGenderGroupResponse.setName(designGendersGroup.getName());
        return designGenderGroupResponse;
    }

    @Override
    public DesignGendersGroupEntity designGenderGroupRequestToDesignGenderGroup(DesignGenderGroupRequest designGenderGroupRequest) {
        DesignGendersGroupEntity gendersGroupEntity = new DesignGendersGroupEntity();
        gendersGroupEntity.setName(designGenderGroupRequest.getName());
        return gendersGroupEntity;
    }
}

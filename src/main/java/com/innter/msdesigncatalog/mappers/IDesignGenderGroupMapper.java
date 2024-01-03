package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignGenderGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGendersGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignGenderGroupMapper {

    DesignGenderGroupResponse designGenderGroupToDesignGenderGroupResponse (DesignGendersGroupEntity designGendersGroup);

    DesignGendersGroupEntity designGenderGroupRequestToDesignGenderGroup (DesignGenderGroupRequest designGenderGroupRequest);
}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGenderGroupResponse;
import com.innter.msdesigncatalog.entities.DesingGendersGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingGenderGroupMapper {

    DesingGenderGroupResponse desingGenderGroupToDesingGenderGroupResponse (DesingGendersGroupEntity desingGendersGroup);

    DesingGendersGroupEntity desingGenderGroupRequestToDesingGenderGroup (DesingGenderGroupRequest desingGenderGroupRequest);
}

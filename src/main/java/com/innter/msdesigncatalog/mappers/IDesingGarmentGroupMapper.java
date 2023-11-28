package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGarmentGroupResponse;
import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingGarmentGroupMapper {
    DesingGarmentGroupResponse desingGarmentGroupToDesingGarmentGroupResponse (DesingGarmentGroupEntity desingGarmentGroup);

    DesingGarmentGroupEntity desingGarmentGroupRequestToDesingGarmentGroup (DesingGarmentGroupRequest desingGarmentGroupRequest);
}

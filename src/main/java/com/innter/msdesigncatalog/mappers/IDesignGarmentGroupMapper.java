package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignGarmentGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGarmentGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignGarmentGroupMapper {
    DesignGarmentGroupResponse designGarmentGroupToDesignGarmentGroupResponse (DesignGarmentGroupEntity designGarmentGroup);

    DesignGarmentGroupEntity designGarmentGroupRequestToDesignGarmentGroup (DesignGarmentGroupRequest designGarmentGroupRequest);
}

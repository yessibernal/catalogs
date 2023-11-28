package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingCompositionGroupMapper {

    DesingCompositionGroupResponse desingCompositionGroupToDesingCompositionGroupResponse (DesingCompositionGroupEntity desingCompositionGroupEntity);

    DesingCompositionGroupEntity desingCompositionGroupRequestToDesingCompositionGroup (DesingCompositionGroupRequest desingCompositionGroupRequest);
}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignCompositionGroupMapper {

    DesignCompositionGroupResponse designCompositionGroupToDesignCompositionGroupResponse (DesignCompositionGroupEntity designCompositionGroupEntity);

    DesignCompositionGroupEntity designCompositionGroupRequestToDesignCompositionGroup (DesignCompositionGroupRequest designCompositionGroupRequest);
}

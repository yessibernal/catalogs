package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.response.DesignPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignPriceCompositionGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignPriceCompositionGroupMapper {
    DesignPriceCompositionGroupResponse designPriceCompositionGroupToDesignPriceCompositionGroupResponse(DesignPriceCompositionGroupEntity designPriceCompositionGroup);


}
package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.response.DesingPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesingPriceCompositionGroupEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingPriceCompositionGroupMapper {
    DesingPriceCompositionGroupResponse desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(DesingPriceCompositionGroupEntity desingPriceCompositionGroup);


}
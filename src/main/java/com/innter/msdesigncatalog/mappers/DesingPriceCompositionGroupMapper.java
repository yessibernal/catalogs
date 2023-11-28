package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.response.DesingPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesingPriceCompositionGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingPriceCompositionGroupMapper implements IDesingPriceCompositionGroupMapper{

    @Override
    public DesingPriceCompositionGroupResponse desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(DesingPriceCompositionGroupEntity desingPriceCompositionGroup) {
        DesingPriceCompositionGroupResponse desingPriceCompositionGroupResponse = new DesingPriceCompositionGroupResponse();
        desingPriceCompositionGroupResponse.setId(desingPriceCompositionGroup.getId());
        desingPriceCompositionGroupResponse.setGarmentsGroupId(desingPriceCompositionGroup.getGarmentsGroup().getId());
        desingPriceCompositionGroupResponse.setGarmentsGroupName(desingPriceCompositionGroup.getGarmentsGroup().getName());
        desingPriceCompositionGroupResponse.setCompositionGroupId(desingPriceCompositionGroup.getCompositionGroup().getId());
        desingPriceCompositionGroupResponse.setCompositionGroupName(desingPriceCompositionGroup.getCompositionGroup().getName());
        desingPriceCompositionGroupResponse.setPrice(desingPriceCompositionGroup.getPrice());
        return desingPriceCompositionGroupResponse;
    }

}

package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.response.DesignPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignPriceCompositionGroupEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignPriceCompositionGroupMapper implements IDesignPriceCompositionGroupMapper{

    @Override
    public DesignPriceCompositionGroupResponse designPriceCompositionGroupToDesignPriceCompositionGroupResponse(DesignPriceCompositionGroupEntity designPriceCompositionGroup) {
        DesignPriceCompositionGroupResponse designPriceCompositionGroupResponse = new DesignPriceCompositionGroupResponse();
        designPriceCompositionGroupResponse.setId(designPriceCompositionGroup.getId());
        designPriceCompositionGroupResponse.setGarmentsGroupId(designPriceCompositionGroup.getGarmentsGroup().getId());
        designPriceCompositionGroupResponse.setGarmentsGroupName(designPriceCompositionGroup.getGarmentsGroup().getName());
        designPriceCompositionGroupResponse.setCompositionGroupId(designPriceCompositionGroup.getCompositionGroup().getId());
        designPriceCompositionGroupResponse.setCompositionGroupName(designPriceCompositionGroup.getCompositionGroup().getName());
        designPriceCompositionGroupResponse.setPrice(designPriceCompositionGroup.getPrice());
        return designPriceCompositionGroupResponse;
    }

}

package com.innter.msdesigncatalog.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignPriceCompositionGroupResponse {

    @JsonProperty("priceCompositionGroupId")
    private Long id;

    @JsonProperty("garmentsGroupId")
    private Long garmentsGroupId;

    @JsonProperty("garmentsGroupName")
    private String garmentsGroupName;

    @JsonProperty("compositionGroupId")
    private Long compositionGroupId;

    @JsonProperty("compositionGroupName")
    private String compositionGroupName;

    @JsonProperty("price")
    private BigDecimal price;
}

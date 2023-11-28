package com.innter.msdesigncatalog.dtos.request;

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
public class DesingPriceCompositionGroupRequest {

    @JsonProperty("garmentsGroupId")
    private Long garmentsGroupId;

    @JsonProperty("compositionGroupId")
    private Long compositionGroupId;

    @JsonProperty("price")
    private BigDecimal price;

}


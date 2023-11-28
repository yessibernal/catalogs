package com.innter.msdesigncatalog.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesingCompositionRequest {

    @JsonProperty("name")
    private String name;

}

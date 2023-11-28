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
public class DesingCareInstructionRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("icon")
    private String icon;

}

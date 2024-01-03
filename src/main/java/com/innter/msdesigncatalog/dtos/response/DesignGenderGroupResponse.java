package com.innter.msdesigncatalog.dtos.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignGenderGroupResponse {

    @JsonProperty("genderGroupId")
    private Long id;

    @JsonProperty("name")
    private String name;
}

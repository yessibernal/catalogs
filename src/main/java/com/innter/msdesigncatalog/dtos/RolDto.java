package com.innter.msdesigncatalog.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolDto {
    private Long id;
    private String rolName;
}

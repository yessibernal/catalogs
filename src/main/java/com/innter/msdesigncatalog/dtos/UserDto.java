package com.innter.msdesigncatalog.dtos;

import com.innter.msdesigncatalog.dtos.RolDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String userName;

    private List<RolDto> roles;
}

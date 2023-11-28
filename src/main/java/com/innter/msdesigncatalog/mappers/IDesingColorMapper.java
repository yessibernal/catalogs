package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingColorRequest;
import com.innter.msdesigncatalog.dtos.response.DesingColorResponse;
import com.innter.msdesigncatalog.entities.DesingColorEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingColorMapper {
    DesingColorResponse desingColorToDesingColorResponse (DesingColorEntity desingColor);

    DesingColorEntity desingColorRequestToDesingColor (DesingColorRequest desingColorRequest);
}

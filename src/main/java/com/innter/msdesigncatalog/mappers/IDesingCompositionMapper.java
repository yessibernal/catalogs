package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionResponse;
import com.innter.msdesigncatalog.entities.DesingCompositionEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingCompositionMapper {

    DesingCompositionResponse desingCompositionToDesingCompositionResponse (DesingCompositionEntity desingCompositionEntity);

    DesingCompositionEntity DesingCompositionRequestToDesingComposition (DesingCompositionRequest desingCompositionRequest);
}

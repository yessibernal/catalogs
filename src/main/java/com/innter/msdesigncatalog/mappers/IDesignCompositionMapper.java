package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionRequest;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignCompositionMapper {

    DesignCompositionResponse designCompositionToDesignCompositionResponse (DesignCompositionEntity designCompositionEntity);

    DesignCompositionEntity DesignCompositionRequestToDesignComposition (DesignCompositionRequest designCompositionRequest);
}

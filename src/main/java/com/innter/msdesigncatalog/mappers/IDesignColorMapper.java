package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignColorRequest;
import com.innter.msdesigncatalog.dtos.response.DesignColorResponse;
import com.innter.msdesigncatalog.entities.DesignColorEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignColorMapper {
    DesignColorResponse designColorToDesignColorResponse (DesignColorEntity designColor);

    DesignColorEntity designColorRequestToDesignColor (DesignColorRequest designColorRequest);
}

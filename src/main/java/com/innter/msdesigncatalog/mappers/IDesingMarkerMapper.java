package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMarkerResponse;
import com.innter.msdesigncatalog.entities.DesingMarkerEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesingMarkerMapper {

    DesingMarkerResponse desingMarkerToDesingMarkerResponse (DesingMarkerEntity desingMarkerEntity);

    DesingMarkerEntity desingMarkerRequestToDesingMarker (DesingMarkerRequest desingMarkerRequest);
}

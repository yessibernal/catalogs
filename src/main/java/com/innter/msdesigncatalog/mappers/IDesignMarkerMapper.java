package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesignMarkerResponse;
import com.innter.msdesigncatalog.entities.DesignMarkerEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignMarkerMapper {

    DesignMarkerResponse designMarkerToDesignMarkerResponse (DesignMarkerEntity designMarkerEntity);

    DesignMarkerEntity designMarkerRequestToDesignMarker (DesignMarkerRequest designMarkerRequest);
}

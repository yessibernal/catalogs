package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesignMarkerResponse;
import com.innter.msdesigncatalog.entities.DesignMarkerEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignMarkerMapper implements IDesignMarkerMapper{
    @Override
    public DesignMarkerResponse designMarkerToDesignMarkerResponse(DesignMarkerEntity designMarkerEntity) {
        DesignMarkerResponse designMarkerResponse = new DesignMarkerResponse();
        designMarkerResponse.setId(designMarkerEntity.getId());
        designMarkerResponse.setName(designMarkerEntity.getName());
        return designMarkerResponse;
    }

    @Override
    public DesignMarkerEntity designMarkerRequestToDesignMarker(DesignMarkerRequest designMarkerRequest) {
        DesignMarkerEntity designMarkerEntity = new DesignMarkerEntity();
        designMarkerEntity.setName(designMarkerRequest.getName());
        return designMarkerEntity;
    }
}

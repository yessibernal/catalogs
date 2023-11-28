package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesingMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMarkerResponse;
import com.innter.msdesigncatalog.entities.DesingMarkerEntity;
import org.springframework.stereotype.Component;

@Component
public class DesingMarkerMapper implements IDesingMarkerMapper{
    @Override
    public DesingMarkerResponse desingMarkerToDesingMarkerResponse(DesingMarkerEntity desingMarkerEntity) {
        DesingMarkerResponse desingMarkerResponse = new DesingMarkerResponse();
        desingMarkerResponse.setId(desingMarkerEntity.getId());
        desingMarkerResponse.setName(desingMarkerEntity.getName());
        return desingMarkerResponse;
    }

    @Override
    public DesingMarkerEntity desingMarkerRequestToDesingMarker(DesingMarkerRequest desingMarkerRequest) {
        DesingMarkerEntity desingMarkerEntity = new DesingMarkerEntity();
        desingMarkerEntity.setName(desingMarkerRequest.getName());
        return desingMarkerEntity;
    }
}

package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignMarkerRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignMarkerResponse;
import com.innter.msdesigncatalog.entities.DesignMarkerEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignMarkerMapper;
import com.innter.msdesigncatalog.repositories.DesignMarkerRepository;
import com.innter.msdesigncatalog.services.IDesignMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignMarkerService implements IDesignMarkerService {
    @Autowired
    private DesignMarkerRepository designMarkerRepository;
    @Autowired
    private DesignMarkerMapper designMarkerMapper;

    @Override
    public DesignMarkerResponse saveDesignMarker(DesignMarkerRequest designMarkerRequest) {
        try {
            DesignMarkerEntity designMarker = designMarkerMapper.designMarkerRequestToDesignMarker(designMarkerRequest);
            designMarkerRepository.save(designMarker);
            return designMarkerMapper.designMarkerToDesignMarkerResponse(designMarker);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Marcador no se pudo crear.");
        }
    }

    @Override
    public List<DesignMarkerResponse> getDesignMarkers(Pageable pageable) {
        try {
            List<DesignMarkerEntity>  designMarkerEntities = designMarkerRepository.findDesignMarker(pageable);
            List<DesignMarkerResponse> designMarkerResponses = new ArrayList<>();
            designMarkerEntities.stream().forEach(designMarker -> {
                designMarkerResponses.add(designMarkerMapper.designMarkerToDesignMarkerResponse(designMarker));
            });
            return designMarkerResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Marcadores no se pudieron encontrar.");
        }
    }

    @Override
    public DesignMarkerResponse editedDesignMarker(DesignMarkerRequest designMarkerRequest, Long id) {
        DesignMarkerEntity designMarker = findDesignMarkerById(designMarkerRepository.findById(id));
        if (designMarker.getStatus() == true){
            designMarker.setName(designMarkerRequest.getName());
            designMarkerRepository.save(designMarker);
            return designMarkerMapper.designMarkerToDesignMarkerResponse(designMarker);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El Marcador no tiene un estado activo");
    }

    @Override
    public DesignMarkerResponse editedDesignMarkerByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignMarkerEntity designMarker = designMarkerRepository.findDesignMarkerStatusById(id);
            designMarker.setStatus(designRequestStatus.getStatus());
            designMarkerRepository.save(designMarker);
            return designMarkerMapper.designMarkerToDesignMarkerResponse(designMarker);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Marcador no fue encontrado.");
        }
    }

    private DesignMarkerEntity findDesignMarkerById(Optional<DesignMarkerEntity> optionalDesignMarker){
        return optionalDesignMarker.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El Marcador no fue encontrado."));
    }
}

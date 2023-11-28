package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingMarkerRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMarkerResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingMarkerEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingMarkerMapper;
import com.innter.msdesigncatalog.repositories.DesingMarkerRepository;
import com.innter.msdesigncatalog.services.IDesingMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingMarkerService implements IDesingMarkerService {
    @Autowired
    private DesingMarkerRepository desingMarkerRepository;
    @Autowired
    private DesingMarkerMapper desingMarkerMapper;

    @Override
    public DesingMarkerResponse saveDesingMarker(DesingMarkerRequest desingMarkerRequest) {
        try {
            DesingMarkerEntity desingMarker = desingMarkerMapper.desingMarkerRequestToDesingMarker(desingMarkerRequest);
            desingMarkerRepository.save(desingMarker);
            return desingMarkerMapper.desingMarkerToDesingMarkerResponse(desingMarker);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Marcador no se pudo crear.");
        }
    }

    @Override
    public List<DesingMarkerResponse> getDesingMarkers(Pageable pageable) {
        try {
            List<DesingMarkerEntity>  desingMarkerEntities = desingMarkerRepository.findDesingMarker(pageable);
            List<DesingMarkerResponse> desingMarkerResponses = new ArrayList<>();
            desingMarkerEntities.stream().forEach(desingMarker -> {
                desingMarkerResponses.add(desingMarkerMapper.desingMarkerToDesingMarkerResponse(desingMarker));
            });
            return desingMarkerResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Marcadores no se pudieron encontrar.");
        }
    }

    @Override
    public DesingMarkerResponse editedDesingMarker(DesingMarkerRequest desingMarkerRequest, Long id) {
        DesingMarkerEntity desingMarker = findDesingMarkerById(desingMarkerRepository.findById(id));
        desingMarker.setName(desingMarkerRequest.getName());
        desingMarkerRepository.save(desingMarker);
        return desingMarkerMapper.desingMarkerToDesingMarkerResponse(desingMarker);
    }

    @Override
    public DesingMarkerResponse editedDesingMarkerByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingMarkerEntity desingMarker = desingMarkerRepository.findDesingMarkerStatusById(id);
            desingMarker.setStatus(desingRequestStatus.getStatus());
            desingMarkerRepository.save(desingMarker);
            return desingMarkerMapper.desingMarkerToDesingMarkerResponse(desingMarker);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Marcador no fue encontrado.");
        }
    }

    private DesingMarkerEntity findDesingMarkerById(Optional<DesingMarkerEntity> optionalDesingMarker){
        return optionalDesingMarker.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El Marcador no fue encontrado."));
    }
}

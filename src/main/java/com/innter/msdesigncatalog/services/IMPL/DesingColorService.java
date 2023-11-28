package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesingColorRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesingColorResponse;
import com.innter.msdesigncatalog.entities.DesingColorEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingColorMapper;
import com.innter.msdesigncatalog.repositories.DesingColorRepository;
import com.innter.msdesigncatalog.services.IDesingColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingColorService implements IDesingColorService {

    @Autowired
    private DesingColorRepository desingColorRepository;

    @Autowired
    private DesingColorMapper desingColorMapper;


    @Override
    public DesingColorResponse saveDesingColor(DesingColorRequest newDesingColor) {
        try {
            DesingColorEntity desingColor = desingColorMapper.desingColorRequestToDesingColor(newDesingColor);
            desingColorRepository.save(desingColor);
            return desingColorMapper.desingColorToDesingColorResponse(desingColor);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El color no se pudo crear.");
        }
    }

    @Override
    public List<DesingColorResponse> getDesingColors(Pageable pageable) {
        try {
            List<DesingColorEntity>  desingColorEntities = desingColorRepository.findDesingColors(pageable);
            List<DesingColorResponse> desingColorResponses = new ArrayList<>();
            desingColorEntities.stream().forEach(desingColor -> {
                desingColorResponses.add(desingColorMapper.desingColorToDesingColorResponse(desingColor));
            });
            return desingColorResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los colores no se pudieron encontrar.");
        }
    }

    @Override
    public DesingColorResponse editedDesingColor(DesingColorRequest newDesingColor, Long id) {
        DesingColorEntity desingColor = findDesingColorById(desingColorRepository.findById(id));
        desingColor.setName(newDesingColor.getName());
        desingColor.setColor(newDesingColor.getColor());
        desingColor.setProvider(newDesingColor.getProvider());
        desingColorRepository.save(desingColor);
        return desingColorMapper.desingColorToDesingColorResponse(desingColor);
    }

    @Override
    public DesingColorResponse editedDesingColorByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingColorEntity desingColor = desingColorRepository.findDesingColorsStatusById(id);
            desingColor.setStatus(desingRequestStatus.getStatus());
            desingColorRepository.save(desingColor);
            return desingColorMapper.desingColorToDesingColorResponse(desingColor);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El color no fue encontrado.");
        }
    }


    private DesingColorEntity findDesingColorById(Optional<DesingColorEntity> optionalDesingColor){
        return optionalDesingColor.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El color no fue encontrado."));
    }
}

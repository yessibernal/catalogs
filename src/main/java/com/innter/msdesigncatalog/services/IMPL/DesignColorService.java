package com.innter.msdesigncatalog.services.IMPL;


import com.innter.msdesigncatalog.dtos.request.DesignColorRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignColorResponse;
import com.innter.msdesigncatalog.entities.DesignColorEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignColorMapper;
import com.innter.msdesigncatalog.repositories.DesignColorRepository;
import com.innter.msdesigncatalog.services.IDesignColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignColorService implements IDesignColorService {

    @Autowired
    private DesignColorRepository designColorRepository;

    @Autowired
    private DesignColorMapper designColorMapper;


    @Override
    public DesignColorResponse saveDesignColor(DesignColorRequest newDesignColor) {
        try {
            DesignColorEntity designColor = designColorMapper.designColorRequestToDesignColor(newDesignColor);
            designColorRepository.save(designColor);
            return designColorMapper.designColorToDesignColorResponse(designColor);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El color no se pudo crear.");
        }
    }

    @Override
    public List<DesignColorResponse> getDesignColors(Pageable pageable) {
        try {
            List<DesignColorEntity> designColorEntities = designColorRepository.findDesignColors(pageable);
            List<DesignColorResponse> designColorResponses = new ArrayList<>();
            designColorEntities.stream().forEach(designColor -> {
                designColorResponses.add(designColorMapper.designColorToDesignColorResponse(designColor));
            });
            return designColorResponses;
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los colores no se pudieron encontrar.");
        }
    }

    @Override
    public DesignColorResponse editedDesignColor(DesignColorRequest newDesignColor, Long id) {
        DesignColorEntity designColor = findDesignColorById(designColorRepository.findById(id));

        if (designColor.getStatus() == true) {
            designColor.setName(newDesignColor.getName());
            designColor.setColor(newDesignColor.getColor());
            designColor.setProvider(newDesignColor.getProvider());
            designColorRepository.save(designColor);
            return designColorMapper.designColorToDesignColorResponse(designColor);
        }

        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El color no tiene un estado activo");
    }

    @Override
    public DesignColorResponse editedDesignColorByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignColorEntity designColor = designColorRepository.findDesignColorsStatusById(id);
            designColor.setStatus(designRequestStatus.getStatus());
            designColorRepository.save(designColor);
            return designColorMapper.designColorToDesignColorResponse(designColor);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El color no fue encontrado.");
        }
    }


    private DesignColorEntity findDesignColorById(Optional<DesignColorEntity> optionalDesignColor) {
        return optionalDesignColor.orElseThrow(() -> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND, "El color no fue encontrado."));
    }
}

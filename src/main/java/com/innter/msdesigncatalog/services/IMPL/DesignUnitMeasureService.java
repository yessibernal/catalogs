package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesignUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesignUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesignUnitMeasureEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignUnitMeasureMapper;
import com.innter.msdesigncatalog.repositories.DesignUnitMeasureRepository;
import com.innter.msdesigncatalog.services.IDesignUnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignUnitMeasureService implements IDesignUnitMeasureService {

    @Autowired
    private DesignUnitMeasureRepository designUnitMeasureRepository ;

    @Autowired
    private DesignUnitMeasureMapper designUnitMeasureMapper;

    @Override
    @Transactional(readOnly = true)
    public DesignUnitMeasureResponse getDesignUnitMeasure(Long id) {
        DesignUnitMeasureEntity designUnitMeasure = findDesignUnitMeasureById(designUnitMeasureRepository.findById(id));
         DesignUnitMeasureResponse  desingUnitMeasureDto = designUnitMeasureMapper.designUnitMeasureToDesignUnitMeasureResponse(designUnitMeasure);
        return desingUnitMeasureDto;
    }

    @Override
    public DesignUnitMeasureResponse saveDesignUnitMeasure(DesignUnitMeasureRequest newDesignUnitMeasure) {
        try {
            DesignUnitMeasureEntity designUnitMeasure = designUnitMeasureMapper.designUnitMeasureRequestToDesignUnitMeasure(newDesignUnitMeasure);
            designUnitMeasureRepository.save(designUnitMeasure);
            return designUnitMeasureMapper.designUnitMeasureToDesignUnitMeasureResponse(designUnitMeasure);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Unidad de Medida no pudo crear.");
        }
    }


    @Override
    public DesignUnitMeasureResponse editedDesignUnitMeasure(DesignUnitMeasureRequest newDesignUnitMeasure, Long id) {
        DesignUnitMeasureEntity designUnitMeasure = findDesignUnitMeasureById(designUnitMeasureRepository.findById(id));
        if (designUnitMeasure.getStatus() == true){
            designUnitMeasure.setName(newDesignUnitMeasure.getName());
            designUnitMeasure.setAbbreviation(newDesignUnitMeasure.getAbbreviation());
            designUnitMeasureRepository.save(designUnitMeasure);
            return designUnitMeasureMapper.designUnitMeasureToDesignUnitMeasureResponse(designUnitMeasure);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La unidad de medida no tiene un estado activo");
    }

    @Override
    public DesignUnitMeasureResponse editedDesignUnitMeasureByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignUnitMeasureEntity designUnitMeasure = designUnitMeasureRepository.findDesignUnitMeasureStatusById(id);
            designUnitMeasure.setStatus(designRequestStatus.getStatus());
            designUnitMeasureRepository.save(designUnitMeasure);
            return designUnitMeasureMapper.designUnitMeasureToDesignUnitMeasureResponse(designUnitMeasure);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La unidad de medida no fue encontrada.");
        }
    }

    @Override
    public List<DesignUnitMeasureResponse> getDesignUnitsMeasure(Pageable pageable) {
        try {
            List<DesignUnitMeasureEntity> designUnitMeasureEntities = designUnitMeasureRepository.findDesignUnitsMeasure(pageable);
            List<DesignUnitMeasureResponse> designUnitMeasureDAO = new ArrayList<DesignUnitMeasureResponse>();
            designUnitMeasureEntities.stream().forEach(designUnitMeasure->{
                designUnitMeasureDAO.add(designUnitMeasureMapper.designUnitMeasureToDesignUnitMeasureResponse(designUnitMeasure));
            });
            return designUnitMeasureDAO;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las Unidades de Medida no se pudieron encontrar.");
        }
    }


    private DesignUnitMeasureEntity findDesignUnitMeasureById(Optional<DesignUnitMeasureEntity> optionalDesignUnitMeasure){
        return optionalDesignUnitMeasure.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La unidad de medida no fue encontrada."));
    }
}

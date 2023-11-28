package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingUnitMeasureRequest;
import com.innter.msdesigncatalog.dtos.response.DesingUnitMeasureResponse;
import com.innter.msdesigncatalog.entities.DesingUnitMeasureEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingUnitMeasureMapper;
import com.innter.msdesigncatalog.repositories.DesingUnitMeasureRepository;
import com.innter.msdesigncatalog.services.IDesingUnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingUnitMeasureService implements IDesingUnitMeasureService {

    @Autowired
    private DesingUnitMeasureRepository desingUnitMeasureRepository ;

    @Autowired
    private DesingUnitMeasureMapper desingUnitMeasureMapper;

    @Override
    @Transactional(readOnly = true)
    public DesingUnitMeasureResponse getDesingUnitMeasure(Long id) {
        DesingUnitMeasureEntity desingUnitMeasure = findDesingUnitMeasureById(desingUnitMeasureRepository.findById(id));
         DesingUnitMeasureResponse  desingUnitMeasureDto = desingUnitMeasureMapper.desingUnitMeasureToDesingUnitMeasureResponse(desingUnitMeasure);
        return desingUnitMeasureDto;
    }

    @Override
    public DesingUnitMeasureResponse saveDesingUnitMeasure(DesingUnitMeasureRequest newDesingUnitMeasure) {
        try {
            DesingUnitMeasureEntity desingUnitMeasure = desingUnitMeasureMapper.desingUnitMeasureRequestToDesingUnitMeasure(newDesingUnitMeasure);
            desingUnitMeasureRepository.save(desingUnitMeasure);
            return desingUnitMeasureMapper.desingUnitMeasureToDesingUnitMeasureResponse(desingUnitMeasure);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Unidad de Medida no pudo crear.");
        }
    }

    @Override
    public DesingUnitMeasureResponse editedDesingUnitMeasure(DesingUnitMeasureRequest newDesingUnitMeasure, Long id) {
        DesingUnitMeasureEntity desingUnitMeasure = findDesingUnitMeasureById(desingUnitMeasureRepository.findById(id));
        desingUnitMeasure.setName(newDesingUnitMeasure.getName());
        desingUnitMeasure.setAbbreviation(newDesingUnitMeasure.getAbbreviation());
        desingUnitMeasureRepository.save(desingUnitMeasure);
        return desingUnitMeasureMapper.desingUnitMeasureToDesingUnitMeasureResponse(desingUnitMeasure);
    }

    @Override
    public DesingUnitMeasureResponse editedDesingUnitMeasureByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingUnitMeasureEntity desingUnitMeasure = desingUnitMeasureRepository.findDesingUnitMeasureStatusById(id);
            desingUnitMeasure.setStatus(desingRequestStatus.getStatus());
            desingUnitMeasureRepository.save(desingUnitMeasure);
            return desingUnitMeasureMapper.desingUnitMeasureToDesingUnitMeasureResponse(desingUnitMeasure);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La unidad de medida no fue encontrada.");
        }
    }

    @Override
    public List<DesingUnitMeasureResponse> getDesingUnitsMeasure(Pageable pageable) {
        try {
            List<DesingUnitMeasureEntity> desingUnitMeasureEntities = desingUnitMeasureRepository.findDesingUnitsMeasure(pageable);
            List<DesingUnitMeasureResponse> desingUnitMeasureDAO = new ArrayList<DesingUnitMeasureResponse>();
            desingUnitMeasureEntities.stream().forEach(desingUnitMeasure->{
                desingUnitMeasureDAO.add(desingUnitMeasureMapper.desingUnitMeasureToDesingUnitMeasureResponse(desingUnitMeasure));
            });
            return desingUnitMeasureDAO;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las Unidades de Medida no se pudieron encontrar.");
        }
    }


    private DesingUnitMeasureEntity findDesingUnitMeasureById(Optional<DesingUnitMeasureEntity> optionalDesingUnitMeasure){
        return optionalDesingUnitMeasure.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La unidad de medida no fue encontrada."));
    }
}

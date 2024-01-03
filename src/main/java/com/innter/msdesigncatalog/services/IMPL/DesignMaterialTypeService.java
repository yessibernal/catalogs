package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignMaterialTypeResponse;
import com.innter.msdesigncatalog.entities.DesignMaterialTypeEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignMaterialTypeMapper;
import com.innter.msdesigncatalog.repositories.DesignMaterialTypeRepository;
import com.innter.msdesigncatalog.services.IDesignMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignMaterialTypeService implements IDesignMaterialTypeService {
    @Autowired
    private DesignMaterialTypeRepository designMaterialTypeRepository;
    @Autowired
    private DesignMaterialTypeMapper designMaterialTypeMapper;

    @Override
    public DesignMaterialTypeResponse saveDesignMaterialTypeResponse(DesignMaterialTypeRequest designMaterialTypeRequest) {
        try {
            DesignMaterialTypeEntity designMaterialType = designMaterialTypeMapper.designMaterialTypeRequestToDesignMaterialType(designMaterialTypeRequest);
            designMaterialTypeRepository.save(designMaterialType);
            return designMaterialTypeMapper.designMaterialTypeToDesignMaterialTypeResponse(designMaterialType);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Tipo de Material no se pudo crear.");
        }
    }

    @Override
    public List<DesignMaterialTypeResponse> getDesignMaterialsType(Pageable pageable) {
        try {
            List<DesignMaterialTypeEntity>  designMaterialTypeEntities = designMaterialTypeRepository.findDesignMaterialType(pageable);
            List<DesignMaterialTypeResponse> designMaterialTypeResponses = new ArrayList<>();
            designMaterialTypeEntities.stream().forEach(designMaterialType -> {
                designMaterialTypeResponses.add(designMaterialTypeMapper.designMaterialTypeToDesignMaterialTypeResponse(designMaterialType));
            });
            return designMaterialTypeResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Tipos de Materiales no se pudieron encontrar.");
        }
    }

    @Override
    public DesignMaterialTypeResponse editedDesignMaterialType(DesignMaterialTypeRequest designMaterialTypeRequest, Long id) {
        DesignMaterialTypeEntity designMaterialType = findDesignMaterialTypeById(designMaterialTypeRepository.findById(id));
        if (designMaterialType.getStatus() == true){
            designMaterialType.setName(designMaterialTypeRequest.getName());
            designMaterialType.setCode(designMaterialTypeRequest.getCode());
            designMaterialType.setType(designMaterialTypeRequest.getType());
            designMaterialType.setClassification(designMaterialTypeRequest.getClassification());
            return designMaterialTypeMapper.designMaterialTypeToDesignMaterialTypeResponse(designMaterialType);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El Tipo de Material no tiene un estado activo");
    }

    @Override
    public DesignMaterialTypeResponse editedDesignMaterialTypeByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignMaterialTypeEntity designMaterialType = designMaterialTypeRepository.findDesignMaterialTypeStatusById(id);
            designMaterialType.setStatus(designRequestStatus.getStatus());
            designMaterialTypeRepository.save(designMaterialType);
            return designMaterialTypeMapper.designMaterialTypeToDesignMaterialTypeResponse(designMaterialType);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Tipo de Material no fue encontrado.");
        }
    }

    private DesignMaterialTypeEntity findDesignMaterialTypeById(Optional<DesignMaterialTypeEntity> optionalDesignMaterialType){
        return optionalDesignMaterialType.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El Tipo de Material no fue encontrado."));
    }
}

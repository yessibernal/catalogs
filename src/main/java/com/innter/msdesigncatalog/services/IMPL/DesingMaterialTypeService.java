package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.response.DesingMaterialTypeResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingMaterialTypeEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingMaterialTypeMapper;
import com.innter.msdesigncatalog.repositories.DesingMaterialTypeRepository;
import com.innter.msdesigncatalog.services.IDesingMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingMaterialTypeService implements IDesingMaterialTypeService {
    @Autowired
    private DesingMaterialTypeRepository desingMaterialTypeRepository;
    @Autowired
    private DesingMaterialTypeMapper desingMaterialTypeMapper;

    @Override
    public DesingMaterialTypeResponse saveDesingMaterialTypeResponse(DesingMaterialTypeRequest desingMaterialTypeRequest) {
        try {
            DesingMaterialTypeEntity desingMaterialType = desingMaterialTypeMapper.desingMaterialTypeRequestToDesingMaterialType(desingMaterialTypeRequest);
            desingMaterialTypeRepository.save(desingMaterialType);
            return desingMaterialTypeMapper.desingMaterialTypeToDesingMaterialTypeResponse(desingMaterialType);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Tipo de Material no se pudo crear.");
        }
    }

    @Override
    public List<DesingMaterialTypeResponse> getDesingMaterialsType(Pageable pageable) {
        try {
            List<DesingMaterialTypeEntity>  desingMaterialTypeEntities = desingMaterialTypeRepository.findDesingMaterialType(pageable);
            List<DesingMaterialTypeResponse> desingMaterialTypeResponses = new ArrayList<>();
            desingMaterialTypeEntities.stream().forEach(desingMaterialType -> {
                desingMaterialTypeResponses.add(desingMaterialTypeMapper.desingMaterialTypeToDesingMaterialTypeResponse(desingMaterialType));
            });
            return desingMaterialTypeResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Tipos de Materiales no se pudieron encontrar.");
        }
    }

    @Override
    public DesingMaterialTypeResponse editedDesingMaterialType(DesingMaterialTypeRequest desingMaterialTypeRequest, Long id) {
        DesingMaterialTypeEntity desingMaterialType = findDesingMaterialTypeById(desingMaterialTypeRepository.findById(id));
        if (desingMaterialType.getStatus() == true){
            desingMaterialType.setName(desingMaterialTypeRequest.getName());
            desingMaterialType.setCode(desingMaterialTypeRequest.getCode());
            desingMaterialType.setType(desingMaterialTypeRequest.getType());
            desingMaterialType.setClassification(desingMaterialTypeRequest.getClassification());
            return desingMaterialTypeMapper.desingMaterialTypeToDesingMaterialTypeResponse(desingMaterialType);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El Tipo de Material no tiene un estado activo");
    }

    @Override
    public DesingMaterialTypeResponse editedDesingMaterialTypeByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingMaterialTypeEntity desingMaterialType = desingMaterialTypeRepository.findDesingMaterialTypeStatusById(id);
            desingMaterialType.setStatus(desingRequestStatus.getStatus());
            desingMaterialTypeRepository.save(desingMaterialType);
            return desingMaterialTypeMapper.desingMaterialTypeToDesingMaterialTypeResponse(desingMaterialType);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Tipo de Material no fue encontrado.");
        }
    }

    private DesingMaterialTypeEntity findDesingMaterialTypeById(Optional<DesingMaterialTypeEntity> optionalDesingMaterialType){
        return optionalDesingMaterialType.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El Tipo de Material no fue encontrado."));
    }
}

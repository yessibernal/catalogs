package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignGenderGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGendersGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignGendersGroupMapper;
import com.innter.msdesigncatalog.repositories.DesignGenderGroupRepository;
import com.innter.msdesigncatalog.services.IDesignGendersGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignGendersGroupService  implements IDesignGendersGroupService {
    @Autowired
    private DesignGenderGroupRepository designGenderGroupRepository;

    @Autowired
    private DesignGendersGroupMapper designGendersGroupMapper;

    @Override
    public DesignGenderGroupResponse saveDesignGenderGroup(DesignGenderGroupRequest designGenderGroupRequest) {
        try {
            DesignGendersGroupEntity designGenderGroup = designGendersGroupMapper.designGenderGroupRequestToDesignGenderGroup(designGenderGroupRequest);
            designGenderGroupRepository.save(designGenderGroup);
            return designGendersGroupMapper.designGenderGroupToDesignGenderGroupResponse(designGenderGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no se pudo crear.");
        }
    }

    @Override
    public List<DesignGenderGroupResponse> getDesignGenderGroup(Pageable pageable) {
        try {
            List<DesignGendersGroupEntity>  entityDesignGenderGroup = designGenderGroupRepository.findDesignGendersGroup(pageable);
            List<DesignGenderGroupResponse> designGenderGroupResponses = new ArrayList<>();
            entityDesignGenderGroup.stream().forEach(designGenderGroup -> designGenderGroupResponses.add(designGendersGroupMapper.designGenderGroupToDesignGenderGroupResponse(designGenderGroup)));
            return designGenderGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no se pudo encontrar.");
        }
    }

    @Override
    public DesignGenderGroupResponse editedDesignGenderGroup(DesignGenderGroupRequest designGenderGroupRequest, Long id) {
        DesignGendersGroupEntity designGendersGroup = findDesignGenderGroupById(designGenderGroupRepository.findById(id));
        if (designGendersGroup.getStatus() == true){
            designGendersGroup.setName(designGenderGroupRequest.getName());
            designGenderGroupRepository.save(designGendersGroup);
            return designGendersGroupMapper.designGenderGroupToDesignGenderGroupResponse(designGendersGroup);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La familia de Genero no tiene un estado activo");

    }

    @Override
    public DesignGenderGroupResponse editedDesignGenderGroupByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignGendersGroupEntity designGendersGroup = designGenderGroupRepository.findDesignGendersGroupStatusById(id);
            designGendersGroup.setStatus(designRequestStatus.getStatus());
            designGenderGroupRepository.save(designGendersGroup);
            return designGendersGroupMapper.designGenderGroupToDesignGenderGroupResponse(designGendersGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no fue encontrada.");
        }
    }

    private DesignGendersGroupEntity findDesignGenderGroupById(Optional<DesignGendersGroupEntity> optionalDesignGenderGroup){
        return optionalDesignGenderGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de Genero no fue encontrada."));
    }
}

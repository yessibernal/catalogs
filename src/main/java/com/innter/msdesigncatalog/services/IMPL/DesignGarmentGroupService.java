package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignGarmentGroupResponse;
import com.innter.msdesigncatalog.entities.DesignGarmentGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignGarmentGroupMapper;
import com.innter.msdesigncatalog.repositories.DesignGarmentGroupRepository;
import com.innter.msdesigncatalog.services.IDesignGarmentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignGarmentGroupService implements IDesignGarmentGroupService {

    @Autowired
    private DesignGarmentGroupRepository designGarmentGroupRepository;

    @Autowired
    private DesignGarmentGroupMapper designGarmentGroupMapper;

    @Override
    public DesignGarmentGroupResponse saveDesignGarmentGroup(DesignGarmentGroupRequest designGarmentGroupRequest) {
        try {
            DesignGarmentGroupEntity designGarmentGroup = designGarmentGroupMapper.designGarmentGroupRequestToDesignGarmentGroup(designGarmentGroupRequest);
            designGarmentGroupRepository.save(designGarmentGroup);
            return designGarmentGroupMapper.designGarmentGroupToDesignGarmentGroupResponse(designGarmentGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no se pudo crear.");
        }
    }

    @Override
    public List<DesignGarmentGroupResponse> getDesignGarmentsGroup(Pageable pageable) {
        try {
            List<DesignGarmentGroupEntity>  designGarmentGroupEntities = designGarmentGroupRepository.findDesignGarmentGroup(pageable);
            List<DesignGarmentGroupResponse> designGarmentGroupResponses = new ArrayList<>();
            designGarmentGroupEntities.stream().forEach(designGarmentGroup -> {
                designGarmentGroupResponses.add(designGarmentGroupMapper.designGarmentGroupToDesignGarmentGroupResponse(designGarmentGroup));
            });
            return designGarmentGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no se pudo encontrar.");
        }
    }

    @Override
    public DesignGarmentGroupResponse editedDesignGarmentGroup(DesignGarmentGroupRequest designGarmentGroupRequest, Long id) {
        DesignGarmentGroupEntity designGarmentGroup = finDesignGarmentGroupById(designGarmentGroupRepository.findById(id));
        if (designGarmentGroup.getStatus() == true){
            designGarmentGroup.setName(designGarmentGroupRequest.getName());
            designGarmentGroup.setCode(designGarmentGroupRequest.getCode());
            designGarmentGroup.setGarmentLocation(designGarmentGroupRequest.getGarmentLocation());
            return designGarmentGroupMapper.designGarmentGroupToDesignGarmentGroupResponse(designGarmentGroup);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La familia de prendas no tiene un estado activo");
    }

    @Override
    public DesignGarmentGroupResponse editedDesignGarmentGroupByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignGarmentGroupEntity designGarmentGroup = designGarmentGroupRepository.findDesignGarmentGroupStatusById(id);
            designGarmentGroup.setStatus(designRequestStatus.getStatus());
            designGarmentGroupRepository.save(designGarmentGroup);
            return designGarmentGroupMapper.designGarmentGroupToDesignGarmentGroupResponse(designGarmentGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no fue encontrada.");
        }
    }

    private DesignGarmentGroupEntity finDesignGarmentGroupById(Optional<DesignGarmentGroupEntity> optionalDesignGarmentGroup){
        return optionalDesignGarmentGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de prendas no fue encontrada."));
    }
}

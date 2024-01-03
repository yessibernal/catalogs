package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignCompositionGroupMapper;
import com.innter.msdesigncatalog.repositories.DesignCompositionGroupRepository;
import com.innter.msdesigncatalog.services.IDesignCompositionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignCompositionGroupService implements IDesignCompositionGroupService {
    @Autowired
    private DesignCompositionGroupRepository designCompositionGroupRepository;
    @Autowired
    private DesignCompositionGroupMapper designCompositionGroupMapper;

    @Override
    public DesignCompositionGroupResponse saveDesignCompositionGroup(DesignCompositionGroupRequest designCompositionGroupRequest) {
        try {
            DesignCompositionGroupEntity designCompositionGroupEntity = designCompositionGroupMapper.designCompositionGroupRequestToDesignCompositionGroup(designCompositionGroupRequest);
            designCompositionGroupRepository.save(designCompositionGroupEntity);
            return designCompositionGroupMapper.designCompositionGroupToDesignCompositionGroupResponse(designCompositionGroupEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Familia de Composición no se pudo crear.");
        }
    }

    @Override
    public List<DesignCompositionGroupResponse> getDesignCompositionGroup(Pageable pageable) {
        try {
            List<DesignCompositionGroupEntity>  designCompositionGroupEntities = designCompositionGroupRepository.findDesignCompositionGroup(pageable);
            List<DesignCompositionGroupResponse> designCompositionGroupResponses = new ArrayList<>();
            designCompositionGroupEntities.stream().forEach(designCompositionGroup -> {
                designCompositionGroupResponses.add(designCompositionGroupMapper.designCompositionGroupToDesignCompositionGroupResponse(designCompositionGroup));
            });
            return designCompositionGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las Familias de Composición no se pudieron encontrar.");
        }
    }

    @Override
    public DesignCompositionGroupResponse editedDesignCompositionGroup(DesignCompositionGroupRequest designCompositionGroupRequest, Long id) {
        DesignCompositionGroupEntity designCompositionGroupEntity = findDesignCompositionGroupById(designCompositionGroupRepository.findById(id));
        if (designCompositionGroupEntity.getStatus() == true){
            designCompositionGroupEntity.setName(designCompositionGroupRequest.getName());
            designCompositionGroupRepository.save(designCompositionGroupEntity);
            return designCompositionGroupMapper.designCompositionGroupToDesignCompositionGroupResponse(designCompositionGroupEntity);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La Familia de Composición no tiene un estado activo");
    }

    @Override
    public DesignCompositionGroupResponse editedDesignCompositionGroupById(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignCompositionGroupEntity designCompositionGroupEntity = designCompositionGroupRepository.findDesignCompositionGroupStatusById(id);
            designCompositionGroupEntity.setStatus(designRequestStatus.getStatus());
            designCompositionGroupRepository.save(designCompositionGroupEntity);
            return designCompositionGroupMapper.designCompositionGroupToDesignCompositionGroupResponse(designCompositionGroupEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Familia de Composición no fue encontrada.");
        }
    }


    private DesignCompositionGroupEntity findDesignCompositionGroupById(Optional<DesignCompositionGroupEntity> optionalDesignCompositionGroup){
        return optionalDesignCompositionGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de Composición no fue encontrada."));
    }
}

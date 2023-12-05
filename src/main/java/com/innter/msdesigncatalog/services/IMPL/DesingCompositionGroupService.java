package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingCompositionGroupMapper;
import com.innter.msdesigncatalog.repositories.DesingCompositionGroupRepository;
import com.innter.msdesigncatalog.services.IDesingCompositionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingCompositionGroupService implements IDesingCompositionGroupService {
    @Autowired
    private DesingCompositionGroupRepository desingCompositionGroupRepository;
    @Autowired
    private DesingCompositionGroupMapper desingCompositionGroupMapper;

    @Override
    public DesingCompositionGroupResponse saveDesingCompositionGroup(DesingCompositionGroupRequest desingCompositionGroupRequest) {
        try {
            DesingCompositionGroupEntity desingCompositionGroupEntity = desingCompositionGroupMapper.desingCompositionGroupRequestToDesingCompositionGroup(desingCompositionGroupRequest);
            desingCompositionGroupRepository.save(desingCompositionGroupEntity);
            return desingCompositionGroupMapper.desingCompositionGroupToDesingCompositionGroupResponse(desingCompositionGroupEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Familia de Composición no se pudo crear.");
        }
    }

    @Override
    public List<DesingCompositionGroupResponse> getDesingCompositionGroup(Pageable pageable) {
        try {
            List<DesingCompositionGroupEntity>  desingCompositionGroupEntities = desingCompositionGroupRepository.findDesingCompositionGroup(pageable);
            List<DesingCompositionGroupResponse> desingCompositionGroupResponses = new ArrayList<>();
            desingCompositionGroupEntities.stream().forEach(desingCompositionGroup -> {
                desingCompositionGroupResponses.add(desingCompositionGroupMapper.desingCompositionGroupToDesingCompositionGroupResponse(desingCompositionGroup));
            });
            return desingCompositionGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Las Familias de Composición no se pudieron encontrar.");
        }
    }

    @Override
    public DesingCompositionGroupResponse editedDesingCompositionGroup(DesingCompositionGroupRequest desingCompositionGroupRequest, Long id) {
        DesingCompositionGroupEntity desingCompositionGroupEntity = findDesingCompositionGroupById(desingCompositionGroupRepository.findById(id));
        if (desingCompositionGroupEntity.getStatus() == true){
            desingCompositionGroupEntity.setName(desingCompositionGroupRequest.getName());
            desingCompositionGroupRepository.save(desingCompositionGroupEntity);
            return desingCompositionGroupMapper.desingCompositionGroupToDesingCompositionGroupResponse(desingCompositionGroupEntity);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La Familia de Composición no tiene un estado activo");
    }

    @Override
    public DesingCompositionGroupResponse editedDesingCompositionGroupById(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingCompositionGroupEntity desingCompositionGroupEntity = desingCompositionGroupRepository.findDesingCompositionGroupStatusById(id);
            desingCompositionGroupEntity.setStatus(desingRequestStatus.getStatus());
            desingCompositionGroupRepository.save(desingCompositionGroupEntity);
            return desingCompositionGroupMapper.desingCompositionGroupToDesingCompositionGroupResponse(desingCompositionGroupEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La Familia de Composición no fue encontrada.");
        }
    }


    private DesingCompositionGroupEntity findDesingCompositionGroupById(Optional<DesingCompositionGroupEntity> optionalDesingCompositionGroup){
        return optionalDesingCompositionGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de Composición no fue encontrada."));
    }
}

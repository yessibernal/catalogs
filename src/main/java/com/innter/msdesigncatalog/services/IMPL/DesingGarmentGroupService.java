package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGarmentGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingGarmentGroupMapper;
import com.innter.msdesigncatalog.repositories.DesingGarmentGroupRepository;
import com.innter.msdesigncatalog.services.IDesingGarmentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingGarmentGroupService implements IDesingGarmentGroupService {

    @Autowired
    private DesingGarmentGroupRepository desingGarmentGroupRepository;

    @Autowired
    private DesingGarmentGroupMapper desingGarmentGroupMapper;

    @Override
    public DesingGarmentGroupResponse saveDesingGarmentGroup(DesingGarmentGroupRequest desingGarmentGroupRequest) {
        try {
            DesingGarmentGroupEntity desingGarmentGroup = desingGarmentGroupMapper.desingGarmentGroupRequestToDesingGarmentGroup(desingGarmentGroupRequest);
            desingGarmentGroupRepository.save(desingGarmentGroup);
            return desingGarmentGroupMapper.desingGarmentGroupToDesingGarmentGroupResponse(desingGarmentGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no se pudo crear.");
        }
    }

    @Override
    public List<DesingGarmentGroupResponse> getDesingGarmentsGroup(org.springframework.data.domain.Pageable pageable) {
        try {
            List<DesingGarmentGroupEntity>  desingGarmentGroupEntities = desingGarmentGroupRepository.findDesingGarmentGroup(pageable);
            List<DesingGarmentGroupResponse> desingGarmentGroupResponses = new ArrayList<>();
            desingGarmentGroupEntities.stream().forEach(desingGarmentGroup -> {
                desingGarmentGroupResponses.add(desingGarmentGroupMapper.desingGarmentGroupToDesingGarmentGroupResponse(desingGarmentGroup));
            });
            return desingGarmentGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no se pudo encontrar.");
        }
    }

    @Override
    public DesingGarmentGroupResponse editedDesingGarmentGroup(DesingGarmentGroupRequest desingGarmentGroupRequest, Long id) {
        DesingGarmentGroupEntity desingGarmentGroup = finDesingGarmentGroupById(desingGarmentGroupRepository.findById(id));
        desingGarmentGroup.setName(desingGarmentGroupRequest.getName());
        desingGarmentGroup.setCode(desingGarmentGroupRequest.getCode());
        desingGarmentGroup.setGarmentLocation(desingGarmentGroupRequest.getGarmentLocation());
        return desingGarmentGroupMapper.desingGarmentGroupToDesingGarmentGroupResponse(desingGarmentGroup);
    }

    @Override
    public DesingGarmentGroupResponse editedDesingGarmentGroupByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingGarmentGroupEntity desingGarmentGroup = desingGarmentGroupRepository.findDesingGarmentGroupStatusById(id);
            desingGarmentGroup.setStatus(desingRequestStatus.getStatus());
            desingGarmentGroupRepository.save(desingGarmentGroup);
            return desingGarmentGroupMapper.desingGarmentGroupToDesingGarmentGroupResponse(desingGarmentGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de prendas no fue encontrada.");
        }
    }

    private DesingGarmentGroupEntity finDesingGarmentGroupById(Optional<DesingGarmentGroupEntity> optionalDesingGarmentGroup){
        return optionalDesingGarmentGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de prendas no fue encontrada."));
    }
}

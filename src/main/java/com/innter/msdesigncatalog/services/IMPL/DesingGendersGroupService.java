package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGenderGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingGendersGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingGendersGroupMapper;
import com.innter.msdesigncatalog.repositories.DesingGenderGroupRepository;
import com.innter.msdesigncatalog.services.IDesingGendersGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingGendersGroupService  implements IDesingGendersGroupService {
    @Autowired
    private DesingGenderGroupRepository desingGenderGroupRepository;

    @Autowired
    private DesingGendersGroupMapper desingGendersGroupMapper;

    @Override
    public DesingGenderGroupResponse saveDesingGenderGroup(DesingGenderGroupRequest desingGenderGroupRequest) {
        try {
            DesingGendersGroupEntity desingGenderGroup = desingGendersGroupMapper.desingGenderGroupRequestToDesingGenderGroup(desingGenderGroupRequest);
            desingGenderGroupRepository.save(desingGenderGroup);
            return desingGendersGroupMapper.desingGenderGroupToDesingGenderGroupResponse(desingGenderGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no se pudo crear.");
        }
    }

    @Override
    public List<DesingGenderGroupResponse> getDesingGenderGroup(Pageable pageable) {
        try {
            List<DesingGendersGroupEntity>  entityDesingGenderGroup = desingGenderGroupRepository.findDesingGendersGroup(pageable);
            List<DesingGenderGroupResponse> desingGenderGroupResponses = new ArrayList<>();
            entityDesingGenderGroup.stream().forEach(desingGenderGroup -> desingGenderGroupResponses.add(desingGendersGroupMapper.desingGenderGroupToDesingGenderGroupResponse(desingGenderGroup)));
            return desingGenderGroupResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no se pudo encontrar.");
        }
    }

    @Override
    public DesingGenderGroupResponse editedDesingGenderGroup(DesingGenderGroupRequest desingGenderGroupRequest, Long id) {
        DesingGendersGroupEntity desingGendersGroup = findDesingGenderGroupById(desingGenderGroupRepository.findById(id));
        if (desingGendersGroup.getStatus() == true){
            desingGendersGroup.setName(desingGenderGroupRequest.getName());
            desingGenderGroupRepository.save(desingGendersGroup);
            return desingGendersGroupMapper.desingGenderGroupToDesingGenderGroupResponse(desingGendersGroup);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La familia de Genero no tiene un estado activo");

    }

    @Override
    public DesingGenderGroupResponse editedDesingGenderGroupByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingGendersGroupEntity desingGendersGroup = desingGenderGroupRepository.findDesingGendersGroupStatusById(id);
            desingGendersGroup.setStatus(desingRequestStatus.getStatus());
            desingGenderGroupRepository.save(desingGendersGroup);
            return desingGendersGroupMapper.desingGenderGroupToDesingGenderGroupResponse(desingGendersGroup);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "La familia de Genero no fue encontrada.");
        }
    }

    private DesingGendersGroupEntity findDesingGenderGroupById(Optional<DesingGendersGroupEntity> optionalDesingGenderGroup){
        return optionalDesingGenderGroup.orElseThrow( ()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"La familia de Genero no fue encontrada."));
    }
}

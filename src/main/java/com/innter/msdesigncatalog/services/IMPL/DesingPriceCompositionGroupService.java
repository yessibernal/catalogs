package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesingPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.entities.DesingCompositionGroupEntity;
import com.innter.msdesigncatalog.entities.DesingGarmentGroupEntity;
import com.innter.msdesigncatalog.entities.DesingPriceCompositionGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesingPriceCompositionGroupMapper;
import com.innter.msdesigncatalog.repositories.DesingPriceCompositionGroupRepository;
import com.innter.msdesigncatalog.services.IDesingPriceCompositionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesingPriceCompositionGroupService implements IDesingPriceCompositionGroupService {
    @Autowired
    private DesingPriceCompositionGroupRepository desingPriceCompositionGroupRepository;

    @Autowired
    private DesingPriceCompositionGroupMapper desingPriceCompositionGroupMapper;


    @Override
    public DesingPriceCompositionGroupResponse saveDesingPriceCompositionGroup(DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest) {

        try {
            DesingGarmentGroupEntity garmentGroup = desingPriceCompositionGroupRepository.findGarmentGroup(desingPriceCompositionGroupRequest.getGarmentsGroupId());
            DesingCompositionGroupEntity compositionGroup = desingPriceCompositionGroupRepository.findCompositionGroup(desingPriceCompositionGroupRequest.getCompositionGroupId());
            DesingPriceCompositionGroupEntity desingPriceCompositionGroup = new DesingPriceCompositionGroupEntity();
            desingPriceCompositionGroup.setGarmentsGroup(garmentGroup);
            desingPriceCompositionGroup.setCompositionGroup(compositionGroup);
            desingPriceCompositionGroup.setPrice(desingPriceCompositionGroupRequest.getPrice());
            desingPriceCompositionGroupRepository.save(desingPriceCompositionGroup);
            return desingPriceCompositionGroupMapper.desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(desingPriceCompositionGroup);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Precio de la Familia de Composición no se pudo crear.");
        }

    }

    @Override
    public List<DesingPriceCompositionGroupResponse> getDesingPriceCompositionGroup(Pageable pageable) {
        try {
            List<DesingPriceCompositionGroupEntity> desingPriceCompositionGroupEntities = desingPriceCompositionGroupRepository.findDesingPriceCompositionGroupEntity(pageable);
            List<DesingPriceCompositionGroupResponse> desingPriceCompositionGroupResponses = new ArrayList<>();
            desingPriceCompositionGroupEntities.stream().forEach(desingPriceCompositionGroup -> {
                desingPriceCompositionGroupResponses.add(desingPriceCompositionGroupMapper.desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(desingPriceCompositionGroup));
            });
            return desingPriceCompositionGroupResponses;
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Precios de las Familias de Composición no se pudieron encontrar.");
        }
    }

    @Override
    public DesingPriceCompositionGroupResponse editedDesingPriceCompositionGroup(DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest, Long id) {
        DesingPriceCompositionGroupEntity desingPriceCompositionGroup = findDesingPriceCompositionGroupById(desingPriceCompositionGroupRepository.findById(id));
        if (desingPriceCompositionGroup.getStatus() == true){
            desingPriceCompositionGroup.setPrice(desingPriceCompositionGroupRequest.getPrice());
            desingPriceCompositionGroupRepository.save(desingPriceCompositionGroup);
            return desingPriceCompositionGroupMapper.desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(desingPriceCompositionGroup);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El Precio de la Familia de Composición no tiene un estado activo");
    }


    @Override
    public DesingPriceCompositionGroupResponse editedDesingPriceCompositionGroupByStatus(DesingRequestStatus desingRequestStatus, Long id) {
        try {
            DesingPriceCompositionGroupEntity desingPriceCompositionGroup = desingPriceCompositionGroupRepository.findDesingPriceCompositionGroupEntityStatusById(id);
            desingPriceCompositionGroup.setStatus(desingRequestStatus.getStatus());
            desingPriceCompositionGroupRepository.save(desingPriceCompositionGroup);
            return desingPriceCompositionGroupMapper.desingPriceCompositionGroupToDesingPriceCompositionGroupResponse(desingPriceCompositionGroup);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Precio de la Familia de Composición no fue encontrado.");
        }
    }

    private DesingPriceCompositionGroupEntity findDesingPriceCompositionGroupById(Optional<DesingPriceCompositionGroupEntity> optionalDesingPriceCompositionGroup) {
        return optionalDesingPriceCompositionGroup.orElseThrow(() -> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND, "El Precio de la Familia de Composición no fue encontrado."));
    }
}

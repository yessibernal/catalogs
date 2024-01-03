package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.request.DesignPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.entities.DesignCompositionGroupEntity;
import com.innter.msdesigncatalog.entities.DesignGarmentGroupEntity;
import com.innter.msdesigncatalog.entities.DesignPriceCompositionGroupEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignPriceCompositionGroupMapper;
import com.innter.msdesigncatalog.repositories.DesignPriceCompositionGroupRepository;
import com.innter.msdesigncatalog.services.IDesignPriceCompositionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignPriceCompositionGroupService implements IDesignPriceCompositionGroupService {
    @Autowired
    private DesignPriceCompositionGroupRepository designPriceCompositionGroupRepository;

    @Autowired
    private DesignPriceCompositionGroupMapper designPriceCompositionGroupMapper;


    @Override
    public DesignPriceCompositionGroupResponse saveDesignPriceCompositionGroup(DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest) {
        try {
            DesignGarmentGroupEntity garmentGroup = designPriceCompositionGroupRepository.findGarmentGroup(designPriceCompositionGroupRequest.getGarmentsGroupId());
            DesignCompositionGroupEntity compositionGroup = designPriceCompositionGroupRepository.findCompositionGroup(designPriceCompositionGroupRequest.getCompositionGroupId());
            DesignPriceCompositionGroupEntity designPriceCompositionGroup = new DesignPriceCompositionGroupEntity();
            designPriceCompositionGroup.setGarmentsGroup(garmentGroup);
            designPriceCompositionGroup.setCompositionGroup(compositionGroup);
            designPriceCompositionGroup.setPrice(designPriceCompositionGroupRequest.getPrice());
            designPriceCompositionGroupRepository.save(designPriceCompositionGroup);
            return designPriceCompositionGroupMapper.designPriceCompositionGroupToDesignPriceCompositionGroupResponse(designPriceCompositionGroup);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Precio de la Familia de Composición no se pudo crear.");
        }
    }

    @Override
    public List<DesignPriceCompositionGroupResponse> getDesignPriceCompositionGroup(Pageable pageable) {
        try {
            List<DesignPriceCompositionGroupEntity> designPriceCompositionGroupEntities = designPriceCompositionGroupRepository.findDesignPriceCompositionGroupEntity(pageable);
            List<DesignPriceCompositionGroupResponse> designPriceCompositionGroupResponses = new ArrayList<>();
            designPriceCompositionGroupEntities.stream().forEach(designPriceCompositionGroup -> {
                designPriceCompositionGroupResponses.add(designPriceCompositionGroupMapper.designPriceCompositionGroupToDesignPriceCompositionGroupResponse(designPriceCompositionGroup));
            });
            return designPriceCompositionGroupResponses;
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los Precios de las Familias de Composición no se pudieron encontrar.");
        }
    }

    @Override
    public DesignPriceCompositionGroupResponse editedDesignPriceCompositionGroup(DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest, Long id) {
        DesignPriceCompositionGroupEntity designPriceCompositionGroup = findDesignPriceCompositionGroupById(designPriceCompositionGroupRepository.findById(id));
        if (designPriceCompositionGroup.getStatus() == true) {
            designPriceCompositionGroup.setPrice(designPriceCompositionGroupRequest.getPrice());
            designPriceCompositionGroupRepository.save(designPriceCompositionGroup);
            return designPriceCompositionGroupMapper.designPriceCompositionGroupToDesignPriceCompositionGroupResponse(designPriceCompositionGroup);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El Precio de la Familia de Composición no tiene un estado activo");
    }


    @Override
    public DesignPriceCompositionGroupResponse editedDesignPriceCompositionGroupByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignPriceCompositionGroupEntity designPriceCompositionGroup = designPriceCompositionGroupRepository.findDesignPriceCompositionGroupEntityStatusById(id);
            designPriceCompositionGroup.setStatus(designRequestStatus.getStatus());
            designPriceCompositionGroupRepository.save(designPriceCompositionGroup);
            return designPriceCompositionGroupMapper.designPriceCompositionGroupToDesignPriceCompositionGroupResponse(designPriceCompositionGroup);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Precio de la Familia de Composición no fue encontrado.");
        }
    }

    private DesignPriceCompositionGroupEntity findDesignPriceCompositionGroupById(Optional<DesignPriceCompositionGroupEntity> optionalDesignPriceCompositionGroup) {
        return optionalDesignPriceCompositionGroup.orElseThrow(() -> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND, "El Precio de la Familia de Composición no fue encontrado."));
    }
}

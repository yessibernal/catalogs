package com.innter.msdesigncatalog.services.IMPL;
import com.innter.msdesigncatalog.dtos.request.DesignPrintRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignPrintResponse;
import com.innter.msdesigncatalog.entities.DesignPrintEntity;
import com.innter.msdesigncatalog.exceptions.BadRequestTextil;
import com.innter.msdesigncatalog.exceptions.NotFoundTextil;
import com.innter.msdesigncatalog.mappers.DesignPrintMapper;
import com.innter.msdesigncatalog.repositories.DesignPrintRepository;
import com.innter.msdesigncatalog.services.IDesignPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignPrintService implements IDesignPrintService {

    @Autowired
    private DesignPrintRepository designPrintRepository;
    @Autowired
    private DesignPrintMapper designPrintMapper;

    @Override
    public DesignPrintResponse saveDesignPrint(DesignPrintRequest newDesignPrint) {
        try {
            DesignPrintEntity designPrint = designPrintMapper.designPrintRequestToDesingPrint( newDesignPrint);
            designPrintRepository.save(designPrint);
            return  designPrintMapper.designPrintToDesignPrintResponse(designPrint);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El estampado no se pudo crear.");
        }
    }

    @Override
    public List<DesignPrintResponse> getDesignPrint(Pageable pageable) {
        try {
            List<DesignPrintEntity> designPrintEntities = designPrintRepository.findDesingPrints(pageable);
            List<DesignPrintResponse>designPrintResponses = new ArrayList<>();
            designPrintEntities.stream().forEach(designPrintResponse ->{
                designPrintResponses.add(designPrintMapper.designPrintToDesignPrintResponse(designPrintResponse));
            });
            return designPrintResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los estampados no se pudieron encontrar.");
        }
    }

    @Override
    public DesignPrintResponse editedDesignPrint(DesignPrintRequest newDesignPrint, Long id) {
        DesignPrintEntity designPrint = findDesignPrintById (designPrintRepository.findById(id));

        if(designPrint.getStatus() == true){
            designPrint.setName(newDesignPrint.getName());
            designPrintRepository.save(designPrint);
            return designPrintMapper.designPrintToDesignPrintResponse(designPrint);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El color no tiene un estado activo");
    }

    @Override
    public DesignPrintResponse editedDesignPrintByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignPrintEntity designPrint = designPrintRepository.findDesingPrintsStatusById(id);
            designPrint.setStatus(designRequestStatus.getStatus());
            designPrintRepository.save(designPrint);
            return designPrintMapper.designPrintToDesignPrintResponse(designPrint);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El estampado no fue encontrado.");
        }
    }

    private  DesignPrintEntity findDesignPrintById (Optional<DesignPrintEntity> optionalDesignPrint){
        return  optionalDesignPrint.orElseThrow(()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El estampado no fue encontrado."));
    }
}

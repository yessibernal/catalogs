package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesignCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.services.IDesignCareInstructionService;
import com.innter.msdesigncatalog.utils.ResponseUtils;
import com.innter.msdesigncatalog.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/designCareInstruction")
public class DesignCareInstructionController {
    @Autowired
    private IDesignCareInstructionService designCareInstructionService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignCareInstruction(@RequestBody DesignCareInstructionRequest designCareInstructionRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designCareInstructionService.saveDesignCareInstruction(designCareInstructionRequest),
                "Las Instrucciones de Cuidado se crearon de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignCareInstruction(@RequestParam(required = false) Integer pageIndex,
                                                      @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designCareInstructionService.getDesignCareInstruction(pageable),
                "Las Instrucciones de Cuidado se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignCareInstruction(@RequestBody DesignCareInstructionRequest designCareInstructionRequest,
                                                         @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                designCareInstructionService.editedDesignCareInstruction(designCareInstructionRequest,id),
                "La Instruccion de Cuidado el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignCareInstructionByStatus( @RequestBody DesignRequestStatus desingRequestStatus,
                                                          @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                designCareInstructionService.editedDesignCareInstructionByStatus(desingRequestStatus,id),
                "El color con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}

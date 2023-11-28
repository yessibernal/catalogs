package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingCareInstructionRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingCareInstructionService;
import com.innter.msdesigncatalog.utils.ResponseUtils;
import com.innter.msdesigncatalog.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/desingCareInstruction")
public class DesingCareInstructionController {
    @Autowired
    private IDesingCareInstructionService desingCareInstructionService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingCareInstruction(@RequestBody DesingCareInstructionRequest desingCareInstructionRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingCareInstructionService.saveDesingCareInstruction(desingCareInstructionRequest),
                "Las Instrucciones de Cuidado se crearon de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingCareInstruction(@RequestParam(required = false) Integer pageIndex,
                                                      @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCareInstructionService.getDesingCareInstruction(pageable),
                "Las Instrucciones de Cuidado se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingCareInstruction(@RequestBody DesingCareInstructionRequest desingCareInstructionRequest,
                                                         @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                desingCareInstructionService.editedDesingCareInstruction(desingCareInstructionRequest,id),
                "La Instruccion de Cuidado el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingCareInstructionByStatus( @RequestBody DesingRequestStatus desingRequestStatus,
                                                          @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                desingCareInstructionService.editedDesingCareInstructionByStatus(desingRequestStatus,id),
                "El color con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}

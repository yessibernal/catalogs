package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingCompositionService;
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
@RequestMapping(value = "/api/desingComposition")
public class DesingCompositionController {
    @Autowired
    private IDesingCompositionService desingCompositionService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingComposition(@RequestBody DesingCompositionRequest desingCompositionRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingCompositionService.saveDesingComposition(desingCompositionRequest),
                "La composición se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingComposition(@RequestParam(required = false) Integer pageIndex,
                                                  @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionService.getDesingCompositions(pageable),
                "Las composiciones se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingComposition( @RequestBody DesingCompositionRequest desingCompositionRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionService.editedDesingComposition(desingCompositionRequest, id),
                "La composición con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingCompositionByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionService.editedDesingCompositionByStatus(desingRequestStatus,id),
                "La composición con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

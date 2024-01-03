package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.services.IDesignCompositionService;
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
@RequestMapping(value = "/api/designComposition")
public class DesignCompositionController {
    @Autowired
    private IDesignCompositionService designCompositionService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignComposition(@RequestBody DesignCompositionRequest designCompositionRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designCompositionService.saveDesignComposition(designCompositionRequest),
                "La composición se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignComposition(@RequestParam(required = false) Integer pageIndex,
                                                  @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designCompositionService.getDesignCompositions(pageable),
                "Las composiciones se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignComposition( @RequestBody DesignCompositionRequest designCompositionRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designCompositionService.editedDesignComposition(designCompositionRequest, id),
                "La composición con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignCompositionByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designCompositionService.editedDesignCompositionByStatus(designRequestStatus,id),
                "La composición con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

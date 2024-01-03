package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesignPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.services.IDesignPriceCompositionGroupService;
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
@RequestMapping(value = "/api/designPriceCompositionGroup")
public class DesignPriceCompositionGroupController {
    @Autowired
    private IDesignPriceCompositionGroupService designPriceCompositionGroupService;
    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignPriceCompositionGroup(@RequestBody DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(
                designPriceCompositionGroupService.saveDesignPriceCompositionGroup(designPriceCompositionGroupRequest),
                "El Precio de la Familia de Composición se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignPriceCompositionGroup(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designPriceCompositionGroupService.getDesignPriceCompositionGroup(pageable),
                "Los Precios de las Familias de Composición se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignPriceCompositionGroup(
            @RequestBody DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                designPriceCompositionGroupService.editedDesignPriceCompositionGroup(designPriceCompositionGroupRequest, id),
                "El Precio de la Familia de Composición con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignPriceCompositionGroupByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designPriceCompositionGroupService.editedDesignPriceCompositionGroupByStatus(designRequestStatus, id),
                "El Precio de la Familia de Composición con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

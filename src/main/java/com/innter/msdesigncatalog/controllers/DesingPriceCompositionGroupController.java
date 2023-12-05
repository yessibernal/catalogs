package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingPriceCompositionGroupService;
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
@RequestMapping(value = "/api/desingPriceCompositionGroup")
public class DesingPriceCompositionGroupController {
    @Autowired
    private IDesingPriceCompositionGroupService desingPriceCompositionGroupService;
    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESING_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingPriceCompositionGroup(@RequestBody DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest) {
        System.out.println("x1");
        System.out.println(desingPriceCompositionGroupRequest.getGarmentsGroupId());
        System.out.println(desingPriceCompositionGroupRequest.getCompositionGroupId());
        System.out.println(desingPriceCompositionGroupRequest.getPrice());
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(
                desingPriceCompositionGroupService.saveDesingPriceCompositionGroup(desingPriceCompositionGroupRequest),
                "El Precio de la Familia de Composición se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESING_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingPriceCompositionGroup(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingPriceCompositionGroupService.getDesingPriceCompositionGroup(pageable),
                "Los Precios de las Familias de Composición se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESING_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingPriceCompositionGroup(
            @RequestBody DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                desingPriceCompositionGroupService.editedDesingPriceCompositionGroup(desingPriceCompositionGroupRequest, id),
                "El Precio de la Familia de Composición con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESING_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingPriceCompositionGroupByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingPriceCompositionGroupService.editedDesingPriceCompositionGroupByStatus(desingRequestStatus, id),
                "El Precio de la Familia de Composición con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

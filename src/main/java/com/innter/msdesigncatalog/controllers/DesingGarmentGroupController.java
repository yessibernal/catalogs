package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingGarmentGroupService;
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
@RequestMapping(value = "/api/desingGarmentGroup")
public class DesingGarmentGroupController {

    @Autowired
    private IDesingGarmentGroupService desingGarmentGroupService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingGarmentGroup(@RequestBody DesingGarmentGroupRequest desingGarmentGroupRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingGarmentGroupService.saveDesingGarmentGroup(desingGarmentGroupRequest),
                "La familia de prendas se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingGarmentGroup(    @RequestParam(required = false) Integer pageIndex,
                                                       @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingGarmentGroupService.getDesingGarmentsGroup(pageable),
                "La familia de prendas fue encontrada correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingGarmentGroup( @RequestBody DesingGarmentGroupRequest desingGarmentGroupRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingGarmentGroupService.editedDesingGarmentGroup(desingGarmentGroupRequest,id),
                "La familia de prendas con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingGarmentGroupByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingGarmentGroupService.editedDesingGarmentGroupByStatus(desingRequestStatus,id),
                "La familia de prendas con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

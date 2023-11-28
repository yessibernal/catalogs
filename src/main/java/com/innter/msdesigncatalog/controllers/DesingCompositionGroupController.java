package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingCompositionGroupService;
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
@RequestMapping(value = "/api/desingCompositionGroup")
public class DesingCompositionGroupController {
    @Autowired
    private IDesingCompositionGroupService desingCompositionGroupService;
    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingCompositionGroup(@RequestBody DesingCompositionGroupRequest newDesingCompositionGroupRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingCompositionGroupService.saveDesingCompositionGroup(newDesingCompositionGroupRequest),
                "La Familia de Composición se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingCompositionGroup(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionGroupService.getDesingCompositionGroup(pageable),
                "Las Familias de Composición se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingCompositionGroup( @RequestBody DesingCompositionGroupRequest desingCompositionGroupRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionGroupService.editedDesingCompositionGroup(desingCompositionGroupRequest,id),
                "La Familia de Composición con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingCompositionGroupByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingCompositionGroupService.editedDesingCompositionGroupById(desingRequestStatus,id),
                "La Familia de Composición con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

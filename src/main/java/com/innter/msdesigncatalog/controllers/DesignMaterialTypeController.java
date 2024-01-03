package com.innter.msdesigncatalog.controllers;


import com.innter.msdesigncatalog.dtos.request.DesignMaterialTypeRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.services.IDesignMaterialTypeService;
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
@RequestMapping(value = "/api/designMaterialType")

public class DesignMaterialTypeController {
    @Autowired
    private IDesignMaterialTypeService designMaterialTypeService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignMaterialType(@RequestBody DesignMaterialTypeRequest newDesignMaterialTypeRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designMaterialTypeService.saveDesignMaterialTypeResponse(newDesignMaterialTypeRequest),
                "El Tipo de Material se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignMaterialType(@RequestParam(required = false) Integer pageIndex,
                                                   @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialTypeService.getDesignMaterialsType(pageable),
                "Los Tipos de Materiales se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignMaterialType(@RequestBody DesignMaterialTypeRequest designMaterialTypeRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialTypeService.editedDesignMaterialType(designMaterialTypeRequest,id),
                "El Tipo de Material con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignMaterialTypeByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialTypeService.editedDesignMaterialTypeByStatus(designRequestStatus,id),
                "El Tipo de Material con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

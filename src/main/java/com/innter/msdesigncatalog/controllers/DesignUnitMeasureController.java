package com.innter.msdesigncatalog.controllers;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesignUnitMeasureRequest;
import com.innter.msdesigncatalog.services.IDesignUnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.innter.msdesigncatalog.utils.ResponseUtils;
import com.innter.msdesigncatalog.utils.SuccessResponse;

@RestController
@RequestMapping(value = "/api/designUnitMeasure")
public class DesignUnitMeasureController {

    @Autowired
    private IDesignUnitMeasureService designUnitMeasureService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdDesignUnitMeasure( @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designUnitMeasureService.getDesignUnitMeasure(id),
                "La unidad de medida con el id:" + id + " se encontró correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignUnitMeasure( @RequestBody DesignUnitMeasureRequest newDesignUnitMeasure) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designUnitMeasureService.saveDesignUnitMeasure(newDesignUnitMeasure),
                "La unidad de medida de creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignUnitMeasure( @RequestBody DesignUnitMeasureRequest newDesignUnitMeasure, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designUnitMeasureService.editedDesignUnitMeasure(newDesignUnitMeasure,id),
                "La unidad de medida con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignUnitMeasureByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designUnitMeasureService.editedDesignUnitMeasureByStatus(designRequestStatus,id),
                "La unidad de medida con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignUnitMeasure(
                                                    @RequestParam(required = false) Integer pageIndex,
                                                    @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex , pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designUnitMeasureService.getDesignUnitsMeasure(pageable),
                "Las unidades de medida se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

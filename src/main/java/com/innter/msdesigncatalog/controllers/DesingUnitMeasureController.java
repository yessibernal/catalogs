package com.innter.msdesigncatalog.controllers;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingUnitMeasureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.innter.msdesigncatalog.services.IDesingUnitMeasureService;
import com.innter.msdesigncatalog.utils.ResponseUtils;
import com.innter.msdesigncatalog.utils.SuccessResponse;

@RestController
@RequestMapping(value = "/api/desingUnitMeasure")
public class DesingUnitMeasureController {

    @Autowired
    private IDesingUnitMeasureService desingUnitMeasureService;

    @Autowired
    private ResponseUtils responseUtils;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdDesingUnitMeasure( @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingUnitMeasureService.getDesingUnitMeasure(id),
                "La unidad de medida con el id:" + id + " se encontró correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingUnitMeasure( @RequestBody DesingUnitMeasureRequest newDesingUnitMeasure) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingUnitMeasureService.saveDesingUnitMeasure(newDesingUnitMeasure),
                "La unidad de medida de creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingUnitMeasure( @RequestBody DesingUnitMeasureRequest newDesingUnitMeasure, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingUnitMeasureService.editedDesingUnitMeasure(newDesingUnitMeasure,id),
                "La unidad de medida con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingUnitMeasureByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingUnitMeasureService.editedDesingUnitMeasureByStatus(desingRequestStatus,id),
                "La unidad de medida con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingUnitMeasure(
                                                    @RequestParam(required = false) Integer pageIndex,
                                                    @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex , pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingUnitMeasureService.getDesingUnitsMeasure(pageable),
                "Las unidades de medida se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }
}

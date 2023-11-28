package com.innter.msdesigncatalog.controllers;

import com.innter.msdesigncatalog.dtos.request.DesingColorRequest;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.services.IDesingColorService;
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
@RequestMapping(value = "/api/desingColor")
public class DesingColorController {

    @Autowired
    private IDesingColorService desingColorService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingColor(@RequestBody DesingColorRequest newDesingColorRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingColorService.saveDesingColor(newDesingColorRequest),
                "El color se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingColor(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingColorService.getDesingColors(pageable),
                "Los colores se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingColor( @RequestBody DesingColorRequest desingColorRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingColorService.editedDesingColor(desingColorRequest,id),
                "El color con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingColorByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingColorService.editedDesingColorByStatus(desingRequestStatus,id),
                "El color con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

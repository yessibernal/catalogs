package com.innter.msdesigncatalog.controllers;
import com.innter.msdesigncatalog.dtos.request.DesignPrintRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.services.IDesignPrintService;
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
@RequestMapping(value = "/api/designPrint")
public class DesignPrintController {

    @Autowired
    private IDesignPrintService designPrintService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignPrint(@RequestBody DesignPrintRequest newDesignPrintRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designPrintService.saveDesignPrint(newDesignPrintRequest),
                "El estampado se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignPrint(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designPrintService.getDesignPrint(pageable),
                "Los estampados se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignPrint( @RequestBody DesignPrintRequest designPrintRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designPrintService.editedDesignPrint(designPrintRequest,id),
                "El estampado con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignPrintByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designPrintService.editedDesignPrintByStatus(designRequestStatus,id),
                "El estampado con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

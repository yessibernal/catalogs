package com.innter.msdesigncatalog.controllers;


import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import com.innter.msdesigncatalog.dtos.request.DesingTracePieceRequest;
import com.innter.msdesigncatalog.services.IDesingTracePieceService;
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
@RequestMapping(value = "/api/desingTracePiece")
public class DesingTracePieceController {
    @Autowired
    private IDesingTracePieceService desingTracePieceService;
    @Autowired
    private ResponseUtils responseUtils;


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesingTracePiece(@RequestBody DesingTracePieceRequest desingTracePieceRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(desingTracePieceService.saveDesingTracePiece(desingTracePieceRequest),
                "La pieza de trazo se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesingTracePieces(    @RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingTracePieceService.getDesingTracePieces(pageable),
                "Las piezas de trazo se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingTracePiece(@RequestBody DesingTracePieceRequest desingTracePieceRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingTracePieceService.editedDesingTracePiece(desingTracePieceRequest,id),
                "La pieza de trazo con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesingTracePieceByStatus(@RequestBody DesingRequestStatus desingRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(desingTracePieceService.editedDesingTracePieceByStatus(desingRequestStatus,id),
                "La pieza de trazo con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}

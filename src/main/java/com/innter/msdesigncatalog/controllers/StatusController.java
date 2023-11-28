package com.innter.msdesigncatalog.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String status(){
        return "{\"status\":\"ok\"}";
    }
}

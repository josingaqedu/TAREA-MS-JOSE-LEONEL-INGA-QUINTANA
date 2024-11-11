package com.ms_open_feign.ms_open_feign.controller;

import com.ms_open_feign.ms_open_feign.entity.CompanyEntity;
import com.ms_open_feign.ms_open_feign.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<CompanyEntity> getCompany(@RequestParam(name = "ruc") String ruc) {
        CompanyEntity companyEntity = companyService.getCompany(ruc);
        return new ResponseEntity<>(companyEntity, HttpStatus.OK);
    }
}

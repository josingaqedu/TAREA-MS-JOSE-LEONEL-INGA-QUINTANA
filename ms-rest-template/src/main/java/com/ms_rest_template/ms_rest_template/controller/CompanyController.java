package com.ms_rest_template.ms_rest_template.controller;

import com.ms_rest_template.ms_rest_template.entity.CompanyEntity;
import com.ms_rest_template.ms_rest_template.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

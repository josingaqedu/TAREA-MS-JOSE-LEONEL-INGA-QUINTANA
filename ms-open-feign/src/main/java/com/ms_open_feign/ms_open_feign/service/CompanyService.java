package com.ms_open_feign.ms_open_feign.service;

import com.ms_open_feign.ms_open_feign.entity.CompanyEntity;

public interface CompanyService {
    CompanyEntity getCompany(String ruc);
}

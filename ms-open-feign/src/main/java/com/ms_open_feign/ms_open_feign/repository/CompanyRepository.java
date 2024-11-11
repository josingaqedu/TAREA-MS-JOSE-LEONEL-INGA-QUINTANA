package com.ms_open_feign.ms_open_feign.repository;

import com.ms_open_feign.ms_open_feign.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByNumeroDocumento(String numeroDocumento);
}

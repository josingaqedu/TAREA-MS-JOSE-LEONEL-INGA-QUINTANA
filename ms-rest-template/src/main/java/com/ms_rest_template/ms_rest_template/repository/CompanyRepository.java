package com.ms_rest_template.ms_rest_template.repository;

import com.ms_rest_template.ms_rest_template.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByNumeroDocumento(String numeroDocumento);
}

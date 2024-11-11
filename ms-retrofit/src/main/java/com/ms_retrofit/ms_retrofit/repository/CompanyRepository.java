package com.ms_retrofit.ms_retrofit.repository;

import com.ms_retrofit.ms_retrofit.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByNumeroDocumento(String numeroDocumento);
}

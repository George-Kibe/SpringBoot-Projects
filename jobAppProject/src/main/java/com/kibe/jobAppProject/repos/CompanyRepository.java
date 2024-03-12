package com.kibe.jobAppProject.repos;

import com.kibe.jobAppProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
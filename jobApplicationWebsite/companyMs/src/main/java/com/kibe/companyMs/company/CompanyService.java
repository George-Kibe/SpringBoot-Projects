package com.kibe.companyMs.company;
import com.kibe.companyMs.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);
    boolean updateCompany(Long id, Company updatedCompany);

    boolean createCompany(Company company);

    boolean deleteCompanyById(Long id);
    public void updateCompanyRating(ReviewMessage reviewMessage);
}

package com.kibe.jobAppProject.services;

import com.kibe.jobAppProject.entity.Company;
import com.kibe.jobAppProject.entity.Job;
import com.kibe.jobAppProject.repos.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService{
    CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company tempCompany = companyOptional.get();
            tempCompany.setDescription(updatedCompany.getDescription());
            tempCompany.setName(updatedCompany.getName());
            tempCompany.setJobs(updatedCompany.getJobs());
            companyRepository.save(tempCompany);
            return true;
        }
        return false;
    }

    @Override
    public boolean createCompany(Company company) {
        try{
            companyRepository.save(company);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        // System.out.println("Encountered Exception: " + e);
        return false;
    }
}

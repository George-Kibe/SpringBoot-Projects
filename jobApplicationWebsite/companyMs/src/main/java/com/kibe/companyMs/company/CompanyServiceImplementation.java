package com.kibe.companyMs.company;
import com.kibe.companyMs.clients.ReviewClient;
import com.kibe.companyMs.dto.ReviewMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService{
    CompanyRepository companyRepository;
    ReviewClient reviewClient;
    public CompanyServiceImplementation(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
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
            // tempCompany.setJobs(updatedCompany.getJobs());
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

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println("Review Message: ");
        System.out.println(reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElse(null);
        if (company != null){
            double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
            company.setRating(averageRating);
            companyRepository.save(company);
        }else {
            System.out.println("Company with ID " + reviewMessage.getCompanyId() + "Not Found");
        }
    }
}


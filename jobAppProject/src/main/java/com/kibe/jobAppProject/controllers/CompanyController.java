package com.kibe.jobAppProject.controllers;

import com.kibe.jobAppProject.entity.Company;
import com.kibe.jobAppProject.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> findAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if (company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/companies")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        boolean created = companyService.createCompany(company);
        if (created) {
            return new ResponseEntity<>("Company created successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @PutMapping("/companies/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company){
        boolean updated = companyService.updateCompany(id,company);
        if(updated){
            return ResponseEntity.ok("Company Details updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted){
            return new ResponseEntity<>("Company of ID "+ id+" Deleted Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

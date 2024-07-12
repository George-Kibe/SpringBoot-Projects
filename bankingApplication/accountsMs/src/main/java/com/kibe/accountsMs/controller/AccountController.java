package com.kibe.accountsMs.controller;

import com.kibe.accountsMs.constants.AccountsConstants;
import com.kibe.accountsMs.dto.CustomerDTO;
import com.kibe.accountsMs.dto.ResponseDTO;
import com.kibe.accountsMs.service.AccountsService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {
    private AccountsService accountsService;
    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/health")
    public  ResponseEntity<String> getserverHealth(){
        return ResponseEntity.status(HttpStatus.OK).body("Server is running fine");
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp="(^$|[0-9]{12})",message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDTO customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
}

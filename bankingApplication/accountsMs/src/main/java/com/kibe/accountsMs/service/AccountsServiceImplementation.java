package com.kibe.accountsMs.service;

import com.kibe.accountsMs.dto.CustomerDTO;
import com.kibe.accountsMs.repository.AccountsRepository;
import com.kibe.accountsMs.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // its going to do constructor injection for the required beans
public class AccountsServiceImplementation implements AccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public boolean createAccount(CustomerDTO customerDTO) {
        return false;
    }
}

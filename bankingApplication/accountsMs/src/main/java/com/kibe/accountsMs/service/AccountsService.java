package com.kibe.accountsMs.service;

import com.kibe.accountsMs.dto.CustomerDTO;

public interface AccountsService {
    boolean createAccount(CustomerDTO customerDTO);
}

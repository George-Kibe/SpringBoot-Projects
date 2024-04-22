package com.kibe.accountsMs.service;

import com.kibe.accountsMs.constants.AccountsConstants;
import com.kibe.accountsMs.dto.AccountDTO;
import com.kibe.accountsMs.dto.CustomerDTO;
import com.kibe.accountsMs.entity.Account;
import com.kibe.accountsMs.entity.Customer;
import com.kibe.accountsMs.exception.CustomerAlreadyExistsException;
import com.kibe.accountsMs.exception.ResourceNotFoundException;
import com.kibe.accountsMs.mapper.AccountsMapper;
import com.kibe.accountsMs.mapper.CustomerMapper;
import com.kibe.accountsMs.repository.AccountsRepository;
import com.kibe.accountsMs.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor // its going to do constructor injection for the required beans
public class AccountsServiceImplementation implements AccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {
            Customer customer  = CustomerMapper.mapToCustomer(customerDTO, new Customer());
            Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
            if (optionalCustomer.isPresent()){
                throw new CustomerAlreadyExistsException("Customer already exists with Mobile Number: " + customerDTO.getMobileNumber());
            }
            Customer savedCustomer = customerRepository.save(customer);
            accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccountDTO(AccountsMapper.mapToAccountsDto(account, new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        return false;
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        return false;
    }

    // function to create a new account
    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 10000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber((randomAccNumber));
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        accountsRepository.save(newAccount);
        return newAccount;
    }
}

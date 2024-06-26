package com.kibe.accountsMs.mapper;

import com.kibe.accountsMs.dto.CustomerDTO;
import com.kibe.accountsMs.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDto(Customer customer, CustomerDTO customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

//    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
//        customerDetailsDto.setName(customer.getName());
//        customerDetailsDto.setEmail(customer.getEmail());
//        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
//        return customerDetailsDto;
//    }

    public static Customer mapToCustomer(CustomerDTO customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}
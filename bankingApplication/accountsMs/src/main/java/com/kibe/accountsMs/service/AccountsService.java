package com.kibe.accountsMs.service;

import com.kibe.accountsMs.dto.CustomerDTO;

public interface AccountsService {
    /**
     *
     * @param customerDTO - CustomerDto Object
     */

    void createAccount(CustomerDTO customerDTO);
    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDTO - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber - Long
     * @return boolean indicating if the update of communication status is successful or not
     */
    boolean updateCommunicationStatus(Long accountNumber);

}

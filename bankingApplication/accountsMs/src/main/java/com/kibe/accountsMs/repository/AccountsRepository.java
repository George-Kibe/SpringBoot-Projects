package com.kibe.accountsMs.repository;

import com.kibe.accountsMs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
}

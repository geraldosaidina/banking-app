package com.geraldosaidina.banking_app.repository;

import com.geraldosaidina.banking_app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

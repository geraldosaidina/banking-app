package com.geraldosaidina.banking_app.mapper;

import com.geraldosaidina.banking_app.dto.AccountDTO;
import com.geraldosaidina.banking_app.model.Account;

public class AccountMapper {

    public static Account maptoAccount(AccountDTO accountDTO) {
        Account account = new Account(
                accountDTO.getId(),
                accountDTO.getAccount_owner(),
                accountDTO.getBalance()
        );

        return account;
    }

    public static AccountDTO maptoAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                account.getAccount_owner(),
                account.getBalance()
        );

        return accountDTO;
    }
}

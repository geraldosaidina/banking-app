package com.geraldosaidina.banking_app.service.impl;

import com.geraldosaidina.banking_app.dto.AccountDTO;
import com.geraldosaidina.banking_app.mapper.AccountMapper;
import com.geraldosaidina.banking_app.model.Account;
import com.geraldosaidina.banking_app.repository.AccountRepository;
import com.geraldosaidina.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.maptoAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.maptoAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);


    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List <Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.maptoAccountDTO(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountRepository.deleteById(id);
    }
}

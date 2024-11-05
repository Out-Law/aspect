package com.example.aspecttask.services;

import com.example.aspecttask.annotations.LogDataSourceError;
import com.example.aspecttask.entities.Account;
import com.example.aspecttask.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @LogDataSourceError
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @LogDataSourceError
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    @LogDataSourceError
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @LogDataSourceError
    public Account updateAccount(Long id, Account account) {
        Account existingAccount = getAccountById(id);
        existingAccount.setClientId(account.getClientId());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        return accountRepository.save(existingAccount);
    }

    @LogDataSourceError
    public void deleteAccount(Long id) {
        Account existingAccount = getAccountById(id);
        accountRepository.delete(existingAccount);
    }
}

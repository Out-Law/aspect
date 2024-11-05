package com.example.aspecttask.services;

import com.example.aspecttask.entities.Transaction;
import com.example.aspecttask.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }

    public Transaction createTransaction(Transaction transaction) {
        accountService.getAccountById(transaction.getAccount().getId());
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = getTransactionById(id);
        existingTransaction.setId(transaction.getAccount().getId());
        existingTransaction.setTransactionAmount(transaction.getTransactionAmount());
        existingTransaction.setTransactionTime(transaction.getTransactionTime());
        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(Long id) {
        Transaction existingTransaction = getTransactionById(id);
        transactionRepository.delete(existingTransaction);
    }
}

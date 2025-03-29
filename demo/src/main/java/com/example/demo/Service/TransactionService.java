package com.example.demo.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Create a new transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setUserId(transactionDetails.getUserId());
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTransactionType(transactionDetails.getTransactionType());
            transaction.setTransactionDate(transactionDetails.getTransactionDate());
            transaction.setStatus(transactionDetails.getStatus());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }

    // Delete a transaction by ID
    public boolean deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get paginated transactions
    public Page<Transaction> getPaginatedTransactions(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size));
    }

    // Sorting
    public List<Transaction> getSortedTransactions(String field) {
        return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Get transactions by user ID
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Get transactions by status
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }

    // (JPQL)
    public List<Transaction> getRecentTransactions() {
        return transactionRepository.findRecentTransactions();
    }

    // Sorting transactions
    public List<Transaction> getSorted(String name) {
        return transactionRepository.findAll(Sort.by(Sort.Direction.ASC, name));
    }
}
package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        try {
            Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
            return ResponseEntity.ok(updatedTransaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionService.deleteTransaction(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get paginated transactions
    @GetMapping("/paginated")
    public Page<Transaction> getPaginatedTransactions(@RequestParam int page, @RequestParam int size) {
        return transactionService.getPaginatedTransactions(page, size);
    }
    // http://localhost:8081/api/transactions/paginated?page=0&size=2

    // Get transactions by user ID
    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
    //  http://localhost:8081/api/transactions/user/1100

    // Get transactions by status
    @GetMapping("/status/{status}")
    public List<Transaction> getTransactionsByStatus(@PathVariable String status) {
        return transactionService.getTransactionsByStatus(status);
    }
    //http://localhost:8081/api/transactions/status/COMPLETED

    // Get recent transactions (JPQL)
    @GetMapping("/recent")
    public List<Transaction> getRecentTransactions() {
        return transactionService.getRecentTransactions();
    }
    //  http://localhost:8081/api/transactions/recent

    // Get sorted transactions
    @GetMapping("/sort/{name}")
    public List<Transaction> getSorted(@PathVariable String name) {
        return transactionService.getSorted(name);
    }
    // http://localhost:8081/api/transactions/sort/amount
}

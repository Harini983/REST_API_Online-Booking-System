package com.example.demo.Repository;

import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find transactions by user ID
    List<Transaction> findByUserId(Long userId);

    // Find transactions by status
    List<Transaction> findByStatus(String status);

    // JPQL Query: Get recent transactions (sorted by transaction date descending)
    @Query("SELECT t FROM Transaction t ORDER BY t.transactionDate DESC")
    List<Transaction> findRecentTransactions();
}
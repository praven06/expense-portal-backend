package com.praveen.expportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.expportal.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Long>{
    List<Receipt> findByExpenseId(Long expenseId);
}

package com.praveen.expportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.expportal.models.ExpensePolicy;

@Repository
public interface ExpensepolicyRepository extends JpaRepository<ExpensePolicy,Long>{
    List<ExpensePolicy> findByCategoryId(Long categoryId);
}

package com.praveen.expportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.expportal.models.Approval;

@Repository
public interface ApprovalRepository  extends JpaRepository<Approval,Long>{
    
}

package com.praveen.expportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.expportal.models.Approval;
import com.praveen.expportal.repositories.ApprovalRepository;

@Service
public class ApprovalService {
    
    @Autowired
    private ApprovalRepository approvalRepository;

    public void createApproval(Approval approval) {
        approvalRepository.save(approval);
    }
}

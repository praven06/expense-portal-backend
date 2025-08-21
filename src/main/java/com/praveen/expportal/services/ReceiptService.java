package com.praveen.expportal.services;

import com.praveen.expportal.models.Receipt;
import com.praveen.expportal.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found with ID: " + id));
    }

    public List<Receipt> getReceiptsByExpenseId(Long expenseId) {
        return receiptRepository.findByExpenseId(expenseId);
    }

    public void deleteReceipt(Long id) {
        if (!receiptRepository.existsById(id)) {
            throw new RuntimeException("Receipt not found with ID: " + id);
        }
        receiptRepository.deleteById(id);
    }
}

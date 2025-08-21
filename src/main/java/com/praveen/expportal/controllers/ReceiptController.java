package com.praveen.expportal.controllers;

import com.praveen.expportal.models.Receipt;
import com.praveen.expportal.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping
    public Receipt uploadReceipt(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/{id}")
    public Receipt getReceiptById(@PathVariable Long id) {
        return receiptService.getReceiptById(id);
    }

    @GetMapping("/expense/{expenseId}")
    public List<Receipt> getReceiptsByExpense(@PathVariable Long expenseId) {
        return receiptService.getReceiptsByExpenseId(expenseId);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }
}

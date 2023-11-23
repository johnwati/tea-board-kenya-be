package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.PaymentMethod;
import com.springbootmicroservices.advertisement.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;




    // Create a new payment method
    @PostMapping
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    // Get all payment methods
    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    // Get payment method by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id) {
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(id);
        if (paymentMethod != null) {
            return ResponseEntity.ok(paymentMethod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update payment method by ID
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod updatedPaymentMethod) {
        PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(id, updatedPaymentMethod);
        if (paymentMethod != null) {
            return ResponseEntity.ok(paymentMethod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete payment method by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok().build();
    }

    // Get all payment methods with pagination
    @GetMapping("/pagination")
    public Page<PaymentMethod> getAllPaymentMethodsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<PaymentMethod> paymentMethodPage = paymentMethodService.getAllPaymentMethodsWithPagination(PageRequest.of(page, size));
        return paymentMethodPage;
    }

    // Search payment methods by name with pagination
    @GetMapping("/search")
    public Page<PaymentMethod> searchPaymentMethodsByName(@RequestParam String paymentMethodName,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<PaymentMethod> paymentMethodPage = paymentMethodService.searchPaymentMethodsByName(paymentMethodName, PageRequest.of(page, size));
        return paymentMethodPage;
    }
}

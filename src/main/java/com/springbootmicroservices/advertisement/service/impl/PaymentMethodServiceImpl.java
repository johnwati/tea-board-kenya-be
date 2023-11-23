package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.PaymentMethod;
import com.springbootmicroservices.advertisement.repository.PaymentMethodRepository;
import com.springbootmicroservices.advertisement.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethod updatedPaymentMethod) {
        updatedPaymentMethod.setPaymentMethodID(id);
        return paymentMethodRepository.save(updatedPaymentMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public Page<PaymentMethod> getAllPaymentMethodsWithPagination(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    @Override
    public Page<PaymentMethod> searchPaymentMethodsByName(String paymentMethodName, Pageable pageable) {
        return paymentMethodRepository.findByPaymentMethodNameContainingIgnoreCase(paymentMethodName, pageable);
    }

    @Override
    public PaymentMethod createOrUpdatePaymentMethod(String paymentMethodName) {
        return null;
    }
}

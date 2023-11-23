package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod getPaymentMethodById(Long id);

    PaymentMethod updatePaymentMethod(Long id, PaymentMethod updatedPaymentMethod);

    void deletePaymentMethod(Long id);

    Page<PaymentMethod> getAllPaymentMethodsWithPagination(Pageable pageable);

    Page<PaymentMethod> searchPaymentMethodsByName(String paymentMethodName, Pageable pageable);

    PaymentMethod createOrUpdatePaymentMethod(String paymentMethodName);
}

package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Page<PaymentMethod> findByPaymentMethodNameContainingIgnoreCase(String paymentMethodName, Pageable pageable);
}

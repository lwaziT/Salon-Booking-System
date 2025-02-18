package com.lwazi.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lwazi.payment_service.model.PaymentOrder;


public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {
    
    PaymentOrder findByPaymentLinkId(String paymentLinkId);
}

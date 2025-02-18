package com.lwazi.payment_service.service;

import com.lwazi.payment_service.domain.PaymentMethod;
import com.lwazi.payment_service.model.PaymentOrder;
import com.lwazi.payment_service.payload.payloadDTO.BookingDTO;
import com.lwazi.payment_service.payload.payloadDTO.UserDTO;
import com.lwazi.payment_service.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    
    PaymentLinkResponse createOrder(UserDTO user,BookingDTO booking, PaymentMethod paymentMethod) throws Exception;
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);
    PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) throws RazorpayException;
    String createStripePaymentLink(UserDTO user, Long amount, Long orderId) throws StripeException;
    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;
}

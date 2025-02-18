package com.lwazi.payment_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.payment_service.domain.PaymentMethod;
import com.lwazi.payment_service.model.PaymentOrder;
import com.lwazi.payment_service.payload.payloadDTO.BookingDTO;
import com.lwazi.payment_service.payload.payloadDTO.UserDTO;
import com.lwazi.payment_service.payload.response.PaymentLinkResponse;
import com.lwazi.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO booking, 
    @RequestParam PaymentMethod paymentMethod) throws Exception {

        UserDTO user = new UserDTO();
        user.setFullname("Patience Dlamini");
        user.setEmail("pdlamini@quantumqube.co.za");
        user.setId(1L);

        PaymentLinkResponse response = this.paymentService.createOrder(user, booking, paymentMethod);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId) throws Exception {

        PaymentOrder response = this.paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{proceed}")
    public ResponseEntity<Boolean> proceedPayment(@RequestParam String paymentId, @RequestParam String paymentLinkId) throws RazorpayException {

        PaymentOrder paymentOrder = this.paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean response = this.paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
        return ResponseEntity.ok(response);
    }
}

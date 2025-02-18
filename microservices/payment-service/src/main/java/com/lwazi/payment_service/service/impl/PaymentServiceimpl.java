package com.lwazi.payment_service.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lwazi.payment_service.domain.PaymentMethod;
import com.lwazi.payment_service.domain.PaymentOrderStatus;
import com.lwazi.payment_service.model.PaymentOrder;
import com.lwazi.payment_service.payload.payloadDTO.BookingDTO;
import com.lwazi.payment_service.payload.payloadDTO.UserDTO;
import com.lwazi.payment_service.payload.response.PaymentLinkResponse;
import com.lwazi.payment_service.repository.PaymentOrderRepository;
import com.lwazi.payment_service.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceimpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorApiSecret;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws Exception {

       Long amount = booking.getTotalPrice();

       PaymentOrder order = new PaymentOrder();
       order.setAmount(amount);
       order.setPaymentMethod(paymentMethod);
       order.setBookingId(booking.getId());
       order.setSalonId(booking.getSalonId());

       PaymentOrder savedOrder = this.paymentOrderRepository.save(order);

       PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

       if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {

            PaymentLink payment = this.createRazorpayPaymentLink(user, 
            savedOrder.getAmount(), 
            savedOrder.getId());

            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");

            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);

            savedOrder.setPaymentLinkId(paymentUrlId);

            this.paymentOrderRepository.save(savedOrder);
       } else {

        String paymentUrl = this.createStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getId());
        paymentLinkResponse.setPayment_link_url(paymentUrl);

       }

       return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {

        PaymentOrder paymentOrder = this.paymentOrderRepository.findById(id).orElse(null);
        if(paymentOrder.equals(null)) {
            throw new Exception("Payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {

        return this.paymentOrderRepository.findByPaymentLinkId(paymentId);

    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO user, Long paymentAmount, Long orderId) throws RazorpayException {

        Long amount = paymentAmount * 100;

        RazorpayClient razorpayClient = new RazorpayClient(this.razorpayApiKey, this.razorApiSecret);

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "ZAR");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getFullname());
        customer.put("email", user.getEmail());

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);

        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("reminder_enable", true);
        paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success/" + orderId);
        paymentLinkRequest.put("callback_method", "get");

        return razorpayClient.paymentLink.create(paymentLinkRequest);
    }

    @Override
    public String createStripePaymentLink(UserDTO user, Long amount, Long orderId) throws StripeException {

        Stripe.apiKey = this.stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                                     .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                                     .setMode(SessionCreateParams.Mode.PAYMENT)
                                     .setSuccessUrl("http://localhost:3000/payment-succes/" + orderId)
                                     .setCancelUrl("http://localhost:3000/cancel")
                                     .addLineItem(SessionCreateParams.LineItem.builder()
                                     .setQuantity(1L)
                                     .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                     .setCurrency("USD")
                                     .setUnitAmount(amount * 100)
                                     .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                     .setName("Salon appointment booking").build()).build()).build()).build();
                                    

        Session session = Session.create(params);
        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {

            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {

                RazorpayClient razorpay = new RazorpayClient(this.razorpayApiKey, this.razorApiSecret);
                Payment payment = razorpay.payments.fetch(paymentId);
                Integer amount = payment.get("amount");
                String status = payment.get("status");

                if(status.equals("captured")) {

                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    this.paymentOrderRepository.save(paymentOrder);

                    return true;
                }
                return false;

            } else {

                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                this.paymentOrderRepository.save(paymentOrder);

                return true;
            }
        }

        return false;
    }
    
}

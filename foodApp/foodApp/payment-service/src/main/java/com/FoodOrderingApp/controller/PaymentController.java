package com.FoodOrderingApp.controller;

import com.FoodOrderingApp.model.Payment;
import com.FoodOrderingApp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<Object> processPayment(@RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(payment.getUserId(), payment.getAmount());
        return ResponseEntity.ok(processedPayment);
    }
}

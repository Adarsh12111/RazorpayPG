package com.razorpay.implementation.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.razorpay.implementation.dto.InitiatedOrderDTO;
import com.razorpay.implementation.service.PaymentGatewayImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/payment")
public class RazorPayPaymentController {

    @Autowired
    private PaymentGatewayImplementationService paymentGatewayImplementationService;

    @GetMapping(path = "/initiated/{amount}/{name}")
    public String initiatedOrder(@PathVariable(value = "amount") Integer amount ,
                                @PathVariable(value = "name") String name) throws RazorpayException {

        InitiatedOrderDTO initiatedOrderDTO = new InitiatedOrderDTO();
        initiatedOrderDTO.setAmount(amount);
        initiatedOrderDTO.setFirst(name);
        initiatedOrderDTO.setLastName("Tripathi");
        return paymentGatewayImplementationService.createOrder(initiatedOrderDTO);
    }
}

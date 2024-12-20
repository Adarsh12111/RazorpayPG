package com.razorpay.implementation.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.razorpay.implementation.dto.InitiatedOrderDTO;
import com.razorpay.implementation.service.PaymentGatewayImplementationService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/payment")
public class RazorPayPaymentController {

    private static  final Logger log = LoggerFactory.getLogger(RazorPayPaymentController.class);


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


    @PostMapping("/callback")
    public String handleCallBack(@RequestBody(required = false) String payload){
        try {
            // Parse the JSON payload
            JSONObject json = new JSONObject(payload);
            String event = json.getString("event");

            log.info("payment payload {}",payload);

            if ("payment.captured".equals(event)) {
                String paymentId = json.getJSONObject("payload").getJSONObject("payment").getString("id");
                // Process the payment ID (update database, send email, etc.)
                System.out.println("Payment captured: " + paymentId);
            }
            return "Callback processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Callback failed";
        }
    }
}

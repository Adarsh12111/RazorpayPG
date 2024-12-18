package com.razorpay.implementation.serviceImpl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.implementation.dto.InitiatedOrderDTO;
import com.razorpay.implementation.model.RazorpayPaymentsConstants;
import com.razorpay.implementation.service.PaymentGatewayImplementationService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentGatewayImplementationServiceImpl implements PaymentGatewayImplementationService {

    private static  final String RAZOR_PAY_CLIENT_ID = "rzp_test_0nYQCzhYoVNiW0";
    private static final String  RAZOR_PAY_CLIENT_SECRET = "O4muLvulsOpguhs6xe8Nd2W9";

    public String  createOrder(InitiatedOrderDTO initiatedOrderDTO) throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient(RAZOR_PAY_CLIENT_ID,RAZOR_PAY_CLIENT_SECRET);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",initiatedOrderDTO.getAmount() * 100);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "QUP_APP@121");
        JSONObject notes = new JSONObject();

        Order order = razorpayClient.orders.create(orderRequest);

        System.out.println(order.toString());

        return order.toString();


    }
}

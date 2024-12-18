package com.razorpay.implementation.service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.razorpay.implementation.dto.InitiatedOrderDTO;

import java.util.Map;

public interface PaymentGatewayImplementationService {

    String  createOrder(InitiatedOrderDTO initiatedOrderDTO) throws RazorpayException;
}

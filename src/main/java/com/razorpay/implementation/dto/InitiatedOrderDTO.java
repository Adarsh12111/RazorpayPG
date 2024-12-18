package com.razorpay.implementation.dto;

import lombok.Data;

@Data
public class InitiatedOrderDTO {
    private Integer amount;
    private String first;
    private String lastName;
}

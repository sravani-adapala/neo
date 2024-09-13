package com.neoteric.fullstackdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class Atm {

    private String cardNumber;
    private String pin;
    private String cvv;
    private Date cardExpiry;
    private String accountNumber;
}

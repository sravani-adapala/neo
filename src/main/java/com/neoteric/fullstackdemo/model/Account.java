package com.neoteric.fullstackdemo.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

    private String accountNumber;
    private String name;
    private String pan;
    private String mobileNumber;
    private double balance;
    private  Address address;


}

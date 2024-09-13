package com.neoteric.fullstackdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private  String add1;

    private  String add2;
    private  String pincode;
    private  String city;
    private  String state;


}

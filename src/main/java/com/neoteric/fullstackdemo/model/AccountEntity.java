package com.neoteric.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="account",schema = "bank")
@Data
public class AccountEntity {

    public  AccountEntity(){

    }

    @Id
    @Column(name="accountnumber")
    private String accountNumber;

    @Column(name="name", nullable= false)
    private String name;

    @Column(name="pan", nullable= false)
    private String pan;

    @Column(name="mobilenumber" ,nullable=false)
    private String mobileNumber;

    @Column(name="balance", nullable=false)
    private double balance;
//
//    @Column(name="address")
//    private String address;

    @OneToMany(mappedBy = "accountEntity",cascade = CascadeType.PERSIST)
    public List<AccountAddressEntity> accountAddressEntityList;

}
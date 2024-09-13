package com.neoteric.fullstackdemo.controller;


import com.neoteric.fullstackdemo.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.service.AccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class AccountController {
    @PostMapping(value = "/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account) throws AccountCreationFailedException {

        AccountService accountService=new AccountService();

        String accountNumber=   accountService.createAccount(account);
        account.setAccountNumber(accountNumber);
        return account;
    }

    @PostMapping(value = "/api/createAccountUsingUI",consumes = "application/json",produces = "application/json")
    public String  oneToManyUsingHibernateFromUI(@RequestBody Account account) throws AccountCreationFailedException   {
        AccountService accountService = new AccountService();

        String accNO=accountService.oneToManyUsingHibernateFromUI(account);
        account.setAccountNumber(accNO);
        return account.getAccountNumber();

    }


}

package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.exception.AtmCreationFailedException;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.Atm;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AtmService {

    private Connection connection;

    public AtmService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }

    public Atm createAtm(Account account) throws Exception {
        Atm atm = null;

        try {
            Statement stmt = connection.createStatement();
            String checkAccountQuery = "SELECT * FROM bank.account WHERE accountnumber = '" + account.getAccountNumber() + "'";
            ResultSet rs = stmt.executeQuery(checkAccountQuery);
            if (!rs.next()) {
                throw new AccountNotFoundException("Account Number " + account.getAccountNumber() + " is not found");
            }

            String cardNumber = UUID.randomUUID().toString();
            String pin = String.valueOf((int) (Math.random() * 9000) + 1000);
            String cvv = String.valueOf((int) (Math.random() * 900) + 100);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 3);
            Date expiry = calendar.getTime();

            // Convert java.util.Date to java.sql.Timestamp
            Timestamp expiryTimestamp = new Timestamp(expiry.getTime());

            String insertAtmQuery = "INSERT INTO bank.atm (cardnumber, pin, cvv, expiry, accountnumber) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertAtmQuery)) {
                pstmt.setString(1, cardNumber);
                pstmt.setString(2, pin);
                pstmt.setString(3, cvv);
                pstmt.setTimestamp(4, expiryTimestamp);
                pstmt.setString(5, account.getAccountNumber());

                int status = pstmt.executeUpdate();

                if (status == 1) {
                    atm = new Atm(cardNumber,pin,cvv,expiry,account.getAccountNumber());
                    System.out.println("ATM is Created with card number: " + cardNumber);
                } else {
                    throw new AtmCreationFailedException("ATM creation failed for account number: " + account.getAccountNumber());
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception Occurred: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("Account Not Found: " + e.getMessage());
            throw e;
        }

        return atm;
    }
}

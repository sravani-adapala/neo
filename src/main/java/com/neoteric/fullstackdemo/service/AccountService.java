package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountAddressEntity;
import com.neoteric.fullstackdemo.model.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

    public String oneToManyUsingHibernateFromUI(Account account){
        SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNumber(  UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());

        List<AccountAddressEntity> accountAddressEntityList=new ArrayList<>();

        AccountAddressEntity accountAddressEntity=new AccountAddressEntity();

        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setStatus(1);

        accountEntity.setAccountAddressEntityList(accountAddressEntityList);

        session.persist(accountEntity);
        transaction.commit();

        return accountEntity.getAccountNumber();


    }


   /* public String createAccountUsingHibernate(Account account){
        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

        Transaction transaction=session.getTransaction();

        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNumber(  UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());
        session.persist(accountEntity);
        transaction.commit();

        return accountEntity.getAccountNumber();


    }*/

    public String createAccount(Account account) throws  AccountCreationFailedException {

        String accountNumber = null;
        try {



            Connection connection = DBConnection.getConnection();

            Statement stmt = connection.createStatement();

            accountNumber = UUID.randomUUID().toString();

            String query = "INSERT INTO bank.account (account_number, name, pan, balance, mobile_number) VALUES ("
                    + "'" + accountNumber + "', "
                    + "'" + account.getName() + "', "
                    + "'" + account.getPan() + "', "
                    + account.getBalance() + ", "
                    + "'" + account.getMobileNumber() + "')";
            int status = stmt.executeUpdate(query);



            if (status == 1) {
                System.out.println(" Account is created "+accountNumber);
            }else {
                throw new AccountCreationFailedException("Account creation is failed " + account.getPan());

            }



        } catch (SQLException e) {
            System.out.println(" Exception occured in sql exception " + e);
        }
        catch(AccountCreationFailedException e) {
            System.out.println("Exception occured " + e);
            throw e;
        }
        return accountNumber;
    }
}
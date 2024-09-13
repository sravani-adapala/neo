package com.neoteric.fullstackdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connection;


    public static Connection getConnection() {


        try {
            if(connection == null){

                System.out.println("Getting Connection");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://@localhost:3306/bank", "root", "root");


            }
            else{
                System.out.println("Returning existing in getConnection ");
            }

        } catch (Exception e) {
  //swallowing the exception
            System.out.println(" Existing Occurred in getConnection "+e);

        }
        return connection;
    }


}

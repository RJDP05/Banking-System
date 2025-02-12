package com.bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {

    private Connection connection;
    private Scanner scanner;
    private PreparedStatement preparedStatement;

    public User(Connection connection, Scanner scanner, PreparedStatement preparedStatement){
        this.connection = connection;
        this.scanner = scanner;
        this.preparedStatement = preparedStatement;
    }


    public void register(){
        scanner.nextLine();
        System.out.print("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.print("Contact Number: ");
        String number = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if(user_exist(number)) {
            System.out.println();
            System.out.println("User Already Exists for this Contact Number Address!!");
            return;
        }

        String register_query = BasicSQL.REGISTER;

        try {
            preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, password);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println();
                System.out.println("Registration Successful!");
            } else {
                System.out.println();
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String login(){

        scanner.nextLine();
        System.out.print("Number: ");
        String number = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String login_query = BasicSQL.LOGIN;

        try{
            preparedStatement = connection.prepareStatement(login_query);
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return number;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean user_exist( String number ){

        String query = BasicSQL.USER_EXIST;
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}


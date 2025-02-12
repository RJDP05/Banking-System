package com.bankingsystem;

import java.sql.*;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {

        //Driver Loading
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.err.println("Error: MySQL JDBC Driver not found. " + e.getMessage());
        }


        try {
            //Connection
            Connection con = DriverManager.getConnection(BasicSQL.SQL_URL,BasicSQL.USERNAME,BasicSQL.PASSWORD);
            System.out.println("DataBase Connected!!");

            Scanner scanner = new Scanner(System.in);

            // Create the PreparedStatement once
            String query = "SELECT * FROM users WHERE full_name = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            User user = new User(con, scanner, preparedStatement);
            Accounts accounts = new Accounts(con, scanner, preparedStatement);
            AccountManager accountManager = new AccountManager(con, scanner, preparedStatement);

            String number;
            long account_number;

            while(true){
                System.out.println();
                System.out.println("*** WELCOME TO BANKING SYSTEM ***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice: ");
                int choice1 = scanner.nextInt();
                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        number = user.login();
                        if(number!=null){
                            System.out.println();
                            System.out.println("User Logged In!");
                            if(!accounts.account_exist(number)){
                                System.out.println();
                                System.out.println("1. Open a new Bank Account");
                                System.out.println("2. Exit");
                                System.out.println("Enter your choice: ");
                                if(scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(number);
                                    System.out.println();
                                    System.out.println("Account Created Successfully");
                                    System.out.println("Your Account Number is: " + account_number);
                                }else{
                                    break;
                                }

                            }

                            account_number = accounts.getAccount_number(number);
                            int choice2 = 0;
                            while (choice2 != 5){
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.println("Enter your choice: ");
                                choice2 = scanner.nextInt();
                                switch (choice2) {
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println();
                                        System.out.println("Enter Valid Choice!");
                                        break;
                                }
                            }

                        }
                        else{
                            System.out.println();
                            System.out.println("Incorrect Number or Password!");
                        }

                    case 3:
                        preparedStatement.close();
                        scanner.close();
                        con.close();
                        System.out.println();
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting System!");
                        return;

                    default:
                        System.out.println();
                        System.out.println("Enter Valid Choice");
                        break;
                }
            }

        }catch (SQLException e){
            System.out.println();
        }
    }
}

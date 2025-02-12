package com.bankingsystem;

public class BasicSQL {
    protected static final String SQL_URL = "jdbc:mysql://localhost:3306/bank-db";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "Rjdp@123";
    protected static final String REGISTER = "INSERT INTO User(full_name, number, password) VALUES(?, ?, ?)";
    protected static final String LOGIN =  "SELECT * FROM User WHERE number = ? AND password = ?";
    protected static final String USER_EXIST = "SELECT * FROM user WHERE number = ?";
    protected static final String OPEN_ACC = "INSERT INTO accounts(account_number, full_name, email, number, balance, security_pin) VALUES(?, ?, ?, ?, ?, ?)";
    protected static final String GET_ACC = "SELECT account_number from accounts WHERE number = ?";
    protected static final String CHECK = "SELECT COUNT(*) FROM accounts WHERE account_number = ?";
    protected static final String ACC_EXIST = "SELECT account_number from accounts WHERE number = ?";
    protected static final String ACC_CHECK = "SELECT * FROM accounts WHERE account_number = ? and security_pin = ?";
    protected static final String CREDIT = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
    protected static final String DEBIT = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
    protected static final String CHECK_BALANCE = "SELECT balance FROM accounts WHERE account_number = ? AND security_pin = ?";

}

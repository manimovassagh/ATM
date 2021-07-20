package io.mani.models;

import java.util.ArrayList;

public class Account {
    /**
     * define ID for each Account
     */
    private String accountID;
    /**
     * define name for each account
     */
    private String AccountName;
    /**
     * specify balance for each account
     */
    private double balance;
    /**
     * The User that own this account
     */
    private User AccountHolder;
    /**
     * The list of transactions for this account
     */
    private ArrayList<Transaction> transactions;


    public Account(String accountName, User accountHolder, Bank bank) {
        //set the account name and account holder
        this.AccountName = accountName;
        this.AccountHolder = accountHolder;
        // define new account UUID
        this.accountID = bank.getNewAccountUUId();
    }
}

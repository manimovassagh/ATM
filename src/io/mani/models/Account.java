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
    private String accountName;

    /**
     * The User that own this account
     */
    private User accountHolder;
    /**
     * The list of transactions for this account
     */
    private ArrayList<Transaction> transactions;


    public Account(String accountName, User accountHolder, Bank bank) {
        //set the account name and account holder
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        // define new account UUID
        this.accountID = bank.getNewAccountUUId();
        //initial a list of transactions
        this.transactions = new ArrayList<Transaction>();



    }

    public String getUUID() {
        return this.accountID;
    }
}

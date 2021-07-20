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
    private String name;
    /**
     * specify balance for each account
     */
    private double balance;
    /**
     * The User that own this account
     */
    private User Holder;
    /**
     * The list of transactions for this account
     */
    private ArrayList<Transaction> transactions;


}

package io.mani.models;

import java.util.ArrayList;

public class Bank {
    /**
     * the name of bank
     */
    private String name;
    /**
     * the bank customer list
     */
    private ArrayList<User> customersList;
    /**
     * the bank account list
     */
    private ArrayList<Account> accounts;

    public String getNewUserUUId() {
       return "1";
    }
}

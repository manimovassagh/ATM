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
        //must be define later
       return "1";
    }

    public String getNewAccountUUId() {
        //must be define later
        return "2";
    }
}

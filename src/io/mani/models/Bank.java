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

    /**
     * get the user uuid
     * @return user uuid
     */
    public String getNewUserUUId() {
        //must be define later
       return "1";
    }

    /**
     * get the account uuid
     * @return account uuid
     */
    public String getNewAccountUUId() {
        //must be define later
        return "2";
    }

    /**
     * add account to accounts list
     * @param account specific account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}

package io.mani.models;

import java.util.ArrayList;
import java.util.Random;

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
     *
     * @return user uuid
     */
    public String getNewUserUUId() {

        //initialize the return values for this method
        String uuid = null;
        Random randomNumber = new Random();
        int lengthOfGeneratedNumber = 6;
        boolean nonUnique;
        do {
            //generate number uuid
            uuid = "";
            for (int i = 0; i < lengthOfGeneratedNumber; i++) {
                uuid += ((Integer) randomNumber.nextInt(10)).toString();
            }
            //check if it is  unique or not
            nonUnique = false;
            for (User user : this.customersList) {
                if (uuid.compareTo(user.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);
        return uuid;

    }

    /**
     * get the account uuid
     *
     * @return account uuid
     */
    public String getNewAccountUUId() {
        //initialize the return values for this method
        String uuid = null;
        Random randomNumber = new Random();
        int lengthOfGeneratedNumber = 6;
        boolean nonUnique;
        do {
            //generate number uuid
            uuid = "";
            for (int i = 0; i < lengthOfGeneratedNumber; i++) {
                uuid += ((Integer) randomNumber.nextInt(10)).toString();
            }
            //check if it is  unique or not
            nonUnique = false;
            for (Account account : this.accounts) {
                if (uuid.compareTo(account.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);
        return uuid;
    }

    /**
     * add account to accounts list
     *
     * @param account specific account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}

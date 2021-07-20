package io.mani.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This is model any User who use our service
 */
public class User {
    /**
     * The ID number of the user
     */
    private String uuid;
    /**
     * The first name of the user
     */
    private String firstName;
    /**
     * the last name of the user
     */
    private String lastName;
    /**
     * The Hash of user pin number
     */
    private int pinHash[];
    /**
     * The list of accounts for this user
     */
    private ArrayList<Account> accounts;

    /**
     * This constructor generate our user instance in the way that we want
     * @param firstName specify the first name of the user
     * @param lastName specify the last name of the user
     * @param pin generate the pin with a hash map built in class (learned from tutorial)
     * @param bankName specify the name of the bank
     */
    public User(String firstName, String lastName, String pin, Bank bankName) {

        //set user name
        this.firstName = firstName;
        this.lastName = lastName;
        //hashing the pin number with MD5 hash, This is to prevent using original pin
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("The Entered Method not recognised. Please Enter a valid Method!");
            e.printStackTrace();
//          System.exit(1);
                    }
    }
}

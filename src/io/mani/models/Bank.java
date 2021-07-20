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
     * Create our new bank objects
     * create an empty list of customers
     * create an empty list of accounts
     * @param bankName the name of our bank object
     */
    public Bank(String bankName) {
        this.name = bankName;
        this.customersList = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

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
     * add account to accounts array list
     * @param account account will be added to accounts list
     */
    public void addAccount(Account account){
        this.accounts.add(account);
    }

    /**
     * define a new user for the bank to register
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param pin pin number of the user
     * @return  The new defined User object
     */
    public User addUser(String firstName,String lastName ,String pin){
        //create a new user object and add it the defined list of users (customerList)
        User newUser = new User(firstName, lastName, pin,this);
        this.customersList.add(newUser);
        //create a saving account for each specified user
        Account newAccount = new Account("Saving Money",newUser,this);

        //add this object to the bank and to account holder , Bank and User
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);
        return newUser;
    }

    /**
     * this method check the credibility of user
     * @param userID UUID of the user
     * @param pin pin number of the user
     * @return User object in successful login otherwise return null
     */
    public User userLogin(String userID, String pin){
        for (User user :this.customersList){
            if (user.getUUID().compareTo(userID)==0 && user.validatePin(pin)){
                return user;
            }

        }
        //if the user could not pass the login process we return null
        return null;
    }

    /**
     * get the name of the bank
     * @return the name of the bank
     */
    public String getName() {
        return this.name;
    }

}

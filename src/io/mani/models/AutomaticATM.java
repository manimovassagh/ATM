package io.mani.models;

import java.util.Scanner;

public class AutomaticATM {
    public static void main(String[] args) {
        //initialize scanner

        Scanner scanner = new Scanner(System.in);

        // initialize Bank
        Bank theBank = new Bank("Sparkasse Mainz");

        // add a user who can register new account
        User aUser = theBank.addUser("Mani", "Movassagh", "5522");

        // add a checking account
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User currentUser;
        while (true){
            //stay in the login prompt until it successfully achieved
            currentUser = AutomaticATM.mainMenuPrompt(theBank, scanner);

            // stay in main menu until user quit from the process
            AutomaticATM.printUserMenu(currentUser, scanner);
        }
    }

    public static User mainMenuPrompt (Bank theBank, Scanner scanner){
        //initial local variables
        String userID;
        String pin;
        User authUser;

        //prompt user for user id and pin both until correct input
        do {
            System.out.printf("\n\nWelcome To %s\n\n ",theBank.getName());
        } while ()
    }

}

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
        while (true) {
            //stay in the login prompt until it successfully achieved
            currentUser = AutomaticATM.mainMenuPrompt(theBank, scanner);

            // stay in main menu until user quit from the process
            AutomaticATM.printUserMenu(currentUser, scanner);
        }
    }


    /**
     * static method to make an interface for the user for login and authentication
     *
     * @param theBank the bank object
     * @param scanner input gateway
     * @return authenticated user
     */
    public static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        //initial local variables
        String userID;
        String pin;
        User authUser;

        //prompt user for user id and pin both until correct input
        do {
            System.out.printf("\n\nWelcome To %s\n\n ", theBank.getName());
            System.out.print("Enter the User ID: ");
            userID = scanner.nextLine();
            System.out.print("Enter Pin:");
            pin = scanner.nextLine();

            // try to get the user id base on given user and pin
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect User ID or Pin number. " + "Please try again");
            }
            //continue the loop until the user give correct user and pin
        } while (authUser == null);
        return authUser;
    }

    /**
     * print the user menu interface
     *
     * @param theUser user
     * @param scanner input gateway
     */
    public static void printUserMenu(User theUser, Scanner scanner) {

        //print a summary of user accounts
        theUser.printAccountsSummary();
        //init
        int choice;
        //user menu
        do {
            System.out.printf("Welcome %s ,What can i do for You? ", theUser.getFirstName());
            System.out.println(" 1-Show Transaction history ");
            System.out.println(" 2-Withdrawl ");
            System.out.println(" 3-Make a Deposit ");
            System.out.println(" 3-Transfer ");
            System.out.println();
            System.out.println("Enter choice: ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("You entered an invalid choice, The valid range is between 1 to 5 !");
            }
        }
        while (choice < 1 || choice > 5);
        {
            switch (choice) {
                case 1:
                    AutomaticATM.showTransactionHistory(theUser, scanner);
                    break;
                case 2:
                    AutomaticATM.withdrawl(theUser, scanner);
                    break;
                case 3:
                    AutomaticATM.depositFounds(theUser, scanner);
                    break;
                case 4:
                    AutomaticATM.transferFunds(theUser, scanner);
                    break;

            }
            //redisplay the menu until the user wants to quit
            if (choice != 5) {
                AutomaticATM.printUserMenu(theUser, scanner);
            }
        }

    }

    /**
     *transfer funds from an account to another
     * @param theUser user
     * @param scanner input gateway
     */
    private static void transferFunds(User theUser, Scanner scanner) {

        //init
        int fromAccount;
        int toAccount;
        double amount;
       double accountBalance;
       // get the accounts to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n " +
                    "to transfer from :");
            fromAccount = scanner.nextInt()-1;
            if (fromAccount<0 || fromAccount>=theUser.numberOfAccounts()){
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (fromAccount < 0 || fromAccount >= theUser.numberOfAccounts());
        accountBalance = theUser.getAccountBalance(fromAccount);


    }

    /**
     *
     * @param theUser
     * @param scanner
     */
    private static void depositFounds(User theUser, Scanner scanner) {
    }


    /**
     *
     * @param theUser
     * @param scanner
     */
    private static void withdrawl(User theUser, Scanner scanner) {

    }

    /**
     *show the transaction history for each specific account
     * @param theUser user
     * @param scanner input gateway
     */
    private static void showTransactionHistory(User theUser, Scanner scanner) {
        int theAccount;
        //get accounts whose transition history to look at
        do {
            System.out.printf("Enter the  number (1-%d) of the account\n " + "whose transaction you want to check ? ", theUser.numberOfAccounts());
            theAccount = scanner.nextInt() - 1;
            if (theAccount < 0 || theAccount >= theUser.numberOfAccounts()) {
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (theAccount < 0 || theAccount >= theUser.numberOfAccounts());
        //print the transaction history
        theUser.printAccountTransactionHistory(theAccount);

    }

}

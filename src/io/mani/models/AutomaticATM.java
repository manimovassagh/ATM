package io.mani.models;

import java.util.Scanner;

public class AutomaticATM {
    public static void main(String[] args) {
        //initialize scanner

        Scanner scanner = new Scanner(System.in);

        // initialize Bank
        Bank theBank = new Bank("Sparkasse Mainz");

        // add a user who can register new account
        User aUser = theBank.addUser("Mani", "Movassagh", "4427");

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
            System.out.printf("Welcome %s ,What can i do for You?\n", theUser.getFirstName());
            System.out.println(" 1-Show Transaction history ");
            System.out.println(" 2-Withdrawl ");
            System.out.println(" 3-Make a Deposit ");
            System.out.println(" 4-Transfer ");
            System.out.println(" 5-Quit ");
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
                case 5:
                    scanner.nextLine();
                    break;

            }
            //redisplay the menu until the user wants to quit
            if (choice != 5) {
                AutomaticATM.printUserMenu(theUser, scanner);
            }
        }

    }

    /**
     * transfer funds from an account to another
     *
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
                    "to transfer from :", theUser.numberOfAccounts());
            fromAccount = scanner.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= theUser.numberOfAccounts()) {
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (fromAccount < 0 || fromAccount >= theUser.numberOfAccounts());
        accountBalance = theUser.getAccountBalance(fromAccount);
        // get the accounts to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account \n " +
                    "to transfer to :", theUser.numberOfAccounts());
            toAccount = scanner.nextInt() - 1;
            if (toAccount < 0 || toAccount >= theUser.numberOfAccounts()) {
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (toAccount < 0 || toAccount >= theUser.numberOfAccounts());

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount of your desire transfer (max €%.02f)", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Negative amount is not allowed !!");
            } else if (amount > accountBalance) {
                System.out.printf("You have not sufficient deposit to do this transaction !\n" +
                        "It can not be greater than €%.02f.\n ", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);
        // finally do the transfer
        theUser.addAccountTransaction(fromAccount, -1 * amount, String.format(
                "Transfer to Account %s", theUser.getAccountUUID(toAccount)
        ));
        theUser.addAccountTransaction(toAccount, amount, String.format(
                "Transfer to Account %s", theUser.getAccountUUID(fromAccount)
        ));


    }

    /**
     * @param theUser
     * @param scanner
     */
    private static void depositFounds(User theUser, Scanner scanner) {
//init
        int toAccount;
        double amount;
        double accountBalance;
        String memo;
        // get the accounts to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n " + "to deposit in: ", theUser.numberOfAccounts());
            toAccount = scanner.nextInt() - 1;
            if (toAccount < 0 || toAccount >= theUser.numberOfAccounts()) {
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (toAccount < 0 || toAccount >= theUser.numberOfAccounts());
        accountBalance = theUser.getAccountBalance(toAccount);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount of your desire transfer (max €%.02f)", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Negative amount is not allowed !!");
            }
        } while (amount < 0);

        scanner.nextLine();
        System.out.println("Enter the memo :");
        memo = scanner.nextLine();
        // do the withdrawl
        theUser.addAccountTransaction(toAccount, amount, memo);

    }


    /**
     * withdraw the account
     *
     * @param theUser user
     * @param scanner input gateway
     */
    private static void withdrawl(User theUser, Scanner scanner) {
        //init
        int fromAccount;
        double amount;
        double accountBalance;
        String memo;
        // get the accounts to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n " +
                    "to withdraw from: :", theUser.numberOfAccounts());
            fromAccount = scanner.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= theUser.numberOfAccounts()) {
                System.out.println("Invalid account Number , Please try again .");
            }
        } while (fromAccount < 0 || fromAccount >= theUser.numberOfAccounts());
        accountBalance = theUser.getAccountBalance(fromAccount);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount of withdraw (max €%.02f)", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Negative amount is not allowed !!");
            } else if (amount > accountBalance) {
                System.out.printf("You have not sufficient deposit to do this transaction !\n" +
                        "It can not be greater than €%.02f.\n ", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);

        scanner.nextLine();
        System.out.println("Enter the memo :");
        memo = scanner.nextLine();
        // do the withdrawl
        theUser.addAccountTransaction(fromAccount, -1 * amount, memo);

    }

    /**
     * show the transaction history for each specific account
     *
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

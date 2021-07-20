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
            while (choice < 1 || choice > 5){
                switch (choice){
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
                if(choice!=5){
                    AutomaticATM.printUserMenu(theUser,scanner);
                }
            }

        }
    }

}

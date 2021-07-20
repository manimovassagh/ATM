package io.mani.models;

import java.util.ArrayList;

public class Account {
    /**
     * define ID for each Account
     */
    private String accountID;
    /**
     * define name for each account
     */
    private String accountName;

    /**
     * The User that own this account
     */
    private User accountHolder;
    /**
     * The list of transactions for this account
     */
    private ArrayList<Transaction> transactions;


    public Account(String accountName, User accountHolder, Bank bank) {
        //set the account name and account holder
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        // define new account UUID
        this.accountID = bank.getNewAccountUUId();
        //initial a list of transactions
        this.transactions = new ArrayList<Transaction>();


    }

    public String getUUID() {
        return this.accountID;
    }

    /**
     * show the summary of account
     *
     * @return formatted String in which the user can better see the overall situation
     */
    public String getSummaryLine() {

        //Todo get the account balance
        double balance = this.getBalance();
        // format the summary line ,depend the balance . if negative then get red
        if (balance >= 0) {
            return String.format("%s : €%.02f : %s", this.accountID, balance, this.accountName);
        } else {
            return String.format("%s : €(%.02f : %s", this.accountID, balance, this.accountName);

        }

    }

    /**
     * get balance with each transaction
     *
     * @return balance of account
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction transaction : this.transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    /**
     * print the transaction history for this account
     */
    public void printTransactionHistory() {
        System.out.printf("\nTransaction History for account %s\n ", this.accountID);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * add transaction for each account
     * create a new transaction object and added to list
     *
     * @param amount amount of transaction
     * @param memo   memo for transaction
     */
    public void addTransaction(double amount, String memo) {
        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);

    }
}

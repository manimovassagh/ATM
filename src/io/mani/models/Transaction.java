package io.mani.models;

import java.util.Date;

public class Transaction {
    /**
     * define the amount of this transaction
     */
    private double amount;
    /**
     * define the transaction timestamp
     */
    private Date timestamp;
    /**
     * define a memo for this transaction
     */
    private String memo;
    /**
     * Shows the referenced account in which transaction happened
     */
    private Account referenceAccount;

    /**
     * constructor to generate account without memo
     *
     * @param amount           amount of account
     * @param referenceAccount the reference account that made the transaction
     */
    public Transaction(double amount, Account referenceAccount) {
        this.amount = amount;
        this.referenceAccount = referenceAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    // here i am overloading the constructor
    public Transaction(double amount, String memo, Account referenceAccount) {
        //call the upper constructor
        this(amount, referenceAccount);
        this.memo = memo;
    }

    /**
     * get amount of the account
     *
     * @return the amount of account
     */
    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : €%.02f : %s ", this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("%s : €(%.02f) : %s ",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        }
    }
}

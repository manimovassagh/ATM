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


}

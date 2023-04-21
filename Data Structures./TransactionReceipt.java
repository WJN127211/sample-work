/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.Calendar;

/**
 *
 * @author w1272
 */
public class TransactionReceipt {

    private TransactionTicket transactionticket;
    private boolean successIndicatorFlag;
    private String reasonforFailure;
    private double preTransactionBalance;
    private double postTransactionBalance;
    private Calendar postTransactionMaturityDate = Calendar.getInstance();

    //Create the constructor
    public TransactionReceipt(TransactionTicket transactionticket,
            boolean successIndicatorFlag, double preTransactionBalance, double postTransactionBalance, String reasonforFailure
    ) {

        this.successIndicatorFlag = successIndicatorFlag;
        this.preTransactionBalance = preTransactionBalance;
        this.postTransactionMaturityDate.add(Calendar.MONTH, transactionticket.gettermOfCD());
        this.reasonforFailure = reasonforFailure;
        this.postTransactionBalance = postTransactionBalance;
        this.transactionticket = transactionticket;

    }

    //Copy Constructor
    public TransactionReceipt(TransactionReceipt trp) {
        this.preTransactionBalance = trp.preTransactionBalance;
        this.transactionticket = trp.transactionticket;
        this.successIndicatorFlag = trp.successIndicatorFlag;
        this.reasonforFailure = trp.reasonforFailure;
        this.postTransactionMaturityDate = trp.postTransactionMaturityDate;
        this.postTransactionBalance = trp.postTransactionBalance;
    }

    //Print the TransactionReceipt
    @Override
    public String toString() {
        
        String result = "";
        if (successIndicatorFlag) {
            String str = transactionticket.getTransactionType();

            switch (str) {
                case "Balance Inquiry":
                case "Close Account":
                case "Reopen Account":
                case "Delete Account":
                    result = transactionticket.toString()
                            + "Current Balance: " + postTransactionBalance + "\n\n\n";
                    break;
                default:
                    result = transactionticket.toString()
                            + "Current Balance: " + preTransactionBalance + "\n"
                            + "New Balance: " + postTransactionBalance + "\n\n\n";

            }
        } else {
            switch (transactionticket.getTransactionType()) {
                case "Balance Inquiry":
                case "Close Account":
                case "Reopen Account":
                case "Delete Account":
                    result = transactionticket.toString()
                            + "Error: " + reasonforFailure + "\n\n\n";
                    break;
                case "Clear Check":
                    result = transactionticket.toString()
                            + "Current Balance: " + preTransactionBalance + "\n"
                            + "New Balance: " + postTransactionBalance + "\n"
                            + "Error: " + reasonforFailure + "\n\n\n";

                default:
                    result = transactionticket.toString()
                            + "Current Balance: " + preTransactionBalance + "\n"
                            + "Error: " + reasonforFailure + "\n\n\n";

            }
        }
        return result;
    }

    //return the  transaction ticket
    public TransactionTicket getTransactionTicket() {
        return transactionticket;

    }

    //return the transaction indicator flag
    public boolean getTransactionSuccessIndicatorFlag() {
        return successIndicatorFlag;
    }

    //return the transaction failure reason
    public String getTransactionFailureReason() {
        return reasonforFailure;
    }

    //return the balance before thr transaction
    public double getPreTransactionBalance() {
        return preTransactionBalance;
    }

    //return the balance after the transaction
    public double getPostTransactionBalance() {

        return postTransactionBalance;

    }

    //return the maturity date
    public Calendar getPostTransactionMaturityDate() {
        return postTransactionMaturityDate;
    }
}

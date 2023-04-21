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
public class TransactionTicket {

    private Calendar today;
    private String typeOfTransaction;
    private int AcctNumber;
    private double TransactionAmount;
    private int termOfCD;
    private Account acct;
    private Check check;
    private String ssn;

    //Create a constructor
    public TransactionTicket(String typeOfTransaction,
            int AcctNumber, double TransactionAmount, int termOfCD, Account acct, Check check, String ssn) {

        this.typeOfTransaction = typeOfTransaction;
        this.AcctNumber = AcctNumber;
        this.TransactionAmount = TransactionAmount;
        this.termOfCD = termOfCD;
        this.today = Calendar.getInstance();
        this.acct = acct;
        this.check = check;
        this.ssn = ssn;
    }

    //copyconstructor
    public TransactionTicket(TransactionTicket Tra) {
        this.typeOfTransaction = Tra.typeOfTransaction;
        this.AcctNumber = Tra.AcctNumber;
        this.TransactionAmount = Tra.TransactionAmount;
        this.termOfCD = Tra.termOfCD;
        this.today = Tra.today;
        this.acct = Tra.acct;
        this.check = Tra.check;
        this.ssn = Tra.ssn;
    }

    @Override
    public String toString() {
        String result = "";
        switch (typeOfTransaction) {
            case "Balance Inquiry":
            case "Close Account":
            case "Reopen Account":
            case "Delete Account":
                result =  "Transaction Type: " + typeOfTransaction + "\n"
                        + "Account Number: " + AcctNumber + "\n";

                break;
            default:
                result = "Transaction Type: " + typeOfTransaction + "\n"
                        + "Account Number: " + AcctNumber + "\n"
                        + "Amount to " + typeOfTransaction + ": " + TransactionAmount + "\n";
        }
        return result;
    }

    public Check getcheck(){
        return check;
    }
    
    public Account getaccount(){
        return acct;
    }
    
    public String getSSN(){
       return ssn;
    }
    //return the transaction date
    public Calendar getDateOfTransaction() {
        return today;
    }

    //return the type of transactiontype
    public String getTransactionType() {
        return typeOfTransaction;
    }

    //return the account number
    public int getAcctNumber() {
        return AcctNumber;
    }

    //return the transaction amount
    public double getTransactionAmount() {
        return TransactionAmount;
    }

    //return the term of CD
    public int gettermOfCD() {
        return termOfCD;
    }
}

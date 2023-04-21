/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author w1272
 */
public class Account implements Comparable<Account>{

    protected Depositor depositor;
    protected int acctnumber;
    protected String accttype;
    protected boolean acctstatus;
    protected double acctbalance;
    protected Calendar maturityDate;
    protected ArrayList<TransactionReceipt> transactionreceipts = new ArrayList<>();

    //Create the constructor
    public Account(Depositor depositor, int acctnumber, String accttype,
            boolean acctstatus, double acctbalance) {
        this.depositor = depositor;
        this.acctnumber = acctnumber;
        this.accttype = accttype;
        this.acctstatus = acctstatus;
        this.acctbalance = acctbalance;
    }
    
    public Account(Depositor depositor, int acctnumber, String accttype,
            boolean acctstatus, double acctbalance, Calendar maturityDate) {
        this.depositor = depositor;
        this.acctnumber = acctnumber;
        this.accttype = accttype;
        this.acctstatus = acctstatus;
        this.acctbalance = acctbalance;
        this.maturityDate = maturityDate;
    }

    //implement No-Arg Constructor
    public Account() {
        super();
    }

    //copy constructor
    public Account(Account acct) {
        this.acctbalance = acct.acctbalance;
        this.acctnumber = acct.acctnumber;
        this.acctstatus = acct.acctstatus;
        this.accttype = acct.accttype;
        this.depositor = acct.depositor;
        this.transactionreceipts = acct.transactionreceipts;
    }

    //Return the maturity date
    public Calendar getMaturityDate() {
        return maturityDate;
    }

    //this method is use to closing the account
    public TransactionReceipt closeAcct(TransactionTicket traticket) {
        boolean flag = true;
        String reason = "";
        acctstatus = false;

//create the transaction receipt
        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                reason);

        transactionreceipts.add(trareceipt);

        return trareceipt;

    }

    //this method use to reopen account
    public TransactionReceipt reopenAcct(TransactionTicket traticket) {
        boolean flag = true;
        String reason = null;
        acctstatus = true;

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                reason);

        transactionreceipts.add(trareceipt);

        return trareceipt;

    }
    
    //return the transactionreceipt of the get history
    public TransactionReceipt getTransactionHistory(TransactionTicket traticket) {
        boolean flag = true;
        String reason = null;

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                reason);

        transactionreceipts.add(trareceipt);
        return trareceipt;

    }
    
    public TransactionReceipt getacctInformation(TransactionTicket traticket) {
        boolean flag = true;
        String reason = null;

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                reason);

        transactionreceipts.add(trareceipt);
        return trareceipt;

    }
    
//return the Account's history of the transaction
    public String getHistory() {
        String result = "";
        for (int i = 0; i < transactionreceipts.size(); i++) {                   //for loop to format the each transaction receipt
            Calendar transactiondate = transactionreceipts.get(i).getTransactionTicket().getDateOfTransaction();
            int day = transactiondate.get(Calendar.DAY_OF_MONTH);
            int month = transactiondate.get(Calendar.MONTH) + 1;
            int year = transactiondate.get(Calendar.YEAR);
            String date = month + "/" + day + "/" + year;
            String typeoftransaction = transactionreceipts.get(i).getTransactionTicket().getTransactionType();
            double amount = transactionreceipts.get(i).getTransactionTicket().getTransactionAmount();
            boolean flags = transactionreceipts.get(i).getTransactionSuccessIndicatorFlag();
            if (flags) {                                                          //indicate the transaction's success or failure
                String flag = "Success";
                String str = String.format("%-10s%s%10d%18s%14.2f%10s\n", date, depositor, acctnumber, typeoftransaction, amount, flag);
                result += str;
            } else {
                String flag = "Failure";
                String str = String.format("%-10s%s%10d%18s%14.2f%10s\n", date, depositor, acctnumber, typeoftransaction, amount, flag);
                result += str;
            }//end if

        }//end for loop
        return result;
    }

    //return the account information
    @Override
    public String toString() {
        String result = "";
        String status;
        if (acctstatus) {
            status = "open";
        } else {
            status = "close";
        }
        result = String.format("%s%10d%9s%10s%14.2f/n ", depositor, acctnumber, accttype, status, acctbalance);
        return result;
    }

    //this is the method to creat the ticket toget the balance of the ticket
    //then create the transactionreceipt add to the arraylist
    public TransactionReceipt getBalance(TransactionTicket traticket) {
        boolean flag = true;
        String reason = "";

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                reason);

        transactionreceipts.add(trareceipt);

        return trareceipt;

    }

    //Generally make deposit to the account, but it will implement througn the subclass
    public TransactionReceipt makedeposit(TransactionTicket traticket) throws InvalidAmountException,
            AccountClosedException, InsufficientFundsException {
        TransactionReceipt trareceipt = null;
        return trareceipt;

    }

    //Generally make withdrawal to the account, but it will implement througn the subclass
    public TransactionReceipt makewithdrawal(TransactionTicket traticket) throws InvalidAmountException,
            AccountClosedException, InsufficientFundsException {
        TransactionReceipt trareceipt = null;
        return trareceipt;

    }

    //Generally clear check to the account, but it will implement througn the subclass
    public TransactionReceipt clearCheck(TransactionTicket traticket, Check check) throws InvalidAmountException,
            AccountClosedException, InsufficientFundsException {
        TransactionReceipt trareceipt = null;
        return trareceipt;

    }

    //return the depositor's info 
    public Depositor getDepositor() {
        return depositor;

    }
    //return the account number
    public int getAcctNumber() {
        return acctnumber;
    }
    //return the account type
    public String getAcctType() {
        return accttype;
    }

    //return the account current balance
    public double getacctbalance() {
        return acctbalance;
    }

    //return the account status
    public boolean getacctstatus() {
        return acctstatus;
    }

    //add the transactionreceipt to the transaction history
    public void addTransaction(TransactionReceipt tranreceipt) {

        transactionreceipts.add(tranreceipt);

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account) {
            Account other = (Account) o;
            return this.acctnumber == other.acctnumber && this.depositor.equals(other.depositor) && this.accttype.equals(other.accttype);
        } else {
            return false;
        }

    }
    
    @Override
    public int compareTo(Account t) {
        String s1 = Integer.toString(acctnumber);
        String s2 = Integer.toString(t.acctnumber);
        return s1.compareTo(s2);
    }
    
    

}

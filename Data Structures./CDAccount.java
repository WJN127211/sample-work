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
public class CDAccount extends Account {

    


    //create the constructor
    public CDAccount(Depositor depositor, int acctnumber, String accttype,
            boolean acctstatus, double acctbalance, Calendar maturityDate) {
        super(depositor, acctnumber, accttype, acctstatus, acctbalance);
        this.maturityDate = maturityDate;
    }

    //create the copy constructor
    public CDAccount(CDAccount C) {
        this.acctbalance = C.acctbalance;
        this.acctnumber = C.acctnumber;
        this.acctstatus = C.acctstatus;
        this.accttype = C.accttype;
        this.depositor = C.depositor;
        this.transactionreceipts = C.transactionreceipts;
        this.maturityDate = C.maturityDate;
    }

    //get the deposit amount, and the term of CD and make the deposit
    public TransactionReceipt makeDeposit(TransactionTicket traticket, double depositamount, int termofCD)
            throws InvalidAmountException, InsufficientFundsException, AccountClosedException, CDMaturityDateException {
        boolean flag = true;
        String reason = null;
        Calendar today = Calendar.getInstance();
        
        double preacctbalance = acctbalance;
        try {
            if (depositamount < 0) {
                throw new InvalidAmountException("CD");//if the amount is insufficient
            }  else if (!acctstatus) {
                throw new AccountClosedException();// if the account has closed

            } else if (today.before(maturityDate)) {
                throw new CDMaturityDateException(acctnumber);//if it doesn't ,eet the maturity date
            } else {
                // make the deposit
                acctbalance = acctbalance + depositamount;
                maturityDate.add(Calendar.MONTH, termofCD);
                Bank.addTotal(depositamount);
                Bank.addTotalSaving(depositamount);
            }
        } catch (AccountClosedException | CDMaturityDateException | InvalidAmountException ex) {
            flag = false;
            reason = ex.getMessage();
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);//get the transaction receipt
        transactionreceipts.add(trareceipt);

        return trareceipt;
    }

    //get the withdrawal amount, and the term of CD and make the deposit
    public TransactionReceipt makeWithdrawal(TransactionTicket traticket, double withdrawalamount, int termofCD)
            throws InvalidAmountException, InsufficientFundsException, AccountClosedException, CDMaturityDateException {
        boolean flag = true;
        String reason = null;
        Calendar today = Calendar.getInstance();
       
        double preacctbalance = acctbalance;
        try {
            if (withdrawalamount < 0) {
                throw new InvalidAmountException("CD");//the withdrawal amount is negative
            } else if (withdrawalamount > acctbalance) {
                throw new InsufficientFundsException();//the account doesn't have the enough balance
            } else if (!acctstatus) {
                throw new AccountClosedException();//the account is close

            } else if (today.before(maturityDate)) {
                throw new CDMaturityDateException(acctnumber);//the date doesn't meet the maturity date
            } else {
                //make the withdrawal
                acctbalance = acctbalance - withdrawalamount;
                maturityDate.add(Calendar.MONTH, termofCD);
                Bank.addTotal(-withdrawalamount);
                Bank.addTotalSaving(-withdrawalamount);
            }
        } catch (AccountClosedException | CDMaturityDateException | InsufficientFundsException | InvalidAmountException ex) {
            flag = false;
            reason = ex.getMessage();
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);
        transactionreceipts.add(trareceipt);

        return trareceipt;
    }
    
    public Calendar getMaturityDate(){
         return maturityDate;
     }
    
    //print the account information
    @Override
     public String toString() {
        String result;
        String status;
        int day = maturityDate.get(Calendar.DAY_OF_MONTH);
            int month = maturityDate.get(Calendar.MONTH);
            int year = maturityDate.get(Calendar.YEAR);
            String date = month+"/"+day+"/"+year;
        if (acctstatus) {
            status = "open";
        } else {
            status = "close";
        }
        result = String.format("%s%10d%9s%10s%14.2f%10s\n ", depositor, acctnumber, accttype, status, acctbalance,date);
        return result;
    }
     
      
    @Override
    public boolean equals(Object o ){
        if(o instanceof CDAccount){
            CDAccount other = (CDAccount) o;
            return this.acctnumber == other.acctnumber&&this.depositor.equals(other.depositor)&&this.accttype.equals(other.accttype);
        }else{
            return false;
        }
        
    }


}

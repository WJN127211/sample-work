/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.ArrayList;

/**
 *
 * @author w1272
 */
public class SavingAccount extends Account {

  

    public SavingAccount(Depositor depositor, int acctnumber, String accttype, boolean acctstatus,
            double acctbalance) {

        super(depositor, acctnumber, accttype, acctstatus, acctbalance);

    }

    //copy constructor
    public SavingAccount(SavingAccount s) {
        this.acctbalance = s.acctbalance;
        this.acctnumber = s.acctnumber;
        this.acctstatus = s.acctstatus;
        this.accttype = s.accttype;
        this.depositor = s.depositor;
        this.transactionreceipts = s.transactionreceipts;
    }

    // get the withdrawal amount and make with withdrawal to this account
    public TransactionReceipt makeWithdrawal(TransactionTicket traticket, double withdrawalamount)
            throws InvalidAmountException, InsufficientFundsException, AccountClosedException {
        boolean flag = true;
        String reason = null;
     
        double preacctbalance = acctbalance;
        try {
            if (withdrawalamount < 0) {
                throw new InvalidAmountException("Saving");// if the amounyt is negative
            } else if (withdrawalamount > acctbalance) {
                throw new InsufficientFundsException();// if the withdrawal amount greater than the balance
            } else if (!acctstatus) {
                throw new AccountClosedException();// if the account is close

            } else {
                //make withdrawal
                acctbalance = acctbalance - withdrawalamount;
                Bank.addTotal(-withdrawalamount);
                Bank.addTotalSaving(-withdrawalamount);
            }
        } catch (InsufficientFundsException | InvalidAmountException | AccountClosedException ex) {
            reason = ex.getMessage();// get the error message
            flag = false;
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);//create the transaction receipt
        transactionreceipts.add(trareceipt);// add this the arraylist

        return trareceipt;
    }

    // get the deposit amount and make deposit to this account
    public TransactionReceipt makeDeposit(TransactionTicket traticket, double depositamount) throws InvalidAmountException, AccountClosedException {
        boolean flag = true;
        String reason = null;
        
        double preacctbalance = acctbalance;
        try {
            if (depositamount <= 0) {
                throw new InvalidAmountException("Saving");// if the amounyt is negative
            } else if (!acctstatus) {
                throw new AccountClosedException();// if the account is closed
            } else {
                //male deposit
                acctbalance = acctbalance + depositamount;
                Bank.addTotal(depositamount);
                Bank.addTotalSaving(depositamount);
            }
        } catch (InvalidAmountException | AccountClosedException ex) {
            reason = ex.getMessage();// get the error message
            flag = false;
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);// create the transaction receipt
        transactionreceipts.add(trareceipt);// add the receipt to the arraylist

        return trareceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SavingAccount) {
            SavingAccount other = (SavingAccount) o;
            return this.acctnumber == other.acctnumber && this.depositor.equals(other.depositor) && this.accttype.equals(other.accttype);
        } else {
            return false;
        }

    }

    //get the account information
    @Override
    public String toString() {
        String result;
        String status;
        if (acctstatus) {
            status = "open";
        } else {
            status = "close";
        }
        result = String.format("%s%10d%9s%10s%14.2f\n ", depositor, acctnumber, accttype, status, acctbalance);
        return result;
    }
}

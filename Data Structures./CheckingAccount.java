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
public class CheckingAccount extends Account {

    //create the constructor
    public CheckingAccount(
            Depositor depositor, int acctnumber, String accttype, boolean acctstatus, double acctbalance) {
        super(depositor, acctnumber, accttype, acctstatus, acctbalance);
    }

    //copy constructor
    public CheckingAccount(CheckingAccount c) {
        this.acctbalance = c.acctbalance;
        this.acctnumber = c.acctnumber;
        this.acctstatus = c.acctstatus;
        this.accttype = c.accttype;
        this.depositor = c.depositor;
        this.transactionreceipts = c.transactionreceipts;
    }

    //get depositamount and make deposit to this account
    public TransactionReceipt makeDeposit(TransactionTicket traticket, double depositamount) throws InvalidAmountException, AccountClosedException {

        boolean flag = true;
        String reason = null;
        
        double preacctbalance = acctbalance;
        try {
            if (depositamount <= 0) {
                throw new InvalidAmountException("Checking");//if the amount is the negative
            } else if (!acctstatus) {
                throw new AccountClosedException();//if the account is close
            } else {
                acctbalance = acctbalance + depositamount;
                Bank.addTotal(depositamount);
                Bank.addTotalSaving(depositamount);
            }
        } catch (InvalidAmountException | AccountClosedException ex) {
            reason = ex.getMessage();
            flag = false;
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);
        transactionreceipts.add(trareceipt);

        return trareceipt;
    }

    //get the withdrawal amount, and make the withdrawal to this account
    public TransactionReceipt makeWithdrawal(TransactionTicket traticket, double withdrawalamount)
            throws InvalidAmountException, InsufficientFundsException, AccountClosedException {

        boolean flag = true;
        String reason = null;
       
        double preacctbalance = acctbalance;
        try {
            if (withdrawalamount < 0) {
                throw new InvalidAmountException("Checking");//if the amount is the negative
            } else if (withdrawalamount > acctbalance || (acctbalance < 2500 && withdrawalamount > acctbalance - 1.5)) {
                throw new InsufficientFundsException();//if the withdrawal amount is greater than the balance
            } else if (!acctstatus) {
                throw new AccountClosedException();//if the account is closed

            } else {
                //withdrawal the account accouding to the account balance
                if (acctbalance >= 2500) {
                    acctbalance = acctbalance - withdrawalamount;
                    Bank.addTotal(-withdrawalamount);
                    Bank.addTotalSaving(-withdrawalamount);
                } else {
                    acctbalance = acctbalance - withdrawalamount - 1.5;
                    Bank.addTotal(-withdrawalamount - 1.5);
                    Bank.addTotalSaving(-withdrawalamount - 1.5);
                }

            }
        } catch (InsufficientFundsException | InvalidAmountException | AccountClosedException ex) {
            reason = ex.getMessage();//get the error message
            flag = false;
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);
        transactionreceipts.add(trareceipt);

        return trareceipt;
    }

    //get the withdrawal amount, and make the clear check to this account
    public TransactionReceipt clearCheck(TransactionTicket traticket, Check check,
            double withdrawalamount) throws InvalidAmountException, InsufficientFundsException, AccountClosedException,
            PostDatedCheckException, CheckTooOldException {

        boolean flag = true;
        String reason = null;
        double preacctbalance = acctbalance;
        Calendar today = Calendar.getInstance();
        Calendar oldcheck = Calendar.getInstance();
        oldcheck.add(Calendar.MONTH, -6);
        

        Calendar dateofcheck = check.getCheckDate();

        try {
            if (withdrawalamount < 0) {
                throw new InvalidAmountException("Checking");//if the amount is negative
            } else if (dateofcheck.before(oldcheck)) {
                throw new CheckTooOldException();// if the check is too old
            } else if (dateofcheck.after(today)) {
                throw new PostDatedCheckException();// if the check is future's check
            } else if (withdrawalamount > acctbalance || (acctbalance < 2500 && withdrawalamount > acctbalance - 1.5)) {
                acctbalance = acctbalance - 2.5;
                Bank.addTotal(-2.5);
                Bank.addTotalSaving(-2.5);
                throw new InsufficientFundsException();// if the account balance less than  the withdrawal amount

            } else if (!acctstatus) {
                throw new AccountClosedException();// if the account close

            } else {
                //clear check the account
                if (acctbalance >= 2500) {
                    acctbalance = acctbalance - withdrawalamount;
                } else {
                    acctbalance = acctbalance - withdrawalamount - 1.5;
                }
                Bank.addTotal(-withdrawalamount);
                Bank.addTotalSaving(-withdrawalamount);

            }
        } catch (InsufficientFundsException | InvalidAmountException | AccountClosedException | CheckTooOldException |PostDatedCheckException ex) {
            reason = ex.getMessage();// get the error message
            flag = false;
            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                    reason);// create the transaction receipt

            transactionreceipts.add(trareceipt);

            return trareceipt;
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, acctbalance,
                reason);// create the transaction receipt

        transactionreceipts.add(trareceipt);

        return trareceipt;

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CheckingAccount) {
            CheckingAccount other = (CheckingAccount) o;
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

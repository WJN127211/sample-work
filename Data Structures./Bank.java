/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author w1272
 */
public class Bank {

    private static double totalAmountInSavingsAccts;
    private static double totalAmountInCheckingAccts;
    private static double totalAmountInCDAccts;
    private static double totalAmountInAllAccts;
    //private List<Account> accounts= new ArrayList<>();
    //private List<Account> accounts = new LinkedList<>();
    //private LinkedListAccounts accounts = new LinkedListAccounts();

    private ArrayHeap accounts;
    private ArrayHeap heap;
    //private LinkedListAccounts llByAcctNumSorted = new LinkedListAccounts();
    //private LinkedListAccounts llBySSNSorted = new LinkedListAccounts();
    //private LinkedListAccounts llByNameSorted = new LinkedListAccounts();
    //private LinkedListAccounts llByBalanceSorted = new LinkedListAccounts();

    private ArrayList<Integer> acctNumQucikSortKey = new ArrayList<>();

    private static GenQueue<TransactionTicket> traticket = new GenQueue<TransactionTicket>();//create the queue of the transaction ticket

    /**
     * private ArrayList<Integer> ssnQuickSortKey = new ArrayList<>(); private
     * ArrayList<Integer> nameQuickSortKey = new ArrayList<>(); private
     * ArrayList<Integer> acctNumBubbleSortKey = new ArrayList<>(); private
     * ArrayList<Integer> ssnBubbleSortKey = new ArrayList<>(); private
     * ArrayList<Integer> nameBubbleSortKey = new ArrayList<>(); private
     * ArrayList<Integer> acctNumInsertionSortKey = new ArrayList<>(); private
     * ArrayList<Integer> ssnInsertionSortKey = new ArrayList<>(); private
     * ArrayList<Integer> nameInsertionSortKey = new ArrayList<>(); private
     * ArrayList<Integer> BalanceQucikSortKey = new ArrayList<>(); private
     * ArrayList<Integer> BalanceBubbleSortKey = new ArrayList<>(); private
     * ArrayList<Integer> BalanceInsertionSortKey = new ArrayList<>(); /*
     */
    //create the constructor
    public Bank() {
        accounts = new ArrayHeap();
        heap = new ArrayHeap();

    }

    public void creatheap(Comparator<Account> comp) {
        for (int i = 0; i < accounts.size(); i++) {
            Account acct = accounts.get(i);
            heap.add(acct, comp);

        }
    }

    //check the queue of the ticket is empty
    public boolean isEmpty() {
        return traticket.empty();
    }

    //add the ticket at the last position
    public void enqueueTra(TransactionTicket ticket) {
        traticket.enqueue(ticket);
    }

    //remove and return the first ticket
    public TransactionTicket dequeueTra() {
        return traticket.dequeue();
    }

    public String printprimaryarray() {
        String result = "";
        result += "Account Number---Sorted the List by Using the Primary Heap Sort:\n";
        String s1 = String.format("%-10s%-12s%6s%13s%15s%15s%15s\n", "Last", "First", "SSN", "Number", "Type", "Balance", "Maturity Date");
        result += s1;
        
        double totalcdamount = getTotalCD(accounts);
        double totalchecking = getTotalChecking(accounts);
        double totalsaving = getTotalSaving(accounts);
        double amount = getTotal(accounts);
        
        String s = String.format("The Total CD Amount in the Bank is: %8.2f\n", totalcdamount);
        s += String.format("The Total Checking Amount in the Bank is: %8.2f\n", totalchecking);
        s += String.format("The Total Saving Amount in the Bank is: %8.2f\n", totalsaving);
        s += String.format("The Total Amount in the Bank is: %8.2f\n", amount);

        while (!accounts.isEmpty()) {

            Account acct =accounts.removeMin();
            Depositor depositor = acct.getDepositor();
            int acctnumber = acct.getAcctNumber();
            String accttype = acct.getAcctType();
            double balance = acct.getacctbalance();

            if (accttype.equals("CD")) {

                Account acct1 = (CDAccount) acct;
                Calendar dat = acct.getMaturityDate();
                int year = dat.get(Calendar.YEAR);
                int month = dat.get(Calendar.MONTH);
                int day = dat.get(Calendar.DAY_OF_MONTH);
                String date = month + "/" + day + "/" + year;
                String str = String.format("%s%10d%15s%14.2f%13s\n", depositor, acctnumber, accttype, balance, date);
                result = result + str;
            } else {
                String date = null;
                String str = String.format("%s%10d%15s%14.2f\n", depositor, acctnumber, accttype, balance);
                result = result + str;
            }// end if 
        }
        result = result + "\n" + s;

        return result;// return the result of the print account

    }

    //print the detail information of the linked sorted account
    public String sortedacct() {

        String s1 = String.format("%-10s%-12s%6s%13s%15s%15s%15s\n", "Last", "First", "SSN", "Number", "Type", "Balance", "Maturity Date");
        String s3 = "Account Number---Sorted the List by Using the Heap Sort:\n";
        String s4 = "SSN---Sorted the List by Using the Heap Sort:\n";
        String s5 = "Name---Sorted the List by Using the Heap Sort:\n";
        String s6 = "Balance---Sorted the List by Using the Heap Sort:\n";

        String result1 = s3 + s1 + printheapsorted(new AccountComparatorByAcctNumber()) + "\n\n";

        result1 = result1 + s4 + s1 + printheapsorted(new AccountComparatorBySSN()) + "\n\n";

        result1 = result1 + s5 + s1 + printheapsorted(new AccountComparatorByName()) + "\n\n";
        result1 = result1 + s6 + s1 + printheapsorted(new AccountComparatorByBalance()) + "\n\n";
        return result1;
    }

    public String printheapsorted(Comparator<Account> comp) {
        String result = "";

        creatheap(comp);

        int size = heap.size();
        
        double totalcdamount = getTotalCD(heap);
        double totalchecking = getTotalChecking(heap);
        double totalsaving = getTotalSaving(heap);
        double amount = getTotal(heap);
        
        String s = String.format("The Total CD Amount in the Bank is: %8.2f\n", totalcdamount);
        s += String.format("The Total Checking Amount in the Bank is: %8.2f\n", totalchecking);
        s += String.format("The Total Saving Amount in the Bank is: %8.2f\n", totalsaving);
        s += String.format("The Total Amount in the Bank is: %8.2f\n", amount);

        
        
        

        while (!heap.isEmpty()) {

            Account acct = heap.removeMin(comp);
            Depositor depositor = acct.getDepositor();
            int acctnumber = acct.getAcctNumber();
            String accttype = acct.getAcctType();
            double balance = acct.getacctbalance();

            if (accttype.equals("CD")) {

                Account acct1 = (CDAccount) acct;
                Calendar dat = acct.getMaturityDate();
                int year = dat.get(Calendar.YEAR);
                int month = dat.get(Calendar.MONTH);
                int day = dat.get(Calendar.DAY_OF_MONTH);
                String date = month + "/" + day + "/" + year;
                String str = String.format("%s%10d%15s%14.2f%13s\n", depositor, acctnumber, accttype, balance, date);
                result = result + str;
            } else {
                String date = null;
                String str = String.format("%s%10d%15s%14.2f\n", depositor, acctnumber, accttype, balance);
                result = result + str;
            }// end if 
        }
        result = result + "\n" + s;

        return result;// return the result of the print account

    }

    

    //return the total amount in the all saving account
    public double getTotalSaving(ArrayHeap accts) {
        double result = 0;
        for (int i = 0; i < accts.size(); i++) {
            if (accts.get(i).accttype.equals("Savings")) {
                result += accts.get(i).getacctbalance();
            }
        }
        Bank.totalAmountInSavingsAccts = result;
        return result;

    }

    //// return the total amount in the all checking account
    public double getTotalChecking(ArrayHeap accts) {
        double result = 0;
        for (int i = 0; i < accts.size(); i++) {
            if (accts.get(i).accttype.equals("Checking")) {
                result += accts.get(i).getacctbalance();
            }
        }
        Bank.totalAmountInCheckingAccts = result;
        return result;
    }

    //return the total amount in the all CD account
    public double getTotalCD(ArrayHeap accts) {
        double result = 0;
        for (int i = 0; i < accts.size(); i++) {
            if (accts.get(i).accttype.equals("CD")) {
                result += accts.get(i).getacctbalance();
            }
        }
        Bank.totalAmountInCDAccts = result;
        return result;
    }

    //return the total amount in all account
    public double getTotal(ArrayHeap accts) {
        double result = 0;
        for (int i = 0; i < accts.size(); i++) {
            result += accts.get(i).getacctbalance();
        }
        Bank.totalAmountInAllAccts = result;
        return result;
    }

    //get the amount and add to the total amount of saving account
    public static void addTotalSaving(double amount) {
        Bank.totalAmountInSavingsAccts = totalAmountInSavingsAccts + amount;
    }

    //get the amount and add to the total amount of Checking account
    public static void addTotalChecking(double amount) {
        Bank.totalAmountInCheckingAccts = totalAmountInCheckingAccts + amount;
    }

    //get the amount and add to the total amount of CD account
    public static void addTotalCD(double amount) {
        Bank.totalAmountInCDAccts = totalAmountInCDAccts + amount;
    }

    //get the amount and add to the total amount of all account
    public static void addTotal(double amount) {
        Bank.totalAmountInAllAccts = totalAmountInAllAccts + amount;
    }

    //get the amount and minus to the total amount of saving account
    public static void SubTotalSaving(double amount) {
        Bank.totalAmountInSavingsAccts = totalAmountInSavingsAccts - amount;
    }

    //get the amount and minus to the total amount of checking account
    public static void SubTotalChecking(double amount) {
        Bank.totalAmountInCheckingAccts = totalAmountInCheckingAccts - amount;
    }

    //get the amount and minus to the total amount of CD account
    public static void SubTotalCD(double amount) {
        Bank.totalAmountInCDAccts = totalAmountInCDAccts - amount;
    }

    //get the amount and minus to the total amount of allaccount
    public static void SubTotal(double amount) {
        Bank.totalAmountInAllAccts = totalAmountInAllAccts - amount;
    }

    //get the account number by the binary search
    //return the index of the account ine arraylist
    //if can't find, return the negativa number
    public int findAcct(int acctnumber) {
        //throgh the arraylist of the account 
        //first quick sort by the account number
        /**
         * QuickSort(acctNumQucikSortKey, new AccountComparatorByAcctNumber());
         * int low, high, test; low = 0; //get the highest index high =
         * accounts.size() - 1; while (low <= high) {
         * test = (low + high) / 2;
         * if (accounts.get(acctNumQucikSortKey.get(test)).getAcctNumber() == acctnumber) {
         * return acctNumQucikSortKey.get(test);
         * } else if (accounts.get(acctNumQucikSortKey.get(test)).getAcctNumber()
         * > acctnumber) { high = test - 1; } else { low = test + 1; } } return
         * -1; /*
         */

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAcctNumber() == acctnumber) {
                return i;
            } //endif 
        } //end for loop 
        return -1;

    }

    public void addAccount(Account acct) {
        accounts.add(acct);
    }

    //By providing the enough information to create the new account, and add this account to the bank system
    public TransactionReceipt openNewAcct(TransactionTicket traticket
    )
            throws InvalidAccountException, InvalidAmountException {

        Depositor depositor = traticket.getaccount().getDepositor();
        int acctnumber = traticket.getAcctNumber();
        String accttype = traticket.getaccount().getAcctType();
        double depositamount = traticket.getTransactionAmount();
        Calendar date = traticket.getaccount().getMaturityDate();
        double preacctbalance = depositamount;
        boolean flag = true;
        String reason = null;
        Account acct;
        int index = findAcct(acctnumber);//test if the account number is exist
        try {
            if (!(index < 0)) { // the account has exist
                throw new InvalidAccountException(acctnumber);
            } else if (depositamount < 0) {// provide the insufficient depositnumber
                throw new InvalidAmountException(accttype);
            } else {// aacording to the different account type create different account
                boolean acctstatus = true;
                if (accttype.equals("CD")) {//the account type is CD
                    Calendar maturityDate = date;

                    CDAccount acct1 = new CDAccount(depositor, acctnumber, accttype, acctstatus, depositamount, maturityDate);

                    accounts.add(acct1);

                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, preacctbalance,
                            reason);
                    acct1.addTransaction(trareceipt);//add the transactionreceipt to the account's transactionreceipt list

                    return trareceipt;
                } else if (accttype.equals("Savings")) {// the account type is saving

                    SavingAccount acct1 = new SavingAccount(depositor, acctnumber, accttype, acctstatus, depositamount);
                    accounts.add(acct1);
                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, preacctbalance,
                            reason);
                    acct1.addTransaction(trareceipt);//add the transactionreceipt to the account's transactionreceipt list

                    return trareceipt;
                } else {//the account type is checking
                    CheckingAccount acct1 = new CheckingAccount(depositor, acctnumber, accttype, acctstatus, depositamount);
                    accounts.add(acct1);

                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, preacctbalance,
                            reason);
                    acct1.addTransaction(trareceipt);//add the transactionreceipt to the account's transactionreceipt list

                    return trareceipt;
                }

            }
        } catch (InvalidAccountException | InvalidAmountException ex) {
            flag = false;
            reason = ex.getMessage();
        }

        TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, preacctbalance, preacctbalance,
                reason);

        return trareceipt;
    }

    //get the account number
    //if we can find the account through the account number, delete yhis account
    public TransactionReceipt deleteAcct(TransactionTicket traticket) throws InvalidAccountException, InsufficientFundsException {
        int acctnumber = traticket.getAcctNumber();
        double amount = 0;
        boolean remove = true;
        String reason = null;
        int index = findAcct(acctnumber);

        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);//if can't find this account
            } else {
                amount = accounts.get(index).getacctbalance();
                if (amount > 0) {
                    throw new InsufficientFundsException();// if the accoount still has balance, we can not delete account
                } else {

                    Account acct = accounts.get(index);

                    acct = accounts.remove(index);// right now we can remove the account successfully

                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, remove, amount, amount,
                            reason);
                    //accts.get(index).addTransaction(trareceipt);//add the transaction receipt to the arraylist
                    return trareceipt;
                }
            }

        } catch (InsufficientFundsException | InvalidAccountException ex) {// handle the different type of the exception
            reason = ex.getMessage();
            remove = false;
            TransactionReceipt trareceipt = new TransactionReceipt(traticket, remove, 0, 0,
                    reason);

            return trareceipt;

        }

    }

    //linke list delete account
    // return the number of accounts
    public int getNumAccts() {
        return accounts.size();
    }

    //return the account by using the account number
    public Account getAccount(int index) {
        String accttype = accounts.get(index).getAcctType();
        switch (accttype) {
            case "Savings": {
                SavingAccount saacct = (SavingAccount) accounts.get(index);
                Account acct = new SavingAccount(saacct);
                return acct;
            }
            case "CD": {
                CDAccount cdacct = (CDAccount) accounts.get(index);
                Account acct = new CDAccount(cdacct);
                return acct;
            }
            default: {
                CheckingAccount chacct = (CheckingAccount) accounts.get(index);
                Account acct = new CheckingAccount(chacct);
                return acct;
            }
        }

    }

    //replace the oringal account by the new account;
    public void replaceaccount(Account acct, int index) {
        accounts.set(index, acct);
    }

    //get the account number
    //if we can find the account, get the current balance of the account
    public TransactionReceipt getBalance(TransactionTicket traticket) throws InvalidAccountException, AccountClosedException {
        int acctnumber = traticket.getAcctNumber();
        int index = findAcct(acctnumber);
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);//the account cannot find
            } else {
                try {
                    if (!accounts.get(index).getacctstatus()) {
                        throw new AccountClosedException();// the account has closed, we can't get anything

                    }

                } catch (AccountClosedException ex) {//handel the account close exception
                    boolean flag = false;
                    String reason = ex.getMessage();
                    double acctbalance = accounts.get(index).getacctbalance();

                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                            reason);
                    return trareceipt;

                }
                Account acct = accounts.get(index);

                TransactionReceipt trareceipt = acct.getBalance(traticket);//get the transaction receipt of the get balance
                replaceaccount(acct, index);
                return trareceipt;
            }
        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);
            return trareceipt;
        }//end try

    }

    //linked list delete account
    //get the all account information if the ssn is same
    public String getacctinfo(TransactionTicket traticket) {
        String ssn = traticket.getSSN();//get ssn
        String str = "";
        for (int i = 0; i < getNumAccts(); i++) {
            String thessn = getAccount(i).getDepositor().getSSN();
            //if the ssn is same , output the account information
            if (thessn.equals(ssn)) {
                str += getAccount(i).toString() + "\n";

            }
        }
        if (str.isEmpty()) {
            return "No Account Information";
        } else {
            return str;//return the result
        }
    }

    //get the transaction history
    public String getHistory(TransactionTicket traticket) {
        String ssn = traticket.getSSN();//get the ssn
        String str = "";
        for (int i = 0; i < getNumAccts(); i++) {
            String thessn = getAccount(i).getDepositor().getSSN();

            //if the ssn are same,output the history of transaction
            if (thessn.equals(ssn)) {
                str += getAccount(i).getHistory();

            }
        }
        if (str.isEmpty()) {
            return "No Transaction History of This SSN";
        } else {
            return str;
        }

    }

    public TransactionReceipt gettrareceipthistory(TransactionTicket traticket) {
        String ssn = traticket.getSSN();
        for (int i = 0; i < getNumAccts(); i++) {
            String thessn = getAccount(i).getDepositor().getSSN();

            if (thessn.equals(ssn)) {
                return getAccount(i).getTransactionHistory(traticket);

            }
        }
        return null;
    }

    //get the account number
    //if we can find the account by using the account number, close the account
    public TransactionReceipt CloseAccount(TransactionTicket traticket) throws InvalidAccountException, AccountClosedException {
        int acctnumber = traticket.getAcctNumber();
        int index = findAcct(acctnumber);
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);// the account cannot find
            } else {
                try {
                    if (!accounts.get(index).getacctstatus()) {
                        throw new AccountClosedException();// the account has closed

                    }

                } catch (AccountClosedException ex) {
                    boolean flag = false;
                    String reason = ex.getMessage();
                    double acctbalance = accounts.get(index).getacctbalance();

                    TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, acctbalance, acctbalance,
                            reason);
                    return trareceipt;

                }
                Account acct = accounts.get(index);

                TransactionReceipt trareceipt = acct.closeAcct(traticket);// operate the close account command

                replaceaccount(acct, index);
                return trareceipt;
            }
        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);//the transaction is failure and return this transaction receipt
            return trareceipt;
        }

    }

    //linked list delete account
    //get the account number
    //if we can find the account by using the account number, close the account
    public TransactionReceipt ReopenAccount(TransactionTicket traticket) throws InvalidAccountException {
        int acctnumber = traticket.getAcctNumber();
        int index = findAcct(acctnumber);
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);// cannot find the account
            } else {

                Account acct = accounts.get(index);

                TransactionReceipt trareceipt = acct.reopenAcct(traticket);// find the account and reopen the account
                replaceaccount(acct, index);
                return trareceipt;
            }
        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);//reopen the account is failure, and create the transaction receipt for this transaction 
            return trareceipt;
        }

    }

    //linked list reopen account
    //add account to the arraylist to the account
    //print all the account
    public String printAcct() {
        String result = "";
        for (int i = 0; i < accounts.size(); i++) {//go through the whole arraylist of the account
            Depositor depositor = accounts.get(i).getDepositor();
            int acctnumber = accounts.get(i).getAcctNumber();
            String accttype = accounts.get(i).getAcctType();
            double balance = accounts.get(i).getacctbalance();
            if (accttype.equals("CD")) {

                Account acct = (CDAccount) accounts.get(i);
                Calendar dat = acct.getMaturityDate();
                int year = dat.get(Calendar.YEAR);
                int month = dat.get(Calendar.MONTH);
                int day = dat.get(Calendar.DAY_OF_MONTH);
                String date = month + "/" + day + "/" + year;
                String str = String.format("%s%10d%15s%14.2f%13s\n", depositor, acctnumber, accttype, balance, date);
                result = result + str;
            } else {
                String date = null;
                String str = String.format("%s%10d%15s%14.2f\n", depositor, acctnumber, accttype, balance);
                result = result + str;
            }// end if 

        }// end the for loop

         double totalcdamount = getTotalCD(accounts);
        double totalchecking = getTotalChecking(accounts);
        double totalsaving = getTotalSaving(accounts);
        double amount = getTotal(accounts);
        
        String s = String.format("The Total CD Amount in the Bank is: %8.2f\n", totalcdamount);
        s += String.format("The Total Checking Amount in the Bank is: %8.2f\n", totalchecking);
        s += String.format("The Total Saving Amount in the Bank is: %8.2f\n", totalsaving);
        s += String.format("The Total Amount in the Bank is: %8.2f\n", amount);
        
        result = result + "\n" + s;

        return result;// return the result of the print account
    }

    //get the account number, and the amount of the deposit
    //if we can find the account and the amount is sufficient, make deposit to this account
    public TransactionReceipt makedeposit(TransactionTicket traticket) throws InvalidAccountException, Exception {
        int acctnumber = traticket.getAcctNumber();
        double depositamount = traticket.getTransactionAmount();
        int termofCD = traticket.gettermOfCD();
        int index = findAcct(acctnumber);
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);// the account cannot find
            } else {
                String accttype = accounts.get(index).getAcctType();// according to the different type of the account create the different account
                if (accttype.equals("CD")) {// the account is CD
                    CDAccount acct = (CDAccount) accounts.get(index);// create the account

                    TransactionReceipt trareceipt = acct.makeDeposit(traticket, depositamount, termofCD);// make deposit

                    replaceaccount(acct, index);

                    return trareceipt;
                } else if (accttype.equals("Checking")) { // the account type is checking
                    CheckingAccount acct = (CheckingAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.makeDeposit(traticket, depositamount);
                    replaceaccount(acct, index);
                    return trareceipt;
                } else {// the account type is saving
                    SavingAccount acct = (SavingAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.makeDeposit(traticket, depositamount);
                    replaceaccount(acct, index);
                    return trareceipt;
                }
            }

        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);// make deposit is failure due to the different reason
            return trareceipt;
        }
    }

    //linked make depoit
    //get the account number
    //if we can find the account number, it will make withdrawal money accourding to the account type;
    public TransactionReceipt makewithdrawal(TransactionTicket traticket) throws InvalidAccountException, Exception {
        int acctnumber = traticket.getAcctNumber();
        double depositamount = traticket.getTransactionAmount();
        int termofCD = traticket.gettermOfCD();
        int index = findAcct(acctnumber);
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);//hndel whrn the account cann ot find
            } else {
                String accttype = accounts.get(index).getAcctType();// get account type
                if (accttype.equals("CD")) {// the account type is CD
                    CDAccount acct = (CDAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.makeWithdrawal(traticket, depositamount, termofCD);
                    replaceaccount(acct, index);
                    return trareceipt;
                } else if (accttype.equals("Checking")) {//the account type is checking
                    CheckingAccount acct = (CheckingAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.makeWithdrawal(traticket, depositamount);
                    replaceaccount(acct, index);
                    return trareceipt;
                } else {//the account type is saving
                    SavingAccount acct = (SavingAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.makeWithdrawal(traticket, depositamount);
                    replaceaccount(acct, index);
                    return trareceipt;
                }
            }

        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);//return the error transactionreceipt
            return trareceipt;
        }
    }

    //this method is only for the checking account to clear check
    //get the account number, if we can find the account clear check the checking account
    public TransactionReceipt clearcheck(TransactionTicket traticket) throws InvalidAccountException, AccountNotCheckingAccountException, Exception {
        int acctnumber = traticket.getAcctNumber();
        double withdrawalamount = traticket.getTransactionAmount();
        Check check = traticket.getcheck();
        int index = findAcct(acctnumber);//find the account position
        try {
            if (index < 0) {
                throw new InvalidAccountException(acctnumber);//if the account cannot find
            } else {
                String accttype = accounts.get(index).getAcctType();
                if (accttype.equals("Checking")) {
                    CheckingAccount acct = (CheckingAccount) accounts.get(index);
                    TransactionReceipt trareceipt = acct.clearCheck(traticket, check, withdrawalamount);
                    replaceaccount(acct, index);
                    return trareceipt;
                } else {
                    throw new AccountNotCheckingAccountException(acctnumber);//if the account is not checking account
                }
            }

        } catch (InvalidAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);//return the error transaction receipt
            return trareceipt;
        } catch (AccountNotCheckingAccountException ex) {
            boolean flag = false;
            String reason = ex.getMessage();

            TransactionReceipt trareceipt = new TransactionReceipt(traticket, flag, 0, 0,
                    reason);//return the error transaction receipt
            accounts.get(index).addTransaction(trareceipt);
            return trareceipt;
        }
    }

    /**
     * get account by the account number sort key
     *
     * @param index
     * @return
     */
    /**
     * public Account getAcctByAcctNumSortKey(int index){
     * sortAccountsByAcctNum(); String accttype =
     * accounts.get(acctNumQucikSortKey.get(index)).getAcctType(); switch
     * (accttype) { case "Savings": { SavingAccount saacct = (SavingAccount)
     * accounts.get(index); Account acct = new SavingAccount(saacct); return
     * acct; } case "CD": { CDAccount cdacct = (CDAccount) accounts.get(index);
     * Account acct = new CDAccount(cdacct); return acct; } default: {
     * CheckingAccount chacct = (CheckingAccount) accounts.get(index); Account
     * acct = new CheckingAccount(chacct); return acct; } }
     *
     * }
     *
     * /**
     * get account by the ssn sort key
     *
     * @param index
     * @return
     */
    /**
     * public Account getAcctByssnSortKey(int index){ sortAccountsBySSN();
     * String accttype = accounts.get(ssnQuickSortKey.get(index)).getAcctType();
     * switch (accttype) { case "Savings": { SavingAccount saacct =
     * (SavingAccount) accounts.get(index); Account acct = new
     * SavingAccount(saacct); return acct; } case "CD": { CDAccount cdacct =
     * (CDAccount) accounts.get(index); Account acct = new CDAccount(cdacct);
     * return acct; } default: { CheckingAccount chacct = (CheckingAccount)
     * accounts.get(index); Account acct = new CheckingAccount(chacct); return
     * acct; } }
     *
     * }
     *
     * /**
     * get account by the name sort key
     *
     * @param index
     * @return
     */
    /**
     * public Account getAcctByNameSortKey(int index){ sortAccountsByName();
     * String accttype =
     * accounts.get(nameQuickSortKey.get(index)).getAcctType(); switch
     * (accttype) { case "Savings": { SavingAccount saacct = (SavingAccount)
     * accounts.get(index); Account acct = new SavingAccount(saacct); return
     * acct; } case "CD": { CDAccount cdacct = (CDAccount) accounts.get(index);
     * Account acct = new CDAccount(cdacct); return acct; } default: {
     * CheckingAccount chacct = (CheckingAccount) accounts.get(index); Account
     * acct = new CheckingAccount(chacct); return acct; } }
     *
     * }
     *
     * /**
     * get account by the balance sort key
     *
     * @param index
     * @return
     */
    /**
     * public Account getAcctByBalanceSortKey(int index){
     * sortAccountsByBalance(); String accttype =
     * accounts.get(BalanceQucikSortKey.get(index)).getAcctType(); switch
     * (accttype) { case "Savings": { SavingAccount saacct = (SavingAccount)
     * accounts.get(index); Account acct = new SavingAccount(saacct); return
     * acct; } case "CD": { CDAccount cdacct = (CDAccount) accounts.get(index);
     * Account acct = new CDAccount(cdacct); return acct; } default: {
     * CheckingAccount chacct = (CheckingAccount) accounts.get(index); Account
     * acct = new CheckingAccount(chacct); return acct; } }
     *
     * }
     * /*
     */
    /**
     * in this method we use the sort key and recursion method to sort the
     * account and create the generic method
     *
     * @param SortKey
     * @param comp
     */
    /**
     * public void QuickSort(ArrayList<Integer> SortKey, Comparator<Account>
     * comp) { SortKey.clear();//reset the sortkey //add the sort key number for
     * (int i = 0; i < accounts.size(); i++) { SortKey.add(i); }
     *
     * quicksort(accounts, SortKey, 0, accounts.size() - 1, comp);//Recursion
     * Method
     *
     * }
     * /*
     */
    /**
     *
     * @param accounts
     * @param SortKey
     * @param first
     * @param end
     * @param comp
     */
    /**
     * public static void quicksort(List<Account> accounts, ArrayList<Integer>
     * SortKey, int first, int end, Comparator<Account> comp) {
     *
     * if (first < end) { int p = partition(accounts, SortKey, first, end,
     * comp);//get pivot position * //Recursion Method quicksort(accounts,
     * SortKey, first, p - 1, comp); quicksort(accounts, SortKey, p + 1, end,
     * comp); }//end if * }//end the method * /** this the partition method use
     * to find the pivot position in this we will need to use the comparator to
     * sort the account by different condition
     *
     * @param accounts
     * @param SortKey
     * @param first
     * @param end
     * @param comp
     * @return
     */
    /**
     * public static int partition(List<Account> accounts, ArrayList<Integer>
     * SortKey, int first, int end, Comparator<Account> comp) {
     *
     * int pivotposition = first;//get the initial pivot position Account acct1
     * = accounts.get(SortKey.get(first));//get the pivot account
     *
     * for (int pos = first + 1; pos <= end; pos++) {
     * Account acct2 = accounts.get(SortKey.get(pos));// get the position account
     * if (comp.compare(acct1, acct2) > 0) {//compare two accounts
     *
     * for (int i = pos; i >= pivotposition + 1; i--) { //set position int temp
     * = SortKey.get(i); int index2 = SortKey.get(i - 1); SortKey.set(i,
     * index2); SortKey.set(i - 1, temp); }//end for * pivotposition++; }//end
     * if }// end for return pivotposition;
     *
     * }// end the method * /** this method is the generic method to sort
     * account by using the bubble sort
     *
     * @param SortKey
     * @param comp
     */
    /**
     * public void sortAccountsByBubbleSort(ArrayList<Integer> SortKey,
     * Comparator<Account> comp) { //reset the sort key SortKey.clear(); //fill
     * the sort key for (int i = 0; i < accounts.size(); i++) { SortKey.add(i);
     * } boolean swapped; int index1, index2; do { swapped = false;//initial
     * value is false for (int pos = 0; pos < (SortKey.size() - 1); pos++) {
     *
     * index1 = SortKey.get(pos);//get the index1
     * index2 = SortKey.get(pos + 1);//get the index 2
     * Account acct1 = accounts.get(index1);//get the account1
     * Account acct2 = accounts.get(index2);//get the account 2
     *
     * if (comp.compare(acct1, acct2) > 0) {//compare the account by different
     * project //set the position SortKey.set(pos, index2); SortKey.set(pos + 1,
     * index1); swapped = true;//indicate swapp successfully }//end if }//end
     * for
     *
     * } while (swapped);//end while
     *
     * }
     *
     * /**
     * this method is the generic method to sort account by using the insertion
     * sort
     *
     * @param SortKey
     * @param comp
     */
    /**
     * public void InsertionSort(ArrayList<Integer> SortKey, Comparator<Account>
     * comp) { //reset the sort key SortKey.clear(); //fill the sort key for
     * (int i = 0; i < accounts.size(); i++) { SortKey.add(i); }
     *
     * int tempIndex, numbIndex; int cand;
     *
     * for (int pos = 1; pos < SortKey.size(); pos++) {
     * tempIndex = SortKey.get(pos);
     * cand = pos;
     * while (cand > 0) { numbIndex = SortKey.get(cand - 1); Account acct1 =
     * accounts.get(numbIndex);//get the account 1 Account acct2 =
     * accounts.get(tempIndex);//get the account 2
     *
     * if (comp.compare(acct1, acct2) > 0) {//we can compare the account by
     * using the different project //set position SortKey.set(cand, numbIndex);
     * cand--; } else { break; }//end if }//end while SortKey.set(cand,
     * tempIndex); }//end for
     *
     * }
     *
     * /**
     * this method will sort account by account number by using quick sort,
     * bubble sort, insertion sort
     */
    /**
     * private void sortAccountsByAcctNum() {
     *
     * QuickSort(acctNumQucikSortKey, new AccountComparatorByAcctNumber());
     * sortAccountsByBubbleSort(acctNumBubbleSortKey, new
     * AccountComparatorByAcctNumber()); InsertionSort(acctNumInsertionSortKey,
     * new AccountComparatorByAcctNumber()); }
     *
     * /**
     * this method will sort account by ssn by using quick sort, bubble sort,
     * insertion sort
     */
    /**
     * private void sortAccountsBySSN() { QuickSort(ssnQuickSortKey, new
     * AccountComparatorBySSN()); sortAccountsByBubbleSort(ssnBubbleSortKey, new
     * AccountComparatorBySSN()); InsertionSort(ssnInsertionSortKey, new
     * AccountComparatorBySSN()); }
     *
     * /**
     * this method will sort account by Name by using quick sort, bubble sort,
     * insertion sort
     */
    /**
     * private void sortAccountsByName() { QuickSort(nameQuickSortKey, new
     * AccountComparatorByName()); sortAccountsByBubbleSort(nameBubbleSortKey,
     * new AccountComparatorByName()); InsertionSort(nameInsertionSortKey, new
     * AccountComparatorByName()); }
     *
     * /**
     * this method will sort account by balance by using quick sort, bubble
     * sort, insertion sort
     */
    /**
     * private void sortAccountsByBalance() { QuickSort(BalanceQucikSortKey, new
     * AccountComparatorByBalance());
     * sortAccountsByBubbleSort(BalanceBubbleSortKey, new
     * AccountComparatorByBalance()); InsertionSort(BalanceInsertionSortKey, new
     * AccountComparatorByBalance()); }
     *
     * /**
     * this method will print all the sort key result
     *
     * @return
     */
    /**
     * public String printsortresult() { sortAccountsByAcctNum();
     * sortAccountsBySSN(); sortAccountsByName(); sortAccountsByBalance();
     * String result1 = "Account Number---Sorted the List by Using the quick
     * sort:\n"; result1 += printsortAcct(acctNumQucikSortKey) + "\n\n"; result1
     * += "Account Number---Sorted the List by Using the bubble sort:\n";
     * result1 += printsortAcct(acctNumBubbleSortKey) + "\n\n"; result1 +=
     * "Account Number---Sorted the List by Using the insertion sort:\n";
     * result1 += printsortAcct(acctNumInsertionSortKey) + "\n\n"; result1 +=
     * "SSN---Sorted the List by Using the quick sort:\n"; result1 +=
     * printsortAcct(ssnQuickSortKey) + "\n\n"; result1 += "SSN---Sorted the
     * List by Using the bubble sort:\n"; result1 +=
     * printsortAcct(ssnBubbleSortKey) + "\n\n"; result1 += "SSN---Sorted the
     * List by Using the insertion sort:\n"; result1 +=
     * printsortAcct(ssnInsertionSortKey) + "\n\n"; result1 += "Name---Sorted
     * the List by Using the quick sort:\n"; result1 +=
     * printsortAcct(nameQuickSortKey) + "\n\n"; result1 += "Name---Sorted the
     * List by Using the bubble sort:\n"; result1 +=
     * printsortAcct(nameBubbleSortKey) + "\n\n"; result1 += "Name---Sorted the
     * List by Using the insertion sort:\n"; result1 +=
     * printsortAcct(nameInsertionSortKey) + "\n\n"; result1 +=
     * "Balance---Sorted the List by Using the quick sort:\n"; result1 +=
     * printsortAcct(BalanceQucikSortKey) + "\n\n"; result1 += "Balance---Sorted
     * the List by Using the bubble sort:\n"; result1 +=
     * printsortAcct(BalanceBubbleSortKey) + "\n\n"; result1 +=
     * "Balance---Sorted the List by Using the insertion sort:\n"; result1 +=
     * printsortAcct(BalanceInsertionSortKey) + "\n\n";
     *
     * return result1; } /*
     */
    //this method use to print the sorted format table  by the account number sort key
    /**
     * public String printsortAcct(ArrayList<Integer> SortKey) {
     *
     * String result = String.format("%-10s%-12s%6s%13s%15s%15s%15s\n", "Last",
     * "First", "SSN", "Number", "Type", "Balance", "Maturity Date"); for (int i
     * = 0; i < accounts.size(); i++) {//go through the whole arraylist of the
     * account Depositor depositor =
     * accounts.get(SortKey.get(i)).getDepositor(); int acctnumber =
     * accounts.get(SortKey.get(i)).getAcctNumber(); String accttype =
     * accounts.get(SortKey.get(i)).getAcctType(); double balance =
     * accounts.get(SortKey.get(i)).getacctbalance(); if (accttype.equals("CD"))
     * {
     *
     * Account acct = (CDAccount) accounts.get(SortKey.get(i)); Calendar dat =
     * acct.getMaturityDate(); int year = dat.get(Calendar.YEAR); int month =
     * dat.get(Calendar.MONTH); int day = dat.get(Calendar.DAY_OF_MONTH); String
     * date = month + "/" + day + "/" + year; String str =
     * String.format("%s%10d%15s%14.2f%13s\n", depositor, acctnumber, accttype,
     * balance, date); result = result + str; } else { String date = null;
     * String str = String.format("%s%10d%15s%14.2f\n", depositor, acctnumber,
     * accttype, balance); result = result + str; }// end if * }// end the for
     * loop result += String.format("The Total CD Amount in the Bank is:
     * %8.2f\n", getTotalCD()); result += String.format("The Total Checking
     * Amount in the Bank is: %8.2f\n", getTotalChecking()); result +=
     * String.format("The Total Saving Amount in the Bank is: %8.2f\n",
     * getTotalSaving()); result += String.format("The Total Amount in the Bank
     * is: %8.2f\n", getTotal());
     *
     * return result;// return the result of the print account } /*
     */
}

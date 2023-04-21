/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class ProgramHW0 {

    public static void main(String[] args) throws IOException, Exception {
        //variable declarations
        Bank bank = new Bank();								//instantiate the Bank
        char choice;										//menu item selected
        boolean notDone = true;								//loop control flag

        // open input test cases file
        File testFile = new File("mytestcases.txt");

        //create Scanner object
        Scanner kybd = new Scanner(testFile);

        //Scanner kybd = new Scanner(System.in);
        // open the output file
        PrintWriter outFile = new PrintWriter("myoutput.txt");
        //PrintWriter outFile = new PrintWriter(System.out);

        /* first part */
 /* fill and print initial database */
        readAccts(bank);
        printAccts(bank,outFile);

        
       
        printheapsortedAccts(bank, outFile);
        //printsortaccountt( bank, outFile);

        /**
         *
         * /* second part
         */
        /* prompts for a transaction and then */
 /* call functions to process the requested transaction */
        do {
            menu();

            choice = kybd.next().charAt(0);

            try {
                switch (choice) {
                    case 'q':
                    case 'Q':
                        notDone = false;

                        //printsortaccountt( bank, outFile);
                        implementticket(bank, outFile);//implement all the tickets that stored in the queue
                        
                        
                        printAccts(bank,outFile);
                        printheapsortedAccts(bank, outFile);
                        printprimaryAccts(bank, outFile);
                        break;
                    case 'b':
                    case 'B':
                        balance(bank, outFile, kybd);
                        break;
                    case 'd':
                    case 'D':
                        deposit(bank, outFile, kybd);
                        break;
                    case 'w':
                    case 'W':
                        withdrawal(bank, outFile, kybd);
                        break;
                    case 'c':
                    case 'C':
                        clearCheck(bank, outFile, kybd);
                        break;
                    case 'n':
                    case 'N':
                        newAcct(bank, outFile, kybd);
                       
                        break;
                    case 's':
                    case 'S':
                        closeAcct(bank, outFile, kybd);
                        break;
                    case 'r':
                    case 'R':
                        reopenAcct(bank, outFile, kybd);
                        break;
                    case 'x':
                    case 'X':
                        deleteAcct(bank, outFile, kybd);
                        break;
                    case 'i':
                    case 'I':
                        acctInfo(bank, outFile, kybd);
                        break;
                    case 'h':
                    case 'H':
                        acctInfoWithTransactionHistory(bank, outFile, kybd);
                        break;
                    default:
                        throw new InvalidMenuSelectionException(choice);
                }
            } catch (InvalidMenuSelectionException ex) {
                //outFile.println("Error: " + choice + " is an invalid selection -  try again");
                outFile.println(ex.getMessage());
                outFile.println();
                outFile.flush();
            }
            // give user a chance to look at output before printing menu
            //pause(kybd);
        } while (notDone);

        //close the output file
        outFile.close();

        //close the test cases input file
        kybd.close();

        System.out.println();

    }

    /* Method readAccts()
	 * Input:
	 *  bank - reference to the Bank object
	 *  maxAccts - maximum number of active accounts allowed
	 * Process:
	 *  Reads the initial database of accounts
	 * Output:
	 *  Fills in the initial array of Account objects within the Bank object
	 *  and also set the inital number of active accounts (stored in the Bank object)
     */
    public static void readAccts(Bank bank) throws IOException {
        // open input  file
        File testFile = new File("inputdata.txt");

        //create Scanner object
        Scanner infile = new Scanner(testFile);
        //Scanner kybd = new Scanner(System.in);
        try {
            while (infile.hasNext()) {
                String line = infile.nextLine();

                String[] tokens = line.split(" ");//spilt the line by the sapce, and create the array

                String first = tokens[0];
                String last = tokens[1];
                String SSN = tokens[2];
                int acctnumber = Integer.parseInt(tokens[3]);//convert to integer
                String accttype = tokens[4];
                double acctbalance = Double.parseDouble(tokens[5]);
                boolean acctstatus = true;
                Calendar today = Calendar.getInstance();

                Name name = new Name(first, last);// create the name
                Depositor depositor = new Depositor(name, SSN);// create the depositor

                //create the different account according the account type
                if (accttype.equals("Savings")) {
                    Account account = new SavingAccount(depositor, acctnumber, accttype, acctstatus, acctbalance);

                    bank.addAccount(account);
                } else if (accttype.equals("Checking")) {
                    Account account = new CheckingAccount(depositor, acctnumber, accttype, acctstatus, acctbalance);

                    bank.addAccount(account);
                } else {
                    //if the account is CD, create the maturity date
                    String date = tokens[6];
                    String[] thedate = date.split("/");
                    int day = Integer.parseInt(thedate[1]);
                    int month = Integer.parseInt(thedate[0]);
                    int year = Integer.parseInt(thedate[2]);
                    today.clear();
                    today.set(Calendar.YEAR, year);
                    today.set(Calendar.MONTH, month);
                    today.set(Calendar.DAY_OF_MONTH, day);

                    Account account = new CDAccount(depositor, acctnumber, accttype, acctstatus, acctbalance, today);

                    bank.addAccount(account);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void printAccts(Bank bank, PrintWriter outFile) throws IOException {
        try {
            outFile.println("Unsorted the List:");
            outFile.printf("%-10s%-12s%6s%13s%15s%15s%15s\n", "Last", "First", "SSN", "Number", "Type", "Balance", "Maturity Date");
            String output = bank.printAcct();// call the method
            outFile.println(output);
            outFile.println();
            
            
            outFile.println();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        outFile.flush();
    }

    /* Method printAccts:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database of accounts
	 * Output:
	 *  Prints the database of accounts
     */
    public static void printheapsortedAccts(Bank bank, PrintWriter outFile) throws IOException {
        try {

           
            String output = bank.sortedacct();// call the method
            
            outFile.println(output);
            outFile.println();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        outFile.flush();
    }
    
    public static void printprimaryAccts(Bank bank, PrintWriter outFile) throws IOException {
        try {

           
            String output = bank.printprimaryarray();// call the method
            
            outFile.println(output);
            outFile.println();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        outFile.flush();
    }


    /* Method menu()
	 * Input:
	 *  none
	 * Process:
	 *  Prints the menu of transaction choices
	 * Output:
	 *  Prints the menu of transaction choices
     */
    public static void menu() {
        System.out.println("W - Withdrawal\n"
                + "D - Deposit\n"
                + "C - Clear Check\n"
                + "N - New account \n"
                + "B - Balance\n"
                + "I - Account Info (without transaction history)\n"
                + "H - Account Info plus Account Transaction History\n"
                + "S - Close Account (close (shut), but do not delete the account) \n"
                + "R - Reopen a closed account\n"
                + "X - Delete Account (close and delete the account from the database)) \n"
                + "Q - Quit");
    }

    public static void implementticket(Bank bank, PrintWriter outFile) throws IOException {
        try {
            while (!bank.isEmpty()) {
                TransactionTicket ticket = bank.dequeueTra();//get the ticket
                String typeoftransaction = ticket.getTransactionType();//get the transaction type

                TransactionReceipt trareceipt;//create the transaction receipt
                switch (typeoftransaction) {
                    case "Close Account":
                        trareceipt = bank.CloseAccount(ticket);//close accoiunt
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Reopen Account":

                        trareceipt = bank.ReopenAccount(ticket);//reopen account

                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Balance Inquiry":
                        trareceipt = bank.getBalance(ticket);//get balance
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Deposit":
                        trareceipt = bank.makedeposit(ticket);//make deposit
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Withdrawal":
                        trareceipt = bank.makewithdrawal(ticket);//make withdrawal
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Clear Check":
                        trareceipt = bank.clearcheck(ticket);//clear check
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "New Account":
                        trareceipt = bank.openNewAcct(ticket);//create the new account
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Delete Account":
                        trareceipt = bank.deleteAcct(ticket);//delete account
                        outFile.println(trareceipt);
                        outFile.flush();
                        break;
                    case "Get Account Info":
                        outFile.println();
                        outFile.printf("%-10s%-12s%6s%13s%8s%12s%12s%15s\n", "Last", "First", "SSN", "Number", "Type", "Status", "Balance", "Maturity Date");//title
                        outFile.println(bank.getacctinfo(ticket));//get account information
                        outFile.flush();
                        break;
                    case "Get History":
                        trareceipt = bank.gettrareceipthistory(ticket);
                        outFile.println(trareceipt);
                        outFile.printf("%3s%10s%11s%10s%15s%13s%19s%10s\n", "Date", "Last", "First", "SSN", "Number", "Type", "Amount", "Flag");// print the title
                        outFile.println(bank.getHistory(ticket));//get the transaction history receipt
                        outFile.flush();
                        break;

                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /* Method closeAcct():
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.closeAccount() to execute the transaction
	 *  If the account exists, the account is closed (or stays closed)
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the account is closed (or stays closed)
	 *  Otherwise, an error message is printed
     */
    public static void closeAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();// get the account number 
            //create the transaction ticket

            TransactionTicket traticket = new TransactionTicket("Close Account", acctnumber, 0, 0, null, null, null);
            bank.enqueueTra(traticket);//add the ticket to the queue

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method ReopenAcct():
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.reopenAccount() to execute the transaction
	 *  If the account exists, the account is reopened (or remains open)
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the account is reopened (or remains open)
	 *  Otherwise, an error message is printed
     */
    public static void reopenAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();// get the account number 
            //create the transaction ticket
            TransactionTicket traticket = new TransactionTicket("Reopen Account", acctnumber, 0, 0, null, null, null);
            bank.enqueueTra(traticket);//add the ticket to the queue

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method balance:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.getBalance() to execute the transaction
	 *  If the account exists, the balance is printed
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the balance is printed
	 *  Otherwise, an error message is printed
     */
    public static void balance(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();// get the account number 
            //create the transaction ticket
            TransactionTicket traticket = new TransactionTicket("Balance Inquiry", acctnumber, 0, 0, null, null, null);
            bank.enqueueTra(traticket);//add the ticket to the queue
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method deposit:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to deposit
	 *  Creates TransactionTicket object
	 *  Calls bank.makeDeposit() to execute the transaction
	 *  If the transaction is valid, it makes the deposit and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid deposit, the deposit transaction is printed
	 *  Otherwise, an error message is printed
     */
    public static void deposit(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {

        try {
            int acctnumber = kybd.nextInt();//get the account number
            double depositamount = kybd.nextDouble();// get the deposit amount
            int termofcd = kybd.nextInt();// get the term of cd
            TransactionTicket traticket = new TransactionTicket("Deposit", acctnumber, depositamount, termofcd, null, null, null);
            //create the transaction ticket
            bank.enqueueTra(traticket);//add the ticket to the queue
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method withdrawal:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to withdraw
	 *  Creates TransactionTicket object
	 *  Calls bank.makeWithdrawal() to execute the transaction
	 *  If the transaction is valid, it makes the withdrawal and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid withdrawal, the withdrawal transaction is printed
	 *  Otherwise, an error message is printed
     */
    public static void withdrawal(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();// get the account number
            double withdrawalamount = kybd.nextDouble();//get the withdrawal amount
            int termofcd = kybd.nextInt();// get the term of cd
            TransactionTicket traticket = new TransactionTicket("Withdrawal", acctnumber, withdrawalamount, termofcd, null, null, null);// create the transaction ticket
            bank.enqueueTra(traticket);//add the ticket to the queue
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method clearCheck:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to withdraw
	 *  Creates a Check object
	 *  Creates TransactionTicket object
	 *  Calls bank.clearCheck() to execute the transaction
	 *  If the transaction is valid, it makes the withdrawal and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid withdrawal, the withdrawal transaction is printed
	 *  Otherwise, an error message is printed
     */
    public static void clearCheck(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();
            double withdrawalamount = kybd.nextDouble();
            String checkdate = kybd.next();
            String checking = checkdate;
            //create the check date
            Calendar dateofcheck = Calendar.getInstance();
            String[] thedate = checking.split("/");
            int day = Integer.parseInt(thedate[1]);
            int month = Integer.parseInt(thedate[0]);
            int year = Integer.parseInt(thedate[2]);
            dateofcheck.clear();
            dateofcheck.set(Calendar.YEAR, year);
            dateofcheck.set(Calendar.MONTH, month);
            dateofcheck.set(Calendar.DAY_OF_MONTH, day);
            Check check = new Check(acctnumber, withdrawalamount, dateofcheck);//creatye the check class
            TransactionTicket traticket = new TransactionTicket("Clear Check", acctnumber, withdrawalamount, 0, null, check, null);// create the transaction ticket
            bank.enqueueTra(traticket);//add the ticket to the queue
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method newAcct:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the new account info
	 *  Calls bank.openNewAccount() to execute the transaction
	 *  If the transaction is valid, a confirmation message is printed  
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid transaction, a confirmation message is printed  
	 *  Otherwise, an error message is printed
     */
    public static void newAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {

            int acctnumber = kybd.nextInt();//get the accoiunt number

            kybd.nextLine();
            String line2 = kybd.nextLine();

            String[] token = line2.split(" ");// create the string array for the account information

            String first = token[0];
            String last = token[1];
            String SSN = token[2];
            String acctype = token[3];
            Name name = new Name(first, last);
            Depositor depositor = new Depositor(name, SSN);

            //if the information without the depositamount and maturity date
            if (token.length == 4) {
                Account acct = new Account(depositor, acctnumber, acctype, true, 0);
                TransactionTicket traticket = new TransactionTicket("New Account", acctnumber, 0, 0, acct, null, null);
                bank.enqueueTra(traticket);//add the ticket to the queue
                //if the information without the maturity date
            } else if (token.length == 5) {
                double balance = Double.parseDouble(token[4]);
                Account acct = new Account(depositor, acctnumber, acctype, true, balance);
                TransactionTicket traticket = new TransactionTicket("New Account", acctnumber, balance, 0, acct, null, null);
                bank.enqueueTra(traticket);//add the ticket to the queue
                //has enough enough information
            } else {
                double balance = Double.parseDouble(token[4]);
                String maturitydate = token[5];
                //create the maturity date
                Calendar today = Calendar.getInstance();
                String[] thedate = maturitydate.split("/");
                int day = Integer.parseInt(thedate[1]);
                int month = Integer.parseInt(thedate[0]);
                int year = Integer.parseInt(thedate[2]);
                today.clear();
                today.set(Calendar.YEAR, year);
                today.set(Calendar.MONTH, month);
                today.set(Calendar.DAY_OF_MONTH, day);
                
                Account acct = new CDAccount(depositor, acctnumber, acctype, true, balance, today);
                
                TransactionTicket traticket = new TransactionTicket("New Account", acctnumber, balance, 0, acct, null, null);// create the transaction ticket
                bank.enqueueTra(traticket);//add the ticket to the queue
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method deleteAcct:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.deleteAccount() to execute the transaction
	 *  If the transaction is valid, a confirmation message is printed  
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
     */
    public static void deleteAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            int acctnumber = kybd.nextInt();//get the account number

            TransactionTicket traticket = new TransactionTicket("Delete Account", acctnumber, 0, 0, null, null, null);//create the transaction ticket

            bank.enqueueTra(traticket);//add the ticket to the queue

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method acctInfo:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested SSN
	 *  searches the account database for matching accounts
	 *  and prints the account info for each matching account
	 *  If there are no matching accounts, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
     */
    public static void acctInfo(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
        try {
            String SSN = kybd.next();//get the SSN number
            int size = bank.getNumAccts();// get the number of aoounts

            //print the information
            TransactionTicket traticket = new TransactionTicket("Get Account Info", 0, 0, 0, null, null, SSN);

            bank.enqueueTra(traticket);//add the ticket to the queue

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /* Method acctInfoWithTransactionHistory:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested SSN
	 *  searches the account database for matching accounts
	 *  and prints the account info for each matching account
	 *  If there are no matching accounts, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
     */
    public static void acctInfoWithTransactionHistory(Bank bank, PrintWriter outFile, Scanner kybd)
            throws IOException {
        try {
            String SSN = kybd.next();//get the SSN

            TransactionTicket traticket = new TransactionTicket("Get History", 0, 0, 0, null, null, SSN);
            bank.enqueueTra(traticket);//add the ticket to the queue

        } catch (Exception ex) {
            System.out.println(ex);
        }
        outFile.println("\n\n");
        outFile.flush();
    }

    /**
     * The method use the sort key to sort the account and return all the result
     * Process:Quick Sort
     *
     * @param bank
     * @param outFile
     * @throws IOException
     */
    /**
     * public static void printsortaccountt(Bank bank, PrintWriter outFile)
     * throws IOException { try {
     *
     * outFile.println("Sorted Account List"); String output =
     * bank.printsortresult();// call the method outFile.println(output);
     * outFile.println(); } catch (Exception ex) { System.out.println(ex); }
     * outFile.flush(); } /*
     */
    /* Method pause() */
    public static void pause(Scanner keyboard) {
    }

}

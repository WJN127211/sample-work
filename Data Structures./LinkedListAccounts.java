/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.*;

/**
 *
 * @author w1272
 */
public class LinkedListAccounts extends LinkedList {

    //Node class
    private class Node {

        Account account;
        Node next;

        //create constructor
        Node(Account acct) {
            account = acct;
            next = null;
        }

        Node(Account acct, Node n) {
            account = acct;
            next = n;
        }
    }

    private Node first;
    private Node last;

    //create the cronstructor
    public LinkedListAccounts() {
        first = null;
        last = null;
    }

    //check the list if empty
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    //get the size of the list
    public int size() {
        int count = 0;
        Node p = first;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    //add the account to the end of the list
    public void add(Account acct) {
        if (isEmpty()) {//if it is empty add to the first
            first = new Node(acct);
            last = first;
        } else {//if not add to the end
            last.next = new Node(acct);
            last = last.next;
        }
    }

    //the generic method to add the account to the list and maintain the sorted
    private <E extends Comparable> void addSortedGeneric(Account acct, E e2, ArrayList<E> e) {

        Node pred = first;//the first node
        Node p = pred;//the prev node
        Node n = p;
        E e1 = e.get(0);
        if (e1.compareTo(e2) > 0) {//if the first one bigger than the added one, only need to add to the first position
            first = new Node(acct, first);
            if (last == null) {
                last = first;
            }
        } else {
            //use the for loop to get the correct position 
            for (int i = 0; i < e.size(); i++) {
                e1 = e.get(i);

                if (e1.compareTo(e2) < 0) {
                    n = p;
                    p = pred;
                    if (pred.next == null) {
                        n = pred;
                        break;
                    } else {
                        pred = pred.next;
                    }

                    //if the comparable result is same compare the account number
                } else if (e1.compareTo(e2) == 0) {
                    int acctnum = acct.getAcctNumber();
                    int nodeacctnum = pred.account.getAcctNumber();
                    if (acctnum > nodeacctnum) {
                        n = p;
                        p = pred;
                        pred = pred.next;
                    }

                } else {
                    break;
                }

            }

            //get the correct position and add the node
            p.next = new Node(acct, p.next);
            // n.next = new Node(acct, n.next);
            if (p.next.next == null) {
                last = p.next;
            }
        }

    }

    //addthe account to the sorted list by the account number
    public void addSortedByAcctNum(Account acct) {
        if (isEmpty()) {//if it is empty add to the first
            first = new Node(acct);
            last = first;
        } else {
            Node p = first;
            //get the arraylist of the account number
            ArrayList<Integer> acctnumlist = new ArrayList<>();
            //add alll the account number to the list
            while (p != null) {
                acctnumlist.add(p.account.getAcctNumber());
                p = p.next;
            }
            Integer acctnumber = acct.getAcctNumber();
            addSortedGeneric(acct, acctnumber, acctnumlist);
        }
    }

    //add the account to the sorted list by the name
    public void addSortedByName(Account acct) {
        if (isEmpty()) {//if first is empty, add to the first
            first = new Node(acct);
            last = first;
        } else {
            Node p = first;
            //create the arraylist of the name list, and fill the list
            ArrayList<Name> acctlist = new ArrayList<>();
            while (p != null) {
                acctlist.add(p.account.getDepositor().getName());
                p = p.next;
            }
            Name name = acct.getDepositor().getName();
            addSortedGeneric(acct, name, acctlist);
        }
    }

    //add the account to the sorted list by the SSN
    public void addSortedBySSN(Account acct) {
        if (isEmpty()) {//if it is empty add to the first
            first = new Node(acct);
            last = first;
        } else {
            Node p = first;
            //create the arraylist of the SSN list, and fill the list
            ArrayList<String> acctlist = new ArrayList<>();
            while (p != null) {
                acctlist.add(p.account.getDepositor().getSSN());
                p = p.next;
            }
            String ssn = acct.getDepositor().getSSN();
            addSortedGeneric(acct, ssn, acctlist);
        }
    }

    //add the account to the sorted list by the balance
    public void addSortedByBalance(Account acct) {
        if (isEmpty()) {//if it is empty add to the first
            first = new Node(acct);
            last = first;
        } else {
            Node p = first;
            //create the arraylist of the balance list, and fill the list
            ArrayList<Double> acctlist = new ArrayList<>();
            while (p != null) {
                acctlist.add(p.account.getacctbalance());
                p = p.next;
            }
            Double balance = acct.getacctbalance();
            addSortedGeneric(acct, balance, acctlist);
        }
    }

    public void add(int index, Account acct) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Index is at least 0
        if (index == 0) {
            // New element goes at beginning
            first = new Node(acct, first);
            if (last == null) {
                last = first;
            }
            return;
        }

        // Set a reference pred to point to the node that
        // will be the predecessor of the new node
        Node pred = first;
        for (int k = 1; k <= index - 1; k++) {
            pred = pred.next;
        }

        // Splice in a node containing the new element
        pred.next = new Node(acct, pred.next);

        // Is there a new last element ?
        if (pred.next.next == null) {
            last = pred.next;
        }
    }

    //get the account at the specific position
    public Account get(int index) {
        Node pred = first;
        //if index equal to 0 return theaccount otherwise get the position node
        if (index == 0) {
            pred = first;
        } else {
            for (int k = 1; k <= index; k++) {
                pred = pred.next;
            }
        }
        //accounding the different account type, creat the account and return
        Account account = pred.account;
        String accttype = account.getAcctType();
        switch (accttype) {
            case "Savings": {
                SavingAccount saacct = (SavingAccount) account;
                Account acct = new SavingAccount(saacct);
                return acct;
            }
            case "CD": {
                CDAccount cdacct = (CDAccount) account;
                Account acct = new CDAccount(cdacct);
                return acct;
            }
            default: {
                CheckingAccount chacct = (CheckingAccount) account;
                Account acct = new CheckingAccount(chacct);
                return acct;
            }
        }
    }

    //replace the account
    public void set(int index, Account acct) {
        Node pred = first;
        if (index == 0) {//if replace the first one
            pred = first;
        } else {
            for (int k = 1; k <= index; k++) {
                pred = pred.next;
            }
        }
        pred.account = acct;
    }

    //replace the sorted account
    public void setSortedByAcctnumber(Account acct) {
        int acctnumber = acct.getAcctNumber();
        Node pred = first;
        while (pred != null) {
            if (pred.account.getAcctNumber() == acctnumber) {
                pred.account = acct;
            }
            pred = pred.next;
        }
    }

    //remove the account
    public Account theremove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        Account element;  // The element to return     
        if (index == 0) {
            // Removal of first item in the list
            element = first.account;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            // To remove an element other than the first,
            // find the predecessor of the element to be removed.
            Node pred = first;

            // Move pred forward index-1 times
            for (int k = 1; k <= index - 1; k++) {
                pred = pred.next;
            }

            // Store the value to return
            element = pred.next.account;

            // Route link around the node to be removed
            pred.next = pred.next.next;

            // Check if pred is now last
            if (pred.next == null) {
                last = pred;
            }
        }
        return element;
    }

    //remove the sorted account
    public void removeSorted(Account acct) {
        Node pred = first;
        int acctnumber = acct.getAcctNumber();
        if (acctnumber == first.account.getAcctNumber()) {
            // Removal of first item in the list
            first = first.next;
            if (first == null) {
                last = null;
            }

        }
        while (pred.next != null
                && pred.next.account.getAcctNumber() != acctnumber) {
            pred = pred.next;
        }

        // pred.next.value  is element
        pred.next = pred.next.next;

        // Check if pred is now last
        if (pred.next == null) {
            last = pred;
        }

    }

    //remove the account
    public boolean remove(Account element) {
        if (isEmpty()) {
            return false;
        }

        if (element.equals(first.account)) {
            // Removal of first item in the list
            first = first.next;
            if (first == null) {
                last = null;
            }
            return true;
        }

        // Find the predecessor of the element to remove
        Node pred = first;
        while (pred.next != null
                && !pred.next.account.equals(element)) {
            pred = pred.next;
        }

        // pred.next == null OR pred.next.value is element
        if (pred.next == null) {
            return false;
        }

        // pred.next.value  is element
        pred.next = pred.next.next;

        // Check if pred is now last
        if (pred.next == null) {
            last = pred;
        }

        return true;
    }
}

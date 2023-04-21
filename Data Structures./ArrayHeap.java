/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author w1272
 */
public class ArrayHeap {

    private ArrayList<Account> arrayHeap = new ArrayList<Account>();

    private Account valueAt(int pos) {
        return arrayHeap.get(pos);
    }

    private void siftUp() {
        int p = arrayHeap.size() - 1;  // Position to sift up
        while (p != 0) {
            int parent = (p - 1) / 2;
            if (valueAt(p).compareTo(valueAt(parent)) >= 0) {
                // We are done
                break;
            } else {
                // Do a swap
                Account temp = arrayHeap.get(parent);
                arrayHeap.set(parent, arrayHeap.get(p));
                arrayHeap.set(p, temp);

                // Move up
                p = parent;
            }
        }
    }

    private void siftUp(Comparator<Account> comp) {
        int p = arrayHeap.size() - 1;  // Position to sift up
        while (p != 0) {
            int parent = (p - 1) / 2;
            if (comp.compare(valueAt(p), valueAt(parent)) >= 0) {
                // We are done
                break;
            } else {
                // Do a swap
                Account temp = arrayHeap.get(parent);
                arrayHeap.set(parent, arrayHeap.get(p));
                arrayHeap.set(p, temp);

                // Move up
                p = parent;
            }
        }
    }

    private void siftDown() {
        int p = 0; // Position to sift down
        int size = arrayHeap.size();
        while (2 * p + 1 < size) {
            int leftChildPos = 2 * p + 1;
            int rightChildPos = leftChildPos + 1;
            int minChildPos = leftChildPos;

            // Is there a right child?
            if (rightChildPos < size) {
                // Which child is smaller

                if (valueAt(rightChildPos).compareTo(valueAt(leftChildPos)) < 0) {
                    minChildPos = rightChildPos;
                }
            }
            // If less than children we are done, 
            // otherwise swap node with smaller child

            if (valueAt(p).compareTo(valueAt(minChildPos)) <= 0) {
                break;
            } else {
                // Do the swap
                Account temp = arrayHeap.get(p);
                arrayHeap.set(p, arrayHeap.get(minChildPos));
                arrayHeap.set(minChildPos, temp);
            }
            // Go down to the child position
            p = minChildPos;
        }
    }

    private void siftDown(Comparator<Account> comp) {
        int p = 0; // Position to sift down
        int size = arrayHeap.size();
        while (2 * p + 1 < size) {
            int leftChildPos = 2 * p + 1;
            int rightChildPos = leftChildPos + 1;
            int minChildPos = leftChildPos;

            // Is there a right child?
            if (rightChildPos < size) {
                // Which child is smaller

                if (comp.compare(valueAt(rightChildPos), valueAt(leftChildPos)) < 0) {
                    minChildPos = rightChildPos;
                }
            }
            // If less than children we are done, 
            // otherwise swap node with smaller child

            if (comp.compare(valueAt(p), valueAt(minChildPos)) <= 0) {
                break;
            } else {
                // Do the swap
                Account temp = arrayHeap.get(p);
                arrayHeap.set(p, arrayHeap.get(minChildPos));
                arrayHeap.set(minChildPos, temp);
            }
            // Go down to the child position
            p = minChildPos;
        }
    }

    public boolean add(Account x) {
        // Add x at the end of the array list
        arrayHeap.add(x);

        // Sift up
        siftUp();
        return true;
    }

    public boolean add(Account x, Comparator<Account> comp) {
        // Add x at the end of the array list
        arrayHeap.add(x);

        // Sift up
        siftUp(comp);
        return true;
    }

    public boolean isEmpty() {
        return arrayHeap.size() == 0;
    }

    public Account removeMin() {
        if (isEmpty()) {
            String message = "Priority Queue is empty.";
            throw new RuntimeException(message);
        } else {
            Account val = arrayHeap.get(0);

            // Replace root by last leaf
            int lastPos = arrayHeap.size() - 1;
            arrayHeap.set(0, arrayHeap.get(lastPos));

            // Remove the last leaf
            arrayHeap.remove(arrayHeap.size() - 1);
            siftDown();
            return val;
        }
    }

    public Account removeMin(Comparator<Account> comp) {
        if (isEmpty()) {
            String message = "Priority Queue is empty.";
            throw new RuntimeException(message);
        } else {
            Account val = arrayHeap.get(0);

            // Replace root by last leaf
            int lastPos = arrayHeap.size() - 1;
            arrayHeap.set(0, arrayHeap.get(lastPos));

            // Remove the last leaf
            arrayHeap.remove(arrayHeap.size() - 1);
            siftDown(comp);
            return val;
        }
    }

    public Account remove(int pos) {
        int p = pos; // Position to sift down
        int size = arrayHeap.size();
        while (2 * p + 1 < size) {
            int leftChildPos = 2 * p + 1;
            int rightChildPos = leftChildPos + 1;
            int minChildPos = leftChildPos;

            // Is there a right child?
            if (rightChildPos < size) {
                // Which child is smaller

                if (valueAt(rightChildPos).compareTo(valueAt(leftChildPos)) < 0) {
                    minChildPos = rightChildPos;
                }
            }
            // If less than children we are done, 
            // otherwise swap node with smaller child

            if (valueAt(p).compareTo(valueAt(minChildPos)) <= 0) {
                break;
            } else {
                // Do the swap
                Account temp = arrayHeap.get(p);
                arrayHeap.set(p, arrayHeap.get(minChildPos));
                arrayHeap.set(minChildPos, temp);
            }
            // Go down to the child position
            p = minChildPos;
        }
        
        Account acct = arrayHeap.get(p);
        arrayHeap.remove(p);
        return acct;

    }
    
    public Account remove(int pos,Comparator<Account> comp) {
        int p = pos; // Position to sift down
        int size = arrayHeap.size();
        while (2 * p + 1 < size) {
            int leftChildPos = 2 * p + 1;
            int rightChildPos = leftChildPos + 1;
            int minChildPos = leftChildPos;

            // Is there a right child?
            if (rightChildPos < size) {
                // Which child is smaller

                if (comp.compare(valueAt(rightChildPos), valueAt(leftChildPos)) < 0) {
                    minChildPos = rightChildPos;
                }
            }
            // If less than children we are done, 
            // otherwise swap node with smaller child

            if (comp.compare(valueAt(p), valueAt(minChildPos)) > 0) {
                break;
            } else {
                // Do the swap
                Account temp = arrayHeap.get(p);
                arrayHeap.set(p, arrayHeap.get(minChildPos));
                arrayHeap.set(minChildPos, temp);
            }
            // Go down to the child position
            p = minChildPos;
        }
        
        Account acct = arrayHeap.get(p);
        arrayHeap.remove(p);
        return acct;

    }
    
    public Account get(int pos){
        String accttype = arrayHeap.get(pos).getAcctType();
        switch (accttype) {
            case "Savings": {
                SavingAccount saacct = (SavingAccount)arrayHeap.get(pos);
                Account acct = new SavingAccount(saacct);
                return acct;
            }
            case "CD": {
                CDAccount cdacct = (CDAccount) arrayHeap.get(pos);
                Account acct = new CDAccount(cdacct);
                return acct;
            }
            default: {
                CheckingAccount chacct = (CheckingAccount) arrayHeap.get(pos);
                Account acct = new CheckingAccount(chacct);
                return acct;
            }
        }
    }
    
    public int size(){
        return arrayHeap.size();
    }
    
    public void set(int pos, Account acct){
        
        arrayHeap.set(pos, acct);
    }


}

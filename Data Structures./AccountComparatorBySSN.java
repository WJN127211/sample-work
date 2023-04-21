/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.Comparator;

/**
 *In this class, we create the comparator to compare the SSN, if the
 * SSN are same, we compare the account number
 * @author w1272
 */
public class AccountComparatorBySSN implements Comparator<Account> {

    @Override
    public int compare(Account acct1, Account acct2) {
        String ssn1 = acct1.getDepositor().getSSN();
        String ssn2 = acct2.getDepositor().getSSN();
        if (ssn1.equals(ssn2)) {
            Integer acctNum1 = acct1.getAcctNumber();
            Integer acctNum2 = acct2.getAcctNumber();
            return (acctNum1.compareTo(acctNum2));
        }else{
            return(ssn1.compareTo(ssn2));
        }

    }
}

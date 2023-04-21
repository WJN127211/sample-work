/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

import java.util.Comparator;

/**
 *In this class, we create the comparator to compare the SSN, if the
 * Name are same, we compare the account number
 * @author w1272
 */
public class AccountComparatorByName implements Comparator<Account>{
    @Override
    public int compare(Account acct1, Account acct2) {
        Name name1 = acct1.getDepositor().getName();
        Name name2 = acct2.getDepositor().getName();
        if (name1.equals(name2)) {
            Integer acctNum1 = acct1.getAcctNumber();
            Integer acctNum2 = acct2.getAcctNumber();
            return (acctNum1.compareTo(acctNum2));
        }else{
            return(name1.compareTo(name2));
        }

    }
    
}

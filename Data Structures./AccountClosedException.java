/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

/**
 *
 * @author w1272
 */

//this Exception handeled when the account atatus is close.
public class AccountClosedException extends Exception{
    public AccountClosedException(){
        super("Account closed - Transaction voided");
    }
    
}


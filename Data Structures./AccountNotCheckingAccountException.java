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

//it handeles when the CD and Saving Account clear check
public class AccountNotCheckingAccountException extends Exception{
    public AccountNotCheckingAccountException(int acctnumber){
        super("Account "+acctnumber+" is not checking account!");
    }
}

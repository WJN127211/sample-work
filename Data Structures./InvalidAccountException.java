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
//the exception will handel when the account doesn't exist
public class InvalidAccountException extends Exception{
    public InvalidAccountException(int acctnumber){
        super("Account "+acctnumber +" is invalid");
    }
    
}

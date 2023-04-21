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
//the exception will handel whrn the amount of the funds is not effective
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(){
        super("Insuffient Funds - Transaction Voided");
    }
}
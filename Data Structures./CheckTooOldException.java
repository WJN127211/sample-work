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
//this exception will handel whrn the check has over more than 6 months
public class CheckTooOldException extends Exception{
    public CheckTooOldException(){
        super("Check Over 6 month - Transaction Voided");
    }
}


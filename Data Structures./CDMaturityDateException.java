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
//this exception will handel when the cd account doesn't meet the maturity date
public class CDMaturityDateException extends Exception{
    public CDMaturityDateException(int acctnumber){
        super("Account "+acctnumber+ " is not maturity - TransactionVoided");
    }
    
}

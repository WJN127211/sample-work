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
// if the amount is not suitable for this transaction
public class InvalidAmountException extends Exception{
    public InvalidAmountException(String tratype){
        super("Invalid "+tratype+" amount - Transaction voided");
    }
    
}

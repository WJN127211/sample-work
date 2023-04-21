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
//if the clear check, the check has the future date
public class PostDatedCheckException extends Exception{
    public PostDatedCheckException(){
        super("Posteddate Check - Transaction Voided");
    }
    
}

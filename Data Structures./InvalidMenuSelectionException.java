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
// if enter the choice doesn't exist, this exception will handel
public class InvalidMenuSelectionException extends Exception{
    public InvalidMenuSelectionException(Character choice){
        super("Invalid Menu Selection!---"+choice);
    }
    
}

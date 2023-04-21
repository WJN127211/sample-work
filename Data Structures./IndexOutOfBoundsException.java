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
public class IndexOutOfBoundsException extends Exception{
    public IndexOutOfBoundsException(String s){
        super(s + " Out of Bounds");
    }
}

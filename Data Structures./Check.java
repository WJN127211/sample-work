/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;
import java.util.Calendar;
/**
 *
 * @author w1272
 */
public class Check {
    private int Acctnumber;
    private double checkamount;
    private Calendar dateofcheck;
    
    //create the constructor
    public Check(int Acctnumber, double checkamount, Calendar dateofcheck){
        this.Acctnumber = Acctnumber;
        this.checkamount = checkamount;
        this.dateofcheck = dateofcheck;
    }
    
    //create the copy constructor
    public Calendar setdateofcheck(int day, int month, int year){
        dateofcheck.set(Calendar.YEAR, year);
        dateofcheck.set(Calendar.MONTH, month);
        dateofcheck.set(Calendar.DAY_OF_MONTH, day);
        return dateofcheck;
    }
    
    //return the account number
    public int getAcctnumber(){
        return Acctnumber;
    }
    
    
    //get the amount of check
    public double getcheckamount(){
        return checkamount;
    }
    
    //get date of check
    public Calendar getCheckDate(){
        return dateofcheck;
    }
    
    
    //copy constructor
    public Check(Check check){
        this.Acctnumber = check.Acctnumber;
        this.checkamount = check.checkamount;
        this.dateofcheck = check.dateofcheck;
    }
    
    //print the message
    @Override
    public String toString(){
        return "Account Number: "+ Acctnumber+"\n"+"Check Amount: "+checkamount+
                "Date of Check: "+dateofcheck.getTime();
    }
}

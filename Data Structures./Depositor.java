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
public class Depositor {
    private Name name;
    private String SSN;
    
    //Create the constructor
    public Depositor(Name name, String SSN){
        this.name = name;
        this.SSN = SSN;
    }
    
    public Depositor(){
        super();
    }
    
    //copy constructor
    public Depositor(Depositor dep){
        this.name = dep.name;
        this.SSN = dep.SSN;
    }
    
    //return the name
    public Name getName(){
        return name;
    }
    
    //Return the SSN
    public String getSSN(){
        return SSN;
    }
    
    //set the name
    public void setName(Name name){
        this.name = name;
    }
    
    //set the ssn
    public void setSSN(String SSN){
        this.SSN = SSN;
    }
    
    @Override
    //Print the name and SSN
    public String toString(){
        String str = String.format("%s%10s", name,SSN);
        return str;
    }
    
    @Override
    //Compare whether the names and SSN  are the same
    public boolean equals(Object o){
        if(o instanceof Depositor){
            Depositor other = (Depositor) o;
            return other.name.equals(other.name)&&other.SSN.equals(other.SSN);
        }else{
            return false;
        }
    }
}

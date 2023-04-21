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
public class Name implements Comparable<Name> {

    private String first;
    private String last;

    //Create the constructor
    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Name() {
        super();
    }

    // copy constructor
    public Name(Name name) {
        this.first = name.first;
        this.last = name.last;
    }

    //return the first name 
    public String getFirstName() {
        return first;
    }

    //return the last name
    public String getLastName() {
        return last;
    }

    //set the first name
    public void setFirstName(String first) {
        this.first = first;
    }

    //set the last name 
    public void setLastName(String last) {
        this.last = last;
    }

    @Override
    //print the name
    public String toString() {
        String str = String.format("%-10s%-10s", last, first);
        return str;
    }

    @Override
    //Compare whether the names are the same
    public boolean equals(Object o) {
        if (o instanceof Name) {
            Name other = (Name) o;
            return this.first.equals(other.first) && this.last.equals(other.last);
        } else {
            return false;
        }

    }

    @Override
    public int compareTo(Name t) {
        if (this.last.compareTo(t.getLastName()) > 0) {
            return 1;
        } else if (this.last.compareTo(t.getLastName()) == 0) {
            if (this.first.compareTo(t.getFirstName()) > 0) {
                return 1;
            } else if (this.first.compareTo(t.getFirstName()) == 0) {
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }

    }

}

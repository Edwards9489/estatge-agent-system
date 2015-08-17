/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_application;
import java.util.*;
/**
 *
 * @author Dwayne
 */
public class Person {
    // instance variables - replace the example below with your own
    private final int personRef;
    private String title;
    private String forename;
    private String surname;
    private Date dateOfBirth;
    private String nationalInsurance;
    private String gender;
    private String maritalStatus;
    private String ethnicOrigin;
    private String language;
    private String nationality;
    private String sexuality;
    private String religion;

    /**
     * Constructor for objects of class Person
     */
    public Person(int personRef, String title, String forename, String surname, int year, int month, int day, String gender)
    {
        // initialise instance variables
        this.personRef = personRef;
        this.title = title;
        this.forename = forename;
        this.surname = surname;
        dateOfBirth = new Date(year, month, day); // depreciated - need to use Date(long)
        this.gender = gender;
    }
    
    public int getPersonRef() {
        return personRef;
    }
    
    public String getForename() {
        return forename;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void updateForename(String name) {
        forename = name;
    }
    
    public void updateSurname(String name) {
        surname = name;
    }
    
    public void updateGender(String gender) {
        this.gender = gender;
    }    
}
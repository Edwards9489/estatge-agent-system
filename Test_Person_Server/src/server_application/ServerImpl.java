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
public class ServerImpl {
    
    private HashMap<String,Person> people = new HashMap<String,Person>();
    private HashMap<String,Address> addresses = new HashMap<String,Address>();
    private HashMap<String,Property> properties = new HashMap<String,Property>();
    private HashMap<String,PropertySubType> propertySubTypes = new HashMap<String,PropertySubType>();
    private HashMap<String,PropertyType> propertyTypes = new HashMap<String,PropertyType>();
    private HashMap<String,PropertyTypeValue> propertyTypeValues = new HashMap<String,PropertyTypeValue>();
    private int personRef;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    //add a Chatter to the list
    public void createPerson(String title, String forename, String surname, int year, int month, int day, String gender) {
        Person p = new Person(personRef, title, forename, surname, year, month, day, gender);
        personRef++;
        people.put(Integer.toString(p.getPersonRef()), p);
    }

    //remove a chatter
    public void deletePerson(Person p) {
        people.remove(Integer.toString(p.getPersonRef()));
    }
}
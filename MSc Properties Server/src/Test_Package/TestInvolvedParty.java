/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.PersonInterface;
import java.util.ArrayList;
import java.util.Date;
import server_application.Address;
import server_application.AddressUsage;
import server_application.ElementImpl;
import server_application.InvolvedParty;
import server_application.Person;

/**
 *
 * @author Dwayne
 */
public class TestInvolvedParty {
    public static void main(String[] args) {
        System.out.println("********************Running Involved Party Test********************");
        
        System.out.println("\n****** Creating Involved Party ******\n");
        
        //////  SETTING UP VARIABLES TO CREATE AN INVOLVED PARTY  //////
        
        Date dob = new Date();
        dob.setMonth(3);
        dob.setDate(9);
        dob.setMonth(dob.getMonth() - 312);
        
        Element test = new ElementImpl("TEST", "TEST", "DEDWARDS");
        ArrayList<ContactInterface> contacts = new ArrayList();
        
        Date date = new Date();
        date.setMonth(0);
        date.setDate(1);
        
        AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS");
        AddressUsage addressUsage = new AddressUsage(address, date, "DEDWARDS");
        
        PersonInterface person = new Person(1, test, "Dwayne", "Leroy", "Edwards", dob, "JL 81 61 90 C", test, test, test, test, test, test, test, contacts, addressUsage, "DEDWARDS");
        
        Date start = new Date();
        start.setMonth(3);
        start.setDate(1);
        
        Element relationship = new ElementImpl("APPL", "Applicant", "DEDWARDS");
        InvolvedParty test1 = new InvolvedParty(1,  person, true, true, start, relationship, "DEDWARDS");
        
        System.out.println("\n****** Testing Accessor Methods ******");
        
        System.out.println(test1);
    }
}

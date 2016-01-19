/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.Note;
import interfaces.PersonInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.ElementImpl;
import server_application.InvolvedParty;
import server_application.NoteImpl;
import server_application.Person;

/**
 *
 * @author Dwayne
 */
public class TestInvolvedParty {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Involved Party Test********************");
            
            System.out.println("\n****** Creating Involved Party ******\n");
            
            //////  SETTING UP VARIABLES TO CREATE AN INVOLVED PARTY  //////
            
            Date dob = new Date();
            dob.setMonth(3);
            dob.setDate(9);
            dob.setMonth(dob.getMonth() - 312);
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST", "DEDWARDS", new Date());
            
            Element test = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ArrayList<ContactInterface> contacts = new ArrayList();
            
            Date date = new Date();
            date.setMonth(0);
            date.setDate(1);
            
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date, note3, "DEDWARDS", new Date());
            
            PersonInterface person = new Person(1, test, "Dwayne", "Leroy", "Edwards", dob, "JL 81 61 90 C", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
            
            Date start = new Date();
            start.setMonth(3);
            start.setDate(1);
            
            Element relationship = new ElementImpl("APPL", "Applicant", note4, "DEDWARDS", new Date());
            InvolvedParty test1 = new InvolvedParty(1, 1, person, true, true, start, relationship, "DEDWARDS", new Date());
            
            System.out.println("\n****** Testing Accessor Methods ******");
            
            System.out.println(test1);
        } catch (RemoteException ex) {
            Logger.getLogger(TestInvolvedParty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

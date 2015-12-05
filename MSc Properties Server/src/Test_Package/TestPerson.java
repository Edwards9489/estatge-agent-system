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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.ElementImpl;
import server_application.NoteImpl;
import server_application.Person;

/**
 *
 * @author Dwayne
 */
public class TestPerson {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Person Test********************");
            
            System.out.println("\n****** Creating Person ******\n");
            
            System.out.println("****** Testing Accessor Methods ******\n");
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
            
            Date dob = new Date();
            dob.setMonth(3);
            dob.setDate(9);
            dob.setMonth(dob.getMonth() - 312);
            
            Element test = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ArrayList<ContactInterface> contacts = new ArrayList();
            
            Date date = new Date();
            date.setMonth(0);
            date.setDate(1);
            
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date, note3, "DEDWARDS", new Date());
            
            Person test1 = new Person(1, test, "Dwayne", "Leroy", "Edwards", dob, "JL 81 61 90 C", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
            
            
            System.out.println(test1);
        } catch (RemoteException ex) {
            Logger.getLogger(TestPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

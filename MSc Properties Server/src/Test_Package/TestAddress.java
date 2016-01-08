/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.ModifiedBy;
import server_application.NoteImpl;

/**
 *
 * @author Dwayne
 */
public class TestAddress {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Address Test********************\n");
            String temp = "";
            System.out.println("****** Creating Test Address ******\n");
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Address test1 = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            
            System.out.println("****** Testing Accessor Methods ******\n");
            
            String hello = "hello";
            String hello2 = "HELLO";
            String hello3 = hello.toUpperCase();
            boolean test = hello3.equals(hello2);
            System.out.println(test);
            System.out.println(test1.getAddressRef());
            System.out.println(test1.getBuildingNumber());
            System.out.println(test1.getBuildingName());
            System.out.println(test1.getSubStreetNumber());
            System.out.println(test1.getSubStreet());
            System.out.println(test1.getStreetNumber());
            System.out.println(test1.getStreet());
            System.out.println(test1.getArea());
            System.out.println(test1.getTown());
            System.out.println(test1.getCountry());
            System.out.println(test1.getPostcode());
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            System.out.println(test1.getCreatedBy());
            System.out.println(test1.getCreatedDate());
            System.out.println(test1.getModifiedBy());
            
            System.out.println("\n****** Amending Address ******\n");
            
            System.out.println("\n****** Testing Mutator Methods ******\n");
            
            ModifiedByInterface modTest1 = new ModifiedBy("Updated Address", "JBLOGGS", new Date());
            
            test1.updateAddress(temp, "Little Cottage", temp, temp, temp, "Brook Crescent", "Enfield", "London", "England", "EN3 4EN", "UPDATED", modTest1);
            
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest2 = new ModifiedBy("Updated Address", "DEDWARDS", new Date());
            
            test1.updateAddress("1", temp, temp, temp, temp, "Brook Crescent", "Enfield", "London", "England", "EN3 4EN", "UPDATED AGAIN",  modTest2);
            
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest3 = new ModifiedBy("Updated Address", "JBLOGGS", new Date());
            
            test1.updateAddress("79", "Kestrel House", temp, temp, temp, "Brook Crescent", "Enfield", "London", "England", "EN3 4EN", "THIRD UPDATE", modTest3);
            
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest4 = new ModifiedBy("Updated Address", "DEDWARDS", new Date());
            
            test1.updateAddress(temp, temp, temp, temp, "5", "Brook Crescent", "Enfield", "London", "England", "EN3 4EN", "FOURTH UPDATE", modTest4);
            
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest5 = new ModifiedBy("Updated Address", "JBLOGGS", new Date());
            
            test1.updateAddress(temp, temp, "7A", "Bourne Hill", temp, "The Ride", "Enfield", "London", "England", "EN3 4EN", "FIFTH UPDATE", modTest5);
            
            System.out.println(test1.printAddress());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest6 = new ModifiedBy("Updated Address", "DEDWARDS", new Date());
            
            test1.updateAddress("12", "Kestrel House", temp, "Brook Crescent", temp, "Hertford Road", "Enfield", "London", "England", "EN3 4EN", "SIXTH UPDATE", modTest6);
            
            System.out.println(test1);
            
            
            
            
            
            
            
            List<String> strings = new ArrayList();
            strings.add("TEST1");
            strings.add("TEST2");
            strings.add("TEST3");
            strings.add("TEST4");
            strings.add("TEST5");
            strings.add("TEST6");
            
            System.out.println(strings);
            
            List<String> copy = strings;
            System.out.println("XXXXXXXXXXXXXXXXXXXX");
            System.out.println(copy);
            
            strings.remove(1);
            strings.remove(3);
            
            System.out.println(strings);
            System.out.println("XXXXXXXXXXXXXXXXXXXX");
            System.out.println(copy);
        } catch (RemoteException ex) {
            Logger.getLogger(TestAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
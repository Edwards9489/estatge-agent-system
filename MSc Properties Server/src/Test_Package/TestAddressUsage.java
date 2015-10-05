/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.ModifiedByInterface;
import java.util.Date;
import server_application.Address;
import server_application.AddressUsage;
import server_application.ModifiedBy;

/**
 *
 * @author Dwayne
 */
public class TestAddressUsage {
    public static void main(String[] args) {
        System.out.println("********************Running Address Usage Test********************\n");
        System.out.println("\n****** Creating Test Address Usage ******\n");
        
        System.out.println("****** Testing Accessor Methods ******\n");
        
        Date date = new Date();
        date.setMonth(0);
        date.setDate(1);
        AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS");
        AddressUsage test1 = new AddressUsage(address, date, "DEDWARDS");
        
        System.out.println(test1.getAddressString());
        System.out.println(test1.getAddress());
        System.out.println(test1.getStartDate());
        System.out.println(test1.getEndDate());
        System.out.println(test1.getCreatedBy());
        System.out.println(test1.getCreatedDate());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate());
        System.out.println(test1.getModifiedBy());
        
        
        System.out.println("****** Testing Mutator Methods ******\n");
        
        System.out.println("****** Updating Address Usage ******\n");
        
        ModifiedByInterface modTest1 = new ModifiedBy("JBLOGGS", "Updated Address Usage");
        
        Date date1 = test1.getStartDate();
        date1.setDate(2);
        
        test1.updateAddress(test1.getAddress(), date1, modTest1);
        System.out.println(test1.getAddressString());
        System.out.println(test1.getStartDate());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate() + "\n");
        
        AddressInterface address1 = new Address(2, "", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", "JBLOGGS");
        ModifiedByInterface modTest2 = new ModifiedBy("DEDWARDS", "Updated Address Usage");
        
        test1.updateAddress(address1, test1.getStartDate(), modTest2);
        System.out.println(test1.getAddress());
        System.out.println(test1.getStartDate());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate() + "\n");
        
        System.out.println("****** Ending Address Usage ******");
        
        ModifiedByInterface modTest3 = new ModifiedBy("JBLOGGS", "Ending Address Usage");
        Date date2 = test1.getStartDate();
        date2.setDate(5);
        System.out.println(test1.isCurrent());
        test1.setEndDate(date2, modTest3);
        System.out.println(test1.getEndDate());
        System.out.println(test1.isCurrent());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate() + "\n");
        
        ModifiedByInterface modTest4 = new ModifiedBy("JBLOGGS", "Updated Address Usage");
        Date date3 = test1.getStartDate();
        date3.setDate(3);
        test1.updateAddress(address, date3, modTest4);
        System.out.println(test1.getAddressString());
        System.out.println(test1.getStartDate() + "\n");
        
        
        ModifiedByInterface modTest5 = new ModifiedBy("DEDWARDS", "Ending Address Usage");
        Date date4 = test1.getEndDate();
        date4.setMonth(11);
        test1.setEndDate(date4, modTest3);
        System.out.println(test1.getEndDate() + "\n");
        test1.updateAddress(address, date3, modTest4);
        
        System.out.println(test1);
    }
}
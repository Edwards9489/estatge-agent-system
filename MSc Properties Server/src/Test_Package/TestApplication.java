/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;
import interfaces.PropertyInterface;
import interfaces.TenancyInterface;
import java.util.ArrayList;
import java.util.Date;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Application;
import server_application.ElementImpl;
import server_application.InvolvedParty;
import server_application.ModifiedBy;
import server_application.Person;
import server_application.Property;
import server_application.Tenancy;

/**
 *
 * @author Dwayne
 */
public class TestApplication {
    public static void main(String[] args) {
        System.out.println("********************Running Application Test********************");
        
        System.out.println("\n****** Creating Application ******\n");
        
        //////  SETTING UP VARIABLES TO CREATE AN APPLICATION  //////
        
        
        Date date = new Date();
        date.setMonth(0);
        date.setDate(1);
        
        ArrayList<InvolvedParty> household = new ArrayList();
        
        Date dob1 = new Date();
        dob1.setMonth(3);
        dob1.setDate(9);
        dob1.setMonth(dob1.getMonth() - 312);
        
        Date dob2 = new Date();
        dob2.setMonth(2);
        dob2.setDate(22);
        dob2.setMonth(dob2.getMonth() - 432);
        
        Date dob3 = new Date();
        dob3.setMonth(3);
        dob3.setDate(7);
        dob3.setMonth(dob3.getMonth() - 72);
        
        Date dob4 = new Date();
        dob4.setMonth(6);
        dob4.setDate(5);
        
        
        Element test = new ElementImpl("TEST", "TEST", "DEDWARDS", new Date());
        ArrayList<ContactInterface> contacts = new ArrayList();
        
        AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS", new Date());
        AddressUsage addressUsage = new AddressUsage(1, address, date, "DEDWARDS", new Date());
        
        PersonInterface person1 = new Person(1, test, "Dwayne", "Leroy", "Edwards", dob1, "JL 81 61 90 C", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
        PersonInterface person2 = new Person(2, test, "Penny", "Kay", "Brindle", dob2, "JL 81 61 90 C", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
        PersonInterface person3 = new Person(3, test, "Tyrese", "Jerome", "Edwards", dob3, "", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
        PersonInterface person4 = new Person(4, test, "Hermione", "Leroy", "Granger", dob4, "", test, test, test, test, test, test, test, addressUsage, "DEDWARDS", new Date());
        
        Date start = new Date();
        start.setMonth(0);
        start.setDate(10);
        
        Element relationship1 = new ElementImpl("APPL", "Applicant", "DEDWARDS", new Date());
        Element relationship2 = new ElementImpl("PTNR", "Patner", "DEDWARDS", new Date());
        Element relationship3 = new ElementImpl("SON", "Son", "DEDWARDS", new Date());
        Element relationship4 = new ElementImpl("DAU", "Daughter", "DEDWARDS", new Date());
        
        InvolvedParty invParty1 = new InvolvedParty(1, 1, person1, true, true, start, relationship1, "DEDWARDS", new Date());
        InvolvedParty invParty2 = new InvolvedParty(2, 1, person2, true, false, start, relationship2, "DEDWARDS", new Date());
        InvolvedParty invParty3 = new InvolvedParty(3, 1, person3, false, false, start, relationship3, "DEDWARDS", new Date());
        InvolvedParty invParty4 = new InvolvedParty(4, 1, person4, false, false, dob4, relationship4, "DEDWARDS", new Date());
        
        AddressInterface address1 = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS", new Date());
        AddressUsage addressUsage1 = new AddressUsage(2, address1, date, "DEDWARDS", new Date());
        
        Application test1 = new Application(1, "Mr Dwayne Leroy Edwards", date, invParty1, addressUsage1, "DEDWARDS", new Date());
        
        PropertyInterface property = new Property(1, address1, new Date(), test, test, "DEDWARDS", new Date());
        TenancyInterface tenancy = new Tenancy(1, start, 12, 12, property, test1, test, "Mr Edwards & Ms Brindle", "DEDWARDS", new Date());
        
        
        System.out.println("****** Testing Accessor Methods ******\n");
        
        System.out.println(test1.getApplicationRef());
        System.out.println(test1.getAppCorrName());
        System.out.println(test1.getAppStatusCode());
        System.out.println(test1.getCurrentApplicationAddressString());
        System.out.println(test1.getApplicationAddressess());
        System.out.println(test1.getAppStartDate());
        System.out.println(test1.getAppEndDate());
        System.out.println(test1.isCurrent());
        System.out.println(test1.isAppInterestedFlag());
        System.out.println(test1.getHousehold());
        System.out.println(test1.getPropertiesInterestedIn());
        System.out.println(test1.getTenancyRef());
        System.out.println(test1.getCreatedBy());
        System.out.println(test1.getCreatedDate());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate());
        System.out.println(test1.getModifiedBy());
        
        
        System.out.println("****** Testing Mutator Methods ******");
        
        ModifiedByInterface modTest = new ModifiedBy("Updated Application", new Date(), "DEDWARDS");
        test1.updateApplication("Mr Edwards & Ms Brindle", date, modTest);
        System.out.println(test1.getAppCorrName());
        
        test1.setAppStatusCode("CUR");
        System.out.println(test1.getAppStatusCode());
        
        ModifiedByInterface modTest1 = new ModifiedBy("Added Involved Party", new Date(), "JBLOGGS");
        System.out.println(test1.getHousehold());
        test1.addInvolvedParty(invParty2, modTest1);
        System.out.println(test1.getHousehold());
        test1.addInvolvedParty(invParty3, modTest1);
        
        
        test1.addInvolvedParty(invParty4, modTest1);
        System.out.println(test1.getHousehold());
        
        
        test1.endInvolvedParty(invParty4.getInvolvedPartyRef(), dob4, test, modTest1);
        
        
        
        ModifiedByInterface modTest2 = new ModifiedBy("Set Tenancy", new Date(), "DEDWARDS");
        System.out.println(test1.getTenancyRef());
        test1.setTenancy(tenancy.getAgreementRef(), modTest2);
        System.out.println(test1.getTenancyRef());
        
        
        System.out.println(test1);
    }
}
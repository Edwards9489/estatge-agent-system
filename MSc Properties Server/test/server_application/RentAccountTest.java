/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.TenancyInterface;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dwayne
 */
public class RentAccountTest {
    
    public RentAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setRent method, of class RentAccount.
     */
    @Test
    public void testSetRent() {
        try {
            System.out.println("setRent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, 1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            PropertyElement rent = new PropertyElement(2, 1, element2, date.getTime(), true, null, 1200.00, note5, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy2);
            TenancyInterface tenancy = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            RentAccount instance = new RentAccount(1, tenancy, "DEDWARDS", new Date());
            
            assertEquals(true, tenancy.getExpectedRevenue() == instance.getRent());
            instance.setRent(1900.00);
            assertEquals(true, instance.getRent() == 1900.00);
            assertEquals(false, tenancy.getExpectedRevenue() == instance.getRent());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRent method, of class RentAccount.
     */
    @Test
    public void testGetRent() {
        System.out.println("getRent");
        try {
            System.out.println("getRent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, 1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            PropertyElement rent = new PropertyElement(2, 1, element2, date.getTime(), true, null, 1200.00, note5, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy2);
            TenancyInterface tenancy = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            RentAccount instance = new RentAccount(1, tenancy, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getRent() == tenancy.getExpectedRevenue());
            assertEquals(false, tenancy.getExpectedRevenue() == 900.00);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTenancy method, of class RentAccount.
     */
    @Test
    public void testGetTenancy() {
        try {
            System.out.println("getTenancy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, 1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            PropertyElement rent = new PropertyElement(2, 1, element2, date.getTime(), true, null, 1200.00, note5, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy2);
            TenancyInterface tenancy = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            TenancyInterface tenancy2 = new Tenancy(2, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            RentAccount instance = new RentAccount(1, tenancy, "DEDWARDS", new Date());
            
            assertEquals(tenancy, instance.getTenancy());
            assertEquals(false, instance.getTenancy().equals(tenancy2));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTenancyRef method, of class RentAccount.
     */
    @Test
    public void testGetTenancyRef() throws Exception {
        try {
            System.out.println("getTenancyRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, 1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            PropertyElement rent = new PropertyElement(2, 1, element2, date.getTime(), true, null, 1200.00, note5, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy2);
            TenancyInterface tenancy = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            TenancyInterface tenancy2 = new Tenancy(2, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            RentAccount instance = new RentAccount(1, tenancy, "DEDWARDS", new Date());
            
            assertEquals(tenancy.getAgreementRef(), instance.getTenancyRef());
            assertEquals(false, instance.getTenancyRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
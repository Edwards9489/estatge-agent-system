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
import interfaces.PropertyInterface;
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
public class TenancyTest {
    
    public TenancyTest() {
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
     * Test of setTenType method, of class Tenancy.
     */
    @Test
    public void testSetTenType() {
        try {
            System.out.println("setTenType");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(element, instance.getTenType());
            instance.setTenType(element2, modifiedBy);
            assertEquals(element2, instance.getTenType());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(false, instance.getTenType().equals(element));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setRent method, of class Tenancy.
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy);
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, property.getRent() == instance.getRent());
            instance.setRent(1500.00, modifiedBy2);
            assertEquals(true, instance.getRent() == 1500.00);
            assertEquals(false, property.getRent() == instance.getRent());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setCharges method, of class Tenancy.
     */
    @Test
    public void testSetCharges() {
        try {
            System.out.println("setCharges");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(4, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, property.getCharges() == instance.getCharges());
            instance.setCharges(500.00, modifiedBy2);
            assertEquals(true, instance.getCharges() == 500.00);
            assertEquals(false, property.getCharges() == instance.getCharges());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropertyRef method, of class Tenancy.
     */
    @Test
    public void testGetPropertyRef() {
        try {
            System.out.println("getPropertyRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(1, instance.getPropertyRef());
            assertEquals(false, instance.getPropertyRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProperty method, of class Tenancy.
     */
    @Test
    public void testGetProperty() {
        try {
            System.out.println("getProperty");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyInterface property2 = new Property(2, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(property, instance.getProperty());
            assertEquals(false, instance.getProperty().equals(property2));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getApplicationRef method, of class Tenancy.
     */
    @Test
    public void testGetApplicationRef() {
        try {
            System.out.println("getApplicationRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(1, instance.getApplicationRef());
            assertEquals(false, instance.getApplicationRef() == 3);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getApplication method, of class Tenancy.
     */
    @Test
    public void testGetApplication() {
        System.out.println("getApplication");
        try {
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            ApplicationInterface application2 = new Application(2, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(application, instance.getApplication());
            assertEquals(false, instance.getApplication().equals(application2));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTenType method, of class Tenancy.
     */
    @Test
    public void testGetTenType() {
        System.out.println("getTenType");
        try {
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(element, instance.getTenType());
            assertEquals(false, instance.getTenType().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRent method, of class Tenancy.
     */
    @Test
    public void testGetRent() {
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy);
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, property.getRent() == instance.getRent());
            assertEquals(false, instance.getRent() == 1500.00);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCharges method, of class Tenancy.
     */
    @Test
    public void testGetCharges() {
        try {
            System.out.println("getCharges");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, property.getCharges() == instance.getCharges());
            assertEquals(false, instance.getCharges() == 500.00);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getExpectedRevenue method, of class Tenancy.
     */
    @Test
    public void testGetExpectedRevenue() {
        System.out.println("getExpectedRevenue");
        try {
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
            ApplicationInterface application = new Application(1, "MR D L EDWARDS", date.getTime(), "NEW", "DEDWARDS", new Date());
            Property property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            property.createPropertyElement(charges, modifiedBy);
            PropertyElement rent = new PropertyElement(2, element2, date.getTime(), true, null, 1200.00, note5, "DEDWARDS", new Date());
            property.createPropertyElement(rent, modifiedBy2);
            Tenancy instance = new Tenancy(1, date2.getTime(), 12, 1, property, application, element, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, (property.getRent() + property.getCharges()) == instance.getExpectedRevenue());
            assertEquals(false, instance.getExpectedRevenue() == 500.00);
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
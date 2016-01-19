/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.LandlordInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class LeaseTest {
    
    public LeaseTest() {
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
     * Test of addLandlord method, of class Lease.
     */
    @Test
    public void testAddLandlord() {
        try {
            System.out.println("addLandlord");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address2, date2.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date2.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(0, instance.getLandlords().size());
            assertEquals(false, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            instance.addLandlord(landlord, modifiedBy);
            assertEquals(true, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            assertEquals(1, instance.getLandlords().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.addLandlord(landlord, modifiedBy);
            assertEquals(1, instance.getLandlords().size());
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of endLandlord method, of class Lease.
     */
    @Test
    public void testEndLandlord() {
        try {
            System.out.println("endLandlord");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address2, date2.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date2.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(0, instance.getLandlords().size());
            assertEquals(false, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            instance.addLandlord(landlord, modifiedBy);
            assertEquals(true, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            assertEquals(1, instance.getLandlords().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.endLandlord(2, modifiedBy2);
            assertEquals(true, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            assertEquals(1, instance.getLandlords().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.endLandlord(landlord.getLandlordRef(), modifiedBy2);
            assertEquals(false, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            assertEquals(0, instance.getLandlords().size());
            assertEquals(2, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLandlords method, of class Lease.
     */
    @Test
    public void testGetLandlords() {
        try {
            System.out.println("getLandlords");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            LandlordInterface landlord2 = new Landlord(2, person, "DEDWARDS", new Date());
            LandlordInterface landlord3 = new Landlord(3, person, "DEDWARDS", new Date());
            LandlordInterface landlord4 = new Landlord(4, person, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address2, date2.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date2.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            List<LandlordInterface> expResult = new ArrayList();
            List<LandlordInterface> test = new ArrayList();
            instance.addLandlord(landlord, modifiedBy);
            expResult.add(landlord);
            assertEquals(1, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            instance.addLandlord(landlord2, modifiedBy2);
            expResult.add(landlord2);
            assertEquals(2, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            instance.addLandlord(landlord3, modifiedBy3);
            expResult.add(landlord3);
            assertEquals(3, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            instance.addLandlord(landlord4, modifiedBy4);
            expResult.add(landlord4);
            assertEquals(4, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            instance.endLandlord(landlord.getLandlordRef(), modifiedBy5);
            expResult.remove(landlord);
            assertEquals(3, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            assertEquals(false, instance.getLandlords().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProperty method, of class Lease.
     */
    @Test
    public void testGetProperty() {
        try {
            System.out.println("getProperty");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyInterface property2 = new Property(2, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(property, instance.getProperty());
            assertEquals(false, instance.getProperty().equals(property2));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropertyRef method, of class Lease.
     */
    @Test
    public void testGetPropertyRef() {
        try {
            System.out.println("getPropertyRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(1, instance.getPropertyRef());
            assertEquals(false, instance.getPropertyRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isFullManagement method, of class Lease.
     */
    @Test
    public void testIsFullManagement() {
        try {
            System.out.println("isFullManagement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isFullManagement());
            assertEquals(false, result.equals(instance.isFullManagement()));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isAlreadyLandlord method, of class Lease.
     */
    @Test
    public void testIsAlreadyLandlord() {
        try {
            System.out.println("isAlreadyLandlord");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address2, date2.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date2.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(false, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            instance.addLandlord(landlord, modifiedBy);
            assertEquals(true, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            instance.endLandlord(2, modifiedBy2);
            assertEquals(true, instance.isAlreadyLandlord(landlord.getLandlordRef()));
            instance.endLandlord(landlord.getLandlordRef(), modifiedBy2);
            assertEquals(false, instance.isAlreadyLandlord(landlord.getLandlordRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getExpenditure method, of class Lease.
     */
    @Test
    public void testGetExpenditure() {
        try {
            System.out.println("getExpenditure");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            Lease instance = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, instance.getExpenditure() == 1000.00);
            assertEquals(false, instance.getExpenditure() == 750.00);
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
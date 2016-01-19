/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
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
public class AddressUsageTest {
    
    public AddressUsageTest() {
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
     * Test of modifiedBy method, of class AddressUsage.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class AddressUsage.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateAddress method, of class AddressUsage.
     */
    @Test
    public void testUpdateAddress() {
        try {
            System.out.println("updateAddress");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 11);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals(address, instance.getAddress());
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(note3.getNote(), instance.getComment());
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.updateAddress(address2, date3.getTime(), "NEW COMMENT", modifiedBy);
            
            assertEquals(address2, instance.getAddress());
            assertEquals(date3.getTime(), instance.getStartDate());
            assertEquals("NEW COMMENT", instance.getComment());
            assertEquals(1, instance.getModifiedBy().size());
            
            instance.setEndDate(endDate, modifiedBy2);
            
            assertEquals(2, instance.getModifiedBy().size());
            
            instance.updateAddress(address, date.getTime(), "TEST", modifiedBy3);
            
            assertEquals(address2, instance.getAddress());
            assertEquals(date3.getTime(), instance.getStartDate());
            assertEquals("NEW COMMENT", instance.getComment());
            assertEquals(2, instance.getModifiedBy().size());
            
            assertEquals(false, address.equals(instance.getAddress()));
            assertEquals(false, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getComment().equals("TEST"));
            assertEquals(false, instance.getModifiedBy().size() == 3);
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddressUsageRef method, of class AddressUsage.
     */
    @Test
    public void testGetAddressUsageRef() {
        try {
            System.out.println("getAddressUsageRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getAddressUsageRef() == 4);
            assertEquals(1, instance.getAddressUsageRef());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddressString method, of class AddressUsage.
     */
    @Test
    public void testGetAddressString() {
        try {
            System.out.println("getAddressString");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getAddressString().equals("TEST"));
            assertEquals("12 Kestrel House, 1 The Close, 1 The Ride, Enfield, London, England, EN3 4EN", instance.getAddressString());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddress method, of class AddressUsage.
     */
    @Test
    public void testGetAddress() {
        try {
            System.out.println("getAddress");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getAddress().equals(address2));
            assertEquals(address, instance.getAddress());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class AddressUsage.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        try {
            System.out.println("getStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getStartDate().equals(new Date()));
            assertEquals(date.getTime(), instance.getStartDate());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class AddressUsage.
     */
    @Test
    public void testGetEndDate() {
        try {
            System.out.println("getEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class AddressUsage.
     */
    @Test
    public void testIsCurrent() {
        System.out.println("isCurrent");
        try {
            System.out.println("getEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Boolean test = false;
            Date endDate = date2.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            assertEquals(false, test.equals(instance.isCurrent()));
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(false, instance.isCurrent());
            assertEquals(true, test.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class AddressUsage.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(note2, instance.getNote());
            assertEquals(false, instance.getNote().equals(note));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class AddressUsage.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class AddressUsage.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.updateAddress(address, date.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class AddressUsage.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.updateAddress(address, date.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class AddressUsage.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateAddress(address, date.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class AddressUsage.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateAddress(address, date.getTime(), "NEW COMMENT", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class AddressUsage.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.updateAddress(address, date.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.updateAddress(address, date.getTime(), "UPDATED COMMENT AGAIN", modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class AddressUsage.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class AddressUsage.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar createdDate = Calendar.getInstance();
            createdDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressUsage instance = new AddressUsage(1, address, date.getTime(), note2, "DEDWARDS", createdDate.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(createdDate.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
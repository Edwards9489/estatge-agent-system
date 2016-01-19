/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.LeaseInterface;
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
public class LeaseAccountTest {
    
    public LeaseAccountTest() {
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
     * Test of getLease method, of class LeaseAccount.
     */
    @Test
    public void testGetLease() {
        try {
            System.out.println("getLease");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            LeaseInterface lease = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            LeaseInterface lease2 = new Lease(2, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            LeaseAccount instance = new LeaseAccount(1, lease, "DEDWARDS", new Date());
            
            assertEquals(lease, instance.getLease());
            assertEquals(false, instance.getLease().equals(lease2));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLeaseRef method, of class LeaseAccount.
     */
    @Test
    public void testGetLeaseRef() {
        try {
            System.out.println("getLeaseRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            PropertyInterface property = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            LeaseInterface lease = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            LeaseAccount instance = new LeaseAccount(1, lease, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getLeaseRef() == 1);
            assertEquals(false, instance.getLeaseRef() == 99);
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getExpenditure method, of class LeaseAccount.
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
            LeaseInterface lease = new Lease(1, date.getTime(), 12, 1, property, true, 1000.00, "EDM", "DEDWARDS", new Date());
            LeaseAccount instance = new LeaseAccount(1, lease, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getExpenditure() == 1000.00);
            assertEquals(false, instance.getExpenditure() == 750.00);
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyInterface;
import java.io.File;
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
public class ApplicationTest {
    
    public ApplicationTest() {
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
     * Test of modifiedBy method, of class Application.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateApplication method, of class Application.
     */
    @Test
    public void testUpdateApplication()  {
        try {
            System.out.println("updateApplication");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals("Dwayne Leroy Edwards", instance.getAppCorrName());
            assertEquals(date2.getTime(), instance.getAppStartDate());
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.updateApplication("UPDATED CORR NAME", date3.getTime(), modifiedBy);
            
            assertEquals("UPDATED CORR NAME", instance.getAppCorrName());
            assertEquals(date3.getTime(), instance.getAppStartDate());
            assertEquals(1, instance.getModifiedBy().size());
            
            assertEquals(false, instance.getAppCorrName().equals("Dwayne Leroy Edwards"));
            assertEquals(false, instance.getAppStartDate().equals(date2.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class Application.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getAppEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date3.getTime(), modifiedBy);
            assertEquals(date3.getTime(), instance.getAppEndDate());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setAppStatusCode method, of class Application.
     */
    @Test
    public void testSetAppStatusCode() {
        try {
            System.out.println("setAppStatusCode");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals("NEW", instance.getAppStatusCode());
            instance.setAppStatusCode("VOID");
            assertEquals("VOID", instance.getAppStatusCode());
            assertEquals(false, instance.getAppStatusCode().equals("NEW"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setAppAddress method, of class Application.
     */
    @Test
    public void testSetAppAddress()  {
        try {
            System.out.println("setAppAddress");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressInterface address3 = new Address(3, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage4 = new AddressUsage(4, address3, date2.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage2.getAddressUsageRef()));
            instance.setAppAddress(addressUsage3, modifiedBy);
            assertEquals(2, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage3.getAddressUsageRef()));
            instance.setAppAddress(addressUsage4, modifiedBy2);
            assertEquals(3, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage4.getAddressUsageRef()));
            assertEquals(false, instance.isCurrentAddress(addressUsage2.getAddressUsageRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteAppAddress method, of class Application.
     */
    @Test
    public void testDeleteAppAddress()  {
        try {
            System.out.println("deleteAppAddress");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressInterface address3 = new Address(3, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage4 = new AddressUsage(4, address3, date2.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage2.getAddressUsageRef()));
            instance.setAppAddress(addressUsage3, modifiedBy);
            assertEquals(2, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage3.getAddressUsageRef()));
            instance.setAppAddress(addressUsage4, modifiedBy2);
            assertEquals(3, instance.getApplicationAddressess().size());
            assertEquals(true, instance.isCurrentAddress(addressUsage4.getAddressUsageRef()));
            
            instance.deleteAppAddress(addressUsage4.getAddressUsageRef(), modifiedBy3);
            assertEquals(2, instance.getApplicationAddressess().size());
            assertEquals(false, instance.isCurrentAddress(addressUsage4.getAddressUsageRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addInvolvedParty method, of class Application.
     */
    @Test
    public void testAddInvolvedParty()  {
        try {
            System.out.println("addInvolvedParty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, date2.getTime(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, false, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getHousehold().size());
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(2, instance.getHousehold().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of endInvolvedParty method, of class Application.
     */
    @Test
    public void testEndInvolvedParty()  {
        try {
            System.out.println("endInvolvedParty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, date2.getTime(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, false, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getHousehold().size());
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(2, instance.getHousehold().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.endInvolvedParty(invParty2.getInvolvedPartyRef(), date3.getTime(), element, modifiedBy2);
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            assertEquals(2, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteInvolvedParty method, of class Application.
     */
    @Test
    public void testDeleteInvolvedParty()  {
        try {
            System.out.println("deleteInvolvedParty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, date2.getTime(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, false, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getHousehold().size());
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(2, instance.getHousehold().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.deleteInvolvedParty(invParty2.getInvolvedPartyRef(), modifiedBy2);
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of changeMainApp method, of class Application.
     */
    @Test
    public void testChangeMainApp()  {
        try {
            System.out.println("changeMainApp");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, date2.getTime(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, false, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            Person person3 = new Person(3, element, "Dwayne", "Leroy", "Edwards", date2.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty3 = new InvolvedParty(3, 1, person3, false, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(invParty, instance.getMainApp());
            
            instance.changeMainApp(invParty.getInvolvedPartyRef(), new Date(), element, modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.changeMainApp(invParty2.getInvolvedPartyRef(), new Date(), element, modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            assertEquals(false, instance.getMainApp().equals(invParty2));
            assertEquals(invParty, instance.getMainApp());
            
            instance.addInvolvedParty(invParty3, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            instance.changeMainApp(invParty3.getInvolvedPartyRef(), new Date(), element, modifiedBy2);
            assertEquals(false, instance.getMainApp().equals(invParty3));
            assertEquals(invParty, instance.getMainApp());
            assertEquals(1, instance.getModifiedBy().size());
            
            instance.addInvolvedParty(invParty2, modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            instance.changeMainApp(invParty2.getInvolvedPartyRef(), new Date(), element, modifiedBy3);
            assertEquals(true, instance.getMainApp().equals(invParty2));
            assertEquals(false, instance.getMainApp().equals(invParty));
            
            assertEquals(false, invParty.isCurrent());
            assertEquals(false, invParty.isMainInd());
            assertEquals(true, invParty2.isMainInd());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setTenancy method, of class Application.
     */
    @Test
    public void testSetTenancy()  {
        try {
            System.out.println("setTenancy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getTenancyRef());
            assertEquals(false, instance.hasTenancyRef());
            instance.setTenancy(4, modifiedBy);
            assertEquals(true, instance.hasTenancyRef());
            assertEquals(true, instance.getTenancyRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of clearTenancy method, of class Application.
     */
    @Test
    public void testClearTenancy() {
        try {
            System.out.println("clearTenancy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getTenancyRef());
            instance.setTenancy(4, modifiedBy);
            assertEquals(true, instance.hasTenancyRef());
            assertEquals(true, instance.getTenancyRef() == 4);
            instance.clearTenancy(modifiedBy2);
            assertEquals(null, instance.getTenancyRef());
            assertEquals(false, instance.hasTenancyRef());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addInterestedProperty method, of class Application.
     */
    @Test
    public void testAddInterestedProperty()  {
        try {
            System.out.println("addInterestedProperty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            PropertyInterface property = new Property(1, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.isAppInterestedFlag());
            assertEquals(0, instance.getModifiedBy().size());
            instance.addInterestedProperty(property, modifiedBy);
            assertEquals(true, instance.isAppInterestedFlag());
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of endInterestInProperty method, of class Application.
     */
    @Test
    public void testEndInterestInProperty()  {
        try {
            System.out.println("endInterestInProperty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            PropertyInterface property = new Property(1, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.isAppInterestedFlag());
            assertEquals(0, instance.getModifiedBy().size());
            instance.addInterestedProperty(property, modifiedBy);
            assertEquals(true, instance.isAppInterestedFlag());
            assertEquals(1, instance.getModifiedBy().size());
            instance.endInterestInProperty(property, modifiedBy2);
            assertEquals(false, instance.isAppInterestedFlag());
            assertEquals(2, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Application.
     */
    @Test
    public void testCreateNote() {
        try {
            System.out.println("createNote");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note5, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note5.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class Application.
     */
    @Test
    public void testDeleteNote()  {
        try {
            System.out.println("deleteNote");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note5, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            instance.deleteNote(7, modifiedBy2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteNote(note5.getReference(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getNotes().size());
            assertEquals(false, instance.hasNote(note5.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createDocument method, of class Application.
     */
    @Test
    public void testCreateDocument()  {
        try {
            System.out.println("createDocument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getDocuments().size());
            instance.createDocument(document, modifiedBy);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(true, instance.hasDocument("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteDocument method, of class Application.
     */
    @Test
    public void testDeleteDocument()  {
        try {
            System.out.println("deleteDocument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note5, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getDocuments().size());
            instance.createDocument(document, modifiedBy);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteDocument(7, modifiedBy2);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(modifiedBy, instance.getLastModification());
            instance.deleteDocument(document.getDocumentRef(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isPersonHouseholdMember method, of class Application.
     */
    @Test
    public void testIsPersonHouseholdMember()  {
        try {
            System.out.println("isPersonHouseholdMember");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, true, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isPersonHouseholdMember(invParty.getPersonRef()));
            assertEquals(false, instance.isPersonHouseholdMember(invParty2.getPersonRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(true, instance.isPersonHouseholdMember(invParty2.getPersonRef()));
            instance.endInvolvedParty(invParty2.getInvolvedPartyRef(), date3.getTime(), element, modifiedBy2);
            assertEquals(false, invParty2.isCurrent());
            assertEquals(false, instance.isPersonHouseholdMember(invParty2.getPersonRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isHouseholdMember method, of class Application.
     */
    @Test
    public void testIsHouseholdMember()  {
        try {
            System.out.println("isHouseholdMember");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, date2.getTime(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, true, false, date2.getTime(), element, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isHouseholdMember(invParty.getInvolvedPartyRef()));
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(true, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
            instance.endInvolvedParty(invParty2.getInvolvedPartyRef(), date3.getTime(), element, modifiedBy2);
            assertEquals(false, invParty2.isCurrent());
            assertEquals(false, instance.isHouseholdMember(invParty2.getInvolvedPartyRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getInvolvedParty method, of class Application.
     */
    @Test
    public void testGetInvolvedParty()  {
        try {
            System.out.println("getInvolvedParty");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, true, false, new Date(), element, "DEDWARDS", new Date());
            
            assertEquals(invParty, instance.getInvolvedParty(invParty.getInvolvedPartyRef()));
            assertEquals(null, instance.getInvolvedParty(invParty2.getInvolvedPartyRef()));
            instance.addInvolvedParty(invParty2, modifiedBy);
            assertEquals(invParty2, instance.getInvolvedParty(invParty2.getInvolvedPartyRef()));
            assertEquals(false, instance.getInvolvedParty(invParty.getInvolvedPartyRef()).equals(invParty2));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getMainApp method, of class Application.
     */
    @Test
    public void testGetMainApp()  {
        try {
            System.out.println("getMainApp");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, true, false, new Date(), element, "DEDWARDS", new Date());
            
            assertEquals(invParty, instance.getMainApp());
            assertEquals(false, instance.getMainApp().equals(invParty2));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getApplicationRef method, of class Application.
     */
    @Test
    public void testGetApplicationRef()  {
        try {
            System.out.println("getApplicationRef");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(1, instance.getApplicationRef());
            assertEquals(false, instance.getApplicationRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAppCorrName method, of class Application.
     */
    @Test
    public void testGetAppCorrName()  {
        try {
            System.out.println("getAppCorrName");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals("Dwayne Leroy Edwards", instance.getAppCorrName());
            assertEquals(false, instance.getAppCorrName().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAppStatusCode method, of class Application.
     */
    @Test
    public void testGetAppStatusCode()  {
        try {
            System.out.println("getAppStatusCode");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals("NEW", instance.getAppStatusCode());
            instance.setAppStatusCode("HSED");
            assertEquals("HSED", instance.getAppStatusCode());
            assertEquals(false, instance.getAppStatusCode().equals("NEW"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAppStartDate method, of class Application.
     */
    @Test
    public void testGetAppStartDate()  {
        try {
            System.out.println("getAppStartDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(date2.getTime(), instance.getAppStartDate());
            assertEquals(false, instance.getAppStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAppEndDate method, of class Application.
     */
    @Test
    public void testGetAppEndDate()  {
        try {
            System.out.println("getAppEndDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getAppEndDate());
            instance.setEndDate(date3.getTime(), modifiedBy);
            assertEquals(date3.getTime(), instance.getAppEndDate());
            assertEquals(false, instance.getAppEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrentAddress method, of class Application.
     */
    @Test
    public void testIsCurrentAddress()  {
        try {
            System.out.println("isCurrentAddress");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrentAddress(addressUsage2.getAddressUsageRef()));
            instance.setAppAddress(addressUsage3, modifiedBy);
            assertEquals(true, instance.isCurrentAddress(addressUsage3.getAddressUsageRef()));
            assertEquals(false, instance.isCurrentAddress(addressUsage2.getAddressUsageRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCurrentApplicationAddress method, of class Application.
     */
    @Test
    public void testGetCurrentApplicationAddress()  {
        try {
            System.out.println("getCurrentApplicationAddress");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals(addressUsage2, instance.getCurrentApplicationAddress());
            instance.setAppAddress(addressUsage3, modifiedBy);
            assertEquals(addressUsage3, instance.getCurrentApplicationAddress());
            assertEquals(false, instance.getCurrentApplicationAddress().equals(addressUsage2));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCurrentApplicationAddressString method, of class Application.
     */
    @Test
    public void testGetCurrentApplicationAddressString()  {
        try {
            System.out.println("getCurrentApplicationAddressString");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            
            assertEquals("12 Kestrel House, 1 The Close, 1 The Ride, Enfield, London, England, EN3 4EN", instance.getCurrentApplicationAddressString());
            instance.setAppAddress(addressUsage3, modifiedBy);
            assertEquals("1 The Close, 1 The Ride, Enfield, London, England, EN3 4EN", instance.getCurrentApplicationAddressString());
            assertEquals(false, instance.getCurrentApplicationAddressString().equals("12 Kestrel House, 1 The Close, 1 The Ride, Enfield, London, England, EN3 4EN"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getApplicationAddressess method, of class Application.
     */
    @Test
    public void testGetApplicationAddressess()  {
        try {
            System.out.println("getApplicationAddressess");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage3 = new AddressUsage(3, address2, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressInterface address3 = new Address(3, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage4 = new AddressUsage(4, address3, date2.getTime(), note3, "DEDWARDS", new Date());
            
            List<AddressUsage> expResult = new ArrayList();
            List<AddressUsage> test = new ArrayList();
            expResult.add(addressUsage2);
            assertEquals(1, instance.getApplicationAddressess().size());
            assertEquals(expResult, instance.getApplicationAddressess());
            instance.setAppAddress(addressUsage3, modifiedBy);
            expResult.add(addressUsage3);
            assertEquals(2, instance.getApplicationAddressess().size());
            assertEquals(expResult, instance.getApplicationAddressess());
            instance.setAppAddress(addressUsage4, modifiedBy2);
            expResult.add(addressUsage4);
            assertEquals(3, instance.getApplicationAddressess().size());
            assertEquals(expResult, instance.getApplicationAddressess());
            instance.deleteAppAddress(addressUsage4.getAddressUsageRef(), modifiedBy3);
            expResult.remove(addressUsage4);
            assertEquals(2, instance.getApplicationAddressess().size());
            assertEquals(expResult, instance.getApplicationAddressess());
            assertEquals(false, instance.getApplicationAddressess().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isAppInterestedFlag method, of class Application.
     */
    @Test
    public void testIsAppInterestedFlag()  {
        try {
            System.out.println("isAppInterestedFlag");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            PropertyInterface property = new Property(1, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.isAppInterestedFlag());
            instance.addInterestedProperty(property, modifiedBy);
            assertEquals(true, instance.isAppInterestedFlag());
            instance.endInterestInProperty(property, modifiedBy2);
            assertEquals(false, instance.isAppInterestedFlag());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getHousehold method, of class Application.
     */
    @Test
    public void testGetHousehold()  {
        try {
            System.out.println("getHousehold");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            Person person2 = new Person(2, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty2 = new InvolvedParty(2, 1, person2, true, false, new Date(), element, "DEDWARDS", new Date());
            
            Person person3 = new Person(3, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty3 = new InvolvedParty(3, 1, person3, false, false, new Date(), element, "DEDWARDS", new Date());
            
            Person person4 = new Person(4, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedPartyInterface invParty4 = new InvolvedParty(4, 1, person4, false, false, new Date(), element, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getHousehold().size());
            List<InvolvedPartyInterface> expResult = new ArrayList();
            List<InvolvedPartyInterface> test = new ArrayList();
            instance.addInvolvedParty(invParty2, modifiedBy);
            expResult.add(invParty);
            expResult.add(invParty2);
            assertEquals(2, instance.getHousehold().size());
            assertEquals(expResult, instance.getHousehold());
            instance.addInvolvedParty(invParty3, modifiedBy2);
            expResult.add(invParty3);
            assertEquals(3, instance.getHousehold().size());
            assertEquals(expResult, instance.getHousehold());
            instance.addInvolvedParty(invParty4, modifiedBy3);
            expResult.add(invParty4);
            assertEquals(4, instance.getHousehold().size());
            assertEquals(expResult, instance.getHousehold());
            assertEquals(false, instance.getHousehold().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropertiesInterestedIn method, of class Application.
     */
    @Test
    public void testGetPropertiesInterestedIn()  {
        try {
            System.out.println("getPropertiesInterestedIn");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            PropertyInterface property = new Property(1, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            PropertyInterface property2 = new Property(2, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            PropertyInterface property3 = new Property(3, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            PropertyInterface property4 = new Property(4, address, date2.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getPropertiesInterestedIn().size());
            List<PropertyInterface> expResult = new ArrayList();
            List<PropertyInterface> test = new ArrayList();
            instance.addInterestedProperty(property, modifiedBy);
            expResult.add(property);
            assertEquals(1, instance.getPropertiesInterestedIn().size());
            assertEquals(expResult, instance.getPropertiesInterestedIn());
            instance.addInterestedProperty(property2, modifiedBy2);
            expResult.add(property2);
            assertEquals(2, instance.getPropertiesInterestedIn().size());
            assertEquals(expResult, instance.getPropertiesInterestedIn());
            instance.addInterestedProperty(property3, modifiedBy3);
            expResult.add(property3);
            assertEquals(3, instance.getPropertiesInterestedIn().size());
            assertEquals(expResult, instance.getPropertiesInterestedIn());
            instance.addInterestedProperty(property4, modifiedBy4);
            expResult.add(property4);
            assertEquals(4, instance.getPropertiesInterestedIn().size());
            assertEquals(expResult, instance.getPropertiesInterestedIn());
            instance.endInterestInProperty(property2, modifiedBy5);
            expResult.remove(property2);
            assertEquals(3, instance.getPropertiesInterestedIn().size());
            assertEquals(expResult, instance.getPropertiesInterestedIn());
            assertEquals(false, instance.getPropertiesInterestedIn().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTenancyRef method, of class Application.
     */
    @Test
    public void testGetTenancyRef()  {
        try {
            System.out.println("getTenancyRef");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getTenancyRef());
            instance.setTenancy(4, modifiedBy);
            assertEquals(true, instance.hasTenancyRef());
            assertEquals(true, instance.getTenancyRef() == 4);
            assertEquals(false, instance.getTenancyRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasTenancyRef method, of class Application.
     */
    @Test
    public void testHasTenancyRef()  {
        try {
            System.out.println("hasTenancyRef");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.hasTenancyRef());
            instance.setTenancy(4, modifiedBy);
            assertEquals(true, instance.hasTenancyRef());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Application.
     */
    @Test
    public void testIsCurrent()  {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getAppEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(null, instance.getAppEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getAppEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Application.
     */
    @Test
    public void testHasNote()  {
        try {
            System.out.println("hasNote");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.hasNote(note5.getReference()));
            instance.createNote(note5, modifiedBy);
            assertEquals(true, instance.hasNote(note5.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Application.
     */
    @Test
    public void testGetNote()  {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, note5.equals(instance.getNote(note5.getReference())));
            assertEquals(null, instance.getNote(note5.getReference()));
            instance.createNote(note5, modifiedBy);
            assertEquals(true, note5.equals(instance.getNote(note5.getReference())));
            assertEquals(note5, instance.getNote(note5.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Application.
     */
    @Test
    public void testGetNotes()  {
        try {
            System.out.println("getNotes");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST NOTE6", "DEDWARDS", new Date());
            Note note7 = new NoteImpl(7, "TEST NOTE7", "DEDWARDS", new Date());
            Note note8 = new NoteImpl(7, "TEST NOTE8", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getNotes().size());
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note5, modifiedBy);
            expResult.add(note5);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note6, modifiedBy2);
            expResult.add(note6);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note7, modifiedBy3);
            expResult.add(note7);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note8, modifiedBy4);
            expResult.add(note8);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note6.getReference(), modifiedBy5);
            expResult.remove(note6);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Application.
     */
    @Test
    public void testHasDocument_int()  {
        try {
            System.out.println("hasDocument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument(7));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Application.
     */
    @Test
    public void testHasDocument_String()  {
        try {
            System.out.println("hasDocument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.hasDocument("TEST.pdf"));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(false, instance.hasDocument("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocument method, of class Application.
     */
    @Test
    public void testGetDocument()  {
        try {
            System.out.println("getDocument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note4, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(document, instance.getDocument(document.getDocumentRef()));
            assertEquals(null, instance.getDocument(9));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocuments method, of class Application.
     */
    @Test
    public void testGetDocuments()  {
        try {
            System.out.println("getDocuments");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST NOTE6", "DEDWARDS", new Date());
            Note note7 = new NoteImpl(7, "TEST NOTE7", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note4, "DEDWARDS", new Date());
            Document document2 = new DocumentImpl(2, new File("TEST2.pdf"), note5, "DEDWARDS", new Date());
            Document document3 = new DocumentImpl(3, new File("TEST3.pdf"), note6, "DEDWARDS", new Date());
            Document document4 = new DocumentImpl(4, new File("TEST4.pdf"), note7, "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(0, instance.getDocuments().size());
            List<Document> expResult = new ArrayList();
            instance.createDocument(document, modifiedBy);
            expResult.add(document);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(expResult, instance.getDocuments());
            instance.createDocument(document2, modifiedBy2);
            expResult.add(document2);
            assertEquals(2, instance.getDocuments().size());
            assertEquals(expResult, instance.getDocuments());
            instance.createDocument(document3, modifiedBy3);
            expResult.add(document3);
            assertEquals(3, instance.getDocuments().size());
            assertEquals(expResult, instance.getDocuments());
            instance.createDocument(document4, modifiedBy4);
            expResult.add(document4);
            assertEquals(4, instance.getDocuments().size());
            assertEquals(expResult, instance.getDocuments());
            instance.deleteDocument(document.getDocumentRef(), modifiedBy5);
            expResult.remove(document);
            assertEquals(3, instance.getDocuments().size());
            assertEquals(expResult, instance.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Application.
     */
    @Test
    public void testHasBeenModified()  {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.hasBeenModified());
            instance.createNote(note5, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Application.
     */
    @Test
    public void testGetLastModifiedBy()  {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createNote(note5, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Application.
     */
    @Test
    public void testGetLastModifiedDate()  {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date2.getTime());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createNote(note5, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Application.
     */
    @Test
    public void testGetModifiedBy()  {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createNote(note5, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Application.
     */
    @Test
    public void testGetLastModification()  {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(null, instance.getLastModification());
            instance.createNote(note5, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note6, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Application.
     */
    @Test
    public void testGetCreatedBy()  {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", new Date());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Application.
     */
    @Test
    public void testGetCreatedDate()  {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            AddressUsage addressUsage2 = new AddressUsage(2, address, date2.getTime(), note4, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            Application instance = new Application(1, "Dwayne Leroy Edwards", date2.getTime(), addressUsage2, "DEDWARDS", date2.getTime());
            instance.addInvolvedParty(invParty, null);
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date2.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
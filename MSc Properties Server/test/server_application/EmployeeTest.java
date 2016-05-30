/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ContractInterface;
import interfaces.Element;
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
public class EmployeeTest {
    
    public EmployeeTest() {
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
     * Test of modifiedBy method, of class Employee.
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updatePassword method, of class Employee.
     */
    @Test
    public void testUpdatePassword() {
        try {
            System.out.println("updatePassword");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            UserImpl user = (UserImpl) instance.getUser();
            assertEquals("TestPassword", user.getPassword());
            assertEquals(0, instance.getModifiedBy().size());
            instance.updatePassword("NewPassword", modifiedBy);
            assertEquals("NewPassword", user.getPassword());
            assertEquals(false, user.getPassword().equals("TestPassword"));
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updatePermissions method, of class Employee.
     */
    @Test
    public void testUpdatePermissions() {
        try {
            System.out.println("updatePermissions");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Boolean result = false;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            UserImpl user = (UserImpl) instance.getUser();
            assertEquals(null, user.getRead());
            assertEquals(null, user.getWrite());
            assertEquals(null, user.getUpdate());
            assertEquals(null, user.getDelete());
            assertEquals(null, user.getEmployeeRead());
            assertEquals(null, user.getEmployeeWrite());
            assertEquals(null, user.getEmployeeUpdate());
            assertEquals(null, user.getEmployeeDelete());
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.updatePermissions(true, true, true, true, false, false, true, true, modifiedBy);
            
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, user.getRead());
            assertEquals(true, user.getWrite());
            assertEquals(true, user.getUpdate());
            assertEquals(true, user.getUpdate());
            assertEquals(false, user.getEmployeeRead());
            assertEquals(false, user.getEmployeeWrite());
            assertEquals(true, user.getEmployeeUpdate());
            assertEquals(true, user.getEmployeeDelete());
            
            assertEquals(false, result.equals(user.getRead()));
            assertEquals(false, result.equals(user.getWrite()));
            assertEquals(false, result.equals(user.getUpdate()));
            assertEquals(false, result.equals(user.getDelete()));
            assertEquals(true, result.equals(user.getEmployeeRead()));
            assertEquals(true, result.equals(user.getEmployeeWrite()));
            assertEquals(false, result.equals(user.getEmployeeUpdate()));
            assertEquals(false, result.equals(user.getEmployeeDelete()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createContract method, of class Employee.
     */
    @Test
    public void testCreateContract() {
        try {
            System.out.println("createContract");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(0, instance.getContracts().size());
            assertEquals(null, instance.getContract());
            instance.createContract(contract, modifiedBy);
            assertEquals(1, instance.getContracts().size());
            assertEquals(contract, instance.getContract());
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteContract method, of class Employee.
     */
    @Test
    public void testDeleteContract() {
        try {
            System.out.println("deleteContract");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(0, instance.getContracts().size());
            assertEquals(null, instance.getContract());
            instance.createContract(contract, modifiedBy);
            assertEquals(1, instance.getContracts().size());
            assertEquals(contract, instance.getContract());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteContract(contract.getAgreementRef(), modifiedBy2);
            assertEquals(0, instance.getContracts().size());
            assertEquals(null, instance.getContract());
            assertEquals(2, instance.getModifiedBy().size());
            
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Employee.
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class Employee.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            instance.deleteNote(5, modifiedBy2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteNote(note.getReference(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getNotes().size());
            assertEquals(false, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeRef method, of class Employee.
     */
    @Test
    public void testGetEmployeeRef() {
        try {
            System.out.println("getEmployeeRef");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(1, instance.getEmployeeRef());
            assertEquals(false, instance.getEmployeeRef() == 8);
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPerson method, of class Employee.
     */
    @Test
    public void testGetPerson() {
        try {
            System.out.println("getPerson");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Person person2 = new Person(2, element, "Test", "Test", "Test", date.getTime(), "Test", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(person, instance.getPerson());
            assertEquals(false, instance.getPerson().equals(person2));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Employee.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Contract contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(false, instance.isCurrent());
            instance.createContract(contract, modifiedBy);
            assertEquals(true, instance.isCurrent());
            contract.setActualEndDate(endDate.getTime(), modifiedBy2);
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Employee.
     */
    @Test
    public void testHasBeenModified() {
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.createNote(note4, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPersonRef method, of class Employee.
     */
    @Test
    public void testGetPersonRef() {
        try {
            System.out.println("getPersonRef");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(1, instance.getPersonRef());
            assertEquals(false, instance.getPersonRef() == 5);
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getUser method, of class Employee.
     */
    @Test
    public void testGetUser() {
        try {
            System.out.println("getUser");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(1, instance.getUser().getEmployeeRef());
            assertEquals(false, instance.getUser().getEmployeeRef() == 5);
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOfficeCode method, of class Employee.
     */
    @Test
    public void testGetOfficeCode() {
        try {
            System.out.println("getOfficeCode");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getOfficeCode());
            instance.createContract(contract, modifiedBy);
            assertEquals("EDM", instance.getOfficeCode());
            assertEquals(false, instance.getOfficeCode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Employee.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createContract(contract, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Employee.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date2.getTime());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createContract(contract, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Employee.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date2.getTime());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createContract(contract, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Employee.
     */
    @Test
    public void testGetLastModification() {
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.createContract(contract, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note4, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Employee.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(false, instance.hasNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Employee.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        try {
            System.out.println("hasNote");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(false, note.equals(instance.getNote(note.getReference())));
            assertEquals(null, instance.getNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(note, instance.getNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Employee.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note4, modifiedBy);
            expResult.add(note4);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note5, modifiedBy2);
            expResult.add(note5);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note6, modifiedBy3);
            expResult.add(note6);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note7, modifiedBy4);
            expResult.add(note7);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note6.getReference(), modifiedBy5);
            expResult.remove(note6);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Employee.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("ADMIN"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Employee.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar createdDate = Calendar.getInstance();
            createdDate.set(2015, 1, 11);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", createdDate.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(createdDate.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasContract method, of class Employee.
     */
    @Test
    public void testHasContract() {
        try {
            System.out.println("hasContract");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasContract(contract.getAgreementRef()));
            instance.createContract(contract, modifiedBy);
            assertEquals(true, instance.hasContract(contract.getAgreementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContract method, of class Employee.
     */
    @Test
    public void testGetContract_int() {
        try {
            System.out.println("getContract - int");
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
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getContract(contract.getAgreementRef()));
            instance.createContract(contract, modifiedBy);
            assertEquals(contract, instance.getContract(contract.getAgreementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContract method, of class Employee.
     */
    @Test
    public void testGetContract_0args() {
        try {
            System.out.println("getContract - no argument");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 7, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            ContractInterface contract2 = new Contract(2, 1, date3.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getContract());
            instance.createContract(contract, modifiedBy);
            assertEquals(contract, instance.getContract());
            assertEquals(false, instance.getContract().equals(contract2));
            instance.createContract(contract2, modifiedBy2);
            assertEquals(contract2, instance.getContract());
            assertEquals(false, instance.getContract().equals(contract));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContracts method, of class Employee.
     */
    @Test
    public void testGetContracts() throws Exception {
        try {
            System.out.println("getContracts");
            Calendar date = Calendar.getInstance();
            date.set(1989, 3, 9);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 7, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address, date2.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            Employee instance = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            ContractInterface contract2 = new Contract(2, 1, date3.getTime(), 12, instance, jobRole, "EDM", "DEDWARDS", new Date());
            
            List<ContractInterface> expResult = new ArrayList();
            List<ContractInterface> test = new ArrayList();
            assertEquals(expResult, instance.getContracts());
            instance.createContract(contract, modifiedBy);
            expResult.add(contract);
            assertEquals(expResult, instance.getContracts());
            assertEquals(false, instance.getContracts().equals(test));
            instance.createContract(contract2, modifiedBy2);
            expResult.add(contract2);
            assertEquals(expResult, instance.getContracts());
            assertEquals(false, instance.getContracts().equals(test));
            instance.deleteContract(contract2.getAgreementRef(), modifiedBy3);
            expResult.remove(contract2);
            assertEquals(expResult, instance.getContracts());
            assertEquals(false, instance.getContracts().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

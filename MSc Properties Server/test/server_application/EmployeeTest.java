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
import interfaces.PersonInterface;
import java.rmi.RemoteException;
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
        System.out.println("updatePassword");
        String password = "";
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.updatePassword(password, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePermissions method, of class Employee.
     */
    @Test
    public void testUpdatePermissions() {
        System.out.println("updatePermissions");
        boolean read = false;
        boolean write = false;
        boolean update = false;
        boolean employeeRead = false;
        boolean employeeWrite = false;
        boolean employeeUpdate = false;
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.updatePermissions(read, write, update, employeeRead, employeeWrite, employeeUpdate, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createContract method, of class Employee.
     */
    @Test
    public void testCreateContract() throws Exception {
        System.out.println("createContract");
        ContractInterface contract = null;
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.createContract(contract, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContract method, of class Employee.
     */
    @Test
    public void testDeleteContract() throws Exception {
        System.out.println("deleteContract");
        int ref = 0;
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.deleteContract(ref, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNote method, of class Employee.
     */
    @Test
    public void testCreateNote() {
        System.out.println("createNote");
        Note note = null;
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.createNote(note, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNote method, of class Employee.
     */
    @Test
    public void testDeleteNote() throws Exception {
        System.out.println("deleteNote");
        int ref = 0;
        ModifiedByInterface modifiedBy = null;
        Employee instance = null;
        instance.deleteNote(ref, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeRef method, of class Employee.
     */
    @Test
    public void testGetEmployeeRef() throws Exception {
        System.out.println("getEmployeeRef");
        Employee instance = null;
        int expResult = 0;
        int result = instance.getEmployeeRef();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class Employee.
     */
    @Test
    public void testGetPerson() throws Exception {
        System.out.println("getPerson");
        Employee instance = null;
        PersonInterface expResult = null;
        PersonInterface result = instance.getPerson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCurrent method, of class Employee.
     */
    @Test
    public void testIsCurrent() throws Exception {
        System.out.println("isCurrent");
        Employee instance = null;
        boolean expResult = false;
        boolean result = instance.isCurrent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasBeenModified method, of class Employee.
     */
    @Test
    public void testHasBeenModified() throws Exception {
        System.out.println("hasBeenModified");
        Employee instance = null;
        boolean expResult = false;
        boolean result = instance.hasBeenModified();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonRef method, of class Employee.
     */
    @Test
    public void testGetPersonRef() throws Exception {
        System.out.println("getPersonRef");
        Employee instance = null;
        int expResult = 0;
        int result = instance.getPersonRef();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Employee.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        Employee instance = null;
        UserImpl expResult = null;
        UserImpl result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeCode method, of class Employee.
     */
    @Test
    public void testGetOfficeCode() throws Exception {
        System.out.println("getOfficeCode");
        Employee instance = null;
        String expResult = "";
        String result = instance.getOfficeCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModifiedBy method, of class Employee.
     */
    @Test
    public void testGetLastModifiedBy() throws Exception {
        System.out.println("getLastModifiedBy");
        Employee instance = null;
        String expResult = "";
        String result = instance.getLastModifiedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModifiedDate method, of class Employee.
     */
    @Test
    public void testGetLastModifiedDate() throws Exception {
        System.out.println("getLastModifiedDate");
        Employee instance = null;
        Date expResult = null;
        Date result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifiedBy method, of class Employee.
     */
    @Test
    public void testGetModifiedBy() throws Exception {
        System.out.println("getModifiedBy");
        Employee instance = null;
        List<ModifiedByInterface> expResult = null;
        List<ModifiedByInterface> result = instance.getModifiedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModification method, of class Employee.
     */
    @Test
    public void testGetLastModification() throws Exception {
        System.out.println("getLastModification");
        Employee instance = null;
        ModifiedByInterface expResult = null;
        ModifiedByInterface result = instance.getLastModification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasNote method, of class Employee.
     */
    @Test
    public void testHasNote() throws Exception {
        System.out.println("hasNote");
        int ref = 0;
        Employee instance = null;
        boolean expResult = false;
        boolean result = instance.hasNote(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class Employee.
     */
    @Test
    public void testGetNote() throws Exception {
        System.out.println("getNote");
        int ref = 0;
        Employee instance = null;
        Note expResult = null;
        Note result = instance.getNote(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotes method, of class Employee.
     */
    @Test
    public void testGetNotes() throws Exception {
        System.out.println("getNotes");
        Employee instance = null;
        List<Note> expResult = null;
        List<Note> result = instance.getNotes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedBy method, of class Employee.
     */
    @Test
    public void testGetCreatedBy() throws Exception {
        System.out.println("getCreatedBy");
        Employee instance = null;
        String expResult = "";
        String result = instance.getCreatedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedDate method, of class Employee.
     */
    @Test
    public void testGetCreatedDate() throws Exception {
        System.out.println("getCreatedDate");
        Employee instance = null;
        Date expResult = null;
        Date result = instance.getCreatedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasContract method, of class Employee.
     */
    @Test
    public void testHasContract() throws Exception {
        System.out.println("hasContract");
        int ref = 0;
        Employee instance = null;
        boolean expResult = false;
        boolean result = instance.hasContract(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContract method, of class Employee.
     */
    @Test
    public void testGetContract_int() throws Exception {
        System.out.println("getContract");
        int ref = 0;
        Employee instance = null;
        ContractInterface expResult = null;
        ContractInterface result = instance.getContract(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContract method, of class Employee.
     */
    @Test
    public void testGetContract_0args() throws Exception {
        System.out.println("getContract");
        Employee instance = null;
        ContractInterface expResult = null;
        ContractInterface result = instance.getContract();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContracts method, of class Employee.
     */
    @Test
    public void testGetContracts() throws Exception {
        System.out.println("getContracts");
        Employee instance = null;
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getContracts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

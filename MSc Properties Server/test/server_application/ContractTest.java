/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ContractInterface;
import interfaces.Element;
import interfaces.Note;
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
public class ContractTest {
    
    public ContractTest() {
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
     * Test of getEmployee method, of class Contract.
     */
    @Test
    public void testGetEmployee() {
        try {
            System.out.println("getEmployee");
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
            Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            Employee employee2 = new Employee(2, person, "DEDWARDS", "OtherPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface instance = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(employee, instance.getEmployee());
            assertEquals(false, instance.getEmployee().equals(employee2));
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Test of getEmployeeRef method, of class Contract.
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
            Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface instance = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(1, instance.getEmployeeRef());
            assertEquals(false, instance.getEmployeeRef() == 5);
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobRole method, of class Contract.
     */
    @Test
    public void testGetJobRole() {
        try {
            System.out.println("getJobRole");
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
            Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            JobRole jobRole2 = new JobRole("TEST", "Test", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface instance = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals(jobRole, instance.getJobRole());
            assertEquals(false, instance.getJobRole().equals(jobRole2));
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobRoleCode method, of class Contract.
     */
    @Test
    public void testGetJobRoleCode() {
        try {
            System.out.println("getJobRoleCode");
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
            Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "ADMIN", new Date());
            JobRole jobRole = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ContractInterface instance = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
            
            assertEquals("MNGR", instance.getJobRoleCode());
            assertEquals(false, instance.getJobRoleCode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
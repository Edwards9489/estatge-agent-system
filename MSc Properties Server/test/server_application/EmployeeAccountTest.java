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
public class EmployeeAccountTest {
    
    public EmployeeAccountTest() {
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
     * Test of setSalary method, of class EmployeeAccount.
     */
    @Test
    public void testSetSalary() {
        try {
            System.out.println("setSalary");
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
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "ADMIN", new Date());
            EmployeeAccount instance = new EmployeeAccount(1, contract, "ADMIN", new Date());
            
            assertEquals(true, instance.getSalary() == 27000.00);
            instance.setSalary(30000.00);
            assertEquals(false, instance.getSalary() == 27000.00);
            assertEquals(true, instance.getSalary() == 30000.00);
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContract method, of class EmployeeAccount.
     */
    @Test
    public void testGetContract() {
        try {
            System.out.println("setSalary");
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
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "ADMIN", new Date());
            ContractInterface contract2 = new Contract(2, 2, date2.getTime(), 12, employee, jobRole, "EDM", "ADMIN", new Date());
            EmployeeAccount instance = new EmployeeAccount(1, contract, "ADMIN", new Date());
            
            assertEquals(contract, instance.getContract());
            assertEquals(false, instance.getContract().equals(contract2));
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContractRef method, of class EmployeeAccount.
     */
    @Test
    public void testGetContractRef() {
        try {
            System.out.println("getContractRef");
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
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "ADMIN", new Date());
            EmployeeAccount instance = new EmployeeAccount(1, contract, "ADMIN", new Date());
            
            assertEquals(1, instance.getContractRef());
            assertEquals(false, instance.getContractRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getSalary method, of class EmployeeAccount.
     */
    @Test
    public void testGetSalary() {
        try {
            System.out.println("getSalary");
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
            ContractInterface contract = new Contract(1, 1, date2.getTime(), 12, employee, jobRole, "EDM", "ADMIN", new Date());
            EmployeeAccount instance = new EmployeeAccount(1, contract, "ADMIN", new Date());
            
            assertEquals(true, instance.getSalary() == 27000.00);
            assertEquals(false, instance.getSalary() == 30000.00);
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
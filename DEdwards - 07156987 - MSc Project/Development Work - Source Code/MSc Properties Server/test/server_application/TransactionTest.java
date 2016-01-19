/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

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
public class TransactionTest {
    
    public TransactionTest() {
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
     * Test of getTransactionRef method, of class Transaction.
     */
    @Test
    public void testGetTransactionRef() {
        try {
            System.out.println("getTransactionRef");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getTransactionRef());
            assertEquals(false, instance.getTransactionRef() == 8);
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccountRef method, of class Transaction.
     */
    @Test
    public void testGetAccountRef() {
        try {
            System.out.println("getAccountRef");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getAccountRef());
            assertEquals(false, instance.getAccountRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getFromRef method, of class Transaction.
     */
    @Test
    public void testGetFromRef() {
        try {
            System.out.println("getFromRef");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getFromRef());
            assertEquals(false, instance.getFromRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getToRef method, of class Transaction.
     */
    @Test
    public void testGetToRef() {
        try {
            System.out.println("getToRef");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(2, instance.getToRef());
            assertEquals(false, instance.getToRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAmount method, of class Transaction.
     */
    @Test
    public void testGetAmount() {
        try {
            System.out.println("getAmount");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getAmount() == 500.00);
            assertEquals(false, instance.getAmount() == 900.00);
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTransactionDate method, of class Transaction.
     */
    @Test
    public void testGetTransactionDate() {
        try {
            System.out.println("getTransactionDate");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(tranDate.getTime(), instance.getTransactionDate());
            assertEquals(false, instance.getTransactionDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Transaction.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(note, instance.getNote());
            assertEquals(false, instance.getNote().equals(note2));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class Transaction.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals(note.getNote(), instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Transaction.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            
            assertEquals("DEDWARDS", instance.getCreatedBy());
            assertEquals(false, instance.getCreatedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Transaction.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", tranDate.getTime());
            
            assertEquals(tranDate.getTime(), instance.getCreatedDate());
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isDebit method, of class Transaction.
     */
    @Test
    public void testIsDebit() {
        try {
            System.out.println("isDebit");
            Calendar tranDate = Calendar.getInstance();
            tranDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Transaction instance = new Transaction(1, 1, 1, 2, 500.00, true, tranDate.getTime(), note, "DEDWARDS", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isDebit());
            assertEquals(false, result.equals(instance.isDebit()));
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.TransactionInterface;
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
public class AccountTest {
    
    public AccountTest() {
        
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
     * Test of modifiedBy method, of class Account.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", new Date(), "DEDWARDS");
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class Account.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 5, 12);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Date endDate = date.getTime();
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED2", new Date(), "DEDWARDS");
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date2.getTime(), "DEDWARDS", new Date());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateAccount method, of class Account.
     */
    @Test
    public void testUpdateAccount() throws Exception {
        try {
            System.out.println("updateAccount");
            Calendar date = Calendar.getInstance();
            date.set(2015, 5, 12);
            Date startDate = date.getTime();
            String accName = "NAMECHANGED";
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED3", new Date(), "DEDWARDS");
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date2.getTime(), "DEDWARDS", new Date());
            instance.updateAccount(startDate, accName, modifiedBy);
            assertEquals(startDate, instance.getStartDate());
            assertEquals(accName, instance.getAccName());
            assertEquals(startDate, instance.getStartDate());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createTransaction method, of class Account.
     */
    @Test
    public void testCreateTransaction() throws Exception {
        System.out.println("createTransaction");
        TransactionInterface transaction = null;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.createTransaction(transaction, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTransaction method, of class Account.
     */
    @Test
    public void testDeleteTransaction() throws Exception {
        System.out.println("deleteTransaction");
        int ref = 0;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.deleteTransaction(ref, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNote method, of class Account.
     */
    @Test
    public void testCreateNote() throws Exception {
        System.out.println("createNote");
        Note note = null;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.createNote(note, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNote method, of class Account.
     */
    @Test
    public void testDeleteNote() throws Exception {
        System.out.println("deleteNote");
        int ref = 0;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.deleteNote(ref, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDocument method, of class Account.
     */
    @Test
    public void testCreateDocument() throws Exception {
        System.out.println("createDocument");
        Document document = null;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.createDocument(document, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDocument method, of class Account.
     */
    @Test
    public void testDeleteDocument() throws Exception {
        System.out.println("deleteDocument");
        int ref = 0;
        ModifiedByInterface modifiedBy = null;
        Account instance = null;
        instance.deleteDocument(ref, modifiedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccRef method, of class Account.
     */
    @Test
    public void testGetAccRef() throws Exception {
        System.out.println("getAccRef");
        Account instance = null;
        int expResult = 0;
        int result = instance.getAccRef();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccName method, of class Account.
     */
    @Test
    public void testGetAccName() throws Exception {
        System.out.println("getAccName");
        Account instance = null;
        String expResult = "";
        String result = instance.getAccName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartDate method, of class Account.
     */
    @Test
    public void testGetStartDate() throws Exception {
        System.out.println("getStartDate");
        Account instance = null;
        Date expResult = null;
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDate method, of class Account.
     */
    @Test
    public void testGetEndDate() throws Exception {
        System.out.println("getEndDate");
        Account instance = null;
        Date expResult = null;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalance method, of class Account.
     */
    @Test
    public void testGetBalance() throws Exception {
        System.out.println("getBalance");
        Account instance = null;
        double expResult = 0.0;
        double result = instance.getBalance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeCode method, of class Account.
     */
    @Test
    public void testGetOfficeCode() throws Exception {
        System.out.println("getOfficeCode");
        Account instance = null;
        String expResult = "";
        String result = instance.getOfficeCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNegativeInd method, of class Account.
     */
    @Test
    public void testIsNegativeInd() throws Exception {
        System.out.println("isNegativeInd");
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.isNegativeInd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCurrent method, of class Account.
     */
    @Test
    public void testIsCurrent() throws Exception {
        System.out.println("isCurrent");
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.isCurrent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasBeenModified method, of class Account.
     */
    @Test
    public void testHasBeenModified() throws Exception {
        System.out.println("hasBeenModified");
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.hasBeenModified();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasTransaction method, of class Account.
     */
    @Test
    public void testHasTransaction() throws Exception {
        System.out.println("hasTransaction");
        int ref = 0;
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.hasTransaction(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasNote method, of class Account.
     */
    @Test
    public void testHasNote() throws Exception {
        System.out.println("hasNote");
        int ref = 0;
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.hasNote(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class Account.
     */
    @Test
    public void testGetNote() throws Exception {
        System.out.println("getNote");
        int ref = 0;
        Account instance = null;
        Note expResult = null;
        Note result = instance.getNote(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotes method, of class Account.
     */
    @Test
    public void testGetNotes() throws Exception {
        System.out.println("getNotes");
        Account instance = null;
        List<Note> expResult = null;
        List<Note> result = instance.getNotes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasDocument method, of class Account.
     */
    @Test
    public void testHasDocument_int() throws Exception {
        System.out.println("hasDocument");
        int ref = 0;
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.hasDocument(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasDocument method, of class Account.
     */
    @Test
    public void testHasDocument_String() throws Exception {
        System.out.println("hasDocument");
        String fileName = "";
        Account instance = null;
        boolean expResult = false;
        boolean result = instance.hasDocument(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDocument method, of class Account.
     */
    @Test
    public void testGetDocument() throws Exception {
        System.out.println("getDocument");
        int ref = 0;
        Account instance = null;
        Document expResult = null;
        Document result = instance.getDocument(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDocuments method, of class Account.
     */
    @Test
    public void testGetDocuments() throws Exception {
        System.out.println("getDocuments");
        Account instance = null;
        List<Document> expResult = null;
        List<Document> result = instance.getDocuments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransaction method, of class Account.
     */
    @Test
    public void testGetTransaction() throws Exception {
        System.out.println("getTransaction");
        int ref = 0;
        Account instance = null;
        TransactionInterface expResult = null;
        TransactionInterface result = instance.getTransaction(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModifiedBy method, of class Account.
     */
    @Test
    public void testGetLastModifiedBy() throws Exception {
        System.out.println("getLastModifiedBy");
        Account instance = null;
        String expResult = "";
        String result = instance.getLastModifiedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModifiedDate method, of class Account.
     */
    @Test
    public void testGetLastModifiedDate() throws Exception {
        System.out.println("getLastModifiedDate");
        Account instance = null;
        Date expResult = null;
        Date result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifiedBy method, of class Account.
     */
    @Test
    public void testGetModifiedBy() throws Exception {
        System.out.println("getModifiedBy");
        Account instance = null;
        List<ModifiedByInterface> expResult = null;
        List<ModifiedByInterface> result = instance.getModifiedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModification method, of class Account.
     */
    @Test
    public void testGetLastModification() throws Exception {
        System.out.println("getLastModification");
        Account instance = null;
        ModifiedByInterface expResult = null;
        ModifiedByInterface result = instance.getLastModification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedBy method, of class Account.
     */
    @Test
    public void testGetCreatedBy() throws Exception {
        System.out.println("getCreatedBy");
        Account instance = null;
        String expResult = "";
        String result = instance.getCreatedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedDate method, of class Account.
     */
    @Test
    public void testGetCreatedDate() throws Exception {
        System.out.println("getCreatedDate");
        Account instance = null;
        Date expResult = null;
        Date result = instance.getCreatedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransactions method, of class Account.
     */
    @Test
    public void testGetTransactions() throws Exception {
        System.out.println("getTransactions");
        Account instance = null;
        List<TransactionInterface> expResult = null;
        List<TransactionInterface> result = instance.getTransactions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Account.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Account instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

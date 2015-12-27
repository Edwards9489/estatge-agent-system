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
    public void testUpdateAccount() {
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
    public void testCreateTransaction() {
        try {
            System.out.println("createTransaction");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED3", new Date(), "DEDWARDS");
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            assertEquals(0.0 == instance.getBalance(), true);
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(-500.00 == instance.getBalance(), true);
            assertEquals(instance.isNegativeInd(), true);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(1, instance.getTransactions().size());
            assertEquals(instance.hasTransaction(transaction.getTransactionRef()), true);
            assertEquals(transaction, instance.getTransaction(transaction.getTransactionRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteTransaction method, of class Account.
     */
    @Test
    public void testDeleteTransaction() {
        try {
            System.out.println("deleteTransaction");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED4", new Date(), "DEDWARDS");
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED5", new Date(), "DEDWARDS");
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            assertEquals(0.0 == instance.getBalance(), true);
            instance.createTransaction(transaction, modifiedBy);
            instance.deleteTransaction(transaction.getTransactionRef(), modifiedBy2);
            assertEquals(0.0 == instance.getBalance(), true);
            assertEquals(instance.isNegativeInd(), false);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getTransactions().size());
            assertEquals(instance.hasTransaction(transaction.getTransactionRef()), false);
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Account.
     */
    @Test
    public void testCreateNote() {
        try {
            System.out.println("createNote");
            Note note = null;
            ModifiedByInterface modifiedBy = null;
            Account instance = null;
            instance.createNote(note, modifiedBy);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
            
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class Account.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
            int ref = 0;
            ModifiedByInterface modifiedBy = null;
            Account instance = null;
            instance.deleteNote(ref, modifiedBy);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createDocument method, of class Account.
     */
    @Test
    public void testCreateDocument() {
        try {
            System.out.println("createDocument");
            Document document = null;
            ModifiedByInterface modifiedBy = null;
            Account instance = null;
            instance.createDocument(document, modifiedBy);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteDocument method, of class Account.
     */
    @Test
    public void testDeleteDocument() {
        try {
            System.out.println("deleteDocument");
            int ref = 0;
            ModifiedByInterface modifiedBy = null;
            Account instance = null;
            instance.deleteDocument(ref, modifiedBy);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccRef method, of class Account.
     */
    @Test
    public void testGetAccRef() {
        try {
            System.out.println("getAccRef");
            Account instance = null;
            int expResult = 0;
            int result = instance.getAccRef();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccName method, of class Account.
     */
    @Test
    public void testGetAccName() {
        try {
            System.out.println("getAccName");
            Account instance = null;
            String expResult = "";
            String result = instance.getAccName();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class Account.
     */
    @Test
    public void testGetStartDate() {
        try {
            System.out.println("getStartDate");
            Account instance = null;
            Date expResult = null;
            Date result = instance.getStartDate();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class Account.
     */
    @Test
    public void testGetEndDate() {
        try {
            System.out.println("getEndDate");
            Account instance = null;
            Date expResult = null;
            Date result = instance.getEndDate();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBalance method, of class Account.
     */
    @Test
    public void testGetBalance() {
        try {
            System.out.println("getBalance");
            Account instance = null;
            double expResult = 0.0;
            double result = instance.getBalance();
            assertEquals(expResult, result, 0.0);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOfficeCode method, of class Account.
     */
    @Test
    public void testGetOfficeCode() {
        try {
            System.out.println("getOfficeCode");
            Account instance = null;
            String expResult = "";
            String result = instance.getOfficeCode();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isNegativeInd method, of class Account.
     */
    @Test
    public void testIsNegativeInd() {
        try {
            System.out.println("isNegativeInd");
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.isNegativeInd();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Account.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.isCurrent();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Account.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.hasBeenModified();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasTransaction method, of class Account.
     */
    @Test
    public void testHasTransaction() {
        try {
            System.out.println("hasTransaction");
            int ref = 0;
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.hasTransaction(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Account.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
            int ref = 0;
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.hasNote(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Account.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            int ref = 0;
            Account instance = null;
            Note expResult = null;
            Note result = instance.getNote(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Account.
     */
    @Test
    public void testGetNotes() {
        try {
            System.out.println("getNotes");
            Account instance = null;
            List<Note> expResult = null;
            List<Note> result = instance.getNotes();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Account.
     */
    @Test
    public void testHasDocument_int() {
        try {
            System.out.println("hasDocument");
            int ref = 0;
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.hasDocument(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Account.
     */
    @Test
    public void testHasDocument_String() {
        try {
            System.out.println("hasDocument");
            String fileName = "";
            Account instance = null;
            boolean expResult = false;
            boolean result = instance.hasDocument(fileName);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocument method, of class Account.
     */
    @Test
    public void testGetDocument() {
        try {
            System.out.println("getDocument");
            int ref = 0;
            Account instance = null;
            Document expResult = null;
            Document result = instance.getDocument(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocuments method, of class Account.
     */
    @Test
    public void testGetDocuments() {
        try {
            System.out.println("getDocuments");
            Account instance = null;
            List<Document> expResult = null;
            List<Document> result = instance.getDocuments();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTransaction method, of class Account.
     */
    @Test
    public void testGetTransaction() {
        try {
            System.out.println("getTransaction");
            int ref = 0;
            Account instance = null;
            TransactionInterface expResult = null;
            TransactionInterface result = instance.getTransaction(ref);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Account.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Account instance = null;
            String expResult = "";
            String result = instance.getLastModifiedBy();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Account.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Account instance = null;
            Date expResult = null;
            Date result = instance.getLastModifiedDate();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Account.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Account instance = null;
            List<ModifiedByInterface> expResult = null;
            List<ModifiedByInterface> result = instance.getModifiedBy();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Account.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Account instance = null;
            ModifiedByInterface expResult = null;
            ModifiedByInterface result = instance.getLastModification();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Account.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Account instance = null;
            String expResult = "";
            String result = instance.getCreatedBy();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Account.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Account instance = null;
            Date expResult = null;
            Date result = instance.getCreatedDate();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTransactions method, of class Account.
     */
    @Test
    public void testGetTransactions() {
        try {
            System.out.println("getTransactions");
            Account instance = null;
            List<TransactionInterface> expResult = null;
            List<TransactionInterface> result = instance.getTransactions();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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

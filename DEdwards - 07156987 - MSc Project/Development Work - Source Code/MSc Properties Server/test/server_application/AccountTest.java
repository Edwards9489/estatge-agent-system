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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
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
     * Test of setEndDate method, of class Account.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            assertEquals(0, instance.getModifiedBy().size());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getEndDate());
            assertEquals(1, instance.getModifiedBy().size());
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date2.getTime(), "DEDWARDS", new Date());
            
            instance.updateAccount(startDate, accName, modifiedBy);
            assertEquals(startDate, instance.getStartDate());
            assertEquals(accName, instance.getAccName());
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(true, 0.0 == instance.getBalance());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(true, -500.00 == instance.getBalance());
            assertEquals(true, instance.isNegativeInd());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(1, instance.getTransactions().size());
            assertEquals(true, instance.hasTransaction(transaction.getTransactionRef()));
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
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(true, 0.0 == instance.getBalance());
            instance.createTransaction(transaction, modifiedBy);
            instance.deleteTransaction(transaction.getTransactionRef(), modifiedBy2);
            assertEquals(true, 0.0 == instance.getBalance());
            assertEquals(false, instance.isNegativeInd());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getTransactions().size());
            assertEquals(false, instance.hasTransaction(transaction.getTransactionRef()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note.getReference()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getDocuments().size());
            instance.createDocument(document, modifiedBy);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(true, instance.hasDocument("TEST.pdf"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(1, instance.getAccRef());
            assertEquals(false, instance.getAccRef() == 2);
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals("Mr Dwayne Leroy Edwards", instance.getAccName());
            assertEquals(false, instance.getAccName().equals("WRONG"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(false, instance.getStartDate().equals(new Date()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(true, 0.0 == instance.getBalance());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(true, -500.00 == instance.getBalance());
            assertEquals(false, 500 == instance.getBalance());
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(true, instance.getOfficeCode().equals("TEST"));
            assertEquals(false, instance.getOfficeCode().equals("EDM"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction2 = new Transaction(2, 1, 3, 8, 700.00, false, new Date(), note2, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.isNegativeInd());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(true, instance.isNegativeInd());
            instance.createTransaction(transaction2, modifiedBy);
            assertEquals(false, instance.isNegativeInd());
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Calendar testDate = Calendar.getInstance();
            testDate.set(2014, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(testDate.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(false, instance.isCurrent());
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasTransaction(transaction.getTransactionRef()));
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(true, instance.hasTransaction(transaction.getTransactionRef()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(true, instance.hasNote(note.getReference()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, note.equals(instance.getNote(note.getReference())));
            assertEquals(null, instance.getNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(note, instance.getNote(note.getReference()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note, modifiedBy);
            expResult.add(note);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note2, modifiedBy2);
            expResult.add(note2);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note3, modifiedBy3);
            expResult.add(note3);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note4, modifiedBy4);
            expResult.add(note4);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note.getReference(), modifiedBy5);
            expResult.remove(note);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
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
            System.out.println("hasDocument - int");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument(7));
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
            System.out.println("hasDocument - String");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument("TEST.pdf"));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(false, instance.hasDocument("TEST"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(document, instance.getDocument(document.getDocumentRef()));
            assertEquals(null, instance.getDocument(9));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            Document document2 = new DocumentImpl(2, new File("TEST2.pdf"), note2, "DEDWARDS", new Date());
            Document document3 = new DocumentImpl(3, new File("TEST3.pdf"), note3, "DEDWARDS", new Date());
            Document document4 = new DocumentImpl(4, new File("TEST4.pdf"), note4, "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getTransaction(transaction.getTransactionRef()));
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(transaction, instance.getTransaction(transaction.getTransactionRef()));
            assertEquals(null, instance.getTransaction(12));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createTransaction(transaction, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, true, new Date(), note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.createTransaction(transaction, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note2, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
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
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            TransactionInterface transaction = new Transaction(1, 1, 3, 8, 500.00, false, new Date(), note, "DEDWARDS", new Date());
            TransactionInterface transaction2 = new Transaction(2, 1, 3, 8, 700.00, true, new Date(), note, "DEDWARDS", new Date());
            TransactionInterface transaction3 = new Transaction(3, 1, 3, 8, 200.00, false, new Date(), note, "DEDWARDS", new Date());
            TransactionInterface transaction4 = new Transaction(4, 1, 3, 8, 1000.00, true, new Date(), note, "DEDWARDS", new Date());
            Account instance = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getTransactions().size());
            List<TransactionInterface> expResult = new ArrayList();
            instance.createTransaction(transaction, modifiedBy);
            expResult.add(transaction);
            assertEquals(1, instance.getTransactions().size());
            assertEquals(expResult, instance.getTransactions());
            instance.createTransaction(transaction2, modifiedBy2);
            expResult.add(transaction2);
            assertEquals(2, instance.getTransactions().size());
            assertEquals(expResult, instance.getTransactions());
            instance.createTransaction(transaction3, modifiedBy3);
            expResult.add(transaction3);
            assertEquals(3, instance.getTransactions().size());
            assertEquals(expResult, instance.getTransactions());
            instance.createTransaction(transaction4, modifiedBy4);
            expResult.add(transaction4);
            assertEquals(4, instance.getTransactions().size());
            assertEquals(expResult, instance.getTransactions());
            instance.deleteTransaction(transaction.getTransactionRef(), modifiedBy5);
            expResult.remove(transaction);
            assertEquals(3, instance.getTransactions().size());
            assertEquals(expResult, instance.getTransactions());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
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
public class AgreementTest {
    
    public AgreementTest() {
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
     * Test of modifiedBy method, of class Agreement.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of setActualEndDate method, of class Agreement.
     */
    @Test
    public void testSetActualEndDate() {
        try {
            System.out.println("setActualEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 6, 10);
            Calendar testDate = Calendar.getInstance();
            testDate.set(2015, 1, 5);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getActualEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setActualEndDate(testDate.getTime(), modifiedBy);
            assertEquals(null, instance.getActualEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setActualEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getActualEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateAgreement method, of class Agreement.
     */
    @Test
    public void testUpdateAgreement() {
        try {
            System.out.println("updateAgreement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            assertEquals(newDate.getTime(), instance.getStartDate());
            assertEquals("Mr Dwayne TEST Edwards", instance.getAgreementName());
            assertEquals(24, instance.getLength());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Agreement.
     */
    @Test
    public void testCreateNote() {
        System.out.println("createNote");
        try {
            System.out.println("createNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of deleteNote method, of class Agreement.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of createDocument method, of class Agreement.
     */
    @Test
    public void testCreateDocument() {
        try {
            System.out.println("createDocument");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of deleteDocument method, of class Agreement.
     */
    @Test
    public void testDeleteDocument() {
        try {
            System.out.println("deleteDocument");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of getAgreementRef method, of class Agreement.
     */
    @Test
    public void testGetAgreementRef() {
        try {
            System.out.println("getAgreementRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(1, instance.getAgreementRef());
            assertEquals(false, instance.getAgreementRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAgreementName method, of class Agreement.
     */
    @Test
    public void testGetAgreementName() {
        try {
            System.out.println("getAgreementName");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals("Mr Dwayne Leroy Edwards", instance.getAgreementName());
            assertEquals(false, instance.getAgreementName().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class Agreement.
     */
    @Test
    public void testGetStartDate() {
        try {
            System.out.println("getStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date.getTime(), "TEST");
            
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(false, instance.getStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getExpectedEndDate method, of class Agreement.
     */
    @Test
    public void testGetExpectedEndDate() {
        try {
            System.out.println("getExpectedEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar expEnd = Calendar.getInstance();
            expEnd.set(2016, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date.getTime(), "TEST");
            
            assertEquals(expEnd.getTime(), instance.getExpectedEndDate());
            assertEquals(false, instance.getExpectedEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getActualEndDate method, of class Agreement.
     */
    @Test
    public void testGetActualEndDate() {
        try {
            System.out.println("getActualEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 6, 10);
            Calendar testDate = Calendar.getInstance();
            testDate.set(2015, 1, 5);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getActualEndDate());
            instance.setActualEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getActualEndDate());
            assertEquals(false, instance.getActualEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLength method, of class Agreement.
     */
    @Test
    public void testGetLength() {
        try {
            System.out.println("getLength");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date.getTime(), "TEST");
            
            assertEquals(12, instance.getLength());
            assertEquals(false, instance.getLength() == 24);
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccountRef method, of class Agreement.
     */
    @Test
    public void testGetAccountRef() {
        try {
            System.out.println("getAccountRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date.getTime(), "TEST");
            
            assertEquals(1, instance.getAccountRef());
            assertEquals(false, instance.getAccountRef() == 2);
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOfficeCode method, of class Agreement.
     */
    @Test
    public void testGetOfficeCode() {
        try {
            System.out.println("getOfficeCode");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date.getTime(), "TEST");
            
            assertEquals("TEST", instance.getOfficeCode());
            assertEquals(false, instance.getOfficeCode().equals("EDM"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Agreement.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 6, 10);
            Calendar testDate = Calendar.getInstance();
            testDate.set(2015, 1, 5);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(true, instance.isCurrent());
            instance.setActualEndDate(testDate.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setActualEndDate(endDate.getTime(), modifiedBy);
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Agreement.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.hasNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Agreement.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.hasBeenModified());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Agreement.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Agreement.
     */
    @Test
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Calendar modDate = Calendar.getInstance();
            modDate.set(2015, 4, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", modDate.getTime());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Agreement.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Calendar modDate = Calendar.getInstance();
            modDate.set(2015, 4, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", modDate.getTime());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Agreement.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getLastModification());
            instance.updateAgreement("Mr Dwayne TEST Edwards", newDate.getTime(), 24, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Agreement.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar newDate = Calendar.getInstance();
            newDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, note.equals(instance.getNote(note.getReference())));
            assertEquals(null, instance.getNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(note, instance.getNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Agreement.
     */
    @Test
    public void testGetNotes() {
        try {
            System.out.println("getNotes");
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
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of hasDocument method, of class Agreement.
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
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument(7));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Agreement.
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
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.hasDocument("TEST.pdf"));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(false, instance.hasDocument("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocument method, of class Agreement.
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
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(document, instance.getDocument(document.getDocumentRef()));
            assertEquals(null, instance.getDocument(9));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocuments method, of class Agreement.
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
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
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
     * Test of getCreatedBy method, of class Agreement.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Agreement.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 10);
            Agreement instance = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", date2.getTime(), "TEST");
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date2.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
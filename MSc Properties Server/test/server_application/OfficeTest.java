/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AgreementInterface;
import interfaces.ContactInterface;
import interfaces.Document;
import interfaces.Element;
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
public class OfficeTest {
    
    public OfficeTest() {
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
     * Test of modifiedBy method, of class Office.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createContact method, of class Office.
     */
    @Test
    public void testCreateContact() {
        try {
            System.out.println("createContact");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact contact = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasContact(contact.getContactRef()));
            assertEquals(0, instance.getModifiedBy().size());
            instance.createContact(contact, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.hasContact(contact.getContactRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteContact method, of class Office.
     */
    @Test
    public void testDeleteContact() {
        try {
            System.out.println("deleteContact");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact contact = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            Contact contact2 = new Contact(2, element, "07872395479", date.getTime(), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasContact(contact.getContactRef()));
            assertEquals(0, instance.getModifiedBy().size());
            instance.createContact(contact, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(1, instance.getContacts().size());
            assertEquals(true, instance.hasContact(contact.getContactRef()));
            instance.createContact(contact2, modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(2, instance.getContacts().size());
            assertEquals(true, instance.hasContact(contact2.getContactRef()));
            instance.deleteContact(contact.getContactRef(), modifiedBy3);
            assertEquals(false, instance.hasContact(contact.getContactRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Office.
     */
    @Test
    public void testCreateNote() {
        try {
            System.out.println("createNote");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note2, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note2.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class Office.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note2, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            instance.deleteNote(5, modifiedBy2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteNote(note2.getReference(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getNotes().size());
            assertEquals(false, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createAgreement method, of class Office.
     */
    @Test
    public void testCreateAgreement() {
        try {
            System.out.println("createAgreement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Agreement agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", new Date(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(false, instance.hasAgreement(agreement.getAgreementRef()));
            assertEquals(0, instance.getAgreements().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.deleteAgreement(agreement.getAgreementRef(), modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(1, instance.getAgreements().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.hasAgreement(agreement.getAgreementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteAgreement method, of class Office.
     */
    @Test
    public void testDeleteAgreement() {
        try {
            System.out.println("deleteAgreement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Agreement agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", new Date(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(0, instance.getAgreements().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.deleteAgreement(agreement.getAgreementRef(), modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(1, instance.getAgreements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteAgreement(agreement.getAgreementRef(), modifiedBy2);
            assertEquals(0, instance.getAgreements().size());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.hasAgreement(agreement.getAgreementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createAccount method, of class Office.
     */
    @Test
    public void testCreateAccount() {
        try {
            System.out.println("createAccount");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AccountInterface account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasAccount(account.getAccRef()));
            assertEquals(0, instance.getAccounts().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.deleteAccount(account.getAccRef(), modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            instance.createAccount(account, modifiedBy);
            assertEquals(1, instance.getAccounts().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.hasAccount(account.getAccRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteAccount method, of class Office.
     */
    @Test
    public void testDeleteAccount() {
        try {
            System.out.println("deleteAccount");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AccountInterface account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getAccounts().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.deleteAccount(account.getAccRef(), modifiedBy);
            assertEquals(0, instance.getModifiedBy().size());
            instance.createAccount(account, modifiedBy);
            assertEquals(1, instance.getAccounts().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteAccount(account.getAccRef(), modifiedBy2);
            assertEquals(0, instance.getAccounts().size());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.hasAccount(account.getAccRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setStartDate method, of class Office.
     */
    @Test
    public void testSetStartDate() {
        try {
            System.out.println("setStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(0, instance.getModifiedBy().size());
            instance.setStartDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(false, instance.getStartDate().equals(date.getTime()));        
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class Office.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar startDate = Calendar.getInstance();
            startDate.set(2015, 3, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 7, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, startDate.getTime(), "DEDWARDS", new Date());
            
            Account account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", startDate.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            assertEquals(true, instance.isCurrent());
            instance.createAccount(account, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isCurrent());
            assertEquals(null, instance.getEndDate());
            account.setEndDate(endDate.getTime(), modifiedBy);
            instance.setEndDate(startDate.getTime(), modifiedBy2);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.isCurrent());
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate.getTime(), modifiedBy2);
            assertEquals(endDate.getTime(), instance.getEndDate());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createDocument method, of class Office.
     */
    @Test
    public void testCreateDocument() {
        try {
            System.out.println("createDocument");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getDocuments().size());
            instance.createDocument(document, modifiedBy);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(true, instance.hasDocument("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteDocument method, of class Office.
     */
    @Test
    public void testDeleteDocument() {
        try {
            System.out.println("deleteDocument");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
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
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOfficeCode method, of class Office.
     */
    @Test
    public void testGetOfficeCode() {
        try {
            System.out.println("getOfficeCode");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals("EDM", instance.getOfficeCode());
            assertEquals(false, instance.getOfficeCode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddress method, of class Office.
     */
    @Test
    public void testGetAddress() {
        try {
            System.out.println("getAddress");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(address, instance.getAddress());
            assertEquals(false, instance.getAddress().equals(address2));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class Office.
     */
    @Test
    public void testGetStartDate() {
        try {
            System.out.println("getStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(false, instance.getStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class Office.
     */
    @Test
    public void testGetEndDate() {
        try {
            System.out.println("getEndDate");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 3, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, date2.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Office.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasNote(note2.getReference()));
            instance.createNote(note2, modifiedBy);
            assertEquals(true, instance.hasNote(note2.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Office.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, note2.equals(instance.getNote(note2.getReference())));
            assertEquals(null, instance.getNote(note2.getReference()));
            instance.createNote(note2, modifiedBy);
            assertEquals(note2, instance.getNote(note2.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Office.
     */
    @Test
    public void testGetNotes() {
        try {
            System.out.println("getNotes");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note2, modifiedBy);
            expResult.add(note2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note3, modifiedBy2);
            expResult.add(note3);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note4, modifiedBy3);
            expResult.add(note4);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note5, modifiedBy4);
            expResult.add(note5);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note4.getReference(), modifiedBy5);
            expResult.remove(note4);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasAgreement method, of class Office.
     */
    @Test
    public void testHasAgreement() {
        try {
            System.out.println("hasAgreement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Agreement agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", new Date(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(0, instance.getAgreements().size());
            assertEquals(false, instance.hasAgreement(agreement.getAgreementRef()));
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(1, instance.getAgreements().size());
            assertEquals(true, instance.hasAgreement(agreement.getAgreementRef()));
            assertEquals(false, instance.hasAgreement(7));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAgreement method, of class Office.
     */
    @Test
    public void testGetAgreement() {
        try {
            System.out.println("getAgreement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AgreementInterface agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            AgreementInterface agreement2 = new Agreement(2, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(null, instance.getAgreement(agreement.getAgreementRef()));
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(agreement, instance.getAgreement(agreement.getAgreementRef()));
            assertEquals(false, instance.getAgreement(agreement.getAgreementRef()).equals(agreement2));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAgreements method, of class Office.
     */
    @Test
    public void testGetAgreements() {
        try {
            System.out.println("getAgreements");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AgreementInterface agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            AgreementInterface agreement2 = new Agreement(2, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            AgreementInterface agreement3 = new Agreement(3, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            AgreementInterface agreement4 = new Agreement(4, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(0, instance.getAgreements().size());
            List<AgreementInterface> expResult = new ArrayList();
            List<AgreementInterface> test = new ArrayList();
            instance.createAgreement(agreement, modifiedBy);
            expResult.add(agreement);
            assertEquals(1, instance.getAgreements().size());
            assertEquals(expResult, instance.getAgreements());
            instance.createAgreement(agreement2, modifiedBy2);
            expResult.add(agreement2);
            assertEquals(2, instance.getAgreements().size());
            assertEquals(expResult, instance.getAgreements());
            instance.createAgreement(agreement3, modifiedBy3);
            expResult.add(agreement3);
            assertEquals(3, instance.getAgreements().size());
            assertEquals(expResult, instance.getAgreements());
            instance.createAgreement(agreement4, modifiedBy4);
            expResult.add(agreement4);
            assertEquals(4, instance.getAgreements().size());
            assertEquals(expResult, instance.getAgreements());
            instance.deleteAgreement(agreement2.getAgreementRef(), modifiedBy5);
            expResult.remove(agreement2);
            assertEquals(3, instance.getAgreements().size());
            assertEquals(expResult, instance.getAgreements());
            assertEquals(false, instance.getAgreements().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasAccount method, of class Office.
     */
    @Test
    public void testHasAccount() {
        try {
            System.out.println("hasAccount");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AccountInterface account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getAccounts().size());
            assertEquals(false, instance.hasAccount(account.getAccRef()));
            instance.createAccount(account, modifiedBy);
            assertEquals(1, instance.getAccounts().size());
            assertEquals(true, instance.hasAccount(account.getAccRef()));
            assertEquals(false, instance.hasAccount(7));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccount method, of class Office.
     */
    @Test
    public void testGetAccount() {
        try {
            System.out.println("getAccount");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AccountInterface account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            AccountInterface account2 = new Account(2, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getAccount(account.getAccRef()));
            instance.createAccount(account, modifiedBy);
            assertEquals(account, instance.getAccount(account.getAccRef()));
            assertEquals(false, instance.getAccount(account.getAccRef()).equals(account2));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAccounts method, of class Office.
     */
    @Test
    public void testGetAccounts() {
        try {
            System.out.println("getAccounts");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            AccountInterface account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            AccountInterface account2 = new Account(2, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            AccountInterface account3 = new Account(3, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            AccountInterface account4 = new Account(4, "Mr Dwayne Leroy Edwards", "TEST", date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getAccounts().size());
            List<AccountInterface> expResult = new ArrayList();
            List<AccountInterface> test = new ArrayList();
            instance.createAccount(account, modifiedBy);
            expResult.add(account);
            assertEquals(1, instance.getAccounts().size());
            assertEquals(expResult, instance.getAccounts());
            instance.createAccount(account2, modifiedBy2);
            expResult.add(account2);
            assertEquals(2, instance.getAccounts().size());
            assertEquals(expResult, instance.getAccounts());
            instance.createAccount(account3, modifiedBy3);
            expResult.add(account3);
            assertEquals(3, instance.getAccounts().size());
            assertEquals(expResult, instance.getAccounts());
            instance.createAccount(account4, modifiedBy4);
            expResult.add(account4);
            assertEquals(4, instance.getAccounts().size());
            assertEquals(expResult, instance.getAccounts());
            instance.deleteAccount(account2.getAccRef(), modifiedBy5);
            expResult.remove(account2);
            assertEquals(3, instance.getAccounts().size());
            assertEquals(expResult, instance.getAccounts());
            assertEquals(false, instance.getAccounts().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContact method, of class Office.
     */
    @Test
    public void testGetContact() {
        try {
            System.out.println("getContact");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Contact contact = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getContact(contact.getContactRef()));
            instance.createContact(contact, modifiedBy);
            assertEquals(contact, instance.getContact(contact.getContactRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContacts method, of class Office.
     */
    @Test
    public void testGetContacts() {
        try {
            System.out.println("getContacts");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact contact = new Contact(1, element, "07872395479", date.getTime(), note3, "DEDWARDS", new Date());
            Contact contact2 = new Contact(2, element, "07872395479", date.getTime(), note4, "DEDWARDS", new Date());
            Contact contact3 = new Contact(3, element, "07872395479", date.getTime(), note5, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            List<ContactInterface> expResult = new ArrayList();
            List<ContactInterface> test = new ArrayList();
            assertEquals(0, instance.getContacts().size());
            assertEquals(expResult, instance.getContacts());
            instance.createContact(contact, modifiedBy);
            expResult.add(contact);
            assertEquals(1, instance.getContacts().size());
            assertEquals(expResult, instance.getContacts());
            instance.createContact(contact2, modifiedBy2);
            expResult.add(contact2);
            assertEquals(2, instance.getContacts().size());
            assertEquals(expResult, instance.getContacts());
            instance.createContact(contact3, modifiedBy3);
            expResult.add(contact3);
            assertEquals(3, instance.getContacts().size());
            assertEquals(expResult, instance.getContacts());
            instance.deleteContact(contact.getContactRef(), modifiedBy4);
            expResult.remove(contact);
            assertEquals(2, instance.getContacts().size());
            assertEquals(expResult, instance.getContacts());
            assertEquals(false, instance.getContacts().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasContact method, of class Office.
     */
    @Test
    public void testHasContact() {
        try {
            System.out.println("hasContact");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Contact contact = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasContact(contact.getContactRef()));
            instance.createContact(contact, modifiedBy);
            assertEquals(true, instance.hasContact(contact.getContactRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of canCloseOffice method, of class Office.
     */
    @Test
    public void testCanCloseOffice() {
        try {
            System.out.println("canCloseOffice");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 4, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Agreement agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", date.getTime(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(true, instance.canCloseOffice());
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(false, instance.canCloseOffice());
            assertEquals(true, agreement.isCurrent());
            agreement.setActualEndDate(date2.getTime(), modifiedBy2);
            assertEquals(false, agreement.isCurrent());
            assertEquals(true, instance.canCloseOffice());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasAgreements method, of class Office.
     */
    @Test
    public void testHasAgreements() {
        try {
            System.out.println("hasAgreements");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Agreement agreement = new Agreement(1, "Mr Dwayne Leroy Edwards", new Date(), 12, 1, "DEDWARDS", new Date(), "TEST");
            
            assertEquals(0, instance.getAgreements().size());
            assertEquals(false, instance.hasAgreements());
            instance.createAgreement(agreement, modifiedBy);
            assertEquals(1, instance.getAgreements().size());
            assertEquals(true, instance.hasAgreements());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasAccounts method, of class Office.
     */
    @Test
    public void testHasAccounts() {
        try {
            System.out.println("hasAccounts");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            Account account = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", new Date(), "DEDWARDS", new Date());
            
            assertEquals(0, instance.getAccounts().size());
            assertEquals(false, instance.hasAccounts());
            instance.createAccount(account, modifiedBy);
            assertEquals(1, instance.getAccounts().size());
            assertEquals(true, instance.hasAccounts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Office.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, date.getTime(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Office.
     */
    @Test
    public void testHasDocument_int() {
        try {
            System.out.println("hasDocument");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument(7));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Office.
     */
    @Test
    public void testHasDocument_String() {
        try {
            System.out.println("hasDocument");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument("TEST.pdf"));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(false, instance.hasDocument("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocument method, of class Office.
     */
    @Test
    public void testGetDocument() {
        try {
            System.out.println("getDocument");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(document, instance.getDocument(document.getDocumentRef()));
            assertEquals(null, instance.getDocument(9));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocuments method, of class Office.
     */
    @Test
    public void testGetDocuments() {
        try {
            System.out.println("getDocuments");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note2, "DEDWARDS", new Date());
            Document document2 = new DocumentImpl(2, new File("TEST2.pdf"), note3, "DEDWARDS", new Date());
            Document document3 = new DocumentImpl(3, new File("TEST3.pdf"), note4, "DEDWARDS", new Date());
            Document document4 = new DocumentImpl(4, new File("TEST4.pdf"), note5, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
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
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Office.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.createNote(note2, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Office.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createNote(note2, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Office.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date2.getTime());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createNote(note2, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Office.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createNote(note2, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Office.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.createNote(note2, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note3, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Office.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Office.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 3, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Office instance = new Office("EDM", address, date.getTime(), "DEDWARDS", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
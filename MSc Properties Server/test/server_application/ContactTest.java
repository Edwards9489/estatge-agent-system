/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

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
public class ContactTest {
    
    public ContactTest() {
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
     * Test of modifiedBy method, of class Contact.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class Contact.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateContact method, of class Contact.
     */
    @Test
    public void testUpdateContact() {
        try {
            System.out.println("updateContact");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 20);
            Date endDate = date3.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            assertEquals(element, instance.getContactType());
            assertEquals("07872395479", instance.getContactValue());
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals("TEST NOTE2", instance.getComment());
            
            assertEquals(false, instance.getContactType().equals(element2));
            assertEquals(false, instance.getContactValue().equals("02083794321"));
            assertEquals(false, instance.getStartDate().equals(endDate));
            assertEquals(false, instance.getComment().equals("NEW COMMENT"));
            
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            
            assertEquals(true, instance.hasBeenModified());
            assertEquals(element2, instance.getContactType());
            assertEquals("02083794321", instance.getContactValue());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("NEW COMMENT", instance.getComment());
            
            assertEquals(false, instance.getContactType().equals(element));
            assertEquals(false, instance.getContactValue().equals("07872395479"));
            assertEquals(false, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getComment().equals("TEST NOTE2"));
            
            instance.setEndDate(endDate, modifiedBy2);
            
            instance.updateContact(element, "07872395479", date.getTime(), "TEST NOTE2 AMENDED", modifiedBy2);
            
            assertEquals(element2, instance.getContactType());
            assertEquals("02083794321", instance.getContactValue());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("NEW COMMENT", instance.getComment());
            
            assertEquals(false, instance.getContactType().equals(element));
            assertEquals(false, instance.getContactValue().equals("07872395479"));
            assertEquals(false, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getComment().equals("TEST NOTE2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContactRef method, of class Contact.
     */
    @Test
    public void testGetContactRef() {
        try {
            System.out.println("getContactRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(1, instance.getContactRef());
            assertEquals(false, instance.getContactRef() == 65);
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContactType method, of class Contact.
     */
    @Test
    public void testGetContactType() {
        try {
            System.out.println("getContactType");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(element, instance.getContactType());
            assertEquals(false, instance.getContactType().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContactTypeDescription method, of class Contact.
     */
    @Test
    public void testGetContactTypeDescription() {
        try {
            System.out.println("getContactTypeDescription");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(element.getDescription(), instance.getContactTypeDescription());
            assertEquals(false, instance.getContactTypeDescription().equals("TEST2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContactValue method, of class Contact.
     */
    @Test
    public void testGetContactValue() {
        try {
            System.out.println("getContactValue");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals("07872395479", instance.getContactValue());
            assertEquals(false, instance.getContactValue().equals("TEST2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class Contact.
     */
    @Test
    public void testGetStartDate() {
        try {
            System.out.println("getStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals(false, instance.getStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class Contact.
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
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(endDate, instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Contact.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Boolean test = false;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(true, instance.isCurrent());
            assertEquals(false, test.equals(instance.isCurrent()));
            instance.setEndDate(endDate, modifiedBy);
            assertEquals(false, instance.isCurrent());
            assertEquals(true, test.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Contact.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 12);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(false, instance.hasBeenModified());
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Contact.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 11);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 12);
            Date endDate = date2.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModifiedBy());
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
            instance.setEndDate(endDate, modifiedBy2);
            assertEquals(modifiedBy2.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Contact.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 11);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 20);
            Date endDate = date3.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(endDate));
            instance.setEndDate(endDate, modifiedBy2);
            assertEquals(modifiedBy2.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Contact.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 11);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 20);
            Date endDate = date3.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
            instance.setEndDate(endDate, modifiedBy2);
            modifiedByList.add(modifiedBy2);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Contact.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 11);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 5, 20);
            Date endDate = date3.getTime();
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModification());
            instance.updateContact(element2, "02083794321", date2.getTime(), "NEW COMMENT", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.setEndDate(endDate, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Contact.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(note2, instance.getNote());
            assertEquals(false, instance.getNote().equals(note));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class Contact.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Contact.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", new Date());
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Contact.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar createdDate = Calendar.getInstance();
            createdDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Contact instance = new Contact(1, element, "07872395479", date.getTime(), note2, "DEDWARDS", createdDate.getTime());
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(createdDate.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
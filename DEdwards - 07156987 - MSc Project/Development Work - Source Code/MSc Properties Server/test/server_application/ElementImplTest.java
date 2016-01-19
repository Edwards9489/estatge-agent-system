/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

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
public class ElementImplTest {

    public ElementImplTest() {
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
     * Test of setCurrent method, of class ElementImpl.
     */
    @Test
    public void testSetCurrent() {
        try {
            System.out.println("setCurrent");
            Boolean result = false;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            assertEquals(false, result.equals(instance.isCurrent()));
            instance.setCurrent(false);
            assertEquals(false, instance.isCurrent());
            assertEquals(true, result.equals(instance.isCurrent()));
            instance.setCurrent(false);
            assertEquals(false, instance.isCurrent());
            assertEquals(true, result.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(ElementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of modifiedBy method, of class ElementImpl.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
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
     * Test of updateElement method, of class ElementImpl.
     */
    @Test
    public void testUpdateElement() {
        try {
            System.out.println("updateElement");
            Boolean result = false;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            assertEquals("TEST", instance.getDescription());
            assertEquals(true, instance.isCurrent());
            assertEquals(0, instance.getModifiedBy().size());
            
            assertEquals(false, instance.getDescription().equals("TESTUPDATED"));
            assertEquals(false, result.equals(instance.isCurrent()));
            assertEquals(false, instance.getModifiedBy().size() == 1);

            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);

            assertEquals("TESTUPDATED", instance.getDescription());
            assertEquals(false, instance.isCurrent());
            assertEquals(1, instance.getModifiedBy().size());

            assertEquals(false, instance.getDescription().equals("TEST"));
            assertEquals(true, result.equals(instance.isCurrent()));
            assertEquals(false, instance.getModifiedBy().isEmpty());
        } catch (RemoteException ex) {
            Logger.getLogger(ElementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCode method, of class ElementImpl.
     */
    @Test
    public void testGetCode() {
        try {
            System.out.println("getCode");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals("TEST", instance.getCode());
            assertEquals(false, instance.getCode().equals("TEST2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDescription method, of class ElementImpl.
     */
    @Test
    public void testGetDescription() {
        try {
            System.out.println("getDescription");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals("TEST", instance.getDescription());
            assertEquals(false, instance.getDescription().equals("TEST2"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class ElementImpl.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Boolean result = false;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            assertEquals(false, result.equals(instance.isCurrent()));
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            assertEquals(false, instance.isCurrent());
            assertEquals(true, result.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class ElementImpl.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class ElementImpl.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            assertEquals(null, instance.getLastModifiedBy());
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class ElementImpl.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 5, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date2.getTime());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class ElementImpl.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            assertEquals(null, instance.getLastModification());
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.updateElement("TESTUPDATED", true, "DEDWARDS", modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class ElementImpl.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateElement("TESTUPDATED", false, "DEDWARDS", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class ElementImpl.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE2", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            assertEquals(note, instance.getNote());
            assertEquals(false, instance.getNote().equals(note2));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class ElementImpl.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            assertEquals("TEST NOTE", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class ElementImpl.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());

            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class ElementImpl.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar createdDate = Calendar.getInstance();
            createdDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ElementImpl instance = new ElementImpl("TEST", "TEST", note, "DEDWARDS", createdDate.getTime());

            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(createdDate.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
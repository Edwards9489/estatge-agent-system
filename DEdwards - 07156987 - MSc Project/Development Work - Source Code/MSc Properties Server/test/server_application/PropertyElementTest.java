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
public class PropertyElementTest {
    
    public PropertyElementTest() {
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
     * Test of modifiedBy method, of class PropertyElement.
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
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updatePropertyElement method, of class PropertyElement.
     */
    @Test
    public void testUpdatePropertyElement() {
        try {
            System.out.println("updatePropertyElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getStringValue());
            assertEquals(true, instance.getDoubleValue() == 1200.00);
            assertEquals(true, instance.isCharge());
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.updatePropertyElement(date2.getTime(), "", 500.00, true, "UPDATED NOTE", modifiedBy);
            
            assertEquals(null, instance.getStringValue());
            assertEquals(true, instance.getDoubleValue() == 500.00);
            assertEquals(true, instance.isCharge());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("UPDATED NOTE", instance.getComment());
            assertEquals(1, instance.getModifiedBy().size());
            
            assertEquals(false, instance.getDoubleValue() == 300.00);
            assertEquals(true, instance.isCharge());
            assertEquals(false, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getComment().equals("TEST NOTE2"));
            
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            
            instance.updatePropertyElement(date2.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy2);
            
            assertEquals("IS NO LONGER A SALARY BENEFIT", instance.getStringValue());
            assertEquals(null, instance.getDoubleValue());
            assertEquals(false, instance.isCharge());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("UPDATED NOTE AGAIN", instance.getComment());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class PropertyElement.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("testSetEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(date.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropertyElementRef method, of class PropertyElement.
     */
    @Test
    public void testGetPropertyElementRef() {
        try {
            System.out.println("getPropertyElementRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getPropertyElementRef());
            assertEquals(false, instance.getPropertyElementRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getElement method, of class PropertyElement.
     */
    @Test
    public void testGetElement() {
        try {
            System.out.println("getElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(element, instance.getElement());
            assertEquals(false, instance.getElement().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getElementCode method, of class PropertyElement.
     */
    @Test
    public void testGetElementCode() {
        try {
            System.out.println("getElementCode");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals("TEST", instance.getElementCode());
            assertEquals(false, instance.getElementCode().equals("EDM"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStringValue method, of class PropertyElement.
     */
    @Test
    public void testGetStringValue() {
        try {
            System.out.println("getStringValue");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), false, "Testing String Value", null, note2, "DEDWARDS", new Date());
            
            assertEquals("Testing String Value", instance.getStringValue());
            assertEquals(false, instance.getStringValue().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDoubleValue method, of class PropertyElement.
     */
    @Test
    public void testGetDoubleValue() {
        try {
            System.out.println("getDoubleValue");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getDoubleValue() == 1200.00);
            assertEquals(false, instance.getDoubleValue() == 900.00);
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class PropertyElement.
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
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class PropertyElement.
     */
    @Test
    public void testGetEndDate() {
        try {
            System.out.println("getEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class PropertyElement.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(date2.getTime(), modifiedBy);
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCharge method, of class PropertyElement.
     */
    @Test
    public void testIsCharge() {
        try {
            System.out.println("isCharge");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isCharge());
            assertEquals(false, result.equals(instance.isCharge()));
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy);
            assertEquals(false, instance.isCharge());
            assertEquals(true, result.equals(instance.isCharge()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isElementCode method, of class PropertyElement.
     */
    @Test
    public void testIsElementCode() {
        try {
            System.out.println("isElementCode");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(true, instance.isElementCode(element.getCode()));
            assertEquals(false, instance.isElementCode("RENT"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class PropertyElement.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class PropertyElement.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class PropertyElement.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date.getTime());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class PropertyElement.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class PropertyElement.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.updatePropertyElement(date.getTime(), "IS NO LONGER A SALARY BENEFIT", null, false, "UPDATED NOTE AGAIN", modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class PropertyElement.
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
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(note2, instance.getNote());
            assertEquals(false, instance.getNote().equals(note));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class PropertyElement.
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
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class PropertyElement.
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
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class PropertyElement.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            PropertyElement instance = new PropertyElement(1, element, date.getTime(), true, null, 1200.00, note2, "DEDWARDS", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyElementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.LandlordInterface;
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
public class PropertyTest {
    
    public PropertyTest() {
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
     * Test of modifiedBy method, of class Property.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setLandlords method, of class Property.
     */
    @Test
    public void testSetLandlords() {
        try {
            System.out.println("setLandlords");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(1989, 3, 9);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address2, date.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date2.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            LandlordInterface landlord2 = new Landlord(2, person, "DEDWARDS", new Date());
            LandlordInterface landlord3 = new Landlord(3, person, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getLandlords().size());
            List<LandlordInterface> test = new ArrayList();
            assertEquals(test, instance.getLandlords());
            test.add(landlord);
            test.add(landlord2);
            test.add(landlord3);
            instance.setLandlords(test, modifiedBy);
            assertEquals(3, instance.getLandlords().size());
            assertEquals(test, instance.getLandlords());
            test.remove(landlord2);
            instance.setLandlords(test, modifiedBy2);
            assertEquals(2, instance.getLandlords().size());
            assertEquals(test, instance.getLandlords());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setLeaseEndDate method, of class Property.
     */
    @Test
    public void testSetLeaseEndDate() {
        try {
            System.out.println("setLeaseEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.setLeaseEndDate(date2.getTime(), modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(date2.getTime(), instance.getLeaseEndDate());
            instance.setLeaseEndDate(date.getTime(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(date.getTime(), instance.getLeaseEndDate());
            assertEquals(false, instance.getLeaseEndDate().equals(date2.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setPropStatus method, of class Property.
     */
    @Test
    public void testSetPropStatus() {
        try {
            System.out.println("setPropStatus");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            assertEquals("NEW", instance.getPropStatus());
            instance.setPropStatus("VOID", modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals("VOID", instance.getPropStatus());
            assertEquals(false, instance.getPropStatus().equals("NEW"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateProperty method, of class Property.
     */
    @Test
    public void testUpdateProperty() {
        try {
            System.out.println("updateProperty");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(1, "", "", "", "", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            assertEquals(address, instance.getAddress());
            assertEquals(date.getTime(), instance.getAcquiredDate());
            assertEquals(element, instance.getPropType());
            assertEquals(element, instance.getPropSubType());
            
            instance.updateProperty(address2, date2.getTime(), element2, element2, modifiedBy);
            
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(address2, instance.getAddress());
            assertEquals(date2.getTime(), instance.getAcquiredDate());
            assertEquals(element2, instance.getPropType());
            assertEquals(element2, instance.getPropSubType());
            
            assertEquals(false, instance.getAddress().equals(address));
            assertEquals(false, instance.getAcquiredDate().equals(date.getTime()));
            assertEquals(false, instance.getPropType().equals(element));
            assertEquals(false, instance.getPropSubType().equals(element));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createPropertyElement method, of class Property.
     */
    @Test
    public void testCreatePropertyElement() {
        try {
            System.out.println("createPropertyElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(2, 1, element, date.getTime(), true, null, 300.00, note5, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getPropertyElements().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(1, instance.getPropertyElements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.createPropertyElement(charges, modifiedBy2);
            assertEquals(2, instance.getPropertyElements().size());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(true, instance.hasPropElement(rent.getPropertyElementRef()));
            assertEquals(false, instance.hasPropElement(8));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of endPropertyElement method, of class Property.
     */
    @Test
    public void testEndPropertyElement() {
        try {
            System.out.println("endPropertyElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Calendar date3 = Calendar.getInstance();
            date3.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date2.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getPropertyElements().size());
            assertEquals(false, instance.hasPropElement(rent.getPropertyElementRef()));
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(1, instance.getPropertyElements().size());
            instance.endPropertyElement(rent.getPropertyElementRef(), date.getTime(), modifiedBy2);
            assertEquals(true, instance.hasCurrentPropElement(rent.getElementCode()));
            assertEquals(1, instance.getModifiedBy().size());
            instance.endPropertyElement(rent.getPropertyElementRef(), date3.getTime(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.hasCurrentPropElement(rent.getElementCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of deletePropertyElement method, of class Property.
     */
    @Test
    public void testDeletePropertyElement() {
        try {
            System.out.println("deletePropertyElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(2, 1, element, date.getTime(), true, null, 300.00, note5, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getPropertyElements().size());
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(1, instance.getPropertyElements().size());
            instance.deletePropertyElement(charges, modifiedBy2);
            assertEquals(1, instance.getPropertyElements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deletePropertyElement(rent, modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getPropertyElements().size());
            assertEquals(false, instance.hasPropElement(rent.getPropertyElementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class Property.
     */
    @Test
    public void testCreateNote() {
        try {
            System.out.println("createNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class Property.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note3, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            instance.deleteNote(5, modifiedBy2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteNote(note3.getReference(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getNotes().size());
            assertEquals(false, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createDocument method, of class Property.
     */
    @Test
    public void testCreateDocument() {
        try {
            System.out.println("createDocument");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getDocuments().size());
            instance.createDocument(document, modifiedBy);
            assertEquals(1, instance.getDocuments().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(true, instance.hasDocument("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteDocument method, of class Property.
     */
    @Test
    public void testDeleteDocument() {
        try {
            System.out.println("deleteDocument");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
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
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropRef method, of class Property.
     */
    @Test
    public void testGetPropRef() {
        try {
            System.out.println("getPropRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getPropRef());
            assertEquals(false, instance.getPropRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddress method, of class Property.
     */
    @Test
    public void testGetAddress() {
        try {
            System.out.println("getAddress");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(1, "", "", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(address, instance.getAddress());
            assertEquals(false, instance.getAddress().equals(address2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLandlords method, of class Property.
     */
    @Test
    public void testGetLandlords() {
        try {
            System.out.println("getLandlords");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(1989, 3, 9);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressInterface address2 = new Address(2, "", "", "", "", "10", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            AddressUsage addressUsage = new AddressUsage(1, address2, date.getTime(), note3, "DEDWARDS", new Date());
            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", date2.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            LandlordInterface landlord2 = new Landlord(2, person, "DEDWARDS", new Date());
            LandlordInterface landlord3 = new Landlord(3, person, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getLandlords().size());
            List<LandlordInterface> expResult = new ArrayList();
            List<LandlordInterface> test = new ArrayList();
            assertEquals(expResult, instance.getLandlords());
            expResult.add(landlord);
            expResult.add(landlord2);
            expResult.add(landlord3);
            instance.setLandlords(expResult, modifiedBy);
            assertEquals(3, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            expResult.remove(landlord2);
            instance.setLandlords(expResult, modifiedBy2);
            assertEquals(2, instance.getLandlords().size());
            assertEquals(expResult, instance.getLandlords());
            assertEquals(false, instance.getLandlords().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAcquiredDate method, of class Property.
     */
    @Test
    public void testGetAcquiredDate() {
        try {
            System.out.println("getAcquiredDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(date.getTime(), instance.getAcquiredDate());
            assertEquals(false, instance.getAcquiredDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLeaseEndDate method, of class Property.
     */
    @Test
    public void testGetLeaseEndDate() {
        try {
            System.out.println("getLeaseEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2017, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLeaseEndDate());
            instance.setLeaseEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getLeaseEndDate());
            instance.setLeaseEndDate(date.getTime(), modifiedBy2);
            assertEquals(date.getTime(), instance.getLeaseEndDate());
            assertEquals(false, instance.getLeaseEndDate().equals(date2.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropType method, of class Property.
     */
    @Test
    public void testGetPropType() {
        try {
            System.out.println("getPropType");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(element, instance.getPropType());
            assertEquals(false, instance.getPropType().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropSubType method, of class Property.
     */
    @Test
    public void testGetPropSubType() {
        try {
            System.out.println("getPropSubType");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(element, instance.getPropSubType());
            assertEquals(false, instance.getPropSubType().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropStatus method, of class Property.
     */
    @Test
    public void testGetPropStatus() {
        try {
            System.out.println("getPropStatus");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals("NEW", instance.getPropStatus());
            instance.setPropStatus("VOID", modifiedBy);
            assertEquals("VOID", instance.getPropStatus());
            assertEquals(false, instance.getPropStatus().equals("NEW"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropertyElements method, of class Property.
     */
    @Test
    public void testGetPropertyElements() {
        try {
            System.out.println("getPropertyElements");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            PropertyElement charges = new PropertyElement(2, 1, element, date.getTime(), true, null, 300.00, note5, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getPropertyElements().size());
            List<PropertyElement> expResult = new ArrayList();
            List<PropertyElement> test = new ArrayList();
            instance.createPropertyElement(rent, modifiedBy);
            expResult.add(rent);
            assertEquals(1, instance.getPropertyElements().size());
            assertEquals(expResult, instance.getPropertyElements());
            instance.createPropertyElement(charges, modifiedBy2);
            expResult.add(charges);
            assertEquals(2, instance.getPropertyElements().size());
            assertEquals(expResult, instance.getPropertyElements());
            instance.deletePropertyElement(charges, modifiedBy3);
            expResult.remove(charges);
            assertEquals(1, instance.getPropertyElements().size());
            assertEquals(expResult, instance.getPropertyElements());
            assertEquals(false, instance.getPropertyElements().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasPropElement method, of class Property.
     */
    @Test
    public void testHasPropElement_String() {
        try {
            System.out.println("hasPropElement - String");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(false, instance.hasPropElement(rent.getElementCode()));
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(rent, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(true, instance.hasPropElement(rent.getElementCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasCurrentPropElement method, of class Property.
     */
    @Test
    public void testHasCurrentPropElement() {
        try {
            System.out.println("hasCurrentPropElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(false, instance.hasCurrentPropElement(rent.getElementCode()));
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(rent, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(true, instance.hasCurrentPropElement(rent.getElementCode()));
            instance.endPropertyElement(rent.getPropertyElementRef(), date2.getTime(), modifiedBy2);
            assertEquals(false, instance.hasCurrentPropElement(rent.getElementCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasPropElement method, of class Property.
     */
    @Test
    public void testHasPropElement_int() {
        try {
            System.out.println("hasPropElement - int");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(false, instance.hasPropElement(rent.getPropertyElementRef()));
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(rent, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(true, instance.hasPropElement(rent.getPropertyElementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPropElement method, of class Property.
     */
    @Test
    public void testGetPropElement() {
        try {
            System.out.println("getPropElement");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(false, instance.hasPropElement(rent.getPropertyElementRef()));
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(rent, instance.getPropElement(rent.getPropertyElementRef()));
            assertEquals(true, instance.hasPropElement(rent.getPropertyElementRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class Property.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2017, 5, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLeaseEndDate());
            assertEquals(false, instance.isCurrent());
            instance.setLeaseEndDate(date2.getTime(), modifiedBy);
            assertEquals(date2.getTime(), instance.getLeaseEndDate());
            assertEquals(true, instance.isCurrent());
            instance.setLeaseEndDate(date.getTime(), modifiedBy2);
            assertEquals(date.getTime(), instance.getLeaseEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRent method, of class Property.
     */
    @Test
    public void testGetRent() {
        try {
            System.out.println("getRent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("RENT", "RENT", note3, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getRent() == 0);
            assertEquals(false, instance.getRent() == 1200.00);
            PropertyElement rent = new PropertyElement(1, 1, element2, date.getTime(), true, null, 1200.00, note4, "DEDWARDS", new Date());
            instance.createPropertyElement(rent, modifiedBy);
            assertEquals(true, instance.getRent() == 1200.00);
            assertEquals(false, instance.getRent() == 0);
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCharges method, of class Property.
     */
    @Test
    public void testGetCharges() {
        try {
            System.out.println("getCharges");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(true, instance.getCharges() == 0);
            assertEquals(false, instance.getCharges() == 300.00);
            PropertyElement charges = new PropertyElement(1, 1, element, date.getTime(), true, null, 300.00, note3, "DEDWARDS", new Date());
            instance.createPropertyElement(charges, modifiedBy);
            assertEquals(true, instance.getCharges() == 300.00);
            assertEquals(false, instance.getCharges() == 0);
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Property.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.createNote(note3, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Property.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createNote(note3, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Property.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar modDate = Calendar.getInstance();
            modDate.set(2015, 4, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", modDate.getTime());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createNote(note3, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Property.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createNote(note3, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Property.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.createNote(note3, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note4, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class Property.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasNote(note3.getReference()));
            instance.createNote(note3, modifiedBy);
            assertEquals(true, instance.hasNote(note3.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Property.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, note.equals(instance.getNote(note3.getReference())));
            assertEquals(null, instance.getNote(note3.getReference()));
            instance.createNote(note3, modifiedBy);
            assertEquals(note3, instance.getNote(note3.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class Property.
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
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST NOTE6", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getNotes().size());
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note3, modifiedBy);
            expResult.add(note3);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note4, modifiedBy2);
            expResult.add(note4);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note5, modifiedBy3);
            expResult.add(note5);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note6, modifiedBy4);
            expResult.add(note6);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note5.getReference(), modifiedBy5);
            expResult.remove(note5);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Property.
     */
    @Test
    public void testHasDocument_int() {
        try {
            System.out.println("hasDocument - int");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument(document.getDocumentRef()));
            assertEquals(false, instance.hasDocument(7));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasDocument method, of class Property.
     */
    @Test
    public void testHasDocument_String() {
        try {
            System.out.println("hasDocument - String");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasDocument("TEST.pdf"));
            instance.createDocument(document, modifiedBy);
            assertEquals(true, instance.hasDocument("TEST.pdf"));
            assertEquals(false, instance.hasDocument("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocument method, of class Property.
     */
    @Test
    public void testGetDocument() {
        try {
            System.out.println("getDocument");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getDocument(document.getDocumentRef()));
            instance.createDocument(document, modifiedBy);
            assertEquals(document, instance.getDocument(document.getDocumentRef()));
            assertEquals(null, instance.getDocument(9));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocuments method, of class Property.
     */
    @Test
    public void testGetDocuments() {
        try {
            System.out.println("getDocuments");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST NOTE5", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST NOTE6", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Document document = new DocumentImpl(1, new File("TEST.pdf"), note3, "DEDWARDS", new Date());
            Document document2 = new DocumentImpl(2, new File("TEST2.pdf"), note4, "DEDWARDS", new Date());
            Document document3 = new DocumentImpl(3, new File("TEST3.pdf"), note5, "DEDWARDS", new Date());
            Document document4 = new DocumentImpl(4, new File("TEST4.pdf"), note6, "DEDWARDS", new Date());
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
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
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Property.
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
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Property.
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
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note2, "DEDWARDS", new Date());
            Property instance = new Property(1, address, date.getTime(), element, element, "DEDWARDS", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
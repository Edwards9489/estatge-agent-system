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
public class AddressTest {
    
    public AddressTest() {
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
     * Test of modifiedBy method, of class Address.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateAddress method, of class Address.
     */
    @Test
    public void testUpdateAddress() {
        try {
            System.out.println("updateAddress");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(0, instance.getModifiedBy().size());
            assertEquals(true, instance.getBuildingNumber().equals("1"));
            assertEquals(true, instance.getBuildingName().equals("Kestrel House"));
            assertEquals(true, instance.getSubStreetNumber().equals("1"));
            assertEquals(true, instance.getSubStreet().equals("Grilse Close"));
            assertEquals(true, instance.getStreetNumber().equals("1"));
            assertEquals(true, instance.getStreet().equals("Cavendish Road"));
            assertEquals(true, instance.getArea().equals("Edmonton"));
            assertEquals(true, instance.getTown().equals("London"));
            assertEquals(true, instance.getCountry().equals("England"));
            assertEquals(true, instance.getPostcode().equals("N9 0DJ"));
            
            assertEquals(false, instance.getBuildingNumber().equals(buildingNumber));
            assertEquals(false, instance.getBuildingName().equals(buildingName));
            assertEquals(false, instance.getSubStreetNumber().equals(subStreetNumber));
            assertEquals(false, instance.getSubStreet().equals(subStreet));
            assertEquals(false, instance.getStreetNumber().equals("TEST"));
            assertEquals(false, instance.getStreet().equals("TEST"));
            assertEquals(false, instance.getArea().equals("TEST"));
            assertEquals(false, instance.getTown().equals("TEST"));
            assertEquals(false, instance.getCountry().equals("TEST"));
            assertEquals(false, instance.getPostcode().equals("TEST"));
            
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            
            assertEquals(false, instance.getBuildingNumber().equals("1"));
            assertEquals(false, instance.getBuildingName().equals("Kestrel House"));
            assertEquals(false, instance.getSubStreetNumber().equals("1"));
            assertEquals(false, instance.getSubStreet().equals("Grilse Close"));
            assertEquals(false, instance.getStreetNumber().equals("TEST"));
            assertEquals(false, instance.getStreet().equals("TEST"));
            assertEquals(false, instance.getArea().equals("TEST"));
            assertEquals(false, instance.getTown().equals("TEST"));
            assertEquals(false, instance.getCountry().equals("TEST"));
            assertEquals(false, instance.getPostcode().equals("TEST"));
            
            
            assertEquals(true, instance.getBuildingNumber().equals(buildingNumber));
            assertEquals(true, instance.getBuildingName().equals(buildingName));
            assertEquals(true, instance.getSubStreetNumber().equals(subStreetNumber));
            assertEquals(true, instance.getSubStreet().equals(subStreet));
            assertEquals(true, instance.getStreetNumber().equals("1"));
            assertEquals(true, instance.getStreet().equals("Cavendish Road"));
            assertEquals(true, instance.getArea().equals("Edmonton"));
            assertEquals(true, instance.getTown().equals("London"));
            assertEquals(true, instance.getCountry().equals("England"));
            assertEquals(true, instance.getPostcode().equals("N9 0DJ"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAddressRef method, of class Address.
     */
    @Test
    public void testGetAddressRef() {
        try {
            System.out.println("getAddressRef");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(1, instance.getAddressRef());
            assertEquals(false, instance.getAddressRef() == 2);
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBuildingNumber method, of class Address.
     */
    @Test
    public void testGetBuildingNumber() {
        try {
            System.out.println("getBuildingNumber");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("1", instance.getBuildingNumber());
            assertEquals(false, instance.getBuildingNumber().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBuildingName method, of class Address.
     */
    @Test
    public void testGetBuildingName() {
        try {
            System.out.println("getBuildingName");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("Kestrel House", instance.getBuildingName());
            assertEquals(false, instance.getBuildingName().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getSubStreetNumber method, of class Address.
     */
    @Test
    public void testGetSubStreetNumber() {
        System.out.println("getSubStreetNumber");
        try {
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("1", instance.getSubStreetNumber());
            assertEquals(false, instance.getSubStreetNumber().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getSubStreet method, of class Address.
     */
    @Test
    public void testGetSubStreet() {
        try {
            System.out.println("getSubStreet");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("Grilse Close", instance.getSubStreet());
            assertEquals(false, instance.getSubStreet().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStreetNumber method, of class Address.
     */
    @Test
    public void testGetStreetNumber() {
        try {
            System.out.println("getStreetNumber");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("1", instance.getStreetNumber());
            assertEquals(false, instance.getStreetNumber().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStreet method, of class Address.
     */
    @Test
    public void testGetStreet() {
        try {
            System.out.println("getStreet");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("Cavendish Road", instance.getStreet());
            assertEquals(false, instance.getStreet().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getArea method, of class Address.
     */
    @Test
    public void testGetArea() {
        try {
            System.out.println("getArea");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("Edmonton", instance.getArea());
            assertEquals(false, instance.getArea().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTown method, of class Address.
     */
    @Test
    public void testGetTown() {
        try {
            System.out.println("getTown");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("London", instance.getTown());
            assertEquals(false, instance.getTown().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCountry method, of class Address.
     */
    @Test
    public void testGetCountry() {
        try {
            System.out.println("getCountry");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("England", instance.getCountry());
            assertEquals(false, instance.getCountry().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPostcode method, of class Address.
     */
    @Test
    public void testGetPostcode() {
        try {
            System.out.println("getPostcode");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("N9 0DJ", instance.getPostcode());
            assertEquals(false, instance.getPostcode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class Address.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(note, instance.getNote());
            assertEquals(false, instance.getNote().equals(note2));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class Address.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals("TEST NOTE", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class Address.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(false, instance.hasBeenModified());
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class Address.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModifiedBy());
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class Address.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class Address.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class Address.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            String buildingNumber = "";
            String buildingName = "";
            String subStreetNumber = "";
            String subStreet = "";
            String comment = "AMENDED ADDRESS";
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(null, instance.getLastModification());
            instance.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.updateAddress(buildingNumber, buildingName, "5", "Parr Close", instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), comment, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class Address.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", new Date());
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class Address.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", date.getTime());
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of printAddress method, of class Address.
     */
    @Test
    public void testPrintAddress() {
        try {
            System.out.println("printAddress");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Address instance = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", note, "DEDWARDS", date.getTime());
            assertEquals(false, instance.printAddress().equals("TEST"));
            assertEquals(true, instance.printAddress().equals("1 Kestrel House, 1 Grilse Close, 1 Cavendish Road, Edmonton, London, England, N9 0DJ"));
            instance.updateAddress("", "", instance.getSubStreetNumber(), instance.getSubStreet(), instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), instance.getComment(), modifiedBy);
            assertEquals(false, instance.printAddress().equals("TEST"));
            assertEquals(true, instance.printAddress().equals("1 Grilse Close, 1 Cavendish Road, Edmonton, London, England, N9 0DJ"));
            instance.updateAddress("", "", "", "", instance.getStreetNumber(), instance.getStreet(), instance.getArea(), instance.getTown(), instance.getCountry(), instance.getPostcode(), instance.getComment(), modifiedBy2);
            assertEquals(false, instance.printAddress().equals("TEST"));
            assertEquals(true, instance.printAddress().equals("1 Cavendish Road, Edmonton, London, England, N9 0DJ"));
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

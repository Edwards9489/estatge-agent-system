/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
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
public class ModifiedByTest {
    
    public ModifiedByTest() {
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
     * Test of getModifiedBy method, of class ModifiedBy.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            ModifiedBy instance = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals("DEDWARDS", instance.getModifiedBy());
            assertEquals(false, instance.getModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ModifiedByTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedDate method, of class ModifiedBy.
     */
    @Test
    public void testGetModifiedDate() {
        try {
            System.out.println("getModifiedDate");
            Calendar modDate = Calendar.getInstance();
            modDate.set(2015, 1, 10);
            ModifiedBy instance = new ModifiedBy("MODIFIED", "DEDWARDS", modDate.getTime());
            
            assertEquals(modDate.getTime(), instance.getModifiedDate());
            assertEquals(false, instance.getModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(ModifiedByTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDescription method, of class ModifiedBy.
     */
    @Test
    public void testGetDescription() {
        try {
            System.out.println("getDescription");
            ModifiedBy instance = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals("MODIFIED", instance.getDescription());
            assertEquals(false, instance.getDescription().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(ModifiedByTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

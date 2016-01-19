/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.rmi.RemoteException;
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
public class UserImplTest {
    
    public UserImplTest() {
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
     * Test of isUser method, of class UserImpl.
     */
    @Test
    public void testIsUser() {
        try {
            System.out.println("isUser");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(true, instance.isUser("DEDWARDS", "TestPassword"));
            assertEquals(false, instance.isUser("DEDWARDS", "TEST"));
            assertEquals(false, instance.isUser("TEST", "TestPassword"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setPasswordReset method, of class UserImpl.
     */
    @Test
    public void testSetPasswordReset() {
        try {
            System.out.println("setPasswordReset");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(true, instance.getPasswordReset());
            instance.setPassword("NEWPassword");
            assertEquals(false, instance.getPasswordReset());
            instance.setPasswordReset(false);
            assertEquals(false, instance.getPasswordReset());
            instance.setPasswordReset(true);
            assertEquals(true, instance.getPasswordReset());
            instance.setPasswordReset(false);
            assertEquals(false, instance.getPasswordReset());
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setPassword method, of class UserImpl.
     */
    @Test
    public void testSetPassword() {
        try {
            System.out.println("setPassword");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(true, instance.getPassword().equals("TestPassword"));
            assertEquals(false, instance.getPassword().equals("TEST"));
            instance.setPassword("NEWPassword");
            assertEquals(true, instance.getPassword().equals("NEWPassword"));
            assertEquals(false, instance.getPassword().equals("TestPassword"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setOfficeCode method, of class UserImpl.
     */
    @Test
    public void testSetOfficeCode() {
        try {
            System.out.println("setOfficeCode");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals("TEST", instance.getOfficeCode());
            instance.setOfficeCode("EDM");
            assertEquals("EDM", instance.getOfficeCode());
            assertEquals(false, instance.getOfficeCode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setUserPermissions method, of class UserImpl.
     */
    @Test
    public void testSetUserPermissions() {
        try {
            System.out.println("setUserPermissions");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getRead());
            assertEquals(false, instance.getWrite());
            assertEquals(false, instance.getUpdate());
            assertEquals(false, instance.getEmployeeRead());
            assertEquals(false, instance.getEmployeeWrite());
            assertEquals(false, instance.getEmployeeUpdate());
            
            instance.setUserPermissions(true, true, true, true, false, false);
            
            assertEquals(true, instance.getRead());
            assertEquals(true, instance.getWrite());
            assertEquals(true, instance.getUpdate());
            assertEquals(true, instance.getEmployeeRead());
            assertEquals(false, instance.getEmployeeWrite());
            assertEquals(false, instance.getEmployeeUpdate());
            
            assertEquals(false, result.equals(instance.getRead()));
            assertEquals(false, result.equals(instance.getWrite()));
            assertEquals(false, result.equals(instance.getUpdate()));
            assertEquals(false, result.equals(instance.getEmployeeRead()));
            assertEquals(true, result.equals(instance.getEmployeeWrite()));
            assertEquals(true, result.equals(instance.getEmployeeUpdate()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeRef method, of class UserImpl.
     */
    @Test
    public void testGetEmployeeRef() {
        try {
            System.out.println("getEmployeeRef");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(1, instance.getEmployeeRef());
            assertEquals(false, instance.getEmployeeRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPersonRef method, of class UserImpl.
     */
    @Test
    public void testGetPersonRef() {
        try {
            System.out.println("getPersonRef");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(1, instance.getPersonRef());
            assertEquals(false, instance.getPersonRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getUsername method, of class UserImpl.
     */
    @Test
    public void testGetUsername() {
        try {
            System.out.println("getUsername");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals("DEDWARDS", instance.getUsername());
            assertEquals(false, instance.getUsername().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPassword method, of class UserImpl.
     */
    @Test
    public void testGetPassword() {
        try {
            System.out.println("getPassword");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals("TestPassword", instance.getPassword());
            assertEquals(false, instance.getPassword().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOfficeCode method, of class UserImpl.
     */
    @Test
    public void testGetOfficeCode() {
        try {
            System.out.println("getOfficeCode");
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals("TEST", instance.getOfficeCode());
            assertEquals(false, instance.getOfficeCode().equals("EDM"));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPasswordReset method, of class UserImpl.
     */
    @Test
    public void testGetPasswordReset() {
        try {
            System.out.println("getPasswordReset");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(true, instance.getPasswordReset());
            assertEquals(false, result.equals(instance.getPasswordReset()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRead method, of class UserImpl.
     */
    @Test
    public void testGetRead() {
        try {
            System.out.println("getRead");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getRead());
            assertEquals(true, result.equals(instance.getRead()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getWrite method, of class UserImpl.
     */
    @Test
    public void testGetWrite() {
        try {
            System.out.println("getWrite");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getWrite());
            assertEquals(true, result.equals(instance.getWrite()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getUpdate method, of class UserImpl.
     */
    @Test
    public void testGetUpdate() {
        try {
            System.out.println("getUpdate");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getUpdate());
            assertEquals(true, result.equals(instance.getUpdate()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeRead method, of class UserImpl.
     */
    @Test
    public void testGetEmployeeRead() {
        try {
            System.out.println("getEmployeeRead");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getEmployeeRead());
            assertEquals(true, result.equals(instance.getEmployeeRead()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeWrite method, of class UserImpl.
     */
    @Test
    public void testGetEmployeeWrite() {
        try {
            System.out.println("getEmployeeWrite");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getEmployeeWrite());
            assertEquals(true, result.equals(instance.getEmployeeWrite()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeUpdate method, of class UserImpl.
     */
    @Test
    public void testGetEmployeeUpdate() {
        try {
            System.out.println("getEmployeeUpdate");
            Boolean result = false;
            UserImpl instance = new UserImpl(1, 1, "DEDWARDS", "TestPassword", "TEST");
            
            assertEquals(false, instance.getEmployeeUpdate());
            assertEquals(true, result.equals(instance.getEmployeeUpdate()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
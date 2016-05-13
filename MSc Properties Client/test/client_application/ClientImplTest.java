/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;

import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.AgreementInterface;
import interfaces.ApplicationInterface;
import interfaces.Client;
import interfaces.ContractInterface;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import interfaces.User;
import java.io.File;
import java.util.Date;
import java.util.List;
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
public class ClientImplTest {
    
    public ClientImplTest() {
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
     * Test of main method, of class ClientImpl.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        ClientImpl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClient method, of class ClientImpl.
     */
    @Test
    public void testCreateClient() throws Exception {
        System.out.println("createClient");
        String[] args = null;
        Client expResult = null;
        Client result = ClientImpl.createClient(args);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetClient method, of class ClientImpl.
     */
    @Test
    public void testResetClient() throws Exception {
        System.out.println("resetClient");
        String[] args = null;
        boolean expResult = false;
        boolean result = ClientImpl.resetClient(args);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStub method, of class ClientImpl.
     */
    @Test
    public void testGetStub() throws Exception {
        System.out.println("getStub");
        ClientImpl instance = new ClientImpl();
        Client expResult = null;
        Client result = instance.getStub();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAlive method, of class ClientImpl.
     */
    @Test
    public void testIsAlive() throws Exception {
        System.out.println("isAlive");
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.isAlive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isServerSet method, of class ClientImpl.
     */
    @Test
    public void testIsServerSet() {
        System.out.println("isServerSet");
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.isServerSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUserSet method, of class ClientImpl.
     */
    @Test
    public void testIsUserSet() {
        System.out.println("isUserSet");
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.isUserSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class ClientImpl.
     */
    @Test
    public void testLogout() throws Exception {
        System.out.println("logout");
        ClientImpl instance = new ClientImpl();
        instance.logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeCode method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeCode() throws Exception {
        System.out.println("getOfficeCode");
        ClientImpl instance = new ClientImpl();
        String expResult = "";
        String result = instance.getOfficeCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class ClientImpl.
     */
    @Test
    public void testGetUsername() throws Exception {
        System.out.println("getUsername");
        ClientImpl instance = new ClientImpl();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class ClientImpl.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        ClientImpl instance = new ClientImpl();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUser method, of class ClientImpl.
     */
    @Test
    public void testIsUser() throws Exception {
        System.out.println("isUser");
        String username = "";
        String password = "";
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.isUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserTenancies method, of class ClientImpl.
     */
    @Test
    public void testUpdateUserTenancies() throws Exception {
        System.out.println("updateUserTenancies");
        List<AgreementInterface> agreements = null;
        ClientImpl instance = new ClientImpl();
        instance.updateUserTenancies(agreements);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserLeases method, of class ClientImpl.
     */
    @Test
    public void testUpdateUserLeases() throws Exception {
        System.out.println("updateUserLeases");
        List<AgreementInterface> agreements = null;
        ClientImpl instance = new ClientImpl();
        instance.updateUserLeases(agreements);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testUpdateUserRentAccounts() throws Exception {
        System.out.println("updateUserRentAccounts");
        List<AccountInterface> accounts = null;
        ClientImpl instance = new ClientImpl();
        instance.updateUserRentAccounts(accounts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTitle method, of class ClientImpl.
     */
    @Test
    public void testCreateTitle() throws Exception {
        System.out.println("createTitle");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createTitle(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTitle method, of class ClientImpl.
     */
    @Test
    public void testUpdateTitle() throws Exception {
        System.out.println("updateTitle");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateTitle(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTitle method, of class ClientImpl.
     */
    @Test
    public void testDeleteTitle() throws Exception {
        System.out.println("deleteTitle");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteTitle(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGender method, of class ClientImpl.
     */
    @Test
    public void testCreateGender() throws Exception {
        System.out.println("createGender");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createGender(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGender method, of class ClientImpl.
     */
    @Test
    public void testUpdateGender() throws Exception {
        System.out.println("updateGender");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateGender(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteGender method, of class ClientImpl.
     */
    @Test
    public void testDeleteGender() throws Exception {
        System.out.println("deleteGender");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteGender(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMaritalStatus method, of class ClientImpl.
     */
    @Test
    public void testCreateMaritalStatus() throws Exception {
        System.out.println("createMaritalStatus");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createMaritalStatus(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMaritalStatus method, of class ClientImpl.
     */
    @Test
    public void testUpdateMaritalStatus() throws Exception {
        System.out.println("updateMaritalStatus");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateMaritalStatus(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMaritalStatus method, of class ClientImpl.
     */
    @Test
    public void testDeleteMaritalStatus() throws Exception {
        System.out.println("deleteMaritalStatus");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteMaritalStatus(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEthnicOrigin method, of class ClientImpl.
     */
    @Test
    public void testCreateEthnicOrigin() throws Exception {
        System.out.println("createEthnicOrigin");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEthnicOrigin(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEthnicOrigin method, of class ClientImpl.
     */
    @Test
    public void testUpdateEthnicOrigin() throws Exception {
        System.out.println("updateEthnicOrigin");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEthnicOrigin(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEthnicOrigin method, of class ClientImpl.
     */
    @Test
    public void testDeleteEthnicOrigin() throws Exception {
        System.out.println("deleteEthnicOrigin");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEthnicOrigin(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLanguage method, of class ClientImpl.
     */
    @Test
    public void testCreateLanguage() throws Exception {
        System.out.println("createLanguage");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLanguage(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLanguage method, of class ClientImpl.
     */
    @Test
    public void testUpdateLanguage() throws Exception {
        System.out.println("updateLanguage");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLanguage(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLanguage method, of class ClientImpl.
     */
    @Test
    public void testDeleteLanguage() throws Exception {
        System.out.println("deleteLanguage");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLanguage(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNationality method, of class ClientImpl.
     */
    @Test
    public void testCreateNationality() throws Exception {
        System.out.println("createNationality");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createNationality(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateNationality method, of class ClientImpl.
     */
    @Test
    public void testUpdateNationality() throws Exception {
        System.out.println("updateNationality");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateNationality(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNationality method, of class ClientImpl.
     */
    @Test
    public void testDeleteNationality() throws Exception {
        System.out.println("deleteNationality");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteNationality(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSexuality method, of class ClientImpl.
     */
    @Test
    public void testCreateSexuality() throws Exception {
        System.out.println("createSexuality");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createSexuality(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSexuality method, of class ClientImpl.
     */
    @Test
    public void testUpdateSexuality() throws Exception {
        System.out.println("updateSexuality");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateSexuality(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSexuality method, of class ClientImpl.
     */
    @Test
    public void testDeleteSexuality() throws Exception {
        System.out.println("deleteSexuality");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteSexuality(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReligion method, of class ClientImpl.
     */
    @Test
    public void testCreateReligion() throws Exception {
        System.out.println("createReligion");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createReligion(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateReligion method, of class ClientImpl.
     */
    @Test
    public void testUpdateReligion() throws Exception {
        System.out.println("updateReligion");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateReligion(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteReligion method, of class ClientImpl.
     */
    @Test
    public void testDeleteReligion() throws Exception {
        System.out.println("deleteReligion");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteReligion(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertyType method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertyType() throws Exception {
        System.out.println("createPropertyType");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertyType(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertyType method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertyType() throws Exception {
        System.out.println("updatePropertyType");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertyType(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertyType method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertyType() throws Exception {
        System.out.println("deletePropertyType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertyType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertySubType method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertySubType() throws Exception {
        System.out.println("createPropertySubType");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertySubType(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertySubType method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertySubType() throws Exception {
        System.out.println("updatePropertySubType");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertySubType(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertySubType method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertySubType() throws Exception {
        System.out.println("deletePropertySubType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertySubType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertyElement method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertyElement_3args() throws Exception {
        System.out.println("createPropertyElement");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertyElement(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertyElement method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertyElement_4args() throws Exception {
        System.out.println("updatePropertyElement");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertyElement(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endPropertyElement method, of class ClientImpl.
     */
    @Test
    public void testEndPropertyElement() throws Exception {
        System.out.println("endPropertyElement");
        int eRef = 0;
        int pRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endPropertyElement(eRef, pRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertyElement method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertyElement_String() throws Exception {
        System.out.println("deletePropertyElement");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertyElement(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyElement method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyElement() throws Exception {
        System.out.println("getPropertyElement");
        int eRef = 0;
        ClientImpl instance = new ClientImpl();
        PropertyElementInterface expResult = null;
        PropertyElementInterface result = instance.getPropertyElement(eRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createContactType method, of class ClientImpl.
     */
    @Test
    public void testCreateContactType() throws Exception {
        System.out.println("createContactType");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createContactType(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateContactType method, of class ClientImpl.
     */
    @Test
    public void testUpdateContactType() throws Exception {
        System.out.println("updateContactType");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateContactType(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContactType method, of class ClientImpl.
     */
    @Test
    public void testDeleteContactType() throws Exception {
        System.out.println("deleteContactType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteContactType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEndReason method, of class ClientImpl.
     */
    @Test
    public void testCreateEndReason() throws Exception {
        System.out.println("createEndReason");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEndReason(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEndReason method, of class ClientImpl.
     */
    @Test
    public void testUpdateEndReason() throws Exception {
        System.out.println("updateEndReason");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEndReason(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEndReason method, of class ClientImpl.
     */
    @Test
    public void testDeleteEndReason() throws Exception {
        System.out.println("deleteEndReason");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEndReason(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRelationship method, of class ClientImpl.
     */
    @Test
    public void testCreateRelationship() throws Exception {
        System.out.println("createRelationship");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createRelationship(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRelationship method, of class ClientImpl.
     */
    @Test
    public void testUpdateRelationship() throws Exception {
        System.out.println("updateRelationship");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateRelationship(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRelationship method, of class ClientImpl.
     */
    @Test
    public void testDeleteRelationship() throws Exception {
        System.out.println("deleteRelationship");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteRelationship(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAddress method, of class ClientImpl.
     */
    @Test
    public void testCreateAddress() throws Exception {
        System.out.println("createAddress");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAddress method, of class ClientImpl.
     */
    @Test
    public void testUpdateAddress() throws Exception {
        System.out.println("updateAddress");
        int aRef = 0;
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateAddress(aRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAddress method, of class ClientImpl.
     */
    @Test
    public void testDeleteAddress() throws Exception {
        System.out.println("deleteAddress");
        int addrRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteAddress(addrRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPerson method, of class ClientImpl.
     */
    @Test
    public void testCreatePerson_15args() throws Exception {
        System.out.println("createPerson");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        int addrRef = 0;
        Date addressStartDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, addrRef, addressStartDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPerson method, of class ClientImpl.
     */
    @Test
    public void testCreatePerson_13args() throws Exception {
        System.out.println("createPerson");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePerson method, of class ClientImpl.
     */
    @Test
    public void testUpdatePerson() throws Exception {
        System.out.println("updatePerson");
        int pRef = 0;
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePerson(pRef, titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class ClientImpl.
     */
    @Test
    public void testDeletePerson() throws Exception {
        System.out.println("deletePerson");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePerson(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPersonNote method, of class ClientImpl.
     */
    @Test
    public void testCreatePersonNote() throws Exception {
        System.out.println("createPersonNote");
        int pRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPersonNote(pRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePersonNote method, of class ClientImpl.
     */
    @Test
    public void testUpdatePersonNote() throws Exception {
        System.out.println("updatePersonNote");
        int pRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePersonNote(pRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePersonNote method, of class ClientImpl.
     */
    @Test
    public void testDeletePersonNote() throws Exception {
        System.out.println("deletePersonNote");
        int pRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePersonNote(pRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPersonDocument method, of class ClientImpl.
     */
    @Test
    public void testCreatePersonDocument() throws Exception {
        System.out.println("createPersonDocument");
        int pRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPersonDocument(pRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePersonDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdatePersonDocument() throws Exception {
        System.out.println("updatePersonDocument");
        int pRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePersonDocument(pRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePersonDocument method, of class ClientImpl.
     */
    @Test
    public void testDeletePersonDocument() throws Exception {
        System.out.println("deletePersonDocument");
        int pRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePersonDocument(pRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadPersonDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadPersonDocument() throws Exception {
        System.out.println("downloadPersonDocument");
        int pRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadPersonDocument(pRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPersonContact method, of class ClientImpl.
     */
    @Test
    public void testCreatePersonContact() throws Exception {
        System.out.println("createPersonContact");
        int pRef = 0;
        String contactTypeCode = "";
        String value = "";
        Date date = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPersonContact(pRef, contactTypeCode, value, date, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePersonContact method, of class ClientImpl.
     */
    @Test
    public void testUpdatePersonContact() throws Exception {
        System.out.println("updatePersonContact");
        int pRef = 0;
        int cRef = 0;
        String contactTypeCode = "";
        String value = "";
        Date date = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePersonContact(pRef, cRef, contactTypeCode, value, date, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endPersonContact method, of class ClientImpl.
     */
    @Test
    public void testEndPersonContact() throws Exception {
        System.out.println("endPersonContact");
        int pRef = 0;
        int cRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endPersonContact(pRef, cRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePersonContact method, of class ClientImpl.
     */
    @Test
    public void testDeletePersonContact() throws Exception {
        System.out.println("deletePersonContact");
        int pRef = 0;
        int cRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePersonContact(pRef, cRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPersonAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testCreatePersonAddressUsage() throws Exception {
        System.out.println("createPersonAddressUsage");
        int pRef = 0;
        int addrRef = 0;
        Date startDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPersonAddressUsage(pRef, addrRef, startDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePersonAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testUpdatePersonAddressUsage() throws Exception {
        System.out.println("updatePersonAddressUsage");
        int pRef = 0;
        int addrUsageRef = 0;
        int addrRef = 0;
        Date startDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePersonAddressUsage(pRef, addrUsageRef, addrRef, startDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endPersonAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testEndPersonAddressUsage() throws Exception {
        System.out.println("endPersonAddressUsage");
        int pRef = 0;
        int aRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endPersonAddressUsage(pRef, aRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePersonAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testDeletePersonAddressUsage() throws Exception {
        System.out.println("deletePersonAddressUsage");
        int pRef = 0;
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePersonAddressUsage(pRef, aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOffice method, of class ClientImpl.
     */
    @Test
    public void testCreateOffice() throws Exception {
        System.out.println("createOffice");
        String officeCode = "";
        int addrRef = 0;
        Date startDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createOffice(officeCode, addrRef, startDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOffice method, of class ClientImpl.
     */
    @Test
    public void testUpdateOffice() throws Exception {
        System.out.println("updateOffice");
        String officeCode = "";
        Date startDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateOffice(officeCode, startDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endOffice method, of class ClientImpl.
     */
    @Test
    public void testEndOffice() throws Exception {
        System.out.println("endOffice");
        String officeCode = "";
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endOffice(officeCode, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOffice method, of class ClientImpl.
     */
    @Test
    public void testDeleteOffice() throws Exception {
        System.out.println("deleteOffice");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteOffice(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOfficeNote method, of class ClientImpl.
     */
    @Test
    public void testCreateOfficeNote() throws Exception {
        System.out.println("createOfficeNote");
        String officeCode = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createOfficeNote(officeCode, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOfficeNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateOfficeNote() throws Exception {
        System.out.println("updateOfficeNote");
        String officeCode = "";
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateOfficeNote(officeCode, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOfficeNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteOfficeNote() throws Exception {
        System.out.println("deleteOfficeNote");
        String officeCode = "";
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteOfficeNote(officeCode, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOfficeDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateOfficeDocument() throws Exception {
        System.out.println("createOfficeDocument");
        String officeCode = "";
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createOfficeDocument(officeCode, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOfficeDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateOfficeDocument() throws Exception {
        System.out.println("updateOfficeDocument");
        String oCode = "";
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateOfficeDocument(oCode, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOfficeDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteOfficeDocument() throws Exception {
        System.out.println("deleteOfficeDocument");
        String officeCode = "";
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteOfficeDocument(officeCode, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadOfficeDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadOfficeDocument() throws Exception {
        System.out.println("downloadOfficeDocument");
        String officeCode = "";
        int version = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadOfficeDocument(officeCode, version, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOfficeContact method, of class ClientImpl.
     */
    @Test
    public void testCreateOfficeContact() throws Exception {
        System.out.println("createOfficeContact");
        String oCode = "";
        String contactTypeCode = "";
        String value = "";
        Date date = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createOfficeContact(oCode, contactTypeCode, value, date, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOfficeContact method, of class ClientImpl.
     */
    @Test
    public void testUpdateOfficeContact() throws Exception {
        System.out.println("updateOfficeContact");
        String oCode = "";
        int cRef = 0;
        String contactTypeCode = "";
        String value = "";
        Date date = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateOfficeContact(oCode, cRef, contactTypeCode, value, date, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endOfficeContact method, of class ClientImpl.
     */
    @Test
    public void testEndOfficeContact() throws Exception {
        System.out.println("endOfficeContact");
        String oCode = "";
        int cRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endOfficeContact(oCode, cRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOfficeContact method, of class ClientImpl.
     */
    @Test
    public void testDeleteOfficeContact() throws Exception {
        System.out.println("deleteOfficeContact");
        String code = "";
        int cRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteOfficeContact(code, cRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testCreateInvolvedParty() throws Exception {
        System.out.println("createInvolvedParty");
        int pRef = 0;
        int aRef = 0;
        boolean joint = false;
        boolean main = false;
        Date start = null;
        String relationshipCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createInvolvedParty(pRef, aRef, joint, main, start, relationshipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testUpdateInvolvedParty() throws Exception {
        System.out.println("updateInvolvedParty");
        int iRef = 0;
        boolean joint = false;
        Date start = null;
        String relationshipCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateInvolvedParty(iRef, joint, start, relationshipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testDeleteInvolvedParty() throws Exception {
        System.out.println("deleteInvolvedParty");
        int iRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteInvolvedParty(iRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createInvolvedPartyNote method, of class ClientImpl.
     */
    @Test
    public void testCreateInvolvedPartyNote() throws Exception {
        System.out.println("createInvolvedPartyNote");
        int iRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createInvolvedPartyNote(iRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateInvolvedPartyNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateInvolvedPartyNote() throws Exception {
        System.out.println("updateInvolvedPartyNote");
        int eRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateInvolvedPartyNote(eRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteInvolvedPartyNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteInvolvedPartyNote() throws Exception {
        System.out.println("deleteInvolvedPartyNote");
        int iRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteInvolvedPartyNote(iRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createApplication method, of class ClientImpl.
     */
    @Test
    public void testCreateApplication() throws Exception {
        System.out.println("createApplication");
        String corrName = "";
        Date appStartDate = null;
        int pRef = 0;
        int addrRef = 0;
        Date addressStartDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createApplication(corrName, appStartDate, pRef, addrRef, addressStartDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateApplication method, of class ClientImpl.
     */
    @Test
    public void testUpdateApplication() throws Exception {
        System.out.println("updateApplication");
        int aRef = 0;
        String corrName = "";
        Date appStartDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateApplication(aRef, corrName, appStartDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteApplication method, of class ClientImpl.
     */
    @Test
    public void testDeleteApplication() throws Exception {
        System.out.println("deleteApplication");
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteApplication(aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testAddInvolvedParty() throws Exception {
        System.out.println("addInvolvedParty");
        int aRef = 0;
        int pRef = 0;
        boolean joint = false;
        Date start = null;
        String relationshipCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.addInvolvedParty(aRef, pRef, joint, start, relationshipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeMainApp method, of class ClientImpl.
     */
    @Test
    public void testChangeMainApp() throws Exception {
        System.out.println("changeMainApp");
        int aRef = 0;
        int iRef = 0;
        Date end = null;
        String endReasonCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.changeMainApp(aRef, iRef, end, endReasonCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testEndInvolvedParty() throws Exception {
        System.out.println("endInvolvedParty");
        int aRef = 0;
        int iRef = 0;
        Date end = null;
        String endReasonCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endInvolvedParty(aRef, iRef, end, endReasonCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addInterestedProperty method, of class ClientImpl.
     */
    @Test
    public void testAddInterestedProperty() throws Exception {
        System.out.println("addInterestedProperty");
        int aRef = 0;
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.addInterestedProperty(aRef, pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endInterestInProperty method, of class ClientImpl.
     */
    @Test
    public void testEndInterestInProperty() throws Exception {
        System.out.println("endInterestInProperty");
        int aRef = 0;
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endInterestInProperty(aRef, pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createApplicationNote method, of class ClientImpl.
     */
    @Test
    public void testCreateApplicationNote() throws Exception {
        System.out.println("createApplicationNote");
        int aRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createApplicationNote(aRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateApplicationNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateApplicationNote() throws Exception {
        System.out.println("updateApplicationNote");
        int aRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateApplicationNote(aRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteApplicationNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteApplicationNote() throws Exception {
        System.out.println("deleteApplicationNote");
        int aRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteApplicationNote(aRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createApplicationDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateApplicationDocument() throws Exception {
        System.out.println("createApplicationDocument");
        int aRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createApplicationDocument(aRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateApplicationDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateApplicationDocument() throws Exception {
        System.out.println("updateApplicationDocument");
        int aRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateApplicationDocument(aRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteApplicationDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteApplicationDocument() throws Exception {
        System.out.println("deleteApplicationDocument");
        int aRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteApplicationDocument(aRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadApplicationDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadApplicationDocument() throws Exception {
        System.out.println("downloadApplicationDocument");
        int aRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadApplicationDocument(aRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateApplicationAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testUpdateApplicationAddressUsage() throws Exception {
        System.out.println("updateApplicationAddressUsage");
        int aRef = 0;
        int addrUsageRef = 0;
        int addrRef = 0;
        Date startDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateApplicationAddressUsage(aRef, addrUsageRef, addrRef, startDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endApplicationAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testEndApplicationAddressUsage() throws Exception {
        System.out.println("endApplicationAddressUsage");
        int addrRef = 0;
        int aRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endApplicationAddressUsage(addrRef, aRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteApplicationAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testDeleteApplicationAddressUsage() throws Exception {
        System.out.println("deleteApplicationAddressUsage");
        int addrRef = 0;
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteApplicationAddressUsage(addrRef, aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createApplicationAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testCreateApplicationAddressUsage() throws Exception {
        System.out.println("createApplicationAddressUsage");
        int applicationRef = 0;
        int addrRef = 0;
        Date startDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createApplicationAddressUsage(applicationRef, addrRef, startDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmployee method, of class ClientImpl.
     */
    @Test
    public void testCreateEmployee() throws Exception {
        System.out.println("createEmployee");
        int pRef = 0;
        String username = "";
        String password = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEmployee(pRef, username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployeeMemorableLocation method, of class ClientImpl.
     */
    @Test
    public void testSetEmployeeMemorableLocation() throws Exception {
        System.out.println("setEmployeeMemorableLocation");
        String memorableLocation = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.setEmployeeMemorableLocation(memorableLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployeePassword method, of class ClientImpl.
     */
    @Test
    public void testUpdateEmployeePassword_String() throws Exception {
        System.out.println("updateEmployeePassword");
        String password = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEmployeePassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployeePassword method, of class ClientImpl.
     */
    @Test
    public void testUpdateEmployeePassword_int_String() throws Exception {
        System.out.println("updateEmployeePassword");
        int empRef = 0;
        String password = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEmployeePassword(empRef, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployee method, of class ClientImpl.
     */
    @Test
    public void testDeleteEmployee() throws Exception {
        System.out.println("deleteEmployee");
        int eRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEmployee(eRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmployeeNote method, of class ClientImpl.
     */
    @Test
    public void testCreateEmployeeNote() throws Exception {
        System.out.println("createEmployeeNote");
        int eRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEmployeeNote(eRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployeeNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateEmployeeNote() throws Exception {
        System.out.println("updateEmployeeNote");
        int eRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEmployeeNote(eRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployeeNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteEmployeeNote() throws Exception {
        System.out.println("deleteEmployeeNote");
        int eRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEmployeeNote(eRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLandlord method, of class ClientImpl.
     */
    @Test
    public void testCreateLandlord() throws Exception {
        System.out.println("createLandlord");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLandlord(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLandlord method, of class ClientImpl.
     */
    @Test
    public void testDeleteLandlord() throws Exception {
        System.out.println("deleteLandlord");
        int lRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLandlord(lRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLandlordNote method, of class ClientImpl.
     */
    @Test
    public void testCreateLandlordNote() throws Exception {
        System.out.println("createLandlordNote");
        int lRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLandlordNote(lRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLandlordNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateLandlordNote() throws Exception {
        System.out.println("updateLandlordNote");
        int lRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLandlordNote(lRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLandlordNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteLandlordNote() throws Exception {
        System.out.println("deleteLandlordNote");
        int lRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLandlordNote(lRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProperty method, of class ClientImpl.
     */
    @Test
    public void testCreateProperty() throws Exception {
        System.out.println("createProperty");
        int addrRef = 0;
        Date startDate = null;
        String propTypeCode = "";
        String propSubTypeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createProperty(addrRef, startDate, propTypeCode, propSubTypeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProperty method, of class ClientImpl.
     */
    @Test
    public void testUpdateProperty() throws Exception {
        System.out.println("updateProperty");
        int pRef = 0;
        int addrRef = 0;
        Date startDate = null;
        String propTypeCode = "";
        String propSubTypeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateProperty(pRef, addrRef, startDate, propTypeCode, propSubTypeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProperty method, of class ClientImpl.
     */
    @Test
    public void testDeleteProperty() throws Exception {
        System.out.println("deleteProperty");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteProperty(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertyNote method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertyNote() throws Exception {
        System.out.println("createPropertyNote");
        int pRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertyNote(pRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertyNote method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertyNote() throws Exception {
        System.out.println("updatePropertyNote");
        int pRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertyNote(pRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertyNote method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertyNote() throws Exception {
        System.out.println("deletePropertyNote");
        int pRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertyNote(pRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertyDocument method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertyDocument() throws Exception {
        System.out.println("createPropertyDocument");
        int pRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertyDocument(pRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertyDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertyDocument() throws Exception {
        System.out.println("updatePropertyDocument");
        int pRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertyDocument(pRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertyDocument method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertyDocument() throws Exception {
        System.out.println("deletePropertyDocument");
        int pRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertyDocument(pRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadPropertyDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadPropertyDocument() throws Exception {
        System.out.println("downloadPropertyDocument");
        int pRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadPropertyDocument(pRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPropertyElement method, of class ClientImpl.
     */
    @Test
    public void testCreatePropertyElement_7args() throws Exception {
        System.out.println("createPropertyElement");
        int pRef = 0;
        String elementCode = "";
        Date startDate = null;
        boolean charge = false;
        String stringValue = "";
        Double doubleValue = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createPropertyElement(pRef, elementCode, startDate, charge, stringValue, doubleValue, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePropertyElement method, of class ClientImpl.
     */
    @Test
    public void testUpdatePropertyElement_7args() throws Exception {
        System.out.println("updatePropertyElement");
        int eRef = 0;
        int pRef = 0;
        Date startDate = null;
        String stringValue = "";
        Double doubleValue = null;
        boolean charge = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updatePropertyElement(eRef, pRef, startDate, stringValue, doubleValue, charge, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePropertyElement method, of class ClientImpl.
     */
    @Test
    public void testDeletePropertyElement_int_int() throws Exception {
        System.out.println("deletePropertyElement");
        int eRef = 0;
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deletePropertyElement(eRef, pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobRole method, of class ClientImpl.
     */
    @Test
    public void testCreateJobRole() throws Exception {
        System.out.println("createJobRole");
        String code = "";
        String jobTitle = "";
        String jobDescription = "";
        boolean fullTime = false;
        double salary = 0.0;
        boolean read = false;
        boolean write = false;
        boolean update = false;
        boolean delete = false;
        boolean employeeRead = false;
        boolean employeeWrite = false;
        boolean employeeUpdate = false;
        boolean employeeDelete = false;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateJobRole method, of class ClientImpl.
     */
    @Test
    public void testUpdateJobRole() throws Exception {
        System.out.println("updateJobRole");
        String code = "";
        String jobTitle = "";
        String jobDescription = "";
        boolean fullTime = false;
        double salary = 0.0;
        boolean current = false;
        boolean read = false;
        boolean write = false;
        boolean update = false;
        boolean delete = false;
        boolean employeeRead = false;
        boolean employeeWrite = false;
        boolean employeeUpdate = false;
        boolean employeeDelete = false;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateJobRole(code, jobTitle, jobDescription, fullTime, salary, current, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobRole method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobRole() throws Exception {
        System.out.println("deleteJobRole");
        String officeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobRole(officeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobRoleNote method, of class ClientImpl.
     */
    @Test
    public void testCreateJobRoleNote() throws Exception {
        System.out.println("createJobRoleNote");
        String officeCode = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobRoleNote(officeCode, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateJobRoleNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateJobRoleNote() throws Exception {
        System.out.println("updateJobRoleNote");
        String officeCode = "";
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateJobRoleNote(officeCode, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobRoleNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobRoleNote() throws Exception {
        System.out.println("deleteJobRoleNote");
        String officeCode = "";
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobRoleNote(officeCode, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobRoleRequirement method, of class ClientImpl.
     */
    @Test
    public void testCreateJobRoleRequirement() throws Exception {
        System.out.println("createJobRoleRequirement");
        String jobRoleCode = "";
        String requirement = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobRoleRequirement(jobRoleCode, requirement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobRoleRequirement method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobRoleRequirement() throws Exception {
        System.out.println("deleteJobRoleRequirement");
        String jobRoleCode = "";
        String requirement = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobRoleRequirement(jobRoleCode, requirement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobRoleBenefit method, of class ClientImpl.
     */
    @Test
    public void testCreateJobRoleBenefit() throws Exception {
        System.out.println("createJobRoleBenefit");
        String jobRoleCode = "";
        String benefit = "";
        Date startDate = null;
        boolean salaryBenefit = false;
        String stringValue = "";
        double doubleValue = 0.0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobRoleBenefit(jobRoleCode, benefit, startDate, salaryBenefit, stringValue, doubleValue, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateJobRoleBenefit method, of class ClientImpl.
     */
    @Test
    public void testUpdateJobRoleBenefit() throws Exception {
        System.out.println("updateJobRoleBenefit");
        int benefitRef = 0;
        String jobRoleCode = "";
        Date startDate = null;
        boolean salaryBenefit = false;
        String stringValue = "";
        double doubleValue = 0.0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateJobRoleBenefit(benefitRef, jobRoleCode, startDate, salaryBenefit, stringValue, doubleValue, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endJobRoleBenefit method, of class ClientImpl.
     */
    @Test
    public void testEndJobRoleBenefit() throws Exception {
        System.out.println("endJobRoleBenefit");
        int benefitRef = 0;
        String jobRoleCode = "";
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endJobRoleBenefit(benefitRef, jobRoleCode, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobRoleBenefit method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobRoleBenefit() throws Exception {
        System.out.println("deleteJobRoleBenefit");
        String jobRoleCode = "";
        int benefit = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobRoleBenefit(jobRoleCode, benefit);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRoleBenefit method, of class ClientImpl.
     */
    @Test
    public void testGetJobRoleBenefit() throws Exception {
        System.out.println("getJobRoleBenefit");
        int jobRoleBenefitRef = 0;
        ClientImpl instance = new ClientImpl();
        JobRoleBenefitInterface expResult = null;
        JobRoleBenefitInterface result = instance.getJobRoleBenefit(jobRoleBenefitRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobRequirement method, of class ClientImpl.
     */
    @Test
    public void testCreateJobRequirement() throws Exception {
        System.out.println("createJobRequirement");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobRequirement(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateJobRequirement method, of class ClientImpl.
     */
    @Test
    public void testUpdateJobRequirement() throws Exception {
        System.out.println("updateJobRequirement");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateJobRequirement(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobRequirement method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobRequirement() throws Exception {
        System.out.println("deleteJobRequirement");
        String requirement = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobRequirement(requirement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createJobBenefit method, of class ClientImpl.
     */
    @Test
    public void testCreateJobBenefit() throws Exception {
        System.out.println("createJobBenefit");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createJobBenefit(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateJobBenefit method, of class ClientImpl.
     */
    @Test
    public void testUpdateJobBenefit() throws Exception {
        System.out.println("updateJobBenefit");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateJobBenefit(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteJobBenefit method, of class ClientImpl.
     */
    @Test
    public void testDeleteJobBenefit() throws Exception {
        System.out.println("deleteJobBenefit");
        String benefit = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteJobBenefit(benefit);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTenancy method, of class ClientImpl.
     */
    @Test
    public void testCreateTenancy() throws Exception {
        System.out.println("createTenancy");
        Date startDate = null;
        int length = 0;
        int pRef = 0;
        int aRef = 0;
        String tenTypeCode = "";
        String officeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createTenancy(startDate, length, pRef, aRef, tenTypeCode, officeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTenancy method, of class ClientImpl.
     */
    @Test
    public void testUpdateTenancy() throws Exception {
        System.out.println("updateTenancy");
        int tRef = 0;
        String name = "";
        Date startDate = null;
        int length = 0;
        String tenTypeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateTenancy(tRef, name, startDate, length, tenTypeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endTenancy method, of class ClientImpl.
     */
    @Test
    public void testEndTenancy() throws Exception {
        System.out.println("endTenancy");
        int tRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endTenancy(tRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTenancy method, of class ClientImpl.
     */
    @Test
    public void testDeleteTenancy() throws Exception {
        System.out.println("deleteTenancy");
        int tRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteTenancy(tRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTenancyNote method, of class ClientImpl.
     */
    @Test
    public void testCreateTenancyNote() throws Exception {
        System.out.println("createTenancyNote");
        int tRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createTenancyNote(tRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTenancyNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateTenancyNote() throws Exception {
        System.out.println("updateTenancyNote");
        int tRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateTenancyNote(tRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTenancyNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteTenancyNote() throws Exception {
        System.out.println("deleteTenancyNote");
        int tRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteTenancyNote(tRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTenancyDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateTenancyDocument() throws Exception {
        System.out.println("createTenancyDocument");
        int tRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createTenancyDocument(tRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTenancyDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateTenancyDocument() throws Exception {
        System.out.println("updateTenancyDocument");
        int tRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateTenancyDocument(tRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTenancyDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteTenancyDocument() throws Exception {
        System.out.println("deleteTenancyDocument");
        int tRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteTenancyDocument(tRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadTenancyDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadTenancyDocument() throws Exception {
        System.out.println("downloadTenancyDocument");
        int tRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadTenancyDocument(tRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTenancyType method, of class ClientImpl.
     */
    @Test
    public void testCreateTenancyType() throws Exception {
        System.out.println("createTenancyType");
        String code = "";
        String description = "";
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createTenancyType(code, description, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTenancyType method, of class ClientImpl.
     */
    @Test
    public void testUpdateTenancyType() throws Exception {
        System.out.println("updateTenancyType");
        String code = "";
        String description = "";
        boolean current = false;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateTenancyType(code, description, current, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTenancyType method, of class ClientImpl.
     */
    @Test
    public void testDeleteTenancyType() throws Exception {
        System.out.println("deleteTenancyType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteTenancyType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLease method, of class ClientImpl.
     */
    @Test
    public void testCreateLease() throws Exception {
        System.out.println("createLease");
        Date startDate = null;
        int length = 0;
        int pRef = 0;
        boolean management = false;
        double expenditure = 0.0;
        String officeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLease(startDate, length, pRef, management, expenditure, officeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLease method, of class ClientImpl.
     */
    @Test
    public void testUpdateLease() throws Exception {
        System.out.println("updateLease");
        int lRef = 0;
        String name = "";
        Date startDate = null;
        int length = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLease(lRef, name, startDate, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endLease method, of class ClientImpl.
     */
    @Test
    public void testEndLease() throws Exception {
        System.out.println("endLease");
        int lRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endLease(lRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLease method, of class ClientImpl.
     */
    @Test
    public void testDeleteLease() throws Exception {
        System.out.println("deleteLease");
        int lRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLease(lRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseNote method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseNote() throws Exception {
        System.out.println("createLeaseNote");
        int lRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseNote(lRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaseNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateLeaseNote() throws Exception {
        System.out.println("updateLeaseNote");
        int lRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLeaseNote(lRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaseNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteLeaseNote() throws Exception {
        System.out.println("deleteLeaseNote");
        int lRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLeaseNote(lRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseDocument() throws Exception {
        System.out.println("createLeaseDocument");
        int lRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseDocument(lRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaseDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateLeaseDocument() throws Exception {
        System.out.println("updateLeaseDocument");
        int lRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLeaseDocument(lRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaseDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteLeaseDocument() throws Exception {
        System.out.println("deleteLeaseDocument");
        int lRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLeaseDocument(lRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadLeaseDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadLeaseDocument() throws Exception {
        System.out.println("downloadLeaseDocument");
        int lRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadLeaseDocument(lRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseLandlord method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseLandlord() throws Exception {
        System.out.println("createLeaseLandlord");
        int lRef = 0;
        int landRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseLandlord(lRef, landRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endLeaseLandlord method, of class ClientImpl.
     */
    @Test
    public void testEndLeaseLandlord() throws Exception {
        System.out.println("endLeaseLandlord");
        int lRef = 0;
        int landRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endLeaseLandlord(lRef, landRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createContract method, of class ClientImpl.
     */
    @Test
    public void testCreateContract() throws Exception {
        System.out.println("createContract");
        Date startDate = null;
        int length = 0;
        int eRef = 0;
        String jobRoleCode = "";
        String officeCode = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createContract(startDate, length, eRef, jobRoleCode, officeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateContract method, of class ClientImpl.
     */
    @Test
    public void testUpdateContract() throws Exception {
        System.out.println("updateContract");
        int cRef = 0;
        String name = "";
        Date startDate = null;
        int length = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateContract(cRef, name, startDate, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endContract method, of class ClientImpl.
     */
    @Test
    public void testEndContract() throws Exception {
        System.out.println("endContract");
        int cRef = 0;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.endContract(cRef, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContract method, of class ClientImpl.
     */
    @Test
    public void testDeleteContract() throws Exception {
        System.out.println("deleteContract");
        int cRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteContract(cRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createContractNote method, of class ClientImpl.
     */
    @Test
    public void testCreateContractNote() throws Exception {
        System.out.println("createContractNote");
        int cRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createContractNote(cRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateContractNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateContractNote() throws Exception {
        System.out.println("updateContractNote");
        int cRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateContractNote(cRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContractNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteContractNote() throws Exception {
        System.out.println("deleteContractNote");
        int cRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteContractNote(cRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createContractDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateContractDocument() throws Exception {
        System.out.println("createContractDocument");
        int cRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createContractDocument(cRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateContractDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateContractDocument() throws Exception {
        System.out.println("updateContractDocument");
        int cRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateContractDocument(cRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContractDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteContractDocument() throws Exception {
        System.out.println("deleteContractDocument");
        int cRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteContractDocument(cRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadContractDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadContractDocument() throws Exception {
        System.out.println("downloadContractDocument");
        int cRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadContractDocument(cRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRentAccNote method, of class ClientImpl.
     */
    @Test
    public void testCreateRentAccNote() throws Exception {
        System.out.println("createRentAccNote");
        int rAccRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createRentAccNote(rAccRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRentAccNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateRentAccNote() throws Exception {
        System.out.println("updateRentAccNote");
        int rAccRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateRentAccNote(rAccRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRentAccNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteRentAccNote() throws Exception {
        System.out.println("deleteRentAccNote");
        int rAccRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteRentAccNote(rAccRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRentAccDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateRentAccDocument() throws Exception {
        System.out.println("createRentAccDocument");
        int rAccRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createRentAccDocument(rAccRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRentAccDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateRentAccDocument() throws Exception {
        System.out.println("updateRentAccDocument");
        int rAccRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateRentAccDocument(rAccRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRentAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteRentAccDocument() throws Exception {
        System.out.println("deleteRentAccDocument");
        int rAccRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteRentAccDocument(rAccRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadRentAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadRentAccDocument() throws Exception {
        System.out.println("downloadRentAccDocument");
        int rAccRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadRentAccDocument(rAccRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseAccNote method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseAccNote() throws Exception {
        System.out.println("createLeaseAccNote");
        int lAccRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseAccNote(lAccRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaseAccNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateLeaseAccNote() throws Exception {
        System.out.println("updateLeaseAccNote");
        int lAccRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLeaseAccNote(lAccRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaseAccNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteLeaseAccNote() throws Exception {
        System.out.println("deleteLeaseAccNote");
        int lAccRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLeaseAccNote(lAccRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseAccDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseAccDocument() throws Exception {
        System.out.println("createLeaseAccDocument");
        int lAccRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseAccDocument(lAccRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaseAccDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateLeaseAccDocument() throws Exception {
        System.out.println("updateLeaseAccDocument");
        int lAccRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateLeaseAccDocument(lAccRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaseAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteLeaseAccDocument() throws Exception {
        System.out.println("deleteLeaseAccDocument");
        int lAccRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLeaseAccDocument(lAccRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadLeaseAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadLeaseAccDocument() throws Exception {
        System.out.println("downloadLeaseAccDocument");
        int lAccRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadLeaseAccDocument(lAccRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmployeeAccNote method, of class ClientImpl.
     */
    @Test
    public void testCreateEmployeeAccNote() throws Exception {
        System.out.println("createEmployeeAccNote");
        int eAccRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEmployeeAccNote(eAccRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployeeAccNote method, of class ClientImpl.
     */
    @Test
    public void testUpdateEmployeeAccNote() throws Exception {
        System.out.println("updateEmployeeAccNote");
        int eAccRef = 0;
        int nRef = 0;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEmployeeAccNote(eAccRef, nRef, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployeeAccNote method, of class ClientImpl.
     */
    @Test
    public void testDeleteEmployeeAccNote() throws Exception {
        System.out.println("deleteEmployeeAccNote");
        int eAccRef = 0;
        int nRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEmployeeAccNote(eAccRef, nRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmployeeAccDocument method, of class ClientImpl.
     */
    @Test
    public void testCreateEmployeeAccDocument() throws Exception {
        System.out.println("createEmployeeAccDocument");
        int eAccRef = 0;
        File fileName = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEmployeeAccDocument(eAccRef, fileName, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployeeAccDocument method, of class ClientImpl.
     */
    @Test
    public void testUpdateEmployeeAccDocument() throws Exception {
        System.out.println("updateEmployeeAccDocument");
        int eAccRef = 0;
        int dRef = 0;
        File fileName = null;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.updateEmployeeAccDocument(eAccRef, dRef, fileName, "");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployeeAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDeleteEmployeeAccDocument() throws Exception {
        System.out.println("deleteEmployeeAccDocument");
        int eAccRef = 0;
        int dRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEmployeeAccDocument(eAccRef, dRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadEmployeeAccDocument method, of class ClientImpl.
     */
    @Test
    public void testDownloadEmployeeAccDocument() throws Exception {
        System.out.println("downloadEmployeeAccDocument");
        int eAccRef = 0;
        int dRef = 0;
        int version = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.downloadEmployeeAccDocument(eAccRef, dRef, version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRentAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testCreateRentAccTransaction() throws Exception {
        System.out.println("createRentAccTransaction");
        int rAccRef = 0;
        int fromRef = 0;
        int toRef = 0;
        double amount = 0.0;
        boolean debit = false;
        Date transactionDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createRentAccTransaction(rAccRef, fromRef, toRef, amount, debit, transactionDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRentAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testDeleteRentAccTransaction() throws Exception {
        System.out.println("deleteRentAccTransaction");
        int tRef = 0;
        int rAccRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteRentAccTransaction(tRef, rAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLeaseAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testCreateLeaseAccTransaction() throws Exception {
        System.out.println("createLeaseAccTransaction");
        int lAccRef = 0;
        int fromRef = 0;
        int toRef = 0;
        double amount = 0.0;
        boolean debit = false;
        Date transactionDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createLeaseAccTransaction(lAccRef, fromRef, toRef, amount, debit, transactionDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaseAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testDeleteLeaseAccTransaction() throws Exception {
        System.out.println("deleteLeaseAccTransaction");
        int tRef = 0;
        int lAccRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteLeaseAccTransaction(tRef, lAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmployeeAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testCreateEmployeeAccTransaction() throws Exception {
        System.out.println("createEmployeeAccTransaction");
        int eAccRef = 0;
        int fromRef = 0;
        int toRef = 0;
        double amount = 0.0;
        boolean debit = false;
        Date transactionDate = null;
        String comment = "";
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.createEmployeeAccTransaction(eAccRef, fromRef, toRef, amount, debit, transactionDate, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployeeAccTransaction method, of class ClientImpl.
     */
    @Test
    public void testDeleteEmployeeAccTransaction() throws Exception {
        System.out.println("deleteEmployeeAccTransaction");
        int tRef = 0;
        int eAccRef = 0;
        ClientImpl instance = new ClientImpl();
        int expResult = 0;
        int result = instance.deleteEmployeeAccTransaction(tRef, eAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class ClientImpl.
     */
    @Test
    public void testGetTitle() throws Exception {
        System.out.println("getTitle");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getTitle(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitles method, of class ClientImpl.
     */
    @Test
    public void testGetTitles() throws Exception {
        System.out.println("getTitles");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getTitles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentTitles method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentTitles() throws Exception {
        System.out.println("getCurrentTitles");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentTitles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGender method, of class ClientImpl.
     */
    @Test
    public void testGetGender() throws Exception {
        System.out.println("getGender");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getGender(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenders method, of class ClientImpl.
     */
    @Test
    public void testGetGenders() throws Exception {
        System.out.println("getGenders");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getGenders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentGenders method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentGenders() throws Exception {
        System.out.println("getCurrentGenders");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentGenders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaritalStatus method, of class ClientImpl.
     */
    @Test
    public void testGetMaritalStatus() throws Exception {
        System.out.println("getMaritalStatus");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getMaritalStatus(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaritalStatuses method, of class ClientImpl.
     */
    @Test
    public void testGetMaritalStatuses() throws Exception {
        System.out.println("getMaritalStatuses");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getMaritalStatuses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentMaritalStatuses method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentMaritalStatuses() throws Exception {
        System.out.println("getCurrentMaritalStatuses");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentMaritalStatuses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEthnicOrigin method, of class ClientImpl.
     */
    @Test
    public void testGetEthnicOrigin() throws Exception {
        System.out.println("getEthnicOrigin");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getEthnicOrigin(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEthnicOrigins method, of class ClientImpl.
     */
    @Test
    public void testGetEthnicOrigins() throws Exception {
        System.out.println("getEthnicOrigins");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getEthnicOrigins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentEthnicOrigins method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentEthnicOrigins() throws Exception {
        System.out.println("getCurrentEthnicOrigins");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentEthnicOrigins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLanguage method, of class ClientImpl.
     */
    @Test
    public void testGetLanguage() throws Exception {
        System.out.println("getLanguage");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getLanguage(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLanguages method, of class ClientImpl.
     */
    @Test
    public void testGetLanguages() throws Exception {
        System.out.println("getLanguages");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getLanguages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentLanguages method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentLanguages() throws Exception {
        System.out.println("getCurrentLanguages");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentLanguages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNationality method, of class ClientImpl.
     */
    @Test
    public void testGetNationality() throws Exception {
        System.out.println("getNationality");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getNationality(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNationalities method, of class ClientImpl.
     */
    @Test
    public void testGetNationalities() throws Exception {
        System.out.println("getNationalities");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getNationalities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentNationalities method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentNationalities() throws Exception {
        System.out.println("getCurrentNationalities");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentNationalities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSexuality method, of class ClientImpl.
     */
    @Test
    public void testGetSexuality() throws Exception {
        System.out.println("getSexuality");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getSexuality(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSexualities method, of class ClientImpl.
     */
    @Test
    public void testGetSexualities() throws Exception {
        System.out.println("getSexualities");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getSexualities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentSexualities method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentSexualities() throws Exception {
        System.out.println("getCurrentSexualities");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentSexualities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReligion method, of class ClientImpl.
     */
    @Test
    public void testGetReligion() throws Exception {
        System.out.println("getReligion");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getReligion(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReligions method, of class ClientImpl.
     */
    @Test
    public void testGetReligions() throws Exception {
        System.out.println("getReligions");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getReligions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentReligions method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentReligions() throws Exception {
        System.out.println("getCurrentReligions");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentReligions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyType method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyType() throws Exception {
        System.out.println("getPropertyType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getPropertyType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyTypes method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyTypes() throws Exception {
        System.out.println("getPropertyTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getPropertyTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPropertyTypes method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentPropertyTypes() throws Exception {
        System.out.println("getCurrentPropertyTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentPropertyTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertySubType method, of class ClientImpl.
     */
    @Test
    public void testGetPropertySubType() throws Exception {
        System.out.println("getPropertySubType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getPropertySubType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertySubTypes method, of class ClientImpl.
     */
    @Test
    public void testGetPropertySubTypes() throws Exception {
        System.out.println("getPropertySubTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getPropertySubTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPropertySubTypes method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentPropertySubTypes() throws Exception {
        System.out.println("getCurrentPropertySubTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentPropertySubTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropElement method, of class ClientImpl.
     */
    @Test
    public void testGetPropElement() throws Exception {
        System.out.println("getPropElement");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getPropElement(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropElements method, of class ClientImpl.
     */
    @Test
    public void testGetPropElements() throws Exception {
        System.out.println("getPropElements");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getPropElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPropElements method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentPropElements() throws Exception {
        System.out.println("getCurrentPropElements");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentPropElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContactType method, of class ClientImpl.
     */
    @Test
    public void testGetContactType() throws Exception {
        System.out.println("getContactType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getContactType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContactTypes method, of class ClientImpl.
     */
    @Test
    public void testGetContactTypes() throws Exception {
        System.out.println("getContactTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getContactTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentContactTypes method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentContactTypes() throws Exception {
        System.out.println("getCurrentContactTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentContactTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndReason method, of class ClientImpl.
     */
    @Test
    public void testGetEndReason() throws Exception {
        System.out.println("getEndReason");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getEndReason(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndReasons method, of class ClientImpl.
     */
    @Test
    public void testGetEndReasons() throws Exception {
        System.out.println("getEndReasons");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getEndReasons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentEndReasons method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentEndReasons() throws Exception {
        System.out.println("getCurrentEndReasons");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentEndReasons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelationship method, of class ClientImpl.
     */
    @Test
    public void testGetRelationship() throws Exception {
        System.out.println("getRelationship");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getRelationship(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelationships method, of class ClientImpl.
     */
    @Test
    public void testGetRelationships() throws Exception {
        System.out.println("getRelationships");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getRelationships();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentRelationships method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentRelationships() throws Exception {
        System.out.println("getCurrentRelationships");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentRelationships();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobBenefit method, of class ClientImpl.
     */
    @Test
    public void testGetJobBenefit() throws Exception {
        System.out.println("getJobBenefit");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getJobBenefit(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobBenefits method, of class ClientImpl.
     */
    @Test
    public void testGetJobBenefits() throws Exception {
        System.out.println("getJobBenefits");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getJobBenefits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentJobBenefits method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentJobBenefits() throws Exception {
        System.out.println("getCurrentJobBenefits");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentJobBenefits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRequirement method, of class ClientImpl.
     */
    @Test
    public void testGetJobRequirement() throws Exception {
        System.out.println("getJobRequirement");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getJobRequirement(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRequirements method, of class ClientImpl.
     */
    @Test
    public void testGetJobRequirements() throws Exception {
        System.out.println("getJobRequirements");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getJobRequirements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentJobRequirements method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentJobRequirements() throws Exception {
        System.out.println("getCurrentJobRequirements");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentJobRequirements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenancyType method, of class ClientImpl.
     */
    @Test
    public void testGetTenancyType() throws Exception {
        System.out.println("getTenancyType");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Element expResult = null;
        Element result = instance.getTenancyType(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenancyTypes method, of class ClientImpl.
     */
    @Test
    public void testGetTenancyTypes() throws Exception {
        System.out.println("getTenancyTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getTenancyTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentTenancyTypes method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentTenancyTypes() throws Exception {
        System.out.println("getCurrentTenancyTypes");
        ClientImpl instance = new ClientImpl();
        List<Element> expResult = null;
        List<Element> result = instance.getCurrentTenancyTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffices method, of class ClientImpl.
     */
    @Test
    public void testGetOffices_0args() throws Exception {
        System.out.println("getOffices");
        ClientImpl instance = new ClientImpl();
        List<OfficeInterface> expResult = null;
        List<OfficeInterface> result = instance.getOffices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentOffices method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentOffices() throws Exception {
        System.out.println("getCurrentOffices");
        ClientImpl instance = new ClientImpl();
        List<OfficeInterface> expResult = null;
        List<OfficeInterface> result = instance.getCurrentOffices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddresses method, of class ClientImpl.
     */
    @Test
    public void testGetAddresses_0args() throws Exception {
        System.out.println("getAddresses");
        ClientImpl instance = new ClientImpl();
        List<AddressInterface> expResult = null;
        List<AddressInterface> result = instance.getAddresses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class ClientImpl.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        ClientImpl instance = new ClientImpl();
        List<User> expResult = null;
        List<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLandlords method, of class ClientImpl.
     */
    @Test
    public void testGetLandlords() throws Exception {
        System.out.println("getLandlords");
        ClientImpl instance = new ClientImpl();
        List<LandlordInterface> expResult = null;
        List<LandlordInterface> result = instance.getLandlords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployees method, of class ClientImpl.
     */
    @Test
    public void testGetEmployees() throws Exception {
        System.out.println("getEmployees");
        ClientImpl instance = new ClientImpl();
        List<EmployeeInterface> expResult = null;
        List<EmployeeInterface> result = instance.getEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentEmployees method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentEmployees() throws Exception {
        System.out.println("getCurrentEmployees");
        ClientImpl instance = new ClientImpl();
        List<EmployeeInterface> expResult = null;
        List<EmployeeInterface> result = instance.getCurrentEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRoles method, of class ClientImpl.
     */
    @Test
    public void testGetJobRoles() throws Exception {
        System.out.println("getJobRoles");
        ClientImpl instance = new ClientImpl();
        List<JobRoleInterface> expResult = null;
        List<JobRoleInterface> result = instance.getJobRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProperties method, of class ClientImpl.
     */
    @Test
    public void testGetProperties_0args() throws Exception {
        System.out.println("getProperties");
        ClientImpl instance = new ClientImpl();
        List<PropertyInterface> expResult = null;
        List<PropertyInterface> result = instance.getProperties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentJobRoles method, of class ClientImpl.
     */
    @Test
    public void testGetCurrentJobRoles() throws Exception {
        System.out.println("getCurrentJobRoles");
        ClientImpl instance = new ClientImpl();
        List<JobRoleInterface> expResult = null;
        List<JobRoleInterface> result = instance.getCurrentJobRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of titleExists method, of class ClientImpl.
     */
    @Test
    public void testTitleExists() throws Exception {
        System.out.println("titleExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.titleExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of genderExists method, of class ClientImpl.
     */
    @Test
    public void testGenderExists() throws Exception {
        System.out.println("genderExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.genderExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maritalStatusExists method, of class ClientImpl.
     */
    @Test
    public void testMaritalStatusExists() throws Exception {
        System.out.println("maritalStatusExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.maritalStatusExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ethnicOriginExists method, of class ClientImpl.
     */
    @Test
    public void testEthnicOriginExists() throws Exception {
        System.out.println("ethnicOriginExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.ethnicOriginExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of languageExists method, of class ClientImpl.
     */
    @Test
    public void testLanguageExists() throws Exception {
        System.out.println("languageExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.languageExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nationalityExists method, of class ClientImpl.
     */
    @Test
    public void testNationalityExists() throws Exception {
        System.out.println("nationalityExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.nationalityExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sexualityExists method, of class ClientImpl.
     */
    @Test
    public void testSexualityExists() throws Exception {
        System.out.println("sexualityExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.sexualityExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of religionExists method, of class ClientImpl.
     */
    @Test
    public void testReligionExists() throws Exception {
        System.out.println("religionExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.religionExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contactTypeExists method, of class ClientImpl.
     */
    @Test
    public void testContactTypeExists() throws Exception {
        System.out.println("contactTypeExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.contactTypeExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propTypeExists method, of class ClientImpl.
     */
    @Test
    public void testPropTypeExists() throws Exception {
        System.out.println("propTypeExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.propTypeExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propSubTypeExists method, of class ClientImpl.
     */
    @Test
    public void testPropSubTypeExists() throws Exception {
        System.out.println("propSubTypeExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.propSubTypeExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propElementExists method, of class ClientImpl.
     */
    @Test
    public void testPropElementExists() throws Exception {
        System.out.println("propElementExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.propElementExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endReasonExists method, of class ClientImpl.
     */
    @Test
    public void testEndReasonExists() throws Exception {
        System.out.println("endReasonExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.endReasonExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of relationshipExists method, of class ClientImpl.
     */
    @Test
    public void testRelationshipExists() throws Exception {
        System.out.println("relationshipExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.relationshipExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jobRoleRequirementExists method, of class ClientImpl.
     */
    @Test
    public void testJobRoleRequirementExists() throws Exception {
        System.out.println("jobRoleRequirementExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.jobRoleRequirementExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jobRoleBenefitExists method, of class ClientImpl.
     */
    @Test
    public void testJobRoleBenefitExists() throws Exception {
        System.out.println("jobRoleBenefitExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.jobRoleBenefitExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tenancyTypeExists method, of class ClientImpl.
     */
    @Test
    public void testTenancyTypeExists() throws Exception {
        System.out.println("tenancyTypeExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.tenancyTypeExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of officeExists method, of class ClientImpl.
     */
    @Test
    public void testOfficeExists() throws Exception {
        System.out.println("officeExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.officeExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jobRoleExists method, of class ClientImpl.
     */
    @Test
    public void testJobRoleExists() throws Exception {
        System.out.println("jobRoleExists");
        String code = "";
        ClientImpl instance = new ClientImpl();
        Boolean expResult = null;
        Boolean result = instance.jobRoleExists(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of personExists method, of class ClientImpl.
     */
    @Test
    public void testPersonExists() throws Exception {
        System.out.println("personExists");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.personExists(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of personEmployeeExists method, of class ClientImpl.
     */
    @Test
    public void testPersonEmployeeExists() throws Exception {
        System.out.println("personEmployeeExists");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.personEmployeeExists(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of personLandlordExists method, of class ClientImpl.
     */
    @Test
    public void testPersonLandlordExists() throws Exception {
        System.out.println("personLandlordExists");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.personLandlordExists(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addressExists method, of class ClientImpl.
     */
    @Test
    public void testAddressExists() throws Exception {
        System.out.println("addressExists");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        boolean expResult = false;
        boolean result = instance.addressExists(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class ClientImpl.
     */
    @Test
    public void testGetAddress() throws Exception {
        System.out.println("getAddress");
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        AddressInterface expResult = null;
        AddressInterface result = instance.getAddress(aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressUsage method, of class ClientImpl.
     */
    @Test
    public void testGetAddressUsage() throws Exception {
        System.out.println("getAddressUsage");
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        AddressUsageInterface expResult = null;
        AddressUsageInterface result = instance.getAddressUsage(aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRole method, of class ClientImpl.
     */
    @Test
    public void testGetJobRole() throws Exception {
        System.out.println("getJobRole");
        String jobRoleCode = "";
        ClientImpl instance = new ClientImpl();
        JobRoleInterface expResult = null;
        JobRoleInterface result = instance.getJobRole(jobRoleCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffice method, of class ClientImpl.
     */
    @Test
    public void testGetOffice() throws Exception {
        System.out.println("getOffice");
        String officeCode = "";
        ClientImpl instance = new ClientImpl();
        OfficeInterface expResult = null;
        OfficeInterface result = instance.getOffice(officeCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvolvedParty method, of class ClientImpl.
     */
    @Test
    public void testGetInvolvedParty() throws Exception {
        System.out.println("getInvolvedParty");
        int iRef = 0;
        ClientImpl instance = new ClientImpl();
        InvolvedPartyInterface expResult = null;
        InvolvedPartyInterface result = instance.getInvolvedParty(iRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplication method, of class ClientImpl.
     */
    @Test
    public void testGetApplication() throws Exception {
        System.out.println("getApplication");
        int aRef = 0;
        ClientImpl instance = new ClientImpl();
        ApplicationInterface expResult = null;
        ApplicationInterface result = instance.getApplication(aRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProperty method, of class ClientImpl.
     */
    @Test
    public void testGetProperty() throws Exception {
        System.out.println("getProperty");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        PropertyInterface expResult = null;
        PropertyInterface result = instance.getProperty(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class ClientImpl.
     */
    @Test
    public void testGetPerson() throws Exception {
        System.out.println("getPerson");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        PersonInterface expResult = null;
        PersonInterface result = instance.getPerson(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetEmployee() throws Exception {
        System.out.println("getEmployee");
        int eRef = 0;
        ClientImpl instance = new ClientImpl();
        EmployeeInterface expResult = null;
        EmployeeInterface result = instance.getEmployee(eRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetPersonEmployee() throws Exception {
        System.out.println("getPersonEmployee");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        EmployeeInterface expResult = null;
        EmployeeInterface result = instance.getPersonEmployee(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLandlord method, of class ClientImpl.
     */
    @Test
    public void testGetLandlord() throws Exception {
        System.out.println("getLandlord");
        int lRef = 0;
        ClientImpl instance = new ClientImpl();
        LandlordInterface expResult = null;
        LandlordInterface result = instance.getLandlord(lRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonLandlord method, of class ClientImpl.
     */
    @Test
    public void testGetPersonLandlord() throws Exception {
        System.out.println("getPersonLandlord");
        int pRef = 0;
        ClientImpl instance = new ClientImpl();
        LandlordInterface expResult = null;
        LandlordInterface result = instance.getPersonLandlord(pRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenancy method, of class ClientImpl.
     */
    @Test
    public void testGetTenancy() throws Exception {
        System.out.println("getTenancy");
        int tRef = 0;
        ClientImpl instance = new ClientImpl();
        TenancyInterface expResult = null;
        TenancyInterface result = instance.getTenancy(tRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLease method, of class ClientImpl.
     */
    @Test
    public void testGetLease() throws Exception {
        System.out.println("getLease");
        int lRef = 0;
        ClientImpl instance = new ClientImpl();
        LeaseInterface expResult = null;
        LeaseInterface result = instance.getLease(lRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContract method, of class ClientImpl.
     */
    @Test
    public void testGetContract() throws Exception {
        System.out.println("getContract");
        int cRef = 0;
        ClientImpl instance = new ClientImpl();
        ContractInterface expResult = null;
        ContractInterface result = instance.getContract(cRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRentAccount method, of class ClientImpl.
     */
    @Test
    public void testGetRentAccount() throws Exception {
        System.out.println("getRentAccount");
        int rAccRef = 0;
        ClientImpl instance = new ClientImpl();
        RentAccountInterface expResult = null;
        RentAccountInterface result = instance.getRentAccount(rAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeaseAccount method, of class ClientImpl.
     */
    @Test
    public void testGetLeaseAccount() throws Exception {
        System.out.println("getLeaseAccount");
        int lAccRef = 0;
        ClientImpl instance = new ClientImpl();
        LeaseAccountInterface expResult = null;
        LeaseAccountInterface result = instance.getLeaseAccount(lAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeAccount method, of class ClientImpl.
     */
    @Test
    public void testGetEmployeeAccount() throws Exception {
        System.out.println("getEmployeeAccount");
        int eAccRef = 0;
        ClientImpl instance = new ClientImpl();
        EmployeeAccountInterface expResult = null;
        EmployeeAccountInterface result = instance.getEmployeeAccount(eAccRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeople method, of class ClientImpl.
     */
    @Test
    public void testGetPeople() throws Exception {
        System.out.println("getPeople");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<PersonInterface> expResult = null;
        List<PersonInterface> result = instance.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddresses method, of class ClientImpl.
     */
    @Test
    public void testGetAddresses_12args() throws Exception {
        System.out.println("getAddresses");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<AddressInterface> expResult = null;
        List<AddressInterface> result = instance.getAddresses(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplications method, of class ClientImpl.
     */
    @Test
    public void testGetApplications() throws Exception {
        System.out.println("getApplications");
        String corrName = "";
        Date appStartDate = null;
        Date endDate = null;
        String statusCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<ApplicationInterface> expResult = null;
        List<ApplicationInterface> result = instance.getApplications(corrName, appStartDate, endDate, statusCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeopleApplications method, of class ClientImpl.
     */
    @Test
    public void testGetPeopleApplications() throws Exception {
        System.out.println("getPeopleApplications");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<ApplicationInterface> expResult = null;
        List<ApplicationInterface> result = instance.getPeopleApplications(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressApplications method, of class ClientImpl.
     */
    @Test
    public void testGetAddressApplications() throws Exception {
        System.out.println("getAddressApplications");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<ApplicationInterface> expResult = null;
        List<ApplicationInterface> result = instance.getAddressApplications(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorrNameApplications method, of class ClientImpl.
     */
    @Test
    public void testGetCorrNameApplications() throws Exception {
        System.out.println("getCorrNameApplications");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<ApplicationInterface> expResult = null;
        List<ApplicationInterface> result = instance.getCorrNameApplications(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvPartyApplication method, of class ClientImpl.
     */
    @Test
    public void testGetInvPartyApplication() throws Exception {
        System.out.println("getInvPartyApplication");
        int iPartyRef = 0;
        ClientImpl instance = new ClientImpl();
        ApplicationInterface expResult = null;
        ApplicationInterface result = instance.getInvPartyApplication(iPartyRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeopleEmployees method, of class ClientImpl.
     */
    @Test
    public void testGetPeopleEmployees() throws Exception {
        System.out.println("getPeopleEmployees");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<EmployeeInterface> expResult = null;
        List<EmployeeInterface> result = instance.getPeopleEmployees(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeopleLandlords method, of class ClientImpl.
     */
    @Test
    public void testGetPeopleLandlords() throws Exception {
        System.out.println("getPeopleLandlords");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LandlordInterface> expResult = null;
        List<LandlordInterface> result = instance.getPeopleLandlords(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProperties method, of class ClientImpl.
     */
    @Test
    public void testGetProperties_7args() throws Exception {
        System.out.println("getProperties");
        Date acquiredDate = null;
        Date leaseEndDate = null;
        String propTypeCode = "";
        String propSubTypeCode = "";
        String propStatus = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<PropertyInterface> expResult = null;
        List<PropertyInterface> result = instance.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressProperties method, of class ClientImpl.
     */
    @Test
    public void testGetAddressProperties() throws Exception {
        System.out.println("getAddressProperties");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<PropertyInterface> expResult = null;
        List<PropertyInterface> result = instance.getAddressProperties(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetTenancies() throws Exception {
        System.out.println("getTenancies");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer propRef = null;
        Integer appRef = null;
        String tenTypeCode = "";
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getTenancies(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplicationTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetApplicationTenancies_6args() throws Exception {
        System.out.println("getApplicationTenancies");
        String corrName = "";
        Date appStartDate = null;
        Date endDate = null;
        String statusCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getApplicationTenancies(corrName, appStartDate, endDate, statusCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplicationTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetApplicationTenancies_int() throws Exception {
        System.out.println("getApplicationTenancies");
        int appRef = 0;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getApplicationTenancies(appRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyTenancies_7args() throws Exception {
        System.out.println("getPropertyTenancies");
        Date acquiredDate = null;
        Date leaseEndDate = null;
        String propTypeCode = "";
        String propSubTypeCode = "";
        String propStatus = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getPropertyTenancies(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetAddressTenancies() throws Exception {
        System.out.println("getAddressTenancies");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getAddressTenancies(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyTenancies_int() throws Exception {
        System.out.println("getPropertyTenancies");
        int propRef = 0;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getPropertyTenancies(propRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetNameTenancies() throws Exception {
        System.out.println("getNameTenancies");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getNameTenancies(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeTenancies() throws Exception {
        System.out.println("getOfficeTenancies");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getOfficeTenancies(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeases method, of class ClientImpl.
     */
    @Test
    public void testGetLeases() throws Exception {
        System.out.println("getLeases");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer propRef = null;
        Boolean management = null;
        Double expenditure = null;
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getLeases(name, startDate, expectedEndDate, endDate, length, propRef, management, expenditure, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyLeases method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyLeases_7args() throws Exception {
        System.out.println("getPropertyLeases");
        Date acquiredDate = null;
        Date leaseEndDate = null;
        String propTypeCode = "";
        String propSubTypeCode = "";
        String propStatus = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getPropertyLeases(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressLeases method, of class ClientImpl.
     */
    @Test
    public void testGetAddressLeases() throws Exception {
        System.out.println("getAddressLeases");
        String buildingNumber = "";
        String buildingName = "";
        String subStreetNumber = "";
        String subStreet = "";
        String streetNumber = "";
        String street = "";
        String area = "";
        String town = "";
        String country = "";
        String postcode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getAddressLeases(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyLeases method, of class ClientImpl.
     */
    @Test
    public void testGetPropertyLeases_int() throws Exception {
        System.out.println("getPropertyLeases");
        int propRef = 0;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getPropertyLeases(propRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameLeases method, of class ClientImpl.
     */
    @Test
    public void testGetNameLeases() throws Exception {
        System.out.println("getNameLeases");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getNameLeases(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeLeases method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeLeases() throws Exception {
        System.out.println("getOfficeLeases");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getOfficeLeases(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLandlordLeases method, of class ClientImpl.
     */
    @Test
    public void testGetLandlordLeases_int() throws Exception {
        System.out.println("getLandlordLeases");
        int landlordRef = 0;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getLandlordLeases(landlordRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLandlordLeases method, of class ClientImpl.
     */
    @Test
    public void testGetLandlordLeases_15args() throws Exception {
        System.out.println("getLandlordLeases");
        String titleCode = "";
        String forename = "";
        String middleNames = "";
        String surname = "";
        Date dateOfBirth = null;
        String nationalInsurance = "";
        String genderCode = "";
        String maritalStatusCode = "";
        String ethnicOriginCode = "";
        String languageCode = "";
        String nationalityCode = "";
        String sexualityCode = "";
        String religionCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getLandlordLeases(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContracts method, of class ClientImpl.
     */
    @Test
    public void testGetContracts() throws Exception {
        System.out.println("getContracts");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer employeeRef = null;
        String jobRoleCode = "";
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getContracts(name, startDate, expectedEndDate, endDate, length, employeeRef, jobRoleCode, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameContracts method, of class ClientImpl.
     */
    @Test
    public void testGetNameContracts() throws Exception {
        System.out.println("getNameContracts");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getNameContracts(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeContracts method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeContracts() throws Exception {
        System.out.println("getOfficeContracts");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getOfficeContracts(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeContracts method, of class ClientImpl.
     */
    @Test
    public void testGetEmployeeContracts() throws Exception {
        System.out.println("getEmployeeContracts");
        int ref = 0;
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getEmployeeContracts(ref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetRentAccounts() throws Exception {
        System.out.println("getRentAccounts");
        String name = "";
        Date startDate = null;
        Date endDate = null;
        Integer balance = null;
        Double rent = null;
        Integer agreementRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getRentAccounts(name, startDate, endDate, balance, rent, agreementRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameRentAcc method, of class ClientImpl.
     */
    @Test
    public void testGetNameRentAcc() throws Exception {
        System.out.println("getNameRentAcc");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getNameRentAcc(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetPropRentAccounts() throws Exception {
        System.out.println("getPropRentAccounts");
        int propRef = 0;
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getPropRentAccounts(propRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplicationRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetApplicationRentAccounts() throws Exception {
        System.out.println("getApplicationRentAccounts");
        int propRef = 0;
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getApplicationRentAccounts(propRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeRentAcc method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeRentAcc() throws Exception {
        System.out.println("getOfficeRentAcc");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getOfficeRentAcc(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenanciesRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetTenanciesRentAccounts() throws Exception {
        System.out.println("getTenanciesRentAccounts");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer propRef = null;
        Integer appRef = null;
        String tenTypeCode = "";
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<RentAccountInterface> expResult = null;
        List<RentAccountInterface> result = instance.getTenanciesRentAccounts(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenancyRentAcc method, of class ClientImpl.
     */
    @Test
    public void testGetTenancyRentAcc() throws Exception {
        System.out.println("getTenancyRentAcc");
        int tenancyRef = 0;
        ClientImpl instance = new ClientImpl();
        RentAccountInterface expResult = null;
        RentAccountInterface result = instance.getTenancyRentAcc(tenancyRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeaseAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetLeaseAccounts() throws Exception {
        System.out.println("getLeaseAccounts");
        String name = "";
        Date startDate = null;
        Date endDate = null;
        Integer balance = null;
        Double expenditure = null;
        Integer agreementRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getLeaseAccounts(name, startDate, endDate, balance, expenditure, agreementRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameLeaseAcc method, of class ClientImpl.
     */
    @Test
    public void testGetNameLeaseAcc() throws Exception {
        System.out.println("getNameLeaseAcc");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getNameLeaseAcc(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropLeaseAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetPropLeaseAccounts() throws Exception {
        System.out.println("getPropLeaseAccounts");
        int propRef = 0;
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getPropLeaseAccounts(propRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLandlordLeaseAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetLandlordLeaseAccounts() throws Exception {
        System.out.println("getLandlordLeaseAccounts");
        int landlordRef = 0;
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getLandlordLeaseAccounts(landlordRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeLeaseAcc method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeLeaseAcc() throws Exception {
        System.out.println("getOfficeLeaseAcc");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getOfficeLeaseAcc(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeasesLeaseAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetLeasesLeaseAccounts() throws Exception {
        System.out.println("getLeasesLeaseAccounts");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer propertyRef = null;
        Boolean management = null;
        Double expenditure = null;
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseAccountInterface> expResult = null;
        List<LeaseAccountInterface> result = instance.getLeasesLeaseAccounts(name, startDate, expectedEndDate, endDate, length, propertyRef, management, expenditure, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeaseLeaseAcc method, of class ClientImpl.
     */
    @Test
    public void testGetLeaseLeaseAcc() throws Exception {
        System.out.println("getLeaseLeaseAcc");
        int leaseRef = 0;
        ClientImpl instance = new ClientImpl();
        LeaseAccountInterface expResult = null;
        LeaseAccountInterface result = instance.getLeaseLeaseAcc(leaseRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetEmployeeAccounts() throws Exception {
        System.out.println("getEmployeeAccounts");
        String name = "";
        Date startDate = null;
        Date endDate = null;
        Integer balance = null;
        Double salary = null;
        Integer agreementRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<EmployeeAccountInterface> expResult = null;
        List<EmployeeAccountInterface> result = instance.getEmployeeAccounts(name, startDate, endDate, balance, salary, agreementRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameEmployeeAcc method, of class ClientImpl.
     */
    @Test
    public void testGetNameEmployeeAcc() throws Exception {
        System.out.println("getNameEmployeeAcc");
        String name = "";
        ClientImpl instance = new ClientImpl();
        List<EmployeeAccountInterface> expResult = null;
        List<EmployeeAccountInterface> result = instance.getNameEmployeeAcc(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobRoleEmployeeAcc method, of class ClientImpl.
     */
    @Test
    public void testGetJobRoleEmployeeAcc() throws Exception {
        System.out.println("getJobRoleEmployeeAcc");
        String jobRoleCode = "";
        ClientImpl instance = new ClientImpl();
        List<EmployeeAccountInterface> expResult = null;
        List<EmployeeAccountInterface> result = instance.getJobRoleEmployeeAcc(jobRoleCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfficeEmployeeAcc method, of class ClientImpl.
     */
    @Test
    public void testGetOfficeEmployeeAcc() throws Exception {
        System.out.println("getOfficeEmployeeAcc");
        String office = "";
        ClientImpl instance = new ClientImpl();
        List<EmployeeAccountInterface> expResult = null;
        List<EmployeeAccountInterface> result = instance.getOfficeEmployeeAcc(office);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractsEmployeeAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetContractsEmployeeAccounts() throws Exception {
        System.out.println("getContractsEmployeeAccounts");
        String name = "";
        Date startDate = null;
        Date expectedEndDate = null;
        Date endDate = null;
        Integer length = null;
        Integer employeeRef = null;
        String jobRoleCode = "";
        Integer accountRef = null;
        String officeCode = "";
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<EmployeeAccountInterface> expResult = null;
        List<EmployeeAccountInterface> result = instance.getContractsEmployeeAccounts(name, startDate, expectedEndDate, endDate, length, employeeRef, jobRoleCode, accountRef, officeCode, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractEmployeeAcc method, of class ClientImpl.
     */
    @Test
    public void testGetContractEmployeeAcc() throws Exception {
        System.out.println("getContractEmployeeAcc");
        int contractRef = 0;
        ClientImpl instance = new ClientImpl();
        EmployeeAccountInterface expResult = null;
        EmployeeAccountInterface result = instance.getContractEmployeeAcc(contractRef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffices method, of class ClientImpl.
     */
    @Test
    public void testGetOffices_4args() throws Exception {
        System.out.println("getOffices");
        Integer addrRef = null;
        Date startDate = null;
        String createdBy = "";
        Date createdDate = null;
        ClientImpl instance = new ClientImpl();
        List<OfficeInterface> expResult = null;
        List<OfficeInterface> result = instance.getOffices(addrRef, startDate, createdBy, createdDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenanciesByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetTenanciesByEmployee() throws Exception {
        System.out.println("getTenanciesByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getTenanciesByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfTenanciesByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfTenanciesByEmployee() throws Exception {
        System.out.println("getNumberOfTenanciesByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfTenanciesByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenanciesByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetTenanciesByOffice() throws Exception {
        System.out.println("getTenanciesByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<TenancyInterface> expResult = null;
        List<TenancyInterface> result = instance.getTenanciesByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfTenanciesByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfTenanciesByOffice() throws Exception {
        System.out.println("getNumberOfTenanciesByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfTenanciesByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeasesByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetLeasesByEmployee() throws Exception {
        System.out.println("getLeasesByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getLeasesByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfLeasesByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfLeasesByEmployee() throws Exception {
        System.out.println("getNumberOfLeasesByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfLeasesByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeasesByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetLeasesByOffice() throws Exception {
        System.out.println("getLeasesByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<LeaseInterface> expResult = null;
        List<LeaseInterface> result = instance.getLeasesByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfLeasesByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfLeasesByOffice() throws Exception {
        System.out.println("getNumberOfLeasesByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfLeasesByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractsByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetContractsByEmployee() throws Exception {
        System.out.println("getContractsByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getContractsByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfContractsByEmployee method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfContractsByEmployee() throws Exception {
        System.out.println("getNumberOfContractsByEmployee");
        int eRef = 0;
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfContractsByEmployee(eRef, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractsByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetContractsByOffice() throws Exception {
        System.out.println("getContractsByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        List<ContractInterface> expResult = null;
        List<ContractInterface> result = instance.getContractsByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfContractsByOffice method, of class ClientImpl.
     */
    @Test
    public void testGetNumberOfContractsByOffice() throws Exception {
        System.out.println("getNumberOfContractsByOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Integer expResult = null;
        Integer result = instance.getNumberOfContractsByOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRevenueForOffice method, of class ClientImpl.
     */
    @Test
    public void testGetRevenueForOffice() throws Exception {
        System.out.println("getRevenueForOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getRevenueForOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpenditureForOffice method, of class ClientImpl.
     */
    @Test
    public void testGetExpenditureForOffice() throws Exception {
        System.out.println("getExpenditureForOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getExpenditureForOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfitForOffice method, of class ClientImpl.
     */
    @Test
    public void testGetProfitForOffice() throws Exception {
        System.out.println("getProfitForOffice");
        String officeCode = "";
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getProfitForOffice(officeCode, startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRevenueOverall method, of class ClientImpl.
     */
    @Test
    public void testGetRevenueOverall() throws Exception {
        System.out.println("getRevenueOverall");
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getRevenueOverall(startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpenditureOverall method, of class ClientImpl.
     */
    @Test
    public void testGetExpenditureOverall() throws Exception {
        System.out.println("getExpenditureOverall");
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getExpenditureOverall(startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfitOverall method, of class ClientImpl.
     */
    @Test
    public void testGetProfitOverall() throws Exception {
        System.out.println("getProfitOverall");
        Date startDate = null;
        Date endDate = null;
        ClientImpl instance = new ClientImpl();
        Double expResult = null;
        Double result = instance.getProfitOverall(startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserTenancies method, of class ClientImpl.
     */
    @Test
    public void testGetUserTenancies() throws Exception {
        System.out.println("getUserTenancies");
        ClientImpl instance = new ClientImpl();
        List<AgreementInterface> expResult = null;
        List<AgreementInterface> result = instance.getUserTenancies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserLeases method, of class ClientImpl.
     */
    @Test
    public void testGetUserLeases() throws Exception {
        System.out.println("getUserLeases");
        ClientImpl instance = new ClientImpl();
        List<AgreementInterface> expResult = null;
        List<AgreementInterface> result = instance.getUserLeases();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserRentAccounts method, of class ClientImpl.
     */
    @Test
    public void testGetUserRentAccounts() throws Exception {
        System.out.println("getUserRentAccounts");
        ClientImpl instance = new ClientImpl();
        List<AccountInterface> expResult = null;
        List<AccountInterface> result = instance.getUserRentAccounts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.ModifiedByInterface;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Contact;
import server_application.Database;
import server_application.ElementImpl;
import server_application.JobRole;
import server_application.JobRoleBenefit;
import server_application.ModifiedBy;
import server_application.Person;

/**
 *
 * @author Dwayne
 */
public class TestDatabase {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Running database test");
        
        Database db = new Database("LIVE", "127.0.0.1", "root", "Toxic9489!999", 3306);
        
        ElementImpl title = new ElementImpl("ERR", "Error", "DEDWARDS", new Date());
        
        JobRole jobRole = new JobRole("MNGR", "Manager", "Managerial Duties", true, 29000.00, true, true, true, true, true, true, "DEDWARDS", new Date());
        
        ModifiedBy modTest = new ModifiedBy("TEST", new Date(), "DEDWARDS");
        
        JobRoleBenefit benefit = new JobRoleBenefit(1, db.getJobBenefit(title.getCode()), new Date(), true, null, 29.00, "DEDWARDS", new Date());
        
        Address address = new Address(1, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", new Date());
        
        Person person = new Person(1, title, "Dwayne", "Leroy", "Edwards", new Date(), "JL 81 61 90 C", title, title, title, title, title, title, title, new AddressUsage(1, db.getAddress(1), new Date(), "DEDWARDS", new Date()), "DEDWARDS", new Date());
        
        Contact contact = new Contact(1, title, "test@test.com", new Date(), "DEDWARDS", new Date());
        
        try {
            
            // System Elements
            db.createContactType(title);
            db.createEndReason(title);
            db.createEthnicOrigin(title);
            db.createGender(title);
            db.createJobBenefit(title);
            db.createJobRequirement(title);
            db.createLanguage(title);
            db.createMaritalStatus(title);
            db.createNationality(title);
            db.createPropElement(title);
            db.createPropertySubType(title);
            db.createPropertyType(title);
            db.createRelationship(title);
            db.createReligion(title);
            db.createSexuality(title);
            db.createTenancyType(title);
            db.createTitle(title);
            
            // Job Role Methods  -- Tested commented Job Role methods
//            db.createJobRole(jobRole);
            jobRole = db.getJobRole("MNGR");
//            jobRole.updateJobRole(jobRole.getJobTitle(), jobRole.getJobDescription(), 30000.00, true, true, true, true, true, true, false, modTest);
//            db.updateJobRole(jobRole.getJobRoleCode());
//            jobRole.createJobRequirement(db.getJobRequirement(title.getCode()), modTest);
//            jobRole.createJobBenefit(benefit, modTest);
//            db.createJobRoleRequirement(jobRole.getJobRoleCode(), title.getCode());
//            db.createJobRoleBenefit(jobRole.getJobRoleCode(), benefit);
            System.out.println("Job Role Requirements: " + jobRole.getJobRequirements());
            System.out.println("Job Role Benefits: " + jobRole.getBenefits());
            
            // Address Methods -- Tested commented Address methods
//            db.createAddress(address);
            address = db.getAddress(1);
//            address.updateAddress("", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", modTest);
//            db.updateAddress(address.getAddressRef());
            System.out.println("Address: " + address.printAddress());
            
            // Person methods -- Tested commented Person methods
//            db.createPerson(person);
            person = db.getPerson(1);
//            person.updatePerson(title, "DwayneUPDATED", "Leroy", "Edwards", new Date(), "UPDATED", title, title, title, title, title, title, title, modTest);
//            db.updatePerson(person.getPersonRef());
//            person.createAddress(new AddressUsage(1, db.getAddress(1), new Date(), "DEDWARDS", new Date()), null);
//            db.createPersonAddressUsage((AddressUsage) person.getLastAddress(), person.getPersonRef());
//            person.createContact(contact, modTest);
//            db.createPersonContact(contact, person.getPersonRef());
            System.out.println(person.toString());
            System.out.println("Person Contacts: " + person.getContacts().size());
            System.out.println("Person Addresses: " + person.getAddresses().size());            
            
            // Office methods -- Tested commented Office methods
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        ModifiedByInterface modTest = new ModifiedBy("DEDWARDS", new Date(), "Amended Title Description");
//        if(title!=null) {
//            title.updateElement("MR - Amended", true, modTest);
//            System.out.println("\n*******************************\n");
//            System.out.println(title.getModifiedBy());
//        }
        
        db.disconnect();
    }
}
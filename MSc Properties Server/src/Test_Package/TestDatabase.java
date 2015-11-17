/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Application;
import server_application.Contact;
import server_application.Database;
import server_application.ElementImpl;
import server_application.Employee;
import server_application.InvolvedParty;
import server_application.JobRole;
import server_application.JobRoleBenefit;
import server_application.Landlord;
import server_application.ModifiedBy;
import server_application.Office;
import server_application.Person;
import server_application.Property;
import server_application.PropertyElement;
import server_application.RentAccount;
import server_application.Tenancy;

/**
 *
 * @author Dwayne
 */
public class TestDatabase {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Running database test");
        
        Date date = new Date();
        date.setMonth(0);
        date.setDate(1);
        
        Database db = new Database("LIVE", "127.0.0.1", "root", "Toxic9489!999", 3306);
        
        ElementImpl element = new ElementImpl("ERR", "Error", "DEDWARDS", new Date());
        ElementImpl element2 = new ElementImpl("TEST", "Test", "DEDWARDS", new Date());
        
        JobRole jobRole = new JobRole("MNGR", "Manager", "Managerial Duties", true, 29000.00, true, true, true, true, true, true, "DEDWARDS", new Date());
        
        ModifiedBy modTest = new ModifiedBy("TEST", new Date(), "DEDWARDS");
        
        JobRoleBenefit benefit = new JobRoleBenefit(1, db.getJobBenefit(element.getCode()), new Date(), true, null, 29.00, "DEDWARDS", new Date());
        
        Address address = new Address(1, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", new Date());
        Address address2 = new Address(2, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", new Date());
        Address address3 = new Address(3, "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", new Date());
        
        AddressUsage addressUsage = new AddressUsage(1, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage2 = new AddressUsage(2, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage3 = new AddressUsage(3, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage4 = new AddressUsage(4, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage5 = new AddressUsage(5, db.getAddress(2), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage6 = new AddressUsage(6, db.getAddress(2), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage7 = new AddressUsage(7, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage8 = new AddressUsage(8, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage9 = new AddressUsage(9, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        AddressUsage addressUsage10 = new AddressUsage(10, db.getAddress(1), new Date(), "DEDWARDS", new Date());
        
        Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", new Date(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
        Person person2 = new Person(2, element, "Penny", "Kay", "Brindle", new Date(), "TEST", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
        Person person3 = new Person(3, element, "TEST", "TEST", "TEST", new Date(), "TEST", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
        
        Contact contact = new Contact(1, element, "test@test.com", new Date(), "DEDWARDS", new Date());
        Contact contact2 = new Contact(2, element, "test@test.com", new Date(), "DEDWARDS", new Date());
        
        Office office = new Office("EDM", db.getAddress(1), new Date(), "DEDWARDS", new Date());
        
        Landlord landlord = new Landlord(1, db.getPerson(1), "DEDWARDS", new Date());
        
        Employee employee = new Employee(1, db.getPerson(1), "DEDWARDS", "TestPassword", "DEDWARDS", new Date());
        
        Property property = new Property(1, db.getAddress(1), new Date(), element, element, "DEDWARDS", new Date());
        Property property2 = new Property(2, db.getAddress(1), new Date(), element, element, "DEDWARDS", new Date());
        Property property3 = new Property(3, db.getAddress(1), new Date(), element, element, "DEDWARDS", new Date());
        
        InvolvedParty invParty = new InvolvedParty(1, 1, db.getPerson(1), true, true, new Date(), db.getRelationship("TEST"), "DEDWARDS", new Date());
        InvolvedParty invParty2 = new InvolvedParty(2, 1, db.getPerson(2), true, false, new Date(), db.getRelationship("TEST"), "DEDWARDS", new Date());
        InvolvedParty invParty3 = new InvolvedParty(3, 1, db.getPerson(3), false, false, new Date(), db.getRelationship("ERR"), "DEDWARDS", new Date());
        
        Application application = new Application(1, "Dwayne Leroy Edwards", new Date(), invParty, addressUsage, "DEDWARDS", new Date());
        
        PropertyElement propElement = new PropertyElement(1, element, new Date(), false, "2 BED", null, "DEDWARDS", new Date());
        PropertyElement propElement2 = new PropertyElement(2, element2, new Date(), true, null, 36.00, "DEDWARDS", new Date());
        
        Tenancy tenancy = new Tenancy(1, new Date(), 24, 1, db.getProperty(property.getPropRef()), db.getApplication(application.getApplicationRef()), element, "EDM", "DEDWARDS", new Date());
        RentAccount rentAcc = new RentAccount(1, tenancy, "DEDWARDS", new Date());
        
        try {
            
            // System Elements
            db.createContactType(element);
            db.createEndReason(element);
            db.createEthnicOrigin(element);
            db.createGender(element);
            db.createJobBenefit(element);
            db.createJobRequirement(element);
            db.createLanguage(element);
            db.createMaritalStatus(element);
            db.createNationality(element);
            db.createPropElement(element);
            db.createPropertySubType(element);
            db.createPropertyType(element);
            db.createRelationship(element);
            db.createReligion(element);
            db.createSexuality(element);
            db.createTenancyType(element);
            db.createTitle(element);
            db.createContactType(element2);
            db.createEndReason(element2);
            db.createEthnicOrigin(element2);
            db.createGender(element2);
            db.createJobBenefit(element2);
            db.createJobRequirement(element2);
            db.createLanguage(element2);
            db.createMaritalStatus(element2);
            db.createNationality(element2);
            db.createPropElement(element2);
            db.createPropertySubType(element2);
            db.createPropertyType(element2);
            db.createRelationship(element2);
            db.createReligion(element2);
            db.createSexuality(element2);
            db.createTenancyType(element2);
            db.createTitle(element2);
            
            // Job Role Methods  -- Tested commented Job Role methods
//            db.createJobRole(jobRole);
            jobRole = db.getJobRole(jobRole.getJobRoleCode());
//            jobRole.updateJobRole(jobRole.getJobTitle(), jobRole.getJobDescription(), 30000.00, true, true, true, true, true, true, false, modTest);
//            db.updateJobRole(jobRole.getJobRoleCode());
//            jobRole.createJobRequirement(db.getJobRequirement(element.getCode()), modTest);
//            jobRole.createJobBenefit(benefit, modTest);
//            db.createJobRoleRequirement(jobRole.getJobRoleCode(), element.getCode());
//            db.createJobRoleBenefit(jobRole.getJobRoleCode(), benefit);
            System.out.println("Job Role Code: " + jobRole.getJobRoleCode());
            System.out.println("Modifications: " + jobRole.getModifiedBy());
            System.out.println("Job Role Requirements: " + jobRole.getJobRequirements());
            System.out.println("Job Role Benefits: " + jobRole.getBenefits() + "\n");
            
            
            // Address Methods -- Tested commented Address methods
//            db.createAddress(address);
            address = db.getAddress(address.getAddressRef());
//            db.createAddress(address2);
            address2 = db.getAddress(address2.getAddressRef());
            db.createAddress(address3);
            address3 = db.getAddress(address3.getAddressRef());
//            address.updateAddress("", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", modTest);
//            db.updateAddress(address.getAddressRef());
            System.out.println("Address Ref: " + address.getAddressRef());
            System.out.println("Address: " + address.printAddress() + "\n");
            System.out.println("Modifications: " + address.getModifiedBy());
            
            System.out.println("\nSystem Addresses: " + db.countAddresses() + "\n");
            
            
            // Person methods -- Tested commented Person methods
//            db.createPerson(person);
            person = db.getPerson(person.getPersonRef());
//            db.createPerson(person2);
            person2 = db.getPerson(person2.getPersonRef());
//            db.createPerson(person3);
            person3 = db.getPerson(person3.getPersonRef());
//            person.updatePerson(element, "DwayneUPDATED", "Leroy", "Edwards", new Date(), "UPDATED", element, element, element, element, element, element, element, modTest);
//            db.updatePerson(person.getPersonRef());
//            person.createAddress(addressUsage, null);
//            db.createPersonAddressUsage((AddressUsage) person.getLastAddress(), person.getPersonRef());
//            person2.createAddress(addressUsage3, null);
//            db.createPersonAddressUsage((AddressUsage) person2.getLastAddress(), person2.getPersonRef());
//            person3.createAddress(addressUsage4, null);
//            db.createPersonAddressUsage((AddressUsage) person3.getLastAddress(), person3.getPersonRef());
            
//            person.createContact(contact, modTest);
//            db.createPersonContact(contact, person.getPersonRef());
            System.out.println("Person Ref: " + person.getPersonRef());
            System.out.println("Modifications: " + person.getModifiedBy());
            System.out.println("Person Contacts: " + person.getContacts().size());
            System.out.println("Person Addresses: " + person.getAddresses().size() + "\n");
            
            System.out.println("System People: " + db.countPeople() + "\n");
            
            
            // Office methods -- Tested commented Office methods
//            db.createOffice(office);
            office = db.getOffice(office.getOfficeCode());
//            office.setStartDate(date, modTest);
//            db.updateOffice(office.getOfficeCode());
//            office.createContact(contact2, modTest);
//            db.createOfficeContact(contact2, office.getOfficeCode());
            System.out.println("Office Code: " + office.getOfficeCode());
            System.out.println("Modifications: " + office.getModifiedBy());
            System.out.println("Office Contacts: " + office.getContacts().size());
            System.out.println("Office Address: " + office.getAddress().printAddress() + "\n");
            
            
            // Landlord methods -- Tested commented Landlord methods
//            db.createLandlord(landlord);
            landlord = db.getLandlord(landlord.getLandlordRef());
            // NEED TO TEST ONCE CREATED A FEW LEASES - landlord.createLease(null, modTest);
            System.out.println("Landlord Leases: " + landlord.getLeases().size() + "(no leases added yet)" + "\n");
            
            System.out.println("System Landlords: " + db.countLandords() + "\n");
            
            
            // Employee methods -- Tested commented Employee methods
//            db.createEmployee(employee);
//            db.createUser(employee.getUser());
            employee = db.getEmployee(employee.getEmployeeRef());
//            employee.updatePassword("UpdatedPassword", modTest);
//            db.updateUser(employee.getUser().getUsername());
            // NEED TO TEST ONCE CREATED A FEW LEASES - landlord.createContract(null, modTest);
            System.out.println("Modifications: " + employee.getModifiedBy());
            System.out.println("Employee Contracts: " + employee.getContracts().size() + "(no contracts added yet)" + "\n");
            
            System.out.println("System Employees: " + db.countEmployees() + "\n");


            // Property methods -- Tested commented Property methods
//            db.createProperty(property);
            property = db.getProperty(property.getPropRef());
//            db.createProperty(property2);
            property2 = db.getProperty(property2.getPropRef());
//            db.createProperty(property3);
            property3 = db.getProperty(property3.getPropRef());
//            property.updateProperty(address2, new Date(), element, element, modTest);
//            db.updateProperty(property.getPropRef());
//            property3.updateProperty(address3, new Date(), element2, element2, modTest);
//            db.updateProperty(property3.getPropRef());
//            property.createPropertyElement(propElement, modTest);
//            property.createPropertyElement(propElement2, modTest);
            System.out.println("Modifications: " + property.getModifiedBy());
            System.out.println("PropertyElements: " + property.getPropertyElements().size() + "\n");
            
            System.out.println("System Properties: " + db.countProperties() + "\n");
            

            
            // PropertyElementValue methods -- Tested commented PropertyElementValue methods
            System.out.println("\nSystem Property Elements\n" + db.getPropElements() + "\n");
//            db.createPropertyElementValue(property.getPropRef(), propElement);
//            db.createPropertyElementValue(property.getPropRef(), propElement2);
            propElement = (PropertyElement) property.getPropElement(propElement.getPropertyElementRef());
            propElement2 = (PropertyElement) property.getPropElement(propElement2.getPropertyElementRef());
            System.out.println("Property Element 1: " + propElement);
            System.out.println("Property Element 2: " + propElement2 + "\n");
            
            System.out.println("System Property Element Values: " + db.getPropElementCount() + "\n");
            
//            propElement.updatePropertyElement(propElement.getStartDate(), "3 BED - AMENDED", null, false, modTest);
//            propElement2.updatePropertyElement(propElement2.getStartDate(), null, 99.99, true, modTest);
//            db.updatePropertyElementValue(property.getPropRef(), propElement);
//            db.updatePropertyElementValue(property.getPropRef(), propElement2);
            
            
////            // Application methods -- Tested commented Application methods
//            db.createApplication(application);
            application = db.getApplication(application.getApplicationRef());
//            db.createApplicationAddressUsage(addressUsage2, application.getApplicationRef());
//            application.updateApplication("Corr Name - AMENDED", date, modTest);
//            db.updateApplication(application.getApplicationRef());
//            application.addInvolvedParty(invParty2, modTest);
//            db.updateApplication(application.getApplicationRef());
//            application.addInvolvedParty(invParty3, modTest);
//            db.updateApplication(application.getApplicationRef());
//            application.addInterestedProperty(property, modTest);
//            db.createPropertyInterest(application.getApplicationRef(), property.getPropRef());
//            application.addInterestedProperty(property2, modTest);
//            db.createPropertyInterest(application.getApplicationRef(), property2.getPropRef());
//            application.addInterestedProperty(property3, modTest);
//            db.createPropertyInterest(application.getApplicationRef(), property3.getPropRef());
//            application.endInterestInProperty(property2, modTest);
//            db.endPropertyInterest(application.getApplicationRef(), property2.getPropRef());
            System.out.println("Application Ref: " + application.getApplicationRef());
            System.out.println("Application Correspondence Name: " + application.getAppCorrName());
            System.out.println("Modifications: " + application.getModifiedBy());
            System.out.println("Application Addresses: " + application.getApplicationAddressess().size());
            System.out.println("Application Household: " + application.getHousehold().size());
            System.out.println("Application Properties Interested In: " + application.getPropertiesInterestedIn().size() + "\n");
            
            System.out.println("System Applications: " + db.countApplications() + "\n");
            
            
            // Involved Party methods -- Tested commented Involved Party methods
//            db.createInvolvedParty(invParty);
            invParty = db.getInvolvedParty(invParty.getInvolvedPartyRef());
//            db.createInvolvedParty(invParty2);
            invParty2 = db.getInvolvedParty(invParty2.getInvolvedPartyRef());
//            db.createInvolvedParty(invParty3);
            invParty3 = db.getInvolvedParty(invParty3.getInvolvedPartyRef());
//            invParty2.updateInvolvedParty(false, invParty2.getStartDate(), element, modTest);
//            db.updateInvolvedParty(invParty2.getInvolvedPartyRef());
            System.out.println("Involved Party Ref: " + invParty2.getInvolvedPartyRef());
            System.out.println("Involved Party End Reason: " + invParty2.getEndReason() + "\n");
            
            System.out.println("System Involved Parties: " + db.countInvolvedParties() + "\n");
            
            
            // Tenancy methods - Tested commented Tenancy methods
//            db.createTenancy(tenancy);
            tenancy = db.getTenancy(tenancy.getAgreementRef());
//            application.setTenancy(tenancy.getAgreementRef(), modTest);
//            db.updateApplication(application.getApplicationRef());
            System.out.println("Tenancy Ref: " + tenancy.getAgreementRef());
            System.out.println("Tenancy Application: " + tenancy.getApplication().getApplicationRef());
            System.out.println("Tenancy Charges: " + tenancy.getCharges());
            System.out.println("Tenancy Rent: " + tenancy.getRent());
            System.out.println("Tenancy Property: " + tenancy.getProperty().getPropRef() + "\n");
            
            System.out.println("System Tenancies: " + db.countTenancies() + "\n");
            
            
            // RentAccount methods - Tested commented RentAccount methods
//            db.createRentAccount(rentAcc);
            rentAcc = db.getRentAccount(rentAcc.getAccRef());
            System.out.println("Rent Acc Ref: " + rentAcc.getAccRef());
            System.out.println("Rent Acc Rent: " + rentAcc.getRent());
            System.out.println("Tenancy Acc Ref: " + rentAcc.getTenancy().getAgreementRef() + "\n");
            
            System.out.println("System Rent Accounts: " + db.countRentAccounts());
            
            
            // Create Property Elements for Property Associated with Tenancy
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
    }
}
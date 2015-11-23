/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.Note;
import interfaces.User;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Application;
import server_application.Contact;
import server_application.Contract;
import server_application.Database;
import server_application.ElementImpl;
import server_application.Employee;
import server_application.EmployeeAccount;
import server_application.InvolvedParty;
import server_application.JobRole;
import server_application.JobRoleBenefit;
import server_application.Landlord;
import server_application.Lease;
import server_application.LeaseAccount;
import server_application.ModifiedBy;
import server_application.NoteImpl;
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
        System.out.println("Running database test\n");
        
        Database db = new Database("LIVE", "127.0.0.1", "root", "Toxic9489!999", 3306);
        
        Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note2 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note3 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note4 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note5 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note6 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note7 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note8 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note9 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note10 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note11 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note12 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note13 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note14 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note15 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note16 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note17 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note18 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note19 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note20 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note21 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note22 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note23 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note24 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note25 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note26 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note27 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note28 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note29 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note30 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note31 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note32 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note33 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note34 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note35 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note36 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note37 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note38 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note39 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        Note note40 = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
        
        ElementImpl element = new ElementImpl("ERR", "Error", note, "DEDWARDS", new Date());
        ElementImpl element2 = new ElementImpl("TEST", "Test", note20, "DEDWARDS", new Date());
        ElementImpl element3 = new ElementImpl("RENT", "Test", note20, "DEDWARDS", new Date());
        
        JobRole jobRole = new JobRole("MNGR", "Manager", "Managerial Duties", true, 29000.00, true, true, true, true, true, true, "DEDWARDS", new Date());
        
        ModifiedBy modTest = new ModifiedBy("TEST", new Date(), "DEDWARDS");
        
        JobRoleBenefit benefit = new JobRoleBenefit(1, element, new Date(), true, null, 29.00, note2, "DEDWARDS", new Date());
        
        Address address = new Address(1, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", note3, "DEDWARDS", new Date());
        Address address2 = new Address(2, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", note4, "DEDWARDS", new Date());
        Address address3 = new Address(3, "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", note5, "DEDWARDS", new Date());
        
        AddressUsage addressUsage = new AddressUsage(1, address, new Date(), note6, "DEDWARDS", new Date());
        AddressUsage addressUsage2 = new AddressUsage(2, address, new Date(), note7, "DEDWARDS", new Date());
        AddressUsage addressUsage3 = new AddressUsage(3, address, new Date(), note8, "DEDWARDS", new Date());
        AddressUsage addressUsage4 = new AddressUsage(4, address, new Date(), note9, "DEDWARDS", new Date());
        AddressUsage addressUsage5 = new AddressUsage(5, address, new Date(), note10, "DEDWARDS", new Date());
        AddressUsage addressUsage6 = new AddressUsage(6, address2, new Date(), note11, "DEDWARDS", new Date());
        AddressUsage addressUsage7 = new AddressUsage(7, address, new Date(), note12, "DEDWARDS", new Date());
        AddressUsage addressUsage8 = new AddressUsage(8, address, new Date(), note13, "DEDWARDS", new Date());
        AddressUsage addressUsage9 = new AddressUsage(9, address, new Date(), note14, "DEDWARDS", new Date());
        AddressUsage addressUsage10 = new AddressUsage(10, address, new Date(), note15, "DEDWARDS", new Date());
        
        Calendar dob = Calendar.getInstance();
        dob.set(1989, 9, 4);
        Calendar dob2 = Calendar.getInstance();
        dob2.set(1979, 22, 3);
        Calendar dob3 = Calendar.getInstance();
        dob3.set(2009, 7, 4);
        
        Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", dob.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
        Person person2 = new Person(2, element, "Penny", "Kay", "Brindle", dob2.getTime(), "TEST", element, element, element, element, element, element, element, addressUsage2, "DEDWARDS", new Date());
        Person person3 = new Person(3, element, "TEST", "TEST", "TEST", dob3.getTime(), "TEST", element, element, element, element, element, element, element, addressUsage3, "DEDWARDS", new Date());
        
        Contact contact = new Contact(1, element, "test@test.com", new Date(), note16, "DEDWARDS", new Date());
        Contact contact2 = new Contact(2, element, "test@test.com", new Date(), note17, "DEDWARDS", new Date());
        
        Office office = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
        
        Landlord landlord = new Landlord(1, person, "DEDWARDS", new Date());
        Landlord landlord2 = new Landlord(2, person2, "DEDWARDS", new Date());
        
        Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "DEDWARDS", new Date());
        
        Property property = new Property(1, address, new Date(), element, element, "DEDWARDS", new Date());
        Property property2 = new Property(2, address, new Date(), element, element, "DEDWARDS", new Date());
        Property property3 = new Property(3, address, new Date(), element, element, "DEDWARDS", new Date());
        
        InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
        InvolvedParty invParty2 = new InvolvedParty(2, 1, person2, true, false, new Date(), element, "DEDWARDS", new Date());
        InvolvedParty invParty3 = new InvolvedParty(3, 1, person3, false, false, new Date(), element2, "DEDWARDS", new Date());
        
        Application application = new Application(1, "Dwayne Leroy Edwards", new Date(), invParty, addressUsage4, "DEDWARDS", new Date());
        
        PropertyElement propElement = new PropertyElement(1, element, new Date(), false, "2 BED", null, note18, "DEDWARDS", new Date());
        PropertyElement propElement2 = new PropertyElement(2, element2, new Date(), true, null, 36.00, note19, "DEDWARDS", new Date());
        PropertyElement propElement3 = new PropertyElement(3, element3, new Date(), true, null, 300.00, note21, "DEDWARDS", new Date());
        
        Tenancy tenancy = new Tenancy(1, new Date(), 24, 1, db.getProperty(1), db.getApplication(1), element, "EDM", "DEDWARDS", new Date());
        RentAccount rentAcc = new RentAccount(1, tenancy, "DEDWARDS", new Date());
        
        Lease lease = new Lease(1, new Date(), 60, 1, db.getProperty(1), false, 1000.00, "EDM", "DEDWARDS", new Date());
        LeaseAccount leaseAcc = new LeaseAccount(1, lease, "DEDWARDS", new Date());
        
        Contract contract = new Contract(1, 1, new Date(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
        EmployeeAccount employeeAcc = new EmployeeAccount(1, contract, "DEDWARDS", new Date());
        
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
            db.createPropElement(element3);
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
//            db.updateJobRole(jobRole.getJobRoleCode());
//            jobRole.createJobBenefit(benefit, modTest);
//            db.updateJobRole(jobRole.getJobRoleCode());
//            db.createJobRoleRequirement(jobRole.getJobRoleCode(), element.getCode());
//            db.createJobRoleBenefit(jobRole.getJobRoleCode(), benefit);
            System.out.println("Job Role Code: " + jobRole.getJobRoleCode());
            System.out.println("Modifications: " + jobRole.getModifiedBy().size());
            System.out.println("Job Role Requirements: " + jobRole.getJobRequirements().size());
            System.out.println("Job Role Benefits: " + jobRole.getBenefits().size() + "\n");
            
            System.out.println("System Job Roles: " + db.getJobRoles().size() + "\n");
            
            
            // Address Methods -- Tested commented Address methods
//            db.createAddress(address);
            address = db.getAddress(address.getAddressRef());
//            db.createAddress(address2);
            address2 = db.getAddress(address2.getAddressRef());
//            db.createAddress(address3);
            address3 = db.getAddress(address3.getAddressRef());
//            address.updateAddress("", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", address.getNote().getNote(), modTest);
//            db.updateAddress(address.getAddressRef());
            System.out.println("Address Ref: " + address.getAddressRef());
            System.out.println("Address: " + address.printAddress());
            System.out.println("Modifications: " + address.getModifiedBy().size() + "\n");
            
            System.out.println("System Addresses: " + db.countAddresses() + "\n");
            
            
            // Person methods -- Tested commented Person methods
//            db.createPerson(person);
            person = db.getPerson(person.getPersonRef());
//            db.createPersonAddressUsage((AddressUsage) person.getLastAddress(), person.getPersonRef());
//            db.createPerson(person2);
//            db.createPersonAddressUsage((AddressUsage) person2.getLastAddress(), person2.getPersonRef());
            person2 = db.getPerson(person2.getPersonRef());
//            db.createPerson(person3);
//            db.createPersonAddressUsage((AddressUsage) person3.getLastAddress(), person3.getPersonRef());
            person3 = db.getPerson(person3.getPersonRef());
//            person.updatePerson(element, "DwayneUPDATED", "Leroy", "Edwards", new Date(), "UPDATED", element, element, element, element, element, element, element, modTest);
//            db.updatePerson(person.getPersonRef());
//            person.createContact(contact, modTest);
//            db.createPersonContact(contact, person.getPersonRef());
            System.out.println("Person Ref: " + person.getPersonRef());
            System.out.println("Modifications: " + person.getModifiedBy().size());
            System.out.println("Person Contacts: " + person.getContacts().size());
            System.out.println("Person Addresses: " + person.getAddresses().size() + "\n");
//            
            System.out.println("System People: " + db.countPeople() + "\n");
            
            
            // Office methods -- Tested commented Office methods
//            db.createOffice(office);
            office = db.getOffice(office.getOfficeCode());
//            office.setStartDate(date, modTest);
//            db.updateOffice(office.getOfficeCode());
//            office.createContact(contact2, modTest);
//            db.createOfficeContact(contact2, office.getOfficeCode());
            System.out.println("Office Code: " + office.getOfficeCode());
            System.out.println("Modifications: " + office.getModifiedBy().size());
            System.out.println("Office Contacts: " + office.getContacts().size());
            System.out.println("Office Address: " + office.getAddress().printAddress() + "\n");
            
            System.out.println("System Offices: " + db.getOffices().size() + "\n");
            
            
            // Landlord methods -- Tested commented Landlord methods
//            db.createLandlord(landlord);
//            landlord = db.getLandlord(landlord.getLandlordRef());
//            db.createLandlord(landlord2);
            landlord2 = db.getLandlord(landlord2.getLandlordRef());
            // NEED TO TEST ONCE CREATED A FEW LEASES - landlord.createLease(null, modTest);
            System.out.println("Landlord Ref: " + landlord.getLandlordRef());
            System.out.println("Modifications: " + landlord.getModifiedBy().size());
            System.out.println("Landlord Leases: " + landlord.getLeases().size() + "(no leases added yet)" + "\n");
            
            System.out.println("System Landlords: " + db.countLandords() + "\n");
            
            
            // Employee methods -- Tested commented Employee methods
//            db.createEmployee(employee);
//            db.createUser(employee.getUser());
            employee = db.getEmployee(employee.getEmployeeRef());
            User user = db.getUser(employee.getEmployeeRef());
//            employee.updatePassword("UpdatedPassword", modTest);
//            db.updateEmployee(employee.getEmployeeRef());
//            db.updateUser(employee.getUser().getUsername());
            // NEED TO TEST ONCE CREATED A FEW LEASES - landlord.createContract(null, modTest);
            System.out.println("Modifications: " + employee.getModifiedBy().size());
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
//            db.updateProperty(property.getPropRef());
//            property.createPropertyElement(propElement2, modTest);
//            db.updateProperty(property.getPropRef());
//            property.createPropertyElement(propElement3, modTest);
//            db.updateProperty(property.getPropRef());
            System.out.println("Property Ref: " + property.getPropRef());
            System.out.println("Modifications: " + property.getModifiedBy().size());
            System.out.println("Property Rent: " + property.getRent());
            System.out.println("Property Charges: " + property.getCharges());
            System.out.println("Property Landlords: " + property.getLandlords().size());
            System.out.println("PropertyElements: " + property.getPropertyElements().size() + "\n");
            
            System.out.println("System Properties: " + db.countProperties() + "\n");
            
            
            
            // PropertyElementValue methods -- Tested commented PropertyElementValue methods
            System.out.println("System Property Elements: " + db.getPropElements().size() + "\n");
//            db.createPropertyElementValue(property.getPropRef(), propElement);
//            db.createPropertyElementValue(property.getPropRef(), propElement2);
            db.createPropertyElementValue(property.getPropRef(), propElement3);
            propElement = (PropertyElement) db.getPropertyElementValue(propElement.getPropertyElementRef());
            propElement2 = (PropertyElement) db.getPropertyElementValue(propElement2.getPropertyElementRef());
            propElement3 = (PropertyElement) db.getPropertyElementValue(propElement3.getPropertyElementRef());
//            propElement.updatePropertyElement(propElement.getStartDate(), "3 BED - AMENDED", null, false, propElement.getNote().getNote(), modTest);
//            db.updatePropertyElementValue(property.getPropRef(), propElement.getPropertyElementRef());
//            propElement2.updatePropertyElement(propElement2.getStartDate(), null, 99.99, true, propElement.getNote().getNote(), modTest);
//            db.updatePropertyElementValue(property.getPropRef(), propElement2.getPropertyElementRef());
            System.out.println("Property Element 1: " + propElement);
            System.out.println("Property Element 2: " + propElement2 + "\n");
            
            System.out.println("System Property Element Values: " + db.getPropElementCount() + "\n");
            
            
            // Application methods -- Tested commented Application methods
//            db.createApplication(application);
            application = db.getApplication(application.getApplicationRef());
//            db.createApplicationAddressUsage((AddressUsage) application.getCurrentApplicationAddress(), application.getApplicationRef());
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
            System.out.println("Modifications: " + application.getModifiedBy().size());
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
            System.out.println("Modifications: " + invParty2.getModifiedBy().size());
            System.out.println("Involved Party End Reason: " + invParty2.getEndReason() + "\n");
            
            System.out.println("System Involved Parties: " + db.countInvolvedParties() + "\n");
            
            
            // Tenancy methods - Tested commented Tenancy methods
//            db.createTenancy(tenancy);
//            tenancy = db.getTenancy(tenancy.getAgreementRef());
//            application.setTenancy(tenancy.getAgreementRef(), modTest);
//            db.updateApplication(application.getApplicationRef());
            System.out.println("Tenancy Ref: " + tenancy.getAgreementRef());
            System.out.println("Modifications: " + tenancy.getModifiedBy().size());
            System.out.println("Tenancy App Ref: " + tenancy.getApplication().getApplicationRef());
            System.out.println("Tenancy Charges: " + tenancy.getCharges());
            System.out.println("Tenancy Rent: " + tenancy.getRent());
            System.out.println("Tenancy Prop Ref: " + tenancy.getProperty().getPropRef() + "\n");
            
            System.out.println("System Tenancies: " + db.countTenancies() + "\n");
            
            
            // RentAccount methods - Tested commented RentAccount methods
//            db.createRentAccount(rentAcc);
            rentAcc = db.getRentAccount(rentAcc.getAccRef());
            System.out.println("Rent Acc Ref: " + rentAcc.getAccRef());
            System.out.println("Modifications: " + rentAcc.getModifiedBy().size());
            System.out.println("Rent Acc Rent: " + rentAcc.getRent());
            System.out.println("Tenancy Ref: " + rentAcc.getTenancy().getAgreementRef() + "\n");
            
            System.out.println("System Rent Accounts: " + db.countRentAccounts() + "\n");
            
            
            // Lease methods - Tested commented Tenancy methods
//            db.createLease(lease);
            lease = db.getLease(lease.getAgreementRef());
//            lease.addLandlord(landlord2, modTest);
//            db.updateLease(lease.getAgreementRef());
//            db.createLeaseLandlord(landlord.getLandlordRef(), lease.getAccountRef());
//            property.setLandlords(lease.getLandlords(), modTest);
//            db.updateProperty(property.getPropRef());
//            landlord.createLease(lease, modTest);
//            db.updateLandlord(landlord.getLandlordRef());
            System.out.println("Lease Ref: " + lease.getAgreementRef());
            System.out.println("Modifications: " + lease.getModifiedBy().size());
            System.out.println("Lease Prop Ref: " + lease.getProperty().getPropRef());
            System.out.println("Lease Expenditure: " + lease.getExpenditure());
            System.out.println("Lease Landlords: " + lease.getLandlords().size() +"\n");
            
            System.out.println("System Leases: " + db.countLeases() + "\n");
            
            
            // LeaseAccount methods - Tested commented RentAccount methods
//            db.createLeaseAccount(leaseAcc);
            leaseAcc = db.getLeaseAccount(leaseAcc.getAccRef());
            System.out.println("Lease Acc Ref: " + leaseAcc.getAccRef());
            System.out.println("Modifications: " + leaseAcc.getModifiedBy().size());
            System.out.println("Lease Acc Expenditure: " + leaseAcc.getExpenditure());
            System.out.println("Lease Ref: " + leaseAcc.getLease().getAgreementRef() + "\n");
            
            System.out.println("System Lease Accounts: " + db.countLeaseAccounts() + "\n");
            
            // Contract methods - Tested commented Tenancy methods
//            db.createContract(contract);
            contract = db.getContract(contract.getAgreementRef());
//            employee.createContract(contract, modTest);
//            db.updateEmployee(employee.getEmployeeRef());
            System.out.println("Contract Ref: " + contract.getAgreementRef());
            System.out.println("Lease Employee Ref: " + contract.getEmployee().getEmployeeRef());
            System.out.println("Lease Job Role: " + contract.getJobRole().getJobRoleCode() + "\n");
            
            System.out.println("System Contracts: " + db.countContracts() + "\n");
            
            
            // EmployeeAccount methods - Tested commented RentAccount methods
//            db.createEmployeeAccount(employeeAcc);
            employeeAcc = db.getEmployeeAccount(employeeAcc.getAccRef());
            System.out.println("Employee Acc Ref: " + employeeAcc.getAccRef());
            System.out.println("Employee Acc Salary: " + employeeAcc.getSalary());
            System.out.println("Contract Ref: " + employeeAcc.getContractRef() + "\n");
            
            System.out.println("System Employee Accounts: " + db.countRentAccounts());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
    }
}
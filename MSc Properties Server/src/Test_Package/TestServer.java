/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.EmployeeInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import interfaces.User;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.ServerImpl;

/**
 *
 * @author Dwayne
 */
public class TestServer {

    public static void main(String[] args) {
        try {
            System.out.println("Running server test\n");
            
            ServerImpl test = new ServerImpl("TEST", "127.0.0.1", "root", "Toxic9489!999", 3306);
            
            Calendar janBeg = Calendar.getInstance();
            janBeg.set(2015, 0, 1);
            
            Calendar janEnd = Calendar.getInstance();
            janEnd.set(2015, 0, 31);
            
            Calendar febBeg = Calendar.getInstance();
            febBeg.set(2015, 1, 1);
            
            Calendar febEnd = Calendar.getInstance();
            febEnd.set(2015, 1, 28);
            
            Calendar marBeg = Calendar.getInstance();
            marBeg.set(2015, 2, 1);
            
            Calendar marEnd = Calendar.getInstance();
            marEnd.set(2015, 2, 31);
            
            Calendar aprBeg = Calendar.getInstance();
            aprBeg.set(2015, 3, 1);
            
            Calendar aprEnd = Calendar.getInstance();
            aprEnd.set(2015, 3, 30);
            
            
//            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
//            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
//            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
//            Note note4 = new NoteImpl(4, "TEST", "DEDWARDS", new Date());
//            Note note5 = new NoteImpl(5, "TEST", "DEDWARDS", new Date());
//            Note note6 = new NoteImpl(6, "TEST", "DEDWARDS", new Date());
//            Note note7 = new NoteImpl(7, "TEST", "DEDWARDS", new Date());
//            Note note8 = new NoteImpl(8, "TEST", "DEDWARDS", new Date());
//            Note note9 = new NoteImpl(9, "TEST", "DEDWARDS", new Date());
//            Note note10 = new NoteImpl(10, "TEST", "DEDWARDS", new Date());
//            Note note11 = new NoteImpl(11, "TEST", "DEDWARDS", new Date());
//            Note note12 = new NoteImpl(12, "TEST", "DEDWARDS", new Date());
//            Note note13 = new NoteImpl(13, "TEST", "DEDWARDS", new Date());
//            Note note14 = new NoteImpl(14, "TEST", "DEDWARDS", new Date());
//            Note note15 = new NoteImpl(15, "TEST", "DEDWARDS", new Date());
//            Note note16 = new NoteImpl(16, "TEST", "DEDWARDS", new Date());
//            Note note17 = new NoteImpl(17, "TEST", "DEDWARDS", new Date());
//            Note note18 = new NoteImpl(18, "TEST", "DEDWARDS", new Date());
//            Note note19 = new NoteImpl(19, "TEST", "DEDWARDS", new Date());
//            Note note20 = new NoteImpl(20, "TEST", "DEDWARDS", new Date());
//            Note note21 = new NoteImpl(21, "TEST", "DEDWARDS", new Date());
//            Note note22 = new NoteImpl(22, "TEST", "DEDWARDS", new Date());
//            Note note23 = new NoteImpl(23, "TEST", "DEDWARDS", new Date());
//            Note note24 = new NoteImpl(24, "TEST", "DEDWARDS", new Date());
//            Note note25 = new NoteImpl(25, "TEST", "DEDWARDS", new Date());
//            Note note26 = new NoteImpl(26, "TEST", "DEDWARDS", new Date());
//            Note note27 = new NoteImpl(27, "TEST", "DEDWARDS", new Date());
//            Note note28 = new NoteImpl(28, "TEST", "DEDWARDS", new Date());
//            Note note29 = new NoteImpl(29, "TEST", "DEDWARDS", new Date());
//            Note note30 = new NoteImpl(30, "TEST", "DEDWARDS", new Date());
//            Note note31 = new NoteImpl(31, "TEST", "DEDWARDS", new Date());
//            Note note32 = new NoteImpl(32, "TEST", "DEDWARDS", new Date());
//            Note note33 = new NoteImpl(33, "TEST", "DEDWARDS", new Date());
//            Note note34 = new NoteImpl(34, "TEST", "DEDWARDS", new Date());
//            Note note35 = new NoteImpl(35, "TEST", "DEDWARDS", new Date());
//            Note note36 = new NoteImpl(36, "TEST", "DEDWARDS", new Date());
//            Note note37 = new NoteImpl(37, "TEST", "DEDWARDS", new Date());
//            Note note38 = new NoteImpl(38, "TEST", "DEDWARDS", new Date());
//            Note note39 = new NoteImpl(39, "TEST", "DEDWARDS", new Date());
//            Note note40 = new NoteImpl(40, "TEST", "DEDWARDS", new Date());
//
//            ModifiedBy modTest = new ModifiedBy("TEST", new Date(), "DEDWARDS");
//
//            JobRoleBenefit benefit = new JobRoleBenefit(1, element, new Date(), true, null, 29.00, note2, "DEDWARDS", new Date());
//
//            Address address = new Address(1, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", note3, "DEDWARDS", new Date());
//            Address address2 = new Address(2, "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", note4, "DEDWARDS", new Date());
//            Address address3 = new Address(3, "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", note5, "DEDWARDS", new Date());
//
//            AddressUsage addressUsage = new AddressUsage(1, address, new Date(), note6, "DEDWARDS", new Date());
//            AddressUsage addressUsage2 = new AddressUsage(2, address, new Date(), note7, "DEDWARDS", new Date());
//            AddressUsage addressUsage3 = new AddressUsage(3, address, new Date(), note8, "DEDWARDS", new Date());
//            AddressUsage addressUsage4 = new AddressUsage(4, address, new Date(), note9, "DEDWARDS", new Date());
//            AddressUsage addressUsage5 = new AddressUsage(5, address, new Date(), note10, "DEDWARDS", new Date());
//            AddressUsage addressUsage6 = new AddressUsage(6, address2, new Date(), note11, "DEDWARDS", new Date());
//            AddressUsage addressUsage7 = new AddressUsage(7, address, new Date(), note12, "DEDWARDS", new Date());
//            AddressUsage addressUsage8 = new AddressUsage(8, address, new Date(), note13, "DEDWARDS", new Date());
//            AddressUsage addressUsage9 = new AddressUsage(9, address, new Date(), note14, "DEDWARDS", new Date());
//            AddressUsage addressUsage10 = new AddressUsage(10, address, new Date(), note15, "DEDWARDS", new Date());
//
//            Calendar dob = Calendar.getInstance();
//            dob.set(1989, 9, 4);
//            Calendar dob2 = Calendar.getInstance();
//            dob2.set(1979, 22, 3);
//            Calendar dob3 = Calendar.getInstance();
//            dob3.set(2009, 7, 4);
//
//            Person person = new Person(1, element, "Dwayne", "Leroy", "Edwards", dob.getTime(), "JL 81 61 90 C", element, element, element, element, element, element, element, addressUsage, "DEDWARDS", new Date());
//            Person person2 = new Person(2, element, "Penny", "Kay", "Brindle", dob2.getTime(), "TEST", element, element, element, element, element, element, element, addressUsage2, "DEDWARDS", new Date());
//            Person person3 = new Person(3, element, "TEST", "TEST", "TEST", dob3.getTime(), "TEST", element, element, element, element, element, element, element, addressUsage3, "DEDWARDS", new Date());
//
//            Contact contact = new Contact(1, element, "test@test.com", new Date(), note16, "DEDWARDS", new Date());
//            Contact contact2 = new Contact(2, element, "test@test.com", new Date(), note17, "DEDWARDS", new Date());
//
//            Office office = new Office("EDM", address, new Date(), "DEDWARDS", new Date());
//
//            Landlord landlord = new Landlord(1, person, "DEDWARDS", new Date());
//            Landlord landlord2 = new Landlord(2, person2, "DEDWARDS", new Date());
//
//            Employee employee = new Employee(1, person, "DEDWARDS", "TestPassword", "DEDWARDS", new Date());
//
//            Property property = new Property(1, address, new Date(), element, element, "DEDWARDS", new Date());
//            Property property2 = new Property(2, address, new Date(), element, element, "DEDWARDS", new Date());
//            Property property3 = new Property(3, address, new Date(), element, element, "DEDWARDS", new Date());
//
//            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
//            InvolvedParty invParty2 = new InvolvedParty(2, 1, person2, true, false, new Date(), element, "DEDWARDS", new Date());
//            InvolvedParty invParty3 = new InvolvedParty(3, 1, person3, false, false, new Date(), element2, "DEDWARDS", new Date());
//
//            Application application = new Application(1, "Dwayne Leroy Edwards", new Date(), invParty, addressUsage4, "DEDWARDS", new Date());
//
//            PropertyElement propElement = new PropertyElement(1, element, new Date(), false, "2 BED", null, note18, "DEDWARDS", new Date());
//            PropertyElement propElement2 = new PropertyElement(2, element2, new Date(), true, null, 36.00, note19, "DEDWARDS", new Date());
//            PropertyElement propElement3 = new PropertyElement(3, element3, new Date(), true, null, 300.00, note21, "DEDWARDS", new Date());

//            Tenancy tenancy = new Tenancy(1, new Date(), 24, 1, test.getProperty(1), test.getApplication(1), element, "EDM", "DEDWARDS", new Date());
//            RentAccount rentAcc = new RentAccount(1, tenancy, "DEDWARDS", new Date());
//
//            Lease lease = new Lease(1, new Date(), 60, 1, test.getProperty(1), false, 1000.00, "EDM", "DEDWARDS", new Date());
//            LeaseAccount leaseAcc = new LeaseAccount(1, lease, "DEDWARDS", new Date());
//
//            Contract contract = new Contract(1, 1, new Date(), 12, employee, jobRole, "EDM", "DEDWARDS", new Date());
//            EmployeeAccount employeeAcc = new EmployeeAccount(1, contract, "DEDWARDS", new Date());
            
            
            
            // CREATING SYSTEM ELEMENTS
            
//            System.out.println(test.createContactType("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEndReason("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEthnicOrigin("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createGender("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobBenefit("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobRequirement("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createLanguage("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createMaritalStatus("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createNationality("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertySubType("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyType("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createRelationship("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createReligion("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createSexuality("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTenancyType("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTitle("ERR", "Error", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createContactType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEndReason("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEthnicOrigin("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createGender("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobBenefit("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobRequirement("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createLanguage("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createMaritalStatus("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createNationality("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertySubType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createRelationship("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createReligion("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createSexuality("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTenancyType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTitle("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement("RENT", "Rent", "COMMENT", "DEDWARDS"));
//            
//            
//            // TEST TO SEE IF DUPLICATE ELEMENTS CAN BE CREATED
//            
//            System.out.println(test.createContactType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEndReason("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createEthnicOrigin("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createGender("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobBenefit("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createJobRequirement("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createLanguage("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createMaritalStatus("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createNationality("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertySubType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createRelationship("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createReligion("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createSexuality("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTenancyType("TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createTitle("TEST", "TEST", "COMMENT", "DEDWARDS"));
            
            // Job Role Methods  -- Tested commented Job Role methods
            System.out.println(test.createJobRole("MNGR", "Manager", "Managerial Duties", true, 29000.00, true, true, true, true, true, true, "DEDWARDS"));
//            System.out.println(test.updateJobRole("MNGR", "Manager", "Managerial Duties", true, 30000.00, true, true, true, true, true, true, false, "DEDWARDS"));
            System.out.println(test.createJobRoleRequirement("MNGR", "ERR", "DEDWARDS"));
            System.out.println(test.createJobRoleBenefit("MNGR", "ERR", janBeg.getTime(), true, null, 29.00, "TEST", "DEDWARDS"));
//            System.out.println(test.createJobRoleNote("MNGR", "New Job Role for Managers", "DEDWARDS"));
            JobRoleInterface jobRole = test.getJobRole("MNGR");
            System.out.println("Job Role Code: " + jobRole.getJobRoleCode());
            System.out.println("Modifications: " + jobRole.getModifiedBy().size());
            System.out.println("Job Role Notes: " + jobRole.getNotes().size());
            System.out.println("Job Role Requirements: " + jobRole.getJobRequirements().size());
            System.out.println("Job Role Benefits: " + jobRole.getBenefits().size() + "\n");
            
            System.out.println("System Job Roles: " + test.getJobRoles().size() + "\n");
            
            
            // Address Methods -- Tested commented Address methods
//            System.out.println(test.createAddress("NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createAddress("TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "COMMENT", "DEDWARDS"));
//            System.out.println(test.createAddress("NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "NFA", "COMMENT", "DEDWARDS"));
//            System.out.println(test.updateAddress(3, "", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", "COMMENT", "DEDWARDS"));
            AddressInterface address = test.getAddress(1);
            AddressInterface address2 = test.getAddress(2);
            AddressInterface address3 = test.getAddress(3);
            System.out.println("Address Ref: " + address3.getAddressRef());
            System.out.println("Address: " + address3.printAddress());
            System.out.println("Modifications: " + address3.getModifiedBy().size() + "\n");
            
            System.out.println("System Addresses: " + test.getAddresses().size() + "\n");
            
            
            Calendar dob = Calendar.getInstance();
            dob.set(1989, 9, 4);
            Calendar dob2 = Calendar.getInstance();
            dob2.set(1979, 22, 3);
            Calendar dob3 = Calendar.getInstance();
            dob3.set(2009, 7, 4);
        
//            System.out.println(test.createPerson("ERR", "Dwayne", "Leroy", "Edwards", dob.getTime(), "JL 81 61 90 C", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "DEDWARDS"));
//            System.out.println(test.createPerson("ERR", "Penny", "Kay", "Brindle", dob2.getTime(), "TEST", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "DEDWARDS"));
//            System.out.println(test.createPerson("ERR", "TEST", "TEST", "TEST", dob3.getTime(), "TEST", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "DEDWARDS"));
            
//            System.out.println(test.createPersonAddressUsage(1, 3, janBeg.getTime(), "DEDWARDS"));
//            System.out.println(test.createPersonAddressUsage(2, 3, janBeg.getTime(), "DEDWARDS"));
//            System.out.println(test.createPersonAddressUsage(3, 3, janBeg.getTime(), "DEDWARDS"));
            
            List<PersonInterface> people = test.getPeople(null, "Dwayne", null, null, null, "JL 81 61 90 C", "ERR", null, null, null, null, null, null, null, null);
            if(people.size() == 1) {
                PersonInterface person = people.get(0);
                System.out.println("Person Ref: " + person.getPersonRef());
                System.out.println("Modifications: " + person.getModifiedBy().size());
                System.out.println("Person Notes: " + person.getNotes().size());
                System.out.println("Person Contacts: " + person.getContacts().size());
                System.out.println("Person Addresses: " + person.getAddresses().size() + "\n");
            }
            
            System.out.println("System People: " + test.getPeople("ERR", null, null, null, null, null, null, null, null, null, null, null, null, null, null).size() + "\n");
            
            
            // Office methods -- Tested commented Office methods
            System.out.println(test.createOffice("EDM", 1, janBeg.getTime(), "DEDWARDS"));
//            System.out.println(test.updateOffice("EDM", janEnd.getTime(), "DEDWARDS"));
//            System.out.println(test.createOfficeContact("EDM", "ERR", "test@test.com", febBeg.getTime(), "DEDWARDS"));
            OfficeInterface office = test.getOffice("EDM");
            System.out.println("Office Code: " + office.getOfficeCode());
            System.out.println("Modifications: " + office.getModifiedBy().size());
            System.out.println("Office Contacts: " + office.getContacts().size());
            System.out.println("Office Address: " + office.getAddress().printAddress() + "\n");
            
            
            // Landlord methods -- Tested commented Landlord methods
            System.out.println(test.createLandlord(1, "DEDWARDS"));
            System.out.println(test.createLandlord(2, "DEDWARDS"));
            LandlordInterface landlord = test.getLandlord(1);
            System.out.println("Landlord Ref: " + landlord.getLandlordRef());
            System.out.println("Modifications: " + landlord.getModifiedBy().size());
            System.out.println("Landlord Leases: " + landlord.getLeases().size() + "(no leases added yet)" + "\n");
            
            System.out.println("System Landlords: " + test.getLandlords().size() + "\n");
            
            
            // Employee methods -- Tested commented Employee methods
            System.out.println(test.createEmployee(1, "DEDWARDS", "test12345", "DEDWARDS"));
            EmployeeInterface employee = test.getEmployee(1);
            User user = employee.getUser();
            System.out.println("Modifications: " + employee.getModifiedBy().size());
            System.out.println("Employee Contracts: " + employee.getContracts().size() + "(no contracts added yet)" + "\n");
            
            System.out.println("System Employees: " + test.getEmployees().size() + "\n");
            
            
            // Property methods -- Tested commented Property methods
//            System.out.println(test.createProperty(1, febBeg.getTime(), "ERR", "ERR", "DEDWARDS"));
            
            PropertyInterface property = test.getProperty(1);
            
            System.out.println("Property Ref: " + property.getPropRef());
            System.out.println("Modifications: " + property.getModifiedBy().size());
            System.out.println("Property Rent: " + property.getRent());
            System.out.println("Property Charges: " + property.getCharges());
            System.out.println("Property Landlords: " + property.getLandlords().size());
            System.out.println("PropertyElements: " + property.getPropertyElements().size() + "\n");
            
            System.out.println("System Properties: " + test.getProperties(null, null, "ERR", null, null, null, null).size() + "\n");
            
            
            // PropertyElementValue methods -- Tested commented PropertyElementValue methods
            System.out.println("System Property Elements: " + test.getPropElements().size());
//            System.out.println(test.createPropertyElement(1, "ERR", febBeg.getTime(), false, "2 BED", null, "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement(1, "TEST", febBeg.getTime(), true, null, 36.00, "COMMENT", "DEDWARDS"));
//            System.out.println(test.createPropertyElement(1, "RENT", febBeg.getTime(), true, "2 BED", 300.00, "COMMENT", "DEDWARDS"));
            PropertyElementInterface propElement = property.getPropElement(1);
            PropertyElementInterface propElement2 = property.getPropElement(2);
            PropertyElementInterface propElement3 = property.getPropElement(3);
//            System.out.println(test.updatePropertyElement(propElement.getPropertyElementRef(), 1, propElement.getStartDate(), "3 BED - AMENDED", null, false, propElement.getComment(), "DEDWARDS"));
//            System.out.println(test.updatePropertyElement(propElement2.getPropertyElementRef(), 1, propElement.getStartDate(), null, 99.99, true, propElement2.getComment(), "DEDWARDS"));
            System.out.println("\nProperty Element 1: " + propElement);
            System.out.println("\nProperty Element 2: " + propElement2 + "\n");
            
            
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            
            
            
            
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
            Logger.getLogger(TestServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
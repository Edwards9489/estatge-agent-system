/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import client_gui.EndObject;
import client_gui.address.AddressDetails;
import client_gui.address.AddressSearch;
import client_gui.address.CreateAddress;
import client_gui.address.UpdateAddress;
import client_gui.addressUsage.AddressUsageDetails;
import client_gui.application.AppDetails;
import client_gui.addressUsage.CreateAddressUsage;
import client_gui.addressUsage.UpdateAddressUsage;
import client_gui.application.AppAdvancedSearch;
import client_gui.application.AppSearch;
import client_gui.contact.ContactDetails;
import client_gui.contact.CreateContact;
import client_gui.contact.UpdateContact;
import client_gui.contract.ContractDetails;
import client_gui.contract.ContractSearch;
import client_gui.empAccount.EmpAccDetails;
import client_gui.contract.CreateContract;
import client_gui.contract.UpdateContract;
import client_gui.document.CreateDocument;
import client_gui.element.CreateElement;
import client_gui.element.ElementDetails;
import client_gui.element.UpdateElement;
import client_gui.empAccount.EmpAccSearch;
import client_gui.employee.CreateEmployee;
import client_gui.note.CreateNote;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.EmployeeSearch;
import client_gui.employee.UpdateEmployeePassword;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.home_screen.HomeForm;
import client_gui.invParty.CreateInvParty;
import client_gui.note.NoteDetails;
import client_gui.invParty.InvPartyDetails;
import client_gui.invParty.UpdateInvParty;
import client_gui.jobBenefit.CreateJobRoleBenefit;
import client_gui.jobBenefit.JobRoleBenefitDetails;
import client_gui.jobBenefit.UpdateJobRoleBenefit;
import client_gui.jobRequirement.CreateJobRoleRequirement;
import client_gui.jobRole.CreateJobRole;
import client_gui.jobRole.JobRoleDetails;
import client_gui.jobRole.UpdateJobRole;
import client_gui.landlord.CreateLandlord;
import client_gui.landlord.LandlordDetails;
import client_gui.landlord.LandlordSearch;
import client_gui.lease.CreateLease;
import client_gui.lease.LeaseDetails;
import client_gui.lease.LeaseSearch;
import client_gui.lease.UpdateLease;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.leaseAcc.LeaseAccSearch;
import client_gui.note.UpdateNote;
import client_gui.office.CreateOffice;
import client_gui.office.OfficeDetails;
import client_gui.office.UpdateOffice;
import client_gui.person.CreatePerson;
import client_gui.person.PersonDetails;
import client_gui.person.PersonSearch;
import client_gui.person.UpdatePerson;
import client_gui.property.CreateProperty;
import client_gui.property.PropertySearch;
import client_gui.property.PropertyDetails;
import client_gui.property.UpdateProperty;
import client_gui.propertyElement.CreatePropElement;
import client_gui.propertyElement.PropElementDetails;
import client_gui.propertyElement.UpdatePropElement;
import client_gui.rentAcc.RentAccDetails;
import client_gui.rentAcc.RentAccSearch;
import client_gui.reporting.ReportingFrame;
import client_gui.systemConfig.SysConfigFrame;
import client_gui.tenancy.CreateTenancy;
import client_gui.tenancy.TenSearch;
import client_gui.tenancy.TenancyDetails;
import client_gui.tenancy.UpdateTenancy;
import client_gui.transaction.CreateTransaction;
import client_gui.transaction.TransactionDetails;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.ApplicationInterface;
import interfaces.ContactInterface;
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
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import interfaces.TransactionInterface;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TESTTestGUI {
    public static void main(String[] args) {
        
        try {
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "TEST", "DEDWARDS", "ToxicTEST"});
            
            TenancyInterface tenancy = client.getTenancy(4);
            LeaseInterface lease = client.getLease(1);
            RentAccountInterface rentAcc = client.getRentAccount(4);
            ContractInterface contract = client.getContract(2);
            EmployeeAccountInterface empAcc = client.getEmployeeAccount(2);
            LeaseAccountInterface leaseAcc = client.getLeaseAccount(1);
            OfficeInterface office = client.getOffice("MNC");
            JobRoleInterface jobRole = client.getJobRole("MNGR");
            PropertyInterface property = client.getProperty(17);
            ApplicationInterface application = client.getApplication(1);
            PersonInterface person = client.getEmployee(1).getPerson();
            InvolvedPartyInterface invParty = client.getInvolvedParty(1);
            LandlordInterface landlord = client.getLandlord(1);
            EmployeeInterface employee2 = client.getEmployee(10);
            List<Element> relationships = client.getRelationships();
            Element relationship = relationships.get(0);
            AddressInterface address = client.getAddress(1);
            List<ContactInterface> contacts = person.getContacts();
            ContactInterface contact = null;
            EmployeeInterface employee = client.getEmployee(8);
            Note note = employee.getNote(339);
            PropertyElementInterface propElement = property.getPropElement(164);
            PropertyElementInterface propElement2 = property.getPropElement(172);
            JobRoleBenefitInterface jobRoleBenefit = jobRole.getJobBenefit(1);
            AddressUsageInterface addressUsage = client.getAddressUsage(2);
            TransactionInterface transaction = empAcc.getTransaction(1);
            if (!contacts.isEmpty()) {
                for (ContactInterface temp : contacts) {
                    if (temp.getContactRef() == 1) {
                        contact = temp;
                    }
                }
            }
            
            // gui Testing
            
            EndObject endObject = new EndObject(client, "Tenancy", tenancy.getAgreementRef());
            endObject.setVisible(true);
            
            
            // Address Testing
            
            AddressDetails addressDetails = new AddressDetails(client, address);
            addressDetails.setVisible(true);
            
//            AddressSearch addressSearch = new AddressSearch(client);
//            addressSearch.setVisible(true);
//            
//            CreateAddress createAddress = new CreateAddress(client);
//            createAddress.setVisible(true);
//            
//            UpdateAddress updateAddress = new UpdateAddress(client, address);
//            updateAddress.setVisible(true);
            
            
            // Address Usage Testing
            
            AddressUsageDetails addressUsageDetails = new AddressUsageDetails(client, addressUsage);
            addressUsageDetails.setVisible(true);
            
//            CreateAddressUsage createAddressUsage = new CreateAddressUsage(client, "Person", 7);
//            createAddressUsage.setVisible(true);
//            
//            UpdateAddressUsage updateAddressUsage = new UpdateAddressUsage(client, addressUsage, "Person", 2);
//            updateAddressUsage.setVisible(true);
            
            
            // Application Testing
            
            AppDetails appDetails = new AppDetails(client, application);
            appDetails.setVisible(true);
            
//            AppSearch appSearch = new AppSearch(client);
//            appSearch.setVisible(true);
//            
//            AppAdvancedSearch appAdvSearch = new AppAdvancedSearch(client);
//            appAdvSearch.setVisible(true);
            
            
            // Contact Testing
            
            if (contact != null) {
                ContactDetails contactDetails = new ContactDetails(client, contact);
                contactDetails.setVisible(true);
            }
            
//            CreateContact createContact = new CreateContact(client, "Person", 1);
//            createContact.setVisible(true);
//            
//            if (contact != null) {
//                UpdateContact updateContact = new UpdateContact(client, contact, "Person", 1);
//                updateContact.setVisible(true);
//            }
            
            
            // Contract Testing
            
            ContractDetails contractDetails = new ContractDetails(client, contract);
            contractDetails.setVisible(true);
            
//            ContractSearch contractSearch = new ContractSearch(client);
//            contractSearch.setVisible(true);
//            
//            CreateContract createContract = new CreateContract(client);
//            createContract.setVisible(true);
//            
//            UpdateContract updateContract = new UpdateContract(client, contract);
//            updateContract.setVisible(true);
            
            
            // Document Testing
            
            CreateDocument createDocument = new CreateDocument(client, "Property", property.getPropRef());
            createDocument.setVisible(true);
            
            
            // Element Testing
            
            ElementDetails elementDetails = new ElementDetails(client, relationship, "Relationship");
            elementDetails.setVisible(true);
            
//            CreateElement createElement = new CreateElement(client, "Relationship");
//            createElement.setVisible(true);
//            
//            UpdateElement updateElement = new UpdateElement(client, relationship, "Relationship");
//            updateElement.setVisible(true);
            
            
            // Employee Account Testing
            
            EmpAccDetails empAccGUI = new EmpAccDetails(client, empAcc);
            empAccGUI.setVisible(true);
            
//            EmpAccSearch empAccSearch = new EmpAccSearch(client);
//            empAccSearch.setVisible(true);
            
            
            // Employee Testing
            
            EmployeeDetails employeeDetails = new EmployeeDetails(client, employee);
            employeeDetails.setVisible(true);
            
            EmployeeDetails employeeDetails2 = new EmployeeDetails(client, employee2);
            employeeDetails2.setVisible(true);
            
//            CreateEmployee createEmployee = new CreateEmployee(client);
//            createEmployee.setVisible(true);
//            
//            EmployeeSearch employeeSearch = new EmployeeSearch(client);
//            employeeSearch.setVisible(true);
//            
//            UpdateEmployeePassword updatePassword = new UpdateEmployeePassword(client, 9);
//            updatePassword.setVisible(true);
//
//            UpdateEmployeeSecurity updateSecurity = new UpdateEmployeeSecurity(client);
//            updateSecurity.setVisible(true);
            
            
            // Home Form Testing
            
            HomeForm homeForm = new HomeForm(client);
            homeForm.setVisible(true);
            
            
            // Involved Party Testing
            
            InvPartyDetails invPartyDetails = new InvPartyDetails(client, invParty);
            invPartyDetails.setVisible(true);
            
//            CreateInvParty createInvParty = new CreateInvParty(client);
//            createInvParty.setVisible(true);
//            
//            UpdateInvParty updateInvParty = new UpdateInvParty(client, invParty);
//            updateInvParty.setVisible(true);
            
            
            // Job Benefit Testing
            
            JobRoleBenefitDetails jobBenefitDetails = new JobRoleBenefitDetails(client, jobRoleBenefit);
            jobBenefitDetails.setVisible(true);
            
//            CreateJobRoleBenefit createJobBenefit = new CreateJobRoleBenefit(client, jobRole.getJobRoleCode());
//            createJobBenefit.setVisible(true);
//
//            UpdateJobRoleBenefit updateJobBenefit = new UpdateJobRoleBenefit(client, jobRoleBenefit);
//            updateJobBenefit.setVisible(true);
            
            
            // Job Requirement Testing
            
//            CreateJobRoleRequirement createJobRequirement = new CreateJobRoleRequirement(client, jobRole);
//            createJobRequirement.setVisible(true);
            
            
            // Job Role Testing

            JobRoleDetails jobRoleDetails = new JobRoleDetails(client, jobRole);
            jobRoleDetails.setVisible(true);
            
//            CreateJobRole createJobRole = new CreateJobRole(client);
//            createJobRole.setVisible(true);
//            
//            UpdateJobRole updateJobRole = new UpdateJobRole(client, jobRole);
//            updateJobRole.setVisible(true);
            
            
            // Landlord Testing
            
            LandlordDetails lanlordDetails = new LandlordDetails(client, landlord);
            lanlordDetails.setVisible(true);
            
//            CreateLandlord createLandlord = new CreateLandlord(client);
//            createLandlord.setVisible(true);
//            
//            LandlordSearch landlordSearch = new LandlordSearch(client);
//            landlordSearch.setVisible(true);
            
            
            // Lease Testing
            
            LeaseDetails leaseDetails = new LeaseDetails(client, lease);
            leaseDetails.setVisible(true);
            
//            CreateLease createLease = new CreateLease(client);
//            createLease.setPropField(3);
//            createLease.setVisible(true);
//            
//            LeaseSearch leaseSearch = new LeaseSearch(client);
//            leaseSearch.setVisible(true);
//            
//            UpdateLease updateLease = new UpdateLease(client, lease);
//            updateLease.setVisible(true);
            
            
            // Lease Acc Testing

            LeaseAccDetails leaseAccGUI = new LeaseAccDetails(client, leaseAcc);
            leaseAccGUI.setVisible(true);
            
//            LeaseAccSearch leaseAccSearch = new LeaseAccSearch(client);
//            leaseAccSearch.setVisible(true);
            
            
            // Note Testing
            
            NoteDetails noteDetails = new NoteDetails(client, note);
            noteDetails.setVisible(true);
            
//            CreateNote createNote = new CreateNote(client, "Person", 1);
//            createNote.setVisible(true);
//            
//            UpdateNote updateNote = new UpdateNote(client, note, "Employee", 8);
//            updateNote.setVisible(true);
            
            
            // Office Testing            
            
            OfficeDetails officeDetails = new OfficeDetails(client, office);
            officeDetails.setVisible(true);
            
//            CreateOffice createOffice = new CreateOffice(client);
//            createOffice.setVisible(true);
//            
//            UpdateOffice updateOffice = new UpdateOffice(client, office);
//            updateOffice.setVisible(true);
            
            
            // Person Testing
            
            PersonDetails personDetails = new PersonDetails(client, person);
            personDetails.setVisible(true);
            
//            CreatePerson createPerson = new CreatePerson(client);
//            createPerson.setVisible(true);
//            
//            PersonSearch personSearch = new PersonSearch(client);
//            personSearch.setVisible(true);
//            
//            UpdatePerson updatePerson = new UpdatePerson(client, person);
//            updatePerson.setVisible(true);
            
            
            // Property Testing
            
            PropertyDetails propertyDetails = new PropertyDetails(client, property);
            propertyDetails.setVisible(true);
            
//            CreateProperty createProperty = new CreateProperty(client);
//            createProperty.setVisible(true);
//            
//            PropertySearch propertySearch = new PropertySearch(client);
//            propertySearch.setVisible(true);
//
//            UpdateProperty updateProperty = new UpdateProperty(client, property);
//            updateProperty.setVisible(true);
            
            
            // Property Element Testing
            
            PropElementDetails propElementDetails = new PropElementDetails(client, propElement);
            propElementDetails.setVisible(true);
            
            PropElementDetails propElementDetails2 = new PropElementDetails(client, propElement2);
            propElementDetails2.setVisible(true);
            
//            CreatePropElement createPropElement = new CreatePropElement(client, 18);
//            createPropElement.setVisible(true);
//            
//            UpdatePropElement updatePropElement = new UpdatePropElement(client, propElement, 17);
//            updatePropElement.setVisible(true);
//            
//            UpdatePropElement updatePropElement2 = new UpdatePropElement(client, propElement2, 17);
//            updatePropElement2.setVisible(true);
            
            
            // Rent Acc Testing
            
            RentAccDetails rentAccGUI = new RentAccDetails(client, rentAcc);
            rentAccGUI.setVisible(true);
            
//            RentAccSearch rentAccSearch = new RentAccSearch(client);
//            rentAccSearch.setVisible(true);
            
            
            // Reporting Testing
            
            ReportingFrame reporting = new ReportingFrame(client);
            reporting.setVisible(true);
            
            
            // System Config Testing
            
            SysConfigFrame sysConfig = new SysConfigFrame(client);
            sysConfig.setVisible(true);
            
            
            // Tenancy Testing
            
            TenancyDetails tenancyDetails = new TenancyDetails(client, tenancy);
            tenancyDetails.setVisible(true);

//            CreateTenancy createTenancy = new CreateTenancy(client);
//            createTenancy.setAppField(2);
//            createTenancy.setPropField(5);
//            createTenancy.setVisible(true);
//            
//            TenSearch tenancySearch = new TenSearch(client);
//            tenancySearch.setVisible(true);
//            
//            UpdateTenancy updateTenancy = new UpdateTenancy(client, tenancy);
//            updateTenancy.setVisible(true);
            
            
            // Transaction Testing
            
            TransactionDetails transactionDetails = new TransactionDetails(client, transaction, "Employee Account");
            transactionDetails.setVisible(true);
            
//            CreateTransaction createTransaction = new CreateTransaction(client, "Employee Account", 2);
//            createTransaction.setVisible(true);
            
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TESTTestGUI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

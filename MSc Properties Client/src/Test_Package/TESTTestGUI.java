/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import client_gui.address.AddressDetails;
import client_gui.address.CreateAddress;
import client_gui.address.UpdateAddress;
import client_gui.application.AppDetails;
import client_gui.addressUsage.CreateAddressUsage;
import client_gui.contact.ContactDetails;
import client_gui.contact.CreateContact;
import client_gui.contact.UpdateContact;
import client_gui.contract.ContractDetails;
import client_gui.empAccount.EmpAccDetails;
import client_gui.contract.CreateContract;
import client_gui.element.CreateElement;
import client_gui.element.ElementDetails;
import client_gui.element.UpdateElement;
import client_gui.employee.CreateEmployee;
import client_gui.note.CreateNote;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.EmployeeSearch;
import client_gui.employee.UpdateEmployeePassword;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.note.NoteDetails;
import client_gui.invParty.InvPartyDetails;
import client_gui.jobBenefit.CreateJobRoleBenefit;
import client_gui.jobBenefit.JobRoleBenefitDetails;
import client_gui.jobBenefit.UpdateJobRoleBenefit;
import client_gui.jobRequirement.CreateJobRoleRequirement;
import client_gui.jobRole.CreateJobRole;
import client_gui.jobRole.JobRoleDetails;
import client_gui.jobRole.UpdateJobRole;
import client_gui.landlord.CreateLandlord;
import client_gui.landlord.LandlordDetails;
import client_gui.lease.CreateLease;
import client_gui.lease.LeaseDetails;
import client_gui.lease.UpdateLease;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.office.CreateOffice;
import client_gui.office.OfficeDetails;
import client_gui.office.UpdateOffice;
import client_gui.person.CreatePerson;
import client_gui.person.PersonDetails;
import client_gui.person.UpdatePerson;
import client_gui.property.CreateProperty;
import client_gui.property.PropertyDetails;
import client_gui.property.UpdateProperty;
import client_gui.propertyElement.CreatePropElement;
import client_gui.propertyElement.PropElementDetails;
import client_gui.propertyElement.UpdatePropElement;
import client_gui.rentAcc.RentAccDetails;
import client_gui.systemConfig.SysConfigFrame;
import client_gui.tenancy.CreateTenancy;
import client_gui.tenancy.TenancyDetails;
import client_gui.tenancy.UpdateTenancy;
import interfaces.AddressInterface;
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
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
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
            PropertyInterface property = client.getProperty(18);
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
            EmployeeInterface employee = client.getEmployee(9);
            Note note = employee.getNote(320);
            PropertyElementInterface propElement = property.getPropElement(164);
            PropertyElementInterface propElement2 = property.getPropElement(172);
            JobRoleBenefitInterface jobRoleBenefit = jobRole.getJobBenefit(1);
            if (!contacts.isEmpty()) {
                for (ContactInterface temp : contacts) {
                    if (temp.getContactRef() == 1) {
                        contact = temp;
                    }
                }
            }
            
            UpdateEmployeePassword updatePassword = new UpdateEmployeePassword(client, 9);
            updatePassword.setVisible(true);
            
//            UpdateEmployeeSecurity updateSecurity = new UpdateEmployeeSecurity(client);
//            updateSecurity.setVisible(true);
            
//            CreateEmployee createEmployee = new CreateEmployee(client);
//            createEmployee.setVisible(true);
//            
//            CreateLandlord createLandlord = new CreateLandlord(client);
//            createLandlord.setVisible(true);
            
//            UpdateOffice updateOffice = new UpdateOffice(client, office);
//            updateOffice.setVisible(true);
            
//            CreateOffice createOffice = new CreateOffice(client);
//            createOffice.setVisible(true);
            
//            if (contact != null) {
//                ContactDetails contactDetails = new ContactDetails(client, contact);
//                contactDetails.setVisible(true);
//            }
            
//            if (contact != null) {
//                UpdateContact updateContact = new UpdateContact(client, contact, "Person", 1);
//                updateContact.setVisible(true);
//            }
            
//            CreateContact createContact = new CreateContact(client, "Person", 1);
//            createContact.setVisible(true);
            
//            CreateTenancy createTenancy = new CreateTenancy(client);
//            createTenancy.setVisible(true);
//            createTenancy.setAppField(2);
//            createTenancy.setPropField(5);
//            
//            UpdateTenancy updateTenancy = new UpdateTenancy(client, tenancy);
//            updateTenancy.setVisible(true);
//            
//            CreateLease createLease = new CreateLease(client);
//            createLease.setVisible(true);
//            createLease.setPropField(3);
//            
//            UpdateLease updateLease = new UpdateLease(client, lease);
//            updateLease.setVisible(true);
            
//            PropElementDetails propElementDetails = new PropElementDetails(client, propElement);
//            propElementDetails.setVisible(true);
//            
//            PropElementDetails propElementDetails2 = new PropElementDetails(client, propElement2);
//            propElementDetails2.setVisible(true);
            
//            CreatePropElement createPropElement = new CreatePropElement(client, 17);
//            createPropElement.setVisible(true);
            
//            UpdatePropElement updatePropElement = new UpdatePropElement(client, propElement, 17);
//            updatePropElement.setVisible(true);
//            
//            UpdatePropElement updatePropElement2 = new UpdatePropElement(client, propElement2, 1);
//            updatePropElement2.setVisible(true);
            
//            UpdateAddress addressGUI = new UpdateAddress(client, address);
//            addressGUI.setVisible(true);
            
//            AddressDetails addressGUI2 = new AddressDetails(client, address);
//            addressGUI2.setVisible(true);
            
//            SysConfigFrame configGUI = new SysConfigFrame(client);
//            configGUI.setVisible(true);
//            
//            UpdateElement elementGUI = new UpdateElement(client, relationship, "Relationship");
//            elementGUI.setVisible(true);
//            
//            CreateElement elementGUI2 = new CreateElement(client, "Relationship");
//            elementGUI2.setVisible(true);
//            
//            ElementDetails elementGUI3 = new ElementDetails(client, relationship, "Relationship");
//            elementGUI3.setVisible(true);
            
//            CreateAddress addressGUI = new CreateAddress(client);
//            addressGUI.setVisible(true);
            
//            CreateAddressUsage addressGUI = new CreateAddressUsage(client, "Person", 7);
//            addressGUI.setVisible(true);
            
//            CreateContract createContract = new CreateContract(client);
//            createContract.setVisible(true);
//            
//            CreateNote createNote = new CreateNote(client, "Person", 1);
//            createNote.setVisible(true);
//            
//            NoteDetails noteGUI = new NoteDetails(client, note);
//            noteGUI.setVisible(true);
            
//            EmployeeSearch empSearchGUI = new EmployeeSearch(client);
//            empSearchGUI.setVisible(true);
            
//            EmployeeDetails empGUI = new EmployeeDetails(client, employee);
//            empGUI.setVisible(true);
//            
//            EmployeeDetails empGUI2 = new EmployeeDetails(client, employee2);
//            empGUI2.setVisible(true);
//            
//            LandlordDetails lanlordGUI = new LandlordDetails(client, landlord);
//            lanlordGUI.setVisible(true);
//            
//            InvPartyDetails invPartyGUI = new InvPartyDetails(client, invParty);
//            invPartyGUI.setVisible(true);
//            
//            PersonDetails personGUI = new PersonDetails(client, person);
//            personGUI.setVisible(true);
//            
//            AppDetails appGUI = new AppDetails(client, application);
//            appGUI.setVisible(true);
//            
//            PropertyDetails propGUI = new PropertyDetails(client, property);
//            propGUI.setVisible(true);
//            
//            CreateJobRoleRequirement jobRequirementGUI = new CreateJobRoleRequirement(client, jobRole);
//            jobRequirementGUI.setVisible(true);
////            
//            JobRoleDetails jobRoleGUI = new JobRoleDetails(client, jobRole);
//            jobRoleGUI.setVisible(true);
//            
//            CreateJobRoleBenefit createJobBenefit = new CreateJobRoleBenefit(client, jobRole.getJobRoleCode());
//            createJobBenefit.setVisible(true);
//            
//            if (jobRoleBenefit != null) {
//                UpdateJobRoleBenefit updateJobBenefit = new UpdateJobRoleBenefit(client, jobRoleBenefit);
//                updateJobBenefit.setVisible(true);
                
//                JobRoleBenefitDetails jobRoleBenefitDetails = new JobRoleBenefitDetails(client, jobRoleBenefit);
//                jobRoleBenefitDetails.setVisible(true);
//            }
            
//            CreateJobRole createJobRole = new CreateJobRole(client);
//            createJobRole.setVisible(true);
//            
//            UpdateJobRole updateJobRole = new UpdateJobRole(client, jobRole);
//            updateJobRole.setVisible(true);
            
//            CreatePerson createPerson = new CreatePerson(client);
//            createPerson.setVisible(true);
            
//            UpdatePerson updatePerson = new UpdatePerson(client, person);
//            updatePerson.setVisible(true);
            
//            CreateProperty createProperty = new CreateProperty(client);
//            createProperty.setVisible(true);
//            
//            UpdateProperty updateProperty = new UpdateProperty(client, property);
//            updateProperty.setVisible(true);
            
//            
//            OfficeDetails officeGUI = new OfficeDetails(client, office);
//            officeGUI.setVisible(true);
//            
//            TenancyDetails tenancyGUI = new TenancyDetails(client, tenancy);
//            tenancyGUI.setVisible(true);
//            
//            RentAccDetails rentAccGUI = new RentAccDetails(client, rentAcc);
//            rentAccGUI.setVisible(true);
//            
//            LeaseDetails leaseGUI = new LeaseDetails(client, lease);
//            leaseGUI.setVisible(true);
//            
//            LeaseAccDetails leaseAccGUI = new LeaseAccDetails(client, leaseAcc);
//            leaseAccGUI.setVisible(true);
//            
//            ContractDetails contractGUI = new ContractDetails(client, contract);
//            contractGUI.setVisible(true);
//            
//            EmpAccDetails empAccGUI = new EmpAccDetails(client, empAcc);
//            empAccGUI.setVisible(true);
            
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TESTTestGUI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

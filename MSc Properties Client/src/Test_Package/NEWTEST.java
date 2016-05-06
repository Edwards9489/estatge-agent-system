/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import client_gui.EndObject;
import client_gui.IntegerListener;
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
public class NEWTEST {
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
            
            HomeForm homeForm = new HomeForm(client);
            homeForm.setVisible(true);
            
//            PersonSearch personSearch = new PersonSearch(client);
//            personSearch.setVisible(true);
            
//            LeaseAccSearch leaseAccSearchGUI = new LeaseAccSearch(client);
//            leaseAccSearchGUI.setVisible(true);
//            
//            EmpAccSearch empAccSearchGUI = new EmpAccSearch(client);
//            empAccSearchGUI.setVisible(true);
//            
//            LeaseSearch leaseSearchGUI = new LeaseSearch(client);
//            leaseSearchGUI.setVisible(true);
//            
//            ContractSearch contractSearchGUI = new ContractSearch(client);
//            contractSearchGUI.setVisible(true);
//            
//            TenSearch tenSearchGUI = new TenSearch(client);
//            tenSearchGUI.setVisible(true);
//            
//            PropertySearch propSearchGUI = new PropertySearch(client);
//            propSearchGUI.setVisible(true);
//            
//            AppSearch appSearchGUI = new AppSearch(client);
//            appSearchGUI.setVisible(true);
//            
//            AppSearch appSearchListener = new AppSearch(client, new IntegerListener() {
//                @Override
//                public void intOmitted(int ref) {
//                    System.out.println("Application Chosen: " + ref);
//                }
//                
//            });
//            appSearchListener.setVisible(true);
            
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(NEWTEST.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

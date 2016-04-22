/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import client_gui.application.AppDetails;
import client_gui.contract.ContractDetails;
import client_gui.empAccount.EmpAccDetails;
import client_gui.employee.CreateContract;
import client_gui.employee.CreateNote;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.EmployeeSearch;
import client_gui.employee.NoteDetails;
import client_gui.invParty.InvPartyDetails;
import client_gui.jobRole.JobRoleDetails;
import client_gui.landlord.LandlordDetails;
import client_gui.lease.LeaseDetails;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.office.OfficeDetails;
import client_gui.person.PersonDetails;
import client_gui.property.PropertyDetails;
import client_gui.rentAcc.RentAccDetails;
import client_gui.tenancy.TenancyDetails;
import interfaces.ApplicationInterface;
import interfaces.ContactInterface;
import interfaces.ContractInterface;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TESTTestGUI {
    public static void main(String[] args) {
        
        try {
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "TEST", "DEDWARDS", "DEDWARDSpassword"});
            
//            CreateContract createContract = new CreateContract(client);
//            createContract.setVisible(true);
//            
//            CreateNote createNote = new CreateNote(client, "Person", 1);
//            createNote.setVisible(true);
            
            EmployeeInterface employee = client.getEmployee(7);
//            Note note = employee.getNote(320);
//            
//            NoteDetails noteGUI = new NoteDetails(client, note);
//            noteGUI.setVisible(true);
            
//            EmployeeSearch empSearchGUI = new EmployeeSearch(client);
//            empSearchGUI.setVisible(true);
            
            EmployeeDetails empGUI = new EmployeeDetails(client, employee);
            empGUI.setVisible(true);
            
            EmployeeInterface employee2 = client.getEmployee(8);
            
            EmployeeDetails empGUI2 = new EmployeeDetails(client, employee2);
            empGUI2.setVisible(true);
            
            Note note = employee2.getNote(340);
            if (note != null) {
                int count = 1;
                System.out.println("List Size: " + note.getModifiedBy().size());
                for (ModifiedByInterface modified : note.getModifiedBy()) {
                    System.out.println("TEST IN LOOP" + count);
                    count++;
                }
            }
            
            
//            
//            LandlordInterface landlord = client.getLandlord(1);
//            
//            LandlordDetails lanlordGUI = new LandlordDetails(client, landlord);
//            lanlordGUI.setVisible(true);
//            
//            InvolvedPartyInterface invParty = client.getInvolvedParty(1);
//            
//            InvPartyDetails invPartyGUI = new InvPartyDetails(client, invParty);
//            invPartyGUI.setVisible(true);
//            
//            PersonInterface person = client.getEmployee(1).getPerson();
//            
//            PersonDetails personGUI = new PersonDetails(client, person);
//            personGUI.setVisible(true);
//            
//            ApplicationInterface application = client.getApplication(1);
//            
//            AppDetails appGUI = new AppDetails(client, application);
//            appGUI.setVisible(true);
//            
//            PropertyInterface property = client.getProperty(1);
//            
//            PropertyDetails propGUI = new PropertyDetails(client, property);
//            propGUI.setVisible(true);
//            
//            JobRoleInterface jobRole = client.getJobRole("MNGR");
//            
//            JobRoleDetails jobRoleGUI = new JobRoleDetails(client, jobRole);
//            jobRoleGUI.setVisible(true);
//            
//            OfficeInterface office = client.getOffice("MNC");
//            
//            OfficeDetails officeGUI = new OfficeDetails(client, office);
//            officeGUI.setVisible(true);
//            
//            TenancyInterface tenancy = client.getTenancy(4);
//            
//            TenancyDetails tenancyGUI = new TenancyDetails(client, tenancy);
//            tenancyGUI.setVisible(true);
//            
//            RentAccountInterface rentAcc = client.getRentAccount(4);
//            
//            RentAccDetails rentAccGUI = new RentAccDetails(client, rentAcc);
//            rentAccGUI.setVisible(true);
//            
//            LeaseInterface lease = client.getLease(1);
//            
//            LeaseDetails leaseGUI = new LeaseDetails(client, lease);
//            leaseGUI.setVisible(true);
//            
//            LeaseAccountInterface leaseAcc = client.getLeaseAccount(1);
//            
//            LeaseAccDetails leaseAccGUI = new LeaseAccDetails(client, leaseAcc);
//            leaseAccGUI.setVisible(true);
//            
//            ContractInterface contract = client.getContract(2);
//            
//            ContractDetails contractGUI = new ContractDetails(client, contract);
//            contractGUI.setVisible(true);
//            
//            EmployeeAccountInterface empAcc = client.getEmployeeAccount(2);
//            
//            EmpAccDetails empAccGUI = new EmpAccDetails(client, empAcc);
//            empAccGUI.setVisible(true);
            
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TESTTestGUI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TestClient2  implements Observer {
    public void run()  {
        try {
            System.out.println("********************Running Client Test********************");
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "LIVE", "DEDWARDS", "DEDWARDS"});
            client.addObserver(this);
            
            
            // ADD METHODS
//            System.out.println("System Offices: " + client.getOffices().size());
            
//            LeaseInterface lease = client.getLease(3);
//            if(lease != null) {
//                System.out.println("Lease 3: " + lease.getAgreementName());
//            }
//            
//            TenancyInterface tenancy = client.getTenancy(2);
//            if(tenancy != null) {
//                System.out.println("Tenancy 2: " + tenancy.getAgreementName());
//            }
//            
//            ContractInterface contract = client.getContract(1);
//            if(contract != null) {
//                System.out.println("Cntract 1: " + contract.getAgreementName());
//            }
//            
//            OfficeInterface office = client.getOffice("MNC");
//            if(office != null) {
//                System.out.println("MNC Office Agreements: " + office.getAgreements().size());
//            }
            
//            List<AgreementInterface> tenancies = client.getUserTenancies();
//            List<AgreementInterface> leases = client.getUserLeases();
//            List<AccountInterface> rentAccounts = client.getUserRentAccounts();
//            System.out.println("\n\nTenancies - " + tenancies.size());
//            
//            for (AgreementInterface temp : tenancies) {
//                System.out.println("\n" + temp.getAgreementRef() + " : " + temp.getAgreementName());
//            }
//            
//            System.out.println("\nLeases - " + leases.size());
//            
//            for (AgreementInterface temp : leases) {
//                System.out.println("\n" + temp.getAgreementRef() + " : " + temp.getAgreementName());
//            }
//            
//            System.out.println("\nRent Accounts - " + rentAccounts.size());
//            
//            for (AccountInterface temp : rentAccounts) {
//                if(temp != null) {
//                    System.out.println("\n" + temp.getAccRef() + " : " + temp.getAccName());
//                }
//            }
//            OfficeInterface office = client.getOffice(client.getOfficeCode());
//            List<AgreementInterface> agreements = office.getAgreements();
//            System.out.println("Agreements - " + agreements);
//            System.out.println("Tenancies - " + tenancies);
//            System.out.println("Leases - " + leases);
//            System.out.println("Office:" + client.getOfficeCode());
//            System.out.println("Office Agreements - " + agreements.size());
//            System.out.println("Office Tenancies: " + tenancies.size());
//            System.out.println("Office Leases: " + leases.size());
//            System.out.println("Office Rent Accounts: " + client.getUserRentAccounts().size());

//            List<PersonInterface> people = client.getPeople(null, null, null, null, null, null, null, null, null, null, null, null, null, "ADMIN", null);
//            System.out.println("\nSystem People - " + people.size());
//            
//            for (PersonInterface temp : people) {
//                if(temp != null) {
//                    System.out.println("\n" + temp.getPersonRef() + " : " + temp.getName() + " : Marital Status: " + temp.getMaritalStatus());
//                }
//            }
            
            
            
            System.out.println("Create Application: " + client.createApplication("TEST8", new Date(), 16, 21, new Date()));
            
            client.logout();
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TestClient2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        TestClient2 test = new TestClient2();
        test.run();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE OCCURED");
    }
}
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
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TestClient  implements Observer {
    
    public void run()  {
        try {
            System.out.println("********************Running Client Test********************");
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "LIVE", "ADMIN", "MScProperties"});
            
            client.addObserver(this);
            
//            System.out.println(client.createContactType("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createEndReason("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createEthnicOrigin("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createGender("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createJobBenefit("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createJobRequirement("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createLanguage("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createMaritalStatus("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createNationality("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createPropertyElement("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createPropertySubType("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createPropertyType("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createRelationship("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createReligion("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createSexuality("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createTenancyType("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createTitle("ERR", "Error", "COMMENT") == 1);
//            System.out.println(client.createContactType("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createEndReason("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createEthnicOrigin("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createGender("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createJobBenefit("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createJobRequirement("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createLanguage("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createMaritalStatus("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createNationality("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createPropertyElement("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createPropertySubType("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createPropertyType("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createRelationship("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createReligion("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createSexuality("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createTenancyType("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createTitle("TEST", "TEST", "COMMENT") == 1);
//            System.out.println(client.createPropertyElement("RENT", "Rent", "COMMENT") == 1);
//            System.out.println(client.createTitle("TEST2", "TEST2", "COMMENT") == 1);
//            System.out.println(client.createTitle("TEST3", "TEST3", "COMMENT") == 1);
//            
//            Calendar dob = Calendar.getInstance();
//            dob.set(1989, 3, 9);
//            Calendar startDate = Calendar.getInstance();
//            startDate.set(2015, 0, 23);
//            
//            System.out.println(client.updateTitle("TEST2", "JUST UPDATED", true, "UPDATED COMMENT") == 1);
//            System.out.println(client.deleteTitle("TEST2") <= 0);
//            System.out.println(client.deleteTitle("TEST3") == 1);
//            
//            System.out.println(client.createAddress("", "", "", "", "5", "Brook Crescent", "Edmonton", "London", "England", "N9 0DJ", "") >= 1);
//            System.out.println(client.createPerson("TEST", "Dwayne", "Leroy", "Edwards", dob.getTime(), "JL816190C", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", 7, startDate.getTime()) >= 1);
//            
//            System.out.println(client.createApplication("MR DWAYNE LEROY EDWARDS", startDate.getTime(), 1, "TEST", 1, startDate.getTime()) > 0);
            
            
            System.out.println(client.createTitle("DEMO", "demo", "Demonstration") > 0);
            System.out.println(client.createTitle("DEMO2", "demo2", "Demonstration2") > 0);
            
            System.out.println(client.updateTitle("DEMO", "demonstration", true, "demo UPDATED") > 0);
            
            System.out.println(client.deleteTitle("DEMO") > 0);   // WILL RETURN FALSE DUE TO TITLE HAVING BEEN MODIFIED
            System.out.println(client.deleteTitle("DEMO2") > 0);
            
            Calendar date = Calendar.getInstance();
            date.set(2015, 0, 1);
            
            Calendar date2 = Calendar.getInstance();
            date2.set(2017, 0, 1);
            
            System.out.println(client.updateTenancy(1, "UPDATED TENANCY", new Date(), 24, "TEST") > 0);
            System.out.println(client.updateContract(4, "UPDATED CONTRACT", new Date(), 24) > 0);
            System.out.println(client.updateTenancy(9, "UPDATED TENANCY", new Date(), 24, "TEST") > 0); //WILL RETURN FALSE AS NO TENANCY REF 9
            System.out.println(client.updateContract(9, "UPDATED CONTRACT", new Date(), 24) > 0);       //WILL RETURN FALSE AS NO CONTRACT REF 9
            System.out.println("\nRent Account 1 Balance: " + client.getRentAccount(1).getBalance());
            System.out.println(client.createRentAccTransaction(1, 1, 2, 500.00, true, new Date(), "Test Transaction") > 0);
            System.out.println("Rent Account 1 Balance (after transaction): " + client.getRentAccount(1).getBalance());
            System.out.println("\nContract Ref 1 (Name): " + client.getContract(1).getAgreementName());
            System.out.println("\nContracts for Office EDM: " + client.getContracts(null, null, null, null, null, null, null, null, "EDM", null, null).size());
            System.out.println("\nContracts for Office BHP: " + client.getContracts(null, null, null, null, null, null, null, null, "BHP", null, null).size());
            System.out.println("\nNumber of Tenancies by Employee Ref 1: " + client.getNumberOfTenanciesByEmployee(1, date.getTime(), date2.getTime()));
            System.out.println("\nNumber of Contracts by Employee Ref 1: " + client.getNumberOfContractsByEmployee(1, date.getTime(), date2.getTime()));
            System.out.println("\nNumber of Leases by Employee Ref 1: " + client.getNumberOfLeasesByEmployee(1, date.getTime(), date2.getTime()));
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        TestClient test = new TestClient();
        test.run();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE OCCURED");
    }
}
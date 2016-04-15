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
import client_gui.lease.LeaseDetails;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.property.PropertyDetails;
import client_gui.rentAcc.RentAccDetails;
import client_gui.tenancy.TenancyDetails;
import interfaces.ApplicationInterface;
import interfaces.ContractInterface;
import interfaces.EmployeeAccountInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
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
public class TestGUI {
    public static void main(String[] args) {
        
        try {
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "LIVE", "DEDWARDS", "DEDWARDS"});
            
            PropertyInterface tenancy = client.getProperty(2);
            
            PropertyDetails test = new PropertyDetails(client, tenancy);
            test.setVisible(true);
            
            test.setVisible(true);
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TestGUI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import client_gui.employee.EmployeeDetails;
import interfaces.EmployeeInterface;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TestClient5  implements Observer {
    public void run()  {
        try {
            System.out.println("********************Running Client Test********************");
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "TEST", "TEST7", "TEST7password"});
            client.addObserver(this);
            
            EmployeeInterface employee = client.getEmployee(7);
            
            EmployeeDetails empGUI = new EmployeeDetails(client, employee);
            empGUI.setVisible(true);
            
            EmployeeInterface employee2 = client.getEmployee(8);
            
            EmployeeDetails empGUI2 = new EmployeeDetails(client, employee2);
            empGUI2.setVisible(true);
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TestClient5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        TestClient5 test = new TestClient5();
        test.run();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE OCCURED");
    }
}
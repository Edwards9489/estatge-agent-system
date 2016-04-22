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
public class TestClient3  implements Observer {
    public void run()  {
        try {
            System.out.println("********************Running Client Test********************");
            ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "TEST", "SDOLAN", "SDOLANpassword"});
            client.addObserver(this);   
            
            //System.out.println("Create Tenancy: " + client.createTenancy(new Date(), 12, 4, 5, "PRO", "MNC"));
            
            //System.out.println("Create Address: " + client.createAddress("", "", "", "", "170", "Parr Close", "Edmonton", "London", "England", "N18 2TX", ""));
            
//            Calendar date = Calendar.getInstance();
//            date.set(1990, 01, 01);
//            
//            Calendar date1 = Calendar.getInstance();
//            date1.set(2016, 02, 01);
            
            //System.out.println("Create Person: " + client.createPerson("MR", "TEST9", "TEST9", "TEST9", date.getTime(), "TEST9", "M", "SNGL", "GBR", "ENGL", "ENGL", "STR", "PNTS", 22, date1.getTime()));
            
            //System.out.println("Landlord: " + client.createLandlord(17));
            
            //System.out.println("Create Property: " + client.createProperty(22, new Date(), "FLT", "NA"));
            
            //System.out.println("Create Age Property Element: " + client.createPropertyElement(17, "AGE", new Date(), false, "Victorian", null, ""));
            
            //System.out.println("Create Baths Property Element: " + client.createPropertyElement(17, "BATHS", new Date(), false, "1", null, ""));
            
            //System.out.println("Create Beds Property Element: " + client.createPropertyElement(17, "BEDS", new Date(), false, "2", null, ""));
            
            //System.out.println("Create Consv Property Element: " + client.createPropertyElement(17, "CONSV", new Date(), false, "N", null, ""));
            
            //System.out.println("Create Driv Property Element: " + client.createPropertyElement(17, "DRIV", new Date(), false, "N", null, ""));
            
            //System.out.println("Create Grdn Property Element: " + client.createPropertyElement(17, "GRDN", new Date(), false, "N", null, ""));
            
            //System.out.println("Create Kitc Property Element: " + client.createPropertyElement(17, "KITC", new Date(), false, "S", null, ""));
            
            //System.out.println("Create Recp Property Element: " + client.createPropertyElement(17, "RECP", new Date(), false, "1", null, ""));
            
            //System.out.println("Create Rent Property Element: " + client.createPropertyElement(17, "RENT", new Date(), true, null, 700.00, ""));
            
            //System.out.println("Create Lvl Property Element: " + client.createPropertyElement(17, "LVL", new Date(), false, "First Floor", null, ""));
            
            //System.out.println("Create Cln Property Element: " + client.createPropertyElement(17, "CLN", new Date(), true, null, 100.00, ""));
            
            //System.out.println("Create Lease: " + client.createLease(new Date(), 12, 17, false, 600.00, "EDM"));
            
            //System.out.println("Create Address: " + client.createAddress(null, null, null, null, "1", "Culpepper Close", "Edmonton", "London", "England", "N18 2DE", ""));
            
            //System.out.println("Create Person: " + client.createPerson("MR", "TEST10", "TEST10", "TEST10", date.getTime(), "TEST10", "M", "SNGL", "GBR", "ENGL", "ENGL", "STR", "PNTS", 23, date1.getTime()));
            
            //System.out.println("Create Employee: " + client.createEmployee(18, "TEST10", "TEST10password"));
            
            //System.out.println("Set Employee Answer: " + client.setEmployeeMemorableLocation("TEST"));
            
            //System.out.println("Create Contract: " + client.createContract(new Date(), 12, 9, "SALES", "EDM"));
            
            EmployeeInterface employee = client.getEmployee(1);
            
            EmployeeDetails empGUI = new EmployeeDetails(client, employee);
            empGUI.setVisible(true);
            
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
            Logger.getLogger(TestClient3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        TestClient3 test = new TestClient3();
        test.run();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE OCCURED");
    }
}
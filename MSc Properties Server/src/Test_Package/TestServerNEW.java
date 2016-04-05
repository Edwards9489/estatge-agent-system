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
public class TestServerNEW {

    public static void main(String[] args) {
        try {
            System.out.println("Running server test\n");
            
            ServerImpl test = new ServerImpl("LIVE", "127.0.0.1", "root", "", 3306);
            
            Calendar startDate = Calendar.getInstance();
            startDate.set(2000, 01, 04);
            
            Calendar endDate = Calendar.getInstance();
            endDate.set(2020, 12, 12);
            
            //System.out.println(test.getTenancies(null, null, null, null, null, null, null, null, null, "EDM", null, null, null));
            //System.out.println(test.getTenanciesByOffice("EDM", startDate.getTime(), endDate.getTime()).size());
            //System.out.println(test.generateOfficeReport(startDate.getTime(), endDate.getTime()));
            //test.generateReport(startDate.getTime(), endDate.getTime());
            //System.out.println(test.generateEmployeeReport(startDate.getTime(), endDate.getTime()));
            //System.out.println(test.generateOfficeReport(startDate.getTime(), endDate.getTime()));
            //System.out.println(test.generateOfficeFinanceReport(startDate.getTime(), endDate.getTime()));
            //System.out.println(test.generateFinanceReport(startDate.getTime(), endDate.getTime()));
            String email = "dwayne.edwards9489@outlook.com";
            int eRef = 1;
            String username = "DEDWARDS";
            String answer = "TEST";
            System.out.println(test.forgotPassword(email, eRef, username, answer));
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            
            
            
            
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
            Logger.getLogger(TestServerNEW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
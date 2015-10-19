/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Database;
import server_application.ElementImpl;
import server_application.ModifiedBy;

/**
 *
 * @author Dwayne
 */
public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running database test");
        
        Database db = new Database();
        
//        try {
//            db.connect();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        Element title = new ElementImpl("MR", "Mr", "DEDWARDS");
//        
//        try {
//            db.createTitle(title);
//        } catch (SQLException ex) {
//            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ModifiedByInterface modTest = new ModifiedBy("Updated Title", "JBLOGGS");
//        title.updateElement("TEST", true, modTest);
        
        Element title = db.getTitle("MR");
        System.out.println(title.toString());
        
        db.disconnect();
    }
}
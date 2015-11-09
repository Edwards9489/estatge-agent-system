/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Database;
import server_application.ModifiedBy;

/**
 *
 * @author Dwayne
 */
public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running database test");
        
        Database db = new Database(null, null, null, null, null);
        
        Element title = db.getTitle("MR");
        if(title != null) {
            System.out.println(title.toString());
        }
        
        
        try {
            db.createTitle(title);
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ModifiedByInterface modTest = new ModifiedBy("DEDWARDS", new Date(), "Amended Title Description");
        title.updateElement("MR - Amended", true, modTest);
        
        
        System.out.println("\n*******************************\n");
        System.out.println(title.getModifiedBy());
        db.disconnect();
    }
}
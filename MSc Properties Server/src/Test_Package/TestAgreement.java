/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import java.util.Date;
import server_application.Agreement;

/**
 *
 * @author Dwayne
 */
public class TestAgreement {
    public static void main(String[] args) {
        System.out.println("********************Running Agreement Test********************");
        
        System.out.println("\nCreating agreement\n\n\n\n");
        
        Agreement test1 = new Agreement(1, "NEWOFFICE", new Date(), 12, "DEDWARDS1", "NEWOFFICE");
        System.out.println("test 1 - 12 months");
        
        Agreement test2 = new Agreement(1, "NEWOFFICE", new Date(), -15, "DEDWARDS2", "NEWOFFICE");
        System.out.println("test 2 - subtract 15 months");
        
        Agreement test3 = new Agreement(1, "NEWOFFICE", new Date(), 6, "DEDWARDS3", "NEWOFFICE");
        System.out.println("test 3 - 6 months");
        
        Agreement test4 = new Agreement(1, "NEWOFFICE", new Date(), 26, "DEDWARDS4", "NEWOFFICE");
        
        System.out.println("test 4 - 26 months");
    }
}
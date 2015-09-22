/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import java.util.Date;
import server_application.Account;

/**
 *
 * @author Dwayne
 */
public class TestAccount {
    public static void main(String[] args) {
        System.out.println("********************Running Address Test********************");
        
        System.out.println("\nCreating Account\n\n\n\n");
        Account test1 = new Account(1, "Mr Dwayne Leroy Edwards", new Date(), "DEDWARDS");
    }
}
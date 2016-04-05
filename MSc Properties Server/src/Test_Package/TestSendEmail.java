/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import server_application.SendEmail;

/**
 *
 * @author Dwayne
 */
public class TestSendEmail {
    public static void main(String[] args) {
        System.out.println("********************Running Account Test********************");

        System.out.println("\n****** Creating Send Email Object ******\n");
        
        SendEmail test = new SendEmail("mscproperties.online@gmail.com", "Toxic9489", "smtp.gmail.com");
        
        String to = "ricky1105@yahoo.com";
        String subject = "TEST";
        String message = "TEST";
        test.sendEmail(to, subject, message);
    }
}
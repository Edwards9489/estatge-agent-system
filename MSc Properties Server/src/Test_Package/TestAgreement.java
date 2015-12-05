/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.ModifiedByInterface;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Agreement;
import server_application.ModifiedBy;

/**
 *
 * @author Dwayne
 */
public class TestAgreement {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Agreement Test********************");
            
            System.out.println("\n****** Creating agreement ******\n");
            
            System.out.println("****** Testing Accessor Methods ******\n");
            
            
            Date date = new Date();
            date.setMonth(0);
            date.setDate(1);
            
            Agreement test1 = new Agreement(1, "Mr Dwayne Leroy Edwards", date, 6, 6, "DEDWARDS1", new Date(), "NEWOFFICE");
            
            System.out.println(test1.getAgreementRef());
            System.out.println(test1.getAgreementName());
            System.out.println(test1.getOfficeCode());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getActualEndDate());
            System.out.println(test1.isCurrent());
            System.out.println(test1.getCreatedBy());
            System.out.println(test1.getCreatedDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            System.out.println(test1.getModifiedBy());
            
            
            System.out.println("\n\n ****** Testing Mutator Methods ******");
            System.out.println("\n****** Updating Agreement ******");
            
            ModifiedByInterface modTest = new ModifiedBy("JBLOGGS", new Date(), "Updated Agreement");
            Date date1 = new Date();
            date1.setMonth(0);
            date1.setDate(2);
            test1.updateAgreement(test1.getAgreementName(), date1, test1.getLength(), modTest);
            
            System.out.println("\n" + test1.getAgreementName());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest1 = new ModifiedBy("DEDWARDS", new Date(), "Updated Agreement");
            test1.updateAgreement(test1.getAgreementName(), test1.getStartDate(), -12, modTest1);
            
            System.out.println("\n" + test1.getAgreementName());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest2 = new ModifiedBy("JBLOGGS", new Date(), "Updated Agreement");
            test1.updateAgreement(test1.getAgreementName(), test1.getStartDate(), 9, modTest2);
            
            System.out.println("\n" + test1.getAgreementName());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest3 = new ModifiedBy("JBLOGGS", new Date(), "Updated Agreement");
            test1.updateAgreement("NEW NAME", test1.getStartDate(), test1.getLength(), modTest3);
            
            System.out.println("\n" + test1.getAgreementName());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            
            System.out.println("\n\n****** Ending Agreement ******");
            
            ModifiedByInterface modTest4 = new ModifiedBy("DEDWARDS", new Date(), "Ended Agreement");
            Date date2 = new Date();
            date2.setMonth(0);
            date2.setDate(5);
            System.out.println(test1.isCurrent());
            System.out.println(test1.getActualEndDate());
            test1.setActualEndDate(date2, modTest4);
            System.out.println(test1.getActualEndDate());
            System.out.println(test1.isCurrent());
            
            ModifiedByInterface modTest5 = new ModifiedBy("JBLOGGS", new Date(), "Updated Agreement");
            test1.updateAgreement(test1.getAgreementName(), test1.getStartDate(), 12, modTest5);
            
            System.out.println("\n" + test1.getAgreementName());
            System.out.println(test1.getLength());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getExpectedEndDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            ModifiedByInterface modTest6 = new ModifiedBy("DEDWARDS", new Date(), "Ended Agreement");
            Date date3 = new Date();
            date3.setMonth(11);
            date3.setDate(30);
            System.out.println(test1.isCurrent());
            System.out.println(test1.getActualEndDate());
            test1.setActualEndDate(date3, modTest6);
            System.out.println(test1.getActualEndDate());
            System.out.println(test1.isCurrent());
            
            test1.updateAgreement(test1.getAgreementName(), test1.getStartDate(), 12, modTest4);
            
            System.out.println(test1);
        } catch (RemoteException ex) {
            Logger.getLogger(TestAgreement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
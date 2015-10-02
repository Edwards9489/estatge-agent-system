/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.ModifiedByInterface;
import interfaces.TransactionInterface;
import java.util.Date;
import server_application.Account;
import server_application.ModifiedBy;
import server_application.Transaction;

/**
 *
 * @author Dwayne
 */
public class TestAccount {
    public static void main(String[] args) {
        System.out.println("********************Running Account Test********************");
        
        System.out.println("\n****** Creating Test Account ******\n");
        Account test1 = new Account(1, "Mr Dwayne Leroy Edwards", new Date(), "DEDWARDS");
        
        System.out.println("****** Testing Accessor Methods ******");
        
        System.out.println(test1.getAccRef());
        System.out.println(test1.getAccName());
        System.out.println(test1.getStartDate());
        System.out.println(test1.getEndDate());
        System.out.println(test1.getBalance());
        System.out.println(test1.isNegativeInd());
        System.out.println(test1.isCurrent());
        System.out.println(test1.getLastModifiedBy()); // Test to ensure does not cause errors with index out of bounds
        System.out.println(test1.getLastModifiedDate()); // Test to ensure does not cause errors with index out of bounds
        System.out.println(test1.getModifiedBy());
        System.out.println(test1.getCreatedBy());
        System.out.println(test1.getCreatedDate());
        System.out.println(test1.getDebitTransactions());
        System.out.println(test1.getCreditTransactions());
        
        
        System.out.println("\n\n****** Testing Mutator Methods ******");
        
        System.out.println("\n****** Updating Account ******");
        
        ModifiedByInterface modTest1 = new ModifiedBy("JBLOGGS", "Updated Account");
        Date date = test1.getStartDate();
        date.setMonth(4);
        
        test1.updateAccount(date, "TEST", modTest1);
        
        System.out.println("\n" + test1.getAccRef());
        System.out.println(test1.getAccName());
        System.out.println(test1.getStartDate());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate());
        
        System.out.println("\n****** Creating Transactions ******");
        
        TransactionInterface tran1 = new Transaction(test1.getAccRef(), 2, 3, 123.35, true, new Date(), "DEDWARDS");
        TransactionInterface tran2 = new Transaction(test1.getAccRef(), 2, 3, 210.98, true, new Date(), "DEDWARDS");
        TransactionInterface tran3 = new Transaction(test1.getAccRef(), 3, 2, 323.98, false, new Date(), "JBLOOGS");
        TransactionInterface tran4 = new Transaction(test1.getAccRef(), 2, 3, -78.00, true, new Date(), "DEDWARDS");
        TransactionInterface tran5 = new Transaction(test1.getAccRef(), 2, 3, 379.10, true, new Date(), "DEDWARDS");
        TransactionInterface tran6 = new Transaction(test1.getAccRef(), 3, 2, 323.97, false, new Date(), "JBLOOGS");
        TransactionInterface tran7 = new Transaction(test1.getAccRef(), 2, 3, 237.21, true, new Date(), "DEDWARDS");
        TransactionInterface tran8 = new Transaction(test1.getAccRef(), 3, 2, 1000.00, false, new Date(), "JBLOOGS");
        
        test1.createTransaction(tran1);
        
        System.out.println("\n" + test1.getBalance());
        System.out.println(test1.isNegativeInd());
        
        test1.createTransaction(tran2);
        test1.createTransaction(tran3);
        
        System.out.println("\n" + test1.getBalance());
        System.out.println(test1.isNegativeInd());
        
        test1.createTransaction(tran4);
        test1.createTransaction(tran5);
        test1.createTransaction(tran6);
        
        System.out.println("\n" + test1.getBalance());
        System.out.println(test1.isNegativeInd());        
        
        test1.createTransaction(tran7);
        
        System.out.println("\n" + test1.getBalance());
        System.out.println(test1.isNegativeInd());    
        
        test1.createTransaction(tran8);
        
        System.out.println("\n" + test1.getBalance());
        System.out.println(test1.isNegativeInd());    
        
        
        
        System.out.println("\n\n****** Ending Account ******");
        
        ModifiedByInterface modTest2 = new ModifiedBy("JBLOGGS", "Ended Account");
        
        System.out.println("\n" + test1.getEndDate());
        System.out.println(test1.isCurrent());
        
        Date date1 = new Date();
        date1.setMonth(7);
        test1.setEndDate(date1, modTest2);
        
        System.out.println(test1.getEndDate());
        System.out.println(test1.isCurrent());
        
        
        System.out.println("\n\n" + test1);
        
        
        // setEndDate(Date endDate, ModifiedByInterface modifiedBy)
        // updateAccount(Date startDate, String accName, ModifiedByInterface modifiedBy)
        // createTransaction(TransactionInterface transaction)
        
        // getAccRef()
        // getAccName()
        // getStartDate()
        // getEndDate()
        // getBalance()
        // isNegativeInd()
        // isCurrent()
        // getLastModifiedBy()
        // getLastModifiedDate()
        // getModifiedBy()
        // getCreatedBy()
        // getCreatedDate()
        // getDebitTransactions()
        // getCreditTransactions()
        // toString()
    }
}
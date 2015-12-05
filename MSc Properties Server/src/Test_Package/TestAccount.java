/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.TransactionInterface;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Account;
import server_application.ModifiedBy;
import server_application.NoteImpl;
import server_application.Transaction;

/**
 *
 * @author Dwayne
 */
public class TestAccount {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Account Test********************");
            
            System.out.println("\n****** Creating Test Account ******\n");
            Calendar dob = Calendar.getInstance();
            dob.set(2015, 1, 10);
            Account test1 = new Account(1, "Mr Dwayne Leroy Edwards", "TEST", dob.getTime(), "DEDWARDS", new Date());
            
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
            System.out.println(test1.getTransactions());
            
            
            System.out.println("\n\n****** Testing Mutator Methods ******");
            
            System.out.println("\n****** Updating Account ******");
            
            ModifiedByInterface modTest1 = new ModifiedBy("JBLOGGS", new Date(), "Updated Account");
            Date date1 = test1.getStartDate();
            date1.setDate(2);
            
            test1.updateAccount(date1, "TEST", modTest1);
            
            System.out.println("\n" + test1.getAccRef());
            System.out.println(test1.getAccName());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            System.out.println("\n****** Creating Transactions ******");
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST", "DEDWARDS", new Date());
            Note note7 = new NoteImpl(7, "TEST", "DEDWARDS", new Date());
            Note note8 = new NoteImpl(8, "TEST", "DEDWARDS", new Date());
            Note note9 = new NoteImpl(9, "TEST", "DEDWARDS", new Date());
            Note note10 = new NoteImpl(10, "TEST", "DEDWARDS", new Date());
            Note note11 = new NoteImpl(11, "TEST", "DEDWARDS", new Date());
            Note note12 = new NoteImpl(12, "TEST", "DEDWARDS", new Date());
            Note note13 = new NoteImpl(13, "TEST", "DEDWARDS", new Date());
            Note note14 = new NoteImpl(14, "TEST", "DEDWARDS", new Date());
            Note note15 = new NoteImpl(15, "TEST", "DEDWARDS", new Date());
            TransactionInterface tran1 = new Transaction(1, test1.getAccRef(), 2, 3, 123.35, true, new Date(), note, "DEDWARDS", new Date());
            TransactionInterface tran2 = new Transaction(2, test1.getAccRef(), 2, 3, 210.98, true, new Date(), note2, "DEDWARDS", new Date());
            TransactionInterface tran3 = new Transaction(3, test1.getAccRef(), 3, 2, 323.98, false, new Date(), note3, "JBLOOGS", new Date());
            TransactionInterface tran4 = new Transaction(4, test1.getAccRef(), 2, 3, -78.00, true, new Date(), note4, "DEDWARDS", new Date());
            TransactionInterface tran5 = new Transaction(5, test1.getAccRef(), 2, 3, 379.10, true, new Date(), note5, "DEDWARDS", new Date());
            TransactionInterface tran6 = new Transaction(6, test1.getAccRef(), 3, 2, 323.97, false, new Date(), note6, "JBLOOGS", new Date());
            TransactionInterface tran7 = new Transaction(7, test1.getAccRef(), 2, 3, 237.21, true, new Date(), note7, "DEDWARDS", new Date());
            TransactionInterface tran8 = new Transaction(8, test1.getAccRef(), 3, 2, 1000.00, false, new Date(), note8, "JBLOOGS", new Date());
            TransactionInterface tran9 = new Transaction(9, test1.getAccRef(), 3, 2, 500.00, true, new Date(), note9, "DEDWARDS", new Date());
            
            
            test1.createTransaction(tran1, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            test1.createTransaction(tran2, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            test1.createTransaction(tran3, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            test1.createTransaction(tran4, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            test1.createTransaction(tran5, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            test1.createTransaction(tran6, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            test1.createTransaction(tran7, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            test1.createTransaction(tran8, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            
            
            System.out.println("\n\n****** Ending Account ******");
            
            ModifiedByInterface modTest2 = new ModifiedBy("JBLOGGS", new Date(), "Ended Account");
            
            System.out.println("\n" + test1.getEndDate());
            System.out.println(test1.isCurrent());
            
            Date date2 = new Date();
            date2.setMonth(0);
            date2.setDate(5);
            test1.setEndDate(date2, modTest2);
            
            System.out.println(test1.getEndDate());
            System.out.println(test1.isCurrent());
            
            test1.createTransaction(tran9, new ModifiedBy("JBLOGGS", new Date(), "Created Transaction"));
            
            System.out.println("\n" + test1.getBalance());
            System.out.println(test1.isNegativeInd());
            
            ModifiedByInterface modTest3 = new ModifiedBy("DEDWARDS", new Date(), "Updating Account");
            Date date3 = new Date();
            date3.setMonth(0);
            date3.setDate(3);
            test1.updateAccount(date3, "TEST2", modTest3);
            
            System.out.println(test1.getAccName());
            System.out.println(test1.getStartDate());
            System.out.println(test1.getLastModifiedBy());
            System.out.println(test1.getLastModifiedDate());
            
            Date date4 = new Date();
            date4.setMonth(11);
            date4.setDate(30);
            test1.setEndDate(date4, modTest2);
            
            System.out.println(test1.getEndDate());
            System.out.println(test1.isCurrent());
            
            test1.updateAccount(date3, "TEST2", modTest3);
            
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
        } catch (RemoteException ex) {
            Logger.getLogger(TestAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
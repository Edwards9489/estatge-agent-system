/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import classes.Utils;
import interfaces.Document;
import interfaces.Note;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.DocumentImpl;
import server_application.NoteImpl;

/**
 *
 * @author Dwayne
 */
public class TestDocument {
    
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Document Test********************");
            
            System.out.println("\n****** Creating Test Document ******\n");
            
            File file = new File("D:\\TESTING\\TESTv1.pdf");
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Document test1 = new DocumentImpl(1, file, note, "DEDWARDS", new Date());
            
            System.out.println("****** Testing Accessor Methods ******");
            
            System.out.println(test1.getDocumentName());
            System.out.println(test1.getDocumentPath());
            System.out.println(Utils.getFileName(test1.getDocumentName()));
            System.out.println(Utils.getFileNameWithoutVersion(test1.getDocumentName()));
            
            System.out.println(test1.getDocumentPath() + "\\" + Utils.getFileNameWithoutVersion(test1.getDocumentName()) + (test1.getPreviousVersions().size() + 2));
        } catch (RemoteException ex) {
            Logger.getLogger(TestDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

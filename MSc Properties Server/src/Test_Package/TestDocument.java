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
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.DocumentImpl;
import server_application.ModifiedBy;
import server_application.NoteImpl;

/**
 *
 * @author Dwayne
 */
public class TestDocument {
    
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("********************Running Document Test********************");
            
            System.out.println("\n****** Creating Test Document ******\n");
            
            File file = new File("D:\\TESTING\\TESTv1.pdf");
            File file2 = new File("test");
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            DocumentImpl test1 = new DocumentImpl(1, file, note, "DEDWARDS", new Date());
            
            System.out.println("****** Testing Accessor Methods ******");
            
            System.out.println(test1.getDocumentName());
            System.out.println(test1.getDocumentPath());
            System.out.println(test1.getDocument().getAbsolutePath());
            System.out.println(test1.getDocument().getCanonicalPath());
            System.out.println(test1.getDocument().getName());
            System.out.println(test1.getDocument().getParent());
            System.out.println(test1.getDocument().getPath());
            System.out.println(test1.getDocument().toString());
            System.out.println(Utils.getFileName(test1.getDocumentName()));
            System.out.println(Utils.getFileNameWithoutVersion(test1.getDocumentName()));
            System.out.println("********************");
            System.out.println(test1.getPreviousVersions().size());
            test1.createNewVersion(file2, new ModifiedBy("test", new Date(), "DEDWARDS"));
            System.out.println(test1.getPreviousVersions().size());
            
            System.out.println(test1.getDocumentPath() + "\\" + Utils.getFileNameWithoutVersion(test1.getDocumentName()) + (test1.getPreviousVersions().size() + 2) + "." + Utils.getFileExtension(test1.getDocumentName()));
        } catch (RemoteException ex) {
            Logger.getLogger(TestDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;
import interfaces.Element;
import interfaces.Note;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Contact;
import server_application.ElementImpl;
import server_application.NoteImpl;

/**
 *
 * @author Dwayne
 */
public class TestContact {
    public void main(String[] args) {
        try {
            System.out.println("****************Running Contact Test****************");
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST", "DEDWARDS", new Date());
            Note note5 = new NoteImpl(5, "TEST", "DEDWARDS", new Date());
            Note note6 = new NoteImpl(6, "TEST", "DEDWARDS", new Date());
            
            Element type1 = new ElementImpl("TEL", "Telephone", note, "DEDWARDS", new Date());
            Element type2 = new ElementImpl("EMAIL", "E-Mail", note2, "DEDWARDS", new Date());
            Element type3 = new ElementImpl("FAX", "Fax", note3, "DEDWARDS", new Date());
            
            Contact contact1 = new Contact(1, type1, "07872395479", new Date(), note4, "DEDWARDS", new Date());
            Contact contact2 = new Contact(2, type2, "dwayne.edwards@enfield.gov.uk", new Date(), note5, "DEDWARDS", new Date());
            Contact contact3 = new Contact(3, type3, "02083794532", new Date(), note6, "DEDWARDS", new Date());
            
            System.out.println();
            System.out.println(contact1.getContactType());
            //contact1.setContactType(type2, "DEDWARDS");
            System.out.println(contact1.getContactType());
            
            
            System.out.println(contact1.getContactValue());
            //contact1.setContactValue("02084824253", "DEDWARDS");
            System.out.println(contact1.getContactValue());
            
            System.out.println(contact1.getStartDate());
            //contact1.setStartDate(new Date(), "DEDWARDS");
            System.out.println(contact1.getStartDate());
            
            System.out.println(contact1.getStartDate());
            //contact1.setStartDate(new Date(), "DEDWARDS");
            System.out.println(contact1.getStartDate());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        } catch (RemoteException ex) {
            Logger.getLogger(TestContact.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
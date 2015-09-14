/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;
import java.util.Date;
import server_application.Contact;
import server_application.ContactType;

/**
 *
 * @author Dwayne
 */
public class TestContact {
    public void main(String[] args) {
        System.out.println("****************Running Contact Test****************");
        
        ContactType type1 = new ContactType("TEL", "Telephone", "DEDWARDS");
        ContactType type2 = new ContactType("EMAIL", "E-Mail", "DEDWARDS");
        ContactType type3 = new ContactType("FAX", "Fax", "DEDWARDS");
        
        Contact contact1 = new Contact(type1, "07872395479", new Date(), "DEDWARDS");
        Contact contact2 = new Contact(type2, "dwayne.edwards@enfield.gov.uk", new Date(), "DEDWARDS");
        Contact contact3 = new Contact(type3, "02083794532", new Date(), "DEDWARDS");
        
        System.out.println();
        System.out.println(contact1.getContactType());
        contact1.setContactType(type2, "DEDWARDS");
        System.out.println(contact1.getContactType());
        
        
        System.out.println(contact1.getContactValue());
        contact1.setContactValue("02084824253", "DEDWARDS");
        System.out.println(contact1.getContactValue());
        
        System.out.println(contact1.getStartDate());
        contact1.setStartDate(new Date(), "DEDWARDS");
        System.out.println(contact1.getStartDate());
        
        System.out.println(contact1.getStartDate());
        contact1.setStartDate(new Date(), "DEDWARDS");
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
        
    }
}
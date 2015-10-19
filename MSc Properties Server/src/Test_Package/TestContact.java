/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;
import interfaces.Element;
import java.util.Date;
import server_application.Contact;
import server_application.ElementImpl;

/**
 *
 * @author Dwayne
 */
public class TestContact {
    public void main(String[] args) {
        System.out.println("****************Running Contact Test****************");
        
        Element type1 = new ElementImpl("TEL", "Telephone", "DEDWARDS", new Date());
        Element type2 = new ElementImpl("EMAIL", "E-Mail", "DEDWARDS", new Date());
        Element type3 = new ElementImpl("FAX", "Fax", "DEDWARDS", new Date());
        
        Contact contact1 = new Contact(1, type1, "07872395479", new Date(), "DEDWARDS", new Date());
        Contact contact2 = new Contact(2, type2, "dwayne.edwards@enfield.gov.uk", new Date(), "DEDWARDS", new Date());
        Contact contact3 = new Contact(3, type3, "02083794532", new Date(), "DEDWARDS", new Date());
        
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
        
    }
}
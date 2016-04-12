/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.PersonInterface;
import interfaces.LandlordInterface;
import interfaces.TenancyInterface;
import interfaces.ApplicationInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Application;
import server_application.ElementImpl;
import server_application.InvolvedParty;
import server_application.Landlord;
import server_application.ModifiedBy;
import server_application.NoteImpl;
import server_application.Person;
import server_application.Property;
import server_application.Tenancy;

/**
 *
 * @author Dwayne
 */
public class TestTenancy {
    public static void main(String[] args) {
        try {
            System.out.println("********************Running Tenancy Test********************");
            
            Note note = new NoteImpl(1, "TEST", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST", "DEDWARDS", new Date());
            
            AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", note, "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "test", note2, "DEDWARDS", new Date());
            PersonInterface person = new Person(1, element, "Dwayne", "Leroy", "Edwards", new Date(), "JL 81 61 90 C", element,
                    element, element, element, element, element, element, null, "DEDWARDS", new Date());
            LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
            InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
            ArrayList<LandlordInterface> landlords = new ArrayList();
            landlords.add(landlord);
            
            AddressUsage addressUsage = new AddressUsage(1, address, new Date(), note3, "DEDWARDS", new Date());
            
            Application application = new Application(1, "Dwayne Leroy Edwards", new Date(), addressUsage, "DEDWARDS", new Date());
        
            application.addInvolvedParty(invParty, null);
            
            Property prop = new Property(1, address, new Date(), element, element, "DEDWARDS", new Date());
            ModifiedByInterface modTest = new ModifiedBy("Amended Landlord", "DEDWARDS", new Date());
            prop.setLandlords(landlords, modTest);
            TenancyInterface test1 = new Tenancy(1, new Date(), 12, 12, prop, application, element, "NEWOFFICE", "DEDWARDS", new Date());
            
            System.out.println(test1.toString());
            System.out.println(test1.getAgreementRef());
            
            String temp = "";
            for(InvolvedPartyInterface invParty1 : (List<InvolvedPartyInterface>) test1.getApplication().getHousehold()) {
                temp = invParty1.getPerson().toString();
            }
            
            System.out.println(temp);
        } catch (RemoteException ex) {
            Logger.getLogger(TestTenancy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

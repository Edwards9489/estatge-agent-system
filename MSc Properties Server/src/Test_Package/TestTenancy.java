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
import interfaces.PropertyInterface;
import interfaces.LandlordInterface;
import interfaces.TenancyInterface;
import interfaces.ApplicationInterface;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import server_application.Address;
import server_application.AddressUsage;
import server_application.Application;
import server_application.ElementImpl;
import server_application.InvolvedParty;
import server_application.Landlord;
import server_application.ModifiedBy;
import server_application.Person;
import server_application.Property;
import server_application.Tenancy;

/**
 *
 * @author Dwayne
 */
public class TestTenancy {
    public static void main(String[] args) {
        System.out.println("********************Running Tenancy Test********************");
        
        // Person - int personRef, Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
        //              Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, String createdBy
        
        // Tenancy - int tenRef, Date startDate, int length, String createdBy, PropertyInterface property, Application application, Element tenType
        
        // Property - int propRef, Address address, ArrayList<Landlord> landlords, boolean management, Date acquiredDate, Element propType, Element propSubType
        
        // Lanlord - int landlordRef, Person person, String createdBy
        
        // Application - int appRef, String corrName, Date startDate, ArrayList<InvolvedPartyInterface> household, AddressUsageInterface address, String createdBy
        
        AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS", new Date());
        Element element = new ElementImpl("TEST", "test", "DEDWARDS", new Date());
        PersonInterface person = new Person(1, element, "Dwayne", "Leroy", "Edwards", new Date(), "JL 81 61 90 C", element, 
                                            element, element, element, element, element, element, null, null, "DEDWARDS", new Date());
        LandlordInterface landlord = new Landlord(1, person, "DEDWARDS", new Date());
        InvolvedParty invParty = new InvolvedParty(1, 1, person, true, true, new Date(), element, "DEDWARDS", new Date());
        ArrayList<LandlordInterface> landlords = new ArrayList();
        landlords.add(landlord);
        
        AddressUsageInterface addressUsage = new AddressUsage(1, address, new Date(), "DEDWARDS", new Date());
        
        ApplicationInterface application = new Application(1, "Mr Dwayne Leroy Edwards", new Date(), invParty, (AddressUsage) addressUsage, "DEDWARDS", new Date());
        PropertyInterface prop = new Property(1, address, true, new Date(), element, element, "DEDWARDS", new Date());
        ModifiedByInterface modTest = new ModifiedBy("Amended Landlord", new Date(), "DEDWARDS");
        prop.setLandlords(landlords, modTest);
        TenancyInterface test1 = new Tenancy(1, new Date(), 12, 12, "DEDWARDS", new Date(), prop, application, element, "NEWOFFICE");
        
        System.out.println(test1.toString());
        System.out.println(test1.getAgreementRef());
        
        String temp = "";
        for(InvolvedPartyInterface invParty1 : (List<InvolvedPartyInterface>) test1.getApplication().getHousehold()) {
            temp = invParty1.getPerson().toString();
        }
        
        System.out.println(temp);
    }
}

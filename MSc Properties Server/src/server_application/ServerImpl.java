/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressUsageInterface;
import interfaces.Server;
import interfaces.RegistryLoader;
import interfaces.Client;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
    
    ///   VARIABLES   ///
        
    // List of logged on users
    private final HashMap<String,Client> users;
    
    // The database for the server
    private final Database database;
    
    // List of reference counters
    private int personRef; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int invPartyRef; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int landlordRef;
    private int employeeRef;
    private int appRef; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int propRef; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int tenRef;
    private int leaseRef;
    private int contractRef;
    private int rentAccRef;
    private int leaseAccRef;
    private int employeeAccRef;
    private int addressRef; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    
    ///   CONSTRUCTORS ///
    
    public ServerImpl() throws RemoteException {
        super();
        users = new HashMap<>();
        this.database = new Database();
        personRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        invPartyRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        landlordRef = 1;
        employeeRef = 1;
        appRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        propRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        tenRef = 1;
        leaseRef = 1;
        contractRef = 1;
        rentAccRef = 1;
        leaseAccRef = 1;
        employeeAccRef = 1;
        addressRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    }
    
    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        // TODO code application logic here
        // start a registry on this machine
        RegistryLoader.Load();
        String myName = "Server";
        String myIP = java.net.InetAddress.getLocalHost().getHostAddress();
        String myURL = "rmi://" + myIP + "/" + myName;
        // register RMI object
        ServerImpl serv = new ServerImpl();
        Server serverStub = (Server) serv;
        //NB rebind will replace any stub with the given name 'Server'
        Naming.rebind(myName, serverStub);
        System.out.println("Server started");
    }
    
    public int createTitle(String code, String description, String createdBy) throws RemoteException {
        if(!this.database.titleExists(code)) {
            Element title = new ElementImpl(code, description, createdBy);
            this.database.createTitle(title);
            return 1;
        }
        return 0;
    }
    
    public int createGender(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.genderExists(code)) {
            Element gender = new ElementImpl(code, description, createdBy);
            this.database.createGender(gender);
            return 1;
        }
        return 0;
    }
    
    public int createMaritalStatus(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.maritalStatusExists(code)) {
            Element status = new ElementImpl(code, description, createdBy);
            this.database.createMaritalStatus(status);
            return 1;
        }
        return 0;
    }
    
    public int createEthnicOrigin(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.ethnicOriginExists(code)) {
            Element origin = new ElementImpl(code, description, createdBy);
            this.database.createEthnicOrigin(origin);
            return 1;
        }
        return 0;
    }
    
    public int createLanguage(String code, String description, String createdBy) {
        if(!this.database.languageExists(code)) {
            Element language = new ElementImpl(code, description, createdBy);
            this.database.createLanguage(language);
            return 1;
        }
        return 0;
    }
    
    public int createNationality(String code, String description, String createdBy) {
        if(!this.database.nationalityExists(code)) {
            Element nationality = new ElementImpl(code, description, createdBy);
            this.database.createNationality(nationality);
            return 1;
        }
        return 0;
    }
    
    public int createSexuality(String code, String description, String createdBy) {
        if(!this.database.sexualityExists(code)) {
            Element sexuality = new ElementImpl(code, description, createdBy);
            this.database.createSexuality(sexuality);
            return 1;
        }
        return 0;
    }
    
    public int createReligion(String code, String description, String createdBy) {
        if(!this.database.religionExists(code)) {
            Element religion = new ElementImpl(code, description, createdBy);
            this.database.createReligion(religion);
            return 1;
        }
        return 0;
    }
    
    public int createPropertyType(String code, String description, String createdBy) {
        if(!this.database.propTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy);
            this.database.createPropertyType(type);
            return 1;
        }
        return 0;
    }
    
    public int createPropertySubType(String code, String description, String createdBy) {
        if(!this.database.propSubTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy);
            this.database.createPropertySubType(type);
            return 1;
        }
        return 0;
    }
    
    public int createPropElement(String code, String description, String createdBy) {
        if(!this.database.propElementExists(code)) {
            Element element = new ElementImpl(code, description, createdBy);
            this.database.createPropElement(element);
            return 1;
        }
        return 0;
    }
    
    public int createPerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
        Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, String createdBy) throws RemoteException {
        Person p = new Person(personRef, title, forename, middleNames, surname, dateOfBirth, nationalInsurance, gender,
                maritalStatus, ethnicOrigin, language, nationality, sexuality, religion, null, null, createdBy);
        personRef++;
        this.database.createPerson(p);
        return p.getPersonRef();
    }
    
    
    
    public int createInvolvedParty(Person p, boolean joint, boolean main, Date start, Element relationship, String createdBy) throws RemoteException {
        InvolvedParty i = new InvolvedParty(invPartyRef, p, joint, main, start, relationship, createdBy);
        invPartyRef++;
        this.database.createInvolvedParty(i);
        return i.getInvolvedPartyRef();
    }
    
    public int createApplication(String corrName, Date startDate, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, AddressUsageInterface address, String createdBy) throws RemoteException {
        InvolvedParty mainApp = database.getInvolvedParty(this.createInvolvedParty((Person) person, joint, main, start, relationship, createdBy));
        Application a = new Application(appRef, corrName, startDate, mainApp, (AddressUsage) address, createdBy);
        appRef++;
        this.database.createApplication(a);
        return a.getApplicationRef();
    }
    
    
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) {
        
        Address address = new Address(addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country,  postcode, createdBy);
        addressRef++;
        return address.getAddressRef();
    }
    
    public int updateAddress(int addressRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) {
        if(database.addressExists(addressRef)) {
            database.getAddress(addressRef).updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, new ModifiedBy("Updated Address", createdBy));
            return 1;
        }
        return 0;
    }
    
    private AddressUsageInterface createAddressUsage(int addressRef, Date startDate, String createdBy) {
        if(database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = new AddressUsage(database.getAddress(addressRef), startDate, createdBy);
            return addressUsage;
        }
        return null;
    }
    
    public int updateAddressUsage(AddressUsageInterface addressUsage, int addressRef, Date startDate, String createdBy) {
        if(addressUsage.isCurrent() && database.addressExists(addressRef)) {
            ModifiedByInterface modifiedBy = new ModifiedBy("Updated Address", createdBy);
            addressUsage.updateAddress(database.getAddress(addressRef), startDate, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    public int createApplicationAddressUsage(int applicationRef, int addressRef, Date startDate, String createdBy) {
        if(database.applicationExists(appRef) && database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addressRef, startDate, createdBy);
            database.getApplication(applicationRef).setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created Address", createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInvolvedParty(int appRef, int personRef, boolean joint, Date start, Element relationship, String createdBy) {
        if(database.applicationExists(appRef) && database.personExists(personRef)) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef, database.getPerson(personRef), false, joint, start, relationship, createdBy);
            database.getApplication(appRef).addInvolvedParty(invParty, new ModifiedBy("Added Involved Party", createdBy));
            return 1;
        }
        return 0;
        
    }
    
    public int changeMainApp(int appRef, int invPartyRef, Date end, Element endReason, String createdBy) {
        if(database.applicationExists(appRef)) {
            Application application = database.getApplication(appRef);
            if(application.isHouseholdMember(invPartyRef)) {
                InvolvedParty party = application.getInvolvedParty(invPartyRef);
                if(!party.isMainInd()) {
                    application.changeMainApp(party.getInvolvedPartyRef(), end, endReason, new ModifiedBy("Changed Main Applicant", createdBy));
                }
                return 1;
            }
        }
        return 0;
    }
    
    public int endInvolvedParty(int appRef, int invPartyRef, Date end, Element endReason, String createdBy) {
        if(database.applicationExists(appRef)) {
            Application application = database.getApplication(appRef);
            if(application.isHouseholdMember(invPartyRef)) {
                InvolvedParty party = application.getInvolvedParty(invPartyRef);
                if(!party.isCurrent()) {
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, endReason, new ModifiedBy("Changed Main Applicant", createdBy));
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int setApplicationTenancy (int appRef, int tenancyRef, String createdBy) {
        if(database.applicationExists(appRef) && database.tenancyExists(tenancyRef)) {
            database.getApplication(appRef).setTenancy(tenancyRef, new ModifiedBy("Set Application Tenancy", createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInterestedProperty(int appRef, int propRef, String createdBy) {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).addInterestedProperty(database.getProperty(propRef), new ModifiedBy("Added Interested Property", createdBy));
            return 1;
        }
        return 0;
    }
    
    public int endInterestInProperty(int appRef, int propRef, String createdBy) {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).endInterestInProperty(database.getProperty(propRef), new ModifiedBy("Ended Interest in Property", createdBy));
            return 1;
        }
        return 0;
    }
    
    public int updateApplication(int appRef, String name, Date startDate, String createdBy) {
        if(database.applicationExists(appRef)) {
            database.getApplication(appRef).updateApplication(name, startDate, new ModifiedBy("Updated Application", createdBy));
            return 1;
        }
        return 0;
    }
    
    public int createPersonAddressUsage(int personRef, int addressRef, Date startDate, String createdBy) {
        if(database.personExists(personRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addressRef, startDate, createdBy);
            database.getPerson(personRef).createAddress(addressUsage, new ModifiedBy("Created Address", createdBy));
            return 1;
        }
        return 0;
    }
    
//    public int createAgreement(String agreementName, Date startDate, int length, String createdBy, String officeCode) {
//        if(database.officeExists(officeCode)) {
//            Agreement agreement
//            return 1;
//        }
//        return 0;
//    }
    
    //add a client to the users list
    @Override
    public void register(Client c) throws RemoteException {
        users.put(c.getName(),c);
    }

    //remove a client from the users list
    @Override
    public void unregister(Client c) throws RemoteException {
        users.remove(c.getName());
    }
    
    // returns true if the server is still running
    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }
}
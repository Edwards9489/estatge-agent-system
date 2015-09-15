/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Server;
import interfaces.RegistryLoader;
import interfaces.Client;
import interfaces.Element;
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
    
    public ServerImpl() throws RemoteException {
        super();
    }
    
    /**
     * @param args the command line arguments
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
    
    // List of business data
    
    private HashMap<String,Person> people = new HashMap<>();
    private HashMap<String, InvolvedParty> involved_parties = new HashMap<>();
    private HashMap<String, Employee> employees = new HashMap<>();
    private HashMap<String, Landlord> landlord = new HashMap<>();
    
    private HashMap<String, Application> applications = new HashMap<>();
    
    private HashMap<String,Property> properties = new HashMap<>();
    
    private HashMap<String, Tenancy> tenancies = new HashMap<>();
    private HashMap<String, Lease> leases = new HashMap<>();
    
    private HashMap<String, RentAccount> rentAccounts = new HashMap<>();
    private HashMap<String, LeaseAccount> leaseAccounts = new HashMap<>();
    
    
    
    
    // List of People details
    
    private HashMap<String, Title> titles = new HashMap<>();
    private HashMap<String, Gender> genders = new HashMap<>();
    private HashMap<String, MaritalStatus> marital_statuses = new HashMap<>();
    private HashMap<String, EthnicOrigin> ethnic_origins = new HashMap<>();
    private HashMap<String, Language> languages = new HashMap<>();
    private HashMap<String, Nationality> nationalities = new HashMap<>();
    private HashMap<String, Sexuality> sexualities = new HashMap<>();
    private HashMap<String, Religion> religions = new HashMap<>();
    
    // List of Involved Party details
    
    private HashMap<String, EndReason> end_reasons = new HashMap<>();
    private HashMap<String, Relationship> relationships = new HashMap<>();
        
    // Lists of Property details
    
    private HashMap<String, Address> addresses = new HashMap<String,Address>();
    private HashMap<String, PropertyType> property_types = new HashMap<>(); // House, Flat, Bungalow
    private HashMap<String, PropertySubType> property_sub_types = new HashMap<>(); // Terraced, Semi-detached
    private HashMap<String, ElementImpl> propertyElements = new HashMap<>();
    
    // List of employee details
    
    private HashMap<String, String> employeeBenefits = new HashMap<>();
    
    // List of logged on users
    
    private HashMap<String,Client> users = new HashMap<String,Client>();
    
    
    
    // List of reference counters
    
    private int personRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int addressRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int propRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int applicationRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    private int involvedPartyRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
    
    
    public void createTitle(String code, String description, String createdBy) {
        ElementImpl title = null;
        if(!titleExists(code)) {
            title = new Title(code, description, createdBy);
            titles.put(title.getCode(), (Title) title);
        }
        //return type;
    }
    
    public void createGender(String code, String description, String createdBy) {
        ElementImpl gen = null;
        if(!genderExists(code)) {
            gen = new Gender(code, description, createdBy);
            genders.put(gen.getCode(), (Gender) gen);
        }
        //return type;
    }
    
    public void createMaritalStatus(String code, String description, String createdBy) {
        ElementImpl status = null;
        if(!maritalStatusExists(code)) {
            status = new MaritalStatus(code, description, createdBy);
            marital_statuses.put(status.getCode(), (MaritalStatus) status);
        }
        //return type;
    }
    
    public void createEthnicOrigin(String code, String description, String createdBy) {
        ElementImpl origin = null;
        if(!ethnicOriginExists(code)) {
            origin = new EthnicOrigin(code, description, createdBy);
            ethnic_origins.put(origin.getCode(), (EthnicOrigin) origin);
        }
        //return type;
    }
    
    public void createLanguage(String code, String description, String createdBy) {
        ElementImpl language = null;
        if(!languageExists(code)) {
            language = new Language(code, description, createdBy);
            languages.put(language.getCode(), (Language) language);
        }
        //return type;
    }
    
    public void createNationality(String code, String description, String createdBy) {
        ElementImpl nationality = null;
        if(!nationalityExists(code)) {
            nationality = new Gender(code, description, createdBy);
            nationalities.put(nationality.getCode(), (Nationality) nationality);
        }
        //return type;
    }
    
    public void createSexuality(String code, String description, String createdBy) {
        ElementImpl sexuality = null;
        if(!sexualityExists(code)) {
            sexuality = new Sexuality(code, description, createdBy);
            sexualities.put(sexuality.getCode(), (Sexuality) sexuality);
        }
        //return type;
    }
    
    public void createReligion(String code, String description, String createdBy) {
        ElementImpl religion = null;
        if(!religionExists(code)) {
            religion = new Religion(code, description, createdBy);
            religions.put(religion.getCode(), (Religion) religion);
        }
        //return type;
    }
    
    public void createAddress(int addressRef, String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String town, String postcode, String createdBy) {
        
        //Address a = new Address(addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, town, postcode,createdBy);
        addressRef++;
        //addresses.put(Integer.toString(a.getAddressRef()), a);
        // return a; - amend to accessor once Interfaces are set up
    }
    
    public void createPropertyType(String code, String description, String createdBy) {
        ElementImpl type = null;
        if(!propTypeExists(code)) {
            type = new PropertyType(code, description, createdBy);
            property_types.put(type.getCode(), (PropertyType) type);
        }
        //return type;
    }
    
    public void createPropertySubType(String code, String description, String createdBy) {
        ElementImpl type = null;
        if(!propSubTypeExists(code)) {
            type = new PropertySubType(code, description, createdBy);
            property_sub_types.put(type.getCode(), (PropertySubType) type);
        }
        //return type;
    }
    
    public void createPropElement(String code, String description, String createdBy) {
        ElementImpl element = null;
        if(!propElementExists(code)) {
            element = new ElementImpl(code, description, createdBy);
            propertyElements.put(element.getCode(), element);
        }
    }
    
    public void createPerson(Element title, String forename, String surname, Date dateOfBirth, Element gender) throws RemoteException {
        Person p = new Person(personRef, (Title) title, forename, surname, dateOfBirth, (Gender) gender);
        personRef++;
        people.put(Integer.toString(p.getPersonRef()), p);
        // return p; - amend to accessor once Interfaces are set up
    }
    
    public void createInvolvedParty(Person p, boolean joint, boolean main, Date start, Relationship relationship, String createdBy) throws RemoteException {
        InvolvedParty i = new InvolvedParty(involvedPartyRef, p, joint, main, start, relationship, createdBy);
        involvedPartyRef++;
        involved_parties.put(Integer.toString(i.getInvolvedPartyRef()), i);
        // return p; - amend to accessor once Interfaces are set up
    }
    
    public void createApplication(ArrayList<InvolvedParty> household, Address address, String corrName, String createdBy) {
        Application a = new Application(applicationRef, household, address, corrName, createdBy);
        applicationRef++;
        applications.put(Integer.toString(a.getApplicationRef()), a);
        // return a; - amend to accessor once Interfaces are set up
    }
    
    
    public boolean titleExists(String code) {
        return titles.containsKey(code);
    }
    
    public boolean genderExists(String code) {
        return genders.containsKey(code);
    }
    
    public boolean maritalStatusExists(String code) {
        return marital_statuses.containsKey(code);
    }
    
    public boolean ethnicOriginExists(String code) {
        return ethnic_origins.containsKey(code);
    }
    
    public boolean languageExists(String code) {
        return languages.containsKey(code);
    }
    
    public boolean nationalityExists(String code) {
        return nationalities.containsKey(code);
    }
    
    public boolean sexualityExists(String code) {
        return sexualities.containsKey(code);
    }
    
    public boolean religionExists(String code) {
        return religions.containsKey(code);
    }
    
    public boolean propTypeExists(String code) {
        return property_types.containsKey(code);
    }
    
    public boolean propSubTypeExists(String code) {
        return property_sub_types.containsKey(code);
    }
    
    public boolean propElementExists(String code) {
        return propertyElements.containsKey(code);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //add a client to the users list
    public void register(Client c) throws RemoteException {
        users.put(c.getName(),c);
    }

    //remove a client from the users list
    public void unregister(Client c) throws RemoteException {
        users.remove(c.getName());
    }
    
    // returns true if the server is still running
    public boolean isAlive() throws RemoteException {
        return true;
    }
}
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
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int addressUsageRef;
    private int contactRef;
    
    ///   CONSTRUCTORS ///
    
    public ServerImpl() throws RemoteException {
        super();
        this.users = new HashMap<>();
        this.database = new Database();
        this.personRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        this.invPartyRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        this.landlordRef = 1;
        this.employeeRef = 1;
        this.appRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        this.propRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        this.tenRef = 1;
        this.leaseRef = 1;
        this.contractRef = 1;
        this.rentAccRef = 1;
        this.leaseAccRef = 1;
        this.employeeAccRef = 1;
        this.addressRef = 1; // when I add the start up from Database content need to amend this to be initialised in the Consturctior from the highest ref
        this.addressUsageRef = 1;
        this.contactRef = 1;
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
            Element title = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createTitle(title);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int updateTitle(String code, String description, boolean current, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.titleExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Title", new Date(), modifiedBy);
            Element title = this.database.getTitle(code);
            title.updateElement(description, current, modified);
            this.database.updateTitle(title);
            return 1;
        }
        return 0;
    }
    
    public int createGender(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.genderExists(code)) {
            Element gender = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createGender(gender);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int updateGender(String code, String description, boolean current, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.genderExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Gender", new Date(), modifiedBy);
            Element gender = this.database.getGender(code);
            gender.updateElement(description, current, modified);
            this.database.updateGender(gender);
            return 1;
        }
        return 0;
    }
    
    public int createMaritalStatus(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.maritalStatusExists(code)) {
            Element status = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createMaritalStatus(status);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createEthnicOrigin(String code, String description, String createdBy)  throws RemoteException {
        if(!this.database.ethnicOriginExists(code)) {
            Element origin = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createEthnicOrigin(origin);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createLanguage(String code, String description, String createdBy) {
        if(!this.database.languageExists(code)) {
            Element language = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createLanguage(language);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createNationality(String code, String description, String createdBy) {
        if(!this.database.nationalityExists(code)) {
            Element nationality = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createNationality(nationality);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createSexuality(String code, String description, String createdBy) {
        if(!this.database.sexualityExists(code)) {
            Element sexuality = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createSexuality(sexuality);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createReligion(String code, String description, String createdBy) {
        if(!this.database.religionExists(code)) {
            Element religion = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createReligion(religion);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createPropertyType(String code, String description, String createdBy) {
        if(!this.database.propTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createPropertyType(type);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createPropertySubType(String code, String description, String createdBy) {
        if(!this.database.propSubTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createPropertySubType(type);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createPropElement(String code, String description, String createdBy) {
        if(!this.database.propElementExists(code)) {
            Element element = new ElementImpl(code, description, createdBy, new Date());
            try {
                this.database.createPropElement(element);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    public int createPerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
        Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, String createdBy) throws RemoteException {
        Person p = new Person(personRef++, title, forename, middleNames, surname, dateOfBirth, nationalInsurance, gender,
                maritalStatus, ethnicOrigin, language, nationality, sexuality, religion, null, null, createdBy, new Date());
        try {
            this.database.createPerson(p);
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p.getPersonRef();
    }
    
    
    
    public int createInvolvedParty(Person p, int appRef, boolean joint, boolean main, Date start, Element relationship, String createdBy) throws RemoteException {
        InvolvedParty i = new InvolvedParty(invPartyRef++, appRef, p, joint, main, start, relationship, createdBy, new Date());
        try {
            this.database.createInvolvedParty(i);
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i.getInvolvedPartyRef();
    }
    
    public int createApplication(String corrName, Date startDate, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, AddressUsageInterface address, String createdBy) throws RemoteException {
        InvolvedParty mainApp = database.getInvolvedParty(this.createInvolvedParty((Person) person, this.appRef, joint, main, start, relationship, createdBy));
        Application a = new Application(this.appRef++, corrName, startDate, mainApp, (AddressUsage) address, createdBy, new Date());
        try {
            this.database.createApplication(a);
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a.getApplicationRef();
    }
    
    
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) {
        
        Address address = new Address(this.addressRef++, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country,  postcode, createdBy, new Date());
        return address.getAddressRef();
    }
    
    public int updateAddress(int addressRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) {
        if(database.addressExists(addressRef)) {
            database.getAddress(addressRef).updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, new ModifiedBy("Updated Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    private AddressUsageInterface createAddressUsage(int addressRef, Date startDate, String createdBy) {
        if(database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addressRef), startDate, createdBy, new Date());
            return addressUsage;
        }
        return null;
    }
    
    public int updateAddressUsage(AddressUsageInterface addressUsage, int addressRef, Date startDate, String createdBy) {
        if(addressUsage.isCurrent() && database.addressExists(addressRef)) {
            ModifiedByInterface modifiedBy = new ModifiedBy("Updated Address", new Date(), createdBy);
            addressUsage.updateAddress(database.getAddress(addressRef), startDate, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    public int createApplicationAddressUsage(int applicationRef, int addressRef, Date startDate, String createdBy) {
        if(database.applicationExists(appRef) && database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addressRef, startDate, createdBy);
            database.getApplication(applicationRef).setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInvolvedParty(int appRef, int personRef, boolean joint, Date start, Element relationship, String createdBy) {
        if(database.applicationExists(appRef) && database.personExists(personRef)) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef, appRef, database.getPerson(personRef), false, joint, start, relationship, createdBy, new Date());
            database.getApplication(appRef).addInvolvedParty(invParty, new ModifiedBy("Added Involved Party", new Date(), createdBy));
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
                    application.changeMainApp(party.getInvolvedPartyRef(), end, endReason, new ModifiedBy("Changed Main Applicant", new Date(), createdBy));
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
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, endReason, new ModifiedBy("Changed Main Applicant", new Date(), createdBy));
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int setApplicationTenancy (int appRef, int tenancyRef, String createdBy) {
        if(database.applicationExists(appRef) && database.tenancyExists(tenancyRef)) {
            database.getApplication(appRef).setTenancy(tenancyRef, new ModifiedBy("Set Application Tenancy", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInterestedProperty(int appRef, int propRef, String createdBy) {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).addInterestedProperty(database.getProperty(propRef), new ModifiedBy("Added Interested Property", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int endInterestInProperty(int appRef, int propRef, String createdBy) {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).endInterestInProperty(database.getProperty(propRef), new ModifiedBy("Ended Interest in Property", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int updateApplication(int appRef, String name, Date startDate, String createdBy) {
        if(database.applicationExists(appRef)) {
            database.getApplication(appRef).updateApplication(name, startDate, new ModifiedBy("Updated Application", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int createPersonAddressUsage(int personRef, int addressRef, Date startDate, String createdBy) {
        if(database.personExists(personRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addressRef, startDate, createdBy);
            database.getPerson(personRef).createAddress(addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
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
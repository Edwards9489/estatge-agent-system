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
    private Database database;
    
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
    
    public int createTitle(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.titleExists(code)) {
            Element title = new ElementImpl(code, description, createdBy, new Date());
            this.database.createTitle(title);
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
    
    public int createGender(String code, String description, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.genderExists(code)) {
            Element gender = new ElementImpl(code, description, createdBy, new Date());
            this.database.createGender(gender);
            return 1;
        }
        return 0;
    }
    
    public int updateGender(String code, String description, boolean current, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.genderExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Gender", new Date(), modifiedBy);
            Element gender = this.database.getGender(code);
            gender.updateElement(description, current, modified);
            this.database.updateGender(gender);
            return 1;
        }
        return 0;
    }
    
    public int createMaritalStatus(String code, String description, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.maritalStatusExists(code)) {
            Element status = new ElementImpl(code, description, createdBy, new Date());
            this.database.createMaritalStatus(status);
            return 1;
        }
        return 0;
    }
    
    public int updateMaritalStatus(String code, String description, boolean current, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.maritalStatusExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Marital Status", new Date(), modifiedBy);
            Element status = this.database.getMaritalStatus(code);
            status.updateElement(description, current, modified);
            this.database.updateMaritalStatus(status);
            return 1;
        }
        return 0;
    }
    
    public int createEthnicOrigin(String code, String description, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.ethnicOriginExists(code)) {
            Element origin = new ElementImpl(code, description, createdBy, new Date());
            this.database.createEthnicOrigin(origin);
            return 1;
        }
        return 0;
    }
    
    public int updateEthnicOrigin(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.ethnicOriginExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Ethnic Origin", new Date(), modifiedBy);
            Element origin = this.database.getEthnicOrigin(code);
            origin.updateElement(description, current, modified);
            this.database.updateEthnicOrigin(origin);
            return 1;
        }
        return 0;
    }
    
    public int createLanguage(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.languageExists(code)) {
            Element language = new ElementImpl(code, description, createdBy, new Date());
            this.database.createLanguage(language);
            return 1;
        }
        return 0;
    }
    
    public int updateLanguage(String code, String description, boolean current, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.languageExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Language", new Date(), modifiedBy);
            Element language = this.database.getLanguage(code);
            language.updateElement(description, current, modified);
            this.database.updateLanguage(language);
            return 1;
        }
        return 0;
    }
    
    public int createNationality(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.nationalityExists(code)) {
            Element nationality = new ElementImpl(code, description, createdBy, new Date());
            this.database.createNationality(nationality);
            return 1;
        }
        return 0;
    }
    
    public int updateNationality(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.nationalityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Nationality", new Date(), modifiedBy);
            Element nationality = this.database.getNationality(code);
            nationality.updateElement(description, current, modified);
            this.database.updateNationality(nationality);
            return 1;
        }
        return 0;
    }
    
    public int createSexuality(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.sexualityExists(code)) {
            Element sexuality = new ElementImpl(code, description, createdBy, new Date());
            this.database.createSexuality(sexuality);
            return 1;
        }
        return 0;
    }
    
    public int updateSexuality(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.sexualityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Sexuality", new Date(), modifiedBy);
            Element sexuality = this.database.getSexuality(code);
            sexuality.updateElement(description, current, modified);
            this.database.updateSexuality(sexuality);
            return 1;
        }
        return 0;
    }
    
    public int createReligion(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.religionExists(code)) {
            Element religion = new ElementImpl(code, description, createdBy, new Date());
            this.database.createReligion(religion);
            return 1;
        }
        return 0;
    }
    
    public int updateReligion(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.religionExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Religion", new Date(), modifiedBy);
            Element religion = this.database.getReligion(code);
            religion.updateElement(description, current, modified);
            this.database.updateReligion(religion);
            return 1;
        }
        return 0;
    }
    
    public int createPropertyType(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy, new Date());
            this.database.createPropertyType(type);
            return 1;
        }
        return 0;
    }
    
    public int updatePropertyType(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Type", new Date(), modifiedBy);
            Element propType = this.database.getPropertyType(code);
            propType.updateElement(description, current, modified);
            this.database.updatePropertyType(propType);
            return 1;
        }
        return 0;
    }
    
    public int createPropertySubType(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propSubTypeExists(code)) {
            Element type = new ElementImpl(code, description, createdBy, new Date());
            this.database.createPropertySubType(type);
            return 1;
        }
        return 0;
    }
    
    public int updatePropertySubType(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propSubTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Sub Type", new Date(), modifiedBy);
            Element propType = this.database.getPropertySubType(code);
            propType.updateElement(description, current, modified);
            this.database.updatePropertySubType(propType);
            return 1;
        }
        return 0;
    }
    
    public int createPropElement(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propElementExists(code)) {
            Element element = new ElementImpl(code, description, createdBy, new Date());
            this.database.createPropElement(element);
            return 1;
        }
        return 0;
    }
    
    public int updatePropertyElement(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propElementExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Element", new Date(), modifiedBy);
            Element propElement = this.database.getPropElement(code);
            propElement.updateElement(description, current, modified);
            this.database.updatePropertyElement(propElement);
            return 1;
        }
        return 0;
    }
    
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy) throws RemoteException, SQLException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            Person p = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                    this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                    this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), null, null, createdBy, new Date());
            this.database.createPerson(p);
            return p.getPersonRef();
        }
        return 0;
    }
    
    public int updatePerson(int personRef, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.personExists(personRef)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Element", new Date(), modifiedBy);
            Element propElement = this.database.getPropElement(personRef);
            propElement.updateElement(description, current, modified);
            this.database.updatePropertyElement(propElement);
            return 1;
        }
        return 0;
    }    
    
    public int createInvolvedParty(Person p, int appRef, boolean joint, boolean main, Date start, Element relationship, String createdBy) throws RemoteException, SQLException {
        InvolvedParty i = new InvolvedParty(invPartyRef++, appRef, p, joint, main, start, relationship, createdBy, new Date());
        try {
            this.database.createInvolvedParty(i);
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i.getInvolvedPartyRef();
    }
    
    public int createApplication(String corrName, Date startDate, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, AddressUsageInterface address, String createdBy) throws RemoteException, SQLException {
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
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) throws RemoteException, SQLException {
        
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
    
    private AddressUsageInterface createAddressUsage(int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addressRef), startDate, createdBy, new Date());
            return addressUsage;
        }
        return null;
    }
    
    public int updateAddressUsage(AddressUsageInterface addressUsage, int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(addressUsage.isCurrent() && database.addressExists(addressRef)) {
            ModifiedByInterface modifiedBy = new ModifiedBy("Updated Address", new Date(), createdBy);
            addressUsage.updateAddress(database.getAddress(addressRef), startDate, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    public int createApplicationAddressUsage(int applicationRef, int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addressRef, startDate, createdBy);
            database.getApplication(applicationRef).setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInvolvedParty(int appRef, int personRef, boolean joint, Date start, Element relationship, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.personExists(personRef)) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef, appRef, database.getPerson(personRef), false, joint, start, relationship, createdBy, new Date());
            database.getApplication(appRef).addInvolvedParty(invParty, new ModifiedBy("Added Involved Party", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int changeMainApp(int appRef, int invPartyRef, Date end, Element endReason, String createdBy) throws RemoteException, SQLException {
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
    
    public int endInvolvedParty(int appRef, int invPartyRef, Date end, Element endReason, String createdBy) throws RemoteException, SQLException {
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
    
    public int setApplicationTenancy (int appRef, int tenancyRef, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.tenancyExists(tenancyRef)) {
            database.getApplication(appRef).setTenancy(tenancyRef, new ModifiedBy("Set Application Tenancy", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int addInterestedProperty(int appRef, int propRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).addInterestedProperty(database.getProperty(propRef), new ModifiedBy("Added Interested Property", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int endInterestInProperty(int appRef, int propRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            database.getApplication(appRef).endInterestInProperty(database.getProperty(propRef), new ModifiedBy("Ended Interest in Property", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int updateApplication(int appRef, String name, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef)) {
            database.getApplication(appRef).updateApplication(name, startDate, new ModifiedBy("Updated Application", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int createPersonAddressUsage(int personRef, int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
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
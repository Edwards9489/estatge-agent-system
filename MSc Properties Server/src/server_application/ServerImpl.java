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
import interfaces.ContactInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyInterface;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private int transactionRef;
    private int propertyElementRef;
    private int jobBenefitRef;
    private int noteRef;
    private int documentRef;
    
    private TaskGenerator scheduler;
    
    ///   CONSTRUCTORS ///
    
    public ServerImpl(String environment, String addr, String username, String password, int port) throws RemoteException {
        super();
        this.users = new HashMap<>();
        this.database = new Database(environment, addr, username, password, port);
        
        this.personRef = this.database.countPeople() + 1;
        this.invPartyRef = this.database.countInvolvedParties() + 1;
        this.landlordRef = this.database.countLandords() + 1;
        this.employeeRef = this.database.countEmployees() + 1;
        this.appRef = this.database.countApplications() + 1;
        this.propRef = this.database.countProperties() + 1;
        this.tenRef = this.database.countTenancies() + 1;
        this.leaseRef = this.database.countLeases() + 1;
        this.contractRef = this.database.countContracts() + 1;
        this.rentAccRef = this.database.countRentAccounts() + 1;
        this.leaseAccRef = this.database.countLeaseAccounts() + 1;
        this.employeeAccRef = this.database.countEmployeeAccounts() + 1;
        this.addressRef = this.database.countAddresses() + 1;
        this.transactionRef = this.database.countTransactions() + 1;
        this.addressUsageRef = this.database.countAddressUsages() + 1;
        this.contactRef = this.database.countContacts() + 1;
        this.noteRef = this.database.countNotes() + 1;
        this.documentRef = this.database.countDocuments() + 1;
        try {
            this.propertyElementRef = this.database.getPropElementCount() + 1;
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.jobBenefitRef = this.database.getJobBenefitCount() + 1;
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        scheduler = new TaskGenerator(this, 1000 * 60 * 60 * 24 * 7); // SET FOR A WEEK
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
        ServerImpl server = (ServerImpl) createServer(new String[]{"LIVE", "127.0.0.1", "root", "Toxic9489!999", "3306"});
    }
    
    public static Server createServer(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        RegistryLoader.Load();
        String environment = "LIVE";
        String addr = "127.0.0.1";
        String username = null;
        String password = null;
        Integer port = null;
        String myIP = java.net.InetAddress.getLocalHost().getHostAddress();
        if(args.length == 5) {
            environment = args[0];
            addr = args[1];
            username = args[2];
            password = args[3];
            port = Integer.parseInt(args[4]);
        }
        String myName = "Server" + environment;
        String myURL = "rmi://" + myIP + "/" + myName;
        
        // register RMI object
        ServerImpl serv = new ServerImpl(environment, addr, username, password, port);
        Server serverStub = (Server) serv;
        
        //NB rebind will replace any stub with the given name 'Server'
        System.out.println(myName);
        Naming.rebind(myName, serverStub);
        System.out.println("Server started");
        return serverStub;
    }
    
    private Note createNote(String comment, String createdBy) {
        Note note = new NoteImpl(noteRef++, comment, createdBy, new Date());
        return note;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ELEMENTS     ////////
    
    @Override
    public int createTitle(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.titleExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element title = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createTitle(title);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateTitle(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException, RemoteException {
        if(!this.database.titleExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Title", new Date(), modifiedBy);
            ElementImpl title = (ElementImpl) this.database.getTitle(code);
            title.updateElement(description, current, comment, modified);
            this.database.updateTitle(title.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteTitle(String code) throws RemoteException, SQLException {
        if(this.database.titleExists(code) && this.database.canDeleteTitle(code)) {
            this.database.deleteTitle(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createGender(String code, String description, String comment, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.genderExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element gender = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createGender(gender);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateGender(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.genderExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Gender", new Date(), modifiedBy);
            ElementImpl gender = (ElementImpl) this.database.getGender(code);
            gender.updateElement(description, current, comment, modified);
            this.database.updateGender(gender.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteGender(String code) throws RemoteException, SQLException {
        if(this.database.genderExists(code) && this.database.canDeleteGender(code)) {
            this.database.deleteGender(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createMaritalStatus(String code, String description, String comment, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.maritalStatusExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element status = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createMaritalStatus(status);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateMaritalStatus(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.maritalStatusExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Marital Status", new Date(), modifiedBy);
            ElementImpl status = (ElementImpl) this.database.getMaritalStatus(code);
            status.updateElement(description, current, comment, modified);
            this.database.updateMaritalStatus(status.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteMaritalStatus(String code) throws RemoteException, SQLException {
        if(this.database.maritalStatusExists(code) && this.database.canDeleteMaritalStatus(code)) {
            this.database.deleteMaritalStatus(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createEthnicOrigin(String code, String description, String comment, String createdBy)  throws RemoteException, SQLException {
        if(!this.database.ethnicOriginExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element origin = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createEthnicOrigin(origin);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateEthnicOrigin(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.ethnicOriginExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Ethnic Origin", new Date(), modifiedBy);
            ElementImpl origin = (ElementImpl) this.database.getEthnicOrigin(code);
            origin.updateElement(description, current, comment, modified);
            this.database.updateEthnicOrigin(origin.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteEthnicOrigin(String code) throws RemoteException, SQLException {
        if(this.database.ethnicOriginExists(code) && this.database.canDeleteEthnicOrigin(code)) {
            this.database.deleteEthnicOrigin(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createLanguage(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.languageExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element language = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createLanguage(language);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateLanguage(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(!this.database.languageExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Language", new Date(), modifiedBy);
            ElementImpl language = (ElementImpl) this.database.getLanguage(code);
            language.updateElement(description, current, comment, modified);
            this.database.updateLanguage(language.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteLanguage(String code) throws RemoteException, SQLException {
        if(this.database.languageExists(code) && this.database.canDeleteLanguage(code)) {
            this.database.deleteLanguage(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createNationality(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.nationalityExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element nationality = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createNationality(nationality);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateNationality(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.nationalityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Nationality", new Date(), modifiedBy);
            ElementImpl nationality = (ElementImpl) this.database.getNationality(code);
            nationality.updateElement(description, current, comment, modified);
            this.database.updateNationality(nationality.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteNationality(String code) throws RemoteException, SQLException {
        if(this.database.nationalityExists(code) && this.database.canDeleteNationality(code)) {
            this.database.deleteNationality(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createSexuality(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.sexualityExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element sexuality = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createSexuality(sexuality);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateSexuality(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.sexualityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Sexuality", new Date(), modifiedBy);
            ElementImpl sexuality = (ElementImpl) this.database.getSexuality(code);
            sexuality.updateElement(description, current, comment, modified);
            this.database.updateSexuality(sexuality.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteSexuality(String code) throws RemoteException, SQLException {
        if(this.database.sexualityExists(code) && this.database.canDeleteSexuality(code)) {
            this.database.deleteSexuality(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createReligion(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.religionExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element religion = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createReligion(religion);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateReligion(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.religionExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Religion", new Date(), modifiedBy);
            ElementImpl religion = (ElementImpl) this.database.getReligion(code);
            religion.updateElement(description, current, comment, modified);
            this.database.updateReligion(religion.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteReligion(String code) throws RemoteException, SQLException {
        if(this.database.religionExists(code) && this.database.canDeleteReligion(code)) {
            this.database.deleteReligion(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY ELEMENTS     ////////
    
    @Override
    public int createPropertyType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element type = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createPropertyType(type);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertyType(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Type", new Date(), modifiedBy);
            ElementImpl propType = (ElementImpl) this.database.getPropertyType(code);
            propType.updateElement(description, current, comment, modified);
            this.database.updatePropertyType(propType.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePropertyType(String code) throws RemoteException, SQLException {
        if(this.database.propTypeExists(code) && this.database.canDeletePropertyType(code)) {
            this.database.deletePropertyType(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createPropertySubType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propSubTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element type = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createPropertySubType(type);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertySubType(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propSubTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Sub Type", new Date(), modifiedBy);
            ElementImpl propType = (ElementImpl) this.database.getPropertySubType(code);
            propType.updateElement(description, current, comment, modified);
            this.database.updatePropertySubType(propType.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePropertySubType(String code) throws RemoteException, SQLException {
        if(this.database.propSubTypeExists(code) && this.database.canDeletePropertySubType(code)) {
            this.database.deletePropertySubType(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createPropertyElement(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propElementExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element element = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createPropElement(element);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertyElement(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.propElementExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Property Element", new Date(), modifiedBy);
            ElementImpl propElement = (ElementImpl) this.database.getPropElement(code);
            propElement.updateElement(description, current, comment, modified);
            this.database.updatePropertyElement(propElement.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePropertyElement(String code) throws RemoteException, SQLException {
        if(this.database.propElementExists(code) && this.database.canDeletePropertyElement(code)) {
            this.database.deletePropertyElement(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE CONTACT ELEMENTS     ////////
    
    @Override
    public int createContactType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.contactTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element contactType = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createContactType(contactType);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateContactType(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.contactTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Contact Type", new Date(), modifiedBy);
            ElementImpl contactType = (ElementImpl) this.database.getContactType(code);
            contactType.updateElement(description, current, comment, modified);
            this.database.updateContactType(contactType.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteContactType(String code) throws RemoteException, SQLException {
        if(this.database.contactTypeExists(code) && this.database.canDeleteContactType(code)) {
            this.database.deleteContactType(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE END REASONS     ////////
    
    @Override
    public int createEndReason(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.endReasonExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element endReason = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createEndReason(endReason);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateEndReason(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.endReasonExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated End Reason", new Date(), modifiedBy);
            ElementImpl endReason = (ElementImpl) this.database.getEndReason(code);
            endReason.updateElement(description, current, comment, modified);
            this.database.updateEndReason(endReason.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteEndReason(String code) throws RemoteException, SQLException {
        if(this.database.endReasonExists(code) && this.database.canDeleteEndReason(code)) {
            this.database.deleteEndReason(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE RELATIONSHIPS     ////////
    
    @Override
    public int createRelationship(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.relationshipExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element relationship = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createRelationship(relationship);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateRelationship(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.relationshipExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Relationship", new Date(), modifiedBy);
            ElementImpl relationship = (ElementImpl) this.database.getRelationship(code);
            relationship.updateElement(description, current, comment, modified);
            this.database.updateRelationship(relationship.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteRelationship(String code) throws RemoteException, SQLException {
        if(this.database.relationshipExists(code) && this.database.canDeleteRelationship(code)) {
            this.database.deleteRelationship(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE ADDRESSES     ////////
    
    @Override
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String comment, String createdBy) throws RemoteException, SQLException {
        Note note = this.createNote(comment, createdBy);
        Address address = new Address(this.addressRef++, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, note, createdBy, new Date());
        this.database.createAddress(address);
        return address.getAddressRef();
    }
    
    @Override
    public int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(database.addressExists(aRef)) {
            Address address = database.getAddress(aRef);
            address.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, new ModifiedBy("Updated Address", new Date(), modifiedBy));
            this.database.updateAddress(address.getAddressRef());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteAddress(int addrRef) throws RemoteException, SQLException {
        if(this.database.addressExists(addrRef) && this.database.canDeleteAddress(addrRef)) {
            this.database.deleteAddress(addrRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHOD TO CREATE ADDRESS USAGE     ////////
    
    private AddressUsage createAddressUsage(int addrRef, Date startDate, String createdBy) throws SQLException {
        if(database.addressExists(addrRef)) {
            Note note = this.createNote(null, createdBy);
            AddressUsage addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addrRef), startDate, note, createdBy, new Date());
            return addressUsage;
        }
        return null;
    }
    
    //////     METHOD TO CREATE CONTACT     ////////
    
    private ContactInterface createContact(String contactTypeCode, String value, Date startDate, String createdBy) throws SQLException {
        if(database.contactTypeExists(contactTypeCode)) {
            Note note = this.createNote(null, createdBy);
            ContactInterface contact = new Contact(this.contactRef++, database.getContactType(contactTypeCode), value, startDate, note, createdBy, new Date());
            return contact;
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON     ////////
    
    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                AddressUsage address = this.createAddressUsage(addrRef, addressStartDate, createdBy);
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), address, createdBy, new Date());
                this.database.createPerson(person);
                this.database.createPersonAddressUsage(address, person.getPersonRef());
                return person.getPersonRef();
            }
        }
        return 0;
    }
    
    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy) throws RemoteException, SQLException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), null, createdBy, new Date());
                this.database.createPerson(person);
                return person.getPersonRef();
            }
        }
        return 0;
    }
    
    @Override
    public int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef) && this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person", new Date(), modifiedBy);
                Person person = this.database.getPerson(pRef);
                person.updatePerson(this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), modified);
                this.database.updatePerson(person.getPersonRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deletePerson(int pRef) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.canDeletePerson(pRef)) {
            this.database.deletePerson(pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON NOTES     ////////
    
    @Override
    public int createPersonNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef)) {
            Note note = this.createNote(comment, createdBy);
            Person person = this.database.getPerson(pRef);
            person.createNote(note, new ModifiedBy("Created Person Note", new Date(), createdBy));
            this.database.updatePerson(pRef);
            this.database.createPersonNote(pRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePersonNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef)) {
            Person person = this.database.getPerson(pRef);
            if(person.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) person.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Person Note", new Date(), modifiedBy));
                this.database.updatePersonNote(pRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deletePersonNote(int pRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef)) {
            Person person = this.database.getPerson(pRef);
            if(person.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) person.getNote(nRef);
                if(!note.hasBeenModified()) {
                    person.deleteNote(nRef, new ModifiedBy("Deleted Person Note", new Date(), modifiedBy));
                    this.database.updatePerson(pRef);
                    this.database.deletePersonNote(pRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD PERSON DOCUMENT     ////////
    
    @Override
    public int createPersonDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && !this.database.getPerson(pRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Person person = this.database.getPerson(pRef);
            person.createDocument(document, new ModifiedBy("Created Person Document", new Date(), createdBy));
            this.database.updatePerson(pRef);
            this.database.createPersonDoc(pRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePersonDocument(int pRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef)) {
            Person person = this.database.getPerson(pRef);
            if(person.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) person.getDocument(dRef);
                person.deleteDocument(dRef, new ModifiedBy("Deleted Person Document", new Date(), modifiedBy));
                this.database.updatePerson(pRef);
                this.database.deletePersonDoc(pRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadPersonDocument(int pRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.getPerson(pRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON CONTACT     ////////
    
    @Override
    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = (Contact) this.createContact(contactTypeCode, value, date, createdBy);
                this.database.createPersonContact(contact, pRef);
                return contact.getContactRef();
            }
        }
        return 0;
    }
    
    @Override
    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Person person = this.database.getPerson(pRef);
            if(person.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person Contact", new Date(), modifiedBy);
                Contact contact = this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, comment, modified);
                this.database.updatePersonContact(contact.getContactRef(), person.getPersonRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deletePersonContact(int pRef, int cRef) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.contactExists(cRef) && this.database.canDeleteContact(cRef)) {
            this.database.deletePersonContact(cRef, pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ADDRESS USAGES     ////////
    
    @Override
    public int createPersonAddressUsage(int pRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.personExists(pRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addrRef, startDate, createdBy);
            database.getPerson(pRef).createAddress(addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage", new Date(), modifiedBy));
            this.database.updatePersonAddressUsage(addrUsageRef, pRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.addressUsageExists(aRef) && this.database.canDeleteAddressUsage(aRef)) {
            this.database.deletePersonAddressUsage(aRef, pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE     ////////
    
    @Override
    public int createOffice(String officeCode, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if (!this.database.officeExists(officeCode) && this.database.addressExists(addrRef)) {
            Office office = new Office(officeCode, this.database.getAddress(addrRef), startDate, createdBy, new Date());
            this.database.createOffice(office);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateOffice(String officeCode, Date startDate, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.officeExists(officeCode)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Office", new Date(), modifiedBy);
            Office office = this.database.getOffice(officeCode);
            office.setStartDate(startDate, modified);
            this.database.updateOffice(office.getOfficeCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteOffice(String code) throws RemoteException, SQLException {
        if(this.database.officeExists(code) && this.database.canDeleteOffice(code)) {
            this.database.deleteOffice(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE OFFICE NOTES     ////////
    
    @Override
    public int createOfficeNote(String officeCode, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.officeExists(officeCode)) {
            Note note = this.createNote(comment, createdBy);
            Office office = this.database.getOffice(officeCode);
            office.createNote(note, new ModifiedBy("Created Office Note", new Date(), createdBy));
            this.database.updateOffice(officeCode);
            this.database.createOfficeNote(officeCode, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateOfficeNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.officeExists(officeCode)) {
            Office office = this.database.getOffice(officeCode);
            if(office.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) office.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Office Note", new Date(), modifiedBy));
                this.database.updateOfficeNote(officeCode, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteOfficeNote(String officeCode, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.officeExists(officeCode)) {
            Office office = this.database.getOffice(officeCode);
            if(office.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) office.getNote(nRef);
                if(!note.hasBeenModified()) {
                    office.deleteNote(nRef, new ModifiedBy("Deleted Office Note", new Date(), modifiedBy));
                    this.database.updateOffice(officeCode);
                    this.database.deleteOfficeNote(officeCode, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD OFFICE DOCUMENT     ////////
    
    @Override
    public int createOfficeDocument(String oCode, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.officeExists(oCode) && !this.database.getOffice(oCode).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Office office = this.database.getOffice(oCode);
            office.createDocument(document, new ModifiedBy("Created Office Document", new Date(), createdBy));
            this.database.updateOffice(oCode);
            this.database.createOfficeDoc(oCode, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteOfficeDocument(String oCode, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.officeExists(oCode)) {
            Office office = this.database.getOffice(oCode);
            if(office.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) office.getDocument(dRef);
                office.deleteDocument(dRef, new ModifiedBy("Deleted Office Document", new Date(), modifiedBy));
                this.database.updateOffice(oCode);
                this.database.deleteOfficeDoc(oCode, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadOfficeDocument(String oCode, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.officeExists(oCode) && this.database.getOffice(oCode).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE CONTACT     ////////
    
    @Override
    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException {
        if (this.database.officeExists(oCode) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = (Contact) this.createContact(contactTypeCode, value, date, createdBy);
                this.database.createOfficeContact(contact, oCode);
                return contact.getContactRef();
            }
        }
        return 0;
    }
    
    @Override
    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(this.database.officeExists(oCode) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Office office = this.database.getOffice(oCode);
            if(office.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Office Contact", new Date(), modifiedBy);
                Contact contact = this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, comment, modified);
                this.database.updateOfficeContact(contact.getContactRef(), office.getOfficeCode());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteOfficeContact(String code, int cRef) throws RemoteException, SQLException {
        if(this.database.officeExists(code) && this.database.contactExists(cRef) && this.database.canDeleteContact(cRef)) {
            this.database.deleteOfficeContact(cRef, code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A INVOLVED PARTY     ////////
    
    @Override
    public int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, int address, String createdBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(aRef) && this.database.personExists(pRef) && this.database.relationshipExists(relationshipCode) && this.database.getRelationship(relationshipCode).isCurrent()) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef++, aRef, this.database.getPerson(pRef), joint, main, start, this.database.getRelationship(relationshipCode), createdBy, new Date());
            Person person = (Person) invParty.getPerson();
            this.database.createInvolvedParty(invParty);
            AddressUsage addressUsage = this.createAddressUsage(address, start, createdBy);
            person.createAddress(addressUsage, new ModifiedBy("Created New Address", new Date(), createdBy));
            this.database.createPersonAddressUsage(addressUsage, person.getPersonRef());
            return invParty.getInvolvedPartyRef();
        }
        return 0;
    }
    
    @Override
    public int updateInvolvedParty(int iRef, boolean joint, boolean main, Date start, String relationshipCode, String modifiedBy) throws  RemoteException, SQLException {
        if(this.database.invPartyExists(iRef) && this.database.relationshipExists(relationshipCode) && this.database.getRelationship(relationshipCode).isCurrent()) {
            InvolvedParty invParty = this.database.getInvolvedParty(iRef);
            ModifiedByInterface modified = new ModifiedBy("Updated Involved Party", new Date(), modifiedBy);
            invParty.updateInvolvedParty(joint, start, this.database.getRelationship(relationshipCode), modified);
            this.database.updateInvolvedParty(invParty.getInvolvedPartyRef());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteInvolvedParty(int iRef) throws RemoteException, SQLException {
        if(this.database.invPartyExists(iRef) && this.database.canDeleteInvolvedParty(iRef)) {
            this.database.deleteInvolvedParty(iRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE INVOLVED PARTY NOTES     ////////
    
    @Override
    public int createInvovedPartyNote(int iRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.invPartyExists(iRef)) {
            Note note = this.createNote(comment, createdBy);
            InvolvedParty invParty = this.database.getInvolvedParty(iRef);
            invParty.createNote(note, new ModifiedBy("Created Involved Party Note", new Date(), createdBy));
            this.database.updateInvolvedParty(iRef);
            this.database.createInvolvedPartyNote(iRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateInvolvedPartyNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.invPartyExists(eRef)) {
            InvolvedParty invParty = this.database.getInvolvedParty(eRef);
            if(invParty.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) invParty.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Involved Party Note", new Date(), modifiedBy));
                this.database.updateInvolvedPartyNote(eRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteInvolvedPartyNote(int iRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.invPartyExists(iRef)) {
            InvolvedParty invParty = this.database.getInvolvedParty(iRef);
            if(invParty.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) invParty.getNote(nRef);
                if(!note.hasBeenModified()) {
                    invParty.deleteNote(nRef, new ModifiedBy("Deleted Involved Party Note", new Date(), modifiedBy));
                    this.database.updateInvolvedParty(iRef);
                    this.database.deleteInvolvedPartyNote(iRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A APPLICATION     ////////
    
    @Override
    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef) && this.database.addressExists(addrRef) && this.database.relationshipExists(relationshipCode)) {
            int mainAppRef = this.createInvolvedParty(pRef, pRef, true, true, appStartDate, relationshipCode, addrRef, createdBy);
            Application application = new Application(this.appRef++, corrName, appStartDate, this.database.getInvolvedParty(mainAppRef), this.createAddressUsage(addrRef, appStartDate, createdBy), createdBy, new Date());
            this.database.createApplication(application);
            this.database.createApplicationAddressUsage((AddressUsage) application.getCurrentApplicationAddress(), application.getApplicationRef());
            return application.getApplicationRef();
        }
        return 0;
    }
    
    @Override
    public int updateApplication(int aRef, String corrName, Date appStartDate, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.applicationExists(aRef)) {
            Application application = this.database.getApplication(aRef);
            application.updateApplication(corrName, appStartDate, new ModifiedBy("Updated Application", new Date(), modifiedBy));
            this.database.updateApplication(application.getApplicationRef());
            return application.getApplicationRef();
        }
        return 0;
    }
    
    @Override
    public int deleteApplication(int aRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.invPartyExists(aRef) && this.database.canDeleteInvolvedParty(aRef)) {
            List<InvolvedPartyInterface> household = this.database.getApplication(aRef).getHousehold();
            for (InvolvedPartyInterface invParty : household) {
                Person person = (Person) invParty.getPerson();
                List<AddressUsageInterface> peopleAddresses = person.getAddresses();
                if (!peopleAddresses.isEmpty()) {
                    if (peopleAddresses.size() > 1) {
                        AddressUsage pOld = (AddressUsage) peopleAddresses.get(peopleAddresses.size() - 2);
                        pOld.setEndDate(null, new ModifiedBy("Reinstated Address Usage", new Date(), modifiedBy));
                    }
                    AddressUsageInterface current = peopleAddresses.get(peopleAddresses.size() - 1);
                    person.deleteAddress(current, new ModifiedBy("Deleted Address Usage", new Date(), modifiedBy));
                    this.database.deletePersonAddressUsage(person.getPersonRef(), current.getAddressUsageRef());
                    this.database.deleteInvolvedParty(invParty.getInvolvedPartyRef());
                }
            }
            AddressUsageInterface appAddress = this.database.getApplication(aRef).getCurrentApplicationAddress();
            this.database.deleteApplicationAddressUsage(appAddress.getAddressUsageRef(), aRef);
            this.database.deleteApplication(aRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO AMEND AN APPLICATION HOUSEHOLD     ////////
    
    @Override
    public int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(aRef) && database.personExists(pRef) && this.database.relationshipExists(relationshipCode)) {
            Application application = database.getApplication(aRef);
            if(application.isPersonHouseholdMember(pRef)) {
                int iRef = this.createInvolvedParty(aRef, pRef, false, joint, start, relationshipCode, application.getCurrentApplicationAddress().getAddress().getAddressRef(), createdBy);
                if(iRef >=1) {
                    application.addInvolvedParty(this.database.getInvolvedParty(iRef), new ModifiedBy("Added Involved Party", new Date(), createdBy));
                    return 1;
                }
            }
        }
        return 0;
    }
    
    @Override
    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException, SQLException {
        if(database.applicationExists(aRef) && this.database.invPartyExists(iRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = database.getApplication(aRef);
            InvolvedParty mainApp = (InvolvedParty) application.getMainApp();
            InvolvedParty party = this.database.getInvolvedParty(iRef);
            if(application.isPersonHouseholdMember(party.getPersonRef())) {
                if(!party.isMainInd() && party.isOver18()) {
                    application.changeMainApp(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Changed Main Applicant", new Date(), modifiedBy));
                }
                Person person = (Person) mainApp.getPerson();
                AddressUsage address = (AddressUsage) person.getCurrentAddress();
                address.setEndDate(end, new ModifiedBy("Ended Address", new Date(), modifiedBy));
                this.database.updatePersonAddressUsage(address.getAddressUsageRef(), person.getPersonRef());
                this.database.updateInvolvedParty(mainApp.getInvolvedPartyRef());
                this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException, SQLException {
        if(database.applicationExists(aRef) && this.database.invPartyExists(iRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = this.database.getApplication(aRef);
            InvolvedParty party = this.database.getInvolvedParty(iRef);
            if(application.isPersonHouseholdMember(party.getPersonRef())) {
                if(!party.isCurrent()) {
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Ended Involved Party", new Date(), modifiedBy));
                    Person person = (Person) party.getPerson();
                    AddressUsage address = (AddressUsage) person.getCurrentAddress();
                    address.setEndDate(end, new ModifiedBy("Ended Address", new Date(), modifiedBy));
                    this.database.updatePersonAddressUsage(address.getAddressUsageRef(), person.getPersonRef());
                    this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHOD TO SET AN APPLICATION TENANCY     ////////
    
    private int setApplicationTenancy (int aRef, int tRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(aRef) && database.tenancyExists(tRef) && this.database.addressExists(addrRef)) {
            Application application = database.getApplication(aRef);
            List<PropertyInterface> properties = application.setTenancy(tRef, new ModifiedBy("Assigned Application Tenancy", new Date(), createdBy));
            for(PropertyInterface property : properties) {
                this.database.endPropertyInterest(aRef, property.getPropRef());
            }
            application.setAppStatusCode("HSED");
            this.database.updateApplication(application.getApplicationRef());
            this.createApplicationAddressUsage(aRef, addrRef, startDate, createdBy);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO AMEND AN APPLICATIONS INTEREST IN PROPERTIES     ////////
    
    @Override
    public int addInterestedProperty(int aRef, int pRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(pRef) && database.applicationExists(aRef)) {
            Application application = database.getApplication(aRef);
            application.addInterestedProperty(database.getProperty(pRef), new ModifiedBy("Added Interested Property", new Date(), createdBy));
            this.database.createPropertyInterest(aRef, pRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int endInterestInProperty(int aRef, int pRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(pRef) && database.applicationExists(aRef)) {
            Application application = database.getApplication(aRef);
            application.endInterestInProperty(database.getProperty(pRef), new ModifiedBy("Ended Interest in Property", new Date(), createdBy));
            this.database.endPropertyInterest(aRef, pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE APPLICATION NOTES     ////////
    
    @Override
    public int createApplicationNote(int aRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(aRef)) {
            Note note = this.createNote(comment, createdBy);
            Application application = this.database.getApplication(aRef);
            application.createNote(note, new ModifiedBy("Created Application Note", new Date(), createdBy));
            this.database.updateApplication(aRef);
            this.database.createApplicationNote(aRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateApplicationNote(int aRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.applicationExists(aRef)) {
            Application application = this.database.getApplication(aRef);
            if(application.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) application.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Application Note", new Date(), modifiedBy));
                this.database.updateApplicationNote(aRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteApplicationNote(int aRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.applicationExists(aRef)) {
            Application application = this.database.getApplication(aRef);
            if(application.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) application.getNote(nRef);
                if(!note.hasBeenModified()) {
                    application.deleteNote(nRef, new ModifiedBy("Deleted Application Note", new Date(), modifiedBy));
                    this.database.updateApplication(aRef);
                    this.database.deleteApplicationNote(aRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD APPLICATION DOCUMENT     ////////
    
    @Override
    public int createApplicationDocument(int aRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(aRef) && !this.database.getApplication(aRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Application application = this.database.getApplication(aRef);
            application.createDocument(document, new ModifiedBy("Created Application Document", new Date(), createdBy));
            this.database.updateApplication(aRef);
            this.database.createApplicationDoc(aRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteApplicationDocument(int aRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.applicationExists(aRef)) {
            Application application = this.database.getApplication(aRef);
            if(application.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) application.getDocument(dRef);
                application.deleteDocument(dRef, new ModifiedBy("Deleted Person Document", new Date(), modifiedBy));
                this.database.updateApplication(aRef);
                this.database.deleteApplicationDoc(aRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    public byte[] downloadApplicationDocument(int aRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.applicationExists(aRef) && this.database.getApplication(aRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE APPLICATION ADDRESS USAGES     ////////
    
    private int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate, String createdBy) throws SQLException {
        if(this.database.applicationExists(applicationRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = (AddressUsage) this.createAddressUsage(addrRef, startDate, createdBy);
            Application application = database.getApplication(applicationRef);
            application.setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            this.database.updateApplication(applicationRef);
            this.database.createApplicationAddressUsage(addressUsage, appRef);
            this.createHouseholdAddressUsage(application, addrRef, startDate, createdBy);
            return 1;
        }
        return 0;
    }
    
    private void createHouseholdAddressUsage(Application application, int addrRef, Date startDate, String createdBy) throws SQLException {
        for (InvolvedPartyInterface invParty : application.getHousehold()) {
            if (invParty.isCurrent()) {
                Person person = (Person) invParty.getPerson();
                AddressUsage tempAddress = this.createAddressUsage(addrRef, startDate, createdBy);
                person.createAddress(tempAddress, new ModifiedBy("Created Address", new Date(), createdBy));
                this.database.createPersonAddressUsage(tempAddress, invParty.getPersonRef());
            }
        }
    }
    
    @Override
    public int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(aRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage", new Date(), modifiedBy));
            this.database.updateApplicationAddressUsage(addrUsageRef, aRef);
            this.updateHouseholdAddressUsage(this.database.getApplication(aRef), addrRef, startDate, comment, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    private void updateHouseholdAddressUsage(Application application, int addrRef, Date startDate, String comment, String modifiedBy) throws SQLException {
        for (InvolvedPartyInterface invParty : application.getHousehold()) {
            if (invParty.isCurrent()) {
                Person person = (Person) invParty.getPerson();
                AddressUsage tempAddress = (AddressUsage) person.getCurrentAddress();
                tempAddress.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage", new Date(), modifiedBy));
                this.database.updatePersonAddressUsage(tempAddress.getAddressUsageRef(), person.getPersonRef());
            }
        }
    }
    
    @Override
    public int deleteApplicationAddressUsage(int addrRef, int aRef) throws SQLException {
        if(this.database.applicationExists(aRef) && this.database.addressUsageExists(addrRef) && this.database.canDeleteAddressUsage(addrRef)) {
            this.database.deleteApplicationAddressUsage(aRef, addrRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE EMPLOYEES     ////////
    
    @Override
    public int createEmployee(int pRef, String username, String password, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && !this.database.personEmployeeExists(pRef) && !this.database.userExists(username)) {
            Employee employee = new Employee(employeeRef++, this.database.getPerson(pRef), username, password, createdBy, new Date());
            this.database.createEmployee(employee);
            this.database.createUser(employee.getUser());
            return employee.getEmployeeRef();
        }
        return 0;
    }
    
    @Override
    public int deleteEmployee(int eRef) throws RemoteException, SQLException {
        if(this.database.employeeExists(eRef) && this.database.canDeleteEmployee(eRef)) {
            this.database.deleteEmployee(eRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE EMPLOYEE NOTES     ////////
    
    @Override
    public int createEmployeeNote(int eRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.employeeExists(eRef)) {
            Note note = this.createNote(comment, createdBy);
            Employee employee = this.database.getEmployee(eRef);
            employee.createNote(note, new ModifiedBy("Created Employee Note", new Date(), createdBy));
            this.database.updateEmployee(eRef);
            this.database.createEmployeeNote(eRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateEmployeeNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = this.database.getEmployee(eRef);
            if(employee.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employee.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Employee Note", new Date(), modifiedBy));
                this.database.updateEmployeeNote(eRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteEmployeeNote(int eRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = this.database.getEmployee(eRef);
            if(employee.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employee.getNote(nRef);
                if(!note.hasBeenModified()) {
                    employee.deleteNote(nRef, new ModifiedBy("Deleted Employee Note", new Date(), modifiedBy));
                    this.database.updateEmployee(eRef);
                    this.database.deleteEmployeeNote(eRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE LANDLORD     ////////
    
    @Override
    public int createLandlord(int pRef, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && !this.database.personLandlordExists(pRef)) {
            Landlord landlord = new Landlord(landlordRef++, this.database.getPerson(pRef), createdBy, new Date());
            this.database.createLandlord(landlord);
            return landlord.getLandlordRef();
        }
        return 0;
    }
    
    @Override
    public int deleteLandlord(int lRef) throws RemoteException, SQLException  {
        if(this.database.landlordExists(lRef) && this.database.canDeleteLandlord(lRef)) {
            this.database.deleteLandlord(lRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE LANDLORD NOTES     ////////
    
    @Override
    public int createLandlordNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.landlordExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            Landlord landlord = this.database.getLandlord(lRef);
            landlord.createNote(note, new ModifiedBy("Created Landlord Note", new Date(), createdBy));
            this.database.updateLandlord(lRef);
            this.database.createLandlordNote(lRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateLandlordNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.landlordExists(lRef)) {
            Landlord landlord = this.database.getLandlord(lRef);
            if(landlord.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) landlord.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Landlord Note", new Date(), modifiedBy));
                this.database.updateLandlordNote(lRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteLandlordNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.landlordExists(lRef)) {
            Landlord landlord = this.database.getLandlord(lRef);
            if(landlord.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) landlord.getNote(nRef);
                if(!note.hasBeenModified()) {
                    landlord.deleteNote(nRef, new ModifiedBy("Deleted Landlord Note", new Date(), modifiedBy));
                    this.database.updateLandlord(lRef);
                    this.database.deleteLandlordNote(lRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PROPERTY     ////////
    
    @Override
    public int createProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propertyExists(pRef) && this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = new Property(propRef++, this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), createdBy, new Date());
            this.database.createProperty(property);
            return property.getPropRef();
        }
        return 0;
    }
    
    @Override
    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = this.database.getProperty(pRef);
            property.updateProperty(this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), new ModifiedBy("Updated Property", new Date(), modifiedBy));
            this.database.updateProperty(pRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteProperty(int pRef) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.canDeleteProperty(pRef)) {
            this.database.deleteLandlord(pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY NOTES     ////////
    
    @Override
    public int createPropertyNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef)) {
            Note note = this.createNote(comment, createdBy);
            Property property = this.database.getProperty(pRef);
            property.createNote(note, new ModifiedBy("Created Property Note", new Date(), createdBy));
            this.database.updateProperty(pRef);
            this.database.createPropertyNote(pRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertyNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property = this.database.getProperty(pRef);
            if(property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Property Note", new Date(), modifiedBy));
                this.database.updatePropertyNote(pRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deletePropertyNote(int pRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property=  this.database.getProperty(pRef);
            if(property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                if(!note.hasBeenModified()) {
                    property.deleteNote(nRef, new ModifiedBy("Deleted Property Note", new Date(), modifiedBy));
                    this.database.updateProperty(pRef);
                    this.database.deletePropertyNote(pRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD PROPERTY DOCUMENT     ////////
    
    @Override
    public int createPropertyDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && !this.database.getProperty(pRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Property property = this.database.getProperty(pRef);
            property.createDocument(document, new ModifiedBy("Created Property Document", new Date(), createdBy));
            this.database.updateProperty(pRef);
            this.database.createPropertyDoc(pRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePropertyDocument(int pRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property=  this.database.getProperty(pRef);
            if(property.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) property.getDocument(dRef);
                property.deleteDocument(dRef, new ModifiedBy("Deleted Property Document", new Date(), modifiedBy));
                this.database.updateProperty(pRef);
                this.database.deletePropertyDoc(pRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadPropertyDocument(int pRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.getProperty(pRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY ELEMENTS     ////////
    
    @Override
    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, double doubleValue, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.propElementExists(elementCode)) {
            Note note = this.createNote(comment, createdBy);
            PropertyElement propElement = new PropertyElement(propertyElementRef++, this.database.getPropElement(elementCode), startDate, charge, stringValue, doubleValue, note, createdBy, new Date());
            Property property = this.database.getProperty(pRef);
            property.createPropertyElement(propElement, new ModifiedBy("Created Property Element", new Date(), createdBy));
            this.database.createPropertyElementValue(pRef, propElement);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property = this.database.getProperty(pRef);
            if(property.hasPropElement(eRef)) {
                PropertyElement propElement = (PropertyElement) property.getPropElement(eRef);
                propElement.updatePropertyElement(startDate, stringValue, doubleValue, charge, comment, new ModifiedBy("Updated Property Element", new Date(), modifiedBy));
                this.database.updatePropertyElementValue(pRef, propElement.getPropertyElementRef());
            }
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deletePropertyElement(int eRef, int pRef) throws RemoteException, SQLException {
        if(this.database.propertyExists(eRef) && this.database.canDeletePropertyElementValue(eRef, pRef)) {
            this.database.deletePropertyElementValue(eRef, pRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLES     ////////
    
    @Override
    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobRoleExists(code)) {
            JobRole jobRole = new JobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, createdBy, new Date());
            this.database.createJobRole(jobRole);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(code)) {
            JobRole jobRole = this.database.getJobRole(code);
            jobRole.updateJobRole(jobTitle, jobDescription, salary, current, read, write, update, employeeRead, employeeWrite, employeeUpdate, new ModifiedBy("Updated Job Role", new Date(), modifiedBy));
            List<Contract> contracts = this.database.getContracts(null, null, null, null, null, null, null, code, null, null, null, null, null);
            for(Contract contract : contracts) {
                EmployeeAccount account = this.database.getEmployeeAccount(contract.getAccountRef());
                account.setSalary(salary);
                this.database.updateEmployeeAccount(account.getAccRef());
            }
            this.database.updateJobRole(code);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteJobRole(String officeCode) throws RemoteException, SQLException {
        if(this.database.officeExists(officeCode) && this.database.canDeleteOffice(officeCode)) {
            this.database.deleteOffice(officeCode);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLE NOTES     ////////
    
    @Override
    public int createJobRoleNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef)) {
            Note note = this.createNote(comment, createdBy);
            Property property = this.database.getProperty(pRef);
            property.createNote(note, new ModifiedBy("Created Property Note", new Date(), createdBy));
            this.database.updateProperty(pRef);
            this.database.createPropertyNote(pRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateJobRoleNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property = this.database.getProperty(pRef);
            if(property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Property Note", new Date(), modifiedBy));
                this.database.updatePropertyNote(pRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteJobRoleNote(int pRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.propertyExists(pRef)) {
            Property property=  this.database.getProperty(pRef);
            if(property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                if(!note.hasBeenModified()) {
                    property.deleteNote(nRef, new ModifiedBy("Deleted Property Note", new Date(), modifiedBy));
                    this.database.updateProperty(pRef);
                    this.database.deletePropertyNote(pRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE JOB ROLE REQUIREMENTS     ////////
    
    @Override
    public int createJobRoleRequirement(String jobRoleCode, String requirement, String createdBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobRequirementExists(requirement)) {
            JobRole jobRole = this.database.getJobRole(jobRoleCode);
            if(!jobRole.hasRequirement(requirement)) {
                jobRole.createJobRequirement(this.database.getJobRequirement(requirement), new ModifiedBy("Created Job Role Requirement", new Date(), createdBy));
                this.database.createJobRoleRequirement(jobRoleCode, requirement);
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteJobRoleRequirement(String jobRoleCode, String requirement) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobRequirementExists(requirement)) {
            this.database.deleteJobRoleRequirement(requirement, jobRoleCode);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLE BENEFITS     ////////
    
    @Override
    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobBenefitExists(benefit)) {
            JobRole jobRole = this.database.getJobRole(jobRoleCode);
            if(!jobRole.hasCurrentBenefit(benefit)) {
                Note note = this.createNote(comment, createdBy);
                JobRoleBenefit jobBenefit = new JobRoleBenefit(jobBenefitRef++, this.database.getJobBenefit(benefit), startDate, salaryBenefit, stringValue, doubleValue, note, createdBy, new Date());
                jobRole.createJobBenefit(jobBenefit, new ModifiedBy("Created Job Role Benefit", new Date(), createdBy));
                this.database.createJobRoleBenefit(jobRoleCode, jobBenefit);
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefitRef) && this.database.getJobRole(jobRoleCode).hasBenefit(benefitRef) && this.database.jobBenefitExists(benefitCode)) {
            JobRoleBenefit jobRoleBenefit = this.database.getJobRoleBenefit(benefitRef);
            jobRoleBenefit.updateJobRoleBenefit(stringValue, doubleValue, salaryBenefit, startDate, comment, new ModifiedBy("Updated Job Role Benefit", new Date(), modifiedBy));
            this.database.updateJobRoleBenefit(jobRoleCode, benefitRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefitRef) && this.database.getJobRole(jobRoleCode).hasBenefit(benefitRef)) {
            JobRole jobRole = this.database.getJobRole(jobRoleCode);
            jobRole.endJobBenefit(benefitRef, endDate, new ModifiedBy("Ended Job Role Benefit", new Date(), modifiedBy));
            this.database.updateJobRole(jobRoleCode);
            this.database.updateJobRoleBenefit(jobRoleCode, benefitRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteJobRoleBenefit(String jobRoleCode, int benefit) throws SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefit)) {
            this.database.deleteJobRoleBenefit(benefit, jobRoleCode);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB REQUIREMENTS     ////////
    
    @Override
    public int createJobRequirement(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobRequirementExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element requirement = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createJobRequirement(requirement);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateJobRequirement(String code, String description, boolean current, String comment, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.jobRequirementExists(code)) {
            ElementImpl requirement = (ElementImpl) this.database.getJobRequirement(code);
            requirement.updateElement(description, current, comment, new ModifiedBy("Updated Requirement", new Date(), modifiedBy));
            this.database.updateJobRequirement(requirement.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteJobRequirement(String requirement) throws RemoteException, SQLException {
        if(this.database.jobRequirementExists(requirement) && this.database.canDeleteJobRequirement(requirement)) {
            this.database.deleteJobRequirement(requirement);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB BENEFITS     ////////
    
    @Override
    public int createJobBenefit(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobBenefitExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element benefit = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createJobBenefit(benefit);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateJobBenefit(String code, String description, boolean current, String comment, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.jobBenefitExists(code)) {
            ElementImpl benefit = (ElementImpl) this.database.getJobBenefit(code);
            benefit.updateElement(description, current, comment, new ModifiedBy("Updated Benefit", new Date(), modifiedBy));
            this.database.updateJobBenefit(benefit.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteJobBenefit(String requirement) throws RemoteException, SQLException {
        if(this.database.jobRequirementExists(requirement) && this.database.canDeleteJobRequirement(requirement)) {
            this.database.deleteJobRequirement(requirement);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A TENANCY     ////////
    
    @Override
    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.applicationExists(aRef) && this.database.tenancyTypeExists(tenTypeCode) && this.database.officeExists(officeCode)) {
            Application application = this.database.getApplication(aRef);
            Property property = this.database.getProperty(pRef);
            Office office = this.database.getOffice(officeCode);
            Tenancy tenancy = new Tenancy(tenRef++, startDate, length, rentAccRef, property, application, this.database.getTenancyType(tenTypeCode), officeCode, createdBy, new Date());
            RentAccount rentAcc = new RentAccount(rentAccRef++, tenancy, createdBy, new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("Created Tenancy", new Date(), createdBy);
            
            this.database.createTenancy(tenancy);
            this.database.createRentAccount(rentAcc);
            
            this.setApplicationTenancy(aRef, tenancy.getAccountRef(), property.getAddress().getAddressRef(), startDate, createdBy);
            
            property.setPropStatus("OCCP", modifiedBy);
            this.database.updateProperty(property.getPropRef());
            
            office.createAgreement(tenancy, modifiedBy);
            this.database.updateOffice(office.getOfficeCode());
            office.createAccount(rentAcc, new ModifiedBy("Created Rent Account", new Date(), createdBy));
            this.database.updateOffice(office.getOfficeCode());
            
            return tenancy.getAgreementRef();
        }
        return 0;
    }
    
    @Override
    public int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.tenancyExists(tRef) && this.database.tenancyTypeExists(tenTypeCode)) {
            Tenancy tenancy = this.database.getTenancy(tRef);
            tenancy.updateAgreement(name, startDate, length, null);
            tenancy.setTenType(this.database.getTenancyType(tenTypeCode), new ModifiedBy("Updated Tenancy", new Date(), modifiedBy));
            this.updateRentAccount(tenancy.getAccountRef(), name, startDate, (tenancy.getRent() + tenancy.getCharges()), modifiedBy);
            this.database.updateTenancy(tenancy.getAgreementRef());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteTenancy(int tRef) throws RemoteException, SQLException {
        if(this.database.tenancyExists(tRef) && this.database.canDeleteTenancy(tRef)) {
            Tenancy tenancy = this.database.getTenancy(tRef);
            Application application = (Application) tenancy.getApplication();
            Property property = (Property) tenancy.getProperty();
            
            if(application != null && property != null && this.database.officeExists(tenancy.getOfficeCode()));
            Office office = this.database.getOffice(tenancy.getOfficeCode());
            
            RentAccount rentAcc = new RentAccount(rentAccRef++, tenancy, createdBy, new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("Created Tenancy", new Date(), createdBy);
            application.setTenancy(tenRef, modifiedBy);
            application.setAppStatusCode("HSED");
            property.setPropStatus("OCCP", modifiedBy);
            office.createAgreement(tenancy, modifiedBy);
            this.database.updateOffice(office.getOfficeCode());
            office.createAgreement(tenancy, new ModifiedBy("Created Rent Account", new Date(), createdBy));
            this.database.updateOffice(office.getOfficeCode());
            this.database.updateApplication(application.getApplicationRef());
            this.database.updateProperty(property.getPropRef());
            this.database.createTenancy(tenancy);
            this.database.createRentAccount(rentAcc);
            this.database.deleteTenancy(tRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE TENANCY NOTES     ////////
    
    @Override
    public int createTenancyNote(int tRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.tenancyExists(tRef)) {
            Note note = this.createNote(comment, createdBy);
            Tenancy tenancy = this.database.getTenancy(tRef);
            tenancy.createNote(note, new ModifiedBy("Created Tenancy Note", new Date(), createdBy));
            this.database.updateTenancy(tRef);
            this.database.createTenancyNote(tRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateTenancyNote(int tRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = this.database.getTenancy(tRef);
            if(tenancy.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) tenancy.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Tenancy Note", new Date(), modifiedBy));
                this.database.updateTenancyNote(tRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteTenancyNote(int tRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = this.database.getTenancy(tRef);
            if(tenancy.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) tenancy.getNote(nRef);
                if(!note.hasBeenModified()) {
                    tenancy.deleteNote(nRef, new ModifiedBy("Deleted Tenancy Note", new Date(), modifiedBy));
                    this.database.updateTenancy(tRef);
                    this.database.deleteTenancyNote(tRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD TENANCY DOCUMENT     ////////
    
    @Override
    public int createTenancyDocument(int tRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.tenancyExists(tRef) && !this.database.getTenancy(tRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Tenancy tenancy = this.database.getTenancy(tRef);
            tenancy.createDocument(document, new ModifiedBy("Created Tenancy Document", new Date(), createdBy));
            this.database.updateTenancy(tRef);
            this.database.createTenancyDoc(tRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteTenancyDocument(int tRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = this.database.getTenancy(tRef);
            if(tenancy.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) tenancy.getDocument(dRef);
                tenancy.deleteDocument(dRef, new ModifiedBy("Deleted Tenancy Document", new Date(), modifiedBy));
                this.database.updateTenancy(tRef);
                this.database.deleteTenancyDoc(tRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadTenancyDocument(int tRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && this.database.getTenancy(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE TENANCY TYPES     ////////
    
    @Override
    public int createTenancyType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException {
        if(!this.database.tenancyTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element tenancyType = new ElementImpl(code, description, note, createdBy, new Date());
            this.database.createContactType(tenancyType);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateTenancyType(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.tenancyTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Tenancy Type", new Date(), modifiedBy);
            ElementImpl tenancyType = (ElementImpl) this.database.getTenancyType(code);
            tenancyType.updateElement(description, current, comment, modified);
            this.database.updateTenancyType(tenancyType.getCode());
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteTenancyType(String code) throws RemoteException, SQLException {
        if(this.database.tenancyTypeExists(code) && this.database.canDeleteTenancyType(code)) {
            this.database.deleteTenancyType(code);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A LEASE     ////////
    
    @Override
    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.officeExists(officeCode)) {
            Lease lease = new Lease(leaseRef++, startDate, length, leaseAccRef, this.database.getProperty(pRef), management, expenditure, officeCode, createdBy, new Date());
            LeaseAccount leaseAcc = new LeaseAccount(leaseAccRef++, lease, createdBy, new Date());
            this.database.createLease(lease);
            this.database.createLeaseAccount(leaseAcc);
            return lease.getAgreementRef();
        }
        return 0;
    }
    
    @Override
    public int updateLease(int lRef, String name, Date startDate, int length, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.leaseExists(lRef)) {
            Lease lease = this.database.getLease(lRef);
            lease.updateAgreement(name, startDate, length, new ModifiedBy("Updated Lease", new Date(), modifiedBy));
            this.database.updateLease(lease.getAgreementRef());
            this.updateLeaseAccount(lease.getAccountRef(), name, startDate, lease.getExpenditure(), modifiedBy);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteLease(int lRef) throws RemoteException, SQLException {
        if(this.database.leaseExists(lRef) && this.database.canDeleteLease(lRef)) {
            g// LOOK AT DELETE APPLICATIONS AND IMPLEMENT ROLLING BACK PROCESS
            this.database.deleteLease(lRef);
            return 1;
        }
        return 0;
    }
    
    
    
    //////     METHODS TO CREATE, UPDATE AND DELETE LEASE NOTES     ////////
    
    @Override
    public int createLeaseNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.leaseExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            Lease lease = this.database.getLease(lRef);
            lease.createNote(note, new ModifiedBy("Created Lease Note", new Date(), createdBy));
            this.database.updateLease(lRef);
            this.database.createLeaseNote(lRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateLeaseNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = this.database.getLease(lRef);
            if(lease.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) lease.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Lease Note", new Date(), modifiedBy));
                this.database.updateLeaseNote(lRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    public int deleteLeaseNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = this.database.getLease(lRef);
            if(lease.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) lease.getNote(nRef);
                if(!note.hasBeenModified()) {
                    lease.deleteNote(nRef, new ModifiedBy("Deleted Lease Note", new Date(), modifiedBy));
                    this.database.updateLease(lRef);
                    this.database.deleteLeaseNote(lRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD LEASE DOCUMENT     ////////
    
    public int createLeaseDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.leaseExists(lRef) && !this.database.getLease(lRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Lease lease = this.database.getLease(lRef);
            lease.createDocument(document, new ModifiedBy("Created Lease Document", new Date(), createdBy));
            this.database.updateLease(lRef);
            this.database.createLeaseDoc(lRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteLeaseDocument(int lRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = this.database.getLease(lRef);
            if(lease.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) lease.getDocument(dRef);
                lease.deleteDocument(dRef, new ModifiedBy("Deleted Lease Document", new Date(), modifiedBy));
                this.database.updateLease(lRef);
                this.database.deleteLeaseDoc(lRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadLeaseDocument(int tRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.leaseExists(tRef) && this.database.getLease(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO CREATE, AND END A LANDLORD FOR A LEASE     ////////
    
    @Override
    public int createLeaseLandlord(int lRef, int landRef, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.leaseExists(lRef) && this.database.landlordExists(landRef)) {
            Lease lease = this.database.getLease(lRef);
            if(!lease.isAlreadyLandlord(lRef)) {
                Landlord landlord = this.database.getLandlord(landRef);
                lease.addLandlord(landlord, new ModifiedBy("Assigned Landlord to Lease", new Date(), modifiedBy));
                this.database.createLeaseLandlord(landRef, lRef);
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int endLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.leaseExists(lRef) && this.database.landlordExists(landRef)) {
            Lease lease = this.database.getLease(lRef);
            if(lease.isAlreadyLandlord(lRef)) {
                lease.endLandlord(lRef, new ModifiedBy("Ended Landlord for Lease", new Date(), modifiedBy));
                this.database.endLeaseLandlord(landRef, lRef);
                return 1;
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A CONTRACT     ////////
    
    public int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode, String createdBy) throws RemoteException, SQLException {
        if(this.database.employeeExists(eRef) && this.database.jobRoleExists(jobRoleCode) && this.database.officeExists(officeCode)) {
            Employee employee = this.database.getEmployee(eRef);
            JobRole jobRole = this.database.getJobRole(jobRoleCode);
            UserImpl user = employee.getUser();
            user.setUserPermissions(jobRole.getRead(), jobRole.getWrite(), jobRole.getUpdate(), jobRole.getEmployeeRead(), jobRole.getEmployeeWrite(), jobRole.getEmployeeUpdate());
            Contract contract = new Contract(contractRef++, employeeAccRef, startDate, length, employee, jobRole, officeCode, createdBy, new Date());
            EmployeeAccount employeeAcc = new EmployeeAccount(employeeAccRef++, contract, createdBy, new Date());
            this.database.createContract(contract);
            this.database.createEmployeeAccount(employeeAcc);
            this.database.updateUser(user.getUsername());
            return contract.getAgreementRef();
        }
        return 0;
    }
    
    public int updateContract(int cRef, String name, Date startDate, int length, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.contractExists(cRef)) {
            Contract contract = this.database.getContract(cRef);
            contract.updateAgreement(name, startDate, length, new ModifiedBy("Updated Contract", new Date(), modifiedBy));
            this.database.updateLease(contract.getAgreementRef());
            this.updateEmployeeAccount(contract.getAccountRef(), name, startDate, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteContract(int cRef) throws SQLException {
        if(this.database.contractExists(cRef) && this.database.canDeleteContract(cRef)) {
            g// LOOK AT DELETE APPLICATIONS AND IMPLEMENT ROLLING BACK PROCESS
            this.database.deleteContract(cRef);
            return 1;
        }
        return 0;
    }
    
    
    
    //////     METHODS TO CREATE, UPDATE AND DELETE CONTRACT NOTES     ////////
    
    @Override
    public int createContractNote(int cRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.contractExists(cRef)) {
            Note note = this.createNote(comment, createdBy);
            Contract contract = this.database.getContract(cRef);
            contract.createNote(note, new ModifiedBy("Created Contract Note", new Date(), createdBy));
            this.database.updateContract(cRef);
            this.database.createContractNote(cRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateContractNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.invPartyExists(cRef)) {
            Contract contract = this.database.getContract(cRef);
            if(contract.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) contract.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Contract Note", new Date(), modifiedBy));
                this.database.updateContractNote(cRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteContractNote(int cRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.contractExists(cRef)) {
            Contract contract = this.database.getContract(cRef);
            if(contract.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) contract.getNote(nRef);
                if(!note.hasBeenModified()) {
                    contract.deleteNote(nRef, new ModifiedBy("Deleted Contract Note", new Date(), modifiedBy));
                    this.database.updateContract(cRef);
                    this.database.deleteContractNote(cRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD CONTRACT DOCUMENT     ////////
    
    @Override
    public int createContractDocument(int cRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.contractExists(cRef) && !this.database.getContract(cRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Contract contract = this.database.getContract(cRef);
            contract.createDocument(document, new ModifiedBy("Created Contract Document", new Date(), createdBy));
            this.database.updateContract(cRef);
            this.database.createContractDoc(cRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteContractDocument(int cRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.contractExists(cRef)) {
            Contract contract = this.database.getContract(cRef);
            if(contract.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) contract.getDocument(dRef);
                contract.deleteDocument(dRef, new ModifiedBy("Deleted Contract Document", new Date(), modifiedBy));
                this.database.updateContract(cRef);
                this.database.deleteContractDoc(cRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadContractDocument(int tRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.contractExists(tRef) && this.database.getContract(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO UPDATE RENT ACCOUNT     ////////
    
    private int updateRentAccount(int rRef, String name, Date startDate, double rent, String modifiedBy) throws SQLException {
        if(this.database.rentAccountExists(rRef)) {
            RentAccount account = this.database.getRentAccount(rRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Rent Account", new Date(), modifiedBy));
            account.setRent(rent);
            this.database.updateRentAccount(account.getAccRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE RENT ACCOUNT NOTES     ////////
    
    @Override
    public int createrRentAccNote(int rRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.rentAccountExists(rRef)) {
            Note note = this.createNote(comment, createdBy);
            RentAccount rentAcc = this.database.getRentAccount(rRef);
            rentAcc.createNote(note, new ModifiedBy("Created Rent Account Note", new Date(), createdBy));
            this.database.updateContract(rRef);
            this.database.createRentAccountNote(rRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateRentAccNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.rentAccountExists(cRef)) {
            RentAccount rentAcc = this.database.getRentAccount(cRef);
            if(rentAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) rentAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Rent Account Note", new Date(), modifiedBy));
                this.database.updateRentAccountNote(cRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteRentAccNote(int rRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccount rentAcc = this.database.getRentAccount(rRef);
            if(rentAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) rentAcc.getNote(nRef);
                if(!note.hasBeenModified()) {
                    rentAcc.deleteNote(nRef, new ModifiedBy("Deleted Rent Account Note", new Date(), modifiedBy));
                    this.database.updateContract(rRef);
                    this.database.deleteRentAccountNote(rRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD RENT ACCOUNT DOCUMENT     ////////
    
    @Override
    public int createRentAccDocument(int rRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.rentAccountExists(rRef) && !this.database.getRentAccount(rRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            RentAccount rentAcc = this.database.getRentAccount(rRef);
            rentAcc.createDocument(document, new ModifiedBy("Created Rent Account Document", new Date(), createdBy));
            this.database.updateRentAccount(rRef);
            this.database.createRentAccountDoc(rRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteRentAccDocument(int rRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccount rentAcc = this.database.getRentAccount(rRef);
            if(rentAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) rentAcc.getDocument(dRef);
                rentAcc.deleteDocument(dRef, new ModifiedBy("Deleted Rent Account Document", new Date(), modifiedBy));
                this.database.updateRentAccount(rRef);
                this.database.deleteRentAccountDoc(rRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadRentAccDocument(int rRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef) && this.database.getRentAccount(rRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    
    
    //////     METHODS TO UPDATE LEASE ACCOUNT     ////////
    
    private int updateLeaseAccount(int lRef, String name, Date startDate, double expenditure, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.leaseAccountExists(lRef)) {
            LeaseAccount account = this.database.getLeaseAccount(lRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Lease Account", new Date(), modifiedBy));
            account.setExpenditure(expenditure);
            this.database.updateLeaseAccount(account.getAccRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE LEASE ACCOUNT NOTES     ////////
    
    @Override
    public int createLeaseAccNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.leaseAccountExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            LeaseAccount leaseAcc = this.database.getLeaseAccount(lRef);
            leaseAcc.createNote(note, new ModifiedBy("Created Lease Account Note", new Date(), createdBy));
            this.database.updateLeaseAccount(lRef);
            this.database.createLeaseAccountNote(lRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateLeaseAccNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount leaseAcc = this.database.getLeaseAccount(lRef);
            if(leaseAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) leaseAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Lease Account Note", new Date(), modifiedBy));
                this.database.updateLeaseAccountNote(lRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteLeaseAccNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount leaseAcc = this.database.getLeaseAccount(lRef);
            if(leaseAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) leaseAcc.getNote(nRef);
                if(!note.hasBeenModified()) {
                    leaseAcc.deleteNote(nRef, new ModifiedBy("Deleted Lease Account Note", new Date(), modifiedBy));
                    this.database.updateLeaseAccount(lRef);
                    this.database.deleteLeaseAccountNote(lRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD LEASE ACCOUNT DOCUMENT     ////////
    
    @Override
    public int createLeaseAccDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.leaseAccountExists(lRef) && !this.database.getLeaseAccount(lRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            LeaseAccount leaseAcc = this.database.getLeaseAccount(lRef);
            leaseAcc.createDocument(document, new ModifiedBy("Created Lease Account Document", new Date(), createdBy));
            this.database.updateLeaseAccount(lRef);
            this.database.createLeaseAccountDoc(lRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteLeaseAccDocument(int lRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount leaseAcc = this.database.getLeaseAccount(lRef);
            if(leaseAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) leaseAcc.getDocument(dRef);
                leaseAcc.deleteDocument(dRef, new ModifiedBy("Deleted Lease Account Document", new Date(), modifiedBy));
                this.database.updateLeaseAccount(lRef);
                this.database.deleteLeaseAccountDoc(lRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadLeaseAccDocument(int lRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef) && this.database.getLeaseAccount(lRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO UPDATE EMPLOYEE ACCOUNTS     ////////
    
    private int updateEmployeeAccount(int eRef, String name, Date startDate, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.employeeAccountExists(eRef)) {
            EmployeeAccount account = this.database.getEmployeeAccount(eRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Employee Account", new Date(), modifiedBy));
            this.database.updateEmployeeAccount(account.getAccRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE EMPLOYEE NOTES     ////////
    
    @Override
    public int createEmployeeAccNote(int eRef, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.employeeAccountExists(eRef)) {
            Note note = this.createNote(comment, createdBy);
            EmployeeAccount employeeAcc = this.database.getEmployeeAccount(eRef);
            employeeAcc.createNote(note, new ModifiedBy("Created Employee Account Note", new Date(), createdBy));
            this.database.updateLeaseAccount(eRef);
            this.database.createEmployeeAccountNote(eRef, note);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateEmployeeAccNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = this.database.getEmployeeAccount(eRef);
            if(employeeAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employeeAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Employee Account Note", new Date(), modifiedBy));
                this.database.updateEmployeeAccountNote(eRef, note.getRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int deleteEmployeeAccNote(int eRef, int nRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = this.database.getEmployeeAccount(eRef);
            if(employeeAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employeeAcc.getNote(nRef);
                if(!note.hasBeenModified()) {
                    employeeAcc.deleteNote(nRef, new ModifiedBy("Deleted Employee Account Note", new Date(), modifiedBy));
                    this.database.updateLeaseAccount(eRef);
                    this.database.deleteEmployeeAccountNote(eRef, note.getRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, DELETE AND DOWNLOAD EMPLOYEE ACCOUNT DOCUMENT     ////////
    
    @Override
    public int createEmployeeAccDocument(int eRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException {
        if(this.database.employeeAccountExists(eRef) && !this.database.getEmployeeAccount(eRef).hasDocument(fileName)) {
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            EmployeeAccount employeeAcc = this.database.getEmployeeAccount(eRef);
            employeeAcc.createDocument(document, new ModifiedBy("Created Employee Account Document", new Date(), createdBy));
            this.database.updateEmployeeAccount(eRef);
            this.database.createEmployeeAccountDoc(eRef, document);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteEmployeeAccDocument(int eRef, int dRef, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = this.database.getEmployeeAccount(eRef);
            if(employeeAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) employeeAcc.getDocument(dRef);
                employeeAcc.deleteDocument(dRef, new ModifiedBy("Deleted Employee Account Document", new Date(), modifiedBy));
                this.database.updateEmployeeAccount(eRef);
                this.database.deleteEmployeeAccountDoc(eRef, document.getDocumentRef());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public byte[] downloadEmployeeAccDocument(int eRef, int dRef, String downloadedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef) && this.database.getEmployeeAccount(eRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef);
        }
        return null;
    }
    
    //////     METHODS TO DELETE ACCOUNTS     ////////
    
    // WRITE DELETE METHODS
    
    //////     METHODS TO CREATE ACCOUNT TRANSACTIONS     ////////    

    @Override
    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException {
        if (this.database.rentAccountExists(rAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, rAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            RentAccount account = this.database.getRentAccount(rAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Rent Transaction", new Date(), createdBy));
            this.database.createRentAccountTransaction(account.getAccRef(), transaction);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteRentAccTransaction(int tRef, int rAccRef) throws RemoteException, SQLException {
        if(this.database.rentAccountExists(rAccRef)) {
            g// LOOK AT DELETE APPLICATIONS AND IMPLEMENT ROLLING BACK PROCESS
            this.database.deleteRentAccountTransaction(rAccRef, tRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException {
        if (this.database.leaseAccountExists(lAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, lAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            LeaseAccount account = this.database.getLeaseAccount(lAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Lease Transaction", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteLeaseAccTransaction(int tRef, int lAccRef) throws RemoteException, SQLException {
        if(this.database.leaseAccountExists(lAccRef)) {
            g// LOOK AT DELETE APPLICATIONS AND IMPLEMENT ROLLING BACK PROCESS
            this.database.deleteLeaseAccountTransaction(lAccRef, tRef);
            return 1;
        }
        return 0;
    }
    
    @Override
    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException {
        if (this.database.employeeAccountExists(eAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, eAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            EmployeeAccount account = this.database.getEmployeeAccount(eAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Employee Transaction", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    @Override
    public int deleteEmployeeAccTransaction(int tRef, int eAccRef) throws RemoteException, SQLException {
        if(this.database.employeeAccountExists(eAccRef)) {
            g// LOOK AT DELETE APPLICATIONS AND IMPLEMENT ROLLING BACK PROCESS
            this.database.deleteEmployeeAccountTransaction(eAccRef, tRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO RETURN PERSON ELEMENT LISTS     ////////
    
    @Override
    public List<Element> getTitles() throws RemoteException {
        return this.database.getTitles();
    }
    
    @Override
    public List<Element> getGenders() throws RemoteException {
        return this.database.getGenders();
    }
    
    @Override
    public List<Element> getMaritalStatuses() throws RemoteException {
        return this.database.getMaritalStatuses();
    }
    
    @Override
    public List<Element> getEthnicOrigins() throws RemoteException {
        return this.database.getEthnicOrigins();
    }
    
    @Override
    public List<Element> getLanguages() throws RemoteException {
        return this.database.getLanguages();
    }
    
    @Override
    public List<Element> getNationalities() throws RemoteException {
        return this.database.getNationalities();
    }
    
    @Override
    public List<Element> getSexualities() throws RemoteException {
        return this.database.getSexualities();
    }
    
    @Override
    public List<Element> getReligions() throws RemoteException {
        return this.database.getReligions();
    }
    
    //////     METHODS TO RETURN PROPERTY ELEMENT LISTS     ////////
    
    @Override
    public List<Element> getPropertyTypes() throws RemoteException {
        return this.database.getPropertyTypes();
    }
    
    @Override
    public List<Element> getPropertySubTypes() throws RemoteException {
        return this.database.getPropertySubTypes();
    }
    
    @Override
    public List<Element> getPropElements() throws RemoteException {
        return this.database.getPropElements();
    }
    
    //////     METHODS TO RETURN CONTACT TYPES LIST     ////////
    
    @Override
    public List<Element> getContactTypes() throws RemoteException {
        return this.database.getContactTypes();
    }
    
    //////     METHODS TO RETURN END REASONS LIST     ////////
    
    @Override
    public List<Element> getEndReasons() throws RemoteException {
        return this.database.getEndReasons();
    }
    
    //////     METHODS TO RETURN RELATIONSHIPS LIST     ////////
    
    @Override
    public List<Element> getRelationships() throws RemoteException {
        return this.database.getRelationships();
    }
    
    //////     METHODS TO RETURN JOB ROLE ELEMENT LISTS     ////////
    
    @Override
    public List<Element> getJobBenefits() throws RemoteException {
        return this.database.getJobBenefits();
    }
    
    @Override
    public List<Element> getJobRequirements() throws RemoteException {
        return this.database.getJobRequirements();
    }
    
    //////     METHODS TO RETURN TENANCY TYPES LIST     ////////
    
    @Override
    public List<Element> getTenancyTypes() throws RemoteException {
        return this.database.getTenancyTypes();
    }
    
    
    

    @Override
    public boolean titleExists(String code) throws RemoteException {
        return this.database.titleExists(code);
    }
    
    @Override
    public boolean genderExists(String code) throws RemoteException {
        return this.database.genderExists(code);
    }
    
    @Override
    public boolean maritalStatusExists(String code) throws RemoteException {
        return this.database.maritalStatusExists(code);
    }
    
    @Override
    public boolean ethnicOriginExists(String code) throws RemoteException {
        return this.database.ethnicOriginExists(code);
    }
    
    @Override
    public boolean languageExists(String code) throws RemoteException {
        return this.database.languageExists(code);
    }
    
    @Override
    public boolean nationalityExists(String code) throws RemoteException {
        return this.database.nationalityExists(code);
    }
    
    @Override
    public boolean sexualityExists(String code) throws RemoteException {
        return this.database.sexualityExists(code);
    }
    
    @Override
    public boolean religionExists(String code) throws RemoteException {
        return this.database.religionExists(code);
    }
    
    @Override
    public boolean contactTypeExists(String code) throws RemoteException {
        return this.database.contactTypeExists(code);
    }
    
    @Override
    public boolean propTypeExists(String code) throws RemoteException {
        return this.database.propTypeExists(code);
    }
    
    @Override
    public boolean propSubTypeExists(String code) throws RemoteException {
        return this.database.propSubTypeExists(code);
    }
    
    @Override
    public boolean propElementExists(String code) throws RemoteException {
        return this.database.propElementExists(code);
    }
    
    @Override
    public boolean endReasonExists(String code) throws RemoteException {
        return this.database.endReasonExists(code);
    }
    
    @Override
    public boolean relationshipExists(String code) throws RemoteException {
        return this.database.relationshipExists(code);
    }
    
    @Override
    public boolean officeExists(String code) throws RemoteException {
        return this.database.officeExists(code);
    }
    
    @Override
    public boolean jobRoleExists(String code) throws RemoteException {
        return this.database.jobRoleExists(code);
    }
    
    @Override
    public boolean isUser(String username, String password) throws RemoteException {
        return this.database.isUser(username, password);
    }
    
    
    ////// MOVE THE SEARCH METHODS INTO SERVER
    
    
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
 
    private byte[] downloadDocument(int dRef) {
        if (this.database.documentExists(dRef)) {
            try {
                Document document = this.database.getDocument(dRef);
                File file = new File(document.getDocumentPath());
                byte buffer[] = new byte[(int) file.length()];
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(document.getDocumentPath()))) {
                    input.read(buffer, 0, buffer.length);
                }
                return (buffer);
            } catch (Exception e) {
                System.out.println("FileImpl: " + e.getMessage());
            }
        }
        return (null);
    }
    
    private DocumentImpl uploadDocument(String fileName, byte[] buffer, String comment, String createdBy) {
        try {
            File file = new File(fileName);
            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileName))) {
                output.write(buffer, 0, buffer.length);
                output.flush();
                Note note = this.createNote(comment, createdBy);
                DocumentImpl document = new DocumentImpl(documentRef++, file, fileName, note, createdBy, new Date());
                return document;
            }
        } catch (Exception e) {
            System.out.println("ServerImpl: " + e.getMessage());
        }
        return null;
    }
}
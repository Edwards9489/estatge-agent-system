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
import interfaces.PropertyInterface;
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
    
    private TaskGenerator scheduler;
    
    ///   CONSTRUCTORS ///
    
    public ServerImpl(String environment, String addr, String username, String password, int port) throws RemoteException {
        super();
        this.users = new HashMap<>();
        //this.database = new Database(environment, addr, username, password, port);
        
//        this.personRef = this.database.countPeople() + 1;
//        this.invPartyRef = this.database.countInvolvedParties() + 1;
//        this.landlordRef = this.database.countLandords() + 1;
//        this.employeeRef = this.database.countEmployees() + 1;
//        this.appRef = this.database.countApplications() + 1;
//        this.propRef = this.database.countProperties() + 1;
//        this.tenRef = this.database.countTenancies() + 1;
//        this.leaseRef = this.database.countLeases() + 1;
//        this.contractRef = this.database.countContracts() + 1;
//        this.rentAccRef = this.database.countRentAccounts() + 1;
//        this.leaseAccRef = this.database.countLeaseAccounts() + 1;
//        this.employeeAccRef = this.database.countEmployeeAccounts() + 1;
//        this.addressRef = this.database.countAddresses() + 1;
//        this.transactionRef = this.database.countTransactions() + 1;
//        this.addressUsageRef = this.database.countAddressUsages() + 1;
//        this.contactRef = this.database.countContacts() + 1;
//        try {
//            this.propertyElementRef = this.database.getPropElementCount() + 1;
//        } catch (SQLException ex) {
//            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            this.jobBenefitRef = this.database.getJobBenefitCount() + 1;
//        } catch (SQLException ex) {
//            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ELEMENTS     ////////
    
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
            ElementImpl title = (ElementImpl) this.database.getTitle(code);
            title.updateElement(description, current, modified);
            this.database.updateTitle(title.getCode());
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
            ElementImpl gender = (ElementImpl) this.database.getGender(code);
            gender.updateElement(description, current, modified);
            this.database.updateGender(gender.getCode());
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
            ElementImpl status = (ElementImpl) this.database.getMaritalStatus(code);
            status.updateElement(description, current, modified);
            this.database.updateMaritalStatus(status.getCode());
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
            ElementImpl origin = (ElementImpl) this.database.getEthnicOrigin(code);
            origin.updateElement(description, current, modified);
            this.database.updateEthnicOrigin(origin.getCode());
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
            ElementImpl language = (ElementImpl) this.database.getLanguage(code);
            language.updateElement(description, current, modified);
            this.database.updateLanguage(language.getCode());
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
            ElementImpl nationality = (ElementImpl) this.database.getNationality(code);
            nationality.updateElement(description, current, modified);
            this.database.updateNationality(nationality.getCode());
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
            ElementImpl sexuality = (ElementImpl) this.database.getSexuality(code);
            sexuality.updateElement(description, current, modified);
            this.database.updateSexuality(sexuality.getCode());
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
            ElementImpl religion = (ElementImpl) this.database.getReligion(code);
            religion.updateElement(description, current, modified);
            this.database.updateReligion(religion.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY ELEMENTS     ////////
    
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
            ElementImpl propType = (ElementImpl) this.database.getPropertyType(code);
            propType.updateElement(description, current, modified);
            this.database.updatePropertyType(propType.getCode());
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
            ElementImpl propType = (ElementImpl) this.database.getPropertySubType(code);
            propType.updateElement(description, current, modified);
            this.database.updatePropertySubType(propType.getCode());
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
            ElementImpl propElement = (ElementImpl) this.database.getPropElement(code);
            propElement.updateElement(description, current, modified);
            this.database.updatePropertyElement(propElement.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE CONTACT ELEMENTS     ////////
    
    public int createContactType(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.contactTypeExists(code)) {
            Element contactType = new ElementImpl(code, description, createdBy, new Date());
            this.database.createContactType(contactType);
            return 1;
        }
        return 0;
    }
    
    public int updateContactType(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.contactTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Contact Type", new Date(), modifiedBy);
            ElementImpl contactType = (ElementImpl) this.database.getContactType(code);
            contactType.updateElement(description, current, modified);
            this.database.updateContactType(contactType.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE END REASONS     ////////
    
    public int createEndReason(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.endReasonExists(code)) {
            Element endReason = new ElementImpl(code, description, createdBy, new Date());
            this.database.createEndReason(endReason);
            return 1;
        }
        return 0;
    }
    
    public int updateEndReason(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.endReasonExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated End Reason", new Date(), modifiedBy);
            ElementImpl endReason = (ElementImpl) this.database.getEndReason(code);
            endReason.updateElement(description, current, modified);
            this.database.updateEndReason(endReason.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE RELATIONSHIPS     ////////
    
    public int createRelationship(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.relationshipExists(code)) {
            Element relationship = new ElementImpl(code, description, createdBy, new Date());
            this.database.createRelationship(relationship);
            return 1;
        }
        return 0;
    }
    
    public int updateRelationship(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.relationshipExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Relationship", new Date(), modifiedBy);
            ElementImpl relationship = (ElementImpl) this.database.getRelationship(code);
            relationship.updateElement(description, current, modified);
            this.database.updateRelationship(relationship.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE ADDRESSES     ////////
    
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) throws RemoteException, SQLException {
        Address address = new Address(this.addressRef++, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, new Date());
        this.database.createAddress(address);
        return address.getAddressRef();
    }
    
    public int updateAddress(int addressRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String createdBy) throws RemoteException, SQLException {
        if(database.addressExists(addressRef)) {
            Address address = database.getAddress(addressRef);
            address.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, new ModifiedBy("Updated Address", new Date(), createdBy));
            this.database.updateAddress(address.getAddressRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHOD TO CREATE ADDRESS USAGE     ////////
    
    private AddressUsage createAddressUsage(int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.addressExists(addrRef)) {
            AddressUsage addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addrRef), startDate, createdBy, new Date());
            return addressUsage;
        }
        return null;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON     ////////
    
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), null, createdBy, new Date());
                AddressUsage address = this.createAddressUsage(addrRef, addressStartDate, createdBy);
                this.database.createPersonAddressUsage(address, person.getPersonRef());
                this.database.createPerson(person);
                return person.getPersonRef();
            }
        }
        return 0;
    }
    
    public int updatePerson(int ref, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.personExists(ref) && this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person", new Date(), modifiedBy);
                Person person = this.database.getPerson(personRef);
                person.updatePerson(this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), modified);
                this.database.updatePerson(person.getPersonRef());
                return 1;
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON CONTACT     ////////
    
    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException {
        if (this.database.personExists(pRef) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = new Contact(this.contactRef++, this.database.getContactType(contactTypeCode), value, date, createdBy, new Date());
                this.database.createPersonContact(contact, pRef);
                return contact.getContactRef();
            }

        }
        return 0;
    }
    
    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String modifiedBy) throws  RemoteException, SQLException {
        if(this.database.personExists(pRef) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Person person = this.database.getPerson(pRef);
            if(person.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person Contact", new Date(), modifiedBy);
                Contact contact = this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, modified);
                this.database.updatePersonContact(contact.getContactRef(), person.getPersonRef());
                return 1;
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ADDRESS USAGES     ////////
    
    public int createPersonAddressUsage(int personRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.personExists(personRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addrRef, startDate, createdBy);
            database.getPerson(personRef).createAddress(addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int updatePersonAddressUsage(int personRef, int addrUsageRef, int addrRef, Date startDate, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.personExists(personRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, new ModifiedBy("Updated Address Usage", new Date(), modifiedBy));
            this.database.updatePersonAddressUsage(addrUsageRef, personRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE     ////////
    
    public int createOffice(String officeCode, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if (!this.database.officeExists(officeCode) && this.database.addressExists(addrRef)) {
            Office office = new Office(officeCode, this.database.getAddress(addrRef), startDate, createdBy, new Date());
            this.database.createOffice(office);
            return 1;
        }
        return 0;
    }
    
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
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE CONTACT     ////////
    
    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException {
        if (this.database.officeExists(oCode) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = new Contact(this.contactRef++, this.database.getContactType(contactTypeCode), value, date, createdBy, new Date());
                this.database.createOfficeContact(contact, oCode);
                return contact.getContactRef();
            }

        }
        return 0;
    }
    
    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String modifiedBy) throws  RemoteException, SQLException {
        if(this.database.officeExists(oCode) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Office office = this.database.getOffice(oCode);
            if(office.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Office Contact", new Date(), modifiedBy);
                Contact contact = this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, modified);
                this.database.updateOfficeContact(contact.getContactRef(), office.getOfficeCode());
                return 1;
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A INVOLVED PARTY     ////////
    
    private int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(aRef) && this.database.personExists(pRef) && this.database.relationshipExists(relationshipCode) && this.database.getRelationship(relationshipCode).isCurrent()) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef++, aRef, this.database.getPerson(pRef), joint, main, start, this.database.getRelationship(relationshipCode), createdBy, new Date());
            this.database.createInvolvedParty(invParty);
            return invParty.getInvolvedPartyRef();
        }
        return 0;
    }
    
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
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A APPLICATION     ////////
    
    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException {
        if(this.database.addressExists(addrRef) && this.database.relationshipExists(relationshipCode)) {
            int mainAppRef = this.createInvolvedParty(this.invPartyRef++, pRef, true, true, appStartDate, relationshipCode, createdBy);
            if(mainAppRef >= 1) {
                AddressUsage address = this.createAddressUsage(addrRef, addressStartDate, createdBy);
                this.database.createApplicationAddressUsage(address, appRef);
                Application application = new Application(this.appRef++, corrName, appStartDate, this.database.getInvolvedParty(mainAppRef), address, createdBy, new Date());
                this.database.createApplication(application);
                return application.getApplicationRef();
            }
        }
        return 0;
    }
    
    public int updateApplication(int aRef, String corrName, Date appStartDate, String modifiedBy) throws RemoteException, SQLException {
        if (this.database.applicationExists(aRef)) {
            Application application = this.database.getApplication(aRef);
            application.updateApplication(corrName, appStartDate, new ModifiedBy("Updated Application", new Date(), modifiedBy));
            this.database.updateApplication(application.getApplicationRef());
            return application.getApplicationRef();
        }
        return 0;
    }
    
    //////     METHODS TO AMEND AN APPLICATION HOUSEHOLD     ////////
    
    public int addInvolvedParty(int appRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.personExists(pRef) && this.database.relationshipExists(relationshipCode)) {
            Application application = database.getApplication(appRef);
            if(application.isPersonHouseholdMember(pRef)) {
                int iRef = this.createInvolvedParty(appRef, pRef, false, joint, start, relationshipCode, createdBy);
                if(iRef >=1) {
                    application.addInvolvedParty(this.database.getInvolvedParty(iRef), new ModifiedBy("Added Involved Party", new Date(), createdBy));
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(aRef) && this.database.invPartyExists(iRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = database.getApplication(aRef);
            InvolvedParty mainApp = (InvolvedParty) application.getMainApp();
            InvolvedParty party = this.database.getInvolvedParty(iRef);
            if(application.isPersonHouseholdMember(party.getPersonRef())) {
                if(!party.isMainInd() && party.isOver18()) {
                    application.changeMainApp(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Changed Main Applicant", new Date(), createdBy));
                }
                this.database.updateInvolvedParty(mainApp.getInvolvedPartyRef());
                this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                return 1;
            }
        }
        return 0;
    }
    
    public int endInvolvedParty(int appRef, int invPartyRef, Date end, String endReasonCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && this.database.invPartyExists(invPartyRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = this.database.getApplication(appRef);
            InvolvedParty party = this.database.getInvolvedParty(invPartyRef);
            if(application.isPersonHouseholdMember(party.getPersonRef())) {
                if(!party.isCurrent()) {
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Ended Involved Party", new Date(), createdBy));
                    this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    //////     METHOD TO SET AN APPLICATION TENANCY     ////////
    
    public int setApplicationTenancy (int appRef, int tenancyRef, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.tenancyExists(tenancyRef)) {
            Application application = database.getApplication(appRef);
            List<PropertyInterface> properties = application.setTenancy(tenancyRef, new ModifiedBy("Assigned Application Tenancy", new Date(), createdBy));
            for(PropertyInterface property : properties) {
                this.database.endPropertyInterest(appRef, property.getPropRef());
            }
            application.setAppStatusCode("HSED");
            this.database.updateApplication(application.getApplicationRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO AMEND AN APPLICATIONS INTEREST IN PROPERTIES     ////////
    
    public int addInterestedProperty(int appRef, int propRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            Application application = database.getApplication(appRef);
            application.addInterestedProperty(database.getProperty(propRef), new ModifiedBy("Added Interested Property", new Date(), createdBy));
            this.database.createPropertyInterest(appRef, propRef);
            return 1;
        }
        return 0;
    }
    
    public int endInterestInProperty(int appRef, int propRef, String createdBy) throws RemoteException, SQLException {
        if(database.propertyExists(propRef) && database.applicationExists(appRef)) {
            Application application = database.getApplication(appRef);
            application.endInterestInProperty(database.getProperty(propRef), new ModifiedBy("Ended Interest in Property", new Date(), createdBy));
            this.database.endPropertyInterest(appRef, propRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE APPLICATION ADDRESS USAGES     ////////
    
    public int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(applicationRef) && this.database.addressExists(addressRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addrRef, startDate, createdBy);
            database.getApplication(applicationRef).setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created Address", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int updateApplicationAddressUsage(int applicationRef, int addrUsageRef, int addrRef, Date startDate, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.applicationExists(applicationRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, new ModifiedBy("Updated Address Usage", new Date(), modifiedBy));
            this.database.updateApplicationAddressUsage(addrUsageRef, applicationRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE EMPLOYEES     ////////
    
    public int createEmployee(int pRef, String username, String password, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && !this.database.employeeExists(this.database.getPerson(pRef)) && !this.database.userExists(username)) {
            Employee employee = new Employee(employeeRef++, this.database.getPerson(pRef), username, password, createdBy, new Date());
            this.database.createEmployee(employee);
            this.database.createUser(employee.getUser());
            return employee.getEmployeeRef();
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE LANDLORD     ////////
    
    public int createLandlord(int pRef, String createdBy) throws RemoteException, SQLException {
        if(this.database.personExists(pRef) && !this.database.landlordExists(this.database.getPerson(pRef))) {
            Landlord landlord = new Landlord(landlordRef++, this.database.getPerson(pRef), createdBy, new Date());
            this.database.createLandlord(landlord);
            return landlord.getLandlordRef();
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A PROPERTY     ////////
    
    public int createProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String createdBy) throws RemoteException, SQLException {
        if(!this.database.propertyExists(pRef) && this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = new Property(propRef++, this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), createdBy, new Date());
            this.database.createProperty(property);
            return property.getPropRef();
        }
        return 0;
    }
    
    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = this.database.getProperty(pRef);
            property.updateProperty(this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), new ModifiedBy("Updated Property", new Date(), modifiedBy));
            this.database.updateProperty(pRef);
            return 1;
        }
        return 0;
    }
    
    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, double doubleValue, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.propElementExists(elementCode)) {
            PropertyElement propElement = new PropertyElement(propertyElementRef++, this.database.getPropElement(elementCode), startDate, charge, stringValue, doubleValue, createdBy, new Date());
            Property property = this.database.getProperty(pRef);
            property.createPropertyElement(propElement, new ModifiedBy("Created Property Element", new Date(), createdBy));
            this.database.createPropertyElementValue(pRef, propElement);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLES     ////////
    
    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobRoleExists(code)) {
            JobRole jobRole = new JobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, createdBy, new Date());
            this.database.createJobRole(jobRole);
            return 1;
        }
        return 0;
    }
    
    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(code)) {
            JobRole jobRole = this.database.getJobRole(code);
            jobRole.updateJobRole(jobTitle, jobDescription, salary, read, read, write, update, employeeRead, employeeWrite, employeeUpdate, new ModifiedBy("Updated Job Role", new Date(), modifiedBy));
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
    
    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String createdBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobBenefitExists(benefit)) {
            JobRole jobRole = this.database.getJobRole(jobRoleCode);
            if(!jobRole.hasBenefit(benefit)) {
                JobRoleBenefit jobBenefit = new JobRoleBenefit(jobBenefitRef++, this.database.getJobBenefit(benefit), startDate, salaryBenefit, stringValue, doubleValue, createdBy, new Date());
                jobRole.createJobBenefit(jobBenefit, new ModifiedBy("Created Job Role Benefit", new Date(), createdBy));
                this.database.createJobRoleBenefit(jobRoleCode, jobBenefit);
                return 1;
            }
        }
        return 0;
    }
    
    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String modifiedBy) throws RemoteException, SQLException {
        if(this.database.jobRoleExists(jobRoleCode) && this.database.jobBenefitExists(benefitCode)) {
            JobRoleBenefit jobRoleBenefit = this.database.getJobRoleBenefit(benefitRef);
            jobRoleBenefit.updateJobRoleBenefit(stringValue, doubleValue, salaryBenefit, startDate, new ModifiedBy("Updated Job Role Benefit", new Date(), modifiedBy));
            this.database.updateJobRoleBenefit(jobRoleCode, benefitRef);
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB REQUIREMENTS     ////////
    
    public int createJobRequirement(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobRequirementExists(code)) {
            Element requirement = new ElementImpl(code, description, createdBy, new Date());
            this.database.createJobRequirement(requirement);
            return 1;
        }
        return 0;
    }
    
    public int updateJobRequirement(String code, String description, boolean current, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.jobRequirementExists(code)) {
            ElementImpl requirement = (ElementImpl) this.database.getJobRequirement(code);
            requirement.updateElement(description, current, new ModifiedBy("Updated Requirement", new Date(), modifiedBy));
            this.database.updateJobRequirement(requirement.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE JOB BENEFITS     ////////
    
    public int createJobBenefit(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.jobBenefitExists(code)) {
            Element benefit = new ElementImpl(code, description, createdBy, new Date());
            this.database.createJobBenefit(benefit);
            return 1;
        }
        return 0;
    }
    
    public int updateJobBenefit(String code, String description, boolean current, String modifiedBy) throws SQLException, RemoteException {
        if(!this.database.jobBenefitExists(code)) {
            ElementImpl benefit = (ElementImpl) this.database.getJobBenefit(code);
            benefit.updateElement(description, current, new ModifiedBy("Updated Benefit", new Date(), modifiedBy));
            this.database.updateJobBenefit(benefit.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A TENANCY     ////////
    
    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode, String createdBy) throws RemoteException, SQLException {
        if(this.database.propertyExists(pRef) && this.database.applicationExists(aRef) && this.database.tenancyTypeExists(tenTypeCode) && this.database.officeExists(officeCode)) {
            Tenancy tenancy = new Tenancy(tenRef++, startDate, length, rentAccRef, this.database.getProperty(pRef), this.database.getApplication(aRef), this.database.getTenancyType(tenTypeCode), officeCode, createdBy, new Date());
            RentAccount rentAcc = new RentAccount(rentAccRef++, tenancy, createdBy, new Date());
            this.database.createTenancy(tenancy);
            this.database.createRentAccount(rentAcc);
            return tenancy.getAgreementRef();
        }
        return 0;
    }
    
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
    
    //////     METHODS TO CREATE, UPDATE AND DELETE TENANCY TYPES     ////////
    
    public int createTenancyType(String code, String description, String createdBy) throws RemoteException, SQLException {
        if(!this.database.tenancyTypeExists(code)) {
            Element tenancyType = new ElementImpl(code, description, createdBy, new Date());
            this.database.createContactType(tenancyType);
            return 1;
        }
        return 0;
    }
    
    public int updateTenancyType(String code, String description, boolean current, String modifiedBy) throws  RemoteException, SQLException {
        if(!this.database.tenancyTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Tenancy Type", new Date(), modifiedBy);
            ElementImpl tenancyType = (ElementImpl) this.database.getTenancyType(code);
            tenancyType.updateElement(description, current, modified);
            this.database.updateTenancyType(tenancyType.getCode());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE A LEASE     ////////
    
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
    
    //////     METHODS TO UPDATE ACCOUNTS     ////////
    
    private int updateRentAccount(int rRef, String name, Date startDate, double rent, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.rentAccountExists(rRef)) {
            RentAccount account = this.database.getRentAccount(rRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Rent Account", new Date(), modifiedBy));
            account.setRent(rent);
            this.database.updateRentAccount(account.getAccRef());
            return 1;
        }
        return 0;
    }
    
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
    
    private int updateEmployeeAccount(int eRef, String name, Date startDate, String modifiedBy) throws SQLException, RemoteException {
        if(this.database.employeeAccountExists(eRef)) {
            EmployeeAccount account = this.database.getEmployeeAccount(eRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Employee Account", new Date(), modifiedBy));
            this.database.updateEmployeeAccount(account.getAccRef());
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO DELETE ACCOUNTS     ////////
    
    // WRITE DELETE METHODS
    
    //////     METHODS TO CREATE ACCOUNT TRANSACTIONS     ////////    

    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String createdBy) {
        if (this.database.rentAccountExists(rAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Transaction transaction = new Transaction(transactionRef++, rAccRef, fromRef, toRef, amount, debit, transactionDate, createdBy, new Date());
            RentAccount account = this.database.getRentAccount(rAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Rent Transaction", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String createdBy) {
        if (this.database.leaseAccountExists(lAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Transaction transaction = new Transaction(transactionRef++, lAccRef, fromRef, toRef, amount, debit, transactionDate, createdBy, new Date());
            LeaseAccount account = this.database.getLeaseAccount(lAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Lease Transaction", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String createdBy) {
        if (this.database.employeeAccountExists(eAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Transaction transaction = new Transaction(transactionRef++, eAccRef, fromRef, toRef, amount, debit, transactionDate, createdBy, new Date());
            EmployeeAccount account = this.database.getEmployeeAccount(eAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Employee Transaction", new Date(), createdBy));
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO RETURN PERSON ELEMENT LISTS     ////////
    
    public List<Element> getTitles() {
        return this.database.getTitles();
    }
    
    public List<Element> getGenders() {
        return this.database.getGenders();
    }
    
    public List<Element> getMaritalStatuses() {
        return this.database.getMaritalStatuses();
    }
    
    public List<Element> getEthnicOrigins() {
        return this.database.getEthnicOrigins();
    }
    
    public List<Element> getLanguages() {
        return this.database.getLanguages();
    }
    
    public List<Element> getNationalities() {
        return this.database.getNationalities();
    }
    
    public List<Element> getSexualities() {
        return this.database.getSexualities();
    }
    
    public List<Element> getReligions() {
        return this.database.getReligions();
    }
    
    //////     METHODS TO RETURN PROPERTY ELEMENT LISTS     ////////
    
    public List<Element> getPropertyTypes() {
        return this.database.getPropertyTypes();
    }
    
    public List<Element> getPropertySubTypes() {
        return this.database.getPropertySubTypes();
    }
    
    public List<Element> getPropElements() {
        return this.database.getPropElements();
    }
    
    //////     METHODS TO RETURN CONTACT TYPES LIST     ////////
    
    public List<Element> getContactTypes() {
        return this.database.getContactTypes();
    }
    
    //////     METHODS TO RETURN END REASONS LIST     ////////
    
    public List<Element> getEndReasons() {
        return this.database.getEndReasons();
    }
    
    //////     METHODS TO RETURN RELATIONSHIPS LIST     ////////
    
    public List<Element> getRelationships() {
        return this.database.getRelationships();
    }
    
    //////     METHODS TO RETURN JOB ROLE ELEMENT LISTS     ////////
    
    public List<Element> getJobBenefits() {
        return this.database.getJobBenefits();
    }
    
    public List<Element> getJobRequirements() {
        return this.database.getJobRequirements();
    }
    
    //////     METHODS TO RETURN TENANCY TYPES LIST     ////////
    
    public List<Element> getTenancyTypes() {
        return this.database.getTenancyTypes();
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
    
    @Override
    public boolean isUser(String username, String password) throws RemoteException {
        String temp = username.toUpperCase();
        if(!this.database.userExists(temp)) {
            return false;
        }
        UserImpl user = this.database.getUser(username);
        return user.isUser(temp, password);
    }
}
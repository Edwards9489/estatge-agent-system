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
    private int jobBenefitRef;
    private int transactionRef;
    
    ///   CONSTRUCTORS ///
    
    public ServerImpl() throws RemoteException {
        super();
        this.users = new HashMap<>();
        this.database = new Database();
        
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
        this.jobBenefitRef = this.database.countJobBenefits() + 1;
        this.addressRef = this.database.countAddresses() + 1;
        this.transactionRef = this.database.countTransactions() + 1;
        this.addressUsageRef = this.database.countAddressUsages() + 1;
        this.contactRef = this.database.countContacts() + 1;
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
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addressRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), null, createdBy, new Date());
                AddressUsage address = this.createAddressUsage(addressRef, addressStartDate, createdBy);
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
                this.database.updatePerson(person);
                return 1;
            }
        }
        return 0;
    }
    
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
                this.database.updatePersonContact(contact, person.getPersonRef());
                return 1;
            }
        }
        return 0;
    }
    
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
                this.database.updateOfficeContact(contact, office.getOfficeCode());
                return 1;
            }
        }
        return 0;
    }
    
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
            this.database.updateInvolvedParty(invParty);
            return 1;
        }
        return 0;
    }
    
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
            this.database.updateApplication(application);
            return application.getApplicationRef();
        }
        return 0;
    }
    
    public int addInvolvedParty(int appRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.personExists(pRef) && this.database.relationshipExists(relationshipCode)) {
            Application application = database.getApplication(appRef);
            if(application.isHouseholdMember(pRef)) {
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
            InvolvedParty mainApp = application.getMainApp();
            InvolvedParty party = this.database.getInvolvedParty(iRef);
            if(application.isHouseholdMember(party.getPersonRef())) {
                if(!party.isMainInd() && party.isOver18()) {
                    application.changeMainApp(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Changed Main Applicant", new Date(), createdBy));
                }
                this.database.updateInvolvedParty(mainApp);
                this.database.updateInvolvedParty(party);
                return 1;
            }
        }
        return 0;
    }
    
    public int endInvolvedParty(int appRef, int invPartyRef, Date end, String endReasonCode, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && this.database.invPartyExists(invPartyRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = this.database.getApplication(appRef);
            InvolvedParty party = this.database.getInvolvedParty(invPartyRef);
            if(application.isHouseholdMember(party.getPersonRef())) {
                if(!party.isCurrent()) {
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Ended Involved Party", new Date(), createdBy));
                    this.database.updateInvolvedParty(party);
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int setApplicationTenancy (int appRef, int tenancyRef, String createdBy) throws RemoteException, SQLException {
        if(database.applicationExists(appRef) && database.tenancyExists(tenancyRef)) {
            Application application = database.getApplication(appRef);
            application.setTenancy(tenancyRef, new ModifiedBy("Assigned Application Tenancy", new Date(), createdBy));
            this.database.updateApplication(application);
            return 1;
        }
        return 0;
    }
    
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
    
    look over how I am creating address usages for applications and people
    
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
            this.database.updateAddress(address);
            return 1;
        }
        return 0;
    }
    
    private AddressUsage createAddressUsage(int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
        if(database.addressExists(addressRef)) {
            AddressUsage addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addressRef), startDate, createdBy, new Date());
            return addressUsage;
        }
        return null;
    }
    
    private int updateAddressUsage(AddressUsageInterface addressUsage, int addressRef, Date startDate, String createdBy) throws RemoteException, SQLException {
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
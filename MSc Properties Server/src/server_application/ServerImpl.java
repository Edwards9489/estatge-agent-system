/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.DateConversion;
import classes.Utils;
import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.AgreementInterface;
import interfaces.ApplicationInterface;
import interfaces.Server;
import interfaces.Client;
import interfaces.ContactInterface;
import interfaces.ContractInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.LoginInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyInterface;
import interfaces.RegistryLoader;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import interfaces.TransactionInterface;
import interfaces.User;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.security.SecureRandom;
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
    private final HashMap<String, Client> users;

    // The database for the server
    private Database database;

    // List of reference counters
    private int personRef;
    private int invPartyRef;
    private int landlordRef;
    private int employeeRef;
    private int appRef;
    private int propRef;
    private int agreementRef;
    private int accountRef;
    private int addressRef;
    private int addressUsageRef;
    private int contactRef;
    private int transactionRef;
    private int propertyElementRef;
    private int jobBenefitRef;
    private int noteRef;
    private int documentRef;

    //private final TaskGenerator scheduler;
    
    private final String documentsLocation;
    
    private final SendEmail sendEmail;
    
    private SecureRandom random;

    ///   CONSTRUCTORS ///
    public ServerImpl(String environment, String addr, String username, String password, int port) throws RemoteException {
        super();
        this.users = new HashMap<>();
        this.database = new Database(environment, addr, username, password, port);

        this.personRef = this.database.countPeople() + 1;
        System.out.println("Person Ref: " + personRef);
        this.invPartyRef = this.database.countInvolvedParties() + 1;
        System.out.println("Inv Party Ref: " + invPartyRef);
        this.landlordRef = this.database.countLandords() + 1;
        System.out.println("Landlord Ref: " + landlordRef);
        this.employeeRef = this.database.countEmployees() + 1;
        System.out.println("Employee Ref: " + employeeRef);
        this.appRef = this.database.countApplications() + 1;
        System.out.println("App Ref: " + appRef);
        this.propRef = this.database.countProperties() + 1;
        System.out.println("Prop Ref: " + propRef);
        this.agreementRef = this.database.getAgreementRef() + 1;
        System.out.println("Agreement Ref: " + agreementRef);
        this.accountRef = this.database.getAccountRef() + 1;
        System.out.println("Account Ref: " + accountRef);
        this.addressRef = this.database.countAddresses() + 1;
        System.out.println("Address Ref: " + addressRef);
        this.addressUsageRef = this.database.countAddressUsages() + 1;
        System.out.println("Address Usage Ref: " + addressUsageRef);
        this.contactRef = this.database.countContacts() + 1;
        System.out.println("Contact Ref: " + contactRef);
        this.noteRef = this.database.countNotes() + 1;
        System.out.println("Note Ref: " + noteRef);
        this.documentRef = this.database.countDocuments() + 1;
        System.out.println("Document Ref: " + documentRef);
        this.propertyElementRef = this.database.countPropElements() + 1;
        System.out.println("Property Element Ref: " + propertyElementRef);
        this.jobBenefitRef = this.database.countJobBenefits() + 1;
        System.out.println("Job Benefit Ref: " + jobBenefitRef);
        this.transactionRef = this.database.countTransactions() + 1;
        System.out.println("Transaction Ref: " + transactionRef);
        
        //Schedule to run every Day at midnight
        //this.scheduler = new TaskGenerator(this, 1000 * 60);
        
        //Location on Server where documents can be saved
        this.documentsLocation = "D:\\DOCUMENTS\\";
        
        this.sendEmail = new SendEmail("mscproperties.online@gmail.com", "Toxic9489", "smtp.gmail.com");
        
        this.random = new SecureRandom();
        
        this.createSuperUser();
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        // start a registry on this machine
        ServerImpl server = (ServerImpl) createServer(new String[]{"LIVE", "127.0.0.1", "root", "Toxic9489!999", "3306"});
    }

    public static Server createServer(String[] args) {
        try {
            RegistryLoader.Load();
            String environment = "LIVE";
            String addr = "127.0.0.1";
            String username = null;
            String password = null;
            Integer port = null;
            String myIP = java.net.InetAddress.getLocalHost().getHostAddress();
            if (args.length == 5) {
                environment = args[0];
                addr = args[1];
                username = args[2];
                password = args[3];
                port = Integer.parseInt(args[4]);
            }
            
            String myName = "Server" + environment;
            String myURL = "rmi://" + myIP + "/" + myName;

            // register RMI object
            Server serverStub = (Server) new ServerImpl(environment, addr, username, password, port);
            
            LoginInterface loginObject = new LoginImpl(serverStub);

            //NB rebind will replace any stub with the given name 'Server'
            System.out.println(myName);
            Naming.rebind(myName, loginObject);
            System.out.println((new Date()) + ": Server up and running");
            return serverStub;
        } catch (RemoteException | UnknownHostException | NumberFormatException | MalformedURLException e) {
        }
        return null;
    }
    
    private void createSuperUser() {
        if(!this.database.userExists("ADMIN")) {
            try {
                UserImpl user = new UserImpl(-1, -1, "ADMIN", "MScProperties", null);
                user.setUserPermissions(true, true, true, true, true, true, true, true);
                this.database.createUser(user);
            } catch (RemoteException | SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Note createNote(String comment, String createdBy) throws RemoteException {
        Note note = new NoteImpl(noteRef++, comment, createdBy, new Date());
        return note;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ELEMENTS     ////////
    @Override
    public int createTitle(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.titleExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element title = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createTitle(title);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTitle(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, RemoteException {
        if (this.database.titleExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Title", modifiedBy, new Date());
            ElementImpl title = (ElementImpl) this.database.getTitle(code);
            title.updateElement(description, current, comment, modified);
            try {
                this.database.updateTitle(title.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteTitle(String code) throws RemoteException {
        if (this.database.titleExists(code) && this.database.canDeleteTitle(code)) {
            try {
                this.database.deleteTitle(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createGender(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.genderExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element gender = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createGender(gender);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateGender(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.genderExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Gender", modifiedBy, new Date());
            ElementImpl gender = (ElementImpl) this.database.getGender(code);
            gender.updateElement(description, current, comment, modified);
            try {
                this.database.updateGender(gender.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteGender(String code) throws RemoteException {
        if (this.database.genderExists(code) && this.database.canDeleteGender(code)) {
            try {
                this.database.deleteGender(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createMaritalStatus(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.maritalStatusExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element status = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createMaritalStatus(status);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateMaritalStatus(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.maritalStatusExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Marital Status", modifiedBy, new Date());
            ElementImpl status = (ElementImpl) this.database.getMaritalStatus(code);
            status.updateElement(description, current, comment, modified);
            try {
                this.database.updateMaritalStatus(status.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteMaritalStatus(String code) throws RemoteException {
        if (this.database.maritalStatusExists(code) && this.database.canDeleteMaritalStatus(code)) {
            try {
                this.database.deleteMaritalStatus(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createEthnicOrigin(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.ethnicOriginExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element origin = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createEthnicOrigin(origin);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEthnicOrigin(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.ethnicOriginExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Ethnic Origin", modifiedBy, new Date());
            ElementImpl origin = (ElementImpl) this.database.getEthnicOrigin(code);
            origin.updateElement(description, current, comment, modified);
            try {
                this.database.updateEthnicOrigin(origin.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteEthnicOrigin(String code) throws RemoteException {
        if (this.database.ethnicOriginExists(code) && this.database.canDeleteEthnicOrigin(code)) {
            try {
                this.database.deleteEthnicOrigin(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createLanguage(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.languageExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element language = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createLanguage(language);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLanguage(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.languageExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Language", modifiedBy, new Date());
            ElementImpl language = (ElementImpl) this.database.getLanguage(code);
            language.updateElement(description, current, comment, modified);
            try {
                this.database.updateLanguage(language.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteLanguage(String code) throws RemoteException {
        if (this.database.languageExists(code) && this.database.canDeleteLanguage(code)) {
            try {
                this.database.deleteLanguage(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createNationality(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.nationalityExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element nationality = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createNationality(nationality);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateNationality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.nationalityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Nationality", modifiedBy, new Date());
            ElementImpl nationality = (ElementImpl) this.database.getNationality(code);
            nationality.updateElement(description, current, comment, modified);
            try {
                this.database.updateNationality(nationality.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteNationality(String code) throws RemoteException {
        if (this.database.nationalityExists(code) && this.database.canDeleteNationality(code)) {
            try {
                this.database.deleteNationality(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createSexuality(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.sexualityExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element sexuality = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createSexuality(sexuality);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateSexuality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.sexualityExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Sexuality", modifiedBy, new Date());
            ElementImpl sexuality = (ElementImpl) this.database.getSexuality(code);
            sexuality.updateElement(description, current, comment, modified);
            try {
                this.database.updateSexuality(sexuality.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteSexuality(String code) throws RemoteException {
        if (this.database.sexualityExists(code) && this.database.canDeleteSexuality(code)) {
            try {
                this.database.deleteSexuality(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createReligion(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.religionExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element religion = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createReligion(religion);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateReligion(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.religionExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Religion", modifiedBy, new Date());
            ElementImpl religion = (ElementImpl) this.database.getReligion(code);
            religion.updateElement(description, current, comment, modified);
            try {
                this.database.updateReligion(religion.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteReligion(String code) throws RemoteException {
        if (this.database.religionExists(code) && this.database.canDeleteReligion(code)) {
            try {
                this.database.deleteReligion(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY ELEMENTS     ////////
    @Override
    public int createPropertyType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.propTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element type = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createPropertyType(type);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePropertyType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.propTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Property Type", modifiedBy, new Date());
            ElementImpl propType = (ElementImpl) this.database.getPropertyType(code);
            propType.updateElement(description, current, comment, modified);
            try {
                this.database.updatePropertyType(propType.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePropertyType(String code) throws RemoteException {
        if (this.database.propTypeExists(code) && this.database.canDeletePropertyType(code)) {
            try {
                this.database.deletePropertyType(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createPropertySubType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.propSubTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element type = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createPropertySubType(type);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePropertySubType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.propSubTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Property Sub Type", modifiedBy, new Date());
            ElementImpl propType = (ElementImpl) this.database.getPropertySubType(code);
            propType.updateElement(description, current, comment, modified);
            try {
                this.database.updatePropertySubType(propType.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePropertySubType(String code) throws RemoteException {
        if (this.database.propSubTypeExists(code) && this.database.canDeletePropertySubType(code)) {
            try {
                this.database.deletePropertySubType(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int createPropertyElement(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.propElementExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element element = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createPropElement(element);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePropertyElement(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.propElementExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Property Element", modifiedBy, new Date());
            ElementImpl propElement = (ElementImpl) this.database.getPropElement(code);
            propElement.updateElement(description, current, comment, modified);
            try {
                this.database.updatePropertyElement(propElement.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePropertyElement(String code) throws RemoteException {
        if (this.database.propElementExists(code) && this.database.canDeletePropertyElement(code)) {
            try {
                this.database.deletePropertyElement(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE CONTACT ELEMENTS     ////////
    @Override
    public int createContactType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.contactTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element contactType = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createContactType(contactType);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateContactType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.contactTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Contact Type", modifiedBy, new Date());
            ElementImpl contactType = (ElementImpl) this.database.getContactType(code);
            contactType.updateElement(description, current, comment, modified);
            try {
                this.database.updateContactType(contactType.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteContactType(String code) throws RemoteException {
        if (this.database.contactTypeExists(code) && this.database.canDeleteContactType(code)) {
            try {
                this.database.deleteContactType(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE END REASONS     ////////
    @Override
    public int createEndReason(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.endReasonExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element endReason = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createEndReason(endReason);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEndReason(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.endReasonExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " End Reason", modifiedBy, new Date());
            ElementImpl endReason = (ElementImpl) this.database.getEndReason(code);
            endReason.updateElement(description, current, comment, modified);
            try {
                this.database.updateEndReason(endReason.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteEndReason(String code) throws RemoteException {
        if (this.database.endReasonExists(code) && this.database.canDeleteEndReason(code)) {
            try {
                this.database.deleteEndReason(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE RELATIONSHIPS     ////////
    @Override
    public int createRelationship(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.relationshipExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element relationship = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createRelationship(relationship);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateRelationship(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.relationshipExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated " + code + " Relationship", modifiedBy, new Date());
            ElementImpl relationship = (ElementImpl) this.database.getRelationship(code);
            relationship.updateElement(description, current, comment, modified);
            try {
                this.database.updateRelationship(relationship.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteRelationship(String code) throws RemoteException {
        if (this.database.relationshipExists(code) && this.database.canDeleteRelationship(code)) {
            try {
                this.database.deleteRelationship(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE ADDRESSES     ////////
    @Override
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String comment, String createdBy) throws RemoteException {
        Note note = this.createNote(comment, createdBy);
        Address address = new Address(this.addressRef++, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, note, createdBy, new Date());
        try {
            this.database.createAddress(address);
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address.getAddressRef();
    }

    @Override
    public int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String country, String postcode, String comment, String modifiedBy) throws RemoteException {
        if (database.addressExists(aRef)) {
            Address address = (Address) database.getAddress(aRef);
            address.updateAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, new ModifiedBy("Updated Address " + aRef, modifiedBy, new Date()));
            try {
                this.database.updateAddress(address.getAddressRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteAddress(int addrRef) throws RemoteException {
        if (this.database.addressExists(addrRef) && this.database.canDeleteAddress(addrRef)) {
            try {
                this.database.deleteAddress(addrRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHOD TO CREATE ADDRESS USAGE     ////////
    private AddressUsage createAddressUsage(int addrRef, Date startDate, String createdBy) throws RemoteException {
        System.out.println("Address exists? - Server createAddress: " + database.addressExists(addrRef));
        if (database.addressExists(addrRef)) {
            Note note = this.createNote(null, createdBy);
            AddressUsage addressUsage = new AddressUsage(this.addressUsageRef++, database.getAddress(addrRef), startDate, note, createdBy, new Date());
            System.out.println("Address Usage - Server createAddress: " + addressUsage);
            return addressUsage;
        }
        return null;
    }

    //////     METHOD TO CREATE CONTACT     ////////
    private ContactInterface createContact(String contactTypeCode, String value, Date startDate, String createdBy) throws RemoteException {
        if (database.contactTypeExists(contactTypeCode)) {
            Note note = this.createNote(null, createdBy);
            ContactInterface contact = new Contact(this.contactRef++, database.getContactType(contactTypeCode), value, startDate, note, createdBy, new Date());
            return contact;
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON     ////////
    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException {
        System.out.println();
        System.out.println("Title exists? - createPersonServer - " + this.database.titleExists(titleCode));
        System.out.println("Gender exists? - createPersonServer - " + this.database.genderExists(genderCode));
        System.out.println("Marital exists? - createPersonServer - " + this.database.maritalStatusExists(maritalStatusCode));
        System.out.println("Ethnic exists? - createPersonServer - " + this.database.ethnicOriginExists(ethnicOriginCode));
        System.out.println("Language exists? - createPersonServer - " + this.database.languageExists(languageCode));
        System.out.println("Nationality exists? - createPersonServer - " + this.database.nationalityExists(nationalityCode));
        System.out.println("Sexuality exists? - createPersonServer - " + this.database.sexualityExists(sexualityCode));
        System.out.println("Religion exists? - createPersonServer - " + this.database.religionExists(religionCode));
        System.out.println("Address exists? - createPersonServer - " + this.database.addressExists(addrRef));
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode) && this.database.addressExists(addrRef)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                AddressUsage address = this.createAddressUsage(addrRef, addressStartDate, createdBy);
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), address, createdBy, new Date());
                
                System.out.println("Person createPersonServer - " + person.getName());
                try {
                    this.database.createPerson(person);
                    this.database.createPersonAddressUsage(address, person.getPersonRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);   
                }
                return person.getPersonRef();
            }
        }
        return 0;
    }

    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy) throws RemoteException {
        if (this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                Person person = new Person(personRef++, this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), null, createdBy, new Date());
                try {
                    this.database.createPerson(person);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return person.getPersonRef();
            }
        }
        return 0;
    }

    @Override
    public int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.titleExists(titleCode) && this.database.genderExists(genderCode) && this.database.maritalStatusExists(maritalStatusCode) && this.database.ethnicOriginExists(ethnicOriginCode) && this.database.languageExists(languageCode) && this.database.nationalityExists(nationalityCode) && this.database.sexualityExists(sexualityCode) && this.database.religionExists(religionCode)) {
            if (this.database.getTitle(titleCode).isCurrent() && this.database.getGender(genderCode).isCurrent() && this.database.getMaritalStatus(maritalStatusCode).isCurrent() && this.database.getEthnicOrigin(ethnicOriginCode).isCurrent() && this.database.getLanguage(languageCode).isCurrent() && this.database.getNationality(nationalityCode).isCurrent() && this.database.getSexuality(sexualityCode).isCurrent() && this.database.getReligion(religionCode).isCurrent()) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person " + pRef, modifiedBy, new Date());
                Person person = (Person) this.database.getPerson(pRef);
                person.updatePerson(this.database.getTitle(titleCode), forename, middleNames, surname, dateOfBirth, nationalInsurance, this.database.getGender(genderCode),
                        this.database.getMaritalStatus(maritalStatusCode), this.database.getEthnicOrigin(ethnicOriginCode), this.database.getLanguage(languageCode), this.database.getNationality(nationalityCode),
                        this.database.getSexuality(sexualityCode), this.database.getReligion(religionCode), modified);
                try {
                    this.database.updatePerson(person.getPersonRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deletePerson(int pRef) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.canDeletePerson(pRef)) {
            try {
                this.database.deletePerson(pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON NOTES     ////////
    @Override
    public int createPersonNote(int pRef, String comment, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef)) {
            Note note = this.createNote(comment, createdBy);
            Person person = (Person) this.database.getPerson(pRef);
            person.createNote(note, new ModifiedBy("Created Person Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updatePerson(pRef);
                this.database.createPersonNote(pRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef)) {
            Person person = (Person) this.database.getPerson(pRef);
            if (person.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) person.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Person Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updatePersonNote(pRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deletePersonNote(int pRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef)) {
            Person person = (Person) this.database.getPerson(pRef);
            if (person.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) person.getNote(nRef);
                if (!note.hasBeenModified()) {
                    person.deleteNote(nRef, new ModifiedBy("Deleted Person Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updatePerson(pRef);
                        this.database.deletePersonNote(pRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD PERSON DOCUMENT     ////////
    @Override
    public int createPersonDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && !this.database.getPerson(pRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Person" + pRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Person person = (Person) this.database.getPerson(pRef);
            person.createDocument(document, new ModifiedBy("Created Person Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updatePerson(pRef);
                this.database.createPersonDoc(pRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.documentExists(dRef) && this.database.getPerson(pRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePersonDocument(int pRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef)) {
            Person person = (Person) this.database.getPerson(pRef);
            if (person.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) person.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    person.deleteDocument(dRef, new ModifiedBy("Deleted Person Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updatePerson(pRef);
                        this.database.deletePersonDoc(pRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadPersonDocument(int pRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.getPerson(pRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A PERSON CONTACT     ////////
    @Override
    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = (Contact) this.createContact(contactTypeCode, value, date, createdBy);
                try {
                    this.database.createPersonContact(contact, pRef);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return contact.getContactRef();
            }
        }
        return 0;
    }

    @Override
    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Person person = (Person) this.database.getPerson(pRef);
            if (person.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Person Contact " + cRef, modifiedBy, new Date());
                Contact contact = (Contact) this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, comment, modified);
                try {
                    this.database.updatePersonContact(contact.getContactRef(), person.getPersonRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deletePersonContact(int pRef, int cRef, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.contactExists(cRef) && this.database.canDeleteContact(cRef)) {
            Person person = (Person) this.database.getPerson(pRef);
            person.deleteContact(this.database.getContact(cRef), new ModifiedBy("Deleted Person Contact " + cRef, modifiedBy, new Date()));
            try {
                this.database.updatePerson(pRef);
                this.database.deletePersonContact(cRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PERSON ADDRESS USAGES     ////////
    @Override
    public int createPersonAddressUsage(int pRef, int addrRef, Date startDate, String createdBy) throws RemoteException {
        if (database.personExists(pRef)) {
            AddressUsageInterface addressUsage = this.createAddressUsage(addrRef, startDate, createdBy);
            Person person = (Person) this.database.getPerson(pRef);
            person.createAddress(addressUsage, new ModifiedBy("Created Address " + addressUsage.getAddressUsageRef(), createdBy, new Date()));
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = (AddressUsage) this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage " + addrUsageRef, modifiedBy, new Date()));
            try {
                this.database.updatePersonAddressUsage(addrUsageRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.addressUsageExists(aRef) && this.database.canDeleteAddressUsage(aRef)) {
            try {
                this.database.deletePersonAddressUsage(aRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE     ////////
    @Override
    public int createOffice(String officeCode, int addrRef, Date startDate, String createdBy) throws RemoteException {
        if (!this.database.officeExists(officeCode) && this.database.addressExists(addrRef)) {
            Office office = new Office(officeCode, this.database.getAddress(addrRef), startDate, createdBy, new Date());
            try {
                this.database.createOffice(office);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateOffice(String officeCode, Date startDate, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(officeCode)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Office " + officeCode, modifiedBy, new Date());
            Office office = (Office) this.database.getOffice(officeCode);
            office.setStartDate(startDate, modified);
            try {
                this.database.updateOffice(office.getOfficeCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteOffice(String code) throws RemoteException {
        if (this.database.officeExists(code) && this.database.canDeleteOffice(code)) {
            try {
                this.database.deleteOffice(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE OFFICE NOTES     ////////
    @Override
    public int createOfficeNote(String officeCode, String comment, String createdBy) throws RemoteException {
        if (this.database.officeExists(officeCode)) {
            Note note = this.createNote(comment, createdBy);
            Office office = (Office) this.database.getOffice(officeCode);
            office.createNote(note, new ModifiedBy("Created Office Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateOffice(officeCode);
                this.database.createOfficeNote(officeCode, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateOfficeNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(officeCode)) {
            Office office = (Office) this.database.getOffice(officeCode);
            if (office.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) office.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Office Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateOfficeNote(officeCode, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteOfficeNote(String officeCode, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(officeCode)) {
            Office office = (Office) this.database.getOffice(officeCode);
            if (office.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) office.getNote(nRef);
                if (!note.hasBeenModified()) {
                    office.deleteNote(nRef, new ModifiedBy("Deleted Office Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateOffice(officeCode);
                        this.database.deleteOfficeNote(officeCode, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD OFFICE DOCUMENT     ////////
    @Override
    public int createOfficeDocument(String oCode, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.officeExists(oCode) && !this.database.getOffice(oCode).hasDocument(fileName)) {
            fileName = documentsLocation + "Office" + oCode + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Office office = (Office) this.database.getOffice(oCode);
            office.createDocument(document, new ModifiedBy("Created Office Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateOffice(oCode);
                this.database.createOfficeDoc(oCode, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateOfficeDocument(String oCode, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(oCode) && this.database.documentExists(dRef) && this.database.getOffice(oCode).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteOfficeDocument(String oCode, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(oCode)) {
            Office office = (Office) this.database.getOffice(oCode);
            if (office.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) office.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    office.deleteDocument(dRef, new ModifiedBy("Deleted Office Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateOffice(oCode);
                        this.database.deleteOfficeDoc(oCode, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadOfficeDocument(String oCode, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.officeExists(oCode) && this.database.getOffice(oCode).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A OFFICE CONTACT     ////////
    @Override
    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException {
        if (this.database.officeExists(oCode) && this.database.contactTypeExists(contactTypeCode)) {
            if (this.database.getContactType(contactTypeCode).isCurrent()) {
                Contact contact = (Contact) this.createContact(contactTypeCode, value, date, createdBy);
                try {
                    this.database.createOfficeContact(contact, oCode);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return contact.getContactRef();
            }
        }
        return 0;
    }

    @Override
    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(oCode) && this.database.contactExists(cRef) && this.database.contactTypeExists(contactTypeCode) && this.database.getContactType(contactTypeCode).isCurrent()) {
            Office office = (Office) this.database.getOffice(oCode);
            if (office.hasContact(cRef)) {
                ModifiedByInterface modified = new ModifiedBy("Updated Office Contact " + cRef, modifiedBy, new Date());
                Contact contact = (Contact) this.database.getContact(cRef);
                contact.updateContact(this.database.getContactType(contactTypeCode), value, date, comment, modified);
                try {
                    this.database.updateOfficeContact(contact.getContactRef(), office.getOfficeCode());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteOfficeContact(String code, int cRef) throws RemoteException {
        if (this.database.officeExists(code) && this.database.contactExists(cRef) && this.database.canDeleteContact(cRef)) {
            try {
                this.database.deleteOfficeContact(cRef, code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A INVOLVED PARTY     ////////
    @Override
    public int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, int address, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.relationshipExists(relationshipCode) && this.database.getRelationship(relationshipCode).isCurrent()) {
            InvolvedParty invParty = new InvolvedParty(invPartyRef++, aRef, this.database.getPerson(pRef), joint, main, start, this.database.getRelationship(relationshipCode), createdBy, new Date());
            Person person = (Person) invParty.getPerson();
            AddressUsage addressUsage = this.createAddressUsage(address, start, createdBy);
            person.createAddress(addressUsage, new ModifiedBy("Created New Address " + addressUsage.getAddressUsageRef(), createdBy, new Date()));
            try {
                this.database.createInvolvedParty(invParty);
                this.database.createPersonAddressUsage(addressUsage, person.getPersonRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return invParty.getInvolvedPartyRef();
        }
        return 0;
    }

    @Override
    public int updateInvolvedParty(int iRef, boolean joint, boolean main, Date start, String relationshipCode, String modifiedBy) throws RemoteException {
        if (this.database.invPartyExists(iRef) && this.database.relationshipExists(relationshipCode) && this.database.getRelationship(relationshipCode).isCurrent()) {
            InvolvedParty invParty = (InvolvedParty) this.database.getInvolvedParty(iRef);
            ModifiedByInterface modified = new ModifiedBy("Updated Involved Party " + iRef, modifiedBy, new Date());
            invParty.updateInvolvedParty(joint, start, this.database.getRelationship(relationshipCode), modified);
            try {
                this.database.updateInvolvedParty(invParty.getInvolvedPartyRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteInvolvedParty(int iRef) throws RemoteException {
        if (this.database.invPartyExists(iRef) && this.database.canDeleteInvolvedParty(iRef)) {
            try {
                this.database.deleteInvolvedParty(iRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE INVOLVED PARTY NOTES     ////////
    @Override
    public int createInvolvedPartyNote(int iRef, String comment, String createdBy) throws RemoteException {
        if (this.database.invPartyExists(iRef)) {
            Note note = this.createNote(comment, createdBy);
            InvolvedParty invParty = (InvolvedParty) this.database.getInvolvedParty(iRef);
            invParty.createNote(note, new ModifiedBy("Created Involved Party Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateInvolvedParty(iRef);
                this.database.createInvolvedPartyNote(iRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateInvolvedPartyNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.invPartyExists(eRef)) {
            InvolvedParty invParty = (InvolvedParty) this.database.getInvolvedParty(eRef);
            if (invParty.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) invParty.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Involved Party Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateInvolvedPartyNote(eRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteInvolvedPartyNote(int iRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.invPartyExists(iRef)) {
            InvolvedParty invParty = (InvolvedParty) this.database.getInvolvedParty(iRef);
            if (invParty.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) invParty.getNote(nRef);
                if (!note.hasBeenModified()) {
                    invParty.deleteNote(nRef, new ModifiedBy("Deleted Involved Party Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateInvolvedParty(iRef);
                        this.database.deleteInvolvedPartyNote(iRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A APPLICATION     ////////
    @Override
    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && this.database.addressExists(addrRef) && this.database.relationshipExists(relationshipCode)) {
            Application application = new Application(this.appRef++, corrName, appStartDate, this.createAddressUsage(addrRef, appStartDate, createdBy), createdBy, new Date());
            int mainAppRef = this.createInvolvedParty(pRef, application.getApplicationRef(), true, true, appStartDate, relationshipCode, addrRef, createdBy);
            application.addInvolvedParty(this.database.getInvolvedParty(mainAppRef), null);
            try {
                this.database.createApplication(application);
                this.database.createApplicationAddressUsage((AddressUsage) application.getCurrentApplicationAddress(), application.getApplicationRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return application.getApplicationRef();
        }
        return 0;
    }

    @Override
    public int updateApplication(int aRef, String corrName, Date appStartDate, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            Application application = (Application) this.database.getApplication(aRef);
            application.updateApplication(corrName, appStartDate, new ModifiedBy("Updated Application " + aRef, modifiedBy, new Date()));
            try {
                this.database.updateApplication(application.getApplicationRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return application.getApplicationRef();
        }
        return 0;
    }

    @Override
    public int deleteApplication(int aRef, String modifiedBy) throws RemoteException {
        if (this.database.invPartyExists(aRef) && this.database.canDeleteInvolvedParty(aRef)) {
            List<InvolvedPartyInterface> household = this.database.getApplication(aRef).getHousehold();
            for (InvolvedPartyInterface invParty : household) {
                Person person = (Person) invParty.getPerson();
                List<AddressUsageInterface> peopleAddresses = person.getAddresses();
                if (!peopleAddresses.isEmpty()) {
                    if (peopleAddresses.size() > 1) {
                        AddressUsage pOld = (AddressUsage) peopleAddresses.get(peopleAddresses.size() - 2);
                        pOld.setEndDate(null, new ModifiedBy("Reinstated Address Usage " + pOld.getAddressUsageRef(), modifiedBy, new Date()));
                        try {
                            this.database.updatePersonAddressUsage(person.getPersonRef(), pOld.getAddressUsageRef());
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    AddressUsageInterface current = peopleAddresses.get(peopleAddresses.size() - 1);
                    person.deleteAddress(current, new ModifiedBy("Deleted Address Usage " + current.getAddressUsageRef(), modifiedBy, new Date()));
                    try {
                        this.database.deletePersonAddressUsage(person.getPersonRef(), current.getAddressUsageRef());
                        this.database.deleteInvolvedParty(invParty.getInvolvedPartyRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            AddressUsageInterface appAddress = this.database.getApplication(aRef).getCurrentApplicationAddress();
            try {
                this.database.deleteApplicationAddressUsage(appAddress.getAddressUsageRef(), aRef);
                this.database.deleteApplication(aRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO AMEND AN APPLICATION HOUSEHOLD     ////////
    @Override
    public int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException {
        if (database.applicationExists(aRef) && database.personExists(pRef) && this.database.relationshipExists(relationshipCode)) {
            Application application = (Application) database.getApplication(aRef);
            if (application.isPersonHouseholdMember(pRef)) {
                int iRef = this.createInvolvedParty(aRef, pRef, false, joint, start, relationshipCode, application.getCurrentApplicationAddress().getAddress().getAddressRef(), createdBy);
                if (iRef >= 1) {
                    application.addInvolvedParty((InvolvedParty) this.database.getInvolvedParty(iRef), new ModifiedBy("Added Involved Party " + iRef, createdBy, new Date()));
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException {
        if (database.applicationExists(aRef) && this.database.invPartyExists(iRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = (Application) database.getApplication(aRef);
            InvolvedParty mainApp = (InvolvedParty) application.getMainApp();
            InvolvedParty party = (InvolvedParty) this.database.getInvolvedParty(iRef);
            if (application.isPersonHouseholdMember(party.getPersonRef())) {
                if (!party.isMainInd() && party.isOver18()) {
                    application.changeMainApp(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Changed Main App from " + mainApp.getInvolvedPartyRef() + " to " + iRef, modifiedBy, new Date()));
                }
                Person person = (Person) mainApp.getPerson();
                AddressUsage address = (AddressUsage) person.getCurrentAddress();
                address.setEndDate(end, new ModifiedBy("Ended Address " + address.getAddressUsageRef(), modifiedBy, new Date()));
                try {
                    this.database.updatePersonAddressUsage(address.getAddressUsageRef(), person.getPersonRef());
                    this.database.updateInvolvedParty(mainApp.getInvolvedPartyRef());
                    this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException {
        if (database.applicationExists(aRef) && this.database.invPartyExists(iRef) && this.database.endReasonExists(endReasonCode) && this.database.getEndReason(endReasonCode).isCurrent()) {
            Application application = (Application) this.database.getApplication(aRef);
            InvolvedParty party = (InvolvedParty) this.database.getInvolvedParty(iRef);
            if (application.isPersonHouseholdMember(party.getPersonRef())) {
                if (!party.isCurrent()) {
                    application.endInvolvedParty(party.getInvolvedPartyRef(), end, this.database.getEndReason(endReasonCode), new ModifiedBy("Ended Involved Party " + iRef, modifiedBy, new Date()));
                    Person person = (Person) party.getPerson();
                    AddressUsage address = (AddressUsage) person.getCurrentAddress();
                    address.setEndDate(end, new ModifiedBy("Ended Address " + address.getAddressUsageRef(), modifiedBy, new Date()));
                    try {
                        this.database.updatePersonAddressUsage(address.getAddressUsageRef(), person.getPersonRef());
                        this.database.updateInvolvedParty(party.getInvolvedPartyRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHOD TO SET AN APPLICATION TENANCY     ////////
    private int setApplicationTenancy(int aRef, int tRef, int addrRef, Date startDate, String createdBy) throws RemoteException {
        if (database.applicationExists(aRef) && database.tenancyExists(tRef) && this.database.addressExists(addrRef)) {
            Application application = (Application) database.getApplication(aRef);
            List<PropertyInterface> properties = application.setTenancy(tRef, new ModifiedBy("Assigned Application Tenancy " + tRef, createdBy, new Date()));
            application.setAppStatusCode("HSED");
            for (PropertyInterface property : properties) {
                try {
                    this.database.endPropertyInterest(aRef, property.getPropRef());
                    this.database.updateApplication(aRef);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.createApplicationAddressUsage(aRef, addrRef, startDate, createdBy);
            return 1;
        }
        return 0;
    }

    //////     METHODS TO AMEND AN APPLICATIONS INTEREST IN PROPERTIES     ////////
    @Override
    public int addInterestedProperty(int aRef, int pRef, String createdBy) throws RemoteException {
        if (database.propertyExists(pRef) && database.applicationExists(aRef)) {
            Application application = (Application) database.getApplication(aRef);
            application.addInterestedProperty(database.getProperty(pRef), new ModifiedBy("Added Interested Property " + pRef, createdBy, new Date()));
            try {
                this.database.createPropertyInterest(aRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int endInterestInProperty(int aRef, int pRef, String createdBy) throws RemoteException {
        if (database.propertyExists(pRef) && database.applicationExists(aRef)) {
            Application application = (Application) database.getApplication(aRef);
            application.endInterestInProperty(database.getProperty(pRef), new ModifiedBy("Ended Interest in Property " + pRef, createdBy, new Date()));
            try {
                this.database.endPropertyInterest(aRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE APPLICATION NOTES     ////////
    @Override
    public int createApplicationNote(int aRef, String comment, String createdBy) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            Note note = this.createNote(comment, createdBy);
            Application application = (Application) this.database.getApplication(aRef);
            application.createNote(note, new ModifiedBy("Created Application Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateApplication(aRef);
                this.database.createApplicationNote(aRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateApplicationNote(int aRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            Application application = (Application) this.database.getApplication(aRef);
            if (application.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) application.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Application Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateApplicationNote(aRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteApplicationNote(int aRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            Application application = (Application) this.database.getApplication(aRef);
            if (application.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) application.getNote(nRef);
                if (!note.hasBeenModified()) {
                    application.deleteNote(nRef, new ModifiedBy("Deleted Application Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateApplication(aRef);
                        this.database.deleteApplicationNote(aRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD APPLICATION DOCUMENT     ////////
    @Override
    public int createApplicationDocument(int aRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.applicationExists(aRef) && !this.database.getApplication(aRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Application" + aRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Application application = (Application) this.database.getApplication(aRef);
            application.createDocument(document, new ModifiedBy("Created Application Document " +document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateApplication(aRef);
                this.database.createApplicationDoc(aRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateApplicationDocument(int aRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef) && this.database.documentExists(dRef) && this.database.getApplication(aRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteApplicationDocument(int aRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            Application application = (Application) this.database.getApplication(aRef);
            if (application.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) application.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    application.deleteDocument(dRef, new ModifiedBy("Deleted Application Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateApplication(aRef);
                        this.database.deleteApplicationDoc(aRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadApplicationDocument(int aRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.applicationExists(aRef) && this.database.getApplication(aRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE APPLICATION ADDRESS USAGES     ////////
    @Override
    public int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate, String createdBy) throws RemoteException {
        if (this.database.applicationExists(applicationRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = (AddressUsage) this.createAddressUsage(addrRef, startDate, createdBy);
            Application application = (Application) database.getApplication(applicationRef);
            application.setAppAddress((AddressUsage) addressUsage, new ModifiedBy("Created App Address " + addressUsage.getAddressUsageRef(), createdBy, new Date()));
            try {
                this.database.createApplicationAddressUsage(addressUsage, applicationRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.createHouseholdAddressUsage(application, addrRef, startDate, createdBy);
            return 1;
        }
        return 0;
    }

    private void createHouseholdAddressUsage(Application application, int addrRef, Date startDate, String createdBy) throws RemoteException {
        for (InvolvedPartyInterface invParty : application.getHousehold()) {
            if (invParty.isCurrent()) {
                Person person = (Person) invParty.getPerson();
                AddressUsage tempAddress = this.createAddressUsage(addrRef, startDate, createdBy);
                person.createAddress(tempAddress, new ModifiedBy("Created Address " + tempAddress.getAddressUsageRef(), createdBy, new Date()));
                try {
                    this.database.createPersonAddressUsage(tempAddress, invParty.getPersonRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException {
        if (this.database.applicationExists(aRef) && this.database.addressUsageExists(addrUsageRef) && this.database.addressExists(addrRef)) {
            AddressUsage addressUsage = (AddressUsage) this.database.getAddressUsage(addrUsageRef);
            addressUsage.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage " + addrUsageRef, modifiedBy, new Date()));
            try {
                this.database.updateApplicationAddressUsage(addrUsageRef, aRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateHouseholdAddressUsage(this.database.getApplication(aRef), addrRef, startDate, comment, modifiedBy);
            return 1;
        }
        return 0;
    }

    private void updateHouseholdAddressUsage(ApplicationInterface application, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException {
        for (InvolvedPartyInterface invParty : application.getHousehold()) {
            if (invParty.isCurrent()) {
                Person person = (Person) invParty.getPerson();
                AddressUsage tempAddress = (AddressUsage) person.getCurrentAddress();
                tempAddress.updateAddress(this.database.getAddress(addrRef), startDate, comment, new ModifiedBy("Updated Address Usage " + tempAddress.getAddressUsageRef(), modifiedBy, new Date()));
                try {
                    this.database.updatePersonAddressUsage(tempAddress.getAddressUsageRef(), person.getPersonRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public int deleteApplicationAddressUsage(int addrRef, int aRef) throws RemoteException {
        if (this.database.applicationExists(aRef) && this.database.addressUsageExists(addrRef) && this.database.canDeleteAddressUsage(addrRef)) {
            try {
                this.database.deleteApplicationAddressUsage(aRef, addrRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    //////     METHODS TO CREATE AND DELETE EMPLOYEES     ////////
    @Override
    public int createEmployee(int pRef, String username, String password, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && !this.database.personEmployeeExists(pRef) && !this.database.userExists(username)) {
            Employee employee = new Employee(employeeRef++, this.database.getPerson(pRef), username, password, createdBy, new Date());
            try {
                this.database.createEmployee(employee);
                this.database.createUser(employee.getUser());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return employee.getEmployeeRef();
        }
        return 0;
    }
    
    @Override
    public int setEmployeeMemorableLocation(String memorableLocation, int eRef) throws RemoteException {
        if(this.database.employeeExists(eRef)) {
            Employee employee = (Employee) this.database.getEmployee(eRef);
            employee.setMemorableLocation(memorableLocation);
            try {
                this.database.updateEmployee(eRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmployeePassword(int eRef, String password, String modifiedBy) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = (Employee) this.database.getEmployee(eRef);
            employee.updatePassword(password, new ModifiedBy("Updated Password of Employee " + eRef, modifiedBy, new Date()));
            User user = employee.getUser();
            try {
                this.database.updateUser(user.getUsername());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    @Override
    public int forgotPassword(String email, int eRef, String username, String answer) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = (Employee) this.database.getEmployee(eRef);
            User user = employee.getUser();
            String empUsername = user.getUsername();
            empUsername = empUsername.toUpperCase();
            username = username.toUpperCase();
            if(username.equals(empUsername)) {
                String empAnswer = employee.getMemorableLocation();
                empAnswer = empAnswer.toUpperCase();
                answer = answer.toUpperCase();
                if(answer.equals(empAnswer)) {
                    String empEmail = this.getEmployeeEmail(eRef);
                    empEmail = empEmail.toUpperCase();
                    email = email.toUpperCase();
                    if(email.equals(empEmail)) {
                        String newPassword = new BigInteger(130, this.random).toString(32);
                        if (this.updateEmployeePassword(user.getEmployeeRef(), newPassword, user.getUsername()) > 0) {
                            String subject = "Password Reset - Employee Ref: " + user.getEmployeeRef();
                            String message = "Your password has been reset to " + newPassword;
                            this.sendEmail.sendEmail(empEmail, subject, message);
                            return 1;
                        }
                    }
                } else {
                    throw new InvalidUserException(username);
                }
            } else {
                throw new InvalidUserException(username);
            }
        }
        return 0;
    }
    
    private String getEmployeeEmail(int eRef) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            EmployeeInterface employee = this.database.getEmployee(eRef);
            PersonInterface person = employee.getPerson();
            String email = "";
            for (ContactInterface temp : person.getContacts()) {
                if(temp.getContactType().getCode().equals("EMAIL") && temp.isCurrent()) {
                    email = temp.getContactValue();
                }
            }
            return email;
        }
        return null;
    }

    @Override
    public int deleteEmployee(int eRef) throws RemoteException {
        if (this.database.employeeExists(eRef) && this.database.canDeleteEmployee(eRef)) {
            try {
                this.database.deleteEmployee(eRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE EMPLOYEE NOTES     ////////
    @Override
    public int createEmployeeNote(int eRef, String comment, String createdBy) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            Note note = this.createNote(comment, createdBy);
            Employee employee = (Employee) this.database.getEmployee(eRef);
            employee.createNote(note, new ModifiedBy("Created Employee Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateEmployee(eRef);
                this.database.createEmployeeNote(eRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmployeeNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = (Employee) this.database.getEmployee(eRef);
            if (employee.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employee.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Employee Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateEmployeeNote(eRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteEmployeeNote(int eRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            Employee employee = (Employee) this.database.getEmployee(eRef);
            if (employee.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employee.getNote(nRef);
                if (!note.hasBeenModified()) {
                    employee.deleteNote(nRef, new ModifiedBy("Deleted Employee Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateEmployee(eRef);
                        this.database.deleteEmployeeNote(eRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE AND DELETE LANDLORD     ////////
    @Override
    public int createLandlord(int pRef, String createdBy) throws RemoteException {
        if (this.database.personExists(pRef) && !this.database.personLandlordExists(pRef)) {
            Landlord landlord = new Landlord(landlordRef++, this.database.getPerson(pRef), createdBy, new Date());
            try {
                this.database.createLandlord(landlord);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return landlord.getLandlordRef();
        }
        return 0;
    }

    @Override
    public int deleteLandlord(int lRef) throws RemoteException {
        if (this.database.landlordExists(lRef) && this.database.canDeleteLandlord(lRef)) {
            try {
                this.database.deleteLandlord(lRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE LANDLORD NOTES     ////////
    @Override
    public int createLandlordNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (this.database.landlordExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            Landlord landlord = (Landlord) this.database.getLandlord(lRef);
            landlord.createNote(note, new ModifiedBy("Created Landlord Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateLandlord(lRef);
                this.database.createLandlordNote(lRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLandlordNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.landlordExists(lRef)) {
            Landlord landlord = (Landlord) this.database.getLandlord(lRef);
            if (landlord.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) landlord.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Landlord Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateLandlordNote(lRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteLandlordNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.landlordExists(lRef)) {
            Landlord landlord = (Landlord) this.database.getLandlord(lRef);
            if (landlord.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) landlord.getNote(nRef);
                if (!note.hasBeenModified()) {
                    landlord.deleteNote(nRef, new ModifiedBy("Deleted Landlord Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLandlord(lRef);
                        this.database.deleteLandlordNote(lRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A PROPERTY     ////////
    @Override
    public int createProperty(int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String createdBy) throws RemoteException {
        System.out.println("Address exists? - createPropertyServer - " + this.database.addressExists(addrRef));
        System.out.println("Prop Type exists - createPropertyServer - " + this.database.propTypeExists(propTypeCode));
        System.out.println("Prop Sub Type exists - createPropertyServer - " + this.database.propSubTypeExists(propSubTypeCode));
        if (this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = new Property(propRef++, this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), createdBy, new Date());
            System.out.println("Property created By - createPropertyServer - " + property.getCreatedBy());
            try {
                this.database.createProperty(property);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return property.getPropRef();
        }
        return 0;
    }

    @Override
    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.addressExists(addrRef) && this.database.propTypeExists(propTypeCode) && this.database.propSubTypeExists(propSubTypeCode)) {
            Property property = (Property) this.database.getProperty(pRef);
            property.updateProperty(this.database.getAddress(addrRef), startDate, this.database.getPropertyType(propTypeCode), this.database.getPropertySubType(propSubTypeCode), new ModifiedBy("Updated Property " + pRef, modifiedBy, new Date()));
            try {
                this.database.updateProperty(pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteProperty(int pRef) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.canDeleteProperty(pRef)) {
            try {
                this.database.deleteLandlord(pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY NOTES     ////////
    @Override
    public int createPropertyNote(int pRef, String comment, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            Note note = this.createNote(comment, createdBy);
            Property property = (Property) this.database.getProperty(pRef);
            property.createNote(note, new ModifiedBy("Created Property Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateProperty(pRef);
                this.database.createPropertyNote(pRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePropertyNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            Property property = (Property) this.database.getProperty(pRef);
            if (property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Property Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updatePropertyNote(pRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deletePropertyNote(int pRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            Property property = (Property) this.database.getProperty(pRef);
            if (property.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) property.getNote(nRef);
                if (!note.hasBeenModified()) {
                    property.deleteNote(nRef, new ModifiedBy("Deleted Property Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateProperty(pRef);
                        this.database.deletePropertyNote(pRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD PROPERTY DOCUMENT     ////////
    @Override
    public int createPropertyDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && !this.database.getProperty(pRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Property" + pRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Property property = (Property) this.database.getProperty(pRef);
            property.createDocument(document, new ModifiedBy("Created Property Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateProperty(pRef);
                this.database.createPropertyDoc(pRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePropertyDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.documentExists(dRef) && this.database.getProperty(pRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePropertyDocument(int pRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            Property property = (Property) this.database.getProperty(pRef);
            if (property.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) property.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    property.deleteDocument(dRef, new ModifiedBy("Deleted Property Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateProperty(pRef);
                        this.database.deletePropertyDoc(pRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadPropertyDocument(int pRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.getProperty(pRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE PROPERTY ELEMENTS     ////////
    @Override
    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, Double doubleValue, String comment, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.propElementExists(elementCode)) {
            Note note = this.createNote(comment, createdBy);
            PropertyElement propElement = new PropertyElement(propertyElementRef++, this.database.getPropElement(elementCode), startDate, charge, stringValue, doubleValue, note, createdBy, new Date());
            Property property = (Property) this.database.getProperty(pRef);
            property.createPropertyElement(propElement, new ModifiedBy("Created Property Element " + propElement.getPropertyElementRef(), createdBy, new Date()));
            try {
                this.database.createPropertyElementValue(pRef, propElement);
                this.database.updateProperty(pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, String modifiedBy) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            Property property = (Property) this.database.getProperty(pRef);
            if (property.hasPropElement(eRef)) {
                PropertyElement propElement = (PropertyElement) property.getPropElement(eRef);
                propElement.updatePropertyElement(startDate, stringValue, doubleValue, charge, comment, new ModifiedBy("Updated Property Element " + eRef, modifiedBy, new Date()));
                try {
                    this.database.updatePropertyElementValue(pRef, propElement.getPropertyElementRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deletePropertyElement(int eRef, int pRef) throws RemoteException {
        if (this.database.propertyExists(eRef) && this.database.canDeletePropertyElementValue(eRef, pRef)) {
            try {
                this.database.deletePropertyElementValue(eRef, pRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLES     ////////
    @Override
    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, boolean update,
            boolean delete, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, boolean employeeDelete, String createdBy) throws RemoteException {
        if (!this.database.jobRoleExists(code)) {
            JobRole jobRole = new JobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete, createdBy, new Date());
            try {
                this.database.createJobRole(jobRole);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write, boolean update,
            boolean delete, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, boolean employeeDelete, String modifiedBy) throws RemoteException {
        if (this.database.jobRoleExists(code)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(code);
            jobRole.updateJobRole(jobTitle, jobDescription, salary, current, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete, new ModifiedBy("Updated Job Role " + code, modifiedBy, new Date()));
            List<ContractInterface> contracts = this.database.getContracts(null, null, null, null, null, null, null, code, null, null, null, null, null);
            for (ContractInterface contract : contracts) {
                EmployeeAccount account = (EmployeeAccount) this.database.getEmployeeAccount(contract.getAccountRef());
                account.setSalary(salary);
                try {
                    this.database.updateEmployeeAccount(account.getAccRef());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                this.database.updateJobRole(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteJobRole(String officeCode) throws RemoteException {
        if (this.database.officeExists(officeCode) && this.database.canDeleteOffice(officeCode)) {
            try {
                this.database.deleteOffice(officeCode);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLE NOTES     ////////
    @Override
    public int createJobRoleNote(String jobRoleCode, String comment, String createdBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode)) {
            Note note = this.createNote(comment, createdBy);
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            jobRole.createNote(note, new ModifiedBy("Created JobRole Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateJobRole(jobRoleCode);
                this.database.createJobRoleNote(jobRoleCode, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateJobRoleNote(String jobRoleCode, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            if (jobRole.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) jobRole.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated JobRole Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateJobRoleNote(jobRoleCode, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteJobRoleNote(String jobRoleCode, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.officeExists(jobRoleCode)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            if (jobRole.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) jobRole.getNote(nRef);
                if (!note.hasBeenModified()) {
                    jobRole.deleteNote(nRef, new ModifiedBy("Deleted JobRole Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateJobRole(jobRoleCode);
                        this.database.deleteJobRoleNote(jobRoleCode, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE AND DELETE JOB ROLE REQUIREMENTS     ////////
    @Override
    public int createJobRoleRequirement(String jobRoleCode, String requirement, String createdBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobRequirementExists(requirement)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            if (!jobRole.hasRequirement(requirement)) {
                jobRole.createJobRequirement(this.database.getJobRequirement(requirement), new ModifiedBy("Created Job Role Requirement " + requirement, createdBy, new Date()));
                try {
                    this.database.updateJobRole(jobRoleCode);
                    this.database.createJobRoleRequirement(jobRoleCode, requirement);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteJobRoleRequirement(String jobRoleCode, String requirement, String deletedBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobRequirementExists(requirement)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            jobRole.removeJobRequirement(this.database.getJobRequirement(requirement), new ModifiedBy("Removed Job Requirement " + requirement, deletedBy, new Date()));
            try {
                this.database.updateJobRole(jobRoleCode);
                this.database.deleteJobRoleRequirement(requirement, jobRoleCode);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE JOB ROLE BENEFITS     ////////
    @Override
    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String createdBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobBenefitExists(benefit)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            if (!jobRole.hasCurrentBenefit(benefit)) {
                Note note = this.createNote(comment, createdBy);
                JobRoleBenefit jobBenefit = new JobRoleBenefit(jobBenefitRef++, this.database.getJobBenefit(benefit), startDate, salaryBenefit, stringValue, doubleValue, note, createdBy, new Date());
                jobRole.createJobBenefit(jobBenefit, new ModifiedBy("Created Job Role Benefit " + jobBenefit.getBenefitRef(), createdBy, new Date()));
                try {
                    this.database.updateJobRole(jobRoleCode);
                    this.database.createJobRoleBenefit(jobRoleCode, jobBenefit);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String modifiedBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefitRef) && this.database.getJobRole(jobRoleCode).hasBenefit(benefitRef) && this.database.jobBenefitExists(benefitCode)) {
            JobRoleBenefit jobRoleBenefit = (JobRoleBenefit) this.database.getJobRoleBenefit(benefitRef);
            jobRoleBenefit.updateJobRoleBenefit(stringValue, doubleValue, salaryBenefit, startDate, comment, new ModifiedBy("Updated Job Role Benefit " + benefitRef, modifiedBy, new Date()));
            try {
                this.database.updateJobRoleBenefit(jobRoleCode, benefitRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate, String modifiedBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefitRef) && this.database.getJobRole(jobRoleCode).hasBenefit(benefitRef)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            jobRole.endJobBenefit(benefitRef, endDate, new ModifiedBy("Ended Job Role Benefit " + benefitRef, modifiedBy, new Date()));
            try {
                this.database.updateJobRole(jobRoleCode);
                this.database.updateJobRoleBenefit(jobRoleCode, benefitRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteJobRoleBenefit(String jobRoleCode, int benefitRef, String deletedBy) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode) && this.database.jobRoleBenefitExists(benefitRef)) {
            JobRole jobRole = (JobRole) this.database.getJobRole(jobRoleCode);
            jobRole.deleteJobBenefit(benefitRef, new ModifiedBy("Deleted Job Role Benefit " + benefitRef, deletedBy, new Date()));
            try {
                this.database.updateJobRole(jobRoleCode);
                this.database.deleteJobRoleBenefit(benefitRef, jobRoleCode);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE JOB REQUIREMENTS     ////////
    @Override
    public int createJobRequirement(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.jobRequirementExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element requirement = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createJobRequirement(requirement);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateJobRequirement(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.jobRequirementExists(code)) {
            ElementImpl requirement = (ElementImpl) this.database.getJobRequirement(code);
            requirement.updateElement(description, current, comment, new ModifiedBy("Updated Requirement " + code, modifiedBy, new Date()));
            try {
                this.database.updateJobRequirement(requirement.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteJobRequirement(String requirement) throws RemoteException {
        if (this.database.jobRequirementExists(requirement) && this.database.canDeleteJobRequirement(requirement)) {
            try {
                this.database.deleteJobRequirement(requirement);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE JOB BENEFITS     ////////
    @Override
    public int createJobBenefit(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.jobBenefitExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element benefit = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createJobBenefit(benefit);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateJobBenefit(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.jobBenefitExists(code)) {
            ElementImpl benefit = (ElementImpl) this.database.getJobBenefit(code);
            benefit.updateElement(description, current, comment, new ModifiedBy("Updated Benefit " + benefit, modifiedBy, new Date()));
            try {
                this.database.updateJobBenefit(benefit.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteJobBenefit(String requirement) throws RemoteException {
        if (this.database.jobRequirementExists(requirement) && this.database.canDeleteJobRequirement(requirement)) {
            try {
                this.database.deleteJobRequirement(requirement);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A TENANCY     ////////
    @Override
    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.applicationExists(aRef) && this.database.tenancyTypeExists(tenTypeCode) && this.database.officeExists(officeCode)) {
            
            ApplicationInterface application = this.database.getApplication(aRef);
            Property property = (Property) this.database.getProperty(pRef);
            Office office = (Office) this.database.getOffice(officeCode);
            Tenancy tenancy = new Tenancy(agreementRef++, startDate, length, accountRef, property, application, this.database.getTenancyType(tenTypeCode), officeCode, createdBy, new Date());
            RentAccount rentAcc = new RentAccount(accountRef++, tenancy, createdBy, new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("Created Tenancy " + tenancy.getAgreementRef(), createdBy, new Date());
            try {
                this.database.createTenancy(tenancy);
                this.database.createRentAccount(rentAcc);
                
                // UPDATE APPLICATION
                this.setApplicationTenancy(aRef, tenancy.getAccountRef(), property.getAddress().getAddressRef(), startDate, createdBy);
                
                // UPDATE PROPERTY
                property.setPropStatus("OCCP", modifiedBy);
                this.database.updateProperty(property.getPropRef());
                
                // UPDATE OFFICE
                office.createAgreement(tenancy, modifiedBy);
                this.database.updateOffice(office.getOfficeCode());
                office.createAccount(rentAcc, new ModifiedBy("Created Rent Account " + rentAcc.getAccRef(), createdBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.updateUserTenancies(tenancy.getOfficeCode());
                return tenancy.getAgreementRef();
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && this.database.tenancyTypeExists(tenTypeCode)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            tenancy.updateAgreement(name, startDate, length, null);
            tenancy.setTenType(this.database.getTenancyType(tenTypeCode), new ModifiedBy("Updated Tenancy " + tRef, modifiedBy, new Date()));
            this.updateRentAccount(tenancy.getAccountRef(), name, startDate, (tenancy.getRent() + tenancy.getCharges()), modifiedBy);
            try {
                this.database.updateTenancy(tenancy.getAgreementRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateUserTenancies(tenancy.getOfficeCode());
            return 1;
        }
        return 0;
    }
    
    public int endTenancy(int tRef, Date endDate, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            Application application = (Application) tenancy.getApplication();
            Property property = (Property) tenancy.getProperty();
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(tenancy.getAccountRef());    
            ModifiedBy modified = new ModifiedBy("Ended Tenancy " + tRef, modifiedBy, new Date());
            try {
                // Update Application Address
                AddressUsage appAddr = (AddressUsage) application.getCurrentApplicationAddress();
                appAddr.setEndDate(endDate, modified);
                this.database.updateApplicationAddressUsage(appAddr.getAddressUsageRef(), application.getApplicationRef());
                
                // Update current household addresses
                List<InvolvedPartyInterface> household = application.getHousehold();
                if (!household.isEmpty()) {
                    for (InvolvedPartyInterface invParty : household) {
                        if (invParty.isCurrent()) {
                            Person person = (Person) invParty.getPerson();
                            AddressUsage pAddr = (AddressUsage) person.getCurrentAddress();
                            pAddr.setEndDate(endDate, modified);
                            this.database.updatePersonAddressUsage(pAddr.getAddressUsageRef(), person.getPersonRef());
                        }
                    }
                }
                
                // Clear Application Tenancy Ref
                application.clearTenancy(modified);
                this.database.updateApplication(application.getApplicationRef());
                
                // Update Property Status
                property.setPropStatus("VOID", modified);
                this.database.updateProperty(property.getPropRef());
                
                // End Tenancy
                tenancy.setActualEndDate(endDate, modified);
                this.database.updateTenancy(tenancy.getAgreementRef());
                
                // End Rent Account
                rentAcc.setEndDate(endDate, new ModifiedBy("Ended Rent Account " + rentAcc.getAccRef(), modifiedBy, new Date()));
                this.database.updateRentAccount(rentAcc.getAccRef());
                this.updateUserTenancies(tenancy.getOfficeCode());
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int deleteTenancy(int tRef, String deletedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && this.database.canDeleteTenancy(tRef)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            Application application = (Application) tenancy.getApplication();
            Property property = (Property) tenancy.getProperty();
            Office office = (Office) this.database.getOffice(tenancy.getOfficeCode());
            try {
                
                // ROLL BACK OF APPLICATION DETAILS
                List<InvolvedPartyInterface> household = application.getHousehold();
                for (InvolvedPartyInterface invParty : household) {
                    Person person = (Person) invParty.getPerson();
                    List<AddressUsageInterface> peopleAddresses = person.getAddresses();
                    if (!peopleAddresses.isEmpty()) {
                        if (peopleAddresses.size() > 1) {
                            AddressUsage pOld = (AddressUsage) peopleAddresses.get(peopleAddresses.size() - 2);
                            pOld.setEndDate(null, new ModifiedBy("Reinstated Address Usage " + pOld.getAddressUsageRef(), deletedBy, new Date()));
                            this.database.updatePersonAddressUsage(person.getPersonRef(), pOld.getAddressUsageRef());
                        }
                        AddressUsageInterface current = peopleAddresses.get(peopleAddresses.size() - 1);
                        person.deleteAddress(current, new ModifiedBy("Deleted Address Usage " + current.getAddressUsageRef(), deletedBy, new Date()));
                        this.database.deletePersonAddressUsage(person.getPersonRef(), current.getAddressUsageRef());
                    }
                }
                AddressUsageInterface appAddress = application.getCurrentApplicationAddress();
                application.deleteAppAddress(appAddress.getAddressUsageRef(), new ModifiedBy("Deleted Address Usage " + appAddress.getAddressUsageRef(), deletedBy, new Date()));
                this.database.updateApplication(application.getApplicationRef());
                this.database.deleteApplicationAddressUsage(appAddress.getAddressUsageRef(), application.getApplicationRef());
                AddressUsage curAppAddress = (AddressUsage) application.getCurrentApplicationAddress();
                curAppAddress.setEndDate(null, new ModifiedBy("Reinstated Address Usage " + curAppAddress.getAddressUsageRef(), deletedBy, new Date()));
                this.database.updateApplicationAddressUsage(curAppAddress.getAddressUsageRef(), application.getApplicationRef());
                application.clearTenancy(new ModifiedBy("Deleted Tenancy", deletedBy, new Date()));
                this.database.updateApplication(application.getApplicationRef());
                application.setAppStatusCode("OPEN");
                
                /// ROOL BACK OF PROPERTY DETAILS
                property.setPropStatus("VOID", new ModifiedBy("Deleted Tenancy " + tRef, deletedBy, new Date()));
                this.database.updateProperty(property.getPropRef());
                
                // ROLL BACK OF OFFICE DETAILS
                office.deleteAgreement(tenancy.getAgreementRef(), new ModifiedBy("Deleted Tenancy " + tRef, deletedBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                office.deleteAccount(tenancy.getAccountRef(), new ModifiedBy("Deleted Rent Account " + tenancy.getAccountRef(), deletedBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.database.deleteTenancy(tenancy.getAgreementRef());
                this.updateUserRentAccounts(tenancy.getOfficeCode());
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    //////     METHODS TO CREATE, UPDATE AND DELETE TENANCY NOTES     ////////
    @Override
    public int createTenancyNote(int tRef, String comment, String createdBy) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            Note note = this.createNote(comment, createdBy);
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            tenancy.createNote(note, new ModifiedBy("Created Tenancy Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateTenancy(tRef);
                this.database.createTenancyNote(tRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTenancyNote(int tRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            if (tenancy.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) tenancy.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Tenancy Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateTenancyNote(tRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteTenancyNote(int tRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            if (tenancy.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) tenancy.getNote(nRef);
                if (!note.hasBeenModified()) {
                    tenancy.deleteNote(nRef, new ModifiedBy("Deleted Tenancy Note " + tRef, modifiedBy, new Date()));
                    try {
                        this.database.updateTenancy(tRef);
                        this.database.deleteTenancyNote(tRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD TENANCY DOCUMENT     ////////
    @Override
    public int createTenancyDocument(int tRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && !this.database.getTenancy(tRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Tenancy" + tRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            tenancy.createDocument(document, new ModifiedBy("Created Tenancy Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateTenancy(tRef);
                this.database.createTenancyDoc(tRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTenancyDocument(int tRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && this.database.documentExists(dRef) && this.database.getTenancy(tRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteTenancyDocument(int tRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            Tenancy tenancy = (Tenancy) this.database.getTenancy(tRef);
            if (tenancy.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) tenancy.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    tenancy.deleteDocument(dRef, new ModifiedBy("Deleted Tenancy Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateTenancy(tRef);
                        this.database.deleteTenancyDoc(tRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadTenancyDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.tenancyExists(tRef) && this.database.getTenancy(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE TENANCY TYPES     ////////
    @Override
    public int createTenancyType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (!this.database.tenancyTypeExists(code)) {
            Note note = this.createNote(comment, createdBy);
            Element tenancyType = new ElementImpl(code, description, note, createdBy, new Date());
            try {
                this.database.createTenancyType(tenancyType);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTenancyType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (this.database.tenancyTypeExists(code)) {
            ModifiedByInterface modified = new ModifiedBy("Updated Tenancy Type " + code, modifiedBy, new Date());
            ElementImpl tenancyType = (ElementImpl) this.database.getTenancyType(code);
            tenancyType.updateElement(description, current, comment, modified);
            try {
                this.database.updateTenancyType(tenancyType.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteTenancyType(String code) throws RemoteException {
        if (this.database.tenancyTypeExists(code) && this.database.canDeleteTenancyType(code)) {
            try {
                this.database.deleteTenancyType(code);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A LEASE     ////////
    @Override
    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.officeExists(officeCode)) {
            Property property = (Property) this.database.getProperty(pRef);
            Office office = (Office) this.database.getOffice(officeCode);
            Lease lease = (Lease) new Lease(agreementRef++, startDate, length, accountRef, this.database.getProperty(pRef), management, expenditure, officeCode, createdBy, new Date());
            LeaseAccount leaseAcc = new LeaseAccount(accountRef++, lease, createdBy, new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("Created Lease " + lease.getAgreementRef(), createdBy, new Date());
            try {
                this.database.createLease(lease);
                this.database.createLeaseAccount(leaseAcc);
                
                // UPDATE PROPERTY DETAILS
                property.setPropStatus("VOID", modifiedBy);
                this.database.updateProperty(property.getPropRef());
                
                // UPDATE OFFICE DETAILS
                office.createAgreement(lease, modifiedBy);
                this.database.updateOffice(office.getOfficeCode());
                office.createAccount(leaseAcc, new ModifiedBy("Created Lease Account " + leaseAcc.getAccRef(), createdBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.updateUserLeases(lease.getOfficeCode());
                return lease.getAgreementRef();
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, List<LandlordInterface> landlords, String officeCode, String createdBy) throws RemoteException {
        if (this.database.propertyExists(pRef) && this.database.officeExists(officeCode)) {
            int lRef = this.createLease(startDate, length, pRef, management, expenditure, officeCode, createdBy);
            
            // UPDATE LANDLORD DETAILS
            if (this.database.leaseExists(lRef)) {
                Lease lease = (Lease) this.database.getLease(lRef);
                for (LandlordInterface temp : landlords) {
                    lease.addLandlord(temp, null);
                }
                try {
                    this.database.updateProperty(pRef);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.updateUserLeases(lease.getOfficeCode());
                return lease.getAgreementRef();
            }
        }
        return 0;
    }

    @Override
    public int updateLease(int lRef, String name, Date startDate, int length, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            lease.updateAgreement(name, startDate, length, new ModifiedBy("Updated Lease " + lRef, modifiedBy, new Date()));
            try {
                this.database.updateLease(lease.getAgreementRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateLeaseAccount(lease.getAccountRef(), name, startDate, modifiedBy);
            this.updateUserLeases(lease.getOfficeCode());
            return 1;
        }
        return 0;
    }
    
    public int endLease(int lRef, Date endDate, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getEmployeeAccount(lease.getAccountRef());
            Property property = (Property) lease.getProperty();
            ModifiedBy modified = new ModifiedBy("Ended Lease " + lRef, modifiedBy, new Date());
            try {
                // Close Property
                property.setPropStatus("CLSD", modified);
                this.database.updateProperty(property.getPropRef());
                
                // End Lease
                lease.setActualEndDate(endDate, modified);
                this.database.updateContract(lease.getAgreementRef());
                
                // End Lease Account
                leaseAcc.setEndDate(endDate, modified);
                this.database.updateEmployeeAccount(leaseAcc.getAccRef());
                
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int deleteLease(int lRef, String deletedBy) throws RemoteException {
        if (this.database.leaseExists(lRef) && this.database.canDeleteLease(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            Property property = (Property) lease.getProperty();
            Office office = (Office) this.database.getOffice(lease.getOfficeCode());
            List<LandlordInterface> landlords = lease.getLandlords();
            ModifiedByInterface modified = new ModifiedBy("Deleted Lease " + lRef, deletedBy, new Date());
            try {
                /// ROOL BACK OF PROPERTY DETAILS
                property.setPropStatus("CLSD", modified);
                this.database.updateProperty(property.getPropRef());
                property.setLandlords(new ArrayList(), modified);
                
                /// ROLL BACK OF LANDLORD DETAILS
                for (LandlordInterface landlord : landlords) {
                    this.database.endLeaseLandlord(landlord.getLandlordRef(), lRef);
                }
                
                // ROLL BACK OF OFFICE DETAILS
                office.deleteAgreement(lease.getAgreementRef(), modified);
                this.database.updateOffice(office.getOfficeCode());
                office.deleteAccount(lease.getAccountRef(), new ModifiedBy("Deleted Lease Account " + lease.getAccountRef(), deletedBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.database.deleteLease(lease.getAgreementRef());
                this.updateUserLeases(lease.getOfficeCode());
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE LEASE NOTES     ////////
    @Override
    public int createLeaseNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            Lease lease = (Lease) this.database.getLease(lRef);
            lease.createNote(note, new ModifiedBy("Created Lease Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateLease(lRef);
                this.database.createLeaseNote(lRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLeaseNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            if (lease.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) lease.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Lease Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateLeaseNote(lRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteLeaseNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            if (lease.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) lease.getNote(nRef);
                if (!note.hasBeenModified()) {
                    lease.deleteNote(nRef, new ModifiedBy("Deleted Lease Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLease(lRef);
                        this.database.deleteLeaseNote(lRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD LEASE DOCUMENT     ////////
    @Override
    public int createLeaseDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.leaseExists(lRef) && !this.database.getLease(lRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Lease" + lRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Lease lease = (Lease) this.database.getLease(lRef);
            lease.createDocument(document, new ModifiedBy("Created Lease Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateLease(lRef);
                this.database.createLeaseDoc(lRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLeaseDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef) && this.database.documentExists(dRef) && this.database.getLease(lRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteLeaseDocument(int lRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            if (lease.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) lease.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    lease.deleteDocument(dRef, new ModifiedBy("Deleted Lease Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLease(lRef);
                        this.database.deleteLeaseDoc(lRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadLeaseDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.leaseExists(tRef) && this.database.getLease(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO CREATE, AND END A LANDLORD FOR A LEASE     ////////
    @Override
    public int createLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef) && this.database.landlordExists(landRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            if (!lease.isAlreadyLandlord(lRef)) {
                LandlordInterface landlord = this.database.getLandlord(landRef);
                lease.addLandlord(landlord, new ModifiedBy("Assigned Landlord " + landRef + " to Lease " + lRef, modifiedBy, new Date()));
                try {
                    this.database.createLeaseLandlord(landRef, lRef);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int endLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseExists(lRef) && this.database.landlordExists(landRef)) {
            Lease lease = (Lease) this.database.getLease(lRef);
            if (lease.isAlreadyLandlord(lRef)) {
                lease.endLandlord(lRef, new ModifiedBy("Ended Landlord " + landRef + " for Lease " + lRef, modifiedBy, new Date()));
                try {
                    this.database.endLeaseLandlord(landRef, lRef);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE A CONTRACT     ////////
    @Override
    public int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode, String createdBy) throws RemoteException {
        if (this.database.employeeExists(eRef) && this.database.jobRoleExists(jobRoleCode) && this.database.officeExists(officeCode)) {
            
            Employee employee = (Employee) this.database.getEmployee(eRef);
            JobRoleInterface jobRole = this.database.getJobRole(jobRoleCode);
            Office office = (Office) this.database.getOffice(officeCode);
            Contract contract = new Contract(agreementRef++, accountRef, startDate, length, employee, jobRole, officeCode, createdBy, new Date());
            EmployeeAccount employeeAcc = new EmployeeAccount(accountRef++, contract, createdBy, new Date());
            ModifiedByInterface modified = new ModifiedBy("Created Contract " + contract.getAgreementRef(), createdBy, new Date());
            try {
                // UPDATE EMPLOYEE DETAILS
                employee.createContract(contract, modified);
                this.database.updateEmployee(employee.getEmployeeRef());
                employee.updatePermissions(jobRole.getRead(), jobRole.getWrite(), jobRole.getUpdate(), jobRole.getDelete(), jobRole.getEmployeeRead(), jobRole.getEmployeeWrite(), jobRole.getEmployeeUpdate(), jobRole.getEmployeeDelete(),null);
                this.database.updateUser(employee.getUser().getUsername());
                
                // UPDATE OFFICE DETAILS
                office.createAgreement(contract, modified);
                this.database.updateOffice(office.getOfficeCode());
                office.createAccount(employeeAcc, new ModifiedBy("Created Employee Account " + employeeAcc.getAccRef(), createdBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.database.createContract(contract);
                this.database.createEmployeeAccount(employeeAcc);
                
                return contract.getAgreementRef();
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    /**
     *
     * @param cRef
     * @param name
     * @param startDate
     * @param length
     * @param modifiedBy
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateContract(int cRef, String name, Date startDate, int length, String modifiedBy) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            Contract contract = (Contract) this.database.getContract(cRef);
            contract.updateAgreement(name, startDate, length, new ModifiedBy("Updated Contract " + cRef, modifiedBy, new Date()));
            try {
                this.database.updateContract(contract.getAgreementRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateEmployeeAccount(contract.getAccountRef(), name, startDate, modifiedBy);
            return 1;
        }
        return 0;
    }
    
    public int endContract(int cRef, Date endDate, String modifiedBy) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            Contract contract = (Contract) this.database.getContract(cRef);
            EmployeeAccount empAcc = (EmployeeAccount) this.database.getEmployeeAccount(contract.getAccountRef());
            ModifiedBy modified = new ModifiedBy("Ended Contract " + cRef, modifiedBy, new Date());
                try {
                // End Contract
                contract.setActualEndDate(endDate, modified);
                this.database.updateContract(contract.getAgreementRef());
                
                // End Employee Account
                empAcc.setEndDate(endDate, modified);
                this.database.updateEmployeeAccount(empAcc.getAccRef());
                
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int deleteContract(int cRef, String deletedBy) throws RemoteException {
        if (this.database.contractExists(cRef) && this.database.canDeleteContract(cRef)) {
            ContractInterface contract = this.database.getContract(cRef);
            Employee employee = (Employee) contract.getEmployee();
            Office office = (Office) this.database.getOffice(contract.getOfficeCode());
            ModifiedByInterface modified = new ModifiedBy("Deleted Contract " + cRef, deletedBy, new Date());
            try {
                // ROLL BACK EMPLOYEE DETAILS
                employee.deleteContract(cRef, modified);
                this.database.updateEmployee(employee.getEmployeeRef());
                List<ContractInterface> contracts = employee.getContracts();
                if (!contracts.isEmpty()) {
                    ContractInterface oldContract = contracts.get(contracts.size() - 1);
                    JobRoleInterface jobRole = oldContract.getJobRole();
                    employee.updatePermissions(jobRole.getRead(), jobRole.getWrite(), jobRole.getUpdate(), jobRole.getDelete(), jobRole.getEmployeeRead(), jobRole.getEmployeeWrite(), jobRole.getEmployeeUpdate(), jobRole.getEmployeeDelete(), null);
                    this.database.updateUser(employee.getUser().getUsername());
                }
                
                // ROLL BACK OFFICE DETAILS
                office.deleteAgreement(cRef, modified);
                this.database.updateOffice(office.getOfficeCode());
                office.deleteAccount(contract.getAccountRef(), new ModifiedBy("Deleted Employee Account " + contract.getAccountRef(), deletedBy, new Date()));
                this.database.updateOffice(office.getOfficeCode());
                
                this.database.deleteContract(contract.getAgreementRef());
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE CONTRACT NOTES     ////////
    @Override
    public int createContractNote(int cRef, String comment, String createdBy) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            Note note = this.createNote(comment, createdBy);
            Contract contract = (Contract) this.database.getContract(cRef);
            contract.createNote(note, new ModifiedBy("Created Contract Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateContract(cRef);
                this.database.createContractNote(cRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }
    
    @Override
    public int updateContractNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.invPartyExists(cRef)) {
            ContractInterface contract = this.database.getContract(cRef);
            if (contract.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) contract.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Contract Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateContractNote(cRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteContractNote(int cRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            Contract contract = (Contract) this.database.getContract(cRef);
            if (contract.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) contract.getNote(nRef);
                if (!note.hasBeenModified()) {
                    contract.deleteNote(nRef, new ModifiedBy("Deleted Contract Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateContract(cRef);
                        this.database.deleteContractNote(cRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD CONTRACT DOCUMENT     ////////
    @Override
    public int createContractDocument(int cRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.contractExists(cRef) && !this.database.getContract(cRef).hasDocument(fileName)) {
            fileName = documentsLocation + "Contract" + cRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            Contract contract = (Contract) this.database.getContract(cRef);
            contract.createDocument(document, new ModifiedBy("Created Contract Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateContract(cRef);
                this.database.createContractDoc(cRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateContractDocument(int cRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.contractExists(cRef) && this.database.documentExists(dRef) && this.database.getContract(cRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteContractDocument(int cRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            Contract contract = (Contract) this.database.getContract(cRef);
            if (contract.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) contract.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    contract.deleteDocument(dRef, new ModifiedBy("Deleted Contract Document " +document.getDocumentRef(), modifiedBy, new Date()));
                    try {
                        this.database.updateContract(cRef);
                        this.database.deleteContractDoc(cRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadContractDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.contractExists(tRef) && this.database.getContract(tRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO UPDATE RENT ACCOUNT     ////////
    private int updateRentAccount(int rRef, String name, Date startDate, double rent, String modifiedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccount account = (RentAccount) this.database.getRentAccount(rRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Rent Account " + rRef, modifiedBy, new Date()));
            account.setRent(rent);
            try {
                this.database.updateRentAccount(account.getAccRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE RENT ACCOUNT NOTES     ////////
    @Override
    public int createRentAccNote(int rRef, String comment, String createdBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef)) {
            Note note = this.createNote(comment, createdBy);
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rRef);
            rentAcc.createNote(note, new ModifiedBy("Created Rent Account Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateContract(rRef);
                this.database.createRentAccountNote(rRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateRentAccNote(int rRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccountInterface rentAcc = this.database.getRentAccount(rRef);
            if (rentAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) rentAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Rent Account Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateRentAccountNote(rRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteRentAccNote(int rRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rRef);
            if (rentAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) rentAcc.getNote(nRef);
                if (!note.hasBeenModified()) {
                    rentAcc.deleteNote(nRef, new ModifiedBy("Deleted Rent Account Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateContract(rRef);
                        this.database.deleteRentAccountNote(rRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD RENT ACCOUNT DOCUMENT     ////////
    @Override
    public int createRentAccDocument(int rRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef) && !this.database.getRentAccount(rRef).hasDocument(fileName)) {
            fileName = documentsLocation + "RentAccount" + rRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rRef);
            rentAcc.createDocument(document, new ModifiedBy("Created Rent Account Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateRentAccount(rRef);
                this.database.createRentAccountDoc(rRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateRentAccDocument(int rRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef) && this.database.documentExists(dRef) && this.database.getRentAccount(rRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteRentAccDocument(int rRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef)) {
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rRef);
            if (rentAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) rentAcc.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    rentAcc.deleteDocument(dRef, new ModifiedBy("Deleted Rent Account Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateRentAccount(rRef);
                        this.database.deleteRentAccountDoc(rRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }

            }
        }
        return 0;
    }

    @Override
    public byte[] downloadRentAccDocument(int rRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.rentAccountExists(rRef) && this.database.getRentAccount(rRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO UPDATE LEASE ACCOUNT     ////////
    private int updateLeaseAccount(int lRef, String name, Date startDate, String modifiedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount account = (LeaseAccount) this.database.getLeaseAccount(lRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Lease Account " + lRef, modifiedBy, new Date()));
            try {
                this.database.updateLeaseAccount(account.getAccRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE LEASE ACCOUNT NOTES     ////////
    @Override
    public int createLeaseAccNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef)) {
            Note note = this.createNote(comment, createdBy);
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getLeaseAccount(lRef);
            leaseAcc.createNote(note, new ModifiedBy("Created Lease Account Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateLeaseAccount(lRef);
                this.database.createLeaseAccountNote(lRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLeaseAccNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccountInterface leaseAcc = this.database.getLeaseAccount(lRef);
            if (leaseAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) leaseAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Lease Account Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateLeaseAccountNote(lRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteLeaseAccNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getLeaseAccount(lRef);
            if (leaseAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) leaseAcc.getNote(nRef);
                if (!note.hasBeenModified()) {
                    leaseAcc.deleteNote(nRef, new ModifiedBy("Deleted Lease Account Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLeaseAccount(lRef);
                        this.database.deleteLeaseAccountNote(lRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD LEASE ACCOUNT DOCUMENT     ////////
    @Override
    public int createLeaseAccDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef) && !this.database.getLeaseAccount(lRef).hasDocument(fileName)) {
            fileName = documentsLocation + "LeaseAccount" + lRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getLeaseAccount(lRef);
            leaseAcc.createDocument(document, new ModifiedBy("Created Lease Account Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateLeaseAccount(lRef);
                this.database.createLeaseAccountDoc(lRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateLeaseAccDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef) && this.database.documentExists(dRef) && this.database.getLeaseAccount(lRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteLeaseAccDocument(int lRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef)) {
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getLeaseAccount(lRef);
            if (leaseAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) leaseAcc.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    leaseAcc.deleteDocument(dRef, new ModifiedBy("Deleted Lease Account Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLeaseAccount(lRef);
                        this.database.deleteLeaseAccountDoc(lRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadLeaseAccDocument(int lRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lRef) && this.database.getLeaseAccount(lRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }

    //////     METHODS TO UPDATE EMPLOYEE ACCOUNTS     ////////
    private int updateEmployeeAccount(int eRef, String name, Date startDate, String modifiedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount account = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            account.updateAccount(startDate, name, new ModifiedBy("Updated Employee Account " + eRef, modifiedBy, new Date()));
            try {
                this.database.updateEmployeeAccount(account.getAccRef());
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    //////     METHODS TO CREATE, UPDATE AND DELETE EMPLOYEE NOTES     ////////
    @Override
    public int createEmployeeAccNote(int eRef, String comment, String createdBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef)) {
            Note note = this.createNote(comment, createdBy);
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            employeeAcc.createNote(note, new ModifiedBy("Created Employee Account Note " + note.getReference(), createdBy, new Date()));
            try {
                this.database.updateLeaseAccount(eRef);
                this.database.createEmployeeAccountNote(eRef, note);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmployeeAccNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            if (employeeAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employeeAcc.getNote(nRef);
                note.setNote(comment, new ModifiedBy("Updated Employee Account Note " + nRef, modifiedBy, new Date()));
                try {
                    this.database.updateEmployeeAccountNote(eRef, note.getReference());
                } catch (SQLException ex) {
                    Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteEmployeeAccNote(int eRef, int nRef, String modifiedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            if (employeeAcc.hasNote(nRef)) {
                NoteImpl note = (NoteImpl) employeeAcc.getNote(nRef);
                if (!note.hasBeenModified()) {
                    employeeAcc.deleteNote(nRef, new ModifiedBy("Deleted Employee Account Note " + nRef, modifiedBy, new Date()));
                    try {
                        this.database.updateLeaseAccount(eRef);
                        this.database.deleteEmployeeAccountNote(eRef, note.getReference());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //////     METHODS TO CREATE, DELETE AND DOWNLOAD EMPLOYEE ACCOUNT DOCUMENT     ////////
    @Override
    public int createEmployeeAccDocument(int eRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef) && !this.database.getEmployeeAccount(eRef).hasDocument(fileName)) {
            fileName = documentsLocation + "EmployeeAccount" + eRef + " - " + fileName + "v1";
            DocumentImpl document = this.uploadDocument(fileName, buffer, comment, createdBy);
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            employeeAcc.createDocument(document, new ModifiedBy("Created Employee Account Document " + document.getDocumentRef(), createdBy, new Date()));
            try {
                this.database.updateEmployeeAccount(eRef);
                this.database.createEmployeeAccountDoc(eRef, document);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmployeeAccDocument(int eRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef) && this.database.documentExists(dRef) && this.database.getEmployeeAccount(eRef).hasDocument(dRef)) {
            this.uploadNewVersionDocument(dRef, buffer, modifiedBy);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteEmployeeAccDocument(int eRef, int dRef, String modifiedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef)) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eRef);
            if (employeeAcc.hasDocument(dRef)) {
                DocumentImpl document = (DocumentImpl) employeeAcc.getDocument(dRef);
                if (!document.hasBeenModified()) {
                    employeeAcc.deleteDocument(dRef, new ModifiedBy("Deleted Employee Account Document " + dRef, modifiedBy, new Date()));
                    try {
                        this.database.updateEmployeeAccount(eRef);
                        this.database.deleteEmployeeAccountDoc(eRef, document.getDocumentRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public byte[] downloadEmployeeAccDocument(int eRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eRef) && this.database.getEmployeeAccount(eRef).hasDocument(dRef)) {
            return this.downloadDocument(dRef, version);
        }
        return null;
    }
    
    //////     METHODS TO CREATE ACCOUNT TRANSACTIONS     ////////    
    @Override
    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        System.out.println("rentAcc exists? - Server createRentAccTran - " + this.database.rentAccountExists(rAccRef));
        System.out.println("personFrom exists? - Server createRentAccTran - " + this.database.personExists(fromRef));
        System.out.println("personTo exists? - Server createRentAccTran - " + this.database.personExists(toRef));
        if (this.database.rentAccountExists(rAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, rAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rAccRef);
            rentAcc.createTransaction(transaction, new ModifiedBy("Created Rent Transaction " + transaction.getTransactionRef(), createdBy, new Date()));
            try {
                this.database.updateRentAccount(rAccRef);
                this.database.createRentAccountTransaction(rentAcc.getAccRef(), transaction);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateUserRentAccounts(rentAcc.getOfficeCode());
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteRentAccTransaction(int tRef, int rAccRef, String deletedBy) throws RemoteException {
        if (this.database.rentAccountExists(rAccRef)) {
            try {
                this.database.deleteRentAccountTransaction(rAccRef, tRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            RentAccount rentAcc = (RentAccount) this.database.getRentAccount(rAccRef);
            rentAcc.deleteTransaction(tRef, new ModifiedBy("Deleted Rent Transaction " + tRef, deletedBy, new Date()));
            this.updateUserRentAccounts(rentAcc.getOfficeCode());
            return 1;
        }
        return 0;
    }

    @Override
    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        if (this.database.leaseAccountExists(lAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, lAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            LeaseAccount account = (LeaseAccount) this.database.getLeaseAccount(lAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Lease Transaction " + transaction.getTransactionRef(), createdBy, new Date()));
            try {
                this.database.updateLeaseAccount(lAccRef);
                this.database.createLeaseAccountTransaction(account.getAccRef(), transaction);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteLeaseAccTransaction(int tRef, int lAccRef, String deletedBy) throws RemoteException {
        if (this.database.leaseAccountExists(lAccRef)) {
            try {
                this.database.deleteLeaseAccountTransaction(lAccRef, tRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            LeaseAccount leaseAcc = (LeaseAccount) this.database.getLeaseAccount(lAccRef);
            leaseAcc.deleteTransaction(tRef, new ModifiedBy("Deleted Lease Transaction " + tRef, deletedBy, new Date()));
            return 1;
        }
        return 0;
    }

    @Override
    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        if (this.database.employeeAccountExists(eAccRef) && this.database.personExists(fromRef) && this.database.personExists(toRef)) {
            Note note = this.createNote(comment, createdBy);
            Transaction transaction = new Transaction(transactionRef++, eAccRef, fromRef, toRef, amount, debit, transactionDate, note, createdBy, new Date());
            EmployeeAccount account = (EmployeeAccount) this.database.getEmployeeAccount(eAccRef);
            account.createTransaction(transaction, new ModifiedBy("Created Employee Transaction " + transaction.getTransactionRef(), createdBy, new Date()));
            try {
                this.database.updateLeaseAccount(eAccRef);
                this.database.createLeaseAccountTransaction(account.getAccRef(), transaction);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteEmployeeAccTransaction(int tRef, int eAccRef, String deletedBy) throws RemoteException {
        if (this.database.employeeAccountExists(eAccRef)) {
            try {
                this.database.deleteEmployeeAccountTransaction(eAccRef, tRef);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            EmployeeAccount employeeAcc = (EmployeeAccount) this.database.getEmployeeAccount(eAccRef);
            employeeAcc.deleteTransaction(tRef, new ModifiedBy("Deleted Transaction " + tRef, deletedBy, new Date()));
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

    ///// METHODS TO RETURN LISTS OF OBJECTS
    @Override
    public List<OfficeInterface> getOffices() throws RemoteException {
        return this.database.getOffices();
    }

    @Override
    public List<AddressInterface> getAddresses() throws RemoteException {
        return this.database.getAddresses();
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        return this.database.getUsers();
    }

    @Override
    public List<LandlordInterface> getLandlords() throws RemoteException {
        return this.database.getLandlords();
    }

    @Override
    public List<EmployeeInterface> getEmployees() throws RemoteException {
        return this.database.getEmployees();
    }

    @Override
    public List<JobRoleInterface> getJobRoles() throws RemoteException {
        return this.database.getJobRoles();
    }

    ///// METHODS TO CHECK IF OBJECT ALREADY EXISTS
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

    @Override
    public boolean personEmployeeExists(int pRef) throws RemoteException {
        return this.database.personEmployeeExists(pRef);
    }

    @Override
    public boolean personLandlordExists(int pRef) throws RemoteException {
        return this.database.personLandlordExists(pRef);
    }

    // SEARCH METHODS
    @Override
    public AddressInterface getAddress(int aRef) throws RemoteException {
        if (this.database.addressExists(aRef)) {
            return this.database.getAddress(aRef);
        }
        return null;
    }

    @Override
    public JobRoleInterface getJobRole(String jobRoleCode) throws RemoteException {
        if (this.database.jobRoleExists(jobRoleCode)) {
            return this.database.getJobRole(jobRoleCode);
        }
        return null;
    }

    @Override
    public OfficeInterface getOffice(String officeCode) throws RemoteException {
        if (this.database.officeExists(officeCode)) {
            return this.database.getOffice(officeCode);
        }
        return null;
    }

    @Override
    public ApplicationInterface getApplication(int aRef) throws RemoteException {
        if (this.database.applicationExists(aRef)) {
            return this.database.getApplication(aRef);
        }
        return null;
    }

    @Override
    public PropertyInterface getProperty(int pRef) throws RemoteException {
        if (this.database.propertyExists(pRef)) {
            return this.database.getProperty(pRef);
        }
        return null;
    }

    @Override
    public EmployeeInterface getEmployee(int eRef) throws RemoteException {
        if (this.database.employeeExists(eRef)) {
            return this.database.getEmployee(eRef);
        }
        return null;
    }

    @Override
    public LandlordInterface getLandlord(int lRef) throws RemoteException {
        if (this.database.landlordExists(lRef)) {
            return this.database.getLandlord(lRef);
        }
        return null;
    }

    @Override
    public TenancyInterface getTenancy(int tRef) throws RemoteException {
        if (this.database.tenancyExists(tRef)) {
            return this.database.getTenancy(tRef);
        }
        return null;
    }

    @Override
    public LeaseInterface getLease(int lRef) throws RemoteException {
        if (this.database.leaseExists(lRef)) {
            return this.database.getLease(lRef);
        }
        return null;
    }

    @Override
    public ContractInterface getContract(int cRef) throws RemoteException {
        if (this.database.contractExists(cRef)) {
            return this.database.getContract(cRef);
        }
        return null;
    }

    @Override
    public RentAccountInterface getRentAccount(int rAccRef) throws RemoteException {
        if (this.database.rentAccountExists(rAccRef)) {
            return this.database.getRentAccount(rAccRef);
        }
        return null;
    }

    @Override
    public LeaseAccountInterface getLeaseAccount(int lAccRef) throws RemoteException {
        if (this.database.leaseAccountExists(lAccRef)) {
            return this.database.getLeaseAccount(lAccRef);
        }
        return null;
    }

    @Override
    public EmployeeAccountInterface getEmployeeAccount(int eAccRef) throws RemoteException {
        if (this.database.employeeAccountExists(eAccRef)) {
            return this.database.getEmployeeAccount(eAccRef);
        }
        return null;
    }

    @Override
    public List<PersonInterface> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
    }

    @Override
    public List<AddressInterface> getAddresses(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getAddresses();
    }

    @Override
    public List<ApplicationInterface> getApplications(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getApplications(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
    }

    @Override
    public List<ApplicationInterface> getPeopleApplications(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        List<PersonInterface> tempPeople = this.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        return this.database.getPeopleApplications(tempPeople);
    }

    @Override
    public List<ApplicationInterface> getAddressApplications(String buildingNumber, String buildingName,
            String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        List<AddressInterface> tempAddresses = this.getAddresses(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        return this.database.getAddressApplications(tempAddresses);
    }

    @Override
    public List<ApplicationInterface> getCorrNameApplcations(String name) throws RemoteException {
        return this.database.getCorrNameApplcations(name);
    }

    @Override
    public List<EmployeeInterface> getPeopleEmployees(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getPeopleEmployees(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
    }

    @Override
    public List<LandlordInterface> getPeopleLandlords(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getPeopleLandlords(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
    }

    @Override
    public List<PropertyInterface> getProperties(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
    }

    @Override
    public ApplicationInterface getInvPartyApplcation(int iPartyRef) throws RemoteException {
        return this.database.getInvPartyApplcation(iPartyRef);
    }

    @Override
    public List<TenancyInterface> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef,
            Integer applicationRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getTenancies(name, startDate, expectedEndDate, endDate, length, propertyRef, applicationRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<TenancyInterface> getApplicationTenancies(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<ApplicationInterface> tempApplications = this.getApplications(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
        return this.database.getApplicationTenancies(tempApplications);
    }

    @Override
    public List<TenancyInterface> getApplicationTenancies(int applicationRef) throws RemoteException {
        return this.database.getApplicationTenancies(applicationRef);
    }

    @Override
    public List<TenancyInterface> getPropertyTenancies(int pRef) throws RemoteException {
        return this.database.getPropertyTenancies(pRef);
    }

    @Override
    public List<TenancyInterface> getPropertyTenancies(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        List<PropertyInterface> tempProperties = this.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        return this.database.getPropertyTenancies(tempProperties);
    }

    @Override
    public List<TenancyInterface> getNameTenancies(String name) throws RemoteException {
        return this.database.getNameTenancies(name);
    }

    @Override
    public List<TenancyInterface> getOfficeTenancies(String office) throws RemoteException {
        return this.database.getOfficeTenancies(office);
    }

    @Override
    public List<LeaseInterface> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getLeases(name, startDate, expectedEndDate, endDate, length, propertyRef, management, expenditure, accountRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<LeaseInterface> getPropertyLeases(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        List<PropertyInterface> tempProperties = this.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        return this.database.getPropertyLeases(tempProperties);
    }

    @Override
    public List<LeaseInterface> getPropertyLeases(int propertyRef) throws RemoteException {
        return this.database.getPropertyLeases(propertyRef);
    }

    @Override
    public List<LeaseInterface> getNameLeases(String name) throws RemoteException {
        return this.database.getNameLeases(name);
    }

    @Override
    public List<LeaseInterface> getOfficeLeases(String office) throws RemoteException {
        return this.database.getOfficeLeases(office);
    }

    @Override
    public List<LeaseInterface> getLandlordLeases(int lRef) throws RemoteException {
        return this.database.getLandlordLeases(lRef);
    }

    @Override
    public List<LeaseInterface> getLandlordLeases(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        List<LandlordInterface> tempLandlords = this.getPeopleLandlords(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance,
                genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        return this.database.getLandlordLeases(tempLandlords);
    }

    @Override
    public List<ContractInterface> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef, Integer empRef,
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getContracts(name, startDate, expectedEndDate, endDate, length, propertyRef, empRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<ContractInterface> getNameContracts(String name) throws RemoteException {
        return this.database.getNameContracts(name);
    }

    @Override
    public List<ContractInterface> getOfficeContracts(String office) throws RemoteException {
        return this.database.getOfficeContracts(office);
    }

    @Override
    public List<ContractInterface> getEmployeeContracts(int ref) throws RemoteException {
        return this.database.getEmployeeContracts(ref);
    }

    @Override
    public List<ContractInterface> getJobRoleContracts(String code) throws RemoteException {
        return this.database.getJobRoleContracts(code);
    }

    @Override
    public List<RentAccountInterface> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getRentAccounts(name, startDate, endDate, balance, rent, agreementRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<RentAccountInterface> getNameRentAcc(String name) throws RemoteException {
        return this.database.getNameRentAcc(name);
    }

    @Override
    public List<RentAccountInterface> getOfficeRentAcc(String office) throws RemoteException {
        return this.database.getOfficeRentAcc(office);
    }

    @Override
    public List<RentAccountInterface> getTenanciesRentAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef,
            Integer applicationRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<TenancyInterface> tempTenancies = this.getTenancies(name, startDate, expectedEndDate, endDate, length, propertyRef, applicationRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
        return this.database.getTenanciesRentAccounts(tempTenancies);
    }

    @Override
    public RentAccountInterface getTenancyRentAcc(int tenancyRef) throws RemoteException {
        return this.database.getTenancyRentAcc(tenancyRef);
    }

    @Override
    public List<LeaseAccountInterface> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getLeaseAccounts(name, startDate, endDate, balance, expenditure, agreementRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<LeaseAccountInterface> getNameLeaseAcc(String name) throws RemoteException {
        return this.database.getNameLeaseAcc(name);
    }

    @Override
    public List<LeaseAccountInterface> getOfficeLeaseAcc(String office) throws RemoteException {
        return this.database.getOfficeLeaseAcc(office);
    }

    @Override
    public List<LeaseAccountInterface> getLeasesLeaseAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<LeaseInterface> tempLeases = this.getLeases(name, startDate, expectedEndDate, endDate, length, propertyRef, management, expenditure, accountRef, officeCode, current, createdBy, createdDate);
        return this.database.getLeasesLeaseAccounts(tempLeases);
    }
    
    @Override
    public LeaseAccountInterface getLeaseLeaseAcc(int lRef) throws RemoteException {
        return this.database.getLeaseLeaseAcc(lRef);
    }

    @Override
    public List<EmployeeAccountInterface> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getEmployeeAccounts(name, startDate, endDate, balance, salary, agreementRef, officeCode, current, createdBy, createdDate);
    }

    @Override
    public List<EmployeeAccountInterface> getNameEmployeeAcc(String name) throws RemoteException {
        return this.database.getNameEmployeeAcc(name);
    }

    @Override
    public List<EmployeeAccountInterface> getOfficeEmployeeAcc(String office) throws RemoteException {
        return this.database.getOfficeEmployeeAcc(office);
    }

    @Override
    public List<EmployeeAccountInterface> getContractsEmployeeAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef, Integer empRef,
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<ContractInterface> tempContracts = this.getContracts(name, startDate, expectedEndDate, endDate, length, propertyRef, empRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
        return this.database.getContractsEmployeeAccounts(tempContracts);
    }

    @Override
    public EmployeeAccountInterface getContractEmployeeAcc(int cRef) throws RemoteException {
        return this.database.getContractEmployeeAcc(cRef);
    }

    @Override
    public List<OfficeInterface> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        return this.database.getOffices(addrRef, startDate, current, createdBy, createdDate);
    }

      /// REPORTING METHODS
    @Override
    public List<TenancyInterface> getTenanciesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (this.database.employeeExists(eRef) && startDate != null && endDate != null && endDate.after(startDate)) {
            User user = this.database.getUser(eRef);
            if (user != null) {
                List<TenancyInterface> tempTenancies = this.getTenancies(null, null, null, null, null, null, null, null, null, null, null, user.getUsername(), null);
                List<TenancyInterface> employeeTenancies = new ArrayList();
                if (!tempTenancies.isEmpty()) {
                    for (TenancyInterface temp : tempTenancies) {
                        if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                            employeeTenancies.add(temp);
                        }
                    }
                }
                return employeeTenancies;
            }
        }
        return null;
    }

    @Override
    public List<TenancyInterface> getTenanciesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (this.database.officeExists(officeCode) && startDate != null && endDate != null && endDate.after(startDate)) {
            List<TenancyInterface> tempTenancies = this.getTenancies(null, null, null, null, null, null, null, null, null, officeCode, null, null, null);
            List<TenancyInterface> officeTenancies = new ArrayList();
            if (!tempTenancies.isEmpty()) {
                for (TenancyInterface temp : tempTenancies) {
                    if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                        officeTenancies.add(temp);
                    }
                }
            }
            return officeTenancies;
        }
        return null;
    }

    @Override
    public List<LeaseInterface> getLeasesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (this.database.employeeExists(eRef) && startDate != null && endDate != null && endDate.after(startDate)) {
            User user = this.database.getUser(eRef);
            if (user != null) {
                List<LeaseInterface> tempLeases = this.getLeases(null, null, null, null, null, null, null, null, null, null, null, user.getUsername(), null);
                List<LeaseInterface> employeeLeases = new ArrayList();
                if (!tempLeases.isEmpty()) {
                    for (LeaseInterface temp : tempLeases) {
                        if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                            employeeLeases.add(temp);
                        }
                    }
                }
                return employeeLeases;
            }
        }
        return null;
    }

    @Override
    public List<LeaseInterface> getLeasesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (this.database.officeExists(officeCode) && startDate != null && endDate != null && endDate.after(startDate)) {
            List<LeaseInterface> tempLeases = this.getLeases(null, null, null, null, null, null, null, null, null, officeCode, null, null, null);
            List<LeaseInterface> officeLeases = new ArrayList();
            if (!tempLeases.isEmpty()) {
                for (LeaseInterface temp : tempLeases) {
                    if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                        officeLeases.add(temp);
                    }
                }

            }
            return officeLeases;
        }
        return null;
    }

    @Override
    public List<ContractInterface> getContractsByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (this.database.employeeExists(eRef) && startDate != null && endDate != null && endDate.after(startDate)) {
            User user = this.database.getUser(eRef);
            if (user != null) {
                List<ContractInterface> tempContracts = this.getContracts(null, null, null, null, null, null, null, null, null, null, null, user.getUsername(), null);
                List<ContractInterface> employeeContracts = new ArrayList();
                if (!tempContracts.isEmpty()) {
                    for (ContractInterface temp : tempContracts) {
                        if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                            employeeContracts.add(temp);
                        }
                    }
                }
                return employeeContracts;
            }
        }
        return null;
    }

    @Override
    public List<ContractInterface> getContractsByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (this.database.officeExists(officeCode) && startDate != null && endDate != null && endDate.after(startDate)) {
            List<ContractInterface> tempContracts = this.getContracts(null, null, null, null, null, null, null, null, null, officeCode, null, null, null);
            List<ContractInterface> officeContracts = new ArrayList();
            if (!tempContracts.isEmpty()) {
                for (ContractInterface temp : tempContracts) {
                    if (startDate.before(temp.getCreatedDate()) || endDate.after(temp.getCreatedDate())) {
                        officeContracts.add(temp);
                    }
                }

            }
            return officeContracts;
        }
        return null;
    }

    @Override
    public double getRevenueForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        double balance = 0;
        if (this.database.officeExists(officeCode) && startDate != null && endDate != null && endDate.after(startDate)) {
            OfficeInterface office = this.database.getOffice(officeCode);
            List<AccountInterface> tempAccounts = office.getAccounts();
            if (!tempAccounts.isEmpty()) {
                for (AccountInterface temp : tempAccounts) {
                    List<TransactionInterface> tempTransactions = temp.getTransactions();
                    if (!tempTransactions.isEmpty()) {
                        for (TransactionInterface tempTransaction : tempTransactions) {
                            if (!(tempTransaction.isDebit() && (startDate.after(tempTransaction.getCreatedDate()) || endDate.before(temp.getCreatedDate())))) {
                                balance = balance + tempTransaction.getAmount();
                            }
                        }
                    }
                }
            }
        }
        return balance;
    }

    @Override
    public double getExpenditureForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        double balance = 0;
        if (this.database.officeExists(officeCode) && startDate != null && endDate != null && endDate.after(startDate)) {
            OfficeInterface office = this.database.getOffice(officeCode);
            List<AccountInterface> tempAccounts = office.getAccounts();
            if (!tempAccounts.isEmpty()) {
                for (AccountInterface temp : tempAccounts) {
                    List<TransactionInterface> tempTransactions = temp.getTransactions();
                    if (!tempTransactions.isEmpty()) {
                        for (TransactionInterface tempTransaction : tempTransactions) {
                            if (!(!tempTransaction.isDebit() && (startDate.after(tempTransaction.getCreatedDate()) || endDate.before(temp.getCreatedDate())))) {
                                balance = balance + tempTransaction.getAmount();
                            }
                        }
                    }
                }
            }
        }
        return balance;
    }

    @Override
    public double getProfitForOffice(String officCode, Date startDate, Date endDate) throws RemoteException {
        return this.getRevenueForOffice(officCode, startDate, endDate) - this.getExpenditureForOffice(officCode, startDate, endDate);
    }

    @Override
    public double getRevenueOverall(Date startDate, Date endDate) throws RemoteException {
        double balance = 0;
        List<AccountInterface> tempAccounts = new ArrayList(this.database.getRentAccounts());
        tempAccounts.addAll(new ArrayList(this.database.getLeaseAccounts()));
        tempAccounts.addAll(new ArrayList(this.database.getEmployeeAccounts()));

        if (!tempAccounts.isEmpty()) {
            for (AccountInterface temp : tempAccounts) {
                List<TransactionInterface> tempTransactions = temp.getTransactions();
                if (!tempTransactions.isEmpty()) {
                    for (TransactionInterface tempTransaction : tempTransactions) {
                        if (!(tempTransaction.isDebit() && (startDate.after(tempTransaction.getCreatedDate()) || endDate.before(temp.getCreatedDate())))) {
                            balance = balance + tempTransaction.getAmount();
                        }
                    }
                }
            }
        }
        return balance;
    }

    @Override
    public double getExpenditureOverall(Date startDate, Date endDate) throws RemoteException {
        double balance = 0;
        List<AccountInterface> tempAccounts = new ArrayList(this.database.getRentAccounts());
        tempAccounts.addAll(new ArrayList(this.database.getLeaseAccounts()));
        tempAccounts.addAll(new ArrayList(this.database.getEmployeeAccounts()));

        if (!tempAccounts.isEmpty()) {
            for (AccountInterface temp : tempAccounts) {
                List<TransactionInterface> tempTransactions = temp.getTransactions();
                if (!tempTransactions.isEmpty()) {
                    for (TransactionInterface tempTransaction : tempTransactions) {
                        if (!(!tempTransaction.isDebit() && (startDate.after(tempTransaction.getCreatedDate()) || endDate.before(temp.getCreatedDate())))) {
                            balance = balance + tempTransaction.getAmount();
                        }
                    }
                }
            }
        }
        return balance;
    }

    @Override
    public double getProfitOverall(Date startDate, Date endDate) throws RemoteException {
        return this.getRevenueOverall(startDate, endDate) - this.getExpenditureOverall(startDate, endDate);
    }
    
    @Override
    public String generateEmployeeReport(Date startDate, Date endDate) throws RemoteException {
        String report = "Employee Report\n\n";
        List<EmployeeInterface> employees = this.getEmployees();
        if(!employees.isEmpty()) {
            for(EmployeeInterface temp : employees) {
                int tenancies = this.getTenanciesByEmployee(temp.getEmployeeRef(), startDate, endDate).size();
                int leases = this.getLeasesByEmployee(temp.getEmployeeRef(), startDate, endDate).size();
                int contracts = this.getContractsByEmployee(temp.getEmployeeRef(), startDate, endDate).size();
                
                report = report + temp.getEmployeeRef() + " - " + temp.getPerson().getName() + "\n\nTenancies Created: " +
                        tenancies + "\nLeases Created: " + leases + "\nContracts Created: " + contracts + "\n\n\n";
            }
        }
        return report;
    }
    
    @Override
    public String generateOfficeReport(Date startDate, Date endDate) throws RemoteException {
        String report = "Office Report\n\n";
        List<OfficeInterface> offices = this.getOffices();
        if(!offices.isEmpty()) {
            for(OfficeInterface temp : offices) {
                int tenancies = this.getTenanciesByOffice(temp.getOfficeCode(), startDate, endDate).size();
                int leases = this.getLeasesByOffice(temp.getOfficeCode(), startDate, endDate).size();
                int contracts = this.getContractsByOffice(temp.getOfficeCode(), startDate, endDate).size();
                
                report = report + temp.getOfficeCode() + " - " + temp.getAddress().printAddress() + "\n\nTenancies Created: " +
                        tenancies + "\nLeases Created: " + leases + "\nContracts Created: " + contracts + "\n\n\n";
            }
        }
        return report;
    }
    
    @Override
    public String generateOfficeFinanceReport(Date startDate, Date endDate) throws RemoteException {
        String report = "Office Finance Report\n\n";
        List<OfficeInterface> offices = this.getOffices();
        if(!offices.isEmpty()) {
            for(OfficeInterface temp : offices) {
                double expenditure = this.getExpenditureForOffice(temp.getOfficeCode(), startDate, endDate);
                double revenue = this.getRevenueForOffice(temp.getOfficeCode(), startDate, endDate);
                double profit = this.getExpenditureForOffice(temp.getOfficeCode(), startDate, endDate);
                
                report = report + temp.getOfficeCode() + " - " + temp.getAddress().printAddress() + "\n\nExpenditure: £" +
                        expenditure + "\nRevenue: £" + revenue + "\n\nProfit: £" + profit + "\n\n\n";
            }
        }
        return report;
    }
    
    @Override
    public String generateFinanceReport(Date startDate, Date endDate) throws RemoteException {
        String report = "Overall Finance Report\n\n";
        double revenue = this.getRevenueOverall(startDate, endDate);
        double expenditure = this.getExpenditureOverall(startDate, endDate);
        double profit = this.getExpenditureOverall(startDate, endDate);

        report = report + " MSc Properties Overall Finance" + "\n\nExpenditure: £"
                + expenditure + "\nRevenue: £" + revenue + "\n\nProfit: £" + profit + "\n\n\n";
        return report;
    }
    
    @Override
    public void generateReport(Date startDate, Date endDate) throws RemoteException {
        
        String report = "MSc Properties Monthly Report for " + DateConversion.dateToString(startDate) + " to " + DateConversion.dateToString(endDate) +
                "\n\n\n" + this.generateEmployeeReport(startDate, endDate) + this.generateOfficeReport(startDate, endDate) +
                this.generateOfficeFinanceReport(startDate, endDate) + this.generateFinanceReport(startDate, endDate);
        
        try {
            // NEED TO WRITE String TO FILE
            File file = new File(documentsLocation + "Report" + DateConversion.dateToString(startDate) + " to " + DateConversion.dateToString(endDate) + ".txt");
            PrintWriter printToReport = new PrintWriter(documentsLocation + "Report" + DateConversion.dateToString(startDate) + " to " + DateConversion.dateToString(endDate) + ".txt");
            printToReport.println(report);
            printToReport.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // NEED TO SAVE REPORT TO EACH OFFICE USING A DOCUMENT OBJECT
        System.out.println(report);
    }
    
    
    // SYSTEM METHODS
    
    public void processRentTransactions(Date date) {
        try {
            List<RentAccountInterface> rentAccounts = this.getRentAccounts(null, date, null, null, null, null, null, null, null, null);
            for (RentAccountInterface temp : rentAccounts) {
                RentAccount temp2 = (RentAccount) temp;
                Application app = (Application) temp.getTenancy().getApplication();
                int mainApp = app.getMainApp().getPersonRef();
                Note note = new NoteImpl(noteRef++, "", "SYS", new Date());
                Transaction tran = new Transaction(transactionRef++, temp.getAccRef(), 0, mainApp, temp2.getRent(), false, date, note, "SYS", new Date());
                temp2.createTransaction(null, new ModifiedBy("Created Transaction " + tran.getTransactionRef(), "SYS", new Date()));
                this.database.createRentAccountTransaction(temp2.getAccRef(), tran);
            }
        } catch (RemoteException | SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processLeaseTransactions(Date date) {
        try {
            List<LeaseAccountInterface> leaseAccounts = this.getLeaseAccounts(null, date, null, null, null, null, null, null, null, null);
            for (LeaseAccountInterface temp : leaseAccounts) {
                LeaseAccount temp2 = (LeaseAccount) temp;
                List<LandlordInterface> landlords =  temp.getLease().getLandlords();
                int landlord = 0;
                if(!landlords.isEmpty()) {
                    landlord = landlords.get(0).getLandlordRef();
                }
                
                Note note = new NoteImpl(noteRef++, "", "SYS", new Date());
                Transaction tran = new Transaction(transactionRef++, temp.getAccRef(), 0, landlord, temp2.getExpenditure(), true, date, note, "SYS", new Date());
                temp2.createTransaction(null, new ModifiedBy("Created Transaction " + tran.getTransactionRef(), "SYS", new Date()));
                this.database.createLeaseAccountTransaction(temp2.getAccRef(), tran);
            }
        } catch (RemoteException | SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processSalaryTransactions() {
        try {
            List<EmployeeAccountInterface> employeeAccounts = this.getEmployeeAccounts(null, new Date(), null, null, null, null, null, null, null, null);
            for (EmployeeAccountInterface temp : employeeAccounts) {
                EmployeeAccount temp2 = (EmployeeAccount) temp;
                EmployeeInterface employee =  temp.getContract().getEmployee();
                int empRef = employee.getPersonRef();
                
                Note note = new NoteImpl(noteRef++, "", "SYS", new Date());
                Transaction tran = new Transaction(transactionRef++, temp.getAccRef(), 0, empRef, temp2.getSalary(), true, new Date(), note, "SYS", new Date());
                temp2.createTransaction(null, new ModifiedBy("Created Transaction " + tran.getTransactionRef(), "SYS", new Date()));
                this.database.createEmployeeAccountTransaction(temp2.getAccRef(), tran);
            }
        } catch (RemoteException | SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // SERVER METHODS
    
    /**
     * add a client to the users list
     * @param client
     * @throws RemoteException 
     */
    @Override
    public void register(Client client) throws RemoteException {
        if(!users.containsKey(client.getUsername())) {
            users.put(client.getUsername(), client);
        }
    }

    /**
     * remove a client from the users list
     * @param client
     * @throws RemoteException 
     */
    @Override
    public void unregister(Client client) throws RemoteException {
        if(users.containsKey(client.getUsername())) {
            users.remove(client.getUsername());
        }
    }

    /**
     * returns true if the server is still running
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }
    
    /**
     * 
     * @param username
     * @return 
     * @throws java.rmi.RemoteException 
     */
    @Override
    public User getUser(String username) throws RemoteException {
        if(this.database.userExists(username)) {
            return this.database.getUser(username);
        }
        return null;
    }

    /**
     * 
     * @param officeCode
     * @throws RemoteException 
     */
    private void updateUserTenancies(String officeCode) throws RemoteException {
        System.out.println("Office exists? - Server updateUserTen - " + this.database.officeExists(officeCode));
        if (this.database.officeExists(officeCode)) {
            List<AgreementInterface> tenancies =  this.getUserTenancies(officeCode);
            System.out.println("Office Tenancies size - Server updateUserTen - " + tenancies.size());
            for (Client client : users.values()) {
                System.out.println("Logged on:  - Server updateUserTen - " + client.getUsername());
                if (client.isAlive() && officeCode.equals(client.getOfficeCode())) {
                    System.out.println("Alive:  - Server updateUserTen - " + client.getUsername());
                    client.updateUserTenancies(tenancies);
                } else if (!client.isAlive()) {
                    users.remove(client.getUsername());
                }
            }
        }
    }
    
    /**
     * 
     * @param officeCode
     * @throws RemoteException 
     */
    private void updateUserLeases(String officeCode) throws RemoteException {
        System.out.println("Office exists? - Server updateUserLease - " + this.database.officeExists(officeCode));
        if (this.database.officeExists(officeCode)) {
            List<AgreementInterface> leases = this.getUserLeases(officeCode);
            System.out.println("Office Leases size - Server updateUserLease - " + leases.size());
            for (Client client : users.values()) {
                System.out.println("Logged on:  - Server updateUserLease - " + client.getUsername());
                if (client.isAlive() && officeCode.equals(client.getOfficeCode())) {
                    System.out.println("Alive:  - Server updateUserLease - " + client.getUsername());
                    client.updateUserLeases(leases);
                } else if (!client.isAlive()) {
                    users.remove(client.getUsername());
                }
            }
        }
    }
    
    /**
     * 
     * @param officeCode
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<AgreementInterface> getUserTenancies(String officeCode) throws RemoteException {
        List<AgreementInterface> tempAgreements = new ArrayList();
        List<AgreementInterface> agreements = new ArrayList();
        List<AgreementInterface> officeAgreements = this.getOffice(officeCode).getAgreements();
        AgreementInterface tempAgreement = null;
        int i = 0;
        int count = 0;
        for(AgreementInterface temp : officeAgreements) {
            if(temp instanceof TenancyInterface) {
                count++;
            }
        }
        while (i < count) {
            for (AgreementInterface temp : officeAgreements) {
                if (tempAgreement != null && temp instanceof TenancyInterface) {
                    if (tempAgreement.getExpectedEndDate().compareTo(temp.getExpectedEndDate()) > 0) {
                        if (!tempAgreements.contains(temp)) {
                            tempAgreement = temp;
                        }
                    }
                } else if (temp instanceof TenancyInterface && !tempAgreements.contains(temp)) { // tempAgreement == null
                    tempAgreement = temp;
                }
            }
            tempAgreements.add(tempAgreement);
            i++;
            tempAgreement = null;
        }
        int ii = 0;
        for(AgreementInterface agreement : tempAgreements) {
            if(ii < 9) {
                agreements.add(agreement);
                ii++;
            }
        }
        return agreements;
    }
    
    /**
     * 
     * @param officeCode
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<AgreementInterface> getUserLeases(String officeCode) throws RemoteException {
        List<AgreementInterface> tempAgreements = new ArrayList();
        List<AgreementInterface> agreements = new ArrayList();
        List<AgreementInterface> officeAgreements = this.getOffice(officeCode).getAgreements();
        AgreementInterface tempAgreement = null;
        int i = 0;
        int count = 0;
        for(AgreementInterface temp : officeAgreements) {
            if(temp instanceof LeaseInterface) {
                count++;
            }
        }
        while (i < count) {
            for (AgreementInterface temp : officeAgreements) {
                if (tempAgreement != null && temp instanceof LeaseInterface) {
                    if (tempAgreement.getExpectedEndDate().compareTo(temp.getExpectedEndDate()) > 0) {
                        if (!tempAgreements.contains(temp)) {
                            tempAgreement = temp;
                        }
                    }
                } else if (temp instanceof LeaseInterface && !tempAgreements.contains(temp)) { // tempAgreement == null
                    tempAgreement = temp;
                }
            }
            tempAgreements.add(tempAgreement);
            i++;
            tempAgreement = null;
        }
        int ii = 0;
        for(AgreementInterface agreement : tempAgreements) {
            if(ii < 9) {
                agreements.add(agreement);
                ii++;
            }
        }
        return agreements;
    }
    
    /**
     * 
     * @param officeCode
     * @throws RemoteException 
     */
    private void updateUserRentAccounts(String officeCode) throws RemoteException {
        System.out.println("Office exists? - Server updateUserRentAcc - " + this.database.officeExists(officeCode));
        if (this.database.officeExists(officeCode)) {
            List<AccountInterface> accounts = this.getUserRentAccounts(officeCode);
            System.out.println("Office RentAccs size - Server updateUserRentAcc - " + accounts.size());
            for (Client client : users.values()) {
                System.out.println("Logged on:  - Server updateUserRentAcc - " + client.getUsername());
                if (client.isAlive() && officeCode.equals(client.getOfficeCode())) {
                    System.out.println("Alive:  - Server updateUserRentAcc - " + client.getUsername());
                    client.updateUserRentAccounts(accounts);
                } else if (!client.isAlive()) {
                    users.remove(client.getUsername());
                }
            }
        }
    }
    
    /**
     * 
     * @param officeCode
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<AccountInterface> getUserRentAccounts(String officeCode) throws RemoteException {
        List<AccountInterface> tempAccounts = new ArrayList();
        List<AccountInterface> accounts = new ArrayList();
        List<AccountInterface> officeAccounts = this.getOffice(officeCode).getAccounts();
        AccountInterface tempAccount = null;
        int i = 0;
        int count = 0;
        for(AccountInterface temp : officeAccounts) {
            if(temp instanceof RentAccountInterface) {
                count++;
            }
        }
        while (i < count) {
            for (AccountInterface temp : officeAccounts) {
                if (tempAccount != null && temp instanceof RentAccountInterface) {
                    if (temp.getBalance() > temp.getBalance()) {
                        if (!tempAccounts.contains(temp)) {
                            tempAccount = temp;
                        }
                    }
                } else if (temp instanceof RentAccountInterface && !tempAccounts.contains(temp)) { // tempAgreement == null
                    tempAccount = temp;
                }
            }
            tempAccounts.add(tempAccount);
            i++;
            tempAccount = null;
        }
        int ii = 0;
        for(AccountInterface agreement : tempAccounts) {
            if(ii < 9) {
                accounts.add(agreement);
                ii++;
            }
        }
        return accounts;
    }
    
    /**
     *
     * @param dRef
     * @return
     */
    private byte[] downloadDocument(int dRef, int version) {
        if (this.database.documentExists(dRef)) {
            try {
                Document document = this.database.getDocument(dRef);
                if (document.hasVersion(version)) {
                    File file = new File(document.getDocumentPath(version));
                    byte buffer[] = new byte[(int) file.length()];
                    try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(document.getDocumentPath(version)))) {
                        input.read(buffer, 0, buffer.length);
                    }
                    return (buffer);
                }
            } catch (Exception e) {
                System.out.println("FileImpl: " + e.getMessage());
            }
        }
        return (null);
    }
    
    /**
     * 
     * @param fileName
     * @param buffer
     * @param comment
     * @param createdBy
     * @return 
     */
    private DocumentImpl uploadDocument(String fileName, byte[] buffer, String comment, String createdBy) {
        try {
            File file = new File(fileName);
            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileName))) {
                output.write(buffer, 0, buffer.length);
                output.flush();
                Note note = this.createNote(comment, createdBy);
                DocumentImpl document = new DocumentImpl(documentRef++, file, note, createdBy, new Date());
                return document;
            }
        } catch (Exception e) {
            System.out.println("ServerImpl: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * 
     * @param docRef
     * @param buffer
     * @param modifiedBy
     * @return 
     */
    private DocumentImpl uploadNewVersionDocument(int docRef, byte[] buffer, String modifiedBy) {
        if (this.database.documentExists(docRef)) {
            try {
                DocumentImpl document = (DocumentImpl) this.database.getDocument(docRef);
                String fileName = document.getDocumentPath(document.getPreviousVersions().size() + 1) + "\\" + Utils.getFileNameWithoutVersion(document.getDocumentName(document.getPreviousVersions().size() + 1)) + 
                        (document.getPreviousVersions().size() + 2) + "." + Utils.getFileExtension(document.getDocumentName(document.getPreviousVersions().size() + 1));
                File file = new File(fileName);
                try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileName))) {
                    output.write(buffer, 0, buffer.length);
                    output.flush();
                    document.createNewVersion(file, new ModifiedBy("Updated Document " + docRef, modifiedBy, new Date()));
                    return document;
                }
            } catch (Exception e) {
                System.out.println("ServerImpl: " + e.getMessage());
            }
        }
        return null;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;

import interfaces.AddressInterface;
import interfaces.ApplicationInterface;
import interfaces.Client;
import interfaces.ContractInterface;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyInterface;
import interfaces.RMISecurityPolicyLoader;
import interfaces.RentAccountInterface;
import interfaces.Server;
import interfaces.TenancyInterface;
import interfaces.User;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class ClientImpl implements Client {

    String name;
    Server server = null;
    User user = null;

    public ClientImpl() {
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.net.MalformedURLException
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException, NotBoundException {

        ClientImpl c1 = (ClientImpl) createClient(new String[]{"arnold"});//server
        ClientImpl c2 = (ClientImpl) createClient(new String[]{"donald", "127.0.0.1"});

    }

    public static Client createClient(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        RMISecurityPolicyLoader.LoadPolicy("RMISecurity.policy");

        ClientImpl c = new ClientImpl();
        //if the command line has at least one argument, the first one is the name
        if (args.length == 5) {
            c.registerWithServer(args[1], args[0]);
        } else {//we are on the same machine as the server
            c.registerWithServer(null, null);
        }

        return c;
    }

    public void registerWithServer(String host, String environment) throws RemoteException, NotBoundException {
        if (host == null) {
            host = "127.0.0.1";
        }
        if (environment == null) {
            environment = "ServerLIVE";
        } else {
            environment = "Server" + environment;
        }
        System.out.println("Environment: " + environment);
        System.out.println("Trying host : " + host);
        //get the registry
        Registry registry = LocateRegistry.getRegistry(host);
        //get the server stub from the registry
        server = (Server) registry.lookup(environment);
        //register the chatter with the server
        server.register(getStub());
        System.out.println("Server found!");
    }

    //Like a singleton pattern, we want a unique stub for this chat
    Client stub = null;

    protected Client getStub() throws RemoteException {
        if (stub == null) {
            stub = (Client) UnicastRemoteObject.exportObject(this, 0);
        }
        return stub;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    private void setName(String string) {
        name = string;
    }

    //if i have died i won't reply!
    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }

    private void setUser(User usr) {
        if (user == null) {
            user = usr;
        }
    }

    public String getOfficeCode() throws RemoteException {
        return user.getOfficeCode();
    }

    public String getUsername() throws RemoteException {
        return user.getUsername();
    }

    public boolean isUser(String username, String password) throws RemoteException {
        return server.isUser(username, password);
    }

    public int createTitle(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createTitle(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateTitle(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateTitle(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteTitle(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteTitle(code);
        }
        return 0;
    }

    public int createGender(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createGender(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateGender(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateGender(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteGender(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteGender(code);
        }
        return 0;
    }

    public int createMaritalStatus(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createMaritalStatus(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateMaritalStatus(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateMaritalStatus(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteMaritalStatus(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteMaritalStatus(code);
        }
        return 0;
    }

    public int createEthnicOrigin(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createEthnicOrigin(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateEthnicOrigin(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateEthnicOrigin(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteEthnicOrigin(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEthnicOrigin(code);
        }
        return 0;
    }

    public int createLanguage(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLanguage(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateLanguage(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateLanguage(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteLanguage(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLanguage(code);
        }
        return 0;
    }

    public int createNationality(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createNationality(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateNationality(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateNationality(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteNationality(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteNationality(code);
        }
        return 0;
    }

    public int createSexuality(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createSexuality(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateSexuality(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateSexuality(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteSexuality(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteSexuality(code);
        }
        return 0;
    }

    public int createReligion(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createReligion(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateReligion(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateReligion(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteReligion(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteReligion(code);
        }
        return 0;
    }

    public int createPropertyType(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPropertyType(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePropertyType(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePropertyType(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePropertyType(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertyType(code);
        }
        return 0;
    }

    public int createPropertySubType(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPropertySubType(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePropertySubType(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePropertySubType(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePropertySubType(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertySubType(code);
        }
        return 0;
    }

    public int createPropertyElement(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPropertyElement(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePropertyElement(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePropertyElement(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePropertyElement(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertyElement(code);
        }
        return 0;
    }

    public int createContactType(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createContactType(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateContactType(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateContactType(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteContactType(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteContactType(code);
        }
        return 0;
    }

    public int createEndReason(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createEndReason(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateEndReason(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateEndReason(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteEndReason(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEndReason(code);
        }
        return 0;
    }

    public int createRelationship(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createRelationship(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateRelationship(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateRelationship(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteRelationship(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteRelationship(code);
        }
        return 0;
    }

    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber,
            String street, String area, String town, String country, String postcode, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, user.getUsername());
        }
        return 0;
    }

    public int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street,
            String area, String town, String country, String postcode, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateAddress(aRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteAddress(int addrRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteAddress(addrRef);
        }
        return 0;
    }

    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, user.getUsername());
        }
        return 0;
    }

    public int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePerson(pRef, titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, user.getUsername());
        }
        return 0;
    }

    public int deletePerson(int pRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePerson(pRef);
        }
        return 0;
    }

    public int createPersonNote(int pRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPersonNote(pRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePersonNote(int pRef, int nRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePersonNote(pRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePersonNote(int pRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePersonNote(pRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createPersonDocument(int pRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createPersonDocument(pRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deletePersonDocument(int pRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePersonDocument(pRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadPersonDocument(int pRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadPersonDocument(pRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPersonContact(pRef, contactTypeCode, value, date, user.getUsername());
        }
        return 0;
    }

    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePersonContact(pRef, cRef, contactTypeCode, value, date, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePersonContact(int pRef, int cRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePersonContact(pRef, cRef, user.getUsername());
        }
        return 0;
    }

    public int createPersonAddressUsage(int pRef, int addrRef, Date startDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPersonAddressUsage(pRef, addrRef, startDate, user.getUsername());
        }
        return 0;
    }

    public int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePersonAddressUsage(pRef, addrUsageRef, addrRef, startDate, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePersonAddressUsage(pRef, aRef);
        }
        return 0;
    }

    public int createOffice(String officeCode, int addrRef, Date startDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createOffice(officeCode, addrRef, startDate, user.getUsername());
        }
        return 0;
    }

    public int updateOffice(String officeCode, Date startDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateOffice(officeCode, startDate, user.getUsername());
        }
        return 0;
    }

    public int deleteOffice(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteOffice(code);
        }
        return 0;
    }

    public int createOfficeNote(String officeCode, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createOfficeNote(officeCode, comment, user.getUsername());
        }
        return 0;
    }

    public int updateOfficeNote(String officeCode, int nRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateOfficeNote(officeCode, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteOfficeNote(String officeCode, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteOfficeNote(officeCode, nRef, user.getUsername());
        }
        return 0;
    }

    public int createOfficeDocument(String officeCode, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createOfficeDocument(officeCode, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteOfficeDocument(String officeCode, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteOfficeDocument(officeCode, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadOfficeDocument(String officeCode, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadOfficeDocument(officeCode, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createOfficeContact(oCode, contactTypeCode, value, date, user.getUsername());
        }
        return 0;
    }

    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateOfficeContact(oCode, cRef, contactTypeCode, value, date, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteOfficeContact(String code, int cRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteOfficeContact(code, cRef);
        }
        return 0;
    }

    public int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, int address) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createInvolvedParty(pRef, aRef, joint, main, start, relationshipCode, address, user.getUsername());
        }
        return 0;
    }

    public int updateInvolvedParty(int iRef, boolean joint, boolean main, Date start, String relationshipCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateInvolvedParty(iRef, joint, main, start, relationshipCode, user.getUsername());
        }
        return 0;
    }

    public int deleteInvolvedParty(int iRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteInvolvedParty(iRef);
        }
        return 0;
    }

    public int createInvovedPartyNote(int iRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createInvolvedPartyNote(iRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateInvolvedPartyNote(int eRef, int nRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateInvolvedPartyNote(eRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteInvolvedPartyNote(int iRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteInvolvedPartyNote(iRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createApplication(corrName, appStartDate, pRef, relationshipCode, addrRef, addressStartDate, user.getUsername());
        }
        return 0;
    }

    public int updateApplication(int aRef, String corrName, Date appStartDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateApplication(aRef, corrName, appStartDate, user.getUsername());
        }
        return 0;
    }

    public int deleteApplication(int aRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteApplication(aRef, user.getUsername());
        }
        return 0;
    }

    public int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.addInvolvedParty(aRef, pRef, joint, start, relationshipCode, createdBy);
        }
        return 0;
    }

    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.changeMainApp(aRef, iRef, end, endReasonCode, user.getUsername());
        }
        return 0;
    }

    public int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.endInvolvedParty(aRef, iRef, end, endReasonCode, user.getUsername());
        }
        return 0;
    }

    public int addInterestedProperty(int aRef, int pRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.addInterestedProperty(aRef, pRef, user.getUsername());
        }
        return 0;
    }

    public int endInterestInProperty(int aRef, int pRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.endInterestInProperty(aRef, pRef, user.getUsername());
        }
        return 0;
    }

    public int createApplicationNote(int aRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createApplicationNote(aRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateApplicationNote(int aRef, int nRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateApplicationNote(aRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteApplicationNote(int aRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteApplicationNote(aRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createApplicationDocument(int aRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createApplicationDocument(aRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteApplicationDocument(int aRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteApplicationDocument(aRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadApplicationDocument(int aRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadApplicationDocument(aRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateApplicationAddressUsage(aRef, addrUsageRef, addrRef, startDate, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteApplicationAddressUsage(int addrRef, int aRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteApplicationAddressUsage(addrRef, aRef);
        }
        return 0;
    }

    public int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createApplicationAddressUsage(applicationRef, addrRef, startDate, user.getUsername());
        }
        return 0;
    }

    public int createEmployee(int pRef, String username, String password) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createEmployee(pRef, username, password, user.getUsername());
        }
        return 0;
    }

    public int deleteEmployee(int eRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEmployee(eRef);
        }
        return 0;
    }

    public int createEmployeeNote(int eRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createEmployeeNote(eRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateEmployeeNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateEmployeeNote(eRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeNote(int eRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEmployeeNote(eRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createLandlord(int lRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLandlord(lRef, user.getUsername());
        }
        return 0;
    }

    public int deleteLandlord(int lRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLandlord(lRef);
        }
        return 0;
    }

    public int createLandlordNote(int lRef, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLandlordNote(lRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateLandlordNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateLandlordNote(lRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteLandlordNote(int lRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLandlordNote(lRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createProperty(pRef, addrRef, startDate, propTypeCode, propSubTypeCode, user.getUsername());
        }
        return 0;
    }

    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateProperty(pRef, addrRef, startDate, propTypeCode, propSubTypeCode, user.getUsername());
        }
        return 0;
    }

    public int deleteProperty(int pRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteProperty(pRef);
        }
        return 0;
    }

    public int createPropertyNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPropertyNote(pRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePropertyNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePropertyNote(pRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePropertyNote(int pRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertyNote(pRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createPropertyDocument(int pRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createPropertyDocument(pRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deletePropertyDocument(int pRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertyDocument(pRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadPropertyDocument(int pRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadPropertyDocument(pRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, double doubleValue, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createPropertyElement(pRef, elementCode, startDate, charge, stringValue, doubleValue, comment, user.getUsername());
        }
        return 0;
    }

    public int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updatePropertyElement(eRef, pRef, startDate, stringValue, doubleValue, charge, comment, user.getUsername());
        }
        return 0;
    }

    public int deletePropertyElement(int eRef, int pRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deletePropertyElement(eRef, pRef);
        }
        return 0;
    }

    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write,
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, user.getUsername());
        }
        return 0;
    }

    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write,
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateJobRole(code, jobTitle, jobDescription, fullTime, salary, current, read, write, update, employeeRead, employeeWrite, employeeUpdate, user.getUsername());
        }
        return 0;
    }

    public int deleteJobRole(String officeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobRole(officeCode);
        }
        return 0;
    }

    public int createJobRoleNote(String officeCode, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobRoleNote(officeCode, comment, user.getUsername());
        }
        return 0;
    }

    public int updateJobRoleNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateJobRoleNote(officeCode, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleNote(String officeCode, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobRoleNote(officeCode, nRef, user.getUsername());
        }
        return 0;
    }

    public int createJobRoleRequirement(String jobRoleCode, String requirement) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobRoleRequirement(jobRoleCode, requirement, user.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleRequirement(String jobRoleCode, String requirement) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobRoleRequirement(jobRoleCode, requirement, user.getUsername());
        }
        return 0;
    }

    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobRoleBenefit(jobRoleCode, benefit, startDate, salaryBenefit, stringValue, doubleValue, comment, user.getUsername());
        }
        return 0;
    }

    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateJobRoleBenefit(benefitRef, jobRoleCode, benefitCode, startDate, salaryBenefit, stringValue, doubleValue, comment, user.getUsername());
        }
        return 0;
    }

    public int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.endJobRoleBenefit(benefitRef, jobRoleCode, endDate, user.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleBenefit(String jobRoleCode, int benefit) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobRoleBenefit(jobRoleCode, benefit, user.getUsername());
        }
        return 0;
    }

    public int createJobRequirement(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobRequirement(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateJobRequirement(String code, String description, boolean current, String comment) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.updateJobRequirement(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteJobRequirement(String requirement) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobRequirement(requirement);
        }
        return 0;
    }

    public int createJobBenefit(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createJobBenefit(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateJobBenefit(String code, String description, boolean current, String comment) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.updateJobBenefit(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteJobBenefit(String benefit) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteJobBenefit(benefit);
        }
        return 0;
    }

    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createTenancy(startDate, length, pRef, aRef, tenTypeCode, officeCode, user.getUsername());
        }
        return 0;
    }

    public int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode, String modifiedBy) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.updateTenancy(tRef, name, startDate, length, tenTypeCode, user.getUsername());
        }
        return 0;
    }

    public int deleteTenancy(int tRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteTenancy(tRef, user.getUsername());
        }
        return 0;
    }

    public int createTenancyNote(int tRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createTenancyNote(tRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateTenancyNote(int tRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateTenancyNote(tRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteTenancyNote(int tRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteTenancyNote(tRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createTenancyDocument(int tRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createTenancyDocument(tRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteTenancyDocument(int tRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteTenancyDocument(tRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadTenancyDocument(int tRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadTenancyDocument(tRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createTenancyType(String code, String description, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createTenancyType(code, description, comment, user.getUsername());
        }
        return 0;
    }

    public int updateTenancyType(String code, String description, boolean current, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateTenancyType(code, description, current, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteTenancyType(String code) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteTenancyType(code);
        }
        return 0;
    }

    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLease(startDate, length, pRef, management, expenditure, officeCode, user.getUsername());
        }
        return 0;
    }

    public int updateLease(int lRef, String name, Date startDate, int length) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.updateLease(lRef, name, startDate, length, user.getUsername());
        }
        return 0;
    }

    public int deleteLease(int lRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLease(lRef, user.getUsername());
        }
        return 0;
    }

    public int createLeaseNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLeaseNote(lRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateLeaseNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateLeaseNote(lRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteLeaseNote(int lRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLeaseNote(lRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createLeaseDocument(int lRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createLeaseDocument(lRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteLeaseDocument(int lRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLeaseDocument(lRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadLeaseDocument(int lRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadLeaseDocument(lRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createLeaseLandlord(int lRef, int landRef) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.createLeaseLandlord(lRef, landRef, user.getUsername());
        }
        return 0;
    }

    public int endLeaseLandlord(int lRef, int landRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.endLeaseLandlord(lRef, landRef, user.getUsername());
        }
        return 0;
    }

    public int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createContract(startDate, length, eRef, jobRoleCode, officeCode, user.getUsername());
        }
        return 0;
    }

    public int updateContract(int cRef, String name, Date startDate, int length) throws SQLException, RemoteException {
        if (server.isAlive()) {
            return server.updateContract(cRef, name, startDate, length, user.getUsername());
        }
        return 0;
    }

    public int deleteContract(int cRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteContract(cRef, user.getUsername());
        }
        return 0;
    }

    public int createContractNote(int cRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createContractNote(cRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateContractNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateContractNote(cRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteContractNote(int cRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteContractNote(cRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createContractDocument(int cRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createContractDocument(cRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteContractDocument(int cRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteContractDocument(cRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadContractDocument(int cRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadContractDocument(cRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createRentAccNote(int rAccRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createRentAccNote(rAccRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateRentAccNote(int rAccRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateRentAccNote(rAccRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteRentAccNote(int rAccRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteRentAccNote(rAccRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createRentAccDocument(int rAccRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createRentAccDocument(rAccRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteRentAccDocument(int rAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteRentAccDocument(rAccRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadRentAccDocument(int rAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadRentAccDocument(rAccRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createLeaseAccNote(int lAccRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLeaseAccNote(lAccRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateLeaseAccNote(int lAccRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateLeaseAccNote(lAccRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteLeaseAccNote(int lAccRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLeaseAccNote(lAccRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createLeaseAccDocument(int lAccRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createLeaseAccDocument(lAccRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteLeaseAccDocument(int lAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLeaseAccDocument(lAccRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadLeaseAccDocument(int lAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadLeaseAccDocument(lAccRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createEmployeeAccNote(int eAccRef, String comment, String createdBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createEmployeeAccNote(eAccRef, comment, user.getUsername());
        }
        return 0;
    }

    public int updateEmployeeAccNote(int eAccRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.updateEmployeeAccNote(eAccRef, nRef, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeAccNote(int eAccRef, int nRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEmployeeAccNote(eAccRef, nRef, user.getUsername());
        }
        return 0;
    }

    public int createEmployeeAccDocument(int eAccRef, String fileName, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createEmployeeAccDocument(eAccRef, fileName, documentData, comment, user.getUsername());
            }
        }
        return 0;
    }

    public int deleteEmployeeAccDocument(int eAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteEmployeeAccDocument(eAccRef, dRef, user.getUsername());
        }
        return 0;
    }

    public int downloadEmployeeAccDocument(int eAccRef, int dRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            try {
                this.openDocument(server.downloadEmployeeAccDocument(eAccRef, dRef, user.getUsername()));
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createRentAccTransaction(rAccRef, fromRef, toRef, amount, debit, transactionDate, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteRentAccTransaction(int tRef, int rAccRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteRentAccTransaction(tRef, rAccRef, user.getUsername());
        }
        return 0;
    }

    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createLeaseAccTransaction(lAccRef, fromRef, toRef, amount, debit, transactionDate, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteLeaseAccTransaction(int tRef, int lAccRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteLeaseAccTransaction(tRef, lAccRef, user.getUsername());
        }
        return 0;
    }

    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.createRentAccTransaction(eAccRef, fromRef, toRef, amount, debit, transactionDate, comment, user.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeAccTransaction(int tRef, int eAccRef) throws RemoteException, SQLException {
        if (server.isAlive()) {
            return server.deleteRentAccTransaction(tRef, eAccRef, user.getUsername());
        }
        return 0;
    }

    public List<Element> getTitles() throws RemoteException {
        if (server.isAlive()) {
            return server.getTitles();
        }
        return null;
    }

    public List<Element> getCurrentTitles() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempTitles = new ArrayList(this.getTitles());
            for (Element temp : tempTitles) {
                if (!temp.isCurrent()) {
                    tempTitles.remove(temp);
                }
            }
            return tempTitles;
        }
        return null;
    }

    public List<Element> getGenders() throws RemoteException {
        if (server.isAlive()) {
            return server.getGenders();
        }
        return null;
    }

    public List<Element> getCurrentGenders() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempGenders = new ArrayList(this.getGenders());
            for (Element temp : tempGenders) {
                if (!temp.isCurrent()) {
                    tempGenders.remove(temp);
                }
            }
            return tempGenders;
        }
        return null;
    }

    public List<Element> getMaritalStatuses() throws RemoteException {
        if (server.isAlive()) {
            return server.getMaritalStatuses();
        }
        return null;
    }

    public List<Element> getCurrentMaritalStatuses() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempStatuses = new ArrayList(this.getMaritalStatuses());
            for (Element temp : tempStatuses) {
                if (!temp.isCurrent()) {
                    tempStatuses.remove(temp);
                }
            }
            return tempStatuses;
        }
        return null;
    }

    public List<Element> getEthnicOrigins() throws RemoteException {
        if (server.isAlive()) {
            return server.getEthnicOrigins();
        }
        return null;
    }

    public List<Element> getCurrentEthnicOrigins() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempOrigins = new ArrayList(this.getEthnicOrigins());
            for (Element temp : tempOrigins) {
                if (!temp.isCurrent()) {
                    tempOrigins.remove(temp);
                }
            }
            return tempOrigins;
        }
        return null;
    }

    public List<Element> getLanguages() throws RemoteException {
        if (server.isAlive()) {
            return server.getLanguages();
        }
        return null;
    }

    public List<Element> getCurrentLanguages() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempLanguages = new ArrayList(this.getLanguages());
            for (Element temp : tempLanguages) {
                if (!temp.isCurrent()) {
                    tempLanguages.remove(temp);
                }
            }
            return tempLanguages;
        }
        return null;
    }

    public List<Element> getNationalities() throws RemoteException {
        if (server.isAlive()) {
            return server.getNationalities();
        }
        return null;
    }

    public List<Element> getCurrentNationalities() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempNationalities = new ArrayList(this.getNationalities());
            for (Element temp : tempNationalities) {
                if (!temp.isCurrent()) {
                    tempNationalities.remove(temp);
                }
            }
            return tempNationalities;
        }
        return null;
    }

    public List<Element> getSexualities() throws RemoteException {
        if (server.isAlive()) {
            return server.getSexualities();
        }
        return null;
    }

    public List<Element> getCurrentSexualities() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempSexualities = new ArrayList(this.getSexualities());
            for (Element temp : tempSexualities) {
                if (!temp.isCurrent()) {
                    tempSexualities.remove(temp);
                }
            }
            return tempSexualities;
        }
        return null;
    }

    public List<Element> getReligions() throws RemoteException {
        if (server.isAlive()) {
            return server.getReligions();
        }
        return null;
    }

    public List<Element> getCurrentReligions() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempReligions = new ArrayList(this.getReligions());
            for (Element temp : tempReligions) {
                if (!temp.isCurrent()) {
                    tempReligions.remove(temp);
                }
            }
            return tempReligions;
        }
        return null;
    }

    public List<Element> getPropertyTypes() throws RemoteException {
        if (server.isAlive()) {
            return server.getPropertyTypes();
        }
        return null;
    }

    public List<Element> getCurrentPropertyTypes() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempTypes = new ArrayList(this.getPropertyTypes());
            for (Element temp : tempTypes) {
                if (!temp.isCurrent()) {
                    tempTypes.remove(temp);
                }
            }
            return tempTypes;
        }
        return null;
    }

    public List<Element> getPropertySubTypes() throws RemoteException {
        if (server.isAlive()) {
            return server.getPropertySubTypes();
        }
        return null;
    }

    public List<Element> getCurrentPropertySubTypes() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempTypes = new ArrayList(this.getPropertySubTypes());
            for (Element temp : tempTypes) {
                if (!temp.isCurrent()) {
                    tempTypes.remove(temp);
                }
            }
            return tempTypes;
        }
        return null;
    }

    public List<Element> getPropElements() throws RemoteException {
        if (server.isAlive()) {
            return server.getPropElements();
        }
        return null;
    }

    public List<Element> getCurrentPropElements() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempElements = new ArrayList(this.getPropElements());
            for (Element temp : tempElements) {
                if (!temp.isCurrent()) {
                    tempElements.remove(temp);
                }
            }
            return tempElements;
        }
        return null;
    }

    public List<Element> getContactTypes() throws RemoteException {
        if (server.isAlive()) {
            return server.getContactTypes();
        }
        return null;
    }

    public List<Element> getCurrentContactTypes() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempTypes = new ArrayList(this.getContactTypes());
            for (Element temp : tempTypes) {
                if (!temp.isCurrent()) {
                    tempTypes.remove(temp);
                }
            }
            return tempTypes;
        }
        return null;
    }

    public List<Element> getEndReasons() throws RemoteException {
        if (server.isAlive()) {
            return server.getEndReasons();
        }
        return null;
    }

    public List<Element> getCurrentEndReasons() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempReasons = new ArrayList(this.getEndReasons());
            for (Element temp : tempReasons) {
                if (!temp.isCurrent()) {
                    tempReasons.remove(temp);
                }
            }
            return tempReasons;
        }
        return null;
    }

    public List<Element> getRelationships() throws RemoteException {
        if (server.isAlive()) {
            return server.getRelationships();
        }
        return null;
    }

    public List<Element> getCurrentRelationships() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempRelationships = new ArrayList(this.getRelationships());
            for (Element temp : tempRelationships) {
                if (!temp.isCurrent()) {
                    tempRelationships.remove(temp);
                }
            }
            return tempRelationships;
        }
        return null;
    }

    public List<Element> getJobBenefits() throws RemoteException {
        if (server.isAlive()) {
            return server.getJobBenefits();
        }
        return null;
    }

    public List<Element> getCurrentJobBenefits() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempBenefits = new ArrayList(this.getJobBenefits());
            for (Element temp : tempBenefits) {
                if (!temp.isCurrent()) {
                    tempBenefits.remove(temp);
                }
            }
            return tempBenefits;
        }
        return null;
    }

    public List<Element> getJobRequirements() throws RemoteException {
        if (server.isAlive()) {
            return server.getJobRequirements();
        }
        return null;
    }

    public List<Element> getCurrentJobRequirements() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempRequirements = new ArrayList(this.getJobRequirements());
            for (Element temp : tempRequirements) {
                if (!temp.isCurrent()) {
                    tempRequirements.remove(temp);
                }
            }
            return tempRequirements;
        }
        return null;
    }

    public List<Element> getTenancyTypes() throws RemoteException {
        if (server.isAlive()) {
            return server.getTenancyTypes();
        }
        return null;
    }

    public List<Element> getCurrentTenancyTypes() throws RemoteException {
        if (server.isAlive()) {
            List<Element> tempTypes = new ArrayList(this.getTenancyTypes());
            for (Element temp : tempTypes) {
                if (!temp.isCurrent()) {
                    tempTypes.remove(temp);
                }
            }
            return tempTypes;
        }
        return null;
    }

    public Boolean titleExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return titleExists(code);
        }
        return null;
    }

    public Boolean genderExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return genderExists(code);
        }
        return null;
    }

    public Boolean maritalStatusExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return maritalStatusExists(code);
        }
        return null;
    }

    public Boolean ethnicOriginExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return ethnicOriginExists(code);
        }
        return null;
    }

    public Boolean languageExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return languageExists(code);
        }
        return null;
    }

    public Boolean nationalityExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return nationalityExists(code);
        }
        return null;
    }

    public Boolean sexualityExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return sexualityExists(code);
        }
        return null;
    }

    public Boolean religionExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return religionExists(code);
        }
        return null;
    }

    public Boolean contactTypeExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return contactTypeExists(code);
        }
        return null;
    }

    public Boolean propTypeExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return propTypeExists(code);
        }
        return null;
    }

    public Boolean propSubTypeExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return propSubTypeExists(code);
        }
        return null;
    }

    public Boolean propElementExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return propElementExists(code);
        }
        return null;
    }

    public Boolean endReasonExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return endReasonExists(code);
        }
        return null;
    }

    public Boolean relationshipExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return relationshipExists(code);
        }
        return null;
    }

    public Boolean officeExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return officeExists(code);
        }
        return null;
    }

    public Boolean jobRoleExists(String code) throws RemoteException {
        if (server.isAlive()) {
            return jobRoleExists(code);
        }
        return null;
    }

    public Boolean isUser(String code) throws RemoteException {
        if (server.isAlive()) {
            return isUser(code);
        }
        return null;
    }
    
    
    /// SEARCH METHODS

    public List<PersonInterface> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return server.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        }
        return null;
    }

    public List<AddressInterface> getAddresses(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return server.getAddresses(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
        }
        return null;
    }

    public List<ApplicationInterface> getApplications(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return server.getApplications(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<ApplicationInterface> getPeopleApplications(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getPeopleApplications(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, 
                sexualityCode, religionCode, createdBy, createdDate);
        }
        return null;
    }
    
    public List<ApplicationInterface> getAddressApplications(String buildingNumber, String buildingName, 
            String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getAddressApplications(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, 
                street, area, town, country, postcode, createdBy, createdDate);
        }
        return null;
    }
    
    public List<ApplicationInterface> getCorrNameApplcations(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getCorrNameApplcations(name);
        }
        return null;
    }
    
    public ApplicationInterface getInvPartyApplcation(int iPartyRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getInvPartyApplcation(iPartyRef);
        }
        return null;
    }
    
    public List<EmployeeInterface> getPeopleEmployees(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getPeopleEmployees(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, 
                languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        }
        return null;
    }
    
    public List<LandlordInterface> getPeopleLandlords(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getPeopleLandlords(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode,
                 languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        }
        return null;
    }
    
    public List<PropertyInterface> getProperties(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        }
        return null;
    }
    
    public List<TenancyInterface> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, 
            Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getTenancies(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<TenancyInterface> getApplicationTenancies(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getApplicationTenancies(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<TenancyInterface> getApplicationTenancies(int appRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getApplicationTenancies(appRef);
        }
        return null;
    }
    
    public List<TenancyInterface> getPropertyTenancies(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getPropertyTenancies(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        }
        return null;
    }
    
    public List<TenancyInterface> getPropertyTenancies(int propRef) throws RemoteException {
        return this.server.getPropertyTenancies(propRef);
    }
    
    public List<TenancyInterface> getNameTenancies(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameTenancies(name);
        }
        return null;
    }
    
    public List<TenancyInterface> getOfficeTenancies(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeTenancies(office);
        }
        return null;
    }
    
    public List<LeaseInterface> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getLeases(name, startDate, expectedEndDate, endDate, length, propRef, management, expenditure, accountRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<LeaseInterface> getPropertyLeases(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getPropertyLeases(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
        }
        return null;
    }
    
    public List<LeaseInterface> getPropertyLeases(int propRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getPropertyLeases(propRef);
        }
        return null;
    }
    
    public List<LeaseInterface> getNameLeases(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameLeases(name);
        }
        return null;
    }
    
    public List<LeaseInterface> getOfficeLeases(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeLeases(office);
        }
        return null;
    }
    
    public List<LeaseInterface> getLandlordLeases(int landlordRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLandlordLeases(landlordRef);
        }
        return null;
    }
    
    public List<LeaseInterface> getLandlordLeases(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLandlordLeases(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        }
        return null;
    }
    
    public List<ContractInterface> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, 
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getContracts(name, startDate, expectedEndDate, endDate, length, propRef, employeeRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }

    public List<ContractInterface> getNameContracts(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameContracts(name);
        }
        return null;
    }
    
    public List<ContractInterface> getOfficeContracts(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeContracts(office);
        }
        return null;
    }
    
    public List<ContractInterface> getEmployeeContracts(int ref) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getEmployeeContracts(ref);
        }
        return null;
    }
    
    public List<RentAccountInterface> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getRentAccounts(name, startDate, endDate, balance, rent, agreementRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<RentAccountInterface> getNameRentAcc(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameRentAcc(name);
        }
        return null;
    }
    
    public List<RentAccountInterface> getOfficeRentAcc(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeRentAcc(office);
        }
        return null;
    }
    
    public List<RentAccountInterface> getTenanciesRentAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef,
            Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getTenanciesRentAccounts(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public RentAccountInterface getTenancyRentAcc(int tenancyRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getTenancyRentAcc(tenancyRef);
        }
        return null;
    }
    
    public List<LeaseAccountInterface> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLeaseAccounts(name, startDate, endDate, balance, expenditure, agreementRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<LeaseAccountInterface> getNameLeaseAcc(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameLeaseAcc(name);
        }
        return null;
    }
    
    public List<LeaseAccountInterface> getOfficeLeaseAcc(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeLeaseAcc(office);
        }
        return null;
    }
    
    public List<LeaseAccountInterface> getLeasesLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLeasesLeaseAccounts(name, startDate, endDate, balance, expenditure, agreementRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public LeaseAccountInterface getLeaseLeaseAcc(int leaseRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLeaseLeaseAcc(leaseRef);
        }
        return null;
    }
    
    public List<EmployeeAccountInterface> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getEmployeeAccounts(name, startDate, endDate, balance, salary, agreementRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public List<EmployeeAccountInterface> getNameEmployeeAcc(String name) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getNameEmployeeAcc(name);
        }
        return null;
    }
    
    public List<EmployeeAccountInterface> getOfficeEmployeeAcc(String office) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getOfficeEmployeeAcc(office);
        }
        return null;
    }
    
    public List<EmployeeAccountInterface> getContractsEmployeeAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, 
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getContractsEmployeeAccounts(name, startDate, expectedEndDate, endDate, length, propRef, employeeRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
        }
        return null;
    }
    
    public EmployeeAccountInterface getContractEmployeeAcc(int contractRef) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getContractEmployeeAcc(contractRef);
        }
        return null;
    }
    
    public List<OfficeInterface> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (this.server.isAlive()) {
            return this.server.getOffices(addrRef, startDate, current, createdBy, createdDate);
        }
        return null;
    }
    
    
    /// REPORTING METHODS
    
    
    
    public List<TenancyInterface> getTenanciesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getTenanciesByEmployee(eRef, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfTenanciesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getTenanciesByEmployee(eRef, startDate, endDate).size();
        }
        return null;
    }
    
    public List<TenancyInterface> getTenanciesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getTenanciesByOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfTenanciesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getTenanciesByOffice(officeCode, startDate, endDate).size();
        }
        return null;
    }
    
    
    public List<LeaseInterface> getLeasesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLeasesByEmployee(eRef, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfLeasesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getLeasesByEmployee(eRef, startDate, endDate).size();
        }
        return null;
    }
    
    public List<LeaseInterface> getLeasesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getLeasesByOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfLeasesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getLeasesByOffice(officeCode, startDate, endDate).size();
        }
        return null;
    }
    
    public List<ContractInterface> getContractsByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getContractsByEmployee(eRef, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfContractsByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getContractsByEmployee(eRef, startDate, endDate).size();
        }
        return null;
    }
    
    public List<ContractInterface> getContractsByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getContractsByOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Integer getNumberOfContractsByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.getContractsByOffice(officeCode, startDate, endDate).size();
        }
        return null;
    }
    
    public Double getRevenueForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getRevenueForOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Double getExpenditureForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getExpenditureForOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Double getProfitForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getProfitForOffice(officeCode, startDate, endDate);
        }
        return null;
    }
    
    public Double getRevenueOverall(Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getRevenueOverall(startDate, endDate);
        }
        return null;
    }
    
    public Double getExpenditureOverall(Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getExpenditureOverall(startDate, endDate);
        }
        return null;
    }
    
    public Double getProfitOverall(Date startDate, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return this.server.getProfitOverall(startDate, endDate);
        }
        return null;
    }
    
    
    

    private byte[] uploadDocument(String fileName) throws SQLException, IOException {
        File file = new File(fileName);
        byte documentData[] = new byte[(int) file.length()];
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.getName()))) {
            input.read(documentData, 0, documentData.length);
        }
        return documentData;
    }

    private File openDocument(byte[] documentData) throws SQLException, IOException {
        File file = File.createTempFile("msctmp", ".pdf", new File("D:/"));
        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.getName()))) {
            output.write(documentData, 0, documentData.length);
            output.flush();
        }
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);

        return file;
    }
}

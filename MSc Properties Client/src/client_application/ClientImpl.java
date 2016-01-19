/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;

import interfaces.AddressInterface;
import interfaces.AgreementInterface;
import interfaces.ApplicationInterface;
import interfaces.Client;
import interfaces.ContractInterface;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.JobRoleInterface;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class ClientImpl extends Observable implements Client {

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

        ClientImpl c1 = (ClientImpl) createClient(new String[]{"dwayne"});//server
        ClientImpl c2 = (ClientImpl) createClient(new String[]{"penny", "127.0.0.1"});

    }

    public static Client createClient(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        RMISecurityPolicyLoader.loadPolicy("RMISecurity.policy");
        
        ClientImpl c = new ClientImpl();
        if (args.length == 4) {
            c.registerWithServer(args[0], args[1]);
            c.login(args[2], args[3]);
        } else { //we are on the same machine as the server
            System.out.println("Login Details not supplied");
            System.out.println("Require Server IP address, Environment, Username and Password");
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
        System.out.println("Environment : " + environment);
        System.out.println("Trying host : " + host);
        //get the registry
        Registry registry = LocateRegistry.getRegistry(host);
        //get the server stub from the registry
        server = (Server) registry.lookup(environment);
        System.out.println("Server found!");
    }
    
    private void login(String username, String passsword) {
        try {
            if(server != null && server.isAlive()) {
                if(server.isUser(username, passsword)) {
                    setUser(server.getUser(username));
                    //register the chatter with the server
                    server.register(getStub());
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Like a singleton pattern, we want a unique stub for this chat
    Client stub = null;

    protected Client getStub() throws RemoteException {
        if (stub == null) {
            stub = (Client) UnicastRemoteObject.exportObject(this, 0);
        }
        return stub;
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
    
    @Override
    public String getOfficeCode() throws RemoteException {
        if(user != null) {
            return user.getOfficeCode();
        }
        return null;
    }
    
    @Override
    public String getUsername() throws RemoteException {
        if(user != null) {
            return user.getUsername();
        }
        return null;
    }

    public boolean isUser(String username, String password) throws RemoteException {
        return server.isUser(username, password);
    }
    
    @Override
    public void updateUserAgreements(List<AgreementInterface> agreements) throws RemoteException {
        this.setChanged();
        this.notifyObservers(agreements);
    }
    
    @Override
    public void updateUserRentAccounts(List<RentAccountInterface> accounts) throws RemoteException {
        this.setChanged();
        this.notifyObservers(accounts);
    }

    public int createTitle(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createTitle(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateTitle(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateTitle(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteTitle(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteTitle(code);
        }
        return 0;
    }

    public int createGender(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createGender(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateGender(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateGender(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteGender(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteGender(code);
        }
        return 0;
    }

    public int createMaritalStatus(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createMaritalStatus(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateMaritalStatus(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateMaritalStatus(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteMaritalStatus(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteMaritalStatus(code);
        }
        return 0;
    }

    public int createEthnicOrigin(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createEthnicOrigin(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateEthnicOrigin(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateEthnicOrigin(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteEthnicOrigin(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEthnicOrigin(code);
        }
        return 0;
    }

    public int createLanguage(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createLanguage(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateLanguage(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateLanguage(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteLanguage(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLanguage(code);
        }
        return 0;
    }

    public int createNationality(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createNationality(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateNationality(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateNationality(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteNationality(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteNationality(code);
        }
        return 0;
    }

    public int createSexuality(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createSexuality(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateSexuality(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateSexuality(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteSexuality(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteSexuality(code);
        }
        return 0;
    }

    public int createReligion(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createReligion(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateReligion(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateReligion(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteReligion(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteReligion(code);
        }
        return 0;
    }

    public int createPropertyType(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPropertyType(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePropertyType(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePropertyType(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePropertyType(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertyType(code);
        }
        return 0;
    }

    public int createPropertySubType(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPropertySubType(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePropertySubType(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePropertySubType(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePropertySubType(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertySubType(code);
        }
        return 0;
    }

    public int createPropertyElement(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPropertyElement(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePropertyElement(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePropertyElement(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePropertyElement(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertyElement(code);
        }
        return 0;
    }

    public int createContactType(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createContactType(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateContactType(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateContactType(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteContactType(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteContactType(code);
        }
        return 0;
    }

    public int createEndReason(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createEndReason(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateEndReason(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateEndReason(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteEndReason(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEndReason(code);
        }
        return 0;
    }

    public int createRelationship(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createRelationship(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateRelationship(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateRelationship(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteRelationship(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteRelationship(code);
        }
        return 0;
    }

    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber,
            String street, String area, String town, String country, String postcode, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, this.getUsername());
        }
        return 0;
    }

    public int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street,
            String area, String town, String country, String postcode, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateAddress(aRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteAddress(int addrRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteAddress(addrRef);
        }
        return 0;
    }

    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate) throws RemoteException {
        if (server.isAlive()) {
            return server.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, this.getUsername());
        }
        return 0;
    }

    public int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePerson(pRef, titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, this.getUsername());
        }
        return 0;
    }

    public int deletePerson(int pRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePerson(pRef);
        }
        return 0;
    }

    public int createPersonNote(int pRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPersonNote(pRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePersonNote(int pRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePersonNote(pRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePersonNote(int pRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePersonNote(pRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createPersonDocument(int pRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createPersonDocument(pRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updatePersonDocument(int pRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updatePersonDocument(pRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deletePersonDocument(int pRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePersonDocument(pRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadPersonDocument(int pRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadPersonDocument(pRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date) throws RemoteException {
        if (server.isAlive()) {
            return server.createPersonContact(pRef, contactTypeCode, value, date, this.getUsername());
        }
        return 0;
    }

    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePersonContact(pRef, cRef, contactTypeCode, value, date, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePersonContact(int pRef, int cRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePersonContact(pRef, cRef, this.getUsername());
        }
        return 0;
    }

    public int createPersonAddressUsage(int pRef, int addrRef, Date startDate) throws RemoteException {
        if (server.isAlive()) {
            return server.createPersonAddressUsage(pRef, addrRef, startDate, this.getUsername());
        }
        return 0;
    }

    public int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePersonAddressUsage(pRef, addrUsageRef, addrRef, startDate, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePersonAddressUsage(pRef, aRef);
        }
        return 0;
    }

    public int createOffice(String officeCode, int addrRef, Date startDate) throws RemoteException {
        if (server.isAlive()) {
            return server.createOffice(officeCode, addrRef, startDate, this.getUsername());
        }
        return 0;
    }

    public int updateOffice(String officeCode, Date startDate) throws RemoteException {
        if (server.isAlive()) {
            return server.updateOffice(officeCode, startDate, this.getUsername());
        }
        return 0;
    }

    public int deleteOffice(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteOffice(code);
        }
        return 0;
    }

    public int createOfficeNote(String officeCode, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createOfficeNote(officeCode, comment, this.getUsername());
        }
        return 0;
    }

    public int updateOfficeNote(String officeCode, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateOfficeNote(officeCode, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteOfficeNote(String officeCode, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteOfficeNote(officeCode, nRef, this.getUsername());
        }
        return 0;
    }

    public int createOfficeDocument(String officeCode, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createOfficeDocument(officeCode, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateOfficeDocument(String oCode, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateOfficeDocument(oCode, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteOfficeDocument(String officeCode, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteOfficeDocument(officeCode, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadOfficeDocument(String officeCode, int version, int dRef) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadOfficeDocument(officeCode, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date) throws RemoteException {
        if (server.isAlive()) {
            return server.createOfficeContact(oCode, contactTypeCode, value, date, this.getUsername());
        }
        return 0;
    }

    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateOfficeContact(oCode, cRef, contactTypeCode, value, date, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteOfficeContact(String code, int cRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteOfficeContact(code, cRef);
        }
        return 0;
    }

    public int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, int address) throws RemoteException {
        if (server.isAlive()) {
            return server.createInvolvedParty(pRef, aRef, joint, main, start, relationshipCode, address, this.getUsername());
        }
        return 0;
    }

    public int updateInvolvedParty(int iRef, boolean joint, boolean main, Date start, String relationshipCode) throws RemoteException {
        if (server.isAlive()) {
            return server.updateInvolvedParty(iRef, joint, main, start, relationshipCode, this.getUsername());
        }
        return 0;
    }

    public int deleteInvolvedParty(int iRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteInvolvedParty(iRef);
        }
        return 0;
    }

    public int createInvovedPartyNote(int iRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createInvolvedPartyNote(iRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateInvolvedPartyNote(int eRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateInvolvedPartyNote(eRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteInvolvedPartyNote(int iRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteInvolvedPartyNote(iRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate) throws RemoteException {
        if (server.isAlive()) {
            return server.createApplication(corrName, appStartDate, pRef, relationshipCode, addrRef, addressStartDate, this.getUsername());
        }
        return 0;
    }

    public int updateApplication(int aRef, String corrName, Date appStartDate) throws RemoteException {
        if (server.isAlive()) {
            return server.updateApplication(aRef, corrName, appStartDate, this.getUsername());
        }
        return 0;
    }

    public int deleteApplication(int aRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteApplication(aRef, this.getUsername());
        }
        return 0;
    }

    public int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode) throws RemoteException {
        if (server.isAlive()) {
            return server.addInvolvedParty(aRef, pRef, joint, start, relationshipCode, this.getUsername());
        }
        return 0;
    }

    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode) throws RemoteException {
        if (server.isAlive()) {
            return server.changeMainApp(aRef, iRef, end, endReasonCode, this.getUsername());
        }
        return 0;
    }

    public int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode) throws RemoteException {
        if (server.isAlive()) {
            return server.endInvolvedParty(aRef, iRef, end, endReasonCode, this.getUsername());
        }
        return 0;
    }

    public int addInterestedProperty(int aRef, int pRef) throws RemoteException {
        if (server.isAlive()) {
            return server.addInterestedProperty(aRef, pRef, this.getUsername());
        }
        return 0;
    }

    public int endInterestInProperty(int aRef, int pRef) throws RemoteException {
        if (server.isAlive()) {
            return server.endInterestInProperty(aRef, pRef, this.getUsername());
        }
        return 0;
    }

    public int createApplicationNote(int aRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createApplicationNote(aRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateApplicationNote(int aRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateApplicationNote(aRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteApplicationNote(int aRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteApplicationNote(aRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createApplicationDocument(int aRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createApplicationDocument(aRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateApplicationDocument(int aRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateApplicationDocument(aRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteApplicationDocument(int aRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteApplicationDocument(aRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadApplicationDocument(int aRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadApplicationDocument(aRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateApplicationAddressUsage(aRef, addrUsageRef, addrRef, startDate, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteApplicationAddressUsage(int addrRef, int aRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteApplicationAddressUsage(addrRef, aRef);
        }
        return 0;
    }

    public int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate) throws RemoteException {
        if (server.isAlive()) {
            return server.createApplicationAddressUsage(applicationRef, addrRef, startDate, this.getUsername());
        }
        return 0;
    }

    public int createEmployee(int pRef, String username, String password) throws RemoteException {
        if (server.isAlive()) {
            return server.createEmployee(pRef, username, password, this.getUsername());
        }
        return 0;
    }

    public int deleteEmployee(int eRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEmployee(eRef);
        }
        return 0;
    }

    public int createEmployeeNote(int eRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createEmployeeNote(eRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateEmployeeNote(int eRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateEmployeeNote(eRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeNote(int eRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEmployeeNote(eRef, nRef, this.getUsername());
        }
        return 0;
    }
    
    public int updateEmployeePassword(int eRef, String password) throws RemoteException {
        if (server.isAlive()) {
            return server.updateEmployeePassword(eRef, password, this.getUsername());
        }
        return 0;
    }

    public int createLandlord(int lRef) throws RemoteException {
        if (server.isAlive()) {
            return server.createLandlord(lRef, this.getUsername());
        }
        return 0;
    }

    public int deleteLandlord(int lRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLandlord(lRef);
        }
        return 0;
    }

    public int createLandlordNote(int lRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createLandlordNote(lRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateLandlordNote(int lRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateLandlordNote(lRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteLandlordNote(int lRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLandlordNote(lRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createProperty(int addrRef, Date startDate, String propTypeCode, String propSubTypeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.createProperty(addrRef, startDate, propTypeCode, propSubTypeCode, this.getUsername());
        }
        return 0;
    }

    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.updateProperty(pRef, addrRef, startDate, propTypeCode, propSubTypeCode, this.getUsername());
        }
        return 0;
    }

    public int deleteProperty(int pRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteProperty(pRef);
        }
        return 0;
    }

    public int createPropertyNote(int pRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPropertyNote(pRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePropertyNote(int pRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePropertyNote(pRef, nRef, comment, this.getUsername());
        }
        return 0;
    }
    
    public int deletePropertyNote(int pRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertyNote(pRef, nRef, this.getUsername());
        }
        return 0;
    }
    
    public int createPropertyDocument(int pRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createPropertyDocument(pRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updatePropertyDocument(int pRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updatePropertyDocument(pRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deletePropertyDocument(int pRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertyDocument(pRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadPropertyDocument(int pRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadPropertyDocument(pRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, Double doubleValue, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createPropertyElement(pRef, elementCode, startDate, charge, stringValue, doubleValue, comment, this.getUsername());
        }
        return 0;
    }

    public int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updatePropertyElement(eRef, pRef, startDate, stringValue, doubleValue, charge, comment, this.getUsername());
        }
        return 0;
    }

    public int deletePropertyElement(int eRef, int pRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deletePropertyElement(eRef, pRef);
        }
        return 0;
    }

    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write,
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, this.getUsername());
        }
        return 0;
    }

    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write,
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) throws RemoteException {
        if (server.isAlive()) {
            return server.updateJobRole(code, jobTitle, jobDescription, fullTime, salary, current, read, write, update, employeeRead, employeeWrite, employeeUpdate, this.getUsername());
        }
        return 0;
    }

    public int deleteJobRole(String officeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobRole(officeCode);
        }
        return 0;
    }

    public int createJobRoleNote(String officeCode, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobRoleNote(officeCode, comment, this.getUsername());
        }
        return 0;
    }

    public int updateJobRoleNote(String officeCode, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateJobRoleNote(officeCode, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleNote(String officeCode, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobRoleNote(officeCode, nRef, this.getUsername());
        }
        return 0;
    }

    public int createJobRoleRequirement(String jobRoleCode, String requirement) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobRoleRequirement(jobRoleCode, requirement, this.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleRequirement(String jobRoleCode, String requirement) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobRoleRequirement(jobRoleCode, requirement, this.getUsername());
        }
        return 0;
    }

    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobRoleBenefit(jobRoleCode, benefit, startDate, salaryBenefit, stringValue, doubleValue, comment, this.getUsername());
        }
        return 0;
    }

    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateJobRoleBenefit(benefitRef, jobRoleCode, benefitCode, startDate, salaryBenefit, stringValue, doubleValue, comment, this.getUsername());
        }
        return 0;
    }

    public int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate) throws RemoteException {
        if (server.isAlive()) {
            return server.endJobRoleBenefit(benefitRef, jobRoleCode, endDate, this.getUsername());
        }
        return 0;
    }

    public int deleteJobRoleBenefit(String jobRoleCode, int benefit) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobRoleBenefit(jobRoleCode, benefit, this.getUsername());
        }
        return 0;
    }

    public int createJobRequirement(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobRequirement(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateJobRequirement(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateJobRequirement(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteJobRequirement(String requirement) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobRequirement(requirement);
        }
        return 0;
    }

    public int createJobBenefit(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createJobBenefit(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateJobBenefit(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateJobBenefit(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteJobBenefit(String benefit) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteJobBenefit(benefit);
        }
        return 0;
    }

    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.createTenancy(startDate, length, pRef, aRef, tenTypeCode, officeCode, this.getUsername());
        }
        return 0;
    }

    public int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.updateTenancy(tRef, name, startDate, length, tenTypeCode, this.getUsername());
        }
        return 0;
    }

    public int deleteTenancy(int tRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteTenancy(tRef, this.getUsername());
        }
        return 0;
    }

    public int createTenancyNote(int tRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createTenancyNote(tRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateTenancyNote(int tRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateTenancyNote(tRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteTenancyNote(int tRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteTenancyNote(tRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createTenancyDocument(int tRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createTenancyDocument(tRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateTenancyDocument(int tRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateTenancyDocument(tRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteTenancyDocument(int tRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteTenancyDocument(tRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadTenancyDocument(int tRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadTenancyDocument(tRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createTenancyType(String code, String description, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createTenancyType(code, description, comment, this.getUsername());
        }
        return 0;
    }

    public int updateTenancyType(String code, String description, boolean current, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateTenancyType(code, description, current, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteTenancyType(String code) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteTenancyType(code);
        }
        return 0;
    }

    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.createLease(startDate, length, pRef, management, expenditure, officeCode, this.getUsername());
        }
        return 0;
    }

    public int updateLease(int lRef, String name, Date startDate, int length) throws RemoteException {
        if (server.isAlive()) {
            return server.updateLease(lRef, name, startDate, length, this.getUsername());
        }
        return 0;
    }

    public int deleteLease(int lRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLease(lRef, this.getUsername());
        }
        return 0;
    }

    public int createLeaseNote(int lRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createLeaseNote(lRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateLeaseNote(int lRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateLeaseNote(lRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteLeaseNote(int lRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLeaseNote(lRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createLeaseDocument(int lRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createLeaseDocument(lRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateLeaseDocument(int lRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateLeaseDocument(lRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }
    
    public int deleteLeaseDocument(int lRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLeaseDocument(lRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadLeaseDocument(int lRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadLeaseDocument(lRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createLeaseLandlord(int lRef, int landRef) throws RemoteException {
        if (server.isAlive()) {
            return server.createLeaseLandlord(lRef, landRef, this.getUsername());
        }
        return 0;
    }

    public int endLeaseLandlord(int lRef, int landRef) throws RemoteException {
        if (server.isAlive()) {
            return server.endLeaseLandlord(lRef, landRef, this.getUsername());
        }
        return 0;
    }

    public int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode) throws RemoteException {
        if (server.isAlive()) {
            return server.createContract(startDate, length, eRef, jobRoleCode, officeCode, this.getUsername());
        }
        return 0;
    }

    public int updateContract(int cRef, String name, Date startDate, int length) throws RemoteException {
        if (server.isAlive()) {
            return server.updateContract(cRef, name, startDate, length, this.getUsername());
        }
        return 0;
    }

    public int deleteContract(int cRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteContract(cRef, this.getUsername());
        }
        return 0;
    }

    public int createContractNote(int cRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createContractNote(cRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateContractNote(int cRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateContractNote(cRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteContractNote(int cRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteContractNote(cRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createContractDocument(int cRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createContractDocument(cRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateContractDocument(int cRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateContractDocument(cRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteContractDocument(int cRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteContractDocument(cRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadContractDocument(int cRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadContractDocument(cRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createRentAccNote(int rAccRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createRentAccNote(rAccRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateRentAccNote(int rAccRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateRentAccNote(rAccRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteRentAccNote(int rAccRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteRentAccNote(rAccRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createRentAccDocument(int rAccRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createRentAccDocument(rAccRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateRentAccDocument(int rAccRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateRentAccDocument(rAccRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteRentAccDocument(int rAccRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteRentAccDocument(rAccRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadRentAccDocument(int rAccRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadRentAccDocument(rAccRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createLeaseAccNote(int lAccRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createLeaseAccNote(lAccRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateLeaseAccNote(int lAccRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateLeaseAccNote(lAccRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteLeaseAccNote(int lAccRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLeaseAccNote(lAccRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createLeaseAccDocument(int lAccRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createLeaseAccDocument(lAccRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateLeaseAccDocument(int lAccRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateLeaseAccDocument(lAccRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteLeaseAccDocument(int lAccRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLeaseAccDocument(lAccRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadLeaseAccDocument(int lAccRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadLeaseAccDocument(lAccRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createEmployeeAccNote(int eAccRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createEmployeeAccNote(eAccRef, comment, this.getUsername());
        }
        return 0;
    }

    public int updateEmployeeAccNote(int eAccRef, int nRef, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.updateEmployeeAccNote(eAccRef, nRef, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeAccNote(int eAccRef, int nRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEmployeeAccNote(eAccRef, nRef, this.getUsername());
        }
        return 0;
    }

    public int createEmployeeAccDocument(int eAccRef, String fileName, String comment) throws RemoteException {
        if (server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.createEmployeeAccDocument(eAccRef, fileName, documentData, comment, this.getUsername());
            }
        }
        return 0;
    }
    
    public int updateEmployeeAccDocument(int eAccRef, int dRef, String fileName) throws RemoteException {
        if(server.isAlive()) {
            byte[] documentData = null;
            try {
                documentData = this.uploadDocument(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (documentData != null && documentData.length >= 1) {
                return server.updateEmployeeAccDocument(eAccRef, dRef, documentData, this.getUsername());
            }
        }
        return 0;
    }

    public int deleteEmployeeAccDocument(int eAccRef, int dRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteEmployeeAccDocument(eAccRef, dRef, this.getUsername());
        }
        return 0;
    }

    public int downloadEmployeeAccDocument(int eAccRef, int dRef, int version) throws RemoteException {
        if (server.isAlive()) {
            try {
                byte[] buffer = server.downloadEmployeeAccDocument(eAccRef, dRef, version, this.getUsername());
                if(buffer != null) {
                    this.openDocument(buffer);
                }
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createRentAccTransaction(rAccRef, fromRef, toRef, amount, debit, transactionDate, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteRentAccTransaction(int tRef, int rAccRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteRentAccTransaction(tRef, rAccRef, this.getUsername());
        }
        return 0;
    }

    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createLeaseAccTransaction(lAccRef, fromRef, toRef, amount, debit, transactionDate, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteLeaseAccTransaction(int tRef, int lAccRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteLeaseAccTransaction(tRef, lAccRef, this.getUsername());
        }
        return 0;
    }

    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment) throws RemoteException {
        if (server.isAlive()) {
            return server.createRentAccTransaction(eAccRef, fromRef, toRef, amount, debit, transactionDate, comment, this.getUsername());
        }
        return 0;
    }

    public int deleteEmployeeAccTransaction(int tRef, int eAccRef) throws RemoteException {
        if (server.isAlive()) {
            return server.deleteRentAccTransaction(tRef, eAccRef, this.getUsername());
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
    
    public List<OfficeInterface> getOffices() throws RemoteException {
        return this.server.getOffices();
    }
    
    public List<OfficeInterface> getCurrentOffices() throws RemoteException {
        List<OfficeInterface> tempOffices = new ArrayList();
        for(OfficeInterface temp : this.getOffices()) {
            if (temp.isCurrent()) {
                tempOffices.add(temp);
            }
        }
        return tempOffices;
    }
    
    public List<AddressInterface> getAddresses() throws RemoteException {
        return this.server.getAddresses();
    }
    
    public List<User> getUsers() throws RemoteException {
        return this.server.getUsers();
    }
    
    public List<LandlordInterface> getLandlords() throws RemoteException {
        return this.server.getLandlords();
    }
    
    public List<EmployeeInterface> getEmployees() throws RemoteException {
        return this.server.getEmployees();
    }
    
    public List<EmployeeInterface> getCurrentEmployees() throws RemoteException {
        List<EmployeeInterface> tempEmployees = new ArrayList();
        for (EmployeeInterface temp : this.getEmployees()) {
            if (temp.isCurrent()) {
                tempEmployees.add(temp);
            }
        }
        return tempEmployees;
    }
    
    public List<JobRoleInterface> getJobRoles() throws RemoteException {
        return this.server.getJobRoles();
    }
    
    public List<JobRoleInterface> getCurrentJobRoles() throws RemoteException {
        List<JobRoleInterface> tempJobRoles = new ArrayList();
        for (JobRoleInterface temp : this.getJobRoles()) {
            if (temp.isCurrent()) {
                tempJobRoles.add(temp);
            }
        }
        return tempJobRoles;
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
    
    public boolean personEmployeeExists(int pRef) throws RemoteException {
        return this.server.personEmployeeExists(pRef);
    }

    
    public boolean personLandlordExists(int pRef) throws RemoteException {
        return this.server.personLandlordExists(pRef);
    }
    
    
    /// SEARCH METHODS
    
    public AddressInterface getAddress(int aRef) throws RemoteException {
        return this.server.getAddress(aRef);
    }
    
    public JobRoleInterface getJobRole(String jobRoleCode) throws RemoteException {
        return this.server.getJobRole(jobRoleCode);
    }
    
    public OfficeInterface getOffice(String officeCode) throws RemoteException {
        return this.server.getOffice(officeCode);
    }
    
    public ApplicationInterface getApplication(int aRef) throws RemoteException {
        return this.server.getApplication(aRef);
    }
    
    public PropertyInterface getProperty(int pRef) throws RemoteException {
        return this.server.getProperty(pRef);
    }
    
    public EmployeeInterface getEmployee(int eRef) throws RemoteException {
        return this.server.getEmployee(eRef);
    }
    
    public LandlordInterface getLandlord(int lRef) throws RemoteException {
        return this.server.getLandlord(lRef);
    }
    
    public TenancyInterface getTenancy(int tRef) throws RemoteException {
        return this.server.getTenancy(tRef);
    }
    
    public LeaseInterface getLease(int lRef) throws RemoteException {
        return this.server.getLease(lRef);
    }
    
    public ContractInterface getContract(int cRef) throws RemoteException {
        return this.server.getContract(cRef);
    }
    
    public RentAccountInterface getRentAccount(int rAccRef) throws RemoteException {
        return this.server.getRentAccount(rAccRef);
    }
    
    public LeaseAccountInterface getLeaseAccount(int lAccRef) throws RemoteException {
        return this.server.getLeaseAccount(lAccRef);
    }
    
    public EmployeeAccountInterface getEmployeeAccount(int eAccRef) throws RemoteException {
        return this.server.getEmployeeAccount(eAccRef);
    }

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
    
    
    public List<RentAccountInterface> getUserRentAccounts() throws RemoteException {
        if (server.isAlive()) {
            return this.server.getUserRentAccounts(user.getOfficeCode());
        }
        return null;
    }
    
    public List<AgreementInterface> getUserAgreements() throws RemoteException {
        if (server.isAlive()) {
            return this.server.getUserAgreements(user.getOfficeCode());
        }
        return null;
    }
    
    
    

    private byte[] uploadDocument(String fileName) throws IOException {
        File file = new File(fileName);
        byte documentData[] = new byte[(int) file.length()];
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.getName()))) {
            input.read(documentData, 0, documentData.length);
        }
        return documentData;
    }

    private File openDocument(byte[] documentData) throws IOException {
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.InvalidSecurityPriviligies;
import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.AgreementInterface;
import interfaces.ApplicationInterface;
import interfaces.Client;
import interfaces.ContractInterface;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.Server;
import interfaces.TenancyInterface;
import interfaces.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
/**
 * Proxy that protects the server from direct calls by the clients. All calls go
 * through a security check.
 */
public class ServerProxy extends UnicastRemoteObject implements Server {

    /**
     * A reference to the real server object
     */
    private Server theServer;

    /**
     * The user associated with this proxy
     */
    private User user;

    ////////////////////////
    /**
     * Class constructor.
     *
     * @param user A subject representing the user for this proxy.
     * @param theServer The real server object.
     * @throws java.rmi.RemoteException
     */
    public ServerProxy(User user, Server theServer) throws RemoteException {
        this.theServer = theServer;
        this.user = user;
    }

    @Override
    public int createTitle(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createTitle(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateTitle(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateTitle(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteTitle(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteTitle(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createGender(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createGender(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateGender(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateGender(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteGender(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteGender(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createMaritalStatus(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createMaritalStatus(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateMaritalStatus(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateMaritalStatus(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteMaritalStatus(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteMaritalStatus(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEthnicOrigin(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createEthnicOrigin(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateEthnicOrigin(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateEthnicOrigin(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEthnicOrigin(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteEthnicOrigin(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLanguage(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLanguage(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLanguage(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLanguage(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLanguage(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLanguage(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createNationality(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createNationality(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateNationality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateNationality(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteNationality(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteNationality(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createSexuality(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createSexuality(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateSexuality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateSexuality(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteSexuality(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteSexuality(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createReligion(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createReligion(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateReligion(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateReligion(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteReligion(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteReligion(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertyType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPropertyType(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertyType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertyType(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertyType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertyType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertySubType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPropertySubType(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertySubType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertySubType(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertySubType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertySubType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertyElement(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPropertyElement(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertyElement(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertyElement(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertyElement(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertyElement(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createContactType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createContactType(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateContactType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateContactType(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteContactType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteContactType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEndReason(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createEndReason(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateEndReason(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateEndReason(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEndReason(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteEndReason(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createRelationship(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createRelationship(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateRelationship(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateRelationship(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteRelationship(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteRelationship(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town, String country, String postcode, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town, String country, String postcode, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateAddress(aRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteAddress(int addrRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteAddress(addrRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, addrRef, addressStartDate, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPerson(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePerson(pRef, titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePerson(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePerson(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPersonNote(int pRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPersonNote(pRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePersonNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePersonNote(pRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePersonNote(int pRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePersonNote(pRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPersonDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPersonDocument(pRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePersonDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePersonDocument(pRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePersonDocument(int pRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePersonDocument(pRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadPersonDocument(int pRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadPersonDocument(pRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPersonContact(int pRef, String contactTypeCode, String value, Date date, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPersonContact(pRef, contactTypeCode, value, date, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePersonContact(pRef, cRef, contactTypeCode, value, date, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePersonContact(int pRef, int cRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePersonContact(pRef, cRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPersonAddressUsage(int pRef, int addrRef, Date startDate, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPersonAddressUsage(pRef, addrRef, startDate, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePersonAddressUsage(pRef, addrUsageRef, addrRef, startDate, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePersonAddressUsage(pRef, aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createOffice(String officeCode, int addrRef, Date startDate, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createOffice(officeCode, addrRef, startDate, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateOffice(String officeCode, Date startDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateOffice(officeCode, startDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteOffice(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteOffice(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createOfficeNote(String officeCode, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createOfficeNote(officeCode, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateOfficeNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateOfficeNote(officeCode, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteOfficeNote(String officeCode, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteOfficeNote(officeCode, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createOfficeDocument(String oCode, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createOfficeDocument(oCode, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateOfficeDocument(String oCode, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateOfficeDocument(oCode, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteOfficeDocument(String oCode, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteOfficeDocument(oCode, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadOfficeDocument(String oCode, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.downloadOfficeDocument(oCode, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createOfficeContact(String oCode, String contactTypeCode, String value, Date date, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createOfficeContact(oCode, contactTypeCode, value, date, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateOfficeContact(oCode, cRef, contactTypeCode, value, date, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteOfficeContact(String code, int cRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteOfficeContact(code, cRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createInvolvedParty(pRef, aRef, joint, main, start, relationshipCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateInvolvedParty(int iRef, boolean joint, Date start, String relationshipCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateInvolvedParty(iRef, joint, start, relationshipCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteInvolvedParty(int iRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteInvolvedParty(iRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createInvolvedPartyNote(int iRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createInvolvedPartyNote(iRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateInvolvedPartyNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateInvolvedPartyNote(eRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteInvolvedPartyNote(int iRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteInvolvedPartyNote(iRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createApplication(corrName, appStartDate, pRef, relationshipCode, addrRef, addressStartDate, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateApplication(int aRef, String corrName, Date appStartDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateApplication(aRef, corrName, appStartDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteApplication(int aRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteApplication(aRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.addInvolvedParty(aRef, pRef, joint, start, relationshipCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int changeMainApp(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.changeMainApp(aRef, iRef, end, endReasonCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.endInvolvedParty(aRef, iRef, end, endReasonCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int addInterestedProperty(int aRef, int pRef, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.addInterestedProperty(aRef, pRef, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endInterestInProperty(int aRef, int pRef, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.endInterestInProperty(aRef, pRef, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createApplicationNote(int aRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createApplicationNote(aRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateApplicationNote(int aRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateApplicationNote(aRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteApplicationNote(int aRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteApplicationNote(aRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createApplicationDocument(int aRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createApplicationDocument(aRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateApplicationDocument(int aRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateApplicationDocument(aRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteApplicationDocument(int aRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteApplicationDocument(aRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadApplicationDocument(int aRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadApplicationDocument(aRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createApplicationAddressUsage(applicationRef, addrRef, startDate, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateApplicationAddressUsage(aRef, addrUsageRef, addrRef, startDate, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteApplicationAddressUsage(int addrRef, int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteApplicationAddressUsage(addrRef, aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEmployee(int pRef, String username, String password, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createEmployee(pRef, username, password, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int setEmployeeMemorableLocation(String memorableLocation, int eRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRef() == eRef) {
                return theServer.setEmployeeMemorableLocation(memorableLocation, eRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int forgotPassword(String email, int eRef, String username, String answer) throws RemoteException {
        if (theServer.isAlive()) {
            return theServer.forgotPassword(email, eRef, username, answer);
        }
        throw new RemoteException();
    }

    @Override
    public int updateEmployeePassword(int employeeRef, String password, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            System.out.println("user is not null? " + user != null);
            System.out.println("user empRef == empRef? " + (user.getEmployeeRef() == employeeRef));
            System.out.println("user can update employees? " + user.getEmployeeUpdate());
            if (user != null && (user.getEmployeeRef() == employeeRef || user.getEmployeeUpdate())) {
                return theServer.updateEmployeePassword(employeeRef, password, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEmployee(int eRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteEmployee(eRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEmployeeNote(int eRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createEmployeeNote(eRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateEmployeeNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateEmployeeNote(eRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEmployeeNote(int eRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteEmployeeNote(eRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLandlord(int pRef, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLandlord(pRef, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLandlord(int lRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLandlord(lRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLandlordNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLandlordNote(lRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLandlordNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLandlordNote(lRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLandlordNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLandlordNote(lRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createProperty(int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createProperty(addrRef, startDate, propTypeCode, propSubTypeCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateProperty(pRef, addrRef, startDate, propTypeCode, propSubTypeCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteProperty(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteProperty(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertyNote(int pRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLandlord(pRef, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertyNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertyNote(pRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertyNote(int pRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertyNote(pRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertyDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPropertyDocument(pRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertyDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertyDocument(pRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertyDocument(int pRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertyDocument(pRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadPropertyDocument(int pRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadPropertyDocument(pRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, Double doubleValue, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createPropertyElement(pRef, elementCode, startDate, charge, stringValue, doubleValue, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updatePropertyElement(eRef, pRef, startDate, stringValue, doubleValue, charge, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deletePropertyElement(int eRef, int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deletePropertyElement(eRef, pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, boolean update, boolean delete, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, boolean employeeDelete, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobRole(code, jobTitle, jobDescription, fullTime, salary, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write, boolean update, boolean delete, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, boolean employeeDelete, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateJobRole(code, jobTitle, jobDescription, fullTime, salary, current, read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobRole(String officeCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobRole(officeCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobRoleNote(String officeCode, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobRoleNote(officeCode, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateJobRoleNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateJobRoleNote(officeCode, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobRoleNote(String officeCode, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobRoleNote(officeCode, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobRoleRequirement(String jobRoleCode, String requirement, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobRoleRequirement(jobRoleCode, requirement, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobRoleRequirement(String jobRoleCode, String requirement, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobRoleRequirement(jobRoleCode, requirement, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobRoleBenefit(jobRoleCode, benefit, startDate, salaryBenefit, stringValue, doubleValue, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateJobRoleBenefit(int benefitRef, String jobRoleCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateJobRoleBenefit(benefitRef, jobRoleCode, startDate, salaryBenefit, stringValue, doubleValue, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.endJobRoleBenefit(benefitRef, jobRoleCode, endDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobRoleBenefit(String jobRoleCode, int benefit, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobRoleBenefit(jobRoleCode, benefit, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public JobRoleBenefitInterface getJobRoleBenefit(int jobRoleBenefitRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getJobRoleBenefit(jobRoleBenefitRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobRequirement(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobRequirement(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateJobRequirement(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateJobRequirement(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobRequirement(String requirement) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobRequirement(requirement);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createJobBenefit(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createJobBenefit(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateJobBenefit(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateJobBenefit(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteJobBenefit(String benefit) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteJobBenefit(benefit);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createTenancy(startDate, length, pRef, aRef, tenTypeCode, officeCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateTenancy(tRef, name, startDate, length, tenTypeCode, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endTenancy(int tRef, Date endDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.endTenancy(tRef, endDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteTenancy(int tRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteTenancy(tRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createTenancyNote(int tRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createTenancyNote(tRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateTenancyNote(int tRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateTenancyNote(tRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteTenancyNote(int tRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteTenancyNote(tRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createTenancyDocument(int tRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createTenancyDocument(tRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateTenancyDocument(int tRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateTenancyDocument(tRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteTenancyDocument(int tRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteTenancyDocument(tRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadTenancyDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadTenancyDocument(tRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createTenancyType(String code, String description, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createTenancyType(code, description, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateTenancyType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateTenancyType(code, description, current, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteTenancyType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteTenancyType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLease(startDate, length, pRef, management, expenditure, officeCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, List<LandlordInterface> landlords, String officeCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLease(startDate, length, pRef, management, expenditure, landlords, officeCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLease(int lRef, String name, Date startDate, int length, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLease(lRef, name, startDate, length, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endLease(int lRef, Date endDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.endLease(lRef, endDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLease(int lRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLease(lRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseNote(lRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLeaseNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLeaseNote(lRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLeaseNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLeaseNote(lRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseDocument(lRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLeaseDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLeaseDocument(lRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLeaseDocument(int lRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLeaseDocument(lRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadLeaseDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadLeaseDocument(tRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseLandlord(lRef, landRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.endLeaseLandlord(lRef, landRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createContract(startDate, length, eRef, jobRoleCode, officeCode, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateContract(int cRef, String name, Date startDate, int length, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateContract(cRef, name, startDate, length, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int endContract(int cRef, Date endDate, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.endContract(cRef, endDate, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteContract(int cRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteContract(cRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createContractNote(int cRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createContractNote(cRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateContractNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateContractNote(cRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteContractNote(int cRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteContractNote(cRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createContractDocument(int cRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createContractDocument(cRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateContractDocument(int cRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateContractDocument(cRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteContractDocument(int cRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteContractDocument(cRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadContractDocument(int tRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.downloadContractDocument(tRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createRentAccNote(int rRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createRentAccNote(rRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateRentAccNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateRentAccNote(cRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteRentAccNote(int rRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteRentAccNote(rRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createRentAccDocument(int rRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createRentAccDocument(rRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateRentAccDocument(int rRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateRentAccDocument(rRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteRentAccDocument(int rRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteRentAccDocument(rRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadRentAccDocument(int rRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadRentAccDocument(rRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseAccNote(int lRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseAccNote(lRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLeaseAccNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLeaseAccNote(lRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLeaseAccNote(int lRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLeaseAccNote(lRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseAccDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseAccDocument(lRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateLeaseAccDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getUpdate()) {
                return theServer.updateLeaseAccDocument(lRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLeaseAccDocument(int lRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLeaseAccDocument(lRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadLeaseAccDocument(int lRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadLeaseAccDocument(lRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEmployeeAccNote(int eRef, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createEmployeeAccNote(eRef, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateEmployeeAccNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateEmployeeAccNote(eRef, nRef, comment, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEmployeeAccNote(int eRef, int nRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteEmployeeAccNote(eRef, nRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEmployeeAccDocument(int eRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createEmployeeAccDocument(eRef, fileName, buffer, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int updateEmployeeAccDocument(int eRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeUpdate()) {
                return theServer.updateEmployeeAccDocument(eRef, dRef, buffer, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEmployeeAccDocument(int eRef, int dRef, String modifiedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteEmployeeAccDocument(eRef, dRef, modifiedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public byte[] downloadEmployeeAccDocument(int eRef, int dRef, int version, String downloadedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.downloadEmployeeAccDocument(eRef, dRef, version, downloadedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createRentAccTransaction(rAccRef, fromRef, toRef, amount, debit, transactionDate, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteRentAccTransaction(int tRef, int rAccRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteRentAccTransaction(tRef, rAccRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getWrite()) {
                return theServer.createLeaseAccTransaction(lAccRef, fromRef, toRef, amount, debit, transactionDate, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteLeaseAccTransaction(int tRef, int lAccRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getDelete()) {
                return theServer.deleteLeaseAccTransaction(tRef, lAccRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeWrite()) {
                return theServer.createEmployeeAccTransaction(eAccRef, fromRef, toRef, amount, debit, transactionDate, comment, createdBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public int deleteEmployeeAccTransaction(int tRef, int eAccRef, String deletedBy) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeDelete()) {
                return theServer.deleteEmployeeAccTransaction(tRef, eAccRef, deletedBy);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getTitle(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTitle(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getTitles() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTitles();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getGender(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getGender(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getGenders() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getGenders();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getMaritalStatus(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getMaritalStatus(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getMaritalStatuses() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getMaritalStatuses();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getEthnicOrigin(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getEthnicOrigin(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getEthnicOrigins() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getEthnicOrigins();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getLanguage(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLanguage(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getLanguages() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLanguages();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getNationality(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNationality(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getNationalities() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNationalities();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getSexuality(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getSexuality(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getSexualities() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getSexualities();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getReligion(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getReligion(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getReligions() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getReligions();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getPropertyType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getPropertyTypes() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyTypes();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getPropertySubType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertySubType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getPropertySubTypes() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertySubTypes();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getPropElement(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropElement(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getPropElements() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropElements();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getContactType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getContactType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getContactTypes() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getContactTypes();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getEndReason(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getEndReason(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getEndReasons() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getEndReasons();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getRelationship(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getRelationship(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getRelationships() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getRelationships();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getJobBenefit(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getJobBenefit(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getJobBenefits() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getJobBenefits();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getJobRequirement(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getJobRequirement(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getJobRequirements() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getJobRequirements();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public Element getTenancyType(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenancyType(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<Element> getTenancyTypes() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenancyTypes();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<OfficeInterface> getOffices() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOffices();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<AddressInterface> getAddresses() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getAddresses();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getUsers();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LandlordInterface> getLandlords() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLandlords();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeInterface> getEmployees() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getEmployees();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<JobRoleInterface> getJobRoles() throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getJobRoles();
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean titleExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.titleExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean genderExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.genderExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean maritalStatusExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.maritalStatusExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean ethnicOriginExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.ethnicOriginExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean languageExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.languageExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean nationalityExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.nationalityExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean sexualityExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.sexualityExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean religionExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.religionExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean contactTypeExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.contactTypeExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean propTypeExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.propTypeExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean propSubTypeExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.propSubTypeExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean propElementExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.propElementExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean endReasonExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.endReasonExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean relationshipExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.relationshipExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean officeExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.officeExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean jobRoleExists(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.jobRoleExists(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean isUser(String username, String password) throws RemoteException {
        if (theServer.isAlive()) {
            return theServer.isUser(username, password);
        }
        throw new RemoteException();
    }

    @Override
    public boolean personExists(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.personExists(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean personEmployeeExists(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.personEmployeeExists(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public boolean personLandlordExists(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.personLandlordExists(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }
    
    @Override
    public boolean addressExists(int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.addressExists(aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public AddressInterface getAddress(int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getAddress(aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public AddressUsageInterface getAddressUsage(int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getAddressUsage(aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public JobRoleInterface getJobRole(String jobRoleCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getJobRole(jobRoleCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public OfficeInterface getOffice(String officeCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getOffice(officeCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public InvolvedPartyInterface getInvolvedParty(int iRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getInvolvedParty(iRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public ApplicationInterface getApplication(int aRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getApplication(aRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public PropertyInterface getProperty(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getProperty(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public PersonInterface getPerson(int pRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPerson(pRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public EmployeeInterface getEmployee(int eRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getEmployee(eRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public LandlordInterface getLandlord(int lRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLandlord(lRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public TenancyInterface getTenancy(int tRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenancy(tRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public LeaseInterface getLease(int lRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLease(lRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public ContractInterface getContract(int cRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContract(cRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public RentAccountInterface getRentAccount(int rAccRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getRentAccount(rAccRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public LeaseAccountInterface getLeaseAccount(int lAccRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeaseAccount(lAccRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public EmployeeAccountInterface getEmployeeAccount(int eAccRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getEmployeeAccount(eAccRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<PersonInterface> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<AddressInterface> getAddresses(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town, String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getAddresses(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ApplicationInterface> getApplications(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getApplications(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ApplicationInterface> getPeopleApplications(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPeopleApplications(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ApplicationInterface> getAddressApplications(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town, String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getAddressApplications(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ApplicationInterface> getCorrNameApplcations(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getCorrNameApplcations(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public ApplicationInterface getInvPartyApplcation(int invPartyRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getInvPartyApplcation(invPartyRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeInterface> getPeopleEmployees(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getPeopleEmployees(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LandlordInterface> getPeopleLandlords(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPeopleLandlords(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<PropertyInterface> getProperties(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getProperties(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenancies(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getApplicationTenancies(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getApplicationTenancies(corrName, appStartDate, endDate, statusCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getApplicationTenancies(int appRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getApplicationTenancies(appRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getPropertyTenancies(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyTenancies(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getPropertyTenancies(int propRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyTenancies(propRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getNameTenancies(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNameTenancies(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getOfficeTenancies(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOfficeTenancies(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeases(name, startDate, expectedEndDate, endDate, length, propRef, management, expenditure, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getPropertyLeases(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyLeases(acquiredDate, leaseEndDate, propTypeCode, propSubTypeCode, propStatus, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getPropertyLeases(int propRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getPropertyLeases(propRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getNameLeases(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNameLeases(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getOfficeLeases(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOfficeLeases(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getLandlordLeases(int landlordRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLandlordLeases(landlordRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getLandlordLeases(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLandlordLeases(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContracts(name, startDate, expectedEndDate, endDate, length, propRef, employeeRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getNameContracts(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getNameContracts(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getOfficeContracts(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getOfficeContracts(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getEmployeeContracts(int ref) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getEmployeeContracts(ref);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getJobRoleContracts(String code) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getJobRoleContracts(code);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<RentAccountInterface> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getRentAccounts(name, startDate, endDate, balance, rent, agreementRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<RentAccountInterface> getNameRentAcc(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNameRentAcc(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<RentAccountInterface> getOfficeRentAcc(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOfficeRentAcc(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<RentAccountInterface> getTenanciesRentAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenanciesRentAccounts(name, startDate, expectedEndDate, endDate, length, propRef, appRef, tenTypeCode, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public RentAccountInterface getTenancyRentAcc(int tenancyRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenancyRentAcc(tenancyRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseAccountInterface> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeaseAccounts(name, startDate, endDate, balance, expenditure, agreementRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseAccountInterface> getNameLeaseAcc(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getNameLeaseAcc(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseAccountInterface> getOfficeLeaseAcc(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOfficeLeaseAcc(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseAccountInterface> getLeasesLeaseAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propertyRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeasesLeaseAccounts(name, startDate, expectedEndDate, endDate, length, propertyRef, management, expenditure, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public LeaseAccountInterface getLeaseLeaseAcc(int leaseRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeaseLeaseAcc(leaseRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeAccountInterface> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getEmployeeAccounts(name, startDate, endDate, balance, salary, agreementRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeAccountInterface> getNameEmployeeAcc(String name) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getNameEmployeeAcc(name);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeAccountInterface> getOfficeEmployeeAcc(String office) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getOfficeEmployeeAcc(office);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<EmployeeAccountInterface> getContractsEmployeeAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContractsEmployeeAccounts(name, startDate, expectedEndDate, endDate, length, propRef, employeeRef, jobRoleCode, accountRef, officeCode, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public EmployeeAccountInterface getContractEmployeeAcc(int contractRef) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContractEmployeeAcc(contractRef);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<OfficeInterface> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getOffices(addrRef, startDate, current, createdBy, createdDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getTenanciesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getTenanciesByEmployee(eRef, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<TenancyInterface> getTenanciesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getTenanciesByOffice(officeCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getLeasesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getLeasesByEmployee(eRef, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<LeaseInterface> getLeasesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getLeasesByOffice(officeCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getContractsByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContractsByEmployee(eRef, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<ContractInterface> getContractsByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getContractsByOffice(officeCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getRevenueForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getRevenueForOffice(officeCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getExpenditureForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getExpenditureForOffice(officeCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getProfitForOffice(String officCode, Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getProfitForOffice(officCode, startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getRevenueOverall(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getRevenueOverall(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getExpenditureOverall(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getExpenditureOverall(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public double getProfitOverall(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.getProfitOverall(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public String generateEmployeeReport(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.generateEmployeeReport(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public String generateOfficeReport(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.generateOfficeReport(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public String generateOfficeFinanceReport(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.generateOfficeFinanceReport(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public String generateFinanceReport(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                return theServer.generateFinanceReport(startDate, endDate);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public void generateReport(Date startDate, Date endDate) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getEmployeeRead()) {
                theServer.generateReport(startDate, endDate);
            } else {
                throw new InvalidSecurityPriviligies(user.getUsername());
            }
        } else {
            throw new RemoteException();
        }
    }

    @Override
    public List<AccountInterface> getUserRentAccounts(String officeCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getUserRentAccounts(officeCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<AgreementInterface> getUserTenancies(String officeCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getUserTenancies(officeCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public List<AgreementInterface> getUserLeases(String officeCode) throws RemoteException {
        if (theServer.isAlive()) {
            if (user != null && user.getRead()) {
                return theServer.getUserLeases(officeCode);
            }
            throw new InvalidSecurityPriviligies(user.getUsername());
        }
        throw new RemoteException();
    }

    @Override
    public void register(Client c) throws RemoteException {
        if (theServer.isAlive()) {
            theServer.register(c);
        } else {
            throw new RemoteException();
        }
    }

    @Override
    public void unregister(Client c) throws RemoteException {
        if (theServer.isAlive()) {
            theServer.unregister(c);
        } else {
            throw new RemoteException();
        }
    }

    @Override
    public boolean isAlive() throws RemoteException {
        return theServer.isAlive();
    }

    @Override
    public User getUser(String username) throws RemoteException {
        if (theServer.isAlive()) {
            return theServer.getUser(username);
        }
        throw new RemoteException();
    }
}

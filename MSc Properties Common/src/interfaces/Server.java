/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface Server extends Remote {
    int createTitle(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateTitle(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteTitle(String code) throws RemoteException, SQLException;
    int createGender(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateGender(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteGender(String code) throws RemoteException, SQLException;
    int createMaritalStatus(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateMaritalStatus(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteMaritalStatus(String code) throws RemoteException, SQLException;
    int createEthnicOrigin(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateEthnicOrigin(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteEthnicOrigin(String code) throws RemoteException, SQLException;
    int createLanguage(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLanguage(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteLanguage(String code) throws RemoteException, SQLException;
    int createNationality(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateNationality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteNationality(String code) throws RemoteException, SQLException;
    int createSexuality(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateSexuality(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteSexuality(String code) throws RemoteException, SQLException;
    int createReligion(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateReligion(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteReligion(String code) throws RemoteException, SQLException;
    int createPropertyType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertyType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePropertyType(String code) throws RemoteException, SQLException;
    int createPropertySubType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertySubType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePropertySubType(String code) throws RemoteException, SQLException;
    int createPropertyElement(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertyElement(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePropertyElement(String code) throws RemoteException, SQLException;
    int createContactType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateContactType(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteContactType(String code) throws RemoteException, SQLException;
    int createEndReason(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateEndReason(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteEndReason(String code) throws RemoteException, SQLException;
    int createRelationship(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateRelationship(String code, String description, boolean current, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteRelationship(String code) throws RemoteException, SQLException;
    int createAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, 
            String area, String town, String country, String postcode, String comment, String createdBy) throws RemoteException, SQLException;
    int updateAddress(int aRef, String buildingNumber, String buildingName, String subStreetNumber, String subStreet, String streetNumber, String street, 
            String area, String town, String country, String postcode, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteAddress(int addrRef) throws RemoteException, SQLException;
    int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException;
    int createPerson(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy) throws RemoteException, SQLException;
    int updatePerson(int pRef, String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String modifiedBy) throws RemoteException, SQLException;
    int deletePerson(int pRef) throws RemoteException, SQLException;
    int createPersonNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePersonNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePersonNote(int pRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createPersonDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePersonDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deletePersonDocument(int pRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadPersonDocument(int pRef, int dRef, String downloadedBy) throws RemoteException;
    int createPersonContact(int pRef, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException;
    int updatePersonContact(int pRef, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws  RemoteException, SQLException;
    int deletePersonContact(int pRef, int cRef, String modifiedBy) throws RemoteException, SQLException;
    int createPersonAddressUsage(int pRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException;
    int updatePersonAddressUsage(int pRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePersonAddressUsage(int pRef, int aRef) throws RemoteException, SQLException;
    int createOffice(String officeCode, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException;
    int updateOffice(String officeCode, Date startDate, String modifiedBy) throws RemoteException, SQLException;
    int deleteOffice(String code) throws RemoteException, SQLException;
    int createOfficeNote(String officeCode, String comment, String createdBy) throws RemoteException, SQLException;
    int updateOfficeNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteOfficeNote(String officeCode, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createOfficeDocument(String oCode, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateOfficeDocument(String oCode, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteOfficeDocument(String oCode, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadOfficeDocument(String oCode, int dRef, String downloadedBy) throws RemoteException;
    int createOfficeContact(String oCode, String contactTypeCode, String value, Date date, String createdBy) throws RemoteException, SQLException;
    int updateOfficeContact(String oCode, int cRef, String contactTypeCode, String value, Date date, String comment, String modifiedBy) throws  RemoteException, SQLException;
    int deleteOfficeContact(String code, int cRef) throws RemoteException, SQLException;
    int createInvolvedParty(int pRef, int aRef, boolean joint, boolean main, Date start, String relationshipCode, int address, String createdBy) throws RemoteException, SQLException;
    int updateInvolvedParty(int iRef, boolean joint, boolean main, Date start, String relationshipCode, String modifiedBy) throws  RemoteException, SQLException;
    int deleteInvolvedParty(int iRef) throws RemoteException, SQLException;
    int createInvolvedPartyNote(int iRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateInvolvedPartyNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteInvolvedPartyNote(int iRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createApplication(String corrName, Date appStartDate, int pRef, String relationshipCode, int addrRef, Date addressStartDate, String createdBy) throws RemoteException, SQLException;
    int updateApplication(int aRef, String corrName, Date appStartDate, String modifiedBy) throws RemoteException, SQLException;
    int deleteApplication(int aRef, String modifiedBy) throws RemoteException, SQLException;
    int addInvolvedParty(int aRef, int pRef, boolean joint, Date start, String relationshipCode, String createdBy) throws RemoteException, SQLException;
    int changeMainApp(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException, SQLException;
    int endInvolvedParty(int aRef, int iRef, Date end, String endReasonCode, String modifiedBy) throws RemoteException, SQLException;
    int addInterestedProperty(int aRef, int pRef, String createdBy) throws RemoteException, SQLException;
    int endInterestInProperty(int aRef, int pRef, String createdBy) throws RemoteException, SQLException;
    int createApplicationNote(int aRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateApplicationNote(int aRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteApplicationNote(int aRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createApplicationDocument(int aRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateApplicationDocument(int aRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteApplicationDocument(int aRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadApplicationDocument(int aRef, int dRef, String downloadedBy) throws RemoteException;
    int createApplicationAddressUsage(int applicationRef, int addrRef, Date startDate, String createdBy) throws RemoteException, SQLException;
    int updateApplicationAddressUsage(int aRef, int addrUsageRef, int addrRef, Date startDate, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteApplicationAddressUsage(int addrRef, int aRef) throws RemoteException, SQLException;
    int createEmployee(int pRef, String username, String password, String createdBy) throws RemoteException, SQLException;
    int updateEmployeePassword(int eRef, String password, String modifiedBy) throws RemoteException, SQLException;
    int deleteEmployee(int eRef) throws RemoteException, SQLException;
    int createEmployeeNote(int eRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateEmployeeNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteEmployeeNote(int eRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createLandlord(int lRef, String createdBy) throws RemoteException, SQLException;
    int deleteLandlord(int lRef) throws RemoteException, SQLException;
    int createLandlordNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLandlordNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteLandlordNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createProperty(int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String createdBy) throws RemoteException, SQLException;
    int updateProperty(int pRef, int addrRef, Date startDate, String propTypeCode, String propSubTypeCode, String modifiedBy) throws RemoteException, SQLException;
    int deleteProperty(int pRef) throws RemoteException, SQLException;
    int createPropertyNote(int pRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertyNote(int pRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePropertyNote(int pRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createPropertyDocument(int pRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertyDocument(int pRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deletePropertyDocument(int pRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadPropertyDocument(int pRef, int dRef, String downloadedBy) throws RemoteException;
    int createPropertyElement(int pRef, String elementCode, Date startDate, boolean charge, String stringValue, Double doubleValue, String comment, String createdBy) throws RemoteException, SQLException;
    int updatePropertyElement(int eRef, int pRef, Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deletePropertyElement(int eRef, int pRef) throws RemoteException, SQLException;
    int createJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String createdBy) throws RemoteException, SQLException;
    int updateJobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean current, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String modifiedBy) throws RemoteException, SQLException;
    int deleteJobRole(String officeCode) throws RemoteException, SQLException;
    int createJobRoleNote(String officeCode, String comment, String createdBy) throws RemoteException, SQLException;
    int updateJobRoleNote(String officeCode, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteJobRoleNote(String officeCode, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createJobRoleRequirement(String jobRoleCode, String requirement, String createdBy) throws RemoteException, SQLException;
    int deleteJobRoleRequirement(String jobRoleCode, String requirement, String deletedBy) throws RemoteException, SQLException;
    int createJobRoleBenefit(String jobRoleCode, String benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String createdBy) throws RemoteException, SQLException;
    int updateJobRoleBenefit(int benefitRef, String jobRoleCode, String benefitCode, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String comment, String modifiedBy) throws RemoteException, SQLException;
    int endJobRoleBenefit(int benefitRef, String jobRoleCode, Date endDate, String modifiedBy) throws RemoteException, SQLException;
    int deleteJobRoleBenefit(String jobRoleCode, int benefit, String deletedBy) throws RemoteException, SQLException;
    int createJobRequirement(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateJobRequirement(String code, String description, boolean current, String comment, String modifiedBy) throws SQLException, RemoteException;
    int deleteJobRequirement(String requirement) throws RemoteException, SQLException;
    int createJobBenefit(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateJobBenefit(String code, String description, boolean current, String comment, String modifiedBy) throws SQLException, RemoteException;
    int deleteJobBenefit(String benefit) throws RemoteException, SQLException;
    int createTenancy(Date startDate, int length, int pRef, int aRef, String tenTypeCode, String officeCode, String createdBy) throws RemoteException, SQLException;
    int updateTenancy(int tRef, String name, Date startDate, int length, String tenTypeCode, String modifiedBy) throws SQLException, RemoteException;
    int deleteTenancy(int tRef, String deletedBy) throws RemoteException, SQLException;
    int createTenancyNote(int tRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateTenancyNote(int tRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteTenancyNote(int tRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createTenancyDocument(int tRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateTenancyDocument(int tRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteTenancyDocument(int tRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadTenancyDocument(int tRef, int dRef, String downloadedBy) throws RemoteException;
    int createTenancyType(String code, String description, String comment, String createdBy) throws RemoteException, SQLException;
    int updateTenancyType(String code, String description, boolean current, String comment, String modifiedBy) throws  RemoteException, SQLException;
    int deleteTenancyType(String code) throws RemoteException, SQLException;
    int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, String officeCode, String createdBy) throws RemoteException, SQLException;
    int createLease(Date startDate, int length, int pRef, boolean management, double expenditure, List<LandlordInterface> landlords, String officeCode, String createdBy) throws RemoteException, SQLException;
    int updateLease(int lRef, String name, Date startDate, int length, String modifiedBy) throws SQLException, RemoteException;
    int deleteLease(int lRef, String deletedBy) throws RemoteException, SQLException;
    int createLeaseNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLeaseNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteLeaseNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createLeaseDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLeaseDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteLeaseDocument(int lRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadLeaseDocument(int tRef, int dRef, String downloadedBy) throws RemoteException;
    int createLeaseLandlord(int lRef, int landRef, String modifiedBy) throws SQLException, RemoteException;
    int endLeaseLandlord(int lRef, int landRef, String modifiedBy) throws RemoteException, SQLException;
    int createContract(Date startDate, int length, int eRef, String jobRoleCode, String officeCode, String createdBy) throws RemoteException, SQLException;
    int updateContract(int cRef, String name, Date startDate, int length, String modifiedBy) throws SQLException, RemoteException;
    int deleteContract(int cRef, String deletedBy) throws RemoteException, SQLException;
    int createContractNote(int cRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateContractNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteContractNote(int cRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createContractDocument(int cRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateContractDocument(int cRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteContractDocument(int cRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadContractDocument(int tRef, int dRef, String downloadedBy) throws RemoteException;
    int createRentAccNote(int rRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateRentAccNote(int cRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteRentAccNote(int rRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createRentAccDocument(int rRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateRentAccDocument(int rRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteRentAccDocument(int rRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadRentAccDocument(int rRef, int dRef, String downloadedBy) throws RemoteException;
    int createLeaseAccNote(int lRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLeaseAccNote(int lRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteLeaseAccNote(int lRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createLeaseAccDocument(int lRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateLeaseAccDocument(int lRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteLeaseAccDocument(int lRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadLeaseAccDocument(int lRef, int dRef, String downloadedBy) throws RemoteException;
    int createEmployeeAccNote(int eRef, String comment, String createdBy) throws RemoteException, SQLException;
    int updateEmployeeAccNote(int eRef, int nRef, String comment, String modifiedBy) throws RemoteException, SQLException;
    int deleteEmployeeAccNote(int eRef, int nRef, String modifiedBy) throws RemoteException, SQLException;
    int createEmployeeAccDocument(int eRef, String fileName, byte[] buffer, String comment, String createdBy) throws RemoteException, SQLException;
    int updateEmployeeAccDocument(int eRef, int dRef, byte[] buffer, String modifiedBy) throws RemoteException;
    int deleteEmployeeAccDocument(int eRef, int dRef, String modifiedBy) throws RemoteException, SQLException;
    byte[] downloadEmployeeAccDocument(int eRef, int dRef, String downloadedBy) throws RemoteException;
    int createRentAccTransaction(int rAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException;
    int deleteRentAccTransaction(int tRef, int rAccRef, String deletedBy) throws RemoteException, SQLException;
    int createLeaseAccTransaction(int lAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException;
    int deleteLeaseAccTransaction(int tRef, int lAccRef, String deletedBy) throws RemoteException, SQLException;
    int createEmployeeAccTransaction(int eAccRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String comment, String createdBy) throws RemoteException, SQLException;
    int deleteEmployeeAccTransaction(int tRef, int eAccRef, String deletedBy) throws RemoteException, SQLException;
    List<Element> getTitles() throws RemoteException;
    List<Element> getGenders() throws RemoteException;
    List<Element> getMaritalStatuses() throws RemoteException;
    List<Element> getEthnicOrigins() throws RemoteException;
    List<Element> getLanguages() throws RemoteException;
    List<Element> getNationalities() throws RemoteException;
    List<Element> getSexualities() throws RemoteException;
    List<Element> getReligions() throws RemoteException;
    List<Element> getPropertyTypes() throws RemoteException;
    List<Element> getPropertySubTypes() throws RemoteException;
    List<Element> getPropElements() throws RemoteException;
    List<Element> getContactTypes() throws RemoteException;
    List<Element> getEndReasons() throws RemoteException;
    List<Element> getRelationships() throws RemoteException;
    List<Element> getJobBenefits() throws RemoteException;
    List<Element> getJobRequirements() throws RemoteException;
    List<Element> getTenancyTypes() throws RemoteException;
    List<OfficeInterface> getOffices() throws RemoteException;
    List<AddressInterface> getAddresses() throws RemoteException;
    List<User> getUsers() throws RemoteException;
    List<LandlordInterface> getLandlords() throws RemoteException;
    List<EmployeeInterface> getEmployees() throws RemoteException;
    List<JobRoleInterface> getJobRoles() throws RemoteException;
    
    boolean titleExists(String code) throws RemoteException;
    boolean genderExists(String code) throws RemoteException;
    boolean maritalStatusExists(String code) throws RemoteException;
    boolean ethnicOriginExists(String code) throws RemoteException;
    boolean languageExists(String code) throws RemoteException;
    boolean nationalityExists(String code) throws RemoteException;
    boolean sexualityExists(String code) throws RemoteException;
    boolean religionExists(String code) throws RemoteException;
    boolean contactTypeExists(String code) throws RemoteException;
    boolean propTypeExists(String code) throws RemoteException;
    boolean propSubTypeExists(String code) throws RemoteException;
    boolean propElementExists(String code) throws RemoteException;
    boolean endReasonExists(String code) throws RemoteException;
    boolean relationshipExists(String code) throws RemoteException;
    boolean officeExists(String code) throws RemoteException;
    boolean jobRoleExists(String code) throws RemoteException;
    boolean isUser(String username, String password) throws RemoteException;
    boolean personEmployeeExists(int pRef) throws RemoteException;
    boolean personLandlordExists(int pRef) throws RemoteException;
    
    
    
    // SEARCH METHODS
    
    
    AddressInterface getAddress(int aRef) throws RemoteException;
    JobRoleInterface getJobRole(String jobRoleCode) throws RemoteException;
    OfficeInterface getOffice(String officeCode) throws RemoteException;
    ApplicationInterface getApplication(int aRef) throws RemoteException;
    PropertyInterface getProperty(int pRef) throws RemoteException;
    EmployeeInterface getEmployee(int eRef) throws RemoteException;
    LandlordInterface getLandlord(int lRef) throws RemoteException;
    TenancyInterface getTenancy(int tRef) throws RemoteException;
    LeaseInterface getLease(int lRef) throws RemoteException;
    ContractInterface getContract(int cRef) throws RemoteException;
    RentAccountInterface getRentAccount(int rAccRef) throws RemoteException;
    LeaseAccountInterface getLeaseAccount(int lAccRef) throws RemoteException;
    EmployeeAccountInterface getEmployeeAccount(int eAccRef) throws RemoteException;
    List<PersonInterface> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException;
    List<AddressInterface> getAddresses(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException;
    List<ApplicationInterface> getApplications(String corrName, Date appStartDate, Date endDate, 
            String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<ApplicationInterface> getPeopleApplications(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException;
    List<ApplicationInterface> getAddressApplications(String buildingNumber, String buildingName, 
            String subStreetNumber, String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException;
    List<ApplicationInterface> getCorrNameApplcations(String name) throws RemoteException;
    ApplicationInterface getInvPartyApplcation(int invPartyRef) throws RemoteException;
    List<EmployeeInterface> getPeopleEmployees(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException;
    List<LandlordInterface> getPeopleLandlords(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException;
    List<PropertyInterface> getProperties(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException;
    List<TenancyInterface> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, 
            Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<TenancyInterface> getApplicationTenancies(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<TenancyInterface> getApplicationTenancies(int appRef) throws RemoteException;
    List<TenancyInterface> getPropertyTenancies(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException;
    List<TenancyInterface> getPropertyTenancies(int propRef) throws RemoteException;
    List<TenancyInterface> getNameTenancies(String name) throws RemoteException;
    List<TenancyInterface> getOfficeTenancies(String office) throws RemoteException;
    List<LeaseInterface> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<LeaseInterface> getPropertyLeases(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException;
    List<LeaseInterface> getPropertyLeases(int propRef) throws RemoteException;
    List<LeaseInterface> getNameLeases(String name) throws RemoteException;
    List<LeaseInterface> getOfficeLeases(String office) throws RemoteException;
    List<LeaseInterface> getLandlordLeases(int landlordRef) throws RemoteException;
    List<LeaseInterface> getLandlordLeases(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode, 
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException;
    List<ContractInterface> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, 
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<ContractInterface> getNameContracts(String name) throws RemoteException;
    List<ContractInterface> getOfficeContracts(String office) throws RemoteException;
    List<ContractInterface> getEmployeeContracts(int ref) throws RemoteException;
    List<ContractInterface> getJobRoleContracts(String code) throws RemoteException;
    List<RentAccountInterface> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<RentAccountInterface> getNameRentAcc(String name) throws RemoteException;
    List<RentAccountInterface> getOfficeRentAcc(String office) throws RemoteException;
    List<RentAccountInterface> getTenanciesRentAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef,
            Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    RentAccountInterface getTenancyRentAcc(int tenancyRef) throws RemoteException;
    List<LeaseAccountInterface> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<LeaseAccountInterface> getNameLeaseAcc(String name) throws RemoteException;
    List<LeaseAccountInterface> getOfficeLeaseAcc(String office) throws RemoteException;
    List<LeaseAccountInterface> getLeasesLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    LeaseAccountInterface getLeaseLeaseAcc(int leaseRef) throws RemoteException;
    List<EmployeeAccountInterface> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    List<EmployeeAccountInterface> getNameEmployeeAcc(String name) throws RemoteException;
    List<EmployeeAccountInterface> getOfficeEmployeeAcc(String office) throws RemoteException;
    List<EmployeeAccountInterface> getContractsEmployeeAccounts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, 
            String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    EmployeeAccountInterface getContractEmployeeAcc(int contractRef) throws RemoteException;
    List<OfficeInterface> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) throws RemoteException;
    
    
    
    // REPORTING METHODS
    
    List<TenancyInterface> getTenanciesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException;
    List<TenancyInterface> getTenanciesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException;
    List<LeaseInterface> getLeasesByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException;
    List<LeaseInterface> getLeasesByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException;
    List<ContractInterface> getContractsByEmployee(int eRef, Date startDate, Date endDate) throws RemoteException;
    List<ContractInterface> getContractsByOffice(String officeCode, Date startDate, Date endDate) throws RemoteException;
    double getRevenueForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException;
    double getExpenditureForOffice(String officeCode, Date startDate, Date endDate) throws RemoteException;
    double getProfitForOffice(String officCode, Date startDate, Date endDate) throws RemoteException;
    double getRevenueOverall(Date startDate, Date endDate) throws RemoteException;
    double getExpenditureOverall(Date startDate, Date endDate) throws RemoteException;
    double getProfitOverall(Date startDate, Date endDate) throws RemoteException;
    
    
    
    public List<RentAccountInterface> getUserRentAccounts(String officeCode) throws RemoteException;
    public List<AgreementInterface> getUserAgreements(String officeCode) throws RemoteException;
    
    
    
    // SERVER METHODS
    
    void   register(Client c) throws RemoteException;
    void unregister(Client c) throws RemoteException;
    boolean isAlive() throws RemoteException;
}
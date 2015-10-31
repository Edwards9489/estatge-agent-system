/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Database {
    
    ///   VARIABLES   ///
    
    // Connection to the database
    private Connection con;

    // List of business data
    private final HashMap<String, Office> offices;

    private final HashMap<Integer, Person> people;

    private final HashMap<Integer, InvolvedParty> involvedParties;
    private final HashMap<Integer, Landlord> landlords;
    private final HashMap<Integer, Employee> employees;

    private final HashMap<Integer, Application> applications;
    private final HashMap<Integer, Property> properties;

    private final HashMap<Integer, Tenancy> tenancies;
    private final HashMap<Integer, Lease> leases;
    private final HashMap<Integer, Contract> contracts;

    private final HashMap<Integer, RentAccount> rentAccounts;
    private final HashMap<Integer, LeaseAccount> leaseAccounts;
    private final HashMap<Integer, EmployeeAccount> employeeAccounts;

    // List of People details
    private final HashMap<String, Element> titles;
    private final HashMap<String, Element> genders;
    private final HashMap<String, Element> maritalStatuses;
    private final HashMap<String, Element> ethnicOrigins;
    private final HashMap<String, Element> languages;
    private final HashMap<String, Element> nationalities;
    private final HashMap<String, Element> sexualities;
    private final HashMap<String, Element> religions;
    
    //List of Contact details
    private final HashMap<String, Element> contactTypes;

    // List of Involved Party details
    private final HashMap<String, Element> endReasons;
    private final HashMap<String, Element> relationships;
    
    // List of Tenancy details
    private final HashMap<String, Element> tenancyTypes;

    // List of contract details
    private final HashMap<String, JobRole> jobRoles;
    private final HashMap<String, Element> jobBenefits;
    private final HashMap<String, Element> jobRequirements;
    private final HashMap<Integer, JobRoleBenefit> jobRoleBenefits;

    // Lists of Property details
    private final HashMap<Integer, Address> addresses;
    private final HashMap<String, Element> propertyTypes; // House, Flat, Bungalow
    private final HashMap<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final HashMap<String, Element> propertyElements;
    
    // List of Account Transactions
    private final HashMap<Integer, Transaction> transactions;
    
    private final HashMap<Integer, AddressUsage> addressUsages;
    private final HashMap<Integer, Contact> contacts;
    private final HashMap<String, UserImpl> users;
    
    
    ///   CONSTRUCTORS ///

    public Database() {
        this.offices = new HashMap<>();

        this.people = new HashMap<>();

        this.involvedParties = new HashMap<>();
        this.landlords = new HashMap<>();
        this.employees = new HashMap<>();

        this.applications = new HashMap<>();
        this.properties = new HashMap<>();

        this.tenancies = new HashMap<>();
        this.leases = new HashMap<>();
        this.contracts = new HashMap<>();

        this.rentAccounts = new HashMap<>();
        this.leaseAccounts = new HashMap<>();
        this.employeeAccounts = new HashMap<>();

        // List of People details
        this.titles = new HashMap<>();
        this.genders = new HashMap<>();
        this.maritalStatuses = new HashMap<>();
        this.ethnicOrigins = new HashMap<>();
        this.languages = new HashMap<>();
        this.nationalities = new HashMap<>();
        this.sexualities = new HashMap<>();
        this.religions = new HashMap<>();
        
        //List of Contact details
        this.contactTypes = new HashMap<>();

        // List of Involved Party details
        this.endReasons = new HashMap<>();
        this.relationships = new HashMap<>();
        
        // List of tenancy details
        this.tenancyTypes = new HashMap<>();

        // List of contract details
        this.jobRoles = new HashMap<>();
        this.jobBenefits = new HashMap<>();
        this.jobRequirements = new HashMap<>();
        this.jobRoleBenefits = new HashMap<>();

        // Lists of Property details
        this.addresses = new HashMap<>();
        this.propertyTypes = new HashMap<>(); // House, Flat, Bungalow
        this.propertySubTypes = new HashMap<>(); // Terraced, Semi-detached
        this.propertyElements = new HashMap<>();
        
        // List of Account Transactions
        this.transactions = new HashMap<>();
        
        this.addressUsages = new HashMap<>();
        this.contacts = new HashMap<>();
        this.users = new HashMap<>();
        
        
        try {
            this.connect();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            this.load();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void connect() throws Exception {
        if (this.con != null) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/msc_properties";
        // jdbc: database type : localhost because it is on my machine : 3306 for port 3306 : mydb for database name
        this.con = DriverManager.getConnection(url, "root", "Toxic9489!999");

        /**
         * To add a library to the project (MySQL Library) : 1. Right-mouse
         * click on the Libraries folder in your project 2. Select Add Library
         * 3. Scroll down and find the Library you need 4. Select it, and click
         * on the Add button
         */
    }

    public void disconnect() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (SQLException ex) {
                System.out.println("Cant close connection");
            }
        }
    }
    
    private void load() throws SQLException {
        if (this.con != null) {
            try {
                
                // NEED TO ADD MODIFICATIONS TO OBJECTS FOR ALL LOAD METHODS;
                
                this.loadTitles();
                this.loadGenders();
                this.loadMaritalStatuses();
                this.loadEthnicOrigins();
                this.loadLanguages();
                this.loadNationalties();
                this.loadSexualities();
                this.loadReligions();
                
                this.loadContactTypes();
                
                this.loadEndReasons();
                this.loadRelationships();
                
                this.loadTenancyTypes();
                
                this.loadJobRequirements();
                this.loadJobBenefits();
                this.loadJobRoles();
                
                this.loadPropertyTypes();
                this.loadPropertySubTypes();
                this.loadPropertyElements();
                
                this.loadAddresses();
                
                this.loadPeople();
                
                this.loadOffices();
                
                this.loadLandlords();
                this.loadEmployees();
                
                this.loadProperties();
                
                this.loadApplications();
                
                this.loadTenancies();
                this.loadRentAccounts();
                
                this.loadLeases();
                this.loadLeaseAccounts();
                
                this.loadContracts();
                this.loadEmployeeAccounts();
            } catch (SQLException ex) {
                System.out.println("Cant load System data");
                ex.printStackTrace();
            }
        } else if(this.con == null) {
            System.out.println("Connection is null");
        }
        //Add all load methods here
    }
    
    private void createElement(String from, Element element) throws SQLException {
        
        String insertSql = "insert into " + from + " (code, description, current, createdBy, createdDate) values (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
            int col = 1;
            insertStat.setString(col++, element.getCode());
            insertStat.setString(col++, element.getDescription());
            insertStat.setBoolean(col++, element.isCurrent());
            insertStat.setString(col++, element.getCreatedBy());
            insertStat.setDate(col++, DateConversion.utilDateToSQLDate(element.getCreatedDate()));
            insertStat.executeUpdate();
            insertStat.close();
        }
    }
    
    private void updateElement(String from, Element element) throws SQLException {
        // use the from String as the from table and the element to update the actual element
        String updateSql = "update " + from + " set description=?, current=? where code=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, element.getDescription());
            updateStat.setBoolean(col++, element.isCurrent());
            updateStat.setString(col++, element.getCode());
            updateStat.executeUpdate();
            updateStat.close();
        }
    }
    
    private List<ElementImpl> loadElements(String from) throws SQLException {
        String sql = "select code, description, createdBy, createdDate from " + from + " order by createdDate";
        List<ElementImpl> elements = new ArrayList<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                String code = results.getString("code");
                String description = results.getString("description");
                boolean current = results.getBoolean("current");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                ElementImpl temp = new ElementImpl(code, description, createdBy, createdDate);
                temp.setCurrent(current);
                
                elements.add(temp);
            }
        }
        return elements;
    }
    
    private void createModifiedBy(String from, ModifiedByInterface modifiedBy, int ref) throws SQLException {
        if(modifiedBy != null) {
            String insertSql = "insert into " + from + " (ref, modifiedBy, modifiedByDate, description) values (?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, modifiedBy.getModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(modifiedBy.getModifiedDate()));
                insertStat.setString(col++, modifiedBy.getDescription());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    private HashMap<Integer, ModifiedByInterface> loadModMap(String from, int reference) throws SQLException {
        String sql = "select ref, modifiedBy, modifiedDate, description from " + from + " order by modifiedDate";
        HashMap<Integer, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, modifiedDate, description);

                    modifiedByMap.put(ref, temp);
                }
            }
        }
        return modifiedByMap;
    }
    
    private void createModifiedBy(String from, ModifiedByInterface modifiedBy, String code) throws SQLException {
        if(modifiedBy != null) {
            String insertSql = "insert into " + from + " (code, modifiedBy, modifiedDate, description) values (?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, code);
                insertStat.setString(col++, modifiedBy.getModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(modifiedBy.getModifiedDate()));
                insertStat.setString(col++, modifiedBy.getDescription());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    private HashMap<String, ModifiedByInterface> loadModMap(String from, String uniqueCode) throws SQLException {
        String sql = "select code, modifiedBy, modifiedDate, description from " + from + " order by modifiedDate";
        HashMap<String, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                String code = results.getString("code");
                if (code.equals(uniqueCode)) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, modifiedDate, description);

                    modifiedByMap.put(code, temp);
                }
            }
        }
        return modifiedByMap;
    }
    
    private void createElementMods(Element element, HashMap<String, ModifiedByInterface> loadedMods) {
        if (element != null && !loadedMods.isEmpty()) {
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(element.getCode().equals(temp.getKey())) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    ElementImpl tempElement = (ElementImpl) element;
                    tempElement.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createContactType(Element conType) throws SQLException {
        if(!this.contactTypeExists(conType.getCode())) {
            this.contactTypes.put(conType.getCode(), conType);
            this.createElement("contactTypes", conType);
        }
    }
    
    public void updateContactType(Element conType) throws SQLException {
        if(this.contactTypeExists(conType.getCode())) {
            this.updateElement("contactTypes", conType);
            this.createModifiedBy("contactTypeModifications", conType.getLastModification(), conType.getCode());
        }
    }
    
    private void loadContactTypes() throws SQLException {
        this.contactTypes.clear();
        List<ElementImpl> loadedContactTypes;
        loadedContactTypes = this.loadElements("contactTypes");
        if (!loadedContactTypes.isEmpty()) {
            for (Element temp : loadedContactTypes) {
                if (temp instanceof Element) {
                    this.contactTypes.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("contactTypeModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getContactType(String code) {
        if(this.contactTypeExists(code)) {
            return this.contactTypes.get(code);
        }
        return null;
    }
    
    public void createPersonContact(Contact contact, int personRef) throws SQLException {
        if(contact != null && !this.contactExists(contact.getContactRef()) && this.personExists(personRef)) {
            contacts.put(contact.getContactRef(), contact);
            String insertSql = "insert into personContacts (contactRef, personRef, contactTypeCode, contactValue, startDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setInt(col++, personRef);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updatePersonContact(int contactRef, int personRef) throws SQLException {
        if(this.contactExists(contactRef) && this.personExists(personRef)) {
            Contact contact = this.getContact(contactRef);
            String updateSql = "update personContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=? where contactRef=? and personRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.setInt(col++, personRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personContactModifications", contact.getLastModification(), personRef);
        }
    }
    
    private void loadPersonContacts(int reference) throws SQLException {
        String sql = "select contactRef, personRef, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate from personContacts order by contactRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.personExists(reference)) {
                    Person person = this.getPerson(reference);
                    int contactRef = results.getInt("contactRef");
                    int personRef = results.getInt("personRef");
                    if (reference == personRef) {
                        Element contactType = this.getContactType(results.getString("contactTypeCode"));
                        String contactValue = results.getString("contactValue");
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");

                        Contact temp = new Contact(contactRef, contactType, contactValue, startDate, createdBy, createdDate);
                        contacts.put(temp.getContactRef(), temp);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createPersonContactMods(temp.getContactRef(), reference, this.loadModMap("personContactModifications", person.getPersonRef()));
                    }
                }
            }
        }
    }
    
    private void createPersonContactMods(int contactRef, int personRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.contactExists(contactRef) && this.personExists(personRef) && !loadedMods.isEmpty()) {
            Contact contact = this.getContact(contactRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(contact.getContactRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Contact tempContact = (Contact) contact;
                    tempContact.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createOfficeContact(Contact contact, String officeCode) throws SQLException {
        if(contact != null && !this.contactExists(contact.getContactRef()) && this.officeExists(officeCode)) {
            contacts.put(contact.getContactRef(), contact);
            String insertSql = "insert into officeContacts (contactRef, officeCode, contactTypeCode, contactValue, startDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setString(col++, officeCode);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateOfficeContact(int contactRef, String officeCode) throws SQLException {
        if(this.contactExists(contactRef) && this.officeExists(officeCode)) {
            Contact contact = this.getContact(contactRef);
            String updateSql = "update officeContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=? where contactRef=? and officeCode=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.setString(col++, officeCode);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("officeContactModifications", contact.getLastModification(), officeCode);
        }
    }
    
    private void loadOfficeContacts(String code) throws SQLException {
        String sql = "select contactRef, officeCode, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate from officeContacts order by contactRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.officeExists(code)) {
                    Office office = this.getOffice(code);
                    int contactRef = results.getInt("contactRef");
                    String officeCode = results.getString("officeCode");
                    if (code.equals(officeCode)) {
                        Element contactType = this.getContactType(results.getString("contactTypeCode"));
                        String contactValue = results.getString("contactValue");
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");

                        Contact temp = new Contact(contactRef, contactType, contactValue, startDate, createdBy, createdDate);
                        contacts.put(temp.getContactRef(), temp);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createOfficeContactMods(temp.getContactRef(), code, this.loadModMap("officeContactModifications", office.getOfficeCode()));
                    }
                }
            }
        }
    }
    
    private void createOfficeContactMods(int contactRef, String code, HashMap<String, ModifiedByInterface> loadedMods) {
        if (this.contactExists(contactRef) && this.officeExists(code) && !loadedMods.isEmpty()) {
            Contact contact = this.getContact(contactRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(contact.getContactRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Contact tempContact = (Contact) contact;
                    tempContact.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPersonAddressUsage(AddressUsage address, int personRef) throws SQLException {
        if(address != null && this.addressUsageExists(address.getAddressUsageRef()) && this.personExists(personRef)) {
            addressUsages.put(address.getAddressUsageRef(), address);
            String insertSql = "insert into personAddressess (addressUsageRef, addressRef, personRef, startDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, personRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updatePersonAddressUsage(int addressRef, int personRef) throws SQLException {
        if(this.addressUsageExists(addressRef) && this.personExists(personRef)) {
            AddressUsage address = this.getAddressUsage(addressRef);
            String updateSql = "update peopleAddresses set addressRef=?, startDate=?, endDate=? where addressUsageRef=? and personRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.setInt(col++, personRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personAddressModifications", address.getLastModification(), address.getAddressUsageRef());
        }
    }
    
    private void loadPersonAddresses(int reference) throws SQLException {
        String sql = "select addressUsageRef, addressRef, personRef, startDate, endDate, createdBy, createdDate from personAddresses order by addressUsageRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while (results.next()) {
                if (this.personExists(reference)) {
                    Person person = this.getPerson(reference);
                    int addressUsageRef = results.getInt("addressUsageRef");
                    
                    AddressInterface address;
                    if(this.addressExists(results.getInt("addressRef"))) {
                        address = this.getAddress(results.getInt("addressRef"));
                    } else {
                        address = this.getAddress(results.getInt("addressRef")); // CREATE ERROR ADDRESS
                    }
                    int personRef = results.getInt("personRef");
                    if (reference == personRef) {
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");

                        AddressUsage temp = new AddressUsage(addressUsageRef, address, startDate, createdBy, createdDate);
                        addressUsages.put(temp.getAddressUsageRef(), temp);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createPersonAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("personAddressesModifications", person.getPersonRef()));
                    }
                }
            }
        }
    }
    
    private void createPersonAddressMods(int addressRef, int personRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressUsageExists(addressRef) && this.personExists(personRef) && !loadedMods.isEmpty()) {
            AddressUsage address = this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(address.getAddressUsageRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    AddressUsage tempAddress = (AddressUsage) address;
                    tempAddress.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException {
        if(address != null && !this.addressUsageExists(address.getAddressUsageRef()) && this.applicationExists(appRef)) {
            addressUsages.put(address.getAddressUsageRef(), address);
            String insertSql = "insert into applicationAddressess (addressUsageRef, addressRef, applicationRef, startDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, appRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateApplicationAddressUsage(int addressRef, int appRef) throws SQLException {
        if(this.addressUsageExists(addressRef) && this.applicationExists(appRef)) {
            AddressUsage address = this.getAddressUsage(addressRef);
            String updateSql = "update applicationAddresses set addressRef=?, startDate=?, endDate=? where addressUsageRef=? and applicationRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.setInt(col++, appRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("applicationAddressModifications", address.getLastModification(), address.getAddressUsageRef());
        }
    }
    
    private void loadApplicationAddresses(int reference) throws SQLException {
        String sql = "select addressUsageRef, addressRef, applicationRef, startDate, endDate, createdBy, createdDate from applicationAddresses order by addressUsageRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.applicationExists(reference)) {
                    Application application = this.getApplication(reference);
                    int addressUsageRef = results.getInt("addressUsageRef");
                    
                    AddressInterface address;
                    if(this.addressExists(results.getInt("addressRef"))) {
                        address = this.getAddress(results.getInt("addressRef"));
                    } else {
                        address = this.getAddress(results.getInt("addressRef")); // CREATE ERROR ADDRESS
                    }
                    int applicationRef = results.getInt("applicationRef");
                    if (reference == applicationRef) {
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");

                        AddressUsage temp = new AddressUsage(addressUsageRef, address, startDate, createdBy, createdDate);
                        addressUsages.put(temp.getAddressUsageRef(), temp);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createApplicationAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("applicationAddressesModifications", application.getApplicationRef()));
                    }
                }
            }
        }
    }
    
    private void createApplicationAddressMods(int addressRef, int applicationRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressUsageExists(addressRef) && this.applicationExists(applicationRef) && !loadedMods.isEmpty()) {
            AddressUsage address = this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(address.getAddressUsageRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    AddressUsage tempAddress = (AddressUsage) address;
                    tempAddress.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createTitle(Element title) throws SQLException {
        if(!this.titleExists(title.getCode())) {
            this.titles.put(title.getCode(), title);
            this.createElement("titles", title);
        }
    }
    
    public void updateTitle(String titleCode) throws SQLException {
        if(this.titleExists(titleCode)) {
            Element title = this.getTitle(titleCode);
            this.updateElement("titles", title);
            this.createModifiedBy("titleModifications", title.getLastModification(), title.getCode());
        }
    }
    
    private void loadTitles() throws SQLException {
        this.titles.clear();
        List<ElementImpl> loadedTitles;
        loadedTitles = this.loadElements("titles");
        if (!loadedTitles.isEmpty()) {
            for (Element temp : loadedTitles) {
                if (temp instanceof Element) {
                    this.titles.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("titleModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getTitle(String code) {
        if(this.titleExists(code)) {
            return this.titles.get(code);
        }
        return null;
    }
    
    public void createGender(Element gender) throws SQLException {
        if(!this.genderExists(gender.getCode())) {
            this.genders.put(gender.getCode(), gender);
            this.createElement("genders", gender);
        }
    }
    
    public void updateGender(String genderCode) throws SQLException {
        if(this.genderExists(genderCode)) {
            Element gender = this.getGender(genderCode);
            this.updateElement("genders", gender);
            this.createModifiedBy("genderModifications", gender.getLastModification(), gender.getCode());
        }
    }
    
    private void loadGenders() throws SQLException {
        this.genders.clear();
        List<ElementImpl> loadedGenders;
        loadedGenders = this.loadElements("genders");
        if (!loadedGenders.isEmpty()) {
            for (Element temp : loadedGenders) {
                if (temp instanceof Element) {
                    this.genders.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("genderModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getGender(String code) {
        if(this.genderExists(code)) {
            return this.genders.get(code);
        }
        return null;
    }
    
    public void createMaritalStatus(Element status) throws SQLException {
        if(!this.maritalStatusExists(status.getCode())) {
            maritalStatuses.put(status.getCode(), status);
            this.createElement("maritalStatuses", status);
        }
    }
    
    public void updateMaritalStatus(String statusCode) throws SQLException {
        if(this.maritalStatusExists(statusCode)) {
            Element status = this.getMaritalStatus(statusCode);
            this.updateElement("maritalStatuses", status);
            this.createModifiedBy("maritalStatusModifications", status.getLastModification(), status.getCode());
        }
    }
    
    private void loadMaritalStatuses() throws SQLException {
        this.maritalStatuses.clear();
        List<ElementImpl> loadedStatuses;
        loadedStatuses = this.loadElements("maritalStatuses");
        if (!loadedStatuses.isEmpty()) {
            for (Element temp : loadedStatuses) {
                if (temp instanceof Element) {
                    this.maritalStatuses.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("maritalStatusModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getMaritalStatus(String code) {
        if(this.maritalStatusExists(code)) {
            return this.maritalStatuses.get(code);
        }
        return null;
    }
    
    public void createEthnicOrigin(Element ethnicOrigin) throws SQLException {
        if(!this.ethnicOriginExists(ethnicOrigin.getCode())) {
            this.ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
            this.createElement("ethnicOrigins", ethnicOrigin);
        }
    }
    
    public void updateEthnicOrigin(String originCode) throws SQLException {
        if(this.ethnicOriginExists(originCode)) {
            Element origin = this.getEthnicOrigin(originCode);
            this.updateElement("ethnicOrigins", origin);
            this.createModifiedBy("ethnicOriginModifications", origin.getLastModification(), origin.getCode());
        }
    }
    
    private void loadEthnicOrigins() throws SQLException {
        this.ethnicOrigins.clear();
        List<ElementImpl> loadedEthnicOrigins;
        loadedEthnicOrigins = this.loadElements("ethnicOrigins");
        if (!loadedEthnicOrigins.isEmpty()) {
            for (Element temp : loadedEthnicOrigins) {
                if (temp instanceof Element) {
                    this.ethnicOrigins.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("ethnicOriginModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getEthnicOrigin(String code) {
        if(this.ethnicOriginExists(code)) {
            return this.ethnicOrigins.get(code);
        }
        return null;
    }
    
    public void createLanguage(Element language) throws SQLException {
        if(!this.languageExists(language.getCode())) {
            this.languages.put(language.getCode(), language);
            this.createElement("languages", language);
        }
    }
    
    public void updateLanguage(String languageCode) throws SQLException {
        if(this.languageExists(languageCode)) {
            Element language = this.getLanguage(languageCode);
            this.updateElement("languages", language);
            this.createModifiedBy("languageModifications", language.getLastModification(), language.getCode());
        }
    }
    
    private void loadLanguages() throws SQLException {
        this.languages.clear();
        List<ElementImpl> loadedLanguages;
        loadedLanguages = this.loadElements("languages");
        if (!loadedLanguages.isEmpty()) {
            for (Element temp : loadedLanguages) {
                if (temp instanceof Element) {
                    this.languages.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("languageModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getLanguage(String code) {
        if(this.languageExists(code)) {
            return this.languages.get(code);
        }
        return null;
    }
    
    public void createNationality(Element nationality) throws SQLException {
        if(!this.nationalityExists(nationality.getCode())) {
            this.nationalities.put(nationality.getCode(), nationality);
            this.createElement("nationalities", nationality);
        }
    }
    
    public void updateNationality(String nationalityCode) throws SQLException {
        if(this.nationalityExists(nationalityCode)) {
            Element nationality = this.getNationality(nationalityCode);
            this.updateElement("nationalities", nationality);
            this.createModifiedBy("nationalityModifications", nationality.getLastModification(), nationality.getCode());
        }
    }
    
    private void loadNationalties() throws SQLException {
        this.nationalities.clear();
        List<ElementImpl> loadedNationalities;
        loadedNationalities = this.loadElements("nationalities");
        if (!loadedNationalities.isEmpty()) {
            for (Element temp : loadedNationalities) {
                if (temp instanceof Element) {
                    this.nationalities.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("nationalityModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getNationality(String code) {
        if(this.nationalityExists(code)) {
            return this.nationalities.get(code);
        }
        return null;
    }
    
    public void createSexuality(Element sex) throws SQLException {
        if(!this.sexualityExists(sex.getCode())) {
            this.sexualities.put(sex.getCode(), sex);
            this.createElement("sexualities", sex);
        }
    }
    
    public void updateSexuality(String sexCode) throws SQLException {
        if(this.sexualityExists(sexCode)) {
            Element sex = this.getSexuality(sexCode);
            this.updateElement("sexualities", sex);
            this.createModifiedBy("sexualityModifications", sex.getLastModification(), sex.getCode());
        }
    }
    
    private void loadSexualities() throws SQLException {
        this.sexualities.clear();
        List<ElementImpl> loadedSexualities;
        loadedSexualities = this.loadElements("sexualities");
        if (!loadedSexualities.isEmpty()) {
            for (Element temp : loadedSexualities) {
                if (temp instanceof Element) {
                    this.sexualities.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("sexualityModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getSexuality(String code) {
        if(this.sexualityExists(code)) {
            return this.sexualities.get(code);
        }
        return null;
    }
    
    public void createReligion(Element religion) throws SQLException {
        if(!this.religionExists(religion.getCode())) {
            this.religions.put(religion.getCode(), religion);
            this.createElement("religions", religion);
        }
    }
    
    public void updateReligion(String religionCode) throws SQLException {
        if(this.religionExists(religionCode)) {
            Element religion = this.getReligion(religionCode);
            this.updateElement("religions", religion);
            this.createModifiedBy("religionModifications", religion.getLastModification(), religion.getCode());
        }
    }
    
    private void loadReligions() throws SQLException {
        this.religions.clear();
        List<ElementImpl> loadedReligions;
        loadedReligions = this.loadElements("religions");
        if (!loadedReligions.isEmpty()) {
            for (Element temp : loadedReligions) {
                if (temp instanceof Element) {
                    this.religions.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("religionModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getReligion(String code) {
        if(this.religionExists(code)) {
            return this.religions.get(code);
        }
        return null;
    }
    
    public void createAddress(Address address) throws SQLException {
        if(!this.addressExists(address.getAddressRef())) {
            this.addresses.put(address.getAddressRef(), address);
            String insertSql = "insert into addresses (addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, "
                    + "streetNumber, street, area, town, country, postcode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressRef());
                insertStat.setString(col++, address.getBuildingNumber());
                insertStat.setString(col++, address.getBuildingName());
                insertStat.setString(col++, address.getSubStreetNumber());
                insertStat.setString(col++, address.getSubStreet());
                insertStat.setString(col++, address.getStreetNumber());
                insertStat.setString(col++, address.getStreet());
                insertStat.setString(col++, address.getArea());
                insertStat.setString(col++, address.getTown());
                insertStat.setString(col++, address.getCountry());
                insertStat.setString(col++, address.getPostcode());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateAddress(int addressRef) throws SQLException {
        if(this.addressExists(addressRef)) {
            Address address = this.getAddress(addressRef);
            String updateSql = "update addresses set buildingNumber=?, buildingName=?, subStreetNumber=?, subStreet=?, "
                    + "streetNumber=?, street=?, area=?, town=?, country=?, postcode=? where addressRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, address.getBuildingNumber());
                updateStat.setString(col++, address.getBuildingName());
                updateStat.setString(col++, address.getSubStreetNumber());
                updateStat.setString(col++, address.getSubStreet());
                updateStat.setString(col++, address.getStreetNumber());
                updateStat.setString(col++, address.getStreet());
                updateStat.setString(col++, address.getArea());
                updateStat.setString(col++, address.getTown());
                updateStat.setString(col++, address.getCountry());
                updateStat.setString(col++, address.getPostcode());
                updateStat.setInt(col++, address.getAddressRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("addressModifications", address.getLastModification(), address.getAddressRef());
        }
    }
    
    private void loadAddresses() throws SQLException {
        String sql = "select addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, "
                    + "streetNumber, street, area, town, country, postcode, createdBy, createdDate from addresses order by addressRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int addressRef = results.getInt("addressRef");
                String buildingNumber = results.getString("buildingNumber");
                String buildingName = results.getString("buildingName");
                String subStreetNumber = results.getString("subStreetNumber");
                String subStreet = results.getString("subStreet");
                String streetNumber = results.getString("streetNumber");
                String street = results.getString("street");
                String area = results.getString("area");
                String town = results.getString("town");
                String country = results.getString("country");
                String postcode = results.getString("postcode");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");

                Address temp = new Address(addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
                
                this.addresses.put(temp.getAddressRef(), temp);
                this.createAddressMods(temp.getAddressRef(), this.loadModMap("addressModifications", temp.getAddressRef()));
            }
        }
    }
    
    private void createAddressMods(int addressRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressExists(addressRef) && !loadedMods.isEmpty()) {
            Address address = this.getAddress(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(address.getAddressRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Address tempAddress = (Address) address;
                    tempAddress.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Address getAddress(int addressRef) {
        if(this.addressExists(addressRef)) {
            return this.addresses.get(addressRef);
        }
        return null;
    }
    
    public void createProperty(Property property) throws SQLException {
        if(!this.propertyExists(property.getPropRef())) {
            properties.put(property.getPropRef(), property);
            String insertSql = "insert into properties (propertyRef, addressRef, acquiredDate, leaseEndDate, propTypeCode, "
                    + "propSubTypeCode, propStatus, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, property.getPropRef());
                insertStat.setInt(col++, property.getAddress().getAddressRef());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(property.getAcquiredDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(property.getLeaseEndDate()));
                insertStat.setString(col++, property.getPropType().getCode());
                insertStat.setString(col++, property.getPropSubType().getCode());
                insertStat.setString(col++, property.getPropStatus());
                insertStat.setString(col++, property.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(property.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.createPropertyElementValues(property.getPropRef(), property.getPropertyElements());
        }
    }
    
    public void updateProperty(int propRef) throws SQLException {
        if(this.propertyExists(propRef)) {
            Property property = this.getProperty(propRef);
            String updateSql = "update properties set addressRef=?, acquiredDate=?, leaseEndDate=?, "
                    + "propTypeCode=?, propSubTypeCode=?, propStatus=? where propertyRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setInt(col++, property.getAddress().getAddressRef());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(property.getAcquiredDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(property.getLeaseEndDate()));
                updateStat.setString(col++, property.getPropType().getCode());
                updateStat.setString(col++, property.getPropSubType().getCode());
                updateStat.setString(col++, property.getPropStatus());
                updateStat.setInt(col++, property.getPropRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("propertyModifications", property.getLastModification(), property.getPropRef());
        }
    }
    
    private void loadProperties() throws SQLException {
        String sql = "select propertyRef, addressRef, acquiredDate, leaseEndDate, propTypeCode, "
                    + "propSubTypeCode, propStatus, createdBy, createdDate from properties order by propertyRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int propertyRef = results.getInt("propertyRef");
                int addressRef = results.getInt("addressRef");
                Address address;
                if(this.addressExists(addressRef)) {
                    address = this.getAddress(addressRef);
                } else {
                    address = this.getAddress(addressRef); // CREATE ERROR ADDRESS
                }
                Date acquiredDate = results.getDate("acquiredDate");
                Date leaseEndDate = results.getDate("leaseEndDate");
                String propTypeCode = results.getString("propTypeCode");
                Element propType;
                if(this.propTypeExists(propTypeCode)) {
                    propType = this.getPropertyType(propTypeCode);
                } else {
                    propType = this.getPropertyType(propTypeCode); // CREATE ERROR PROP TYPE
                }
                String propSubTypeCode = results.getString("propSubTypeCode");
                Element propSubType;
                if(this.propSubTypeExists(propSubTypeCode)) {
                    propSubType = this.getPropertySubType(propSubTypeCode);
                } else {
                    propSubType = this.getPropertySubType(propSubTypeCode); // CREATE ERROR PROP TYPE
                }
                String propStatus = results.getString("propStatus");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                Property temp = new Property(propertyRef, address, acquiredDate, propType, propSubType, createdBy, createdDate);
                this.loadPropertyElementValues(temp.getPropRef());
                this.properties.put(temp.getPropRef(), temp);
                this.createPropertyMods(temp.getPropRef(), this.loadModMap("propertyModifications", temp.getPropRef()));
                temp.setLeaseEndDate(leaseEndDate, null);
                temp.setPropStatus(propStatus, null);
            }
        }
    }
    
    private void createPropertyMods(int propRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.propertyExists(propRef) && !loadadMods.isEmpty()) {
            Property property = this.getProperty(propRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(property.getPropRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    property.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Property getProperty(int propRef) {
        if (properties.containsKey(propRef)) {
            return properties.get(propRef);
        }
        return null;
    }
    
    public void createPropertyType(Element type) throws SQLException {
        if(!this.propTypeExists(type.getCode())) {
            this.propertyTypes.put(type.getCode(), type);
            this.createElement("propertyTypes", type);
        }
    }
    
    public void updatePropertyType(String typeCode) throws SQLException {
        if(this.propTypeExists(typeCode)) {
            Element type = this.getPropertyType(typeCode);
            this.updateElement("propertyTypes", type);
            this.createModifiedBy("propertyTypeModifications", type.getLastModification(), type.getCode());
        }
    }
    
    private void loadPropertyTypes() throws SQLException {
        this.propertyTypes.clear();
        List<ElementImpl> loadedPropTypes;
        loadedPropTypes = this.loadElements("propertyTypes");
        if (!loadedPropTypes.isEmpty()) {
            for (Element temp : loadedPropTypes) {
                if (temp instanceof Element) {
                    this.propertyTypes.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("propertyTypeModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getPropertyType(String code) {
        if(this.propTypeExists(code)) {
            return this.propertyTypes.get(code);
        }
        return null;
    }
    
    public void createPropertySubType(Element type) throws SQLException {
        if(!this.propSubTypeExists(type.getCode())) {
            this.propertySubTypes.put(type.getCode(), type);
            this.createElement("propertySubTypes", type);
        }
    }
    
    public void updatePropertySubType(String typeCode) throws SQLException {
        Element type = this.getPropertySubType(typeCode);
        if(this.propSubTypeExists(type.getCode())) {
            this.updateElement("propertySubTypes", type);
            this.createModifiedBy("propertySubTypeModifications", type.getLastModification(), type.getCode());
        }
    }
    
    private void loadPropertySubTypes() throws SQLException {
        this.propertySubTypes.clear();
        List<ElementImpl> loadedPropSubTypes;
        loadedPropSubTypes = this.loadElements("propertySubTypes");
        if (!loadedPropSubTypes.isEmpty()) {
            for (Element temp : loadedPropSubTypes) {
                if (temp instanceof Element) {
                    this.propertySubTypes.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("propertySubTypeModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getPropertySubType(String code) {
        if(this.propSubTypeExists(code)) {
            return this.propertySubTypes.get(code);
        }
        return null;
    }
    
    private void createPropertyElementValues(int propertyRef, List<PropertyElement> propertyElements) throws SQLException {
        if (!propertyElements.isEmpty() && this.propertyExists(propertyRef)) {
            for (PropertyElement temp : propertyElements) {
                this.createPropertyElementValue(propertyRef, temp);
            }
        }
    }
    
    public void createPropertyElementValue(int propertyRef, PropertyElement propertyElement) throws SQLException {
        if (propertyElement != null && this.propertyExists(propertyRef) && this.propElementExists(propertyElement.getElement().getCode())) {
            String insertSql = "";
            if (propertyElement.isCharge()) {
                insertSql = "insert into propertyElementValues (propertyElementRef, propertyRef, elementCode, doubleValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            } else if (!propertyElement.isCharge()) {
                insertSql = "insert into propertyElementValues (propertyElementRef, propertyRef, elementCode, stringValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, propertyElement.getPropertyElementRef());
                insertStat.setInt(col++, propertyRef);
                insertStat.setString(col++, propertyElement.getElementCode());
                if (propertyElement.isCharge()) {
                    insertStat.setDouble(col++, propertyElement.getDoubleValue());
                } else if (!propertyElement.isCharge()) {
                    insertStat.setString(col++, propertyElement.getStringValue());
                }
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getEndDate()));
                insertStat.setString(col++, propertyElement.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updatePropertyElementValue(int propertyRef, PropertyElement propertyElement) throws SQLException {
        if (propertyElement != null && this.propertyExists(propertyRef) && this.propElementExists(propertyElement.getElement().getCode())) {
            String updateSql = "";
            if (propertyElement.isCharge()) {
                updateSql = "update propertyElementValues set doubleValue=?, startDate=?, endDate=? where propertyElementRef=? and propertyRef=? and elementCode=?";
            } else if (!propertyElement.isCharge()) {
                updateSql = "update propertyElementValues set stringValue=?, startDate=?, endDate=? where propertyElementRef=? and propertyRef=? and elementCode=?";
            }
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                if (propertyElement.isCharge()) {
                    updateStat.setDouble(col++, propertyElement.getDoubleValue());
                } else if (!propertyElement.isCharge()) {
                    updateStat.setString(col++, propertyElement.getStringValue());
                }
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getEndDate()));
                updateStat.setInt(col++, propertyElement.getPropertyElementRef());
                updateStat.setInt(col++, propertyRef);
                updateStat.setString(col++, propertyElement.getElementCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("propertyElementValueModifications", propertyElement.getLastModification(), propertyElement.getPropertyElementRef()); // 
        }
    }

    private void loadPropertyElementValues(int propRef) throws SQLException {
        if (this.propertyExists(propRef)) {
            Property property = this.getProperty(propRef);
            String sql = "select propertyElementRef, propertyRef, elementCode, doubleValue, startDate, endDate, createdBy, createdDate from propertyElementValues order by createdDate";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);

                while (results.next()) {
                    int propertyElementRef = results.getInt("propertyElementRef");
                    int propertyRef = results.getInt("propertyRef");
                    if (this.propertyExists(propRef) && propertyRef == propRef) {
                        String elementCode = results.getString("elementCode");
                        Element element;
                        if (this.propElementExists(elementCode)) {
                            element = this.getPropElement(elementCode);
                            Date startDate = results.getDate("startDate");
                            Date endDate = results.getDate("endDate");
                            String stringValue = results.getString("stringValue");
                            double doubleValue = results.getDouble("doubleValue");
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            PropertyElement temp = new PropertyElement(propertyElementRef, element, startDate, stringValue != null, stringValue, doubleValue, createdBy, createdDate);
                            temp.updatePropertyElement(startDate, stringValue, doubleValue, stringValue != null, null);
                            if (endDate != null) {
                                temp.setEndDate(endDate, null);
                            }
                            this.createPropElementMods(temp, this.loadModMap("PropertyElementValueModifications", temp.getPropertyElementRef()));
                            property.createPropertyElement(temp, null);
                        }
                    }
                }
            }
        }
    }
    
    private void createPropElementMods(PropertyElement element, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.propElementExists(element.getElementCode()) && !loadadMods.isEmpty()) {
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(element.getPropertyElementRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    element.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPropElement(Element element) throws SQLException {
        if(!this.propElementExists(element.getCode())) {
            this.propertyElements.put(element.getCode(), element);
            this.createElement("propertyElements", element);
        }
    }
    
    public void updatePropertyElement(String elementCode) throws SQLException {
        Element element = this.getPropElement(elementCode);
        if(this.propElementExists(element.getCode())) {
            this.updateElement("propertyElements", element);
            this.createModifiedBy("propertyElementModifications", element.getLastModification(), element.getCode());
        }
    }
    
    private void loadPropertyElements() throws SQLException {
        this.propertyElements.clear();
        List<ElementImpl> loadedPropertyElements;
        loadedPropertyElements = this.loadElements("propertyElements");
        if (!loadedPropertyElements.isEmpty()) {
            for (Element temp : loadedPropertyElements) {
                if (temp instanceof Element) {
                    this.propertyElements.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("propertyElementModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getPropElement(String code) {
        if(this.propElementExists(code)) {
            return this.propertyElements.get(code);
        }
        return null;
    }
    
    public void createPerson(Person person) throws SQLException {
        if(!this.personExists(person.getPersonRef())) {
            people.put(person.getPersonRef(), person);
            String insertSql = "insert into people (personRef, titleCode, forename, middleNames, surname, dateOfBirth, "
                    + "nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, "
                    + "sexualityCode, religionCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, person.getPersonRef());
                insertStat.setString(col++, person.getTitle().getCode());
                insertStat.setString(col++, person.getForename());
                insertStat.setString(col++, person.getMiddleNames());
                insertStat.setString(col++, person.getSurname());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(person.getDateOfBirth()));
                insertStat.setString(col++, person.getNI());
                insertStat.setString(col++, person.getGender().getCode());
                insertStat.setString(col++, person.getMaritalStatus().getCode());
                insertStat.setString(col++, person.getEthnicOrigin().getCode());
                insertStat.setString(col++, person.getLanguage().getCode());
                insertStat.setString(col++, person.getNationality().getCode());
                insertStat.setString(col++, person.getSexuality().getCode());
                insertStat.setString(col++, person.getReligion().getCode());
                insertStat.setString(col++, person.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(person.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updatePerson(int personRef) throws SQLException {
        if(this.personExists(personRef)) {
            Person person = this.getPerson(personRef);
            String updateSql = "update people set titleCode=?, forename=?, middleNames=?, surname=?, dateOfBirth=?, nationalInsurance=?, "
                    + "genderCode=?, maritalStatusCode=?, ethnicOriginCode=?, languageCode=?, nationalityCode=?, sexualityCode=?, "
                    + "religionCode=?, where personRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, person.getTitle().getCode());
                updateStat.setString(col++, person.getForename());
                updateStat.setString(col++, person.getMiddleNames());
                updateStat.setString(col++, person.getSurname());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(person.getDateOfBirth()));
                updateStat.setString(col++, person.getNI());
                updateStat.setString(col++, person.getGender().getCode());
                updateStat.setString(col++, person.getMaritalStatus().getCode());
                updateStat.setString(col++, person.getEthnicOrigin().getCode());
                updateStat.setString(col++, person.getLanguage().getCode());
                updateStat.setString(col++, person.getNationality().getCode());
                updateStat.setString(col++, person.getSexuality().getCode());
                updateStat.setString(col++, person.getReligion().getCode());
                updateStat.setInt(col++, person.getPersonRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("personModifications", person.getLastModification(), person.getPersonRef());
        }
    }
    
    private void loadPeople() throws SQLException {
        this.people.clear();
        String sql = "select personRef, titleCode, forename, middleNames, surname, dateOfBirth, "
                + "nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, "
                + "sexualityCode, religionCode, createdBy, createdDate from people order by personRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int personRef = results.getInt("personRef");
                
                Element title;
                if(this.titleExists(results.getString("titleCode"))) {
                    title = this.getTitle(results.getString("titleCode"));
                } else {
                    title = this.getTitle(results.getString("titleCode")); // Create ERROR Code
                }
                
                String forename = results.getString("forename");
                String middleNames = results.getString("middleNames");
                String surname = results.getString("surname");
                Date dateOfBirth = results.getDate("dateOfBirth");
                String nationalInsurance = results.getString("nationalInsurance");
                
                Element gender;
                if(this.genderExists(results.getString("genderCode"))) {
                    gender = this.getGender(results.getString("genderCode"));
                } else {
                    gender = this.getGender(results.getString("genderCode")); // Create ERROR Code
                }
                
                Element maritalStatus;
                if(this.maritalStatusExists(results.getString("maritalStatusCode"))) {
                    maritalStatus = this.getTitle(results.getString("maritalStatusCode"));
                } else {
                    maritalStatus = this.getTitle(results.getString("maritalStatusCode")); // Create ERROR Code
                }
                
                Element ethnicOrigin;
                if(this.ethnicOriginExists(results.getString("ethnicOriginCodec"))) {
                    ethnicOrigin = this.getEthnicOrigin(results.getString("ethnicOriginCode"));
                } else {
                    ethnicOrigin = this.getEthnicOrigin(results.getString("ethnicOriginCode")); // Create ERROR Code
                }
                
                Element language;
                if(this.languageExists(results.getString("languageCode"))) {
                    language = this.getLanguage(results.getString("languageCode"));
                } else {
                    language = this.getLanguage(results.getString("languageCode")); // Create ERROR Code
                }
                
                Element nationality;
                if(this.nationalityExists(results.getString("nationalityCode"))) {
                    nationality = this.getNationality(results.getString("nationalityCode"));
                } else {
                    nationality = this.getNationality(results.getString("nationalityCode")); // Create ERROR Code
                }
                
                Element sexuality;
                if(this.sexualityExists(results.getString("sexualityCode"))) {
                    sexuality = this.getSexuality(results.getString("sexualityCode"));
                } else {
                    sexuality = this.getSexuality(results.getString("sexualityCode")); // Create ERROR Code
                }
                
                Element religion;
                if(this.religionExists(results.getString("religionCode"))) {
                    religion = this.getReligion(results.getString("religionCode"));
                } else {
                    religion = this.getReligion(results.getString("religionCode")); // Create ERROR Code
                }
                
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");

                Person temp = new Person(personRef, title, forename, middleNames, surname, dateOfBirth, nationalInsurance, gender, maritalStatus, ethnicOrigin, language, nationality, sexuality, religion, null, createdBy, createdDate);
                
                this.people.put(temp.getPersonRef(), temp);
                this.createPeopleMods(temp.getPersonRef(), this.loadModMap("peopleModifications", temp.getPersonRef()));
                this.loadPersonContacts(temp.getPersonRef());
                this.loadPersonAddresses(temp.getPersonRef());
            }
        }
    }
    
    private void createPeopleMods(int personRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.personExists(personRef) && !loadedMods.isEmpty()) {
            Person person = this.getPerson(personRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(person.getPersonRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Person tempPerson = (Person) person;
                    tempPerson.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Person getPerson(int personRef) {
        if(this.personExists(personRef)) {
            return this.people.get(personRef);
        }
        return null;
    }
    
    public void createInvolvedParty(InvolvedParty invParty) throws SQLException {
        if(!this.invPartyExists(invParty.getInvolvedPartyRef())) {
            involvedParties.put(invParty.getInvolvedPartyRef(), invParty);
            String insertSql = "insert into involvedParties (invPartyRef, appRef, personRef, jointApplicantInd, mainApplicantInd, "
                    + "startDate, relationshipCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, invParty.getInvolvedPartyRef());
                insertStat.setInt(col++, invParty.getApplicationRef());
                insertStat.setInt(col++, invParty.getPersonRef());
                insertStat.setBoolean(col++, invParty.isJointInd());
                insertStat.setBoolean(col++, invParty.isMainInd());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getStartDate()));
                insertStat.setString(col++, invParty.getRelationship().getCode());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }

    public void updateInvolvedParty(int invPartyRef) throws SQLException {
        if (this.invPartyExists(invPartyRef)) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            String updateSql = "update involvedParties set jointApplicantInd=?, mainApplicantInd=?, startDate=?, "
                    + "endDate=?, endReasonCode=?, relationshipCode=?, where involvedPartyRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setBoolean(col++, invParty.isJointInd());
                updateStat.setBoolean(col++, invParty.isMainInd());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getEndDate()));
                updateStat.setString(col++, invParty.getEndReason().getCode());
                updateStat.setString(col++, invParty.getRelationship().getCode());
                updateStat.setInt(col++, invParty.getInvolvedPartyRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("involvedPartyModifications", invParty.getLastModification(), invParty.getInvolvedPartyRef());
        }
    }
    
    private void loadInvolvedParties(int reference) throws SQLException {
        this.involvedParties.clear();
        String sql = "select invPartyRef, appRef, personRef jointApplicantInd, mainApplicantInd, startDate, endDate, endReasonCode, "
                + "relationshipCode, createdBy, createdDate from involvedParties order by invPartyRef";

        try (Statement selectInvStat = con.createStatement()) {
            ResultSet results = selectInvStat.executeQuery(sql);
            while (results.next()) {
                int appRef = results.getInt("appRef");
                if (this.applicationExists(reference) && appRef == reference) {
                    Application application = this.getApplication(reference);
                    int invPartyRef = results.getInt("invPartyRef");

                    if (this.personExists(results.getInt("personRef")));
                    {
                        Person person = this.getPerson(results.getInt("personRef"));
                        boolean jointApplicantInd = results.getBoolean("jointApplicantInd");
                        boolean mainApplicantInd = results.getBoolean("mainApplicantInd");
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");

                        String endReasonCode = results.getString("endReasonCode");
                        Element endReason = null;
                        if (endReasonCode != null) {
                            if (this.endReasonExists(endReasonCode)) {
                                endReason = this.getEndReason(endReasonCode);
                            } else {
                                endReason = this.getEndReason(endReasonCode); // CREATE ERROR CODE
                            }
                        }

                        Element relationship;
                        if (this.relationshipExists(results.getString("relationshipCode"))) {
                            relationship = this.getRelationship(results.getString("relationshipCode"));
                        } else {
                            relationship = this.getRelationship(results.getString("relationshipCode")); // CREATE ERROR CODE
                        }

                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");

                        InvolvedParty temp = new InvolvedParty(invPartyRef, appRef, person, jointApplicantInd, mainApplicantInd, startDate, relationship, createdBy, createdDate);
                        involvedParties.put(temp.getInvolvedPartyRef(), temp);
                        application.addInvolvedParty(temp, null);
                        if (endDate != null && endReason != null) {
                            temp.endInvolvedParty(endDate, endReason, null);
                        }
                        this.createInvolvedPartyMods(temp.getInvolvedPartyRef(), this.loadModMap("involvedPartyModifications", temp.getInvolvedPartyRef()));
                    }
                }
            }
        }
    }
    
    private void createInvolvedPartyMods(int invPartyRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.invPartyExists(invPartyRef) && !loadedMods.isEmpty()) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(invParty.getApplicationRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    InvolvedParty tempInvParty = invParty;
                    tempInvParty.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public InvolvedParty getInvolvedParty(int invPartyRef) {
        InvolvedParty invParty = null;
        if(this.invPartyExists(invPartyRef)) {
            invParty = this.involvedParties.get(invPartyRef);
        }
        return invParty;
    }
    
    public void createEndReason(Element endReason) throws SQLException {
        if(!this.endReasonExists(endReason.getCode())) {
            this.endReasons.put(endReason.getCode(), endReason);
            this.createElement("endReasons", endReason);
        }
    }
    
    public void updateEndReason(String endReasonCode) throws SQLException {
        if(this.endReasonExists(endReasonCode)) {
            Element endReason = this.getEndReason(endReasonCode);
            this.updateElement("endReasons", endReason);
            this.createModifiedBy("endReasonModifications", endReason.getLastModification(), endReason.getCode());
        }
    }
    
    private void loadEndReasons() throws SQLException {
        this.endReasons.clear();
        List<ElementImpl> loadedEndReasons;
        loadedEndReasons = this.loadElements("endReasons");
        if (!loadedEndReasons.isEmpty()) {
            for (Element temp : loadedEndReasons) {
                if (temp instanceof Element) {
                    this.endReasons.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("endReasonModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getEndReason(String code) {
        if(this.endReasonExists(code)) {
            return this.endReasons.get(code);
        }
        return null;
    }
    
    public void createRelationship(Element relationship) throws SQLException {
        if(!this.relationshipExists(relationship.getCode())) {
            this.relationships.put(relationship.getCode(), relationship);
            this.createElement("relationships", relationship);
        }
    }
    
    public void updateRelationship(String relationshipCode) throws SQLException {
        if(this.relationshipExists(relationshipCode)) {
            Element relationship = this.getRelationship(relationshipCode);
            this.updateElement("relationships", relationship);
            this.createModifiedBy("relationshipModifications", relationship.getLastModification(), relationship.getCode());
        }
    }
    
    private void loadRelationships() throws SQLException {
        this.relationships.clear();
        List<ElementImpl> loadedRelationships;
        loadedRelationships = this.loadElements("relationships");
        if (!loadedRelationships.isEmpty()) {
            for (Element temp : loadedRelationships) {
                if (temp instanceof Element) {
                    this.relationships.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("relationshipModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getRelationship(String code) {
        if(this.relationshipExists(code)) {
            return this.relationships.get(code);
        }
        return null;
    }
    
    public void createApplication(Application application) throws SQLException {
        if(!this.applicationExists(application.getApplicationRef())) {
            this.applications.put(application.getApplicationRef(), application);
            String insertSql = "insert into applications (appRef, appCorrName, appStartDate, appStatusCode, "
                    + "tenancyRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, application.getApplicationRef());
                insertStat.setString(col++, application.getAppCorrName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                insertStat.setString(col++, application.getAppStatusCode());
                insertStat.setInt(col++, application.getTenancyRef());
                insertStat.setString(col++, application.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateApplication(int appRef) throws SQLException {
        if(this.applicationExists(appRef)) {
            Application application = this.getApplication(appRef);
            String updateSql = "update applications set appCorrName=?, appStartDate=?, appEndDate=?, "
                    + "appStatusCode=?, tenancyRef=? where appRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, application.getAppCorrName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppEndDate()));
                updateStat.setString(col++, application.getAppStatusCode());
                updateStat.setInt(col++, application.getTenancyRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("applicationModifications", application.getLastModification(), application.getApplicationRef());
        }
    }
    
    private void loadApplications() throws SQLException {
        this.applications.clear();
        String sql = "select appRef, appCorrName, appStartDate, appEndDate, appStatusCode, "
                    + "tenancyRef, createdBy, createdDate from applications order by appRef";
        
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            while (results.next()) {
                
                int appRef = results.getInt("appRef");
                
                String appCorrName = results.getString("appCorrName");
                Date appStartDate = results.getDate("appStartDate");
                Date appEndDate = results.getDate("appEndDate");
                String appStatusCode = results.getString("appStatusCode");
                int tenancyRef = results.getInt("tenancyRef");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                Application temp = new Application(appRef, appCorrName, appStartDate, appStatusCode, createdBy, createdDate);
                
                if(appEndDate != null) {
                    temp.setEndDate(appEndDate, null);
                }
                
                if(this.tenancyExists(tenancyRef)) {
                    temp.setTenancy(tenancyRef, null);
                }
                
                this.applications.put(temp.getApplicationRef(), temp);
                
                this.createApplicationMods(temp.getApplicationRef(), this.loadModMap("applicationModifications", temp.getApplicationRef()));
                this.loadApplicationAddresses(temp.getApplicationRef());
                this.loadInvolvedParties(temp.getApplicationRef());
                this.loadPropertiesInterestedIn(temp.getApplicationRef());
            }
        }
    }
    
    private void createApplicationMods(int appRef, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (this.applicationExists(appRef) && !loadedMods.isEmpty()) {
            Application application = this.getApplication(appRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(application.getApplicationRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Application tempApp = (Application) application;
                    tempApp.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Application getApplication(int appRef) {
        if(this.applicationExists(appRef)) {
            return this.applications.get(appRef);
        }
        return null;
    }
    
    public void createPropertyInterest(int appRef, int propRef) throws SQLException {
        if(this.applicationExists(appRef) && this.propertyExists(propRef)) {
            
            String checkSql = "select count(appRef) as count from propertyInterest where appRef=? and propRef=?";
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (PreparedStatement checkStat = con.prepareStatement(checkSql)) {
                checkStat.setInt(1, appRef);
                checkStat.setInt(2, propRef);
                ResultSet checkResult = checkStat.executeQuery();
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if (count < 1) {
                    String insertSql = "insert into propertyInterest (appRef, propRef, current) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                
                    System.out.println("Inserting property " + propRef + " for appliction " + appRef);
                    insertStat.setInt(col++, appRef);
                    insertStat.setInt(col++, propRef);
                    insertStat.setBoolean(col++, true);
                    System.out.println(insertStat);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update propertyInterest set current=? where appRef=? and propRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    
                    System.out.println("Updating appliction " + appRef + " : Inserting property " + propRef + " for appliction " + appRef);
                    updateStat.setBoolean(col++, true);
                    System.out.println(updateStat);
                    updateStat.executeUpdate();
                    updateStat.close();
                    if(count > 1) {
                        System.out.println("ERROR IN DATABASE - MORE THAN ONE ENTRY FOR PROPERTY " + propRef + " - APPLICATION" + appRef);
                    }
                }
                checkStat.close();
            }
        }
    }

    private void loadPropertiesInterestedIn(int appRef) throws SQLException {
        if (this.applicationExists(appRef)) {
            Application application = this.getApplication(appRef);
            String sql = "select propRef from propertyInterest where appRef=? and current=?";
            try (PreparedStatement selectStat = con.prepareStatement(sql)) {
                selectStat.setInt(1, appRef);
                selectStat.setBoolean(2, true);

                ResultSet results = selectStat.executeQuery(sql);

                while (results.next()) {
                    int propRef = results.getInt("propRef");
                    if (this.propertyExists(propRef)) {
                        Property temp = this.getProperty(propRef);
                        application.addInterestedProperty(temp, null);
                    }
                }
            }
        }
    }
    
    public void endPropertyInterest(int appRef, int propRef) throws SQLException {
        if(this.applicationExists(appRef) && this.propertyExists(propRef)) {
            
            String checkSql = "select count(appRef) as count from propertyInterest where appRef=? and propRef=?";
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (PreparedStatement checkStat = con.prepareStatement(checkSql)) {
                ResultSet checkResult = checkStat.executeQuery();
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if(count >= 1) {
                    String updateSql = "update propertyInterest set current=? where appRef=? and propRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, false);
                    System.out.println(updateStat);
                    updateStat.executeUpdate();
                    if (count == 1) {
                        System.out.println("Updating appliction " + appRef + " : Ending property " + propRef + " for appliction " + appRef);
                    } else if (count > 1) {
                        System.out.println("ERROR IN DATABASE - Updating appliction " + appRef + " : Ending property " + propRef + " for appliction " + appRef);
                    }
                } else if (count == 0) {
                    String insertSql = "insert into propertyInterest (appRef, propRef, current) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                    System.out.println("ERROR IN DATABASE - Creating not current record for appliction " + appRef + " : Inserting property " + propRef + " for appliction " + appRef);
                    insertStat.setInt(col++, appRef);
                    insertStat.setInt(col++, propRef);
                    insertStat.setBoolean(col++, false);
                    System.out.println(insertStat);
                    insertStat.executeUpdate();
                    insertStat.close();
                }
                checkStat.close();
            }
        }
    }
    
    public void createLandlord(Landlord landlord) throws SQLException {
        if(!this.landlordExists(landlord.getLandlordRef())) {
            landlords.put(landlord.getLandlordRef(), landlord);
            String insertSql = "insert into landlords (landlordRef, personRef, createdBy, createdDate) values (?, ?, ?, ?,)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, landlord.getLandlordRef());
                insertStat.setInt(col++, landlord.getPersonRef());
                insertStat.setString(col++, landlord.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(landlord.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }

    private void loadLandlords() throws SQLException {
        this.landlords.clear();
        String sql = "select landlordRef, personRef, createdBy, createdDate from landlords order by landlordRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int landlordRef = results.getInt("landlordRef");
                int personRef = results.getInt("personRef");
                if (this.personExists(personRef)) {
                    Person person = this.getPerson(personRef);
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    Landlord temp = new Landlord(landlordRef, person, createdBy, createdDate);
                    landlords.put(temp.getLandlordRef(), temp);
                    this.createLandlordMods(temp.getLandlordRef(), this.loadModMap("PropertyElementValueModifications", temp.getLandlordRef()));
                }
            }
        }
    }
    
    private void createLandlordMods(int landlordRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.landlordExists(landlordRef) && !loadadMods.isEmpty()) {
            Landlord landlord = this.getLandlord(landlordRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(landlord.getLandlordRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    landlord.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Landlord getLandlord(int landlordRef) {
        if(this.landlordExists(landlordRef)) {
            return this.landlords.get(landlordRef);
        }
        return null;
    }
    
    public void createOffice(Office office) throws SQLException {
        if(!this.officeExists(office.getOfficeCode())) {
            this.offices.put(office.getOfficeCode(), office);
            String insertSql = "insert into Offices (officeCode, addressRef, startDate, createdBy, createdDate) values (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, office.getOfficeCode());
                insertStat.setInt(col++, office.getAddress().getAddressRef());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getStartDate()));
                insertStat.setString(col++, office.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateOffice(String officeCode) throws SQLException {
        if(this.officeExists(officeCode)) {
            Office office = this.getOffice(officeCode);
            String updateSql = "update Offices set addressRef=?, startDate=?, endDate=?, where officeCode=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setInt(col++, office.getAddress().getAddressRef());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getEndDate()));
                updateStat.setString(col++, office.getOfficeCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("officeModifications", office.getLastModification(), office.getOfficeCode());
        }
    }
    
    private void loadOffices() throws SQLException {
        this.offices.clear();
        String sql = "select officeCode, addressRef, startDate, endDate, createdBy, createdDate from offices order by createdDate";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                String officeCode = results.getString("officeCode");
                AddressInterface address;
                if(this.addressExists(results.getInt("addressRef"))) {
                    address = this.getAddress(results.getInt("addressRef"));
                } else {
                    address = this.getAddress(results.getInt("addressRef")); // NEED TO CREATE AN ERROR ADDRESS
                }
                Date startDate = results.getDate("startDate");
                Date endDate = results.getDate("endDate");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");

                Office temp = new Office(officeCode, address, startDate, createdBy, createdDate);
                if(endDate != null) {
                    temp.setEndDate(endDate, null);
                }
                
                this.offices.put(temp.getOfficeCode(), temp);
                this.createOfficeMods(temp.getOfficeCode(), this.loadModMap("addressModifications", temp.getOfficeCode()));
                this.loadOfficeContacts(temp.getOfficeCode());
            }
        }
    }
    
    private void createOfficeMods(String officeCode, HashMap<String, ModifiedByInterface> loadedMods) {
        if (this.officeExists(officeCode) && !loadedMods.isEmpty()) {
            Office office = this.getOffice(officeCode);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(temp.getKey().equals(office.getOfficeCode())) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    Office tempOffice = (Office) office;
                    tempOffice.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Office getOffice(String code) {
        if(this.officeExists(code)) {
            return this.offices.get(code);
        }
        return null;
    }
    
    public void createJobRole(JobRole jobRole) throws SQLException {
        if(!this.jobRoleExists(jobRole.getJobRoleCode())) {
            jobRoles.put(jobRole.getJobRoleCode(), jobRole);
            String insertSql = "insert into jobRoles (jobRoleCode, jobTitle, jobDescription, fullTime, salary, "
                    + "current, read, write, update, employeeRead, employeeWrite, employeeUpdate, createdBy, "
                    + "createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, jobRole.getJobRoleCode());
                insertStat.setString(col++, jobRole.getJobTitle());
                insertStat.setString(col++, jobRole.getJobDescription());
                insertStat.setBoolean(col++, jobRole.isFullTime());
                insertStat.setDouble(col++, jobRole.getSalary());
                insertStat.setBoolean(col++, jobRole.isCurrent());
                insertStat.setString(col++, jobRole.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(jobRole.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.createJobRoleRequirements(jobRole.getJobRoleCode(), jobRole.getJobRequirements());
            this.createJobRoleBenefits(jobRole.getJobRoleCode(), jobRole.getBenefits());
        }
    }

    public void updateJobRole(String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode)) {
            JobRole jobRole = this.getJobRole(jobRoleCode);
            String updateSql = "update jobRole set jobTitle=?, jobDescription=? salary=?, current=?, "
                    + "read=?, write=?, update=?, employeeRead=?, employeeWrite=?, "
                    + "employeeUpdate=?, where jobRoleCode=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, jobRole.getJobTitle());
                updateStat.setString(col++, jobRole.getJobDescription());
                updateStat.setBoolean(col++, jobRole.isRead());
                updateStat.setBoolean(col++, jobRole.isWrite());
                updateStat.setBoolean(col++, jobRole.isUpdate());
                updateStat.setBoolean(col++, jobRole.isEmployeeRead());
                updateStat.setBoolean(col++, jobRole.isEmployeeWrite());
                updateStat.setBoolean(col++, jobRole.isEmployeeUpdate());
                updateStat.setString(col++, jobRole.getJobRoleCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("jobRoleModifications", jobRole.getLastModification(), jobRole.getJobRoleCode());
        }
    }
    
    private void loadJobRoles() throws SQLException {
        this.jobRoles.clear();
        String sql = "select jobRoleCode, jobTitle, jobDescription, fullTime, salary, "
                    + "current, read, write, update, employeeRead, employeeWrite, employeeUpdate, "
                    + "createdBy, createdDate from jobRoles order by createdDate";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                String jobRoleCode = results.getString("jobRoleCode");
                String jobTitle = results.getString("jobTitle");
                String jobDescription = results.getString("jobDescription");
                boolean fullTime = results.getBoolean("fullTime");
                boolean read = results.getBoolean("read");
                boolean write = results.getBoolean("write");
                boolean update = results.getBoolean("update");
                boolean employeeRead = results.getBoolean("employeeRead");
                boolean employeeWrite = results.getBoolean("employeeWrite");
                boolean employeeUpdate = results.getBoolean("employeeUpdate");
                double salary = results.getDouble("salary");
                boolean current = results.getBoolean("current");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                JobRole temp = new JobRole(jobRoleCode, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, createdBy, createdDate);
                temp.setCurrent(current);
                
                this.jobRoles.put(temp.getJobRoleCode(), temp);
                this.createJobRoleMods(temp.getJobRoleCode(), this.loadModMap("jobRoleModifications", temp.getJobRoleCode()));
                this.loadJobRoleRequirements(temp.getJobRoleCode());
                this.loadJobRoleBenefits(temp.getJobRoleCode());
            }
        }
    }
    
    private void createJobRoleMods(String jobRoleCode, HashMap<String, ModifiedByInterface> loadedMods) {
        if (this.jobRoleExists(jobRoleCode) && !loadedMods.isEmpty()) {
            JobRole jobRole = this.getJobRole(jobRoleCode);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(temp.getKey().equals(jobRole.getJobRoleCode())) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    jobRole.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public JobRole getJobRole(String jobRoleCode) {
        if(this.jobRoleExists(jobRoleCode)) {
            return this.jobRoles.get(jobRoleCode);
        }
        return null;
    }
    
    // IS THE PRIVATE METHOD USED TO STORE JOB REQUIREMENTS IN BULK WHEN A JOB ROLE IS CREATED
    private void createJobRoleRequirements(String code, List<Element> jobRequirements) throws SQLException {
        if (!jobRequirements.isEmpty() && this.jobRoleExists(code)) {
            for (Element temp : jobRequirements) {
                this.createJobRoleRequirement(code, temp.getCode());
            }
        }
    }
    
    public void createJobRoleRequirement(String jobRoleCode, String requirementCode) throws SQLException {
        if (!requirementCode.isEmpty() && this.jobRoleExists(jobRoleCode) && this.jobRequirementExists(requirementCode)) {
            String insertSql = "insert into jobRoleRequirements (jobRoleCode, requirementCode) values (?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, jobRoleCode);
                insertStat.setString(col++, requirementCode);
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    private void loadJobRoleRequirements(String code) throws SQLException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = this.getJobRole(code);
            String sql = "select jobRoleCode, requirementCode from jobRoleRequirements order by createdDate";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);

                while (results.next()) {
                    String jobRoleCode = results.getString("jobRoleCode");
                    if (jobRoleCode.equals(code));
                    {
                        String requirementCode = results.getString("requirementCode");
                        Element temp = this.getJobRequirement(requirementCode);
                        jobRole.createJobRequirement(temp, null);
                    }
                }
            }
        }
    }
    
    // IS THE PRIVATE METHOD USED TO STORE JOB BENEFITS IN BULK WHEN A JOB ROLE IS CREATED
    private void createJobRoleBenefits(String code, List<JobRoleBenefit> benefits) throws SQLException {
        if (!benefits.isEmpty() && this.jobRoleExists(code)) {
            for (JobRoleBenefit temp : benefits) {
                this.createJobRoleBenefit(code, temp);
            }
        }
    }
    
    public void createJobRoleBenefit(String jobRoleCode, JobRoleBenefit benefit) throws SQLException {
        if (benefit != null && this.jobRoleExists(jobRoleCode) && this.jobBenefitExists(benefit.getBenefit().getCode())) {
            this.jobRoleBenefits.put(benefit.getBenefitRef(), benefit);
            String insertSql = "";
            if (benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, doubleValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            } else if (!benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, stringValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, benefit.getBenefitRef());
                insertStat.setString(col++, jobRoleCode);
                insertStat.setString(col++, benefit.getBenefitCode());
                if (benefit.isSalaryBenefit()) {
                    insertStat.setDouble(col++, benefit.getDoubleValue());
                } else if (!benefit.isSalaryBenefit()) {
                    insertStat.setString(col++, benefit.getStringValue());
                }
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getEndDate()));
                insertStat.setString(col++, benefit.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateJobRoleBenefit(String code, int benefitRef) throws SQLException {
        if(this.jobRoleExists(code) && this.jobRoleBenefitExists(benefitRef)) {
            JobRoleBenefit benefit = this.getJobRoleBenefit(benefitRef);
            String updateSql = "";
            if (benefit.isSalaryBenefit()) {
                updateSql = "update jobRoleBenefits set doubleValue=?, startDate=?, endDate=? where jobBenefitRef=? and jobRoleCode=? and benefitCode=?";
            } else if (!benefit.isSalaryBenefit()) {
                updateSql = "update jobRoleBenefits set stringValue=?, startDate, endDate=? where jobBenefitRef=? and jobRoleCode=? and benefitCode=?";
            }
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                if (benefit.isSalaryBenefit()) {
                    updateStat.setDouble(col++, benefit.getDoubleValue());
                } else if (!benefit.isSalaryBenefit()) {
                    updateStat.setString(col++, benefit.getStringValue());
                }
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getEndDate()));
                updateStat.setInt(col++, benefit.getBenefitRef());
                updateStat.setString(col++, code);
                updateStat.setString(col++, benefit.getBenefitCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getBenefitRef()); // 
        }
    }
    
    private void loadJobRoleBenefits(String code) throws SQLException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = this.getJobRole(code);
            String sql = "select jobBenefitRef, jobRoleCode, benefitCode, doubleValue, stringValue, startDate, endDate, createdBy, createdDate from jobRoleBenefits order by createdDate";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);
                
                while (results.next()) {
                    String jobRoleCode = results.getString("jobRoleCode");
                    if (jobRoleCode.equals(code)) {
                        int jobBenefitRef = results.getInt("requirementCode");
                        String benefitCode = results.getString("benefitCode");
                        if(this.jobBenefitExists(benefitCode)) {
                            Date startDate = results.getDate("startDate");
                            Date endDate = results.getDate("endDate");
                            String stringValue = results.getString("stringValue");
                            double doubleValue = results.getDouble("doubleValue");
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            JobRoleBenefit temp = new JobRoleBenefit(jobBenefitRef, this.getJobBenefit(benefitCode), startDate, stringValue!=null, stringValue, doubleValue, createdBy, createdDate);
                            if(endDate != null) {
                                temp.setEndDate(endDate, null);
                            }
                            this.jobRoleBenefits.put(temp.getBenefitRef(), temp);
                            this.createBenefitMods(temp, this.loadModMap("jobRoleBenefitModifications", temp.getBenefitRef()));
                            jobRole.createJobBenefit(temp, null);
                        }
                    }
                }
            }
        }
    }
    
    private void createBenefitMods(JobRoleBenefit benefit, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (benefit != null && this.jobBenefitExists(benefit.getBenefit().getCode()) && !loadedMods.isEmpty()) {
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(temp.getKey().equals(benefit.getBenefitRef())) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    benefit.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createJobRequirement(Element requirement) throws SQLException {
        if(!this.jobRequirementExists(requirement.getCode())) {
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.createElement("jobRequirements", requirement);
        }
    }
    
    public void updateJobRequirement(String requirementCode) throws SQLException {
        if(this.jobRequirementExists(requirementCode)) {
            Element requirement = this.getJobRequirement(requirementCode);
            this.updateElement("jobRequirements", requirement);
            this.createModifiedBy("jobRequirementModifications", requirement.getLastModification(), requirement.getCode());
        }
    }
    
    private void loadJobRequirements() throws SQLException {
        this.jobRequirements.clear();
        List<ElementImpl> loadedJobRequirements;
        loadedJobRequirements = this.loadElements("jobRequirements");
        if (!loadedJobRequirements.isEmpty()) {
            for (Element temp : loadedJobRequirements) {
                if (temp instanceof Element) {
                    this.jobRequirements.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("jobRequirementModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getJobRequirement(String code) {
        if(this.jobRequirementExists(code)) {
            return this.jobRequirements.get(code);
        }
        return null;
    }
    
    public void createJobBenefit(Element benefit) throws SQLException {
        if(!this.jobBenefitExists(benefit.getCode())) {
            this.jobBenefits.put(benefit.getCode(), benefit);
            this.createElement("jobBenefits", benefit);
        }
    }
    
    public void updateJobBenefit(String benefitCode) throws SQLException {
        if(this.jobBenefitExists(benefitCode)) {
            Element benefit = this.getJobBenefit(benefitCode);
            this.updateElement("jobBenefits", benefit);
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getCode());
        }
    }
    
    private void loadJobBenefits() throws SQLException {
        this.jobBenefits.clear();
        List<ElementImpl> loadedJobBenefits;
        loadedJobBenefits = this.loadElements("jobBenefits");
        if (!loadedJobBenefits.isEmpty()) {
            for (Element temp : loadedJobBenefits) {
                if (temp instanceof Element) {
                    this.jobBenefits.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("jobBenefitModifications", temp.getCode()));
                }
            }
        }
    }

    public Element getJobBenefit(String code) {
        if (this.jobBenefitExists(code)) {
            return this.jobBenefits.get(code);
        }
        return null;
    }

    public void createEmployee(Employee employee) throws SQLException {
        if (!this.employeeExists(employee.getEmployeeRef())) {
            employees.put(employee.getEmployeeRef(), employee);
            String insertSql = "insert into employees (employeeRef, personRef, officeCode, createdBy, createdDate) values (?, ?, ?, ?,)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, employee.getEmployeeRef());
                insertStat.setInt(col++, employee.getPersonRef());
                insertStat.setString(col++, employee.getOfficeCode());
                insertStat.setString(col++, employee.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employee.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }

    public void updateEmployee(int employeeRef) throws SQLException {
        if (this.employeeExists(employeeRef)) {
            Employee employee = this.getEmployee(employeeRef);
            String updateSql = "update employees set officeCode=? where employeeRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, employee.getOfficeCode());
                updateStat.setInt(col++, employee.getEmployeeRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("employeeModifications", employee.getLastModification(), employee.getEmployeeRef());
        }
    }

    private void loadEmployees() throws SQLException {
        this.employees.clear();
        String sql = "select employeeRef, personRef, officeCode, createdBy, createdDate from employees order by employeeRef";
        String sql1 = "select count(employeeRef) as count from users where employeeRef=?";
        String sql2 = "select username, password from users where employeeRef=?";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while (results.next()) {
                int employeeRef = results.getInt("employeeRef");
                int personRef = results.getInt("personRef");
                if (this.personExists(personRef)) {
                    Person person = this.getPerson(personRef);
                    String officeCode = results.getString("officeCode");
                    if (this.officeExists(officeCode)) {
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        try (PreparedStatement selectStat1 = con.prepareStatement(sql1)) {
                            selectStat1.setInt(1, employeeRef);
                            ResultSet results1 = selectStat1.executeQuery();
                            results1.next();
                            int count = results1.getInt("count");
                            if(count == 1) {
                                try (PreparedStatement selectStat2 = con.prepareStatement(sql2)) {
                                selectStat2.setInt(1, employeeRef);
                                ResultSet results2 = selectStat2.executeQuery();
                                String username = results2.getString("username");
                                String password =  results2.getString("password");
                                Employee temp = new Employee(employeeRef, person, username, password, createdBy, createdDate);
                                employees.put(temp.getEmployeeRef(), temp);
                                users.put(temp.getUser().getUsername(), temp.getUser());
                                this.createEmployeeMods(temp.getEmployeeRef(), this.loadModMap("employeeModifications", temp.getEmployeeRef()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void createEmployeeMods(int employeeRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.employeeExists(employeeRef) && !loadadMods.isEmpty()) {
            Employee employee = this.getEmployee(employeeRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(employee.getEmployeeRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    employee.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Employee getEmployee(int employeeRef) {
        if(this.employeeExists(employeeRef)) {
            return this.employees.get(employeeRef);
        }
        return null;
    }
    
    public void createTenancy(Tenancy tenancy) throws SQLException {
        if(!this.tenancyExists(tenancy.getAgreementRef())) {
            tenancies.put(tenancy.getAgreementRef(), tenancy);
            String insertSql = "insert into tenancies (tenancyRef, name, startDate, expectedEndDate, length, accountRef, officeCode, "
                    + "appRef, propRef, tenTypeCode, rent, charges, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, tenancy.getAgreementRef());
                insertStat.setString(col++, tenancy.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getExpectedEndDate()));
                insertStat.setInt(col++, tenancy.getLength());
                insertStat.setInt(col++, tenancy.getAccountRef());
                insertStat.setString(col++, tenancy.getOfficeCode());
                insertStat.setInt(col++, tenancy.getApplication().getApplicationRef());
                insertStat.setInt(col++, tenancy.getProperty().getPropRef());
                insertStat.setString(col++, tenancy.getTenType().getCode());
                insertStat.setDouble(col++, tenancy.getRent());
                insertStat.setDouble(col++, tenancy.getCharges());
                insertStat.setString(col++, tenancy.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateTenancy(int tenancyRef) throws SQLException {
        if(this.tenancyExists(tenancyRef)) {
            Tenancy tenancy = this.getTenancy(tenancyRef);
            String updateSql = "update tenancies set name=?, startDate=?, expectedEndDate=?, actualEndDate=?, "
                    + "length=?, tenTypeCode=?, rent=?, charges=? where tenancyRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, tenancy.getAgreementName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getExpectedEndDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getActualEndDate()));
                updateStat.setInt(col++, tenancy.getLength());
                updateStat.setString(col++, tenancy.getTenType().getCode());
                updateStat.setDouble(col++, tenancy.getRent());
                updateStat.setDouble(col++, tenancy.getCharges());
                updateStat.setInt(col++, tenancy.getAgreementRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("tenancyModifications", tenancy.getLastModification(), tenancy.getAgreementRef());
        }
    }

    private void loadTenancies() throws SQLException {
        this.tenancies.clear();
        String sql = "select tenancyRef, startDate, expectedEndDate, actualEndDate, length, accountRef, officeCode, "
                + "appRef, propRef, tenTypeCode, rent, charges, createdBy, createdDate from tenancies order by tenancyRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int tenancyRef = results.getInt("tenancyRef");
                Date startDate = results.getDate("startDate");
                Date actualEndDate = results.getDate("actualEndDate");
                int length = results.getInt("length");
                int accountRef = results.getInt("accountRef");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int appRef = results.getInt("appRef");
                    if (this.applicationExists(appRef)) {
                        Application application = this.getApplication(appRef);
                        int propRef = results.getInt("propRef");
                        if (this.propertyExists(propRef)) {
                            Property property = this.getProperty(propRef);
                            String tenTypeCode = results.getString("tenTypeCode");
                            if (this.tenancyTypeExists(tenTypeCode)) {
                                Element tenType = this.getTenancyType(tenTypeCode);
                                String createdBy = results.getString("createdBy");
                                Date createdDate = results.getDate("createdDate");
                                Tenancy temp = new Tenancy(tenancyRef, startDate, length, accountRef, property, application, tenType, officeCode, createdBy, createdDate);
                                this.tenancies.put(temp.getAgreementRef(), temp);
                                if (actualEndDate != null) {
                                    temp.setActualEndDate(actualEndDate, null);
                                }
                                this.createTenancyMods(temp.getAgreementRef(), this.loadModMap("tenancyModifications", temp.getAgreementRef()));
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void createTenancyMods(int tenancyRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.tenancyExists(tenancyRef) && !loadadMods.isEmpty()) {
            Tenancy tenancy = this.getTenancy(tenancyRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(tenancy.getAgreementRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    tenancy.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Tenancy getTenancy(int tenancyRef) {
        if (tenancies.containsKey(tenancyRef)) {
            return tenancies.get(tenancyRef);
        }
        return null;
    }
    
    public void createTenancyType(Element tenTpe) throws SQLException {
        if(!this.tenancyTypeExists(tenTpe.getCode())) {
            this.tenancyTypes.put(tenTpe.getCode(), tenTpe);
            this.createElement("tenancyTypes", tenTpe);
        }
    }
    
    public void updateTenancyType(String tenTypeCode) throws SQLException {
        if(this.tenancyTypeExists(tenTypeCode)) {
            Element tenType = this.getTenancyType(tenTypeCode);
            this.updateElement("tenancyTypes", tenType);
            this.createModifiedBy("tenancyTypeModifications", tenType.getLastModification(), tenType.getCode());
        }
    }
    
    private void loadTenancyTypes() throws SQLException {
        this.tenancyTypes.clear();
        List<ElementImpl> loadedTenancyTypes;
        loadedTenancyTypes = this.loadElements("tenancyTypes");
        if (!loadedTenancyTypes.isEmpty()) {
            for (Element temp : loadedTenancyTypes) {
                if (temp instanceof Element) {
                    this.tenancyTypes.put(temp.getCode(), temp);
                    this.createElementMods(temp, this.loadModMap("tenancyTypeModifications", temp.getCode()));
                }
            }
        }
    }
    
    public Element getTenancyType(String code) {
        if(this.tenancyTypeExists(code)) {
            return this.tenancyTypes.get(code);
        }
        return null;
    }
    
    public void createLease(Lease lease) throws SQLException {
        if(!this.leaseExists(lease.getAgreementRef())) {
            leases.put(lease.getAgreementRef(), lease);
            String insertSql = "insert into leases (leaseRef, name, startDate, expectedEndDate, length, accountRef"
                    + "officeCode, propertyRef, expenditure, fullManagement, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, lease.getAgreementRef());
                insertStat.setString(col++, lease.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getExpectedEndDate()));
                insertStat.setInt(col++, lease.getLength());
                insertStat.setInt(col++, lease.getAccountRef());
                insertStat.setString(col++, lease.getOfficeCode());
                insertStat.setInt(col++, lease.getPropertyRef());
                insertStat.setDouble(col++, lease.getExpenditure());
                insertStat.setBoolean(col++, lease.isFullManagement());
                insertStat.setString(col++, lease.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateLease(int leaseRef) throws SQLException {
        if(this.leaseExists(leaseRef)) {
            Lease lease = this.getLease(leaseRef);
            String updateSql = "update leases set name=?, startDate=?, expectedEndDate=?, "
                    + "actualEndDate=?, length=? where leaseRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, lease.getAgreementName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getExpectedEndDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getActualEndDate()));
                updateStat.setInt(col++, lease.getLength());
                updateStat.setInt(col++, lease.getAgreementRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("leaseModifications", lease.getLastModification(), lease.getAgreementRef());
        }
    }

    private void loadLeases() throws SQLException {
        this.leases.clear();
        String sql = "select leaseRef, name, startDate, expectedEndDate, length, accountRef, officeCode, "
                + "propRef, fullManagement, expenditure, createdBy, createdDate from leases order by leaseRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int leaseRef = results.getInt("leaseRef");
                String name = results.getString("name");
                Date startDate = results.getDate("startDate");
                Date actualEndDate = results.getDate("actualEndDate");
                int length = results.getInt("length");
                int accountRef = results.getInt("accountRef");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int propRef = results.getInt("propRef");
                    if (this.propertyExists(propRef)) {
                        Property property = this.getProperty(propRef);
                        boolean fullManagement = results.getBoolean("fullManagement");
                        double expenditure = results.getDouble("expenditure");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        Lease temp = new Lease(leaseRef, startDate, length, accountRef, property, fullManagement, expenditure, officeCode, createdBy, createdDate);
                        this.leases.put(temp.getAgreementRef(), temp);
                        if (actualEndDate != null) {
                            temp.setActualEndDate(actualEndDate, null);
                        }
                        this.loadLeaseLandlords(temp.getAccountRef());
                        property.setLandlords(temp.getLandlords(), null);
                        this.createLeaseMods(temp.getAgreementRef(), this.loadModMap("leaseModifications", temp.getAgreementRef()));
                    }
                }
            }
        }
    }
    
    private void createLeaseMods(int leaseRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseExists(leaseRef) && !loadadMods.isEmpty()) {
            Lease lease = this.getLease(leaseRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(lease.getAgreementRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    lease.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Lease getLease(int leaseRef) {
        if (leases.containsKey(leaseRef)) {
            return leases.get(leaseRef);
        }
        return null;
    }
    
    public void createLeaseLandlord(int landlordRef, int leaseRef) throws SQLException {
       if(this.leaseExists(leaseRef) && this.landlordExists(landlordRef)) {
            String checkSql = "select count(landlordRef) as count from leaseLandlord where landlordRef=? and leaseRef=?";
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (PreparedStatement checkStat = con.prepareStatement(checkSql)) {
                checkStat.setInt(1, landlordRef);
                checkStat.setInt(2, leaseRef);
                ResultSet checkResult = checkStat.executeQuery();
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if (count < 1) {
                    String insertSql = "insert into leaseLandlord (landlordRef, leaseRef, current) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                
                    System.out.println("Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    insertStat.setInt(col++, landlordRef);
                    insertStat.setInt(col++, leaseRef);
                    insertStat.setBoolean(col++, true);
                    System.out.println(insertStat);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update leaseLandlord set current=? where landlordRef=? and leaseRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    
                    System.out.println("Updating landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    updateStat.setBoolean(col++, true);
                    System.out.println(updateStat);
                    updateStat.executeUpdate();
                    updateStat.close();
                    if(count > 1) {
                        System.out.println("ERROR IN DATABASE - MORE THAN ONE ENTRY FOR LANDLORD " + landlordRef + " - LEASE" + leaseRef);
                    }
                }
                checkStat.close();
            }
        }
    }
    
    private void loadLeaseLandlords(int ref) throws SQLException {
        String sql = "select landlordRef, leaseRef, current from leaseLandlords order by leaseRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while (results.next()) {
                int leaseRef = results.getInt("leaseRef");
                int landlordRef = results.getInt("landlordRef");
                boolean current = results.getBoolean("current");
                if(leaseRef == ref && this.leaseExists(leaseRef) && this.landlordExists(landlordRef) && current) {
                    Lease lease = this.getLease(leaseRef);
                    lease.addLandlord(this.getLandlord(landlordRef), null);
                }
            }
        }
    }
    
    public void endLeaseLandlord(int landlordRef, int leaseRef) throws SQLException  {
        if(this.leaseExists(leaseRef) && this.landlordExists(landlordRef)) {
            String checkSql = "select count(landlordRef) as count from leaseLandlord where landlordRef=? and leaseRef=?";
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (PreparedStatement checkStat = con.prepareStatement(checkSql)) {
                checkStat.setInt(1, landlordRef);
                checkStat.setInt(2, leaseRef);
                ResultSet checkResult = checkStat.executeQuery();
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if (count > 1) {
                    String updateSql = "update leaseLandlord set current=? where landlordRef=? and leaseRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, false);
                    System.out.println(updateStat);
                    updateStat.executeUpdate();
                    if (count == 1) {
                        System.out.println("Updating landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    } else if (count > 1) {
                        System.out.println("ERROR IN DATABASE - MORE THAN ONE ENTRY FOR LANDLORD " + landlordRef + " - LEASE" + leaseRef);
                    }
                } else if (count == 0) {
                    String insertSql = "insert into leaseLandlord (landlordRef, leaseRef, current) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                    System.out.println("ERROR IN DATABASE - Creating not current record for landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    insertStat.setInt(col++, landlordRef);
                    insertStat.setInt(col++, leaseRef);
                    insertStat.setBoolean(col++, false);
                    System.out.println(insertStat);
                    insertStat.executeUpdate();
                    insertStat.close();
                }
                checkStat.close();
            }
        }
    }
    
    public void createContract(Contract contract) throws SQLException {
        if(!this.contractExists(contract.getAgreementRef())) {
            contracts.put(contract.getAgreementRef(), contract);
            String insertSql = "insert into contracts (contractRef, name, startDate, expectedEndDate, length, "
                    + "accountRef, officeCode, employeeRef, jobRoleCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contract.getAgreementRef());
                insertStat.setString(col++, contract.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getExpectedEndDate()));
                insertStat.setInt(col++, contract.getLength());
                insertStat.setInt(col++, contract.getAccountRef());
                insertStat.setString(col++, contract.getOfficeCode());
                insertStat.setInt(col++, contract.getEmployee().getEmployeeRef());
                insertStat.setString(col++, contract.getJobRole().getJobRoleCode());
                insertStat.setString(col++, contract.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateContract(int contractRef) throws SQLException {
        if(this.contractExists(contractRef)) {
            Contract contract = this.getContract(contractRef);
            String updateSql = "update contracts set name=?, startDate=?, expectedEndDate=?, "
                    + "actualEndDate=?, length=?, where contractRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, contract.getAgreementName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getExpectedEndDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getActualEndDate()));
                updateStat.setInt(col++, contract.getLength());
                updateStat.setInt(col++, contract.getAgreementRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("contractModifications", contract.getLastModification(), contract.getAgreementRef());
        }
    }
    
    private void loadContracts() throws SQLException {
        this.contracts.clear();
       String sql = "select contractRef, name, startDate, expectedEndDate, actualEndDate, length, accountRef, officeCode, "
                + "employeeRef, jobRoleCode, createdBy, createdDate from contracts order by contractRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int contractRef = results.getInt("contractRef");
                Date startDate = results.getDate("startDate");
                Date actualEndDate = results.getDate("actualEndDate");
                int length = results.getInt("length");
                int accountRef = results.getInt("accountRef");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int employeeRef = results.getInt("employeeRef");
                    if (this.employeeExists(employeeRef)) {
                        Employee employee = this.getEmployee(employeeRef);
                        String jobRoleCode = results.getString("jobRoleCode");
                        if (this.jobRoleExists(jobRoleCode)) {
                            JobRole jobRole = this.getJobRole(jobRoleCode);
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            Contract temp = new Contract(contractRef, accountRef, startDate, length, employee, jobRole, officeCode, createdBy, createdDate);
                            if (actualEndDate != null) {
                                temp.setActualEndDate(actualEndDate, null);
                            }
                            this.contracts.put(temp.getAgreementRef(), temp);
                            this.createContractMods(temp.getAgreementRef(), this.loadModMap("contractModifications", temp.getAgreementRef()));
                        }
                    }
                }
            }
        }
    }
    
    private void createContractMods(int contractRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(contractRef) && !loadadMods.isEmpty()) {
            Contract contract = this.getContract(contractRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(contract.getAgreementRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    contract.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Contract getContract(int contractRef) {
        if (contracts.containsKey(contractRef)) {
            return contracts.get(contractRef);
        }
        return null;
    }
    
    public void createRentAccount(RentAccount rentAcc) throws SQLException {
        if(!this.rentAccountExists(rentAcc.getAccRef())) {
            rentAccounts.put(rentAcc.getAccRef(), rentAcc);
            String insertSql = "insert into rentAccounts (rentAccRef, name, startDate, balance, "
                    + "officeCode, rent, tenancyRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, rentAcc.getAccRef());
                insertStat.setString(col++, rentAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getStartDate()));
                insertStat.setDouble(col++, rentAcc.getBalance());
                insertStat.setString(col++, rentAcc.getOfficeCode());
                insertStat.setDouble(col++, rentAcc.getRent());
                insertStat.setInt(col++, rentAcc.getTenancyRef());
                insertStat.setString(col++, rentAcc.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateRentAccount(int rentAccRef) throws SQLException {
        if(this.rentAccountExists(rentAccRef)) {
            RentAccount rentAcc = this.getRentAccount(rentAccRef);
            String updateSql = "update rentAccounts set name=?, startDate=?, endDate=?, "
                    + "balance=?, rent=? where rentAccRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, rentAcc.getAccName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getEndDate()));
                updateStat.setDouble(col++, rentAcc.getBalance());
                updateStat.setDouble(col++, rentAcc.getRent());
                updateStat.setInt(col++, rentAcc.getAccRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("rentAccountModifications", rentAcc.getLastModification(), rentAcc.getAccRef());
        }
    }
    
    private void loadRentAccounts() throws SQLException {
        this.rentAccounts.clear();
        String sql = "select rentAccRef, name, startDate, endDate, balance, officeCode, "
                + "rent, tenancyRef, createdBy, createdDate from rentAccounts order by rentAccRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int rentAccRef = results.getInt("rentAccRef");
                Date endDate = results.getDate("endDate");
                int balance = results.getInt("balance");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int tenancyRef = results.getInt("tenancyRef");
                    if (this.tenancyExists(tenancyRef)) {
                        Tenancy tenancy = this.getTenancy(tenancyRef);
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        RentAccount temp = new RentAccount(rentAccRef, tenancy, createdBy, createdDate);
                        this.loadTransactions("rentTransactions", temp);
                        temp.setBalance(balance);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.rentAccounts.put(temp.getAccRef(), temp);
                        this.createRentAccMods(temp.getAccRef(), this.loadModMap("rentAccountModifications", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void createRentAccMods(int rentAccRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.rentAccountExists(rentAccRef) && !loadadMods.isEmpty()) {
            RentAccount rentAcc = this.getRentAccount(rentAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(rentAcc.getAccRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    rentAcc.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public RentAccount getRentAccount(int rentAccRef) {
        if (rentAccounts.containsKey(rentAccRef)) {
            return rentAccounts.get(rentAccRef);
        }
        return null;
    }
    
    public void createLeaseAccount(LeaseAccount leaseAcc) throws SQLException {
        if(!this.leaseAccountExists(leaseAcc.getAccRef())) {
            leaseAccounts.put(leaseAcc.getAccRef(), leaseAcc);
            String insertSql = "insert into leaseAccounts (leaseAccRef, name, startDate, balance, "
                    + "officeCode, leaseRef, expenditure, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, leaseAcc.getAccRef());
                insertStat.setString(col++, leaseAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getStartDate()));
                insertStat.setDouble(col++, leaseAcc.getBalance());
                insertStat.setString(col++, leaseAcc.getOfficeCode());
                insertStat.setInt(col++, leaseAcc.getLeaseRef());
                insertStat.setDouble(col++, leaseAcc.getExpenditure());
                insertStat.setString(col++, leaseAcc.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }

    public void updateLeaseAccount(int leaseAccRef) throws SQLException {
        if (this.leaseAccountExists(leaseAccRef)) {
            LeaseAccount leaseAcc = this.getLeaseAccount(leaseAccRef);
            String updateSql = "update leaseAccounts set name=?, startDate=?, endDate=?, "
                    + "balance=?, expenditure=? where leaseAccRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, leaseAcc.getAccName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getEndDate()));
                updateStat.setDouble(col++, leaseAcc.getBalance());
                updateStat.setDouble(col++, leaseAcc.getExpenditure());
                updateStat.setInt(col++, leaseAcc.getAccRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("leaseAccountModifications", leaseAcc.getLastModification(), leaseAcc.getAccRef());
        }
    }

    private void loadLeaseAccounts() throws SQLException {
        this.leaseAccounts.clear();
        String sql = "select leaseAccRef, name, startDate, endDate, balance, officeCode, "
                + "expenditure, leaseRef, createdBy, createdDate from leaseAccounts order by leaseAccRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int leaseAccRef = results.getInt("leaseAccRef");
                Date endDate = results.getDate("endDate");
                int balance = results.getInt("balance");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int leaseRef = results.getInt("leaseRef");
                    if (this.leaseExists(leaseRef)) {
                        Lease lease = this.getLease(leaseRef);
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        LeaseAccount temp = new LeaseAccount(leaseAccRef, lease, createdBy, createdDate);
                        this.loadTransactions("leaseTransactions", temp);
                        temp.setBalance(balance);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.leaseAccounts.put(temp.getAccRef(), temp);
                        this.createLeaseAccMods(temp.getAccRef(), this.loadModMap("leaseAccountModifications", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void createLeaseAccMods(int leaseAccRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseAccountExists(leaseAccRef) && !loadadMods.isEmpty()) {
            LeaseAccount leaseAcc = this.getLeaseAccount(leaseAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(leaseAcc.getAccRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    leaseAcc.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public LeaseAccount getLeaseAccount(int leaseAccRef) {
        if (leaseAccounts.containsKey(leaseAccRef)) {
            return leaseAccounts.get(leaseAccRef);
        }
        return null;
    }
    
    public void createEmployeeAccount(EmployeeAccount employeeAcc) throws SQLException {
        if(!this.employeeAccountExists(employeeAcc.getAccRef())) {
            employeeAccounts.put(employeeAcc.getAccRef(), employeeAcc);
            String insertSql = "insert into employeeAccounts (employeeAccRef, name, startDate, "
                    + "balance, officeCode, contractRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, employeeAcc.getAccRef());
                insertStat.setString(col++, employeeAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getStartDate()));
                insertStat.setDouble(col++, employeeAcc.getBalance());
                insertStat.setString(col++, employeeAcc.getOfficeCode());
                insertStat.setInt(col++, employeeAcc.getContractRef());
                insertStat.setString(col++, employeeAcc.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateEmployeeAccount(int employeeAccRef) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef)) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            String updateSql = "update employeeAccounts set name=?, startDate=?, endDate=?, "
                    + "balance=?, salary=?, where employeeAccRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, employeeAcc.getAccName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getEndDate()));
                updateStat.setDouble(col++, employeeAcc.getBalance());
                updateStat.setDouble(col++, employeeAcc.getSalary());
                updateStat.setInt(col++, employeeAcc.getAccRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("employeeAccountModifications", employeeAcc.getLastModification(), employeeAcc.getAccRef());
        }
    }
    
    private void loadEmployeeAccounts() throws SQLException {
        this.employeeAccounts.clear();
       String sql = "select employeeAccRef, name, startDate, endDate, balance, officeCode, "
                + "salary, contractRef, createdBy, createdDate from employeeAccounts order by employeeAccRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int employeeAccRef = results.getInt("employeeAccRef");
                Date endDate = results.getDate("endDate");
                int balance = results.getInt("balance");
                String officeCode = results.getString("officeCode");
                if (this.officeExists(officeCode)) {
                    int contractRef = results.getInt("contractRef");
                    if (this.contractExists(contractRef)) {
                        Contract contract = this.getContract(contractRef);
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        EmployeeAccount temp = new EmployeeAccount(employeeAccRef, contract, createdBy, createdDate);
                        this.loadTransactions("contractTransactions", temp);
                        temp.setBalance(balance);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.employeeAccounts.put(temp.getAccRef(), temp);
                        this.createEmployeeAccMods(temp.getAccRef(), this.loadModMap("employeeAccountModifications", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void createEmployeeAccMods(int employeeAccRef, HashMap<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(employeeAccRef) && !loadadMods.isEmpty()) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(employeeAcc.getAccRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    employeeAcc.modifiedBy(tempMod);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public EmployeeAccount getEmployeeAccount(int employeeAccRef) {
        if (employeeAccounts.containsKey(employeeAccRef)) {
            return employeeAccounts.get(employeeAccRef);
        }
        return null;
    }
    
    public void createTransaction(String from, Transaction transaction) throws SQLException {
        if(!this.transactionExists(transaction.getTransactionRef())) {
            transactions.put(transaction.getTransactionRef(), transaction);
            String insertSql = "insert into transactions (transactionRef, accountRef, fromRef, toRef, amount, "
                    + "isDebit, transactionDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, transaction.getTransactionRef());
                insertStat.setInt(col++, transaction.getAccountRef());
                insertStat.setInt(col++, transaction.getFromRef());
                insertStat.setInt(col++, transaction.getToRef());
                insertStat.setDouble(col++, transaction.getAmount());
                insertStat.setBoolean(col++, transaction.isDebit());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(transaction.getTransactionDate()));
                insertStat.setString(col++, transaction.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(transaction.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    private void loadTransactions(String from, Account account) throws SQLException {
        this.transactions.clear();
        String sql = "select transactionRef, accountRef, fromRef, toRef, amount, "
                    + "isDebit, transactionDate, createdBy, createdDate from ? where accountRef=? order by transactionRef";
        try (PreparedStatement selectStat = con.prepareStatement(sql)) {
            selectStat.setString(1, from);
            selectStat.setInt(2, account.getAccRef());
            
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int transactionRef = results.getInt("transactionRef");
                int accountRef = results.getInt("accountRef");
                if (account.getAccRef() == accountRef) {
                    int fromRef = results.getInt("fromRef");
                    int toRef = results.getInt("toRef");
                    double amount = results.getDouble("amount");
                    boolean isDebit = results.getBoolean("isDebit");
                    Date transactionDate = results.getDate("transactionDate");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    Transaction temp = new Transaction(transactionRef, accountRef, fromRef, toRef, amount, isDebit, transactionDate, createdBy, createdDate);
                    this.transactions.put(temp.getTransactionRef(), temp);
                    account.createTransaction(temp, null);
                }
            }
        }
    }
    
    public JobRoleBenefit getJobRoleBenefit(int benRef) {
        if(this.jobRoleBenefitExists(benRef)) {
            return jobRoleBenefits.get(benRef);
        }
        return null;
    }
    
    public Contact getContact(int contactRef) {
        if(this.contactExists(contactRef)) {
            return contacts.get(contactRef);
        }
        return null;
    }
    
    public AddressUsage getAddressUsage(int addressUsageRef) {
        if(this.addressUsageExists(addressUsageRef)) {
            return this.addressUsages.get(addressUsageRef);
        }
        return null;
    }
    
    public void createUser(UserImpl user) throws SQLException {
        if(!this.userExists(user.getUsername())) {
            users.put(user.getUsername(), user);
            String insertSql = "insert into users (employeeRef, username, password, read, write, update, "
                    + "employeeRead, employeeWrite, employeeUpdate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, user.getEmployeeRef());
                insertStat.setString(col++, user.getUsername());
                insertStat.setString(col++, user.getPassword());
                insertStat.setBoolean(col++, user.getRead());
                insertStat.setBoolean(col++, user.getWrite());
                insertStat.setBoolean(col++, user.getUpdate());
                insertStat.setBoolean(col++, user.getEmployeeRead());
                insertStat.setBoolean(col++, user.getEmployeeWrite());
                insertStat.setBoolean(col++, user.getEmployeeUpdate());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateUser(String username) throws SQLException {
        if(this.userExists(username)) {
            UserImpl user = this.getUser(username);
            String updateSql = "update users set password=?, read=?, write=?, update=?, "
                    + "employeeRead=?, employeeWrite=?, employeeUpdate=? where username=? and employeeRef=?";
            try(PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, user.getPassword());
                updateStat.setBoolean(col++, user.getRead());
                updateStat.setBoolean(col++, user.getWrite());
                updateStat.setBoolean(col++, user.getUpdate());
                updateStat.setBoolean(col++, user.getEmployeeRead());
                updateStat.setBoolean(col++, user.getEmployeeWrite());
                updateStat.setBoolean(col++, user.getEmployeeUpdate());
                updateStat.setString(col++, user.getUsername());
                updateStat.setInt(col++, user.getEmployeeRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
        }
    }
    
    public UserImpl getUser(String username) {
        if(this.userExists(username)) {
            return this.users.get(username);
        }
        return null;
    }

    public boolean titleExists(String code) {
        return titles.containsKey(code);
    }
    
    public boolean genderExists(String code) {
        return genders.containsKey(code);
    }
    
    public boolean maritalStatusExists(String code) {
        return maritalStatuses.containsKey(code);
    }
    
    public boolean ethnicOriginExists(String code) {
        return ethnicOrigins.containsKey(code);
    }
    
    public boolean languageExists(String code) {
        return languages.containsKey(code);
    }
    
    public boolean nationalityExists(String code) {
        return nationalities.containsKey(code);
    }
    
    public boolean sexualityExists(String code) {
        return sexualities.containsKey(code);
    }
    
    public boolean religionExists(String code) {
        return religions.containsKey(code);
    }
    
    public boolean contactTypeExists(String code) {
        return this.contactTypes.containsKey(code);
    }
    
    public boolean propTypeExists(String code) {
        return this.propertyTypes.containsKey(code);
    }
    
    public boolean propSubTypeExists(String code) {
        return this.propertySubTypes.containsKey(code);
    }
    
    public boolean propElementExists(String code) {
        return this.propertyElements.containsKey(code);
    }
    
    public boolean personExists(int personRef) {
        return this.people.containsKey(personRef);
    }
    
    public boolean invPartyExists(int invPartyRef) {
        return this.involvedParties.containsKey(invPartyRef);
    }
    
    public boolean endReasonExists(String code) {
        return endReasons.containsKey(code);
    }
    
    public boolean relationshipExists(String code) {
        return relationships.containsKey(code);
    }
    
    public boolean applicationExists(int appRef) {
        return this.applications.containsKey(appRef);
    }
    
    public boolean addressExists(int addressRef) {
        return this.addresses.containsKey(addressRef);
    }
    
    public boolean propertyExists(int propRef) {
        return this.properties.containsKey(propRef);
    }
    
    public boolean landlordExists(int landlordRef) {
        return this.landlords.containsKey(landlordRef);
    }
    
    public boolean officeExists(String code) {
        return offices.containsKey(code);
    }
    
    public boolean jobRoleExists(String code) {
        return jobRoles.containsKey(code);
    }
    
    public boolean jobRoleBenefitExists(int benRef) {
        return jobRoleBenefits.containsKey(benRef);
    }
    
    public boolean jobBenefitExists(String code) {
        return this.jobBenefits.containsKey(code);
    }
    
    public boolean jobRequirementExists(String code) {
        return this.jobRequirements.containsKey(code);
    }
    
    public boolean employeeExists(int employeeRef) {
        return this.employees.containsKey(employeeRef);
    }
    
    public boolean tenancyExists(int tenancyRef) {
        return this.tenancies.containsKey(tenancyRef);
    }
    
    public boolean tenancyTypeExists(String code) {
        return this.tenancyTypes.containsKey(code);
    }
    
    public boolean leaseExists(int leaseRef) {
        return this.leases.containsKey(leaseRef);
    }
    
    public boolean contractExists(int contractRef) {
        return this.contracts.containsKey(contractRef);
    }
    
    public boolean rentAccountExists(int rentAccRef) {
        return this.rentAccounts.containsKey(rentAccRef);
    }
    
    public boolean leaseAccountExists(int leaseAccRef) {
        return this.leaseAccounts.containsKey(leaseAccRef);
    }
    
    public boolean employeeAccountExists(int employeeAccountRef) {
        return this.employeeAccounts.containsKey(employeeAccountRef);
    }
    
    public boolean transactionExists(int transactionRef) {
        return this.transactions.containsKey(transactionRef);
    }
    
    public boolean contactExists(int contactRef) {
        return this.contacts.containsKey(contactRef);
    }
    
    public boolean addressUsageExists(int addressUsageRef) {
        return this.addressUsages.containsKey(addressUsageRef);
    }
    
    public boolean userExists(String username) {
        return this.users.containsKey(username);
    }
    
    public int countPeople() {
        return this.people.size();
    }
    
    public int countInvolvedParties() {
        return this.involvedParties.size();
    }
    
    public int countLandords() {
        return this.landlords.size();
    }
    
    public int countEmployees() {
        return this.employees.size();
    }
    
    public int countApplications() {
        return this.applications.size();
    }
    
    public int countProperties() {
        return this.properties.size();
    }
    
    public int countTenancies() {
        return this.tenancies.size();
    }
    
    public int countLeases() {
        return this.leases.size();
    }
    
    public int countContracts() {
        return this.contracts.size();
    }
    
    public int countRentAccounts() {
        return this.rentAccounts.size();
    }
    
    public int countLeaseAccounts() {
        return this.titles.size();
    }
    
    public int countEmployeeAccounts() {
        return this.employeeAccounts.size();
    }
    
    public int countAddresses() {
        return this.addresses.size();
    }
    
    public int countTransactions() {
        return this.transactions.size();
    }
    
    public int countAddressUsages() {
        return this.addressUsages.size();
    }
    
    public int countContacts() {
        return this.contacts.size();
    }
    
    public int getPropElementCount() throws SQLException {
        String selectSql = "select count(propertyElementRef) as count from propertyElementValues";
        try(Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            int checkSum = results.getInt("count");
            return checkSum;
        }
    }
    
    public int getJobBenefitCount() throws SQLException {
        String selectSql = "select count(jobBenefitRef) as count from jobRoleBenefits";
        try(Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            int checkSum = results.getInt("count");
            return checkSum;
        }
    }
    
    ///////    FOR ADVANCED SEARCH, USE METHODS LIKE GET APPLICATIONS(person details), GET APPLICATIONS (property details), GET APPLICATIONS(application details)
    //////     GET TENANCIES(person details), GET TENANCIES(property details), GET TENANCIES (landlord details) and so on for each thing I want to return
}
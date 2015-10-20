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

    // Lists of Property details
    private final HashMap<Integer, Address> addresses;
    private final HashMap<String, Element> propertyTypes; // House, Flat, Bungalow
    private final HashMap<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final HashMap<String, Element> propertyElements;
    
    // List of Account Transactions
    private final HashMap<Integer, Transaction> transactions;
    
    
    
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

        // Lists of Property details
        this.addresses = new HashMap<>();
        this.propertyTypes = new HashMap<>(); // House, Flat, Bungalow
        this.propertySubTypes = new HashMap<>(); // Terraced, Semi-detached
        this.propertyElements = new HashMap<>();
        
        // List of Account Transactions
        this.transactions = new HashMap<>();
        
        
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
                this.loadPropertyTypes();
                this.loadPropertySubTypes();
                this.loadPropertyElements();
                
                
                this.loadAddresses();
                this.loadPeople();
                this.loadOffices();
                //this.loadInvolvedParties();
                //this.loadApplications();
                //this.loadJobBenefit();
                //this.loadJobRoles();
                
                
                
                
                
        

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
                Boolean current = results.getBoolean("current");
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
    
    public void createModifiedBy(String from, ModifiedByInterface modifiedBy, String code) throws SQLException {
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
        Element conType = null;
        if(this.contactTypeExists(code)) {
            conType = this.contactTypes.get(code);
        }
        return conType;
    }
    
    public void createPersonContact(Contact contact, int personRef) throws SQLException {
        if(contact != null) {
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
    
    
    
    public void updatePersonContact(Contact contact, int personRef) throws SQLException {
        if(contact != null) {
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
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createPersonContactMods(temp, reference, this.loadModMap("personContactModifications", person.getPersonRef()));
                    }
                }
            }
        }
    }
    
    private void createPersonContactMods(Contact contact, int personRef, HashMap<Integer, ModifiedByInterface> loadedContacts) {
        if (contact != null && this.personExists(personRef) && !loadedContacts.isEmpty()) {
            Iterator it = loadedContacts.entrySet().iterator();
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
        if(contact != null) {
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
    
    public void updateOfficeContact(Contact contact, String officeCode) throws SQLException {
        if(contact != null) {
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
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createOfficeContactMods(temp, code, this.loadModMap("officeContactModifications", office.getOfficeCode()));
                    }
                }
            }
        }
    }
    
    private void createOfficeContactMods(Contact contact, String code, HashMap<String, ModifiedByInterface> loadedContacts) {
        if (contact != null && this.officeExists(code) && !loadedContacts.isEmpty()) {
            Iterator it = loadedContacts.entrySet().iterator();
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
        if(address != null && this.personExists(personRef)) {
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
    
    public void updatePersonAddressUsage(AddressUsage address, int personRef) throws SQLException {
        if(address != null && this.personExists(personRef)) {
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
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createPersonAddressMods(temp, reference, this.loadModMap("personAddressesModifications", person.getPersonRef()));
                    }
                }
            }
        }
    }
    
    private void createPersonAddressMods(AddressUsage address, int personRef, HashMap<Integer, ModifiedByInterface> loadedAddresses) {
        if (address != null && this.personExists(personRef) && !loadedAddresses.isEmpty()) {
            Iterator it = loadedAddresses.entrySet().iterator();
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
        if(address != null && this.applicationExists(appRef)) {
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
    
    public void updateApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException {
        if(address != null && this.applicationExists(appRef)) {
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
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.createApplicationAddressMods(temp, reference, this.loadModMap("applicationAddressesModifications", application.getApplicationRef()));
                    }
                }
            }
        }
    }
    
    private void createApplicationAddressMods(AddressUsage address, int applicationRef, HashMap<Integer, ModifiedByInterface> loadedAddresses) {
        if (address != null && this.applicationExists(applicationRef) && !loadedAddresses.isEmpty()) {
            Iterator it = loadedAddresses.entrySet().iterator();
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
    
    public void updateTitle(Element title) throws SQLException {
        if(this.titleExists(title.getCode())) {
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
        Element title = null;
        if(this.titleExists(code)) {
            title = this.titles.get(code);
        }
        return title;
    }
    
    public void createGender(Element gender) throws SQLException {
        if(!this.genderExists(gender.getCode())) {
            this.genders.put(gender.getCode(), gender);
            this.createElement("genders", gender);
        }
    }
    
    public void updateGender(Element gender) throws SQLException {
        if(this.genderExists(gender.getCode())) {
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
        Element gender = null;
        if(this.genderExists(code)) {
            gender = this.genders.get(code);
        }
        return gender;
    }
    
    public void createMaritalStatus(Element status) throws SQLException {
        if(!this.maritalStatusExists(status.getCode())) {
            maritalStatuses.put(status.getCode(), status);
            this.createElement("maritalStatuses", status);
        }
    }
    
    public void updateMaritalStatus(Element status) throws SQLException {
        if(this.maritalStatusExists(status.getCode())) {
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
        Element status = null;
        if(this.maritalStatusExists(code)) {
            status = this.maritalStatuses.get(code);
        }
        return status;
    }
    
    public void createEthnicOrigin(Element ethnicOrigin) throws SQLException {
        if(!this.ethnicOriginExists(ethnicOrigin.getCode())) {
            this.ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
            this.createElement("ethnicOrigins", ethnicOrigin);
        }
    }
    
    public void updateEthnicOrigin(Element origin) throws SQLException {
        if(this.ethnicOriginExists(origin.getCode())) {
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
        Element origin = null;
        if(this.ethnicOriginExists(code)) {
            origin = this.ethnicOrigins.get(code);
        }
        return origin;
    }
    
    public void createLanguage(Element language) throws SQLException {
        if(!this.languageExists(language.getCode())) {
            this.languages.put(language.getCode(), language);
            this.createElement("languages", language);
        }
    }
    
    public void updateLanguage(Element language) throws SQLException {
        if(this.languageExists(language.getCode())) {
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
        Element language = null;
        if(this.languageExists(code)) {
            language = this.languages.get(code);
        }
        return language;
    }
    
    public void createNationality(Element nationality) throws SQLException {
        if(!this.nationalityExists(nationality.getCode())) {
            this.nationalities.put(nationality.getCode(), nationality);
            this.createElement("nationalities", nationality);
        }
    }
    
    public void updateNationality(Element nationality) throws SQLException {
        if(this.nationalityExists(nationality.getCode())) {
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
        Element nationality = null;
        if(this.nationalityExists(code)) {
            nationality = this.nationalities.get(code);
        }
        return nationality;
    }
    
    public void createSexuality(Element sex) throws SQLException {
        if(!this.sexualityExists(sex.getCode())) {
            this.sexualities.put(sex.getCode(), sex);
            this.createElement("sexualities", sex);
        }
    }
    
    public void updateSexuality(Element sex) throws SQLException {
        if(this.sexualityExists(sex.getCode())) {
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
        Element sex = null;
        if(this.sexualityExists(code)) {
            sex = this.sexualities.get(code);
        }
        return sex;
    }
    
    public void createReligion(Element religion) throws SQLException {
        if(!this.religionExists(religion.getCode())) {
            this.religions.put(religion.getCode(), religion);
            this.createElement("religions", religion);
        }
    }
    
    public void updateReligion(Element religion) throws SQLException {
        if(this.religionExists(religion.getCode())) {
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
        Element religion = null;
        if(this.religionExists(code)) {
            religion = this.religions.get(code);
        }
        return religion;
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
    
    public void updateAddress(Address address) throws SQLException {
        if(this.addressExists(address.getAddressRef())) {
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
                this.createAddressMods(this.getAddress(temp.getAddressRef()), this.loadModMap("addressModifications", temp.getAddressRef()));
            }
        }
    }
    
    private void createAddressMods(Address address, HashMap<Integer, ModifiedByInterface> loadedAddresses) {
        if (address != null && this.addressExists(address.getAddressRef()) && !loadedAddresses.isEmpty()) {
            Iterator it = loadedAddresses.entrySet().iterator();
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
        Address address = null;
        if(this.addressExists(addressRef)) {
            address = this.addresses.get(addressRef);
        }
        return address;
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
    
    public void updateProperty(Property property) throws SQLException {
        if(this.propertyExists(property.getPropRef())) {
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
    
    public Property getProperty(int propRef) {
        Property temp = null;
        if (properties.containsKey(propRef)) {
            temp = properties.get(propRef);
        }
        return temp;
    }
    
    public void createPropertyType(Element type) throws SQLException {
        if(!this.propTypeExists(type.getCode())) {
            this.propertyTypes.put(type.getCode(), type);
            this.createElement("propertyTypes", type);
        }
    }
    
    public void updatePropertyType(Element type) throws SQLException {
        if(this.propTypeExists(type.getCode())) {
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
        Element type = null;
        if(this.propTypeExists(code)) {
            type = this.propertyTypes.get(code);
        }
        return type;
    }
    
    public void createPropertySubType(Element type) throws SQLException {
        if(!this.propSubTypeExists(type.getCode())) {
            this.propertySubTypes.put(type.getCode(), type);
            this.createElement("propertySubTypes", type);
        }
    }
    
    public void updatePropertySubType(Element type) throws SQLException {
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
        Element type = null;
        if(this.propSubTypeExists(code)) {
            type = this.propertySubTypes.get(code);
        }
        return type;
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
        if(propertyElement != null && this.propertyExists(propertyRef) && this.propElementExists(propertyElement.getElement().getCode())) {
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
    
    public void createPropElement(Element element) throws SQLException {
        if(!this.propElementExists(element.getCode())) {
            this.propertyElements.put(element.getCode(), element);
            this.createElement("propertyElements", element);
        }
    }
    
    public void updatePropertyElement(Element element) throws SQLException {
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
        Element element = null;
        if(this.propElementExists(code)) {
            element = this.propertyElements.get(code);
        }
        return element;
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
    
    public void updatePerson(Person person) throws SQLException {
        if(this.personExists(person.getPersonRef())) {
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

                Person temp = new Person(personRef, title, forename, middleNames, surname, dateOfBirth, nationalInsurance, gender, maritalStatus, ethnicOrigin, language, nationality, sexuality, religion, null, null, createdBy, createdDate);
                
                this.people.put(temp.getPersonRef(), temp);
                this.createPeopleMods(this.getPerson(temp.getPersonRef()), this.loadModMap("peopleModifications", temp.getPersonRef()));
                this.loadPersonContacts(temp.getPersonRef());
                this.loadPersonAddresses(temp.getPersonRef());
            }
        }
    }
    
    private void createPeopleMods(Person person, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (person != null && this.personExists(person.getPersonRef()) && !loadedMods.isEmpty()) {
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
        Person person = null;
        if(this.personExists(personRef)) {
            person = this.people.get(personRef);
        }
        return person;
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

    public void updateInvolvedParty(InvolvedParty invParty) throws SQLException {
        if (this.invPartyExists(invParty.getInvolvedPartyRef())) {
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
                        this.createInvolvedPartyMods(temp, this.loadModMap("involvedPartyModifications", temp.getInvolvedPartyRef()));
                    }
                }
            }
        }
    }
    
    
    
    private void createInvolvedPartyMods(InvolvedParty involvedParty, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (involvedParty != null && this.invPartyExists(involvedParty.getInvolvedPartyRef()) && !loadedMods.isEmpty()) {
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                if(involvedParty.getApplicationRef() == (Integer) temp.getKey()) {
                    tempMod = (ModifiedByInterface) temp.getValue();
                    InvolvedParty tempInvParty = involvedParty;
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
    
    public void updateEndReason(Element endReason) throws SQLException {
        if(this.endReasonExists(endReason.getCode())) {
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
        Element endReason = null;
        if(this.endReasonExists(code)) {
            endReason = this.endReasons.get(code);
        }
        return endReason;
    }
    
    public void createRelationship(Element relationship) throws SQLException {
        if(!this.relationshipExists(relationship.getCode())) {
            this.relationships.put(relationship.getCode(), relationship);
            this.createElement("relationships", relationship);
        }
    }
    
    public void updateRelationship(Element relationship) throws SQLException {
        if(this.relationshipExists(relationship.getCode())) {
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
        Element reltionship = null;
        if(this.relationshipExists(code)) {
            reltionship = this.relationships.get(code);
        }
        return reltionship;
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
    
    public void updateApplication(Application application) throws SQLException {
        if(this.applicationExists(application.getApplicationRef())) {
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
                
                this.applications.put(temp.getApplicationRef(), temp);
                
                this.createApplicationMods(this.getApplication(temp.getApplicationRef()), this.loadModMap("applicationModifications", temp.getApplicationRef()));
                this.loadApplicationAddresses(temp.getApplicationRef());
                this.loadInvolvedParties(temp.getApplicationRef());
                this.loadPropertiesInterestedIn(temp.getApplicationRef());   // LOOK OVER WHAT I AM DOING WITH PROPERTIESINTERESTED IN
            }
        }
    }
    
    private void createApplicationMods(Application application, HashMap<Integer, ModifiedByInterface> loadedMods) {
        if (application != null && this.applicationExists(application.getApplicationRef()) && !loadedMods.isEmpty()) {
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
        Application app = null;
        if(this.applicationExists(appRef)) {
            app = this.applications.get(appRef);
        }
        return app;
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
    
    public Landlord getLandlord(int landlordRef) {
        Landlord landlord = null;
        if(this.landlordExists(landlordRef)) {
            landlord = this.landlords.get(landlordRef);
        }
        return landlord;
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
    
    public void updateOffice(Office office) throws SQLException {
        if(this.officeExists(office.getOfficeCode())) {
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
                this.createOfficeMods(this.getOffice(temp.getOfficeCode()), this.loadModMap("addressModifications", temp.getOfficeCode()));
                this.loadOfficeContacts(temp.getOfficeCode());
            }
        }
    }
    
    private void createOfficeMods(Office office, HashMap<String, ModifiedByInterface> loadedOffices) {
        if (office != null && this.officeExists(office.getOfficeCode()) && !loadedOffices.isEmpty()) {
            Iterator it = loadedOffices.entrySet().iterator();
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
        Office office = null;
        if(this.officeExists(code)) {
            office = this.offices.get(code);
        }
        return office;
    }
    
    public void createJobRole(JobRole jobRole) throws SQLException {
        if(!this.jobRoleExists(jobRole.getJobRoleCode())) {
            jobRoles.put(jobRole.getJobRoleCode(), jobRole);
            String insertSql = "insert into jobRoles (jobRoleCode, jobTitle, jobDescription, fullTime, salary, "
                    + "current, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
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
            this.createJobRequirements(jobRole.getJobRoleCode(), jobRole.getJobRequirements());
            this.createJobBenefits(jobRole.getJobRoleCode(), jobRole.getBenefits());
        }
    }

    public void updateJobRole(JobRole jobRole) throws SQLException {
        if (this.jobRoleExists(jobRole.getJobRoleCode())) {
            String updateSql = "update jobRole set jobTitle=?, jobDescription=? salary=?, current=?, where jobRoleCode=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, jobRole.getJobTitle());
                updateStat.setString(col++, jobRole.getJobDescription());
                updateStat.setString(col++, jobRole.getJobRoleCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("jobRoleModifications", jobRole.getLastModification(), jobRole.getJobRoleCode());
        }
    }
    
    public JobRole getJobRole(String jobRoleCode) {
        JobRole jobRole = null;
        if(this.jobRoleExists(jobRoleCode)) {
            jobRole = this.jobRoles.get(jobRoleCode);
        }
        return jobRole;
    }
    
    private void createJobRequirements(String code, List<String> jobRequirements) throws SQLException {
        if (!jobRequirements.isEmpty() && this.jobRoleExists(code)) {
            for (String temp : jobRequirements) {
                this.createJobRequirement(code, temp);
            }
        }
    }
    
    public void createJobRequirement(String code, String jobRequirement) throws SQLException {
        if (!jobRequirement.isEmpty() && this.jobRoleExists(code)) {
            String insertSql = "insert into jobRoleRequirements (jobRoleCode, requirement) values (?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, code);
                insertStat.setString(col++, jobRequirement);
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    private void createJobBenefits(String code, List<JobRoleBenefit> benefits) throws SQLException {
        if (!benefits.isEmpty() && this.jobRoleExists(code)) {
            for (JobRoleBenefit temp : benefits) {
                this.createJobBenefit(code, temp);
            }
        }
    }
    
    public void createJobBenefit(String code, JobRoleBenefit benefit) throws SQLException {
        if (benefit != null && this.jobRoleExists(code) && this.jobBenefitExists(benefit.getBenefit().getCode())) {
            String insertSql = "";
            if (benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, doubleValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            } else if (!benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, stringValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, benefit.getBenefitRef());
                insertStat.setString(col++, code);
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
    
    public void updateJobBenefit(String code, JobRoleBenefit benefit) throws SQLException {
        if(benefit != null && this.jobRoleExists(code) && this.jobBenefitExists(benefit.getBenefit().getCode())) {
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
    
    public void createJobRequirement(Element requirement) throws SQLException {
        if(!this.jobRequirementExists(requirement.getCode())) {
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.createElement("jobRequirements", requirement);
        }
    }
    
    public void updateJobRequirement(Element requirement) throws SQLException {
        if(this.jobRequirementExists(requirement.getCode())) {
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
        Element requirement = null;
        if(this.jobRequirementExists(code)) {
            requirement = this.jobRequirements.get(code);
        }
        return requirement;
    }
    
    public void createJobBenefit(Element benefit) throws SQLException {
        if(!this.jobBenefitExists(benefit.getCode())) {
            this.jobBenefits.put(benefit.getCode(), benefit);
            this.createElement("jobBenefits", benefit);
        }
    }
    
    public void updateJobBenefit(Element benefit) throws SQLException {
        if(this.jobBenefitExists(benefit.getCode())) {
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
        Element benefit = null;
        if(this.jobBenefitExists(code)) {
            benefit = this.jobBenefits.get(code);
        }
        return benefit;
    }
    
    public void createEmployee(Employee employee) throws SQLException {
        if(!this.employeeExists(employee.getEmployeeRef())) {
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
    
    public void updateEmployee(Employee employee) throws SQLException {
        if(this.employeeExists(employee.getEmployeeRef())) {
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
    
    public Employee getEmployee(int employeeRef) {
        Employee employee = null;
        if(this.employeeExists(employeeRef)) {
            employee = this.employees.get(employeeRef);
        }
        return employee;
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
    
    public void updateTenancy(Tenancy tenancy) throws SQLException {
        if(this.tenancyExists(tenancy.getAgreementRef())) {
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
            this.createModifiedBy("agreementModifications", tenancy.getLastModification(), tenancy.getAgreementRef());
        }
    }
    
    public Tenancy getTenancy(int tenancyRef) {
        Tenancy temp = null;
        if (tenancies.containsKey(tenancyRef)) {
            temp = tenancies.get(tenancyRef);
        }
        return temp;
    }
    
    public void createTenancyType(Element tenTpe) throws SQLException {
        if(!this.tenancyTypeExists(tenTpe.getCode())) {
            this.tenancyTypes.put(tenTpe.getCode(), tenTpe);
            this.createElement("tenancyTypes", tenTpe);
        }
    }
    
    public void updateTenancyType(Element tenType) throws SQLException {
        if(this.tenancyTypeExists(tenType.getCode())) {
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
        Element tenType = null;
        if(this.tenancyTypeExists(code)) {
            tenType = this.tenancyTypes.get(code);
        }
        return tenType;
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
    
    public void updateLease(Lease lease) throws SQLException {
        if(this.leaseExists(lease.getAgreementRef())) {
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
            this.createModifiedBy("agreementModifications", lease.getLastModification(), lease.getAgreementRef());
        }
    }
    
    public Lease getLease(int leaseRef) {
        Lease temp = null;
        if (leases.containsKey(leaseRef)) {
            temp = leases.get(leaseRef);
        }
        return temp;
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
    
    public void updateContract(Contract contract) throws SQLException {
        if(this.contractExists(contract.getAgreementRef())) {
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
            this.createModifiedBy("agreementModifications", contract.getLastModification(), contract.getAgreementRef());
        }
    }
    
    public Contract getContract(int contractRef) {
        Contract temp = null;
        if (contracts.containsKey(contractRef)) {
            temp = contracts.get(contractRef);
        }
        return temp;
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
    
    public void updateRentAccount(RentAccount rentAcc) throws SQLException {
        if(this.rentAccountExists(rentAcc.getAccRef())) {
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
            this.createModifiedBy("accountModifications", rentAcc.getLastModification(), rentAcc.getAccRef());
        }
    }
    
    public RentAccount getRentAccount(int rentAccRef) {
        RentAccount temp = null;
        if (rentAccounts.containsKey(rentAccRef)) {
            temp = rentAccounts.get(rentAccRef);
        }
        return temp;
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
    
    public void updateLeaseAccount(LeaseAccount leaseAcc) throws SQLException {
        if(this.leaseAccountExists(leaseAcc.getAccRef())) {
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
            this.createModifiedBy("accountModifications", leaseAcc.getLastModification(), leaseAcc.getAccRef());
        }
    }
    
    public LeaseAccount getLeaseAccount(int leaseAccRef) {
        LeaseAccount temp = null;
        if (leaseAccounts.containsKey(leaseAccRef)) {
            temp = leaseAccounts.get(leaseAccRef);
        }
        return temp;
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
    
    public void updateEmployeeAccount(EmployeeAccount employeeAcc) throws SQLException {
        if(this.employeeAccountExists(employeeAcc.getAccRef())) {
            String updateSql = "update Accounts set name=?, startDate=?, endDate=?, "
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
            this.createModifiedBy("accountModifications", employeeAcc.getLastModification(), employeeAcc.getAccRef());
        }
    }
    
    public Tenancy getEmployeeAccount(int tenancyRef) {
        Tenancy temp = null;
        if (tenancies.containsKey(tenancyRef)) {
            temp = tenancies.get(tenancyRef);
        }
        return temp;
    }
    
    public void createTransaction(Transaction transaction) throws SQLException {
        if(!this.transactionExists(transaction.getTransactionRef())) {
            transactions.put(transaction.getTransactionRef(), transaction);
            String insertSql = "insert into transactions (transactionRef, accountRef, fromRef, toRef, amount, "
                    + "isDebit, transactionDatecreatedBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public Tenancy getTransaction(int tenancyRef) {
        Tenancy temp = null;
        if (tenancies.containsKey(tenancyRef)) {
            temp = tenancies.get(tenancyRef);
        }
        return temp;
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
    
    private boolean contactTypeExists(String code) {
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
    
    private boolean tenancyTypeExists(String code) {
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
    
    ///////    FOR ADVANCED SEARCH, USE METHODS LIKE GET APPLICATIONS(person details), GET APPLICATIONS (property details), GET APPLICATIONS(application details)
    //////     GET TENANCIES(person details), GET TENANCIES(property details), GET TENANCIES (landlord details) and so on for each thing I want to return
}
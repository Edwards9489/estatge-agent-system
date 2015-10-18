/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
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

    // List of Involved Party details
    private final HashMap<String, Element> endReasons;
    private final HashMap<String, Element> relationships;

    // List of contract details
    private final HashMap<String, JobRole> jobRoles;
    private final HashMap<String, Element> jobBenefits;
    private final HashMap<String, Element> jobRequirements;

    // Lists of Property details
    private final HashMap<Integer, Address> addresses;
    private final HashMap<String, Element> propertyTypes; // House, Flat, Bungalow
    private final HashMap<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final HashMap<String, Element> propertyElements;
    
    // List of Employee details
    
    
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

        // List of Involved Party details
        this.endReasons = new HashMap<>();
        this.relationships = new HashMap<>();

        // List of contract details
        this.jobRoles = new HashMap<>();
        this.jobBenefits = new HashMap<>();
        this.jobRequirements = new HashMap<>();

        // Lists of Property details
        this.addresses = new HashMap<>();
        this.propertyTypes = new HashMap<>(); // House, Flat, Bungalow
        this.propertySubTypes = new HashMap<>(); // Terraced, Semi-detached
        this.propertyElements = new HashMap<>();
        
        
        try {
            this.connect();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
//        try {
//            this.load();
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void connect() throws Exception {
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
    
    public void save() throws SQLException {
        
        String checkSql = "select count(*) as count from people where id=?";
        PreparedStatement checkStat = this.con.prepareStatement(checkSql);
        
        String insertSql = "insert into people (id, name, age, employment_status, tax_id, uk_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStat = this.con.prepareStatement(insertSql);
        
        String updateSql = "update people set name=?, age=?, employment_status=?, tax_id=?, uk_citizen=?, gender=?, occupation=? where id=?";
        PreparedStatement updateStat = this.con.prepareStatement(updateSql);
        
        for(Person person: this.people.values()) {
//            int id = person.getId();
//            String name = person.getName();
//            AgeCategory age = person.getAgeCategory();
//            EmploymentCategory emp = person.getEmpCategory();
//            String tax = person.getTaxId();
//            Boolean isUk = person.isUKCitizen();
//            Gender gender = person.getGender();
//            String occupation = person.getOccupation();
            
//            checkStat.setInt(1, id);
            
            ResultSet checkResult = checkStat.executeQuery();
            checkResult.next();
            
            int count = checkResult.getInt(1);
            
            if(count == 0) {
//              System.out.println("Inserting person with ID " + id);
                int col = 1;
                
//                insertStat.setInt(col++, id);
//                insertStat.setString(col++, name);
//                insertStat.setString(col++, age.name());
//                insertStat.setString(col++, emp.name());
//                insertStat.setString(col++, tax);
//                insertStat.setBoolean(col++, isUk);
//                insertStat.setString(col++, gender.name());
//                insertStat.setString(col++, occupation);
                
                insertStat.executeUpdate();
            }
            else {
//              System.out.println("Updating person with ID " + id);
                int col = 1;
                
//                updateStat.setString(col++, name);
//                updateStat.setString(col++, age.name());
//                updateStat.setString(col++, emp.name());
//                updateStat.setString(col++, tax);
//                updateStat.setBoolean(col++, isUk);
//                updateStat.setString(col++, gender.name());
//                updateStat.setString(col++, occupation);
//                updateStat.setInt(col++, id);
                
                updateStat.executeUpdate();
            }
            
            
        }
        
        checkStat.close();
        insertStat.close();
        updateStat.close();
    }
    
    public void load() throws SQLException {
        this.people.clear();
        
        String sql = "select id, name, age, employment_status, tax_id, uk_citizen, gender, occupation from people order by name";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String age = results.getString("age");
                String emp = results.getString("employment_status");
                String taxId = results.getString("tax_id");
                Boolean isUK = results.getBoolean("uk_citizen");
                String gender = results.getString("gender");
                String occupation = results.getString("occupation");
                
//          Person person = new Person(id, name, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxId, isUK, Gender.valueOf(gender), occupation);
//          people.add(person);
                
                System.out.println(isUK);
            }
        }
    }
    
    public void updateElement(String from, Element element) throws SQLException {
        // use the from String as the from table and the element to update the actual element
        String updateSql = "update " + from + " set description=?, current=? where code=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, from);
            updateStat.setString(col++, element.getDescription());
            updateStat.setBoolean(col++, element.isCurrent());
            updateStat.executeUpdate();
            updateStat.close();
        }
    }
    
    public void createElement(String from, Element element) throws SQLException {
        
        String insertSql = "insert into " + from + " (code, description, createdBy, createdDate) values (?, ?, ?, ?)";
        try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
            int col = 1;
            insertStat.setString(col++, element.getCode());
            insertStat.setString(col++, element.getDescription());
            insertStat.setString(col++, element.getCreatedBy());
            insertStat.setDate(col++, DateConversion.utilDateToSQLDate(element.getCreatedDate()));
            System.out.println(insertStat);
            insertStat.executeUpdate();
            insertStat.close();
        }
    }
    
    public void createModifiedBy(String from, ModifiedByInterface modifiedBy, int ref) throws SQLException {
        if(modifiedBy != null) {
            String insertSql = "insert into " + from + " (ref, modifiedBy, modifiedByDate, description) values (?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, from);
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, modifiedBy.getModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(modifiedBy.getModifiedDate()));
                insertStat.setString(col++, modifiedBy.getDescription());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void createModifiedBy(String from, ModifiedByInterface modifiedBy, String code) throws SQLException {
        if(modifiedBy != null) {
            String insertSql = "insert into " + from + " (code, modifiedBy, modifiedByDate, description) values (?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, from);
                insertStat.setString(col++, code);
                insertStat.setString(col++, modifiedBy.getModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(modifiedBy.getModifiedDate()));
                insertStat.setString(col++, modifiedBy.getDescription());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void createContact(Contact contact, int personRef) throws SQLException {
        if(contact != null) {
            String insertSql = "insert into personContacts (contactRef, personRef, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setInt(col++, personRef);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void createContact(Contact contact, String officeCode) throws SQLException {
        if(contact != null) {
            String insertSql = "insert into officeContacts (contactRef, officeCode, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setString(col++, officeCode);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateContact(Contact contact, int personRef) throws SQLException {
        if(contact != null) {
            String updateSql = "update officeContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=? where contactRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personContactModifications", contact.getLastModification(), personRef);
        }
    }
    
    public void updateContact(Contact contact, String officeCode) throws SQLException {
        if(contact != null) {
            String updateSql = "update officeContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=? where contactRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("officeContactModifications", contact.getLastModification(), officeCode);
        }
    }
    
    public void createPersonAddressUsage(AddressUsage address, int personRef) throws SQLException {
        if(address != null && this.personExists(personRef)) {
            String insertSql = "insert into personAddressess (addressUsageRef, addressRef, personRef, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, personRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void createApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException {
        if(address != null && this.applicationExists(appRef)) {
            String insertSql = "insert into applicationAddressess (addressUsageRef, addressRef, applicationRef, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, appRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updatePersonAddressUsage(AddressUsage address, int personRef) throws SQLException {
        if(address != null && this.personExists(personRef)) {
            String updateSql = "update peopleAddresses set addressRef=?, startDate=?, endDate=? where addressUsageRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personAddressModifications", address.getLastModification(), address.getAddressUsageRef());
        }
    }
    
    public void updateApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException {
        if(address != null && this.applicationExists(appRef)) {
            String updateSql = "update applicationAddresses set addressRef=?, startDate=?, endDate=? where addressUsageRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("applicationAddressModifications", address.getLastModification(), address.getAddressUsageRef());
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
    
    public Address getAddress(int addressRef) {
        Address address = null;
        if(this.addressExists(addressRef)) {
            address = this.addresses.get(addressRef);
        }
        return address;
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
    
    public Element getPropertySubType(String code) {
        Element type = null;
        if(this.propSubTypeExists(code)) {
            type = this.propertySubTypes.get(code);
        }
        return type;
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
                    + "startDate, endDate relationshipCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, invParty.getInvolvedPartyRef());
                insertStat.setInt(col++, invParty.getApplicationRef());
                insertStat.setInt(col++, invParty.getPersonRef());
                insertStat.setBoolean(col++, invParty.isJointInd());
                insertStat.setBoolean(col++, invParty.isMainInd());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getEndDate()));
                insertStat.setString(col++, invParty.getRelationship().getCode());
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }
    
    public void updateInvolvedParty(InvolvedParty invParty) throws SQLException {
        if(this.invPartyExists(invParty.getInvolvedPartyRef())) {
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
    
    public Element getRelationship(String code) {
        Element reltionship = null;
        if(this.relationshipExists(code)) {
            reltionship = this.relationships.get(code);
        }
        return reltionship;
    }
    
    
    //////    LOOK OVER APPLICATION    /////////////
    /////  PROPERTIES INTERESTED IN   /////////////
    
    
    public void createApplication(Application application) throws SQLException {
        if(!this.applicationExists(application.getApplicationRef())) {
            this.applications.put(application.getApplicationRef(), application);
            String insertSql = "insert into applications (appRef, appCorrName, appStartDate, appEndDate, appStatusCode, "
                    + "appInterestedFlag, tenancyRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, application.getApplicationRef());
                insertStat.setString(col++, application.getAppCorrName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppEndDate()));
                insertStat.setString(col++, application.getAppStatusCode());
                insertStat.setBoolean(col++, application.isAppInterestedFlag());
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
                    + "appStatusCode=?, appInterestedFlag=?, tenancyRef=? where appRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, application.getAppCorrName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppEndDate()));
                updateStat.setString(col++, application.getAppStatusCode());
                updateStat.setBoolean(col++, application.isAppInterestedFlag());
                updateStat.setInt(col++, application.getTenancyRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("applicationModifications", application.getLastModification(), application.getApplicationRef());
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
            String insertSql = "insert into Offices (officeCode, addressRef, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, office.getOfficeCode());
                insertStat.setInt(col++, office.getAddress().getAddressRef());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(office.getEndDate()));
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
            this.createModifiedBy("OfficeModifications", office.getLastModification(), office.getOfficeCode());
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
    
    private void createJobRequirements(String code, List<String> jobRequirements) throws SQLException {
        if (!jobRequirements.isEmpty() && this.jobRoleExists(code)) {
            for (String temp : jobRequirements) {
                this.createJobRequirement(code, temp);
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
    
    public void createJobBenefit(String code, JobRoleBenefit benefit) throws SQLException {
        if (benefit != null && this.jobRoleExists(code)) {
            String insertSql = "";
            if (benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobRoleCode, benefitCode, doubleValue, current, createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            } else if (!benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobRoleCode, benefitCode, stringValue, current, createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, code);
                insertStat.setString(col++, benefit.getBenefitCode());
                if (benefit.isSalaryBenefit()) {
                    insertStat.setDouble(col++, benefit.getDoubleValue());
                } else if (!benefit.isSalaryBenefit()) {
                    insertStat.setString(col++, benefit.getStringValue());
                }
                insertStat.setBoolean(col++, benefit.isCurrent());
                insertStat.setString(col++, benefit.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
        }
    }

    public void updateJobRole(JobRole jobRole) throws SQLException {
        if (this.jobRoleExists(jobRole.getJobRoleCode())) {
            String updateSql = "update jobRole set jobTitle=?, jobDescription=? where jobRoleCode=?";
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
    
    public void createJobRoleBenefit(Element benefit) throws SQLException {
        if(!this.jobBenefitExists(benefit.getCode())) {
            this.jobBenefits.put(benefit.getCode(), benefit);
            this.createElement("jobBenefits", benefit);
        }
    }
    
    public void updateJobRoleBenefit(Element benefit) throws SQLException {
        if(this.jobBenefitExists(benefit.getCode())) {
            this.updateElement("jobBenefits", benefit);
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getCode());
        }
    }
    
    public Element getJobRoleBenefit(String code) {
        Element benefit = null;
        if(this.jobBenefitExists(code)) {
            benefit = this.jobBenefits.get(code);
        }
        return benefit;
    }
    
    public void createJobRoleRequirement(Element requirement) throws SQLException {
        if(!this.jobRequirementExists(requirement.getCode())) {
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.createElement("jobRequirements", requirement);
        }
    }
    
    public void updateJobRoleRequirement(Element requirement) throws SQLException {
        if(this.jobRequirementExists(requirement.getCode())) {
            this.updateElement("jobRequirements", requirement);
            this.createModifiedBy("jobRequirementModifications", requirement.getLastModification(), requirement.getCode());
        }
    }
    
    public Element getJobRoleRequirement(String code) {
        Element benefit = null;
        if(this.jobRequirementExists(code)) {
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
    
    public void createTenancy(Tenancy tenancy) throws SQLException {
        if(!this.tenancyExists(tenancy.getAgreementRef())) {
            tenancies.put(tenancy.getAgreementRef(), tenancy);
            String insertSql = "insert into tenancies (tenancyRef, name, startDate, expectedEndDate, actualEndDate, length, officeCode, "
                    + "appRef, propRef, tenTypeCode, rent, charges, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, tenancy.getAgreementRef());
                insertStat.setString(col++, tenancy.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getExpectedEndDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(tenancy.getActualEndDate()));
                insertStat.setInt(col++, tenancy.getLength());
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
    
    public void createLease(Lease lease) throws SQLException {
        if(!this.leaseExists(lease.getAgreementRef())) {
            leases.put(lease.getAgreementRef(), lease);
            String insertSql = "insert into leases (leaseRef, name, startDate, expectedEndDate, actualEndDate, length, "
                    + "expenditure, officeCode, fullManagement, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, lease.getAgreementRef());
                insertStat.setString(col++, lease.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getExpectedEndDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(lease.getActualEndDate()));
                insertStat.setInt(col++, lease.getLength());
                insertStat.setString(col++, lease.getOfficeCode());
                insertStat.setBoolean(col++, lease.isFullManagement());
                insertStat.setDouble(col++, lease.getExpenditure());
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
            String insertSql = "insert into contracts (contractRef, name, startDate, expectedEndDate, actualEndDate, "
                    + "length, officeCode, employeeRef, jobRoleCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contract.getAgreementRef());
                insertStat.setString(col++, contract.getAgreementName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getExpectedEndDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contract.getActualEndDate()));
                insertStat.setInt(col++, contract.getLength());
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
            String insertSql = "insert into rentAccounts (rentAccRef, name, startDate, endDate, balance, "
                    + "officeCode, rent, tenancyRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, rentAcc.getAccRef());
                insertStat.setString(col++, rentAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(rentAcc.getEndDate()));
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
            String insertSql = "insert into leaseAccounts (leaseAccRef, name, startDate, endDate, balance, "
                    + "officeCode, leaseRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, leaseAcc.getAccRef());
                insertStat.setString(col++, leaseAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(leaseAcc.getEndDate()));
                insertStat.setDouble(col++, leaseAcc.getBalance());
                insertStat.setString(col++, leaseAcc.getOfficeCode());
                insertStat.setInt(col++, leaseAcc.getLeaseRef());
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
            String insertSql = "insert into employeeAccounts (employeeAccRef, name, startDate, endDate, "
                    + "balance, officeCode, contractRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, employeeAcc.getAccRef());
                insertStat.setString(col++, employeeAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getStartDate()));
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getEndDate()));
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
    
    public boolean tenancyExists(int tenancyRef) {
        return this.tenancies.containsKey(tenancyRef);
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
    
    public boolean officeExists(String code) {
        return offices.containsKey(code);
    }
    
    public boolean jobRoleExists(String code) {
        return jobRoles.containsKey(code);
    }
    
    public boolean employeeExists(int employeeRef) {
        return this.employees.containsKey(employeeRef);
    }
    
    public boolean jobBenefitExists(String code) {
        return this.jobBenefits.containsKey(code);
    }
    
    public boolean jobRequirementExists(String code) {
        return this.jobRequirements.containsKey(code);
    }
    
    
    ///////    FOR ADVANCED SEARCH, USE METHODS LIKE GET APPLICATIONS(person details), GET APPLICATIONS (property details), GET APPLICATIONS(application details)
    //////     GET TENANCIES(person details), GET TENANCIES(property details), GET TENANCIES (landlord details) and so on for each thing I want to return
}
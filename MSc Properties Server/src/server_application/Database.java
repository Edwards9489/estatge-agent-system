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
    private final HashMap<String, Element> employeeBenefits;

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
        this.employeeBenefits = new HashMap<>();

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
        String updateSql = "update ? set description=?, current=? where code=?";
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
            String insertSql = "insert into ? (ref, modifiedBy, modifiedByDate, description) values (?, ?, ?, ?)";
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
            String insertSql = "insert into ? (code, modifiedBy, modifiedByDate, description) values (?, ?, ?, ?)";
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
            String insertSql = "insert into PersonContacts (contactRef, personRef, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
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
            String insertSql = "insert into OfficeContacts (contactRef, officeCode, contactTypeCode, contactValue, startDate, endDate, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
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
        this.createModifiedBy("PersonContactModifications", contact.getLastModification(), personRef);
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
        this.createModifiedBy("OfficeContactModifications", contact.getLastModification(), officeCode);
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
            this.createModifiedBy("TitleModifications", title.getLastModification(), title.getCode());
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
            this.createElement("Genders", gender);
        }
    }
    
    public void updateGender(Element gender) throws SQLException {
        if(this.genderExists(gender.getCode())) {
            this.updateElement("Genders", gender);
            this.createModifiedBy("GenderModifications", gender.getLastModification(), gender.getCode());
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
        if(this.maritalStatusExists(status.getCode())) {
            maritalStatuses.put(status.getCode(), status);
            this.createElement("MaritalStatuses", status);
        }
    }
    
    public void updateMaritalStatus(Element status) throws SQLException {
        if(this.maritalStatusExists(status.getCode())) {
            this.updateElement("MaritalStatuses", status);
            this.createModifiedBy("MaritalStatusModifications", status.getLastModification(), status.getCode());
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
        if(this.ethnicOriginExists(ethnicOrigin.getCode())) {
            this.ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
            this.createElement("EthnicOrigins", ethnicOrigin);
        }
    }
    
    public void updateEthnicOrigin(Element origin) throws SQLException {
        if(this.ethnicOriginExists(origin.getCode())) {
            this.updateElement("EthnicOrigins", origin);
            this.createModifiedBy("EthnicOriginModifications", origin.getLastModification(), origin.getCode());
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
        if(this.languageExists(language.getCode())) {
            this.languages.put(language.getCode(), language);
            this.createElement("Languages", language);
        }
    }
    
    public void updateLanguage(Element language) throws SQLException {
        if(this.languageExists(language.getCode())) {
            this.updateElement("Languages", language);
            this.createModifiedBy("LanguageModifications", language.getLastModification(), language.getCode());
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
        if(this.nationalityExists(nationality.getCode())) {
            this.nationalities.put(nationality.getCode(), nationality);
            this.createElement("Nationalities", nationality);
        }
    }
    
    public void updateNationality(Element nationality) throws SQLException {
        if(this.nationalityExists(nationality.getCode())) {
            this.updateElement("Nationalities", nationality);
            this.createModifiedBy("NationalityModifications", nationality.getLastModification(), nationality.getCode());
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
        if(this.sexualityExists(sex.getCode())) {
            this.sexualities.put(sex.getCode(), sex);
            this.createElement("Sexualities", sex);
        }
    }
    
    public void updateSexuality(Element sex) throws SQLException {
        if(this.sexualityExists(sex.getCode())) {
            this.updateElement("Sexualities", sex);
            this.createModifiedBy("SexualityModifications", sex.getLastModification(), sex.getCode());
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
        if(this.religionExists(religion.getCode())) {
            this.religions.put(religion.getCode(), religion);
            this.createElement("Religions", religion);
        }
    }
    
    public void updateReligion(Element religion) throws SQLException {
        if(this.religionExists(religion.getCode())) {
            this.updateElement("Religions", religion);
            this.createModifiedBy("ReligionModifications", religion.getLastModification(), religion.getCode());
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
        if(this.addressExists(address.getAddressRef())) {
            this.addresses.put(address.getAddressRef(), address);
            String insertSql = "insert into Addresses (addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, "
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
            String updateSql = "update Addresses set buildingNumber=?, buildingName=?, subStreetNumber=?, subStreet=?, "
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
            this.createModifiedBy("AddressModifications", address.getLastModification(), address.getAddressRef());
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
        if(this.propTypeExists(type.getCode())) {
            this.propertyTypes.put(type.getCode(), type);
            this.createElement("PropertyTypes", type);
        }
    }
    
    public void updatePropertyType(Element type) throws SQLException {
        if(this.propTypeExists(type.getCode())) {
            this.updateElement("PropertyTypes", type);
            this.createModifiedBy("PropertyTypeModifications", type.getLastModification(), type.getCode());
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
        if(this.propSubTypeExists(type.getCode())) {
            this.propertySubTypes.put(type.getCode(), type);
            this.createElement("PropertySubTypes", type);
        }
    }
    
    public void updatePropertySubType(Element type) throws SQLException {
        if(this.propSubTypeExists(type.getCode())) {
            this.updateElement("PropertySubTypes", type);
            this.createModifiedBy("PropertySubTypeModifications", type.getLastModification(), type.getCode());
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
        if(this.propElementExists(element.getCode())) {
            this.propertyElements.put(element.getCode(), element);
            this.createElement("PropertyElements", element);
        }
    }
    
    public void updatePropertyElement(Element element) throws SQLException {
        if(this.propElementExists(element.getCode())) {
            this.updateElement("PropertyElements", element);
            this.createModifiedBy("PropertyElementModifications", element.getLastModification(), element.getCode());
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
        if(this.personExists(person.getPersonRef())) {
            people.put(person.getPersonRef(), person);
            String insertSql = "insert into People (personRef, titleCode, forename, middleNames, surname, dateOfBirth, "
                    + "nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, "
                    + "sexualityCode, religionCode, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            String updateSql = "update People set titleCode=?, forename=?, middleNames=?, surname=?, dateOfBirth=?, nationalInsurance=?, "
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
            this.createModifiedBy("PersonModifications", person.getLastModification(), person.getPersonRef());
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
        if(this.invPartyExists(invParty.getInvolvedPartyRef())) {
            involvedParties.put(invParty.getInvolvedPartyRef(), invParty);
            String insertSql = "insert into InvolvedParties (invPartyRef, appRef, personRef, jointApplicantInd, mainApplicantInd, "
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
        if(this.invPartyExists(invParty.getInvolvedPartyRef())) {
            String updateSql = "update InvolvedParties set jointApplicantInd=?, mainApplicantInd=?, startDate=?, "
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
            this.createModifiedBy("InvolvedPartyModifications", invParty.getLastModification(), invParty.getInvolvedPartyRef());
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
            this.createElement("EndReasons", endReason);
        }
    }
    
    public void updateEndReason(Element endReason) throws SQLException {
        if(this.endReasonExists(endReason.getCode())) {
            this.updateElement("EndReasons", endReason);
            this.createModifiedBy("EndReasonModifications", endReason.getLastModification(), endReason.getCode());
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
            this.createElement("EndReasons", relationship);
        }
    }
    
    public void updateRelationship(Element relationship) throws SQLException {
        if(this.relationshipExists(relationship.getCode())) {
            this.updateElement("Relationships", relationship);
            this.createModifiedBy("RelationshipModifications", relationship.getLastModification(), relationship.getCode());
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
    
    
    public void createApplication(Application application) throws SQLException {
        if(this.applicationExists(application.getApplicationRef())) {
            this.applications.put(application.getApplicationRef(), application);
            String insertSql = "insert into Applications (appRef, appCorrName, appStartDate, appEndDate, appStatusCode, "
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
            String updateSql = "update Applications set appCorrName=?, appStartDate=?, appEndDate=?, "
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
            this.createModifiedBy("ApplicationModifications", application.getLastModification(), application.getApplicationRef());
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
        if(this.landlordExists(landlord.getLandlordRef())) {
            landlords.put(landlord.getLandlordRef(), landlord);
            String insertSql = "insert into landlords (landlordRef, personRef, createdBy, createdDate) values (?, ?, ?, ?, ?)";
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
    
    public void createEmployeeBenefit(Element benefit) throws SQLException {
        if(!this.employeeBenefitExists(benefit.getCode())) {
            this.employeeBenefits.put(benefit.getCode(), benefit);
            this.createElement("EmployeeBenefits", benefit);
        }
    }
    
    public void updateEmployeeBenefit(Element benefit) throws SQLException {
        if(this.employeeBenefitExists(benefit.getCode())) {
            this.updateElement("EmployeeBenefits", benefit);
            this.createModifiedBy("EmployeeBenefitModifications", benefit.getLastModification(), benefit.getCode());
        }
    }
    
    public Element getEmployeeBenefit(String code) {
        Element benefit = null;
        if(this.employeeBenefitExists(code)) {
            benefit = this.employeeBenefits.get(code);
        }
        return benefit;
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
    
    public boolean officeExists(String code) {
        return offices.containsKey(code);
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
    
    public boolean tenancyExists(int tenancyRef) {
        return this.tenancies.containsKey(tenancyRef);
    }
    
    public boolean landlordExists(int landlordRef) {
        return this.landlords.containsKey(landlordRef);
    }
    
    public Property getProperty(int propRef) {
        Property temp = null;
        if (properties.containsKey(propRef)) {
            temp = properties.get(propRef);
        }
        return temp;
    }
    
    public Tenancy getTenancy(int tenancyRef) {
        Tenancy temp = null;
        if (tenancies.containsKey(tenancyRef)) {
            temp = tenancies.get(tenancyRef);
        }
        return temp;
    }
    
    public boolean employeeBenefitExists(String code) {
        return employeeBenefits.containsKey(code);
    }
}
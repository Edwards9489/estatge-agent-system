/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
    private final HashMap<String, Element> employeeBenefits;

    // Lists of Property details
    private final HashMap<Integer, Address> addresses;
    private final HashMap<String, Element> propertyTypes; // House, Flat, Bungalow
    private final HashMap<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final HashMap<String, Element> propertyElements;
    
    ///   CONSTRUCTORS ///

    public Database() {
        offices = new HashMap<>();

        people = new HashMap<>();

        involvedParties = new HashMap<>();
        landlords = new HashMap<>();
        employees = new HashMap<>();

        applications = new HashMap<>();
        properties = new HashMap<>();

        tenancies = new HashMap<>();
        leases = new HashMap<>();
        contracts = new HashMap<>();

        rentAccounts = new HashMap<>();
        leaseAccounts = new HashMap<>();
        employeeAccounts = new HashMap<>();

        // List of People details
        titles = new HashMap<>();
        genders = new HashMap<>();
        maritalStatuses = new HashMap<>();
        ethnicOrigins = new HashMap<>();
        languages = new HashMap<>();
        nationalities = new HashMap<>();
        sexualities = new HashMap<>();
        religions = new HashMap<>();

        // List of Involved Party details
        endReasons = new HashMap<>();
        relationships = new HashMap<>();

        // List of contract details
        jobRoles = new HashMap<>();
        employeeBenefits = new HashMap<>();

        // Lists of Property details
        addresses = new HashMap<>();
        propertyTypes = new HashMap<>(); // House, Flat, Bungalow
        propertySubTypes = new HashMap<>(); // Terraced, Semi-detached
        propertyElements = new HashMap<>();
        
        
        try {
            connect();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            load();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void connect() throws Exception {

        if (con != null) {
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/person_collection";
        // jdbc: database type : localhost because it is on my machine : 3306 for port 3306 : mydb for database name
        con = DriverManager.getConnection(url, "root", "Toxic9489!999");

        /**
         * To add a library to the project (MySQL Library) : 1. Right-mouse
         * click on the Libraries folder in your project 2. Select Add Library
         * 3. Scroll down and find the Library you need 4. Select it, and click
         * on the Add button
         */
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Cant close connection");
            }
        }
    }
    
    public void save() throws SQLException {
        
        String checkSql = "select count(*) as count from people where id=?";
        PreparedStatement checkStat = con.prepareStatement(checkSql);
        
        String insertSql = "insert into people (id, name, age, employment_status, tax_id, uk_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStat = con.prepareStatement(insertSql);
        
        String updateSql = "update people set name=?, age=?, employment_status=?, tax_id=?, uk_citizen=?, gender=?, occupation=? where id=?";
        PreparedStatement updateStat = con.prepareStatement(updateSql);
        
        for(Person person: people.values()) {
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
    
    private void load() throws SQLException {
        people.clear();
        
        String sql = "select id, name, age, employment_status, tax_id, uk_citizen, gender, occupation from people order by name";
        Statement selectStat = con.createStatement();
        
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
        
        selectStat.close();
        
        
    }
    
    public void createTitle(Element title) {
        titles.put(title.getCode(), title);
        //return type;
    }
    
    public void createGender(Element gender) {
        genders.put(gender.getCode(), gender);
        //return type;
    }
    
    public void createMaritalStatus(Element status) {
        maritalStatuses.put(status.getCode(), status);
        //return type;
    }
    
    public void createEthnicOrigin(Element ethnicOrigin) {
        ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
        //return type;
    }
    
    public void createLanguage(Element language) {
        languages.put(language.getCode(), language);
        //return type;
    }
    
    public void createNationality(Element nationality) {
        nationalities.put(nationality.getCode(), nationality);
        //return type;
    }
    
    public void createSexuality(Element sexuality) {
        sexualities.put(sexuality.getCode(), sexuality);
        //return type;
    }
    
    public void createReligion(Element religion) {
        religions.put(religion.getCode(), religion);
        //return type;
    }
    
    public void createAddress(Address address) {
        addresses.put(address.getAddressRef(), address);
        // return a; - amend to accessor once Interfaces are set up
    }
    
    public void createPropertyType(Element type) {
        propertyTypes.put(type.getCode(), type);
        //return type;
    }
    
    public void createPropertySubType(Element type) {
        propertySubTypes.put(type.getCode(), type);
        //return type;
    }
    
    public void createPropElement(Element element) {
        propertyElements.put(element.getCode(), element);
    }
    
    public void createPerson(Person person) {
        people.put(person.getPersonRef(), person);
        // return p; - amend to accessor once Interfaces are set up
    }
    
    public void createInvolvedParty(InvolvedParty invParty) {
        involvedParties.put(invParty.getInvolvedPartyRef(), invParty);
        // return p; - amend to accessor once Interfaces are set up
    }
    
    public void createApplication(Application app) {
        applications.put(app.getApplicationRef(), app);
        // return a; - amend to accessor once Interfaces are set up
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
        return propertyTypes.containsKey(code);
    }
    
    public boolean propSubTypeExists(String code) {
        return propertySubTypes.containsKey(code);
    }
    
    public boolean propElementExists(String code) {
        return propertyElements.containsKey(code);
    }
}
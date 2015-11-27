/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
import interfaces.LandlordInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyElementInterface;
import interfaces.User;
import java.io.File;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
    private final Map<String, Office> offices;

    private final Map<Integer, Person> people;

    private final Map<Integer, InvolvedParty> involvedParties;
    private final Map<Integer, Landlord> landlords;
    private final Map<Integer, Employee> employees;

    private final Map<Integer, Application> applications;
    private final Map<Integer, Property> properties;

    private final Map<Integer, Tenancy> tenancies;
    private final Map<Integer, Lease> leases;
    private final Map<Integer, Contract> contracts;

    private final Map<Integer, RentAccount> rentAccounts;
    private final Map<Integer, LeaseAccount> leaseAccounts;
    private final Map<Integer, EmployeeAccount> employeeAccounts;

    // List of People details
    private final Map<String, Element> titles;
    private final Map<String, Element> genders;
    private final Map<String, Element> maritalStatuses;
    private final Map<String, Element> ethnicOrigins;
    private final Map<String, Element> languages;
    private final Map<String, Element> nationalities;
    private final Map<String, Element> sexualities;
    private final Map<String, Element> religions;
    
    //List of Contact details
    private final Map<String, Element> contactTypes;

    // List of Involved Party details
    private final Map<String, Element> endReasons;
    private final Map<String, Element> relationships;
    
    // List of Tenancy details
    private final Map<String, Element> tenancyTypes;

    // List of contract details
    private final Map<String, JobRole> jobRoles;
    private final Map<String, Element> jobBenefits;
    private final Map<String, Element> jobRequirements;
    private final Map<Integer, JobRoleBenefit> jobRoleBenefits;

    // Lists of Property details
    private final Map<Integer, Address> addresses;
    private final Map<String, Element> propertyTypes; // House, Flat, Bungalow
    private final Map<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final Map<String, Element> propertyElements;
    private final Map<Integer, PropertyElement> propertyElementValues;
    
    // List of Account Transactions
    private final Map<Integer, Transaction> transactions;
    
    private final Map<Integer, AddressUsage> addressUsages;
    private final Map<Integer, Contact> contacts;
    private final Map<String, UserImpl> users;
    private final Map<Integer, Note> notes;
    private final Map<Integer, Document> documents;
    
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Database
     * @param environment
     * @param addr
     * @param username
     * @param password
     * @param port 
     */
    public Database(String environment, String addr, String username, String password, Integer port) throws RemoteException {
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
        this.propertyElementValues = new HashMap<>();
        
        // List of Account Transactions
        this.transactions = new HashMap<>();
        
        this.addressUsages = new HashMap<>();
        this.contacts = new HashMap<>();
        this.users = new HashMap<>();
        this.notes = new HashMap<>();
        this.documents = new HashMap<>();
        
        
        try {
            this.connect(environment, addr, username, password, port);
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            this.load();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param env
     * @param address
     * @param user
     * @param passw
     * @param port
     * @throws Exception 
     */
    private void connect(String env, String address, String user, String passw, int port) throws Exception {
        if (this.con != null) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }
        
        if(env == null) {
            env = "LIVE";
        } else if(address == null) {
            address = "localhost";
        } else if(user == null) {
            user = "root";
        } else if(passw == null) {
            passw = "Toxic9489!999";
        }
        

        String url = "jdbc:mysql://" + address + ":" + port + "/msc_properties" + env + "2";
        // jdbc: database type : localhost because it is on my machine : 3306 for port 3306 : msc_properties(+ enviornment) for database name
        this.con = DriverManager.getConnection(url, user, passw);

        /**
         * To add a library to the project (MySQL Library) : 1. Right-mouse
         * click on the Libraries folder in your project 2. Select Add Library
         * 3. Scroll down and find the Library you need 4. Select it, and click
         * on the Add button
         */
    }
    /**
     * 
     */
    public void disconnect() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (SQLException ex) {
                System.out.println("Cant close connection");
            }
        }
    }
    
    /**
     * 
     * @throws SQLException
     */
    private void load() throws SQLException, RemoteException {
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
    
    /**
     * 
     * @param from
     * @param element
     * @throws SQLException 
     */
    
    private void createElement(String from, Element element) throws SQLException {
        String insertSql = "insert into " + from + " (code, description, noteRef, comment, cur, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
            int col = 1;
            insertStat.setString(col++, element.getCode());
            insertStat.setString(col++, element.getDescription());
            insertStat.setInt(col++, element.getNote().getRef());
            insertStat.setString(col++, element.getComment());
            insertStat.setBoolean(col++, element.isCurrent());
            insertStat.setString(col++, element.getCreatedBy());
            insertStat.setDate(col++, DateConversion.utilDateToSQLDate(element.getCreatedDate()));
            insertStat.executeUpdate();
            insertStat.close();
        }
    }
    
    /**
     * 
     * @param from
     * @param element
     * @throws SQLException 
     */
    private void updateElement(String from, Element element) throws SQLException {
        // use the from String as the from table and the element to update the actual element
        String updateSql = "update " + from + " set description=?, comment=?, cur=? where code=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, element.getDescription());
            updateStat.setString(col++, element.getComment());
            updateStat.setBoolean(col++, element.isCurrent());
            updateStat.setString(col++, element.getCode());
            updateStat.executeUpdate();
            updateStat.close();
        }
    }
    
    private void deleteElement(String from, String code) throws SQLException {
        String deleteSql = "delete from " + from + " where code=" + code;
        try (Statement deleteStat = this.con.createStatement()) {
            deleteStat.executeUpdate(deleteSql);
        }
    }
    
    /**
     * 
     * @param from
     * @return
     * @throws SQLException 
     */
    private List<ElementImpl> loadElements(String from) throws SQLException {
        String sql = "select code, description, noteRef, comment, cur, createdBy, createdDate from " + from + " order by createdDate";
        List<ElementImpl> elements = new ArrayList<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                String code = results.getString("code");
                String description = results.getString("description");
                int noteRef = results.getInt("noteRef");
                String comment = results.getString("comment");
                boolean current = results.getBoolean("cur");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                ElementImpl temp = new ElementImpl(code, description, note, createdBy, createdDate);
                temp.setCurrent(current);
                
                elements.add(temp);
            }
        }
        return elements;
    }
    
    /**
     * 
     * @param from
     * @param modifiedBy
     * @param ref
     * @throws SQLException 
     */
    private void createModifiedBy(String from, ModifiedByInterface modifiedBy, int ref) throws SQLException {
        if(modifiedBy != null) {
            String insertSql = "insert into " + from + " (ref, modifiedBy, modifiedDate, description) values (?, ?, ?, ?)";
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
    
    /**
     * 
     * @param from
     * @param reference
     * @return
     * @throws SQLException 
     */
    private Map<Integer, ModifiedByInterface> loadModMap(String from, int reference) throws SQLException {
        String sql = "select modificationRef, ref, modifiedBy, modifiedDate, description from " + from + " order by modifiedDate";
        Map<Integer, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int modificationRef = results.getInt("modificationRef");
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, modifiedDate, description);

                    modifiedByMap.put(modificationRef, temp);
                }
            }
        }
        return modifiedByMap;
    }
    
    /**
     * 
     * @param from
     * @param modifiedBy
     * @param code
     * @throws SQLException 
     */
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
    
    /**
     * 
     * @param from
     * @param uniqueCode
     * @return
     * @throws SQLException 
     */
    private Map<Integer, ModifiedByInterface> loadModMap(String from, String uniqueCode) throws SQLException {
        String sql = "select modificationRef, code, modifiedBy, modifiedDate, description from " + from + " order by modifiedDate";
        Map<Integer, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            while(results.next()) {
                int modificationRef = results.getInt("modificationRef");
                String code = results.getString("code");
                if (uniqueCode.equals(code)) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, modifiedDate, description);
                    modifiedByMap.put(modificationRef, temp);
                }
            }
        }
        return modifiedByMap;
    }
    
    /**
     * 
     * @param element
     * @param loadedMods 
     */
    private void loadElementMods(Element element, Map<Integer, ModifiedByInterface> loadedMods) {
        if (element != null && !loadedMods.isEmpty()) {
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                ElementImpl tempElement = (ElementImpl) element;
                tempElement.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    /**
     * 
     * @param from
     * @param reference
     * @return
     * @throws SQLException 
     */
    private Map<Integer, Document> loadDocMap(String from, int reference) throws SQLException {
        String sql = "select documentRef, ref, documentName, documentPath, noteRef, comment, createdBy, createdDate from " + from + " order by documentRef";
        Map<Integer, Document> fileMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int documentRef = results.getInt("documentRef");
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String documentName = results.getString("documentName");
                    String documentPath = results.getString("documentPath");
                    int noteRef = results.getInt("noteRef");
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    
                    File document = new File(documentPath); // Possibly might not work once File is used, may need to use getResouceAsStream
                    Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                    Document temp = new DocumentImpl(documentRef, document, documentName, note, createdBy, createdDate);

                    fileMap.put(documentRef, temp);
                }
            }
        }
        return fileMap;
    }
    
    /**
     * 
     * @param from
     * @param uniqueCode
     * @return
     * @throws SQLException 
     */
    private Map<Integer, Document> loadDocMap(String from, String uniqueCode) throws SQLException {
        String sql = "select documentRef, code, documentName, documentPath, noteRef, comment, createdBy, createdDate from " + from + " order by documentRef";
        Map<Integer, Document> fileMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int documentRef = results.getInt("documentRef");
                String code = results.getString("code");
                if (uniqueCode.equals(code)) {
                    String documentName = results.getString("documentName");
                    String documentPath = results.getString("documentPath");
                    int noteRef = results.getInt("noteRef");
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    
                    //  InputStream input = Database.class.getResourceAsStream(documentPath);   //// NEED TO LOOK INTO HOW THIS WORKS
                    
                    File document = new File(documentPath);
                    Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                    Document temp = new DocumentImpl(documentRef, document, documentName, note, createdBy, createdDate);

                    fileMap.put(documentRef, temp);
                }
            }
        }
        return fileMap;
    }
    
    private void createDocument(String from, int ref, Document document) throws SQLException {
        if(!this.documentExists(document.getDocumentRef())) {
            String insertSql = "insert into " + from + " (documentRef, ref, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, document.getDocumentName());
                insertStat.setString(col++, document.getDocumentPath());
                insertStat.setInt(col++, document.getNote().getRef());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.documents.put(document.getDocumentRef(), document);
        }
    }
    
    private void deleteDocument(String from, int ref, int documentRef) throws SQLException {
        if (this.documentExists(documentRef) && this.canDeleteDocument(documentRef)) {
            String deleteSql = "delete from " + from + " where documentRef=" + documentRef + " and ref=" + ref;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.documents.remove(documentRef);
                }
            }
        }
    }
    
    private void createDocument(String from, String code, Document document) throws SQLException {
        if(!this.documentExists(document.getDocumentRef())) {
            String insertSql = "insert into " + from + " (documentRef, code, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setString(col++, code);
                insertStat.setString(col++, document.getDocumentName());
                insertStat.setString(col++, document.getDocumentPath());
                insertStat.setInt(col++, document.getNote().getRef());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.documents.put(document.getDocumentRef(), document);
        }
    }
    
    private void deleteDocument(String from, String code, int documentRef) throws SQLException {
        if (this.documentExists(documentRef) && this.canDeleteDocument(documentRef)) {
            String deleteSql = "delete from " + from + " where documentRef=" + documentRef + " and code=" + code;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.documents.remove(documentRef);
                }
            }
        }
    }
    
    public boolean canDeleteDocument(int documentRef) {
        return (this.documentExists(documentRef));
    }
    
    public Document getDocument(int ref) {
        if(this.noteExists(ref)) {
            return documents.get(ref);
        }
        return null;
    }
    
    private Map<Integer, Note> loadNoteMap(String from, int reference) throws SQLException {
        String sql = "select noteRef, ref, comment, createdBy, createdDate from " + from + " order by createdDate";
        Map<Integer, Note> noteMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int noteRef = results.getInt("noteRef");
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");

                    NoteImpl temp = new NoteImpl(noteRef, comment, createdBy, createdDate);
                    this.createNoteMods(temp, this.loadModMap("noteModifications", noteRef));

                    noteMap.put(noteRef, temp);
                }
            }
        }
        return noteMap;
    }
    
    private Map<Integer, Note> loadNoteMap(String from, String uniqueCode) throws SQLException {
        String sql = "select noteRef, code, comment, createdBy, createdDate from " + from + " order by createdDate";
        Map<Integer, Note> noteMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while(results.next()) {
                int noteRef = results.getInt("noteRef");
                String code = results.getString("code");
                if (uniqueCode.equals(code)) {
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");

                    NoteImpl temp = new NoteImpl(noteRef, comment, createdBy, createdDate);
                    this.createNoteMods(temp, this.loadModMap("noteModifications", noteRef));
                    noteMap.put(noteRef, temp);
                }
            }
        }
        return noteMap;
    }
    
    private void deleteNote(int ref) {
        if(this.noteExists(ref) && !this.getNote(ref).hasBeenModified()) {
            notes.remove(ref);
        }
    }
    
    private void createNote(String from, int ref, Note note) throws SQLException {
        if(!this.noteExists(note.getRef())) {
            String insertSql = "insert into " + from + " (noteRef, ref, comment, createdBy, createdDate) values (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, note.getRef());
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, note.getNote());
                insertStat.setString(col++, note.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(note.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.notes.put(note.getRef(), note);
        }
    }
    
    private void updateNote(String from, int ref, int noteRef) throws SQLException {
        if(this.noteExists(noteRef)) {
            Note note = this.getNote(noteRef);
            String updateSql = "update " + from + " set comment=? where noteRef=" + noteRef + " and ref=" + ref;
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, note.getNote());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("noteModifications", note.getLastModification(), note.getRef());
        }
    }
    
    private void deleteNote(String from, int ref, int noteRef) throws SQLException {
        if (this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            String deleteSql = "delete from " + from + " where noteRef=" + noteRef + " and ref=" + ref;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.notes.remove(noteRef);
                }
            }
        }
    }
    
    private void createNote(String from, String code, Note note) throws SQLException {
        if(!this.noteExists(note.getRef())) {
            String insertSql = "insert into " + from + " (noteRef, code, comment, createdBy, createdDate) values (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, note.getRef());
                insertStat.setString(col++, code);
                insertStat.setString(col++, note.getNote());
                insertStat.setString(col++, note.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(note.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.notes.put(note.getRef(), note);
        }
    }
    
    private void updateNote(String from, String code, int noteRef) throws SQLException {
        if(this.noteExists(noteRef)) {
            Note note = this.getNote(noteRef);
            String updateSql = "update " + from + " set comment=? where noteRef=" + noteRef + " and code=" + code;
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, note.getNote());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("noteModifications", note.getLastModification(), note.getRef());
        }
    }
    
    private void deleteNote(String from, String code, int noteRef) throws SQLException {
        if (this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            String deleteSql = "delete from " + from + " where noteRef=" + noteRef + " and code=" + code;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.notes.remove(noteRef);
                }
            }
        }
    }
    
    public boolean canDeleteNote(int noteRef) {
        return (this.noteExists(noteRef) && !this.getNote(noteRef).hasBeenModified());
    }
    
    private void createNoteMods(NoteImpl note, Map<Integer, ModifiedByInterface> loadadMods) {
        if (note != null && this.noteExists(note.getRef()) && !loadadMods.isEmpty()) {
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                note.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public Note getNote(int ref) {
        if(this.noteExists(ref)) {
            return notes.get(ref);
        }
        return null;
    }
    
    /**
     * 
     * @param conType
     * @throws SQLException 
     */
    public void createContactType(Element conType) throws SQLException {
        if(conType != null && !this.contactTypeExists(conType.getCode())) {
            this.createElement("contactTypes", conType);
            this.contactTypes.put(conType.getCode(), conType);
            this.notes.put(conType.getNote().getRef(), conType.getNote());
        }
    }
    
    /**
     * 
     * @param contactTypeCode
     * @throws SQLException 
     */
    public void updateContactType(String contactTypeCode) throws SQLException {
        if(this.contactTypeExists(contactTypeCode)) {
            Element contactType = this.getContactType(contactTypeCode);
            this.updateElement("contactTypes", contactType);
            this.createModifiedBy("contactTypeModifications", contactType.getLastModification(), contactType.getCode());
        }
    }
    
    public void deleteContactType(String contactTypeCode) throws SQLException {
        if (this.contactTypeExists(contactTypeCode) && this.canDeleteContactType(contactTypeCode)) {
            this.deleteElement("contactTypes", contactTypeCode);
            this.deleteNote(this.getContactType(contactTypeCode).getNote().getRef());
            this.contactTypes.remove(contactTypeCode);
        }
    }
    
    public boolean canDeleteContactType(String contactTypeCode) {
        if (this.contactTypeExists(contactTypeCode) && !this.getContactType(contactTypeCode).hasBeenModified()) {
            for (Contact contact : this.getContacts()) {
                if (contactTypeCode.equals(contact.getContactType().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @throws SQLException 
     */
    private void loadContactTypes() throws SQLException {
        this.contactTypes.clear();
        List<ElementImpl> loadedContactTypes;
        loadedContactTypes = this.loadElements("contactTypes");
        if (!loadedContactTypes.isEmpty()) {
            for (Element temp : loadedContactTypes) {
                this.contactTypes.put(temp.getCode(), temp);
                this.notes.put(temp.getNote().getRef(), temp.getNote());
                this.loadElementMods(temp, this.loadModMap("contactTypeModifications", temp.getCode()));
            }
        }
    }
    
    /**
     * 
     * @param code
     * @return 
     */
    public Element getContactType(String code) {
        if(this.contactTypeExists(code)) {
            return this.contactTypes.get(code);
        }
        return null;
    }
    
    /**
     * 
     * @param contact
     * @param personRef
     * @throws SQLException 
     */
    public void createPersonContact(Contact contact, int personRef) throws SQLException {
        if(contact != null && !this.contactExists(contact.getContactRef()) && this.personExists(personRef)) {
            String insertSql = "insert into personContacts (contactRef, personRef, contactTypeCode, contactValue, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setInt(col++, personRef);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setInt(col++, contact.getNote().getRef());
                insertStat.setString(col++, contact.getComment());
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            contacts.put(contact.getContactRef(), (Contact) contact);
            notes.put(contact.getNote().getRef(), contact.getNote());
        }
    }
    
    /**
     * 
     * @param contactRef
     * @param personRef
     * @throws SQLException 
     */
    public void updatePersonContact(int contactRef, int personRef) throws SQLException {
        if(this.contactExists(contactRef) && this.personExists(personRef)) {
            Contact contact = this.getContact(contactRef);
            String updateSql = "update personContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=?, comment=? where contactRef=? and personRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setString(col++, contact.getComment());
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.setInt(col++, personRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personContactModifications", contact.getLastModification(), contact.getContactRef());
        }
    }
    
    public void deletePersonContact(int contactRef, int personRef) throws SQLException {
        if (this.contactExists(contactRef) && this.personExists(personRef) && this.getPerson(personRef).hasContact(contactRef) && this.canDeleteContact(contactRef)) {
            String deleteSql = "delete from personContacts where contactRef=" + contactRef + " and personRef=" + personRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getContact(contactRef).getNote().getRef());
                    this.contacts.remove(contactRef);
                }
            }
        }
    }
    
    /**
     * 
     * @param reference
     * @throws SQLException 
     */
    private void loadPersonContacts(int reference) throws SQLException {
        String sql = "select contactRef, personRef, contactTypeCode, contactValue, startDate, endDate, noteRef, comment, createdBy, createdDate from personContacts order by contactRef";
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
                        int noteRef = results.getInt("noteRef");
                        String comment = results.getString("comment");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        Contact temp = new Contact(contactRef, contactType, contactValue, startDate, note, createdBy, createdDate);
                        this.contacts.put(temp.getContactRef(), temp);
                        this.notes.put(note.getRef(), note);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        person.createContact(temp, null);
                        this.loadPersonContactMods(temp.getContactRef(), reference, this.loadModMap("personContactModifications", temp.getContactRef()));
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param contactRef
     * @param personRef
     * @param loadedMods 
     */
    private void loadPersonContactMods(int contactRef, int personRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.contactExists(contactRef) && this.personExists(personRef) && !loadedMods.isEmpty()) {
            Contact contact = this.getContact(contactRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Contact tempContact = (Contact) contact;
                tempContact.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    /**
     * 
     * @param contact
     * @param officeCode
     * @throws SQLException 
     */
    public void createOfficeContact(Contact contact, String officeCode) throws SQLException {
        if(contact != null && !this.contactExists(contact.getContactRef()) && this.officeExists(officeCode)) {
            String insertSql = "insert into officeContacts (contactRef, officeCode, contactTypeCode, contactValue, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setString(col++, officeCode);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setInt(col++, contact.getNote().getRef());
                insertStat.setString(col++, contact.getComment());
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            contacts.put(contact.getContactRef(), contact);
            notes.put(contact.getNote().getRef(), contact.getNote());
        }
    }
    
    /**
     * 
     * @param contactRef
     * @param officeCode
     * @throws SQLException 
     */
    public void updateOfficeContact(int contactRef, String officeCode) throws SQLException {
        if(this.contactExists(contactRef) && this.officeExists(officeCode)) {
            Contact contact = this.getContact(contactRef);
            String updateSql = "update officeContacts set contactTypeCode=?, contactValue=?, startDate=?, endDate=?, comment=? where contactRef=? and officeCode=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setString(col++, contact.getContactType().getCode());
            updateStat.setString(col++, contact.getContactValue());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getEndDate()));
            updateStat.setString(col++, contact.getComment());
            updateStat.setInt(col++, contact.getContactRef());
            updateStat.setString(col++, officeCode);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("officeContactModifications", contact.getLastModification(), contact.getContactRef());
        }
    }
    
    public void deleteOfficeContact(int contactRef, String code) throws SQLException {
        if (this.contactExists(contactRef) && this.officeExists(code) && this.getOffice(code).hasContact(contactRef) && this.canDeleteContact(contactRef)) {
            String deleteSql = "delete from officeContacts where contactRef=" + contactRef + " and officeCode=" + code;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getContact(contactRef).getNote().getRef());
                    this.contacts.remove(contactRef);
                }
            }
        }
    }
    
    /**
     * 
     * @param code
     * @throws SQLException 
     */
    private void loadOfficeContacts(String code) throws SQLException {
        String sql = "select contactRef, officeCode, contactTypeCode, contactValue, startDate, endDate, noteRef, comment, createdBy, createdDate from officeContacts order by contactRef";
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
                        int noteRef = results.getInt("noteRef");
                        String comment = results.getString("comment");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        Contact temp = new Contact(contactRef, contactType, contactValue, startDate, note, createdBy, createdDate);
                        this.contacts.put(temp.getContactRef(), temp);
                        this.notes.put(note.getRef(), note);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        office.createContact(temp, null);
                        this.loadOfficeContactMods(temp.getContactRef(), code, this.loadModMap("officeContactModifications", temp.getContactRef()));
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param contactRef
     * @param code
     * @param loadedMods 
     */
    private void loadOfficeContactMods(int contactRef, String code, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.contactExists(contactRef) && this.officeExists(code) && !loadedMods.isEmpty()) {
            Contact contact = this.getContact(contactRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Contact tempContact = (Contact) contact;
                tempContact.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public boolean canDeleteContact(int contactRef) {
        return (this.contactExists(contactRef) && !this.getContact(contactRef).hasBeenModified());
    }
    
    /**
     * 
     * @param address
     * @param personRef
     * @throws SQLException 
     */
    public void createPersonAddressUsage(AddressUsage address, int personRef) throws SQLException {
        if(address != null && !this.addressUsageExists(address.getAddressUsageRef()) && this.personExists(personRef)) {
            String insertSql = "insert into personAddresses (addressUsageRef, addressRef, personRef, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, personRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setInt(col++, address.getNote().getRef());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                addressUsages.put(address.getAddressUsageRef(), address);
                notes.put(address.getNote().getRef(), address.getNote());
            }
        }
    }
    
    /**
     * 
     * @param addressRef
     * @param personRef
     * @throws SQLException 
     */
    public void updatePersonAddressUsage(int addressRef, int personRef) throws SQLException {
        if(this.addressUsageExists(addressRef) && this.personExists(personRef)) {
            AddressUsage address = this.getAddressUsage(addressRef);
            String updateSql = "update personAddresses set addressRef=?, startDate=?, endDate=?, comment=? where addressUsageRef=? and personRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setString(col++, address.getComment());
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.setInt(col++, personRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("personAddressModifications", address.getLastModification(), address.getAddressUsageRef());
        }
    }
    
    public void deletePersonAddressUsage(int personRef, int addrRef) throws SQLException {
        if (this.addressUsageExists(addrRef) && this.personExists(personRef) && this.getPerson(personRef).getCurrentAddress().getAddressUsageRef() == addrRef && this.canDeleteAddressUsage(addrRef)) {
            String deleteSql = "delete from personAddresses where addressUsageRef=" + addrRef + " and personRef=" + personRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddressUsage(addrRef).getNote().getRef());
                    this.addressUsages.remove(addrRef);
                }
            }
        }
    }
    
    /**
     * 
     * @param reference
     * @throws SQLException 
     */
    private void loadPersonAddresses(int reference) throws SQLException {
        String sql = "select addressUsageRef, addressRef, personRef, startDate, endDate, noteRef, comment, createdBy, createdDate from personAddresses order by addressUsageRef";
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
                        int noteRef = results.getInt("noteRef");
                        String comment = results.getString("comment");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        AddressUsage temp = new AddressUsage(addressUsageRef, address, startDate, note, createdBy, createdDate);
                        this.addressUsages.put(temp.getAddressUsageRef(), temp);
                        this.notes.put(note.getRef(), note);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        person.createAddress(temp, null);
                        this.loadPersonAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("personAddressModifications", temp.getAddressUsageRef()));
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param addressRef
     * @param personRef
     * @param loadedMods 
     */
    private void loadPersonAddressMods(int addressRef, int personRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressUsageExists(addressRef) && this.personExists(personRef) && !loadedMods.isEmpty()) {
            AddressUsage address = this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                AddressUsage tempAddress = (AddressUsage) address;
                tempAddress.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    /**
     * 
     * @param address
     * @param appRef
     * @throws SQLException 
     */
    public void createApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException {
        if(address != null && !this.addressUsageExists(address.getAddressUsageRef()) && this.applicationExists(appRef)) {
            String insertSql = "insert into applicationAddresses (addressUsageRef, addressRef, appRef, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, appRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setInt(col++, address.getNote().getRef());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                addressUsages.put(address.getAddressUsageRef(), address);
                notes.put(address.getNote().getRef(), address.getNote());
            }
        }
    }
    
    /**
     * 
     * @param addressRef
     * @param appRef
     * @throws SQLException 
     */
    public void updateApplicationAddressUsage(int addressRef, int appRef) throws SQLException {
        if(this.addressUsageExists(addressRef) && this.applicationExists(appRef)) {
            AddressUsage address = this.getAddressUsage(addressRef);
            String updateSql = "update applicationAddresses set addressRef=?, startDate=?, endDate=?, comment=? where addressUsageRef=? and appRef=?";
        try (PreparedStatement updateStat = this.con.prepareStatement(updateSql)) {
            int col = 1;
            updateStat.setInt(col++, address.getAddress().getAddressRef());
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
            updateStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getEndDate()));
            updateStat.setString(col++, address.getComment());
            updateStat.setInt(col++, address.getAddressUsageRef());
            updateStat.setInt(col++, appRef);
            updateStat.executeUpdate();
            updateStat.close();
        }
        this.createModifiedBy("applicationAddressModifications", address.getLastModification(), address.getAddressUsageRef());
        }
    }
    
    public void deleteApplicationAddressUsage(int addrRef, int appRef) throws SQLException {
        if (this.addressUsageExists(addrRef) && this.applicationExists(appRef) && this.getApplication(appRef).getCurrentApplicationAddress().getAddressUsageRef() == addrRef && this.canDeleteAddressUsage(addrRef)) {
            String deleteSql = "delete from applicationAddresses where addressUsageRef=" + addrRef + " and appRef=" + appRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddressUsage(addrRef).getNote().getRef());
                    this.addressUsages.remove(addrRef);
                }
            }
        }
    }
    
    /**
     * 
     * @param reference
     * @throws SQLException 
     */
    private void loadApplicationAddresses(int reference) throws SQLException {
        String sql = "select addressUsageRef, addressRef, appRef, startDate, endDate, noteRef, comment, createdBy, createdDate from applicationAddresses order by addressUsageRef";
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
                    int applicationRef = results.getInt("appRef");
                    if (reference == applicationRef) {
                        Date startDate = results.getDate("startDate");
                        Date endDate = results.getDate("endDate");
                        int noteRef = results.getInt("noteRef");
                        String comment = results.getString("comment");
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        AddressUsage temp = new AddressUsage(addressUsageRef, address, startDate, note, createdBy, createdDate);
                        this.addressUsages.put(temp.getAddressUsageRef(), temp);
                        this.notes.put(note.getRef(), note);
                        if(endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        application.setAppAddress(temp, null);
                        this.loadApplicationAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("applicationAddressModifications", temp.getAddressUsageRef()));
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param addressRef
     * @param applicationRef
     * @param loadedMods 
     */
    private void loadApplicationAddressMods(int addressRef, int applicationRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressUsageExists(addressRef) && this.applicationExists(applicationRef) && !loadedMods.isEmpty()) {
            AddressUsage address = this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                AddressUsage tempAddress = address;
                tempAddress.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public boolean canDeleteAddressUsage(int addressRef) {
        return (this.addressUsageExists(addressRef) && !this.getAddressUsage(addressRef).hasBeenModified());
    }
    
    /**
     * 
     * @param title
     * @throws SQLException 
     */
    public void createTitle(Element title) throws SQLException {
        if(!this.titleExists(title.getCode())) {
            this.createElement("titles", title);
            this.titles.put(title.getCode(), title);
            this.notes.put(title.getNote().getRef(), title.getNote());
        }
    }
    
    /**
     * 
     * @param titleCode
     * @throws SQLException 
     */
    public void updateTitle(String titleCode) throws SQLException {
        if(this.titleExists(titleCode)) {
            Element title = this.getTitle(titleCode);
            this.updateElement("titles", title);
            this.createModifiedBy("titleModifications", title.getLastModification(), title.getCode());
        }
    }
    
    public void deleteTitle(String titleCode) throws SQLException {
        if(this.titleExists(titleCode) && this.canDeleteTitle(titleCode)) {
            this.deleteElement("titles", titleCode);
            this.deleteNote(this.getTitle(titleCode).getNote().getRef());
            this.titles.remove(titleCode);
        }
    }
    
    public boolean canDeleteTitle(String titleCode) {
        if(this.titleExists(titleCode) && !this.getTitle(titleCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(titleCode.equals(person.getTitle().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @throws SQLException 
     */
    private void loadTitles() throws SQLException {
        this.titles.clear();
        List<ElementImpl> loadedTitles;
        loadedTitles = this.loadElements("titles");
        if (!loadedTitles.isEmpty()) {
            for (Element temp : loadedTitles) {
                if (temp instanceof Element) {
                    this.titles.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("titleModifications", temp.getCode()));
                }
            }
        }
    }
    
    /**
     * 
     * @param code
     * @return 
     */
    public Element getTitle(String code) {
        if(this.titleExists(code)) {
            return this.titles.get(code);
        }
        return null;
    }
    
    /**
     * 
     * @param gender
     * @throws SQLException 
     */
    public void createGender(Element gender) throws SQLException {
        if(!this.genderExists(gender.getCode())) {
            this.createElement("genders", gender);
            this.genders.put(gender.getCode(), gender);
            this.notes.put(gender.getNote().getRef(), gender.getNote());
        }
    }
    
    /**
     * 
     * @param genderCode
     * @throws SQLException 
     */
    public void updateGender(String genderCode) throws SQLException {
        if(this.genderExists(genderCode)) {
            Element gender = this.getGender(genderCode);
            this.updateElement("genders", gender);
            this.createModifiedBy("genderModifications", gender.getLastModification(), gender.getCode());
        }
    }
    
    public void deleteGender(String genderCode) throws SQLException {
        if(this.genderExists(genderCode) && this.canDeleteGender(genderCode)) {
            this.deleteElement("genders", genderCode);
            this.deleteNote(this.getGender(genderCode).getNote().getRef());
            this.genders.remove(genderCode);
        }
    }
    
    public boolean canDeleteGender(String genderCode) {
        if(this.genderExists(genderCode) && !this.getGender(genderCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(genderCode.equals(person.getGender().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @throws SQLException 
     */
    private void loadGenders() throws SQLException {
        this.genders.clear();
        List<ElementImpl> loadedGenders;
        loadedGenders = this.loadElements("genders");
        if (!loadedGenders.isEmpty()) {
            for (Element temp : loadedGenders) {
                if (temp instanceof Element) {
                    this.genders.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("genderModifications", temp.getCode()));
                }
            }
        }
    }
    
    /**
     * 
     * @param code
     * @return 
     */
    public Element getGender(String code) {
        if(this.genderExists(code)) {
            return this.genders.get(code);
        }
        return null;
    }
    
    /**
     * 
     * @param status
     * @throws SQLException 
     */
    public void createMaritalStatus(Element status) throws SQLException {
        if(!this.maritalStatusExists(status.getCode())) {
            this.createElement("maritalStatuses", status);
            this.maritalStatuses.put(status.getCode(), status);
            this.notes.put(status.getNote().getRef(), status.getNote());
        }
    }
    
    public void updateMaritalStatus(String statusCode) throws SQLException {
        if(this.maritalStatusExists(statusCode)) {
            Element status = this.getMaritalStatus(statusCode);
            this.updateElement("maritalStatuses", status);
            this.createModifiedBy("maritalStatusModifications", status.getLastModification(), status.getCode());
        }
    }
    
    public void deleteMaritalStatus(String statusCode) throws SQLException {
        if(this.maritalStatusExists(statusCode) && this.canDeleteMaritalStatus(statusCode)) {
            this.deleteElement("maritalStatuses", statusCode);
            this.deleteNote(this.getMaritalStatus(statusCode).getNote().getRef());
            this.maritalStatuses.remove(statusCode);
        }
    }
    
    public boolean canDeleteMaritalStatus(String statusCode) {
        if(this.maritalStatusExists(statusCode) && !this.getMaritalStatus(statusCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(statusCode.equals(person.getMaritalStatus().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadMaritalStatuses() throws SQLException {
        this.maritalStatuses.clear();
        List<ElementImpl> loadedStatuses;
        loadedStatuses = this.loadElements("maritalStatuses");
        if (!loadedStatuses.isEmpty()) {
            for (Element temp : loadedStatuses) {
                if (temp instanceof Element) {
                    this.maritalStatuses.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("maritalStatusModifications", temp.getCode()));
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
            this.createElement("ethnicOrigins", ethnicOrigin);
            this.ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
            this.notes.put(ethnicOrigin.getNote().getRef(), ethnicOrigin.getNote());
        }
    }
    
    public void updateEthnicOrigin(String originCode) throws SQLException {
        if(this.ethnicOriginExists(originCode)) {
            Element origin = this.getEthnicOrigin(originCode);
            this.updateElement("ethnicOrigins", origin);
            this.createModifiedBy("ethnicOriginModifications", origin.getLastModification(), origin.getCode());
        }
    }
    
    public void deleteEthnicOrigin(String originCode) throws SQLException {
        if(this.ethnicOriginExists(originCode) && this.canDeleteEthnicOrigin(originCode)) {
            this.deleteElement("ethnicOrigins", originCode);
            this.deleteNote(this.getEthnicOrigin(originCode).getNote().getRef());
            this.ethnicOrigins.remove(originCode);
        }
    }
    
    public boolean canDeleteEthnicOrigin(String originCode) {
        if(this.ethnicOriginExists(originCode) && !this.getEthnicOrigin(originCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(originCode.equals(person.getEthnicOrigin().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadEthnicOrigins() throws SQLException {
        this.ethnicOrigins.clear();
        List<ElementImpl> loadedEthnicOrigins;
        loadedEthnicOrigins = this.loadElements("ethnicOrigins");
        if (!loadedEthnicOrigins.isEmpty()) {
            for (Element temp : loadedEthnicOrigins) {
                if (temp instanceof Element) {
                    this.ethnicOrigins.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("ethnicOriginModifications", temp.getCode()));
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
            this.createElement("languages", language);
            this.languages.put(language.getCode(), language);
            this.notes.put(language.getNote().getRef(), language.getNote());
        }
    }
    
    public void updateLanguage(String languageCode) throws SQLException {
        if(this.languageExists(languageCode)) {
            Element language = this.getLanguage(languageCode);
            this.updateElement("languages", language);
            this.createModifiedBy("languageModifications", language.getLastModification(), language.getCode());
        }
    }
    
    public void deleteLanguage(String languageCode) throws SQLException {
        if(this.languageExists(languageCode) && this.canDeleteLanguage(languageCode)) {
            this.deleteElement("languages", languageCode);
            this.deleteNote(this.getLanguage(languageCode).getNote().getRef());
            this.languages.remove(languageCode);
        }
    }
    
    public boolean canDeleteLanguage(String languageCode) {
        if(this.languageExists(languageCode) && !this.getLanguage(languageCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(languageCode.equals(person.getLanguage().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadLanguages() throws SQLException {
        this.languages.clear();
        List<ElementImpl> loadedLanguages;
        loadedLanguages = this.loadElements("languages");
        if (!loadedLanguages.isEmpty()) {
            for (Element temp : loadedLanguages) {
                if (temp instanceof Element) {
                    this.languages.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("languageModifications", temp.getCode()));
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
            this.createElement("nationalities", nationality);
            this.nationalities.put(nationality.getCode(), nationality);
            this.notes.put(nationality.getNote().getRef(), nationality.getNote());
        }
    }
    
    public void updateNationality(String nationalityCode) throws SQLException {
        if(this.nationalityExists(nationalityCode)) {
            Element nationality = this.getNationality(nationalityCode);
            this.updateElement("nationalities", nationality);
            this.createModifiedBy("nationalityModifications", nationality.getLastModification(), nationality.getCode());
        }
    }
    
    public void deleteNationality(String nationalityCode) throws SQLException {
        if(this.nationalityExists(nationalityCode) && this.canDeleteNationality(nationalityCode)) {
            this.deleteElement("nationalitys", nationalityCode);
            this.deleteNote(this.getNationality(nationalityCode).getNote().getRef());
            this.nationalities.remove(nationalityCode);
        }
    }
    
    public boolean canDeleteNationality(String nationalityCode) {
        if(this.nationalityExists(nationalityCode) && !this.getNationality(nationalityCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(nationalityCode.equals(person.getNationality().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadNationalties() throws SQLException {
        this.nationalities.clear();
        List<ElementImpl> loadedNationalities;
        loadedNationalities = this.loadElements("nationalities");
        if (!loadedNationalities.isEmpty()) {
            for (Element temp : loadedNationalities) {
                if (temp instanceof Element) {
                    this.nationalities.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("nationalityModifications", temp.getCode()));
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
            this.createElement("sexualities", sex);
            this.sexualities.put(sex.getCode(), sex);
            this.notes.put(sex.getNote().getRef(), sex.getNote());
        }
    }
    
    public void updateSexuality(String sexCode) throws SQLException {
        if(this.sexualityExists(sexCode)) {
            Element sex = this.getSexuality(sexCode);
            this.updateElement("sexualities", sex);
            this.createModifiedBy("sexualityModifications", sex.getLastModification(), sex.getCode());
        }
    }
    
    public void deleteSexuality(String sexualityCode) throws SQLException {
        if(this.sexualityExists(sexualityCode) && this.canDeleteSexuality(sexualityCode)) {
            this.deleteElement("sexualitys", sexualityCode);
            this.deleteNote(this.getSexuality(sexualityCode).getNote().getRef());
            this.sexualities.remove(sexualityCode);
        }
    }
    
    public boolean canDeleteSexuality(String sexualityCode) {
        if(this.sexualityExists(sexualityCode) && !this.getSexuality(sexualityCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(sexualityCode.equals(person.getSexuality().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadSexualities() throws SQLException {
        this.sexualities.clear();
        List<ElementImpl> loadedSexualities;
        loadedSexualities = this.loadElements("sexualities");
        if (!loadedSexualities.isEmpty()) {
            for (Element temp : loadedSexualities) {
                if (temp instanceof Element) {
                    this.sexualities.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("sexualityModifications", temp.getCode()));
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
            this.createElement("religions", religion);
            this.religions.put(religion.getCode(), religion);
            this.notes.put(religion.getNote().getRef(), religion.getNote());
        }
    }
    
    public void updateReligion(String religionCode) throws SQLException {
        if(this.religionExists(religionCode)) {
            Element religion = this.getReligion(religionCode);
            this.updateElement("religions", religion);
            this.createModifiedBy("religionModifications", religion.getLastModification(), religion.getCode());
        }
    }
    
    public void deleteReligion(String religionCode) throws SQLException {
        if(this.religionExists(religionCode) && this.canDeleteReligion(religionCode)) {
            this.deleteElement("religions", religionCode);
            this.deleteNote(this.getReligion(religionCode).getNote().getRef());
            this.religions.remove(religionCode);
        }
    }
    
    public boolean canDeleteReligion(String religionCode) {
        if(this.religionExists(religionCode) && !this.getReligion(religionCode).hasBeenModified()) {
            for(Person person : this.getPeople()) {
                if(religionCode.equals(person.getReligion().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadReligions() throws SQLException {
        this.religions.clear();
        List<ElementImpl> loadedReligions;
        loadedReligions = this.loadElements("religions");
        if (!loadedReligions.isEmpty()) {
            for (Element temp : loadedReligions) {
                if (temp instanceof Element) {
                    this.religions.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("religionModifications", temp.getCode()));
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
            String insertSql = "insert into addresses (addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, "
                    + "streetNumber, street, area, town, country, postcode, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                insertStat.setInt(col++, address.getNote().getRef());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                this.addresses.put(address.getAddressRef(), address);
                this.notes.put(address.getNote().getRef(), address.getNote());
            }
        }
    }
    
    public void updateAddress(int addressRef) throws SQLException {
        if(this.addressExists(addressRef)) {
            Address address = this.getAddress(addressRef);
            String updateSql = "update addresses set buildingNumber=?, buildingName=?, subStreetNumber=?, subStreet=?, "
                    + "streetNumber=?, street=?, area=?, town=?, country=?, postcode=?, comment=? where addressRef=?";
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
                updateStat.setString(col++, address.getComment());
                updateStat.setInt(col++, address.getAddressRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("addressModifications", address.getLastModification(), address.getAddressRef());
        }
    }
    
    public void deleteAddress(int addrRef) throws SQLException {
        if (this.addressExists(addrRef) && this.canDeleteAddress(addrRef)) {
            String deleteSql = "delete from Addresses where addressRef=" + addrRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddress(addrRef).getNote().getRef());
                    this.addresses.remove(addrRef);
                }
            }
        }
    }
    
    public boolean canDeleteAddress(int addressRef) {
        if (this.addressExists(addressRef) && !this.getAddress(addressRef).hasBeenModified()) {
            for(AddressUsage address : this.getAddressUsages()) {
                if(address.getAddress().getAddressRef() == addressRef) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadAddresses() throws SQLException {
        String sql = "select addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, "
                    + "town, country, postcode, noteRef, comment, createdBy, createdDate from addresses order by addressRef";
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
                int noteRef = results.getInt("noteRef");
                String comment = results.getString("comment");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                Address temp = new Address(addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, note, createdBy, createdDate);
                this.addresses.put(temp.getAddressRef(), temp);
                this.notes.put(note.getRef(), note);
                this.loadAddressMods(temp.getAddressRef(), this.loadModMap("addressModifications", temp.getAddressRef()));
            }
        }
    }
    
    private void loadAddressMods(int addressRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressExists(addressRef) && !loadedMods.isEmpty()) {
            Address address = this.getAddress(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Address tempAddress = (Address) address;
                tempAddress.modifiedBy(tempMod);
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
            properties.put(property.getPropRef(), property);
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
    
    public void deleteProperty(int propRef) throws SQLException {
        if (this.propertyExists(propRef) && this.canDeleteProperty(propRef)) {
            String deleteSql = "delete from Properties where propRef=" + propRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.properties.remove(propRef);
                }
            }
        }
    }
    
    public boolean canDeleteProperty(int propRef) {
        if (this.propertyExists(propRef) && !this.getProperty(propRef).hasBeenModified()) {
            for(Lease lease : this.getLeases()) {
                if(lease.getPropertyRef() == propRef) {
                    return false;
                }
            }
            for(Tenancy tenancy : this.getTenancies()) {
                if(tenancy.getPropertyRef() == propRef) {
                    return false;
                }
            }
            return true;
        }
        return false;
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
                this.properties.put(temp.getPropRef(), temp);
                this.loadPropertyElementValues(temp.getPropRef());
                this.loadPropertyMods(temp.getPropRef(), this.loadModMap("propertyModifications", temp.getPropRef()));
                this.loadPropertyNotes(temp.getPropRef(), this.loadNoteMap("propertyNotes", temp.getPropRef()));
                this.loadPropertyDocs(temp.getPropRef(), this.loadDocMap("propertyDocuments", temp.getPropRef()));
                temp.setLeaseEndDate(leaseEndDate, null);
                temp.setPropStatus(propStatus, null);
            }
        }
    }
    
    private void loadPropertyMods(int propRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.propertyExists(propRef) && !loadadMods.isEmpty()) {
            Property property = this.getProperty(propRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                property.modifiedBy(tempMod);
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
    
    private void loadPropertyDocs(int propertyRef, Map<Integer, Document> loadedDocs) {
        if (this.propertyExists(propertyRef) && !loadedDocs.isEmpty()) {
            Property property = this.getProperty(propertyRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                property.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPropertyDoc(int propertyRef, DocumentImpl document) throws SQLException {
        if(this.propertyExists(propertyRef) && !this.documentExists(document.getDocumentRef()) && this.getProperty(propertyRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("propertyDocuments", propertyRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deletePropertyDoc(int propertyRef, int documentRef) throws SQLException {
        if(this.propertyExists(propertyRef) && this.documentExists(documentRef) && this.getProperty(propertyRef).hasDocument(documentRef)) {
            this.deleteDocument("propertyDocuments", propertyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadPropertyNotes(int propRef, Map<Integer, Note> loadadNotes) {
        if (this.propertyExists(propRef) && !loadadNotes.isEmpty()) {
            Property property = this.getProperty(propRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                property.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPropertyNote(int propRef, Note note) throws SQLException {
        if(this.propertyExists(propRef) && !this.noteExists(note.getRef()) && this.getProperty(propRef).hasNote(note.getRef())) {
            this.createNote("propertyNotes", propRef, note);
        }
    }
    
    public void updatePropertyNote(int propRef, int noteRef) throws SQLException {
        if(this.propertyExists(propRef) && this.noteExists(noteRef)) {
            this.updateNote("propertyNotes", propRef, noteRef);
        }
    }
    
    public void deletePropertyNote(int propRef, int noteRef) throws SQLException {
        if (this.propertyExists(propRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("propertyNotes", propRef, noteRef);
        }
    }
    
    public void createPropertyType(Element type) throws SQLException {
        if(!this.propTypeExists(type.getCode())) {
            this.createElement("propertyTypes", type);
            this.propertyTypes.put(type.getCode(), type);
            this.notes.put(type.getNote().getRef(), type.getNote());
        }
    }
    
    public void updatePropertyType(String typeCode) throws SQLException {
        if(this.propTypeExists(typeCode)) {
            Element type = this.getPropertyType(typeCode);
            this.updateElement("propertyTypes", type);
            this.createModifiedBy("propertyTypeModifications", type.getLastModification(), type.getCode());
        }
    }
    
    public void deletePropertyType(String typeCode) throws SQLException {
        if(this.propTypeExists(typeCode) && this.canDeletePropertyType(typeCode)) {
            this.deleteElement("propertyTypes", typeCode);
            this.deleteNote(this.getPropertyType(typeCode).getNote().getRef());
            this.propertyTypes.remove(typeCode);
        }
    }
    
    public boolean canDeletePropertyType(String typeCode) {
        if(this.propTypeExists(typeCode) && !this.getPropertyType(typeCode).hasBeenModified()) {
            for(Property property : this.getProperties()) {
                if(typeCode.equals(property.getPropType().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadPropertyTypes() throws SQLException {
        this.propertyTypes.clear();
        List<ElementImpl> loadedPropTypes;
        loadedPropTypes = this.loadElements("propertyTypes");
        if (!loadedPropTypes.isEmpty()) {
            for (Element temp : loadedPropTypes) {
                if (temp instanceof Element) {
                    this.propertyTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertyTypeModifications", temp.getCode()));
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
            this.createElement("propertySubTypes", type);
            this.propertySubTypes.put(type.getCode(), type);
            this.notes.put(type.getNote().getRef(), type.getNote());
        }
    }
    
    public void updatePropertySubType(String typeCode) throws SQLException {
        Element type = this.getPropertySubType(typeCode);
        if(this.propSubTypeExists(type.getCode())) {
            this.updateElement("propertySubTypes", type);
            this.createModifiedBy("propertySubTypeModifications", type.getLastModification(), type.getCode());
        }
    }
    
    public void deletePropertySubType(String typeCode) throws SQLException {
        if(this.propSubTypeExists(typeCode) && this.canDeletePropertySubType(typeCode)) {
            this.deleteElement("propertySubTypes", typeCode);
            this.deleteNote(this.getPropertySubType(typeCode).getNote().getRef());
            this.propertySubTypes.remove(typeCode);
        }
    }
    
    public boolean canDeletePropertySubType(String typeCode) {
        if(this.propSubTypeExists(typeCode) && !this.getPropertySubType(typeCode).hasBeenModified()) {
            for(Property property : this.getProperties()) {
                if(typeCode.equals(property.getPropSubType().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadPropertySubTypes() throws SQLException {
        this.propertySubTypes.clear();
        List<ElementImpl> loadedPropSubTypes;
        loadedPropSubTypes = this.loadElements("propertySubTypes");
        if (!loadedPropSubTypes.isEmpty()) {
            for (Element temp : loadedPropSubTypes) {
                if (temp instanceof Element) {
                    this.propertySubTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertySubTypeModifications", temp.getCode()));
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
    
    private void createPropertyElementValues(int propertyRef, List<PropertyElementInterface> propertyElements) throws SQLException {
        if (!propertyElements.isEmpty() && this.propertyExists(propertyRef)) {
            for (PropertyElementInterface temp : propertyElements) {
                this.createPropertyElementValue(propertyRef, (PropertyElement) temp);
            }
        }
    }
    
    public void createPropertyElementValue(int propertyRef, PropertyElement propertyElement) throws SQLException {
        if (propertyElement != null && this.propertyExists(propertyRef) && !this.propertyElementValueExists(propertyElement.getPropertyElementRef()) && this.propElementExists(propertyElement.getElement().getCode()) && this.getProperty(propertyRef).hasPropElement(propertyElement.getPropertyElementRef())) {
            String insertSql = "";
            if (propertyElement.isCharge()) {
                insertSql = "insert into propertyElementValues (propertyElementRef, propRef, elementCode, doubleValue, startDate, endDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else if (!propertyElement.isCharge()) {
                insertSql = "insert into propertyElementValues (propertyElementRef, propRef, elementCode, stringValue, startDate, endDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                insertStat.setInt(col++, propertyElement.getNote().getRef());
                insertStat.setString(col++, propertyElement.getComment());
                insertStat.setString(col++, propertyElement.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.propertyElementValues.put(propertyElement.getPropertyElementRef(), propertyElement);
            this.notes.put(propertyElement.getNote().getRef(), propertyElement.getNote());
        }
    }

    public void updatePropertyElementValue(int propertyRef, int propElementRef) throws SQLException {
        if (this.propertyExists(propertyRef) && this.propertyElementValueExists(propElementRef)) {
            PropertyElementInterface propertyElement = this.getPropertyElementValue(propElementRef);
            if (this.propElementExists(propertyElement.getElementCode())) {
                String updateSql = "";
                if (propertyElement.isCharge()) {
                    updateSql = "update propertyElementValues set doubleValue=?, startDate=?, endDate=?, comment=? where propertyElementRef=? and propRef=? and elementCode=?";
                } else if (!propertyElement.isCharge()) {
                    updateSql = "update propertyElementValues set stringValue=?, startDate=?, endDate=?, comment=? where propertyElementRef=? and propRef=? and elementCode=?";
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
                    updateStat.setString(col++, propertyElement.getComment());
                    updateStat.setInt(col++, propertyElement.getPropertyElementRef());
                    updateStat.setInt(col++, propertyRef);
                    updateStat.setString(col++, propertyElement.getElementCode());
                    updateStat.executeUpdate();
                    updateStat.close();
                }
            }
            this.createModifiedBy("propertyElementValueModifications", propertyElement.getLastModification(), propertyElement.getPropertyElementRef()); // 
        }
    }
    
    public void deletePropertyElementValue(int propElementRef, int propRef) throws SQLException {
        if (this.propertyExists(propRef) && this.propertyElementValueExists(propElementRef) && this.getProperty(propRef).hasPropElement(propElementRef) && this.canDeletePropertyElementValue(propElementRef, propRef)) {
            String deleteSql = "delete from PropertyElementValues where propertyElementRef=" + propElementRef + " and propRef=" + propRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >= 1) {
                    propertyElementValues.remove(propElementRef);
                    this.deleteNote(this.getPropertyElementValue(propElementRef).getNote().getRef());
                }
            }
        }
    }
    
    public boolean canDeletePropertyElementValue(int propElementRef, int propRef) {
        return this.propertyExists(propRef) && !this.getProperty(propRef).getPropElement(propElementRef).hasBeenModified();
    }

    private void loadPropertyElementValues(int propRef) throws SQLException {
        if (this.propertyExists(propRef)) {
            Property property = this.getProperty(propRef);
            String sql = "select propertyElementRef, propRef, elementCode, stringValue, doubleValue, startDate, endDate, noteRef, comment, createdBy, createdDate from propertyElementValues order by createdDate";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);
                while (results.next()) {
                    int propertyElementRef = results.getInt("propertyElementRef");
                    int propertyRef = results.getInt("propRef");
                    if (this.propertyExists(propRef) && propertyRef == propRef) {
                        String elementCode = results.getString("elementCode");
                        Element element;
                        if (this.propElementExists(elementCode)) {
                            element = this.getPropElement(elementCode);
                            Date startDate = results.getDate("startDate");
                            Date endDate = results.getDate("endDate");
                            String stringValue = results.getString("stringValue");
                            double doubleValue = results.getDouble("doubleValue");
                            int noteRef = results.getInt("noteRef");
                            String comment = results.getString("comment");
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            
                            Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                            PropertyElement temp = new PropertyElement(propertyElementRef, element, startDate, stringValue == null, stringValue, doubleValue, note, createdBy, createdDate);
                            if (endDate != null) {
                                temp.setEndDate(endDate, null);
                            }
                            this.propertyElementValues.put(temp.getPropertyElementRef(), temp);
                            this.notes.put(note.getRef(), note);
                            this.loadPropElementMods(temp, this.loadModMap("PropertyElementValueModifications", temp.getPropertyElementRef()));
                            property.createPropertyElement(temp, null);
                        }
                    }
                }
            }
        }
    }
    
    private void loadPropElementMods(PropertyElement element, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.propElementExists(element.getElementCode()) && !loadadMods.isEmpty()) {
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                element.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public PropertyElement getPropertyElementValue(int propElementRef) {
        if(this.propertyElementValueExists(propElementRef)) {
            return this.propertyElementValues.get(propElementRef);
        }
        return null;
    }
    
    public void createPropElement(Element element) throws SQLException {
        if(!this.propElementExists(element.getCode())) {
            this.createElement("propertyElements", element);
            this.propertyElements.put(element.getCode(), element);
            this.notes.put(element.getNote().getRef(), element.getNote());
        }
    }
    
    public void updatePropertyElement(String elementCode) throws SQLException {
        Element element = this.getPropElement(elementCode);
        if(this.propElementExists(element.getCode())) {
            this.updateElement("propertyElements", element);
            this.createModifiedBy("propertyElementModifications", element.getLastModification(), element.getCode());
        }
    }
    
    public void deletePropertyElement(String elementCode) throws SQLException {
        if(this.propElementExists(elementCode) && this.canDeletePropertyElement(elementCode)) {
            this.deleteElement("propertyElements", elementCode);
            this.deleteNote(this.getPropElement(elementCode).getNote().getRef());
            this.propertyElements.remove(elementCode);
        }
    }
    
    public boolean canDeletePropertyElement(String elementCode) {
        if(this.propElementExists(elementCode) && !this.getPropElement(elementCode).hasBeenModified()) {
            for(Property property : this.getProperties()) {
                if(property.hasPropElement(elementCode)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadPropertyElements() throws SQLException {
        this.propertyElements.clear();
        List<ElementImpl> loadedPropertyElements;
        loadedPropertyElements = this.loadElements("propertyElements");
        if (!loadedPropertyElements.isEmpty()) {
            for (Element temp : loadedPropertyElements) {
                if (temp instanceof Element) {
                    this.propertyElements.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertyElementModifications", temp.getCode()));
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
                insertStat.setString(col++, person.getNationality().getCode());
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
            people.put(person.getPersonRef(), person);
        }
    }
    
    public void updatePerson(int personRef) throws SQLException {
        if(this.personExists(personRef)) {
            Person person = this.getPerson(personRef);
            String updateSql = "update people set titleCode=?, forename=?, middleNames=?, surname=?, dateOfBirth=?, nationalInsurance=?, "
                    + "genderCode=?, maritalStatusCode=?, ethnicOriginCode=?, languageCode=?, nationalityCode=?, sexualityCode=?, "
                    + "religionCode=? where personRef=?";
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
    
    public void deletePerson(int personRef) throws SQLException {
        if (this.personExists(personRef) && this.canDeletePerson(personRef)) {
            String deleteSql = "delete from people where personRef=" + personRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.people.remove(personRef);
                }
            }
        }
    }
    
    public boolean canDeletePerson(int personRef) {
        return this.propertyExists(personRef) && !this.getPerson(personRef).hasBeenModified() && !this.personInvPartyExists(personRef) && !this.personEmployeeExists(personRef) && !this.personLandlordExists(personRef);
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
                if(this.ethnicOriginExists(results.getString("ethnicOriginCode"))) {
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
                this.loadPersonMods(temp.getPersonRef(), this.loadModMap("personModifications", temp.getPersonRef()));
                this.loadPersonNotes(temp.getPersonRef(), this.loadNoteMap("personNotes", temp.getPersonRef()));
                this.loadPersonDocs(temp.getPersonRef(), this.loadDocMap("personDocuments", temp.getPersonRef()));
                this.loadPersonContacts(temp.getPersonRef());
                this.loadPersonAddresses(temp.getPersonRef());
            }
        }
    }
    
    private void loadPersonMods(int personRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.personExists(personRef) && !loadedMods.isEmpty()) {
            Person person = this.getPerson(personRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Person tempPerson = (Person) person;
                tempPerson.modifiedBy(tempMod);
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
    
    private void loadPersonDocs(int personRef, Map<Integer, Document> loadedDocs) {
        if (this.personExists(personRef) && !loadedDocs.isEmpty()) {
            Person person = this.getPerson(personRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                person.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPersonDoc(int appRef, DocumentImpl document) throws SQLException {
        if(this.personExists(appRef) && !this.documentExists(document.getDocumentRef()) && this.getPerson(appRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("personDocuments", appRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deletePersonDoc(int appRef, int documentRef) throws SQLException {
        if(this.personExists(appRef) && this.documentExists(documentRef) && this.getPerson(appRef).hasDocument(documentRef)) {
            this.deleteDocument("personDocuments", appRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadPersonNotes(int personRef, Map<Integer, Note> loadadNotes) {
        if (this.personExists(personRef) && !loadadNotes.isEmpty()) {
            Person person = this.getPerson(personRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                person.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createPersonNote(int personRef, Note note) throws SQLException {
        if(this.personExists(personRef) && !this.noteExists(note.getRef()) && this.getPerson(personRef).hasNote(note.getRef())) {
            this.createNote("personNotes", personRef, note);
        }
    }
    
    public void updatePersonNote(int personRef, int noteRef) throws SQLException {
        if(this.personExists(personRef) && this.noteExists(noteRef)) {
            this.updateNote("personNotes", personRef, noteRef);
        }
    }
    
    public void deletePersonNote(int personRef, int noteRef) throws SQLException {
        if (this.personExists(personRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("personNotes", personRef, noteRef);
        }
    }
    
    public void createInvolvedParty(InvolvedParty invParty) throws SQLException {
        if(!this.invPartyExists(invParty.getInvolvedPartyRef())) {
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
                insertStat.setString(col++, invParty.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            involvedParties.put(invParty.getInvolvedPartyRef(), invParty);
        }
    }

    public void updateInvolvedParty(int invPartyRef) throws SQLException {
        if (this.invPartyExists(invPartyRef)) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            String updateSql = "update involvedParties set jointApplicantInd=?, mainApplicantInd=?, startDate=?, "
                    + "endDate=?, endReasonCode=?, relationshipCode=? where invPartyRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setBoolean(col++, invParty.isJointInd());
                updateStat.setBoolean(col++, invParty.isMainInd());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getEndDate()));
                if(invParty.getEndReason() != null) {
                    updateStat.setString(col++, invParty.getEndReason().getCode());
                } else {
                    updateStat.setString(col++, null);
                }
                updateStat.setString(col++, invParty.getRelationship().getCode());
                updateStat.setInt(col++, invParty.getInvolvedPartyRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("involvedPartyModifications", invParty.getLastModification(), invParty.getInvolvedPartyRef());
        }
    }
    
    public void deleteInvolvedParty(int invPartyRef) throws SQLException {
        if (this.personExists(invPartyRef) && this.canDeleteInvolvedParty(invPartyRef)) {
            String deleteSql = "delete from people where personRef=" + invPartyRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.people.remove(invPartyRef);
                }
            }
        }
    }
    
    public boolean canDeleteInvolvedParty(int invPartyRef) {
        return (this.invPartyExists(invPartyRef) && !this.getInvolvedParty(invPartyRef).isMainInd() && !this.getInvolvedParty(invPartyRef).hasBeenModified());
    }
    
    private void loadInvolvedParties(int reference) throws SQLException {
        this.involvedParties.clear();
        String sql = "select invPartyRef, appRef, personRef, jointApplicantInd, mainApplicantInd, startDate, endDate, endReasonCode, "
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
                        this.loadInvolvedPartyMods(temp.getInvolvedPartyRef(), this.loadModMap("involvedPartyModifications", temp.getInvolvedPartyRef()));
                        this.loadInvolvedPartyNotes(temp.getInvolvedPartyRef(), this.loadNoteMap("involvedPartyNotes", temp.getInvolvedPartyRef()));
                    }
                }
            }
        }
    }
    
    private void loadInvolvedPartyMods(int invPartyRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.invPartyExists(invPartyRef) && !loadedMods.isEmpty()) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                InvolvedParty tempInvParty = invParty;
                tempInvParty.modifiedBy(tempMod);
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
    
    private void loadInvolvedPartyNotes(int invPartyRef, Map<Integer, Note> loadadNotes) {
        if (this.invPartyExists(invPartyRef) && !loadadNotes.isEmpty()) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                invParty.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createInvolvedPartyNote(int invPartyRef, Note note) throws SQLException {
        if(this.invPartyExists(invPartyRef) && !this.noteExists(note.getRef()) && this.getInvolvedParty(invPartyRef).hasNote(note.getRef())) {
            this.createNote("involvedPartyNotes", invPartyRef, note);
        }
    }
    
    public void updateInvolvedPartyNote(int invPartyRef, int noteRef) throws SQLException {
        if(this.invPartyExists(invPartyRef) && this.noteExists(noteRef)) {
            this.updateNote("involvedPartyNotes", invPartyRef, noteRef);
        }
    }
    
    public void deleteInvolvedPartyNote(int invPartyRef, int noteRef) throws SQLException {
        if (this.invPartyExists(invPartyRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("involvedPartyNotes", invPartyRef, noteRef);
        }
    }
    
    public void createEndReason(Element endReason) throws SQLException {
        if(!this.endReasonExists(endReason.getCode())) {
            this.createElement("endReasons", endReason);
            this.endReasons.put(endReason.getCode(), endReason);
            this.notes.put(endReason.getNote().getRef(), endReason.getNote());
        }
    }
    
    public void updateEndReason(String endReasonCode) throws SQLException {
        if(this.endReasonExists(endReasonCode)) {
            Element endReason = this.getEndReason(endReasonCode);
            this.updateElement("endReasons", endReason);
            this.createModifiedBy("endReasonModifications", endReason.getLastModification(), endReason.getCode());
        }
    }
    
    public void deleteEndReason(String endReason) throws SQLException {
        if(this.endReasonExists(endReason) && this.canDeleteEndReason(endReason)) {
            this.deleteElement("endReasons", endReason);
            this.deleteNote(this.getEndReason(endReason).getNote().getRef());
            endReasons.remove(endReason);
        }
    }
    
    public boolean canDeleteEndReason(String endReason) {
        if(this.endReasonExists(endReason) && !this.getEndReason(endReason).hasBeenModified()) {
            for(InvolvedParty invParty : this.getInvolvedParties()) {
                if(invParty.getEndReason() != null && endReason.equals(invParty.getEndReason().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadEndReasons() throws SQLException {
        this.endReasons.clear();
        List<ElementImpl> loadedEndReasons;
        loadedEndReasons = this.loadElements("endReasons");
        if (!loadedEndReasons.isEmpty()) {
            for (Element temp : loadedEndReasons) {
                if (temp instanceof Element) {
                    this.endReasons.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("endReasonModifications", temp.getCode()));
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
            this.createElement("relationships", relationship);
            this.relationships.put(relationship.getCode(), relationship);
            this.notes.put(relationship.getNote().getRef(), relationship.getNote());
        }
    }
    
    public void updateRelationship(String relationshipCode) throws SQLException {
        if(this.relationshipExists(relationshipCode)) {
            Element relationship = this.getRelationship(relationshipCode);
            this.updateElement("relationships", relationship);
            this.createModifiedBy("relationshipModifications", relationship.getLastModification(), relationship.getCode());
        }
    }
    
    public void deleteRelationship(String relationship) throws SQLException {
        if(this.relationshipExists(relationship) && this.canDeleteRelationship(relationship)) {
            this.deleteElement("relationships", relationship);
            this.deleteNote(this.getRelationship(relationship).getNote().getRef());
            relationships.remove(relationship);
        }
    }
    
    public boolean canDeleteRelationship(String relationship) {
        if(this.relationshipExists(relationship) && !this.getRelationship(relationship).hasBeenModified()) {
            for(InvolvedParty invParty : this.getInvolvedParties()) {
                if(relationship.equals(invParty.getRelationship().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadRelationships() throws SQLException {
        this.relationships.clear();
        List<ElementImpl> loadedRelationships;
        loadedRelationships = this.loadElements("relationships");
        if (!loadedRelationships.isEmpty()) {
            for (Element temp : loadedRelationships) {
                if (temp instanceof Element) {
                    this.relationships.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("relationshipModifications", temp.getCode()));
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
            String insertSql = "insert into applications (appRef, appCorrName, appStartDate, appStatus, "
                    + "createdBy, createdDate) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, application.getApplicationRef());
                insertStat.setString(col++, application.getAppCorrName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                insertStat.setString(col++, application.getAppStatusCode());
                insertStat.setString(col++, application.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.applications.put(application.getApplicationRef(), application);
        }
    }
    
    public void updateApplication(int appRef) throws SQLException {
        if(this.applicationExists(appRef)) {
            Application application = this.getApplication(appRef);
            String updateSql;
            if(application.hasTenancyRef()) {
                updateSql = "update applications set appCorrName=?, appStartDate=?, appEndDate=?, "
                    + "appStatus=?, tenancyRef=? where appRef=?";
            } else { // No Tenancy
                updateSql = "update applications set appCorrName=?, appStartDate=?, appEndDate=?, "
                    + "appStatus=? where appRef=?";
            }
             
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, application.getAppCorrName());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(application.getAppEndDate()));
                updateStat.setString(col++, application.getAppStatusCode());
                if(application.hasTenancyRef()) {
                    updateStat.setInt(col++, application.getTenancyRef());
                }
                updateStat.setInt(col++, application.getApplicationRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("applicationModifications", application.getLastModification(), application.getApplicationRef());
        }
    }
    
    public void deleteApplication(int appRef) throws SQLException {
        if (this.applicationExists(appRef) && this.canDeleteApplication(appRef)) {
            String deleteSql = "delete from applications where appRef=" + appRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.applications.remove(appRef);
                }
            }
        }
    }
    
    public boolean canDeleteApplication(int appRef) {
        if (this.applicationExists(appRef) && this.getApplication(appRef).hasBeenModified()) {
            for(InvolvedPartyInterface invParty : this.getApplication(appRef).getHousehold()) {
                if(invParty.hasBeenModified()) {
                    return false;
                }
            }
            for(AddressUsageInterface address : this.getApplication(appRef).getApplicationAddressess()) {
                if(address.hasBeenModified()) {
                    return false;
                }
            }
            for(Tenancy tenancy : this.getTenancies()) {
                if(tenancy.getApplicationRef() == appRef) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadApplications() throws SQLException {
        this.applications.clear();
        String sql = "select appRef, appCorrName, appStartDate, appEndDate, appStatus, "
                    + "tenancyRef, createdBy, createdDate from applications order by appRef";
        
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            while (results.next()) {
                
                int appRef = results.getInt("appRef");
                
                String appCorrName = results.getString("appCorrName");
                Date appStartDate = results.getDate("appStartDate");
                Date appEndDate = results.getDate("appEndDate");
                String appStatus = results.getString("appStatus");
                int tenancyRef = results.getInt("tenancyRef");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                Application temp = new Application(appRef, appCorrName, appStartDate, appStatus, createdBy, createdDate);
                
                if(appEndDate != null) {
                    temp.setEndDate(appEndDate, null);
                }
                
                if(tenancyRef >= 1) {
                    temp.setTenancy(tenancyRef, null);
                }
                
                this.applications.put(temp.getApplicationRef(), temp);
                this.loadApplicationMods(temp.getApplicationRef(), this.loadModMap("applicationModifications", temp.getApplicationRef()));
                this.loadApplicationNotes(temp.getApplicationRef(), this.loadNoteMap("applicationNotes", temp.getApplicationRef()));
                this.loadApplicationDocs(temp.getApplicationRef(), this.loadDocMap("applicationDocuments", temp.getApplicationRef()));
                this.loadApplicationAddresses(temp.getApplicationRef());
                this.loadInvolvedParties(temp.getApplicationRef());
                this.loadPropertiesInterestedIn(temp.getApplicationRef());
            }
        }
    }
    
    private void loadApplicationMods(int appRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.applicationExists(appRef) && !loadedMods.isEmpty()) {
            Application application = this.getApplication(appRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Application tempApp = (Application) application;
                tempApp.modifiedBy(tempMod);
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
    
    private void loadApplicationDocs(int appRef, Map<Integer, Document> loadedDocs) {
        if (this.applicationExists(appRef) && !loadedDocs.isEmpty()) {
            Application application = this.getApplication(appRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                application.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createApplicationDoc(int appRef, DocumentImpl document) throws SQLException {
        if(this.applicationExists(appRef) && !this.documentExists(document.getDocumentRef()) && this.getApplication(appRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("applicationDocuments", appRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteApplicationDoc(int appRef, int documentRef) throws SQLException {
        if(this.applicationExists(appRef) && this.documentExists(documentRef) && this.getApplication(appRef).hasDocument(documentRef)) {
            this.deleteDocument("applicationDocuments", appRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadApplicationNotes(int appRef, Map<Integer, Note> loadadNotes) {
        if (this.applicationExists(appRef) && !loadadNotes.isEmpty()) {
            Application application = this.getApplication(appRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                application.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createApplicationNote(int appRef, Note note) throws SQLException {
        if(this.applicationExists(appRef) && !this.noteExists(note.getRef()) && this.getApplication(appRef).hasNote(note.getRef())) {
            this.createNote("applicationNotes", appRef, note);
        }
    }
    
    public void updateApplicationNote(int appRef, int noteRef) throws SQLException {
        if(this.applicationExists(appRef) && this.noteExists(noteRef)) {
            this.updateNote("applicationNotes", appRef, noteRef);
        }
    }
    
    public void deleteApplicationNote(int appRef, int noteRef) throws SQLException {
        if (this.applicationExists(appRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("applicationNotes", appRef, noteRef);
        }
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
                    String insertSql = "insert into propertyInterest (appRef, propRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                
                    System.out.println("Inserting property " + propRef + " for appliction " + appRef);
                    insertStat.setInt(col++, appRef);
                    insertStat.setInt(col++, propRef);
                    insertStat.setBoolean(col++, true);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update propertyInterest set cur=? where appRef=? and propRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    
                    System.out.println("Updating appliction " + appRef + " : Inserting property " + propRef + " for appliction " + appRef);
                    updateStat.setBoolean(col++, true);
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
            String sql = "select propRef from propertyInterest where appRef=? and cur=?";
            try (PreparedStatement selectStat = con.prepareStatement(sql)) {
                selectStat.setInt(1, appRef);
                selectStat.setBoolean(2, true);

                ResultSet results = selectStat.executeQuery();

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
            
            String checkSql = "select count(appRef) as count from propertyInterest where appRef=" + appRef + " and propRef=" + propRef;
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (Statement checkStat = con.createStatement()) {
                ResultSet checkResult = checkStat.executeQuery(checkSql);
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if(count >= 1) {
                    String updateSql = "update propertyInterest set cur=? where appRef=" + appRef + " and propRef=" + propRef;
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, false);
                    updateStat.executeUpdate();
                    if (count == 1) {
                        System.out.println("Updating appliction " + appRef + " : Ending property " + propRef + " for appliction " + appRef);
                    } else if (count > 1) {
                        System.out.println("ERROR IN DATABASE - Updating appliction " + appRef + " : Ending property " + propRef + " for appliction " + appRef);
                    }
                } else if (count == 0) {
                    String insertSql = "insert into propertyInterest (appRef, propRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                    System.out.println("ERROR IN DATABASE - Creating not current record for appliction " + appRef + " : Inserting property " + propRef + " for appliction " + appRef);
                    insertStat.setInt(col++, appRef);
                    insertStat.setInt(col++, propRef);
                    insertStat.setBoolean(col++, false);
                    insertStat.executeUpdate();
                    insertStat.close();
                }
                checkStat.close();
            }
        }
    }
    
    public void createLandlord(Landlord landlord) throws SQLException {
        if(!this.landlordExists(landlord.getLandlordRef())) {
            String insertSql = "insert into landlords (landlordRef, personRef, createdBy, createdDate) values (?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, landlord.getLandlordRef());
                insertStat.setInt(col++, landlord.getPersonRef());
                insertStat.setString(col++, landlord.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(landlord.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            landlords.put(landlord.getLandlordRef(), landlord);
        }
    }

    public void updateLandlord(int landlordRef) throws SQLException {
        if (this.landlordExists(landlordRef)) {
            Landlord landlord = this.getLandlord(landlordRef);
            this.createModifiedBy("landlordModifications", landlord.getLastModification(), landlord.getLandlordRef());
        }
    }
    
    public void deleteLandlord(int landlordRef) throws SQLException {
        if (this.landlordExists(landlordRef) && this.canDeleteApplication(landlordRef)) {
            String deleteSql = "delete from landlords where landlordRef=" + landlordRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.landlords.remove(landlordRef);
                }
            }
        }
    }
    
    public boolean canDeleteLandlord(int landlordRef) {
        if (this.landlordExists(landlordRef) && this.getLandlord(landlordRef).hasBeenModified()) {
            for(Lease lease : this.getLeases()) {
                if(lease.isAlreadyLandlord(landlordRef)) {
                    return false;
                }
            }
            return true;
        }
        return false;
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
                    this.loadLandlordMods(temp.getLandlordRef(), this.loadModMap("landlordModifications", temp.getLandlordRef()));
                    this.loadLandlordNotes(temp.getLandlordRef(), this.loadNoteMap("landlordNotes", temp.getLandlordRef()));
                }
            }
        }
    }
    
    private void loadLandlordMods(int landlordRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.landlordExists(landlordRef) && !loadadMods.isEmpty()) {
            Landlord landlord = this.getLandlord(landlordRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                landlord.modifiedBy(tempMod);
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
    
    private void loadLandlordNotes(int landlordRef, Map<Integer, Note> loadadNotes) {
        if (this.landlordExists(landlordRef) && !loadadNotes.isEmpty()) {
            Landlord landlord = this.getLandlord(landlordRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                landlord.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createLandlordNote(int landlordRef, Note note) throws SQLException {
        if(this.landlordExists(landlordRef) && !this.noteExists(note.getRef()) && this.getLandlord(landlordRef).hasNote(note.getRef())) {
            this.createNote("landlordNotes", landlordRef, note);
        }
    }
    
    public void updateLandlordNote(int landlordRef, int noteRef) throws SQLException {
        if(this.landlordExists(landlordRef) && this.noteExists(noteRef)) {
            this.updateNote("landlordNotes", landlordRef, noteRef);
        }
    }
    
    public void deleteLandlordNote(int landlordRef, int noteRef) throws SQLException {
        if (this.landlordExists(landlordRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("landlordNotes", landlordRef, noteRef);
        }
    }
    
    public void createOffice(Office office) throws SQLException {
        if(!this.officeExists(office.getOfficeCode())) {
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
            this.offices.put(office.getOfficeCode(), office);
        }
    }
    
    public void updateOffice(String officeCode) throws SQLException {
        if(this.officeExists(officeCode)) {
            Office office = this.getOffice(officeCode);
            String updateSql = "update Offices set addressRef=?, startDate=?, endDate=? where officeCode=?";
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
    
    public void deleteOffice(String officeCode) throws SQLException {
        if (this.officeExists(officeCode) && this.canDeleteOffice(officeCode)) {
            String deleteSql = "delete from offices where officeCode=" + officeCode;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.offices.remove(officeCode);
                }
            }
        }
    }
    
    public boolean canDeleteOffice(String officeCode) {
        return this.officeExists(officeCode) && this.getOffice(officeCode).hasBeenModified() && !this.getOffice(officeCode).hasAgreements();
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
                this.loadOfficeMods(temp.getOfficeCode(), this.loadModMap("officeModifications", temp.getOfficeCode()));
                this.loadOfficeNotes(temp.getOfficeCode(), this.loadNoteMap("officeNotes", temp.getOfficeCode()));
                this.loadOfficeDocs(temp.getOfficeCode(), this.loadDocMap("officeDocuments", temp.getOfficeCode()));
                this.loadOfficeContacts(temp.getOfficeCode());
            }
        }
    }
    
    private void loadOfficeMods(String officeCode, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.officeExists(officeCode) && !loadedMods.isEmpty()) {
            Office office = this.getOffice(officeCode);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                Office tempOffice = (Office) office;
                tempOffice.modifiedBy(tempMod);
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
    
    private void loadOfficeDocs(String officeCode, Map<Integer, Document> loadedDocs) {
        if (this.officeExists(officeCode) && !loadedDocs.isEmpty()) {
            Office office = this.getOffice(officeCode);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                office.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createOfficeDoc(String officeCode, DocumentImpl document) throws SQLException {
        if(this.officeExists(officeCode) && !this.documentExists(document.getDocumentRef()) && this.getOffice(officeCode).hasDocument(document.getDocumentRef())) {
            this.createDocument("officeDocuments", officeCode, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteOfficeDoc(String officeCode, int documentRef) throws SQLException {
        if(this.officeExists(officeCode) && this.documentExists(documentRef) && this.getOffice(officeCode).hasDocument(documentRef)) {
            this.deleteDocument("officeDocuments", officeCode, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadOfficeNotes(String officeCode, Map<Integer, Note> loadadNotes) {
        if (this.officeExists(officeCode) && !loadadNotes.isEmpty()) {
            Office office = this.getOffice(officeCode);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                office.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createOfficeNote(String officeCode, Note note) throws SQLException {
        if(this.officeExists(officeCode) && !this.noteExists(note.getRef()) && this.getOffice(officeCode).hasNote(note.getRef())) {
            this.createNote("officeNotes", officeCode, note);
        }
    }
    
    public void updateOfficeNote(String officeCode, int noteRef) throws SQLException {
        if(this.officeExists(officeCode) && this.noteExists(noteRef)) {
            this.updateNote("officeNotes", officeCode, noteRef);
        }
    }
    
    public void deleteOfficeNote(String officeCode, int noteRef) throws SQLException {
        if (this.officeExists(officeCode) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("officeNotes", officeCode, noteRef);
        }
    }
    
    public void createJobRole(JobRole jobRole) throws SQLException {
        if(!this.jobRoleExists(jobRole.getJobRoleCode())) {
            String insertSql = "insert into jobRoles (jobRoleCode, jobTitle, jobDescription, fullTime, salary, "
                    + "cur, otherRead, otherWrite, otherUpdate, employeeRead, employeeWrite, employeeUpdate, "
                    + "createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setString(col++, jobRole.getJobRoleCode());
                insertStat.setString(col++, jobRole.getJobTitle());
                insertStat.setString(col++, jobRole.getJobDescription());
                insertStat.setBoolean(col++, jobRole.isFullTime());
                insertStat.setDouble(col++, jobRole.getSalary());
                insertStat.setBoolean(col++, jobRole.isCurrent());
                insertStat.setBoolean(col++, jobRole.getRead());
                insertStat.setBoolean(col++, jobRole.getWrite());
                insertStat.setBoolean(col++, jobRole.getUpdate());
                insertStat.setBoolean(col++, jobRole.getEmployeeRead());
                insertStat.setBoolean(col++, jobRole.getEmployeeWrite());
                insertStat.setBoolean(col++, jobRole.getEmployeeUpdate());
                insertStat.setString(col++, jobRole.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(jobRole.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            jobRoles.put(jobRole.getJobRoleCode(), jobRole);
            this.createJobRoleRequirements(jobRole.getJobRoleCode(), jobRole.getJobRequirements());
            this.createJobRoleBenefits(jobRole.getJobRoleCode(), jobRole.getBenefits());
        }
    }

    public void updateJobRole(String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode)) {
            JobRole jobRole = this.getJobRole(jobRoleCode);
            String updateSql = "update jobRoles set jobTitle=?, jobDescription=?, salary=?, cur=?, "
                    + "otherRead=?, otherWrite=?, otherUpdate=?, employeeRead=?, employeeWrite=?, "
                    + "employeeUpdate=? where jobRoleCode=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, jobRole.getJobTitle());
                updateStat.setString(col++, jobRole.getJobDescription());
                updateStat.setDouble(col++, jobRole.getSalary());
                updateStat.setBoolean(col++, jobRole.isCurrent());
                updateStat.setBoolean(col++, jobRole.getRead());
                updateStat.setBoolean(col++, jobRole.getWrite());
                updateStat.setBoolean(col++, jobRole.getUpdate());
                updateStat.setBoolean(col++, jobRole.getEmployeeRead());
                updateStat.setBoolean(col++, jobRole.getEmployeeWrite());
                updateStat.setBoolean(col++, jobRole.getEmployeeUpdate());
                updateStat.setString(col++, jobRole.getJobRoleCode());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("jobRoleModifications", jobRole.getLastModification(), jobRole.getJobRoleCode());
        }
    }
    
    public void deleteJobRole(String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode) && this.canDeleteJobRole(jobRoleCode)) {
            String deleteSql = "delete from jobRoles where jobRoleCode=" + jobRoleCode;
            try(Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.jobRoles.remove(jobRoleCode);
                }
            }
        }
    }
    
    public boolean canDeleteJobRole(String jobRoleCode) {
        if (this.jobRoleExists(jobRoleCode) && this.getJobRole(jobRoleCode).hasBeenModified()) {
            for (Contract contract : this.getContracts()) {
                if (jobRoleCode.equals(contract.getJobRoleCode())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void loadJobRoles() throws SQLException {
        this.jobRoles.clear();
        String sql = "select jobRoleCode, jobTitle, jobDescription, fullTime, salary, cur, "
                    + "otherRead, otherWrite, otherUpdate, employeeRead, employeeWrite, "
                    + "employeeUpdate, createdBy, createdDate from jobroles order by createdDate";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                String jobRoleCode = results.getString("jobRoleCode");
                String jobTitle = results.getString("jobTitle");
                String jobDescription = results.getString("jobDescription");
                boolean fullTime = results.getBoolean("fullTime");
                boolean read = results.getBoolean("otherRead");
                boolean write = results.getBoolean("otherWrite");
                boolean update = results.getBoolean("otherUpdate");
                boolean employeeRead = results.getBoolean("employeeRead");
                boolean employeeWrite = results.getBoolean("employeeWrite");
                boolean employeeUpdate = results.getBoolean("employeeUpdate");
                double salary = results.getDouble("salary");
                boolean current = results.getBoolean("cur");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");
                
                JobRole temp = new JobRole(jobRoleCode, jobTitle, jobDescription, fullTime, salary, read, write, update, employeeRead, employeeWrite, employeeUpdate, createdBy, createdDate);
                temp.setCurrent(current);
                
                this.jobRoles.put(temp.getJobRoleCode(), temp);
                this.loadJobRoleMods(temp.getJobRoleCode(), this.loadModMap("jobRoleModifications", temp.getJobRoleCode()));
                this.loadJobRoleNotes(temp.getJobRoleCode(), this.loadNoteMap("jobRoleNotes", temp.getJobRoleCode()));
                this.loadJobRoleRequirements(temp.getJobRoleCode());
                this.loadJobRoleBenefits(temp.getJobRoleCode());
            }
        }
    }
    
    private void loadJobRoleMods(String jobRoleCode, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.jobRoleExists(jobRoleCode) && !loadedMods.isEmpty()) {
            JobRole jobRole = this.getJobRole(jobRoleCode);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                jobRole.modifiedBy(tempMod);
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
    
    private void loadJobRoleNotes(String jobRoleCode, Map<Integer, Note> loadadNotes) {
        if (this.jobRoleExists(jobRoleCode) && !loadadNotes.isEmpty()) {
            JobRole jobRole = this.getJobRole(jobRoleCode);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                jobRole.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createJobRoleNote(String jobRoleCode, Note note) throws SQLException {
        if(this.jobRoleExists(jobRoleCode) && !this.noteExists(note.getRef()) && this.getJobRole(jobRoleCode).hasNote(note.getRef())) {
            this.createNote("jobRoleNotes", jobRoleCode, note);
        }
    }
    
    public void updateJobRoleNote(String jobRoleCode, int noteRef) throws SQLException {
        if(this.jobRoleExists(jobRoleCode) && this.noteExists(noteRef)) {
            this.updateNote("jobRoleNotes", jobRoleCode, noteRef);
        }
    }
    
    public void deleteJobRoleNote(String jobRoleCode, int noteRef) throws SQLException {
        if (this.officeExists(jobRoleCode) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("jobRoleNotes", jobRoleCode, noteRef);
        }
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
    
    public void deleteJobRoleRequirement(String requirementCode, String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode) && this.jobRequirementExists(requirementCode)) {
            String deleteSql = "delete from jobRoleRequirements where requirementCode=" + requirementCode + "and jobRoleCode=" + jobRoleCode;
            try(Statement deleteStat = this.con.createStatement()) {
                deleteStat.executeUpdate(deleteSql);
            }
        }
    }
    
    private void loadJobRoleRequirements(String code) throws SQLException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = this.getJobRole(code);
            String sql = "select jobRoleCode, requirementCode from jobRoleRequirements";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);

                while (results.next()) {
                    String jobRoleCode = results.getString("jobRoleCode");
                    if (code.equals(jobRoleCode));
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
    private void createJobRoleBenefits(String code, List<JobRoleBenefitInterface> benefits) throws SQLException {
        if (!benefits.isEmpty() && this.jobRoleExists(code)) {
            for (JobRoleBenefitInterface benefit : benefits) {
                JobRoleBenefit temp = (JobRoleBenefit) benefit;
                this.createJobRoleBenefit(code, temp);
            }
        }
    }
    
    public void createJobRoleBenefit(String jobRoleCode, JobRoleBenefit benefit) throws SQLException {
        if (benefit != null && this.jobRoleExists(jobRoleCode) && this.jobBenefitExists(benefit.getBenefit().getCode()) && !this.jobRoleBenefitExists(benefit.getBenefitRef())) {
            String insertSql = "";
            if (benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, doubleValue, startDate, endDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else if (!benefit.isSalaryBenefit()) {
                insertSql = "insert into jobRoleBenefits (jobBenefitRef, jobRoleCode, benefitCode, stringValue, startDate, endDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                insertStat.setInt(col++, benefit.getNote().getRef());
                insertStat.setString(col++, benefit.getComment());
                insertStat.setString(col++, benefit.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.jobRoleBenefits.put(benefit.getBenefitRef(), benefit);
            this.notes.put(benefit.getNote().getRef(), benefit.getNote());
        }
    }
    
    public void updateJobRoleBenefit(String code, int benefitRef) throws SQLException {
        if(this.jobRoleExists(code) && this.jobRoleBenefitExists(benefitRef)) {
            JobRoleBenefit benefit = this.getJobRoleBenefit(benefitRef);
            String updateSql = "";
            if (benefit.isSalaryBenefit()) {
                updateSql = "update jobRoleBenefits set benefitCode=?, doubleValue=?, startDate=?, endDate=?, comment=? where jobBenefitRef=? and jobRoleCode=?";
            } else if (!benefit.isSalaryBenefit()) {
                updateSql = "update jobRoleBenefits set benefitCode=?, stringValue=?, startDate, endDate=?, comment=? where jobBenefitRef=? and jobRoleCode=?";
            }
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, benefit.getBenefitCode());
                if (benefit.isSalaryBenefit()) {
                    updateStat.setDouble(col++, benefit.getDoubleValue());
                } else if (!benefit.isSalaryBenefit()) {
                    updateStat.setString(col++, benefit.getStringValue());
                }
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getEndDate()));
                updateStat.setString(col++, benefit.getComment());
                updateStat.setInt(col++, benefit.getBenefitRef());
                updateStat.setString(col++, code);
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getBenefitRef()); // 
        }
    }
    
    public void deleteJobRoleBenefit(int benefitRef, String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode) && this.jobRoleBenefitExists(benefitRef) && this.getJobRoleBenefit(benefitRef).hasBeenModified()) {
            String deleteSql = "delete from jobRoleBenefits where benefitRef=" + benefitRef + "and jobRoleCode=" + jobRoleCode;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    this.jobRoleBenefits.remove(benefitRef);
                    this.deleteNote(this.getJobRoleBenefit(benefitRef).getNote().getRef());
                }
            }
        }
    }
    
    private void loadJobRoleBenefits(String code) throws SQLException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = this.getJobRole(code);
            String sql = "select jobBenefitRef, jobRoleCode, benefitCode, doubleValue, stringValue, startDate, endDate, noteRef, comment, createdBy, createdDate from jobRoleBenefits order by createdDate";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);
                
                while (results.next()) {
                    String jobRoleCode = results.getString("jobRoleCode");
                    if (code.equals(jobRoleCode)) {
                        int jobBenefitRef = results.getInt("jobBenefitRef");
                        String benefitCode = results.getString("benefitCode");
                        if(this.jobBenefitExists(benefitCode)) {
                            Date startDate = results.getDate("startDate");
                            Date endDate = results.getDate("endDate");
                            String stringValue = results.getString("stringValue");
                            double doubleValue = results.getDouble("doubleValue");
                            int noteRef = results.getInt("noteRef");
                            String comment = results.getString("comment");
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            
                            Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                            JobRoleBenefit temp = new JobRoleBenefit(jobBenefitRef, this.getJobBenefit(benefitCode), startDate, stringValue!=null, stringValue, doubleValue, note, createdBy, createdDate);
                            if(endDate != null) {
                                temp.setEndDate(endDate, null);
                            }
                            this.jobRoleBenefits.put(temp.getBenefitRef(), temp);
                            this.notes.put(note.getRef(), note);
                            this.loadBenefitMods(temp, this.loadModMap("jobRoleBenefitModifications", temp.getBenefitRef()));
                            jobRole.createJobBenefit(temp, null);
                        }
                    }
                }
            }
        }
    }
    
    private void loadBenefitMods(JobRoleBenefit benefit, Map<Integer, ModifiedByInterface> loadedMods) {
        if (benefit != null && this.jobBenefitExists(benefit.getBenefit().getCode()) && !loadedMods.isEmpty()) {
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                benefit.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createJobRequirement(Element requirement) throws SQLException {
        if(!this.jobRequirementExists(requirement.getCode())) {
            this.createElement("jobRequirements", requirement);
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.notes.put(requirement.getNote().getRef(), requirement.getNote());
        }
    }
    
    public void updateJobRequirement(String requirementCode) throws SQLException {
        if(this.jobRequirementExists(requirementCode)) {
            Element requirement = this.getJobRequirement(requirementCode);
            this.updateElement("jobRequirements", requirement);
            this.createModifiedBy("jobRequirementModifications", requirement.getLastModification(), requirement.getCode());
        }
    }
    
    public void deleteJobRequirement(String requirementCode) throws SQLException {
        if(this.jobRequirementExists(requirementCode) && this.canDeleteJobRequirement(requirementCode)) {
            this.deleteElement("jobRequirements", requirementCode);
            this.deleteNote(this.getJobRequirement(requirementCode).getNote().getRef());
            jobRequirements.remove(requirementCode);
        }
    }
    
    public boolean canDeleteJobRequirement(String requirementCode) {
        if(this.jobRequirementExists(requirementCode) && !this.getJobRequirement(requirementCode).hasBeenModified()) {
            for(JobRole jobRole : this.getJobRoles()) {
                for(Element requirement : jobRole.getJobRequirements()) {
                    if(requirementCode.equals(requirement.getCode())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadJobRequirements() throws SQLException {
        this.jobRequirements.clear();
        List<ElementImpl> loadedJobRequirements;
        loadedJobRequirements = this.loadElements("jobRequirements");
        if (!loadedJobRequirements.isEmpty()) {
            for (Element temp : loadedJobRequirements) {
                if (temp instanceof Element) {
                    this.jobRequirements.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("jobRequirementModifications", temp.getCode()));
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
            this.createElement("jobBenefits", benefit);
            this.jobBenefits.put(benefit.getCode(), benefit);
            this.notes.put(benefit.getNote().getRef(), benefit.getNote());
        }
    }
    
    public void updateJobBenefit(String benefitCode) throws SQLException {
        if(this.jobBenefitExists(benefitCode)) {
            Element benefit = this.getJobBenefit(benefitCode);
            this.updateElement("jobBenefits", benefit);
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getCode());
        }
    }
    
    public void deleteJobBenefit(String benefitCode) throws SQLException {
        if(this.jobBenefitExists(benefitCode) && this.canDeleteJobBenefit(benefitCode)) {
            this.deleteElement("jobBenefits", benefitCode);
            this.deleteNote(this.getJobBenefit(benefitCode).getNote().getRef());
            jobBenefits.remove(benefitCode);
        }
    }
    
    public boolean canDeleteJobBenefit(String benefitCode) {
        if (this.jobBenefitExists(benefitCode) && !this.getJobBenefit(benefitCode).hasBeenModified()) {
            for (JobRoleBenefit benefit : this.getJobRoleBenefits()) {
                if (benefitCode.equals(benefit.getBenefitCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadJobBenefits() throws SQLException {
        this.jobBenefits.clear();
        List<ElementImpl> loadedJobBenefits;
        loadedJobBenefits = this.loadElements("jobBenefits");
        if (!loadedJobBenefits.isEmpty()) {
            for (Element temp : loadedJobBenefits) {
                if (temp instanceof Element) {
                    this.jobBenefits.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("jobBenefitModifications", temp.getCode()));
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
            String insertSql = "insert into employees (employeeRef, personRef, officeCode, createdBy, createdDate) values (?, ?, ?, ?, ?)";
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
            employees.put(employee.getEmployeeRef(), employee);
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
    
    public void deleteEmployee(int employeeRef) throws SQLException {
        if(this.employeeExists(employeeRef) && this.canDeleteEmployee(employeeRef)) {
            String deleteSql = "delete from employees where employeeRef=" + employeeRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    this.deleteUser(employeeRef, this.getUser(employeeRef).getUsername());
                    employees.remove(employeeRef);
                }
            }
        }
    }
    
    public boolean canDeleteEmployee(int employeeRef) {
        if (this.employeeExists(employeeRef) && !this.getEmployee(employeeRef).hasBeenModified()) {
            for (Contract contract : this.getContracts()) {
                if (contract.getEmployeeRef() == employeeRef) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void loadEmployees() throws SQLException, RemoteException {
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
                    if ((officeCode == null || this.officeExists(officeCode))) {
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
                                results2.next();
                                String username = results2.getString("username");
                                String password =  results2.getString("password");
                                Employee temp = new Employee(employeeRef, person, username, password, createdBy, createdDate);
                                employees.put(temp.getEmployeeRef(), temp);
                                users.put(temp.getUser().getUsername(), temp.getUser());
                                this.loadEmployeeMods(temp.getEmployeeRef(), this.loadModMap("employeeModifications", temp.getEmployeeRef()));
                                this.loadEmployeeNotes(temp.getEmployeeRef(), this.loadNoteMap("employeeNotes", temp.getEmployeeRef()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void loadEmployeeMods(int employeeRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.employeeExists(employeeRef) && !loadadMods.isEmpty()) {
            Employee employee = this.getEmployee(employeeRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                employee.modifiedBy(tempMod);
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
    
    private void loadEmployeeNotes(int employeeRef, Map<Integer, Note> loadadNotes) {
        if (this.employeeExists(employeeRef) && !loadadNotes.isEmpty()) {
            Employee employee = this.getEmployee(employeeRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                employee.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createEmployeeNote(int employeeRef, Note note) throws SQLException {
        if(this.employeeExists(employeeRef) && !this.noteExists(note.getRef()) && this.getEmployee(employeeRef).hasNote(note.getRef())) {
            this.createNote("employeeNotes", employeeRef, note);
        }
    }
    
    public void updateEmployeeNote(int employeeRef, int noteRef) throws SQLException {
        if(this.employeeExists(employeeRef) && this.noteExists(noteRef)) {
            this.updateNote("employeeNotes", employeeRef, noteRef);
        }
    }
    
    public void deleteEmployeeNote(int employeeRef, int noteRef) throws SQLException {
        if (this.employeeExists(employeeRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("employeeNotes", employeeRef, noteRef);
        }
    }
    
    public void createTenancy(Tenancy tenancy) throws SQLException {
        if(!this.tenancyExists(tenancy.getAgreementRef())) {
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
            tenancies.put(tenancy.getAgreementRef(), tenancy);
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
    
    public void deleteTenancy(int tenancyRef) throws SQLException {
        if(this.tenancyExists(tenancyRef) && this.canDeleteTenancy(tenancyRef)) {
            String deleteSql = "delete from tenancies where tenancyRef=" + tenancyRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    tenancies.remove(tenancyRef);
                    this.deleteRentAccount(this.getTenancy(tenancyRef).getAccountRef());
                }
            }
        }
    }
    
    public boolean canDeleteTenancy(int tenancyRef) {
        return this.tenancyExists(tenancyRef) && this.getTenancy(tenancyRef).hasBeenModified() && this.getRentAccount(this.getTenancy(tenancyRef).getAccountRef()).hasBeenModified();
    }

    private void loadTenancies() throws SQLException {
        this.tenancies.clear();
        String sql = "select tenancyRef, name, startDate, expectedEndDate, actualEndDate, length, accountRef, officeCode, "
                + "appRef, propRef, tenTypeCode, rent, charges, createdBy, createdDate from tenancies order by tenancyRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int tenancyRef = results.getInt("tenancyRef");
                String name = results.getString("name");
                Date startDate = results.getDate("startDate");
                Date expectedEndDate = results.getDate("expectedEndDate");
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
                                this.loadTenancyMods(temp.getAgreementRef(), this.loadModMap("tenancyModifications", temp.getAgreementRef()));
                                this.loadTenancyNotes(temp.getAgreementRef(), this.loadNoteMap("tenancyNotes", temp.getAgreementRef()));
                                this.loadTenancyDocs(temp.getAgreementRef(), this.loadDocMap("tenancyDocuments", temp.getAgreementRef()));
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void loadTenancyMods(int tenancyRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.tenancyExists(tenancyRef) && !loadadMods.isEmpty()) {
            Tenancy tenancy = this.getTenancy(tenancyRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                tenancy.modifiedBy(tempMod);
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
    
    private void loadTenancyDocs(int tenancyRef, Map<Integer, Document> loadedDocs) {
        if (this.tenancyExists(tenancyRef) && !loadedDocs.isEmpty()) {
            Tenancy tenancy = this.getTenancy(tenancyRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                tenancy.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createTenancyDoc(int tenancyRef, DocumentImpl document) throws SQLException {
        if(this.tenancyExists(tenancyRef) && !this.documentExists(document.getDocumentRef()) && this.getTenancy(tenancyRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("tenancyDocuments", tenancyRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteTenancyDoc(int tenancyRef, int documentRef) throws SQLException {
        if(this.tenancyExists(tenancyRef) && this.documentExists(documentRef) && this.getTenancy(tenancyRef).hasDocument(documentRef)) {
            this.deleteDocument("tenancyDocuments", tenancyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadTenancyNotes(int tenancyRef, Map<Integer, Note> loadadNotes) {
        if (this.tenancyExists(tenancyRef) && !loadadNotes.isEmpty()) {
            Tenancy tenancy = this.getTenancy(tenancyRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                tenancy.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createTenancyNote(int tenancyRef, Note note) throws SQLException {
        if(this.tenancyExists(tenancyRef) && !this.noteExists(note.getRef()) && this.getTenancy(tenancyRef).hasNote(note.getRef())) {
            this.createNote("tenancyNotes", tenancyRef, note);
        }
    }
    
    public void updateTenancyNote(int tenancyRef, int noteRef) throws SQLException {
        if(this.tenancyExists(tenancyRef) && this.noteExists(noteRef)) {
            this.updateNote("tenancyNotes", tenancyRef, noteRef);
        }
    }
    
    public void deleteTenancyNote(int tenancyRef, int noteRef) throws SQLException {
        if (this.tenancyExists(tenancyRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("tenancyNotes", tenancyRef, noteRef);
        }
    }
    
    public void createTenancyType(Element tenType) throws SQLException {
        if(!this.tenancyTypeExists(tenType.getCode())) {
            this.createElement("tenancyTypes", tenType);
            this.tenancyTypes.put(tenType.getCode(), tenType);
            this.notes.put(tenType.getNote().getRef(), tenType.getNote());
        }
    }
    
    public void updateTenancyType(String tenTypeCode) throws SQLException {
        if(this.tenancyTypeExists(tenTypeCode)) {
            Element tenType = this.getTenancyType(tenTypeCode);
            this.updateElement("tenancyTypes", tenType);
            this.createModifiedBy("tenancyTypeModifications", tenType.getLastModification(), tenType.getCode());
        }
    }
    
    public void deleteTenancyType(String tenTypeCode) throws SQLException {
        if(this.tenancyTypeExists(tenTypeCode) && this.canDeleteTitle(tenTypeCode)) {
            this.deleteElement("tenancyTypes", tenTypeCode);
            this.deleteNote(this.getTenancyType(tenTypeCode).getNote().getRef());
            this.titles.remove(tenTypeCode);
        }
    }
    
    public boolean canDeleteTenancyType(String tenTypeCode) {
        if(this.tenancyTypeExists(tenTypeCode) && !this.getTenancyType(tenTypeCode).hasBeenModified()) {
            for(Tenancy tenancy : this.getTenancies()) {
                if(tenTypeCode.equals(tenancy.getTenType().getCode())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void loadTenancyTypes() throws SQLException {
        this.tenancyTypes.clear();
        List<ElementImpl> loadedTenancyTypes;
        loadedTenancyTypes = this.loadElements("tenancyTypes");
        if (!loadedTenancyTypes.isEmpty()) {
            for (Element temp : loadedTenancyTypes) {
                if (temp instanceof Element) {
                    this.tenancyTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getRef(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("tenancyTypeModifications", temp.getCode()));
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
            String insertSql = "insert into leases (leaseRef, name, startDate, expectedEndDate, length, accountRef, "
                    + "officeCode, propRef, expenditure, fullManagement, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            leases.put(lease.getAgreementRef(), lease);
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
    
    public void deleteLease(int leaseRef) throws SQLException {
        if(this.leaseExists(leaseRef) && this.canDeleteLease(leaseRef)) {
            String deleteSql = "delete from leases where leaseRef=" + leaseRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    leases.remove(leaseRef);
                    this.deleteLeaseAccount(this.getLease(leaseRef).getAccountRef());
                }
            }
        }
    }
    
    public boolean canDeleteLease(int leaseRef) {
        return this.leaseExists(leaseRef) && this.getLease(leaseRef).hasBeenModified() && this.getLeaseAccount(this.getLease(leaseRef).getAccountRef()).hasBeenModified();
    }

    private void loadLeases() throws SQLException {
        this.leases.clear();
        String sql = "select leaseRef, name, startDate, expectedEndDate, actualEndDate, length, accountRef, officeCode, "
                + "propRef, fullManagement, expenditure, createdBy, createdDate from leases order by leaseRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int leaseRef = results.getInt("leaseRef");
                String name = results.getString("name");
                Date startDate = results.getDate("startDate");
                Date expectedEndDate = results.getDate("expectedEndDate");
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
                        this.loadLeaseMods(temp.getAgreementRef(), this.loadModMap("leaseModifications", temp.getAgreementRef()));
                        this.loadLeaseNotes(temp.getAgreementRef(), this.loadNoteMap("leaseNotes", temp.getAgreementRef()));
                        this.loadLeaseDocs(temp.getAgreementRef(), this.loadDocMap("leaseDocuments", temp.getAgreementRef()));
                    }
                }
            }
        }
    }
    
    private void loadLeaseMods(int leaseRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseExists(leaseRef) && !loadadMods.isEmpty()) {
            Lease lease = this.getLease(leaseRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                lease.modifiedBy(tempMod);
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
    
    private void loadLeaseDocs(int leaseRef, Map<Integer, Document> loadedDocs) {
        if (this.leaseExists(leaseRef) && !loadedDocs.isEmpty()) {
            Lease lease = this.getLease(leaseRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                lease.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createLeaseDoc(int leaseRef, DocumentImpl document) throws SQLException {
        if(this.leaseExists(leaseRef) && !this.documentExists(document.getDocumentRef()) && this.getLease(leaseRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("leaseDocuments", leaseRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteLeaseDoc(int tenancyRef, int documentRef) throws SQLException {
        if(this.leaseExists(tenancyRef) && this.documentExists(documentRef) && this.getLease(tenancyRef).hasDocument(documentRef)) {
            this.deleteDocument("leaseDocuments", tenancyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadLeaseNotes(int leaseRef, Map<Integer, Note> loadadNotes) {
        if (this.leaseExists(leaseRef) && !loadadNotes.isEmpty()) {
            Lease lease = this.getLease(leaseRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                lease.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createLeaseNote(int leaseRef, Note note) throws SQLException {
        if(this.leaseExists(leaseRef) && !this.noteExists(note.getRef()) && this.getLease(leaseRef).hasNote(note.getRef())) {
            this.createNote("leaseNotes", leaseRef, note);
        }
    }
    
    public void updateLeaseNote(int leaseRef, int noteRef) throws SQLException {
        if(this.leaseExists(leaseRef) && this.noteExists(noteRef)) {
            this.updateNote("leaseNotes", leaseRef, noteRef);
        }
    }
    
    public void deleteLeaseNote(int leaseRef, int noteRef) throws SQLException {
        if (this.leaseExists(leaseRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("leaseNotes", leaseRef, noteRef);
        }
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
                    String insertSql = "insert into leaseLandlord (landlordRef, leaseRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                
                    System.out.println("Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    insertStat.setInt(col++, landlordRef);
                    insertStat.setInt(col++, leaseRef);
                    insertStat.setBoolean(col++, true);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update leaseLandlord set cur=? where landlordRef=? and leaseRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    
                    System.out.println("Updating landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    updateStat.setBoolean(col++, true);
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
        String sql = "select landlordRef, leaseRef, cur from leaseLandlord order by leaseRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while (results.next()) {
                int leaseRef = results.getInt("leaseRef");
                int landlordRef = results.getInt("landlordRef");
                boolean current = results.getBoolean("cur");
                if(leaseRef == ref && this.leaseExists(leaseRef) && this.landlordExists(landlordRef) && current) {
                    Lease lease = this.getLease(leaseRef);
                    lease.addLandlord(this.getLandlord(landlordRef), null);
                    Landlord landlord = this.getLandlord(landlordRef);
                    landlord.createLease(lease, null);
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
                    String updateSql = "update leaseLandlord set cur=? where landlordRef=? and leaseRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, false);
                    updateStat.executeUpdate();
                    if (count == 1) {
                        System.out.println("Updating landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    } else if (count > 1) {
                        System.out.println("ERROR IN DATABASE - MORE THAN ONE ENTRY FOR LANDLORD " + landlordRef + " - LEASE" + leaseRef);
                    }
                } else if (count == 0) {
                    String insertSql = "insert into leaseLandlord (landlordRef, leaseRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
                    System.out.println("ERROR IN DATABASE - Creating not current record for landlord " + landlordRef + " : Inserting landlord " + landlordRef + " for lease " + leaseRef);
                    insertStat.setInt(col++, landlordRef);
                    insertStat.setInt(col++, leaseRef);
                    insertStat.setBoolean(col++, false);
                    insertStat.executeUpdate();
                    insertStat.close();
                }
                checkStat.close();
            }
        }
    }
    
    public void createContract(Contract contract) throws SQLException {
        if(!this.contractExists(contract.getAgreementRef())) {
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
            contracts.put(contract.getAgreementRef(), contract);
        }
    }
    
    public void updateContract(int contractRef) throws SQLException {
        if(this.contractExists(contractRef)) {
            Contract contract = this.getContract(contractRef);
            String updateSql = "update contracts set name=?, startDate=?, expectedEndDate=?, "
                    + "actualEndDate=?, length=? where contractRef=?";
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
    
    public void deleteContract(int contractRef) throws SQLException {
        if(this.contractExists(contractRef) && this.canDeleteContract(contractRef)) {
            String deleteSql = "delete from contracts where contractRef=" + contractRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    contracts.remove(contractRef);
                    this.deleteEmployeeAccount(this.getContract(contractRef).getAccountRef());
                }
            }
        }
    }
    
    public boolean canDeleteContract(int contractRef) {
        return this.contractExists(contractRef) && this.getContract(contractRef).hasBeenModified() && this.getEmployeeAccount(this.getContract(contractRef).getAccountRef()).hasBeenModified();
    }
    
    private void loadContracts() throws SQLException {
        this.contracts.clear();
       String sql = "select contractRef, name, startDate, expectedEndDate, actualEndDate, length, accountRef, officeCode, "
                + "employeeRef, jobRoleCode, createdBy, createdDate from contracts order by contractRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int contractRef = results.getInt("contractRef");
                String name = results.getString("name");
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
                            employee.createContract(temp, null);
                            UserImpl user = employee.getUser();
                            user.setUserPermissions(jobRole.getRead(), jobRole.getWrite(), jobRole.getUpdate(), jobRole.getEmployeeRead(), jobRole.getEmployeeWrite(), jobRole.getEmployeeUpdate());
                            this.loadContractMods(temp.getAgreementRef(), this.loadModMap("contractModifications", temp.getAgreementRef()));
                            this.loadContractNotes(temp.getAgreementRef(), this.loadNoteMap("contractNotes", temp.getAgreementRef()));
                            this.loadContractDocs(temp.getAgreementRef(), this.loadDocMap("contractDocuments", temp.getAgreementRef()));
                        }
                    }
                }
            }
        }
    }
    
    private void loadContractMods(int contractRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(contractRef) && !loadadMods.isEmpty()) {
            Contract contract = this.getContract(contractRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                contract.modifiedBy(tempMod);
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
    
    private void loadContractDocs(int contractRef, Map<Integer, Document> loadedDocs) {
        if (this.contractExists(contractRef) && !loadedDocs.isEmpty()) {
            Contract contract = this.getContract(contractRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                contract.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createContractDoc(int contractRef, DocumentImpl document) throws SQLException {
        if(this.contractExists(contractRef) && !this.documentExists(document.getDocumentRef()) && this.getContract(contractRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("contractDocuments", contractRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteContractDoc(int contractRef, int documentRef) throws SQLException {
        if(this.leaseExists(contractRef) && this.documentExists(documentRef) && this.getContract(contractRef).hasDocument(documentRef)) {
            this.deleteDocument("contractDocuments", contractRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadContractNotes(int contractRef, Map<Integer, Note> loadadNotes) {
        if (this.contractExists(contractRef) && !loadadNotes.isEmpty()) {
            Contract contract = this.getContract(contractRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                contract.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createContractNote(int contractRef, Note note) throws SQLException {
        if(this.contractExists(contractRef) && !this.noteExists(note.getRef()) && this.getContract(contractRef).hasNote(note.getRef())) {
            this.createNote("contractNotes", contractRef, note);
        }
    }
    
    public void updateContractNote(int contractRef, int noteRef) throws SQLException {
        if(this.contractExists(contractRef) && this.noteExists(noteRef)) {
            this.updateNote("contractNotes", contractRef, noteRef);
        }
    }
    
    public void deleteContractNote(int contractRef, int noteRef) throws SQLException {
        if (this.contractExists(contractRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("contractNotes", contractRef, noteRef);
        }
    }
    
    public void createRentAccount(RentAccount rentAcc) throws SQLException {
        if(!this.rentAccountExists(rentAcc.getAccRef())) {
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
            rentAccounts.put(rentAcc.getAccRef(), rentAcc);
        }
    }
    
    private void deleteRentAccount(int rentAccRef) throws SQLException {
        if(this.rentAccountExists(rentAccRef)) {
            String deleteSql = "delete from rentAccounts where rentAccRef=" + rentAccRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    rentAccounts.remove(rentAccRef);
                }
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
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.rentAccounts.put(temp.getAccRef(), temp);
                        this.loadRentAccountMods(temp.getAccRef(), this.loadModMap("rentAccountModifications", temp.getAccRef()));
                        this.loadRentAccountNotes(temp.getAccRef(), this.loadNoteMap("rentAccountNotes", temp.getAccRef()));
                        this.loadRentAccountDocs(temp.getAccRef(), this.loadDocMap("rentAccountDocuments", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void loadRentAccountMods(int rentAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.rentAccountExists(rentAccRef) && !loadadMods.isEmpty()) {
            RentAccount rentAcc = this.getRentAccount(rentAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                rentAcc.modifiedBy(tempMod);
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
    
    private void loadRentAccountDocs(int rentAccRef, Map<Integer, Document> loadedDocs) {
        if (this.rentAccountExists(rentAccRef) && !loadedDocs.isEmpty()) {
            RentAccount rentAcc = this.getRentAccount(rentAccRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                rentAcc.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createRentAccountDoc(int rentAccRef, DocumentImpl document) throws SQLException {
        if(this.rentAccountExists(rentAccRef) && !this.documentExists(document.getDocumentRef()) && this.getRentAccount(rentAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("rentAccountDocuments", rentAccRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteRentAccountDoc(int rentAccRef, int documentRef) throws SQLException {
        if(this.rentAccountExists(rentAccRef) && this.documentExists(documentRef) && this.getRentAccount(rentAccRef).hasDocument(documentRef)) {
            this.deleteDocument("rentAccountDocuments", rentAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadRentAccountNotes(int rentAccRef, Map<Integer, Note> loadadNotes) {
        if (this.rentAccountExists(rentAccRef) && !loadadNotes.isEmpty()) {
            RentAccount rentAcc = this.getRentAccount(rentAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                rentAcc.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createRentAccountNote(int rentAccRef, Note note) throws SQLException {
        if(this.rentAccountExists(rentAccRef) && !this.noteExists(note.getRef()) && this.getRentAccount(rentAccRef).hasNote(note.getRef())) {
            this.createNote("rentAccountNotes", rentAccRef, note);
        }
    }
    
    public void updateRentAccountNote(int rentAccRef, int noteRef) throws SQLException {
        if(this.rentAccountExists(rentAccRef) && this.noteExists(noteRef)) {
            this.updateNote("rentAccountNotes", rentAccRef, noteRef);
        }
    }
    
    public void deleteRentAccountNote(int rentAccRef, int noteRef) throws SQLException {
        if (this.rentAccountExists(rentAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("rentAccountNotes", rentAccRef, noteRef);
        }
    }
    
    public void createRentAccountTransaction(int rentAccRef, Transaction transaction) throws SQLException {
        if(transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.rentAccountExists(rentAccRef)) {
            this.createTransaction("rentAccountTransactions", transaction);
        }
    }
    
    public void deleteRentAccountTransaction(int rentAccRef, int transactionRef) throws SQLException {
        if(this.transactionExists(transactionRef) && this.rentAccountExists(rentAccRef)) {
            this.deleteTransaction("rentAccountTransactions", transactionRef, rentAccRef);
        }
    }
    
    public void createLeaseAccount(LeaseAccount leaseAcc) throws SQLException {
        if(!this.leaseAccountExists(leaseAcc.getAccRef())) {
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
            leaseAccounts.put(leaseAcc.getAccRef(), leaseAcc);
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
    
    private void deleteLeaseAccount(int leaseAccRef) throws SQLException {
        if(this.leaseAccountExists(leaseAccRef)) {
            String deleteSql = "delete from leaseAccounts where leaseAccRef=" + leaseAccRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    leaseAccounts.remove(leaseAccRef);
                }
            }
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
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.leaseAccounts.put(temp.getAccRef(), temp);
                        this.loadLeaseAccountMods(temp.getAccRef(), this.loadModMap("leaseAccountModifications", temp.getAccRef()));
                        this.loadLeaseAccountNotes(temp.getAccRef(), this.loadNoteMap("leaseAccountNotes", temp.getAccRef()));
                        this.loadLeaseAccountDocs(temp.getAccRef(), this.loadDocMap("leaseAccountDocuments", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void loadLeaseAccountMods(int leaseAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseAccountExists(leaseAccRef) && !loadadMods.isEmpty()) {
            LeaseAccount leaseAcc = this.getLeaseAccount(leaseAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                    tempMod = (ModifiedByInterface) temp.getValue();
                    leaseAcc.modifiedBy(tempMod);
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
    
    private void loadLeaseAccountDocs(int leaseAccRef, Map<Integer, Document> loadedDocs) {
        if (this.leaseAccountExists(leaseAccRef) && !loadedDocs.isEmpty()) {
            LeaseAccount leaseAcc = this.getLeaseAccount(leaseAccRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                leaseAcc.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createLeaseAccountDoc(int leaseAccRef, DocumentImpl document) throws SQLException {
        if(this.leaseAccountExists(leaseAccRef) && !this.documentExists(document.getDocumentRef()) && this.getLeaseAccount(leaseAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("leaseAccountDocuments", leaseAccRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteLeaseAccountDoc(int leaseAccRef, int documentRef) throws SQLException {
        if(this.leaseAccountExists(leaseAccRef) && this.documentExists(documentRef) && this.getLeaseAccount(leaseAccRef).hasDocument(documentRef)) {
            this.deleteDocument("leaseAccountDocuments", leaseAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadLeaseAccountNotes(int leaseAccRef, Map<Integer, Note> loadadNotes) {
        if (this.leaseAccountExists(leaseAccRef) && !loadadNotes.isEmpty()) {
            LeaseAccount leaseAcc = this.getLeaseAccount(leaseAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                leaseAcc.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createLeaseAccountNote(int leaseAccRef, Note note) throws SQLException {
        if(this.leaseAccountExists(leaseAccRef) && !this.noteExists(note.getRef()) && this.getLeaseAccount(leaseAccRef).hasNote(note.getRef())) {
            this.createNote("leaseAccountNotes", leaseAccRef, note);
        }
    }
    
    public void updateLeaseAccountNote(int leaseAccRef, int noteRef) throws SQLException {
        if(this.leaseAccountExists(leaseAccRef) && this.noteExists(noteRef)) {
            this.updateNote("leaseAccountNotes", leaseAccRef, noteRef);
        }
    }
    
    public void deleteLeaseAccountNote(int leaseAccRef, int noteRef) throws SQLException {
        if (this.leaseAccountExists(leaseAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("leaseAccountNotes", leaseAccRef, noteRef);
        }
    }
    
    public void createLeaseAccountTransaction(int leaseAccRef, Transaction transaction) throws SQLException {
        if(transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.leaseAccountExists(leaseAccRef)) {
            this.createTransaction("leaseAccountTransactions", transaction);
        }
    }
    
    public void deleteLeaseAccountTransaction(int leaseAccRef, int transactionRef) throws SQLException {
        if(this.transactionExists(transactionRef) && this.leaseAccountExists(leaseAccRef)) {
            this.deleteTransaction("leaseAccountTransactions", transactionRef, leaseAccRef);
        }
    }
    
    public void createEmployeeAccount(EmployeeAccount employeeAcc) throws SQLException {
        if(!this.employeeAccountExists(employeeAcc.getAccRef())) {
            String insertSql = "insert into employeeAccounts (employeeAccRef, name, startDate, "
                    + "balance, officeCode, salary, contractRef, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, employeeAcc.getAccRef());
                insertStat.setString(col++, employeeAcc.getAccName());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getStartDate()));
                insertStat.setDouble(col++, employeeAcc.getBalance());
                insertStat.setString(col++, employeeAcc.getOfficeCode());
                insertStat.setDouble(col++, employeeAcc.getContract().getJobRole().getSalary());
                insertStat.setInt(col++, employeeAcc.getContractRef());
                insertStat.setString(col++, employeeAcc.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(employeeAcc.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            employeeAccounts.put(employeeAcc.getAccRef(), employeeAcc);
        }
    }
    
    public void updateEmployeeAccount(int employeeAccRef) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef)) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            String updateSql = "update employeeAccounts set name=?, startDate=?, endDate=?, "
                    + "balance=?, salary=? where employeeAccRef=?";
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
    
    private void deleteEmployeeAccount(int employeeAccRef) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef)) {
            String deleteSql = "delete from employeeAccounts where employeeAccRef=" + employeeAccRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    employeeAccounts.remove(employeeAccRef);
                }
            }
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
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        this.employeeAccounts.put(temp.getAccRef(), temp);
                        this.loadEmployeeAccountMods(temp.getAccRef(), this.loadModMap("employeeAccountModifications", temp.getAccRef()));
                        this.loadEmployeeAccountNotes(temp.getAccRef(), this.loadNoteMap("employeeAccountNotes", temp.getAccRef()));
                        this.loadEmployeeAccountDocs(temp.getAccRef(), this.loadDocMap("employeeAccountNotes", temp.getAccRef()));
                    }
                }
            }
        }
    }
    
    private void loadEmployeeAccountMods(int employeeAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(employeeAccRef) && !loadadMods.isEmpty()) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                    tempMod = (ModifiedByInterface) temp.getValue();
                    employeeAcc.modifiedBy(tempMod);
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
    
    private void loadEmployeeAccountDocs(int employeeAccRef, Map<Integer, Document> loadedDocs) {
        if (this.employeeAccountExists(employeeAccRef) && !loadedDocs.isEmpty()) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            Iterator it = loadedDocs.entrySet().iterator();
            while (it.hasNext()) {
                Document tempDoc;
                Map.Entry temp = (Map.Entry) it.next();
                tempDoc = (Document) temp.getValue();
                employeeAcc.createDocument(tempDoc, null);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createEmployeeAccountDoc(int employeeAccRef, DocumentImpl document) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef) && !this.documentExists(document.getDocumentRef()) && this.getEmployeeAccount(employeeAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("employeeAccountDocuments", employeeAccRef, document);
            this.notes.put(document.getNote().getRef(), document.getNote());
        }
    }
    
    public void deleteEmployeeAccountDoc(int employeeAccRef, int documentRef) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef) && this.documentExists(documentRef) && this.getEmployeeAccount(employeeAccRef).hasDocument(documentRef)) {
            this.deleteDocument("employeeAccountDocuments", employeeAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }
    
    private void loadEmployeeAccountNotes(int employeeAccRef, Map<Integer, Note> loadadNotes) {
        if (this.employeeAccountExists(employeeAccRef) && !loadadNotes.isEmpty()) {
            EmployeeAccount employeeAcc = this.getEmployeeAccount(employeeAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                employeeAcc.createNote(tempNote, null);
                notes.put(tempNote.getRef(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    
    public void createEmployeeAccountNote(int employeeAccRef, Note note) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef) && !this.noteExists(note.getRef()) && this.getEmployeeAccount(employeeAccRef).hasNote(note.getRef())) {
            this.createNote("employeeAccountNotes", employeeAccRef, note);
        }
    }
    
    public void updateEmployeeAccountNote(int employeeAccRef, int noteRef) throws SQLException {
        if(this.employeeAccountExists(employeeAccRef) && this.noteExists(noteRef)) {
            this.updateNote("employeeAccountNotes", employeeAccRef, noteRef);
        }
    }
    
    public void deleteEmployeeAccountNote(int employeeAccRef, int noteRef) throws SQLException {
        if (this.employeeAccountExists(employeeAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("employeeAccountNotes", employeeAccRef, noteRef);
        }
    }
    
    public void createEmployeeAccountTransaction(int employeeAccRef, Transaction transaction) throws SQLException {
        if(transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.employeeAccountExists(employeeAccRef)) {
            this.createTransaction("employeeAccountTransactions", transaction);
        }
    }
    
    public void deleteEmployeeAccountTransaction(int employeeAccRef, int transactionRef) throws SQLException {
        if(this.transactionExists(transactionRef) && this.employeeAccountExists(employeeAccRef)) {
            this.deleteTransaction("employeeAccountTransactions", transactionRef, employeeAccRef);
        }
    }
    
    private void createTransaction(String from, Transaction transaction) throws SQLException {
        if(!this.transactionExists(transaction.getTransactionRef())) {
            String insertSql = "insert into " + from + " (transactionRef, accountRef, fromRef, toRef, amount, "
                    + "isDebit, transactionDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            transactions.put(transaction.getTransactionRef(), transaction);
        }
    }
    
    private void deleteTransaction(String from, int transactionRef, int accRef) throws SQLException {
        if(this.tenancyExists(transactionRef) && this.canDeleteTenancy(transactionRef)) {
            String deleteSql = "delete from " + from + " where transactionRef=" + transactionRef + " and accRef=" + accRef;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    transactions.remove(transactionRef);
                    this.deleteNote(this.getTransaction(transactionRef).getNote().getRef());
                }
            }
        }
    }
    
    private void loadTransactions(String from, Account account) throws SQLException {
        this.transactions.clear();
        String sql = "select transactionRef, accountRef, fromRef, toRef, amount, isDebit, "
                    + "transactionDate, noteRef, comment, createdBy, createdDate from " + from + " where accountRef=? order by transactionRef";
        try (PreparedStatement selectStat = con.prepareStatement(sql)) {
            selectStat.setInt(1, account.getAccRef());
            
            ResultSet results = selectStat.executeQuery();

            while (results.next()) {
                int transactionRef = results.getInt("transactionRef");
                int accountRef = results.getInt("accountRef");
                if (account.getAccRef() == accountRef) {
                    int fromRef = results.getInt("fromRef");
                    int toRef = results.getInt("toRef");
                    double amount = results.getDouble("amount");
                    boolean isDebit = results.getBoolean("isDebit");
                    Date transactionDate = results.getDate("transactionDate");
                    int noteRef = results.getInt("noteRef");
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    
                    Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                    Transaction temp = new Transaction(transactionRef, accountRef, fromRef, toRef, amount, isDebit, transactionDate, note, createdBy, createdDate);
                    this.transactions.put(temp.getTransactionRef(), temp);
                    account.createTransaction(temp, null);
                }
            }
        }
    }
    
    public Transaction getTransaction(int ref) {
        if(this.transactions.containsKey(ref)) {
            return this.transactions.get(ref);
        }
        return null;
    }
    
    public void createUser(UserImpl user) throws SQLException {
        if(!this.userExists(user.getUsername()) && this.employeeExists(user.getEmployeeRef())) {
            String insertSql = "insert into users (employeeRef, username, password, otherRead, otherWrite, "
                    + "otherUpdate, employeeRead, employeeWrite, employeeUpdate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            users.put(user.getUsername(), user);
        }
    }
    
    public void updateUser(String username) throws SQLException {
        if(this.userExists(username)) {
            UserImpl user = this.getUser(username);
            String updateSql = "update users set password=?, otherRead=?, otherWrite=?, otherUpdate=?, "
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
            Employee employee = this.getEmployee(user.getEmployeeRef());
            this.createModifiedBy("employeeModifications", employee.getLastModification(), employee.getEmployeeRef());
        }
    }
    
    private void deleteUser(int employeeRef, String username) throws SQLException {
        if(this.userExists(username) && this.getUser(username).getEmployeeRef() == employeeRef) {
            String deleteSql = "delete from users where employeeRef=" + employeeRef + " and username=" + username;
            try(Statement deleteStat = this.con.createStatement()) {
                if(deleteStat.executeUpdate(deleteSql) >=1) {
                    users.remove(username);
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
    
    public UserImpl getUser(String username) {
        if(this.userExists(username)) {
            return this.users.get(username);
        }
        return null;
    }
    
    public User getUser(int employeeRef) {
        for (User user : this.getUsers()) {
            if(user.getEmployeeRef() == employeeRef) {
                return user;
            }
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
    
    public boolean propertyElementValueExists(int ref) {
        return this.propertyElementValues.containsKey(ref);
    }
    
    public boolean personExists(int personRef) {
        return this.people.containsKey(personRef);
    }
    
    public boolean invPartyExists(int invPartyRef) {
        return this.involvedParties.containsKey(invPartyRef);
    }
    
    public boolean personInvPartyExists(int personRef) {
        for(InvolvedParty invParties : involvedParties.values()) {
            if(invParties.getPersonRef() == personRef) {
                return true;
            }
        }
        return false;
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
    
    public boolean personLandlordExists(int personRef) {
        for(Landlord landlord : landlords.values()) {
            if(landlord.getPersonRef() == personRef) {
                return true;
            }
        }
        return false;
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
    
    public boolean personEmployeeExists(int personRef) {
        for(Employee employee : employees.values()) {
            if(employee.getPersonRef() == personRef) {
                return true;
            }
        }
        return false;
    }
    
    public boolean documentExists(int ref) {
        return documents.containsKey(ref);
    }
    
    public boolean noteExists(int ref) {
        return notes.containsKey(ref);
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
    
    public boolean isUser(String username, String password) {
        if(this.userExists(username)) {
            return this.getUser(username).isUser(username, password);
        }
        return false;
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
        return this.leaseAccounts.size();
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
    
    public int countNotes() {
        return this.notes.size();
    }
    
    public int countDocuments() {
        return this.documents.size();
    }
    
    public int getPropElementCount() throws SQLException {
        String selectSql = "select count(propertyElementRef) as count from propertyElementValues";
        try(Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            return checkSum;
        }
    }
    
    public int getJobBenefitCount() throws SQLException {
        String selectSql = "select count(jobBenefitRef) as count from jobRoleBenefits";
        try(Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            return checkSum;
        }
    }
    
    public List<Office> getOffices() {
        return Collections.unmodifiableList(new ArrayList<>(offices.values()));
    }
    
    public List<Address> getAddresses() {
        return Collections.unmodifiableList(new ArrayList<>(addresses.values()));
    }
    
    public List<Person> getPeople() {
        return Collections.unmodifiableList(new ArrayList<>(people.values()));
    }
    
    public List<InvolvedParty> getInvolvedParties() {
        return Collections.unmodifiableList(new ArrayList<>(involvedParties.values()));
    }
    
    public List<Landlord> getLandlords() {
        return Collections.unmodifiableList(new ArrayList<>(landlords.values()));
    }
    
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }
    
    public List<Application> getApplications() {
        return Collections.unmodifiableList(new ArrayList<>(applications.values()));
    }
    
    public List<Property> getProperties() {
        return Collections.unmodifiableList(new ArrayList<>(properties.values()));
    }
    
    public List<Tenancy> getTenancies() {
        return Collections.unmodifiableList(new ArrayList<>(tenancies.values()));
    }
    
    public List<Lease> getLeases() {
        return Collections.unmodifiableList(new ArrayList<>(leases.values()));
    }
    
    public List<Contract> getContracts() {
        return Collections.unmodifiableList(new ArrayList<>(contracts.values()));
    }
    
    public List<RentAccount> getRentAccounts() {
        return Collections.unmodifiableList(new ArrayList<>(rentAccounts.values()));
    }
    
    public List<LeaseAccount> getLeaseAccounts() {
        return Collections.unmodifiableList(new ArrayList<>(leaseAccounts.values()));
    }
    
    public List<EmployeeAccount> getEmployeeAccounts() {
        return Collections.unmodifiableList(new ArrayList<>(employeeAccounts.values()));
    }
    
    public List<Element> getTitles() {
        return Collections.unmodifiableList(new ArrayList<>(titles.values()));
    }
    
    public List<Element> getGenders() {
        return Collections.unmodifiableList(new ArrayList<>(genders.values()));
    }
    
    public List<Element> getMaritalStatuses() {
        return Collections.unmodifiableList(new ArrayList<>(maritalStatuses.values()));
    }
    
    public List<Element> getEthnicOrigins() {
        return Collections.unmodifiableList(new ArrayList<>(ethnicOrigins.values()));
    }
    
    public List<Element> getLanguages() {
        return Collections.unmodifiableList(new ArrayList<>(languages.values()));
    }
    
    public List<Element> getNationalities() {
        return Collections.unmodifiableList(new ArrayList<>(nationalities.values()));
    }
    
    public List<Element> getSexualities() {
        return Collections.unmodifiableList(new ArrayList<>(sexualities.values()));
    }
    
    public List<Element> getReligions() {
        return Collections.unmodifiableList(new ArrayList<>(religions.values()));
    }
    
    public List<Element> getPropertyTypes() {
        return Collections.unmodifiableList(new ArrayList<>(propertyTypes.values()));
    }
    
    public List<Element> getPropertySubTypes() {
        return Collections.unmodifiableList(new ArrayList<>(propertySubTypes.values()));
    }
    
    public List<Element> getPropElements() {
        return Collections.unmodifiableList(new ArrayList<>(propertyElements.values()));
    }
    
    public List<Element> getContactTypes() {
        return Collections.unmodifiableList(new ArrayList<>(contactTypes.values()));
    }
    
    public List<Element> getEndReasons() {
        return Collections.unmodifiableList(new ArrayList<>(endReasons.values()));
    }
    
    public List<JobRole> getJobRoles() {
        return Collections.unmodifiableList(new ArrayList<>(jobRoles.values()));
    }
    
    public List<Element> getRelationships() {
        return Collections.unmodifiableList(new ArrayList<>(relationships.values()));
    }
    
    public List<Element> getJobBenefits() {
        return Collections.unmodifiableList(new ArrayList<>(jobBenefits.values()));
    }
    
    public List<Element> getJobRequirements() {
        return Collections.unmodifiableList(new ArrayList<>(jobRequirements.values()));
    }
    
    public List<Element> getTenancyTypes() {
        return Collections.unmodifiableList(new ArrayList<>(tenancyTypes.values()));
    }
    
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(new ArrayList<>(transactions.values()));
    }
    
    public List<JobRoleBenefit> getJobRoleBenefits() {
        return Collections.unmodifiableList(new ArrayList<>(jobRoleBenefits.values()));
    }
    
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(new ArrayList<>(contacts.values()));
    }
    
    public List<AddressUsage> getAddressUsages() {
        return Collections.unmodifiableList(new ArrayList<>(addressUsages.values()));
    }
    
    private List<UserImpl> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(users.values()));
    }
    
    public List<Person> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, int addrRef, Date addressStartDate, String createdBy, Date createdDate) {
        List<Person> tempPeople = new ArrayList<>(this.getPeople());
        if(!tempPeople.isEmpty()) {
            for(Person temp : tempPeople) {
                if(titleCode != null && !titleCode.isEmpty() && this.titleExists(titleCode) && !titleCode.equals(temp.getTitle().getCode())) {
                    tempPeople.remove(temp);
                } else if(forename != null && !forename.isEmpty() && !forename.equals(temp.getForename())) {
                    tempPeople.remove(temp);
                } else if(middleNames != null && !middleNames.isEmpty() && !middleNames.equals(temp.getMiddleNames())) {
                    tempPeople.remove(temp);
                } else if(surname != null && !surname.isEmpty() && !surname.equals(temp.getSurname())) {
                    tempPeople.remove(temp);
                } else if(dateOfBirth != null && dateOfBirth.compareTo(temp.getDateOfBirth()) != 0) {
                    tempPeople.remove(temp);
                } else if(nationalInsurance != null && !nationalInsurance.isEmpty() && nationalInsurance.equals(temp.getNI())) {
                    tempPeople.remove(temp);
                } else if (maritalStatusCode != null && !maritalStatusCode.isEmpty() && this.maritalStatusExists(maritalStatusCode) && !maritalStatusCode.equals(temp.getMaritalStatus().getCode())) {
                    tempPeople.remove(temp);
                } else if (ethnicOriginCode != null && !ethnicOriginCode.isEmpty() && this.ethnicOriginExists(ethnicOriginCode) && !ethnicOriginCode.equals(temp.getEthnicOrigin().getCode())) {
                    tempPeople.remove(temp);
                } else if (languageCode != null && !languageCode.isEmpty() && this.languageExists(languageCode) && !languageCode.equals(temp.getLanguage().getCode())) {
                    tempPeople.remove(temp);
                } else if (nationalityCode != null && !nationalityCode.isEmpty() && this.nationalityExists(nationalityCode) && !nationalityCode.equals(temp.getNationality().getCode())) {
                    tempPeople.remove(temp);
                } else if (sexualityCode != null && !sexualityCode.isEmpty() && this.sexualityExists(sexualityCode) && !sexualityCode.equals(temp.getSexuality().getCode())) {
                    tempPeople.remove(temp);
                } else if (religionCode != null && !religionCode.isEmpty() && this.religionExists(religionCode) && !religionCode.equals(temp.getReligion().getCode())) {
                    tempPeople.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempPeople.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempPeople.remove(temp);
                }
            }
            return tempPeople;
        }
        return null;
    }
    
    public List<Address> getAddresses(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) {
        List<Address> tempAddresses = new ArrayList<>(this.getAddresses());
        if(!tempAddresses.isEmpty()) {
            for(Address temp : tempAddresses) {
                if(buildingNumber != null && !buildingNumber.isEmpty() && !buildingNumber.equals(temp.getBuildingNumber())) {
                    tempAddresses.remove(temp);
                } else if(buildingName != null && !buildingName.isEmpty() && !buildingName.equals(temp.getBuildingName())) {
                    tempAddresses.remove(temp);
                } else if(subStreetNumber != null && !subStreetNumber.isEmpty() && !subStreetNumber.equals(temp.getSubStreetNumber())) {
                    tempAddresses.remove(temp);
                } else if(subStreet != null && !subStreet.isEmpty() && !subStreet.equals(temp.getSubStreet())) {
                    tempAddresses.remove(temp);
                } else if(streetNumber != null && !streetNumber.isEmpty() && !streetNumber.equals(temp.getStreetNumber())) {
                    tempAddresses.remove(temp);
                } else if(street != null && !street.isEmpty() && !street.equals(temp.getStreet())) {
                    tempAddresses.remove(temp);
                } else if(area != null && !area.isEmpty() && !area.equals(temp.getArea())) {
                    tempAddresses.remove(temp);
                } else if(town != null && !town.isEmpty() && !town.equals(temp.getTown())) {
                    tempAddresses.remove(temp);
                } else if(country != null && !country.isEmpty() && !country.equals(temp.getCountry())) {
                    tempAddresses.remove(temp);
                } else if(postcode != null && !postcode.isEmpty() && !postcode.equals(temp.getPostcode())) {
                    tempAddresses.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempAddresses.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempAddresses.remove(temp);
                }
            }
            return tempAddresses;
        }
        return null;
    }
    
    public List<Application> getApplications(String corrName, Date appStartDate, Date endDate, String statusCode, Boolean current, String createdBy, Date createdDate) {
        List<Application> tempApplications = new ArrayList<>(this.getApplications());
        if(!tempApplications.isEmpty()) {
            for(Application temp : tempApplications) {
                if(corrName != null && !corrName.isEmpty() && !corrName.equals(temp.getAppCorrName())) {
                    tempApplications.remove(temp);
                } else if(appStartDate != null && appStartDate.compareTo(temp.getAppStartDate()) != 0) {
                    tempApplications.remove(temp);
                } else if (endDate != null && endDate.compareTo(temp.getAppEndDate()) != 0) {
                    tempApplications.remove(temp);
                } else if (statusCode != null && !statusCode.isEmpty() && !statusCode.equals(temp.getAppStatusCode())) {
                    tempApplications.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempApplications.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempApplications.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempApplications.remove(temp);
                }
            }
            return tempApplications;
        }
        return null;
    }
    
    public List<Application> getPeopleApplications(List<Person> tempPeople) {
        List<Application> tempApplications = new ArrayList<>(this.getApplications());
        if (!tempPeople.isEmpty() && !tempApplications.isEmpty()) {
            for (Application temp : tempApplications) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempPeople.size()) {
                    Person tempPerson = tempPeople.get(i);
                    if (temp.isPersonHouseholdMember(tempPerson.getPersonRef())) {
                        cont = false;
                    }
                    i++;
                }
                if (cont) {
                    tempApplications.remove(temp);
                }
            }
            return tempApplications;
        }
        return null;
    }

    public List<Application> getAddressApplications(List<Address> tempAddresses) {
        List<Application> tempApplications = new ArrayList<>(this.getApplications());
        if (!tempAddresses.isEmpty() && !tempApplications.isEmpty()) {
            for (Application temp : tempApplications) {
                boolean cont = true;
                List<AddressUsageInterface> appAddresses = temp.getApplicationAddressess();
                if (appAddresses.isEmpty()) {
                    cont = false;
                } else {
                    int i = 0;
                    while (cont && i < tempAddresses.size()) {
                        Address tempAddress = tempAddresses.get(i);
                        int ind = 0;
                        while (cont && ind < appAddresses.size()) {
                            AddressUsage appAddress = (AddressUsage) appAddresses.get(ind);
                            if (tempAddress.getAddressRef() == appAddress.getAddress().getAddressRef()) {
                                cont = false;
                            }
                            ind++;
                        }
                        i++;
                    }
                }
                if (cont) {
                    tempApplications.remove(temp);
                }
            }
            return tempApplications;
        }
        return null;
    }

    public List<Application> getCorrNameApplcations(String name) {
        List<Application> tempApplications = new ArrayList<>(this.getApplications());
        if (name != null && !name.isEmpty() && !tempApplications.isEmpty()) {
            for (Application tempApp : tempApplications) {
                if (!name.equals(tempApp.getAppCorrName())) {
                    tempApplications.remove(tempApp);
                }
            }
            return tempApplications;
        }
        return null;
    }
    
    public Application getInvPartyApplcation(int invPartyRef) {
        if (this.invPartyExists(invPartyRef)) {
            InvolvedParty invParty = this.getInvolvedParty(invPartyRef);
            if(this.applicationExists(invParty.getApplicationRef())) {
                return this.getApplication(invParty.getApplicationRef());
            }
        }
        return null;
    }
    
    public List<Tenancy> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if (!tempTenancies.isEmpty()) {
            for(Tenancy temp : tempTenancies) {
                if(name != null && name.isEmpty() && name.equals(temp.getAgreementName())) {
                    tempTenancies.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempTenancies.remove(temp);
                } else if(expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                    tempTenancies.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                    tempTenancies.remove(temp);
                } else if(length != null && length != temp.getLength()) {
                    tempTenancies.remove(temp);
                } else if (propRef != null && this.propertyExists(propRef) && propRef != temp.getProperty().getPropRef()) {
                    tempTenancies.remove(temp);
                } else if (appRef != null && this.applicationExists(appRef) && appRef != temp.getApplication().getApplicationRef()) {
                    tempTenancies.remove(temp);
                } else if (tenTypeCode != null && !tenTypeCode.isEmpty() && this.tenancyTypeExists(tenTypeCode) && !tenTypeCode.equals(temp.getTenType().getCode())) {
                    tempTenancies.remove(temp);
                } else if(accountRef != null && this.rentAccountExists(accountRef) && accountRef != temp.getAccountRef()) {
                    tempTenancies.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempTenancies.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempTenancies.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempTenancies.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getApplicationTenancies(List<Application> tempApplications) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if(!tempApplications.isEmpty() && !tempTenancies.isEmpty()) {
            for(Tenancy temp : tempTenancies) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempApplications.size()) {
                    Application tempApp = tempApplications.get(i);
                    if(temp.getApplication().getApplicationRef() == tempApp.getApplicationRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getApplcationTenancies(int appRef) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if (this.applicationExists(appRef) && !tempTenancies.isEmpty()) {
            Application tempApp = this.getApplication(appRef);
            for (Tenancy temp : tempTenancies) {
                if (temp.getApplication().getApplicationRef() != tempApp.getApplicationRef()) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getPropertyTenancies(List<Property> tempProperties) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if(!tempProperties.isEmpty()) {
            for(Tenancy temp : tempTenancies) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempProperties.size()) {
                    Property tempProperty = tempProperties.get(i);
                    if(temp.getProperty().getPropRef() == tempProperty.getPropRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getPropertyTenancies(int propRef) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if (this.propertyExists(propRef) && !tempTenancies.isEmpty()) {
            Property tempApp = this.getProperty(propRef);
            for (Tenancy temp : tempTenancies) {
                if (temp.getProperty().getPropRef() != tempApp.getPropRef()) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getNameTenancies(String name) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if(!tempTenancies.isEmpty()) {
            for(Tenancy temp : tempTenancies) {
                if(!name.equals(temp.getAgreementName())) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Tenancy> getOfficeTenancies(String office) {
        List<Tenancy> tempTenancies = new ArrayList<>(this.getTenancies());
        if(this.officeExists(office) && !tempTenancies.isEmpty()) {
            for(Tenancy temp : tempTenancies) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempTenancies.remove(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }
    
    public List<Lease> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if (!tempLeases.isEmpty()) {
            for(Lease temp : tempLeases) {
                if(name != null && name.isEmpty() && name.equals(temp.getAgreementName())) {
                    tempLeases.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempLeases.remove(temp);
                } else if(expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                    tempLeases.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                    tempLeases.remove(temp);
                }  else if(length != null && length != temp.getLength()) {
                    tempLeases.remove(temp);
                } else if (propRef != null && this.propertyExists(propRef) && propRef != temp.getProperty().getPropRef()) {
                    tempLeases.remove(temp);
                } else if (management != null && management != temp.isFullManagement()) {
                    tempLeases.remove(temp);
                } else if (expenditure != null && expenditure != temp.getExpenditure()) {
                    tempLeases.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempLeases.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempLeases.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempLeases.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }
    
    public List<Lease> getPropertyLeases(List<Property> tempProperties) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if(!tempProperties.isEmpty() && !tempLeases.isEmpty()) {
            for(Lease temp : tempLeases) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempProperties.size()) {
                    Property tempProperty = tempProperties.get(i);
                    if(temp.getProperty().getPropRef() == tempProperty.getPropRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }
    
    public List<Lease> getPropertyLeases(int propRef) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if (this.propertyExists(propRef) && !tempLeases.isEmpty()) {
            Property tempProperty = this.getProperty(propRef);
            for (Lease temp : tempLeases) {
                if (temp.getProperty().getPropRef() != tempProperty.getPropRef()) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }
    
    public List<Lease> getNameLeases(String name) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if(!tempLeases.isEmpty()) {
            for(Lease temp : tempLeases) {
                if(!name.equals(temp.getAgreementName())) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }
    
    public List<Lease> getOfficeLeases(String office) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if(this.officeExists(office) && !tempLeases.isEmpty()) {
            for(Lease temp : tempLeases) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<Lease> getLandlordLeases(int landlordRef) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if (this.landlordExists(landlordRef) && !tempLeases.isEmpty()) {
            for (Lease temp : tempLeases) {
                List<LandlordInterface> tempLandlords = temp.getLandlords();
                boolean cont = true;
                int i = 0;
                while (cont && i < tempLandlords.size()) {
                    LandlordInterface tempLandlord = tempLandlords.get(i);
                    if (tempLandlord.getLandlordRef() == landlordRef) {
                        cont = false;
                    }
                    i++;
                }
                if (cont) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<Lease> getLandlordLeases(List<Landlord> tempLandlords) {
        List<Lease> tempLeases = new ArrayList<>(this.getLeases());
        if (!tempLandlords.isEmpty() && !tempLeases.isEmpty()) {
            for (Lease temp : tempLeases) {
                List<LandlordInterface> leaseLandlords = temp.getLandlords();
                boolean cont = true;
                int i = 0;
                int ind = 0;
                while (cont && i < leaseLandlords.size()) {
                    LandlordInterface tempLeaseLandlord = leaseLandlords.get(i);
                    while (cont && ind < tempLandlords.size()) {
                        Landlord tempLandlord = tempLandlords.get(ind);
                        if (tempLandlord.getLandlordRef() == tempLeaseLandlord.getLandlordRef()) {
                            cont = false;
                        }
                        ind++;
                    }
                    i++;
                }
                if (cont) {
                    tempLeases.remove(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }
    
    public List<Contract> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if (!tempContracts.isEmpty()) {
            for(Contract temp : tempContracts) {
                if(name != null && name.isEmpty() && name.equals(temp.getAgreementName())) {
                    tempContracts.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempContracts.remove(temp);
                } else if(expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                    tempContracts.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                    tempContracts.remove(temp);
                }  else if(length != null && length != temp.getLength()) {
                    tempContracts.remove(temp);
                } else if (employeeRef != null && this.employeeExists(employeeRef) && employeeRef != temp.getEmployee().getEmployeeRef()) {
                    tempContracts.remove(temp);
                } else if (jobRoleCode != null && this.jobRoleExists(jobRoleCode) && jobRoleCode.equals(temp.getJobRole().getJobRoleCode())) {
                    tempContracts.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempContracts.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempContracts.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempContracts.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempContracts.remove(temp);
                }
            }
        }
        return tempContracts;
    }
    
    public List<Contract> getNameContracts(String name) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if(!tempContracts.isEmpty()) {
            for(Contract temp : tempContracts) {
                if(!name.equals(temp.getAgreementName())) {
                    tempContracts.remove(temp);
                }
            }
        }
        return tempContracts;
    }
    
    public List<Contract> getOfficeContracts(String office) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if(this.officeExists(office) && !tempContracts.isEmpty()) {
            for(Contract temp : tempContracts) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempContracts.remove(temp);
                }
            }
        }
        return tempContracts;
    }
    
    public List<Contract> getEmployeeContracts(int ref) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if (this.employeeExists(ref) && !tempContracts.isEmpty()) {
            for (Contract temp : tempContracts) {
                if (temp.getEmployeeRef() != ref) {
                    tempContracts.remove(temp);
                }
            }
            return tempContracts;
        }
        return null;
    }
    
    public List<Contract> getJobRoleContracts(String code) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if (this.jobRoleExists(code) && !tempContracts.isEmpty()) {
            for (Contract temp : tempContracts) {
                if (!code.equals(temp.getJobRole().getJobRoleCode())) {
                    tempContracts.remove(temp);
                }
            }
            return tempContracts;
        }
        return null;
    }
    
    public List<Contract> getJobRoleContracts(List<JobRole> tempJobRoles) {
        List<Contract> tempContracts = new ArrayList<>(this.getContracts());
        if(!tempJobRoles.isEmpty()) {
            for(Contract temp : tempContracts) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempJobRoles.size()) {
                    JobRole tempJobRole = tempJobRoles.get(i);
                    if(tempJobRole.getJobRoleCode().equals(temp.getJobRoleCode())) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempContracts.remove(temp);
                }
            }
        }
        return tempContracts;
    }
    
    public List<RentAccount> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<RentAccount> tempRentAccounts = new ArrayList<>(this.getRentAccounts());
        if (!tempRentAccounts.isEmpty()) {
            for(RentAccount temp : tempRentAccounts) {
                if(name != null && name.isEmpty() && name.equals(temp.getAccName())) {
                    tempRentAccounts.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempRentAccounts.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                    tempRentAccounts.remove(temp);
                } else if(balance != null && balance != temp.getBalance()) {
                    tempRentAccounts.remove(temp);
                } else if (rent != null && rent != temp.getRent()) {
                    tempRentAccounts.remove(temp);
                } else if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getTenancyRef()) {
                    tempRentAccounts.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempRentAccounts.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempRentAccounts.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempRentAccounts.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempRentAccounts.remove(temp);
                }
            }
        }
        return tempRentAccounts;
    }
    
    public List<RentAccount> getNameRentAcc(String name) {
        List<RentAccount> tempRentAcc = new ArrayList<>(this.getRentAccounts());
        if(!tempRentAcc.isEmpty()) {
            for(RentAccount temp : tempRentAcc) {
                if(!name.equals(temp.getAccName())) {
                    tempRentAcc.remove(temp);
                }
            }
        }
        return tempRentAcc;
    }
    
    public List<RentAccount> getOfficeRentAcc(String office) {
        List<RentAccount> tempRentAcc = new ArrayList<>(this.getRentAccounts());
        if(this.officeExists(office) && !tempRentAcc.isEmpty()) {
            for(RentAccount temp : tempRentAcc) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempRentAcc.remove(temp);
                }
            }
        }
        return tempRentAcc;
    }
    
    public List<RentAccount> getTenanciesRentAccounts(List<Tenancy> tempTenancies) {
        List<RentAccount> tempRentAccounts = new ArrayList<>(this.getRentAccounts());
        if(!tempTenancies.isEmpty()) {
            for(RentAccount temp : tempRentAccounts) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempTenancies.size()) {
                    Tenancy tempTenancy = tempTenancies.get(i);
                    if(tempTenancy.getAgreementRef() == temp.getTenancyRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempRentAccounts.remove(temp);
                }
            }
        }
        return tempRentAccounts;
    }
    
    public RentAccount getTenancyRentAcc(int ref) {
        if (this.tenancyExists(ref)) {
            Tenancy temp = this.getTenancy(ref);
            if(this.rentAccountExists(temp.getAccountRef())) {
                return this.getRentAccount(temp.getAccountRef());
            }
        }
        return null;
    }
    
    public List<LeaseAccount> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<LeaseAccount> tempLeaseAccounts = new ArrayList<>(this.getLeaseAccounts());
        if (!tempLeaseAccounts.isEmpty()) {
            for(LeaseAccount temp : tempLeaseAccounts) {
                if(name != null && name.isEmpty() && name.equals(temp.getAccName())) {
                    tempLeaseAccounts.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempLeaseAccounts.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                    tempLeaseAccounts.remove(temp);
                } else if(balance != null && balance != temp.getBalance()) {
                    tempLeaseAccounts.remove(temp);
                } else if (expenditure != null && expenditure != temp.getExpenditure()) {
                    tempLeaseAccounts.remove(temp);
                } else if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getLeaseRef()) {
                    tempLeaseAccounts.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempLeaseAccounts.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempLeaseAccounts.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempLeaseAccounts.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempLeaseAccounts.remove(temp);
                }
            }
        }
        return tempLeaseAccounts;
    }
    
    public List<LeaseAccount> getNameLeaseAcc(String name) {
        List<LeaseAccount> tempLeaseAcc = new ArrayList<>(this.getLeaseAccounts());
        if(!tempLeaseAcc.isEmpty()) {
            for(LeaseAccount temp : tempLeaseAcc) {
                if(!name.equals(temp.getAccName())) {
                    tempLeaseAcc.remove(temp);
                }
            }
        }
        return tempLeaseAcc;
    }
    
    public List<LeaseAccount> getOfficeLeaseAcc(String office) {
        List<LeaseAccount> tempLeaseAcc = new ArrayList<>(this.getLeaseAccounts());
        if(this.officeExists(office) && !tempLeaseAcc.isEmpty()) {
            for(LeaseAccount temp : tempLeaseAcc) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempLeaseAcc.remove(temp);
                }
            }
        }
        return tempLeaseAcc;
    }
    
    public List<LeaseAccount> getLeasesLeaseAccounts(List<Lease> tempTenancies) {
        List<LeaseAccount> tempLeaseAccounts = new ArrayList<>(this.getLeaseAccounts());
        if(!tempTenancies.isEmpty()) {
            for(LeaseAccount temp : tempLeaseAccounts) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempTenancies.size()) {
                    Lease tempLease = tempTenancies.get(i);
                    if(tempLease.getAgreementRef() == temp.getLeaseRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempLeaseAccounts.remove(temp);
                }
            }
        }
        return tempLeaseAccounts;
    }
    
    public LeaseAccount getLeaseLeaseAcc(int ref) {
        if (this.leaseExists(ref)) {
            Lease temp = this.getLease(ref);
            if(this.rentAccountExists(temp.getAccountRef())) {
                return this.getLeaseAccount(temp.getAccountRef());
            }
        }
        return null;
    }
    
    public List<EmployeeAccount> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef,  String officeCode, Boolean current, String createdBy, Date createdDate) {
        List<EmployeeAccount> tempEmployeeAccounts = new ArrayList<>(this.getEmployeeAccounts());
        if (!tempEmployeeAccounts.isEmpty()) {
            for(EmployeeAccount temp : tempEmployeeAccounts) {
                if(name != null && name.isEmpty() && name.equals(temp.getAccName())) {
                    tempEmployeeAccounts.remove(temp);
                } else if(startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempEmployeeAccounts.remove(temp);
                } else if(endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                    tempEmployeeAccounts.remove(temp);
                } else if(balance != null && balance != temp.getBalance()) {
                    tempEmployeeAccounts.remove(temp);
                } else if (salary != null && salary != temp.getSalary()) {
                    tempEmployeeAccounts.remove(temp);
                } else if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getContractRef()) {
                    tempEmployeeAccounts.remove(temp);
                } else if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                    tempEmployeeAccounts.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempEmployeeAccounts.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempEmployeeAccounts.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempEmployeeAccounts.remove(temp);
                }
            }
        }
        return tempEmployeeAccounts;
    }
    
    public List<EmployeeAccount> getNameEmployeeAcc(String name) {
        List<EmployeeAccount> tempEmployeeAcc = new ArrayList<>(this.getEmployeeAccounts());
        if(!tempEmployeeAcc.isEmpty()) {
            for(EmployeeAccount temp : tempEmployeeAcc) {
                if(!name.equals(temp.getAccName())) {
                    tempEmployeeAcc.remove(temp);
                }
            }
        }
        return tempEmployeeAcc;
    }
    
    public List<EmployeeAccount> getOfficeEmployeeAcc(String office) {
        List<EmployeeAccount> tempEmployeeAcc = new ArrayList<>(this.getEmployeeAccounts());
        if(this.officeExists(office) && !tempEmployeeAcc.isEmpty()) {
            for(EmployeeAccount temp : tempEmployeeAcc) {
                if(!office.equals(temp.getOfficeCode())) {
                    tempEmployeeAcc.remove(temp);
                }
            }
        }
        return tempEmployeeAcc;
    }
    
    public List<EmployeeAccount> getContractsEmployeeAccounts(List<Contract> tempTenancies) {
        List<EmployeeAccount> tempEmployeeAccounts = new ArrayList<>(this.getEmployeeAccounts());
        if(!tempTenancies.isEmpty()) {
            for(EmployeeAccount temp : tempEmployeeAccounts) {
                boolean cont = true;
                int i = 0;
                while(cont && i < tempTenancies.size()) {
                    Contract tempContract = tempTenancies.get(i);
                    if(tempContract.getAgreementRef() == temp.getContractRef()) {
                        cont = false;
                    }
                    i++;
                }
                if(cont) {
                    tempEmployeeAccounts.remove(temp);
                }
            }
        }
        return tempEmployeeAccounts;
    }
    
    public EmployeeAccount getContractEmployeeAcc(int ref) {
        if (this.contractExists(ref)) {
            Contract temp = this.getContract(ref);
            if(this.employeeAccountExists(temp.getAccountRef())) {
                return this.getEmployeeAccount(temp.getAccountRef());
            }
        }
        return null;
    }
    
    public List<Office> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) {
        List<Office> tempOffices = new ArrayList<>(this.getOffices());
        if(!tempOffices.isEmpty()) {
            for(Office temp : tempOffices) {
                if(addrRef != null && addrRef == temp.getAddress().getAddressRef()) {
                    tempOffices.remove(temp);
                } else if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                    tempOffices.remove(temp);
                } else if (current != null && current != temp.isCurrent()) {
                    tempOffices.remove(temp);
                } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                    tempOffices.remove(temp);
                } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                    tempOffices.remove(temp);
                }
            }
        }
        return tempOffices;
    }
}
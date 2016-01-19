/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.DateConversion;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.ApplicationInterface;
import interfaces.ContactInterface;
import interfaces.ContractInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.EmployeeAccountInterface;
import interfaces.EmployeeInterface;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PersonInterface;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import interfaces.TransactionInterface;
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
    private final Map<String, OfficeInterface> offices;

    private final Map<Integer, PersonInterface> people;

    private final Map<Integer, InvolvedPartyInterface> involvedParties;
    private final Map<Integer, LandlordInterface> landlords;
    private final Map<Integer, EmployeeInterface> employees;

    private final Map<Integer, ApplicationInterface> applications;
    private final Map<Integer, PropertyInterface> properties;

    private final Map<Integer, TenancyInterface> tenancies;
    private final Map<Integer, LeaseInterface> leases;
    private final Map<Integer, ContractInterface> contracts;

    private final Map<Integer, RentAccountInterface> rentAccounts;
    private final Map<Integer, LeaseAccountInterface> leaseAccounts;
    private final Map<Integer, EmployeeAccountInterface> employeeAccounts;

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
    private final Map<String, JobRoleInterface> jobRoles;
    private final Map<String, Element> jobBenefits;
    private final Map<String, Element> jobRequirements;
    private final Map<Integer, JobRoleBenefitInterface> jobRoleBenefits;

    // Lists of Property details
    private final Map<Integer, AddressInterface> addresses;
    private final Map<String, Element> propertyTypes; // House, Flat, Bungalow
    private final Map<String, Element> propertySubTypes; // Terraced, Semi-detached
    private final Map<String, Element> propertyElements;
    private final Map<Integer, PropertyElementInterface> propertyElementValues;

    // List of Account Transactions
    private final Map<Integer, TransactionInterface> transactions;

    private final Map<Integer, AddressUsageInterface> addressUsages;
    private final Map<Integer, ContactInterface> contacts;
    private final Map<String, User> users;
    private final Map<Integer, Note> notes;
    private final Map<Integer, Document> documents;

    ///   CONSTRUCTORS ///
    /**
     * Constructor for objects of class Database
     *
     * @param environment
     * @param addr
     * @param username
     * @param password
     * @param port
     */
    public Database(String environment, String addr, String username, String password, Integer port) {
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
        } catch (SQLException | RemoteException ex) {
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

        if (env == null) {
            env = "LIVE";
        } else if (address == null) {
            address = "localhost";
        } else if (user == null) {
            user = "root";
        } else if (passw == null) {
            passw = "Toxic9489!999";
        }

        String url = "jdbc:mysql://" + address + ":" + port + "/msc_properties" + env;
        // jdbc: database type : localhost because it is on my machine : 3306 for port 3306 : msc_properties(+ enviornment) for database name
        this.con = DriverManager.getConnection(url, user, passw);
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
     * @throws RemoteException
     */
    private void load() throws SQLException, RemoteException {
        if (this.con != null) {
            try {
                this.loadSuperUser();
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
                ex.printStackTrace();
                System.out.println("Cant load System data");
            }
        } else if (this.con == null) {
            System.out.println("Connection is null");
        }
    }
    
    private void loadSuperUser() throws RemoteException, SQLException {
        String sql = "select username, employeeRef, password, otherRead, otherWrite, otherUpdate, employeeRead, employeeWrite, employeeUpdate from users where username='ADMIN'";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            
            while (results.next()) {
                int employeeRef = results.getInt("employeeRef");
                String username = results.getString("username");
                String password = results.getString("password");
                boolean read = results.getBoolean("otherRead");
                boolean write = results.getBoolean("otherWrite");
                boolean update = results.getBoolean("otherUpdate");
                boolean employeeRead = results.getBoolean("employeeRead");
                boolean employeeWrite = results.getBoolean("employeeWrite");
                boolean employeeUpdate = results.getBoolean("employeeUpdate");
                UserImpl temp = new UserImpl(employeeRef, -1, username, password, null);
                temp.setUserPermissions(read, write, update, employeeRead, employeeWrite, employeeUpdate);
                
                users.put(username, temp);
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param from
     * @param element
     * @throws SQLException
     */
    private void createElement(String from, Element element) throws SQLException, RemoteException {
        String insertSql = "insert into " + from + " (code, description, noteRef, comment, cur, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
            int col = 1;
            insertStat.setString(col++, element.getCode());
            insertStat.setString(col++, element.getDescription());
            insertStat.setInt(col++, element.getNote().getReference());
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
    private void updateElement(String from, Element element) throws SQLException, RemoteException {
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

    /**
     *
     * @param from
     * @param code
     * @throws SQLException
     */
    private void deleteElement(String from, String code) throws SQLException {
        String deleteSql = "delete from " + from + " where code='" + code + "'";
        try (Statement deleteStat = this.con.createStatement()) {
            deleteStat.executeUpdate(deleteSql);
            deleteStat.close();
        }
    }

    /**
     *
     * @param from
     * @return
     * @throws SQLException
     */
    private List<ElementImpl> loadElements(String from) throws SQLException, RemoteException {
        String sql = "select code, description, noteRef, comment, cur, createdBy, createdDate from " + from + " order by createdDate";
        List<ElementImpl> elements = new ArrayList<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
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
            selectStat.close();
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
    private void createModifiedBy(String from, ModifiedByInterface modifiedBy, int ref) throws SQLException, RemoteException {
        if (modifiedBy != null) {
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
    private Map<Integer, ModifiedByInterface> loadModMap(String from, int reference) throws SQLException, RemoteException {
        String sql = "select modificationRef, ref, modifiedBy, modifiedDate, description from " + from + " order by modificationRef";
        Map<Integer, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int modificationRef = results.getInt("modificationRef");
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, description, modifiedDate);

                    modifiedByMap.put(modificationRef, temp);
                }
            }
            selectStat.close();
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
    private void createModifiedBy(String from, ModifiedByInterface modifiedBy, String code) throws SQLException, RemoteException {
        if (modifiedBy != null) {
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
    private Map<Integer, ModifiedByInterface> loadModMap(String from, String uniqueCode) throws SQLException, RemoteException {
        String sql = "select modificationRef, code, modifiedBy, modifiedDate, description from " + from + " order by modificationRef";
        Map<Integer, ModifiedByInterface> modifiedByMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            while (results.next()) {
                int modificationRef = results.getInt("modificationRef");
                String code = results.getString("code");
                if (uniqueCode.equals(code)) {
                    String modifiedBy = results.getString("modifiedBy");
                    Date modifiedDate = results.getDate("modifiedDate");
                    String description = results.getString("description");

                    ModifiedBy temp = new ModifiedBy(modifiedBy, description, modifiedDate);
                    modifiedByMap.put(modificationRef, temp);
                }
            }
            selectStat.close();
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
    private Map<Integer, Document> loadDocMap(String from, int reference) throws SQLException, RemoteException {
        String sql = "select uniqueRef, documentRef, ref, documentName, documentPath, noteRef, comment, createdBy, createdDate from " + from + " order by uniqueRef";
        Map<Integer, Document> fileMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int documentRef = results.getInt("documentRef");
                int ref = results.getInt("ref");
                if (reference == ref) {
                    String documentName = results.getString("documentName");
                    String documentPath = results.getString("documentPath");
                    int noteRef = results.getInt("noteRef");
                    String comment = results.getString("comment");
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    
                    //  InputStream input = Database.class.getResourceAsStream(documentPath);   //// NEED TO LOOK INTO HOW THIS WORKS

                    File document = new File(documentPath); // Possibly might not work once File is used, may need to use getResourceAsStream
                    if (!this.documentExists(documentRef)) {
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        DocumentImpl temp = new DocumentImpl(documentRef, document, note, createdBy, createdDate);
                        documents.put(temp.getDocumentRef(), temp);
                        this.createDocumentMods(temp, this.loadModMap("documentModifications", documentRef));
                        fileMap.put(documentRef, temp);
                    } else if (this.documentExists(documentRef)) {
                        DocumentImpl temp = (DocumentImpl) this.getDocument(ref);
                        temp.createNewVersion(document, null);
                    }
                }
            }
            selectStat.close();
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
    private Map<Integer, Document> loadDocMap(String from, String uniqueCode) throws SQLException, RemoteException {
        String sql = "select uniqueRef, documentRef, code, documentName, documentPath, noteRef, comment, createdBy, createdDate from " + from + " order by uniqueRef";
        Map<Integer, Document> fileMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
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
                    File document = new File(documentPath); // Possibly might not work once File is used, may need to use getResourceAsStream
                    if (!this.documentExists(documentRef)) {
                        Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                        DocumentImpl temp = new DocumentImpl(documentRef, document, note, createdBy, createdDate);
                        documents.put(temp.getDocumentRef(), temp);
                        this.createDocumentMods(temp, this.loadModMap("documentModifications", documentRef));
                        fileMap.put(documentRef, temp);
                    } else if (this.documentExists(documentRef)) {
                        DocumentImpl temp = (DocumentImpl) this.getDocument(documentRef);
                        temp.createNewVersion(document, null);
                    }
                }
            }
            selectStat.close();
        }
        return fileMap;
    }

    /**
     *
     * @param from
     * @param ref
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    private void createDocument(String from, int ref, Document document) throws SQLException, RemoteException {
        if (!this.documentExists(document.getDocumentRef())) {
            String insertSql = "insert into " + from + " (documentRef, ref, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, document.getDocumentName(document.getPreviousVersions().size() + 1));   
                insertStat.setString(col++, document.getDocumentPath(document.getPreviousVersions().size() + 1));
                insertStat.setInt(col++, document.getNote().getReference());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.documents.put(document.getDocumentRef(), document);
        }
    }

    /**
     *
     * @param from
     * @param ref
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    private void updateDocument(String from, int ref, int dRef) throws SQLException, RemoteException {
        if (this.documentExists(dRef)) {
            Document document = this.getDocument(dRef);
            String insertSql = "insert into " + from + " (documentRef, ref, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, document.getDocumentName(document.getPreviousVersions().size() + 1) + ".pdf");
                insertStat.setString(col++, document.getDocumentPath(document.getPreviousVersions().size() + 1));
                insertStat.setInt(col++, document.getNote().getReference());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getLastModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getLastModifiedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.createModifiedBy("documentModifications", document.getLastModification(), document.getDocumentRef());
        }
    }

    /**
     *
     * @param from
     * @param ref
     * @param documentRef
     * @throws SQLException
     */
    private void deleteDocument(String from, int ref, int documentRef) throws SQLException {
        if (this.documentExists(documentRef) && this.canDeleteDocument(documentRef)) {
            String deleteSql = "delete from " + from + " where documentRef=" + documentRef + " and ref=" + ref;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.documents.remove(documentRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    private void createDocument(String from, String code, Document document) throws SQLException, RemoteException {
        if (!this.documentExists(document.getDocumentRef())) {
            String insertSql = "insert into " + from + " (documentRef, code, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setString(col++, code);
                insertStat.setString(col++, document.getDocumentName(document.getPreviousVersions().size() + 1) + ".pdf");
                insertStat.setString(col++, document.getDocumentPath(document.getPreviousVersions().size() + 1));
                insertStat.setInt(col++, document.getNote().getReference());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.documents.put(document.getDocumentRef(), document);
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    private void updateDocument(String from, String code, int dRef) throws SQLException, RemoteException {
        if (this.documentExists(dRef)) {
            Document document = this.getDocument(dRef);
            String insertSql = "insert into " + from + " (documentRef, code, documentName, documentPath, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, document.getDocumentRef());
                insertStat.setString(col++, code);
                insertStat.setString(col++, document.getDocumentName(document.getPreviousVersions().size() + 1) + ".pdf");
                insertStat.setString(col++, document.getDocumentPath(document.getPreviousVersions().size() + 1));
                insertStat.setInt(col++, document.getNote().getReference());
                insertStat.setString(col++, document.getComment());
                insertStat.setString(col++, document.getLastModifiedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(document.getLastModifiedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.createModifiedBy("documentModifications", document.getLastModification(), document.getDocumentRef());
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param documentRef
     * @throws SQLException
     */
    private void deleteDocument(String from, String code, int documentRef) throws SQLException {
        if (this.documentExists(documentRef) && this.canDeleteDocument(documentRef)) {
            String deleteSql = "delete from " + from + " where documentRef=" + documentRef + " and code='" + code + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.documents.remove(documentRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param documentRef
     * @return
     */
    public boolean canDeleteDocument(int documentRef) {
        return (this.documentExists(documentRef));
    }
    
    private void createDocumentMods(DocumentImpl doc, Map<Integer, ModifiedByInterface> loadadMods) throws RemoteException {
        if (doc != null && this.documentExists(doc.getDocumentRef()) && !loadadMods.isEmpty()) {
            Iterator it = loadadMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                doc.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param ref
     * @return
     */
    public Document getDocument(int ref) {
        if (this.noteExists(ref)) {
            return documents.get(ref);
        }
        return null;
    }

    /**
     *
     * @param from
     * @param reference
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    private Map<Integer, Note> loadNoteMap(String from, int reference) throws SQLException, RemoteException {
        String sql = "select noteRef, ref, comment, createdBy, createdDate from " + from + " order by noteRef";
        Map<Integer, Note> noteMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
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
            selectStat.close();
        }
        return noteMap;
    }

    /**
     *
     * @param from
     * @param uniqueCode
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    private Map<Integer, Note> loadNoteMap(String from, String uniqueCode) throws SQLException, RemoteException {
        String sql = "select noteRef, code, comment, createdBy, createdDate from " + from + " order by noteRef";
        Map<Integer, Note> noteMap = new HashMap<>();
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
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
            selectStat.close();
        }
        return noteMap;
    }

    /**
     *
     * @param ref
     * @throws RemoteException
     */
    private void deleteNote(int ref) throws RemoteException {
        if (this.noteExists(ref) && !this.getNote(ref).hasBeenModified()) {
            notes.remove(ref);
        }
    }

    /**
     *
     * @param from
     * @param ref
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    private void createNote(String from, int ref, Note note) throws SQLException, RemoteException {
        if (!this.noteExists(note.getReference())) {
            String insertSql = "insert into " + from + " (noteRef, ref, comment, createdBy, createdDate) values (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, note.getReference());
                insertStat.setInt(col++, ref);
                insertStat.setString(col++, note.getNote());
                insertStat.setString(col++, note.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(note.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.notes.put(note.getReference(), note);
        }
    }

    /**
     *
     * @param from
     * @param ref
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void updateNote(String from, int ref, int noteRef) throws SQLException, RemoteException {
        if (this.noteExists(noteRef)) {
            Note note = this.getNote(noteRef);
            String updateSql = "update " + from + " set comment=? where noteRef=" + noteRef + " and ref=" + ref;
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, note.getNote());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("noteModifications", note.getLastModification(), note.getReference());
        }
    }

    /**
     *
     * @param from
     * @param ref
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void deleteNote(String from, int ref, int noteRef) throws SQLException, RemoteException {
        if (this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            String deleteSql = "delete from " + from + " where noteRef=" + noteRef + " and ref=" + ref;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.notes.remove(noteRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    private void createNote(String from, String code, Note note) throws SQLException, RemoteException {
        if (!this.noteExists(note.getReference())) {
            String insertSql = "insert into " + from + " (noteRef, code, comment, createdBy, createdDate) values (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, note.getReference());
                insertStat.setString(col++, code);
                insertStat.setString(col++, note.getNote());
                insertStat.setString(col++, note.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(note.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.notes.put(note.getReference(), note);
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void updateNote(String from, String code, int noteRef) throws SQLException, RemoteException {
        if (this.noteExists(noteRef)) {
            Note note = this.getNote(noteRef);
            String updateSql = "update " + from + " set comment=? where noteRef=" + noteRef + " and code='" + code + "'";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setString(col++, note.getNote());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("noteModifications", note.getLastModification(), note.getReference());
        }
    }

    /**
     *
     * @param from
     * @param code
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void deleteNote(String from, String code, int noteRef) throws SQLException, RemoteException {
        if (this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            String deleteSql = "delete from " + from + " where noteRef=" + noteRef + " and code='" + code + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.notes.remove(noteRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param noteRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteNote(int noteRef) throws RemoteException {
        return (this.noteExists(noteRef) && !this.getNote(noteRef).hasBeenModified());
    }

    /**
     *
     * @param note
     * @param loadadMods
     * @throws RemoteException
     */
    private void createNoteMods(NoteImpl note, Map<Integer, ModifiedByInterface> loadadMods) throws RemoteException {
        if (note != null && this.noteExists(note.getReference()) && !loadadMods.isEmpty()) {
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

    /**
     *
     * @param ref
     * @return
     */
    public Note getNote(int ref) {
        if (this.noteExists(ref)) {
            return notes.get(ref);
        }
        return null;
    }

    /**
     *
     * @param conType
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createContactType(Element conType) throws SQLException, RemoteException {
        if (conType != null && !this.contactTypeExists(conType.getCode())) {
            this.createElement("contactTypes", conType);
            this.contactTypes.put(conType.getCode(), conType);
            this.notes.put(conType.getNote().getReference(), conType.getNote());
        }
    }

    /**
     *
     * @param contactTypeCode
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updateContactType(String contactTypeCode) throws SQLException, RemoteException {
        if (this.contactTypeExists(contactTypeCode)) {
            Element contactType = this.getContactType(contactTypeCode);
            this.updateElement("contactTypes", contactType);
            this.createModifiedBy("contactTypeModifications", contactType.getLastModification(), contactType.getCode());
        }
    }

    /**
     *
     * @param contactTypeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteContactType(String contactTypeCode) throws SQLException, RemoteException {
        if (this.contactTypeExists(contactTypeCode) && this.canDeleteContactType(contactTypeCode)) {
            this.deleteElement("contactTypes", contactTypeCode);
            this.deleteNote(this.getContactType(contactTypeCode).getNote().getReference());
            this.contactTypes.remove(contactTypeCode);
        }
    }

    /**
     *
     * @param contactTypeCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteContactType(String contactTypeCode) throws RemoteException {
        if (this.contactTypeExists(contactTypeCode) && !this.getContactType(contactTypeCode).hasBeenModified()) {
            for (ContactInterface contact : this.getContacts()) {
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
     * @throws RemoteException
     */
    private void loadContactTypes() throws SQLException, RemoteException {
        this.contactTypes.clear();
        List<ElementImpl> loadedContactTypes;
        loadedContactTypes = this.loadElements("contactTypes");
        if (!loadedContactTypes.isEmpty()) {
            for (Element temp : loadedContactTypes) {
                this.contactTypes.put(temp.getCode(), temp);
                this.notes.put(temp.getNote().getReference(), temp.getNote());
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
        if (this.contactTypeExists(code)) {
            return this.contactTypes.get(code);
        }
        return null;
    }

    /**
     *
     * @param contact
     * @param personRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createPersonContact(Contact contact, int personRef) throws SQLException, RemoteException {
        if (contact != null && !this.contactExists(contact.getContactRef()) && this.personExists(personRef)) {
            String insertSql = "insert into personContacts (contactRef, personRef, contactTypeCode, contactValue, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setInt(col++, personRef);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setInt(col++, contact.getNote().getReference());
                insertStat.setString(col++, contact.getComment());
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            contacts.put(contact.getContactRef(), (Contact) contact);
            notes.put(contact.getNote().getReference(), contact.getNote());
        }
    }

    /**
     *
     * @param contactRef
     * @param personRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updatePersonContact(int contactRef, int personRef) throws SQLException, RemoteException {
        if (this.contactExists(contactRef) && this.personExists(personRef)) {
            ContactInterface contact = this.getContact(contactRef);
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

    /**
     *
     * @param contactRef
     * @param personRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePersonContact(int contactRef, int personRef) throws SQLException, RemoteException {
        if (this.contactExists(contactRef) && this.personExists(personRef) && this.getPerson(personRef).hasContact(contactRef) && this.canDeleteContact(contactRef)) {
            String deleteSql = "delete from personContacts where contactRef=" + contactRef + " and personRef=" + personRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getContact(contactRef).getNote().getReference());
                    this.contacts.remove(contactRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param reference
     * @throws SQLException
     */
    private void loadPersonContacts(int reference) throws SQLException, RemoteException {
        String sql = "select contactRef, personRef, contactTypeCode, contactValue, startDate, endDate, noteRef, comment, createdBy, createdDate from personContacts order by contactRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.personExists(reference)) {
                    Person person = (Person) this.getPerson(reference);
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
                        this.notes.put(note.getReference(), note);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        person.createContact(temp, null);
                        this.loadPersonContactMods(temp.getContactRef(), reference, this.loadModMap("personContactModifications", temp.getContactRef()));
                    }
                }
            }
            selectStat.close();
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
            Contact contact = (Contact) this.getContact(contactRef);
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
     * @throws java.rmi.RemoteException
     */
    public void createOfficeContact(Contact contact, String officeCode) throws SQLException, RemoteException {
        if (contact != null && !this.contactExists(contact.getContactRef()) && this.officeExists(officeCode)) {
            String insertSql = "insert into officeContacts (contactRef, officeCode, contactTypeCode, contactValue, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, contact.getContactRef());
                insertStat.setString(col++, officeCode);
                insertStat.setString(col++, contact.getContactType().getCode());
                insertStat.setString(col++, contact.getContactValue());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getStartDate()));
                insertStat.setInt(col++, contact.getNote().getReference());
                insertStat.setString(col++, contact.getComment());
                insertStat.setString(col++, contact.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(contact.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            contacts.put(contact.getContactRef(), contact);
            notes.put(contact.getNote().getReference(), contact.getNote());
        }
    }

    /**
     *
     * @param contactRef
     * @param officeCode
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updateOfficeContact(int contactRef, String officeCode) throws SQLException, RemoteException {
        if (this.contactExists(contactRef) && this.officeExists(officeCode)) {
            Contact contact = (Contact) this.getContact(contactRef);
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

    /**
     *
     * @param contactRef
     * @param code
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteOfficeContact(int contactRef, String code) throws SQLException, RemoteException {
        if (this.contactExists(contactRef) && this.officeExists(code) && this.getOffice(code).hasContact(contactRef) && this.canDeleteContact(contactRef)) {
            String deleteSql = "delete from officeContacts where contactRef=" + contactRef + " and officeCode='" + code + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getContact(contactRef).getNote().getReference());
                    this.contacts.remove(contactRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param code
     * @throws SQLException
     */
    private void loadOfficeContacts(String code) throws SQLException, RemoteException {
        String sql = "select contactRef, officeCode, contactTypeCode, contactValue, startDate, endDate, noteRef, comment, createdBy, createdDate from officeContacts order by contactRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.officeExists(code)) {
                    Office office = (Office) this.getOffice(code);
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
                        this.notes.put(note.getReference(), note);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        office.createContact(temp, null);
                        this.loadOfficeContactMods(temp.getContactRef(), code, this.loadModMap("officeContactModifications", temp.getContactRef()));
                    }
                }
            }
            selectStat.close();
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
            Contact contact = (Contact) this.getContact(contactRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                contact.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    public boolean canDeleteContact(int contactRef) throws RemoteException {
        return (this.contactExists(contactRef) && !this.getContact(contactRef).hasBeenModified());
    }

    /**
     *
     * @param address
     * @param personRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createPersonAddressUsage(AddressUsage address, int personRef) throws SQLException, RemoteException {
        if (address != null && !this.addressUsageExists(address.getAddressUsageRef()) && this.personExists(personRef)) {
            String insertSql = "insert into personAddresses (addressUsageRef, addressRef, personRef, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, personRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setInt(col++, address.getNote().getReference());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                addressUsages.put(address.getAddressUsageRef(), address);
                notes.put(address.getNote().getReference(), address.getNote());
            }
        }
    }

    /**
     *
     * @param addressRef
     * @param personRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updatePersonAddressUsage(int addressRef, int personRef) throws SQLException, RemoteException {
        if (this.addressUsageExists(addressRef) && this.personExists(personRef)) {
            AddressUsageInterface address = this.getAddressUsage(addressRef);
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

    /**
     *
     * @param personRef
     * @param addrRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePersonAddressUsage(int personRef, int addrRef) throws SQLException, RemoteException {
        if (this.addressUsageExists(addrRef) && this.personExists(personRef) && this.getPerson(personRef).getCurrentAddress().getAddressUsageRef() == addrRef && this.canDeleteAddressUsage(addrRef)) {
            String deleteSql = "delete from personAddresses where addressUsageRef=" + addrRef + " and personRef=" + personRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddressUsage(addrRef).getNote().getReference());
                    this.addressUsages.remove(addrRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param reference
     * @throws SQLException
     */
    private void loadPersonAddresses(int reference) throws SQLException, RemoteException {
        String sql = "select addressUsageRef, addressRef, personRef, startDate, endDate, noteRef, comment, createdBy, createdDate from personAddresses order by addressUsageRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.personExists(reference)) {
                    Person person = (Person) this.getPerson(reference);
                    int addressUsageRef = results.getInt("addressUsageRef");

                    AddressInterface address;
                    if (this.addressExists(results.getInt("addressRef"))) {
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
                        this.notes.put(note.getReference(), note);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        person.createAddress(temp, null);
                        this.loadPersonAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("personAddressModifications", temp.getAddressUsageRef()));
                    }
                }
            }
            selectStat.close();
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
            AddressUsage address = (AddressUsage) this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                address.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param address
     * @param appRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createApplicationAddressUsage(AddressUsage address, int appRef) throws SQLException, RemoteException {
        if (address != null && !this.addressUsageExists(address.getAddressUsageRef()) && this.applicationExists(appRef)) {
            String insertSql = "insert into applicationAddresses (addressUsageRef, addressRef, appRef, startDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = this.con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, address.getAddressUsageRef());
                insertStat.setInt(col++, address.getAddress().getAddressRef());
                insertStat.setInt(col++, appRef);
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getStartDate()));
                insertStat.setInt(col++, address.getNote().getReference());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                addressUsages.put(address.getAddressUsageRef(), address);
                notes.put(address.getNote().getReference(), address.getNote());
            }
        }
    }

    /**
     *
     * @param addressRef
     * @param appRef
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updateApplicationAddressUsage(int addressRef, int appRef) throws SQLException, RemoteException {
        if (this.addressUsageExists(addressRef) && this.applicationExists(appRef)) {
            AddressUsage address = (AddressUsage) this.getAddressUsage(addressRef);
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

    /**
     *
     * @param addrRef
     * @param appRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteApplicationAddressUsage(int addrRef, int appRef) throws SQLException, RemoteException {
        if (this.addressUsageExists(addrRef) && this.applicationExists(appRef) && this.getApplication(appRef).getCurrentApplicationAddress().getAddressUsageRef() == addrRef && this.canDeleteAddressUsage(addrRef)) {
            String deleteSql = "delete from applicationAddresses where addressUsageRef=" + addrRef + " and appRef=" + appRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddressUsage(addrRef).getNote().getReference());
                    this.addressUsages.remove(addrRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param reference
     * @throws SQLException
     */
    private void loadApplicationAddresses(int reference) throws SQLException, RemoteException {
        String sql = "select addressUsageRef, addressRef, appRef, startDate, endDate, noteRef, comment, createdBy, createdDate from applicationAddresses order by addressUsageRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                if (this.applicationExists(reference)) {
                    Application application = (Application) this.getApplication(reference);
                    int addressUsageRef = results.getInt("addressUsageRef");

                    AddressInterface address;
                    if (this.addressExists(results.getInt("addressRef"))) {
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
                        this.notes.put(note.getReference(), note);
                        if (endDate != null) {
                            temp.setEndDate(endDate, null);
                        }
                        application.setAppAddress(temp, null);
                        this.loadApplicationAddressMods(temp.getAddressUsageRef(), reference, this.loadModMap("applicationAddressModifications", temp.getAddressUsageRef()));
                    }
                }
            }
            selectStat.close();
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
            AddressUsage address = (AddressUsage) this.getAddressUsage(addressRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                address.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param addressRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteAddressUsage(int addressRef) throws RemoteException {
        return (this.addressUsageExists(addressRef) && !this.getAddressUsage(addressRef).hasBeenModified());
    }

    /**
     *
     * @param title
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createTitle(Element title) throws SQLException, RemoteException {
        if (!this.titleExists(title.getCode())) {
            this.createElement("titles", title);
            this.titles.put(title.getCode(), title);
            this.notes.put(title.getNote().getReference(), title.getNote());
        }
    }

    /**
     *
     * @param titleCode
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updateTitle(String titleCode) throws SQLException, RemoteException {
        if (this.titleExists(titleCode)) {
            Element title = this.getTitle(titleCode);
            this.updateElement("titles", title);
            this.createModifiedBy("titleModifications", title.getLastModification(), title.getCode());
        }
    }

    /**
     *
     * @param titleCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteTitle(String titleCode) throws SQLException, RemoteException {
        if (this.titleExists(titleCode) && this.canDeleteTitle(titleCode)) {
            this.deleteElement("titles", titleCode);
            this.deleteNote(this.getTitle(titleCode).getNote().getReference());
            this.titles.remove(titleCode);
        }
    }

    /**
     *
     * @param titleCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteTitle(String titleCode) throws RemoteException {
        if (this.titleExists(titleCode) && !this.getTitle(titleCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (titleCode.equals(person.getTitle().getCode())) {
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
    private void loadTitles() throws SQLException, RemoteException {
        this.titles.clear();
        List<ElementImpl> loadedTitles;
        loadedTitles = this.loadElements("titles");
        if (!loadedTitles.isEmpty()) {
            for (Element temp : loadedTitles) {
                if (temp instanceof Element) {
                    this.titles.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
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
        if (this.titleExists(code)) {
            return this.titles.get(code);
        }
        return null;
    }

    /**
     *
     * @param gender
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createGender(Element gender) throws SQLException, RemoteException {
        if (!this.genderExists(gender.getCode())) {
            this.createElement("genders", gender);
            this.genders.put(gender.getCode(), gender);
            this.notes.put(gender.getNote().getReference(), gender.getNote());
        }
    }

    /**
     *
     * @param genderCode
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void updateGender(String genderCode) throws SQLException, RemoteException {
        if (this.genderExists(genderCode)) {
            Element gender = this.getGender(genderCode);
            this.updateElement("genders", gender);
            this.createModifiedBy("genderModifications", gender.getLastModification(), gender.getCode());
        }
    }

    /**
     *
     * @param genderCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteGender(String genderCode) throws SQLException, RemoteException {
        if (this.genderExists(genderCode) && this.canDeleteGender(genderCode)) {
            this.deleteElement("genders", genderCode);
            this.deleteNote(this.getGender(genderCode).getNote().getReference());
            this.genders.remove(genderCode);
        }
    }

    /**
     *
     * @param genderCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteGender(String genderCode) throws RemoteException {
        if (this.genderExists(genderCode) && !this.getGender(genderCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (genderCode.equals(person.getGender().getCode())) {
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
    private void loadGenders() throws SQLException, RemoteException {
        this.genders.clear();
        List<ElementImpl> loadedGenders;
        loadedGenders = this.loadElements("genders");
        if (!loadedGenders.isEmpty()) {
            for (Element temp : loadedGenders) {
                if (temp instanceof Element) {
                    this.genders.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
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
        if (this.genderExists(code)) {
            return this.genders.get(code);
        }
        return null;
    }

    /**
     *
     * @param status
     * @throws SQLException
     * @throws java.rmi.RemoteException
     */
    public void createMaritalStatus(Element status) throws SQLException, RemoteException {
        if (!this.maritalStatusExists(status.getCode())) {
            this.createElement("maritalStatuses", status);
            this.maritalStatuses.put(status.getCode(), status);
            this.notes.put(status.getNote().getReference(), status.getNote());
        }
    }

    /**
     *
     * @param statusCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateMaritalStatus(String statusCode) throws SQLException, RemoteException {
        if (this.maritalStatusExists(statusCode)) {
            Element status = this.getMaritalStatus(statusCode);
            this.updateElement("maritalStatuses", status);
            this.createModifiedBy("maritalStatusModifications", status.getLastModification(), status.getCode());
        }
    }

    /**
     *
     * @param statusCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteMaritalStatus(String statusCode) throws SQLException, RemoteException {
        if (this.maritalStatusExists(statusCode) && this.canDeleteMaritalStatus(statusCode)) {
            this.deleteElement("maritalStatuses", statusCode);
            this.deleteNote(this.getMaritalStatus(statusCode).getNote().getReference());
            this.maritalStatuses.remove(statusCode);
        }
    }

    /**
     *
     * @param statusCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteMaritalStatus(String statusCode) throws RemoteException {
        if (this.maritalStatusExists(statusCode) && !this.getMaritalStatus(statusCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (statusCode.equals(person.getMaritalStatus().getCode())) {
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
     * @throws RemoteException
     */
    private void loadMaritalStatuses() throws SQLException, RemoteException {
        this.maritalStatuses.clear();
        List<ElementImpl> loadedStatuses;
        loadedStatuses = this.loadElements("maritalStatuses");
        if (!loadedStatuses.isEmpty()) {
            for (Element temp : loadedStatuses) {
                if (temp instanceof Element) {
                    this.maritalStatuses.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("maritalStatusModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getMaritalStatus(String code) {
        if (this.maritalStatusExists(code)) {
            return this.maritalStatuses.get(code);
        }
        return null;
    }

    /**
     *
     * @param ethnicOrigin
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEthnicOrigin(Element ethnicOrigin) throws SQLException, RemoteException {
        if (!this.ethnicOriginExists(ethnicOrigin.getCode())) {
            this.createElement("ethnicOrigins", ethnicOrigin);
            this.ethnicOrigins.put(ethnicOrigin.getCode(), ethnicOrigin);
            this.notes.put(ethnicOrigin.getNote().getReference(), ethnicOrigin.getNote());
        }
    }

    /**
     *
     * @param originCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEthnicOrigin(String originCode) throws SQLException, RemoteException {
        if (this.ethnicOriginExists(originCode)) {
            Element origin = this.getEthnicOrigin(originCode);
            this.updateElement("ethnicOrigins", origin);
            this.createModifiedBy("ethnicOriginModifications", origin.getLastModification(), origin.getCode());
        }
    }

    /**
     *
     * @param originCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEthnicOrigin(String originCode) throws SQLException, RemoteException {
        if (this.ethnicOriginExists(originCode) && this.canDeleteEthnicOrigin(originCode)) {
            this.deleteElement("ethnicOrigins", originCode);
            this.deleteNote(this.getEthnicOrigin(originCode).getNote().getReference());
            this.ethnicOrigins.remove(originCode);
        }
    }

    /**
     *
     * @param originCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteEthnicOrigin(String originCode) throws RemoteException {
        if (this.ethnicOriginExists(originCode) && !this.getEthnicOrigin(originCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (originCode.equals(person.getEthnicOrigin().getCode())) {
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
     * @throws RemoteException
     */
    private void loadEthnicOrigins() throws SQLException, RemoteException {
        this.ethnicOrigins.clear();
        List<ElementImpl> loadedEthnicOrigins;
        loadedEthnicOrigins = this.loadElements("ethnicOrigins");
        if (!loadedEthnicOrigins.isEmpty()) {
            for (Element temp : loadedEthnicOrigins) {
                if (temp instanceof Element) {
                    this.ethnicOrigins.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("ethnicOriginModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getEthnicOrigin(String code) {
        if (this.ethnicOriginExists(code)) {
            return this.ethnicOrigins.get(code);
        }
        return null;
    }

    /**
     *
     * @param language
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLanguage(Element language) throws SQLException, RemoteException {
        if (!this.languageExists(language.getCode())) {
            this.createElement("languages", language);
            this.languages.put(language.getCode(), language);
            this.notes.put(language.getNote().getReference(), language.getNote());
        }
    }

    /**
     *
     * @param languageCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLanguage(String languageCode) throws SQLException, RemoteException {
        if (this.languageExists(languageCode)) {
            Element language = this.getLanguage(languageCode);
            this.updateElement("languages", language);
            this.createModifiedBy("languageModifications", language.getLastModification(), language.getCode());
        }
    }

    /**
     *
     * @param languageCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLanguage(String languageCode) throws SQLException, RemoteException {
        if (this.languageExists(languageCode) && this.canDeleteLanguage(languageCode)) {
            this.deleteElement("languages", languageCode);
            this.deleteNote(this.getLanguage(languageCode).getNote().getReference());
            this.languages.remove(languageCode);
        }
    }

    /**
     *
     * @param languageCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteLanguage(String languageCode) throws RemoteException {
        if (this.languageExists(languageCode) && !this.getLanguage(languageCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (languageCode.equals(person.getLanguage().getCode())) {
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
     * @throws RemoteException
     */
    private void loadLanguages() throws SQLException, RemoteException {
        this.languages.clear();
        List<ElementImpl> loadedLanguages;
        loadedLanguages = this.loadElements("languages");
        if (!loadedLanguages.isEmpty()) {
            for (Element temp : loadedLanguages) {
                if (temp instanceof Element) {
                    this.languages.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("languageModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getLanguage(String code) {
        if (this.languageExists(code)) {
            return this.languages.get(code);
        }
        return null;
    }

    /**
     *
     * @param nationality
     * @throws SQLException
     * @throws RemoteException
     */
    public void createNationality(Element nationality) throws SQLException, RemoteException {
        if (!this.nationalityExists(nationality.getCode())) {
            this.createElement("nationalities", nationality);
            this.nationalities.put(nationality.getCode(), nationality);
            this.notes.put(nationality.getNote().getReference(), nationality.getNote());
        }
    }

    /**
     *
     * @param nationalityCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateNationality(String nationalityCode) throws SQLException, RemoteException {
        if (this.nationalityExists(nationalityCode)) {
            Element nationality = this.getNationality(nationalityCode);
            this.updateElement("nationalities", nationality);
            this.createModifiedBy("nationalityModifications", nationality.getLastModification(), nationality.getCode());
        }
    }

    /**
     *
     * @param nationalityCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteNationality(String nationalityCode) throws SQLException, RemoteException {
        if (this.nationalityExists(nationalityCode) && this.canDeleteNationality(nationalityCode)) {
            this.deleteElement("nationalities", nationalityCode);
            this.deleteNote(this.getNationality(nationalityCode).getNote().getReference());
            this.nationalities.remove(nationalityCode);
        }
    }

    /**
     *
     * @param nationalityCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteNationality(String nationalityCode) throws RemoteException {
        if (this.nationalityExists(nationalityCode) && !this.getNationality(nationalityCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (nationalityCode.equals(person.getNationality().getCode())) {
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
     * @throws RemoteException
     */
    private void loadNationalties() throws SQLException, RemoteException {
        this.nationalities.clear();
        List<ElementImpl> loadedNationalities;
        loadedNationalities = this.loadElements("nationalities");
        if (!loadedNationalities.isEmpty()) {
            for (Element temp : loadedNationalities) {
                if (temp instanceof Element) {
                    this.nationalities.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("nationalityModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getNationality(String code) {
        if (this.nationalityExists(code)) {
            return this.nationalities.get(code);
        }
        return null;
    }

    /**
     *
     * @param sex
     * @throws SQLException
     * @throws RemoteException
     */
    public void createSexuality(Element sex) throws SQLException, RemoteException {
        if (!this.sexualityExists(sex.getCode())) {
            this.createElement("sexualities", sex);
            this.sexualities.put(sex.getCode(), sex);
            this.notes.put(sex.getNote().getReference(), sex.getNote());
        }
    }

    /**
     *
     * @param sexCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateSexuality(String sexCode) throws SQLException, RemoteException {
        if (this.sexualityExists(sexCode)) {
            Element sex = this.getSexuality(sexCode);
            this.updateElement("sexualities", sex);
            this.createModifiedBy("sexualityModifications", sex.getLastModification(), sex.getCode());
        }
    }

    /**
     *
     * @param sexualityCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteSexuality(String sexualityCode) throws SQLException, RemoteException {
        if (this.sexualityExists(sexualityCode) && this.canDeleteSexuality(sexualityCode)) {
            this.deleteElement("sexualities", sexualityCode);
            this.deleteNote(this.getSexuality(sexualityCode).getNote().getReference());
            this.sexualities.remove(sexualityCode);
        }
    }

    /**
     *
     * @param sexualityCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteSexuality(String sexualityCode) throws RemoteException {
        if (this.sexualityExists(sexualityCode) && !this.getSexuality(sexualityCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (sexualityCode.equals(person.getSexuality().getCode())) {
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
     * @throws RemoteException
     */
    private void loadSexualities() throws SQLException, RemoteException {
        this.sexualities.clear();
        List<ElementImpl> loadedSexualities;
        loadedSexualities = this.loadElements("sexualities");
        if (!loadedSexualities.isEmpty()) {
            for (Element temp : loadedSexualities) {
                if (temp instanceof Element) {
                    this.sexualities.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("sexualityModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getSexuality(String code) {
        if (this.sexualityExists(code)) {
            return this.sexualities.get(code);
        }
        return null;
    }

    /**
     *
     * @param religion
     * @throws SQLException
     * @throws RemoteException
     */
    public void createReligion(Element religion) throws SQLException, RemoteException {
        if (!this.religionExists(religion.getCode())) {
            this.createElement("religions", religion);
            this.religions.put(religion.getCode(), religion);
            this.notes.put(religion.getNote().getReference(), religion.getNote());
        }
    }

    /**
     *
     * @param religionCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateReligion(String religionCode) throws SQLException, RemoteException {
        if (this.religionExists(religionCode)) {
            Element religion = this.getReligion(religionCode);
            this.updateElement("religions", religion);
            this.createModifiedBy("religionModifications", religion.getLastModification(), religion.getCode());
        }
    }

    /**
     *
     * @param religionCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteReligion(String religionCode) throws SQLException, RemoteException {
        if (this.religionExists(religionCode) && this.canDeleteReligion(religionCode)) {
            this.deleteElement("religions", religionCode);
            this.deleteNote(this.getReligion(religionCode).getNote().getReference());
            this.religions.remove(religionCode);
        }
    }

    /**
     *
     * @param religionCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteReligion(String religionCode) throws RemoteException {
        if (this.religionExists(religionCode) && !this.getReligion(religionCode).hasBeenModified()) {
            for (PersonInterface person : this.getPeople()) {
                if (religionCode.equals(person.getReligion().getCode())) {
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
     * @throws RemoteException
     */
    private void loadReligions() throws SQLException, RemoteException {
        this.religions.clear();
        List<ElementImpl> loadedReligions;
        loadedReligions = this.loadElements("religions");
        if (!loadedReligions.isEmpty()) {
            for (Element temp : loadedReligions) {
                if (temp instanceof Element) {
                    this.religions.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("religionModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getReligion(String code) {
        if (this.religionExists(code)) {
            return this.religions.get(code);
        }
        return null;
    }

    /**
     *
     * @param address
     * @throws SQLException
     * @throws RemoteException
     */
    public void createAddress(Address address) throws SQLException, RemoteException {
        if (!this.addressExists(address.getAddressRef())) {
            String insertSql = "insert into addresses (addressRef, buildingNumber, buildingName, subStreetNumber, subStreet, "
                    + "streetNumber, street, area, town, country, postcode, noteRef, comment, createdBy, createdDate) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                insertStat.setInt(col++, address.getNote().getReference());
                insertStat.setString(col++, address.getComment());
                insertStat.setString(col++, address.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(address.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
                this.addresses.put(address.getAddressRef(), address);
                this.notes.put(address.getNote().getReference(), address.getNote());
            }
        }
    }

    /**
     *
     * @param addressRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateAddress(int addressRef) throws SQLException, RemoteException {
        if (this.addressExists(addressRef)) {
            AddressInterface address = this.getAddress(addressRef);
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

    /**
     *
     * @param addrRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteAddress(int addrRef) throws SQLException, RemoteException {
        if (this.addressExists(addrRef) && this.canDeleteAddress(addrRef)) {
            String deleteSql = "delete from Addresses where addressRef=" + addrRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteNote(this.getAddress(addrRef).getNote().getReference());
                    this.addresses.remove(addrRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param addressRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteAddress(int addressRef) throws RemoteException {
        if (this.addressExists(addressRef) && !this.getAddress(addressRef).hasBeenModified()) {
            for (AddressUsageInterface address : this.getAddressUsages()) {
                if (address.getAddress().getAddressRef() == addressRef) {
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
     * @throws RemoteException
     */
    private void loadAddresses() throws SQLException, RemoteException {
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
                Address temp = new Address(addressRef, buildingNumber, buildingName, subStreetNumber,
                        subStreet, streetNumber, street, area, town, country, postcode, note, createdBy, createdDate);
                this.addresses.put(temp.getAddressRef(), temp);
                this.notes.put(note.getReference(), note);
                this.loadAddressMods(temp.getAddressRef(), this.loadModMap("addressModifications", temp.getAddressRef()));
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param addressRef
     * @param loadedMods
     */
    private void loadAddressMods(int addressRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.addressExists(addressRef) && !loadedMods.isEmpty()) {
            Address address = (Address) this.getAddress(addressRef);
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

    /**
     *
     * @param addressRef
     * @return
     */
    public AddressInterface getAddress(int addressRef) {
        if (this.addressExists(addressRef)) {
            return this.addresses.get(addressRef);
        }
        return null;
    }

    /**
     *
     * @param property
     * @throws SQLException
     * @throws RemoteException
     */
    public void createProperty(Property property) throws SQLException, RemoteException {
        if (!this.propertyExists(property.getPropRef())) {
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

    /**
     *
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateProperty(int propRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef)) {
            PropertyInterface property = this.getProperty(propRef);
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

    /**
     *
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteProperty(int propRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef) && this.canDeleteProperty(propRef)) {
            String deleteSql = "delete from Properties where propRef=" + propRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.properties.remove(propRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param propRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteProperty(int propRef) throws RemoteException {
        if (this.propertyExists(propRef) && !this.getProperty(propRef).hasBeenModified()) {
            for (LeaseInterface lease : this.getLeases()) {
                if (lease.getPropertyRef() == propRef) {
                    return false;
                }
            }
            for (TenancyInterface tenancy : this.getTenancies()) {
                if (tenancy.getPropertyRef() == propRef) {
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
     * @throws RemoteException
     */
    private void loadProperties() throws SQLException, RemoteException {
        String sql = "select propertyRef, addressRef, acquiredDate, leaseEndDate, propTypeCode, "
                + "propSubTypeCode, propStatus, createdBy, createdDate from properties order by propertyRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int propertyRef = results.getInt("propertyRef");
                int addressRef = results.getInt("addressRef");
                AddressInterface address;
                if (this.addressExists(addressRef)) {
                    address = this.getAddress(addressRef);
                } else {
                    address = this.getAddress(addressRef); // CREATE ERROR ADDRESS
                }
                Date acquiredDate = results.getDate("acquiredDate");
                Date leaseEndDate = results.getDate("leaseEndDate");
                String propTypeCode = results.getString("propTypeCode");
                Element propType;
                if (this.propTypeExists(propTypeCode)) {
                    propType = this.getPropertyType(propTypeCode);
                } else {
                    propType = this.getPropertyType(propTypeCode); // CREATE ERROR PROP TYPE
                }
                String propSubTypeCode = results.getString("propSubTypeCode");
                Element propSubType;
                if (this.propSubTypeExists(propSubTypeCode)) {
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
            selectStat.close();
        }
    }

    /**
     *
     * @param propRef
     * @param loadadMods
     */
    private void loadPropertyMods(int propRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.propertyExists(propRef) && !loadadMods.isEmpty()) {
            Property property = (Property) this.getProperty(propRef);
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

    /**
     *
     * @param propRef
     * @return
     */
    public PropertyInterface getProperty(int propRef) {
        if (properties.containsKey(propRef)) {
            return properties.get(propRef);
        }
        return null;
    }

    /**
     *
     * @param propertyRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadPropertyDocs(int propertyRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.propertyExists(propertyRef) && !loadedDocs.isEmpty()) {
            Property property = (Property) this.getProperty(propertyRef);
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

    /**
     *
     * @param propertyRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertyDoc(int propertyRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.propertyExists(propertyRef) && !this.documentExists(document.getDocumentRef()) && this.getProperty(propertyRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("propertyDocuments", propertyRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param propertyRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertyDoc(int propertyRef, int dRef) throws SQLException, RemoteException {
        if (this.propertyExists(propertyRef) && this.documentExists(dRef) && this.getProperty(propertyRef).hasDocument(dRef)) {
            this.updateDocument("propertyDocuments", propertyRef, dRef);
        }
    }

    /**
     *
     * @param propertyRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertyDoc(int propertyRef, int documentRef) throws SQLException, RemoteException {
        if (this.propertyExists(propertyRef) && this.documentExists(documentRef) && this.getProperty(propertyRef).hasDocument(documentRef)) {
            this.deleteDocument("propertyDocuments", propertyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param propRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadPropertyNotes(int propRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.propertyExists(propRef) && !loadadNotes.isEmpty()) {
            Property property = (Property) this.getProperty(propRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                property.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param propRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertyNote(int propRef, Note note) throws SQLException, RemoteException {
        if (this.propertyExists(propRef) && !this.noteExists(note.getReference()) && this.getProperty(propRef).hasNote(note.getReference())) {
            this.createNote("propertyNotes", propRef, note);
        }
    }

    /**
     *
     * @param propRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertyNote(int propRef, int noteRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef) && this.noteExists(noteRef)) {
            this.updateNote("propertyNotes", propRef, noteRef);
        }
    }

    /**
     *
     * @param propRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertyNote(int propRef, int noteRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("propertyNotes", propRef, noteRef);
        }
    }

    /**
     *
     * @param type
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertyType(Element type) throws SQLException, RemoteException {
        if (!this.propTypeExists(type.getCode())) {
            this.createElement("propertyTypes", type);
            this.propertyTypes.put(type.getCode(), type);
            this.notes.put(type.getNote().getReference(), type.getNote());
        }
    }

    /**
     *
     * @param typeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertyType(String typeCode) throws SQLException, RemoteException {
        if (this.propTypeExists(typeCode)) {
            Element type = this.getPropertyType(typeCode);
            this.updateElement("propertyTypes", type);
            this.createModifiedBy("propertyTypeModifications", type.getLastModification(), type.getCode());
        }
    }

    /**
     *
     * @param typeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertyType(String typeCode) throws SQLException, RemoteException {
        if (this.propTypeExists(typeCode) && this.canDeletePropertyType(typeCode)) {
            this.deleteElement("propertyTypes", typeCode);
            this.deleteNote(this.getPropertyType(typeCode).getNote().getReference());
            this.propertyTypes.remove(typeCode);
        }
    }

    /**
     *
     * @param typeCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeletePropertyType(String typeCode) throws RemoteException {
        if (this.propTypeExists(typeCode) && !this.getPropertyType(typeCode).hasBeenModified()) {
            for (PropertyInterface property : this.getProperties()) {
                if (typeCode.equals(property.getPropType().getCode())) {
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
     * @throws RemoteException
     */
    private void loadPropertyTypes() throws SQLException, RemoteException {
        this.propertyTypes.clear();
        List<ElementImpl> loadedPropTypes;
        loadedPropTypes = this.loadElements("propertyTypes");
        if (!loadedPropTypes.isEmpty()) {
            for (Element temp : loadedPropTypes) {
                if (temp instanceof Element) {
                    this.propertyTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertyTypeModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getPropertyType(String code) {
        if (this.propTypeExists(code)) {
            return this.propertyTypes.get(code);
        }
        return null;
    }

    /**
     *
     * @param type
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertySubType(Element type) throws SQLException, RemoteException {
        if (!this.propSubTypeExists(type.getCode())) {
            this.createElement("propertySubTypes", type);
            this.propertySubTypes.put(type.getCode(), type);
            this.notes.put(type.getNote().getReference(), type.getNote());
        }
    }

    /**
     *
     * @param typeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertySubType(String typeCode) throws SQLException, RemoteException {
        Element type = this.getPropertySubType(typeCode);
        if (this.propSubTypeExists(type.getCode())) {
            this.updateElement("propertySubTypes", type);
            this.createModifiedBy("propertySubTypeModifications", type.getLastModification(), type.getCode());
        }
    }

    /**
     *
     * @param typeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertySubType(String typeCode) throws SQLException, RemoteException {
        if (this.propSubTypeExists(typeCode) && this.canDeletePropertySubType(typeCode)) {
            this.deleteElement("propertySubTypes", typeCode);
            this.deleteNote(this.getPropertySubType(typeCode).getNote().getReference());
            this.propertySubTypes.remove(typeCode);
        }
    }

    /**
     *
     * @param typeCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeletePropertySubType(String typeCode) throws RemoteException {
        if (this.propSubTypeExists(typeCode) && !this.getPropertySubType(typeCode).hasBeenModified()) {
            for (PropertyInterface property : this.getProperties()) {
                if (typeCode.equals(property.getPropSubType().getCode())) {
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
     * @throws RemoteException
     */
    private void loadPropertySubTypes() throws SQLException, RemoteException {
        this.propertySubTypes.clear();
        List<ElementImpl> loadedPropSubTypes;
        loadedPropSubTypes = this.loadElements("propertySubTypes");
        if (!loadedPropSubTypes.isEmpty()) {
            for (Element temp : loadedPropSubTypes) {
                if (temp instanceof Element) {
                    this.propertySubTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertySubTypeModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getPropertySubType(String code) {
        if (this.propSubTypeExists(code)) {
            return this.propertySubTypes.get(code);
        }
        return null;
    }

    /**
     *
     * @param propertyRef
     * @param propertyElements
     * @throws SQLException
     * @throws RemoteException
     */
    private void createPropertyElementValues(int propertyRef, List<PropertyElementInterface> propertyElements) throws SQLException, RemoteException {
        if (!propertyElements.isEmpty() && this.propertyExists(propertyRef)) {
            for (PropertyElementInterface temp : propertyElements) {
                this.createPropertyElementValue(propertyRef, (PropertyElement) temp);
            }
        }
    }

    /**
     *
     * @param propertyRef
     * @param propertyElement
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertyElementValue(int propertyRef, PropertyElement propertyElement) throws SQLException, RemoteException {
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
                insertStat.setInt(col++, propertyElement.getNote().getReference());
                insertStat.setString(col++, propertyElement.getComment());
                insertStat.setString(col++, propertyElement.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(propertyElement.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.propertyElementValues.put(propertyElement.getPropertyElementRef(), propertyElement);
            this.notes.put(propertyElement.getNote().getReference(), propertyElement.getNote());
        }
    }

    /**
     *
     * @param propertyRef
     * @param propElementRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertyElementValue(int propertyRef, int propElementRef) throws SQLException, RemoteException {
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

    /**
     *
     * @param propElementRef
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertyElementValue(int propElementRef, int propRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef) && this.propertyElementValueExists(propElementRef) && this.getProperty(propRef).hasPropElement(propElementRef) && this.canDeletePropertyElementValue(propElementRef, propRef)) {
            String deleteSql = "delete from PropertyElementValues where propertyElementRef=" + propElementRef + " and propRef=" + propRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    propertyElementValues.remove(propElementRef);
                    this.deleteNote(this.getPropertyElementValue(propElementRef).getNote().getReference());
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param propElementRef
     * @param propRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeletePropertyElementValue(int propElementRef, int propRef) throws RemoteException {
        return this.propertyExists(propRef) && !this.getProperty(propRef).getPropElement(propElementRef).hasBeenModified();
    }

    /**
     *
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadPropertyElementValues(int propRef) throws SQLException, RemoteException {
        if (this.propertyExists(propRef)) {
            Property property = (Property) this.getProperty(propRef);
            String sql = "select propertyElementRef, propRef, elementCode, stringValue, doubleValue, startDate, endDate, noteRef, comment, createdBy, createdDate from propertyElementValues order by propertyElementRef";
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
                            this.notes.put(note.getReference(), note);
                            this.loadPropElementMods(temp, this.loadModMap("PropertyElementValueModifications", temp.getPropertyElementRef()));
                            property.createPropertyElement(temp, null);
                        }
                    }
                }
                selectStat.close();
            }
        }
    }

    /**
     *
     * @param element
     * @param loadadMods
     * @throws RemoteException
     */
    private void loadPropElementMods(PropertyElement element, Map<Integer, ModifiedByInterface> loadadMods) throws RemoteException {
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

    /**
     *
     * @param propElementRef
     * @return
     */
    public PropertyElementInterface getPropertyElementValue(int propElementRef) {
        if (this.propertyElementValueExists(propElementRef)) {
            return this.propertyElementValues.get(propElementRef);
        }
        return null;
    }

    public void createPropElement(Element element) throws SQLException, RemoteException {
        if (!this.propElementExists(element.getCode())) {
            this.createElement("propertyElements", element);
            this.propertyElements.put(element.getCode(), element);
            this.notes.put(element.getNote().getReference(), element.getNote());
        }
    }

    /**
     *
     * @param elementCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePropertyElement(String elementCode) throws SQLException, RemoteException {
        Element element = this.getPropElement(elementCode);
        if (this.propElementExists(element.getCode())) {
            this.updateElement("propertyElements", element);
            this.createModifiedBy("propertyElementModifications", element.getLastModification(), element.getCode());
        }
    }

    /**
     *
     * @param elementCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePropertyElement(String elementCode) throws SQLException, RemoteException {
        if (this.propElementExists(elementCode) && this.canDeletePropertyElement(elementCode)) {
            this.deleteElement("propertyElements", elementCode);
            this.deleteNote(this.getPropElement(elementCode).getNote().getReference());
            this.propertyElements.remove(elementCode);
        }
    }

    /**
     *
     * @param elementCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeletePropertyElement(String elementCode) throws RemoteException {
        if (this.propElementExists(elementCode) && !this.getPropElement(elementCode).hasBeenModified()) {
            for (PropertyInterface property : this.getProperties()) {
                if (property.hasPropElement(elementCode)) {
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
     * @throws RemoteException
     */
    private void loadPropertyElements() throws SQLException, RemoteException {
        this.propertyElements.clear();
        List<ElementImpl> loadedPropertyElements;
        loadedPropertyElements = this.loadElements("propertyElements");
        if (!loadedPropertyElements.isEmpty()) {
            for (Element temp : loadedPropertyElements) {
                if (temp instanceof Element) {
                    this.propertyElements.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("propertyElementModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getPropElement(String code) {
        if (this.propElementExists(code)) {
            return this.propertyElements.get(code);
        }
        return null;
    }

    /**
     *
     * @param person
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPerson(Person person) throws SQLException, RemoteException {
        if (!this.personExists(person.getPersonRef())) {
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

    /**
     *
     * @param personRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePerson(int personRef) throws SQLException, RemoteException {
        if (this.personExists(personRef)) {
            PersonInterface person = this.getPerson(personRef);
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

    /**
     *
     * @param personRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePerson(int personRef) throws SQLException, RemoteException {
        if (this.personExists(personRef) && this.canDeletePerson(personRef)) {
            String deleteSql = "delete from people where personRef=" + personRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.people.remove(personRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param personRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeletePerson(int personRef) throws RemoteException {
        return this.propertyExists(personRef) && !this.getPerson(personRef).hasBeenModified() && !this.personInvPartyExists(personRef) && !this.personEmployeeExists(personRef) && !this.personLandlordExists(personRef);
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadPeople() throws SQLException, RemoteException {
        this.people.clear();
        String sql = "select personRef, titleCode, forename, middleNames, surname, dateOfBirth, "
                + "nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, "
                + "sexualityCode, religionCode, createdBy, createdDate from people order by personRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int personRef = results.getInt("personRef");

                Element title;
                if (this.titleExists(results.getString("titleCode"))) {
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
                if (this.genderExists(results.getString("genderCode"))) {
                    gender = this.getGender(results.getString("genderCode"));
                } else {
                    gender = this.getGender(results.getString("genderCode")); // Create ERROR Code
                }

                Element maritalStatus;
                if (this.maritalStatusExists(results.getString("maritalStatusCode"))) {
                    maritalStatus = this.getTitle(results.getString("maritalStatusCode"));
                } else {
                    maritalStatus = this.getTitle(results.getString("maritalStatusCode")); // Create ERROR Code
                }

                Element ethnicOrigin;
                if (this.ethnicOriginExists(results.getString("ethnicOriginCode"))) {
                    ethnicOrigin = this.getEthnicOrigin(results.getString("ethnicOriginCode"));
                } else {
                    ethnicOrigin = this.getEthnicOrigin(results.getString("ethnicOriginCode")); // Create ERROR Code
                }

                Element language;
                if (this.languageExists(results.getString("languageCode"))) {
                    language = this.getLanguage(results.getString("languageCode"));
                } else {
                    language = this.getLanguage(results.getString("languageCode")); // Create ERROR Code
                }

                Element nationality;
                if (this.nationalityExists(results.getString("nationalityCode"))) {
                    nationality = this.getNationality(results.getString("nationalityCode"));
                } else {
                    nationality = this.getNationality(results.getString("nationalityCode")); // Create ERROR Code
                }

                Element sexuality;
                if (this.sexualityExists(results.getString("sexualityCode"))) {
                    sexuality = this.getSexuality(results.getString("sexualityCode"));
                } else {
                    sexuality = this.getSexuality(results.getString("sexualityCode")); // Create ERROR Code
                }

                Element religion;
                if (this.religionExists(results.getString("religionCode"))) {
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
            selectStat.close();
        }
    }

    /**
     *
     * @param personRef
     * @param loadedMods
     */
    private void loadPersonMods(int personRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.personExists(personRef) && !loadedMods.isEmpty()) {
            Person person = (Person) this.getPerson(personRef);
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

    /**
     *
     * @param personRef
     * @return
     */
    public PersonInterface getPerson(int personRef) {
        if (this.personExists(personRef)) {
            return this.people.get(personRef);
        }
        return null;
    }

    /**
     *
     * @param personRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadPersonDocs(int personRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.personExists(personRef) && !loadedDocs.isEmpty()) {
            Person person = (Person) this.getPerson(personRef);
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

    /**
     *
     * @param personRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPersonDoc(int personRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.personExists(personRef) && !this.documentExists(document.getDocumentRef()) && this.getPerson(personRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("personDocuments", personRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param personRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePersonDoc(int personRef, int dRef) throws SQLException, RemoteException {
        if (this.personExists(personRef) && this.documentExists(dRef) && this.getPerson(personRef).hasDocument(dRef)) {
            this.updateDocument("personDocuments", personRef, dRef);
        }
    }

    /**
     *
     * @param personRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePersonDoc(int personRef, int documentRef) throws SQLException, RemoteException {
        if (this.personExists(personRef) && this.documentExists(documentRef) && this.getPerson(personRef).hasDocument(documentRef)) {
            this.deleteDocument("personDocuments", personRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param personRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadPersonNotes(int personRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.personExists(personRef) && !loadadNotes.isEmpty()) {
            Person person = (Person) this.getPerson(personRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                person.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param personRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPersonNote(int personRef, Note note) throws SQLException, RemoteException {
        if (this.personExists(personRef) && !this.noteExists(note.getReference()) && this.getPerson(personRef).hasNote(note.getReference())) {
            this.createNote("personNotes", personRef, note);
        }
    }

    /**
     *
     * @param personRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updatePersonNote(int personRef, int noteRef) throws SQLException, RemoteException {
        if (this.personExists(personRef) && this.noteExists(noteRef)) {
            this.updateNote("personNotes", personRef, noteRef);
        }
    }

    /**
     *
     * @param personRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deletePersonNote(int personRef, int noteRef) throws SQLException, RemoteException {
        if (this.personExists(personRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("personNotes", personRef, noteRef);
        }
    }

    /**
     *
     * @param invParty
     * @throws SQLException
     * @throws RemoteException
     */
    public void createInvolvedParty(InvolvedParty invParty) throws SQLException, RemoteException {
        if (!this.invPartyExists(invParty.getInvolvedPartyRef())) {
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

    /**
     *
     * @param invPartyRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateInvolvedParty(int invPartyRef) throws SQLException, RemoteException {
        if (this.invPartyExists(invPartyRef)) {
            InvolvedPartyInterface invParty = this.getInvolvedParty(invPartyRef);
            String updateSql = "update involvedParties set jointApplicantInd=?, mainApplicantInd=?, startDate=?, "
                    + "endDate=?, endReasonCode=?, relationshipCode=? where invPartyRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
                int col = 1;
                updateStat.setBoolean(col++, invParty.isJointInd());
                updateStat.setBoolean(col++, invParty.isMainInd());
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getStartDate()));
                updateStat.setDate(col++, DateConversion.utilDateToSQLDate(invParty.getEndDate()));
                if (invParty.getEndReason() != null) {
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

    /**
     *
     * @param invPartyRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteInvolvedParty(int invPartyRef) throws SQLException, RemoteException {
        if (this.personExists(invPartyRef) && this.canDeleteInvolvedParty(invPartyRef)) {
            String deleteSql = "delete from people where personRef=" + invPartyRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.people.remove(invPartyRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param invPartyRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteInvolvedParty(int invPartyRef) throws RemoteException {
        return (this.invPartyExists(invPartyRef) && !this.getInvolvedParty(invPartyRef).isMainInd() && !this.getInvolvedParty(invPartyRef).hasBeenModified());
    }

    /**
     *
     * @param reference
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadInvolvedParties(int reference) throws SQLException, RemoteException {
        this.involvedParties.clear();
        String sql = "select invPartyRef, appRef, personRef, jointApplicantInd, mainApplicantInd, startDate, endDate, endReasonCode, "
                + "relationshipCode, createdBy, createdDate from involvedParties order by invPartyRef";

        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);
            while (results.next()) {
                int appRef = results.getInt("appRef");
                if (this.applicationExists(reference) && appRef == reference) {
                    Application application = (Application) this.getApplication(reference);
                    int invPartyRef = results.getInt("invPartyRef");

                    if (this.personExists(results.getInt("personRef")));
                    {
                        PersonInterface person = this.getPerson(results.getInt("personRef"));
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
            selectStat.close();
        }
    }

    /**
     *
     * @param invPartyRef
     * @param loadedMods
     */
    private void loadInvolvedPartyMods(int invPartyRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.invPartyExists(invPartyRef) && !loadedMods.isEmpty()) {
            InvolvedParty invParty = (InvolvedParty) this.getInvolvedParty(invPartyRef);
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

    /**
     *
     * @param invPartyRef
     * @return
     */
    public InvolvedPartyInterface getInvolvedParty(int invPartyRef) {
        if (this.invPartyExists(invPartyRef)) {
            return this.involvedParties.get(invPartyRef);
        }
        return null;
    }

    /**
     *
     * @param invPartyRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadInvolvedPartyNotes(int invPartyRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.invPartyExists(invPartyRef) && !loadadNotes.isEmpty()) {
            InvolvedParty invParty = (InvolvedParty) this.getInvolvedParty(invPartyRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                invParty.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param invPartyRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createInvolvedPartyNote(int invPartyRef, Note note) throws SQLException, RemoteException {
        if (this.invPartyExists(invPartyRef) && !this.noteExists(note.getReference()) && this.getInvolvedParty(invPartyRef).hasNote(note.getReference())) {
            this.createNote("involvedPartyNotes", invPartyRef, note);
        }
    }

    /**
     *
     * @param invPartyRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateInvolvedPartyNote(int invPartyRef, int noteRef) throws SQLException, RemoteException {
        if (this.invPartyExists(invPartyRef) && this.noteExists(noteRef)) {
            this.updateNote("involvedPartyNotes", invPartyRef, noteRef);
        }
    }

    /**
     *
     * @param invPartyRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteInvolvedPartyNote(int invPartyRef, int noteRef) throws SQLException, RemoteException {
        if (this.invPartyExists(invPartyRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("involvedPartyNotes", invPartyRef, noteRef);
        }
    }

    /**
     *
     * @param endReason
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEndReason(Element endReason) throws SQLException, RemoteException {
        if (!this.endReasonExists(endReason.getCode())) {
            this.createElement("endReasons", endReason);
            this.endReasons.put(endReason.getCode(), endReason);
            this.notes.put(endReason.getNote().getReference(), endReason.getNote());
        }
    }

    /**
     *
     * @param endReasonCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEndReason(String endReasonCode) throws SQLException, RemoteException {
        if (this.endReasonExists(endReasonCode)) {
            Element endReason = this.getEndReason(endReasonCode);
            this.updateElement("endReasons", endReason);
            this.createModifiedBy("endReasonModifications", endReason.getLastModification(), endReason.getCode());
        }
    }

    /**
     *
     * @param endReason
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEndReason(String endReason) throws SQLException, RemoteException {
        if (this.endReasonExists(endReason) && this.canDeleteEndReason(endReason)) {
            this.deleteElement("endReasons", endReason);
            this.deleteNote(this.getEndReason(endReason).getNote().getReference());
            endReasons.remove(endReason);
        }
    }

    /**
     *
     * @param endReason
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteEndReason(String endReason) throws RemoteException {
        if (this.endReasonExists(endReason) && !this.getEndReason(endReason).hasBeenModified()) {
            for (InvolvedPartyInterface invParty : this.getInvolvedParties()) {
                if (invParty.getEndReason() != null && endReason.equals(invParty.getEndReason().getCode())) {
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
     * @throws RemoteException
     */
    private void loadEndReasons() throws SQLException, RemoteException {
        this.endReasons.clear();
        List<ElementImpl> loadedEndReasons;
        loadedEndReasons = this.loadElements("endReasons");
        if (!loadedEndReasons.isEmpty()) {
            for (Element temp : loadedEndReasons) {
                if (temp instanceof Element) {
                    this.endReasons.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("endReasonModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getEndReason(String code) {
        if (this.endReasonExists(code)) {
            return this.endReasons.get(code);
        }
        return null;
    }

    /**
     *
     * @param relationship
     * @throws SQLException
     * @throws RemoteException
     */
    public void createRelationship(Element relationship) throws SQLException, RemoteException {
        if (!this.relationshipExists(relationship.getCode())) {
            this.createElement("relationships", relationship);
            this.relationships.put(relationship.getCode(), relationship);
            this.notes.put(relationship.getNote().getReference(), relationship.getNote());
        }
    }

    /**
     *
     * @param relationshipCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateRelationship(String relationshipCode) throws SQLException, RemoteException {
        if (this.relationshipExists(relationshipCode)) {
            Element relationship = this.getRelationship(relationshipCode);
            this.updateElement("relationships", relationship);
            this.createModifiedBy("relationshipModifications", relationship.getLastModification(), relationship.getCode());
        }
    }

    /**
     *
     * @param relationship
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteRelationship(String relationship) throws SQLException, RemoteException {
        if (this.relationshipExists(relationship) && this.canDeleteRelationship(relationship)) {
            this.deleteElement("relationships", relationship);
            this.deleteNote(this.getRelationship(relationship).getNote().getReference());
            relationships.remove(relationship);
        }
    }

    /**
     *
     * @param relationship
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteRelationship(String relationship) throws RemoteException {
        if (this.relationshipExists(relationship) && !this.getRelationship(relationship).hasBeenModified()) {
            for (InvolvedPartyInterface invParty : this.getInvolvedParties()) {
                if (relationship.equals(invParty.getRelationship().getCode())) {
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
     * @throws RemoteException
     */
    private void loadRelationships() throws SQLException, RemoteException {
        this.relationships.clear();
        List<ElementImpl> loadedRelationships;
        loadedRelationships = this.loadElements("relationships");
        if (!loadedRelationships.isEmpty()) {
            for (Element temp : loadedRelationships) {
                if (temp instanceof Element) {
                    this.relationships.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("relationshipModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getRelationship(String code) {
        if (this.relationshipExists(code)) {
            return this.relationships.get(code);
        }
        return null;
    }

    /**
     *
     * @param application
     * @throws SQLException
     * @throws RemoteException
     */
    public void createApplication(Application application) throws SQLException, RemoteException {
        if (!this.applicationExists(application.getApplicationRef())) {
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

    /**
     *
     * @param appRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateApplication(int appRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef)) {
            ApplicationInterface application = this.getApplication(appRef);
            String updateSql;
            if (application.hasTenancyRef()) {
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
                if (application.hasTenancyRef()) {
                    updateStat.setInt(col++, application.getTenancyRef());
                }
                updateStat.setInt(col++, application.getApplicationRef());
                updateStat.executeUpdate();
                updateStat.close();
            }
            this.createModifiedBy("applicationModifications", application.getLastModification(), application.getApplicationRef());
        }
    }

    /**
     *
     * @param appRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteApplication(int appRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.canDeleteApplication(appRef)) {
            String deleteSql = "delete from applications where appRef=" + appRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.applications.remove(appRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param appRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteApplication(int appRef) throws RemoteException {
        if (this.applicationExists(appRef) && this.getApplication(appRef).hasBeenModified()) {
            for (InvolvedPartyInterface invParty : this.getApplication(appRef).getHousehold()) {
                if (invParty.hasBeenModified()) {
                    return false;
                }
            }
            for (AddressUsageInterface address : this.getApplication(appRef).getApplicationAddressess()) {
                if (address.hasBeenModified()) {
                    return false;
                }
            }
            for (TenancyInterface tenancy : this.getTenancies()) {
                if (tenancy.getApplicationRef() == appRef) {
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
     * @throws RemoteException
     */
    private void loadApplications() throws SQLException, RemoteException {
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

                if (appEndDate != null) {
                    temp.setEndDate(appEndDate, null);
                }

                if (tenancyRef >= 1) {
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
            selectStat.close();
        }
    }

    /**
     *
     * @param appRef
     * @param loadedMods
     */
    private void loadApplicationMods(int appRef, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.applicationExists(appRef) && !loadedMods.isEmpty()) {
            Application application = (Application) this.getApplication(appRef);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                application.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param appRef
     * @return
     */
    public ApplicationInterface getApplication(int appRef) {
        if (this.applicationExists(appRef)) {
            return this.applications.get(appRef);
        }
        return null;
    }

    /**
     *
     * @param appRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadApplicationDocs(int appRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.applicationExists(appRef) && !loadedDocs.isEmpty()) {
            Application application = (Application) this.getApplication(appRef);
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

    /**
     *
     * @param appRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createApplicationDoc(int appRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && !this.documentExists(document.getDocumentRef()) && this.getApplication(appRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("applicationDocuments", appRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param appRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateApplicationDoc(int appRef, int dRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.documentExists(dRef) && this.getApplication(appRef).hasDocument(dRef)) {
            this.updateDocument("applicationDocuments", appRef, dRef);
        }
    }

    /**
     *
     * @param appRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteApplicationDoc(int appRef, int documentRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.documentExists(documentRef) && this.getApplication(appRef).hasDocument(documentRef)) {
            this.deleteDocument("applicationDocuments", appRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param appRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadApplicationNotes(int appRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.applicationExists(appRef) && !loadadNotes.isEmpty()) {
            Application application = (Application) this.getApplication(appRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                application.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param appRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createApplicationNote(int appRef, Note note) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && !this.noteExists(note.getReference()) && this.getApplication(appRef).hasNote(note.getReference())) {
            this.createNote("applicationNotes", appRef, note);
        }
    }

    /**
     *
     * @param appRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateApplicationNote(int appRef, int noteRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.noteExists(noteRef)) {
            this.updateNote("applicationNotes", appRef, noteRef);
        }
    }

    /**
     *
     * @param appRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteApplicationNote(int appRef, int noteRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("applicationNotes", appRef, noteRef);
        }
    }

    /**
     *
     * @param appRef
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void createPropertyInterest(int appRef, int propRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.propertyExists(propRef)) {

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
                    insertStat.setInt(col++, appRef);
                    insertStat.setInt(col++, propRef);
                    insertStat.setBoolean(col++, true);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update propertyInterest set cur=? where appRef=? and propRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, true);
                    updateStat.setInt(col++, appRef);
                    updateStat.setInt(col++, propRef);
                    updateStat.executeUpdate();
                    updateStat.close();
                }
                checkStat.close();
            }
        }
    }

    /**
     *
     * @param appRef
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadPropertiesInterestedIn(int appRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef)) {
            Application application = (Application) this.getApplication(appRef);
            String sql = "select propRef from propertyInterest where appRef=? and cur=?";
            try (PreparedStatement selectStat = con.prepareStatement(sql)) {
                selectStat.setInt(1, appRef);
                selectStat.setBoolean(2, true);

                ResultSet results = selectStat.executeQuery();

                while (results.next()) {
                    int propRef = results.getInt("propRef");
                    if (this.propertyExists(propRef)) {
                        PropertyInterface temp = this.getProperty(propRef);
                        application.addInterestedProperty(temp, null);
                    }
                }
                selectStat.close();
            }
        }
    }

    /**
     *
     * @param appRef
     * @param propRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void endPropertyInterest(int appRef, int propRef) throws SQLException, RemoteException {
        if (this.applicationExists(appRef) && this.propertyExists(propRef)) {

            String checkSql = "select count(appRef) as count from propertyInterest where appRef=" + appRef + " and propRef=" + propRef;
            PreparedStatement insertStat;
            PreparedStatement updateStat;
            try (Statement checkStat = con.createStatement()) {
                ResultSet checkResult = checkStat.executeQuery(checkSql);
                checkResult.next();
                int count = checkResult.getInt("count");
                int col = 1;
                if (count >= 1) {
                    String updateSql = "update propertyInterest set cur=? where appRef=" + appRef + " and propRef=" + propRef;
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, false);
                    updateStat.executeUpdate();
                    updateStat.close();
                } else if (count == 0) {
                    String insertSql = "insert into propertyInterest (appRef, propRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
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

    /**
     *
     * @param landlord
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLandlord(Landlord landlord) throws SQLException, RemoteException {
        if (!this.landlordExists(landlord.getLandlordRef())) {
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

    /**
     *
     * @param landlordRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLandlord(int landlordRef) throws SQLException, RemoteException {
        if (this.landlordExists(landlordRef)) {
            Landlord landlord = (Landlord) this.getLandlord(landlordRef);
            this.createModifiedBy("landlordModifications", landlord.getLastModification(), landlord.getLandlordRef());
        }
    }

    /**
     *
     * @param landlordRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLandlord(int landlordRef) throws SQLException, RemoteException {
        if (this.landlordExists(landlordRef) && this.canDeleteApplication(landlordRef)) {
            String deleteSql = "delete from landlords where landlordRef=" + landlordRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.landlords.remove(landlordRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param landlordRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteLandlord(int landlordRef) throws RemoteException {
        if (this.landlordExists(landlordRef) && this.getLandlord(landlordRef).hasBeenModified()) {
            for (LeaseInterface lease : this.getLeases()) {
                if (lease.isAlreadyLandlord(landlordRef)) {
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
     * @throws RemoteException
     */
    private void loadLandlords() throws SQLException, RemoteException {
        this.landlords.clear();
        String sql = "select landlordRef, personRef, createdBy, createdDate from landlords order by landlordRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int landlordRef = results.getInt("landlordRef");
                int personRef = results.getInt("personRef");
                if (this.personExists(personRef)) {
                    PersonInterface person = this.getPerson(personRef);
                    String createdBy = results.getString("createdBy");
                    Date createdDate = results.getDate("createdDate");
                    Landlord temp = new Landlord(landlordRef, person, createdBy, createdDate);
                    landlords.put(temp.getLandlordRef(), temp);
                    this.loadLandlordMods(temp.getLandlordRef(), this.loadModMap("landlordModifications", temp.getLandlordRef()));
                    this.loadLandlordNotes(temp.getLandlordRef(), this.loadNoteMap("landlordNotes", temp.getLandlordRef()));
                }
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param landlordRef
     * @param loadadMods
     */
    private void loadLandlordMods(int landlordRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.landlordExists(landlordRef) && !loadadMods.isEmpty()) {
            Landlord landlord = (Landlord) this.getLandlord(landlordRef);
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

    /**
     *
     * @param landlordRef
     * @return
     */
    public LandlordInterface getLandlord(int landlordRef) {
        if (this.landlordExists(landlordRef)) {
            return this.landlords.get(landlordRef);
        }
        return null;
    }

    public LandlordInterface getPersonLandlord(int personRef) throws RemoteException {
        if (this.personLandlordExists(personRef)) {
            for (LandlordInterface temp : this.getLandlords()) {
                if (personRef == temp.getPersonRef()) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param landlordRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadLandlordNotes(int landlordRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.landlordExists(landlordRef) && !loadadNotes.isEmpty()) {
            Landlord landlord = (Landlord) this.getLandlord(landlordRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                landlord.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param landlordRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLandlordNote(int landlordRef, Note note) throws SQLException, RemoteException {
        if (this.landlordExists(landlordRef) && !this.noteExists(note.getReference()) && this.getLandlord(landlordRef).hasNote(note.getReference())) {
            this.createNote("landlordNotes", landlordRef, note);
        }
    }

    /**
     *
     * @param landlordRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLandlordNote(int landlordRef, int noteRef) throws SQLException, RemoteException {
        if (this.landlordExists(landlordRef) && this.noteExists(noteRef)) {
            this.updateNote("landlordNotes", landlordRef, noteRef);
        }
    }

    /**
     *
     * @param landlordRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLandlordNote(int landlordRef, int noteRef) throws SQLException, RemoteException {
        if (this.landlordExists(landlordRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("landlordNotes", landlordRef, noteRef);
        }
    }

    /**
     *
     * @param office
     * @throws SQLException
     * @throws RemoteException
     */
    public void createOffice(Office office) throws SQLException, RemoteException {
        if (!this.officeExists(office.getOfficeCode())) {
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

    /**
     *
     * @param officeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateOffice(String officeCode) throws SQLException, RemoteException {
        if (this.officeExists(officeCode)) {
            OfficeInterface office = this.getOffice(officeCode);
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

    /**
     *
     * @param officeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteOffice(String officeCode) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && this.canDeleteOffice(officeCode)) {
            String deleteSql = "delete from offices where officeCode='" + officeCode + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.offices.remove(officeCode);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param officeCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteOffice(String officeCode) throws RemoteException {
        return this.officeExists(officeCode) && this.getOffice(officeCode).hasBeenModified() && !this.getOffice(officeCode).hasAgreements();
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadOffices() throws SQLException, RemoteException {
        this.offices.clear();
        String sql = "select officeCode, addressRef, startDate, endDate, createdBy, createdDate from offices order by createdDate";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                String officeCode = results.getString("officeCode");
                AddressInterface address;
                if (this.addressExists(results.getInt("addressRef"))) {
                    address = this.getAddress(results.getInt("addressRef"));
                } else {
                    address = this.getAddress(results.getInt("addressRef")); // NEED TO CREATE AN ERROR ADDRESS
                }
                Date startDate = results.getDate("startDate");
                Date endDate = results.getDate("endDate");
                String createdBy = results.getString("createdBy");
                Date createdDate = results.getDate("createdDate");

                Office temp = new Office(officeCode, address, startDate, createdBy, createdDate);
                if (endDate != null) {
                    temp.setEndDate(endDate, null);
                }

                this.offices.put(temp.getOfficeCode(), temp);
                this.loadOfficeMods(temp.getOfficeCode(), this.loadModMap("officeModifications", temp.getOfficeCode()));
                this.loadOfficeNotes(temp.getOfficeCode(), this.loadNoteMap("officeNotes", temp.getOfficeCode()));
                this.loadOfficeDocs(temp.getOfficeCode(), this.loadDocMap("officeDocuments", temp.getOfficeCode()));
                this.loadOfficeContacts(temp.getOfficeCode());
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param officeCode
     * @param loadedMods
     */
    private void loadOfficeMods(String officeCode, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.officeExists(officeCode) && !loadedMods.isEmpty()) {
            Office office = (Office) this.getOffice(officeCode);
            Iterator it = loadedMods.entrySet().iterator();
            while (it.hasNext()) {
                ModifiedByInterface tempMod;
                Map.Entry temp = (Map.Entry) it.next();
                tempMod = (ModifiedByInterface) temp.getValue();
                office.modifiedBy(tempMod);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public OfficeInterface getOffice(String code) {
        if (this.officeExists(code)) {
            return this.offices.get(code);
        }
        return null;
    }

    /**
     *
     * @param officeCode
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadOfficeDocs(String officeCode, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.officeExists(officeCode) && !loadedDocs.isEmpty()) {
            Office office = (Office) this.getOffice(officeCode);
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

    /**
     *
     * @param officeCode
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createOfficeDoc(String officeCode, DocumentImpl document) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && !this.documentExists(document.getDocumentRef()) && this.getOffice(officeCode).hasDocument(document.getDocumentRef())) {
            this.createDocument("officeDocuments", officeCode, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param officeCode
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateOfficeDoc(String officeCode, int dRef) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && this.documentExists(dRef) && this.getOffice(officeCode).hasDocument(dRef)) {
            this.updateDocument("officeDocuments", officeCode, dRef);
        }
    }

    /**
     *
     * @param officeCode
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteOfficeDoc(String officeCode, int documentRef) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && this.documentExists(documentRef) && this.getOffice(officeCode).hasDocument(documentRef)) {
            this.deleteDocument("officeDocuments", officeCode, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param officeCode
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadOfficeNotes(String officeCode, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.officeExists(officeCode) && !loadadNotes.isEmpty()) {
            Office office = (Office) this.getOffice(officeCode);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                office.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param officeCode
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createOfficeNote(String officeCode, Note note) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && !this.noteExists(note.getReference()) && this.getOffice(officeCode).hasNote(note.getReference())) {
            this.createNote("officeNotes", officeCode, note);
        }
    }

    /**
     *
     * @param officeCode
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateOfficeNote(String officeCode, int noteRef) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && this.noteExists(noteRef)) {
            this.updateNote("officeNotes", officeCode, noteRef);
        }
    }

    /**
     *
     * @param officeCode
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteOfficeNote(String officeCode, int noteRef) throws SQLException, RemoteException {
        if (this.officeExists(officeCode) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("officeNotes", officeCode, noteRef);
        }
    }

    /**
     *
     * @param jobRole
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobRole(JobRole jobRole) throws SQLException, RemoteException {
        if (!this.jobRoleExists(jobRole.getJobRoleCode())) {
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

    /**
     *
     * @param jobRoleCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateJobRole(String jobRoleCode) throws SQLException, RemoteException {
        if (this.jobRoleExists(jobRoleCode)) {
            JobRoleInterface jobRole = this.getJobRole(jobRoleCode);
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

    /**
     *
     * @param jobRoleCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteJobRole(String jobRoleCode) throws SQLException, RemoteException {
        if (this.jobRoleExists(jobRoleCode) && this.canDeleteJobRole(jobRoleCode)) {
            String deleteSql = "delete from jobRoles where jobRoleCode='" + jobRoleCode + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.jobRoles.remove(jobRoleCode);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param jobRoleCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteJobRole(String jobRoleCode) throws RemoteException {
        if (this.jobRoleExists(jobRoleCode) && this.getJobRole(jobRoleCode).hasBeenModified()) {
            for (ContractInterface contract : this.getContracts()) {
                if (jobRoleCode.equals(contract.getJobRoleCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadJobRoles() throws SQLException, RemoteException {
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
            selectStat.close();
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param loadedMods
     */
    private void loadJobRoleMods(String jobRoleCode, Map<Integer, ModifiedByInterface> loadedMods) {
        if (this.jobRoleExists(jobRoleCode) && !loadedMods.isEmpty()) {
            JobRole jobRole = (JobRole) this.getJobRole(jobRoleCode);
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

    /**
     *
     * @param jobRoleCode
     * @return
     */
    public JobRoleInterface getJobRole(String jobRoleCode) {
        if (this.jobRoleExists(jobRoleCode)) {
            return this.jobRoles.get(jobRoleCode);
        }
        return null;
    }

    /**
     *
     * @param jobRoleCode
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadJobRoleNotes(String jobRoleCode, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.jobRoleExists(jobRoleCode) && !loadadNotes.isEmpty()) {
            JobRole jobRole = (JobRole) this.getJobRole(jobRoleCode);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                jobRole.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobRoleNote(String jobRoleCode, Note note) throws SQLException, RemoteException {
        if (this.jobRoleExists(jobRoleCode) && !this.noteExists(note.getReference()) && this.getJobRole(jobRoleCode).hasNote(note.getReference())) {
            this.createNote("jobRoleNotes", jobRoleCode, note);
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateJobRoleNote(String jobRoleCode, int noteRef) throws SQLException, RemoteException {
        if (this.jobRoleExists(jobRoleCode) && this.noteExists(noteRef)) {
            this.updateNote("jobRoleNotes", jobRoleCode, noteRef);
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteJobRoleNote(String jobRoleCode, int noteRef) throws SQLException, RemoteException {
        if (this.officeExists(jobRoleCode) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("jobRoleNotes", jobRoleCode, noteRef);
        }
    }

    /**
     * // IS THE PRIVATE METHOD USED TO STORE JOB REQUIREMENTS IN BULK WHEN A
     * JOB ROLE IS CREATED
     *
     * @param code
     * @param jobRequirements
     * @throws SQLException
     * @throws RemoteException
     */
    private void createJobRoleRequirements(String code, List<Element> jobRequirements) throws SQLException, RemoteException {
        if (!jobRequirements.isEmpty() && this.jobRoleExists(code)) {
            for (Element temp : jobRequirements) {
                this.createJobRoleRequirement(code, temp.getCode());
            }
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param requirementCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobRoleRequirement(String jobRoleCode, String requirementCode) throws SQLException, RemoteException {
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

    /**
     *
     * @param requirementCode
     * @param jobRoleCode
     * @throws SQLException
     */
    public void deleteJobRoleRequirement(String requirementCode, String jobRoleCode) throws SQLException {
        if (this.jobRoleExists(jobRoleCode) && this.jobRequirementExists(requirementCode)) {
            String deleteSql = "delete from jobRoleRequirements where requirementCode='" + requirementCode + "' and jobRoleCode='" + jobRoleCode + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                deleteStat.executeUpdate(deleteSql);
                deleteStat.close();
            }
        }
    }

    /**
     *
     * @param code
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadJobRoleRequirements(String code) throws SQLException, RemoteException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = (JobRole) this.getJobRole(code);
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
                selectStat.close();
            }
        }
    }

    /**
     * // IS THE PRIVATE METHOD USED TO STORE JOB BENEFITS IN BULK WHEN A JOB
     * ROLE IS CREATED
     *
     * @param code
     * @param benefits
     * @throws SQLException
     * @throws RemoteException
     */
    private void createJobRoleBenefits(String code, List<JobRoleBenefitInterface> benefits) throws SQLException, RemoteException {
        if (!benefits.isEmpty() && this.jobRoleExists(code)) {
            for (JobRoleBenefitInterface benefit : benefits) {
                JobRoleBenefit temp = (JobRoleBenefit) benefit;
                this.createJobRoleBenefit(code, temp);
            }
        }
    }

    /**
     *
     * @param jobRoleCode
     * @param benefit
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobRoleBenefit(String jobRoleCode, JobRoleBenefit benefit) throws SQLException, RemoteException {
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
                insertStat.setInt(col++, benefit.getNote().getReference());
                insertStat.setString(col++, benefit.getComment());
                insertStat.setString(col++, benefit.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(benefit.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            this.jobRoleBenefits.put(benefit.getBenefitRef(), benefit);
            this.notes.put(benefit.getNote().getReference(), benefit.getNote());
        }
    }

    /**
     *
     * @param code
     * @param benefitRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateJobRoleBenefit(String code, int benefitRef) throws SQLException, RemoteException {
        if (this.jobRoleExists(code) && this.jobRoleBenefitExists(benefitRef)) {
            JobRoleBenefitInterface benefit = this.getJobRoleBenefit(benefitRef);
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

    /**
     *
     * @param benefitRef
     * @param jobRoleCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteJobRoleBenefit(int benefitRef, String jobRoleCode) throws SQLException, RemoteException {
        if (this.jobRoleExists(jobRoleCode) && this.jobRoleBenefitExists(benefitRef) && this.getJobRoleBenefit(benefitRef).hasBeenModified()) {
            String deleteSql = "delete from jobRoleBenefits where benefitRef=" + benefitRef + "and jobRoleCode='" + jobRoleCode + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.jobRoleBenefits.remove(benefitRef);
                    this.deleteNote(this.getJobRoleBenefit(benefitRef).getNote().getReference());
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param code
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadJobRoleBenefits(String code) throws SQLException, RemoteException {
        if (this.jobRoleExists(code)) {
            JobRole jobRole = (JobRole) this.getJobRole(code);
            String sql = "select jobBenefitRef, jobRoleCode, benefitCode, doubleValue, stringValue, startDate, endDate, noteRef, comment, createdBy, createdDate from jobRoleBenefits order by jobBenefitRef";
            try (Statement selectStat = con.createStatement()) {
                ResultSet results = selectStat.executeQuery(sql);

                while (results.next()) {
                    String jobRoleCode = results.getString("jobRoleCode");
                    if (code.equals(jobRoleCode)) {
                        int jobBenefitRef = results.getInt("jobBenefitRef");
                        String benefitCode = results.getString("benefitCode");
                        if (this.jobBenefitExists(benefitCode)) {
                            Date startDate = results.getDate("startDate");
                            Date endDate = results.getDate("endDate");
                            String stringValue = results.getString("stringValue");
                            double doubleValue = results.getDouble("doubleValue");
                            int noteRef = results.getInt("noteRef");
                            String comment = results.getString("comment");
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");

                            Note note = new NoteImpl(noteRef, comment, createdBy, createdDate);
                            JobRoleBenefit temp = new JobRoleBenefit(jobBenefitRef, this.getJobBenefit(benefitCode), startDate, stringValue != null, stringValue, doubleValue, note, createdBy, createdDate);
                            if (endDate != null) {
                                temp.setEndDate(endDate, null);
                            }
                            this.jobRoleBenefits.put(temp.getBenefitRef(), temp);
                            this.notes.put(note.getReference(), note);
                            this.loadBenefitMods(temp, this.loadModMap("jobRoleBenefitModifications", temp.getBenefitRef()));
                            jobRole.createJobBenefit(temp, null);
                        }
                    }
                }
                selectStat.close();
            }
        }
    }

    /**
     *
     * @param benefit
     * @param loadedMods
     * @throws RemoteException
     */
    private void loadBenefitMods(JobRoleBenefit benefit, Map<Integer, ModifiedByInterface> loadedMods) throws RemoteException {
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

    /**
     *
     * @param requirement
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobRequirement(Element requirement) throws SQLException, RemoteException {
        if (!this.jobRequirementExists(requirement.getCode())) {
            this.createElement("jobRequirements", requirement);
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.notes.put(requirement.getNote().getReference(), requirement.getNote());
        }
    }

    /**
     *
     * @param requirementCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateJobRequirement(String requirementCode) throws SQLException, RemoteException {
        if (this.jobRequirementExists(requirementCode)) {
            Element requirement = this.getJobRequirement(requirementCode);
            this.updateElement("jobRequirements", requirement);
            this.createModifiedBy("jobRequirementModifications", requirement.getLastModification(), requirement.getCode());
        }
    }

    /**
     *
     * @param requirementCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteJobRequirement(String requirementCode) throws SQLException, RemoteException {
        if (this.jobRequirementExists(requirementCode) && this.canDeleteJobRequirement(requirementCode)) {
            this.deleteElement("jobRequirements", requirementCode);
            this.deleteNote(this.getJobRequirement(requirementCode).getNote().getReference());
            jobRequirements.remove(requirementCode);
        }
    }

    /**
     *
     * @param requirementCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteJobRequirement(String requirementCode) throws RemoteException {
        if (this.jobRequirementExists(requirementCode) && !this.getJobRequirement(requirementCode).hasBeenModified()) {
            for (JobRoleInterface jobRole : this.getJobRoles()) {
                for (Element requirement : jobRole.getJobRequirements()) {
                    if (requirementCode.equals(requirement.getCode())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadJobRequirements() throws SQLException, RemoteException {
        this.jobRequirements.clear();
        List<ElementImpl> loadedJobRequirements;
        loadedJobRequirements = this.loadElements("jobRequirements");
        if (!loadedJobRequirements.isEmpty()) {
            for (Element temp : loadedJobRequirements) {
                if (temp instanceof Element) {
                    this.jobRequirements.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("jobRequirementModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getJobRequirement(String code) {
        if (this.jobRequirementExists(code)) {
            return this.jobRequirements.get(code);
        }
        return null;
    }

    /**
     *
     * @param benefit
     * @throws SQLException
     * @throws RemoteException
     */
    public void createJobBenefit(Element benefit) throws SQLException, RemoteException {
        if (!this.jobBenefitExists(benefit.getCode())) {
            this.createElement("jobBenefits", benefit);
            this.jobBenefits.put(benefit.getCode(), benefit);
            this.notes.put(benefit.getNote().getReference(), benefit.getNote());
        }
    }

    /**
     *
     * @param benefitCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateJobBenefit(String benefitCode) throws SQLException, RemoteException {
        if (this.jobBenefitExists(benefitCode)) {
            Element benefit = this.getJobBenefit(benefitCode);
            this.updateElement("jobBenefits", benefit);
            this.createModifiedBy("jobBenefitModifications", benefit.getLastModification(), benefit.getCode());
        }
    }

    /**
     *
     * @param benefitCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteJobBenefit(String benefitCode) throws SQLException, RemoteException {
        if (this.jobBenefitExists(benefitCode) && this.canDeleteJobBenefit(benefitCode)) {
            this.deleteElement("jobBenefits", benefitCode);
            this.deleteNote(this.getJobBenefit(benefitCode).getNote().getReference());
            jobBenefits.remove(benefitCode);
        }
    }

    /**
     *
     * @param benefitCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteJobBenefit(String benefitCode) throws RemoteException {
        if (this.jobBenefitExists(benefitCode) && !this.getJobBenefit(benefitCode).hasBeenModified()) {
            for (JobRoleBenefitInterface benefit : this.getJobRoleBenefits()) {
                if (benefitCode.equals(benefit.getBenefitCode())) {
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
     * @throws RemoteException
     */
    private void loadJobBenefits() throws SQLException, RemoteException {
        this.jobBenefits.clear();
        List<ElementImpl> loadedJobBenefits;
        loadedJobBenefits = this.loadElements("jobBenefits");
        if (!loadedJobBenefits.isEmpty()) {
            for (Element temp : loadedJobBenefits) {
                if (temp instanceof Element) {
                    this.jobBenefits.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("jobBenefitModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getJobBenefit(String code) {
        if (this.jobBenefitExists(code)) {
            return this.jobBenefits.get(code);
        }
        return null;
    }

    /**
     *
     * @param employee
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployee(Employee employee) throws SQLException, RemoteException {
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

    /**
     *
     * @param employeeRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEmployee(int employeeRef) throws SQLException, RemoteException {
        if (this.employeeExists(employeeRef)) {
            Employee employee = (Employee) this.getEmployee(employeeRef);
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

    /**
     *
     * @param employeeRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEmployee(int employeeRef) throws SQLException, RemoteException {
        if (this.employeeExists(employeeRef) && this.canDeleteEmployee(employeeRef)) {
            String deleteSql = "delete from employees where employeeRef=" + employeeRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    this.deleteUser(employeeRef, this.getUser(employeeRef).getUsername());
                    employees.remove(employeeRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param employeeRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteEmployee(int employeeRef) throws RemoteException {
        if (this.employeeExists(employeeRef) && !this.getEmployee(employeeRef).hasBeenModified()) {
            for (ContractInterface contract : this.getContracts()) {
                if (contract.getEmployeeRef() == employeeRef) {
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
     * @throws RemoteException
     */
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
                    PersonInterface person = this.getPerson(personRef);
                    String officeCode = results.getString("officeCode");
                    if ((officeCode == null || this.officeExists(officeCode))) {
                        String createdBy = results.getString("createdBy");
                        Date createdDate = results.getDate("createdDate");
                        try (PreparedStatement selectStat1 = con.prepareStatement(sql1)) {
                            selectStat1.setInt(1, employeeRef);
                            ResultSet results1 = selectStat1.executeQuery();
                            results1.next();
                            int count = results1.getInt("count");
                            if (count == 1) {
                                try (PreparedStatement selectStat2 = con.prepareStatement(sql2)) {
                                    selectStat2.setInt(1, employeeRef);
                                    ResultSet results2 = selectStat2.executeQuery();
                                    results2.next();
                                    String username = results2.getString("username");
                                    String password = results2.getString("password");
                                    Employee temp = new Employee(employeeRef, person, username, password, createdBy, createdDate);
                                    employees.put(temp.getEmployeeRef(), temp);
                                    users.put(temp.getUser().getUsername(), temp.getUser());
                                    this.loadEmployeeMods(temp.getEmployeeRef(), this.loadModMap("employeeModifications", temp.getEmployeeRef()));
                                    this.loadEmployeeNotes(temp.getEmployeeRef(), this.loadNoteMap("employeeNotes", temp.getEmployeeRef()));
                                    selectStat2.close();
                                }
                            }
                            selectStat1.close();
                        }
                    }
                }
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param employeeRef
     * @param loadadMods
     */
    private void loadEmployeeMods(int employeeRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.employeeExists(employeeRef) && !loadadMods.isEmpty()) {
            Employee employee = (Employee) this.getEmployee(employeeRef);
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

    /**
     *
     * @param employeeRef
     * @return
     */
    public EmployeeInterface getEmployee(int employeeRef) {
        if (this.employeeExists(employeeRef)) {
            return this.employees.get(employeeRef);
        }
        return null;
    }

    public EmployeeInterface getPersonEmployee(int personRef) throws RemoteException {
        if (this.personEmployeeExists(personRef)) {
            for (EmployeeInterface temp : this.getEmployees()) {
                if (personRef == temp.getPersonRef()) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param employeeRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadEmployeeNotes(int employeeRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.employeeExists(employeeRef) && !loadadNotes.isEmpty()) {
            Employee employee = (Employee) this.getEmployee(employeeRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                employee.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param employeeRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployeeNote(int employeeRef, Note note) throws SQLException, RemoteException {
        if (this.employeeExists(employeeRef) && !this.noteExists(note.getReference()) && this.getEmployee(employeeRef).hasNote(note.getReference())) {
            this.createNote("employeeNotes", employeeRef, note);
        }
    }

    /**
     *
     * @param employeeRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEmployeeNote(int employeeRef, int noteRef) throws SQLException, RemoteException {
        if (this.employeeExists(employeeRef) && this.noteExists(noteRef)) {
            this.updateNote("employeeNotes", employeeRef, noteRef);
        }
    }

    /**
     *
     * @param employeeRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEmployeeNote(int employeeRef, int noteRef) throws SQLException, RemoteException {
        if (this.employeeExists(employeeRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("employeeNotes", employeeRef, noteRef);
        }
    }

    /**
     *
     * @param tenancy
     * @throws SQLException
     * @throws RemoteException
     */
    public void createTenancy(Tenancy tenancy) throws SQLException, RemoteException {
        if (!this.tenancyExists(tenancy.getAgreementRef())) {
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

    /**
     *
     * @param tenancyRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateTenancy(int tenancyRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef)) {
            Tenancy tenancy = (Tenancy) this.getTenancy(tenancyRef);
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

    /**
     *
     * @param tenancyRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteTenancy(int tenancyRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && this.canDeleteTenancy(tenancyRef)) {
            String deleteSql = "delete from tenancies where tenancyRef=" + tenancyRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    tenancies.remove(tenancyRef);
                    this.deleteRentAccount(this.getTenancy(tenancyRef).getAccountRef());
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param tenancyRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteTenancy(int tenancyRef) throws RemoteException {
        return this.tenancyExists(tenancyRef) && this.getTenancy(tenancyRef).hasBeenModified() && this.getRentAccount(this.getTenancy(tenancyRef).getAccountRef()).hasBeenModified();
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadTenancies() throws SQLException, RemoteException {
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
                        ApplicationInterface application = this.getApplication(appRef);
                        int propRef = results.getInt("propRef");
                        if (this.propertyExists(propRef)) {
                            PropertyInterface property = this.getProperty(propRef);
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
            selectStat.close();
        }
    }

    /**
     *
     * @param tenancyRef
     * @param loadadMods
     */
    private void loadTenancyMods(int tenancyRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.tenancyExists(tenancyRef) && !loadadMods.isEmpty()) {
            Tenancy tenancy = (Tenancy) this.getTenancy(tenancyRef);
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

    /**
     *
     * @param tenancyRef
     * @return
     */
    public TenancyInterface getTenancy(int tenancyRef) {
        if (tenancies.containsKey(tenancyRef)) {
            return tenancies.get(tenancyRef);
        }
        return null;
    }

    /**
     *
     * @param tenancyRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadTenancyDocs(int tenancyRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.tenancyExists(tenancyRef) && !loadedDocs.isEmpty()) {
            Tenancy tenancy = (Tenancy) this.getTenancy(tenancyRef);
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

    /**
     *
     * @param tenancyRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createTenancyDoc(int tenancyRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && !this.documentExists(document.getDocumentRef()) && this.getTenancy(tenancyRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("tenancyDocuments", tenancyRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param tenancyRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateTenancyDoc(int tenancyRef, int dRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && this.documentExists(dRef) && this.getTenancy(tenancyRef).hasDocument(dRef)) {
            this.updateDocument("tenancyDocuments", tenancyRef, dRef);
        }
    }

    /**
     *
     * @param tenancyRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteTenancyDoc(int tenancyRef, int documentRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && this.documentExists(documentRef) && this.getTenancy(tenancyRef).hasDocument(documentRef)) {
            this.deleteDocument("tenancyDocuments", tenancyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param tenancyRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadTenancyNotes(int tenancyRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.tenancyExists(tenancyRef) && !loadadNotes.isEmpty()) {
            Tenancy tenancy = (Tenancy) this.getTenancy(tenancyRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                tenancy.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param tenancyRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createTenancyNote(int tenancyRef, Note note) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && !this.noteExists(note.getReference()) && this.getTenancy(tenancyRef).hasNote(note.getReference())) {
            this.createNote("tenancyNotes", tenancyRef, note);
        }
    }

    /**
     *
     * @param tenancyRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateTenancyNote(int tenancyRef, int noteRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && this.noteExists(noteRef)) {
            this.updateNote("tenancyNotes", tenancyRef, noteRef);
        }
    }

    /**
     *
     * @param tenancyRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteTenancyNote(int tenancyRef, int noteRef) throws SQLException, RemoteException {
        if (this.tenancyExists(tenancyRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("tenancyNotes", tenancyRef, noteRef);
        }
    }

    /**
     *
     * @param tenType
     * @throws SQLException
     * @throws RemoteException
     */
    public void createTenancyType(Element tenType) throws SQLException, RemoteException {
        if (!this.tenancyTypeExists(tenType.getCode())) {
            this.createElement("tenancyTypes", tenType);
            this.tenancyTypes.put(tenType.getCode(), tenType);
            this.notes.put(tenType.getNote().getReference(), tenType.getNote());
        }
    }

    /**
     *
     * @param tenTypeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateTenancyType(String tenTypeCode) throws SQLException, RemoteException {
        if (this.tenancyTypeExists(tenTypeCode)) {
            Element tenType = this.getTenancyType(tenTypeCode);
            this.updateElement("tenancyTypes", tenType);
            this.createModifiedBy("tenancyTypeModifications", tenType.getLastModification(), tenType.getCode());
        }
    }

    /**
     *
     * @param tenTypeCode
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteTenancyType(String tenTypeCode) throws SQLException, RemoteException {
        if (this.tenancyTypeExists(tenTypeCode) && this.canDeleteTitle(tenTypeCode)) {
            this.deleteElement("tenancyTypes", tenTypeCode);
            this.deleteNote(this.getTenancyType(tenTypeCode).getNote().getReference());
            this.titles.remove(tenTypeCode);
        }
    }

    /**
     *
     * @param tenTypeCode
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteTenancyType(String tenTypeCode) throws RemoteException {
        if (this.tenancyTypeExists(tenTypeCode) && !this.getTenancyType(tenTypeCode).hasBeenModified()) {
            for (TenancyInterface tenancy : this.getTenancies()) {
                if (tenTypeCode.equals(tenancy.getTenType().getCode())) {
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
     * @throws RemoteException
     */
    private void loadTenancyTypes() throws SQLException, RemoteException {
        this.tenancyTypes.clear();
        List<ElementImpl> loadedTenancyTypes;
        loadedTenancyTypes = this.loadElements("tenancyTypes");
        if (!loadedTenancyTypes.isEmpty()) {
            for (Element temp : loadedTenancyTypes) {
                if (temp instanceof Element) {
                    this.tenancyTypes.put(temp.getCode(), temp);
                    this.notes.put(temp.getNote().getReference(), temp.getNote());
                    this.loadElementMods(temp, this.loadModMap("tenancyTypeModifications", temp.getCode()));
                }
            }
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public Element getTenancyType(String code) {
        if (this.tenancyTypeExists(code)) {
            return this.tenancyTypes.get(code);
        }
        return null;
    }

    /**
     *
     * @param lease
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLease(Lease lease) throws SQLException, RemoteException {
        if (!this.leaseExists(lease.getAgreementRef())) {
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

    /**
     *
     * @param leaseRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLease(int leaseRef) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef)) {
            Lease lease = (Lease) this.getLease(leaseRef);
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

    /**
     *
     * @param leaseRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLease(int leaseRef) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && this.canDeleteLease(leaseRef)) {
            String deleteSql = "delete from leases where leaseRef=" + leaseRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    leases.remove(leaseRef);
                    this.deleteLeaseAccount(this.getLease(leaseRef).getAccountRef());
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param leaseRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteLease(int leaseRef) throws RemoteException {
        return this.leaseExists(leaseRef) && this.getLease(leaseRef).hasBeenModified() && this.getLeaseAccount(this.getLease(leaseRef).getAccountRef()).hasBeenModified();
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadLeases() throws SQLException, RemoteException {
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
                        Property property = (Property) this.getProperty(propRef);
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
            selectStat.close();
        }
    }

    /**
     *
     * @param leaseRef
     * @param loadadMods
     */
    private void loadLeaseMods(int leaseRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseExists(leaseRef) && !loadadMods.isEmpty()) {
            Lease lease = (Lease) this.getLease(leaseRef);
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

    /**
     *
     * @param leaseRef
     * @return
     */
    public LeaseInterface getLease(int leaseRef) {
        if (leases.containsKey(leaseRef)) {
            return leases.get(leaseRef);
        }
        return null;
    }

    /**
     *
     * @param leaseRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadLeaseDocs(int leaseRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.leaseExists(leaseRef) && !loadedDocs.isEmpty()) {
            Lease lease = (Lease) this.getLease(leaseRef);
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

    /**
     *
     * @param leaseRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseDoc(int leaseRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && !this.documentExists(document.getDocumentRef()) && this.getLease(leaseRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("leaseDocuments", leaseRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param leaseRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLeaseDoc(int leaseRef, int dRef) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && this.documentExists(dRef) && this.getLease(leaseRef).hasDocument(dRef)) {
            this.updateDocument("leaseDocuments", leaseRef, dRef);
        }
    }

    /**
     *
     * @param tenancyRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLeaseDoc(int tenancyRef, int documentRef) throws SQLException, RemoteException {
        if (this.leaseExists(tenancyRef) && this.documentExists(documentRef) && this.getLease(tenancyRef).hasDocument(documentRef)) {
            this.deleteDocument("leaseDocuments", tenancyRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param leaseRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadLeaseNotes(int leaseRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.leaseExists(leaseRef) && !loadadNotes.isEmpty()) {
            Lease lease = (Lease) this.getLease(leaseRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                lease.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param leaseRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseNote(int leaseRef, Note note) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && !this.noteExists(note.getReference()) && this.getLease(leaseRef).hasNote(note.getReference())) {
            this.createNote("leaseNotes", leaseRef, note);
        }
    }

    /**
     *
     * @param leaseRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLeaseNote(int leaseRef, int noteRef) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && this.noteExists(noteRef)) {
            this.updateNote("leaseNotes", leaseRef, noteRef);
        }
    }

    /**
     *
     * @param leaseRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLeaseNote(int leaseRef, int noteRef) throws SQLException, RemoteException {
        if (this.leaseExists(leaseRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("leaseNotes", leaseRef, noteRef);
        }
    }

    /**
     *
     * @param landlordRef
     * @param leaseRef
     * @throws SQLException
     */
    public void createLeaseLandlord(int landlordRef, int leaseRef) throws SQLException {
        if (this.leaseExists(leaseRef) && this.landlordExists(landlordRef)) {
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
                    insertStat.setInt(col++, landlordRef);
                    insertStat.setInt(col++, leaseRef);
                    insertStat.setBoolean(col++, true);
                    insertStat.executeUpdate();
                    insertStat.close();
                } else if (count >= 1) {
                    String updateSql = "update leaseLandlord set cur=? where landlordRef=? and leaseRef=?";
                    updateStat = con.prepareStatement(updateSql);
                    updateStat.setBoolean(col++, true);
                    updateStat.setInt(col++, landlordRef);
                    updateStat.setInt(col++, leaseRef);
                    updateStat.executeUpdate();
                    updateStat.close();
                }
                checkStat.close();
            }
        }
    }

    /**
     *
     * @param ref
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadLeaseLandlords(int ref) throws SQLException, RemoteException {
        String sql = "select landlordRef, leaseRef, cur from leaseLandlord order by leaseRef, landlordRef";
        try (Statement selectStat = con.createStatement()) {
            ResultSet results = selectStat.executeQuery(sql);

            while (results.next()) {
                int leaseRef = results.getInt("leaseRef");
                int landlordRef = results.getInt("landlordRef");
                boolean current = results.getBoolean("cur");
                if (leaseRef == ref && this.leaseExists(leaseRef) && this.landlordExists(landlordRef) && current) {
                    Lease lease = (Lease) this.getLease(leaseRef);
                    lease.addLandlord(this.getLandlord(landlordRef), null);
                    Landlord landlord = (Landlord) this.getLandlord(landlordRef);
                    landlord.createLease(lease, null);
                }
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param landlordRef
     * @param leaseRef
     * @throws SQLException
     */
    public void endLeaseLandlord(int landlordRef, int leaseRef) throws SQLException {
        if (this.leaseExists(leaseRef) && this.landlordExists(landlordRef)) {
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
                    updateStat.close();
                } else if (count == 0) {
                    String insertSql = "insert into leaseLandlord (landlordRef, leaseRef, cur) values (?, ?, ?)";
                    insertStat = con.prepareStatement(insertSql);
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

    /**
     *
     * @param contract
     * @throws SQLException
     * @throws RemoteException
     */
    public void createContract(Contract contract) throws SQLException, RemoteException {
        if (!this.contractExists(contract.getAgreementRef())) {
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

    /**
     *
     * @param contractRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateContract(int contractRef) throws SQLException, RemoteException {
        if (this.contractExists(contractRef)) {
            ContractInterface contract = this.getContract(contractRef);
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

    /**
     *
     * @param contractRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteContract(int contractRef) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && this.canDeleteContract(contractRef)) {
            String deleteSql = "delete from contracts where contractRef=" + contractRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    contracts.remove(contractRef);
                    this.deleteEmployeeAccount(this.getContract(contractRef).getAccountRef());
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param contractRef
     * @return
     * @throws RemoteException
     */
    public boolean canDeleteContract(int contractRef) throws RemoteException {
        return this.contractExists(contractRef) && this.getContract(contractRef).hasBeenModified() && this.getEmployeeAccount(this.getContract(contractRef).getAccountRef()).hasBeenModified();
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadContracts() throws SQLException, RemoteException {
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
                        Employee employee = (Employee) this.getEmployee(employeeRef);
                        String jobRoleCode = results.getString("jobRoleCode");
                        if (this.jobRoleExists(jobRoleCode)) {
                            JobRoleInterface jobRole = this.getJobRole(jobRoleCode);
                            String createdBy = results.getString("createdBy");
                            Date createdDate = results.getDate("createdDate");
                            Contract temp = new Contract(contractRef, accountRef, startDate, length, employee, jobRole, officeCode, createdBy, createdDate);
                            if (actualEndDate != null) {
                                temp.setActualEndDate(actualEndDate, null);
                            }
                            this.contracts.put(temp.getAgreementRef(), temp);
                            employee.createContract(temp, null);
                            UserImpl user = (UserImpl) employee.getUser();
                            user.setUserPermissions(jobRole.getRead(), jobRole.getWrite(), jobRole.getUpdate(), jobRole.getEmployeeRead(), jobRole.getEmployeeWrite(), jobRole.getEmployeeUpdate());
                            this.loadContractMods(temp.getAgreementRef(), this.loadModMap("contractModifications", temp.getAgreementRef()));
                            this.loadContractNotes(temp.getAgreementRef(), this.loadNoteMap("contractNotes", temp.getAgreementRef()));
                            this.loadContractDocs(temp.getAgreementRef(), this.loadDocMap("contractDocuments", temp.getAgreementRef()));
                        }
                    }
                }
            }
            selectStat.close();
        }
    }

    /**
     *
     * @param contractRef
     * @param loadadMods
     */
    private void loadContractMods(int contractRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(contractRef) && !loadadMods.isEmpty()) {
            Contract contract = (Contract) this.getContract(contractRef);
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

    /**
     *
     * @param contractRef
     * @return
     */
    public ContractInterface getContract(int contractRef) {
        if (contracts.containsKey(contractRef)) {
            return contracts.get(contractRef);
        }
        return null;
    }

    /**
     *
     * @param contractRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadContractDocs(int contractRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.contractExists(contractRef) && !loadedDocs.isEmpty()) {
            Contract contract = (Contract) this.getContract(contractRef);
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

    /**
     *
     * @param contractRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createContractDoc(int contractRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && !this.documentExists(document.getDocumentRef()) && this.getContract(contractRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("contractDocuments", contractRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param contractRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateContractDoc(int contractRef, int dRef) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && this.documentExists(dRef) && this.getContract(contractRef).hasDocument(dRef)) {
            this.updateDocument("contractDocuments", contractRef, dRef);
        }
    }

    /**
     *
     * @param contractRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteContractDoc(int contractRef, int documentRef) throws SQLException, RemoteException {
        if (this.leaseExists(contractRef) && this.documentExists(documentRef) && this.getContract(contractRef).hasDocument(documentRef)) {
            this.deleteDocument("contractDocuments", contractRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param contractRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadContractNotes(int contractRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.contractExists(contractRef) && !loadadNotes.isEmpty()) {
            Contract contract = (Contract) this.getContract(contractRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                contract.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param contractRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createContractNote(int contractRef, Note note) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && !this.noteExists(note.getReference()) && this.getContract(contractRef).hasNote(note.getReference())) {
            this.createNote("contractNotes", contractRef, note);
        }
    }

    /**
     *
     * @param contractRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateContractNote(int contractRef, int noteRef) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && this.noteExists(noteRef)) {
            this.updateNote("contractNotes", contractRef, noteRef);
        }
    }

    /**
     *
     * @param contractRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteContractNote(int contractRef, int noteRef) throws SQLException, RemoteException {
        if (this.contractExists(contractRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("contractNotes", contractRef, noteRef);
        }
    }

    /**
     *
     * @param rentAcc
     * @throws SQLException
     * @throws RemoteException
     */
    public void createRentAccount(RentAccount rentAcc) throws SQLException, RemoteException {
        if (!this.rentAccountExists(rentAcc.getAccRef())) {
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

    /**
     *
     * @param rentAccRef
     * @throws SQLException
     */
    private void deleteRentAccount(int rentAccRef) throws SQLException {
        if (this.rentAccountExists(rentAccRef)) {
            String deleteSql = "delete from rentAccounts where rentAccRef=" + rentAccRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    rentAccounts.remove(rentAccRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @param rentAccRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateRentAccount(int rentAccRef) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef)) {
            RentAccount rentAcc = (RentAccount) this.getRentAccount(rentAccRef);
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

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadRentAccounts() throws SQLException, RemoteException {
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
                        TenancyInterface tenancy = this.getTenancy(tenancyRef);
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
            selectStat.close();
        }
    }

    /**
     *
     * @param rentAccRef
     * @param loadadMods
     */
    private void loadRentAccountMods(int rentAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.rentAccountExists(rentAccRef) && !loadadMods.isEmpty()) {
            RentAccount rentAcc = (RentAccount) this.getRentAccount(rentAccRef);
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

    /**
     *
     * @param rentAccRef
     * @return
     */
    public RentAccountInterface getRentAccount(int rentAccRef) {
        if (rentAccounts.containsKey(rentAccRef)) {
            return rentAccounts.get(rentAccRef);
        }
        return null;
    }

    /**
     *
     * @param rentAccRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadRentAccountDocs(int rentAccRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.rentAccountExists(rentAccRef) && !loadedDocs.isEmpty()) {
            RentAccount rentAcc = (RentAccount) this.getRentAccount(rentAccRef);
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

    /**
     *
     * @param rentAccRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createRentAccountDoc(int rentAccRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && !this.documentExists(document.getDocumentRef()) && this.getRentAccount(rentAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("rentAccountDocuments", rentAccRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param rentAccRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateRentAccountDoc(int rentAccRef, int dRef) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && this.documentExists(dRef) && this.getRentAccount(rentAccRef).hasDocument(dRef)) {
            this.updateDocument("rentAccountDocuments", rentAccRef, dRef);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteRentAccountDoc(int rentAccRef, int documentRef) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && this.documentExists(documentRef) && this.getRentAccount(rentAccRef).hasDocument(documentRef)) {
            this.deleteDocument("rentAccountDocuments", rentAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadRentAccountNotes(int rentAccRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.rentAccountExists(rentAccRef) && !loadadNotes.isEmpty()) {
            RentAccount rentAcc = (RentAccount) this.getRentAccount(rentAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                rentAcc.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param rentAccRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createRentAccountNote(int rentAccRef, Note note) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && !this.noteExists(note.getReference()) && this.getRentAccount(rentAccRef).hasNote(note.getReference())) {
            this.createNote("rentAccountNotes", rentAccRef, note);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateRentAccountNote(int rentAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && this.noteExists(noteRef)) {
            this.updateNote("rentAccountNotes", rentAccRef, noteRef);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteRentAccountNote(int rentAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.rentAccountExists(rentAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("rentAccountNotes", rentAccRef, noteRef);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param transaction
     * @throws SQLException
     * @throws RemoteException
     */
    public void createRentAccountTransaction(int rentAccRef, Transaction transaction) throws SQLException, RemoteException {
        if (transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.rentAccountExists(rentAccRef)) {
            this.createTransaction("rentTransactions", transaction);
        }
    }

    /**
     *
     * @param rentAccRef
     * @param transactionRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteRentAccountTransaction(int rentAccRef, int transactionRef) throws SQLException, RemoteException {
        if (this.transactionExists(transactionRef) && this.rentAccountExists(rentAccRef)) {
            this.deleteTransaction("rentTransactions", transactionRef, rentAccRef);
        }
    }

    /**
     *
     * @param leaseAcc
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseAccount(LeaseAccount leaseAcc) throws SQLException, RemoteException {
        if (!this.leaseAccountExists(leaseAcc.getAccRef())) {
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

    /**
     *
     * @param leaseAccRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLeaseAccount(int leaseAccRef) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef)) {
            LeaseAccountInterface leaseAcc = this.getLeaseAccount(leaseAccRef);
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

    /**
     *
     * @param leaseAccRef
     * @throws SQLException
     */
    private void deleteLeaseAccount(int leaseAccRef) throws SQLException {
        if (this.leaseAccountExists(leaseAccRef)) {
            String deleteSql = "delete from leaseAccounts where leaseAccRef=" + leaseAccRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    leaseAccounts.remove(leaseAccRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadLeaseAccounts() throws SQLException, RemoteException {
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
                        LeaseInterface lease = this.getLease(leaseRef);
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
            selectStat.close();
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param loadadMods
     */
    private void loadLeaseAccountMods(int leaseAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.leaseAccountExists(leaseAccRef) && !loadadMods.isEmpty()) {
            LeaseAccount leaseAcc = (LeaseAccount) this.getLeaseAccount(leaseAccRef);
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

    /**
     *
     * @param leaseAccRef
     * @return
     */
    public LeaseAccountInterface getLeaseAccount(int leaseAccRef) {
        if (leaseAccounts.containsKey(leaseAccRef)) {
            return leaseAccounts.get(leaseAccRef);
        }
        return null;
    }

    /**
     *
     * @param leaseAccRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadLeaseAccountDocs(int leaseAccRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && !loadedDocs.isEmpty()) {
            LeaseAccount leaseAcc = (LeaseAccount) this.getLeaseAccount(leaseAccRef);
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

    /**
     *
     * @param leaseAccRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseAccountDoc(int leaseAccRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && !this.documentExists(document.getDocumentRef()) && this.getLeaseAccount(leaseAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("leaseAccountDocuments", leaseAccRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLeaseAccountDoc(int leaseAccRef, int dRef) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && this.documentExists(dRef) && this.getLeaseAccount(leaseAccRef).hasDocument(dRef)) {
            this.updateDocument("leaseAccountDocuments", leaseAccRef, dRef);
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLeaseAccountDoc(int leaseAccRef, int documentRef) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && this.documentExists(documentRef) && this.getLeaseAccount(leaseAccRef).hasDocument(documentRef)) {
            this.deleteDocument("leaseAccountDocuments", leaseAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadLeaseAccountNotes(int leaseAccRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && !loadadNotes.isEmpty()) {
            LeaseAccount leaseAcc = (LeaseAccount) this.getLeaseAccount(leaseAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                leaseAcc.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseAccountNote(int leaseAccRef, Note note) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && !this.noteExists(note.getReference()) && this.getLeaseAccount(leaseAccRef).hasNote(note.getReference())) {
            this.createNote("leaseAccountNotes", leaseAccRef, note);
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateLeaseAccountNote(int leaseAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && this.noteExists(noteRef)) {
            this.updateNote("leaseAccountNotes", leaseAccRef, noteRef);
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteLeaseAccountNote(int leaseAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.leaseAccountExists(leaseAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("leaseAccountNotes", leaseAccRef, noteRef);
        }
    }

    /**
     *
     * @param leaseAccRef
     * @param transaction
     * @throws SQLException
     * @throws RemoteException
     */
    public void createLeaseAccountTransaction(int leaseAccRef, Transaction transaction) throws SQLException, RemoteException {
        if (transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.leaseAccountExists(leaseAccRef)) {
            this.createTransaction("leaseTransactions", transaction);
        }
    }

    /**
     *
     *
     * @param leaseAccRef
     * @param transactionRef
     * @throws java.sql.SQLException
     * @throws java.rmi.RemoteException
     */
    public void deleteLeaseAccountTransaction(int leaseAccRef, int transactionRef) throws SQLException, RemoteException {
        if (this.transactionExists(transactionRef) && this.leaseAccountExists(leaseAccRef)) {
            this.deleteTransaction("leaseTransactions", transactionRef, leaseAccRef);
        }
    }

    /**
     *
     * @param employeeAcc
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployeeAccount(EmployeeAccount employeeAcc) throws SQLException, RemoteException {
        if (!this.employeeAccountExists(employeeAcc.getAccRef())) {
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

    /**
     *
     * @param employeeAccRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEmployeeAccount(int employeeAccRef) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef)) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.getEmployeeAccount(employeeAccRef);
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

    /**
     *
     * @param employeeAccRef
     * @throws SQLException
     */
    private void deleteEmployeeAccount(int employeeAccRef) throws SQLException {
        if (this.employeeAccountExists(employeeAccRef)) {
            String deleteSql = "delete from employeeAccounts where employeeAccRef=" + employeeAccRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    employeeAccounts.remove(employeeAccRef);
                    deleteStat.close();
                }
            }
        }
    }

    /**
     *
     * @throws SQLException
     * @throws RemoteException
     */
    private void loadEmployeeAccounts() throws SQLException, RemoteException {
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
                        ContractInterface contract = this.getContract(contractRef);
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
                        this.loadEmployeeAccountDocs(temp.getAccRef(), this.loadDocMap("employeeAccountDocuments", temp.getAccRef()));
                    }
                }
            }
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param loadadMods
     */
    private void loadEmployeeAccountMods(int employeeAccRef, Map<Integer, ModifiedByInterface> loadadMods) {
        if (this.contractExists(employeeAccRef) && !loadadMods.isEmpty()) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.getEmployeeAccount(employeeAccRef);
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

    /**
     *
     * @param employeeAccRef
     * @return
     */
    public EmployeeAccountInterface getEmployeeAccount(int employeeAccRef) {
        if (employeeAccounts.containsKey(employeeAccRef)) {
            return employeeAccounts.get(employeeAccRef);
        }
        return null;
    }

    /**
     *
     * @param employeeAccRef
     * @param loadedDocs
     * @throws RemoteException
     */
    private void loadEmployeeAccountDocs(int employeeAccRef, Map<Integer, Document> loadedDocs) throws RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && !loadedDocs.isEmpty()) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.getEmployeeAccount(employeeAccRef);
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

    /**
     *
     * @param employeeAccRef
     * @param document
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployeeAccountDoc(int employeeAccRef, DocumentImpl document) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && !this.documentExists(document.getDocumentRef()) && this.getEmployeeAccount(employeeAccRef).hasDocument(document.getDocumentRef())) {
            this.createDocument("employeeAccountDocuments", employeeAccRef, document);
            this.notes.put(document.getNote().getReference(), document.getNote());
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param dRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEmployeeAccountDoc(int employeeAccRef, int dRef) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && this.documentExists(dRef) && this.getEmployeeAccount(employeeAccRef).hasDocument(dRef)) {
            this.updateDocument("employeeAccountDocuments", employeeAccRef, dRef);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param documentRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEmployeeAccountDoc(int employeeAccRef, int documentRef) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && this.documentExists(documentRef) && this.getEmployeeAccount(employeeAccRef).hasDocument(documentRef)) {
            this.deleteDocument("employeeAccountDocuments", employeeAccRef, documentRef);
            this.deleteNote(documentRef);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param loadadNotes
     * @throws RemoteException
     */
    private void loadEmployeeAccountNotes(int employeeAccRef, Map<Integer, Note> loadadNotes) throws RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && !loadadNotes.isEmpty()) {
            EmployeeAccount employeeAcc = (EmployeeAccount) this.getEmployeeAccount(employeeAccRef);
            Iterator it = loadadNotes.entrySet().iterator();
            while (it.hasNext()) {
                Note tempNote;
                Map.Entry temp = (Map.Entry) it.next();
                tempNote = (Note) temp.getValue();
                employeeAcc.createNote(tempNote, null);
                notes.put(tempNote.getReference(), tempNote);
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param note
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployeeAccountNote(int employeeAccRef, Note note) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && !this.noteExists(note.getReference()) && this.getEmployeeAccount(employeeAccRef).hasNote(note.getReference())) {
            this.createNote("employeeAccountNotes", employeeAccRef, note);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateEmployeeAccountNote(int employeeAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && this.noteExists(noteRef)) {
            this.updateNote("employeeAccountNotes", employeeAccRef, noteRef);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param noteRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEmployeeAccountNote(int employeeAccRef, int noteRef) throws SQLException, RemoteException {
        if (this.employeeAccountExists(employeeAccRef) && this.noteExists(noteRef) && this.canDeleteNote(noteRef)) {
            this.deleteNote("employeeAccountNotes", employeeAccRef, noteRef);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param transaction
     * @throws SQLException
     * @throws RemoteException
     */
    public void createEmployeeAccountTransaction(int employeeAccRef, Transaction transaction) throws SQLException, RemoteException {
        if (transaction != null && !this.transactionExists(transaction.getTransactionRef()) && this.employeeAccountExists(employeeAccRef)) {
            this.createTransaction("contractTransactions", transaction);
        }
    }

    /**
     *
     * @param employeeAccRef
     * @param transactionRef
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteEmployeeAccountTransaction(int employeeAccRef, int transactionRef) throws SQLException, RemoteException {
        if (this.transactionExists(transactionRef) && this.employeeAccountExists(employeeAccRef)) {
            this.deleteTransaction("contractTransactions", transactionRef, employeeAccRef);
        }
    }

    private void createTransaction(String from, Transaction transaction) throws SQLException, RemoteException {
        if (!this.transactionExists(transaction.getTransactionRef())) {
            String insertSql = "insert into " + from + " (transactionRef, accountRef, fromRef, toRef, amount, "
                    + "isDebit, transactionDate, noteRef, comment, createdBy, createdDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStat = con.prepareStatement(insertSql)) {
                int col = 1;
                insertStat.setInt(col++, transaction.getTransactionRef());
                insertStat.setInt(col++, transaction.getAccountRef());
                insertStat.setInt(col++, transaction.getFromRef());
                insertStat.setInt(col++, transaction.getToRef());
                insertStat.setDouble(col++, transaction.getAmount());
                insertStat.setBoolean(col++, transaction.isDebit());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(transaction.getTransactionDate()));
                insertStat.setInt(col++, transaction.getNote().getReference());
                insertStat.setString(col++, transaction.getNote().getNote());
                insertStat.setString(col++, transaction.getCreatedBy());
                insertStat.setDate(col++, DateConversion.utilDateToSQLDate(transaction.getCreatedDate()));
                insertStat.executeUpdate();
                insertStat.close();
            }
            transactions.put(transaction.getTransactionRef(), transaction);
        }
    }

    private void deleteTransaction(String from, int transactionRef, int accRef) throws SQLException, RemoteException {
        if (this.tenancyExists(transactionRef) && this.canDeleteTenancy(transactionRef)) {
            String deleteSql = "delete from " + from + " where transactionRef=" + transactionRef + " and accRef=" + accRef;
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    transactions.remove(transactionRef);
                    this.deleteNote(this.getTransaction(transactionRef).getNote().getReference());
                    deleteStat.close();
                }
            }
        }
    }

    private void loadTransactions(String from, Account account) throws SQLException, RemoteException {
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
            selectStat.close();
        }
    }

    public TransactionInterface getTransaction(int ref) {
        if (this.transactions.containsKey(ref)) {
            return this.transactions.get(ref);
        }
        return null;
    }

    public void createUser(User user) throws SQLException, RemoteException {
        if ((!this.userExists(user.getUsername())) && (this.employeeExists(user.getEmployeeRef()) || user.getUsername().equals("ADMIN"))) {
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

    public void updateUser(String username) throws SQLException, RemoteException {
        if (this.userExists(username)) {
            User user = this.getUser(username);
            String updateSql = "update users set password=?, otherRead=?, otherWrite=?, otherUpdate=?, "
                    + "employeeRead=?, employeeWrite=?, employeeUpdate=? where username=? and employeeRef=?";
            try (PreparedStatement updateStat = con.prepareStatement(updateSql)) {
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
            EmployeeInterface employee = this.getEmployee(user.getEmployeeRef());
            this.createModifiedBy("employeeModifications", employee.getLastModification(), employee.getEmployeeRef());
        }
    }

    private void deleteUser(int employeeRef, String username) throws SQLException, RemoteException {
        if (this.userExists(username) && this.getUser(username).getEmployeeRef() == employeeRef) {
            String deleteSql = "delete from users where employeeRef=" + employeeRef + " and username='" + username + "'";
            try (Statement deleteStat = this.con.createStatement()) {
                if (deleteStat.executeUpdate(deleteSql) >= 1) {
                    users.remove(username);
                    deleteStat.close();
                }
            }
        }
    }

    public JobRoleBenefitInterface getJobRoleBenefit(int benRef) {
        if (this.jobRoleBenefitExists(benRef)) {
            return jobRoleBenefits.get(benRef);
        }
        return null;
    }

    public ContactInterface getContact(int contactRef) {
        if (this.contactExists(contactRef)) {
            return contacts.get(contactRef);
        }
        return null;
    }

    public AddressUsageInterface getAddressUsage(int addressUsageRef) {
        if (this.addressUsageExists(addressUsageRef)) {
            return this.addressUsages.get(addressUsageRef);
        }
        return null;
    }

    public User getUser(String username) {
        if (this.userExists(username)) {
            return this.users.get(username);
        }
        return null;
    }

    public User getUser(int employeeRef) throws RemoteException {
        for (User user : this.getUsers()) {
            if (user.getEmployeeRef() == employeeRef) {
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

    public boolean personInvPartyExists(int personRef) throws RemoteException {
        for (InvolvedPartyInterface invParties : involvedParties.values()) {
            if (invParties.getPersonRef() == personRef) {
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

    public boolean personLandlordExists(int personRef) throws RemoteException {
        for (LandlordInterface landlord : landlords.values()) {
            if (landlord.getPersonRef() == personRef) {
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

    public boolean personEmployeeExists(int personRef) throws RemoteException {
        for (EmployeeInterface employee : employees.values()) {
            if (employee.getPersonRef() == personRef) {
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

    public boolean isUser(String username, String password) throws RemoteException {
        if (this.userExists(username)) {
            return this.getUser(username).isUser(username, password);
        }
        return false;
    }

    public int countPeople() {
        String selectSql = "select max(personRef) as count from people";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countInvolvedParties() {
        String selectSql = "select max(invPartyRef) as count from involvedParties";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countLandords() {
        String selectSql = "select max(landlordRef) as count from landlords";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countEmployees() {
        String selectSql = "select max(employeeRef) as count from employees";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countApplications() {
        String selectSql = "select max(appRef) as count from applications";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countProperties() {
        String selectSql = "select max(propertyRef) as count from properties";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countTenancies() {
        String selectSql = "select max(tenancyRef) as count from tenancies";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countLeases() {
        String selectSql = "select max(leaseRef) as count from leases";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countContracts() {
        String selectSql = "select max(contractRef) as count from contracts";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countRentAccounts() {
        String selectSql = "select max(rentAccRef) as count from rentAccounts";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countLeaseAccounts() {
        String selectSql = "select max(leaseAccRef) as count from leaseAccounts";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countEmployeeAccounts() {
        String selectSql = "select max(employeeAccRef) as count from employeeAccounts";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countAddresses() {
        String selectSql = "select max(addressRef) as count from addresses";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countTransactions() throws RemoteException {
        int result = 0;
        List<TransactionInterface> transactionsList = this.getTransactions();
        if (transactionsList.isEmpty()) {
            for (TransactionInterface temp : transactionsList) {
                try {
                    if (temp.getTransactionRef() > result) {
                        result = temp.getTransactionRef();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int countAddressUsages() {
        int result = 0;
        List<AddressUsageInterface> addressUsagesList = this.getAddressUsages();
        if (addressUsagesList.isEmpty()) {
            for (AddressUsageInterface temp : addressUsagesList) {
                try {
                    if (temp.getAddressUsageRef() > result) {
                        result = temp.getAddressUsageRef();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int countContacts() {
        int result = 0;
        List<ContactInterface> contactList = this.getContacts();
        if (contactList.isEmpty()) {
            for (ContactInterface temp : contactList) {
                try {
                    if (temp.getContactRef() > result) {
                        result = temp.getContactRef();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int countNotes() {
        int result = 0;
        List<Note> notesList = this.getNotes();
        if (notesList.isEmpty()) {
            for (Note temp : notesList) {
                try {
                    if (temp.getReference() > result) {
                        result = temp.getReference();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int countDocuments() throws RemoteException {
        int result = 0;
        List<Document> documentsList = this.getDocuments();
        if (documentsList.isEmpty()) {
            for (Document temp : documentsList) {
                try {
                    if (temp.getDocumentRef() > result) {
                        result = temp.getDocumentRef();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int countPropElements() {
        String selectSql = "select max(propertyElementRef) as count from propertyElementValues";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countJobBenefits() {
        String selectSql = "select max(jobBenefitRef) as count from jobRoleBenefits";
        try (Statement statement = con.createStatement()) {
            ResultSet results = statement.executeQuery(selectSql);
            results.next();
            int checkSum = results.getInt(1);
            statement.close();
            return checkSum;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<OfficeInterface> getOffices() {
        return Collections.unmodifiableList(new ArrayList<>(offices.values()));
    }

    public List<AddressInterface> getAddresses() {
        return Collections.unmodifiableList(new ArrayList<>(addresses.values()));
    }

    public List<PersonInterface> getPeople() {
        return Collections.unmodifiableList(new ArrayList<>(people.values()));
    }

    public List<InvolvedPartyInterface> getInvolvedParties() {
        return Collections.unmodifiableList(new ArrayList<>(involvedParties.values()));
    }

    public List<LandlordInterface> getLandlords() {
        return Collections.unmodifiableList(new ArrayList<>(landlords.values()));
    }

    public List<EmployeeInterface> getEmployees() {
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(users.values()));
    }

    public List<ApplicationInterface> getApplications() {
        return Collections.unmodifiableList(new ArrayList<>(applications.values()));
    }

    public List<PropertyInterface> getProperties() {
        return Collections.unmodifiableList(new ArrayList<>(properties.values()));
    }

    public List<TenancyInterface> getTenancies() {
        return Collections.unmodifiableList(new ArrayList<>(tenancies.values()));
    }

    public List<LeaseInterface> getLeases() {
        return Collections.unmodifiableList(new ArrayList<>(leases.values()));
    }

    public List<ContractInterface> getContracts() {
        return Collections.unmodifiableList(new ArrayList<>(contracts.values()));
    }

    public List<RentAccountInterface> getRentAccounts() {
        return Collections.unmodifiableList(new ArrayList<>(rentAccounts.values()));
    }

    public List<LeaseAccountInterface> getLeaseAccounts() {
        return Collections.unmodifiableList(new ArrayList<>(leaseAccounts.values()));
    }

    public List<EmployeeAccountInterface> getEmployeeAccounts() {
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

    public List<JobRoleInterface> getJobRoles() {
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

    public List<TransactionInterface> getTransactions() {
        return Collections.unmodifiableList(new ArrayList<>(transactions.values()));
    }

    public List<JobRoleBenefitInterface> getJobRoleBenefits() {
        return Collections.unmodifiableList(new ArrayList<>(jobRoleBenefits.values()));
    }

    public List<ContactInterface> getContacts() {
        return Collections.unmodifiableList(new ArrayList<>(contacts.values()));
    }

    public List<AddressUsageInterface> getAddressUsages() {
        return Collections.unmodifiableList(new ArrayList<>(addressUsages.values()));
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(new ArrayList<>(notes.values()));
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(new ArrayList<>(documents.values()));
    }

    public List<PersonInterface> getPeople(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode,
            String maritalStatusCode, String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        List<PersonInterface> tempPeople = new ArrayList();
        for (PersonInterface temp : this.getPeople()) {
            boolean add = true;
            if (titleCode != null && !titleCode.isEmpty() && this.titleExists(titleCode) && !titleCode.equals(temp.getTitle().getCode())) {
                add = false;
            }
            if (forename != null && !forename.isEmpty() && !forename.equals(temp.getForename())) {
                add = false;
            }
            if (middleNames != null && !middleNames.isEmpty() && !middleNames.equals(temp.getMiddleNames())) {
                add = false;
            }
            if (surname != null && !surname.isEmpty() && !surname.equals(temp.getSurname())) {
                add = false;
            }
            if (dateOfBirth != null && dateOfBirth.compareTo(temp.getDateOfBirth()) != 0) {
                add = false;
            }
            if (nationalInsurance != null && !nationalInsurance.isEmpty() && !nationalInsurance.equals(temp.getNI())) {
                add = false;
            }
            if (maritalStatusCode != null && !maritalStatusCode.isEmpty() && this.maritalStatusExists(maritalStatusCode) && !maritalStatusCode.equals(temp.getMaritalStatus().getCode())) {
                add = false;
            }
            if (ethnicOriginCode != null && !ethnicOriginCode.isEmpty() && this.ethnicOriginExists(ethnicOriginCode) && !ethnicOriginCode.equals(temp.getEthnicOrigin().getCode())) {
                add = false;
            }
            if (languageCode != null && !languageCode.isEmpty() && this.languageExists(languageCode) && !languageCode.equals(temp.getLanguage().getCode())) {
                add = false;
            }
            if (nationalityCode != null && !nationalityCode.isEmpty() && this.nationalityExists(nationalityCode) && !nationalityCode.equals(temp.getNationality().getCode())) {
                add = false;
            }
            if (sexualityCode != null && !sexualityCode.isEmpty() && this.sexualityExists(sexualityCode) && !sexualityCode.equals(temp.getSexuality().getCode())) {
                add = false;
            }
            if (religionCode != null && !religionCode.isEmpty() && this.religionExists(religionCode) && !religionCode.equals(temp.getReligion().getCode())) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempPeople.add(temp);
            }
        }
        return tempPeople;
    }

    /////  NEED TO AMEND ALL BELOW SEARCH METHODS TO THE SAME AS ABOVE
    public List<AddressInterface> getAddresses(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) throws RemoteException {
        List<AddressInterface> tempAddresses = new ArrayList<>();
        for (AddressInterface temp : this.getAddresses()) {
            boolean add = true;
            if (buildingNumber != null && !buildingNumber.isEmpty() && !buildingNumber.equals(temp.getBuildingNumber())) {
                add = false;
            }
            if (buildingName != null && !buildingName.isEmpty() && !buildingName.equals(temp.getBuildingName())) {
                add = false;
            }
            if (subStreetNumber != null && !subStreetNumber.isEmpty() && !subStreetNumber.equals(temp.getSubStreetNumber())) {
                add = false;
            }
            if (subStreet != null && !subStreet.isEmpty() && !subStreet.equals(temp.getSubStreet())) {
                add = false;
            }
            if (streetNumber != null && !streetNumber.isEmpty() && !streetNumber.equals(temp.getStreetNumber())) {
                add = false;
            }
            if (street != null && !street.isEmpty() && !street.equals(temp.getStreet())) {
                add = false;
            }
            if (area != null && !area.isEmpty() && !area.equals(temp.getArea())) {
                add = false;
            }
            if (town != null && !town.isEmpty() && !town.equals(temp.getTown())) {
                add = false;
            }
            if (country != null && !country.isEmpty() && !country.equals(temp.getCountry())) {
                add = false;
            }
            if (postcode != null && !postcode.isEmpty() && !postcode.equals(temp.getPostcode()) && !postcode.equals(temp.getCountry())) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempAddresses.add(temp);
            }
        }
        return tempAddresses;
    }

    public List<ApplicationInterface> getApplications(String corrName, Date appStartDate, Date endDate, String statusCode,
            Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<ApplicationInterface> tempApplications = new ArrayList<>();
        for (ApplicationInterface temp : this.getApplications()) {
            boolean add = true;
            if (corrName != null && !corrName.isEmpty() && !corrName.equals(temp.getAppCorrName())) {
                add = false;
            }
            if (appStartDate != null && appStartDate.compareTo(temp.getAppStartDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getAppEndDate()) != 0) {
                add = false;
            }
            if (statusCode != null && !statusCode.isEmpty() && !statusCode.equals(temp.getAppStatusCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempApplications.add(temp);
            }
        }
        return tempApplications;
    }

    /**
     * returns a list of applications with an InvolvedParty that is associated
     * to any Person within the tempPeople list
     *
     * @param tempPeople
     * @return
     * @throws RemoteException
     */
    public List<ApplicationInterface> getPeopleApplications(List<PersonInterface> tempPeople) throws RemoteException {
        List<ApplicationInterface> tempApplications = new ArrayList<>();
        if (!tempPeople.isEmpty()) {
            for (ApplicationInterface temp : this.getApplications()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempPeople.size()) {
                    PersonInterface tempPerson = tempPeople.get(i);
                    if (temp.isPersonHouseholdMember(tempPerson.getPersonRef())) {
                        tempApplications.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
            return tempApplications;
        }
        return null;
    }

    public List<ApplicationInterface> getAddressApplications(List<AddressInterface> tempAddresses) throws RemoteException {
        List<ApplicationInterface> tempApplications = new ArrayList<>();
        if (!tempAddresses.isEmpty()) {
            for (ApplicationInterface temp : this.getApplications()) {
                boolean cont = true;
                List<AddressUsageInterface> appAddresses = temp.getApplicationAddressess();
                if (!appAddresses.isEmpty()) {
                    int i = 0;
                    while (cont && i < tempAddresses.size()) {
                        AddressInterface tempAddress = tempAddresses.get(i);
                        int ind = 0;
                        while (cont && ind < appAddresses.size()) {
                            AddressUsageInterface appAddress = appAddresses.get(ind);
                            if (tempAddress.getAddressRef() == appAddress.getAddress().getAddressRef()) {
                                tempApplications.add(temp);
                                cont = false;
                            }
                            ind++;
                        }
                        i++;
                    }
                }
            }
            return tempApplications;
        }
        return null;
    }

    public List<ApplicationInterface> getCorrNameApplcations(String name) throws RemoteException {
        List<ApplicationInterface> tempApplications = new ArrayList();
        if (name != null && !name.isEmpty()) {
            for (ApplicationInterface tempApp : this.getApplications()) {
                if (name.equals(tempApp.getAppCorrName())) {
                    tempApplications.add(tempApp);
                }
            }
            return tempApplications;
        }
        return null;
    }

    public ApplicationInterface getInvPartyApplcation(int invPartyRef) throws RemoteException {
        if (this.invPartyExists(invPartyRef)) {
            InvolvedPartyInterface invParty = this.getInvolvedParty(invPartyRef);
            if (this.applicationExists(invParty.getApplicationRef())) {
                return this.getApplication(invParty.getApplicationRef());
            }
        }
        return null;
    }

    public List<EmployeeInterface> getPeopleEmployees(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        List<PersonInterface> tempPeople = this.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        List<EmployeeInterface> tempEmployees = new ArrayList();
        if (!tempPeople.isEmpty()) {
            for (PersonInterface temp : tempPeople) {
                if (this.personEmployeeExists(temp.getPersonRef())) {
                    tempEmployees.add(this.getPersonEmployee(temp.getPersonRef()));
                }
            }
            return tempEmployees;
        }
        return null;
    }

    public List<LandlordInterface> getPeopleLandlords(String titleCode, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, String genderCode, String maritalStatusCode,
            String ethnicOriginCode, String languageCode, String nationalityCode, String sexualityCode, String religionCode, String createdBy, Date createdDate) throws RemoteException {
        List<PersonInterface> tempPeople = this.getPeople(titleCode, forename, middleNames, surname, dateOfBirth, nationalInsurance, genderCode, maritalStatusCode, ethnicOriginCode, languageCode, nationalityCode, sexualityCode, religionCode, createdBy, createdDate);
        List<LandlordInterface> tempLandlords = new ArrayList();
        if (!tempPeople.isEmpty()) {
            for (PersonInterface temp : tempPeople) {
                if (this.personEmployeeExists(temp.getPersonRef())) {
                    tempLandlords.add(this.getPersonLandlord(temp.getPersonRef()));
                }
            }
            return tempLandlords;
        }
        return null;
    }

    public List<PropertyInterface> getProperties(Date acquiredDate, Date leaseEndDate, String propTypeCode, String propSubTypeCode, String propStatus, String createdBy, Date createdDate) throws RemoteException {
        List<PropertyInterface> tempProperties = new ArrayList();
        for (PropertyInterface temp : this.getProperties()) {
            boolean add = true;
            if (acquiredDate != null && acquiredDate.compareTo(temp.getAcquiredDate()) != 0) {
                add = false;
            }
            if (leaseEndDate != null && leaseEndDate.compareTo(temp.getLeaseEndDate()) != 0) {
                add = false;
            }
            if (propTypeCode != null && this.propTypeExists(propTypeCode) && !propTypeCode.equals(temp.getPropType().getCode())) {
                add = false;
            }
            if (propSubTypeCode != null && this.propSubTypeExists(propSubTypeCode) && !propSubTypeCode.equals(temp.getPropSubType().getCode())) {
                add = false;
            }
            if (propStatus != null && propStatus.equals(temp.getPropStatus())) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempProperties.add(temp);
            }
        }
        return tempProperties;
    }

    public List<TenancyInterface> getTenancies(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef,
            Integer appRef, String tenTypeCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        for (TenancyInterface temp : this.getTenancies()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAgreementName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                add = false;
            }
            if (length != null && length != temp.getLength()) {
                add = false;
            }
            if (propRef != null && this.propertyExists(propRef) && propRef != temp.getProperty().getPropRef()) {
                add = false;
            }
            if (appRef != null && this.applicationExists(appRef) && appRef != temp.getApplication().getApplicationRef()) {
                add = false;
            }
            if (tenTypeCode != null && !tenTypeCode.isEmpty() && this.tenancyTypeExists(tenTypeCode) && !tenTypeCode.equals(temp.getTenType().getCode())) {
                add = false;
            }
            if (accountRef != null && this.rentAccountExists(accountRef) && accountRef != temp.getAccountRef()) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempTenancies.add(temp);
            }
        }
        return tempTenancies;
    }

    public List<TenancyInterface> getApplicationTenancies(List<ApplicationInterface> tempApplications) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        if (!tempApplications.isEmpty()) {
            for (TenancyInterface temp : this.getTenancies()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempApplications.size()) {
                    ApplicationInterface tempApp = tempApplications.get(i);
                    if (temp.getApplication().getApplicationRef() == tempApp.getApplicationRef()) {
                        tempTenancies.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
            return tempTenancies;
        }
        return null;
    }

    public List<TenancyInterface> getApplicationTenancies(int appRef) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        if (this.applicationExists(appRef)) {
            for (TenancyInterface temp : this.getTenancies()) {
                if (temp.getApplication().getApplicationRef() == appRef) {
                    tempTenancies.add(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }

    public List<TenancyInterface> getPropertyTenancies(List<PropertyInterface> tempProperties) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        if (!tempProperties.isEmpty()) {
            for (TenancyInterface temp : this.getTenancies()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempProperties.size()) {
                    PropertyInterface tempProperty = tempProperties.get(i);
                    if (temp.getProperty().getPropRef() == tempProperty.getPropRef()) {
                        tempTenancies.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
            return tempTenancies;
        }
        return null;
    }

    public List<TenancyInterface> getPropertyTenancies(int propRef) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        if (this.propertyExists(propRef)) {
            PropertyInterface tempProp = this.getProperty(propRef);
            for (TenancyInterface temp : this.getTenancies()) {
                if (temp.getProperty().getPropRef() == tempProp.getPropRef()) {
                    tempTenancies.add(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }

    public List<TenancyInterface> getNameTenancies(String name) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        for (TenancyInterface temp : this.getTenancies()) {
            if (name.equals(temp.getAgreementName())) {
                tempTenancies.add(temp);
            }
        }
        return tempTenancies;
    }

    public List<TenancyInterface> getOfficeTenancies(String office) throws RemoteException {
        List<TenancyInterface> tempTenancies = new ArrayList();
        if (this.officeExists(office)) {
            for (TenancyInterface temp : this.getTenancies()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempTenancies.add(temp);
                }
            }
            return tempTenancies;
        }
        return null;
    }

    public List<LeaseInterface> getLeases(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Boolean management, Double expenditure, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        for (LeaseInterface temp : this.getLeases()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAgreementName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                add = false;
            }
            if (length != null && length != temp.getLength()) {
                add = false;
            }
            if (propRef != null && this.propertyExists(propRef) && propRef != temp.getProperty().getPropRef()) {
                add = false;
            }
            if (management != null && management != temp.isFullManagement()) {
                add = false;
            }
            if (expenditure != null && expenditure != temp.getExpenditure()) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempLeases.add(temp);
            }
        }
        return tempLeases;
    }

    public List<LeaseInterface> getPropertyLeases(List<PropertyInterface> tempProperties) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        if (!tempProperties.isEmpty()) {
            for (LeaseInterface temp : this.getLeases()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempProperties.size()) {
                    PropertyInterface tempProperty = tempProperties.get(i);
                    if (temp.getProperty().getPropRef() == tempProperty.getPropRef()) {
                        tempLeases.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<LeaseInterface> getPropertyLeases(int propRef) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        if (this.propertyExists(propRef)) {
            PropertyInterface tempProperty = this.getProperty(propRef);
            for (LeaseInterface temp : this.getLeases()) {
                if (temp.getProperty().getPropRef() == tempProperty.getPropRef()) {
                    tempLeases.add(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<LeaseInterface> getNameLeases(String name) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        for (LeaseInterface temp : this.getLeases()) {
            if (name.equals(temp.getAgreementName())) {
                tempLeases.add(temp);
            }
        }
        return tempLeases;
    }

    public List<LeaseInterface> getOfficeLeases(String office) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        if (this.officeExists(office)) {
            for (LeaseInterface temp : this.getLeases()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempLeases.add(temp);
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<LeaseInterface> getLandlordLeases(int landlordRef) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        if (this.landlordExists(landlordRef)) {
            for (LeaseInterface temp : this.getLeases()) {
                List<LandlordInterface> tempLandlords = temp.getLandlords();
                boolean cont = true;
                int i = 0;
                while (cont && i < tempLandlords.size()) {
                    LandlordInterface tempLandlord = tempLandlords.get(i);
                    if (tempLandlord.getLandlordRef() == landlordRef) {
                        tempLeases.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<LeaseInterface> getLandlordLeases(List<LandlordInterface> tempLandlords) throws RemoteException {
        List<LeaseInterface> tempLeases = new ArrayList();
        if (!tempLandlords.isEmpty()) {
            for (LeaseInterface temp : this.getLeases()) {
                List<LandlordInterface> leaseLandlords = temp.getLandlords();
                boolean cont = true;
                int i = 0;
                int ind = 0;
                while (cont && i < leaseLandlords.size()) {
                    LandlordInterface tempLeaseLandlord = leaseLandlords.get(i);
                    while (cont && ind < tempLandlords.size()) {
                        LandlordInterface tempLandlord = tempLandlords.get(ind);
                        if (tempLandlord.getLandlordRef() == tempLeaseLandlord.getLandlordRef()) {
                            tempLeases.add(temp);
                            cont = false;
                        }
                        ind++;
                    }
                    i++;
                }
            }
            return tempLeases;
        }
        return null;
    }

    public List<ContractInterface> getContracts(String name, Date startDate, Date expectedEndDate, Date endDate, Integer length, Integer propRef, Integer employeeRef, String jobRoleCode, Integer accountRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<ContractInterface> tempContracts = new ArrayList();
        for (ContractInterface temp : this.getContracts()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAgreementName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (expectedEndDate != null && expectedEndDate.compareTo(temp.getExpectedEndDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getActualEndDate()) != 0) {
                add = false;
            }
            if (length != null && length != temp.getLength()) {
                add = false;
            }
            if (employeeRef != null && this.employeeExists(employeeRef) && employeeRef != temp.getEmployee().getEmployeeRef()) {
                add = false;
            }
            if (jobRoleCode != null && this.jobRoleExists(jobRoleCode) && !jobRoleCode.equals(temp.getJobRole().getJobRoleCode())) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempContracts.add(temp);
            }
        }
        return tempContracts;
    }

    public List<ContractInterface> getNameContracts(String name) throws RemoteException {
        List<ContractInterface> tempContracts = new ArrayList();
        for (ContractInterface temp : this.getContracts()) {
            if (name.equals(temp.getAgreementName())) {
                tempContracts.add(temp);
            }
        }
        return tempContracts;
    }

    public List<ContractInterface> getOfficeContracts(String office) throws RemoteException {
        List<ContractInterface> tempContracts = new ArrayList();
        if (this.officeExists(office)) {
            for (ContractInterface temp : this.getContracts()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempContracts.add(temp);
                }
            }
        }
        return tempContracts;
    }

    public List<ContractInterface> getEmployeeContracts(int ref) throws RemoteException {
        List<ContractInterface> tempContracts = new ArrayList();
        if (this.employeeExists(ref)) {
            for (ContractInterface temp : this.getContracts()) {
                if (temp.getEmployeeRef() == ref) {
                    tempContracts.add(temp);
                }
            }
            return tempContracts;
        }
        return null;
    }

    public List<ContractInterface> getJobRoleContracts(String code) throws RemoteException {
        List<ContractInterface> tempContracts = new ArrayList();
        if (this.jobRoleExists(code)) {
            for (ContractInterface temp : this.getContracts()) {
                if (code.equals(temp.getJobRole().getJobRoleCode())) {
                    tempContracts.add(temp);
                }
            }
            return tempContracts;
        }
        return null;
    }

    public List<RentAccountInterface> getRentAccounts(String name, Date startDate, Date endDate, Integer balance, Double rent, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<RentAccountInterface> tempRentAccounts = new ArrayList();
        for (RentAccountInterface temp : this.getRentAccounts()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAccName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                add = false;
            }
            if (balance != null && balance != temp.getBalance()) {
                add = false;
            }
            if (rent != null && rent != temp.getRent()) {
                add = false;
            }
            if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getTenancyRef()) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempRentAccounts.add(temp);
            }
        }
        return tempRentAccounts;
    }

    public List<RentAccountInterface> getNameRentAcc(String name) throws RemoteException {
        List<RentAccountInterface> tempRentAcc = new ArrayList();
        for (RentAccountInterface temp : this.getRentAccounts()) {
            if (name.equals(temp.getAccName())) {
                tempRentAcc.add(temp);
            }
        }
        return tempRentAcc;
    }

    public List<RentAccountInterface> getOfficeRentAcc(String office) throws RemoteException {
        List<RentAccountInterface> tempRentAcc = new ArrayList();
        if (this.officeExists(office)) {
            for (RentAccountInterface temp : this.getRentAccounts()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempRentAcc.add(temp);
                }
            }
        }
        return tempRentAcc;
    }

    public List<RentAccountInterface> getTenanciesRentAccounts(List<TenancyInterface> tempTenancies) throws RemoteException {
        List<RentAccountInterface> tempRentAccounts = new ArrayList();
        if (!tempTenancies.isEmpty()) {
            for (RentAccountInterface temp : this.getRentAccounts()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempTenancies.size()) {
                    TenancyInterface tempTenancy = tempTenancies.get(i);
                    if (tempTenancy.getAgreementRef() == temp.getTenancyRef()) {
                        tempRentAccounts.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
        }
        return tempRentAccounts;
    }

    public RentAccountInterface getTenancyRentAcc(int tenancyRef) throws RemoteException {
        if (this.tenancyExists(tenancyRef)) {
            TenancyInterface temp = this.getTenancy(tenancyRef);
            if (this.rentAccountExists(temp.getAccountRef())) {
                return this.getRentAccount(temp.getAccountRef());
            }
        }
        return null;
    }

    public List<LeaseAccountInterface> getLeaseAccounts(String name, Date startDate, Date endDate, Integer balance, Double expenditure, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<LeaseAccountInterface> tempLeaseAccounts = new ArrayList();
        for (LeaseAccountInterface temp : this.getLeaseAccounts()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAccName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                add = false;
            }
            if (balance != null && balance != temp.getBalance()) {
                add = false;
            }
            if (expenditure != null && expenditure != temp.getExpenditure()) {
                add = false;
            }
            if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getLeaseRef()) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempLeaseAccounts.add(temp);
            }
        }
        return tempLeaseAccounts;
    }

    public List<LeaseAccountInterface> getNameLeaseAcc(String name) throws RemoteException {
        List<LeaseAccountInterface> tempLeaseAcc = new ArrayList();
        for (LeaseAccountInterface temp : this.getLeaseAccounts()) {
            if (name.equals(temp.getAccName())) {
                tempLeaseAcc.add(temp);
            }
        }
        return tempLeaseAcc;
    }

    public List<LeaseAccountInterface> getOfficeLeaseAcc(String office) throws RemoteException {
        List<LeaseAccountInterface> tempLeaseAcc = new ArrayList();
        if (this.officeExists(office)) {
            for (LeaseAccountInterface temp : this.getLeaseAccounts()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempLeaseAcc.add(temp);
                }
            }
        }
        return tempLeaseAcc;
    }

    public List<LeaseAccountInterface> getLeasesLeaseAccounts(List<LeaseInterface> tempLeases) throws RemoteException {
        List<LeaseAccountInterface> tempLeaseAccounts = new ArrayList();
        if (!tempLeases.isEmpty()) {
            for (LeaseAccountInterface temp : this.getLeaseAccounts()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempLeases.size()) {
                    LeaseInterface tempLease = tempLeases.get(i);
                    if (tempLease.getAgreementRef() == temp.getLeaseRef()) {
                        tempLeaseAccounts.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
        }
        return tempLeaseAccounts;
    }

    public LeaseAccountInterface getLeaseLeaseAcc(int leaseRef) throws RemoteException {
        if (this.leaseExists(leaseRef)) {
            LeaseInterface temp = this.getLease(leaseRef);
            if (this.rentAccountExists(temp.getAccountRef())) {
                return this.getLeaseAccount(temp.getAccountRef());
            }
        }
        return null;
    }

    public List<EmployeeAccountInterface> getEmployeeAccounts(String name, Date startDate, Date endDate, Integer balance, Double salary, Integer agreementRef, String officeCode, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<EmployeeAccountInterface> tempEmployeeAccounts = new ArrayList();
        for (EmployeeAccountInterface temp : this.getEmployeeAccounts()) {
            boolean add = true;
            if (name != null && name.isEmpty() && !name.equals(temp.getAccName())) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            }
            if (endDate != null && endDate.compareTo(temp.getEndDate()) != 0) {
                add = false;
            }
            if (balance != null && balance != temp.getBalance()) {
                add = false;
            }
            if (salary != null && salary != temp.getSalary()) {
                add = false;
            }
            if (agreementRef != null && this.tenancyExists(agreementRef) && agreementRef != temp.getContractRef()) {
                add = false;
            }
            if (officeCode != null && !officeCode.isEmpty() && this.officeExists(officeCode) && !officeCode.equals(temp.getOfficeCode())) {
                add = false;
            }
            if (current != null && current != temp.isCurrent()) {
                add = false;
            }
            if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            }
            if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempEmployeeAccounts.add(temp);
            }
        }
        return tempEmployeeAccounts;
    }

    public List<EmployeeAccountInterface> getNameEmployeeAcc(String name) throws RemoteException {
        List<EmployeeAccountInterface> tempEmployeeAcc = new ArrayList();
        for (EmployeeAccountInterface temp : this.getEmployeeAccounts()) {
            if (name.equals(temp.getAccName())) {
                tempEmployeeAcc.add(temp);
            }
        }
        return tempEmployeeAcc;
    }

    public List<EmployeeAccountInterface> getOfficeEmployeeAcc(String office) throws RemoteException {
        List<EmployeeAccountInterface> tempEmployeeAcc = new ArrayList();
        if (this.officeExists(office)) {
            for (EmployeeAccountInterface temp : this.getEmployeeAccounts()) {
                if (office.equals(temp.getOfficeCode())) {
                    tempEmployeeAcc.add(temp);
                }
            }
        }
        return tempEmployeeAcc;
    }

    public List<EmployeeAccountInterface> getContractsEmployeeAccounts(List<ContractInterface> tempContracts) throws RemoteException {
        List<EmployeeAccountInterface> tempEmployeeAccounts = new ArrayList();
        if (!tempContracts.isEmpty()) {
            for (EmployeeAccountInterface temp : this.getEmployeeAccounts()) {
                boolean cont = true;
                int i = 0;
                while (cont && i < tempContracts.size()) {
                    ContractInterface tempContract = tempContracts.get(i);
                    if (tempContract.getAgreementRef() == temp.getContractRef()) {
                        tempEmployeeAccounts.add(temp);
                        cont = false;
                    }
                    i++;
                }
            }
        }
        return tempEmployeeAccounts;
    }

    public EmployeeAccountInterface getContractEmployeeAcc(int contractRef) throws RemoteException {
        if (this.contractExists(contractRef)) {
            ContractInterface temp = this.getContract(contractRef);
            if (this.employeeAccountExists(temp.getAccountRef())) {
                return this.getEmployeeAccount(temp.getAccountRef());
            }
        }
        return null;
    }

    public List<OfficeInterface> getOffices(Integer addrRef, Date startDate, Boolean current, String createdBy, Date createdDate) throws RemoteException {
        List<OfficeInterface> tempOffices = new ArrayList();
        for (OfficeInterface temp : this.getOffices()) {
            boolean add = true;
            if (addrRef != null && addrRef != temp.getAddress().getAddressRef()) {
                add = false;
            }
            if (startDate != null && startDate.compareTo(temp.getStartDate()) != 0) {
                add = false;
            } else if (current != null && current != temp.isCurrent()) {
                add = false;
            } else if (createdBy != null && !createdBy.isEmpty() && !createdBy.equals(temp.getCreatedBy())) {
                add = false;
            } else if (createdDate != null && createdDate.compareTo(temp.getCreatedDate()) != 0) {
                add = false;
            }
            if (add) {
                tempOffices.add(temp);
            }
        }
        return tempOffices;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_application;
import classes.DateConversion;
import interfaces.AddressUsageInterface;
import interfaces.ContactInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PersonInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dwayne
 */
public class Person extends UnicastRemoteObject implements PersonInterface {
    
    ///   VARIABLES   ///
    
    private final int personRef;
    private Element title;
    private String forename;
    private String middleNames;
    private String surname;
    private Date dateOfBirth;
    private String nationalInsurance;
    private Element gender;
    private Element maritalStatus;
    private Element ethnicOrigin;
    private Element language;
    private Element nationality;
    private Element sexuality;
    private Element religion;
    private final List<ContactInterface> contacts;
    private final List<AddressUsageInterface> addresses;
    private final List<Note> notes;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<Document> documents;

    /**
     * Constructor for objects of class Person
     * @param personRef
     * @param title
     * @param forename
     * @param middleNames
     * @param surname
     * @param dateOfBirth
     * @param nationalInsurance
     * @param gender
     * @param maritalStatus
     * @param ethnicOrigin
     * @param language
     * @param nationality
     * @param sexuality
     * @param religion
     * @param address
     * @param createdBy
     * @param createdDate
     * @throws java.rmi.RemoteException
     */
    public Person(int personRef, Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender, Element maritalStatus, Element ethnicOrigin,
            Element language, Element nationality, Element sexuality, Element religion, AddressUsageInterface address, String createdBy, Date createdDate) throws RemoteException {
        this.personRef = personRef;
        this.setTitle(title);
        this.setForename(forename);
        this.setMiddleNames(middleNames);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setNationalInsurance(nationalInsurance);
        this.setGender(gender);
        this.setMaritalStatus(maritalStatus);
        this.setEthnicOrigin(ethnicOrigin);
        this.setLanguage(language);
        this.setNationality(nationality);
        this.setSexuality(sexuality);
        this.setReligion(religion);
        this.contacts = new ArrayList();
        this.addresses = new ArrayList();
        if(address != null) {
            this.addresses.add(address);
        }
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.documents = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param title 
     */
    private void setTitle(Element title) {
        this.title = title;
    }
    
    /**
     * 
     * @param forename 
     */
    private void setForename(String forename) {
        this.forename = forename;
    }
    
    /**
     * 
     * @param middleNames 
     */
    private void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }
    
    /**
     * 
     * @param surname 
     */
    private void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * 
     * @param dateOfBirth 
     */
    private void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * 
     * @param nationalInsurance 
     */
    private void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }
    
    /**
     * 
     * @param gender 
     */
    private void setGender(Element gender) {
        this.gender = gender;
    }
    
    /**
     * 
     * @param maritalStatus 
     */
    private void setMaritalStatus(Element maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    /**
     * 
     * @param ethnicOrigin 
     */
    private void setEthnicOrigin(Element ethnicOrigin) {
        this.ethnicOrigin = ethnicOrigin;
    }
    
    /**
     * 
     * @param language 
     */
    private void setLanguage(Element language) {
        this.language = language;
    }
    
    /**
     * 
     * @param nationality 
     */
    private void setNationality(Element nationality) {
        this.nationality = nationality;
    }
    
    /**
     * 
     * @param sexuality 
     */
    private void setSexuality(Element sexuality) {
        this.sexuality = sexuality;
    }
    
    /**
     * 
     * @param religion 
     */
    private void setReligion(Element religion) {
        this.religion = religion;
    }
    
    /**
     * 
     * @param modifiedBy 
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * 
     * @param contact
     * @param modifiedBy 
     */
    public void createContact(ContactInterface contact, ModifiedByInterface modifiedBy) {
        this.contacts.add(contact);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     *
     * @param contact
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteContact(ContactInterface contact, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.contacts.isEmpty()) {
            if(contact != null && !contact.hasBeenModified() && this.contacts.contains(contact)) {
                this.contacts.remove(contact);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param address
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void createAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.addresses.isEmpty()) {
            for(AddressUsageInterface addressUsage : this.addresses) {
                if(addressUsage.isCurrent()) {
                    AddressUsage temp = (AddressUsage) addressUsage;
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        this.addresses.add(address);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     *
     * @param address
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.addresses.isEmpty()) {
            if(address != null && address.isCurrent() && !address.hasBeenModified() && this.addresses.contains(address)) {
                this.addresses.remove(address);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     *
     * @param note
     * @param modifiedBy
     */
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(!note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     *
     * @param document
     * @param modifiedBy
     * @throws RemoteException
     */
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param title
     * @param forename
     * @param middleNames
     * @param surname
     * @param dateOfBirth
     * @param nationalInsurance
     * @param gender
     * @param maritalStatus
     * @param ethnicOrigin
     * @param language
     * @param nationality
     * @param sexuality
     * @param religion
     * @param modifiedBy 
     */
    public void updatePerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, ModifiedByInterface modifiedBy) {
        this.setTitle(title);
        this.setForename(forename);
        this.setMiddleNames(middleNames);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setNationalInsurance(nationalInsurance);
        this.setGender(gender);
        this.setMaritalStatus(maritalStatus);
        this.setEthnicOrigin(ethnicOrigin);
        this.setLanguage(language);
        this.setNationality(nationality);
        this.setSexuality(sexuality);
        this.setReligion(religion);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return personRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPersonRef() throws RemoteException {
        return this.personRef;
    }
    
    /**
     * 
     * @return title
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getTitle() throws RemoteException {
        return this.title;
    }
    
    /**
     * 
     * @return forename
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getForename() throws RemoteException {
        return this.forename;
    }
    
    /**
     * 
     * @return middleNames
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getMiddleNames() throws RemoteException {
        return this.middleNames;
    }
    
    /**
     * 
     * @return surname
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getSurname() throws RemoteException {
        return this.surname;
    }
    
    /**
     * 
     * @return String representation of Person full name
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getName() throws RemoteException {
        String temp = "";
        if(this.title != null) {
            temp = this.title.getDescription();
        }
        if(!(this.forename == null || this.forename.isEmpty())) {
            temp = temp + " " + this.forename;
        }
        if(!(this.middleNames == null || this.middleNames.isEmpty())) {
            temp = temp + " " + this.middleNames;
        }
        if(!(this.surname == null || this.surname.isEmpty())) {
            temp = temp + " " + this.surname;
        }
        return temp;
    }
    
    /**
     * 
     * @return dateOfBirth
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getDateOfBirth() throws RemoteException {
        return this.dateOfBirth;
    }
    
    /**
     * 
     * @return nationalInsurance
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getNI() throws RemoteException {
        return this.nationalInsurance;
    }
    
    /**
     * 
     * @return gender
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getGender() throws RemoteException {
        return this.gender;
    }
    
    /**
     * 
     * @return true if number of years between dateOfBirth and TODAY >= 18
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isOver18() throws RemoteException {
        Calendar dob = DateConversion.dateToCalendar(this.dateOfBirth);
        Calendar now = Calendar.getInstance();
        int years = -1;
        while (!dob.after(now)) {
            dob.add(Calendar.YEAR, 1);
            years++;
        }
        return years >= 18;
    }
    
    /**
     * 
     * @return maritalStatus 
     * @throws java.rmi.RemoteException 
     */
    @Override
    public Element getMaritalStatus() throws RemoteException {
        return this.maritalStatus;
    }
    
    /**
     * 
     * @return ethnicOrigin
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getEthnicOrigin() throws RemoteException {
        return this.ethnicOrigin;
    }
    
    /**
     * 
     * @return language
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getLanguage() throws RemoteException {
        return this.language;
    }
    
    /**
     * 
     * @return nationality
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getNationality() throws RemoteException {
        return this.nationality;
    }
    
    /**
     * 
     * @return sexuality
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getSexuality() throws RemoteException {
        return this.sexuality;
    }
    
    /**
     * 
     * @return religion
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getReligion() throws RemoteException {
        return this.religion;
    }
    
    /**
     * @return current AddressUsage
     * @throws java.rmi.RemoteException
     */
    @Override
    public AddressUsageInterface getCurrentAddress() throws RemoteException {
        if(!this.addresses.isEmpty()) {
            return this.addresses.get(this.addresses.size()-1);
        }
        return null;
    }
    
    /**
     * @return String of current Address
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCurrentAddressString() throws RemoteException {
        if(!this.addresses.isEmpty()) {
            return this.addresses.get(this.addresses.size()-1).getAddressString();
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user who modified the Person
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date the last user modified the Person
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Person
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Person
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasNote(int ref) throws RemoteException {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getReference() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public Note getNote(int ref) throws RemoteException {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getReference() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
    /**
     * 
     * @param contactRef
     * @return true if contacts contains a Contact with ref == contactRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean hasContact(int contactRef) throws RemoteException {
        for(ContactInterface contact : contacts) {
            if(contact.getContactRef() == contactRef) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ContactInterface getContact(int contactRef) throws RemoteException {
        if (this.hasContact(contactRef)) {
            return contacts.get(contactRef);
        }
        return null;
    }
    
    /**
     * 
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ContactInterface> getContacts() throws RemoteException {
        return Collections.unmodifiableList(this.contacts);
    }
    
    /**
     * 
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<AddressUsageInterface> getAddresses() throws RemoteException {
        return Collections.unmodifiableList(this.addresses);
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasDocument(int ref) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param fileName
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasDocument(String fileName) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(fileName.equals(document.getCurrentDocumentName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public Document getDocument(int ref) throws RemoteException {
        if(this.hasDocument(ref)) {
            for (Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return document;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Document> getDocuments() throws RemoteException {
        return Collections.unmodifiableList(this.documents);
    }
    
    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    
    /**
     * 
     * @return String representation of the Person
     */
    @Override
    public String toString() {
        try {
            String temp = "\n\nPerson Ref: " + this.personRef + "\nName: " + this.title.getDescription() + " " + this.forename + " " + this.middleNames + " " + this.surname + "\nDOB: " + this.dateOfBirth
                    + "\nNI no: " + this.nationalInsurance + "\nGender: " + this.gender.getDescription() + "\nMaritalStatus: " + this.maritalStatus.getDescription()
                    + "\nEthnic Origin: " + this.ethnicOrigin.getDescription() + "\nLanguage: " + this.language.getDescription() + "\nNationality: " + this.nationality.getDescription()
                    + "\nSexuality: " + this.sexuality.getDescription() + "\nReligion: " + this.religion.getDescription();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
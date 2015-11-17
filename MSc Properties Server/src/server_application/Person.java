/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_application;
import interfaces.AddressUsageInterface;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;
import java.util.*;
/**
 *
 * @author Dwayne
 */
public class Person implements PersonInterface {
    
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
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;

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
     */
    public Person(int personRef, Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,Element maritalStatus, Element ethnicOrigin,
            Element language, Element nationality, Element sexuality, Element religion, AddressUsageInterface address, String createdBy, Date createdDate) {
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
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
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
     * @param contactType
     * @param contactValue
     * @param startDate
     * @param modifiedBy 
     */
    public void updateContact(ContactInterface contact, Element contactType, String contactValue, Date startDate, ModifiedByInterface modifiedBy) {
        if(this.contacts.isEmpty()) {
            if(this.contacts.contains(contact)) {
                Contact temp = (Contact) this.contacts.get(this.contacts.indexOf(contact));
                if(temp.isCurrent()) {
                    temp.updateContact(contactType, contactValue, startDate, modifiedBy);
                    this.modifiedBy(modifiedBy);
                }
            }
        }
    }
    
    /**
     * 
     * @param address
     * @param modifiedBy 
     */
    public void createAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) {
        if(!this.addresses.isEmpty()) {
            for(AddressUsageInterface addressUsage : this.addresses) {
                if(addressUsage.isCurrent()) {
                    AddressUsage temp = (AddressUsage) addressUsage;
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                    this.modifiedBy(modifiedBy);
                }
            }
        }
        this.addresses.add(address);
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
     */
    @Override
    public int getPersonRef() {
        return this.personRef;
    }
    
    /**
     * 
     * @return title
     */
    @Override
    public Element getTitle() {
        return this.title;
    }
    
    /**
     * 
     * @return forename
     */
    @Override
    public String getForename() {
        return this.forename;
    }
    
    /**
     * 
     * @return middleNames
     */
    @Override
    public String getMiddleNames() {
        return this.middleNames;
    }
    
    /**
     * 
     * @return surname
     */
    @Override
    public String getSurname() {
        return this.surname;
    }
    
    /**
     * 
     * @return String representation of Person full name
     */
    @Override
    public String getName() {
        String temp = this.title.getDescription();
        if(!this.forename.isEmpty()) {
            temp = temp + " " + this.forename;
        }
        if(!this.middleNames.isEmpty()) {
            temp = temp + " " + this.middleNames;
        }
        if(!this.surname.isEmpty()) {
            temp = temp + " " + this.surname;
        }
        return temp;
    }
    
    /**
     * 
     * @return dateOfBirth
     */
    @Override
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    /**
     * 
     * @return nationalInsurance
     */
    @Override
    public String getNI() {
        return this.nationalInsurance;
    }
    
    /**
     * 
     * @return gender
     */
    @Override
    public Element getGender() {
        return this.gender;
    }
    
    /**
     * 
     * @return true if number of years between dateOfBirth and TODAY >= 18
     */
    @Override
    public boolean isOver18() {
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
     */
    @Override
    public Element getMaritalStatus() {
        return this.maritalStatus;
    }
    
    /**
     * 
     * @return ethnicOrigin
     */
    @Override
    public Element getEthnicOrigin() {
        return this.ethnicOrigin;
    }
    
    /**
     * 
     * @return language
     */
    @Override
    public Element getLanguage() {
        return this.language;
    }
    
    /**
     * 
     * @return nationality
     */
    @Override
    public Element getNationality() {
        return this.nationality;
    }
    
    /**
     * 
     * @return sexuality
     */
    @Override
    public Element getSexuality() {
        return this.sexuality;
    }
    
    /**
     * 
     * @return religion
     */
    @Override
    public Element getReligion() {
        return this.religion;
    }
    
    /**
     * @return current AddressUsage
     */
    @Override
    public AddressUsageInterface getCurrenttAddress() {
        if(!this.addresses.isEmpty()) {
            return this.addresses.get(this.addresses.size()-1);
        }
        return null;
    }
    
    /**
     * @return String of current Address
     */
    @Override
    public String getCurrentAddressString() {
        if(!this.addresses.isEmpty()) {
            return this.addresses.get(this.addresses.size()-1).getAddressString();
        }
        return null;
    }
    
    /**
     * @return appAddresses
     */
    @Override
    public List<AddressUsageInterface> getApplicationAddressess() {
        return Collections.unmodifiableList((List<AddressUsageInterface>) this.addresses);
    }
    
    /**
     * 
     * @return the name of the last user who modified the Person
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date the last user modified the Person
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Person
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Person
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * 
     * @param contactRef
     * @return true if contacts contains a Contact with ref == contactRef
     */
    public boolean hasContact(int contactRef) {
        for(ContactInterface contact : contacts) {
            if(contact.getContactRef() == contactRef) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return contacts
     */
    @Override
    public List<ContactInterface> getContacts() {
        return Collections.unmodifiableList(this.contacts);
    }
    
    /**
     * 
     * @return contacts
     */
    @Override
    public List<AddressUsageInterface> getAddresses() {
        return Collections.unmodifiableList(this.addresses);
    }
    
    /**
     * 
     * @return contacts
     */
    @Override
    public AddressUsageInterface getLastAddress() {
        return addresses.get(addresses.size() - 1);
    }
    
    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    /**
     * 
     * @return String representation of the Person
     */
    @Override
    public String toString() {
        String temp = "\n\nPerson Ref: " + this.personRef + "\nName: " + this.title.getDescription() + " " + this.forename + " " + this.middleNames + " " + this.surname + "\nDOB: " + this.dateOfBirth
                + "\nNI no: " + this.nationalInsurance + "\nGender: " + this.gender.getDescription() + "\nMaritalStatus: " + this.maritalStatus.getDescription()
                + "\nEthnic Origin: " + this.ethnicOrigin.getDescription() + "\nLanguage: " + this.language.getDescription() + "\nNationality: " + this.nationality.getDescription()
                + "\nSexuality: " + this.sexuality.getDescription() + "\nReligion: " + this.religion.getDescription();
        return temp;
    }
}
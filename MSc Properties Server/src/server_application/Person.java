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
    private final ArrayList<ContactInterface> contacts;
    private final ArrayList<AddressUsageInterface> addresses;
    private final String createdBy;
    private final Date createdDate;
    private final ArrayList<ModifiedBy> modifiedBy;

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
     * @param createdBy
     */
    public Person(int personRef, Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, ArrayList<ContactInterface> contacts, AddressUsageInterface address, String createdBy) {
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
        this.contacts = contacts;
        this.addresses = new ArrayList();
        this.addresses.add(address);
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setTitle(Element title) {
        this.title = title;
    }
    
    private void setForename(String forename) {
        this.forename = forename;
    }
    
    private void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }
    
    private void setSurname(String surname) {
        this.surname = surname;
    }
    
    private void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    private void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }
    
    private void setGender(Element gender) {
        this.gender = gender;
    }
    
    private void setMaritalStatus(Element maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    private void setEthnicOrigin(Element ethnicOrigin) {
        this.ethnicOrigin = ethnicOrigin;
    }
    
    private void setLanguage(Element language) {
        this.language = language;
    }
    
    private void setNationality(Element nationality) {
        this.nationality = nationality;
    }
    
    private void setSexuality(Element sexuality) {
        this.sexuality = sexuality;
    }
    
    private void setReligion(Element religion) {
        this.religion = religion;
    }
    
    @Override
    public void createContact(ContactInterface contact) {
        contacts.add(contact);
    }
    
    @Override
    public void createAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) {
        if(!addresses.isEmpty()) {
            for(AddressUsageInterface temp : addresses) {
                if(temp.isCurrent()) {
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        addresses.add(address);
    }
    
    @Override
    public void setPerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion) {
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
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public int getPersonRef() {
        return this.personRef;
    }
    
    @Override
    public String getForename() {
        return this.forename;
    }
    
    @Override
    public String getMiddleNames() {
        return this.middleNames;
    }
    
    @Override
    public String getSurname() {
        return this.surname;
    }
    
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
    
    @Override
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    @Override
    public String getNI() {
        return this.nationalInsurance;
    }
    
    @Override
    public Element getGender() {
        return this.gender;
    }
    
    @Override
    public boolean isOver18() {
        Calendar dob = DateConversion.dateToCalendar(dateOfBirth);
        Calendar now = Calendar.getInstance();
        int years = -1;
        while (!dob.after(now)) {
            dob.add(Calendar.YEAR, 1);
            years++;
        }
        return years >= 18;
    }
    
    @Override
    public Element getMaritalStatus() {
        return this.maritalStatus;
    }
    
    @Override
    public Element getEthnicOrigin() {
        return this.ethnicOrigin;
    }
    
    @Override
    public Element getLanguage() {
        return this.language;
    }
    
    @Override
    public Element getNationality() {
        return this.nationality;
    }
    
    @Override
    public Element getSexuality() {
        return this.sexuality;
    }
    
    @Override
    public Element getReligion() {
        return this.religion;
    }
    
    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "\n\nPerson Ref: " + this.personRef + "\nName: " + this.title.getDescription() + " " + this.forename + " " + this.middleNames + " " + this.surname + "\nDOB: " + this.dateOfBirth
                + "\nNI no: " + this.nationalInsurance + "\nGender: " + this.gender.getDescription() + "\nMaritalStatus: " + this.maritalStatus.getDescription()
                + "\nEthnic Origin: " + this.ethnicOrigin.getDescription() + "\nLanguage: " + this.language.getDescription() + "\nNationality: " + this.nationality.getDescription()
                + "\nSexuality: " + this.sexuality.getDescription() + "\nReligion: " + this.religion.getDescription();
        return temp;
    }
}
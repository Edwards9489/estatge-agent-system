/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_application;
import interfaces.AddressUsageInterface;
import interfaces.ContactInterface;
import interfaces.Element;
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
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, String createdBy) {
        this.personRef = personRef;
        this.setPerson(title, forename, middleNames, surname, dateOfBirth, nationalInsurance,
                gender, maritalStatus, ethnicOrigin, language, nationality, sexuality, religion);
        this.contacts = new ArrayList();
        this.addresses = new ArrayList();
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
    
    private void setNI(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
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
    public void createAddress(AddressUsageInterface address) {
        if(!addresses.isEmpty()) {
            for(AddressUsageInterface temp : addresses) {
                if(temp.isCurrent()) {
                    temp.setEndDate(address.getStartDate());
                }
            }
        }
        addresses.add(address);
    }
    
    @Override
    public void setPerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion) {
        setTitle(title);
        setForename(forename);
        setMiddleNames(middleNames);
        setSurname(surname);
        setDateOfBirth(dateOfBirth);
        setNationalInsurance(nationalInsurance);
        setGender(gender);
        setMaritalStatus(maritalStatus);
        setEthnicOrigin(ethnicOrigin);
        setLanguage(language);
        setNationality(nationality);
        setSexuality(sexuality);
        setReligion(religion);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public int getPersonRef() {
        return personRef;
    }
    
    @Override
    public String getForename() {
        return forename;
    }
    
    @Override
    public String getMiddleNames() {
        return middleNames;
    }
    
    @Override
    public String getSurname() {
        return surname;
    }
    
    @Override
    public String getName() {
        String temp = title.getDescription();
        if(!forename.isEmpty()) {
            temp = temp + " " + forename;
        }
        if(!middleNames.isEmpty()) {
            temp = temp + " " + middleNames;
        }
        if(!surname.isEmpty()) {
            temp = temp + " " + surname;
        }
        return temp;
    }
    
    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    @Override
    public String getNI() {
        return nationalInsurance;
    }
    
    @Override
    public Element getGender() {
        return gender;
    }
    
    @Override
    public Element getMaritalStatus() {
        return maritalStatus;
    }
    
    @Override
    public Element getEthnicOrigin() {
        return ethnicOrigin;
    }
    
    @Override
    public Element getLanguage() {
        return language;
    }
    
    @Override
    public Element getNationality() {
        return nationality;
    }
    
    @Override
    public Element getSexuality() {
        return sexuality;
    }
    
    @Override
    public Element getReligion() {
        return religion;
    }
    
    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "#" + personRef + " : " + title.getDescription() + " " + forename + " " + middleNames + " " + surname + "\nDOB: " + dateOfBirth
                + "\nNI no: " + nationalInsurance + "\nGender: " + gender.getDescription() + "\nMaritalStatus: " + maritalStatus.getDescription()
                + "\nEthnic Origin: " + ethnicOrigin.getDescription() + "\nLanguage: " + language.getDescription() + "\nNationality: " + nationality.getDescription()
                + "\nSexuality: " + sexuality.getDescription() + "\nReligion: " + religion.getDescription();
        return temp;
    }
}
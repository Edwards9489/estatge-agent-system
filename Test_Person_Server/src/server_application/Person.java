/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_application;
import java.util.*;
/**
 *
 * @author Dwayne
 */
public class Person {
    // instance variables - replace the example below with your own
    private final int personRef;
    private Title title;
    private String forename;
    private String middleNames;
    private String surname;
    private Date dateOfBirth;
    private String nationalInsurance;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private EthnicOrigin ethnicOrigin;
    private Language language;
    private Nationality nationality;
    private Sexuality sexuality;
    private Religion religion;
    private ArrayList<Contact> contacts = new ArrayList();
    private ArrayList<AddressUsage> addresses;

    /**
     * Constructor for objects of class Person
     */
    public Person(int personRef, Title title, String forename, String surname, Date dateOfBirth, Gender gender)
    {
        // initialise instance variables
        this.personRef = personRef;
        this.title = title;
        this.forename = forename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
    
    public int getPersonRef() {
        return personRef;
    }
    
    public String getForename() {
        return forename;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public String getNI() {
        return nationalInsurance;
    }
    
    public ElementImpl getMaritalStatus() {
        return maritalStatus;
    }
    
    public ElementImpl getEthnicOrigin() {
        return ethnicOrigin;
    }
    
    public ElementImpl getLanguage() {
        return language;
    }
    
    public ElementImpl getNationality() {
        return nationality;
    }
    
    public ElementImpl getSexuality() {
        return sexuality;
    }
    
    public ElementImpl getReligion() {
        return religion;
    }
    
    public void updateTitle(Title title) {
        this.title = title;
    }
    
    public void updateForename(String forename) {
        this.forename = forename;
    }
    
    public void updateSurname(String surname) {
        this.surname = surname;
    }
    
    public void updateGender(Gender gender) {
        this.gender = gender;
    }
    
    public void updateNI(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }
    
    public void updateMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    public void updateEthnicOrigin(EthnicOrigin ethnicOrigin) {
        this.ethnicOrigin = ethnicOrigin;
    }
    
    public void updateLanguage(Language language) {
        this.language = language;
    }
    
    public void updateNationality(Nationality nationality) {
        this.nationality = nationality;
    }
    
    public void updateSexuality(Sexuality sexuality) {
        this.sexuality = sexuality;
    }
    
    public void updateReligion(Religion religion) {
        this.religion = religion;
    }
    
    public void createContact(ContactType type, String value, Date date, String createdBy) {
        Contact c = new Contact(type, value, date, createdBy);
        contacts.add(c);
    }
    
    
}
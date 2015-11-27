/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AgreementInterface;
import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Agreement implements AgreementInterface {
    
    ///   VARIABLES   ///
    
    private final int agreementRef;
    private String agreementName;
    private Date startDate;
    private Date expectedEndDate;
    private Date actualEndDate;
    private int length;
    private final int accountRef;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<Note> notes;
    private final String officeCode;
    private final List<Document> documents;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Agreement
     * @param agreementRef
     * @param agreementName
     * @param startDate
     * @param length
     * @param accountRef
     * @param createdBy
     * @param createdDate
     * @param officeCode 
     */
    public Agreement(int agreementRef, String agreementName, Date startDate, int length, int accountRef, String createdBy, Date createdDate, String officeCode) {
        this.agreementRef = agreementRef;
        this.agreementName = agreementName;
        this.startDate = startDate;
        this.length = length;
        this.setExpectedEndDate(this.calculateExpectedEndDate());
        this.accountRef = accountRef;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = new ArrayList();
        this.notes = new ArrayList();
        this.officeCode = officeCode;
        this.documents = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param name
     */
    private void setAgreementName(String name) {
        this.agreementName = name;
    }
    
    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param expectedEndDate
     */
    private void setExpectedEndDate(Date expectedEndDate) {
        if(expectedEndDate.after(this.startDate)) {
            this.expectedEndDate = expectedEndDate;
        }
    }

    /**
     * @param length
     */
    private void setLength(int length) {
        if(length > 0) {
            this.length = length;
            this.setExpectedEndDate(calculateExpectedEndDate());
        }
    }
    
    /**
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param actualEndDate
     * @param modifiedBy
     */
    public void setActualEndDate(Date actualEndDate, ModifiedByInterface modifiedBy) {
        if(actualEndDate == null || actualEndDate.after(this.startDate)) {
            this.actualEndDate = actualEndDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param name
     * @param startDate
     * @param length
     * @param modifiedBy
     */
    public void updateAgreement(String name, Date startDate, int length, ModifiedByInterface modifiedBy) {
        if(this.isCurrent()) {
            this.setAgreementName(name);
            this.setStartDate(startDate);
            this.setLength(length);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createDocument(Document document, ModifiedByInterface modifiedBy) {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return startDate + length (in months)
     */
    private Date calculateExpectedEndDate() {
        Calendar cal = DateConversion.dateToCalendar(this.startDate);
        cal.add(Calendar.MONTH, this.length);
        return cal.getTime();
    }
    
    /**
     * @return actualEndDate == null
     */
    private boolean isEndDateNull() {
        return this.actualEndDate == null;
    }

    /**
     * @return agreementRef
     */
    @Override
    public int getAgreementRef() {
        return agreementRef;
    }
    
    /**
     * @return agreementName
     */
    @Override
    public String getAgreementName() {
        return agreementName;
    }

    /**
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return expectedEndDate
     */
    @Override
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    /**
     * @return actualEndDate
     */
    @Override
    public Date getActualEndDate() {
        return actualEndDate;
    }

    /**
     * @return length
     */
    @Override
    public int getLength() {
        return length;
    }
    
    /**
     * @return accountRef
     */
    @Override
    public int getAccountRef() {
        return this.accountRef;
    }
    
    /**
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }
    
    /**
     * @return true if actualEndDate == null || (actualEndDate != null) && actualEndDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(this.isEndDateNull()) {
            return true;
        }
        else {
            return actualEndDate.after(new Date());
        }
    }
    
    @Override
    public boolean hasNote(int ref) {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Agreement
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the date the of the last modification to the Agreement
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy objects for the Agreement
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Agreement
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public Note getNote(int ref) {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getRef() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() {
        return Collections.unmodifiableList(this.notes);
    }
    
    @Override
    public boolean hasDocument(int ref) {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean hasDocument(String fileName) {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(fileName.equals(document.getDocumentName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Document getDocument(int ref) {
        if(this.hasDocument(ref)) {
            for (Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return document;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Document> getDocuments() {
        return Collections.unmodifiableList(this.documents);
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @return String representation of the Agreement
     */
    @Override
    public String toString() {
        String temp = "\n\nAgreement Ref: " + this.getAgreementRef() + "\nAgreement Name: " + this.getAgreementName() +
                "\nOffice Code: " + this.getOfficeCode() + "\nAgreement Length: " + this.getLength() + "\nStart Date: " +
                this.getStartDate() + "\nExpected End Date: " + this.getExpectedEndDate() + "\nActual End Date: " +
                this.getActualEndDate() + "\nIs Current: " + this.isCurrent() + "\nCreated By: " + this.getCreatedBy() +
                "\nCreated Date: " + this.getCreatedDate() + "\nLast Modified By:" + this.getLastModifiedBy() +
                "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}
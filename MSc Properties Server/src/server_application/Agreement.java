/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.DateConversion;
import interfaces.AgreementInterface;
import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Agreement extends UnicastRemoteObject implements AgreementInterface {
    
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
     * @throws java.rmi.RemoteException 
     */
    public Agreement(int agreementRef, String agreementName, Date startDate, int length, int accountRef, String createdBy, Date createdDate, String officeCode) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void updateAgreement(String name, Date startDate, int length, ModifiedByInterface modifiedBy) throws RemoteException {
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
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAgreementRef() throws RemoteException {
        return agreementRef;
    }
    
    /**
     * @return agreementName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getAgreementName() throws RemoteException {
        return agreementName;
    }

    /**
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return startDate;
    }

    /**
     * @return expectedEndDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getExpectedEndDate() throws RemoteException {
        return expectedEndDate;
    }

    /**
     * @return actualEndDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getActualEndDate() throws RemoteException {
        return actualEndDate;
    }

    /**
     * @return length
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getLength() throws RemoteException {
        return length;
    }
    
    /**
     * @return accountRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAccountRef() throws RemoteException {
        return this.accountRef;
    }
    
    /**
     * @return officeCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getOfficeCode() throws RemoteException {
        return officeCode;
    }
    
    /**
     * @return true if actualEndDate == null || (actualEndDate != null) && actualEndDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(this.isEndDateNull()) {
            return true;
        }
        else {
            return actualEndDate.after(new Date());
        }
    }
    
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
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Agreement
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
     * @return the date the of the last modification to the Agreement
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
     * @return the list of modifiedBy objects for the Agreement
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Agreement
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
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
    
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
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
    
    @Override
    public boolean hasDocument(String fileName) throws RemoteException {
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
        return createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
    
    /**
     * @return String representation of the Agreement
     */
    @Override
    public String toString() {
        try {
            String temp = "\n\nAgreement Ref: " + this.getAgreementRef() + "\nAgreement Name: " + this.getAgreementName() +
                    "\nOffice Code: " + this.getOfficeCode() + "\nAgreement Length: " + this.getLength() + "\nStart Date: " +
                    this.getStartDate() + "\nExpected End Date: " + this.getExpectedEndDate() + "\nActual End Date: " +
                    this.getActualEndDate() + "\nIs Current: " + this.isCurrent() + "\nCreated By: " + this.getCreatedBy() +
                    "\nCreated Date: " + this.getCreatedDate() + "\nLast Modified By:" + this.getLastModifiedBy() +
                    "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Agreement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
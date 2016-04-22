/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PersonInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class InvolvedParty extends UnicastRemoteObject implements InvolvedPartyInterface {
    
    ///   VARIABLES   ///
        
    private final int involvedPartyRef;
    private final int appRef;
    private final PersonInterface person;
    private boolean jointApplicantInd;
    private boolean mainApplicantInd;
    private Date startDate; // start date of the involved party against the application
    private Date endDate; // end date of the involved party against the application
    private Element endReason; // Indicates the reason the involved party was ended against the application
    private Element relationship; // Indicates the relationship of this involved party to the main applicant
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class InvolvedParty
     * @param invPartyRef
     * @param appRef
     * @param person
     * @param joint
     * @param main
     * @param start
     * @param relationship
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public InvolvedParty(int invPartyRef, int appRef, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, String createdBy, Date createdDate) throws RemoteException {
        this.involvedPartyRef = invPartyRef;
        this.appRef = appRef;
        this.person = person;
        this.mainApplicantInd = main;
        this.jointApplicantInd = joint;
        this.startDate = start;
        this.relationship = relationship;
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param start
     */
    private void setStartDate(Date start) {
        this.startDate = start;
    }
    
    /**
     * 
     * @param relationship
     */
    private void setRelationship(Element relationship) {
        this.relationship = relationship;
    }
    
    /**
     * 
     * @param end
     */
    private void setEndDate(Date end) {
        System.out.println("SET END" + end.after(this.startDate));
        if(end.after(this.startDate)) {
            System.out.println("IN IF STATEMENT");
            this.endDate = end;
        }
    }
    
    /**
     * 
     * @param endReason
     */
    private void setEndReason(Element endReason) {
        this.endReason = endReason;
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
     * @param joint
     */
    private void setJointInd(boolean joint) {
        this.jointApplicantInd = joint;
    }
    
    /**
     * 
     * @param modifiedBy
     */
    public void setMainInd(ModifiedByInterface modifiedBy) {
        this.mainApplicantInd = !this.mainApplicantInd;
        this.modifiedBy(modifiedBy);
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
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
     * @param end
     * @param endReason
     * @param modifiedBy 
     */
    public void endInvolvedParty(Date end, Element endReason, ModifiedByInterface modifiedBy) {
        System.out.println(end.after(this.startDate));
        if(end.after(this.startDate)) {
            this.setEndDate(end);
            this.setEndReason(endReason);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param joint
     * @param start
     * @param relationship
     * @param modifiedBy 
     */
    public void updateInvolvedParty(boolean joint, Date start, Element relationship, ModifiedByInterface modifiedBy) {
        this.setJointInd(joint);
        this.setStartDate(start);
        this.setRelationship(relationship);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return involvedPartyRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getInvolvedPartyRef() throws RemoteException {
        return this.involvedPartyRef;
    }
    
    /**
     * 
     * @return appRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getApplicationRef() throws RemoteException {
        return this.appRef;
    }
    
    /**
     * 
     * @return ref of person
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPersonRef() throws RemoteException {
        return this.getPerson().getPersonRef();
    }
    
    /**
     * 
     * @return person
     * @throws java.rmi.RemoteException
     */
    @Override
    public PersonInterface getPerson() throws RemoteException {
        return this.person;
    }
    
    /**
     * 
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return this.startDate;
    }
    
    /**
     * 
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return this.endDate;
    }
    
    /**
     * 
     * @return endReason
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getEndReason() throws RemoteException {
        return this.endReason;
    }
    
    /**
     * 
     * @return relationship
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getRelationship() throws RemoteException {
        return this.relationship;
    }
    
    /**
     * 
     * @return jointApplicantInd
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isJointInd() throws RemoteException {
        return this.jointApplicantInd;
    }
    
    /**
     * 
     * @return mainApplicantInd
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isMainInd() throws RemoteException {
        return this.mainApplicantInd;
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(this.endDate == null) {
            return true;
        }
        else {
            return this.endDate.after(new Date());
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
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return true if person.isOver18() == true
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isOver18() throws RemoteException {
        return this.person.isOver18();
    }
    
    /**
     * 
     * @return the name of the last user that modified the InvolvedParty
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
     * @return the last date the InvolvedParty was modified
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
     * @return the list of modifiedBy objects for the InvolvedParty
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the InvolvedParty
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
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }
    
    /**
     * 
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    /**
     * 
     * @return String representation of the InvolvedParty
     */
    @Override
    public String toString() {
        try {
            String temp = "\n\nInvolved Party Ref: " + this.getInvolvedPartyRef() + "\nPerson" + this.getPerson() +
                    "\nMain Applicant: " + this.isMainInd() + "\nJoint Applicant: " + this.isJointInd() +
                    "\nRelationship: " + this.getRelationship().getDescription() + "\nStart Date: " + this.getStartDate() +
                    "\nEnd Date: " + this.getEndDate() + "\nEnd Reason: " + this.getEndReason();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(InvolvedParty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
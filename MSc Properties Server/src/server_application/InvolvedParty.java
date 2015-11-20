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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class InvolvedParty implements InvolvedPartyInterface {
    
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
     */
    public InvolvedParty(int invPartyRef, int appRef, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, String createdBy, Date createdDate) {
        this.involvedPartyRef = invPartyRef;
        this.appRef = appRef;
        this.person = person;
        if(!person.isOver18()) {
            main = false;
            joint = false;
        }
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
        if(end.after(this.startDate)) {
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
    public void setJointInd(boolean joint) {
        this.jointApplicantInd = joint;
    }
    
    /**
     * 
     */
    public void setMainInd() {
        this.mainApplicantInd = !this.mainApplicantInd;
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
    
    /**
     * 
     * @param end
     * @param endReason
     * @param modifiedBy 
     */
    public void endInvolvedParty(Date end, Element endReason, ModifiedByInterface modifiedBy) {
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
     * @param relationhip
     * @param modifiedBy 
     */
    public void updateInvolvedParty(boolean joint, Date start, Element relationhip, ModifiedByInterface modifiedBy) {
        this.setJointInd(joint);
        this.setStartDate(start);
        this.setRelationship(relationship);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return involvedPartyRef
     */
    @Override
    public int getInvolvedPartyRef() {
        return this.involvedPartyRef;
    }
    
    /**
     * 
     * @return appRef
     */
    @Override
    public int getApplicationRef() {
        return this.appRef;
    }
    
    /**
     * 
     * @return ref of person
     */
    @Override
    public int getPersonRef() {
        return this.getPerson().getPersonRef();
    }
    
    /**
     * 
     * @return person
     */
    @Override
    public PersonInterface getPerson() {
        return this.person;
    }
    
    /**
     * 
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return this.startDate;
    }
    
    /**
     * 
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return this.endDate;
    }
    
    /**
     * 
     * @return endReason
     */
    @Override
    public Element getEndReason() {
        return this.endReason;
    }
    
    /**
     * 
     * @return relationship
     */
    @Override
    public Element getRelationship() {
        return this.relationship;
    }
    
    /**
     * 
     * @return jointApplicantInd
     */
    @Override
    public boolean isJointInd() {
        return this.jointApplicantInd;
    }
    
    /**
     * 
     * @return mainApplicantInd
     */
    @Override
    public boolean isMainInd() {
        return this.mainApplicantInd;
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(this.endDate == null) {
            return true;
        }
        else {
            return this.endDate.after(new Date());
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
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return true if person.isOver18() == true
     */
    @Override
    public boolean isOver18() {
        return this.person.isOver18();
    }
    
    /**
     * 
     * @return the name of the last user that modified the InvolvedParty
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
     * @return the last date the InvolvedParty was modified
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
     * @return the list of modifiedBy objects for the InvolvedParty
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the InvolvedParty
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
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * 
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    /**
     * 
     * @return String representation of the InvolvedParty
     */
    @Override
    public String toString() {
        String temp = "\n\nInvolved Party Ref: " + this.getInvolvedPartyRef() + "\nPerson" + this.getPerson() +
                "\nMain Applicant: " + this.isMainInd() + "\nJoint Applicant: " + this.isJointInd() +
                "\nRelationship: " + this.getRelationship().getDescription() + "\nStart Date: " + this.getStartDate() +
                "\nEnd Date: " + this.getEndDate() + "\nEnd Reason: " + this.getEndReason();
        return temp;
    }
}
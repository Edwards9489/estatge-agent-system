/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
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
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
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
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setStartDate(Date start) {
        this.startDate = start;
    }
    
    private void setRelationship(Element relationship) {
        this.relationship = relationship;
    }
    
    private void setEndDate(Date end) {
        if(end.after(this.startDate)) {
            this.endDate = end;
        }
    }
    
    private void setEndReason(Element endReason) {
        this.endReason = endReason;
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    public void setJointInd(boolean joint) {
        this.jointApplicantInd = joint;
    }
    
    public void setMainInd() {
        this.mainApplicantInd = !this.mainApplicantInd;
    }
    
    @Override
    public void endInvolvedParty(Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(end.after(this.startDate)) {
            this.setEndDate(end);
            this.setEndReason(endReason);
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void updateInvolvedParty(boolean joint, Date start, Element relationhip, ModifiedByInterface modifiedBy) {
        this.setJointInd(joint);
        this.setStartDate(start);
        this.setRelationship(relationship);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public int getInvolvedPartyRef() {
        return this.involvedPartyRef;
    }
    
    @Override
    public int getApplicationRef() {
        return this.appRef;
    }
    
    @Override
    public int getPersonRef() {
        return this.getPerson().getPersonRef();
    }
    
    @Override
    public PersonInterface getPerson() {
        return this.person;
    }
    
    @Override
    public Date getStartDate() {
        return this.startDate;
    }
    
    @Override
    public Date getEndDate() {
        return this.endDate;
    }
    
    @Override
    public Element getEndReason() {
        return this.endReason;
    }
    
    @Override
    public Element getRelationship() {
        return this.relationship;
    }
    
    @Override
    public boolean isJointInd() {
        return this.jointApplicantInd;
    }
    
    @Override
    public boolean isMainInd() {
        return this.mainApplicantInd;
    }
    
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
    public boolean isOver18() {
        return this.person.isOver18();
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public String createdBy() {
        return this.createdBy;
    }
    
    @Override
    public Date createdDate() {
        return this.createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "\n\nInvolved Party Ref: " + this.getInvolvedPartyRef() + "\nPerson" + this.getPerson() +
                "\nMain Applicant: " + this.isMainInd() + "\nJoint Applicant: " + this.isJointInd() +
                "\nRelationship: " + this.getRelationship().getDescription() + "\nStart Date: " + this.getStartDate() +
                "\nEnd Date: " + this.getEndDate() + "\nEnd Reason: " + this.getEndReason();
        return temp;
    }
}
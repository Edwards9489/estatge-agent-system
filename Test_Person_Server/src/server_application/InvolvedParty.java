/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.PersonInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class InvolvedParty implements InvolvedPartyInterface {
    
    private final int involvedPartyRef;
    private final PersonInterface person;
    private boolean jointApplicantInd;
    private boolean mainApplicantInd;
    private Date startDate; // start date of the involved party against the application
    private Date endDate; // end date of the involved party against the application
    private Element endReason; // Indicates the reason the involved party was ended against the application
    private Element relationship; // Indicates the relationship of this involved party to the main applicant
    private final String createdBy;
    private final Date createdDate;
    
    public InvolvedParty(int invPartyRef, PersonInterface person, boolean joint, boolean main, Date start, Element relationship, String createdBy) {
        this.involvedPartyRef = invPartyRef;
        this.person = person;
        this.mainApplicantInd = main;
        this.jointApplicantInd = joint;
        this.startDate = start;
        this.relationship = relationship;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    public int getInvolvedPartyRef() {
        return involvedPartyRef;
    }
    
    public PersonInterface getPerson() {
        return person;
    }
    
    public boolean getJointInd() {
        return jointApplicantInd;
    }
    
    public boolean getMainInd() {
        return mainApplicantInd;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public Element getEndReason() {
        return endReason;
    }
    
    public Element getRelationship() {
        return relationship;
    }
    
    public String createdBy() {
        return createdBy;
    }
    
    public Date createdDate() {
        return createdDate;
    }
    
    public void setJointInd(boolean joint) {
        jointApplicantInd = joint;
    }
    
    public void setMainInd(boolean main) {
        mainApplicantInd = main;
    }
    
    public void setStartDate(Date start) {
        startDate = start;
    }
    
    public void setEndDate(Date end) {
        endDate = end;
    }
    
    public void setEndReason(Element endReason) {
        this.endReason = endReason;
    }
    
    public void setRelationship(Element relationship) {
        this.relationship = relationship;
    }
    
    public void endInvolvedParty(Date end, Element end_reason) {
        setEndDate(end);
        setEndReason(end_reason);
    }
    
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }
    
    public void setInvolvedParty(boolean joint, Date startDate, Date endDate, Element endReason, Element relationship) {
        this.jointApplicantInd = joint;
        this.startDate = startDate;
        this.relationship = relationship;
        if(endDate != null) {
            endInvolvedParty(endDate, endReason);
        }
    }
}
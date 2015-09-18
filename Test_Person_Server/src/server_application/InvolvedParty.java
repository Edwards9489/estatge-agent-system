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
public class InvolvedParty {
    
    private final int involvedPartyRef;
    private Person person;
    private boolean jointApplicantInd;
    private boolean mainApplicantInd;
    private Date startDate; // start date of the involved party against the application
    private Date endDate; // end date of the involved party against the application
    private EndReason endReason; // Indicates the reason the involved party was ended against the application
    private Relationship relationship; // Indicates the relationship of this involved party to the main applicant
    private final String createdBy;
    private final Date createdDate;
    
    public InvolvedParty(int invPartyRef, Person person, boolean joint, boolean main, Date start, Relationship relationship, String createdBy) {
        this.involvedPartyRef = invPartyRef;
        this.person = person;
        this.jointApplicantInd = joint;
        this.startDate = start;
        this.relationship = relationship;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    public int getInvolvedPartyRef() {
        return involvedPartyRef;
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
    
    public EndReason getEndReason() {
        return endReason;
    }
    
    public Relationship getRelationship() {
        return relationship;
    }
    
    public void updateJointInd(boolean joint) {
        jointApplicantInd = joint;
    }
    
    public void updateMainInd(boolean main) {
        mainApplicantInd = main;
    }
    
    public void updateStartDate(Date start) {
        startDate = start;
    }
    
    public void updateEndDate(Date end) {
        endDate = end;
    }
    
    public void updateEndReason(EndReason end_reason) {
        this.endReason = end_reason;
    }
    
    public void updateRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
    
    public void endInvolvedParty(Date end, EndReason end_reason) {
        updateEndDate(end);
        updateEndReason(end_reason);
    }
    
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }
}
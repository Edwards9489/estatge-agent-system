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
    private boolean joint_applicant_ind;
    private boolean main_applicant_ind;
    private Date start_date; // start date of the involved party against the application
    private Date end_date; // end date of the involved party against the application
    private EndReason end_reason; // Indicates the reason the involved party was ended against the application
    private Relationship relationship; // Indicates the relationship of this involved party to the main applicant
    private final String createdBy;
    private final Date createdDate;
    
    public InvolvedParty(int inv_party_ref, Person person, boolean joint, boolean main, Date start, Relationship relationship, String createdBy) {
        this.involvedPartyRef = inv_party_ref;
        this.person = person;
        this.joint_applicant_ind = joint;
        this.start_date = start;
        this.relationship = relationship;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    public int getInvolvedPartyRef() {
        return involvedPartyRef;
    }
    
    public boolean getJointInd() {
        return joint_applicant_ind;
    }
    
    public boolean getMainInd() {
        return main_applicant_ind;
    }
    
    public Date getStartDate() {
        return start_date;
    }
    
    public Date getEndDate() {
        return end_date;
    }
    
    public EndReason getEndReason() {
        return end_reason;
    }
    
    public Relationship getRelationship() {
        return relationship;
    }
    
    public void updateJointInd(boolean joint) {
        joint_applicant_ind = joint;
    }
    
    public void updateMainInd(boolean main) {
        main_applicant_ind = main;
    }
    
    public void updateStartDate(Date start) {
        start_date = start;
    }
    
    public void updateEndDate(Date end) {
        end_date = end;
    }
    
    public void updateEndReason(EndReason end_reason) {
        this.end_reason = end_reason;
    }
    
    public void updateRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
    
    public void endInvolvedParty(Date end, EndReason end_reason) {
        updateEndDate(end);
        updateEndReason(end_reason);
    }
}
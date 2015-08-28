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
    
    private final int involved_party_ref;
    private Person person;
    private boolean joint_applicant_ind;
    private boolean main_applicant_ind;
    private Date created_date; // indicates when the involved party was created against 
    private Date start_date; // start date of the involved party against the application
    private Date end_date; // end date of the involved party against the application
    private String end_reason; // Indicates the reason the involved party was ended against the application
    private String relationship; // Indicates the relationship of this involved party to the main applicant
    
    private InvolvedParty(int inv_party_ref, Person person, boolean joint, boolean main, Date start, String relationship) {
        involved_party_ref = inv_party_ref;
        this.person = person;
        joint_applicant_ind = joint;
        created_date = new Date();
        start_date = start;
        this.relationship = relationship;
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
    
    public String getEndReason() {
        return end_reason;
    }
    
    public String getRelationship() {
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
    
    public void updateEndReason(String end_reason) {
        this.end_reason = end_reason;
    }
    
    public void updateRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    public void endInvolvedParty(Date end, String end_reason) {
        updateEndDate(end);
        updateEndReason(end_reason);
    }
}
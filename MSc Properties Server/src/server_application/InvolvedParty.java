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
    
    ///   VARIABLES   ///
        
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
    
    ///   CONSTRUCTORS ///
    
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
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setStartDate(Date start) {
        startDate = start;
    }
    
    private void setRelationship(Element relationship) {
        this.relationship = relationship;
    }
    
    private void setEndDate(Date end) {
        endDate = end;
    }
    
    private void setEndReason(Element endReason) {
        this.endReason = endReason;
    }
    
    public void setJointInd(boolean joint) {
        jointApplicantInd = joint;
    }
    
    public void setMainInd(boolean main) {
        mainApplicantInd = main;
    }
    
    @Override
    public void endInvolvedParty(Date end, Element endReason) {
        setEndDate(end);
        setEndReason(endReason);
    }
    
    @Override
    public void updateInvolvedParty(boolean joint, Date start, Element relationhip) {
        setJointInd(joint);
        setStartDate(start);
        setRelationship(relationship);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public int getInvolvedPartyRef() {
        return involvedPartyRef;
    }
    
    @Override
    public int getPersonRef() {
        return getPerson().getPersonRef();
    }
    
    @Override
    public PersonInterface getPerson() {
        return person;
    }
    
    @Override
    public Date getStartDate() {
        return startDate;
    }
    
    @Override
    public Date getEndDate() {
        return endDate;
    }
    
    @Override
    public Element getEndReason() {
        return endReason;
    }
    
    @Override
    public Element getRelationship() {
        return relationship;
    }
    
    @Override
    public boolean getJointInd() {
        return jointApplicantInd;
    }
    
    @Override
    public boolean getMainInd() {
        return mainApplicantInd;
    }
    
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }
    
    @Override
    public String createdBy() {
        return createdBy;
    }
    
    @Override
    public Date createdDate() {
        return createdDate;
    }
}
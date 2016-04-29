/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class JobRoleBenefit extends UnicastRemoteObject implements JobRoleBenefitInterface {
    
    ///   VARIABLES   ///
    private final int jobRoleBenefitRef;
    private final Element benefit;
    private String stringValue;
    private Double doubleValue;
    private Date startDate;
    private Date endDate;
    private final String jobRoleCode;
    private final Note note;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private boolean salaryBenefit;
    
    ///   CONSTRUCTORS   ///
    
    /**
     * Constructor for objects of class JobRoleBenefit
     * @param ref
     * @param benefit
     * @param startDate
     * @param salaryBenefit
     * @param stringValue
     * @param doubleValue
     * @param note
     * @param jobRoleCode
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public JobRoleBenefit(int ref, Element benefit, Date startDate, boolean salaryBenefit, String stringValue, Double doubleValue, Note note, String jobRoleCode, String createdBy, Date createdDate) throws RemoteException {
        this.jobRoleBenefitRef = ref;
        this.benefit = benefit;
        this.startDate = startDate;
        this.salaryBenefit = salaryBenefit;
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
        }
        this.jobRoleCode = jobRoleCode;
        this.note = note;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param stringValue
     */
    private void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * @param doubleValue
     */
    private void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param salaryBenefit
     */
    private void setSalaryBenefit(boolean salaryBenefit) {
        this.salaryBenefit = salaryBenefit;
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
    }
    
    /**
     * 
     * @param stringValue
     * @param doubleValue
     * @param salaryBenefit
     * @param startDate
     * @param comment
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void updateJobRoleBenefit(String stringValue, Double doubleValue, boolean salaryBenefit, Date startDate, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        setSalaryBenefit(salaryBenefit);
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
            setStringValue(null);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
            setDoubleValue(null);
        }
        this.setComment(comment, modifiedBy);
        setStartDate(startDate);
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate == null || endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return benefitRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getBenefitRef() throws RemoteException {
        return this.jobRoleBenefitRef;
    }
    
    /**
     * @return benefit
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getBenefit() throws RemoteException {
        return this.benefit;
    }
    
    /**
     * 
     * @return the code of benefit
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getBenefitCode() throws RemoteException {
        return this.getBenefit().getCode();
    }

    /**
     * @return stringValue
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getStringValue() throws RemoteException {
        return this.stringValue;
    }

    /**
     * @return doubleValue
     * @throws java.rmi.RemoteException
     */
    @Override
    public Double getDoubleValue() throws RemoteException {
        return this.doubleValue;
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
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return endDate;
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.after(new Date());
        }
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    @Override
    public String getJobRoleCode() throws RemoteException {
        return this.jobRoleCode;
    }
    
    /**
     * 
     * @return the name of the last user to modify the JobRoleBenefit
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
     * @return the last date a user modified the JobRoleBenefit
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
     * @return the list of modifiedBy objects for the JobRoleBenefit
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the JobRoleBenefit
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
    public Note getNote() throws RemoteException {
        return note;
    }
    
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }

    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }

    /**
     * @return salaryBenefit
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isSalaryBenefit() throws RemoteException {
        return this.salaryBenefit;
    }
}
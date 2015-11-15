/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class JobRoleBenefit implements JobRoleBenefitInterface {
    
    ///   VARIABLES   ///
    private final int jobRoleBenefitRef;
    private final Element benefit;
    private String stringValue;
    private double doubleValue;
    private Date startDate;
    private Date endDate;
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
     * @param createdBy
     * @param createdDate 
     */
    public JobRoleBenefit(int ref, Element benefit, Date startDate, boolean salaryBenefit, String stringValue, double doubleValue, String createdBy, Date createdDate) {
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
    private void setDoubleValue(double doubleValue) {
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
    
    /**
     * 
     * @param stringValue
     * @param doubleValue
     * @param salaryBenefit
     * @param startDate
     * @param modifiedBy 
     */
    public void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit, Date startDate, ModifiedByInterface modifiedBy) {
        setSalaryBenefit(salaryBenefit);
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
        }
        setStartDate(startDate);
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return benefitRef
     */
    @Override
    public int getBenefitRef() {
        return this.jobRoleBenefitRef;
    }
    
    /**
     * @return benefit
     */
    @Override
    public Element getBenefit() {
        return this.benefit;
    }
    
    /**
     * 
     * @return the code of benefit
     */
    @Override
    public String getBenefitCode() {
        return this.getBenefit().getCode();
    }

    /**
     * @return stringValue
     */
    @Override
    public String getStringValue() {
        return this.stringValue;
    }

    /**
     * @return doubleValue
     */
    @Override
    public double getDoubleValue() {
        return this.doubleValue;
    }
    
    /**
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }
    
    /**
     * 
     * @return the name of the last user to modify the JobRoleBenefit
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
     * @return the last date a user modified the JobRoleBenefit
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
     * @return the list of modifiedBy objects for the JobRoleBenefit
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the JobRoleBenefit
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * @return salaryBenefit
     */
    @Override
    public boolean isSalaryBenefit() {
        return this.salaryBenefit;
    }
}
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
     * @param stringValue the stringValue to set
     */
    private void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * @param doubleValue the doubleValue to set
     */
    private void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * @param startDate the startDate to set
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param salaryBenefit the salaryBenefit to set
     */
    private void setSalaryBenefit(boolean salaryBenefit) {
        this.salaryBenefit = salaryBenefit;
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    @Override
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
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the benefitRef
     */
    @Override
    public int getBenefitRef() {
        return this.jobRoleBenefitRef;
    }
    
    /**
     * @return the benefit
     */
    @Override
    public Element getBenefit() {
        return this.benefit;
    }
    
    @Override
    public String getBenefitCode() {
        return this.getBenefit().getCode();
    }

    /**
     * @return the stringValue
     */
    @Override
    public String getStringValue() {
        return this.stringValue;
    }

    /**
     * @return the doubleValue
     */
    @Override
    public double getDoubleValue() {
        return this.doubleValue;
    }
    
    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
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

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * @return the salaryBenefit
     */
    @Override
    public boolean isSalaryBenefit() {
        return this.salaryBenefit;
    }
}

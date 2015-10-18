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
    private final Element benefit;
    private String stringValue;
    private double doubleValue;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private boolean salaryBenefit;
    private boolean current;
    
    ///   CONSTRUCTORS   ///
    
    public JobRoleBenefit(Element benefit, boolean salaryBenefit, String createdBy) {
        this.benefit = benefit;
        this.salaryBenefit = salaryBenefit;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.current = true;
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
     * @param salaryBenefit the salaryBenefit to set
     */
    private void setSalaryBenefit(boolean salaryBenefit) {
        this.salaryBenefit = salaryBenefit;
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    private void setCurrent(boolean current) {
        this.current = current;
    }
    
    @Override
    public void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit, boolean current, ModifiedByInterface modifiedBy) {
        setSalaryBenefit(salaryBenefit);
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
        }
        this.setCurrent(current);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
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
    
    public boolean isCurrent() {
        return this.current;
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
    public List getModifiedBy() {
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

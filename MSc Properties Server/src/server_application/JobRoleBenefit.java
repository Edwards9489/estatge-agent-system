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
    private final Element element;
    private String stringValue;
    private double doubleValue;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private boolean salaryBenefit;
    
    ///   CONSTRUCTORS   ///
    
    public JobRoleBenefit(int ref, Element element, boolean salaryBenefit, String createdBy) {
        this.jobRoleBenefitRef = ref;
        this. element = element;
        this.salaryBenefit = salaryBenefit;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
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
    
    @Override
    public void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit, ModifiedByInterface modifiedBy) {
        setSalaryBenefit(salaryBenefit);
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
        }
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    public int getJobRoleBenefitRef() {
        return this.jobRoleBenefitRef;
    }
    /**
     * @return the element
     */
    @Override
    public Element getElement() {
        return this.element;
    }
    
    @Override
    public String getElementCode() {
        return this.getElement().getCode();
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

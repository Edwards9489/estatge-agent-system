/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class JobRoleBenefit implements JobRoleBenefitInterface {
    
    ///   VARIABLES   ///
    
    private final Element element;
    private String stringValue;
    private double doubleValue;
    private final String createdBy;
    private final Date createdDate;
    private boolean salaryBenefit;
    
    ///   CONSTRUCTORS   ///
    
    public JobRoleBenefit(Element element, boolean salaryBenefit, String createdBy) {
        this. element = element;
        this.salaryBenefit = salaryBenefit;
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
    
    public void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit) {
        setSalaryBenefit(salaryBenefit);
        if(salaryBenefit) {
            setDoubleValue(doubleValue);
        }
        else if(!salaryBenefit) {
            setStringValue(stringValue);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the element
     */
    public Element getElement() {
        return element;
    }
    
    public String getElementCode() {
        return getElement().getCode();
    }

    /**
     * @return the stringValue
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * @return the doubleValue
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return the salaryBenefit
     */
    public boolean isSalaryBenefit() {
        return salaryBenefit;
    }
}

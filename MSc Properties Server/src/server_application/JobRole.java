/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import interfaces.JobRoleInterface;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class JobRole implements JobRoleInterface {
    
    ///   VARIABLES   ///
    
    private final String jobRoleCode;
    private String jobTitle;
    private String jobDescription;
    private final HashMap<String, Element> jobRequirements;
    private final boolean fullTime;
    private double salary;
    private final HashMap<String, JobRoleBenefitInterface> benefits; // Create Job Role Benefit class
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public JobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, String createdBy) {
        this.jobRoleCode = code;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.fullTime = fullTime;
        this.salary = salary;
        this.jobRequirements = new HashMap<>();
        this.benefits = new HashMap<>();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param jobTitle the jobTitle to set
     */
    private void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @param jobDescription the jobDescription to set
     */
    private void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @param salary the salary to set
     */
    private void setSalary(double salary) {
        this.salary = salary;
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    @Override
    public void updateJobRole(String title, String description, double salary, ModifiedByInterface modifiedBy) {
        this.setJobTitle(title);
        this.setJobDescription(description);
        this.setSalary(salary);
        this.modifiedBy(modifiedBy);
    }
    
    @Override
    public void createJobRequirement(Element requirement, ModifiedByInterface modifiedBy) {
        if(this.jobRequirements.containsKey(requirement.getCode())) {
            this.jobRequirements.put(requirement.getCode(), requirement);
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void removeJobRequirement(Element requirement, ModifiedByInterface modifiedBy) {
        if(this.jobRequirements.containsKey(requirement.getCode())) {
            this.benefits.remove(requirement.getCode());
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void createBenefit(JobRoleBenefitInterface benefit, ModifiedByInterface modifiedBy) {
        if(this.benefits.containsKey(benefit.getElementCode())) {
            this.benefits.put(benefit.getElementCode(), benefit);
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void removeBenefit(JobRoleBenefitInterface benefit, ModifiedByInterface modifiedBy) {
        if(this.benefits.containsKey(benefit.getElementCode())) {
            this.benefits.remove(benefit.getElementCode());
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the jobRoleRef
     */
    @Override
    public String getJobRoleCode() {
        return this.jobRoleCode;
    }

    /**
     * @return the jobTitle
     */
    @Override
    public String getJobTitle() {
        return this.jobTitle;
    }
    
    /**
     * @return the jobDescription
     */
    @Override
    public String getJobDescription() {
        return this.jobDescription;
    }

    /**
     * @return the jobRequirements
     */
    @Override
    public List getJobRequirements() {
        return Collections.unmodifiableList((List) this.jobRequirements.values());
    }

    /**
     * @return the fullTime
     */
    @Override
    public boolean isFullTime() {
        return this.fullTime;
    }

    /**
     * @return the salary
     */
    @Override
    public double getSalary() {
        return this.salary;
    }

    /**
     * @return the benefits
     */
    @Override
    public List getBenefits() {
        return Collections.unmodifiableList((List) this.benefits.values());
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
}
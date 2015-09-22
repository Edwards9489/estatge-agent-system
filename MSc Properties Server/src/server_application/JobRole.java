/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import interfaces.JobRoleInterface;
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
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public JobRole(String code, String jobTitle, String jobDescription, boolean fullTime, String createdBy) {
        this.jobRoleCode = code;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.fullTime = fullTime;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        jobRequirements = new HashMap<>();
        benefits = new HashMap<>();
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
    
    @Override
    public void updateJobRole(String title, String description) {
        setJobTitle(title);
        setJobDescription(description);
    }
    
    @Override
    public void createJobRequirement(Element requirement) {
        if(jobRequirements.containsKey(requirement.getCode())) {
            jobRequirements.put(requirement.getCode(), requirement);
        }
    }
    
    @Override
    public void removeJobRequirement(Element requirement) {
        if(jobRequirements.containsKey(requirement.getCode())) {
            benefits.remove(requirement.getCode());
        }
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public void createBenefit(JobRoleBenefitInterface benefit) {
        if(benefits.containsKey(benefit.getElementCode())) {
            benefits.put(benefit.getElementCode(), benefit);
        }
    }
    
    @Override
    public void removeBenefit(JobRoleBenefitInterface benefit) {
        if(benefits.containsKey(benefit.getElementCode())) {
            benefits.remove(benefit.getElementCode());
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the jobRoleRef
     */
    @Override
    public String getJobRoleCode() {
        return jobRoleCode;
    }

    /**
     * @return the jobTitle
     */
    @Override
    public String getJobTitle() {
        return jobTitle;
    }
    
    /**
     * @return the jobDescription
     */
    @Override
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * @return the jobRequirements
     */
    @Override
    public List getJobRequirements() {
        return Collections.unmodifiableList((List) jobRequirements.values());
    }

    /**
     * @return the fullTime
     */
    @Override
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * @return the salary
     */
    @Override
    public double getSalary() {
        return salary;
    }

    /**
     * @return the benefits
     */
    @Override
    public List getBenefits() {
        return Collections.unmodifiableList((List) benefits.values());
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}
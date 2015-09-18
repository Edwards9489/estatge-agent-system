/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class JobRole {
    private final int jobRoleRef;
    private String jobTitle;
    private String jobDescription;
    private HashMap<String, Requirement> jobRequirements;
    private boolean fullTime;
    private double salary;
    private double totalSalaryBenefits;
    private HashMap<String, Benefit> benefits;
    private final String createdBy;
    private final Date createdDate;
    
    
    public JobRole(int jobRef, String createdBy) {
        jobRoleRef = jobRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        jobRequirements = new HashMap<>();
        benefits = new HashMap<>();
    }

    /**
     * @return the jobRoleRef
     */
    public int getJobRoleRef() {
        return jobRoleRef;
    }

    /**
     * @return the jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the jobDescription
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * @param jobDescription the jobDescription to set
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @return the jobRequirements
     */
    public List getJobRequirements() {
        return Collections.unmodifiableList((List) jobRequirements.values());
    }
    
    public void createJobRequirement(Requirement requirement) {
        if(jobRequirements.containsKey(requirement.getCode())) {
            jobRequirements.put(requirement.getCode(), requirement);
        }
    }

    /**
     * @return the fullTime
     */
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * @param fullTime the fullTime to set
     */
    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the benefits
     */
    public List getBenefits() {
        return Collections.unmodifiableList((List) benefits.values());
    }
    
    public void createBenefit(Benefit benefit) {
        if(benefits.containsKey(benefit.getCode())) {
            benefits.put(benefit.getCode(), benefit);
        }
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
}
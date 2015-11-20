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
import interfaces.Note;
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
    private final HashMap<String, Element> requirements;
    private final boolean fullTime;
    private double salary;
    private boolean current;
    private final HashMap<Integer, JobRoleBenefitInterface> benefits; // Create Job Role Benefit class
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private boolean read;
    private boolean write;
    private boolean update;
    private boolean employeeRead;
    private boolean employeeWrite;
    private boolean employeeUpdate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * 
     * @param code
     * @param jobTitle
     * @param jobDescription
     * @param fullTime
     * @param salary
     * @param read
     * @param write
     * @param update
     * @param employeeRead
     * @param employeeWrite
     * @param employeeUpdate
     * @param createdBy
     * @param createdDate 
     */
    public JobRole(String code, String jobTitle, String jobDescription, boolean fullTime, double salary, boolean read, boolean write, 
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, String createdBy, Date createdDate) {
        this.jobRoleCode = code;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.fullTime = fullTime;
        this.salary = salary;
        this.current = true;
        this.requirements = new HashMap<>();
        this.benefits = new HashMap<>();
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.read = read;
        this.write = write;
        this.update = update;
        this.employeeRead = employeeRead;
        this.employeeWrite = employeeWrite;
        this.employeeUpdate = employeeUpdate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param jobTitle
     */
    private void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @param jobDescription
     */
    private void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @param salary
     */
    private void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @param read
     */
    private void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @param write
     */
    private void setWrite(boolean write) {
        this.write = write;
    }

    /**
     * @param update
     */
    private void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @param employeeRead
     */
    private void setEmployeeRead(boolean employeeRead) {
        this.employeeRead = employeeRead;
    }

    /**
     * @param employeeWrite
     */
    private void setEmployeeWrite(boolean employeeWrite) {
        this.employeeWrite = employeeWrite;
    }

    /**
     * @param employeeUpdate
     */
    private void setEmployeeUpdate(boolean employeeUpdate) {
        this.employeeUpdate = employeeUpdate;
    }
    
    /**
     * @param current
     */
    public void setCurrent(boolean current) {
        this.current = current;
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param title
     * @param description
     * @param salary
     * @param current
     * @param read
     * @param write
     * @param update
     * @param employeeRead
     * @param employeeWrite
     * @param employeeUpdate
     * @param modifiedBy 
     */
    public void updateJobRole(String title, String description, double salary, boolean current, boolean read, boolean write,
            boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate, ModifiedByInterface modifiedBy) {
        this.setJobTitle(title);
        this.setJobDescription(description);
        this.setSalary(salary);
        this.setCurrent(current);
        this.setRead(read);
        this.setWrite(write);
        this.setUpdate(update);
        this.setEmployeeRead(employeeRead);
        this.setEmployeeWrite(employeeWrite);
        this.setEmployeeUpdate(employeeUpdate);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param requirement
     * @param modifiedBy 
     */
    public void createJobRequirement(Element requirement, ModifiedByInterface modifiedBy) {
        if(!this.hasRequirement(requirement.getCode())) {
            this.requirements.put(requirement.getCode(), requirement);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param requirement
     * @param modifiedBy 
     */
    public void removeJobRequirement(Element requirement, ModifiedByInterface modifiedBy) {
        if(this.hasRequirement(requirement.getCode())) {
            this.requirements.remove(requirement.getCode());
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param benefit
     * @param modifiedBy 
     */
    public void createJobBenefit(JobRoleBenefitInterface benefit, ModifiedByInterface modifiedBy) {
        if(!this.hasCurrentBenefit(benefit.getBenefitCode())) {
            benefits.put(benefit.getBenefitRef(), benefit);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param ref
     * @param endDate
     * @param modifiedBy 
     */
    public void endJobBenefit(int ref, Date endDate, ModifiedByInterface modifiedBy) {
        if(this.hasBenefit(ref)) {
            JobRoleBenefit benefit = (JobRoleBenefit) this.benefits.get(ref);
            benefit.setEndDate(endDate, modifiedBy);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return jobRoleRef
     */
    @Override
    public String getJobRoleCode() {
        return this.jobRoleCode;
    }

    /**
     * @return jobTitle
     */
    @Override
    public String getJobTitle() {
        return this.jobTitle;
    }
    
    /**
     * @return jobDescription
     */
    @Override
    public String getJobDescription() {
        return this.jobDescription;
    }

    /**
     * @return jobRequirements
     */
    @Override
    public List<Element> getJobRequirements() {
        return Collections.unmodifiableList(new ArrayList<>(this.requirements.values()));
    }

    /**
     * @return fullTime
     */
    @Override
    public boolean isFullTime() {
        return this.fullTime;
    }

    /**
     * @return salary
     */
    @Override
    public double getSalary() {
        return this.salary;
    }
    
    /**
     * @return current
     */
    @Override
    public boolean isCurrent() {
        return this.current;
    }
    
    /**
     * 
     * @param code
     * @return true if requirements contains Element with code == code
     */
    public boolean hasRequirement(String code) {
        return this.requirements.containsKey(code);
    }
    
    public boolean hasBenefit(int ref) {
        return this.benefits.containsKey(ref);
    }
    
    public boolean hasCurrentBenefit(String code) {
        for(JobRoleBenefitInterface benefit : benefits.values()) {
            if(benefit.isCurrent() && code.equals(benefit.getBenefitCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return benefits
     */
    @Override
    public List<JobRoleBenefitInterface> getBenefits() {
        return Collections.unmodifiableList(new ArrayList<>(this.benefits.values()));
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user to modify the JobRole
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
     * @return the last date a user made a modification to the JobRole
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
     * @return the list of modifiedBy objects for the JobRole
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for JobRole
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public boolean hasNote(int ref) {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Note getNote(int ref) {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getRef() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() {
        return Collections.unmodifiableList(this.notes);
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
     * @return read
     */
    public boolean getRead() {
        return read;
    }

    /**
     * @return write
     */
    public boolean getWrite() {
        return write;
    }

    /**
     * @return update
     */
    public boolean getUpdate() {
        return update;
    }

    /**
     * @return employeeRead
     */
    public boolean getEmployeeRead() {
        return employeeRead;
    }

    /**
     * @return employeeWrite
     */
    public boolean getEmployeeWrite() {
        return employeeWrite;
    }

    /**
     * @return employeeUpdate
     */
    public boolean getEmployeeUpdate() {
        return employeeUpdate;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class JobRole {
    private final int jobRoleRef;
    private String jobTitle;
    private String jobDescription;
    private ArrayList<Requirement> jobRequirements;
    private boolean fullTime;
    private double salary;
    private ArrayList<Benefit> benefits;
    private final String createdBy;
    private final Date createdDate;
    
    
    public JobRole(int jobRef, String createdBy) {
        jobRoleRef = jobRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
}
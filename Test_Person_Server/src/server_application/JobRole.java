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
    private final int job_role_ref;
    private String jobTitle;
    private String jobDescription;
    private String jobRequirements;
    private boolean fullTime;
    private double salary;
    private ArrayList<String> benefits;
    
    
    public JobRole(int jobRef) {
        job_role_ref = jobRef;
    }
}
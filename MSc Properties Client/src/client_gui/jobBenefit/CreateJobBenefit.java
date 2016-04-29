/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobBenefit;

import client_application.ClientImpl;
import interfaces.JobRoleInterface;
import javax.swing.JFrame;

/**
 *
 * @author Dwayne
 */
public class CreateJobBenefit extends JFrame {
    ClientImpl client = null;
    JobRoleInterface jobRole = null;

    public CreateJobBenefit(ClientImpl client, JobRoleInterface jobRole) {
        super("MSc Properties");
        setClient(client);
        setJobRole(jobRole);
        layoutComponents();
    }
    
    private void setClient(ClientImpl model) {
        if (this.client == null) {
            this.client = model;
        }
    }
    
    private void setJobRole(JobRoleInterface jobRole) {
        if (this.jobRole == null) {
            this.jobRole = jobRole;
        }
    }
    
    private void layoutComponents() {
        
    }
    
}

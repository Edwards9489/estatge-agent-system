/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobBenefit;

import client_application.ClientImpl;
import interfaces.JobRoleBenefitInterface;
import javax.swing.JFrame;

/**
 *
 * @author Dwayne
 */
public class JobRoleBenefitDetails extends JFrame {
    private ClientImpl client = null;
    JobRoleBenefitInterface jobRoleBenefit = null;

    public JobRoleBenefitDetails(ClientImpl client, JobRoleBenefitInterface jobRoleBenefit) {
        super("MSc Properties");
        setClient(client);
        setJobRoleBenefit(jobRoleBenefit);
        layoutComponents();
    }
    
    private void setClient(ClientImpl model) {
        if (this.client == null) {
            this.client = model;
        }
    }
    
    private void setJobRoleBenefit(JobRoleBenefitInterface jobRoleBenefit) {
        if (this.jobRoleBenefit == null) {
            this.jobRoleBenefit = jobRoleBenefit;
        }
    }
    
    private void layoutComponents() {
        
    }
    
}

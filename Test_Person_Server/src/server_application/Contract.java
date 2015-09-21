/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Contract extends Agreement {
    private final Employee employee;
    private final JobRole jobRole;
    
    public Contract(int contractRef, Date startDate, int length, String createdBy, Employee employee, JobRole jobRole) {
        super(contractRef, startDate, length, createdBy);
        this.employee = employee;
        this.jobRole = jobRole;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /**
     * @return the jobRole
     */
    public JobRole getJobRole() {
        return jobRole;
    }
}
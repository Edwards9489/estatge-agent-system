/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import interfaces.JobRoleInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Contract extends Agreement implements ContractInterface {
    
    ///   VARIABLES   ///
    
    private final EmployeeInterface employee;
    private final JobRoleInterface jobRole;
    
    ///   CONSTRUCTORS ///
    
    public Contract(int contractRef, int accountRef, Date startDate, int length, EmployeeInterface employee, JobRoleInterface jobRole, String officeCode, String createdBy, Date createdDate) {
        super(contractRef, employee.getPerson().getName(), startDate, length, accountRef, createdBy, createdDate, officeCode);
        this.employee = employee;
        this.jobRole = jobRole;
    }
    
    
    ///   MUTATOR METHODS   ///
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the employee
     */
    @Override
    public EmployeeInterface getEmployee() {
        return employee;
    }
    
    @Override
    public int getEmployeeRef() {
        return getEmployee().getEmployeeRef();
    }
    
    /**
     * @return the jobRole
     */
    @Override
    public JobRoleInterface getJobRole() {
        return jobRole;
    }
    
    @Override
    public String getJobRoleRef() {
        return getJobRole().getJobRoleCode();
    }
}
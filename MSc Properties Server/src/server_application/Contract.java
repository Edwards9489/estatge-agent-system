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
    
    /**
     * Constructor for objects of class Contract
     * @param contractRef
     * @param accountRef
     * @param startDate
     * @param length
     * @param employee
     * @param jobRole
     * @param officeCode
     * @param createdBy
     * @param createdDate 
     */
    public Contract(int contractRef, int accountRef, Date startDate, int length, EmployeeInterface employee, JobRoleInterface jobRole, String officeCode, String createdBy, Date createdDate) {
        super(contractRef, employee.getPerson().getName(), startDate, length, accountRef, createdBy, createdDate, officeCode);
        this.employee = employee;
        this.jobRole = jobRole;
    }
    
    
    ///   MUTATOR METHODS   ///
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return employee
     */
    @Override
    public EmployeeInterface getEmployee() {
        return employee;
    }
    
    /**
     * 
     * @return ref of employee
     */
    @Override
    public int getEmployeeRef() {
        return getEmployee().getEmployeeRef();
    }
    
    /**
     * @return jobRole
     */
    @Override
    public JobRoleInterface getJobRole() {
        return jobRole;
    }
    
    /**
     * @return code of jobRole
     */
    @Override
    public String getJobRoleCode() {
        return getJobRole().getJobRoleCode();
    }
    
    @Override
    public String toString() {
        return "AMEND toString()";
    }
}
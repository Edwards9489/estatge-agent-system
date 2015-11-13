/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ContractInterface;
import interfaces.EmployeeAccountInterface;
import java.util.Date;

/**
 * 
 * @author Dwayne
 */
public class EmployeeAccount extends Account implements EmployeeAccountInterface {
    
    ///   VARIABLES   ///
    
    private final ContractInterface contract;
    private double salary;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class EmployeeAccount
     * @param employeeAccRef
     * @param contract
     * @param createdBy
     * @param createdDate 
     */
    public EmployeeAccount(int employeeAccRef, ContractInterface contract, String createdBy, Date createdDate) {
        super(employeeAccRef, contract.getAgreementName(), contract.getOfficeCode(), contract.getStartDate(), createdBy, createdDate);
        this.contract = contract;
        salary = contract.getJobRole().getSalary();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param salary 
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return contract
     */
    @Override
    public ContractInterface getContract() {
        return contract;
    }
    
    /**
     * 
     * @return ref of contract
     */
    @Override
    public int getContractRef() {
        return contract.getAgreementRef();
    }
    
    /**
     * 
     * @return salary
     */
    @Override
    public double getSalary() {
        return this.salary;
    }
}
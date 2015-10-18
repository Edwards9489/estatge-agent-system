/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ContractInterface;
import interfaces.EmployeeAccountInterface;

/**
 *
 * @author Dwayne
 */
public class EmployeeAccount extends Account implements EmployeeAccountInterface {
    
    ///   VARIABLES   ///
    
    private final ContractInterface contract;
    private double salary;
    
    ///   CONSTRUCTORS ///
    
    public EmployeeAccount(int employeeAccRef, ContractInterface contract, String createdBy) {
        super(employeeAccRef, contract.getAgreementName(), contract.getOfficeCode(), contract.getStartDate(), createdBy);
        this.contract = contract;
        salary = contract.getJobRole().getSalary();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the contract
     */
    @Override
    public ContractInterface getContract() {
        return contract;
    }
    
    @Override
    public int getContractRef() {
        return contract.getAgreementRef();
    }
    
    @Override
    public double getSalary() {
        return this.salary;
    }
}
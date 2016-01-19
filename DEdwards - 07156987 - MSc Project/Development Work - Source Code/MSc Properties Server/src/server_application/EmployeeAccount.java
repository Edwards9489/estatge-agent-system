/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ContractInterface;
import interfaces.EmployeeAccountInterface;
import java.rmi.RemoteException;
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
     * @throws java.rmi.RemoteException 
     */
    public EmployeeAccount(int employeeAccRef, ContractInterface contract, String createdBy, Date createdDate) throws RemoteException {
        super(employeeAccRef, contract.getAgreementName(), contract.getOfficeCode(), contract.getStartDate(), createdBy, createdDate);
        this.contract = contract;
        salary = contract.getJobRole().getSalary();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     *
     * @param salary
     * @throws java.rmi.RemoteException
     */
    public void setSalary(double salary) throws RemoteException {
        this.salary = salary;
    }
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return contract
     * @throws java.rmi.RemoteException
     */
    @Override
    public ContractInterface getContract() throws RemoteException {
        return contract;
    }
    
    /**
     * 
     * @return ref of contract
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getContractRef() throws RemoteException {
        return contract.getAgreementRef();
    }
    
    /**
     * 
     * @return salary
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getSalary() throws RemoteException {
        return this.salary;
    }
}
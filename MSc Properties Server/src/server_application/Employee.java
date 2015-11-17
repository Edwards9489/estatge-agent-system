/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Employee implements EmployeeInterface {
    
    ///   VARIABLES   ///
    
    private final int employeeRef;
    private final PersonInterface person;
    private final List<ContractInterface> contracts;
    private String officeCode; // Create a class for office, which stores the office information
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private final UserImpl user;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Employee
     * @param employeeRef
     * @param person
     * @param username
     * @param password
     * @param createdBy
     * @param createdDate
     * @throws RemoteException 
     */
    public Employee(int employeeRef, PersonInterface person, String username, String password, String createdBy, Date createdDate) throws RemoteException {
        this.employeeRef = employeeRef;
        this.person = person;
        this.contracts = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.user = new UserImpl(employeeRef, username, password, null);
    }
    
    
    
    ///   MUTATOR METHODS   ///
        
    /**
     * @param officeCode the officeCode to set
     */
    private void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
        this.user.setOfficeCode(officeCode);
    }
    
    /**
     * 
     * @param modifiedBy 
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * 
     * @param password 
     * @param modifiedBy 
     */
    public void updatePassword(String password, ModifiedByInterface modifiedBy) {
        user.setPassword(password);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param contract
     * @param modifiedBy 
     */
    public void createContract(ContractInterface contract, ModifiedByInterface modifiedBy) {
        if(!contracts.isEmpty()) {
            Agreement temp = (Agreement) contracts.get(contracts.size()-1);
            if(temp.isCurrent()) {
                temp.setActualEndDate(contract.getStartDate(), modifiedBy);
            }
        }
        contracts.add(contract);
        this.modifiedBy(modifiedBy);
        setOfficeCode(contract.getOfficeCode());
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return employeeRef
     */
    @Override
    public int getEmployeeRef() {
        return employeeRef;
    }

    /**
     * @return person
     */
    @Override
    public PersonInterface getPerson() {
        return person;
    }
    
    /**
     * 
     * @return true if contracts.isEmpty() == false || getContract.isCurrent()
     */
    public boolean isCurrent() {
        if(contracts.isEmpty()) {
            return false;
        } else {
            return this.getContract().isCurrent();
        }
    }
    
    /**
     * @return ref of person
     */
    @Override
    public int getPersonRef() {
        return person.getPersonRef();
    }
    
    /**
     * 
     * @return user
     */
    public UserImpl getUser() {
        return this.user;
    }

    /**
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }
    
    /**
     * 
     * @return the name of the last user to modify the Employee
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date of the last modification for the Employee
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Employee
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Employee
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @return most recent contract
     */
    @Override
    public ContractInterface getContract() {
        return contracts.get(contracts.size()-1);
    }
    
    /**
     * @return contracts
     */
    @Override
    public List<ContractInterface> getContracts() {
        return Collections.unmodifiableList(contracts);
    }
}
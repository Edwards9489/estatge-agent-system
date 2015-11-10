/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AgreementInterface;
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
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    public void updatePassword(String password) {
        user.setPassword(password);
    }
    
    public void createContract(ContractInterface contract, ModifiedByInterface modifiedBy) {
        if(!contracts.isEmpty()) {
            AgreementInterface temp = contracts.get(contracts.size()-1);
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
     * @return the employeeRef
     */
    @Override
    public int getEmployeeRef() {
        return employeeRef;
    }

    /**
     * @return the person
     */
    @Override
    public PersonInterface getPerson() {
        return person;
    }
    
    public boolean isCurrent() {
        if(contracts.isEmpty()) {
            return false;
        } else {
            return this.getContract().isCurrent();
        }
    }
    
    /**
     * @return the person
     */
    @Override
    public int getPersonRef() {
        return person.getPersonRef();
    }
    
    public UserImpl getUser() {
        return this.user;
    }

    /**
     * @return the officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @return the current contracts
     */
    @Override
    public ContractInterface getContract() {
        return contracts.get(contracts.size()-1);
    }
    
    /**
     * @return the addresses
     */
    @Override
    public List<ContractInterface> getContracts() {
        return Collections.unmodifiableList(contracts);
    }
}
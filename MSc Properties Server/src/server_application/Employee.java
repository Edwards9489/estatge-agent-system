/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressUsageInterface;
import interfaces.AgreementInterface;
import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import interfaces.PersonInterface;
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
    private final ArrayList<ContractInterface> contracts;
    private final ArrayList<AddressUsageInterface> addresses;
    private String officeCode; // Create a class for office, which stores the office information
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    
    public Employee(int employeeRef, PersonInterface person, String createdBy) {
        this.employeeRef = employeeRef;
        this.person = person;
        this.contracts = new ArrayList();
        this.addresses = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
        
    /**
     * @param officeCode the officeCode to set
     */
    private void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
    
    public void createContract(ContractInterface contract) {
        if(!contracts.isEmpty()) {
            AgreementInterface temp = contracts.get(contracts.size()-1);
            if(temp.isCurrent()) {
                temp.setActualEndDate(contract.getStartDate());
            }
        }
        contracts.add(contract);
        setOfficeCode(contract.getOfficeCode());
    }
    
    public void createAddress(AddressUsageInterface addressUsage) {
        if(!addresses.isEmpty()) {
            AddressUsageInterface temp = addresses.get(addresses.size()-1);
            temp.setEndDate(addressUsage.getStartDate());
        }
        addresses.add(addressUsage);
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

    /**
     * @return the officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
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
    public List getContracts() {
        return Collections.unmodifiableList(contracts);
    }
    
    /**
     * @return the current addresses
     */
    @Override
    public AddressUsageInterface getAddressUsage() {
        return addresses.get(addresses.size()-1);
    }
    
    /**
     * @return the addresses
     */
    @Override
    public List getAddresses() {
        return Collections.unmodifiableList(addresses);
    }
}
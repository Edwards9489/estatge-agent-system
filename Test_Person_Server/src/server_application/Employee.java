/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Employee {
    private final int employeeRef;
    private final Person person;
    private final ArrayList<Contract> contracts;
    private final ArrayList<AddressUsage> addresses;
    private String officeCode; // Create a class for office, which stores the office information
    private final String createdBy;
    private final Date createdDate;
    
    
    public Employee(int employeeRef, Person person, String createdBy) {
        this.employeeRef = employeeRef;
        this.person = person;
        this.contracts = new ArrayList();
        this.addresses = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    /**
     * @return the employeeRef
     */
    public int getEmployeeRef() {
        return employeeRef;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return the officeCode
     */
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param officeCode the officeCode to set
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
    
    public Contract getContract() {
        return contracts.get(contracts.size()-1);
    }
    
    /**
     * @return the addresses
     */
    public List getContracts() {
        return Collections.unmodifiableList(contracts);
    }
    
    public void createContract(Contract contract) {
        if(!contracts.isEmpty()) {
            Agreement temp = contracts.get(contracts.size()-1);
            if(temp.isCurrent()) {
                temp.setActualEndDate(contract.getStartDate());
            }
        }
        contracts.add(contract);
    }
    
    public AddressUsage getAddressUsage() {
        return addresses.get(addresses.size()-1);
    }
    
    /**
     * @return the addresses
     */
    public List getAddresses() {
        return Collections.unmodifiableList(addresses);
    }
    
    public void createAddress(AddressUsage addressUsage) {
        if(!addresses.isEmpty()) {
            AddressUsage temp = addresses.get(addresses.size()-1);
            temp.setEndDate(addressUsage.getStartDate());
        }
        addresses.add(addressUsage);
    }
}
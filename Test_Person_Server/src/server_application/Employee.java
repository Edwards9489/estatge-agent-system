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
    private Person person;
    private ArrayList<Contract> contracts;
    private final String createdBy;
    private final Date createdDate;
    private String officeCode; // Create a class for office, which stores the office information
    private ArrayList<AddressUsage> addressUsages;
    
    
    public Employee(int employeeRef, Person person, String createdBy) {
        this.employeeRef = employeeRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        contracts = new ArrayList();
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
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
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
     * @return the officeCode
     */
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * @param officeCode the officeCode to set
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
    
    /**
     * @return the addressUsages
     */
    public List getAddressUsages() {
        return Collections.unmodifiableList(addressUsages);
    }
    
    public void createAddressUsage(AddressUsage addressUsage) {
        if(!addressUsages.isEmpty()) {
            AddressUsage temp = addressUsages.get(addressUsages.size()-1);
            temp.setEndDate(addressUsage.getStartDate());
        }
        addressUsages.add(addressUsage);
    }
    
    /**
     * @return the addressUsages
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
}
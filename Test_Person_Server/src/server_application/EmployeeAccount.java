/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class EmployeeAccount extends Account{
    private Contract contract;
    
    public EmployeeAccount(int employeeAccRef, String name, Date leaseAccStart, Contract contract, String createdBy) {
        super(employeeAccRef, name, leaseAccStart, createdBy);
        this.contract = contract;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
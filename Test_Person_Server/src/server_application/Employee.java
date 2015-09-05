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
public class Employee {
    private final int employeeRef;
    private Contract contract;
    private final String createdBy;
    private final Date createdDate;
    
    public Employee(int employeeRef, String createdBy) {
        this.employeeRef = employeeRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
}
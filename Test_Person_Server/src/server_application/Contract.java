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
public class Contract extends Agreement {
    private final Employee employee;
    
    public Contract(int contractRef, Date startDate, int length, String createdBy, Employee employee, Office office) {
        super(contractRef, startDate, length, createdBy);
        this.employee = employee;
    }

    /**
     * @return the jobRole
     */
    public Employee getEmployee() {
        return employee;
    }
}
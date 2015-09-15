/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Contract {
    
    private final int contractRef;
    private JobRole jobRole;
    private Date startDate;
    private Date expectedEndDate;
    private Date actualEndDate;
    private int length;
    private final String createdBy;
    private final Date createdDate;
    
    public Contract(int contractRef, String createdBy) {
        this.contractRef = contractRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
}
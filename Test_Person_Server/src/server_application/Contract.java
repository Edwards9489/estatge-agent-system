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
    private int length;
    
    public Contract(int contractRef) {
        this.contractRef = contractRef;
    }
}
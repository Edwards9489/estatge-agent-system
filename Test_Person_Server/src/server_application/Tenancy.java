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
public class Tenancy {
    // instance variables - replace the example below with your own
    private final int tenancy_ref;
    private Date ten_start_date;
    private Date ten_exp_end_date;
    private Date actual_end_date;
    private Property property;
    private String ten_type;
    
    public Tenancy(int tenRef) {
        tenancy_ref = tenRef;
    }
}
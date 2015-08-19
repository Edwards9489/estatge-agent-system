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
public class RentAccount {
    // instance variables - replace the example below with your own
    private final int rent_acc_ref;
    private String account_name;
    private Date rent_acc_start;
    private Date rent_acc_end;
    private double net_rent;
    private double balance;
    private Tenancy tenancy;
    private boolean arrears_ind;
    
    

    public RentAccount(int rent_ref) {
        rent_acc_ref = rent_ref;
    }
}
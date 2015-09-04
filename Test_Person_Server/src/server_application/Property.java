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
public class Property
{
    // instance variables - replace the example below with your own
    private final int propRef;
    private Address address;
    private Landlord landlord;
    private boolean fullManagement; // indicates if MSc Properties will manage all of the managerial affairs such as damage to prop, or just renting
    private Date acquired_date;
    private Date lease_end_date;
    private String prop_type;
    private String prop_sub_type;
    private String prop_status; // Occupied, Void, New, End etc
    
    /**
     * Constructor for objects of class Property
     */
    public Property(int propRef)
    {
        // initialise instance variables
        this.propRef = propRef;
    }
}
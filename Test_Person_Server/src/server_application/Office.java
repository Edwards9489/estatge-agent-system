/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.ArrayList;

/**
 *
 * @author Dwayne
 */
public class Office {
    private final String officeCode;
    private Address address;
    private ArrayList<Contact> contacts;
    
    
    public Office(String officeCode, Address address) {
        this.officeCode = officeCode;
        this.address = address;
    }
}
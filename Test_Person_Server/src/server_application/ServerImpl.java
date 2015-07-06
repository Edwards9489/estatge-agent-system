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
public class ServerImpl {
    
    private HashMap<String,Person> people = new HashMap<String,Person>();
    private HashMap<String,Address> addresses = new HashMap<String,Address>();
    private HashMap<String,Property> properties = new HashMap<String,Property>();
    private HashMap<String,PropertySubType> propertySubTypes = new HashMap<String,PropertySubType>();
    private HashMap<String,PropertyType> propertyTypes = new HashMap<String,PropertyType>();
    private HashMap<String,PropertyTypeValue> propertyTypeValues = new HashMap<String,PropertyTypeValue>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    //add a Chatter to the list
    public void register(String title, String forename, String surename, ) {
        chatters.put(c.getName(),c);
    }

    //remove a chatter
    public void unregister(Chatter c) {
        chatters.remove(c.getName());
    }
    
}
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
public class PropertyElement{

    private final int propElementRef;
    private final ElementImpl element;
    private String value;
    private Date startDate;
    private Date endDate;
    private boolean current;
    private final String createdBy;
    private final Date createdDate;

    public PropertyElement(int propElementRef, ElementImpl element, String createdBy) {
        this. element = element;
        this.propElementRef = propElementRef;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    // MIGHT NOT NEED THIS CLASS AS CAN JUST HAVE A HASHMAP OF PROPERTY ELEMENTS IN SERVERIMPL FOR MASTER LISTS OF ALL PROPERTY ELEMENTS
    // AND THEN A HASHMAP OF (PROPERTY) ELEMENTS WITHIN EACH PROPERTY FOR THEIR OWN PERSONAL PROPERTY ELEMENTS
    
    // REASSESED AND THINK I WILL NEED A LIST OF PROPERTYELEMENT IN THE PROPERTY WHICH ALLOWS A VALUE TO BE ASSIGNED
    // TO THE PROPERTY ELEMENT WHICH IS ASSOCIATED WITH THE PROPERTY (I.E RENT, NUMBER OF BEDROOMS)
    // THESE ELEMENTS WILL ONLY BE SELECTED FROM THE HASHMAP OF PROPERTY ELEMENTS WITHIN SERVERIMPL
}

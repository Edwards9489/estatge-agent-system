/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

/**
 *
 * @author Dwayne
 */
public class Element {
    private final String code;
    private String description;
    private boolean current;
    
    public Element(String code, String description) {
        this.code = code;
        this.description = description;
        current = true;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCurrent() {
        return current;
    }
    
    public void updateDescription(String description) {
        this.description = description;
    }
    
    public void updateCurrent() {
        current = !current;
    }
}
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
    private int tenancy_ref;
    private Date ten_start_date;
    private Date ten_exp_end_date;
    private Date actual_end_date;
    private Property property;
    private String ten_type;
    private HashMap<String, PropertyElement> charges;
    
    public Tenancy(int tenRef, Date startDate, Date expEndDate, Property property, String tenType) {
        this.tenancy_ref = tenRef;
        this.ten_start_date = startDate;
        this.ten_exp_end_date = expEndDate;
        this.property = property;
        this.ten_type = tenType;
    }

    /**
     * @return the tenancy_ref
     */
    public int getTenancy_ref() {
        return tenancy_ref;
    }

    /**
     * @param tenancy_ref the tenancy_ref to set
     */
    public void setTenancy_ref(int tenancy_ref) {
        this.tenancy_ref = tenancy_ref;
    }

    /**
     * @return the ten_start_date
     */
    public Date getTen_start_date() {
        return ten_start_date;
    }

    /**
     * @param ten_start_date the ten_start_date to set
     */
    public void setTen_start_date(Date ten_start_date) {
        this.ten_start_date = ten_start_date;
    }

    /**
     * @return the ten_exp_end_date
     */
    public Date getTen_exp_end_date() {
        return ten_exp_end_date;
    }

    /**
     * @param ten_exp_end_date the ten_exp_end_date to set
     */
    public void setTen_exp_end_date(Date ten_exp_end_date) {
        this.ten_exp_end_date = ten_exp_end_date;
    }

    /**
     * @return the actual_end_date
     */
    public Date getActual_end_date() {
        return actual_end_date;
    }

    /**
     * @param actual_end_date the actual_end_date to set
     */
    public void setActual_end_date(Date actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * @return the ten_type
     */
    public String getTen_type() {
        return ten_type;
    }

    /**
     * @param ten_type the ten_type to set
     */
    public void setTen_type(String ten_type) {
        this.ten_type = ten_type;
    }

    /**
     * @return the charges
     */
    public HashMap<String, PropertyElement> getCharges() {
        return charges;
    }
}
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
    private int tenancyRef;
    private Date tenStartDate;
    private Date tenExpEndDate;
    private Date actualEndDate;
    private Property property;
    private TenancyType tenType;
    private HashMap<String, PropertyElement> charges;
    
    public Tenancy(int tenRef, Date startDate, Date expEndDate, Property property, TenancyType tenType) {
        this.tenancyRef = tenRef;
        this.tenStartDate = startDate;
        this.tenExpEndDate = expEndDate;
        this.property = property;
        this.tenType = tenType;
    }

    /**
     * @return the tenancyRef
     */
    public int getTenancyRef() {
        return tenancyRef;
    }

    /**
     * @param tenancyRef the tenancyRef to set
     */
    public void setTenancyRef(int tenancyRef) {
        this.tenancyRef = tenancyRef;
    }

    /**
     * @return the tenStartDate
     */
    public Date getTenStartDate() {
        return tenStartDate;
    }

    /**
     * @param tenStartDate the tenStartDate to set
     */
    public void setTenStartDate(Date tenStartDate) {
        this.tenStartDate = tenStartDate;
    }

    /**
     * @return the tenExpEndDate
     */
    public Date getTenExpEndDate() {
        return tenExpEndDate;
    }

    /**
     * @param tenExpEndDate the tenExpEndDate to set
     */
    public void setTenExpEndDate(Date tenExpEndDate) {
        this.tenExpEndDate = tenExpEndDate;
    }

    /**
     * @return the actualEndDate
     */
    public Date getActual_end_date() {
        return actualEndDate;
    }

    /**
     * @param actual_end_date the actualEndDate to set
     */
    public void setActual_end_date(Date actual_end_date) {
        this.actualEndDate = actual_end_date;
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
     * @return the tenType
     */
    public TenancyType getTenType() {
        return tenType;
    }

    /**
     * @param tenType the tenType to set
     */
    public void setTenType(TenancyType tenType) {
        this.tenType = tenType;
    }

    /**
     * @return the charges
     */
    public HashMap<String, PropertyElement> getCharges() {
        return charges;
    }
}
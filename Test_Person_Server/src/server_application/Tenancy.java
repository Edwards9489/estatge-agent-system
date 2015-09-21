/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.PropertyInterface;
import interfaces.TenancyInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Tenancy extends Agreement implements TenancyInterface {
    private final ApplicationInterface application;
    private final PropertyInterface property;
    private Element tenType;
    private double rent; // amount tenant pays for rent per calendar month
    private double charges; // amount tenant pays for additional charges per calendar month
    private double expectedRevenue;
    
    public Tenancy(int tenRef, Date startDate, int length, String createdBy, PropertyInterface property, ApplicationInterface application, Element tenType) {
        super(tenRef, startDate, length, createdBy);
        this.property = property;
        this.application = application;
        this.tenType = tenType;
        this.rent = property.getRent();
        this.charges = property.getCharges();
        setRevenue();
    }

    /**
     * @return the property
     */
    public PropertyInterface getProperty() {
        return property;
    }
    
    /**
     * @return the application
     */
    public ApplicationInterface getApplication() {
        return application;
    }

    /**
     * @return the tenType
     */
    public Element getTenType() {
        return tenType;
    }

    /**
     * @return the rent
     */
    public double getRent() {
        return rent;
    }

    /**
     * @return the charges
     */
    public double getCharges() {
        return charges;
    }

    /**
     * @param tenType the tenType to set
     */
    public void setTenType(Element tenType) {
        this.tenType = tenType;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(double rent) {
        this.rent = rent;
        setRevenue();
    }

    /**
     * @param charges the charges to set
     */
    public void setCharges(double charges) {
        this.charges = charges;
        setRevenue();
    }
    
    private void setRevenue() {
        expectedRevenue = rent + charges;
    }
}
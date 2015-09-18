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
public class Tenancy extends Agreement {
    private final Application application;
    private final Property property;
    private TenancyType tenType;
    private double rent; // amount tenant pays for rent per calendar month
    private double charges; // amount tenant pays for additional charges per calendar month
    private double expectedRevenue;
    
    public Tenancy(int tenRef, Date startDate, int length, String createdBy, Property property, Application application, TenancyType tenType) {
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
    public Property getProperty() {
        return property;
    }
    
    /**
     * @return the application
     */
    public Application getApplication() {
        return application;
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
     * @return the rent
     */
    public double getRent() {
        return rent;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(double rent) {
        this.rent = rent;
        setRevenue();
    }

    /**
     * @return the charges
     */
    public double getCharges() {
        return charges;
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
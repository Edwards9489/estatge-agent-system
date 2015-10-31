/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.PropertyInterface;
import interfaces.TenancyInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Tenancy extends Agreement implements TenancyInterface {
    
    ///   VARIABLES   ///
    
    private final ApplicationInterface application;
    private final PropertyInterface property;
    private Element tenType;
    private double rent; // amount tenant pays for rent per calendar month
    private double charges; // amount tenant pays for additional charges per calendar month
    
    ///   CONSTRUCTORS ///
    
    public Tenancy(int tenRef, Date startDate, int length, int accountRef, PropertyInterface property, ApplicationInterface application, Element tenType, String officeCode, String createdBy, Date createdDate) {
        super(tenRef, application.getAppCorrName(), startDate, length, accountRef, createdBy, createdDate, officeCode);
        this.property = property;
        this.application = application;
        this.tenType = tenType;
        this.rent = property.getRent();
        this.charges = property.getCharges();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param tenType the tenType to set
     * @param modifiedBy
     */
    @Override
    public void setTenType(Element tenType, ModifiedByInterface modifiedBy) {
        this.tenType = tenType;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param rent the rent to set
     */
    @Override
    public void setRent(double rent) {
        this.rent = rent;
    }

    /**
     * @param charges the charges to set
     */
    @Override
    public void setCharges(double charges) {
        this.charges = charges;
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the property
     */
    @Override
    public PropertyInterface getProperty() {
        return property;
    }
    
    /**
     * @return the application
     */
    @Override
    public ApplicationInterface getApplication() {
        return application;
    }

    /**
     * @return the tenType
     */
    @Override
    public Element getTenType() {
        return tenType;
    }

    /**
     * @return the rent
     */
    @Override
    public double getRent() {
        return rent;
    }

    /**
     * @return the charges
     */
    @Override
    public double getCharges() {
        return charges;
    }
    
    @Override
    public double getExpectedRevenue() {
        return rent + charges;
    }
}
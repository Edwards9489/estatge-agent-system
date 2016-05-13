/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import classes.Utils;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.PropertyInterface;
import interfaces.TenancyInterface;
import java.math.BigDecimal;
import java.rmi.RemoteException;
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
    
    /**
     * Constructor for objects of class Tenancy
     * @param tenRef
     * @param startDate
     * @param length
     * @param accountRef
     * @param property
     * @param application
     * @param tenType
     * @param officeCode
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public Tenancy(int tenRef, Date startDate, int length, int accountRef, PropertyInterface property, ApplicationInterface application, Element tenType, String officeCode, String createdBy, Date createdDate) throws RemoteException {
        super(tenRef, application.getAppCorrName(), startDate, length, accountRef, createdBy, createdDate, officeCode);
        this.property = property;
        this.application = application;
        this.tenType = tenType;
        this.rent = property.getRent();
        this.charges = property.getCharges();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param tenType
     * @param modifiedBy
     */
    public void setTenType(Element tenType, ModifiedByInterface modifiedBy) {
        this.tenType = tenType;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param rent
     * @param modifiedBy
     */
    public void setRent(double rent, ModifiedByInterface modifiedBy) {
        this.rent = rent;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param charges
     * @param modifiedBy
     */
    public void setCharges(double charges, ModifiedByInterface modifiedBy) {
        this.charges = charges;
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return ref of property
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPropertyRef() throws RemoteException {
        return property.getPropRef();
    }
            
    /**
     * @return property
     * @throws java.rmi.RemoteException
     */
    @Override
    public PropertyInterface getProperty() throws RemoteException {
        return property;
    }
    
    /**
     * @return ref of application
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getApplicationRef() throws RemoteException {
        return application.getApplicationRef();
    }
    
    /**
     * @return application
     * @throws java.rmi.RemoteException
     */
    @Override
    public ApplicationInterface getApplication() throws RemoteException {
        return application;
    }

    /**
     * @return tenType
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getTenType() throws RemoteException {
        return tenType;
    }

    /**
     * @return rent
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getRent() throws RemoteException {
        return rent;
    }

    /**
     * @return charges
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getCharges() throws RemoteException {
        return charges;
    }
    
    /**
     * 
     * @return rent + charges
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getExpectedRevenue() throws RemoteException {
        BigDecimal decimalValue = Utils.convertDouble(rent + charges);
        return decimalValue.doubleValue();
    }
}
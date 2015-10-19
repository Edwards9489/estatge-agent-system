/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface PropertyInterface {
    void setLandlords(ArrayList<LandlordInterface> landlord, ModifiedByInterface modifiedBy);
    void setLeaseEndDate(Date leaseEndDate, ModifiedByInterface modifiedBy);
    void setPropStatus(String propStatus, ModifiedByInterface modifiedBy);
    void updateProperty(AddressInterface address, Date acquiredDate, Element propType, Element propSubType, ModifiedByInterface modifiedBy);
    int getPropRef();
    AddressInterface getAddress();
    List getLandlords();
    Date getAcquiredDate();
    Date getLeaseEndDate();
    Element getPropType();
    Element getPropSubType();
    String getPropStatus();
    List getPropertyElements();
    boolean isCurrent();
    double getRent();
    double getCharges();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
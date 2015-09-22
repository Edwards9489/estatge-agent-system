/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface AddressInterface {
    int getAddressRef();
    String getBuildingNumber();
    String getBuildingName();
    String getSubStreetNumber();
    String getSubStreet();
    String getStreetNumber();
    String getStreet();
    String getArea();
    String getTown();
    String getCountry();
    String getPostcode();
    String getCreatedBy();
    Date getCreatedDate();
    List getModifiedByList();
    ModifiedByInterface getModifiedBy();
    void setAddress(String buildingNumber, String buildingName,
            String subStreetNumber, String subStreet, String streetNumber,
            String street, String area, String town, String country, String postcode);
}
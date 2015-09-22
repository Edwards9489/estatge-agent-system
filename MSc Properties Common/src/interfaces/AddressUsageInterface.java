/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface AddressUsageInterface {
    String getAddressString();
    AddressInterface getAddress();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    String getCreatedBy();
    Date getCreatedDate();
    void setEndDate(Date endDate);
    void updateAddress(AddressInterface address, Date startDate);
}
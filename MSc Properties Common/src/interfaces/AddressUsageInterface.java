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
public interface AddressUsageInterface {
    int getAddressUsageRef();
    String getAddressString();
    AddressInterface getAddress();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean hasBeenModified();
    Note getNote();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
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
public interface PropertyElementInterface {
    int getPropertyElementRef();
    Element getElement();
    String getElementCode();
    String getStringValue();
    double getDoubleValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean isCharge();
    boolean isElementCode(String code);
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}

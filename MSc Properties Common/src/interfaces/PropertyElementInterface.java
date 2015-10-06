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
    void updatePropertyElement(Date startDate, String stringValue, double doubleValue, boolean charge, ModifiedByInterface modifiedBy);
    void setEndDate(Date endDate, ModifiedByInterface modifiedBy);
    Element getElement();
    String getElementCode();
    String getStringValue();
    double getDoubleValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean isCharge();
    boolean isElementCode(String code);
    String getLastModifiedBy();
    Date getLastModifiedDate();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}

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
public interface PropertyElementInterface {
    void updatePropertyElement(Date startDate, String stringValue, double doubleValue, boolean charge);
    void setEndDate(Date endDate);
    Element getElement();
    String getElementCode();
    String getStringValue();
    double getDoubleValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean isCharge();
    boolean isElementCode(String code);
    String getCreatedBy();
    Date getCreatedDate();
}

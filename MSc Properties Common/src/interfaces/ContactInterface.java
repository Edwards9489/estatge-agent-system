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
public interface ContactInterface {
    void setEndDate(Date endDate, ModifiedByInterface modifiedBy);
    void updateContact(Element contactType, String contactValue, Date startDate, ModifiedByInterface modifiedBy);
    int getContactRef();
    Element getContactType();
    String getContactTypeDescription();
    String getContactValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
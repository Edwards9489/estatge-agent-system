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
    int getContactRef();
    Element getContactType();
    String getContactTypeDescription();
    String getContactValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    Note getNote();
    String getComment();
    String getCreatedBy();
    Date getCreatedDate();
}
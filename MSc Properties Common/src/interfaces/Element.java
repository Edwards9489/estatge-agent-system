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
public interface Element {
    void updateElement(String description, boolean current, ModifiedByInterface modifiedBy);
    String getCode();
    String getDescription();
    boolean isCurrent();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
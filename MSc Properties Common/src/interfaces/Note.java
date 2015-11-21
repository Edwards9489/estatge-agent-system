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
public interface Note {
    String getCreatedBy();
    Date getCreatedDate();
    ModifiedByInterface getLastModification();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    List<ModifiedByInterface> getModifiedBy();
    String getNote();
    int getRef();
    boolean hasBeenModified();
}
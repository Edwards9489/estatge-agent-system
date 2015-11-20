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
public interface LandlordInterface {
    int getLandlordRef();
    PersonInterface getPerson();
    int getPersonRef();
    boolean hasLease(int ref);
    List<LeaseInterface> getLeases();
    LeaseInterface getLease(int ref);
    boolean hasNote(int ref);
    Note getNote(int ref);
    List<Note> getNotes();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
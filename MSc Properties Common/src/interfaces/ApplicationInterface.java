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
public interface ApplicationInterface {
    int getApplicationRef();
    String getAppCorrName();
    Date getAppStartDate();
    Date getAppEndDate();
    String getAppStatusCode();
    AddressUsageInterface getCurrentApplicationAddress();
    String getCurrentApplicationAddressString();
    List<AddressUsageInterface> getApplicationAddressess();
    boolean isAppInterestedFlag();
    List<InvolvedPartyInterface> getHousehold();
    List<PropertyInterface> getPropertiesInterestedIn();
    int getTenancyRef();
    boolean isCurrent();
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
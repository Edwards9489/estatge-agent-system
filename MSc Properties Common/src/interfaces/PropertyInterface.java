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
public interface PropertyInterface {
    int getPropRef();
    AddressInterface getAddress();
    List<LandlordInterface> getLandlords();
    Date getAcquiredDate();
    Date getLeaseEndDate();
    Element getPropType();
    Element getPropSubType();
    String getPropStatus();
    List<PropertyElementInterface> getPropertyElements();
    boolean isCurrent();
    double getRent();
    double getCharges();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    boolean hasNote(int ref);
    Note getNote(int ref);
    List<Note> getNotes();
    boolean hasDocument(int ref);
    boolean hasDocument(String fileName);
    Document getDocument(int ref);
    List<Document> getDocuments();
    String getCreatedBy();
    Date getCreatedDate();
}
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
public interface PersonInterface {
    int getPersonRef();
    Element getTitle();
    String getForename();
    String getMiddleNames();
    String getSurname();
    String getName();
    Date getDateOfBirth();
    String getNI();
    Element getGender();
    boolean isOver18();
    Element getMaritalStatus();
    Element getEthnicOrigin();
    Element getLanguage();
    Element getNationality();
    Element getSexuality();
    Element getReligion();
    AddressUsageInterface getCurrentAddress();
    String getCurrentAddressString();
    List<AddressUsageInterface> getApplicationAddressess();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    boolean hasNote(int ref);
    Note getNote(int ref);
    List<Note> getNotes();
    String getCreatedBy();
    Date getCreatedDate();
    boolean hasContact(int contactRef);
    List<ContactInterface> getContacts();
    List<AddressUsageInterface> getAddresses();
    AddressUsageInterface getLastAddress();
}
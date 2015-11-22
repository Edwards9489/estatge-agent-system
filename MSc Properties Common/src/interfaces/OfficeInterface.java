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
public interface OfficeInterface {
    String getOfficeCode();
    AddressInterface getAddress();
    Date getStartDate();
    Date getEndDate();
    boolean hasAgreement(int ref);
    AgreementInterface getAgreement(int ref);
    List<AgreementInterface> getAgreements();
    boolean hasAccount(int ref);
    AccountInterface getAccount(int ref);
    List<AccountInterface> getAccounts();
    ContactInterface getContact(int ref);
    List<ContactInterface> getContacts();
    boolean hasContact(int ref);
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
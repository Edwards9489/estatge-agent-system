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
    List<AgreementInterface> getAgreements();
    List<AccountInterface> getAccounts();
    List<ContactInterface> getContacts();
    boolean isCurrent();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
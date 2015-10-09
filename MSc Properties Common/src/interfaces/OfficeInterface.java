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
    void setStartDate(Date startDate, ModifiedByInterface modifiedBy);
    void setEndDate(Date endDate, ModifiedByInterface modifiedBy);
    void addAgreement(AgreementInterface agreement, ModifiedByInterface modifiedBy);
    void addAccount(AccountInterface account, ModifiedByInterface modifiedBy);
    String getOfficeCode();
    AddressInterface getAddress();
    Date getStartDate();
    Date getEndDate();
    List getContacts();
    List getAgreements();
    List getAccounts();
    boolean isCurrent();
    boolean canCloseOffice();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}
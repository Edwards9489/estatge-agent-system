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
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);
    void addAgreement(AgreementInterface agreement);
    void addAccount(AccountInterface account);
    String getOfficeCode();
    AddressInterface getAddress();
    Date getStartDate();
    Date getEndDate();
    List getContacts();
    List getAgreements();
    List getAccounts();
    boolean getCurrent();
    boolean canCloseOffice();
    String getCreatedBy();
    Date getCreatedDate();
}
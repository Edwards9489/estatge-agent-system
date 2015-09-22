/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface AgreementInterface {
    int getAgreementRef();
    String getAgreementName();
    Date getStartDate();
    Date getExpectedEndDate();
    Date getActualEndDate();
    int getLength();
    String getOfficeCode();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isCurrent();
    void setActualEndDate(Date endDate);
    void updateAgreement(Date startDate, int length);
}
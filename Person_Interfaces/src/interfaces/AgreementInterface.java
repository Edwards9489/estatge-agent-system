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
    Date getStartDate();
    Date getExpectedEndDate();
    Date getActualEndDate();
    int getLength();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isCurrent();
}
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
    String getCurrentApplicationAddressString();
    List getApplicationAddressess();
    boolean isAppInterestedFlag();
    List getHousehold();
    List getPropertiesInterestedIn();
    TenancyInterface getTenancy();
    boolean isCurrent();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
    void updateApplication(String name, Date startDate, ModifiedByInterface modifiedBy);
    void setEndDate(Date endDate, ModifiedByInterface modifiedBy);
}

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
    String getAppStatusCode();
    boolean isAppInterestedFlag();
    List getHousehold();
    List getPropertiesInterestedIn();
    TenancyInterface getTenancy();
    String getCreatedBy();
    Date getCreatedDate();
}

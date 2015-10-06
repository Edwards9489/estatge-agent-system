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
public interface JobRoleBenefitInterface {
    void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit, ModifiedByInterface modifiedBy);
    Element getElement();
    String getElementCode();
    String getStringValue();
    double getDoubleValue();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isSalaryBenefit();
}
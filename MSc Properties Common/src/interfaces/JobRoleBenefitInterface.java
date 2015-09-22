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
public interface JobRoleBenefitInterface {
    void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit);
    Element getElement();
    String getElementCode();
    String getStringValue();
    double getDoubleValue();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isSalaryBenefit();
}
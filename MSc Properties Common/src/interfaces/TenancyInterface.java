/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Dwayne
 */
public interface TenancyInterface extends AgreementInterface {
    int getPropertyRef();
    PropertyInterface getProperty();
    int getApplicationRef();
    ApplicationInterface getApplication();
    Element getTenType();
    double getRent();
    double getCharges();
    double getExpectedRevenue();
}
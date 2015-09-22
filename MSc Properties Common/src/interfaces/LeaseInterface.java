/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface LeaseInterface extends AgreementInterface {
    void addLandlord(LandlordInterface landlord);
    List getLandlords();
    PropertyInterface getProperty();
    int getPropertyRef();
    boolean isFullManagement();
    boolean isAlreadyLandlord(LandlordInterface landlord);
    double getExpenditure();
}

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
public interface RentAccountInterface extends AccountInterface {
    double getRent();
    TenancyInterface getTenancy();
    int getTenancyRef();
}

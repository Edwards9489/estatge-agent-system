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
public interface EmployeeAccountInterface extends AccountInterface {
    ContractInterface getContract();
    int getContractRef();
    double getSalary();
}
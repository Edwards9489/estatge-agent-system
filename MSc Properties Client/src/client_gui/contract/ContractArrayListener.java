/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contract;

import interfaces.ContractInterface;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface ContractArrayListener {
    void arrayOmitted(List<ContractInterface> array);
}

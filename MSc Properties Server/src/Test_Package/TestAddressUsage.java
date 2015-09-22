/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import java.util.Date;
import server_application.Address;
import server_application.AddressUsage;

/**
 *
 * @author Dwayne
 */
public class TestAddressUsage {
    public static void main(String[] args) {
        System.out.println("********************Running Address Usage Test********************");
        AddressInterface address = new Address(1, "12", "Kestrel House", "1", "The Close", "1", "The Ride", "Enfield", "London", "England", "EN3 4EN", "DEDWARDS");
        AddressUsageInterface test1 = new AddressUsage(address, new Date(), "DEDWARDS");
        System.out.println(test1.toString());
    }
}
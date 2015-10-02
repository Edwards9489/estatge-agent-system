/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import server_application.Address;

/**
 *
 * @author Dwayne
 */
public class TestAddress {
    public static void main(String[] args) {
        System.out.println("********************Running Address Test********************");
        String temp;
        System.out.println("****** Creating Test Address");
        Address test1 = new Address(1, "1", "Kestrel House", "1", "Grilse Close", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", "DEDWARDS");
        //Address test1 = new Address(1, "", "", "", "", "1", "Cavendish Road", "Edmonton", "London", "England", "N9 0DJ", "DEDWARDS");
        
        System.out.println(test1.getAddressRef());
        System.out.println(test1.getBuildingNumber());
        System.out.println(test1.getBuildingName());
        System.out.println(test1.getSubStreetNumber());
        System.out.println(test1.getSubStreet());
        System.out.println(test1.getStreetNumber());
        System.out.println(test1.getStreet());
        System.out.println(test1.getArea());
        System.out.println(test1.getTown());
        System.out.println(test1.getCountry());
        System.out.println(test1.getPostcode());
        System.out.println(test1.getLastModifiedBy());
        System.out.println(test1.getLastModifiedDate());
        System.out.println(test1.getCreatedBy());
        System.out.println(test1.getCreatedDate());
        System.out.println(test1.getModifiedBy());
        
        System.out.println(test1);
        
        
        
    }
}
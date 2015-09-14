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
        
        System.out.println("\nCreating address 1: 12 Kestrel House, 1 Brook Close, 5 Brook Crescent, Edmonton, London, England, N9 0DJ");
        
        // Creating Address1
        Address address1 = new Address(1, "N9 0DJ", "DEDWARDS");
        System.out.println(address1);
        System.out.println(address1.getmodifiedBy());
        
        // Checking postocde
        System.out.println(address1.getPostcode());
        
        // Checking created by
        System.out.println(address1.getCreatedBy());
        
        // Checking created date
        System.out.println(address1.getCreatedDate());
        
        System.out.println("\n\nSetting Address1 building number\n");
        System.out.println(address1.isBuildingNumberNull());
        address1.setBuildingNumber("12");
        System.out.println(address1.isBuildingNumberNull());
        System.out.println(address1.getBuildingNumber());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 building name\n");
        System.out.println(address1.isBuildingNameNull());
        address1.setBuildingName("Kestrel House");
        System.out.println(address1.isBuildingNameNull());
        System.out.println(address1.getBuildingName());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 sub street number\n");
        System.out.println(address1.isSubStreetNumberNull());
        address1.setSubStreetNumber("1");
        System.out.println(address1.isSubStreetNumberNull());
        System.out.println(address1.getSubStreetNumber());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 sub street\n");
        System.out.println(address1.isSubStreetNull());
        address1.setSubStreet("Brook Close");
        System.out.println(address1.isSubStreetNull());
        System.out.println(address1.getSubStreet());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 street number\n");
        System.out.println(address1.isStreetNumberNull());
        address1.setStreetNumber("5");
        System.out.println(address1.isStreetNumberNull());
        System.out.println(address1.getStreetNumber());
        
        System.out.println("\n\nSetting Address1 street\n");
        System.out.println(address1.isStreetNull());
        address1.setStreet("Brook Crescent");
        System.out.println(address1.isStreetNull());
        System.out.println(address1.getStreet());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 area\n");
        System.out.println(address1.isAreaNull());
        address1.setArea("Edmonton");
        System.out.println(address1.isAreaNull());
        System.out.println(address1.getArea());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 town\n");
        System.out.println(address1.isTownNull());
        address1.setTown("London");
        System.out.println(address1.isTownNull());
        System.out.println(address1.getTown());
        System.out.println(address1.getmodifiedBy());
        
        System.out.println("\n\nSetting Address1 country\n");
        System.out.println(address1.isCountryNull());
        address1.setCountry("England");
        System.out.println(address1.isCountryNull());
        System.out.println(address1.getCountry());
        System.out.println(address1.getmodifiedBy());
        
        // Printing out Address1
        System.out.println(address1);
        
        
        System.out.println("\n Creating address 2: 100 Swaythling Close, 10 Hertford Road, Edmonton, London, England, N18 2TX");
        
        // Creating Address2
        Address address2 = new Address(2, "N18 2TX", "DEDWARDS");
        System.out.println(address2);
        
        // Checking postocde
        System.out.println(address2.getPostcode());
        
        // Checking created by
        System.out.println(address2.getCreatedBy());
        
        // Checking created date
        System.out.println(address2.getCreatedDate());
        
        System.out.println("\n\nSetting Address2 sub street number\n");
        System.out.println(address2.isSubStreetNumberNull());
        address2.setSubStreetNumber("100");
        System.out.println(address2.isSubStreetNumberNull());
        System.out.println(address2.getSubStreetNumber());
        
        System.out.println("\n\nSetting Address2 sub street\n");
        System.out.println(address2.isSubStreetNull());
        address2.setSubStreet("Swaythling Close");
        System.out.println(address2.isSubStreetNull());
        System.out.println(address2.getSubStreet());
        
        System.out.println("\n\nSetting Address2 street number\n");
        System.out.println(address2.isStreetNumberNull());
        address2.setSubStreetNumber("10");
        System.out.println(address2.isStreetNumberNull());
        System.out.println(address2.getStreetNumber());
        
        System.out.println("\n\nSetting Address2 street\n");
        System.out.println(address2.isStreetNull());
        address2.setStreet("Hertford Road");
        System.out.println(address2.isStreetNull());
        System.out.println(address2.getStreet());
        
        System.out.println("\n\nSetting Address2 area\n");
        System.out.println(address2.isAreaNull());
        address2.setArea("Edmonton");
        System.out.println(address2.isAreaNull());
        System.out.println(address2.getArea());
        
        System.out.println("\n\nSetting Address2 town\n");
        System.out.println(address2.isTownNull());
        address2.setTown("London");
        System.out.println(address2.isTownNull());
        System.out.println(address2.getTown());
        
        System.out.println("\n\nSetting Address2 country\n");
        System.out.println(address2.isCountryNull());
        address2.setCountry("England");
        System.out.println(address2.isCountryNull());
        System.out.println(address2.getCountry());
        
        // Printing out Address2
        System.out.println(address2);
        
        System.out.println("\n Creating address 3: 75 Shorpshire House, 1 Bell Lane, Enfield, London, England, EN3 4EN");
        
        // Creating Address3
        Address address3 = new Address(2, "75", "Shorpshire House", "1", "Bell Lane", "", "Enfield", "London", "England", "EN3 3EN", "DEDWARDS");
        System.out.println(address3);
        
        // Checking postocde
        System.out.println(address3.getPostcode());
        
        System.out.println("\n\nIncorrect Postcode entered so set to correct postcode\n");
        address3.setPostcode("EN3 4EN");
        System.out.println(address3.getmodifiedBy());
        
        // Checking created by
        System.out.println(address3.getCreatedBy());
        
        // Checking created date
        System.out.println(address3.getCreatedDate());
        
        // Printing out Address3
        System.out.println(address3);
    }
}
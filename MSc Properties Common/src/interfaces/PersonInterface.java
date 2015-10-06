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
public interface PersonInterface {
    int getPersonRef();
    String getForename();
    String getMiddleNames();
    String getSurname();
    String getName();
    Date getDateOfBirth();
    String getNI();
    Element getGender();
    boolean isOver18();
    Element getMaritalStatus();
    Element getEthnicOrigin();
    Element getLanguage();
    Element getNationality();
    Element getSexuality();
    Element getReligion();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
    void createContact(ContactInterface contact, ModifiedByInterface modifiedBy);
    void createAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy);
    void updatePerson(Element title, String forename, String middleNames, String surname, Date dateOfBirth, String nationalInsurance, Element gender,
            Element maritalStatus, Element ethnicOrigin, Element language, Element nationality, Element sexuality, Element religion, ModifiedByInterface modifiedBy);
}
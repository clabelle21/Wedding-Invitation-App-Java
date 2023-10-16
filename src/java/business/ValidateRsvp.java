/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import transferObjects.Guest;

/**
 *
 * @author chris
 */
public class ValidateRsvp {
    
    public static void validate(Guest rsvp) throws ValidationException {
        String errorMessage = "";
        if(isNull(rsvp.getLastName()) || !stringValidates(rsvp.getLastName(), "[A-Za-z\\s]+")) {
            errorMessage += "Invalid value for Last Name`";
        }
        if(isNull(rsvp.getFirstName()) || !stringValidates(rsvp.getFirstName(), "[A-Za-z\\s]+")) {
            errorMessage += "Invalid value for First Name`";
        }
        if(isNull(rsvp.isAttending())) {
            errorMessage += "You must select Attending / Absent`";
        }
        if(!isNull(rsvp.getEmail())) {
            if(!stringValidates(rsvp.getEmail(), "[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,4}")) {
                errorMessage += "Invalid value for Email`";
            } 
        }
        if(!isNull(rsvp.getOtherAllergy())) {
            if(!stringValidates(rsvp.getOtherAllergy(), "[^*/]+")) {
                errorMessage += "Other allergies cannot contarin characters: * or /`";
            }
        }
        if(!isNull(rsvp.getId())) {
            if(!stringValidates(rsvp.getId(), "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")) {
                errorMessage += "Invalid id format`";
            }
        }
        if(!errorMessage.equals("")) {
            throw new ValidationException(errorMessage);
        }
    }
    
    private static boolean stringValidates(String s, String regex) {
        return (s.trim().matches(regex));
    }
    
    private static boolean isNull(Object o) {
        return o == null;
    }
}

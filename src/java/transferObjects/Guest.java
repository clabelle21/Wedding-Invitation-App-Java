/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferObjects;

/**
 *
 * @author chris
 */
public class Guest {

    private String id;
    private Integer guestType;
    private String lastName;
    private String firstName;
    private boolean attending;
    private boolean vegetarian;
    private boolean vegan;
    private boolean gluten;
    private String otherAllergy;
    private String email;
    
    public Guest(String id, Integer guestType, String lastName, String firstName, 
            Boolean attending, Boolean vegetarian, Boolean vegan, Boolean gluten, 
            String otherAllergy, String email) {
        this.id = id;
        this.guestType = guestType;
        this.lastName = lastName;
        this.firstName = firstName;
        setAttending(attending);
        setVegetarian(vegetarian);
        setVegan(vegan);
        setGluten(gluten);
        this.otherAllergy = otherAllergy;
        this.email = email;
    }
    
    public String getId() {
        return id;
    }

    public int getGuestType() {
        return guestType;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isAttending() {
        return attending;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isGluten() {
        return gluten;
    }

    public String getOtherAllergy() {
        return otherAllergy;
    }

    public String getEmail() {
        return email;
    }
    
    private void setAttending(Boolean attending) {
        try {
            this.attending = attending;
        }
        catch(NullPointerException e) {}
    }
    
    private void setVegan(Boolean vegan) {
        try {
            this.vegan = vegan;
        }
        catch(NullPointerException e) {}
    } 
    
    private void setGluten(Boolean gluten) {
        try {
            this.gluten = gluten;
        }
        catch(NullPointerException e) {}
    } 
    
    private void setVegetarian(Boolean vegetarian) {
        try {
            this.vegetarian = vegetarian;
        }
        catch(NullPointerException e) {}
    } 
}

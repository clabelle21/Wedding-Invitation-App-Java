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
public class Confirmation {

    private String id;
    private String guestId;
    private String confirmationDttm;
    
    public Confirmation(String id, String guestId, String confirmationDttm) {
        this.id = id;
        this.guestId = id;
        this.confirmationDttm = confirmationDttm;
    }
    
    public String getId() {
        return id;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getConfirmationDttm() {
        return confirmationDttm;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christopher Labelle
 */
public class ValidGuestSession {
    
    private HttpSession session;
    
    public ValidGuestSession(HttpSession session) {
        this.session = session;
    }
    
    public Object getAttribute(String key) {
        return this.session.getAttribute(key);
    }
    
    public void setAttribute(String key, Object value) {
        this.session.setAttribute(key, value);
    }
    
    public void removeAttribute(String key) {
        try {
           this.session.removeAttribute(key); 
        }
        catch(NullPointerException e) {
            System.out.println("Attribute '" + key + "' does not exist.");
        } 
    }
    
    public void endSession() {
        this.session.invalidate();
    }
    
    public void setSessionAttributes() {
        
    }
}

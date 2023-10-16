/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import transferObjects.Guest;
import transferObjects.Confirmation;

/**
 *
 * @author chris
 */
public class Dao {
    
    
    // ------- REGISTER GUEST ----------------------------------------------
    private static final String INSERT_GUEST_SQL = 
            "INSERT INTO GUESTS (id, last_name, first_name, attending,"
            + " vegetarian, vegan, gluten, other_allergy, email)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_GUEST_SQL = 
            "update guests set last_name = ?, first_name = ?," + 
            " attending = ?, vegetarian = ?, vegan = ?, gluten = ?," + 
            " other_allergy = ?, email = ? where id = ?";
    
    private static final String GET_AVAILABLE_CONFIRMATION_ID_SQL = 
            "select id from guest_confirmation where guest_id IS NULL order by confirmation_dttm limit 1";
    
    private static final String CONFIRM_GUEST_SQL = 
            " update guest_confirmation set guest_id = ?" + 
            " where id = ?";
    
    private static final String GET_CONFIRMATION_ID_SQL = 
            "select * from guest_confirmation where guest_id = ?";
    // ------- END REGISTER GUEST -------------------------------------------
    
    public Dao() {
    }
    
    private static final String DOES_GUEST_EXIST_SQL =
            "select id, guest_type, last_name, first_name, attending, "
            + "vegetarian, vegan, gluten, other_allergy, email FROM guests WHERE id = ?";
	
    private static final String IS_GUEST_CONFIRMED_SQL =
            "select id, guest_id, confirmation_dttm from guest_confirmation WHERE guest_id = ?";
    
    public Guest guestExists(String uuid) {
        Guest rsvp = null;
        try(Connection conx = new DataSource().createConnection();
        PreparedStatement pstmt = conx.prepareStatement(DOES_GUEST_EXIST_SQL);) {
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            if(rs.first()) {
               rsvp = new Guest(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getBoolean(5),
                    rs.getBoolean(6),
                    rs.getBoolean(7),
                    rs.getBoolean(8),
                    rs.getString(9),
                    rs.getString(10)
                ); 
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rsvp;
    }
	
    public boolean guestConfirmed(String uuid) {
        boolean confirmed = false;
        try(Connection conx = new DataSource().createConnection();
        PreparedStatement pstmt = conx.prepareStatement(IS_GUEST_CONFIRMED_SQL);) {
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            if(rs.isBeforeFirst()) {confirmed = true;}
        } catch (SQLException e) { e.printStackTrace();}
        return confirmed;
    }
    
    
    public String addGuest(Guest rsvp) {
        String uuid = UUID.randomUUID().toString();
        try(Connection con = new DataSource().createConnection();
        PreparedStatement pstmt = con.prepareStatement(INSERT_GUEST_SQL);) {
            pstmt.setString(1, uuid);
            pstmt.setString(2, rsvp.getLastName().trim());
            pstmt.setString(3, rsvp.getFirstName().trim());
            pstmt.setBoolean(4, rsvp.isAttending());
            pstmt.setBoolean(5, rsvp.isVegetarian());
            pstmt.setBoolean(6, rsvp.isVegan());
            pstmt.setBoolean(7, rsvp.isGluten());
            if(rsvp.getOtherAllergy() == null || rsvp.getOtherAllergy().trim().isEmpty()) {pstmt.setNull(8, java.sql.Types.NULL);}
            else {pstmt.setString(8, rsvp.getOtherAllergy().trim());}
            if(rsvp.getEmail() == null || rsvp.getEmail().trim().isEmpty()) {pstmt.setNull(9, java.sql.Types.NULL);}
            else {pstmt.setString(9, rsvp.getEmail().trim());}
            pstmt.execute();
        } catch (SQLException e) {
            uuid = null;
            e.printStackTrace();
        }
        return uuid;
    }
     
    public void updateGuest(Guest rsvp, String uuid) {
        try(Connection con = new DataSource().createConnection();
        PreparedStatement pstmt = con.prepareStatement(UPDATE_GUEST_SQL);) {
            pstmt.setString(1, rsvp.getLastName().trim());
            pstmt.setString(2, rsvp.getFirstName().trim());
            pstmt.setBoolean(3, rsvp.isAttending());
            pstmt.setBoolean(4, rsvp.isVegetarian());
            pstmt.setBoolean(5, rsvp.isVegan());
            pstmt.setBoolean(6, rsvp.isGluten());
            if(rsvp.getOtherAllergy() == null || rsvp.getOtherAllergy().trim().isEmpty()) {pstmt.setNull(7, java.sql.Types.NULL);}
            else {pstmt.setString(7, rsvp.getOtherAllergy().trim());}
            if(rsvp.getEmail() == null || rsvp.getEmail().trim().isEmpty()) {pstmt.setNull(8, java.sql.Types.NULL);}
            else {pstmt.setString(8, rsvp.getEmail().trim());}
            pstmt.setString(9, uuid);
            pstmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public String geAvailabletConfirmationId() {
        String confirmationId = null;
        try(Connection con = new DataSource().createConnection();
        PreparedStatement pstmt = con.prepareStatement(GET_AVAILABLE_CONFIRMATION_ID_SQL);) {
            ResultSet rs = pstmt.executeQuery();
            rs.first();
            confirmationId = rs.getString(1);
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return confirmationId;
    }
    
    public void confirmGuest(String uuid, String confirmationId) {
        try(Connection con = new DataSource().createConnection();
        PreparedStatement pstmt = con.prepareStatement(CONFIRM_GUEST_SQL);) {
            pstmt.setString(1, uuid);
            pstmt.setString(2, confirmationId);
            pstmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public Confirmation getConfirmation(String uuid) {
        Confirmation confirmation = null;
        try(Connection con = new DataSource().createConnection();
        PreparedStatement pstmt = con.prepareStatement(GET_CONFIRMATION_ID_SQL);) {
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            rs.first();
            confirmation = new Confirmation(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
            );
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return confirmation;
    } 
}

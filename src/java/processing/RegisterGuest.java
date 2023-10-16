package processing;

import business.ValidateRsvp;
import business.ValidationException;
import dataaccess.Dao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.ValidGuestSession;
import transferObjects.Confirmation;
import transferObjects.Guest;

/**
 *
 * @author 29751
 */
public class RegisterGuest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
         
        response.setContentType("text/html;charset=UTF-8");
        
        String lastName = this.trimParameter(request.getParameter("lastName"));
        String firstName = this.trimParameter(request.getParameter("firstName"));
        String attending = this.trimParameter(request.getParameter("isAttending")) == null ? "" : this.trimParameter(request.getParameter("isAttending"));
        
        boolean vegetarian = false;
        boolean vegan = false;
        boolean gluten = false;
        String checkbox = this.trimParameter(request.getParameter("vegetarian"));
        if(checkbox != null) {vegetarian = checkbox.equals("vegetarian");}
        checkbox = this.trimParameter(request.getParameter("vegan"));
        if(checkbox != null) {vegan = checkbox.equals("vegan");}
        checkbox = this.trimParameter(request.getParameter("gluten"));
        if(checkbox != null) {gluten = checkbox.equals("gluten");}
        
        String otherAllergy = this.trimParameter(request.getParameter("otherAllergy"));
        String email = this.trimParameter(request.getParameter("email"));
        ValidGuestSession session = new ValidGuestSession(request.getSession());
        
        String guestId = null;
        try {
            guestId = session.getAttribute("uuid").toString();
        }
        catch(NullPointerException e) {}
        
        Guest rsvp = new Guest(
                guestId,
                null, // guest type
                lastName == null || lastName.isEmpty() ? null : lastName,
                firstName == null || firstName.isEmpty() ? null : firstName,
                attending.trim().equals("attending"),
                vegetarian,
                vegan,
                gluten,
                otherAllergy == null || otherAllergy.isEmpty() ? null : otherAllergy,
                email == null || email.isEmpty() ? null : email
        );
        
        session.setAttribute("uuid", guestId);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("attending", attending);
        session.setAttribute("vegetarian", vegetarian);
        session.setAttribute("vegan", vegan);
        session.setAttribute("gluten", gluten);
        session.setAttribute("otherAllergy", otherAllergy);
        session.setAttribute("email", email);
        
        try {
            
            ValidateRsvp.validate(rsvp);
            Dao dao = new Dao();
            if(guestId != null) {
                dao.updateGuest(rsvp, guestId);
            }
            else {
                guestId = dao.addGuest(rsvp);
            }
            dao.confirmGuest(guestId, dao.geAvailabletConfirmationId()); 
           
            Confirmation confirmation = dao.getConfirmation(guestId);
            
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("attending", attending);
            request.setAttribute("confirmationId", confirmation.getId());
            try {
                request.getRequestDispatcher("confirm_rsvp.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(RegisterGuest.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.endSession();
        }
        catch(ValidationException e) {
            System.out.println(e.getMessage());
            session.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect("rsvp.jsp");
        }
    }
    
    private String trimParameter(String s) {
        if(s != null) {s = s.trim();}
        return s;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

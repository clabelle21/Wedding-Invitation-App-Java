/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import dataaccess.Dao;
import dataaccess.DataSource;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ValidGuestSession;
import transferObjects.Guest;

/**
 *
 * @author chris
 */
//@WebServlet(name = "ValidateGuest", urlPatterns = {"/ValidateGuest"})
public class ValidateGuest extends HttpServlet {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String uuid = request.getParameter("id");
        if(uuid != null && !uuid.equals("")) {
            
            Dao dao = new Dao();
            Guest guest = dao.guestExists(uuid);
            
            if(guest != null) {
                if(!dao.guestConfirmed(uuid)) {
                    ValidGuestSession session = new ValidGuestSession(request.getSession());
                    session.setAttribute("uuid", guest.getId());
                    session.setAttribute("guestType", guest.getGuestType());
                    session.setAttribute("lastName", guest.getLastName());
                    session.setAttribute("firstName", guest.getFirstName());
                    session.setAttribute("attending", guest.isAttending());
                }
            }
        }
        response.sendRedirect("rsvp.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

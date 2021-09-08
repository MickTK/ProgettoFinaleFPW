package it.unica.avis.servlet;

import it.unica.avis.model.Medico;
import it.unica.avis.model.MedicoFactory;
import it.unica.avis.model.SessioneFactory;
import it.unica.avis.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "NuovoMedicoServlet", urlPatterns = {"/NuovoMedicoServlet"})
public class NuovoMedicoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("user_amm") != null){
            try{
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String cellulare = request.getParameter("cellulare");
            String email = request.getParameter("email");
            String asl = request.getParameter("asl");
            
            Utils.checkString(nome, 1, 50);
            Utils.checkString(cognome, 1, 50);
            Utils.checkString(cellulare, 8, 20);
            
            try{
                Integer.parseInt(cellulare);
            }
            catch(Exception e){
                throw new Exception(" numero di telefono errato");
            }
            
            Utils.checkString(email, 6, 50);
            Utils.checkEmail(email);
            Utils.checkString(asl, 1, 50);
            
            MedicoFactory.getInstance().setMedico(nome, cognome, cellulare, email, asl);
            
            request.setAttribute("riscontro", "positivo");
            
            response.setContentType("application/json");
            response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            request.getRequestDispatcher("confermaJSON.jsp").forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("riscontro", e.getMessage());
                request.getRequestDispatcher("confermaJSON.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect("./index");
        }
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

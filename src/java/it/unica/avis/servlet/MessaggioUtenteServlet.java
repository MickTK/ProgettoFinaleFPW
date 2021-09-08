package it.unica.avis.servlet;

import it.unica.avis.model.MessaggioFactory;
import it.unica.avis.model.Utente;
import it.unica.avis.model.UtenteFactory;
import it.unica.avis.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MessaggioUtenteServlet", urlPatterns = {"/messaggioUtente"})
public class MessaggioUtenteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        try{
            String user = (String) session.getAttribute("user");
            String messaggio = request.getParameter("messaggio");

            Utente utente = UtenteFactory.getInstance().getUtenteByUsername(user);
            
            MessaggioFactory.getInstance().setMessaggioUtente(Utils.dataCorrenteCompleta(), messaggio, utente);
            
            request.setAttribute("riscontro", "positivo");

            response.setContentType("application/json");
            response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            request.getRequestDispatcher("messaggioUtenteJSON.jsp").forward(request, response);
        }
        catch(Exception e){
            request.setAttribute("riscontro", e.getMessage());
            request.getRequestDispatcher("messaggioUtenteJSON.jsp").forward(request, response);
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

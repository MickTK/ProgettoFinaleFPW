package it.unica.avis.servlet;

import it.unica.avis.model.Donazione;
import it.unica.avis.model.DonazioneFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
import it.unica.avis.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CronologiaUtenteServlet", urlPatterns = {"/cronologia"})
public class CronologiaUtenteServlet extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession(false); 
       
       try{
           if(session != null && session.getAttribute("user") != null){
            String user = (String) session.getAttribute("user");
            List<Prenotazione> p = PrenotazioneFactory.getInstance().getPrenotazionePerUtente(user);
            List<Donazione> e = DonazioneFactory.getInstance().getDonazioniEffettuatePerUtente(user);
            
            // Debug
            //System.out.println(Utils.dataCorrente());
            
            request.setAttribute("prenotazione", p.toArray(new Prenotazione[0]));
            request.setAttribute("effettuate", e.toArray(new Donazione[0]));
            
            request.getRequestDispatcher("donazioniEffettuate.jsp").forward(request, response);
            } else{
                response.sendRedirect("login.jsp");
            }
       }
       catch(Exception e){
           request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
            request.setAttribute("link", "index.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response); // Inoltra alla pagina di errore
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
